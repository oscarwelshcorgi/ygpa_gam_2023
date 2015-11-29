<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld"%>
<%--
  @Class Name : GamHtldRentMngt.jsp
  @Description : 배후단지임대관리
  @Modification Information

     수정일         수정자                   수정내용
    ------------ 	----------	---------------------------
    2014.01.10  heroine     최초 생성
    2014.12.16	eunsungj	죄다 뜯어 고침 ㅡㅡ;

   author eunsungj
   since 2014.01.10

  Copyright (C) 2013 by LFIT  All right reserved.
--%>
<validator:javascript formName="gamHtldRentMngt" method="validateGamAssetRent" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamHtldRentMngtDetail" method="validateGamAssetRentDetail" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamHtldRentMngtPhoto" method="validateGamAssetRentFile" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>
<%--
 	아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
--%>
function GamHtldRentMngtModule() {}

<%--
	EmdModule을 상속하여 모듈 클래스를 정의한다.
--%>
GamHtldRentMngtModule.prototype = new EmdModule(900, 645);

<%--
	페이지가 호출 되었을때 호출 되는 함수
--%>
GamHtldRentMngtModule.prototype.loadComplete = function() {

    // 자산임대 테이블 설정
    this.$("#assetRentMngtList").flexigrid({
        module: this,
        url: '/oper/htld/selectHtldRentList.do',
        dataType: 'json',
        colModel : [
                    {display:'구역', name:'rentArea',width:82, sortable:false,align:'center'},
//                    {display:'관리번호', name:'rentMngNo',width:82, sortable:false,align:'center'},
                    {display:'입주기업', name:'entrpsNm',width:190, sortable:false,align:'left'},
                    {display:'입주면적(㎡)', name:'grAr',width:88, sortable:false,align:'right', displayFormat: 'number',  displayOption:"0,000.00"},
                    {display:'적용단가(원)', name:'applcPrice',width:80, sortable:false,align:'right', displayFormat: 'number',  displayOption:"0,000.0"},
                    {display:'영업개시일', name:'operYrMt',width:80, sortable:false,align:'center'},
                    {display:'계약기간', name:'grUsagePdPeriod',width:175, sortable:false,align:'center'},
                    {display:'소재지', name:'gisAssetsLocplc',width:170, sortable:false,align:'left'},
                    {display:'고지방법', name:'payMthNm',width:60, sortable:false,align:'center'},
                    {display:'납부방법', name:'nticMthNm',width:60, sortable:false,align:'left'},
                    {display:'과세구분', name:'taxtSeNm',width:120, sortable:false,align:'left'},
                    {display:'요금종류', name:'chrgeKndNm',width:160, sortable:false,align:'left'},
                    {display:'업종', name:'compTp',width:110, sortable:false,align:'right'},
                    {display:'취급화종', name:'frghtTp',width:120, sortable:false,align:'center'},
                    {display:'해지/변경', name:'termnKndNm',width:60, sortable:false,align:'center'},
                    {display:'해지/변경일자', name:'termnDt',width:80, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        groupBy: "rentArea",
        preProcess: function(module,data) {
            module.$('#totalResultCnt').val(data.totalCount);
            module.$('#totalArea').val(data.sumGrAr);
            module.$('#totalUse').val(data.sumGrFee);
            module.$('#totalGrRdcxptFee').val(data.sumGrRdcxptFee);
            $.each(data.resultList, function() {
//            	this.intrRateDisp = this.intrRate+ ' %';
            	this.grUsagePdPeriod = this.grUsagePdFrom+" ~ " +this.grUsagePdTo;
            	switch(this.termnKnd) {
            		case '1' :
            			this.termnKndNm = '해지';
            			break;
            		case '2' :
            			this.termnKndNm = '변경';
            			break;
            	}
            });
            return data;
        }
    });
	
    this.$("#assetRentMngtList").on('onLoadDataComplete', function(event, module, data) {
    	module._loadedItem=false;
    	module._editChanged=false;
    	module._editable=false;
    	module._selectedItem=null;
    	module._rentDetail=null;
    	module._detailMode="";
    	module.setButtonStatus();
    	module.showTermnKndTr();
	});
    
    this.$("#assetRentMngtList").on('onItemSelected', function(event, module, row, grid, param) {
		// 항목에 따른 버튼 세팅
    	module._loadedItem=false;
    	module._editChanged=false;
    	module._editable=false;
    	module._selectedItem=row;
    	module._rentDetail=row;
    	module._detailMode="";
    	module.setButtonStatus();
    	module.showTermnKndTr();
    });

    this.$("#assetRentMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
    	module._loadedItem=false;
    	module._editChanged=false;
    	module._editable=false;
    	module._selectedItem=row;
    	module._detailMode="";
        module.$("#assetRentListTab").tabs("option", {active: 1});
    });

    // 자산임대상세 테이블 설정
    this.$("#assetRentDetailList").flexigrid({
        module: this,
        dataType: 'json',
        colModel : [
           {display:'자산코드', name:'gisAssetsCode',width:80, sortable:false,align:'center'},
           {display:'자산명', name:'gisAssetsNm',width:240, sortable:false,align:'left'},
           {display:'소재지', name:'gisAssetsLocplcAll',width:260, sortable:false,align:'center'},
           {display:'자산면적', name:'gisAssetsAr',width:80, sortable:false,align:'right', displayFormat: 'number', displayOption:"0,000.00"},
           {display:'적용단가', name:'applcPrice',width:100, sortable:false,align:'right', displayFormat: 'input-number', displayOption:"0,000.0"},
           {display:'사용면적', name:'usageAr',width:80, sortable:false,align:'right', displayFormat: 'input-number', displayOption:"0,000.00"}
        ],
        showTableToggleBtn: true,
        height: '200',
        preProcess: function(module, data) {
        	$.each(data.resultList, function() {
        		this._updtId = '';
        		module.makeAssetCd(this);
		    });
        	return data;
        }
    });

    this.$("#assetRentDetailList").on('onItemSelected', function(event, module, row, grid, param) {
    });

    this.$("#assetRentDetailList").on('onItemDoubleClick', function(event, module, row, grid, param) {
    });

    this.$("#assetRentDetailList").on('onCellEdited', function(event, module, row, grid, param) {
        if(row._updtId!="I") row._updtId="U";
    	module.onCalc();
    });

    // 실적평가 그리드 설정
    this.$("#bizAssessGrid").flexigrid({
        module: this,
        url: '/oper/htld/selectHtldAssessList.do',
        dataType: 'json',
        colModel : [
                    {display:'평가회차', name:'assessNo', width:80, sortable:true, align:'center', displayFormat:'input'},
                    {display:'기간From', name:'dtFrom', width:120, sortable:true, align:'left', displayFormat:'cal'},
                    {display:'기간To', name:'dtTo', width:120, sortable:true, align:'left', displayFormat:'cal'},
                    {display:'구분', name:'assessSe', width:100, sortable:true, align:'left', displayFormat:'ajaxselect', url:'/oper/htld/selectHtldAssessSeCodeList.do', params:[]},
                    {display:'평가일', name:'assessDt', width:100, sortable:true, align:'left', displayFormat:'cal'},
                    {display:'평가결과', name:'assessResult', width:100, sortable:true, align:'center', displayFormat:'dyn'},
                    {display:'비고', name:'rm', width:100, sortable:true, align:'left', displayFormat:'input'}
                    ],
        showTableToggleBtn: false,
        height: '400',
        preProcess: function(module,data) {
            module._assessNo=module._assessNo||0;
            $.each(data.resultList, function() {
            	if(module._assessNo<this.assessNo) module._assessNo=this.assessNo;
            });
            module._deleteAssessList=[];
            return data;
        }
    });

    this.$("#bizAssessGrid").on('onItemSelected', function(event, module, row, grid, param) {
    });

    this.$("#bizAssessGrid").on('onCellEdited', function(event, module, row, grid, param) {
        if(row._updtId!="I") row._updtId="U";
    });

    this.$("#nticListGrid").flexigrid({
        module: this,
        url: '/oper/htld/selectHtldNticList.do',
        dataType: 'json',
        colModel : [
                    {display:'고지회차', name:'nticCnt', width:50, sortable:true, align:'center'},
                    {display:'요금부과기간', name:'rentPeriod', width:200, sortable:true, align:'center'},
                    {display:'요금종류', name:'chrgeKndNm', width:200, sortable:true, align:'center'},
                    {display:'고지금액', name:'billAmnt', width:100, sortable:true, align:'right', displayFormat:'number'},
                    {display:'고지일자', name:'billDt', width:110, sortable:true, align:'center'},
                    {display:'납부상태', name:'rcvdTpNm', width:80, sortable:true, align:'center'},
                    {display:'납부일자', name:'rcvdDt', width:110, sortable:true, align:'center'}
                    ],
        showTableToggleBtn: false,
        height: '400',
        preProcess: function(module,data) {
            $.each(data.resultList, function() {
            	switch(this.rcvdTp) {
            	case '1':
            		this.rcvdTpNm="연체고지";
            		break;
            	case '2':
            	case '3':
            		this.rcvdTpNm="수납";
            		break;
            	case '0':
        		default:
            		this.rcvdTpNm="미수납";
            		break;
            	}
            	this.rentPeriod = this.nticPdFrom + " ~ " + this.nticPdTo;
            });

            return data;
        }
    });

    this.$("#nticListGrid").on('onItemSelected', function(event, module, row, grid, param) {
    });

    this.setEvents();

    // 초기값 정의
    this.$('#sGrUsagePdFrom').val(EMD.util.getDate());
	    
    this._editMode='';
    this._detailMode="";
    this._loadedItem=false;

	this._editable=false;

    this.setButtonStatus();
	this.showTermnKndTr();
	
    this.loadData();	// 데이터 조회
    console.log('load complete');
};

<%--
	이벤트 정의
--%>
GamHtldRentMngtModule.prototype.setEvents = function() {
    this.$("#sEntrpscd").bind("keyup change", {module: this}, function(event) {
		if(event.data.module.$('#sEntrpscd').val() ==''){
			event.data.module.$('#sEntrpsNm').val('');
		}
    });
    this.$('#gamAssetRentForm :input').on('change', {module:this}, function(event) {
    	var module=event.data.module;
    	if(module._editable) module._editChanged=true;
    	module.setButtonStatus();
    });
    // tabs3
    this.$('.calcInput').on('change keyup', {module: this}, function(event) {
        event.data.module.onCalc();
    });
    this.$('#nticMth').on('change', {module: this}, function(event) {
    	event.data.module.onChangeNticMth($(this).val());
    });
    this.$('#intrRate').on('change keyup', {module: this}, function(event) {
        event.data.module.onCalc();
    });
    this.$('#cofixList').on('change', {module: this}, function(event) {
    	event.data.module.onCofixListChange($(this).val());
    });
    this.$(".photoEditItem").bind("keyup change", {module: this}, function(event) {
    	event.data.module.applyPhotoData();
    });
};

<%--
	버튼 상태 설정
--%>
GamHtldRentMngtModule.prototype.setButtonStatus = function() {
	var tab_active = this.$('#assetRentListTab').tabs('option', 'active');
	switch(tab_active) {
	case 0:
		var rows = this.$('#assetRentMngtList').selectedRows();
		if(rows.length) {
			this.$('#btnEApproval').hide();	// 결재 요청 disable
			this.$('#btnRemoveItem').show();
			this.$('#btnRentFeeMngt').show();
			this.$('#btnHtldRentListExcelDownload').show();
			if(rows[0].termnKnd >= '1' ) { //계약이 해지되거나 변경된 데이터일 경우
				this.$('#addAssetRentRenew').hide();
				this.$("#btnTerminateItem").hide();
				this.$('#btnChangeItem').hide();
			} else {
				this.$('#addAssetRentRenew').show();
				this.$("#btnTerminateItem").show();
				this.$('#btnChangeItem').show();
			}
		} else {
			this.$('#addAssetRentRenew').hide();
			this.$('#btnRemoveItem').hide();
			this.$('#btnEApproval').hide();
			this.$('#btnRentFeeMngt').hide();
			this.$("#btnTerminateItem").hide();
			this.$('#btnChangeItem').hide();
			var rowCnt = this.$('#assetRentMngtList').flexRowCount();
			if(rowCnt>0) {
				this.$('#btnHtldRentListExcelDownload').show();
			}
			else {
				this.$('#btnHtldRentListExcelDownload').hide();
			}
		}
		break;
	case 1:
		if(this._detailMode=="I") {
			this.$('#btnEApproval2').hide();
			this.$('#btnRemoveItem2').hide();
			this.$('#btnSaveItem').show();

			this.$('#popupEntrpsInfoInput').show();
			this.$('#btnMangeCharger').show();

			this.$('#btnInsertItemDetail').show();
			this.$('#btnRemoveItemDetail').show();

			this.$('#entrpscd').removeAttr('readonly');
	        this.$('#popupEntrpsInfoInput').removeAttr('disabled');

	        this.$('#btnSaveComment').hide();	
	        
	        this.$('#btnChangeSaveItem').hide();
	        this._editable=true;
        } else {
			var rows = this.$('#assetRentMngtList').selectedRows();
			if(rows.length) {
				this.$('#btnEApproval2').hide();	// 결재 요청 disable
				this.$('#btnSaveItem').show();
				this.$('#btnMangeCharger').hide();
			    this.$('#entrpscd').attr('readonly', true);
		        this.$('#btnSaveComment').show();
		        if(rows[0].termnKnd >= '1') { //계약이 해지되거나 변경된 데이터의 경우
		        	this.$('#btnInsertItemDetail').hide();
		        	this.$('#btnRemoveItemDetail').hide();
		        	this.$('#btnSaveItem').hide();
		        	this.$('#btnChangeSaveItem').hide();
		        } else {
		        	this.$('#btnInsertItemDetail').show();
		        	this.$('#btnRemoveItemDetail').show();
		        	if(this._detailMode=='C') { //계약변경을 할 경우...
			        	this.$('#btnChangeSaveItem').show();
			        	this.$('#btnSaveItem').hide();
			        	this.$('#btnRemoveItem2').hide();
		        	} else {
		        		this.$('#btnChangeSaveItem').hide();
		        		this.$('#btnSaveItem').show();
		        		this.$('#btnRemoveItem2').show();
		        	}
		        }
			} else {
				this.$('#btnEApproval2').hide();
				this.$('#btnSaveItem').hide();
				this.$('#entrpscd').removeAttr('readonly');
		        this.$('#popupEntrpsInfoInput').removeAttr('disabled');
		        this.$('#btnSaveComment').hide();
			}
		}
		break;
	case 2:
		var rows = this.$('#assetRentMngtList').selectedRows();
		if(rows.length) {
			this.$('#btnEApproval2').hide();	// 결재 요청 disable
		} 
		break;
	default:
		return;
	}
};

<%--
	변경사유를 설정한다.
--%>
GamHtldRentMngtModule.prototype.showTermnKndTr = function() {
	if(this._detailMode=="I") {
		this.$('#termnRmTr').hide();
		return; 
	}
	if(this._detailMode=="C") {
		this.$('#termnRmTr').show();
		return; 
	}
	if(this._rentDetail) {
		if(this._rentDetail.termnKnd == '2') {  //계약이 변경된 상태의 조건
			this.$('#termnRmTr').show();
		} else {
			this.$('#termnRmTr').hide();
		}
	} else {
		this.$('#termnRmTr').hide();
	}
};

<%--
	자산코드와 주소를 문자열로 변환한다
--%>
GamHtldRentMngtModule.prototype.makeAssetCd = function(assetCd) {
	assetCd.gisAssetsCode = assetCd.gisAssetsCd+'-'+assetCd.gisAssetsSubCd;
	assetCd.gisAssetsLocplcAll = assetCd.gisAssetsLocplc;
	if(assetCd.gisAssetsLnm!=undefined && assetCd.gisAssetsLnm!=null) {
		assetCd.gisAssetsLocplcAll+=" "+assetCd.gisAssetsLnm;
		if(assetCd.gisAssetsLnmSub!=undefined && assetCd.gisAssetsLnmSub!=null) {
			assetCd.gisAssetsLocplcAll = assetCd.gisAssetsLocplcAll+"-"+assetCd.gisAssetsLnmSub;
		}
	}
};

<%--
	적용방법이 수정 되었을때 호출되는 이벤트 핸들러
--%>
GamHtldRentMngtModule.prototype.onChangeNticMth = function(nticMth) {
    //alert($(this).val());
    if( nticMth != '' && nticMth != '1' ) {
    	this.$('#cofixList').val( this.$('#blceStdrIntrrate').val() );
    	this.$('#intrRate').val(Number(this.$('#cofixList').val()) * 100);
    } else {
    	this.$('#cofixList').val("");
    	this.$('#intrRate').val("");
    }
    // calc first payment amount
    this.onCalc();
};

<%--
	분납 이자율이 변경 되었을 때 호출 되는 이벤트 핸들러
--%>
GamHtldRentMngtModule.prototype.onCofixListChange = function(cofix) {
	//alert('||'+$(this).val()+'||');
    if( cofix == '' ) {
    	this.$('#intrRate').val("");
    } else {
    	var intrRateCal = (Number(cofix) * 100) + "";

    	if(intrRateCal.length > 4) {
    		intrRateCal = intrRateCal.substring(0,5);
    		intrRateCal = Number(intrRateCal).toFixed(2);
		}

    	this.$('#intrRate').val( intrRateCal );
    	this.onCalc();
    }
};

<%--
	업체 담당자 정보를 로딩한다.
--%>
GamHtldRentMngtModule.prototype.loadEntrpsChargerList = function() {
	var entrpsCd=this.$('#entrpscd').val();
	this.$('#chargerTlphonNo').text('');
	this.$('#chargerMoblphonNo').text('');
	if(entrpsCd!=null && entrpsCd.length>0) {
		//var loadOpt = [{name: 'entrpscd', value: entrpsCd}];
		var loadOpt = {'entrpscd': entrpsCd};
	    this.doAction('/asset/rent/selectEntrpsChargerList.do', loadOpt, function(module, result) {
	    	//console.log('charger list load completed');
	        if(result.resultCode=='0') {
		       	 var selectCharger = module.$('#selectCharger');
		       	selectCharger.off('change');
		       	selectCharger.empty();
		       	selectCharger.append('<option value="">선택</option>');
		       	 $.each(result.resultList, function() {
		       		selectCharger.append('<option value="'+this.chargerNo+'" data-moblphonno="'+this.chargerMoblphonNo+'" data-tlphonno="'+this.chargerTlphonNo+'" data-fax="'+this.chargerFax+'" data-email="'+ +this.chargerEmail+'">'+this.chargerNm+'</option>');
		       	 });
		       	selectCharger.on('change', {module: module}, function(event) {
		       		var sel = $(this).children(':selected');
		       		var m = event.data.module;
		       		//console.log('charger selected : '+sel);
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

<%--
	기간에 대한 달 수 계산 함수
--%>
GamHtldRentMngtModule.prototype.calcMonth = function(dtFrom, dtTo) {
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
};

<%--
	적용방법에 따른 요금 계산
--%>
GamHtldRentMngtModule.prototype.onCalc = function() {
	var usageAr=0;
	this.$('#assetRentDetailList')[0].dgrid.forEachRow(function(id) {
    	usageAr+=Number(this.cells(id,5).getValue());
    });
	this.$('#grAr').val(usageAr);

    if( this.$('#grUsagePdFrom').val() != '' && this.$('#grUsagePdTo').val() != ''
         && this.$('#applcPrice').val() != '' ) {
        var calFee      = 0;  //계산된 사용료
        var usageAr     = 0;  //사용면적
        var applcPrice = 0;  //적용단가
        var rdcxptFee   = 0;  //감면사용료
        var dayUseCnt   = 0;  //사용일수
        var usagePdFrom = ""; //사용기간 from
        var usagePdTo   = ""; //사용기간 to

        applcPrice = Number(this.$('#applcPrice').val().replace(/,/g, ''));
        usagePdFrom = EMD.util.strToDate(this.$('#grUsagePdFrom').val());
    	usagePdTo = new Date(usagePdFrom);
    	var nticMth=this.$('#nticMth').val();
    	var usageAr=this.$('#grAr').val();
        if(nticMth=='1') {
        	usagePdTo = EMD.util.strToDate(this.$('#grUsagePdTo').val());
        }
        else if(nticMth=='2') {
        	if(usagePdTo.getMonth()<6) {
        		usagePdTo.setMonth(6);
        		usagePdTo.setDate(0);
        	}
        	else {
        		usagePdTo.setMonth(0);
        		usagePdTo.setDate(0);
        		usagePdTo.setYear(usagePdTo.getYear()+1);
        	}
        }
        else if(nticMth=='3') {
        	if(usagePdTo.getMonth()<5) {
        		usagePdTo.setMonth(5);
        		usagePdTo.setDate(0);
        	}
        	else if(usagePdTo.getMonth()<9) {
        		usagePdTo.setMonth(9);
        		usagePdTo.setDate(0);
        	}
        	else if(usagePdTo.getMonth()<12) {
        		usagePdTo.setMonth(0);
        		usagePdTo.setDate(0);
        		usagePdTo.setYear(usagePdTo.getYear()+1);
        	}
        }
        else if(nticMth=='4') {
        	if(usagePdTo.getMonth()<4) {
        		usagePdTo.setMonth(4);
        		usagePdTo.setDate(0);
        	}
        	else if(usagePdTo.getMonth()<7) {
        		usagePdTo.setMonth(7);
        		usagePdTo.setDate(0);
        	}
        	else if(usagePdTo.getMonth()<10) {
        		usagePdTo.setMonth(10);
        		usagePdTo.setDate(0);
        	}
        	else if(usagePdTo.getMonth()<12) {
        		usagePdTo.setMonth(0);
        		usagePdTo.setDate(0);
        		usagePdTo.setYear(usagePdTo.getYear()+1);
        	}
        }
        else if(nticMth=='5') {
    		usagePdTo.setMonth(usagePdTo.getMonth()+1);
    		usagePdTo.setDate(0);
        }
        else if(nticMth=='6') {
    		usagePdTo.setMonth(0);
    		usagePdTo.setDate(0);
    		usagePdTo.setYear(usagePdTo.getYear()+1);
        }
        else {
        	EMD.util.showMessage(nticMth, '지원하지 않는 고지방법입니다.');
        }

        /* 날짜계산 */
        var dtFr = new Date(usagePdFrom);
        var dtTo = new Date(usagePdTo);

        usageMonths = this.calcMonth(dtFr, dtTo);

       	calFee = applcPrice*usageMonths.month*usageAr+applcPrice*usageMonths.day/usageMonths.lastMonthDay*usageAr;
        var calcStr="";
        if(usageMonths.month) {
        	if(usageMonths.day) {
        		calcStr="적용단가("+$.number(applcPrice, false)+"원)*사용면적("+usageAr+"m²)*(사용개월수("+$.number(usageMonths.month, false)+"개월 "+usageMonths.day+"일/"+usageMonths.lastMonthDay+")";
        	}
        	else {
        		calcStr="적용단가("+$.number(applcPrice, false)+"원)*사용면적("+usageAr+"m²)*(사용개월수("+$.number(usageMonths.month, false)+"개월)";
        	}
        }
        else {
    		calcStr="적용단가("+$.number(applcPrice, false)+"원)*사용면적("+usageAr+"m²)*(사용일수("+$.number(usageMonths.day, false)+"일)";

        }
//    	this.$('#rm').val(calcStr);

        calFee = Math.floor(calFee/10)*10;

        this.$('#grCalcFee').val($.number(calFee));
    } else {
        applcTariff = this.$('#applcPrice').val();
		if(applcTariff!=0) {
	    	this.$('#fee').val('');
	        this.$('#rdcxptFee').val('');
		}
    }
};

<%--
	문자열을 받아서 숫자로 리턴한다. (EMD.util.getNumber 추가 예정)
--%>
GamHtldRentMngtModule.prototype.getNumber = function(value) {
    var rnum=value!=undefined?value:0;

    if(typeof rnum=="string") rnum=Number(rnum.replace(/,/g,""));
	return rnum;
};

<%--
버튼 클릭에 대한 이벤트 핸들러 (EmdModule에서 Overriding 된 함수임 모듈에서 자동으로 호출 됨)
스위치문 안에 코드가 길어지면 반드시 하위 함수로 분리 할 것.
    	case 문에 주석을 달때는 case 문 뒤에 붙일 것
--%>
 GamHtldRentMngtModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
	    case 'popupEntrpsInfo': // 팝업을 호출한다.(조회 조건)
	        this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', {});
	        break;
        case 'searchBtn':		// 조회
			this.loadData();
            break;
        case 'addAssetRentFirst':	// 최초신청
        	this.addRentData();
            break;
        case 'addAssetRentRenew':	 // 연장신청
        	this.extendRentData();
            break;
        case 'btnSaveItem':	// 신청저장
			this.storeRentData();
            break;
        case 'btnChangeItem' : //계약변경
        	this.changeRentData();
        	break;
        case 'btnChangeSaveItem': //변경저장
        	this.changeSaveRentData();
        	break;
        case 'btnRemoveItem':	//신청삭제
        case 'btnRemoveItem2':
        	this.deleteRentData();
            break;
        case 'btnNoticeAdit':	// 추가고지
        case 'btnNoticeAdit2':
        	this.addNoticeAdit();
            break;
        case 'btnEApproval':    // 전자결재
        case 'btnEApproval2':
        	this.approvalEA();
            break;
        case 'btnHtldRentListExcelDownload':	// 엑셀 다운로드
        	this.$('#assetRentMngtList').flexExcelDown('/oper/gnrl/selectHtldRentListExcel.do');
            break;
        case 'btnRentFeeMngt':	// 임대료 관리 페이지를 호출한다.
            var rows = this.$('#assetRentMngtList').selectedRows();
            var opts = {};

            if(rows.length>0) {
            	opts = {
            			action: 'selectRentFee',
            			nticVo:{ prtAtCode: rows[0].prtAtCode, mngYear: rows[0].mngYear, mngNo: rows[0].mngNo, mngCnt: rows[0].mngCnt }
            	};
        }
       	 	EMD.util.create_window('gamHtldRentFeeMngt', '배후단지임대료관리', '/oper/htld/gamHtldRentFeeMngt.do', null, opts);
			break;

        case 'popupEntrpsInfoInput': // 팝업을 호출한다.(자산임대신청)
            this.doExecuteDialog('insertEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', {});
            break;
        case 'btnSaveComment':	//코멘트저장
            var inputVO=this.makeFormArgs('#gamAssetRentForm');
            this.doAction('/oper/htld/updateHtldRentComment.do', inputVO, function(module, result) {
                if(result.resultCode=='0') {
                	module._editChanged=false;
    }
                alert(result.resultMsg);
            });
            break;
        case 'btnMangeCharger': // 업체정보관리
       	 	EMD.util.create_window('gamCmpyInfoMngt', '업체정보 관리', '/code/gamCmpyInfoMngt.do', null, {entrpscd:this.$('#entrpscd').val()});
        	break;
        case 'btnInsertItemDetail':	//임대상세추가
        	this.addRentDetailItem();
            break;
        case 'btnRemoveItemDetail':	// 자산임대상세 삭제 (Grid상에서만 삭제됨)
        	this.deleteRentDetailItem();
            break;
        case 'btnAppendBizAssess':
        	this.appendBizAssess();
        	break;
        case 'btnSaveBizAssess':
        	this.saveBizAssess();
        	break;
        case 'btnRemoveBizAssess':
        	this.removeBizAssess();
        	break;
        case 'btnCallBizAssess':
            var rows = this.$('#assetRentMngtList').selectedRows();
            var opts = {};

            if(rows.length>0) {
            	opts = {
            			action: 'selectRentAssess',
            			nticVo:{ prtAtCode: rows[0].prtAtCode, mngYear: rows[0].mngYear, mngNo: rows[0].mngNo, mngCnt: rows[0].mngCnt }
            	};
        	}
        	EMD.util.create_window('gamHtldRentAssessMngt', '배후단지 실적평가 관리', '/oper/htld/gamHtldRentAssessMngt.do', null, opts);
        	break;
        case 'btnCallBizNticAssess':
            var rows = this.$('#assetRentMngtList').selectedRows();
            var opts = {};

            if(rows.length>0) {
            	opts = {
            			action: 'selectRentFee',
            			nticVo:{ prtAtCode: rows[0].prtAtCode, mngYear: rows[0].mngYear, mngNo: rows[0].mngNo, mngCnt: rows[0].mngCnt }
            	};
        	}
        	EMD.util.create_window('gamHtldRentFeeMngt', '배후단지 임대료 고지 관리', '/oper/htld/gamHtldRentFeeMngt.do', null, opts);
        	break;
        case 'btnTerminateItem': //계약해지
        	this.terminateRentData();
        	break;
    }
};

<%--
	전자결재 연동
--%>
GamHtldRentMngtModule.prototype.approvalEA = function() {
    if(this.$('#assetRentMngtList').selectedRowCount()>0) {

        var rows = this.$('#assetRentMngtList').selectedRows()[0];

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
            this.requestEApproval(opts, function(module, msg){
				alert(msg);
                var searchOpt=module.makeFormArgs('#gamAssetRentForm');
                module.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
        	});
        }
    } else {
    	alert("목록에서 결제할 건을 선택하십시오.");
    	return;
    }
};

<%--
	평가 등록 추가
--%>
GamHtldRentMngtModule.prototype.appendBizAssess = function() {
	this._assessNo=this._assessNo || 0;
	this._assessNo++;

	this.$('#bizAssessGrid').flexAddRow({
		_updtId:'I',
    	prtAtCode: this._rentDetail.prtAtCode,
    	mngYear: this._rentDetail.mngYear,
    	mngNo: this._rentDetail.mngNo,
    	mngCnt: this._rentDetail.mngCnt,
		assessNo:this._assessNo,
		assessPdFrom:'',
		assessPdTo:'',
		assessSeNm:'',
		assessDt:EMD.util.getDate(),
		assessResult:0,
		rm:''
	});
};

<%--
	평가 등록 삭제
--%>
GamHtldRentMngtModule.prototype.removeBizAssess = function() {
    var rowId=this.$('#bizAssessGrid').selectedRowIds();

    if(rowId.length == 0) {
        alert("삭제할 항목을 선택하십시오.");
    } else {
		for(var i=rowId.length-1; i>=0; i--) {
		    var row=this.$('#bizAssessGrid').flexGetRow(rowId[i]);

		    if(row._updtId==undefined || row._updtId!='I') {
		    	this._deleteAssessList[this._deleteAssessList.length]=row;
		    }
		    this.$('#bizAssessGrid').flexRemoveRow(rowId[i]);
		}
    }
};

<%--
	평가 등록 저장
--%>
GamHtldRentMngtModule.prototype.saveBizAssess = function() {
	var assessList={};
	assessList['_cList'] = JSON.stringify(this.$('#bizAssessGrid').selectFilterData([{col: '_updtId', filter: 'I'}]));
	assessList['_uList'] = JSON.stringify(this.$('#bizAssessGrid').selectFilterData([{col: '_updtId', filter: 'U'}]));
	assessList['_dList'] = JSON.stringify(this._deleteAssessList);

	this.doAction('/oper/htld/updateHtldAssessList.do', assessList, function(module, result) {
        if(result.resultCode == 0){
        	module.loadAssessList();
        	module._editChanged=false;
        }
        alert(result.resultMsg);
    });
};

<%--
	임대계약 추가
--%>
GamHtldRentMngtModule.prototype.addRentData = function() {
	this.$("#assetRentMngtList").noSelect();
	this._detailMode="I";
    this.$("#assetRentListTab").tabs("option", {active: 1});  // 탭을 전환 한다.

    // 기본값 정의
    var today = EMD.util.getDate();

	this.$('#prtAtCode').val('622');
	this.$('#payMth').val('Pre');
	this.$('#intrRate').val('0');
	this.$('#nticMth').val('4');
	this.$('#taxtSe').val('2');
	this.$('#applcMth').val('6'); //적용방법
	this.$('#applcTariff').val('0.05'); //적용요율
	this.$('#exemptSe').val('0'); // 면제구분

    this.doExecuteDialog('insertEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', {});
};

<%--
	임대계약 연장
--%>
GamHtldRentMngtModule.prototype.extendRentData = function() {
	var rows = this.$('#assetRentMngtList').selectedRows();

	if (rows.length >= 1) {
		if (confirm("연장신청을 하시겠습니까?")) {
			this.doAction('/oper/htld/insertHtldRentExtend.do', rows[0],
					function(module, result) {
						if (result.resultCode == '0') {
							module._loadedItem = false;
							module._selectedItem = result.rentVo;
							module._editMode = '';
							this._detailMode == "X";
							module.$("#assetRentListTab").tabs("option", {
								active : 1
							});
						} else
							alert(result.resultMsg);
					});
		}
	} else {
			alert("목록에서 연장신청할 업체를 선택하십시오.");
	}
};

<%--
임대계약 저장
--%>
GamHtldRentMngtModule.prototype.storeRentData = function() {
	if(!validateGamAssetRent(this.$('#gamAssetRentForm')[0])) {
        return;
    }

    if( this.$('#nticMth').val() != '1' && this.$('#intrRate').val() == '' ) {
        alert("고지방법이 분납인 경우는 분납이자율을 입력하십시오.");
        return;
    }

    if(this.$('#chrgeKnd').val()=='') {
    	alert("요금 종류 코드를 선택 하십시요.");
    	return;
    }

   if( confirm("저장하시겠습니까?") ) {
    	if(this._detailMode=="") {	// update data
    		var assetRent = {};
    		assetRent['assetRentVo'] = JSON.stringify(this.getFormValues('#gamAssetRentForm'));

    		assetRent['_cList'] = JSON.stringify(this.$('#assetRentDetailList').selectFilterData([{col: '_updtId', filter: 'I'}]));
    		assetRent['_uList'] = JSON.stringify(this.$('#assetRentDetailList').selectFilterData([{col: '_updtId', filter: 'U'}]));
    		assetRent['_dList'] = JSON.stringify(this._deleteDataList);

            this.doAction('/oper/htld/updateHtldRent.do', assetRent, function(module, result) {
                if(result.resultCode == 0){
                	module.loadDetail();
                	module._editChanged=false;
                	module.setButtonStatus();
                	module.showTermnKndTr();
                }
                alert(result.resultMsg);
            });
		}
    	else {	// insert data
    		var assetRent = {};
    		assetRent['assetRentVo'] = JSON.stringify(this.getFormValues('#gamAssetRentForm'));
    		assetRent['_cList'] = JSON.stringify(this.$('#assetRentDetailList').flexGetData());

            this.doAction('/oper/htld/insertHtldRent.do', assetRent, function(module, result) {
                if(result.resultCode == 0){
                	module._loadedItem=false;
                	module._editChanged=false;
                	module._detailMode="";
                	module.loadData();
//                	module._selectedItem=result.newRentVo;
//                	module.makeFormValues('#gamAssetRentForm', result.newRentVo);
//                	module.loadDetail();
                    }
                    alert(result.resultMsg);
           	});
       }
    }
};

<%--
임대계약 변경
--%>
GamHtldRentMngtModule.prototype.changeRentData = function() {
	var rows = this.$('#assetRentMngtList').selectedRows();
	if (rows.length >= 1) {
		this._detailMode = 'C';
		this.$("#assetRentListTab").tabs("option", { active : 1 });
	} else {
		alert("목록에서 변경신청할 계약을 선택하십시오.");
	}
};

<%--
임대계약 변경저장
--%>
GamHtldRentMngtModule.prototype.changeSaveRentData = function() {
	if(!validateGamAssetRent(this.$('#gamAssetRentForm')[0])) {
        return;
    }

    if( this.$('#nticMth').val() != '1' && this.$('#intrRate').val() == '' ) {
        alert("고지방법이 분납인 경우는 분납이자율을 입력하십시오.");
        return;
    }

    if(this.$('#chrgeKnd').val()=='') {
    	alert("요금 종류 코드를 선택 하십시요.");
    	return;
    }

    if(this.$('#termnRm').val()=='') {
    	alert("변경 사유를 입력 하십시요.");
    	return;
    }

   if( confirm("변경신청을 하면 기존의 계약은 해지상태가 되고 새로운 계약신청이 이루어집니다. 변경하시겠습니까?") ) {
	   		this.$('#termnKnd').val('1'); //계약해지코드 자동 입력 
    		var assetRent = {};
    		assetRent['assetRentVo'] = JSON.stringify(this.getFormValues('#gamAssetRentForm'));
    		assetRent['_cList'] = JSON.stringify(this.$('#assetRentDetailList').flexGetData());

            this.doAction('/oper/htld/changeHtldRent.do', assetRent, function(module, result) {
                if(result.resultCode == 0){
                	module._loadedItem=false;
                	module._editChanged=false;
                	module._detailMode="";
                	module.loadData();
                }
                alert(result.resultMsg);
           	});
    }
};

<%--
	임대계약 삭제
--%>
GamHtldRentMngtModule.prototype.deleteRentData = function() {
            var rows = this.$('#assetRentMngtList').selectedRows();

            if(rows.length == 0) {
                alert("자산임대목록에서 신청삭제할 행을 선택하십시오.");
            } else {
            	if( confirm("신청삭제를 하시겠습니까?") ) {

            this.doAction('/oper/htld/deleteHtldRent.do', rows[0], function(module, result) {

                        if(result.resultCode=='0') {
                        	module.loadData();
                        }

                        alert(result.resultMsg);
                    });

                    this.$("#assetRentListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
                }
            }
  };
  
 <%--
 	임대계약 해지
 --%>
GamHtldRentMngtModule.prototype.terminateRentData = function() {
	var rows = this.$('#assetRentMngtList').selectedRows();
	if(rows.length == 0) {
		alert('임대계약을 해지할 행을 선택하십시오.');
		return;
	}
	var row = rows[0];
	if(row.termnKnd >= '1') {
		alert('이미 해지되거나 변경된 임대료 계약입니다.');
		return;
	}
	if(confirm('계약해지시 미고지 임대료 정보는 삭제됩니다. 계약해지를 하시겠습니까?')) {
		rows[0].termnKnd = '1';
		this.doAction('/oper/htld/terminateHtldRent.do', rows[0], function(module, result) {
			if(result.resultCode=='0') {
				module.loadData();
			}
			alert(result.resultMsg);
		});
		this.$("#assetRentListTab").tabs("option", {active: 0});
	}
};
  

<%--
	추가고지 기존 고지분에 추가로 고지를 한다.
--%>
GamHtldRentMngtModule.prototype.addNoticeAdit = function() {
            var rows = this.$('#assetRentMngtList').selectedRows();

    if(rows.length>=1) {
    	var row=rows[0];
    	/*
            if( row['prmisnYn'] != 'Y' ) {
                alert("승낙된 상태가 아닙니다.");
                return;
            }
    	*/
        this.doExecuteDialog('insertLevReqestAdit', '추가 사용료 고지', '/oper/gnrl/popupLevReqestAdit.do', row);

            } else {
                alert("목록에서 선택하십시오.");
            }
};

<%--
	임대 상세 추가.
--%>
GamHtldRentMngtModule.prototype.addRentDetailItem = function() {
	this.doExecuteDialog('selectAssetsCdRentPopup', '시설 선택', '/popup/showAssetsCd.do', {}, {"gisAssetsPrprtySeCd":"L"});
};

<%--
	임대 상세 삭제
--%>
GamHtldRentMngtModule.prototype.deleteRentDetailItem = function() {
    var rowId=this.$('#assetRentDetailList').selectedRowIds();

    if(rowId.length == 0) {
        alert("자산임대상세목록에서 삭제할 행을 선택하십시오.");
    } else {
		for(var i=rowId.length-1; i>=0; i--) {
		    var row=this.$('#assetRentDetailList').flexGetRow(rowId[i]);

		    if(row._updtId==undefined || row._updtId!='I') {
		    	this._deleteDataList[this._deleteDataList.length]=row;
		    }
		    this.$('#assetRentDetailList').flexRemoveRow(rowId[i]);
		}

		this.onCalc();
    }
};

<%--
	임대 내역 밸리데이션
--%>
GamHtldRentMngtModule.prototype.validateRentDetail = function() {
        	if(!validateGamAssetRentDetail(this.$('#gamAssetRentDetailForm')[0])) {
        return false;
            }

            if( this.$('#gisAssetsCd').val() == '' ) {
                alert("자산을 조회하여 선택하십시오.");
        return false;
            }

            if( this.$('#usagePdFrom').val() == '' ) {
                alert("사용기간(시작)을 선택하십시오.");
        return false;
            }

            if( this.$('#usagePdTo').val() == '' ) {
                alert("사용기간(종료)을 선택하십시오.");
        return false;
            }

            if( this.$('#usageAr').val() == '' ) {
                alert("사용면적를 입력하십시오.");
        return false;
            }

            if( this.$('#applcMth').val() == '' ) {
                alert("적용방법을 선택하십시오.");
        return false;
            }
    else {
    	switch(this.$('#applcMth').val()) {
    	case '1':	// 국유재산법
    		if( this.$('#olnlp').val() == '' ) {
                alert("공시지가를 입력하십시오.");
                return false;
    		}
    		if( this.$('#applcTariff').val() == '' ) {
                alert("적용요율을 선택하십시오.");
                return false;
            }
    		break;
    	case '2':	// 공사규정
    		break;
    	case '3':	// 입찰
    		break;
    	case '4':	// 무역항규정
    	case '6':	// 배후단지
    		if( this.$('#applcPrice').val() == '' ) {
                alert("적용단가를 입력하십시오.");
                return false;
    		}
    		break;
    	case '5':	// 임대계약서
    		break;
    	case '9':	// 기타
    		break;
    	}
    }

            if( this.$('#exemptSe').val() == '' ) {
                alert("면제구분을 선택하십시오.");
        return false;
            }

            if( this.$('#exemptSe').val() == '1' ) {
                if( this.$('#exemptPdFrom').val() == '' ) {
                    alert("면제기간(시작)을 선택하십시오.");
            return false;
                }
                if( this.$('#exemptPdTo').val() == '' ) {
                    alert("면제기간(종료)을 선택하십시오.");
            return false;
                }
            }

            if( this.$('#fee').val() == '' ) {
                alert("사용료를 입력하십시오.");
        return false;
            }

	return true;
};

<%--
	파일 적용
--%>
GamHtldRentMngtModule.prototype.applyPhotoData = function() {

	if(!validateGamAssetRentFile(this.$('#gamAssetRentFileForm')[0])) {
        return;
    }

	var selectRow = this.$('#assetRentFileList').selectedRows();
	if(selectRow.length > 0) {

    var row=selectRow[0];
	  		var rowid=this.$("#assetRentFileList").selectedRowIds()[0];
	  		row=this.getFormValues('#gamAssetRentFileForm', row);
        if(row["_updtId"]!='I') row["_updtId"]='U';   // 삽입된 데이터가 아니면 업데이트 플래그를 추가한다.
        this.$('#assetRentFileList').flexUpdateRow(rowid, row);
    }
    else {
    }
};

<%--
	EmdModule에서 Overriding 된 Submit 함수.
	모듈에서 엔터키를 입력 하거나 submitButton 클래스의 버튼이 눌려졌을때 호출되는 이벤트 함수. (포커스에 따라 동작 안될 때도 있음.)
--%>
GamHtldRentMngtModule.prototype.onSubmit = function() {
    this.loadData();
};

<%--
	메인 그리드의 데이터를 조회 한다.
--%>
GamHtldRentMngtModule.prototype.loadData = function() {
    this.$("#assetRentListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamAssetRentMngtSearchForm');
    this.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();

    this._deleteDataList=[];
};

<%--
	실적평가 목록을 조회 한다.
--%>
GamHtldRentMngtModule.prototype.loadAssessList = function() {
	if(this._rentDetail==null) {
		alert('선택한 계약이 없습니다.');
		return;
	}
    var searchOpt=EMD.util.objectToArray({
    	prtAtCode: this._rentDetail.prtAtCode,
    	mngYear: this._rentDetail.mngYear,
    	mngNo: this._rentDetail.mngNo,
    	mngCnt: this._rentDetail.mngCnt
    });
    this.$('#bizAssessGrid').flexOptions({params:searchOpt}).flexReload();
};

<%--
	고지내역을 조회 한다.
--%>
GamHtldRentMngtModule.prototype.loadNticList = function() {
	if(this._rentDetail==null) {
		alert('선택한 계약이 없습니다.');
		return;
	}
    var searchOpt=EMD.util.objectToArray({
    	prtAtCode: this._rentDetail.prtAtCode,
    	mngYear: this._rentDetail.mngYear,
    	mngNo: this._rentDetail.mngNo,
    	mngCnt: this._rentDetail.mngCnt
    });
    this.$('#nticListGrid').flexOptions({params:searchOpt}).flexReload();
};

<%--
탭이 변경 되기 전에 호출되는 이벤트 핸들러 : 리턴값이 false이면 탭 변경이 취소되어 탭이 바뀌질 않는다.
--%>
GamHtldRentMngtModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
    	if(this._editChanged && (this._detailMode=="I")) {
    		if(!confirm('수정된 내역이 있습니다. 수정을 취소 하시겠습니까?')) {
    			return false;
    		}
    		this._detailMode=="";
    	}
        break;
    case 'tabs2':
    	if(this._detailMode=="I" || this._selectedItem!=null) return;
   		alert('임대 목록에서 임대 내역을 선택 해 주시기 바랍니다.');
   		return false;
    case 'tabs3':
        break;
      default:
        return true;
	}
	return true;
};

<%--
	탭이 변경 된 후 호출 되는 이벤트 핸들러
--%>
GamHtldRentMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
    	this._loadedItem=false;
        break;
    case 'tabs2':
   		this.loadDetail();
        break;
    case 'tabs3':
    	this.loadAssessList();
        break;
    case 'tabs4':
    	this.loadNticList();
        break;
        }
};

<%--
	탭 변경 시 상세 폼 데이터를 로딩 한다.
--%>
GamHtldRentMngtModule.prototype.loadDetail = function(mode) {
	var row;
	if(this._detailMode=='I') {
    	row={}; // clear
    	this._selectedItem=row;
    	this.makeFormValues('#gamAssetRentForm', this._selectedItem);
    	this.$('#assetRentDetailList').flexEmptyData();
    	this.setButtonStatus();
    	this._loadedItem=true;
	}
	else {	// if modify
	    if(this._selectedItem==null) {
	    	alert('선택된 항목이 없습니다.');
	    }
        else {
	        this.doAction('/oper/htld/selectHtldRentDetailPk.do', this._selectedItem, function(module, data) {
	        	if(data.resultCode!=0) {
	        		alert(data.resultMsg);
	        		return;
	        	}
	        	module.makeFormValues('#gamAssetRentForm', data.result);

	        	module._rentDetail = data.result;
	        	
	        	module.$('#assetRentDetailList').flexEmptyData();
	        	var detail={resultList: data.resultDetailList};
	        	module.$('#assetRentDetailList')[0].dgrid.p.preProcess(module, detail);
	        	module.$('#assetRentDetailList').flexAddData(detail);

	        	module.onCalc();	//  고지방법에 따른 1회차 사용료 적용
	        	module.setButtonStatus();
	        	module.showTermnKndTr();
	        	module._loadedItem=true;
	        });
        }
	}
	this._deleteRentDetailItem=[];	// 삭제 목록 초기화
	this.setButtonStatus();
	this.showTermnKndTr();
};

<%--
	팝업이 종료 될때 리턴 값이 오출 된다.	EmdModule에서 호출 함.
		popupId : 팝업 대화상자 아이디
		msg : 팝업에서 전송한 메시지 (취소는 cancel)
		value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
--%>
GamHtldRentMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     case 'selectEntrpsInfoPopup':
         if (msg != 'cancel') {
             this.$('#sEntrpscd').val(value.entrpscd);
             this.$('#sEntrpsNm').val(value.entrpsNm);
         } else {
         }
         break;
     case 'insertEntrpsInfoPopup':
         if (msg != 'cancel') {
             this.$('#entrpscd').val(value.entrpscd);
             this.$('#entrpsNm').val(value.entrpsNm);
             this.loadEntrpsChargerList();	// 담당자 목록을 불러온다.
         } else {
         }
         break;
     case 'insertAssetRentPrmisnPopup':
         if (msg != 'cancel') {
             if( value == "0" ) {
					this.loadData();
             }
         } else {
         }
         break;
     case 'insertLevReqestAdit':
    	 break;
     case 'selectAssetsCdRentPopup':
         if (msg != 'cancel') {
        	 value._updtId="I";
        	 //value.usageAr=Number(value.gisAssetsRealRentAr);
        	 this.$('#assetRentDetailList').flexAddRow(value);
        	 this.onCalc();
         } else {
         }
         break;

     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');

         break;
     }
};

<%--
다음 변수는 고정 적으로 정의 해야 함
	module_instance는 고정 변수 GamHtldRentMngtModule은 위에서 EmdModule을 상속 받는 이 윈도우의 모듈 함수로 정의 됨.
--%>
var module_instance = new GamHtldRentMngtModule();

</script>
<%--
	이 페이지를 로딩 하기 위한 window_id 변수. jsp를 호출 하는 model에 담겨서 와야 함.
--%>
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetRentMngtSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>구역</th>
                            <td>
								<input size="10" id="sRentArea" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM062" value=""/>
                            <!--
                                <input id="sMngYear" type="text" class="mngYear" size="4">
                                <input id="sMngNo" type="text" class="mngNo" size="3">
                                <input id="sMngCnt" type="text" class="mngCnt" size="2">
                                 -->
                            </td>
                            <th>신청업체</th>
                            <td colspan="3">
                            	<input id="sEntrpscd" type="text" size="10">&nbsp; &nbsp;
                            	<input id="sEntrpsNm" type="text" size="15" >&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>

                            <td rowSpan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                            <th>조회일자</th>
                            <td>
                            <input id="sGrUsagePdFrom" type="text" class="emdcal" size="8">
                            <input id="sGrUsagePdTo" type="text" class="emdcal" size="8">
                            </td>
                            <th>총면적</th>
                            <td>
                                <input id="sGrArFrom" class="ygpaNumber" size="5">~
                                <input id="sGrArTo" class="ygpaNumber" size="5"> m<sup>2</sup>
                            </td>
                            <th>계약해지/변경</th>
                            <td>
                            	<select id="sTermnKnd">
                            		<option value="">전체</option>
                            		<option selected="selected" value="0">사용</option>
                            		<option value="1">해지</option>
                            		<option value="2">변경</option>
                            	</select>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
            <ul>
                <li><a href="#tabs1" class="emdTab">배후단지임대 목록</a></li>
                <li><a href="#tabs2" class="emdTab">배후단지임대 내역</a></li>
                <li><a href="#tabs3" class="emdTab">실적평가</a></li>
                <li><a href="#tabs4" class="emdTab">고지내역</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;">
                <table id="assetRentMngtList" style="display:none" class="fillHeight"></table>

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
							</tr>
						</table>
    	               	<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                                <button id="addAssetRentFirst">계약등록</button>
	                                <button id="addAssetRentRenew">계약연장</button>
	                                <button id="btnChangeItem" >계약변경</button>
	                                <button id="btnTerminateItem" >계약해지</button>
	                                <button id="btnRemoveItem">삭제</button>
	                                <button id="btnEApproval">결재요청</button>
	                                <!--
	                                <button id="btnHtldRentListExcelDownload">엑셀</button>
	                                 -->
                  					<button data-role="gridXlsDown" data-flexi-grid="assetRentMngtList" data-xls-name="배후단지임대목록.xls" data-xls-title="배후단지 임대목록">엑셀</button>
                      	            <button id="btnRentFeeMngt">임대료 고지관리</button>
	                                <!-- <button id="btnNoticeAdit">추가고지</button> -->
	                                <!-- <button id="btnShowMap">맵조회</button> -->
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage " style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamAssetRentForm">
						<input type="hidden" id="prtAtCode" value="622"/>
						<input type="hidden" id="quayGroupCd"/>
						<input type="hidden" id="mngYear"/>
						<input type="hidden" id="mngNo"/>
						<input type="hidden" id="mngCnt"/>
						<input type="hidden" id="termnKnd"/>
                        <table class="editForm">
                            <tr>
								<th width="10%" height="18">구역</th>
                                <td>
									<input size="10" id="rentAreaCd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM062" value=""/>
                                </td>
								<th width="10%" height="18">신청업체</th>
                                <td colspan="3">
                                    <input type="text" size="10" id="entrpscd" maxlength="10" readonly/>
                                    <button id="popupEntrpsInfoInput" class="popupButton">선택</button>
                                    <input type="text" size="30" id="entrpsNm" disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">영업개시일</th>
                                <td>
									<input type="text" size="12" id="operYrMt" maxlength=""/>
                                </td>
								<th width="10%" height="18">계약기간</th>
                                <td colspan="3">
                                    <input type="text" size="12" id="grUsagePdFrom" class="emdcal"/>~
                                    <input type="text" size="12" id="grUsagePdTo" class="emdcal"/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">업종</th>
                                <td>
									<input type="text" size="20" id="compTp"/>
                                </td>
								<th width="10%" height="18">취급화종</th>
                                <td>
                                    <input type="text" size="20" id="frghtTp" />
                                </td>
								<th width="10%" height="18">임대면적</th>
                                <td><input type="text" size="16" class="ygpaNumber" data-decimal-point="2" id="grAr" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">고지방법</th>
                                <td>
                                    <input id="nticMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM008" />
                                </td>
								<th width="10%" height="18">납부방법</th>
                                <td>
                                    <input id="payMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM043" />
                                </td>
								<th width="10%" height="18">과세구분</th>
                                <td>
                                    <input id="taxtSe" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM016" />
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">비고</th>
                                <td colspan="3"><input type="text" size="60" id="rm" maxlength="90"/></td>
                                <th width="10%" height="18">요금종류 </th>
                                <td>
                                    <input id="chrgeKnd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM053" />
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">코멘트</th>
                                <td colspan="5">
                                	<input type="text" size="106" id="cmt" maxlength="80"/>
                                	<button id="btnSaveComment">코멘트저장</button>
                                </td>
                            </tr>
                            <tr id="termnRmTr">
								<th width="10%" height="18">변경사유</th>
                                <td colspan="5">
                                	<input type="text" size="106" id="termnRm" maxlength="300"/>
                                </td>
                            </tr>
                        </table>
                    </form>

	                 <table class="searchPanel fillHeight">
	                    <tbody>
	                    <tr>
	                        <th width="70%">배후단지임대 상세목록</th>
	                        <th style="text-align:right">
                                  <button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="assetRentDetailList" data-style="default">맵조회</button>
	                        	<button id="btnInsertItemDetail" class="buttonAdd">임대상세추가</button>
	                        	<button id="btnRemoveItemDetail" class="buttonDelete">임대상세삭제</button>
	                        </th>
	                    </tr>
	                    </tbody>
	                 </table>
	                 <table id="assetRentDetailList" style="display:none"></table>

	                 <table style="width:100%">
	                    <tr>
	                        <td><!-- <button id="xxxx">GIS 등록</button><button id="xxxx">위치조회</button> --></td>
	                        <td width="100"></td>
	                        <td style="text-align:right">
	                        <button id="btnEApproval2">결재요청</button>
	                        <button id="btnRemoveItem2" class="buttonDelete">신청삭제</button>
	                        <button id="btnSaveItem" class="buttonSave">신청저장</button>
	                        <button id="btnChangeSaveItem" class="buttonSave">변경저장</button>
	                        </td>
	                    </tr>
	                 </table>
                 </div>
            </div>
            <div id="tabs3" class="emdTabPage fillHeight" style="overflow: hidden;">
                <div class="emdControlPanel">
                <table id="bizAssessGrid" style="display:none" class="fillHeight"></table>
                	<button id="btnAppendBizAssess">추가</button>
                	<button id="btnRemoveBizAssess">삭제</button>
                	<button id="btnSaveBizAssess">저장</button>
                	<!-- <button id="btnCallBizAssess">평가관리</button> -->
                	<button data-role="gridXlsDown" data-flexi-grid="bizAssessGrid" data-xls-name="배후단지실적평가.xls" data-xls-title="배후단지 실적평가">엑셀</button>
               	</div>
            </div>
            <div id="tabs4" class="emdTabPage fillHeight" style="overflow: hidden;">
                <div class="emdControlPanel">
                <table id="nticListGrid" style="display:none" class="fillHeight"></table>
                	<button id="btnCallBizNticAssess">임대료고지 관리</button>
                	<button data-role="gridXlsDown" data-flexi-grid="nticListGrid" data-xls-name="배후단지임대료고지내역.xls" data-xls-title="배후단지 임대료 고지 내역">엑셀</button>
              	</div>
            </div>
        </div>
    </div>
</div>