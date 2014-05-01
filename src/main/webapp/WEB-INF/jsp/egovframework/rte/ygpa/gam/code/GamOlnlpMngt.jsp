<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamOlnlpMngt.jsp
  * @Description : 공시지가 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.03.07  kok          최초 생성
  *
  * author kok
  * since 2014.03.07
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamOlnlpCode" staticJavascript="false" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamOlnlpMngtModule() {}

GamOlnlpMngtModule.prototype = new EmdModule(800,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamOlnlpMngtModule.prototype.loadComplete = function() {

	// 공시지가 등록현황 목록
	this.$("#olnlpInsertList").flexigrid({
		module: this,
		url: '<c:url value="/code/gamOlnlpInsertList.do" />',
		dataType: "json",
		colModel : [
				{display:"항코드",		 			name:"gisAssetsPrtAtCode",	width:40,		sortable:false,		align:"center"},
				{display:"항이름",		 			name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
				{display:"자산코드",		 			name:"gisAssetsTotCd",			width:60,		sortable:false,		align:"center"},
				{display:"자산명",		 			name:"gisAssetsNm",			width:140,		sortable:false,		align:"center"},
				{display:"소재지",		 			name:"gisAssetsLocplc",		width:220,		sortable:false,		align:"center"},
				{display:"지번", 					name:"gisAssetsLnmDisplay",	width:60,		sortable:false,		align:"center"},
				{display:"현재공시지가", 					name:"currOlnlp",	width:100,		sortable:false,		align:"center"}
			],
		height: "auto"
	});

	this.$("#olnlpInsertList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#olnlpMngtListTab").tabs("option", {active: 1});		// 탭을 전환 한다.
		module._selectRow= row;
/*
		var listInput = {gisAssetsCd:row["gisAssetsCd"], gisAssetsPrtAtCode:row["gisAssetsPrtAtCode"], gisAssetsSubCd:row["gisAssetsSubCd"]};
		module.doAction('<c:url value="/code/gamOlnlpMngtList.do" />', listInput, function(module, result) {

			module.$("#gisAssetsCd").val(result.searchOption.gisAssetsCd);
			module.$("#gisAssetsPrtAtCode").val(result.searchOption.gisAssetsPrtAtCode);
			module.$("#gisAssetsSubCd").val(result.searchOption.gisAssetsSubCd);

			var searchOpt = module.makeFormArgs("#olnlpManageVO");
			module.$("#olnlpMngtList").flexOptions({params:searchOpt}).flexReload();
			//if(result.totalCount == 0) alert("등록된 공시지가 목록이 없습니다.");
	 	}); */
	});


	// 공시지가 목록
	this.$("#olnlpMngtList").flexigrid({
		module: this,
		url: '<c:url value="/code/gamOlnlpMngtList.do" />',
		dataType: "json",
		colModel : [
				{display:"순번", 					name:"rnum",		width:80,		sortable:false,		align:"center"},
				{display:"시작일자",	 				name:"beginDt",			width:150,		sortable:false,		align:"center"},
				{display:"종료일자",		 			name:"endDt",			width:150,		sortable:false,		align:"center"},
				{display:"공시지가",		 			name:"olnlp",			width:270,		sortable:false,		align:"center"}
			],
		height: "auto",
		preProcess: function(module, data) {
			module._maxOlnlpSeq=0;
			$.each(data, function(d) {
				if(d.olnlpSeq>module._maxOlnlpSeq) module._maxOlnlpSeq=d.olnlpSeq;
			});
			return data;
		}
	});

	this.$("#olnlpMngtList").on("onItemSelected", function(event, module, row, grid, param) {
		module.makeFormValues('#olnlpManageVO', row);
		module._editData=module.getFormValues('#olnlpManageVO', row);
		module._editRow=module.$('#olnlpMngtList').selectedRowIds()[0];
	});

	this.$(".olnlpManageVO").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyOlnlp();
	});

/*
	this.$("#olnlpInsertList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#olnlpMngtListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		var listInput = {gisAssetsCd:row["gisAssetsCd"], gisAssetsPrtAtCode:row["gisAssetsPrtAtCode"], gisAssetsSubCd:row["gisAssetsSubCd"]};
		module.doAction('<c:url value="/code/gamOlnlpMngtList.do" />', listInput, function(module, result) {

			module.$("#gisAssetsCd").val(result.searchOption.gisAssetsCd);
			module.$("#gisAssetsPrtAtCode").val(result.searchOption.gisAssetsPrtAtCode);
			module.$("#gisAssetsSubCd").val(result.searchOption.gisAssetsSubCd);

			var searchOpt = module.makeFormArgs("#olnlpManageVO");
			module.$("#olnlpMngtList").flexOptions({params:searchOpt}).flexReload();
			//if(result.totalCount == 0) alert("등록된 공시지가 목록이 없습니다.");
	 	});
	});

	this.$("#olnlpMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {

		module.$("#beginDt").val(row["beginDt"]);			// 시작일자
		module.$("#endDt").val(row["endDt"]);				// 종료일자
		module.$("#olnlp").val(row["olnlp"]);				// 공지시가
		module.$("#olnlpSeq").val(row["olnlpSeq"]);			// 순번
		module.$("#cmd").val("update");						// 저장 Flag
	}); */
};

GamOlnlpMngtModule.prototype.applyOlnlp = function() {
	var selectRow = this.$('#olnlpMngtList').selectedRows();
	if(selectRow.length==0) return;

	var rownum;
	var row = {};
	row=this.getFormValues('#olnlpManageVO', selectRow[0]);
	rownum=this.$('#olnlpMngtList').selectedRowIds()[0];

	if(row["_updtId"]!='I') row["_updtId"]='U';
	this.$('#olnlpMngtList').flexUpdateRow(rownum, row);

	console.log('_updtId : ' + row["_updtId"]);

	this._edited=true;
};

/**
 * 정의 된 버튼 클릭 시
 */
GamOlnlpMngtModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":

			if(this.$("#gisAssetsLnmSub").val() != ""){
				if(this.$("#gisAssetsLnm").val() == ""){
					this.$("#gisAssetsLnm").focus();
					alert("지번 정보를 입력하여 주십시오.");
					return;
				}
			}

			var searchOpt = this.makeFormArgs("#olnlpForm");
		 	this.$("#olnlpMngtListTab").tabs("option", {active: 0});
		 	this.$("#olnlpInsertList").flexOptions({params:searchOpt}).flexReload();
		break;

		// 추가
		case "addBtn":
			this.$('#olnlpManageVO :input').val('');
			this._maxOlnlpSeq+=1;
			this._editRow={updtId: "I", gisAssetsPrtAtCode: this._selectRow.gisAssetsPrtAtCode
					, gisAssetsCd: this._selectRow.gisAssetsCd
					, gisAssetsSubCd: this._selectRow.gisAssetsSubCd, olnlpSeq: this._maxOlnlpSeq};

//			this.$("#olnlpMngtList").flexAddRow(this._editData);

		break;
		// 삭제
		case "deleteBtn":
			var rows = this.$("#olnlpMngtList").selectedRows();

			if(rows.length==0) {
				alert('삭제할 공시지가 항목을 선택 하십시요.');
				return;
			}
			if(confirm("선택 한 공시지가를 삭제하시겠습니까?")){
				var row=rows[0];
				if(row["_updtId"]!="I") {
					this.deleteList[this.deleteList.length]=row;
				}
				this.$('#olnlpMngtList').flexRemoveRow(this.$('#olnlpMngtList').selectedRowIds()[i]);
			}
		break;

		// 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisCodePopup", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;

		// 저장
		case "saveBtn":
			this.saveOlnlp();

		break;

		case 'insertExcel':
			this.uploadXlsFile('xlsUpload', function(module, result) {
				var searchOpt = module.makeFormArgs("#olnlpManageVO");
				module.$('#olnlpInsertList').flexOptions({params:searchOpt}).flexReload();
			}, '공시지가 엑셀파일 업로드', '/code/GamExcelOlnlpRegist.do');
			break;
	}
};

GamOlnlpMngtModule.prototype.saveOlnlp = function() {
	if( confirm("변경된 목록을 저장하시겠습니까?") ) {
	    // 변경된 자료를 저장한다.
	    var inputVO=[];
	    inputVO[inputVO.length]={name: 'updateList', value :JSON.stringify(this.$('#olnlpMngtList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

	    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#olnlpMngtList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

	    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this.deleteList) };

	    this.doAction('<c:url value="/code/mergeGamOlnlpMngt.do" />', inputVO, function(module, result) {
	        if(result.resultCode == 0){
	        	this.loadOlnlpList();
	        }
	        alert(result.resultMsg);
	        module._edited=false;
	    });
	}
};

GamOlnlpMngtModule.prototype.loadOlnlpList = function() {
	var row = this.$("#olnlpInsertList").selectedRows();
	if(row.length!=0) {
		var searchOpt = [
		          {name: 'gisAssetsPrtAtCode', value:row[0].gisAssetsPrtAtCode},
		          {name: 'gisAssetsCd', value:row[0].gisAssetsCd},
		          {name: 'gisAssetsSubCd', value:row[0].gisAssetsSubCd}
		          ];
		this.$('#olnlpMngtList').flexOptions({params:searchOpt}).flexReload();
		this.deleteList = [];
	}
};

/**
 * 탭 변경시 실행 이벤트
 */
 GamOlnlpMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		this.loadOlnlpList();
		break;
	}
};

GamOlnlpMngtModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		var row = this.$("#olnlpInsertList").selectedRows();
		if(row.length==0) {
			alert('먼저 자산을 선택 하십시요.');
			return false;
		}
		else {
			this.$('#lbGisAssetsPrtAtCode').text(row[0].gisAssetsPrtAtCode);
			this.$('#lbGisAssetsPrtAtNm').text(row[0].gisAssetsPrtAtName);
			this.$('#lbGisAssetsTotCd').text(row[0].gisAssetsTotCd);
			this.$('#lbGisAssetsNm').text(row[0].gisAssetsNm);
			this.$('#lbGisAssetsLocplc').text(row[0].gisAssetsLocplc);
			this.$('#lbGisAssetsLnmDisplay').text(row[0].gisAssetsLnmDisplay);
		}
		break;
	}
	return true;
};
/**
 * 팝업 close 이벤트
 */
GamOlnlpMngtModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){

		// 자산코드 조회
		case "searchGisCodePopup":
			this.$("#searchAssetsCd").val(value["gisAssetsCd"]);
			this.$("#searchAssetsSubCd").val(value["gisAssetsSubCd"]);
		break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			throw 0;
		break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamOlnlpMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="olnlpForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항코드</th>
							<td><input id="searchAssetsPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>자산코드</th>
							<td>
								<input id="searchAssetsCd" type="text" size="3" maxlength="3" title="검색조건" disabled="disabled"/>&nbsp;-&nbsp;
								<input id="searchAssetsSubCd" type="text" size="2" maxlength="2" title="검색조건" disabled="disabled"/>
								<button id="searchPopupBtn">자산코드</button>
							</td>
							<td rowSpan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						<tr>
							<th>소재지</th>
							<td>
								<input id="gisAssetsLocplc" type="text" size="50" title="검색조건" />
							</td>
							<th>지번</th>
							<td>
								<input id="gisAssetsLnm" type="text" size="5" title="검색조건"  />&nbsp;-&nbsp;
								<input id="gisAssetsLnmSub" type="text" size="5" title="검색조건"  />
							</td>
						</tr>
					</tbody>
				</table>
				<!-- <div class="emdControlPanel">
					<button id="searchBtn">조회</button>
				</div> -->
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="olnlpMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
			<ul>
				<li><a href="#tabs1" class="emdTab">공시지가등록현황목록</a></li>
				<li><a href="#tabs2" class="emdTab">공시지가목록</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="olnlpInsertList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="insertExcel">엑셀등록</button>
				</div>
			</div>

			<!-- 공시지가 목록 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<table class="detailForm">
					<colgroup>
						<col width="120"/>
						<col width="120"/>
						<col width="120"/>
						<col width="120"/>
						<col width="120"/>
						<col width="120"/>
					</colgroup>
					<tbody>
						<tr>
							<th>항구분</th>
							<td><span id="lbGisAssetsPrtAtCodeNm"></span>(<span id="lbGisAssetsPrtAtCode"></span>)</td>
							<th>자산코드</th>
							<td><span id="lbGisAssetsTotCd"></span></td>
							<th>자산명</th>
							<td><span id="lbGisAssetsNm"></span></td>
						</tr>
						<tr>
							<th>소재지</th>
							<td colspan="3'"><span id="lbGisAssetsLocplc"></span>&nbsp;<span id="lbGisAssetsLnmDisplay"></span></td>
						</tr>
					</tbody>
				</table>
				<table id="olnlpMngtList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="addBtn">추가</button>
					<button id="deleteBtn">삭제</button>
					<button id="saveBtn">저장</button>
				</div>
				<form id="olnlpManageVO">
				<table class="editForm">
					<tbody>
						<tr>
							<th>적용시작일자</th>
							<td><input id="beginDt" type="text" size="20" maxlength="10" class="emdcal" title="시작일자" /></td>
							<th>적용종료일자</th>
							<td><input id="endDt" type="text" size="20" maxlength="10" class="emdcal" title="종료일자" /></td>
						</tr>
						<tr>
							<th>공시지가</th>
							<td colspan="2">
								<input id="olnlp" type="text" size="40" title="공시지가 금액" />
							</td>
						</tr>
					</tbody>
				</table>
				</form>
			</div>
		</div>
	</div>
</div>