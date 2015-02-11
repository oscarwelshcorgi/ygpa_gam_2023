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
 	아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.	(주석 표준화 할 것!!)
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
                    {display:'입주면적(m<sup>2</sup>)', name:'grAr',width:88, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'업종', name:'compTp',width:110, sortable:false,align:'right'},
                    {display:'계약기간', name:'grUsagePdPeriod',width:175, sortable:false,align:'center'},
                    {display:'운영연월', name:'operYrMt',width:80, sortable:false,align:'center'},
                    {display:'취급화종', name:'frghtTp',width:120, sortable:false,align:'center'}
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
//            	this.payinstIntrrateDisp = this.payinstIntrrate+ ' %';
            	this.grUsagePdPeriod = this.grUsagePdFrom+" ~ " +this.grUsagePdTo;
            });

            return data;
        }
    });

    this.$("#assetRentMngtList").on('onItemSelected', function(event, module, row, grid, param) {
		// 항목에 따른 버튼 세팅
    	module._loadedItem=false;
    	module._editChanged=false;
    	module._editable=false;
    	module._selectedItem=row;
    	module.setButtonStatus();
    });

    this.$("#assetRentMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
    	module._loadedItem=false;
    	module._editChanged=false;
    	module._editable=false;
    	module._selectedItem=row;
        module.$("#assetRentListTab").tabs("option", {active: 1});
    });

    // 자산임대상세 테이블 설정
    this.$("#assetRentDetailList").flexigrid({
        module: this,
        dataType: 'json',
        colModel : [
                    {display:'자산코드', name:'gisAssetsCdStr',width:60, sortable:false,align:'center'},
                    {display:'자산명', name:'gisAssetsNm',width:140, sortable:false,align:'left'},
                    {display:'소재지', name:'gisAssetsLocplcAll',width:200, sortable:false,align:'center'},
                    {display:'자산면적', name:'gisAssetsAr',width:80, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'사용면적', name:'usageAr',width:80, sortable:false,align:'right', displayFormat: 'input-number', displayOption:"0,000.00"}
                    ],
        showTableToggleBtn: true,
        height: '120',
        preProcess: function(module, data) {
        	$.each(data.resultList, function() {
        		this._updtId = '';
        		module.makeAssetCd(this);
    });
        	return data;
        }
    });

    this.$("#assetRentDetailList").on('onItemSelected', function(event, module, row, grid, param) {
    	module._selectRentDetailItem=row;
    	module._rentDetailMode='';
    });

    this.$("#assetRentDetailList").on('onItemDoubleClick', function(event, module, row, grid, param) {
    	module._selectRentDetailItem=row;
    	module._rentDetailMode='';
        module.$("#assetRentListTab").tabs("option", {active: 2});
    });

    this.$("#assetRentDetailList").on('onCellEdited', function(event, module, row, grid, param) {
    	module.onCalc();
    });

    // 실적평가 그리드 설정
    this.$("#bizAssessGrid").flexigrid({
        module: this,
        dataType: 'json',
        colModel : [
                    {display:'회차', name:'photoSeq', width:80, sortable:true, align:'center'},
                    {display:'평가기간', name:'photoSj', width:240, sortable:true, align:'left'},
                    {display:'평가일', name:'photoDesc', width:100, sortable:true, align:'left'},
                    {display:'평기결과', name:'shotDt', width:100, sortable:true, align:'center'}
                    ],
        showTableToggleBtn: false,
        height: '240'
    });

    this.$("#bizAssessGrid").on('onItemSelected', function(event, module, row, grid, param) {
        module.makeFormValues('#gamAssetRentFileForm', row);
        module._editDataAssess=module.getFormValues('#gamAssetRentFileForm', row);
        module._editRowAssess=module.$('#assetRentFileList').selectedRowIds()[0];
    });

    this.setEvents();

    // 초기값 정의
    this.$('#sGrUsagePdFrom').val(EMD.util.getDate());

    this._editMode='';
    this._detailMode="";
    this._loadedItem=false;

	this._editable=false;

    this.setButtonStatus();

    this.loadData();	// 데이터 조회

    console.log('debug');	// debug가 종료 되면 반드시 제거 할 것
};

<%--
	이벤트 정의
--%>
GamHtldRentMngtModule.prototype.setEvents = function() {
	// tabs 2
    this.$('#applcTariff').on('change', {module: this}, function(event) {
        if( $(this).val() == '' ) {
            event.data.module.$('#applcTariffNm').val("");
        } else {
            event.data.module.$('#applcTariffNm').val($(this).getSelectedCodeLabel());
        }
    });
    this.$('#exemptSe').on('change', {module: this}, function(event) {
    	event.data.module.onExemptSeChange($(this).val());
    });
    this.$('#applcMth').on('change', {module: this}, function(event) {
        event.data.module.onApplcMthChange($(this).val());
    });
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
    this.$('#payinstIntrrate').on('change keyup', {module: this}, function(event) {
        event.data.module.calcFirstPaymentAmount();
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
			this.$('#addAssetRentRenew').show();
			this.$('#btnEApproval').hide();	// 결재 요청 disable
			if(rows[0].prmisnYn=='Y') {
				this.$('#btnRemoveItem').hide();
				this.$('#btnRentFeeMngt').show();
        	}
			else {
				this.$('#btnRemoveItem').show();
				this.$('#btnRentFeeMngt').hide();
			}
			this.$('#btnHtldRentListExcelDownload').show();
		}
		else {
			this.$('#addAssetRentRenew').hide();
			this.$('#btnRemoveItem').hide();
			this.$('#btnEApproval').hide();
			this.$('#btnRentFeeMngt').hide();
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
			this._editable=true;
        }
		else {
			var rows = this.$('#assetRentMngtList').selectedRows();
			if(rows.length) {
				this.$('#btnEApproval2').hide();	// 결재 요청 disable
				if(rows[0].prmisnYn=='Y') {
					this.$('#btnRemoveItem2').hide();

//					this.$('#btnSaveItem').hide(); // test
					this.$('#btnSaveItem').show();

					this.$('#popupEntrpsInfoInput').hide();
					this.$('#btnMangeCharger').hide();

					this.$('#btnInsertItemDetail').hide();
					this.$('#btnRemoveItemDetail').hide();

			        this.$('#entrpscd').attr('readonly', true);
			        this.$('#popupEntrpsInfoInput').attr('disabled', 'disabled');
	}
				else {
					this.$('#btnRemoveItem2').show();

					this.$('#btnSaveItem').show();
					this._editable=true;

					this.$('#popupEntrpsInfoInput').show();
					this.$('#btnMangeCharger').show();

					this.$('#btnInsertItemDetail').show();
					this.$('#btnRemoveItemDetail').show();

			        this.$('#entrpscd').removeAttr('readonly');
			        this.$('#popupEntrpsInfoInput').removeAttr('disabled');

	}
		        this.$('#btnSaveComment').show();
			}
			else {
				this.$('#btnEApproval2').hide();
				this.$('#btnRemoveItem2').hide();
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
		else {
		}
		break;
	default:
		return;
	}
};

<%--
	자산코드와 주소를 문자열로 변환한다
--%>
GamHtldRentMngtModule.prototype.makeAssetCd = function(assetCd) {
	assetCd.gisAssetsCdStr = assetCd.gisAssetsCd+'-'+assetCd.gisAssetsSubCd;
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
    	this.$('#payinstIntrrate').val(Number(this.$('#cofixList').val()) * 100);
    } else {
    	this.$('#cofixList').val("");
    	this.$('#payinstIntrrate').val("");
    }
    // calc first payment amount
    this.calcFirstPaymentAmount();
};

<%--
	분납 이자율이 변경 되었을 때 호출 되는 이벤트 핸들러
--%>
GamHtldRentMngtModule.prototype.onCofixListChange = function(cofix) {
	//alert('||'+$(this).val()+'||');
    if( cofix == '' ) {
    	this.$('#payinstIntrrate').val("");
    } else {
    	var payinstIntrrateCal = (Number(cofix) * 100) + "";

    	if(payinstIntrrateCal.length > 4) {
    		payinstIntrrateCal = payinstIntrrateCal.substring(0,5);
    		payinstIntrrateCal = Number(payinstIntrrateCal).toFixed(2);
		}

    	this.$('#payinstIntrrate').val( payinstIntrrateCal );
    	this.calcFirstPaymentAmount();
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
	    	console.log('charger list load completed');
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

<%--
요금 산정 방식에 따른 첫회 사용료 계산 함수
--%>
GamHtldRentMngtModule.prototype.calcFirstPaymentAmount = function() {
	var firstAmt=0;
	var nticMth=this.$('#nticMth').val();
	var totalAmount=this.getNumber(this.$('#grFee').val());
	var payinstIntrrate=this.getNumber(this.$('#payinstIntrrate').val())/100;
    var grUsagePdFrom = EMD.util.strToDate(this.$('#grUsagePdFrom').val());
    var grUsagePdTo = EMD.util.strToDate(this.$('#grUsagePdTo').val());
    var fromDt, toDt;
	var totalMonths = grUsagePdTo.getMonth() - grUsagePdFrom.getMonth()+1;
	var totalDays = Math.floor((grUsagePdTo-grUsagePdFrom) / (1000*60*60*24))+1;
    var nDays;
    var firstPayValStr="";

	switch(nticMth) {
	case '2':	// 반기납
		fromDt = grUsagePdFrom;
		toDt = new Date(fromDt);
		toDt.setMonth(fromDt.getMonth()+6);
		toDt=toDt-(1000*60*60*24);
		if(6>=totalMonths) firstAmt=totalAmount;
		else {
			firstAmt = totalAmount*6/totalMonths;
		}
		// 이자율 계산
		firstAmt += (totalAmount-firstAmt) * (payinstIntrrate) /2;
		firstAmt = Math.floor(firstAmt);
		firstPayValStr="반기납 : 총사용료 ("+$.number(totalAmount, true)+"원) ";
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
		toDt=toDt-(1000*60*60*24);
		if(4>=totalMonths) firstAmt=totalAmount;
		else {
			firstAmt=totalAmount*4/totalMonths;
		}
		// 이자율 계산
		firstAmt += (totalAmount-firstAmt) * (payinstIntrrate) /3;
		firstAmt = Math.floor(firstAmt);
		firstPayValStr="3분납 : 총사용료 ("+$.number(totalAmount, true)+"원)";
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
		toDt=toDt-(1000*60*60*24);
		if(3>=totalMonths) firstAmt=totalAmount;
		else {
			nDays = Math.floor((toDt-fromDt) / (1000*60*60*24))+1;
			firstAmt=Math.floor(totalAmount*3/totalMonths);
		}
		firstAmt += (totalAmount-firstAmt) * (payinstIntrrate) /4;
		firstPayValStr="분기납 : 총사용료 ("+$.number(totalAmount, true)+"원)";
		break;
	case '5':	// 월납
		fromDt = grUsagePdFrom;
		toDt = new Date(fromDt);
		toDt.setMonth(grUsagePdFrom.getMonth()+1);
		toDt=toDt-(1000*60*60*24);
		if(1>=totalMonths) firstAmt=totalAmount;
		else {
			nDays = Math.floor((toDt-fromDt) / (1000*60*60*24))+1;
			firstAmt=Math.floor(totalAmount*1/totalMonths);
		}
		firstAmt += (totalAmount-firstAmt) * (payinstIntrrate) /12;
		firstAmt = Math.floor(firstAmt);
		firstPayValStr="월납 : 총사용료 ("+$.number(totalAmount, true)+"원)";
		break;
	case '6':	// 연납
		fromDt = grUsagePdFrom;
		toDt = new Date(fromDt);
		toDt.setMonth(grUsagePdFrom.getMonth()+1);
		toDt=toDt-(1000*60*60*24);
		if(1>=totalMonths) firstAmt=totalAmount;
		else {
			nDays = Math.floor((toDt-fromDt) / (1000*60*60*24))+1;
			firstAmt=Math.floor(totalAmount*1/totalMonths);
		}
		firstAmt += (totalAmount-firstAmt) * (payinstIntrrate) /12;
		firstAmt = Math.floor(firstAmt);
		firstPayValStr="연납 : 총사용료 ("+$.number(totalAmount, true)+"원)";
		break;
	default:
		firstAmt=totalAmount;
		fromDt=grUsagePdFrom;
		toDt=grUsagePdFrom;
		nDays = Math.floor((grUsagePdTo-grUsagePdFrom) / (1000*60*60*24))+1;
		firstPayValStr="일시납 : 총사용료 ("+$.number(totalAmount, true)+"원)";
		break;
	}

	this.$('#firstPayVal').val($.number(firstAmt));
	this.$('#firstPayValStr').text(firstPayValStr);
};

<%--
	적용방법에 따른 변경 이벤트 핸들러
--%>
GamHtldRentMngtModule.prototype.onApplcMthChange = function(applcMth) {
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
		this.$('.nationAssetLaw').hide();
		this.$('.tradePortLaw').hide();
//		this.$('#applcTariff').val('0');
		break;
	default:	// 기타
		this.$('.nationAssetLaw').show();
		this.$('.tradePortLaw').hide();
		break;
	}
    this.onCalc();
};

<%--
	면제 구분 방식에 변경의 이벤트 핸들러
--%>
GamHtldRentMngtModule.prototype.onExemptSeChange = function(exemptSe) {
    if(exemptSe=='0') {
    	this.$('.exempt').hide();
}
    else {
    	this.$('.exempt').show();
    	if(exemptSe=='1') {
    		this.$('#exemptPdFrom').enable();
    		this.$('#exemptPdTo').enable();
    	}
    	else {
    		this.$('#exemptPdFrom').disable();
    		this.$('#exemptPdTo').disable();
    	}
    }

    if( exemptSe != '1' ) {
    	this.$('#exemptPdFrom').val("");
    	this.$('#exemptPdTo').val("");
    }
};

<%--
	국유재산법 사용료 계산
--%>
GamHtldRentMngtModule.prototype.calcNationAssetLaw = function() {
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

/*         calFee = Math.floor(calFee/10)*10;
        rdcxptFee = Math.floor(rdcxptFee/10)*10;

        //(사용료 = 공시지가*((사용일수)/365)*사용면적)*적용요율 ? 감면사용료 )
        if( exemptSe == '2' ) {     // 전체면제 일 경우 사용료는 0
        	rdcxptFee = calFee;
        	exemptCnt = dayUseCnt;
            calFee = 0;
        } else {
            calFee = olnlp*dayUseCnt/365*usageAr*applcTariff - rdcxptFee;
        }
        this.$('#computDtls').val("( 공시지가("+$.number(olnlp, false)+"원)*사용면적("+$.number(usageAr, false)+"m²)*(사용일수("+$.number(dayUseCnt, false)+"일)-면제일수("+$.number(exemptCnt, false)+"일) ) / 365 * "+applcTariffStr);
*/
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
	무역항규정 사용료 계산 함수
--%>
GamHtldRentMngtModule.prototype.calcTradePortLaw = function() {

    if( this.$('#grUsagePdFrom').val() != '' && this.$('#grUsagePdTo').val() != ''
         && this.$('#applcPrice').val() != '' ) {
        var calFee      = 0;  //계산된 사용료
        var usageAr     = 0;  //사용면적
        var applcPrice = 0;  //적용단가
        var rdcxptFee   = 0;  //감면사용료
        var dayUseCnt   = 0;  //사용일수
        var usagePdFrom = ""; //사용기간 from
        var usagePdTo   = ""; //사용기간 to

        this.$('#assetRentDetailList')[0].forEachCell('usageAr', function(cellObj,ind) {
        	usageAr+=Number(cellObj);
        });
        this.$('#grAr').val(usageAr);
        applcPrice = Number(this.$('#applcPrice').val().replace(/,/g, ''));
        usagePdFrom = EMD.util.getDate(this.$('#grUsagePdFrom').val());
    	usagePdTo = new Date(usagePdFrom);
        if(this.$('#nticMth')=='1') {
        	usagePdTo = EMD.util.getDate(this.$('#grUsagePdTo').val());
        }
        else if(this.$('#nticMth')=='2') {
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
        else if(this.$('#nticMth')=='2') {
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
        else if(this.$('#nticMth')=='3') {
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
        else if(this.$('#nticMth')=='4') {
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
        else if(this.$('#nticMth')=='5') {
    		usagePdTo.setMonth(usagePdTo.getMonth()+1);
    		usagePdTo.setDate(0);
        }
        else if(this.$('#nticMth')=='6') {
    		usagePdTo.setMonth(0);
    		usagePdTo.setDate(0);
    		usagePdTo.setYear(usagePdTo.getYear()+1);
        }
        else {
        	EMD.util.showMessage(this.$('#nticMth'), '지원하지 않는 고지방법입니다.');
        }

        /* 날짜계산 */
        var dtFr = EMD.util.strToDate(usagePdFrom);
        var dtTo = EMD.util.strToDate(usagePdTo);

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
    	this.$('#rm').val(calcStr);

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

<%--
	적용방법에 따른 요금 계산
--%>
GamHtldRentMngtModule.prototype.onCalc = function() {
	this.calcTradePortLaw();
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
       	 	EMD.util.create_window('배후단지임대료관리', '/oper/htld/gamHtldRentFeeMngt.do', null, opts);
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
       	 	EMD.util.create_window('업체정보 관리', '/code/gamCmpyInfoMngt.do', null, {entrpscd:this.$('#entrpscd').val()});
        	break;
        case 'btnInsertItemDetail':	//임대상세추가
        	this.addRentDetailItem();
            break;
        case 'btnRemoveItemDetail':	// 자산임대상세 삭제 (Grid상에서만 삭제됨)
        	this.deleteRentDetailItem();
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
	임대계약 추가
--%>
GamHtldRentMngtModule.prototype.addRentData = function() {
	this.$("#assetRentMngtList").noSelect();
	this._detailMode="I";
    this.$("#assetRentListTab").tabs("option", {active: 1});  // 탭을 전환 한다.

    // 기본값 정의
    var today = EMD.util.getDate();
    this.$('#frstReqstDt').val(today);
    this.$('#reqstDt').val(today);

	this.$('#prtAtCode').val('622');
	this.$('#payMth').val('Pre');
	this.$('#payinstIntrrate').val('0');
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

            if( this.$('#nticMth').val() != '1' && this.$('#payinstIntrrate').val() == '' ) {
                alert("고지방법이 분납인 경우는 분납이자율을 입력하십시오.");
                return;
            }

            if( confirm("저장하시겠습니까?") ) {
    	if(this._detailMode=="") {	// update data
    		var assetRent = {};
    		assetRent['assetRentVo'] = JSON.stringify(this.getFormValues('#gamAssetRentForm'));

    		assetRent['_cList'] = JSON.stringify(this.$('#assetRentDetailList').selectFilterData([{col: '_updtId', filter: 'I'}]));
    		assetRent['_uList'] = JSON.stringify(this.$('#assetRentDetailList').selectFilterData([{col: '_updtId', filter: 'U'}]));
    		assetRent['_dList'] = JSON.stringify(this._deleteDataList);

    		assetRent['_cfList'] = JSON.stringify(this.$('#assetRentFileList').selectFilterData([{col: '_updtId', filter: 'I'}]));
    		assetRent['_ufList'] = JSON.stringify(this.$('#assetRentFileList').selectFilterData([{col: '_updtId', filter: 'U'}]));
    		assetRent['_dfList'] = JSON.stringify(this._deleteDataFileList);

            this.doAction('/oper/htld/updateHtldRent.do', assetRent, function(module, result) {
                if(result.resultCode == 0){
                	module.loadDetail();
                	module._editChanged=false;
                	module.setButtonStatus();
                }
                alert(result.resultMsg);
            });
		}
    	else {	// insert data
    		var assetRent = {};
    		assetRent['assetRentVo'] = JSON.stringify(this.getFormValues('#gamAssetRentForm'));
    		assetRent['_cList'] = JSON.stringify(this.$('#assetRentDetailList').flexGetData());
    		assetRent['_cfList'] = JSON.stringify(this.$('#assetRentFileList').flexGetData());

            this.doAction('/oper/htld/insertHtldRent.do', assetRent, function(module, result) {
                    if(result.resultCode == 0){
                	module._loadedItem=false;
                	module._editChanged=false;
                	module.loadDetail();
                    }
                    alert(result.resultMsg);
                });
            }
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
	사용승낙
--%>
GamHtldRentMngtModule.prototype.confirmPrmisn = function() {
            var rows = this.$('#assetRentMngtList').selectedRows();
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
            'quayGroupCd': rows[0]['quayGroupCd'],
                    'taxtSe': rows[0]['taxtSe']
                };

        this.doExecuteDialog('insertAssetRentPrmisnPopup', '승낙', '/oper/htld/popup/showHtldRentPrmisn.do', opts);

            } else {
                alert("목록에서 선택하십시오.");
            }
};

<%--
	사용승낙 취소
--%>
GamHtldRentMngtModule.prototype.cancelPrmisn = function() {
            var rows = this.$('#assetRentMngtList').selectedRows();
            var row = this.$('#assetRentMngtList').selectedRows()[0];

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
            this.doAction('/oper/htld/cancelHtldRentPrmisn.do', rows[0], function(module, result) {
                        if(result.resultCode=='0') {
                        	 module.loadData();
                        }

                        alert(result.resultMsg);
                    });
                }
            } else {
                alert("목록에서 선택하십시오.");
            }
};

<%--
	추가고지 기존 고지분에 추가로 고지를 한다.
--%>
GamHtldRentMngtModule.prototype.addNoticeAdit = function() {
            var rows = this.$('#assetRentMngtList').selectedRows();

    if(rows.length>=1) {
    	var row=rows[0];
            if( row['prmisnYn'] != 'Y' ) {
                alert("승낙된 상태가 아닙니다.");
                return;
            }
        this.doExecuteDialog('insertLevReqestAdit', '추가 사용료 고지', '/oper/gnrl/popupLevReqestAdit.do', row);

            } else {
                alert("목록에서 선택하십시오.");
            }
};

<%--
	임대 상세 추가.
--%>
GamHtldRentMngtModule.prototype.addRentDetailItem = function() {
	this.$('#assetRentDetailList').noSelect();
	this._rentDetailMode='I';
	this.$("#assetRentListTab").tabs("option", {active: 2});  // 탭을 전환 한다.
	this.$("#usagePdFrom").val(EMD.util.getDate());
	this.$("#applcMth").val("6");
	this.onApplcMthChange("6");

	this.doExecuteDialog('selectAssetsCdRentPopup', '시설 선택', '/popup/showAssetsCd.do', {});
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
		    	this._deleteRentDetailItem[this._deleteRentDetailItem.length]=row;
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
	임대 상세 적용
--%>
GamHtldRentMngtModule.prototype.applyRentDetailItem = function() {
	if(!this.validateRentDetail()) {
                    return;
                }
    var detail=this.getFormValues('#gamAssetRentDetailForm', this._selectRentDetailItem);
    detail['_updtId']="U";

    if(this._rentDetailMode=='I') {
    	this.makeAssetCd(detail);
    	this.$('#assetRentDetailList').flexAddRow(detail);
                }
    else {
        this.$('#assetRentDetailList').flexUpdateRow(this._selectRentDetailRow, detail);
            }

	this.calcRentMasterValues();	// 총사용료, 총면적 재 계산

    this.$("#assetRentListTab").tabs("option", {active: 1});  // 탭을 전환 한다.

    this._editChanged=true;
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
        break;
    case 'tabs4':
        break;
        }
};

<%--
	탭 변경 시 상세 폼 데이터를 로딩 한다.
--%>
GamHtldRentMngtModule.prototype.loadDetail  = function(mode) {
	var row;
	if(this._detailMode=='I') {
    	row={}; // clear
    	this._selectedItem=row;
    	this.makeFormValues('#gamAssetRentForm', this._selectedItem);
    	this.$('#assetRentDetailList').flexEmptyData();
    	this.setButtonStatus();
    	if(mode=='detail') {
	        row={
	        		'applcMth': '6',
	        		'applcTariff': '0.05',
	        		'exemptSe': '0'
	        };
		 	this.loadOlnlpList();
		    this.makeFormArgs('#gamAssetRentForm', row);
			this.onApplcMthChange(row['applcMth']);
    	}
    	this._loadedItem=true;
	}
	else {	// if modify
	    if(this._selectedItem==null) {
	    	alert('선택된 항목이 없습니다.');
	    }
        else {
	        this.doAction('/oper/htld/selectHtldRentDetailPk.do', this._selectedItem, function(module, data) {
	        	module.makeFormValues('#gamAssetRentForm', data.result);

	        	module.$('#assetRentDetailList').flexEmptyData();
	        	var detail={resultList: data.resultDetailList};
	        	module.$('#assetRentDetailList')[0].dgrid.p.preProcess(module, detail);
	        	module.$('#assetRentDetailList').flexAddData(detail);

	        	module.onCalc();	//  고지방법에 따른 1회차 사용료 적용
	        	module.setButtonStatus();
	        	module._loadedItem=true;
	        });
        }
	}
	this._deleteRentDetailItem=[];	// 삭제 목록 초기화
	this.setButtonStatus();
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
      		var gisAssetsLocplcAll = value.gisAssetsLocplc;
			if(value.gisAssetsLnm!=undefined && value.gisAssetsLnm!=null) {
				gisAssetsLocplcAll+=" "+value.gisAssetsLnm;
				if(value.gisAssetsLnmSub!=undefined && value.gisAssetsLnmSub!=null) {
					gisAssetsLocplcAll = gisAssetsLocplcAll+"-"+value.gisAssetsLnmSub;
				}
			}
            this.$('#gisAssetsLocplcAll').val(gisAssetsLocplcAll);

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

<%--
	시설코드에 따른 공시 지가를 로드 한다.
--%>
GamHtldRentMngtModule.prototype.loadOlnlpList = function(prtFcltyCd, sel_price) {
	this.$('#olnlpList').empty();
	if(prtFcltyCd==null || prtFcltyCd['gisAssetsPrtAtCode']==undefined
			|| prtFcltyCd['gisAssetsCd']==undefined
			|| prtFcltyCd['gisAssetsSubCd']==undefined) return;
	this._selOlnlpPrice=sel_price;
    this.doAction('/asset/rent/selectOlnlpInfo.do', prtFcltyCd, function(module, result) {
        if(result.resultCode=='0') {
        	var n=false;
       	 var olnlplist = module.$('#olnlpList');
       	 olnlplist.off('change');
       	 olnlplist.empty();
   		 olnlplist.append('<option value="">공시지가선택</option>');
       	 $.each(result.resultList, function() {
       		 if(!n) {
       			 if(module._selOlnlpPrice==olnlp) {
       				olnlplist.append('<option value="'+this.olnlp+'" selected="selected">'+this.olnlpApplyDates+'</option>');
       				n=true;
       			 }
       			 else olnlplist.append('<option value="'+this.olnlp+'">'+this.olnlpApplyDates+'</option>');
       		 }
       		 else olnlplist.append('<option value="'+this.olnlp+'">'+this.olnlpApplyDates+'</option>');
       	 });
   		 olnlplist.on('change', {module: module}, function(event) {
   			 event.data.module.$('#olnlp').val($.number($(this).children(':selected').val()));
   			event.data.module.onCalc();
   		 });
   		if(!n) {
   		olnlplist.find('option:eq(1)').attr("selected","selected");
   		module.$('#olnlp').val(olnlplist.find('option:eq(1)').val());
        }
        }
    });
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
                            <td>
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
	                                <button id="btnRemoveItem">삭제</button>
	                                <button id="btnEApproval">결재요청</button>
	                                <button id="btnHtldRentListExcelDownload">엑셀</button>
                      	            <button id="btnRentFeeMngt">임대료 고지관리</button>
	                                <!-- <button id="btnNoticeAdit">추가고지</button> -->
	                                <!-- <button id="btnShowMap">맵조회</button> -->
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamAssetRentForm">
						<input type="hidden" id="prtAtCode" value="622"/>
						<input type="hidden" id="quayGroupCd"/>
						<input type="hidden" id="mngYear"/>
						<input type="hidden" id="mngNo"/>
						<input type="hidden" id="mngCnt"/>
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
								<th width="10%" height="18">운영연월</th>
                                <td>
									<input type="text" size="12" id="operYrMt"/>
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
									<input type="text" size="40" id="compTp"/>
                                </td>
								<th width="10%" height="18">취급화종</th>
                                <td colspan="3">
                                    <input type="text" size="20" id="frghtTp" />
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">임대면적</th>
                                <td><input type="text" size="16" class="ygpaNumber" data-decimal-point="2" id="grAr" disabled/></td>
								<th width="10%" height="18">적용단가</th>
                                <td><input type="text" size="16" class="ygpaNumber" id="applcPrice"/></td>
								<th width="10%" height="18">첫회 임대료</th>
                                <td><input type="text" size="16" class="ygpaNumber" id="grFee" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">고지방법</th>
                                <td>
                                    <input id="nticMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM008" />
                                </td>
								<th width="10%" height="18">분납이자율</th>
                                <td colspan="3">
									<input type="text" size="5" id="payinstIntrrate" maxlength="4" size="5"/>
                                    <select id="cofixList">
                                        <option value="">선택</option>
                                        <c:forEach items="${cofixList}" var="cofixListItem">
                                            <option value="${cofixListItem.code }">${cofixListItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">납부방법</th>
                                <td>
                                    <input id="payMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM043" />
                                </td>
								<th width="10%" height="18">과세구분</th>
                                <td colspan="3">
                                    <input id="taxtSe" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM016" />
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">비고</th>
                                <td colspan="5"><input type="text" size="120" id="rm" maxlength="90"/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">코멘트</th>
                                <td colspan="5">
                                	<input type="text" size="106" id="cmt" maxlength="80"/>
                                	<button id="btnSaveComment">코멘트저장</button>
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
	                        </td>
	                    </tr>
	                 </table>
                 </div>
            </div>
            <div id="tabs3" class="emdTabPage" style="overflow: scroll;">

                <table id="assetRentFileList" style="display:none"></table>
                <div class="emdControlPanel"><button id="btnUploadFile">업로드</button><button id="btnDownloadFile">다운로드</button><button id="btnRemoveFile">삭제</button></div>
                <form id="gamAssetRentFileForm">
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
                <div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>

            </div>
            <div id="tabs4" class="emdTabPage" style="overflow: scroll;">

                <table id="assetRentFileList" style="display:none"></table>
                <div class="emdControlPanel"><button id="btnUploadFile">업로드</button><button id="btnDownloadFile">다운로드</button><button id="btnRemoveFile">삭제</button></div>
                <form id="gamAssetRentFileForm">
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
                <div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>

            </div>


        </div>
    </div>
</div>