<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamAssetMngt.jsp
  * @Description : 자산코드관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2013.10.29  장은성          최초 생성
  *
  * author 장은성
  * since 2013.10.29
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamBuldMktcStdAmVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamBuldMktcStdAmModule() {}

GamBuldMktcStdAmModule.prototype = new EmdModule(800, 525);

// 페이지가 호출 되었을때 호출 되는 함수
GamBuldMktcStdAmModule.prototype.loadComplete = function() {
	this.$("#mainGrid").flexigrid({
		module: this,
		url: '/code/gamBuldMktcStdAmList.do',
		dataType: "json",
		colModel : [	
			{display:"연도", 			name:"stdyy",			width:60, 	sortable:false,	 align:"center"},
			{display:"주소", 			name:"fullAdres",		width:250, 	sortable:false,	 align:"center"},
			{display:"건물동 ", 		name:"bulddong",		width:60, 	sortable:false,	 align:"center"},
			{display:"건물호 ", 		name:"bdh",				width:60, 	sortable:false,	 align:"center"},
			{display:"시가표준액(원)", 	name:"mktcStdAm",		width:100, 	sortable:false,	 align:"right",	displayFormat: 'number'},
			{display:"실축년도", 		name:"competDe",		width:100, 	sortable:false,	 align:"center"},
			{display:"전용면적(m2)", 	name:"prvuseAr",		width:100, 	sortable:false,	 align:"right",	displayFormat: 'number'},
			],
			showTableToggleBtn : false,
			height: 'auto',		

	});

	 // 그리드 클릭
 	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainKeyValue = row.adres;
		module._mainmode = "modify";
    });

	 // 그리드 더블클릭
	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainKeyValue = row.adres;
		module._mainmode = "modify";
		module.$("#mainTab").tabs("option", {active: 1});
	});
	 
	// 연도 셀렉트 박스 생성	
	var toDate = new Date();
	var toYear = toDate.getFullYear();

	for(var i = toYear;i>=2010;i--){
		this.$("#stdyy").append("<option value='" + i + "'>" + i + "년</option>");
	}
}

<%
/**
 * @FUNCTION NAME : onTabChange
 * @DESCRIPTION   : 탭이 변경 될때 호출된다. (태그로 정의 되어 있음)
 * @PARAMETER     :
 *   1. newTabId - NEW TAB ID
 *   2. oldTabId - OLD TAB ID
**/
%>
 GamBuldMktcStdAmModule.prototype.onTabChange = function(newTabId, oldTabId) {
	console.log("onTabChange");
	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			// 탭 1번 수정 때
			if (this._mainmode == "modify") {	// 탭 1번 수정 때
				this.loadDetail(oldTabId);
				this.$("#adres").disable();
				
			} else if (this._mainmode == "insert") { // 탭 1번 추가 때
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				
				var toDate = new Date();
				var toStdyy = toDate.getFullYear();
				this.$('#stdyy').val(toStdyy);
				
				//this.$('#popupSpecFcltsMngGroupNo').enable();
				
				//기본 데이터 설정
				// this.addData();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				
				var toDate = new Date();
				var toStdyy = toDate.getFullYear();
				this.$('#stdyy').val(toStdyy);
			}
			break;
	}

}
 
 GamBuldMktcStdAmModule.prototype.loadData = function() {
		if(this.$('#searchBupjungdongNm').val().length<2 && this.$('#searchBupjungdongCd').val().length<4) {
			alert('법정동 코드를 4자리 이상 입력 하거나 검색할 주소를 두자 이상 입력 하세요');
			this.$('#searchBupjungdongNm').addClass('ui-state-error');
			this.$('#searchBupjungdongCd').addClass('ui-state-error');
			return;
		}
		else {
			this.$('#searchBupjungdongNm').removeClass('ui-state-error');
			this.$('#searchBupjungdongCd').removeClass('ui-state-error');
		}
		var searchOpt=this.makeFormArgs('#searchBupjungDong');
	 	this.$('#addrList').flexOptions({params:searchOpt}).flexReload();
	}; 
	
<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : 버튼클릭 호출된다. 
 * @PARAMETER     :
 *   1. buttonId - buttonId
**/
%>
GamBuldMktcStdAmModule.prototype.onButtonClick = function(buttonId) {
	console.log("onButtonClick");
	switch (buttonId) {
		case 'btnAdd':
			this._mainmode = 'insert';
			this._mainKeyValue = '';
			this.$("#mainTab").tabs("option", {active: 1});
			break;
		case 'btnSave':
			this.saveData();
			break;
		case 'btnDelete':
		case 'btnDelete1':
			if (this._mainmode=="modify") {
				this.loadDetail('listTab');
				this.deleteData();
			}
			break;
		// 주소조회		
		case 'selectAddr':
	        this.doExecuteDialog('selectAddrPopup', '주소 입력', '/popup/showAddrPopup.do', []);
			break;
	}
}

GamBuldMktcStdAmModule.prototype.onClosePopup = function(popupId, msg, value){
	switch (popupId){
	case 'selectAddrPopup':
	if (msg == 'ok') {
		this.$('#adstrdCode').val(value.bupjungdongCd);		
		this.$('#adres').val(value.bupjungdongNm);		
	}
	break;
	}
}

<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
 GamBuldMktcStdAmModule.prototype.onSubmit = function() {
	this.$("#adres").val('');
	this.loadData();
}

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
 GamBuldMktcStdAmModule.prototype.loadData = function() {
	this._mainmode='';
	this.$("#mainTab").tabs("option", {active: 0});	// 탭 이동
	var searchOpt=this.makeFormArgs('#searchForm');	// searchOpt = {"sAdres":"", "sLnm":"", "sSlno""}
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();	// 그리드 설정된 내용으로 로딩
}

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamBuldMktcStdAmModule.prototype.loadDetail = function(tabId) {
	console.log("loadDetail");
	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();	// 현재 선택된 row 값 가지고 오기
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);	// row[0] 번째 데이터를 detailForm에 넣기(자동으로 id 매칭하여 넣어 줌)
//		this.makeDivValues('#detailForm', row[0]);
	}
}

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamBuldMktcStdAmModule.prototype.saveData = function() {
	console.log("saveData");
	
 	if (!validateGamBuldMktcStdAmVO(this.$("#detailForm")[0])){
		return;
	} 
 		
	var inputVO = this.makeFormArgs("#detailForm");
	if (this._mainmode == "insert") { // 추가 버튼 눌렀을때 insert로 변경됨
		this.doAction('/code/gamBuldMktcStdAmInsertList.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	} else { // 추가 버튼 눌렀을때 insert가 아닌경우
		this.doAction('/code/gamBuldMktcStdAmUpdateList.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	}
}


<%
/**
 * @FUNCTION NAME : deleteData
 * @DESCRIPTION   : 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamBuldMktcStdAmModule.prototype.deleteData = function() {
	console.log("deleteData");

	if (confirm("삭제하시겠습니까?")) {
		//var deleteVO = row[0];
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/code/gamBuldMktcStdAmDeleteList.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module._mainKeyValue = '';
				module.loadData();
			}
			alert(result.resultMsg);
		});
	} 
	
} 

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamBuldMktcStdAmModule();

</script>
<%
/******************************** UI     START ********************************/
%>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId }'/>
<div  class="window_main">
<!-- 11. SEARCH AREA (조회조건 영역) -->
	<div id="searchViewStack" class="emdPanel">
		<div class="viewPanel">
			<form id="searchForm">		
						<table style="width:100%;" class="searchPanel">
							<tbody>
								<tr>
									<th>주소</th>
									<td>
										<input type="text"  id="sAdres" data-column-id="sAdres" size = "40"> 
									</td>
									<th>지번</th>
									<td>
										<input id="sLnm" data-column-id="sLnm" type="text" size="4">
									</td>
									<th>부번</th>
									<td>
										<input id="sSlno" data-column-id="sSlno" type="text" size="4">
									</td>
									<td><button class="buttonSearch">조회</button></td>
								</tr>						
							</tbody>
						</table>
			</form>
		</div>
	</div>
<!-- 2. DATA AREA (자료 영역) -->
	<div class="emdPanel fillHeight">
	<!-- 21. TAB AREA (탭 영역) -->
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#listTab" class="emdTab">건축물시가표준액목록</a></li>
				<li><a href="#detailTab" class="emdTab">건축물시가표준액상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" data-onactivate="onShowTab2Activate">
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnAdd" class="buttonAdd">추가</button>
					<button id="btnDelete" class="buttonDelete">삭제</button>		
				</div>
			</div>
			
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow: scroll">
				<form id="detailForm">
					<input type="hidden"  id="sn" data-column-id="sn" />
					<input type="hidden"  id="adstrdCode" data-column-id="adstrdCode" />
					<table class="detailPanel">
						<tr>
						<th width="20%" height="23" class="required_text">기준년도</th>
	 							<td colspan="3">
	 								<select id="stdyy" data-column-id="stdyy">
									</select>
	 							</td>
						</tr>
	
						<tr>
							<th width="20%" height="23" class="required_text">주소</th>
							<td colspan="3">
							<input type="text" size="80" id="adres" data-column-id="adres" maxlength="80"/>
							<button id="selectAddr" class="popupButton">주소</button>
							</td>
							</td>
						</tr>		
						<tr>
							<th width="20%" height="23" class="required_text">지번</th>
							<td colspan="3">
								<input type="text" size="4" id="lnm" data-column-id="lnm" maxlength="5" />
								-
								<input type="text" size="4" id="slno" data-column-id="slno" maxlength="5" />
							</td>
						</tr>		
										
						<tr>
							<th width="20%" height="23" class="required_text">특수번지</th> <!-- 건물위치  -->
							<td colspan="3">								
								<select id="buldLc" data-column-id="buldLc">
									<option value="" selected>-선택-</option>
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
 							</td>
						</tr>
						
						<tr>
							<th width="20%" height="23" class="required_text">건물 동</th>
							<td colspan="3"><input type="text" size="20" id="bulddong" data-column-id="bulddong" maxlength="50" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">건물 호</th> 
							<td colspan="3"><input type="text" size="20" id="bdh" data-column-id="bdh" maxlength="30" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">건물용도</th> 
							<td colspan="3">								
								<select id="buldPrpos" data-column-id="buldPrpos">
									<option value="" selected="selected">선택</option>
									<option value="01000">단독주택</option>
									<option value="02000">공동주택</option>
									<option value="03000">제1종근린생활시설</option>
									<option value="04000">제2종근린생활시설</option>
									<option value="05000">문화및집회시설</option>
									<option value="06000">종교시설</option>
									<option value="07000">판매시설</option>
									<option value="08000">운수시설</option>
									<option value="09000">의료시설</option>
									<option value="10000">교육연구시설</option>
									<option value="11000">노유자시설</option>
									<option value="12000">수련시설</option>
									<option value="13000">운동시설</option>
									<option value="14000">업무시설</option>
									<option value="15000">숙박시설</option>
									<option value="16000">위락시설</option>
									<option value="17000">공장</option>
									<option value="18000">창고시설</option>
									<option value="19000">위험물저장및처리시설</option>
									<option value="20000">자동차관련시설</option>
									<option value="21000">동.식물관련시설</option>
									<option value="23000">교정및군사시설</option>
									<option value="24000">방송통신시설</option>
									<option value="25000">발전시설</option>
									<option value="26000">묘지관련시설</option>
									<option value="27000">관광휴게시설</option>
									<option value="28000">가설건축물</option>
									<option value="29000">장례시설</option>
									<option value="30000">자원순환관련시설</option>
									<option value="31000">야영장시설</option>
								</select>
 							</td> 						
						</tr>
						
						<tr>
						<th width="20%" height="23" class="required_text">건물구조</th> 
							<td colspan="3">								
								<select id="buldRescue" data-column-id="buldRescue">
									<option value="" selected="selected">전체</option>
									<option value="11">벽돌구조</option>
									<option value="12">블록구조</option>
									<option value="13">석구조</option>
									<option value="14">스틸하우스조</option>
									<option value="17">보강콘크리트조</option>
									<option value="19">기타조적구조</option>
									<option value="21">철근콘크리트구조</option>
									<option value="22">프리케스트콘크리트구조</option>
									<option value="23">철파이프조</option>
									<option value="24">돌담 및 토담조</option>
									<option value="26">라멘조</option>
									<option value="27">석회 및 흙혼합 벽돌조</option>
									<option value="29">기타콘크리트구조</option>
									<option value="31">일반철골구조</option>
									<option value="32">경량철골구조</option>
									<option value="33">강파이프구조</option>
									<option value="34">공업화박판강구조(PEB)</option>
									<option value="35">단일형강구조</option>
									<option value="36">트러스구조</option>
									<option value="39">기타강구조</option>
									<option value="41">철골콘크리트구조</option>
									<option value="42">철골철근콘크리트구조</option>
									<option value="43">철골철근콘크리트합성구조</option>
									<option value="49">기타철골철근콘크리트구조</option>
									<option value="51">일반목구조</option>
									<option value="52">통나무구조</option>
									<option value="53">트러스목구조</option>
									<option value="61">시멘트블럭조</option>
									<option value="63">조립식판넬조</option>
									<option value="72">흙벽돌조</option>
									<option value="74">컨테이너조</option>
									<option value="81">막구조</option>
									<option value="99">기타구조</option></select>
 							</td> 							
						</tr>
						
						<tr>
							<th width="20%" height="23" class="required_text">신축년도</th> <!-- 준공일자 -->
							<td colspan="3"><input type="text" size="20" id="competDe" data-column-id="competDe" class="emdcal"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">전용면적(m2)</th> 
							<td colspan="3"><input type="text" id="prvuseAr" data-column-id="prvuseAr" class="ygpaNumber" size="20" maxlength="20" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">시가표준액(원)</th> 
							<td colspan="3"><input type="text" id="mktcStdAm" data-column-id="mktcStdAm" class="ygpaNumber" size="20" maxlength="20" /></td>
						</tr>
						
				</table>
				
				<div style="vertical-align: bottom; text-align: right;">
					<button id="btnSave">저장</button>					
					<button id="btnDelete">삭제</button>
				</div>
			</form>				
		</div>
	</div>
</div>

