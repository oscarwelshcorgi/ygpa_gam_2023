<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocApply.jsp
 * @Description : 항만시설사용료면제요청관리
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.09.30    HNJ        최초 생성
 *
 * author HNJ
 * since 2014.09.30
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<%-- <validator:javascript formName="gamSocApply" method="validateGamAssetRent" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamSocApplyDetail" method="validateGamAssetRentDetail" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamSocApplyFile" method="validateGamAssetRentFile" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" /> --%>
<!--
<validator:javascript formName="gamSocApply" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamSocApplyDetail" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamSocApplyFile" staticJavascript="false" xhtml="true" cdata="false" />
 -->
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSocApplyModule() {}

GamSocApplyModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocApplyModule.prototype.loadComplete = function() {

    // 업체신청 면제요청목록 설정
    this.$("#socApplyList").flexigrid({
        module: this,
        url: '<c:url value="/oper/gnrl/gamSelectPrtFcltyRentMngtList.do" />',
        dataType: 'json',
        colModel : [
					{display:'관리청', name:'prtAtCode',width:40, sortable:false,align:'center'},
                    {display:'준공년도', name:'cmplYr',width:55, sortable:false,align:'center'},
                    {display:'공사번호', name:'constNo',width:80, sortable:false,align:'center'},
                    {display:'요청청', name:'appPrtAtCode',width:60, sortable:false,align:'center'},
                    {display:'요청업체', name:'appAgentCode',width:100, sortable:false,align:'left'},
                    {display:'횟수', name:'useNo',width:55, sortable:false,align:'center'},
                    {display:'사용여부', name:'useYn',width:55, sortable:false,align:'center'},
                    {display:'보전요청액', name:'exmpAmnt',width:80, sortable:false,align:'center'},
                    {display:'보전기간시작일', name:'periodFr',width:55, sortable:false,align:'center'},
                    {display:'보전기간종료일', name:'periodTo',width:80, sortable:false,align:'center'},
                    {display:'신청일자', name:'applDate',width:55, sortable:false,align:'center'},
                    {display:'조건', name:'exmpCond',width:80, sortable:false,align:'center'},
                    {display:'보전누계액', name:'exmpAcc',width:80, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            module.$('#totalResultCnt').val(data.totalCount);
            module.$('#totalArea').val(data.sumGrAr);
            module.$('#totalUse').val(data.sumGrFee);
            module.$('#totalGrRdcxptFee').val(data.sumGrRdcxptFee);
            $.each(data.resultList, function() {
            	this.payinstIntrrateDisp = this.payinstIntrrate+ ' %';
            });

            return data;
        }
    });

    // 당해시설 및 타인 사용료 징수 시 해당시설코드 설정
    this.$("#socApplyFcltyList").flexigrid({
        module: this,
        url: '<c:url value="/oper/gnrl/gamSelectPrtFcltyRentMngtDetailList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'삭제', name:'chkApplyFclty',width:40, sortable:false,align:'center', displayFormat:"checkbox"},
                    {display:'시설코드', name:'facCode',width:50, sortable:false,align:'center'},
                    {display:'시설하위코드', name:'facSubCode',width:80, sortable:false,align:'center'},
                    {display:'시설명', name:'facKorNm',width:230, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: true,
        height: '400px'
    });

    // 투자비보전처리대상 요금종류 설정
    this.$("#socApplyChrgeKndList").flexigrid({
        module: this,
        url: '<c:url value="/oper/gnrl/gamSelectPrtFcltyRentMngtFileList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'삭제', name:'chkApplyChrgeKnd', width:40, sortable:true, align:'center', displayFormat:"checkbox"},
                    {display:'요금코드', name:'feeTp', width:100, sortable:true, align:'left'},
                    {display:'요금명', name:'feeTpKorNm', width:280, sortable:true, align:'left'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    this.$("#socApplyList").on('onItemSelected', function(event, module, row, grid, param) {
        module.$('#cmd').val('modify');

        module.$('#gamSocApplyForm :input').val('');

        module.makeFormValues('#gamSocApplyForm', row);
        module._editData=module.getFormValues('#gamSocApplyForm', row);
        module._editRow=module.$('#socApplyList').selectedRowIds()[0];

        //해당하는 자산임대상세 목록과 파일목록를 불러온다.
        module.$('#detailPrtAtCode').val(row['prtAtCode']);
        module.$('#prtAtCodeStr').val(row['prtAtCode']);
        module.$('#detailMngYear').val(row['mngYear']);
        module.$('#detailMngNo').val(row['mngNo']);
        module.$('#detailMngCnt').val(row['mngCnt']);

        if( row['prmisnYn'] == 'Y' ) {
            module.$('#entrpscd').attr('readonly', true);
            module.$('#popupEntrpsInfoInput').attr('disabled', 'disabled');
        }

        var searchOpt=module.makeFormArgs('#gamSocApplyForm');
        module.$('#assetRentDetailList').flexOptions({params:searchOpt}).flexReload();
        module.$('#assetRentFileList').flexOptions({params:searchOpt}).flexReload();

        module.calcFirstPaymentAmount();	//  고지방법에 따른 1회차 사용료 적용
        module.loadEntrpsChargerList();	// 담당자 목록을 불러온다.

//        module.selectFeatureData('assetRentDetail', row, true);

        //this._deleteDataFileList=[]; //삭제파일목록 초기화
    });

    this.$("#assetRentDetailList").on('onItemSelected', function(event, module, row, grid, param) {
        //module.$('#btnApplyGisAssetsCode').prop('disabled', false);
        module.$('#gamSocApplyDetailForm :input').val('');

        module.makeFormValues('#gamSocApplyDetailForm', row);
        module._editData=module.getFormValues('#gamSocApplyDetailForm', row);
        module._editRow=module.$('#assetRentDetailList').selectedRowIds()[0];

        module.loadEntrpsChargerList();	// 담당자 목록을 불러온다.

    });

    this.$("#assetRentFileList").on('onItemSelected', function(event, module, row, grid, param) {
        module.makeFormValues('#gamSocApplyFileForm', row);
        module._editDataFile=module.getFormValues('#gamSocApplyFileForm', row);
        module._editRowFile=module.$('#assetRentFileList').selectedRowIds()[0];

        if(row.filenmPhysicl!=null || row.filenmPhysicl!='') {
            // 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
            var filenm=row['filenmPhysicl'];
            var ext=filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();
            if(ext=='jpg' || ext=='jpeg' || ext=='bmp' || ext=='png' || ext=='gif') {
                $imgURL = module.getImageUrl(filenm);
                module.$("#previewImage").fadeIn(400, function() {
                    module.$("#previewImage").attr('src', $imgURL);
                });
            }
            else {
                module.$("#previewImage").attr('src', '');
            }
        }
    });

    this.$("#socApplyList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#assetRentListTab").tabs("option", {active: 1});
        module.$('#cmd').val('modify');
//        module.$('#gamSocApplyForm :input').val('');	// makeFormValues 에서 값을 지우므로 특별 한 일이 없는 한 각각 지우지 않는다. 클래스 특성을 반영 못 하는 경우가 생긴다

        module.makeFormValues('#gamSocApplyForm', row);
        module._editData=module.getFormValues('#gamSocApplyForm', row);
        module._editRow=module.$('#socApplyList').selectedRowIds()[0];

        if(row!=null) {
            module.$('#cmd').val('modify');
        }

        module.calcFirstPaymentAmount();	//  고지방법에 따른 1회차 사용료 적용
        //this._deleteDataFileList=[]; //삭제파일목록 초기화
    });

    this.$("#assetRentDetailList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#assetRentListTab").tabs("option", {active: 2});
        module.$('#gamSocApplyDetailForm :input').val('');
        module.makeFormValues('#gamSocApplyDetailForm', row);
        module._editData=module.getFormValues('#gamSocApplyDetailForm', row);
        module._editRow=module.$('#assetRentDetailList').selectedRowIds()[0];

        module.loadOlnlpList(row);

        if(row!=null) {
            module.$('#detailCmd').val('modify');
        }
    });

    // 컴포넌트이 이벤트를 추가한다. (기존 코드 데이터에 선택 값이 onchange 안되는 점을 수정 함)
    this.$('#prtAtCode').on('change', {module: this}, function(event) {
        event.data.module.$('#prtAtCodeStr').val($(this).val());
        //alert($(this).getSelectedCodeLabel() + '이(가) 선택되었습니다.');
    });

    this.$('#applcTariff').on('change', {module: this}, function(event) {
        if( $(this).val() == '' ) {
            event.data.module.$('#applcTariffNm').val("");
        } else {
            event.data.module.$('#applcTariffNm').val($(this).getSelectedCodeLabel());
        }
    });

    this.$('#exemptSe').on('change', {module: this}, function(event) {
        event.data.module.$('#exemptSeStr').val($(this).val());

        if( $(this).val() != '1' ) {
            event.data.module.$('#exemptPdFrom').val("");
            event.data.module.$('#exemptPdTo').val("");
        }
    });

    this.$('#applcMth').on('change', {module: this}, function(event) {
        var m = event.data.module;
        m.onApplcMthChange(m.$('#applcMth').val());
    });

	// 국유재산법
	this.$('.nationAssetLaw').show();
	this.$('.tradePortLaw').hide();

    this.$('.calcInput').on('change keyup', {module: this}, function(event) {
        var m = event.data.module;
        m.onCalc();
    });

    this.$('#nticMth').on('change', {module: this}, function(event) {
        //alert($(this).val());
        if( $(this).val() != '' && $(this).val() != '1' ) {
        	event.data.module.$('#cofixList').val( event.data.module.$('#blceStdrIntrrate').val() );
        	event.data.module.$('#payinstIntrrate').val(Number(event.data.module.$('#cofixList').val()) * 100);
        } else {
        	event.data.module.$('#cofixList').val("");
        	event.data.module.$('#payinstIntrrate').val("");
        }
        // calc first payment amount
        event.data.module.calcFirstPaymentAmount();
    });

    this.$('#payinstIntrrate').on('change keyup', {module: this}, function(event) {
        event.data.module.calcFirstPaymentAmount();
    });
    this.$('#cofixList').on('change', {module: this}, function(event) {
    	//alert('||'+$(this).val()+'||');
        if( $(this).val() == '' ) {
        	event.data.module.$('#payinstIntrrate').val("");
        } else {
        	var payinstIntrrateCal = (Number($(this).val()) * 100) + "";

        	if(payinstIntrrateCal.length > 4) {
        		payinstIntrrateCal = payinstIntrrateCal.substring(0,5);
        		payinstIntrrateCal = Number(payinstIntrrateCal).toFixed(2);
        	}

        	event.data.module.$('#payinstIntrrate').val( payinstIntrrateCal );
        	var e = jQuery.Event( "change" );
        	e.data ={module: event.data.module};

        	event.data.module.$('#payinstIntrrate').trigger(e);
        }
    });

    this.$('#exemptRsnCd').on('change', {module: this}, function(event) {
        event.data.module.$('#exemptRsnCdStr').val($(this).val());
    });

    this.$(".photoEditItem").bind("keyup change", {module: this}, function(event) {
    	// // console.log("keyup or change event occur");
    	event.data.module.applyPhotoData();
    });

	var today = new Date();

	var serchYr = today.getFullYear();
	var serchMn = today.getMonth() + 1;

	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}

	var serchday = today.getDate();
	if(serchday < 10){
		serchday = "0" + serchday;
	}
	var searchEndDate = serchYr + "-" + serchMn + "-" + serchday;

	today.setMonth(today.getMonth() - 3);

	serchYr = today.getFullYear();
	serchMn = today.getMonth() + 1;
	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}
	serchday = today.getDate();
	if(serchday < 10){
		serchday = "0" + serchday;
	}
	this.$("#sEntrpscd").bind("keyup change", {module: this}, function(event) {
		if(event.data.module.$('#sEntrpscd').val() ==''){
			event.data.module.$('#sEntrpsNm').val('');
		}

    });

 	var searchOpt=this.makeFormArgs('#gamSocApplySearchForm');
	this.$('#socApplyList').flexOptions({params:searchOpt}).flexReload();
};

GamSocApplyModule.prototype.loadEntrpsChargerList = function() {
	var entrpsCd=this.$('#entrpscd').val();
	this.$('#chargerTlphonNo').text('');
	this.$('#chargerMoblphonNo').text('');
	if(entrpsCd!=null && entrpsCd.length>0) {
		//var loadOpt = [{name: 'entrpscd', value: entrpsCd}];
		var loadOpt = {'entrpscd': entrpsCd};
	    this.doAction('<c:url value="/asset/rent/selectEntrpsChargerList.do" />', loadOpt, function(module, result) {
	    	console.log('charger list load completed');
	        if(result.resultCode=='0') {
		       	 var selectCharger = module.$('#selectCharger');
		       	selectCharger.off('change');
		       	selectCharger.empty();
		       	selectCharger.append('<option value="">선택</option>');
		       	 $.each(result.resultList, function() {
		       		selectCharger.append('<option value="'+this.chargerNo+'" data-moblphonno="'+this.chargerMoblphonNo+'" data-tlphonno="'+this.chargerTlphonNo+'" data-fax="'+this.chargerFax+'" data-email="'+ +this.chargerEmail+'">'+this.chargerNm+'</option>')
		       	 });
		       	selectCharger.on('change', {module: module}, function(event) {
		       		console.log(module);
		       		var sel = $(this).children(':selected');

		       		console.log($(this));
		       		console.log(sel);
		       		var m = event.data.module;
		       		console.log('charger selected : '+sel);
		   			m.$('#chargerNo').val(sel.val());
		   			if(sel.val()!=null && sel.val()!='') {
		   				var tlNo=sel.data('tlphonno');
		   				if(sel.data('fax')!=null && sel.data('fax').length>0) {
		   					tlNo+= ', FAX:'+sel.data('fax');
		   				}
		   				m.$('#chargerTlphonNo').text(tlNo);
		   				m.$('#chargerMoblphonNo').text(sel.data('moblphonno'));
		   			}
		   		 });
		       	var selected = module.$('#chargerNo').val();
		       	if(selected!=null && selected!='') {
		       		selectCharger.val(selected);
		       		var sel = selectCharger.children(':selected');
		   			if(sel.val()!=null && sel.val()!='') {
		   				var tlNo=sel.data('tlphonno');
		   				if(sel.data('fax')!=null && sel.data('fax').length>0) {
		   					tlNo+= ' FAX:'+sel.data('fax');
		   				}
		   				module.$('#chargerTlphonNo').text(tlNo);
		   				module.$('#chargerMoblphonNo').text(sel.data('moblphonno'));
		   			}
		        }
	        }
	    });
	}
};



/**
 * 정의 된 버튼 클릭 시
 */
 GamSocApplyModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
			this.loadData();
            break;

        // 최초신청
        case 'addAssetRentFirst':
            this.$("#assetRentListTab").tabs("option", {active: 1});  // 탭을 전환 한다.
            this.$('#gamSocApplyForm').find(':input').val('');
            this.$('#gamSocApplyDetailForm').find(':input').val('');
            this.$('#gamSocApplyFileForm').find(':input').val('');
            this.$('#selectCharger').empty();
            this.$('#chargerTlphonNo').text('');
        	this.$('#chargerMoblphonNo').text('');

            //this.$("#assetRentDetailList").flexRemove();
            this.$("#assetRentDetailList").flexAddData({resultList:[]}); //그리드 초기화
            this.$("#assetRentFileList").flexAddData({resultList:[]}); //그리드 초기화
            this.$("#cmd").val('insert');

            this.$('#deptcd').val(this.$('#loginOrgnztId').val());
            this.$('#popupEntrpsInfoInput').removeAttr('disabled');
            this.$('#frstReqstDt').val(this.$('#currentDateStr').val());
            this.$('#reqstDt').val(this.$('#currentDateStr').val());

            this.$('#prtAtCode').val('621');
            this.$('#payMth').val('Pre');
            this.$('#nticMth').val('1');
            this.$('#taxtSe').val('2');
            this.$('#applcMth').val('1'); //적용방법
            this.$('#applcTariff').val('0.05'); //적용요율
            this.$('#exemptSe').val('0'); // 면제구분

            break;

        // 연장신청
        case 'addAssetRentRenew':
            var rows = this.$('#socApplyList').selectedRows();

            /* if( rows[0]['quayGroupCd'] != 'P' ) {
                alert("해당 건은 자산임대관리 메뉴에서 연장신청이 불가능합니다.");
                return;
            } */

            if(rows.length>=1) {
                //this.$('#rPrtAtCode').val(row[0]['prtAtCode']);

                if( confirm("연장신청을 하시겠습니까?") ) {
                    this.doAction('<c:url value="/oper/gnrl/gamInsertPrtFcltyRentMngtRenew.do" />', rows[0], function(module, result) {

                        if(result.resultCode=='0') {
                        	module.loadData();
                        }

                        alert(result.resultMsg);
                    });
                //
                }
            } else {
                alert("목록에서 연장신청할 업체를 선택하십시오.");
            }

            break;

        // 신청저장
        case 'btnSaveItem':

        	if(!validateGamAssetRent(this.$('#gamSocApplyForm')[0])) {
                return;
            }

            /* if( this.$("#cmd").val() != 'insert' && this.$('#quayGroupCd').val() != 'P' ) {
                alert("해당 건은 자산임대관리 메뉴에서 저장이 불가능합니다.");
                return;
            } */

            if( this.$('#prtAtCode').val() == '' ) {
                alert("항구분을 선택하십시오.");
                return;
            }

            if( this.$('#entrpscd').val() == '' ) {
                alert("신청업체를 선택하십시오.");
                return;
            }

            if( this.$('#frstReqstDt').val() == '' ) {
                alert("최초신청일자가 없습니다..");
                return;
            }

            if( this.$('#reqstDt').val() == '' ) {
                alert("신청일자가 없습니다..");
                return;
            }

            if( this.$('#payMth').val() == '' ) {
                alert("납부방법을 선택하십시오.");
                return;
            }

            if( this.$('#nticMth').val() == '' ) {
                alert("고지방법을 선택하십시오.");
                return;
            }

            if( this.$('#nticMth').val() == '1' && this.$('#payinstIntrrate').val() != '' && this.$('#payinstIntrrate').val() != '0' ) {
                alert("고지방법이 일괄납부인 경우는 분납이자율을 입력하지 마십시오.");
                return;
            }

            if( this.$('#nticMth').val() != '1' && this.$('#payinstIntrrate').val() == '' ) {
                alert("고지방법이 분납인 경우는 분납이자율을 입력하십시오.");
                return;
            }

            if( confirm("저장하시겠습니까?") ) {
                // 변경된 자료를 저장한다.
                var inputVO=[{name: 'test', value:'test hello'}];
                //var inputVO=[{}];

                //this._editData=this.getFormValues('#gamSocApplyDetailForm', this._editData);

                inputVO[inputVO.length]={name: 'updateList', value :JSON.stringify(this.$('#assetRentDetailList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

                inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#assetRentDetailList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

                inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataList) };

                if(this._deleteDataFileList == undefined ) {
                    this._deleteDataFileList=[];
                }

                inputVO[inputVO.length]={name: 'updateFileList', value :JSON.stringify(this.$('#assetRentFileList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

                inputVO[inputVO.length]={name: 'insertFileList', value: JSON.stringify(this.$('#assetRentFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

                inputVO[inputVO.length]={name: 'deleteFileList', value: JSON.stringify(this._deleteDataFileList) };

                //var otherForm=this.getFormValues('#gamSocApplyForm', {});  // 폼만 있을 경우

                this._editData2=this.getFormValues('#gamSocApplyForm', {_updtId:'I'});
                inputVO[inputVO.length]={name: 'form', value: JSON.stringify(this._editData2) };    // 폼의 데이터를 컨트롤러에 보낸다.

                //// console.log(inputVO);
                // 데이터를 저장 하고 난 뒤 리스트를 다시 로딩 한다.

                this.doAction('<c:url value="/oper/gnrl/gamSavePrtFcltyRentMngt.do" />', inputVO, function(module, result) {
                    if(result.resultCode == 0){
                    	module.loadData();
                    }
                    alert(result.resultMsg);
                });


                this.$("#assetRentListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
            }

            break;

        //신청삭제
        case 'btnRemoveItem':
            var rows = this.$('#socApplyList').selectedRows();

            /* if( rows[0]['quayGroupCd'] != 'P' ) {
                alert("해당 건은 자산임대관리 메뉴에서 삭제가 불가능합니다.");
                return;
            } */

            if(rows.length == 0) {
                alert("자산임대목록에서 신청삭제할 행을 선택하십시오.");
            } else {
            	if( confirm("신청삭제를 하시겠습니까?") ) {
                    if( rows[0]['prmisnYn'] == null || rows[0]['prmisnYn'] == '' ) {
                        this.$('#detailPrmisnYn').val('N');
                    }

                    var inputVO=this.makeFormArgs('#gamSocApplyForm');

                    this.doAction('<c:url value="/oper/gnrl/gamDeletePrtFcltyRentMngt.do" />', inputVO, function(module, result) {

                        if(result.resultCode=='0') {
                        	module.loadData();
                        }

                        alert(result.resultMsg);
                    });

                    this.$("#assetRentListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
                    this.$('#gamSocApplyForm :input').val("");
                    this.$("#cmd").val('insert');
                }
            }

            break;

        //코멘트저장
        case 'btnSaveComment':
            var inputVO=this.makeFormArgs('#gamSocApplyForm');

            /* if( this.$('#quayGroupCd').val() != 'P' ) {
                alert("해당 건은 자산임대관리 메뉴에서 코멘트저장이 불가능합니다.");
                return;
            } */

            this.doAction('<c:url value="/oper/gnrl/gamUpdatePrtFcltyRentMngtComment.do" />', inputVO, function(module, result) {
                if(result.resultCode=='0') {
                }

                alert(result.resultMsg);
            });

            break;

        
        

        case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            /*
            var opts = {
                'gisAssetsPrtAtCode': this.$('#prtAtCode').val(),
                'gisAssetsCd': this.$('#gisAssetsCd').val(),
                'gisAssetsSubCd': this.$('#gisAssetsSubCd').val()
            };
            */
            var opts;

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;

        case 'popupEntrpsInfoInput': // 팝업을 호출한다.(자산임대입력)
            var opts;

            this.doExecuteDialog('insertEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;


        case 'btnPrmisn': // 사용승낙
            var rows = this.$('#socApplyList').selectedRows();
            var row = rows[0];

            if(rows.length>=1) {
            	if( row['prmisnYn'] == 'Y' ) {
                    alert("이미 사용승낙된 상태 입니다.");
                    return;
                }

                if( row['sanctnSttus'] != '1' ) {
                	if(!confirm("결재완료 되지 않았습니다. 결재 처리 되지 않은 자료를 사용승낙을 하시겠습니까?")) {
                        return;
                	}
/*                     alert("결재완료 상태가 아닙니다.");
                    return; */
                }

            	var opts = {
                    'prtAtCode': rows[0]['prtAtCode'],
                    'mngYear': rows[0]['mngYear'],
                    'mngNo': rows[0]['mngNo'],
                    'mngCnt': rows[0]['mngCnt']
                };

                this.doExecuteDialog('insertAssetRentPrmisnPopup', '승낙', '<c:url value="/oper/gnrl/popup/showPrtFcltyRentMngtPrmisn.do"/>', opts);

            } else {
                alert("목록에서 선택하십시오.");
            }

            break;


        case 'btnPrmisnCancel': // 승낙취소
            var rows = this.$('#socApplyList').selectedRows();
            var row = this.$('#socApplyList').selectedRows()[0];

            /* if( rows[0]['quayGroupCd'] != 'P' ) {
                alert("해당 건은 자산임대관리 메뉴에서 승낙취소가 불가능합니다.");
                return;
            } */

            if( row['prmisnYn'] != 'Y' ) {
                alert("승낙된 상태가 아닙니다.");
                return;
            }

            if(rows.length>=1) {
                if( confirm("승낙취소를 하시겠습니까?") ) {
                    this.doAction('<c:url value="/oper/gnrl/gamUpdatePrtFcltyRentMngtPrmisnCancel.do" />', rows[0], function(module, result) {
                        if(result.resultCode=='0') {
                        	 module.loadData();
                        }

                        alert(result.resultMsg);
                    });
                }
            } else {
                alert("목록에서 선택하십시오.");
            }

            break;

        case 'btnMangeCharger': // 업체정보관리
       	 	EMD.util.create_window('업체정보 관리', '<c:url value="/code/gamCmpyInfoMngt.do"/>', null, {entrpscd:this.$('#entrpscd').val()});
        	break;

    }
};

GamSocApplyModule.prototype.applyPhotoData = function() {

	if(!validateGamAssetRentFile(this.$('#gamSocApplyFileForm')[0])) {
        return;
    }

	var selectRow = this.$('#assetRentFileList').selectedRows();
	if(selectRow.length > 0) {

    var row=selectRow[0];
	  		var rowid=this.$("#assetRentFileList").selectedRowIds()[0];
	  		row=this.getFormValues('#gamSocApplyFileForm', row);
        if(row["_updtId"]!='I') row["_updtId"]='U';   // 삽입된 데이터가 아니면 업데이트 플래그를 추가한다.
        this.$('#assetRentFileList').flexUpdateRow(rowid, row);
    }
    else {
    }
};

GamSocApplyModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamSocApplyModule.prototype.loadData = function() {
    this.$("#assetRentListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamSocApplySearchForm');
    this.$('#socApplyList').flexOptions({params:searchOpt}).flexReload();
	// console.log('debug');

};

GamSocApplyModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        var row = this.$('#socApplyList').selectedRows();
        if(row.length==0) {
            this.$('#cmd').val('insert');
        }
        else {
            this.$('#cmd').val('modify');
        }

        if(oldTabId=='tabs1') {
        	this._deleteDataList=[];    // 삭제 목록 초기화
        	this._deleteDataFileList=[];    // 파일삭제 목록 초기화
        }
        break;
    case 'tabs3':
        var row = this.$('#assetRentDetailList').selectedRows();
        if(row.length==0) {
            this.$('#detailCmd').val('insert');
            this._selectAssetsCd={};	// 데이터 추가시 빈 값을 입력 한다.
        }
        else {
            this.$('#detailCmd').val('modify');
            this._selectAssetsCd=row;
			this.onApplcMthChange(row[0]['applcMth']);
        }
        break;

    case 'tabs4':

        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamSocApplyModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     case 'selectEntrpsInfoPopup':
         if (msg != 'cancel') {
             this.$('#sEntrpscd').val(value.entrpscd);
             this.$('#sEntrpsNm').val(value.entrpsNm);
			 this.loadData();
         } else {
             alert('취소 되었습니다');
         }
         break;
     case 'insertEntrpsInfoPopup':
         if (msg != 'cancel') {
             this.$('#entrpscd').val(value.entrpscd);
             this.$('#entrpsNm').val(value.entrpsNm);
             this.loadEntrpsChargerList();	// 담당자 목록을 불러온다.
         } else {
             alert('취소 되었습니다');
         }
         break;
     case 'insertAssetRentPrmisnPopup':
         if (msg != 'cancel') {
             if( value == "0" ) {
					this.loadData();
             }
         } else {
             alert('취소 되었습니다');
         }
         break;
     case 'insertLevReqestAdit':
    	 break;
     case 'selectAssetsCdRentPopup':
         if (msg != 'cancel') {
             this.$('#gisAssetsPrtAtCode').val(value.gisAssetsPrtAtCode);
             this.$('#gisAssetsCd').val(value.gisAssetsCd);
             this.$('#gisAssetsSubCd').val(value.gisAssetsSubCd);
             this.$('#gisAssetsNm').val(value.gisAssetsNm);
             this.$('#gisAssetsLocplc').val(value.gisAssetsLocplc);
             this.$('#gisAssetsLnm').val(value.gisAssetsLnm);
             this.$('#gisAssetsLnmSub').val(value.gisAssetsLnmSub);
             this.$('#gisAssetsAr').val(value.gisAssetsAr);
             this.$('#gisAssetsRealRentAr').val(value.gisAssetsRealRentAr);
             this.$('#gisAssetsPrtAtCodeNm').val(value.gisAssetsPrtAtCodeNm);

             this.$('#usageAr').val(value.gisAssetsRealRentAr);

             //this.$('#quayCd').val(value.gisAssetsQuayCd);
             this.$('#assetsCdStr').val(value.gisAssetsCd + "-" + value.gisAssetsSubCd);
             this._selectAssetsCd=value;

             this.loadOlnlpList(value);
         } else {
             alert('취소 되었습니다');
         }
         break;

     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');

         break;
     }
};

GamSocApplyModule.prototype.loadOlnlpList = function(prtFcltyCd) {
    this.doAction('<c:url value="/asset/rent/selectOlnlpInfo.do" />', prtFcltyCd, function(module, result) {
        if(result.resultCode=='0') {
       	 var olnlplist = module.$('#olnlpList');
       	 olnlplist.off('change');
       	 olnlplist.empty();
   		 olnlplist.append('<option value="">공시지가선택</option>')
       	 $.each(result.resultList, function() {
       		 olnlplist.append('<option value="'+this.olnlp+'">'+this.olnlpApplyDates+'</option>')
       	 });
   		 olnlplist.on('change', {module: module}, function(event) {
   			 event.data.module.$('#olnlp').val($.number($(this).children(':selected').val()));
   			event.data.module.onCalc();
   		 });
   		olnlplist.find('option:eq(1)').attr("selected","selected");
   		module.$('#olnlp').val(olnlplist.find('option:eq(1)').val());
        }
    });
}

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSocApplyModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocApplySearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>*공사관리청코드</th>
                            <td>
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">전체</option>
                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>*공사준공년도</th>
                            <td width="250px">
                                <select id="sCmplYr">
                                    <option value="" selected="selected">년</option>
                                    <c:forEach  items="${yearsList}" var="yearsItem">
                                        <option value="${yearsItem }">${yearsItem }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>*공사일련번호</th>
                            <td>
                            	<input id="sConstNo" type="text" size="15">
                            </td>
                            <td style="text-align:right;">
				                <button id="popupSocAgentFInfo" class="popupButton">허가원부선택</button>
                            </td>
                            <td  rowSpan="2">
								<button id="searchBtn" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                        <tr>
                            <th>*면제요청청코드</th>
                            <td>
                                <select id="sAppPrtAtCode">
                                    <option value="" selected="selected">전체</option>
                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>*면제요청업체코드</th>
                            <td>
                                <input id="sAppAgentCode" type="text" size="5">&nbsp; &nbsp;
                            	<input id="sAppAgentName" type="text" size="10" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <th>*요청횟수</th>
                            <td>
                            	<input id="sUseNo" type="text" size="15">
                            </td>
                            <td style="text-align:right;">
				                <button id="popupReqExemption" class="popupButton">면제요청</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">업체신청 면제요청목록</a></li>
                <li><a href="#tabs2" class="emdTab">업체신청 면제요청내역</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
            	<div class="emdControlPanel">
					<form id="form1">
    	               	<table class="detailForm"  style="width:100%;">
                            <tr>
                                <th width="16%">보전처리기간</th>
                                <td>
									<input id="periodFr" type="text" class="emdcal" size="20"> ~ <input id="periodTo" type="text" class="emdcal" size="20">
                                </td>
                                <th width="16%">*보전처리조건</th>
                                <td>
                                	<select id="exmpCond">
	                                    <option value="1" selected="selected">금액</option>
										<option value="2">기간</option>
	                                </select>
                                </td>
                            </tr>
                            <tr>
                            	<th width="16%">*공사업체</th>
                                <td>
                                	<input id="agentCode" type="text" size="10">&nbsp; &nbsp;
	                            	<input id="agentName" type="text" size="15" disabled="disabled">&nbsp; &nbsp;
	                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                                </td>
                                <th width="16%">*적용요율</th>
                                <td>
                                	<select id="rateGubun">
	                                    <option value="1" selected="selected">과거</option>
										<option value="2">현재</option>
	                                </select>
                                </td>
                            </tr>
                            <tr>
                                <th width="16%">특이사항</th>
                                <td colSpan="3"><input type="text" id="remark" size="120"></td>
                            </tr>
                            <tr>
                                <th width="16%">*공사명칭</th>
                                <td><input type="text" id="item" size="55"></td>
                                <th width="16%">*사용여부</th>
                                <td>
                                	<select id="useYn">
	                                    <option value="Y" selected="selected">보전처리개시</option>
										<option value="N">보전처리중지</option>
	                                </select>
                                </td>
                            </tr>
                            <tr>
                                <th width="16%">사업자등록번호</th>
                                <td><input input="text" id="bzRgstId" size="55" ></td>
                                <th width="16%">신청일자</th>
                                <td><input id="applDate" type="text" class="emdcal" size="20"></td>
                            </tr>
                        </table><br>
                        <table class="detailForm"  style="width:100%;">
                            <tr>
                                <th width="16%">외항접안</th>
                                <td><input type="text" id="r11Rate"></td>
                                <th width="16%">내항접안</th>
                                <td><input type="text" id="r12Rate"></td>
                                <th width="16%">외항정박</th>
                                <td><input type="text" id="r21Rate"></td>
                                <th width="16%">내항정박</th>
                                <td><input type="text" id="r22Rate"></td>
                            </tr>
                        </table>
					</form>
                </div>
                <table id="socApplyList" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel">
					<form id="form1">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="12%" height="20">자료수</th>
								<td><input type="text" size="15" id="totalResultCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">보전처리요청액</th>
								<td><input type="text" size="32" id="totalExmpAmnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">보전처리누계액</th>
								<td><input type="text" size="32" id="totalExmpAcc" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
    	               	<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                                <button id="btnSaveItem">저장</button>
	                                <button id="btnRemoveItem">삭제</button>
	                                <button id="btnPrintItem">출력</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamSocApplyForm">
                    	<table style="width:100%;">
                    		<tr>
                    			<td width="50%" style="font-weight: bold;"> > 당해시설 및 타인 사용료 징수시 해당시설코드(R1,R2 대상시설)</td>
                    			<td style="font-weight: bold;"> > 투자비보전처리대상 요금종류</td>
                    		</tr>
        	               	<tr>
								<td style="padding-right:15px;">
									<table id="socApplyFcltyList" style="display:none" class="fillHeight"></table>
			                        <table style="width:100%;">
				                        <tr>
				                            <td style="text-align: right">
				                                <button id="btnAddFcltyItem">행추가</button>
				                                <button id="btnRemoveFcltyItem">행삭제</button>
				                            </td>
				                        </tr>
									</table>
								</td>
								<td style="padding-right:15px;">
									<table id="socApplyChrgeKndList" style="display:none" class="fillHeight"></table>
			                        <table style="width:100%;">
				                        <tr>
				                            <td style="text-align: right">
				                                <button id="btnAddApplyChrgeKndItem">행추가</button>
				                                <button id="btnRemoveApplyChrgeKndItem">행삭제</button>
				                            </td>
				                        </tr>
									</table>
								</td>
							</tr>
						</table>
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right;padding-right:15px;">
	                                <button id="btnSaveDetailItem">저장</button>
	                            </td>
	                        </tr>
						</table>
                    </form>
                 </div>
            </div>
        </div>
    </div>
</div>