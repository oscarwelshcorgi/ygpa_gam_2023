<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
					{display:'No', 			name:'rn',				width:30, 	sortable:false,		align:'center'},
					{display:'사용자아이디',	name:'uniqId',			width:0, 	sortable:false,		align:'center'},
					/*{display:'항코드', 		name:'PRT_AT_CODE',		width:60, 	sortable:false,		align:'center'},*/
					{display:'아이디', 		name:'userId',			width:100, 	sortable:false,		align:'center'},
					{display:'사용자이름', 	name:'userNm',			width:80, 	sortable:false,		align:'center'},
					{display:'사용자이메일', 	name:'emailAdres',		width:200, 	sortable:false,		align:'center'},
					{display:'전화번호', 	name:'middleTelno',		width:100, 	sortable:false,		align:'center'},
					{display:'등록일', 		name:'sbscrbDe',		width:80, 	sortable:false,		align:'center'},
					{display:'가입상태',		name:'sttus',			width:120,	sortable:false,		align:'center'}
					],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: '260'
	});

	this.$("#userMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#userMngListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		//module.$("#uniqId").val(row["uniqId"]);
		//var inputVO = module.makeFormArgs("#seleteManageVO");
		var inputVO = {uniqId: row["uniqId"]};
		module.doAction('<c:url value="/cmmn/gamUserSelectUpdtView.do" />', inputVO, function(module, result) {
			module.$("#emplyrId").val(result.userManageVO.emplyrId);
			module.$("#emplyrNm").val(result.userManageVO.emplyrNm);
			//alert(result.userManageVO);
			//alert(result.userManageVO);
	 	});
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

		// 추가
		case 'addBtn':
			this.$("#userMngListTab").tabs("option", {active: 1});
			this.$("#userManageVO :input").val("");
			this.$("#cmd").val("insert");
		break;

		// 목록
		case "listBtn":
			this.$("#userMngListTab").tabs("option", {active: 0});
			this.$("#userManageVO :input").val("");
			this.$("#cmd").val("insert");
		break;

		// 취소
		case "cancelBtn":
			this.$("#userManageVO :input").val("");
			this.$("#cmd").val("insert");
		break;

		// 저장
		case "saveBtn":
			var inputVO=this.makeFormArgs("#userManageVO");
			if(this.$("#cmd").val() == "insert") {
				this.$("#zip").val(this.$("#zip_view").val());
			 	this.doAction('<c:url value="/cmmn/gamUserInsert.do" />', inputVO, function(result) {
			 		if(result.resultCode == 0){
			 			this.$("#userMngListTab").tabs("option", {active: 0});
			 			this.$("#userManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
			else {
			 	this.doAction('<c:url value="/cmmn/gamUserSelectUpdt.do" />', inputVO, function(result) {
			 		if(result.resultCode == 0){
			 			this.$("#userMngListTab").tabs("option", {active: 0});
			 			this.$("#userManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
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
<form id="seleteManageVO">
<input type="hidden" id="uniqId" name="uniqId" />
</form>
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
			</form>
			<div class="emdControlPanel">
				<button id="searchBtn">검색</button>
				<button id="addBtn">추가</button>
			</div>
		</div>
	</div>

	<div class="emdPanel" style="overflow: auto;">
		<div id="userMngListTab" class="emdTabPanel" data-onchange="onTabChange">
		<ul>
			<li><a href="#tabs1" class="emdTab">사용자목록</a></li>
			<li><a href="#tabs2" class="emdTab">사용자상세</a></li>
		</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="userMngList" style="display:none"></table>
			</div>
			<div id="tabs2" class="emdTabPage" style="height:330px; overflow: scroll;">
				<form id="userManageVO">
					<input type="hidden" id="cmd"/>
					<input type="hidden" name="zip" id="zip" />
					<table class="searchPanel">
			            <tr>
			                <th width="20%" height="23" class="required_text">사용자아이디<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
			                <td width="80%" >
			                    <input id="emplyrId" title="사용자아이디" size="20" maxlength="20" />
			                    <a href="#LINK" onclick="fnIdCheck();">
			                        <img src="<c:url value='/images/egovframework/com/cmm/icon/search.gif' />" alt="중복아이디 검색" />(중복아이디 검색)
			                    </a>
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >
			                    사용자이름<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />
			                </th>
			                <td width="80%" >
			                    <input name="emplyrNm" id="emplyrNm" title="사용자이름" type="text" size="20" value="" maxlength="60" />
			                </td>
			            </tr>

			            <tr>
			                <th width="20%" height="23" class="required_text"  >
			                    비밀번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />
			                </th>
			                <td width="80%" >
			                    <input type="password" id="password" title="비밀번호" size="20" maxlength="20" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >
			                    비밀번호확인<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />
			                </th>
			                <td width="80%" >
			                    <input name="password2" id="password2" title="비밀번호확인" type="password" size="20" maxlength="20" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >
			                    비밀번호힌트<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />
			                </th>
			                <td width="80%" >
			                    <select id="passwordHint" title="비밀번호힌트">
			                    	<c:forEach varStatus="var" items="${passwordHint_result}" var="result">
				                    	<option value="${result.code}" label="${result.codeNm}"/>
			                    	</c:forEach>
			                    </select>
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text" >
			                    비밀번호정답<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />
			                </th>
			                <td width="80%" >
			                    <input id="passwordCnsr" title="비밀번호정답"  size="50" maxlength="100" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >소속기관코드&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <select id="insttCode" title="소속기관코드">
				                    <c:forEach varStatus="var" items="${insttCode_result}" var="result">
				                    	<option value="${result.code}" label="${result.codeNm}"/>
			                    	</c:forEach>
			                    </select>
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >조직아이디&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <select id="orgnztId" name="orgnztId" title="조직아이디">
			                   		<c:forEach varStatus="var" items="${orgnztId_result}" var="result">
				                    	<option value="${result.code}" label="${result.codeNm}"/>
			                    	</c:forEach>
			                    </select>
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >직위명&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <input id="ofcpsNm" title="직위명" size="20" maxlength="50" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >사번&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <input id="emplNo" title="사번" size="20" maxlength="20" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >성별구분코드&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <select id="sexdstnCode" title="성별구분코드">
			                       	<c:forEach varStatus="var" items="${sexdstnCode_result}" var="result">
				                    	<option value="${result.code}" label="${result.codeNm}"/>
			                    	</c:forEach>
			                    </select>
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >생일&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <input id="brth" title="생일" size="20" maxlength="8" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >
			                    집전화번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />
			                </th>
			                <td width="80%" >
			                    <input id="areaNo" title="areaNo" size="4" maxlength="4" />
			                    - <input title="homemiddleTelno" id="homemiddleTelno" size="4" maxlength="4" />
			                    - <input title="homeendTelno" id="homeendTelno" size="4" maxlength="4" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >사무실전화번호&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <input id="offmTelno" title="사무실전화번호" size="20" maxlength="15" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >팩스번호&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <input id="fxnum" title="팩스번호" size="20" maxlength="15" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">핸드폰번호&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <input id="moblphonNo" title="핸드폰번호" size="20" maxlength="15" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">
			                    이메일주소<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />
			                </th>
			                <td width="80%">
			                    <input id="emailAdres" title="이메일주소" size="20" maxlength="50" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">
			                    우편번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />
			                </th>
			                <td width="80%" >
			                    <input name="zip_view" id="zip_view" type="text" title="우편번호" size="20" value="<c:out value='${userManageVO.zip}'/>"  maxlength="8" />
		                        <a href="#LINK" onclick="javascript:fn_egov_ZipSearch(document.userManageVO, document.userManageVO.zip, document.userManageVO.zip_view, document.userManageVO.homeadres);">
		                            <img src="<c:url value='/images/egovframework/com/cmm/icon/search.gif' />" alt="" />(우편번호 검색)
		                        </a>
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >
			                    주소<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />
			                </th>
			                <td width="80%" >
			                    <input id="homeadres" title="주소" size="40" maxlength="100" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >상세주소&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <input id="detailAdres" title="상세주소" size="40" maxlength="50" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >
			                    그룹아이디<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />
			                </th>
			                <td width="80%" >
			                    <select id="groupId" title="그룹아이디">
			                        <c:forEach varStatus="var" items="${groupId_result}" var="result">
				                    	<option value="${result.code}" label="${result.codeNm}"/>
			                    	</c:forEach>
			                    </select>
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >
			                    사용자상태코드<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />
			                </th>
			                <td width="80%" >
			                    <select id="emplyrSttusCode" title="사용자상태코드">
			                        <c:forEach varStatus="var" items="${emplyrSttusCode_result}" var="result">
				                    	<option value="${result.code}" label="${result.codeNm}"/>
			                    	</c:forEach>
			                    </select>
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >사용자DN&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <input id="subDn" title="사용자DN" size="40" maxlength="100" />
			                    &nbsp;<a href="#LINK" onclick="fn_egov_inqire_cert()" style="selector-dummy: expression(this.hideFocus=false);"><img src="<c:url value='/images/egovframework/com/cmm/icon/search.gif' />"
				     			width="15" height="15" alt="search"/></a>
			                </td>
			            </tr>
					</table>
					<div class="emdControlPanel">
						<button id="saveBtn">등록</button>
						<button id="listBtn">목록</button>
						<button id="cancelBtn">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>