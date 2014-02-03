<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamMenuMngModule() {}

GamMenuMngModule.prototype = new EmdModule(840, 540);

// 페이지가 호출 되었을때 호출 되는 함수
GamMenuMngModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#menuMngList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/gamMenuManageSelect.do" />',
		dataType: 'json',
		colModel : [
					{display:'메뉴ID', 			name:'menuNo',			width:80, 		sortable:false,		align:'center'},
					{display:'메뉴한글명', 		name:'menuNm',			width:80, 		sortable:false,		align:'center'},
					{display:'프로그램파일명', 	name:'progrmFileNm',	width:160, 		sortable:false,		align:'center'},
					{display:'메뉴설명',			name:'menuDc',			width:120,		sortable:false,		align:'center'},
					{display:'메뉴순서',			name:'menuOrdr',		width:0,		sortable:false,		align:'center'},
					{display:'관련이미지경로',	name:'relateImagePath',	width:0,		sortable:false,		align:'center'},
					{display:'관련이미지명',		name:'relateImageNm',	width:0,		sortable:false,		align:'center'},
					{display:'상위메뉴ID',		name:'upperMenuId',		width:80,		sortable:false,		align:'center'}
					],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: '280'
	});

	this.$("#menuMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#menuMngListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		if(row != null) {
			
			module.$("#menuNo").attr("disabled","disabled");
			module.$("#upperMenuId").attr("disabled","disabled");
			module.$("#progrmFileNm").attr("disabled","disabled");
			
			module.$("#cmd").val("modify");	 							// 더블클릭한 아이템을 수정한다
			module.$("#menuNo").val(row["menuNo"]);						// 메뉴No
			module.$("#menuOrdr").val(row["menuOrdr"]);					// 메뉴순서
			module.$("#menuNm").val(row["menuNm"]);						// 메뉴명
			module.$("#upperMenuId").val(row["upperMenuId"]);			// 상위메뉴No					
			module.$("#progrmFileNm").val(row["progrmFileNm"]);			// 파일명				
			module.$("#relateImageNm").val(row["relateImageNm"]);		// 관련이미지명
			module.$("#relateImagePath").val(row["relateImagePath"]);	// 관련이미지경로
			module.$("#menuDc").val(row["menuDc"]);						// 메뉴설명
			throw 0;
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
		 	this.$("#menuMngList").flexOptions({params:searchOpt}).flexReload(); 
		break;

		// 목록
		case "listBtn":
			this.$("#menuMngListTab").tabs("option", {active: 0}); 
		break;
		
		// 추가
		case "addBtn":
			this.$("#menuNo").removeAttr("disabled");
			this.$("#upperMenuId").removeAttr("disabled");
			this.$("#progrmFileNm").removeAttr("disabled");
			this.$("#menuMngListTab").tabs("option", {active: 1});
			this.$("#menuManageVO :input").val("");
			this.$("#cmd").val("insert");
		break;
			
		// 프로그램목록조회 팝업
		case "popupBtn":
			this.doExecuteDialog('selectProgramPopList', '프로그램목록조회', '<c:url value="/cmmn/popup/gamPopupProgramView.do"/>', {progrmFileNm: this.$("#progrmFileNm").val()});
		break;
		
		// 저장
		case "saveBtn":
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
			this.$("#progrmFileNm").val(value);
		break;
	
		default:
			alert('알수없는 팝업 이벤트가 호출 되었습니다.');
			throw 0;
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
							<td><input name="searchKeyword" id="searchKeyword" type="text" size="80" value="<c:out value="${searchVO.searchKeyword}" />"  maxlength="60" title="검색조건" /></td>
						</tr>
					</tbody>
				</table>
				<div class="emdControlPanel">
					<button id="searchBtn">조회</button>
					<button id="addBtn">추가</button>
				</div>
			</form>
		</div>
	</div>

	<div class="emdPanel">
		<div id="menuMngListTab" class="emdTabPanel" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">메뉴목록</a></li>
				<li><a href="#tabs2" class="emdTab">메뉴상세</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: auto;">
				<table id="menuMngList" style="display:none"></table>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: scroll;">
				<form id="menuManageVO">
					<input type="hidden" id="cmd"/>
					<table class="searchPanel">
						<colgroup>
							<col width="30%" />
							<col />
							<col width="30%" />
							<col />
						</colgroup>
						<tr>
							<th width="20%" height="23" class="required_text">메뉴No<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="25" id="menuNo" /></td>
							<th width="20%" height="23" class="required_text">메뉴순서<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="25" id="menuOrdr"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">메뉴명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="25" id="menuNm"/></td>
							<th width="20%" height="23" class="required_text">상위메뉴No<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="25" id="upperMenuId"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">파일명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td colspan="3">
								<input type="text" size="40" id="progrmFileNm" />&nbsp;&nbsp;
								<button id="popupBtn">프로그램파일명 검색</button>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">관련이미지명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="25" id="relateImageNm"/></td>
							<th width="20%" height="23" class="required_text">관련이미지경로<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="25" id="relateImagePath"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">메뉴설명</th>
							<td colspan="3"><textarea cols="76" rows="10" id="menuDc"></textarea></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="listBtn">목록</button>
					<button id="saveBtn">저장</button>
					<button id="deleteBtn">삭제</button>
				</div>
			</div>
		</div>
	</div>
</div>