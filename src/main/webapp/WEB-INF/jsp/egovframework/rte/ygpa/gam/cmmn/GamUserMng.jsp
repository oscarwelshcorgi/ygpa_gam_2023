<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamUserMng.jsp
  * @Description : 사용자관리
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
function GamUserMngListModule() {}

GamUserMngListModule.prototype = new EmdModule();

// 페이지가 호출 되었을때 호출 되는 함수
GamUserMngListModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#userMngList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/gamUserManage.do" />',
		dataType: 'json',
		colModel : [
					{display:'No', 			name:'rn',				width:40, 	sortable:false,		align:'center'},
					{display:'항코드', 		name:'PRT_AT_CODE',		width:60, 	sortable:false,		align:'center'},
					{display:'아이디', 		name:'userId',			width:100, 	sortable:false,		align:'center'},
					{display:'사용자이름', 	name:'userNm',			width:100, 	sortable:false,		align:'center'},
					{display:'사용자이메일', 	name:'emailAdres',		width:160, 	sortable:false,		align:'center'},
					{display:'전화번호', 	name:'middleTelno',		width:120, 	sortable:false,		align:'center'},
					{display:'등록일', 		name:'sbscrbDe',		width:80, 	sortable:false,		align:'center'},
					{display:'가입상태',		name:'sttus',			width:96,	sortable:false,		align:'center'}
					],
		usepager: true,
		useRp: true,
		rp: 20,
		showTableToggleBtn: false,
		height: '300'
	});
	
	this.$("#userMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#userMngListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		if(row!=null) {
			module.$('#cmd').val('modify');	 							// 더블클릭한 아이템을 수정한다
			/*module.$('#menuNo').val(row['menuNo']);					// 메뉴No
			module.$('#menuOrdr').val(row['menuOrdr']);					// 메뉴순서
			module.$('#menuNm').val(row['menuNm']);						// 메뉴명
			module.$('#upperMenuId').val(row['upperMenuId']);			// 상위메뉴No					
			module.$('#progrmFileNm').val(row['progrmFileNm']);			// 파일명				
			module.$('#relateImageNm').val(row['relateImageNm']);		// 관련이미지명
			module.$('#relateImagePath').val(row['relateImagePath']);	// 관련이미지경로
			module.$('#menuDc').val(row['menuDc']);						// 메뉴설명
			*/
			
			module.doAction('<c:url value="/cmmn/gamMenuListInsert.do" />', inputVO, function(result) {
		 		alert(result);
		 		console.log("result : "+result);
				/*if(result.resultCode == 0){
		 			this.$("#menuMngListTab").tabs("option", {active: 0}); 
		 			this.$("#menuManageVO :input").val("");
		 		}
		 		alert(result.resultMsg);
		 		*/
		 	});
			
			throw 0;
		}
	});
};


/**
 * 정의 된 버튼 클릭 시
 */
 GamUserMngListModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case 'searchBtn':
			var searchOpt=this.makeFormArgs('#userMngForm');
		 	this.$('#userMngList').flexOptions({params:searchOpt}).flexReload(); 
			break;
		
		// 신규저장
		case 'insertBtn':
			//location.href = "<c:url value='/cmmn/gamAuthorGrpMng.do'/>"; 
			break;
		
		// 삭제
		case 'deleteBtn':
			var searchOpt=this.makeFormArgs('#searchGisAssetCode');
		 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
			break;
	}
};


/**
 * 탭 변경시 실행 이벤트
 */
 GamUserMngListModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case 'tabs1':
		break;
	case 'tabs2':
		var row = this.$('#userMngList').selectedRows();
		if(row.length == 0){
			this.$('#cmd').val('insert');
		}else{
			this.$('#cmd').val('modify');
			
			this.doAction('<c:url value="/cmmn/gamMenuListInsert.do" />', inputVO, function(result) {
		 		alert(result);
		 		console.log("result : "+result);
				/*if(result.resultCode == 0){
		 			this.$("#menuMngListTab").tabs("option", {active: 0}); 
		 			this.$("#menuManageVO :input").val("");
		 		}
		 		alert(result.resultMsg);
		 		*/
		 	});
			/*this.$('#menuNo').val(row['menuNo']);						// 메뉴No
			this.$('#menuOrdr').val(row['menuOrdr']);					// 메뉴순서
			this.$('#menuNm').val(row['menuNm']);						// 메뉴명
			this.$('#upperMenuId').val(row['upperMenuId']);				// 상위메뉴No					
			this.$('#progrmFileNm').val(row['progrmFileNm']);			// 파일명				
			this.$('#relateImageNm').val(row['relateImageNm']);			// 관련이미지명
			this.$('#relateImagePath').val(row['relateImagePath']);		// 관련이미지경로
			this.$('#menuDc').val(row['menuDc']);						// 메뉴설명
			*/
		}
		break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamUserMngListModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="userMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<td>
				                <div>
					                <label for="sbscrbSttus" >
						                <select name="sbscrbSttus" id="sbscrbSttus" title="검색조건선택1">
						                    <option value="0" <c:if test="${empty userSearchVO.sbscrbSttus || userSearchVO.sbscrbSttus == '0'}">selected="selected"</c:if> >상태(전체)</option>
						                    <option value="A" <c:if test="${userSearchVO.sbscrbSttus == 'A'}">selected="selected"</c:if> >가입신청</option>
						                    <option value="D" <c:if test="${userSearchVO.sbscrbSttus == 'D'}">selected="selected"</c:if> >삭제</option>
						                    <option value="P" <c:if test="${userSearchVO.sbscrbSttus == 'P'}">selected="selected"</c:if> >승인</option>
						                </select>
						                <select name="searchCondition" id="searchCondition" title="검색조건선택2">
						                    <option value="0" <c:if test="${userSearchVO.searchCondition == '0'}">selected="selected"</c:if> >ID</option>
						                    <option value="1" <c:if test="${empty userSearchVO.searchCondition || userSearchVO.searchCondition == '1'}">selected="selected"</c:if> >Name</option>
						                </select>
						                <input name="searchKeyword" type="text" value="<c:out value="${userSearchVO.searchKeyword}"/>" title="검색단어입력" />
					                </label>
				                </div>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="emdTabPage">
					<div class="emdControlPanel">
						<button id="searchBtn">검색</button>
						<button id="deleteBtn">삭제</button>
						<button id="listBtn">목록</button>
						<a href="#" data-role="LoadModule" data-url="<c:url value='/cmmn/gamUserInsertView.do'/>"><button id="insertBtn">등록</button></a>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="emdPanel">
		<div id="userMngListTab" class="emdTabPanel" data-onchange="onTabChange">
		<ul>
			<li><a href="#tabs1" class="emdTab">사용자목록</a></li>
			<li><a href="#tabs2" class="emdTab">사용자상세</a></li>
		</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="userMngList" style="display:none"></table>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: scroll;">
				<form id="userManageVO">
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