<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltsMngFeeMng.jsp
  * @Description : 시설물 관리비 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.19  Lee     최초 생성
  *
  * author lee
  * since 2014.11.19  Lee
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamFcltsMngFeeMng" method="validateGamFcltsMngFeeMng" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamFcltsMngFeeMngDetail" method="validateGamFcltsMngFeeMngDetail" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamFcltsMngFeeMngFile" method="validateGamFcltsMngFeeMngFile" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltsMngFeeMngModule() {}

GamFcltsMngFeeMngModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltsMngFeeMngModule.prototype.loadComplete = function() {

    // 시설물 관리비 관리 테이블 설정
    this.$("#FcltsMngFeeMngList").flexigrid({
        module: this,
        url: '/mngFee/gamSelectFcltsMngFeeMng.do',
        dataType: 'json',
        colModel : [
					{display:'관리 월', name:'mngMt',width:80, sortable:false,align:'center'},
                    {display:'관리비 업무 구분', name:'mngFeeJobSe',width:100, sortable:false,align:'center'},
                    {display:'관리비 제목', name:'mngFeeSj',width:100, sortable:false,align:'center'},
                    {display:'시설 관리 용역비', name:'fcltyMngFee',width:160, sortable:false,align:'center'},
                    {display:'전기 요금', name:'elctyFee',width:80, sortable:false,align:'left' ,displayFormat: 'number'},
                    {display:'상하수도 요금', name:'waterFee',width:100, sortable:false,align:'left' ,displayFormat: 'number'},
                    {display:'도시가스 요금', name:'gasFee',width:100, sortable:false,align:'left' ,displayFormat: 'number'},
                    {display:'환경개선 부담금', name:'envFee',width:100, sortable:false,align:'left' ,displayFormat: 'number'},
                    {display:'관리비 합계', name:'mngTotalFee',width:100, sortable:false,align:'left' ,displayFormat: 'number'}
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

    // 자산임대상세 테이블 설정
    this.$("#FcltsMngFeeMngDetailList").flexigrid({
        module: this,
        url: '/mngFee/gamSelectFcltsMngFeeMngDetailList.do',
        dataType: 'json',
        colModel : [
                    {display:'업체코드', name:'entrpscd',width:80, sortable:false,align:'center'},
                    {display:'사용 면적', name:'usageAr',width:80, sortable:false,align:'center'},
                    {display:'관리비', name:'mngFee',width:60, sortable:false,align:'center' ,displayFormat: 'number'},
                    {display:'전기 요금', name:'elctyFee',width:120, sortable:false,align:'center' ,displayFormat: 'number'},
                    {display:'상하수도 요금', name:'waterFee',width:120, sortable:false,align:'center' ,displayFormat: 'number'},
                    {display:'도시가스 요금', name:'gasFee',width:120, sortable:false,align:'center' ,displayFormat: 'number'},
                    {display:'관리비 합계', name:'mngTotalFee',width:120, sortable:false,align:'center' ,displayFormat: 'number'}
                    ],
        showTableToggleBtn: true,
        height: '102'
    });

    // 첨부파일 테이블 설정
    this.$("#FcltsMngFeeMngFileList").flexigrid({
        module: this,
        url: '/mngFee/gamSelectFcltsMngFeeMngFileList.do',
        dataType: 'json',
        colModel : [
                    {display:'순번', name:'photoSeq', width:80, sortable:true, align:'center'},
                    {display:'사진제목', name:'photoSj', width:200, sortable:true, align:'left'},
                    {display:'사진파일명', name:'filenmLogic', width:200, sortable:true, align:'left'},
                    {display:'사진설명', name:'photoDesc', width:250, sortable:true, align:'left'},
                    {display:'촬영일자', name:'shotDt', width:100, sortable:true, align:'center'}

                    ],
        showTableToggleBtn: false,
        height: '160'
    });

    this.$("#FcltsMngFeeMngList").on('onItemSelected', function(event, module, row, grid, param) {
        module.$('#cmd').val('modify');

        module.$('#gamFcltsMngFeeMngForm :input').val('');

        module.makeFormValues('#gamFcltsMngFeeMngForm', row);
        module._editData=module.getFormValues('#gamFcltsMngFeeMngForm', row);
        module._editRow=module.$('#FcltsMngFeeMngList').selectedRowIds()[0];

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

        var searchOpt=module.makeFormArgs('#gamFcltsMngFeeMngForm');
        module.$('#FcltsMngFeeMngDetailList').flexOptions({params:searchOpt}).flexReload();
//         module.$('#FcltsMngFeeMngFileList').flexOptions({params:searchOpt}).flexReload();

//         module.calcFirstPaymentAmount();	//  고지방법에 따른 1회차 사용료 적용
//         module.loadEntrpsChargerList();	// 담당자 목록을 불러온다.

        //this._deleteDataFileList=[]; //삭제파일목록 초기화
    });

    this.$("#FcltsMngFeeMngDetailList").on('onItemSelected', function(event, module, row, grid, param) {
    	console.log(module);
        module.$('#gamFcltsMngFeeMngDetailForm :input').val('');

        module.makeFormValues('#gamFcltsMngFeeMngDetailForm', row);
        module._editData=module.getFormValues('#gamFcltsMngFeeMngDetailForm', row);
        module._editRow=module.$('#FcltsMngFeeMngDetailList').selectedRowIds()[0];

        module.loadEntrpsChargerList();	// 담당자 목록을 불러온다.

    });

    this.$("#FcltsMngFeeMngFileList").on('onItemSelected', function(event, module, row, grid, param) {
        module.makeFormValues('#gamFcltsMngFeeMngFileForm', row);
        module._editDataFile=module.getFormValues('#gamFcltsMngFeeMngFileForm', row);
        module._editRowFile=module.$('#FcltsMngFeeMngFileList').selectedRowIds()[0];

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

    this.$("#FcltsMngFeeMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#FcltsMngFeeMngListTab").tabs("option", {active: 1});
        module.$('#cmd').val('modify');
//      module.$('#gamFcltsMngFeeMngForm :input').val('');	// makeFormValues 에서 값을 지우므로 특별 한 일이 없는 한 각각 지우지 않는다. 클래스 특성을 반영 못 하는 경우가 생긴다

        module.makeFormValues('#gamFcltsMngFeeMngForm', row);
        module._editData=module.getFormValues('#gamFcltsMngFeeMngForm', row);
        module._editRow=module.$('#FcltsMngFeeMngList').selectedRowIds()[0];

        if(row!=null) {
            module.$('#cmd').val('modify');
        }

//         module.calcFirstPaymentAmount();	//  고지방법에 따른 1회차 사용료 적용
        //this._deleteDataFileList=[]; //삭제파일목록 초기화
    });

    this.$("#FcltsMngFeeMngDetailList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#FcltsMngFeeMngListTab").tabs("option", {active: 2});
        module.$('#gamFcltsMngFeeMngDetailForm :input').val('');
        module.makeFormValues('#gamFcltsMngFeeMngDetailForm', row);
        module._editData=module.getFormValues('#gamFcltsMngFeeMngDetailForm', row);
        module._editRow=module.$('#FcltsMngFeeMngDetailList').selectedRowIds()[0];

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
/*     	if( $(this).val() == '1' ) {
    		event.data.module.$('#applcTariffStr').val("0.05");
    	} else if( $(this).val() == '2' ) {
    		event.data.module.$('#applcTariffStr').val("0.025");
    	} else if( $(this).val() == '3' ) {
    		event.data.module.$('#applcTariffStr').val("0.01");
    	} else {
    		event.data.module.$('#applcTariffStr').val("");
    	}
 */
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
//         event.data.module.calcFirstPaymentAmount();
    });

    this.$('#payinstIntrrate').on('change keyup', {module: this}, function(event) {
//         event.data.module.calcFirstPaymentAmount();
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

    this.$("#sEntrpscd").bind("keyup change", {module: this}, function(event) {
		if(event.data.module.$('#sEntrpscd').val() ==''){
			event.data.module.$('#sEntrpsNm').val('');
		}
    });

    this.$('.sMngMt').on('change', {module: this}, function(event) {
		if(!event.data.module.$('#usageMtYear').val() =='' && !event.data.module.$('#usageMtMon').val() ==''){
			event.data.module.$('#usageMt').val(event.data.module.$('#usageMtYear').val()+event.data.module.$('#usageMtMon').val());
		}else{
			event.data.module.$('#usageMt').val('');
			return;
		}
    });
};

GamFcltsMngFeeMngModule.prototype.loadEntrpsChargerList = function() {
	var entrpsCd=this.$('#entrpscd').val();
	this.$('#chargerTlphonNo').text('');
	this.$('#chargerMoblphonNo').text('');
	if(entrpsCd!=null && entrpsCd.length>0) {
		//var loadOpt = [{name: 'entrpscd', value: entrpsCd}];
		var loadOpt = {'entrpscd': entrpsCd};
	    this.doAction('/asset/rent/selectEntrpsChargerList.do', loadOpt, function(module, result) {
	    	console.log('charger list load completed');
	        if(result.resultCode=='0') {
		       	 var selectCharger = module.$('#selectCharger');
		       	selectCharger.off('change');
		       	selectCharger.empty();
		       	selectCharger.append('<option value="">선택</option>')
		       	 $.each(result.resultList, function() {
		       		selectCharger.append('<option value="'+this.chargerNo+'" data-moblphonno="'+this.chargerMoblphonNo+'" data-tlphonno="'+this.chargerTlphonNo+'" data-fax="'+this.chargerFax+'" data-email="'+ +this.chargerEmail+'">'+this.chargerNm+'</option>')
		       	 });
		       	selectCharger.on('change', {module: module}, function(event) {
		       		var sel = $(this).children(':selected');
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


GamFcltsMngFeeMngModule.prototype.calcFirstPaymentAmount = function() {
	var firstAmt=0;
	var nticMth=this.$('#nticMth').val();
	var totalAmount=Number(this.$('#grFee').val().replace(/,/gi, ""));
	var payinstIntrrate=Number(this.$('#payinstIntrrate').val().replace(/,/gi, ""))/100;
    var grUsagePdFrom = new Date(Date.parse(this.$('#grUsagePdFrom').val())); //총사용기간FROM
    var grUsagePdTo = new Date(Date.parse(this.$('#grUsagePdTo').val())); //총사용기간To
    var fromDt, toDt;
	var totalMonths = grUsagePdTo.getMonth() - grUsagePdFrom.getMonth()+1;
	var totalDays = Math.floor((grUsagePdTo-grUsagePdFrom) / (1000*60*60*24))+1;
    var nDays;
//    console.log("calc Start : "+ nticMth);
	switch(nticMth) {
	case '2':	// 반기납
		fromDt = grUsagePdFrom;
		toDt = new Date(fromDt);
		toDt.setMonth(fromDt.getMonth()+6);
//		toDt.setDate(1);
		toDt=toDt-(1000*60*60*24);
		if(6>=totalMonths) firstAmt=totalAmount;
		else {
			//nDays = Math.floor((toDt-fromDt) / (1000*60*60*24))+1;
			//firstAmt=Math.floor(totalAmount*nDays/totalDays/10)*10;
			firstAmt = totalAmount*6/totalMonths;
		}
		// 이자율 계산
		firstAmt += (totalAmount-firstAmt) * (payinstIntrrate) /2;
		firstAmt = Math.floor(firstAmt);
		break;
	case '3':	// 3분납
		fromDt = grUsagePdFrom;
		toDt = new Date(fromDt);
		if(grUsagePdFrom.getMonth()<4) {
			toDt.setMonth(4);
		}
		else if(grUsagePdFrom.getMonth()<8) {
			toDt.setMonth(8);
		}
		else  {
			toDt.setFullYear(toDt.getFullYear()+1);
			toDt.setMonth(0);
		}
//		toDt.setDate(1);
		toDt=toDt-(1000*60*60*24);
		if(4>=totalMonths) firstAmt=totalAmount;
		else {
//			nDays = Math.floor((toDt-fromDt) / (1000*60*60*24))+1;
			firstAmt=totalAmount*4/totalMonths;
		}
		// 이자율 계산
		firstAmt += (totalAmount-firstAmt) * (payinstIntrrate) /3;
		firstAmt = Math.floor(firstAmt);
		break;
	case '4':	// 분기납
		fromDt = grUsagePdFrom;
		toDt = new Date(fromDt);
		if(grUsagePdFrom.getMonth()<3) {
			toDt.setMonth(3);
		}
		else if(grUsagePdFrom.getMonth()<6) {
			toDt.setMonth(6);
		}
		else if(grUsagePdFrom.getMonth()<9) {
			toDt.setMonth(9);
		}
		else {
			toDt.setFullYear(toDt.getFullYear()+1);
			toDt.setMonth(0);
		}
//		toDt.setDate(1);
		toDt=toDt-(1000*60*60*24);
		if(3>=totalMonths) firstAmt=totalAmount;
		else {
			nDays = Math.floor((toDt-fromDt) / (1000*60*60*24))+1;
			firstAmt=Math.floor(totalAmount*3/totalMonths);
		}
		firstAmt += (totalAmount-firstAmt) * (payinstIntrrate) /4;
//		firstAmt = Math.floor(firstAmt/10)*10;
		break;
	case '5':	// 월납
		fromDt = grUsagePdFrom;
		toDt = new Date(fromDt);
		toDt.setMonth(grUsagePdFrom.getMonth()+1);
//		toDt.setDate(1);
		toDt=toDt-(1000*60*60*24);
		if(1>=totalMonths) firstAmt=totalAmount;
		else {
			nDays = Math.floor((toDt-fromDt) / (1000*60*60*24))+1;
			firstAmt=Math.floor(totalAmount*1/totalMonths);
//			firstAmt=totalAmount*1-firstAmt
		}
		firstAmt += (totalAmount-firstAmt) * (payinstIntrrate) /12;
		firstAmt = Math.floor(firstAmt);
		break;
	default:
		firstAmt=totalAmount;
		fromDt=grUsagePdFrom;
		toDt=grUsagePdFrom;
		nDays = Math.floor((grUsagePdTo-grUsagePdFrom) / (1000*60*60*24))+1;
		break;
	}
    console.log("calc first Amount : "+ firstAmt);

	this.$('#firstPayVal').val($.number(firstAmt));
	//this.$('#firstPayValStr').text('산출내역 : 사용기간 ['+EMD.util.getDate(fromDt)+'~'+EMD.util.getDate(toDt)+'] : 사용일수 : '+$.number(nDays)+' 일/ 전체일수 : ' +$.number(totalDays)+' 일 * 사용료 : '+$.number(totalAmount)+'원');
};

GamFcltsMngFeeMngModule.prototype.onApplcMthChange = function(applcMth) {
	console.log("applc mth change");
	switch(applcMth) {
	case '1':	// 국유재산법
		this.$('.nationAssetLaw').show();
		this.$('.tradePortLaw').hide();
		break;
	case '2':	// 공사규정
		this.$('.nationAssetLaw').show();
		this.$('.tradePortLaw').hide();
		break;
	case '3':	// 입찰
		this.$('.nationAssetLaw').show();
		this.$('.tradePortLaw').hide();
		break;
	case '4':	// 무역항규정
	case '6':	// 배후단지
		this.$('.nationAssetLaw').hide();
		this.$('.tradePortLaw').show();
		break;
	case '5':	// 임대계약서
		this.$('.nationAssetLaw').show();
		this.$('.tradePortLaw').hide();
		this.$('#applcTariff').val('0');
//		this.$('#applcTariff_select').f('0');
		break;
	default:	// 기타
		this.$('.nationAssetLaw').show();
		this.$('.tradePortLaw').hide();
		break;
	}
    this.onCalc();
}

GamFcltsMngFeeMngModule.prototype.calcNationAssetLaw = function() {
    if( this.$('#olnlp').val() != '' && this.$('#usagePdFrom').val() != '' && this.$('#usagePdTo').val() != ''
        && this.$('#usageAr').val() != '' && this.$('#applcTariff').val() != '' && this.$('#exemptSe').val() != ''
    ) {
        var calFee      = 0;  //계산된 사용료
        var olnlp       = 0;  //공시지가
        var usageAr     = 0;  //사용면적
        var applcTariff = 0;  //적용요율(계산용)
        var applcTariffStr = "";  //적용요율
        var rdcxptFee   = 0;  //감면사용료
        var dayUseCnt   = 0;  //사용일수
        var usagePdFrom = ""; //사용기간 from
        var usagePdTo   = ""; //사용기간 to
        var exemptPdFrom = "";    // 면제기간
        var exemptPdTo = "";      // 면제기간
        var exemptCnt   = 0;      // 면제일수
        var exemptSe    = ""; //면제구분 0:면제없음, 1:일부면제, 2:전체면제

        olnlp = this.$('#olnlp').val().replace(/,/g, '')*1;
        usageAr = Number(this.$('#usageAr').val().replace(/,/g, ''));
        applcTariff = Number(this.$('#applcTariff').val().replace(/,/g, ''));
        applcTariffStr = this.$('#applcTariff').getSelectedCodeLabel();
        usagePdFrom = this.$('#usagePdFrom').val();
        usagePdTo = this.$('#usagePdTo').val();
        exemptPdFrom = this.$('#exemptPdFrom').val();
        exemptPdTo = this.$('#exemptPdTo').val();
        exemptSe = this.$('#exemptSe').val();

        var monfee=Math.round(olnlp*applcTariff/12);

        if( exemptSe == '1' ) {        // 일부면제
              if( exemptPdFrom == '' ) {
                  return;
              }
              if( exemptPdTo == '' ) {
                  return;
              }
        }

        if( exemptSe == '0' ) {               // 면제없음.
            rdcxptFee = 0;
        } else if( exemptSe == '1' ) {   // 일부면제
        	var dtFr = EMD.util.strToDate(exemptPdFrom);
        	var dtTo = EMD.util.strToDate(exemptPdTo);

        	var days = Math.round(Math.abs((dtTo-dtFr)/(1000*60*60*24)));

            /* 면제 일수 계산 */
            exemptCnt = Number(days);

            exemptMonths = this.calcMonth(dtFr, dtTo);

            rdcxptFee = monfee*usageAr*exemptMonths.month+monfee*usageAr*exemptMonths.day/exemptMonths.lastMonthDay;
        }

        /* 날짜계산 */

        var dtFr = EMD.util.strToDate(usagePdFrom);
        var dtTo = EMD.util.strToDate(usagePdTo);

        usageMonths = this.calcMonth(dtFr, dtTo);

        //(사용료 = 공시지가*((사용일수)/365)*사용면적)*적용요율 ? 감면사용료 )
        if( exemptSe == '2' ) {     // 전체면제 일 경우 사용료는 0
        	rdcxptFee = calFee;
        	exemptCnt = dayUseCnt;
            calFee = 0;
        } else {
            calFee = monfee*usageAr*usageMonths.month+monfee*usageAr*usageMonths.day/usageMonths.lastMonthDay - rdcxptFee;
        }
        var calcStr="";
        if(usageMonths.month) {
        	if(usageMonths.day) {
        		calcStr="( 공시지가("+$.number(olnlp, false)+"원)*적용요율("+applcTariff*1000+"/1000)*사용면적("+$.number(usageAr, false)+"m²)*(사용개월수("+$.number(usageMonths.month, false)+"개월 "+usageMonths.day+"일/"+usageMonths.lastMonthDay+")";
        	}
        	else {
        		calcStr="( 공시지가("+$.number(olnlp, false)+"원)*적용요율("+applcTariff*1000+"/1000)*사용면적("+$.number(usageAr, false)+"m²)*(사용개월수("+$.number(usageMonths.month, false)+"개월)";
        	}
        }
        else {
    		calcStr="( 공시지가("+$.number(olnlp, false)+"원)*적용요율("+applcTariff*1000+"/1000)*사용면적("+$.number(usageAr, false)+"m²)*(사용개월수("+$.number(usageMonths.month, false)+"개월)";

        }
        if(rdcxptFee>0) {
			if(exemptMonths.month) {
				if(exemptMonths.day) {
		    		calcStr+="-( 면제개월수("+exemptMonths.month+"개월 "+exemptMonths.day+"일/"+exemptMonths.lastMonthDay+")";

				}
				else {
		    		calcStr+="-( 면제개월수("+exemptMonths.month+"개월)";
				}
			}
			else {
	    		calcStr+="-( 면제일수("+exemptMonths.day+"일/"+exemptMonths.lastMonthDay+")";
			}
			calcStr += " 면제 금액 : "+$.number(rdcxptFee, false)+"원)";
        }
        calcStr += " )";
    	this.$('#computDtls').val(calcStr);

        calFee = Math.ceil(calFee/10)*10;
        rdcxptFee = Math.ceil(rdcxptFee/10)*10;

        this.$('#fee').val($.number(calFee));
        this.$('#rdcxptFee').val($.number(rdcxptFee));
    } else {
        var applcTariff = Number(this.$('#applcTariff').val().replace(/,/g, ''));
		if(applcTariff!=0) {
	    	this.$('#fee').val('');
	        this.$('#rdcxptFee').val('');
		}
    }

};

GamFcltsMngFeeMngModule.prototype.calcMonth = function(dtFrom, dtTo) {
	var retval={month: 0, day: 0, lastMonthDay: 31};

	var months = 0;
	var dtCurr=new Date(dtFrom);

	dtTo.setDate(dtTo.getDate()+1);	// 날짜를 하루 뒤로 계산 한다.
	dtCurr.setMonth(dtCurr.getMonth()+1);
	for(;dtCurr<=dtTo;) {
		months++;
		dtCurr.setMonth(dtCurr.getMonth()+1);
	}
	var newMonth = new Date(dtCurr.getFullYear(), dtCurr.getMonth(), 1, 0, 0, 0);

	retval.lastMonthDay=new Date(newMonth-24*60*60*1000).getDate();

	dtCurr.setMonth(dtCurr.getMonth()-1);

	retval.month=months;
	retval.day = Math.floor(Math.abs((dtTo-dtCurr)/(1000*60*60*24)));

	return retval;
}

GamFcltsMngFeeMngModule.prototype.calcTradePortLaw = function() {
    if( this.$('#usagePdFrom').val() != '' && this.$('#usagePdTo').val() != ''
        && this.$('#usageAr').val() != '' && this.$('#applcPrice').val() != '' && this.$('#exemptSe').val() != ''
    ) {
        var calFee      = 0;  //계산된 사용료
        var olnlp       = 0;  //공시지가
        var usageAr     = 0;  //사용면적
        var applcPrice = 0;  //적용단가
        var rdcxptFee   = 0;  //감면사용료
        var dayUseCnt   = 0;  //사용일수
        var usagePdFrom = ""; //사용기간 from
        var usagePdTo   = ""; //사용기간 to
        var exemptPdFrom = "";    // 면제기간
        var exemptPdTo = "";      // 면제기간
        var exemptMonths={};      // 면제개월 수
        var exemptSe    = ""; //면제구분 0:면제없음, 1:일부면제, 2:전체면제

        usageAr = Number(this.$('#usageAr').val().replace(/,/g, ''));
        applcPrice = Number(this.$('#applcPrice').val().replace(/,/g, ''));
        usagePdFrom = this.$('#usagePdFrom').val();
        usagePdTo = this.$('#usagePdTo').val();
        exemptPdFrom = this.$('#exemptPdFrom').val();
        exemptPdTo = this.$('#exemptPdTo').val();
        exemptSe = this.$('#exemptSe').val();

        if( exemptSe == '1' ) {        // 일부면제
              if( exemptPdFrom == '' ) {
                  return;
              }
              if( exemptPdTo == '' ) {
                  return;
              }
        }

        if( exemptSe == '0' ) {               // 면제없음.
            rdcxptFee = 0;
        } else if( exemptSe == '1' ) {   // 일부면제
        	var dtFr = EMD.util.strToDate(exemptPdFrom);
        	var dtTo = EMD.util.strToDate(exemptPdTo);

        	var days = Math.round(Math.abs((dtTo-dtFr)/(1000*60*60*24)));

            /* 면제 일수 계산 */
            exemptCnt = Number(days);

            exemptMonths = this.calcMonth(dtFr, dtTo);

            rdcxptFee = applcPrice * exemptMonths.month * usageAr +applcPrice * exemptMonths.day/exemptMonths.lastMonthDay * usageAr;
            rdcxptFee = applcPrice * exemptMonths.month * usageAr +applcPrice * exemptMonths.day/exemptMonths.lastMonthDay * usageAr;

        }

        /* 날짜계산 */

             	var dtFr = EMD.util.strToDate(usagePdFrom);
        	var dtTo = EMD.util.strToDate(usagePdTo);

            usageMonths = this.calcMonth(dtFr, dtTo);
console.log('debug');
        //(사용료 = 공시지가*((사용일수)/365)*사용면적)*적용요율 ? 감면사용료 )
        if( exemptSe == '2' ) {     // 전체면제 일 경우 사용료는 0
        	rdcxptFee = calFee;
        	exemptCnt = dayUseCnt;
            calFee = 0;
        } else {
        	calFee = applcPrice*usageMonths.month*usageAr+applcPrice*usageMonths.day/usageMonths.lastMonthDay*usageAr - rdcxptFee;
        }
        var calcStr="";
        if(usageMonths.month) {
        	if(usageMonths.day) {
        		calcStr="( 적용단가("+$.number(applcPrice, false)+"원)*사용면적("+usageAr+"m²)*(사용개월수("+$.number(usageMonths.month, false)+"개월 "+usageMonths.day+"일/"+usageMonths.lastMonthDay+")";
        	}
        	else {
        		calcStr="( 적용단가("+$.number(applcPrice, false)+"원)*사용면적("+usageAr+"m²)*(사용개월수("+$.number(usageMonths.month, false)+"개월)";
        	}
        }
        else {
    		calcStr="( 적용단가("+$.number(applcPrice, false)+"원)*사용면적("+usageAr+"m²)*(사용일수("+$.number(usageMonths.day, false)+"일)";

        }
        if(rdcxptFee>0) {
			if(exemptMonths.month) {
				if(exemptMonths.day) {
		    		calcStr+="-( 면제개월수("+exemptMonths.month+"개월 "+exemptMonths.day+"일/"+exemptMonths.lastMonthDay+")";

				}
				else {
		    		calcStr+="-( 면제개월수("+exemptMonths.month+"개월)";
				}
			}
			else {
	    		calcStr+="-( 면제일수("+exemptMonths.day+"일/"+exemptMonths.lastMonthDay+")";
			}
			calcStr += " 면제 금액 : "+$.number(rdcxptFee, false)+"원)";
        }
        calcStr += " )";
    	this.$('#computDtls').val(calcStr);

        calFee = Math.floor(calFee/10)*10;
        rdcxptFee = Math.floor(rdcxptFee/10)*10;

        this.$('#fee').val($.number(calFee));
        this.$('#rdcxptFee').val($.number(rdcxptFee));
    } else {
        applcTariff = Number(this.$('#applcTariff').val().replace(/,/g, ''));
		if(applcTariff!=0) {
	    	this.$('#fee').val('');
	        this.$('#rdcxptFee').val('');
		}
    }
};

GamFcltsMngFeeMngModule.prototype.onCalc = function() {
	switch(this.$('#applcMth').val()) {
	case '1':	// 국유재산법
		this.calcNationAssetLaw();
		break;
	case '2':	// 공사규정
		break;
	case '3':	// 입찰
		break;
	case '4':	// 무역항규정
	case '6':	// 배후단지
		this.calcTradePortLaw();
		break;
	case '5':	// 임대계약서
		break;
	case '9':	// 기타
		break;
	}
};

GamFcltsMngFeeMngModule.prototype.calcRentMasterValues = function() {
    /* 총사용료, 총면적 계산 시작 */
    var fee = 0;
    var rdcxptFee = 0;
    var usageAr = 0;
    var usagePdFrom = 0;
    var usagePdTo = 0;
    var minUsagePdFrom = 0;
    var maxUsagePdTo = 0;

    for( var i = 0 ; i < this.$('#FcltsMngFeeMngDetailList').flexGetData().length ; i++ ) {
        var row = this.$('#FcltsMngFeeMngDetailList').flexGetRow(i);

        if( row['fee'] != '' && row['fee'] != null ) {
            var feeStr = row['fee']+"";
            feeStr = feeStr.replace(/,/g,"");
            fee += Number(feeStr);
        }

        if( row['rdcxptFee'] != '' && row['rdcxptFee'] != null ) {
            var rdcxptFeeStr = row['rdcxptFee']+"";
            rdcxptFeeStr = rdcxptFeeStr.replace(/,/g,"");
            rdcxptFee += Number(rdcxptFeeStr);
        }

        if( row['usageAr'] != '' && row['usageAr'] != null ) {
            var usageArStr = row['usageAr']+"";
            usageArStr = usageArStr.replace(/,/g,"");
            usageAr += Number(usageArStr);
        }

        if( row['usagePdFrom'] != '' && row['usagePdFrom'] != null ) {
            var usagePdFromStr = row['usagePdFrom']+"";
            usagePdFromStr = usagePdFromStr.replace(/-/g,"");
            usagePdFrom = Number(usagePdFromStr);

            if( minUsagePdFrom == 0 ) {
                minUsagePdFrom = usagePdFrom;
            } else {
                if( minUsagePdFrom > usagePdFrom ) {
                    minUsagePdFrom = usagePdFrom;
                }
            }
        }

        if( row['usagePdTo'] != '' && row['usagePdTo'] != null ) {
            var usagePdToStr = row['usagePdTo']+"";
            usagePdToStr = usagePdToStr.replace(/-/g,"");
            usagePdTo = Number(usagePdToStr);

            if( maxUsagePdTo == 0 ) {
                maxUsagePdTo = usagePdTo;
            } else {
                if( maxUsagePdTo < usagePdTo ) {
                    maxUsagePdTo = usagePdTo;
                }
            }
        }
    }

    this.$('#grFee').val( fee ); //총사용료
    this.$('#grRdcxptFee').val( rdcxptFee ); //총감면사용료
    this.$('#grAr').val( usageAr ); //총사용면적

    if( minUsagePdFrom > 0 ) {
        var minUsagePdFromStr = minUsagePdFrom+"";

        if( minUsagePdFromStr.length == 8 ) {
            minUsagePdFromStr = minUsagePdFromStr.substring(0,4) + "-" + minUsagePdFromStr.substring(4,6) + "-" + minUsagePdFromStr.substring(6,8);
            this.$('#grUsagePdFrom').val( minUsagePdFromStr ); //총사용기간FROM
        } else {
            this.$('#grUsagePdFrom').val( "" ); //총사용기간FROM
        }
    }

    if( maxUsagePdTo > 0 ) {
        var maxUsagePdToStr = maxUsagePdTo+"";

        if( maxUsagePdToStr.length == 8 ) {
            maxUsagePdToStr = maxUsagePdToStr.substring(0,4) + "-" + maxUsagePdToStr.substring(4,6) + "-" + maxUsagePdToStr.substring(6,8);
            this.$('#grUsagePdTo').val( maxUsagePdToStr ); //총사용기간FROM
        } else {
            this.$('#grUsagePdTo').val( "" ); //총사용기간FROM
        }
    }

    if( this.$('#FcltsMngFeeMngDetailList').flexGetData().length == 0 ) {
        this.$('#grFee').val( "" ); //총사용료
        this.$('#grRdcxptFee').val( "" ); //총감면사용료
        this.$('#grAr').val( "" ); //총사용면적
        this.$('#grUsagePdFrom').val( "" ); //총사용기간FROM
        this.$('#grUsagePdTo').val( "" ); //총사용기간FROM
    }

//     this.calcFirstPaymentAmount();
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamFcltsMngFeeMngModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
			this.loadData();
            break;

         // 신청
        case 'btnAddItem':
            this.$("#FcltsMngFeeMngListTab").tabs("option", {active: 1});  // 탭을 전환 한다.
            this.$('#gamFcltsMngFeeMngForm').find(':input').val('');
            this.$('#gamFcltsMngFeeMngDetailForm').find(':input').val('');
            this.$('#gamFcltsMngFeeMngFileForm').find(':input').val('');

            this.$("#FcltsMngFeeMngDetailList").flexEmptyData(); //그리드 초기화
            this.$("#FcltsMngFeeMngFileList").flexEmptyData(); //그리드 초기화
            this.$("#cmd").val('insert');

            break;



        // 신청저장
        case 'btnSaveItem':

            if( confirm("저장하시겠습니까?") ) {
                // 변경된 자료를 저장한다.
                var inputVO=[{name: 'test', value:'test hello'}];
                //var inputVO=[{}];

                //this._editData=this.getFormValues('#gamFcltsMngFeeMngDetailForm', this._editData);

                inputVO[inputVO.length]={name: 'updateList', value :JSON.stringify(this.$('#FcltsMngFeeMngDetailList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

                inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#FcltsMngFeeMngDetailList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

                inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataList) };

                if(this._deleteDataFileList == undefined ) {
                    this._deleteDataFileList=[];
                }

                inputVO[inputVO.length]={name: 'updateFileList', value :JSON.stringify(this.$('#FcltsMngFeeMngFileList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

                inputVO[inputVO.length]={name: 'insertFileList', value: JSON.stringify(this.$('#FcltsMngFeeMngFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

                inputVO[inputVO.length]={name: 'deleteFileList', value: JSON.stringify(this._deleteDataFileList) };

                //var otherForm=this.getFormValues('#gamFcltsMngFeeMngForm', {});  // 폼만 있을 경우

                this._editData2=this.getFormValues('#gamFcltsMngFeeMngForm', {_updtId:'I'});
                inputVO[inputVO.length]={name: 'form', value: JSON.stringify(this._editData2) };    // 폼의 데이터를 컨트롤러에 보낸다.
				console.log(this._editData2);
                //// console.log(inputVO);
                // 데이터를 저장 하고 난 뒤 리스트를 다시 로딩 한다.

                this.doAction('/mngFee/gamSaveFcltsMngFeeMng.do', inputVO, function(module, result) {
                    if(result.resultCode == 0){
                    	module.loadData();
                    }
                    alert(result.resultMsg);
                });


                this.$("#FcltsMngFeeMngListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
            }

            break;

        //신청삭제
        case 'btnRemoveItem':
            var rows = this.$('#FcltsMngFeeMngList').selectedRows();


            if(rows.length == 0) {
                alert("자산임대목록에서 신청삭제할 행을 선택하십시오.");
            } else {
            	if( confirm("신청삭제를 하시겠습니까?") ) {
                    if( rows[0]['prmisnYn'] == null || rows[0]['prmisnYn'] == '' ) {
                        this.$('#detailPrmisnYn').val('N');
                    }

                    var inputVO=this.makeFormArgs('#gamFcltsMngFeeMngForm');

                    this.doAction('/mngFee/gamDeleteFcltsMngFeeMng.do', inputVO, function(module, result) {

                        if(result.resultCode=='0') {
                        	module.loadData();
                        }

                        alert(result.resultMsg);
                    });

                    this.$("#FcltsMngFeeMngListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
                    this.$('#gamFcltsMngFeeMngForm :input').val("");
                    this.$("#cmd").val('insert');
                }
            }

            break;


        // 관리비 관리 상세 추가
        case 'btnInsertItemDetail':
        	 this.$("#FcltsMngFeeMngListTab").tabs("option", {active: 2});  // 탭을 전환 한다.
             this.$('#gamFcltsMngFeeMngDetailForm').find(':input').val('');

        	 this.$('#olnlpList').empty();

             this.$("#detailCmd").val('insert');
             this.$('#detailPrtAtCode').val( this.$('#prtAtCode').val() );
             //this.$('#detailPrtAtCodeNm').val( this.$('#prtAtCodeNm').val() );
             this.$('#detailMngYear').val( this.$('#mngYear').val() );
             this.$('#detailMngNo').val( this.$('#mngNo').val() );
             this.$('#detailMngCnt').val( this.$('#mngCnt').val() );
             this.$('#applcMth').val('6'); //적용방법
             this.onApplcMthChange(this.$('#applcMth').val());
             this.$('#applcTariff').val('0.05'); //적용요율
             this.$('#exemptSe').val('0'); // 면제구분
             this._editData=this.getFormValues('#gamFcltsMngFeeMngDetailForm', {_updtId:'I'});
//             this._editRow=this.$('#FcltsMngFeeMngDetailList').flexGetData().length;
				this._editRow=null;

            break;

        // 관리비 관리 상세테이블  삭제 (Grid상에서만 삭제됨)
        case 'btnRemoveItemDetail':
            var rows = this.$('#FcltsMngFeeMngDetailList').selectedRows();

            if(rows.length == 0) {
                alert("자산임대상세목록에서 삭제할 행을 선택하십시오.");
            } else {
                if(this.$('#FcltsMngFeeMngDetailList').selectedRowIds().length>0) {
                    for(var i=this.$('#FcltsMngFeeMngDetailList').selectedRowIds().length-1; i>=0; i--) {
                        var row=this.$('#FcltsMngFeeMngDetailList').flexGetRow(this.$('#FcltsMngFeeMngDetailList').selectedRowIds()[i]);

                        if(row._updtId==undefined || row._updtId!='I') {
                            this._deleteDataList[this._deleteDataList.length]=row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
                        }
                        this.$('#FcltsMngFeeMngDetailList').flexRemoveRow(this.$('#FcltsMngFeeMngDetailList').selectedRowIds()[i]);
                    }

                    /* 총사용료, 총면적 계산 시작 */
                    var fee = 0;
                    var rdcxptFee = 0;
                    var usageAr = 0;
                    var usagePdFrom = 0;
                    var usagePdTo = 0;
                    var minUsagePdFrom = 0;
                    var maxUsagePdTo = 0;

                    for( var i = 0 ; i < this.$('#FcltsMngFeeMngDetailList').flexGetData().length ; i++ ) {
                        var row = this.$('#FcltsMngFeeMngDetailList').flexGetRow(i);

                        if( row['fee'] != '' && row['fee'] != null ) {
                            var feeStr = row['fee']+"";
                            feeStr = feeStr.replace(/,/g,"");
                            fee += Number(feeStr);
                        }

                        if( row['rdcxptFee'] != '' && row['rdcxptFee'] != null ) {
                            var rdcxptFeeStr = row['rdcxptFee']+"";
                            rdcxptFeeStr = rdcxptFeeStr.replace(/,/g,"");
                            rdcxptFee += Number(rdcxptFeeStr);
                        }

                        if( row['usageAr'] != '' && row['usageAr'] != null ) {
                            var usageArStr = row['usageAr']+"";
                            usageArStr = usageArStr.replace(/,/g,"");
                            usageAr += Number(usageArStr);
                        }

                        if( row['usagePdFrom'] != '' && row['usagePdFrom'] != null ) {
                            var usagePdFromStr = row['usagePdFrom']+"";
                            usagePdFromStr = usagePdFromStr.replace(/-/g,"");
                            usagePdFrom = Number(usagePdFromStr);

                            if( minUsagePdFrom == 0 ) {
                                minUsagePdFrom = usagePdFrom;
                            } else {
                                if( minUsagePdFrom > usagePdFrom ) {
                                    minUsagePdFrom = usagePdFrom;
                                }
                            }
                        }

                        if( row['usagePdTo'] != '' && row['usagePdTo'] != null ) {
                            var usagePdToStr = row['usagePdTo']+"";
                            usagePdToStr = usagePdToStr.replace(/-/g,"");
                            usagePdTo = Number(usagePdToStr);

                            if( maxUsagePdTo == 0 ) {
                                maxUsagePdTo = usagePdTo;
                            } else {
                                if( maxUsagePdTo < usagePdTo ) {
                                    maxUsagePdTo = usagePdTo;
                                }
                            }
                        }
                    }

                    this.$('#grFee').val( fee ); //총사용료
                    this.$('#grRdcxptFee').val( rdcxptFee ); //총감면사용료
                    this.$('#grAr').val( usageAr ); //총사용면적

                    if( minUsagePdFrom > 0 ) {
                        var minUsagePdFromStr = minUsagePdFrom+"";

                        if( minUsagePdFromStr.length == 8 ) {
                            minUsagePdFromStr = minUsagePdFromStr.substring(0,4) + "-" + minUsagePdFromStr.substring(4,6) + "-" + minUsagePdFromStr.substring(6,8);
                            this.$('#grUsagePdFrom').val( minUsagePdFromStr ); //총사용기간FROM
                        } else {
                            this.$('#grUsagePdFrom').val( "" ); //총사용기간FROM
                        }
                    }

                    if( maxUsagePdTo > 0 ) {
                        var maxUsagePdToStr = maxUsagePdTo+"";

                        if( maxUsagePdToStr.length == 8 ) {
                            maxUsagePdToStr = maxUsagePdToStr.substring(0,4) + "-" + maxUsagePdToStr.substring(4,6) + "-" + maxUsagePdToStr.substring(6,8);
                            this.$('#grUsagePdTo').val( maxUsagePdToStr ); //총사용기간FROM
                        } else {
                            this.$('#grUsagePdTo').val( "" ); //총사용기간FROM
                        }
                    }

                    if( this.$('#FcltsMngFeeMngDetailList').flexGetData().length == 0 ) {
                    	this.$('#grFee').val( "" ); //총사용료
                        this.$('#grRdcxptFee').val( "" ); //총감면사용료
                        this.$('#grAr').val( "" ); //총사용면적
                    	this.$('#grUsagePdFrom').val( "" ); //총사용기간FROM
                    	this.$('#grUsagePdTo').val( "" ); //총사용기간FROM
                    }

                    /* 총사용료, 총면적 계산 종료 */
                }
            }

            this.$('#gamFcltsMngFeeMngDetailForm').find(':input').val('');
            this.$("#detailCmd").val('insert');

            break;


        case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            var opts;

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', opts);
            break;

        case 'popupEntrpsInfoInput': // 팝업을 호출한다.(자산임대입력)
            var opts;

            this.doExecuteDialog('insertEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', opts);
            break;


        case 'btnPrmisn': // 사용승낙
            var rows = this.$('#FcltsMngFeeMngList').selectedRows();
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
                }

            	var opts = {
                    'prtAtCode': rows[0]['prtAtCode'],
                    'mngYear': rows[0]['mngYear'],
                    'mngNo': rows[0]['mngNo'],
                    'mngCnt': rows[0]['mngCnt'],
                    'taxtSe': rows[0]['taxtSe']
                };

                this.doExecuteDialog('insertFcltsMngFeeMngPrmisnPopup', '승낙', '/mngFee/popup/showFcltsMngFeeMngPrmisn.do', opts);

            } else {
                alert("목록에서 선택하십시오.");
            }

            break;


        case 'btnPrmisnCancel': // 승낙취소
            var rows = this.$('#FcltsMngFeeMngList').selectedRows();
            var row = this.$('#FcltsMngFeeMngList').selectedRows()[0];

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
                    this.doAction('/mngFee/gamUpdateFcltsMngFeeMngPrmisnCancel.do', rows[0], function(module, result) {
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
        case 'btnNoticeAdit':	// 추가고지
        case 'btnNoticeAdit2':
            var rows = this.$('#FcltsMngFeeMngList').selectedRows();
            var row = this.$('#FcltsMngFeeMngList').selectedRows()[0];

            if( row['prmisnYn'] != 'Y' ) {
                alert("승낙된 상태가 아닙니다.");
                return;
            }

            if(rows.length>=1) {
                this.doExecuteDialog('insertLevReqestAdit', '추가 사용료 고지', '/oper/gnrl/popupLevReqestAdit.do', rows[0]);
            } else {
                alert("목록에서 선택하십시오.");
            }

            break;
        case 'popupFcltyCd':    //GIS자산코드 팝업을 호출한다.
            var opts;

            this.doExecuteDialog('selectAssetsCdRentPopup', '시설 선택', '/popup/showAssetsCd.do', opts);
            break;

        case 'btnRentDetailApply': //임대상세적용


            if(this._editData==null) return;   // 추가나 삭제가 없으면 적용 안됨 2014-03-11 추가
            this._editData=this.getFormValues('#gamFcltsMngFeeMngDetailForm', this._editData);
            //this._editData=this.getFormValues('#gamFcltsMngFeeMngDetailForm', this._editData);

            if(this._editRow!=null) {  // 이전에 _updtId 로 선택 한 것을 _editRow 로 변경 2014-03-14.001
            	this._editData._updtId!='I'
                if(this._editData._updtId!='I') this._editData._updtId='U';   // 삽입된 데이터가 아니면 업데이트 플래그를 추가한다.
                this.$('#FcltsMngFeeMngDetailList').flexUpdateRow(this._editRow, this._editData);
                this._editRow=null;    // 편집 저장 하였으므로 로우 편집을 종료 한다.
            }
            else {
                this.$('#FcltsMngFeeMngDetailList').flexAddRow(this._editData);
            }

            this.$('#gamFcltsMngFeeMngDetailForm').find(':input').val('');
            this._editData=null;       // 적용 이후 데이터 추가나 삭제 가 되지 않도록 편집 데이터를 제거 함/ 2014-03-11 추가

// 			this.calcRentMasterValues();

            /* 총사용료, 총면적 계산 종료 */

            this.$("#FcltsMngFeeMngListTab").tabs("option", {active: 1});  // 탭을 전환 한다.

            break;

        case 'btnUploadFile':
            // 사진을 업로드하고 업로드한 사진 목록을 result에 어레이로 리턴한다.
            this.uploadFile('uploadPhoto', function(module, result) {
//              var userid=EMD.util.getLoginUserVO().userNm; 임시
                var userid='admin';
                $.each(result, function(){
                    //module.$('#FcltsMngFeeMngFileList').flexAddRow({photoSj: '', filenmLogical: this.logicalFileNm, filenmPhyicl: this.physcalFileNm, regUsr: userid, registDt:  EMD.util.getTimeStamp()}); // 업로드 파일명이 physcalFileNm (물리명), logicalFileNm (논리명)으로 리턴 된다.
                    //module.$('#FcltsMngFeeMngFileList').flexAddRow({prtAtCode: '', mngYear: '', mngNo: '', mngCnt: '', photoSeq: '', photoSj: '', filenmLogic: this.logicalFileNm, filenmPhysicl: this.physcalFileNm, shotDt: '', photoDesc: '', regUsr: '', registDt:  EMD.util.getTimeStamp()}); // 업로드 파일명이 physcalFileNm (물리명), logicalFileNm (논리명)으로 리턴 된다.
                    module.$('#FcltsMngFeeMngFileList').flexAddRow({_updtId:'I', prtAtCode: '', mngYear: '', mngNo: '', mngCnt: '', photoSeq: '', photoSj:this.logicalFileNm.substring(0, this.logicalFileNm.lastIndexOf('.')) , filenmLogic: this.logicalFileNm, filenmPhysicl: this.physcalFileNm, shotDt: '', photoDesc: '', regUsr: '', registDt:  EMD.util.getTimeStamp()}); // 업로드 파일명이 physcalFileNm (물리명), logicalFileNm (논리명)으로 리턴 된다.
                });
            }, '첨부파일 업로드');

            //this._editDataFile=this.getFormValues('#gamFcltsMngFeeMngFileForm', {_updtId:'I'});
            //this._editRowFile=this.$('#FcltsMngFeeMngFileList').flexGetData().length;

            break;

        case 'btnDownloadFile':
    		var selectRow = this.$('#FcltsMngFeeMngFileList').selectedRows();
    		if(selectRow.length > 0) {
    			var row=selectRow[0];
    			this.downloadFile(row["filenmPhysicl"], row["filenmLogic"]);
    		}
    		break;
        case 'btnApplyPhotoData':
			this.applyPhotoData();
            break;

        // 파일 삭제 (Grid상에서만 삭제됨)
        case 'btnRemoveFile':
            var rows = this.$('#FcltsMngFeeMngFileList').selectedRows();

            if(rows.length == 0) {
                alert("파일목록에서 삭제할 행을 선택하십시오.");
            } else {
                if(this.$('#FcltsMngFeeMngFileList').selectedRowIds().length>0) {
                    for(var i=this.$('#FcltsMngFeeMngFileList').selectedRowIds().length-1; i>=0; i--) {
                        var row=this.$('#FcltsMngFeeMngFileList').flexGetRow(this.$('#FcltsMngFeeMngFileList').selectedRowIds()[i]);

                        //alert( row._updtId );

                        if(row._updtId==undefined || row._updtId!='I') {
                            this._deleteDataFileList[this._deleteDataFileList.length]=row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
                        }
                        this.$('#FcltsMngFeeMngFileList').flexRemoveRow(this.$('#FcltsMngFeeMngFileList').selectedRowIds()[i]);
                        this.$("#previewImage").attr('src', '');
                    }
                }
            }

            this.$('#gamFcltsMngFeeMngFileForm').find(':input').val('');
            this._editDataFile = null;

            break;

        case 'btnEApproval':    // 전자결재 테스트
            if(this.$('#FcltsMngFeeMngList').selectedRowCount()>0) {

                //alert(EMD.context_root);

                var rows = this.$('#FcltsMngFeeMngList').selectedRows()[0];

                /* if( rows['quayGroupCd'] != 'P' ) {
                    alert("해당 건은 자산임대관리 메뉴에서 결재가 불가능합니다.");
                    return;
                } */

                if( rows['sanctnSttus'] == '1' || rows['sanctnSttus'] == '2' || rows['sanctnSttus'] == '5' ) {
                	alert("결재요청을 할수없는 상태 입니다.");
                	return;
                }

                if( confirm("결재요청을 하시겠습니까?") ) {
	                var opts = {
	                        type: 'ARU',
	                        prtAtCode: rows['prtAtCode'],
	                        mngYear: rows['mngYear'],
	                        mngNo: rows['mngNo'],
	                        mngCnt: rows['mngCnt']
	                };

	                /*
	                this.requestEApproval(opts);
	                alert("결재요청을 하였습니다.");
	                */
	                this.requestEApproval(opts, function(module, msg){
						alert(msg);
		                //재조회 안됨..
		                var searchOpt=module.makeFormArgs('#gamFcltsMngFeeMngForm');
		                module.$('#FcltsMngFeeMngList').flexOptions({params:searchOpt}).flexReload();
                	});
                }
            } else {
            	alert("목록에서 결제할 건을 선택하십시오.");
            	return;
            }
            break;
        case 'btnMangeCharger': // 업체정보관리
       	 	EMD.util.create_window('업체정보 관리', '/code/gamCmpyInfoMngt.do', null, {entrpscd:this.$('#entrpscd').val()});
        	break;

        case 'btnHtldRentListExcelDownload':	// 엑셀 다운로드
        	this.$('#FcltsMngFeeMngList').flexExcelDown('/oper/gnrl/selectHtldRentListExcel.do');
            break;

        case 'btnRentFeeMngt':
            var rows = this.$('#FcltsMngFeeMngList').selectedRows();
            var opts = {};

            if(rows.length>0) {
            	opts = {
            			action: 'selectRentFee',
            			nticVo:{ prtAtCode: rows[0].prtAtCode, mngYear: rows[0].mngYear, mngNo: rows[0].mngNo, mngCnt: rows[0].mngCnt }
            	};
            }
       	 	EMD.util.create_window('배후단지임대료관리', '/mngFee/gamHtldRentFeeMngt.do', null, opts);

    }
};

GamFcltsMngFeeMngModule.prototype.applyPhotoData = function() {

	if(!validateGamFcltsMngFeeMngFile(this.$('#gamFcltsMngFeeMngFileForm')[0])) {
        return;
    }

	var selectRow = this.$('#FcltsMngFeeMngFileList').selectedRows();
	if(selectRow.length > 0) {

    var row=selectRow[0];
	  		var rowid=this.$("#FcltsMngFeeMngFileList").selectedRowIds()[0];
	  		row=this.getFormValues('#gamFcltsMngFeeMngFileForm', row);
        if(row["_updtId"]!='I') row["_updtId"]='U';   // 삽입된 데이터가 아니면 업데이트 플래그를 추가한다.
        this.$('#FcltsMngFeeMngFileList').flexUpdateRow(rowid, row);
    }
    else {
    }
};

GamFcltsMngFeeMngModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamFcltsMngFeeMngModule.prototype.loadData = function() {
    this.$("#FcltsMngFeeMngListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#GamFcltsMngFeeMngSearchForm');
    this.$('#FcltsMngFeeMngList').flexOptions({params:searchOpt}).flexReload();
	// console.log('debug');

};

GamFcltsMngFeeMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        var row = this.$('#FcltsMngFeeMngList').selectedRows();
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
        var row = this.$('#FcltsMngFeeMngDetailList').selectedRows();
        if(row.length==0) {
            this.$('#detailCmd').val('insert');
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
GamFcltsMngFeeMngModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     case 'selectEntrpsInfoPopup':
         if (msg != 'cancel') {
             this.$('#sEntrpscd').val(value.entrpscd);
             this.$('#sEntrpsNm').val(value.entrpsNm);
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
     case 'insertFcltsMngFeeMngPrmisnPopup':
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
//              this.$('#quayCd').val(value.gisAssetsQuayCd);
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

GamFcltsMngFeeMngModule.prototype.loadOlnlpList = function(prtFcltyCd) {
    this.doAction('/asset/rent/selectOlnlpInfo.do', prtFcltyCd, function(module, result) {
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
var module_instance = new GamFcltsMngFeeMngModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="GamFcltsMngFeeMngSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>관리비 관리 사용년도</th>
                            <td>
                                <select id="sMngMtYear" >
	                                    <option value="">선택</option>
	                                    <c:forEach items="${yearsList}" var="yearListItem">
	                                        <option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
	                                    </c:forEach>
	                                </select>
	                                <select id="sMngMtMon" >
	                                    <option value="">선택</option>
	                                    <c:forEach items="${monList}" var="monListItem">
	                                        <option value="${monListItem.code }">${monListItem.codeNm }</option>
	                                    </c:forEach>
	                                </select>
                            </td>
                            <th>관리비 업무 구분</th>
                            <td>
								<select id="sMngFeeJobSe">
									<option value="" selected>전체</option>
									<option value="M">마린센터</option>
									<option value="E">전기시설</option>
								</select>
							</td>
                            <td>
								<button class="buttonSearch">조회</button>
							</td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="FcltsMngFeeMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">시설물 관리비 관리</a></li>
                <li><a href="#tabs2" class="emdTab">시설물 관리비 관리 내역</a></li>
                <li><a href="#tabs3" class="emdTab">시설물 관리비 관리 상세내역</a></li>
                <li><a href="#tabs4" class="emdTab">첨부파일</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;">
                <table id="FcltsMngFeeMngList" style="display:none" class="fillHeight"></table>

                <div class="emdControlPanel">
					<form id="form1">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="12%" height="20">자료수</th>
								<td><input type="text" size="7" id="totalResultCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">총면적</th>
								<td><input type="text" size="19" id="totalArea" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">총사용료</th>
								<td><input type="text" size="19" id="totalUse" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">총감면사용료</th>
								<td><input type="text" size="19" id="totalGrRdcxptFee" class="ygpaNumber" disabled="disabled" /></td>
								<td>
		                            <input id="loginOrgnztId" type="hidden" value="<c:out value="${loginOrgnztId}"/>"/>
		                            <input id="loginUserId" type="hidden" value="<c:out value="${loginUserId}"/>"/>
		                            <input id="currentDateStr" type="hidden" value="<c:out value="${currentDateStr}"/>"/>
		                            <input id="blceStdrIntrrate" type="hidden" value="<c:out value="${blceStdrIntrrate}"/>"/>
		                            <input id="blceStdrIntrrateShow" type="hidden" value="<c:out value="${blceStdrIntrrateShow}"/>"/>
		                        </td>
							</tr>
						</table>
    	               	<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">

	                                <button id="btnAddItem">신청</button>
	                                <button id="btnRemoveItem">신청삭제</button>
	                                <button id="btnEApproval">결재요청</button>
	                                <button id="btnPrmisn">사용승낙</button>
	                                <button id="btnPrmisnCancel">승낙취소</button>
	                                <button id="btnHtldRentListExcelDownload">엑셀</button>
                      	            <button id="btnRentFeeMngt">사용료관리</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamFcltsMngFeeMngForm">
                        <input type="hidden" id="cmd"/>
                        <!-- <input type="hidden" id="quayGroupCd"/> -->

                        <table class="editForm">
                            <tr>
								<th width="10%" height="18">관리 월</th>
                                <td>
                                <select id="MngMtYear" class='selt'>
	                                    <option value="">선택</option>
	                                    <c:forEach items="${yearsList}" var="yearListItem">
	                                        <option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
	                                    </c:forEach>
	                                </select>
	                                <select id="mngMtMon" class='selt'>
	                                    <option value="">선택</option>
	                                    <c:forEach items="${monList}" var="monListItem">
	                                        <option value="${monListItem.code }">${monListItem.codeNm }</option>
	                                    </c:forEach>
	                                </select>
	                                <input id="mngMt" type="text"  maxlength="6"/>
                        	    </td>
								<th width="10%" height="18">담당부서</th>
                                <td>
                                    <input id="deptcd" class="ygpaDeptSelect" data-default-prompt="선택" data-value="<c:out value="${loginOrgnztId}"/>" />
                                </td>
								<th width="10%" height="18">관리비 업무 구분</th>
                                <td>
									<select id="mngFeeJobSe">
										<option value="M" selected>마린센터</option>
										<option value="E">전기시설</option>
									</select>
								</td>
                            </tr>
                            <tr>
								<th width="10%" height="18">비고</th>
                                <td colspan="5"><input type="text" size="120" id="mngFeeSj" maxlength="90"/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">승낙여부</th>
                                <td>
                                    <select id="prmisnYn" disabled>
                                        <option value="">선택</option>
                                        <option value="Y">Y</option>
                                        <option value="N" selected="selected">N</option>
                                    </select>
                                </td>
								<th width="10%" height="18">승낙일자</th>
                                <td><input type="text" size="16" id="prmisnDt" disabled></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">시설 관리 용역비</th>
                                <td><input type="text" size="16" class="ygpaNumber" id="fcltyMngFee" /></td>
								<th width="10%" height="18">전기 요금</th>
                                <td><input type="text" size="16" class="ygpaNumber" id="elctyFee" /></td>
								<th width="10%" height="18">상하수도 요금</th>
                                <td><input type="text" size="16" class="ygpaNumber" id="waterFee" /></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">도시가스 요금</th>
                                <td><input type="text" size="16" class="ygpaNumber" id="gasFee" /></td>
								<th width="10%" height="18">환경개선 부담금</th>
                                <td><input type="text" size="16" class="ygpaNumber" id="envFee" /></td>
								<th width="10%" height="18">관리비 합계</th>
                                <td><input type="text" size="16" class="ygpaNumber" id="mngTotalFee" /></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">납부방법</th>
                                <td>
                                    <input id="payMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM043" />
                                </td>
								<th width="10%" height="18">고지방법</th>
                                <td>
                                    <input id="nticMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM008" />
                                </td>
								<th width="10%" height="18">분납이자율</th>
                                <td>
									<input type="text" size="19" id="payinstIntrrate" maxlength="4" size="5"/>
                                    <select id="cofixList">
                                        <option value="">선택</option>
                                        <c:forEach items="${cofixList}" var="cofixListItem">
                                            <option value="${cofixListItem.code }">${cofixListItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">과세구분</th>
                                <td>
                                    <input id="taxtSe" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM016" />
                                </td>
								<th width="10%" height="18">첫회 사용료</th>
                                <td colspan="3">
                                	<input type="text" size="13" id="firstPayVal" class="skipValue">
                                </td>
                            </tr>
                    </form>

	                 <table class="searchPanel">
	                    <tbody>
	                    <tr>
	                        <th width="70%">시설물 관리 상세목록</th>
	                        <th style="text-align:right">
                                  <button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="FcltsMngFeeMngDetailList" data-style="default">맵조회</button>
	                        	<button id="btnInsertItemDetail" class="buttonAdd">임대상세추가</button>
	                        	<button id="btnRemoveItemDetail" class="buttonDelete">임대상세삭제</button>
	                        </th>
	                    </tr>
	                    </tbody>
	                 </table>

	                 <table id="FcltsMngFeeMngDetailList" style="display:none"></table>

	                 <table style="width:100%">
	                    <tr>
	                        <td width="100"></td>
	                        <td style="text-align:right">
	                        <button id="btnEApproval">결재요청</button><button id="btnPrmisn">사용승낙</button>
	                            <button id="btnPrmisnCancel">승낙취소</button><button id="btnRemoveItem" class="buttonDelete">신청삭제</button><button id="btnSaveItem" class="buttonSave">신청저장</button>
	                        </td>
	                    </tr>
	                 </table>
                 </div>
            </div>

            <div id="tabs3" class="emdTabPage" style="overflow: scroll;">

                    <form id="gamFcltsMngFeeMngDetailForm">
                        <input type="hidden" id="detailCmd"/>
                        <input type="hidden" id="detailPrtAtCode" data-column-id="prtAtCode"/>
                        <input type="hidden" id="detailPrmisnYn"/>
                        <table class="editForm">
                        	<tr>
                        		<th width="10%" height="18">신청업체</th>
                                <td>
                                    <input type="text" size="10" id="entrpscd" maxlength="10" readonly/>
                                    <input type="text" size="18" id="entrpsNm" disabled/>
                                    <button id="popupEntrpsInfoInput" class="popupButton">선택</button>
                                </td>
                        	</tr>
                            <tr>
								<th width="10%" height="18">사용면적</th>
                                <td colspan="5"><input type="text" size="20" id="usageAr" maxlength="200" class="ygpaNumber"/></td>
								<th width="10%" height="18">관리비</th>
                                <td colspan="5"><input type="text" size="20" id="mngFee" maxlength="200" class="ygpaNumber"/></td>
								<th width="10%" height="18">전기요금</th>
                                <td colspan="5"><input type="text" size="20" id="elctyFee" maxlength="200" class="ygpaNumber"/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">상하수도 요금</th>
                                <td colspan="5"><input type="text" size="20" id="waterFee" maxlength="200" class="ygpaNumber"/></td>
								<th width="10%" height="18">도시가스 요금</th>
                                <td colspan="5"><input type="text" size="20" id="gasFee" maxlength="200" class="ygpaNumber"/></td>
								<th width="10%" height="18">관리비 합계</th>
                                <td colspan="5"><input type="text" size="20" id="mngTotalFee" maxlength="200" class="ygpaNumber"/></td>
                            </tr>


                        </table>
                    </form>


                <table style="width:100%">
                    <tr>
                        <td></td>
                        <td width="100"></td>
                        <td style="text-align:right">
                        <button id="btnRentDetailApply">임대상세적용</button>
                        </td>
                    </tr>
                 </table>

            </div>

            <div id="tabs4" class="emdTabPage" style="overflow: scroll;">

                <table id="FcltsMngFeeMngFileList" style="display:none"></table>
                <div class="emdControlPanel"><button id="btnUploadFile">업로드</button><button id="btnDownloadFile">다운로드</button><button id="btnRemoveFile">삭제</button></div>
                <form id="gamFcltsMngFeeMngFileForm">
                    <input type="hidden" id="photoPrtAtCode" data-column-id="prtAtCode"/>
                    <input type="hidden" id="photoMngYear" data-column-id="mngYear"/>
                    <input type="hidden" id="photoMngNo" data-column-id="mngNo"/>
                    <input type="hidden" id="photoMngCnt" data-column-id="mngCnt"/>
                    <input type="hidden" id="photoSeq" data-column-id="photoSeq"/>

                    <table class="editForm">
                        <tr>
							<th width="10%" height="18">사진제목</th>
                            <td>
                                <input id="photoSj" type="text" size="70" class="photoEditItem" maxlength="40"/>
                            </td>
							<th width="10%" height="18">촬영일자</th>
                            <td>
                                <input id="shotDt" type="text" size="10" class="emdcal photoEditItem" readonly>
                            </td>
                        </tr>
                        <tr>
							<th width="10%" height="18">사진파일명</th>
                            <td colspan="3">
                                <input id="filenmLogic" type="text" size="135" class="photoEditItem" disabled/>
                            </td>
                        </tr>
                        <tr>
							<th width="10%" height="18">사진설명</th>
                            <td colspan="3">
                                <input id="photoDesc" type="text" size="135" class="photoEditItem" maxlength="90">
                            </td>
                        </tr>
                    </table>
                </form>
                    <!-- <button id="btnApplyPhotoData">첨부파일 적용</button> -->
                <div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>

            </div>


        </div>
    </div>
</div>