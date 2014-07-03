<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamMenuMng.jsp
  * @Description : 메뉴 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.09  kok          최초 생성
  *
  * author kok
  * since 2014.01.09
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamMenuMng" staticJavascript="false" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamMenuMngModule() {}

GamMenuMngModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamMenuMngModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#menuMngList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/gamMenuManageSelect.do" />',
		dataType: 'json',
		colModel : [
					{display:'메뉴ID', 		name:'menuNo',			width:58, 		sortable:false,		align:'center'},
					{display:'메뉴한글명', 		name:'menuNm',			width:130, 		sortable:false,		align:'left'},
					{display:'프로그램파일명', 	name:'progrmFileNm',	width:200, 		sortable:false,		align:'left'},
					{display:'메뉴설명',		name:'menuDc',			width:130,		sortable:false,		align:'left'},
					{display:'메뉴순서',		name:'menuOrdr',		width:50,		sortable:false,		align:'center'},
					{display:'관련이미지경로',	name:'relateImagePath',	width:95,		sortable:false,		align:'left'},
					{display:'관련이미지명',	name:'relateImageNm',	width:90,		sortable:false,		align:'left'},
					{display:'상위메뉴ID',		name:'upperMenuId',		width:70,		sortable:false,		align:'center'}
					],
		height: 'auto'
	});

	this.$("#menuMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#menuMngListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		if(row != null) {

			//module.$("#menuNo").attr("disabled","disabled");
			//module.$("#upperMenuId").attr("disabled","disabled");

			module.$("#cmd").val("modify");	 							// 더블클릭한 아이템을 수정한다
			module.$("#beforeMenuNo").val(row["menuNo"]);						// 메뉴No
			module.$("#menuNo").val(row["menuNo"]);						// 메뉴No
			module.$("#menuOrdr").val(row["menuOrdr"]);					// 메뉴순서
			module.$("#menuNm").val(row["menuNm"]);						// 메뉴명
			module.$("#upperMenuId").val(row["upperMenuId"]);			// 상위메뉴No
			module.$("#progrmFileNm").val(row["progrmFileNm"]);			// 파일명
			module.$("#relateImageNm").val(row["relateImageNm"]);		// 관련이미지명
			module.$("#relateImagePath").val(row["relateImagePath"]);	// 관련이미지경로
			module.$("#menuDc").val(row["menuDc"]);						// 메뉴설명
			module.$("#progrmKoreanNm").val(row["progrmKoreanNm"]);
			

		}
	});
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamMenuMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			var searchOpt=this.makeFormArgs("#menuMngForm");
			this.$("#menuMngListTab").tabs("option", {active: 0});
		 	this.$("#menuMngList").flexOptions({params:searchOpt}).flexReload();
		break;

		// 목록
		case "listBtn":
			this.$("#menuMngListTab").tabs("option", {active: 0});
		break;

		// 추가
		case "addBtn":
			//this.$("#menuNo").removeAttr("disabled");
			//this.$("#upperMenuId").removeAttr("disabled");
			this.$("#menuMngListTab").tabs("option", {active: 1});
			this.$("#menuManageVO :input").val("");
			this.$('#beforeMenuNo').val("-1");
			this.$("#cmd").val("insert");
		break;

		// 프로그램목록조회 팝업
		case "popupBtn":
			this.doExecuteDialog('selectProgramPopList', '프로그램목록조회', '<c:url value="/cmmn/popup/gamPopupProgramView.do"/>', {progrmFileNm: this.$("#progrmFileNm").val()});
		break;

		// 저장
		case "saveBtn":

			if(!validateGamMenuMng(this.$("#menuManageVO")[0])) return;

		 	var inputVO = this.makeFormArgs("#menuManageVO");
			if(this.$("#cmd").val() == "insert") {
			 	this.doAction('<c:url value="/cmmn/gamMenuListInsert.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0") {
						var searchOpt = module.makeFormArgs("#menuMngForm");
						module.$("#menuMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#menuMngListTab").tabs("option", {active: 0});
						module.$("#menuManageVO :input").val("");
			 		}
					alert(result.resultMsg);
			 	});
			}
			else {
			 	this.doAction('<c:url value="/cmmn/gamMenuListUpdt.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0") {
						var searchOpt = module.makeFormArgs("#menuMngForm");
						module.$("#menuMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#menuMngListTab").tabs("option", {active: 0});
						module.$("#menuManageVO :input").val("");
			 		}
					alert(result.resultMsg);
			 	});
			}
		break;

		// 삭제
		case "deleteBtn":
			if(confirm("삭제하시겠습니까?")){
				var inputVO = this.makeFormArgs("#menuManageVO");
			 	this.doAction('<c:url value="/cmmn/gamMenuListDelete.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0") {
						var searchOpt = module.makeFormArgs("#menuMngForm");
						module.$("#menuMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#menuMngListTab").tabs("option", {active: 0});
						module.$("#menuManageVO :input").val("");
			 		}
					alert(result.resultMsg);
			 	});
			}
		break;
	}
};


/**
 * 탭 변경시 실행 이벤트
 */
GamMenuMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {

		case "tabs1":
			break;

		case "tabs2":
			var row = this.$("#menuMngList").selectedRows();
			if(row.length == 0){
				this.$("#cmd").val("insert");
			}else{
				this.$("#cmd").val("modify");
				this.$("#menuNo").val(row["menuNo"]);						// 메뉴No
				this.$("#menuOrdr").val(row["menuOrdr"]);					// 메뉴순서
				this.$("#menuNm").val(row["menuNm"]);						// 메뉴명
				this.$("#upperMenuId").val(row["upperMenuId"]);				// 상위메뉴No
				this.$("#progrmFileNm").val(row["progrmFileNm"]);			// 파일명
				this.$("#relateImageNm").val(row["relateImageNm"]);			// 관련이미지명
				this.$("#relateImagePath").val(row["relateImagePath"]);		// 관련이미지경로
				this.$("#menuDc").val(row["menuDc"]);						// 메뉴설명
			}
		break;
	}
};

GamMenuMngModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){
		case "selectProgramPopList":
			this.$("#progrmFileNm").val(value.progrmFileNm);
			this.$("#progrmKoreanNm").val(value.progrmKoreanNm);
		break;

		default:
			alert('알수없는 팝업 이벤트가 호출 되었습니다.');
			
		break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamMenuMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<div class="emdPanel">
		<div class="viewStack">
			<form id="menuMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>메뉴 명</th>
							<td>&nbsp;<input name="searchKeyword" id="searchKeyword" type="text" size="80" maxlength="60" title="검색조건" /></td>
							<td><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="menuMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">메뉴목록</a></li>
				<li><a href="#tabs2" class="emdTab">메뉴상세</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="menuMngList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="addBtn">추가</button>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="menuManageVO">
					<input type="hidden" id="cmd"/>
					<input type="hidden" id="beforeMenuNo"/>
					<table class="searchPanel editForm">
						<colgroup>
							<col width="20%" />
							<col />
							<col width="20%" />
							<col />
						</colgroup>
						<tr>
							<th width="20%" height="23" class="required_text">메뉴No</th>
							<td><input type="text" size="51" id="menuNo" maxlength="20" data-required="true"/></td>
							<th width="20%" height="23" class="required_text">메뉴순서</th>
							<td><input type="text" size="51" id="menuOrdr" maxlength="5" data-required="true"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">메뉴명</th>
							<td><input type="text" size="51" id="menuNm" maxlength="60" data-required="true"/></td>
							<th width="20%" height="23" class="required_text">상위메뉴No</th>
							<td><input type="text" size="51" id="upperMenuId" maxlength="20" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">파일명</th>
							<td colspan="3">
								<input type="text" size="48" id="progrmFileNm" maxlength="40" disabled="disabled" data-required="true"/>&nbsp;&nbsp;
								<input type="text" size="48" id="progrmKoreanNm" maxlength="40" disabled="disabled" data-required="true"/>&nbsp;&nbsp;
								<button id="popupBtn">프로그램파일명 검색</button>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">관련이미지명</th>
							<td><input type="text" size="51" id="relateImageNm" maxlength="60" /></td>
							<th width="20%" height="23" class="required_text">관련이미지경로</th>
							<td><input type="text" size="51" id="relateImagePath" maxlength="100" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">메뉴설명</th>
							<td colspan="3"><textarea cols="98" rows="10" id="menuDc" maxlength="250"></textarea></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
<!-- 					<button id="listBtn">목록</button> -->
					<button id="saveBtn">저장</button>
					<button id="deleteBtn">삭제</button>
				</div>
			</div>
		</div>
	</div>
</div>