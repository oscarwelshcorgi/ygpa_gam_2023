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

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdModule Override 및 이벤트 처리 정의 부분 시작	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	페이지가 호출 되었을때 호출 되는 함수
--%>
GamHtldRentMngtModule.prototype.loadComplete = function() {
    // 임대목록
    this.$("#assetRentMngtList").flexigrid({
        module: this,
        url: '/oper/htld/selectHtldRentList.do',
        dataType: 'json',
        colModel : [
                    {display:'구역', name:'rentArea',width:82, sortable:false,align:'center'},
                    {display:'입주기업', name:'entrpsNm',width:190, sortable:false,align:'left'},
                    {display:'계약기간', name:'grUsagePdPeriod',width:175, sortable:false,align:'center'},
                    {display:'소재지', name:'gisAssetsLocplc',width:170, sortable:false,align:'left'},
                    {display:'입주면적(㎡)', name:'usageAr',width:88, sortable:false,align:'right', displayFormat: 'number',  displayOption:"0,000.00"},
                    {display:'적용단가(원)', name:'applcPrice',width:80, sortable:false,align:'right', displayFormat: 'number',  displayOption:"0,000.00"},
                    /* {display:'영업개시일', name:'operYrMt',width:80, sortable:false,align:'center'}, */
                    /* {display:'실적평가기간', name:'bizAssessPdPeriod',width:175, sortable:false,align:'center'}, */
                    {display:'납부방법', name:'nticMthNm',width:60, sortable:false,align:'left'},
                    {display:'요금종류', name:'chrgeKndNm',width:160, sortable:false,align:'left'},
                    {display:'과세구분', name:'taxtSeNm',width:120, sortable:false,align:'center'},
                    {display:'고지방법', name:'payMthNm',width:60, sortable:false,align:'center'},
                    /* {display:'업종', name:'compTp',width:110, sortable:false,align:'right'},
                    {display:'취급화종', name:'frghtTp',width:120, sortable:false,align:'center'}, */
                    {display:'해지/변경', name:'termnKndNm',width:60, sortable:false,align:'center'},
                    {display:'해지/변경일자', name:'termnDt',width:80, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        mergeRows: 'rentArea,entrpsNm,grUsagePdPeriod,gisAssetsLocplc,payMthNm,nticMthNm,taxtSeNm,chrgeKndNm,termnKndNm,termnDt',
        preProcess: function(module,data) {
            module.$('#totalResultCnt').val(data.totalCount);
            module.$('#totalArea').val(data.sumGrAr);
            module.$('#totalUse').val(data.sumGrFee);
            module.$('#totalGrRdcxptFee').val(data.sumGrRdcxptFee);
            $.each(data.resultList, function() {
            	this.grUsagePdPeriod = this.grUsagePdFrom+" ~ " +this.grUsagePdTo;
            	if((this.bizAssessPdFrom != void(0)) && (this.bizAssessPdTo != void(0)))
            		this.bizAssessPdPeriod = this.bizAssessPdFrom+" ~ " + this.bizAssessPdTo;
            	switch(this.termnKnd) {
            		case '1' : this.termnKndNm = '해지'; break;
            		case '2' : this.termnKndNm = '변경'; break;
            	}
            });
            return data;
        }
    });

	var priceSeOption = [
	                       	{value:'1', name:'적용임대료'},
	                       	{value:'2', name:'월사용료'}
	                      ];
    
    // 임대상세목록
    this.$("#assetRentDetailList").flexigrid({
        module: this,
        dataType: 'json',
        colModel : [
           {display:'자산코드', name:'gisAssetsCode',width:80, sortable:false,align:'center'},
           {display:'자산명', name:'gisAssetsNm',width:180, sortable:false,align:'left'},
           {display:'소재지', name:'gisAssetsLocplcAll',width:220, sortable:false,align:'center'},
           {display:'자산면적', name:'gisAssetsAr',width:80, sortable:false,align:'right', displayFormat: 'number', displayOption:"0,000.00"},
           {display:'적용단가', name:'applcPrice',width:100, sortable:false,align:'right', displayFormat: 'input-number', displayOption:"0,000.00"},
           {display:'단가구분', name:'priceSe',width:100, sortable:false,align:'center', displayFormat:'select', displayOption:priceSeOption},
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
	
    // 실적평가목록
    this.$("#bizAssessGrid").flexigrid({
        module: this,
        url: '/oper/htld/selectHtldBizAssessList.do',
        dataType: 'json',
        colModel : [
                    {display:'평가회차', name:'assessNo', width:80, sortable:true, align:'center', displayFormat:'input'},
                    {display:'평가기간시작일', name:'dtFrom', width:130, sortable:true, align:'left', displayFormat:'cal'},
                    {display:'평가기간종료일', name:'dtTo', width:130, sortable:true, align:'left', displayFormat:'cal'},
   					{display:'적용단가(원)', name:'usagePrice',width:80, sortable:false,align:'right', displayFormat: 'input-number', displayOption: "0.0"},
    				{display:'변경단가(원)', name:'changePrice',width:80, sortable:false,align:'right', displayFormat: 'input-number', displayOption: "0.0"},
    				{display:'인상단가(원)', name:'increasePrice',width:80, sortable:false,align:'right', displayFormat: 'input-number', displayOption: "0.0"},
    				{display:'사용면적(㎡)', name:'usageAr',width:80, sortable:false,align:'right', displayFormat: 'input-number', displayOption: "0.00"},
                    {display:'평가금액(원)', name:'assessAmt', width:130, sortable:true, align:'right', displayFormat: 'input-number'}
                    ],
        showTableToggleBtn: false,
        height: '380',
        preProcess: function(module,data) {
            module._bizAssessNo=module._bizAssessNo||0;
            $.each(data.resultList, function() {
            	if(module._bizAssessNo<this.assessNo) module._bizAssessNo=this.assessNo;
            });
            module._deleteBizAssessList=[];
            module.$('#grBizNticPdFrom').val(data.assessPd.nticPdFrom);
            module.$('#grBizNticPdTo').val(data.assessPd.nticPdTo);
            return data;
        }
    });
    
    // 면적평가목록
    this.$("#areaAssessGrid").flexigrid({
        module: this,
        url: '/oper/htld/selectHtldAreaAssessList.do',
        dataType: 'json',
        colModel : [
                    {display:'평가회차', name:'assessNo', width:80, sortable:true, align:'center'},
                    {display:'평가기간From', name:'dtFrom', width:130, sortable:true, align:'left'},
                    {display:'평가기간To', name:'dtTo', width:130, sortable:true, align:'left'},
   					{display:'사용면적(㎡)', name:'usageAr',width:80, sortable:false,align:'right'},
    				{display:'변경면적(㎡)', name:'changeAr',width:80, sortable:false,align:'right'},
    				{display:'변동면적(㎡)', name:'increaseAr',width:80, sortable:false,align:'right'},
    				{display:'적용단가(원)', name:'usagePrice',width:80, sortable:false,align:'right', displayFormat: 'number', displayOption: "0.0"},
                    {display:'평가금액(원)', name:'assessAmt', width:130, sortable:true, align:'right', displayFormat: 'number'}
                    ],
        showTableToggleBtn: false,
        height: '340',
        preProcess: function(module,data) {
           	module.appendAreaAssess();
           	return data;
        }
    });
	    
    // 고지내역목록
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
            	case '1' : this.rcvdTpNm="연체고지"; break;
            	case '2' :
            	case '3' : this.rcvdTpNm="수납"; break;
            	case '0' :
        		default : this.rcvdTpNm="미수납"; break;
            	}
            	this.rentPeriod = this.nticPdFrom + " ~ " + this.nticPdTo;
            });
            return data;
        }
    });

    // 초기값 정의
    this.$('#sGrUsagePdFrom').val(EMD.util.getDate());	    
    this._editMode='';
    this._detailMode="";
    this._loadedItem=false;
	this._editable=false;

    this.setEvents();
    this.setButtonStatus();
	this.showTermnKndTr();
	
    this.loadData();	// 데이터 조회    
};

<%--
	EmdModule에서 Overriding 된 Submit 함수.
	모듈에서 엔터키를 입력 하거나 submitButton 클래스의 버튼이 눌려졌을때 호출되는 이벤트 함수. (포커스에 따라 동작 안될 때도 있음.)
--%>
GamHtldRentMngtModule.prototype.onSubmit = function() {
	this.loadData();
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
        case 'addAssetRentFirst':	// 계약등록
        	this.addRentData();
            break;
        case 'addAssetRentRenew': // 계약연장
        	this.extendRentData();
            break;
        case 'btnChangeItem' : //계약변경
        	this.changeRentData();
        	break;
        case 'btnTerminateItem': //계약해지
        	this.terminateRentData();
        	break;
        case 'btnEApproval':    // 결재요청
        case 'btnEApproval2':
        	this.approvalEA();
            break;
        case 'btnRentFeeMngt':	 // 임대료 고지관리
        case 'btnRentFeeMngt2':
        case 'btnRentFeeMngt3':
        case 'btnRentFeeMngt4':
        	this.loadRentFeeMngt();
			break;
        case 'btnInsertItemDetail':	//임대상세추가
        	this.addRentDetailItem();
            break;
        case 'btnRemoveItemDetail':	// 임대상세삭제
        	this.deleteRentDetailItem();
            break;
        case 'btnSaveItem':	// 신청저장
			this.storeRentData();
            break;
        case 'btnChangeSaveItem': //변경저장
        	this.changeSaveRentData();
        	break;
        case 'btnRemoveItem':	//신청삭제
        case 'btnRemoveItem2':
        	this.deleteRentData();
            break;
        case 'popupEntrpsInfoInput': // 팝업을 호출한다.(자산임대신청)
            this.doExecuteDialog('insertEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', {});
            break;
        case 'btnSaveComment':	//코멘트저장
        	this.saveComment();
            break;
        case 'btnAppendBizAssess': //실적평가 추가
        	this.appendBizAssess();
        	break;
        case 'btnSaveBizAssess': //실적평가 저장
        	this.saveBizAssess();
        	break;
        case 'btnRemoveBizAssess': //실적평가 삭제
        	this.removeBizAssess();
        	break;
        case 'btnAppendAreaAssess': //면적평가 추가
        	this.appendAreaAssess();
        	break;
        case 'btnSaveAreaAssess': //면적평가 저장
        	this.saveAreaAssess();
        	break;
        case 'btnRemoveAreaAssess': //면적평가 삭제
        	this.removeAreaAssess();
        	break;
        case 'btnExcelDownload': //엑셀다운로드
        	this.tableToExcel();
        	break;
    }
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
		}
		break;
	case 'insertEntrpsInfoPopup':
		if (msg != 'cancel') {
			this.$('#entrpscd').val(value.entrpscd);
			this.$('#entrpsNm').val(value.entrpsNm);
			this.loadEntrpsChargerList();	// 담당자 목록을 불러온다.
		}
		break;
	case 'insertAssetRentPrmisnPopup':
		if (msg != 'cancel') {
			if( value == "0" ) {
				this.loadData();
			}
		} 
		break;
	case 'selectAssetsCdRentPopup':
		if (msg != 'cancel') {
			value._updtId="I";
			value.priceSe = '1';
			this.$('#assetRentDetailList').flexAddRow(value);
			this.onCalc();
		}
		break;
	default:
		alert('알수없는 팝업 이벤트가 호출 되었습니다.');
		break;
	}
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
   		break;
    case 'tabs3':
    case 'tabs4':
    case 'tabs5':
    	if(this._selectedItem!=null) return;
   		alert('임대 목록에서 임대 내역을 선택 해 주시기 바랍니다.');
   		return false;
        break;
    default:
        break;
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
    	this.loadBizAssessList();
        break;
    case 'tabs4':
    	this.loadAreaAssessList();
        break;
    case 'tabs5':
    	this.loadNticList();
        break;
    }
};

<%--
	이벤트 정의
--%>
GamHtldRentMngtModule.prototype.setEvents = function() {
    // 임대목록에 데이터 로드가 완료될 때
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
    
    // 임대목록에 데이터가 선택될 때
    this.$("#assetRentMngtList").on('onItemSelected', function(event, module, row, grid, param) {
    	module._loadedItem=false;
    	module._editChanged=false;
    	module._editable=false;
    	module._selectedItem=row;
    	module._rentDetail=row;
    	module._detailMode="";
    	module.setButtonStatus();
    	module.showTermnKndTr();
    });

    // 임대목록에 데이터가 더블클릭될 때
    this.$("#assetRentMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
    	module._loadedItem=false;
    	module._editChanged=false;
    	module._editable=false;
    	module._selectedItem=row;
    	module._detailMode="";
        module.$("#assetRentListTab").tabs("option", {active: 1});
    });
	
    // 임대상세목록에 셀 편집이 완료될 때
    this.$("#assetRentDetailList").on('onCellEdited', function(event, module, row, rid, cid) {
        if(row._updtId!="I") row._updtId="U";
    	module.onCalcGrAr();
    });
    
    // 실적평가목록 로드가 완료될 때
    this.$("#bizAssessGrid").on('onLoadDataComplete', function(event, module, data, grid, param) {
        module.onCalcBizAssessAmtTot();
    });

    // 실적평가목록에 셀 편집이 완료될 때
    this.$("#bizAssessGrid").on('onCellEdited', function(event, module, row, rid, cid) {
    	if(row._updtId!="I") row._updtId="U";
        module.onCalcBizAssessListCellEdited(row, rid, cid);
    });
        
    // 신청업체 코드가 변경될 때
    this.$("#sEntrpscd").bind("keyup change", {module: this}, function(event) {
		if(event.data.module.$('#sEntrpscd').val() ==''){
			event.data.module.$('#sEntrpsNm').val('');
		}
    });
    
    // 임대내역 입력 폼에 데이터가 변경되었을 때
    this.$('#gamAssetRentForm :input').on('change', {module:this}, function(event) {
    	var module=event.data.module;
    	if(module._editable) module._editChanged=true;
    	module.setButtonStatus();
    });

    // 임대내역 입력 폼에 데이터가 변경되었을 때
    this.$('#areaAssessInputForm :input').on('change', {module:this}, function(event) {
    	var module=event.data.module;
    	module.onCalcAreaAssessAmt();
    });

    // 면적평가의 적용면적이 변경될 때
    this.$("#usageAr").bind("keyup", {module: this}, function(event) {
    	var module=event.data.module;
    	module.onCalcAreaAssessAmt();
    });

    // 면적평가의 변경면적이 변경될 때
    this.$("#changeAr").bind("keyup", {module: this}, function(event) {
    	var module=event.data.module;
    	module.onCalcAreaAssessAmt();
    });

    // 면적평가의 변동면적이 변경될 때
    this.$("#increaseAr").bind("keyup", {module: this}, function(event) {
    	var module=event.data.module;
    	module.onCalcAreaAssessAmt();
    });

    // 면적평가의 적용단가이 변경될 때
    this.$("#usagePrice").bind("keyup", {module: this}, function(event) {
    	var module=event.data.module;
    	module.onCalcAreaAssessAmt();
    });

    // 면적평가목록에서 데이터가 선택될 때
    this.$("#areaAssessGrid").on('onItemSelected', function(event, module, row, grid, param) {
    	module.makeFormValues('#areaAssessInputForm', row);
    	module.$('nticCnt').val(row.nticCnt);;
    	module.$('chrgeKnd').val(row.chrgeKnd);
    	module._areaAssessMode = 'U';
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

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdModule Override 및 이벤트 처리 정의 부분 종료	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	tabs1 : 배후단지임대목록 관련 사용자 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	임대계약목록 조회
--%>
GamHtldRentMngtModule.prototype.loadData = function() {
    this.$("#assetRentListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamAssetRentMngtSearchForm');
    this.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();

    this._deleteDataList=[];
};

<%--
	임대계약 등록
--%>
GamHtldRentMngtModule.prototype.addRentData = function() {
	this.$("#assetRentMngtList").noSelect();
	this._detailMode="I";
	this.$("#assetRentListTab").tabs("option", {active: 1});  // 탭을 전환 한다.
	
	// 기본값 정의
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
	if(rows.length < 1) {
		alert("목록에서 연장신청할 업체를 선택하십시오.");
		return;
	}
	if (confirm("연장신청을 하시겠습니까?")) {
		this.doAction('/oper/htld/insertHtldRentExtend.do', rows[0], function(module, result) {
			if (result.resultCode == '0') {
				module._loadedItem = false;
				module._selectedItem = result.rentVo;
				module._editMode = '';
				this._detailMode == "X";
				module.$("#assetRentListTab").tabs("option", { active : 1 });
			} else {
				alert(result.resultMsg);
			}
		});
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
	임대계약 삭제
--%>
GamHtldRentMngtModule.prototype.deleteRentData = function() {
	var rows = this.$('#assetRentMngtList').selectedRows();
	if(rows.length == 0) {
    	alert("자산임대목록에서 신청삭제할 행을 선택하십시오.");
    	return;
    }
    if( confirm("신청삭제를 하시겠습니까?") ) {
        this.doAction('/oper/htld/deleteHtldRent.do', rows[0], function(module, result) {
        	if(result.resultCode=='0') {
            	module.loadData();
           	}
            alert(result.resultMsg);
		});
        this.$("#assetRentListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
    }   
};

<%--
	전자결재 연동
--%>
GamHtldRentMngtModule.prototype.approvalEA = function() {
	if(this.$('#assetRentMngtList').selectedRowCount()<1) {
		alert("목록에서 결제할 건을 선택하십시오.");
		return;
	}	

	var row = this.$('#assetRentMngtList').selectedRows()[0];
    if( row['sanctnSttus'] == '1' || row['sanctnSttus'] == '2' || row['sanctnSttus'] == '5' ) {
    	alert("결재요청을 할수없는 상태 입니다.");
    	return;
	}

    if( confirm("결재요청을 하시겠습니까?") ) {
        var opts = {
                type: 'ARU',
                prtAtCode: row['prtAtCode'],
                mngYear: row['mngYear'],
                mngNo: row['mngNo'],
                mngCnt: row['mngCnt']
		};
        this.requestEApproval(opts, function(module, msg) {
			alert(msg);
            var searchOpt=module.makeFormArgs('#gamAssetRentForm');
            module.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
    	});
    }
};

<%--
	임대료 고지관리 로드
--%>
GamHtldRentMngtModule.prototype.loadRentFeeMngt = function() {
    var rows = this.$('#assetRentMngtList').selectedRows();
    var opts = {};

    if(rows.length>0) {
    	opts = {
    			action: 'selectRentFee',
    			nticVo:{ prtAtCode: rows[0].prtAtCode, mngYear: rows[0].mngYear, mngNo: rows[0].mngNo, mngCnt: rows[0].mngCnt }
    	};
	}
	EMD.util.create_window('gamHtldRentFeeMngt', '배후단지임대료관리', '/oper/htld/gamHtldRentFeeMngt.do', null, opts);
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	tabs1 : 배후단지임대목록 관련 사용자 함수 정의 종료
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	tabs2 : 배후단지임대내역 관련 사용자 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	임대내역 로드
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
	자산코드 및 주소 문자열 변환
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
	변경사유 설정
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
	코멘트 저장
--%>
GamHtldRentMngtModule.prototype.saveComment = function() {
    var inputVO=this.makeFormArgs('#gamAssetRentForm');
    this.doAction('/oper/htld/updateHtldRentComment.do', inputVO, function(module, result) {
        if(result.resultCode=='0') {
        	module._editChanged=false;
		}
        alert(result.resultMsg);
    });		
};

<%--
	업체 담당자 정보를 로딩한다.
--%>
GamHtldRentMngtModule.prototype.loadEntrpsChargerList = function() {
	var entrpsCd=this.$('#entrpscd').val();
	this.$('#chargerTlphonNo').text('');
	this.$('#chargerMoblphonNo').text('');
	if(entrpsCd!=null && entrpsCd.length>0) {
		var loadOpt = {'entrpscd': entrpsCd};
	    this.doAction('/asset/rent/selectEntrpsChargerList.do', loadOpt, function(module, result) {
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
	임대 상세 추가
--%>
GamHtldRentMngtModule.prototype.addRentDetailItem = function() {
	/* 
	var list = this.$('#assetRentDetailList').flexGetData();
	if(list.length >= 1) {
		alert('임대상세자료가 존재하여 추가할 수 없습니다.');
		return;
	}
	 */
	this.doExecuteDialog('selectAssetsCdRentPopup', '시설 선택', '/popup/showAssetsCd.do', {}, {"gisAssetsPrprtySeCd":"L"});
};

<%--
	임대 상세 삭제
--%>
GamHtldRentMngtModule.prototype.deleteRentDetailItem = function() {
	var rowId=this.$('#assetRentDetailList').selectedRowIds();
	if(rowId.length < 1) {
		alert("자산임대상세목록에서 삭제할 행을 선택하십시오.");
		return;
	}
	for(var i=rowId.length-1; i>=0; i--) {
	    var row=this.$('#assetRentDetailList').flexGetRow(rowId[i]);
	    if(row._updtId==undefined || row._updtId!='I') {
	    	this._deleteDataList[this._deleteDataList.length]=row;
	    }
	    this.$('#assetRentDetailList').flexRemoveRow(rowId[i]);
	}
	this.onCalcGrAr();
};

<%--
	임대 면적 계산
--%>
GamHtldRentMngtModule.prototype.onCalcGrAr = function() {
	var usageAr=0;
	this.$('#assetRentDetailList')[0].dgrid.forEachRow(function(id) {
    	usageAr+=Number(this.cells(id,6).getValue());
    });
	this.$('#grAr').val(usageAr);
};

<%--
	임대 내역 밸리데이션
--%>
GamHtldRentMngtModule.prototype.validateRentDetail = function() {
	if(!validateGamAssetRentDetail(this.$('#gamAssetRentDetailForm')[0])) return false;

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
	} else {
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
		case '2': break; // 공사규정
		case '3': break; // 입찰
		case '4':	// 무역항규정
		case '6':	// 배후단지
			if( this.$('#applcPrice').val() == '' ) {
	            alert("적용단가를 입력하십시오.");
	            return false;
			}
			break;
		case '5': break; // 임대계약서
		case '9': break; // 기타
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
			
    		/* selectOption에 event가 안 먹기 때문에 이 부분을 처리할 수 없음. 2016-2-27 김종민
    		assetRent['_cList'] = JSON.stringify(this.$('#assetRentDetailList').selectFilterData([{col: '_updtId', filter: 'I'}]));
    		assetRent['_uList'] = JSON.stringify(this.$('#assetRentDetailList').selectFilterData([{col: '_updtId', filter: 'U'}]));
    		assetRent['_dList'] = JSON.stringify(this._deleteDataList);
    		*/
    		assetRent['assetRentDetailList'] =  JSON.stringify(this.$('#assetRentDetailList').flexGetData());
    		
            this.doAction('/oper/htld/updateHtldRent.do', assetRent, function(module, result) {
                if(result.resultCode == 0){
                	module.loadDetail();
                	module._editChanged=false;
                	module.setButtonStatus();
                	module.showTermnKndTr();
                }
                alert(result.resultMsg);
            });
		} else {	// insert data
    		var assetRent = {};
    		assetRent['assetRentVo'] = JSON.stringify(this.getFormValues('#gamAssetRentForm'));
    		assetRent['_cList'] = JSON.stringify(this.$('#assetRentDetailList').flexGetData());

            this.doAction('/oper/htld/insertHtldRent.do', assetRent, function(module, result) {
                if(result.resultCode == 0) {
                	module._loadedItem=false;
                	module._editChanged=false;
                	module._detailMode="";
                	module.loadData();
                }
                alert(result.resultMsg);
           	});
       	}
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

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	tabs2 : 배후단지임대내역 관련 사용자 함수 정의 종료
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	tabs3 : 실적평가 관련 사용자 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	실적평가 목록 조회
--%>
GamHtldRentMngtModule.prototype.loadBizAssessList = function() {
	if(this._rentDetail==null) {
		alert('선택한 계약이 없습니다.');
		return;
	}
	this.$('#grBizNticPdFrom').val('');
    this.$('#grBizNticPdTo').val('');
    this.$('#sumBizAssessAmt').val(0);
	var opt = {
	    	prtAtCode: this._rentDetail.prtAtCode,
	    	mngYear: this._rentDetail.mngYear,
	    	mngNo: this._rentDetail.mngNo,
	    	mngCnt: this._rentDetail.mngCnt,
	    	assessSe : '1'
	};
	var params = { params : EMD.util.objectToArray(opt) };
    this.$('#bizAssessGrid').flexOptions(params).flexReload();
};

<%--
	실적평가 등록 추가
--%>
GamHtldRentMngtModule.prototype.appendBizAssess = function() {
	this._bizAssessNo=this._bizAssessNo || 0;
	this._bizAssessNo++;

	this.$('#bizAssessGrid').flexAddRow({
		_updtId:'I',
    	prtAtCode: this._rentDetail.prtAtCode,
    	mngYear: this._rentDetail.mngYear,
    	mngNo: this._rentDetail.mngNo,
    	mngCnt: this._rentDetail.mngCnt,
    	chrgeKnd: this._rentDetail.chrgeKnd, 
		assessSe:'1',
		assessNo:this._bizAssessNo,
		dtFrom:'',
		dtTo:'',
		usagePrice:0,
		changePrice:0,
		increasePrice:0,
		usageAr:0,
		assessAmt:0
	});
};

<%--
	실적평가 등록 삭제
--%>
GamHtldRentMngtModule.prototype.removeBizAssess = function() {
    var rowId=this.$('#bizAssessGrid').selectedRowIds();
    if(rowId.length == 0) {
        alert("삭제할 항목을 선택하십시오.");
        return;
    }
	for(var i=rowId.length-1; i>=0; i--) {
	    var row=this.$('#bizAssessGrid').flexGetRow(rowId[i]);
	    if(row._updtId==undefined || row._updtId!='I') {
	    	this._deleteBizAssessList[this._deleteBizAssessList.length]=row;
	    }
	    this.$('#bizAssessGrid').flexRemoveRow(rowId[i]);
	}
	this.onCalcBizAssessAmtTot();
};

<%--
	실적평가 등록 저장
--%>
GamHtldRentMngtModule.prototype.saveBizAssess = function() {
	var assessList={};
		
	if(!EMD.util.isDate(this.$('#grBizNticPdFrom').val())) {
		alert('고지대상기간을 입력하지 않았거나 날짜형식에 맞지 않습니다');
		return;
	}
	if(!EMD.util.isDate(this.$('#grBizNticPdTo').val())) {
		alert('고지대상기간을 입력하지 않았거나 날짜형식에 맞지 않습니다');
		return;
	}
	
	var nticData = { nticPdFrom : this.$('#grBizNticPdFrom').val(), nticPdTo : this.$('#grBizNticPdTo').val(), fee : this.$('#sumBizAssessAmt').val() };
	
	assessList['_cList'] = JSON.stringify(this.$('#bizAssessGrid').selectFilterData([{col: '_updtId', filter: 'I'}]));
	assessList['_uList'] = JSON.stringify(this.$('#bizAssessGrid').selectFilterData([{col: '_updtId', filter: 'U'}]));
	assessList['_dList'] = JSON.stringify(this._deleteBizAssessList);
	assessList['_rentData'] = JSON.stringify(this._rentDetail);
	assessList['_nticData'] = JSON.stringify(nticData);

	this.doAction('/oper/htld/updateBizHtldAssessList.do', assessList, function(module, result) {
        module.loadBizAssessList();
        module._editChanged=false;
        alert(result.resultMsg);
    });
};

<%--
	실적평가 셀 편집 처리
--%>
GamHtldRentMngtModule.prototype.onCalcBizAssessListCellEdited = function(row, rid, cid) {
	if(row._updtId!="I") row._updtId="U";
	switch(cid) {
	case 'usagePrice':
		if(row.changePrice != void(0)) {
			if(row.usagePrice != void(0)) {
				row.increasePrice = this.getNumber(row.changePrice) - this.getNumber(row.usagePrice);
				if(row.increasePrice < 0) row.increasePrice = 0;
			} else {
				row.increasePrice = 0;
			}
		} else {
			row.increasePrice = 0;
		}
		break;
	case 'changePrice':
		if(row.changePrice != void(0)) {
			if(row.usagePrice != void(0)) {
				row.increasePrice = this.getNumber(row.changePrice) - this.getNumber(row.usagePrice);
				if(row.increasePrice < 0) row.increasePrice = 0;
			} else {
				row.increasePrice = 0;
			}
		} else {
			row.increasePrice = 0;
		}
		break;	
	}
	this.onCalcBizAssessAmt(row);
	this.$("#bizAssessGrid").flexUpdateRow(rid, row);
	this.onCalcBizAssessAmtTot();
};

<%--
	실적평가 평가금액 계산
--%>
GamHtldRentMngtModule.prototype.onCalcBizAssessAmt = function(row) {
	var dtFrom = row.dtFrom, dtTo = row.dtTo, increasePrice = row.increasePrice, usageAr = row.usageAr;
	var applyMonths = 0, assessAmt = 0; 
	row.assessAmt = assessAmt;
	if((dtFrom==void(0)) || (dtTo==void(0)) || (increasePrice==0) || (usageAr==void(0)))  return;

	dtFrom = EMD.util.strToDate(dtFrom);
	dtTo = EMD.util.strToDate(dtTo);
	
	if(dtFrom.getTime() > dtTo.getTime()) {
		alert('평가기간시작일이 평가기간종료일보다 큽니다.');
		return;
	}
	
	applyMonths = this.getPdMonths(dtFrom, dtTo);

	assessAmt = applyMonths * increasePrice * usageAr;
	row.assessAmt = Math.floor(assessAmt * 0.1) * 10;
};

<%--
	실적평가합계금액 계산
--%>
GamHtldRentMngtModule.prototype.onCalcBizAssessAmtTot = function() {
	var result = 0;
	var rows = this.$('#bizAssessGrid').flexGetData();
	for(var i=0; i<rows.length; i++) {
		result += this.getNumber(rows[i].assessAmt);
	}
	result = (result > 0) ? Math.floor(result*0.1) * 10 : 0; //1원단위는 절사한다.
	this.$('#sumBizAssessAmt').val(result);
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	tabs3 : 실적평가 관련 사용자 함수 정의 종료
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	tabs4 : 면적평가 관련 사용자 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	면적평가 목록 조회
--%>
GamHtldRentMngtModule.prototype.loadAreaAssessList = function() {
	if(this._rentDetail==null) {
		alert('선택한 계약이 없습니다.');
		return;
	}
	var opt = {
	    	prtAtCode: this._rentDetail.prtAtCode,
	    	mngYear: this._rentDetail.mngYear,
	    	mngNo: this._rentDetail.mngNo,
	    	mngCnt: this._rentDetail.mngCnt,
	    	assessSe : '2'
	};
	var params = { params : EMD.util.objectToArray(opt) };
	this.$('#areaAssessGrid').flexOptions(params).flexReload();
};

<%--
	면적평가 추가
--%>
GamHtldRentMngtModule.prototype.appendAreaAssess = function() {
	this._areaAssessMode = 'I';
	this.makeFormValues('#areaAssessInputForm', {});
};

<%--
	면적평가 삭제
--%>
GamHtldRentMngtModule.prototype.removeAreaAssess = function() {
    var rows=this.$('#areaAssessGrid').selectedRows();
    if(rows.length == 0) {
        alert("삭제할 항목을 선택하십시오.");
        return;
    }
    var row = rows[[0]];
	var vo = EMD.util.objectToArray(row);
	this.doAction('/oper/htld/deleteAreaHtldAssessList.do', vo, function(module, result) {
		if(result.resultCode == '0'){
			module.loadAreaAssessList();
		}
        alert(result.resultMsg);
    });    
};

<%--
	면적평가 저장
--%>
GamHtldRentMngtModule.prototype.saveAreaAssess = function() {
	if(!EMD.util.isDate(this.$('#dtFrom').val())) {
		alert('평가기간을 입력하지 않았거나 날짜형식에 맞지 않습니다');
		return;
	}
	if(!EMD.util.isDate(this.$('#dtTo').val())) {
		alert('평가기간을 입력하지 않았거나 날짜형식에 맞지 않습니다');
		return;
	}

	if(this.$('#assessAmt').val() == '') {
		alert('평가금액을 입력하지 않았습니다.');
		return;
	}
	
	if(Number(this.$('#assessAmt').val()) <= 0) {
		alert('0이하의 평가금액은 저장이 되지 않습니다.');
		return;
	}

	var vo = {};

	vo['prtAtCode'] = this._rentDetail.prtAtCode;
	vo['mngYear'] = this._rentDetail.mngYear;
	vo['mngNo'] = this._rentDetail.mngNo;
	vo['mngCnt'] = this._rentDetail.mngCnt;
	vo['chrgeKnd'] = this._rentDetail.chrgeKnd;
	vo['entrpscd'] = this._rentDetail.entrpscd;
	vo['nticPdFrom'] = this.$('#dtFrom').val();
	vo['nticPdTo'] = this.$('#dtTo').val();
	vo['fee'] = this.$('#assessAmt').val();
	vo['assessSe'] = '2';
	vo['dtFrom'] = this.$('#dtFrom').val();
	vo['dtTo'] = this.$('#dtTo').val();
	vo['usageAr'] = this.$('#usageAr').val();
	vo['changeAr'] = this.$('#changeAr').val();
	vo['increaseAr'] = this.$('#increaseAr').val();
	vo['usagePrice'] = this.$('#usagePrice').val();
	vo['assessAmt'] = this.$('#assessAmt').val();
	
	if(this._areaAssessMode == 'I') {		
		vo = EMD.util.objectToArray(vo);
		this.doAction('/oper/htld/insertAreaHtldAssessList.do', vo, function(module, result) {
			if(result.resultCode == '0'){
				module.loadAreaAssessList();
			}
	        alert(result.resultMsg);
	    });
	} else {
		vo['nticCnt'] = this.$('#nticCnt').val();
		vo['assessNo'] = this.$('#assessNo').val();
		vo = EMD.util.objectToArray(vo);
		this.doAction('/oper/htld/updateAreaHtldAssessList.do', vo, function(module, result) {
			if(result.resultCode == '0'){
	        	module.loadAreaAssessList();
			}
	        alert(result.resultMsg);
	    });
	}	
};

<%--
	면적평가 평가금액 계산
--%>
GamHtldRentMngtModule.prototype.onCalcAreaAssessAmt = function() {
	var usageAr = this.$('#usageAr').val(), changeAr = this.$('#changeAr').val(), increaseAr = 0;
	if((usageAr==void(0)) || (changeAr==void(0))) return;
	if((usageAr=='') || (changeAr=='')) return;
	usageAr = Number(this.getNumber(usageAr));
	changeAr = Number(this.getNumber(changeAr));
	increaseAr = changeAr - usageAr;
	this.$('#increaseAr').val(increaseAr);

	
	var dtFrom = this.$('#dtFrom').val(), dtTo = this.$('#dtTo').val(), usagePrice = this.$('#usagePrice').val();
	var applyMonths = 0, assessAmt = 0; 
	if((dtFrom==void(0)) || (dtTo==void(0)) || (usagePrice==void(0)) || (increaseAr==void(0)))  return;
	
	if((increaseAr=='') || (usagePrice==''))  return;
	
	if(!EMD.util.isDate(dtFrom)) return;
	if(!EMD.util.isDate(dtTo)) return;
	
	dtFrom = EMD.util.strToDate(dtFrom);
	dtTo = EMD.util.strToDate(dtTo);
	
	usagePrice = Number(this.getNumber(usagePrice));
	increaseAr = Number(this.getNumber(increaseAr));
	
	if(dtFrom.getTime() > dtTo.getTime()) {
		alert('평가기간시작일이 평가기간종료일보다 큽니다.');
		return;
	}
	
	applyMonths = this.getPdMonths(dtFrom, dtTo);
	
	assessAmt = applyMonths * increaseAr * usagePrice;
	assessAmt = (assessAmt > 0) ? Math.floor(assessAmt*0.1) * 10 : 0; //1원단위는 절사한다.
	this.$('#assessAmt').val(assessAmt);
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	tabs4 : 면적평가 관련 사용자 함수 정의 종료
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	tabs5 : 고지내역 관련 사용자 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	고지내역 조회
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

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	tabs5 : 고지내역 관련 사용자 함수 정의 종료
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	지역적 모듈 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	Date객체 변형 객체 정의.
--%>
GamHtldRentMngtModule.prototype.createExtendedDate = function (argDate) {
	var dateObj = argDate;
	var daysOfMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
	var year = dateObj.getFullYear(), month = dateObj.getMonth(), day = dateObj.getDate(), time = dateObj.getTime();
	daysOfMonth[1] = (((year%4==0) && (year%100!=0)) || (year%400==0)) ? 29 : 28;
	return {
		getDateObject : function() { return dateObj; }
		, getYear : function() { return year; }
		, getMonth : function() { return (month + 1); }
		, getDay : function() { return day; }
		, getTime : function() { return time; }
		, isLeapYear : function() { return daysOfMonth[1] == 29; }
		, getLastDayOfMonth : function() { return daysOfMonth[month]; }
		, getQuarter : function() { return Math.floor((month/3)+1); }
		, getRemainDaysOfMonth : function() { return daysOfMonth[month] - day + 1; }
		, isLastDayOfMonth : function() { return day == daysOfMonth[month]; }
		, equals : function(argDate) { return argDate.getTime() == time; }
		, equalsYear : function(argDate) { return argDate.getYear() == year; }
		, equalsMonth : function(argDate) { return argDate.getMonth() == this.getMonth(); }
		, equalsYearMonth : function(argDate) { return (argDate.getYear() == year) && (argDate.getMonth() == this.getMonth()); }
		, equalsQuarter : function(argDate) { return argDate.getQuarter() == this.getQuarter(); }
		, isQuarterStartDate : function() { return ((this.getMonth() == 1) || (this.getMonth() == 4) || (this.getMonth() == 7) || (this.getMonth() == 10)) && (day == 1); }
		, isQuarterEndDate : function() { return ((this.getMonth() == 3) || (this.getMonth() == 6) || (this.getMonth() == 9) || (this.getMonth() == 12)) && this.isLastDayOfMonth(); }
		, getQuarterStartDate : function() {
			var y=this.getYear(); var m=0; var d = 1;
			switch(this.getQuarter()) {
			case 1 : m = 1; break;
			case 2 : m = 4; break;
			case 3 : m = 7; break;
			case 4 : m = 10; break;
			}
			return new Date(y, m-1, d);
		}
	};	
};

<%--
	기간의 월수 구하기
--%>
GamHtldRentMngtModule.prototype.getPdMonths = function(dtFrom, dtTo) {
	var result = 0;
	var onCalcSameYearMonths = function(dtFrom, dtTo) { //같은년도의 기간월수 구하기
		var result = 0;
		if(dtFrom.equalsMonth(dtTo)) {
			result = ((dtFrom.getDay() == 1) && (dtTo.isLastDayOfMonth())) ? 1 : (dtTo.getDay() - dtFrom.getDay() + 1) / dtTo.getLastDayOfMonth();
		} else {
			result = dtTo.getMonth() - dtFrom.getMonth() - 1;
			result += (dtFrom.getDay() == 1) ? 1 : ((dtFrom.getLastDayOfMonth() - dtFrom.getDay() + 1) / dtFrom.getLastDayOfMonth());
			result += (dtTo.isLastDayOfMonth()) ? 1 : (dtTo.getDay() / dtTo.getLastDayOfMonth());
		}
		return result;
	};

	dtFrom = this.createExtendedDate(dtFrom);
	dtTo = this.createExtendedDate(dtTo);

	if(dtFrom.equalsYear(dtTo)) {
		result = onCalcSameYearMonths(dtFrom, dtTo);
	} else {
		result = (dtTo.getYear() - dtFrom.getYear() - 1) * 12;
		if((dtFrom.getMonth() == 1) && (dtFrom.getDay() == 1)) {
			result += 12;
		} else {
			var dtTempTo = this.createExtendedDate(EMD.util.strToDate(dtFrom.getYear() + '-12-31'));
			result += onCalcSameYearMonths(dtFrom, dtTempTo);			
		}
		if((dtTo.getMonth() == 12) && (dtTo.getDay() == 31)) {
			result += 12;
		} else {
			var dtTempFrom = this.createExtendedDate(EMD.util.strToDate(dtTo.getYear() + '-01-01'));
			result += onCalcSameYearMonths(dtTempFrom, dtTo);
		}
	}
	
	return result;
}; 

<%--
	문자열를 숫자로 변환. (EMD.util.getNumber 추가 예정)
--%>
GamHtldRentMngtModule.prototype.getNumber = function(value) {
	var rnum=value!=undefined?value:0;
	if(typeof rnum=="string") rnum=Number(rnum.replace(/,/g,""));
	return rnum;
};

GamHtldRentMngtModule.prototype.tableToExcel = function() {
	var clone =	this.$('#assetRentMngtList').clone();
	$(clone).find('th,td').each(function() {
		if($(this).css('display')=='none') {
			$(this).remove();
		}
		else {
			$(this).css('border-left', '1px solid black');
			$(this).css('border-top', '1px solid black');
			$(this).css('border-right', '1px solid black');
			$(this).css('border-bottom', '1px solid black');
		}
	});
	clone.find("td:eq(0)").css("width","100");
	clone.find("td:eq(1)").css("width","220");
	clone.find("td:eq(2)").css("width","100");
	clone.find("td:eq(3)").css("width","230");
	clone.find("td:eq(4)").css("width","100");
	clone.find("td:eq(5)").css("width","100");
	clone.find("td:eq(6)").css("width","80");
	clone.find("td:eq(7)").css("width","80");
	clone.find("td:eq(8)").css("width","100");
	clone.find("td:eq(9)").css("width","200");
	clone.find("td:eq(10)").css("width","80");
	clone.find("td:eq(11)").css("width","90");
	clone.table2excel({
		filename: "배후단지임대목록",
	});
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	지역적 모듈 함수 정의 종료
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

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
                <!-- 
                <li><a href="#tabs3" class="emdTab">실적평가</a></li>
                <li><a href="#tabs4" class="emdTab">면적평가</a></li>
                -->
                <li><a href="#tabs5" class="emdTab">고지내역</a></li>
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
	              					<!-- <button data-role="gridXlsDown" data-flexi-grid="assetRentMngtList" data-xls-name="배후단지임대목록.xls" data-xls-title="배후단지 임대목록">엑셀</button> -->
	              					<button id="btnExcelDownload">엑셀</button>
                      	            <button id="btnRentFeeMngt">임대료 고지관리</button>
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
						<input type="hidden" id="quayGroupCd" value="H"/>
						<input type="hidden" id="mngYear"/>
						<input type="hidden" id="mngNo"/>
						<input type="hidden" id="mngCnt"/>
						<input type="hidden" id="termnKnd"/>
                        <table class="editForm" style="width:100%">
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
									<input type="text" size="12" id="operYrMt" maxlength="6"/>
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
           	<!-- 
            <div id="tabs3" class="emdTabPage fillHeight" style="overflow: hidden;">
                <div class="emdControlPanel">
                	<table id="bizAssessGrid" style="display:none" class="fillHeight"></table>
                    <table id="summaryTable" style="width:100%;" class="summaryPanel">
                        <tr>
                        	<th width="10%">
                        		고지대상기간
                        	</th>
                            <td width="40%" style="text-align: left;">
                                <input type="text" size="12" id="grBizNticPdFrom" class="emdcal"/>~
                                <input type="text" size="12" id="grBizNticPdTo" class="emdcal"/>
                           	</td>
                     	    <th width="10%">
                        		평가합계금액
                        	</th>
                            <td width="40%" style="text-align: left;">
                            	<input type="text" size="20" id="sumBizAssessAmt" class="ygpaNumber"/>원
                           	</td>
                        </tr>
                    </table>
	                <button id="btnAppendBizAssess">추가</button>
	                <button id="btnRemoveBizAssess">삭제</button>
	                <button id="btnSaveBizAssess">저장</button>
	                <button id="btnRentFeeMngt2">임대료 고지관리</button>
	                <button data-role="gridXlsDown" data-flexi-grid="bizAssessGrid" data-xls-name="배후단지실적평가.xls" data-xls-title="배후단지 실적평가">엑셀</button>
               	</div>
            </div>
            <div id="tabs4" class="emdTabPage fillHeight" style="overflow: hidden;">
                <div class="emdControlPanel">
       	        	<table id="areaAssessGrid" style="display:none" class="fillHeight"></table>
       	        	<form id="areaAssessInputForm">
						<input type="hidden" id="nticCnt"/>
						<input type="hidden" id="aChrgeKnd"/>
	       	        	<table class="detailPanel" style="width:100%;">
	 						<tr>
								<th width="10%" height="18">평가회차</th>
								<td width="23%"><input id="assessNo" type="text" size="20" disabled="disabled"></td>
								<th width="10%" height="18">평가기간</th>
								<td colspan="3">
									<input type="text" size="20" id="dtFrom" class="emdcal"/>~
									<input type="text" size="20" id="dtTo" class="emdcal"/>
		                        </td>
							</tr>
							<tr>
								<th width="10%" height="18">사용면적</th>
								<td><input id="usageAr" type="text" class="ygpaNumber" data-decimal-point="2"  size="20">(㎡)</td>
								<th width="10%" height="18">변경면적</th>
								<td width="23%"><input id="changeAr" type="text" class="ygpaNumber" data-decimal-point="2"  size="20">(㎡)</td>
								<th width="10%" height="18">변동면적</th>
								<td><input id="increaseAr" type="text" class="ygpaNumber" data-decimal-point="2" size="20">(㎡)</td>
							</tr>
							<tr>
								<th width="10%" height="18">적용단가</th>
								<td><input id="usagePrice" type="text" class="ygpaNumber" data-decimal-point="1" size="20">원</td>
								<th width="10%" height="18">평가금액</th>
								<td colspan="3"><input id="assessAmt" type="text" class="ygpaNumber" size="20">원</td>
	 						</tr>
	                   </table>
                   </form>
                	<button id="btnAppendAreaAssess">추가</button>
                	<button id="btnRemoveAreaAssess">삭제</button>
                	<button id="btnSaveAreaAssess">저장</button>
                	<button id="btnRentFeeMngt3">임대료고지 관리</button>
                	<button data-role="gridXlsDown" data-flexi-grid="areaAssessGrid" data-xls-name="배후단지면적평가.xls" data-xls-title="배후단지 면적평가">엑셀</button>
               	</div>
            </div>
            -->
            <div id="tabs5" class="emdTabPage fillHeight" style="overflow: hidden;">
                <div class="emdControlPanel">
                <table id="nticListGrid" style="display:none" class="fillHeight"></table>
                	<button id="btnRentFeeMngt4">임대료고지 관리</button>
                	<button data-role="gridXlsDown" data-flexi-grid="nticListGrid" data-xls-name="배후단지임대료고지내역.xls" data-xls-title="배후단지 임대료 고지 내역">엑셀</button>
              	</div>
            </div>
        </div>
    </div>
</div>