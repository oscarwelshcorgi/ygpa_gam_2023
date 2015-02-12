<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
	/**
	 * @Class Name : GamFcltyUsageMng.jsp
	 * @Description : 시설물 사용현황 조회
	 * @Modification Information
	 *
	 *   수정일          수정자                수정내용
	 *  -----------    --------    ---------------------------
	 *  2014.12.08		jckim       최초 생성
	 *	2014.02.11		정성현		대부분 수정
	 * author jckim
	 * since 2014.12.08
	 *
	 * Copyright (C) 2013 by LFIT  All right reserved.
	 */
%>
<validator:javascript formName="fcltyUsageSttusSearchForm"  method="validateFcltyUsageSttusDate"  staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>

<%
/**
 * @FUNCTION NAME : GamFcltyUsageSttusInqireModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
	function GamFcltyUsageSttusInqireModule() {
	}

	GamFcltyUsageSttusInqireModule.prototype = new EmdModule(1000, 600);

	// 페이지가 호출 되었을때 호출 되는 함수
<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>

 GamFcltyUsageSttusInqireModule.prototype.loadComplete = function() {

	this._searchVO = "";
	// 자산임대 테이블 설정
/* GIS 자산 리스트 */
	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fcltyMng/gamFcltyGisPrtFcltyCdList.do',
		dataType : 'json',
		colModel : [ {display : '항구분',		 name : 'gisAssetsPrtAtNm', width : 80, sortable : false, align : 'center'}
		            ,{display : '시설 구분',		 name : 'fcltsJobSeNm', width : 100, sortable : false, align : 'center'}
		            ,{display : '시설 명',		 name : 'prtFcltyNm', width : 250, sortable : false, align : 'left'}
		            ,{display : '시설물관리그룹',name:'fcltsMngGroupNm',	width:120, sortable:false, align:'left'}
		            ,{display : '시설 규격',		 name : 'prtFcltyStndrd', width : 100, sortable : false, align : 'center'}
		            ,{display : '시설 단위',		 name : 'prtFcltyUnit', width : 100, sortable : false, align : 'center'}
		            ,{display : '시설 수량',		 name : 'prtPrtFcltyCnt', width : 90, sortable : false, align : 'center'}
		            ,{display : '시설 담당',		 name : 'prtPrtFcltyMnger', width : 100, sortable : false, align : 'center'}
		],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#gisTotalCount').val(data.totalCount);
			module.makeFormValues('#fcltyUsageSttusSearchForm', data.result);
			return data;
		}
	});

	
	// 자산임대현황 내역
		this.$("#assetsRentGrid").flexigrid({
		module : this,
		url : '/fcltyMng/selectLoadAssetRentData.do',
		dataType : 'json',
		colModel : [ {display : '항구분', 	  	 name : 'prtAtCode', width : 60, sortable : false, align : 'center'}
		            ,{display : '사용기간',  	 name : 'usagePd', width : 200, sortable : false, align : 'center'}
		            ,{display : '업체명', 		 name : 'entrpscd', width : 120, sortable : false, align : 'left'}
		            ,{display : '신청일자', 		 name : 'reqstDt', width : 75, sortable : false, align : 'center'}
		            ,{display : '허가일자',		 name : 'prmisnDt', width : 75, sortable : false, align : 'center'}
		            ,{display : '납부방법',		 name : 'payMth', width : 70, sortable : false, align : 'center'}
		            ,{display : '과세구분',		 name : 'taxtNm', width : 90, sortable : false, align : 'center'}
		            ,{display : '사용면적㎡', 	 name : 'usageAr', width : 70, sortable : false, align : 'right', displayFormat : 'number', displayOption:{format:"0,000.00"}},
		            ,{display : '사용목적',		 name : 'usagePurps', width : 150, sortable : false, align : 'left'}
		            ,{display : '사용내역',		 name : 'usageDtls', width : 125, sortable : false, align : 'left'}
		            ,{display : '산출내역',		 name : 'computDtls', width : 90, sortable : false, align : 'left'}
		            ,{display : '공시지가',		 name : 'olnlp', width : 70, sortable : false, align : 'right', displayFormat : 'number'}
		            ,{display : '적용요율',		 name : 'applcTariff', width : 70, sortable : false, align : 'right' , displayFormat : 'number', displayOption:{format:"0,000.00"}},
		            ,{display : '적용방법',		 name : 'applcMth', width : 70, sortable : false, align : 'left'}
		            ,{display : '금액',			 name : 'fee', width : 100, sortable : false, align : 'right', displayFormat : 'number'}
		             ],
		height : 'auto'
		,preProcess :function(module,data){
			module.$('#assetsTotalCount').val(data.totalCount);
			return data;
		}
	});
 	 	this.$("#assetsRentGrid").on('onLoadDataComplete', function(event, module, data, grid, param) {
		module.selectData();
		});  
	 	
	// 점검 관리 내역
	this.$("#qcwWrtMngGrid").flexigrid({
		module : this,
		url : '/fcltyMng/selectLoadQcwWrtMngData.do',
		dataType : 'json',
		colModel : [ {display : '관리 그룹', 		name : 'fcltsMngGroupNm', width : 110, sortable : false, align : 'center'}
			        ,{display : '업무 구분', 		name : 'fcltsJobNm', width : 70, sortable : false, align : 'center'}
	        		,{display : '시행 년도', 		name : 'enforceYear', width : 60, sortable : false, align : 'center'}
			        ,{display : '관리 명', 			name : 'qcMngNm', width : 80, sortable : false, align : 'left'}
			        ,{display : '진단 일자', 		name : 'qcInspDt', width : 75, sortable : false, align : 'center'}
			        ,{display : '진단 구분',			name : 'qcInspSeNm', width : 60, sortable : false, align : 'center'}
			        ,{display : '진단 기관 명',		name : 'qcInspInsttNm', width : 80, sortable : false, align : 'right'}
			        ,{display : '기술자 명', 		name : 'responEngineerNm', width : 70, sortable : false, align : 'right'}
			        ,{display : '시작일자', 			name : 'qcBeginDt', width : 75, sortable : false, align : 'center'}
			        ,{display : '종료 일자',			name : 'qcEndDt', width : 75, sortable : false, align : 'center'}
			        ,{display : '진단 금액', 		name : 'qcInspAmt', width : 80, sortable : false, align : 'right', displayFormat : 'number'}
			        ,{display : '비고',				name : 'rm', width : 85, sortable : false, align : 'left'}
		],
		height : '250'
	});

// 시설물 점검기록 선택
	this.$("#qcwWrtMngGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module.makeFormValues('#qcwWrtMngDetail' , row);
		});

	this.$("#qcwWrtMngGrid").on('onLoadDataComplete', function(event, module, data, grid, param) {
		module.selectData();
	});  

	// 유지 보수 내역 
	this.$("#maintMngGrid").flexigrid({
		module : this,
		url : '/fcltyMng/selectLoadMaintMngData.do',
		dataType : 'json',
		colModel : [{display:"시설물관리그룹", 		name:"fcltsMngGroupNm",			width:130, 		sortable:false,		align:"center"},
					{display:"유지보수공사명",		name:"mntnRprCnstNm",			width:250, 		sortable:false,		align:"left"},
					{display:"시작일자",				name:"mntnRprCnstStartDt",		width:80, 		sortable:false,		align:"center"},
					{display:"종료일자",				name:"mntnRprCnstEndDt",		width:80, 		sortable:false,		align:"center"},
					{display:"공사금액", 			name:"mntnRprCnstAmt",			width:100, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"유지보수예산", 		name:"mntnRprBdgt",				width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"시공자", 				name:"cnstrtr",					width:150, 		sortable:false,		align:"center"},
					{display:" ", 					name:"mntnFcltsCnstInfo",		width:0, 		sortable:false,		align:"center"}
					
					
			],
		height: "230",
		groupBy: "mntnFcltsCnstInfo"
	});
	
	this.$("#maintMngGrid").on('onItemSelected', function(event, module, row, grid, param) {
			module.makeFormValues('#maintMngDetail' , row);
		});
	
	this.$("#maintMngGrid").on('onLoadDataComplete', function(event, module, data, grid, param) {
			module.selectData();
	});  
	// 하자 보수 내역
	this.$("#repairMngGrid").flexigrid({
		module : this,
		url : '/fcltyMng/selectLoadRepairMngData.do',
		dataType : 'json',
		colModel : [ {display : '시설물관리그룹', name : 'fcltsMngGroupNm', width : 100, sortable : false, align : 'center'}
					,{display : '하자검사구분', name : 'flawExamSeNm', width : 100, sortable : false, align : 'center'}
					,{display : '하자보수 명', name : 'flawRprNm', width : 100, sortable :false, align : 'left'}
					,{display : '하자 유무', name : 'flawEnnc', width : 100, sortable : false, align : 'center'}
					,{display : '하자발생 일자', name : 'flawOccrrncDt', width : 100, sortable : false, align : 'center'}
					,{display : '하자보수 유형', name : 'flawRprTy', width : 100, sortable : false, align : 'center'}
					,{display : '시행년도', name : 'enforceYear', width : 100, sortable : false, align : 'center'}
					,{display : '시작일자', name : 'flawRprStartDt', width : 100, sortable : false, align : 'center'}
					,{display : '종료일자', name : 'flawRprEndDt', width : 100, sortable : false, align : 'center'}
					,{display : '하자보수 업체명', name : 'flawRprEntrpsNm', width : 100, sortable : false, align : 'left'}
					,{display : '하자보수 완료여부', name : 'flawRprComptYn', width : 130, sortable : false, align : 'center'}
					,{display : '비고', name : 'rm', width : 100, sortable : false, align : 'left'}
		           ],
		height : '230'
	});
	
	 // 마우스 클릭(하자보수)
	this.$("#repairMngGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module.makeFormValues('#repairMngDetail', row);
		
	}); 
	this.$("#repairMngGrid").on('onLoadDataComplete', function(event, module, data, grid, param) {
		module.selectData();
	});  
	  this.$("#sUsagePdFrom").val(EMD.util.getDate(EMD.util.addMonths(-12)));
	  this.$("#sUsagePdTo").val(EMD.util.getDate());
		
	  this.$("#sFcltsMngGroupNo").bind("keyup change", {module: this}, function(event) {
	  	event.data.module.getSearchFcltsMngGroupNm();
	  });	
 };
 
 <%
 /**
  * @FUNCTION NAME : selectData
  * @DESCRIPTION   : 데이터 조회결과값 처리
  * @PARAMETER     : NONE	
  * 
 **/
 %>
 GamFcltyUsageSttusInqireModule.prototype.selectData = function() {
		
	    if(this._cmd == 'rent'){
				var rentRowCnt = this.$("#assetsRentGrid").flexRowCount();	
				if (rentRowCnt == 0 ) {
					alert('해당 조건의 자료가 존재하지 않습니다!');
					this.$("#mainTab").tabs("option", {active: 0});
					return;
					}
				}
		if(this._cmd =='qcw'){	
				var qcwRowCnt = this.$("#qcwWrtMngGrid").flexRowCount();
				if (qcwRowCnt == 0 ) {
					alert('해당 조건의 자료가 존재하지 않습니다!');
					this.$("#mainTab").tabs("option", {active: 0});
					return;
				}
			}	
		if(this._cmd =='maint'){
				var maintRowCnt = this.$("#maintMngGrid").flexRowCount();
				if (maintRowCnt == 0 ) {
					alert('해당 조건의 자료가 존재하지 않습니다!');
					this.$("#mainTab").tabs("option", {active: 0});
					return;
				}
			}
		if(this._cmd =='repairMng'){
				var repairRowCnt = this.$("#repairMngGrid").flexRowCount();
				if (repairRowCnt == 0 ) {
					alert('해당 조건의 자료가 존재하지 않습니다!');
					this.$("#mainTab").tabs("option", {active: 0});
					return;
			}
		}
	}; 





<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>

GamFcltyUsageSttusInqireModule.prototype.onSubmit = function() {
 	
	if(!validateFcltyUsageSttusDate(this.$('#fcltyUsageSttusSearchForm')[0])){
 		return;
 	} //validation check
	if (this.$("#sUsagePdFrom").val() > this.$("#sUsagePdTo").val()){
 		alert('조회 기간 From의 날짜가 To 날짜보다 클수 없습니다.');
 		return;
	}
	this.loadData();
};


<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>

GamFcltyUsageSttusInqireModule.prototype.loadData = function() {
	this.$("#mainTab").tabs("option", {active: 0});
	var searchVO=this.makeFormArgs('#fcltyUsageSttusSearchForm');
	this.$('#mainGrid').flexOptions({params:searchVO}).flexReload();
};


<%
/**
 * @FUNCTION NAME : selectLoadAssetRentData
 * @DESCRIPTION   : 임대현황 리스트 
 * @PARAMETER     : NONE
**/
%>
GamFcltyUsageSttusInqireModule.prototype.selectLoadAssetRentData = function() {
	var row = this.$('#mainGrid').selectedRows()[0];
	var searchVO = [
	                { name: 'gisAssetsPrtAtCode', value: row['gisAssetsPrtAtCode'] },
	                { name: 'gisAssetsCd', value: row['gisAssetsCd'] },
	                { name: 'gisAssetsSubCd', value: row['gisAssetsSubCd'] },
	                { name: 'fcltsMngNo', value: row['fcltsMngNo'] },
	                { name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	                { name: 'sUsagePdFrom', value: this.$('#sUsagePdFrom').val() },
	                { name: 'sUsagePdTo', value: this.$('#sUsagePdTo').val() }
	               ];

	this.$('#assetsRentGrid').flexOptions({params:searchVO}).flexReload();
};

<%
/**
 * @FUNCTION NAME : selectLoadQcMngWrtData
 * @DESCRIPTION   : 점검기록 리스트 
 * @PARAMETER     : NONE
**/
%>
GamFcltyUsageSttusInqireModule.prototype.selectLoadQcwWrtMngData = function() {
		row=this.$('#mainGrid').selectedRows()[0];
		var searchVO = [
		                { name: 'fcltsMngNo', value: row['fcltsMngNo'] },
		                { name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
		                { name: 'sUsagePdFrom', value: this.$('#sUsagePdFrom').val() },
		                { name: 'sUsagePdTo', value: this.$('#sUsagePdTo').val() }
						];
		this.$('#qcwWrtMngGrid').flexOptions({params:searchVO}).flexReload();
	};
<%
/**
 * @FUNCTION NAME : selectLoadMaintMngData
 * @DESCRIPTION   : 유지보수 리스트 
 * @PARAMETER     : NONE
**/
%>
GamFcltyUsageSttusInqireModule.prototype.selectLoadMaintMngData = function() {
		row=this.$('#mainGrid').selectedRows()[0];
			var searchVO = [
			                { name: 'fcltsMngNo', value: row['fcltsMngNo'] },
			                { name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
			                { name: 'sUsagePdFrom', value: this.$('#sUsagePdFrom').val() },
			                { name: 'sUsagePdTo', value: this.$('#sUsagePdTo').val() }
			               ];
			this.$('#maintMngGrid').flexOptions({params:searchVO}).flexReload();
			
		};
<%
/**
 * @FUNCTION NAME : selectLoadRepairMngData
 * @DESCRIPTION   : 하자보수 리스트 
 * @PARAMETER     : NONE
**/
%>
GamFcltyUsageSttusInqireModule.prototype.selectLoadRepairMngData = function() {
	row=this.$('#mainGrid').selectedRows()[0];
		var searchVO = [
		                { name: 'fcltsMngNo', value: row['fcltsMngNo'] },
		                { name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
		                { name: 'sUsagePdFrom', value: this.$('#sUsagePdFrom').val() },
		                { name: 'sUsagePdTo', value: this.$('#sUsagePdTo').val() }
						];
		
		this.$('#repairMngGrid').flexOptions({params:searchVO}).flexReload();
	
	};	



<%
/**
 * @FUNCTION NAME : getSearchFcltsMngGroupNm
 * @DESCRIPTION   : 조회조건 시설물관리그룹명을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyUsageSttusInqireModule.prototype.getSearchFcltsMngGroupNm= function() {
console.log('asdf');
	var sFcltsMngGroupNo = this.$('#sFcltsMngGroupNo').val();
	if (sFcltsMngGroupNo.length == 14) {
		var searchVO = { 'sFcltsMngGroupNo':sFcltsMngGroupNo };
		this.doAction('/fcltyMng/selectGetSearchFcltsMngGroupNm.do', searchVO, function(module, result) {
			console.log(result);
			if (result.resultCode == "0") {
				module.$('#sFcltsMngGroupNoNm').val(result.result.fcltsMngGroupNm);
			}
		});
	} else {
		this.$('#sFcltsMngGroupNoNm').val('');
	}

};

<%
/**
 * @FUNCTION NAME : onClosePopup
 * @DESCRIPTION   : CLOSE POPUP EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
 *   2. msg      - MESSAGE
 *   3. value    - VALUE
**/
%>
GamFcltyUsageSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		// 검색조건 시설물 관리 그룹 
		case "sSelectFcltsMngGroup":
			this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#sFcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
		break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");

		break;
	}
};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamFcltyUsageSttusInqireModule.prototype.onButtonClick = function(buttonId) {
	switch (buttonId) {
		case 'btnGisExcelDownload':
			this.downloadGisExcel();
			break;
		case 'btnAssetsExcelDownload' :
			this.downloadAssetsExcel();
			break;
			// 검색조건 시설물 관리 그룹 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("sSelectFcltsMngGroup", "시설물 관리 그룹 번호", '/popup/showFcltsMngGroup.do', null);
		break;
	}

};


<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 시설물현황리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyUsageSttusInqireModule.prototype.downloadGisExcel = function() {
	var totalCount = Number(this.$('#gisTotalCount').val().replace(/,/gi, ""));
	if (totalCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/fcltyMng/gamExcelFcltyGisPrtFcltyCdList.do');
};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 임대현황리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyUsageSttusInqireModule.prototype.downloadAssetsExcel = function() {
	var totalCount = Number(this.$('#assetsTotalCount').val().replace(/,/gi, ""));
	if (totalCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#assetsRentGrid').flexExcelDown('/fcltyMng/gamExcelFcltyAssetsRentList.do');
};
<%
/**
 * @FUNCTION NAME : initDisplay
 * @DESCRIPTION   : 리스트를 초기화한다.
 * @PARAMETER     :  NONE
**/
%>
 GamFcltyUsageSttusInqireModule.prototype.initDisplay = function(){
	
		this.$('#assetsRentMngGrid').flexEmptyData();
		this.$('#qcwWrtMngGrid').flexEmptyData();
		this.$("#qcwWrtMngDetail :input").val("");
		this.$('#maintMngGrid').flexEmptyData();
		this.$("#maintMngDetail :input").val("");
		this.$('#repairMngGrid').flexEmptyData();
		this.$("#repairMngDetail :input").val("");
		this._cmd='';
};

<%
/**
 * @FUNCTION NAME : onTabChange
 * @DESCRIPTION   : 탭이 변경 될때 호출된다. (태그로 정의 되어 있음)
 * @PARAMETER     :
 *   1. newTabId - NEW TAB ID
 *   2. oldTabId - OLD TAB ID
**/
%>
 GamFcltyUsageSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch (newTabId) {
		case "tabs1":
				break;
		
		case "tabs2":
			this.initDisplay();
			this._cmd='rent';
			this.selectLoadAssetRentData();
			
				break;
		
		case "tabs3":
			this.initDisplay();
			this._cmd='qcw';
			this.selectLoadQcwWrtMngData();
			
			 	break;
		
		case "tabs4":
			this.initDisplay();
			this._cmd='maint';
			this.selectLoadMaintMngData();
				break;

		
		case "tabs5":
			this.initDisplay();
			this._cmd='repairMng';
			this.selectLoadRepairMngData();
			
				break;
		}
		return true;
	};
<%
/**
 * @FUNCTION NAME : onTabChangeBefore
 * @DESCRIPTION   : 탭이 변경 되기전에 호출된다. (태그로 정의 되어 있음)
 * @PARAMETER     :
 *   1. newTabId - NEW TAB ID
 *   2. oldTabId - OLD TAB ID
**/
%>
 
GamFcltyUsageSttusInqireModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	var rowCnt = this.$('#mainGrid').selectedRows();

	switch (newTabId) {
		case "tabs1":
			break;
		case "tabs2":
			if (rowCnt.length == 0) {
				alert('항목을 선택해주세요.');
				return false;
			}
			break;

		case "tabs3":
			if (rowCnt.length == 0) {
				alert('항목을 선택해주세요.');
				return false;
			}
			break;

		case "tabs4":
			if (rowCnt.length == 0) {
				alert('항목을 선택해주세요.');
				return false;
			}
			break;
		case "tabs5":
			if (rowCnt.length == 0) {
				alert('항목을 선택해주세요.');
				
				return false;
			}
			break;
		}
		return true;
	};

	// 다음 변수는 고정 적으로 정의 해야 함
	var module_instance = new GamFcltyUsageSttusInqireModule();
</script>

<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

	<div id="searchViewStack" class="emdPanel">
		<div class="viewPanel">
			<form id="fcltyUsageSttusSearchForm">
				<table style="width: 100%;" class="searchPanel">
					<tbody>
						<tr>
							<th>항구분</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>시설구분</th>
							<!-- <td><input type="text" id="sFcltsJobSe"/></td> -->
							<td>
								<select id="sFcltsJobSe" class="searchEditItem">
									<option value="A">건축시설</option>
									<option value="E">전기시설</option>
									<option value="M">기계시설</option>
									<option value="C">토목시설</option>
									<option value="I">정보통신시설</option>
								</select>
							</td>
							<th>시설명</th>
							<td><input type="text" id="sPrtFcltyNm"/></td>
							<td rowSpan="2"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>시설물 관리 그룹</th>
							<!-- <td><input type="text" id="sFcltsMngNo"/></td> -->
							<td colspan="3">
								<input id="sFcltsMngGroupNo" type="text" size="14" title="시설물관리그룹넘버" />
								<input id="sFcltsMngGroupNoNm" type="text" size="30" title="시설물관리그룹명" disabled="disabled" />
								<button id="searchPopupBtn" class="popupButton">선택</button>
							</td>
							<th>사용기간</th>
							<td>
								<input id="sUsagePdFrom" type="text" class="emdcal" size="8" > ~ <!-- data-required="true"> ~  -->
								<input id="sUsagePdTo" type="text" class="emdcal" size="8">
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
 		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore" >
			<ul>
				<li><a href="#tabs1" class="emdTab">시설물 사용현황</a></li>
				<li><a href="#tabs2" class="emdTab">시설물 임대현황</a></li>
				<li><a href="#tabs3" class="emdTab">시설물 점검기록</a></li>
				<li><a href="#tabs4" class="emdTab">시설물 유지보수</a></li>
				<li><a href="#tabs5" class="emdTab">시설물 하자보수</a></li>
			</ul>

			<!-- TAB 1 AREA (LIST) -->
			<div id="tabs1" class="emdTabPage fillHeight" style="overflow:scroll;" >
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
  				<div id="gisPrtFcltyCdPanel" class="emdControlPanel">
					<form id="gisPrtFcltyCdForm">
						<table style="width:100%;" class="summaryPanel">
							<tr>
								<th width="80" height="20">조회 자료수</th>
								<td ><input type="text" size="12" id="gisTotalCount" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align: right">
									<button data-role="gridXlsDown" data-flexi-grid="mainGrid">엑셀다운로드</button>
<!-- 	                                <button data-cmd="btnGisExcelDownload">엑셀다운로드</button>  -->
								</td>
							</tr>
						</table>
					</form>
 				</div>
			</div>
<!-- 탭2 -->
			<div id="tabs2" class="emdTabPage fillHeight" style="overflow:scroll;" >
			<table id="assetsRentGrid" style="display:none" class="fillHeight"></table>
				<table style="width:100%;" class="summaryPanel">
					<tr>
						<th width="80" height="20">조회 자료수</th>
						<td><input type="text" size="12" id="assetsTotalCount" class="ygpaNumber" disabled="disabled" /></td>
						<td style="text-align: right">
                               <button data-role="gridXlsDown" data-flexi-grid="assetsRentGrid">엑셀다운로드</button>
						</td>
					</tr>
				</table>
			</div>
<!-- 탭3 -->
			<div id="tabs3" class="emdTabPage fillHeight" style="overflow:scroll;">
				<form id="qcwWrtMngDetail">
			  <table id='qcwWrtMngGrid' style="display:none" ></table> 
				<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">점검기록 진단결과</th>
						</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							 <tr>
							<th width="10%" height="18">시설물 관리그룹</th>
								<td>
									<input type="text" size="15" id="fcltsMngGroupNm" disabled/>
								</td>
							<th width="10%" height="18">관　　리　　명</th>
								<td>
									<input type="text" size="15" id="qcMngNm" disabled/>
								</td>
							<th width="10%" height="18">상　태　등　급</th>
								<td>
									<input type="text" size="15" id="sttusEvlLvl" disabled/>
								</td>
							</tr> 
							 <tr>
							<th width="10%" height="18">진　단　결　과</th>
								<td colspan="5">
									<textarea id="qcInspResult" cols="125" rows="5"  style="overflow:auto;" readonly></textarea>
								</td>		
							</tr>
							<tr>							
							<th width="10%" height="18">조　치　내　용</th>
								<td colspan="5">
									<input type="text" id="actionCn" size="110" disabled />
								</td>
 							</tr>
						</table>
					
				</form>
			</div>						
<!-- 탭4 -->
			<div id="tabs4" class="emdTabPage fillHeight" style="overflow:scroll;">
			 	<form id="maintMngDetail">	
					<table id='maintMngGrid' style="display:none"></table>
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">유지보수 진단결과</th>
						</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							 <tr>
							<th width="10%" height="18">시설물 관리그룹</th>
								<td>
									<input type="text" size="20" id="fcltsMngGroupNm" disabled/>
								</td>
							<th width="10%" height="18">계　약　번　호</th>
								<td>
									<input type="text" size="20" id="ctrtNo" disabled/>
								</td>
							<th width="10%" height="18">유지보수　구분</th>
								<td>
									<input type="text" size="10" id="mntnRprSeNm" disabled/>
								</td>	
							</tr>
							<tr>
							 <th width="10%" height="18">계　　약　　명</th>
								<td colspan="5">
									<input type="text" size="100" id="ctrtNm" disabled/>
								</td>
							</tr>
							<tr>							
							<th width="10%" height="18">유지보수　부위</th>
								<td colspan="5">
									<input type="text" id="mntnRprPart" size="110" disabled />
								</td>
 							</tr>
							 <tr>
							<th width="10%" height="18">유지보수 　내용</th>
								<td colspan="5">
									<textarea id="mntnRprCn" cols="125" rows="5"  style="overflow:auto;" readonly></textarea>
								</td>		
							</tr>
							
						</table>
				</form>
			</div>
<!-- 탭5 -->
			<div id="tabs5" class="emdTabPage fillHeight" style="overflow:scroll;">
				<form id="repairMngDetail">	
				<table id='repairMngGrid' style="display:none"></table>
				<table class="summaryPanel" style="width:100%;">
					<tr>
						<th style="font-weight:bold; height:20px;">하자보수 진단결과</th>
					</tr>
					</table>
					<table class="detailPanel" style="width:100%;">
						 <tr>
						<th width="10%" height="18">시설물 관리그룹</th>
							<td>
								<input type="text" size="20" id="fcltsMngGroupNm" disabled/>
							</td>
						<th width="10%" height="18">계　약　번　호</th>
							<td>
								<input type="text" size="20" id="ctrtNo" disabled/>
							</td>
						<th width="10%" height="18">하자보수　구분</th>
							<td>
								<input type="text" size="10" id="flawExamSeNm" disabled/>
							</td>	
						</tr>
						<tr>
						 <th width="10%" height="18">하자　검사자</th>
							<td>
								<input type="text" size="20" id="flawExamUsr" disabled/>
							</td>
						 <th width="10%" height="18">검사자　성명</th>
							<td>
								<input type="text" size="20" id="flawExamUsrNm" disabled/>
							</td>
						 <th width="10%" height="18">검사자　소속</th>
							<td>
								<input type="text" size="20" id="flawExamUsrDept" disabled/>
							</td>		
						</tr>
						<tr>							
						<th width="10%" height="18">하자보수 　내용</th>
							<td colspan="5">
								<input type="text" id="flawRprContents" size="110" disabled />
							</td>
							</tr>
						 <tr>
						<th width="10%" height="18">하자검사 　결과</th>
							<td colspan="5">
								<textarea id="flawExamResult" cols="125" rows="5"  style="overflow:auto;" readonly></textarea>
							</td>		
						</tr>
						
					</table>
			</form>
			</div>

		</div>
	</div>
</div>