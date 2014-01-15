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
  *  2014.01.09  권옥경          최초 생성
  *
  * author 권옥경
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

GamMenuMngModule.prototype = new EmdModule();

// 페이지가 호출 되었을때 호출 되는 함수
GamMenuMngModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#menuMngList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/gamMenuManageSelect.do" />',
		dataType: 'json',
		colModel : [
					{display:'선택',				name:'UPDT_DT',			width:96,		sortable:false,		align:'center'},
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
		height: '250'
	});

	this.$("#menuMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#menuMngListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		if(row!=null) {
			module.$('#cmd').val('modify');	 							// 더블클릭한 아이템을 수정한다
			module.$('#menuNo').val(row['menuNo']);						// 메뉴No
			module.$('#menuOrdr').val(row['menuOrdr']);					// 메뉴순서
			module.$('#menuNm').val(row['menuNm']);						// 메뉴명
			module.$('#upperMenuId').val(row['upperMenuId']);			// 상위메뉴No					
			module.$('#progrmFileNm').val(row['progrmFileNm']);			// 파일명				
			module.$('#relateImageNm').val(row['relateImageNm']);		// 관련이미지명
			module.$('#relateImagePath').val(row['relateImagePath']);	// 관련이미지경로
			module.$('#menuDc').val(row['menuDc']);						// 메뉴설명
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
			var searchOpt=this.makeFormArgs('#menuMngForm');
		 	this.$('#menuMngList').flexOptions({params:searchOpt}).flexReload(); 
		break;

		// 목록
		case "listBtn":
			this.$("#menuMngListTab").tabs("option", {active: 0}); 
		break;
		
		// 추가
		case "addBtn":
			this.$("#menuMngListTab").tabs("option", {active: 1});
			this.$("#menuManageVO :input").val("");
			this.$("#cmd").val("insert");
		break;
			
		// 저장
		case "saveBtn":
		 	var inputVO=this.makeFormArgs("#menuManageVO");
			if(this.$("#cmd").val() == "insert") {
			 	this.doAction('<c:url value="/cmmn/gamMenuListInsert.do" />', inputVO, function(result) {
			 		if(result.resultCode == 0){
			 			this.$("#menuMngListTab").tabs("option", {active: 0}); 
			 			this.$("#menuManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
			else {
			 	this.doAction('<c:url value="/cmmn/gamMenuListUpdt.do" />', inputVO, function(result) {
			 		if(result.resultCode == 0){
			 			this.$("#menuMngListTab").tabs("option", {active: 0}); 
			 			this.$("#menuManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
		
		// 삭제
		case "deleteBtn":
			if(confirm("삭제하시겠습니까?")){
				var inputVO=this.makeFormArgs("#menuManageVO");
			 	this.doAction('<c:url value="/cmmn/gamMenuListDelete.do" />', inputVO, function(result) {
			 		if(result.resultCode == 0){
			 			this.$("#menuMngListTab").tabs("option", {active: 0}); 
			 			this.$("#menuManageVO :input").val("");
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
	case 'tabs1':
		break;
	case 'tabs2':
		var row = this.$('#menuMngList').selectedRows();
		if(row.length == 0){
			this.$('#cmd').val('insert');
		}else{
			this.$('#cmd').val('modify');
			this.$('#menuNo').val(row['menuNo']);						// 메뉴No
			this.$('#menuOrdr').val(row['menuOrdr']);					// 메뉴순서
			this.$('#menuNm').val(row['menuNm']);						// 메뉴명
			this.$('#upperMenuId').val(row['upperMenuId']);				// 상위메뉴No					
			this.$('#progrmFileNm').val(row['progrmFileNm']);			// 파일명				
			this.$('#relateImageNm').val(row['relateImageNm']);			// 관련이미지명
			this.$('#relateImagePath').val(row['relateImagePath']);		// 관련이미지경로
			this.$('#menuDc').val(row['menuDc']);						// 메뉴설명
		}
		break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamMenuMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<div class="emdPanel">
		<div class="viewStack">
			<form id="menuMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>메뉴 명</th>
							<td><input name="searchKeyword" id="searchKeyword" type="text" size="80" value="${searchVO.searchKeyword }"  maxlength="60" title="검색조건" /></td>
						</tr>
					</tbody>
				</table>
				<div class="emdTabPage">
					<div class="emdControlPanel">
						<button id="searchBtn">조회</button>
					</div>
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
			<div id="tabs1" class="emdTabPage">
				<table id="menuMngList" style="display:none"></table>
				<div class="emdControlPanel"><button id="addBtn">추가</button></div>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: scroll;">
				<form id="menuManageVO">
					<input type="hidden" id="cmd"/>
					<table class="tableForm">
						<colgroup>
							<col width="30%" />
							<col />
							<col width="30%" />
							<col />
						</colgroup>
						<tr>
							<th><span class="label">메뉴No</span></th>
							<td><input type="text" size="25" id="menuNo"/></td>
							<th><span class="label">메뉴순서</span></th>
							<td><input type="text" size="25" id="menuOrdr"/></td>
						</tr>
						<tr>
							<th><span class="label">메뉴명</span></th>
							<td><input type="text" size="25" id="menuNm"/></td>
							<th><span class="label">상위메뉴No</span></th>
							<td><input type="text" size="25" id="upperMenuId"/></td>
						</tr>
						<tr>
							<th><span class="label">파일명</span></th>
							<td colspan="3"><input type="text" size="75" id="progrmFileNm"/></td>
						</tr>
						<tr>
							<th><span class="label">관련이미지명</span></th>
							<td><input type="text" size="25" id="relateImageNm"/></td>
							<th><span class="label">관련이미지경로</span></th>
							<td><input type="text" size="25" id="relateImagePath"/></td>
						</tr>
						<tr>
							<th><span class="label">메뉴설명</span></th>
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