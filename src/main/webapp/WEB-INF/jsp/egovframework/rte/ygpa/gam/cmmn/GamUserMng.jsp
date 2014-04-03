<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamUserMng.jsp
  * @Description : 사용자관리
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
<validator:javascript formName="gamUserMng" staticJavascript="false" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamUserMngListModule() {}

GamUserMngListModule.prototype = new EmdModule(900, 540);

// 페이지가 호출 되었을때 호출 되는 함수
GamUserMngListModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#userMngList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/gamUserManage.do" />',
		dataType: "json",
		colModel : [
					{display:"No", 			name:"rn",				width:30, 	sortable:false,		align:"center"},
					{display:"아이디", 		name:"userId",			width:100, 	sortable:false,		align:"center"},
					{display:"사용자이름", 	name:"userNm",			width:80, 	sortable:false,		align:"center"},
					{display:"사용자이메일", 	name:"emailAdres",		width:210, 	sortable:false,		align:"center"},
					{display:"전화번호", 	name:"allTelno",		width:120, 	sortable:false,		align:"center"},
					{display:"등록일", 		name:"sbscrbDe",		width:100, 	sortable:false,		align:"center"},
					{display:"가입상태",		name:"sttus",			width:140,	sortable:false,		align:"center"}
					],
		height: "auto"
	});

	this.$("#userMngList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#userMngListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		module.doAction('<c:url value="/cmmn/gamUserSelectUpdtView.do" />', {uniqId: row["uniqId"]}, function(module, result) {

			module.$("#cmd").val("modify");
			var zipSet = result.userManageVO.zip.substring(0,3) + "-" + result.userManageVO.zip.substring(3); 

			module.$("#uniqId").val(result.userManageVO.uniqId);													// 사용자 고유 ID
			module.$("#emplyrId").val(result.userManageVO.emplyrId);												// 사용자 ID
			module.$("#emplyrNm").val(result.userManageVO.emplyrNm);												// 사용자 이름
			this.$(".displayPassword").show();
//			module.$("#password").val(result.userManageVO.password);												// 비밀번호
//			module.$("#password2").val(result.userManageVO.password);												// 비밀번호 확인
			module.$("#passwordHint").val(result.userManageVO.passwordHint).attr("selected","selected");			// 비밀번호 질문
			module.$("#passwordCnsr").val(result.userManageVO.passwordCnsr);										// 비밀번호 답변
			module.$("#emplNo").val(result.userManageVO.emplNo);													// 사번
			module.$("#sexdstnCode").val(result.userManageVO.sexdstnCode).attr("selected","selected");				// 성별
			module.$("#brth").val(result.userManageVO.brth);														// 생일
			module.$("#areaNo").val(result.userManageVO.areaNo);													// 집전화 국번
			module.$("#homemiddleTelno").val(result.userManageVO.homemiddleTelno);									// 집전화 중간번호
			module.$("#homeendTelno").val(result.userManageVO.homeendTelno);										// 집전화 끝번호
			module.$("#fxnum").val(result.userManageVO.fxnum);														// 팩스번호
			module.$("#homeadres").val(result.userManageVO.homeadres);												// 자택주소
			module.$("#detailAdres").val(result.userManageVO.detailAdres);											// 자택 상세주소
			module.$("#zip").val(zipSet);																			// 자택 우편번호
			module.$("#offmTelno").val(result.userManageVO.offmTelno);												// 사무실 전화번호
			module.$("#moblphonNo").val(result.userManageVO.moblphonNo);											// 핸드폰 번호
			module.$("#emailAdres").val(result.userManageVO.emailAdres);											// 이메일 주소
			module.$("#ofcpsNm").val(result.userManageVO.ofcpsNm);													// 직위명
			module.$("#groupId").val(result.userManageVO.groupId).attr("selected","selected");						// 그룹 ID
			module.$("#orgnztId").val(result.userManageVO.orgnztId).attr("selected","selected");					// 조직 ID
			module.$("#insttCode").val(result.userManageVO.insttCode).attr("selected","selected");					// 소속기관 코드
			module.$("#emplyrSttusCode").val(result.userManageVO.emplyrSttusCode).attr("selected","selected");		// 사용자상태 코드
			module.$("#subDn").val(result.userManageVO.subDn);														// 사용자 DN
			
			module.$("#checkId").val("ok");
	 	});
	});
};


/**
 * 정의 된 버튼 클릭 시
 */
GamUserMngListModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			var searchOpt = this.makeFormArgs("#userMngForm");
		 	this.$("#userMngList").flexOptions({params:searchOpt}).flexReload();
		 	throw 0;
		break;

		// 추가
		case "addBtn":
			this.$("#userMngListTab").tabs("option", {active: 1});
			this.$(".displayPassword").show();
			this.$("#userManageVO :input").val("");
			this.$("#cmd").val("insert");
			this.$("#checkId").val("");
		break;

		// 아이디 입력
		case "inputUserId":
			this.$("#emplyrId").removeAttr("disabled");
			this.$("#emplyrId").focus();
			this.$("#checkId").val("");
		break;
			
		// 중복아이디 검색
		case "checkUserId":
			if(this.$("#emplyrId").val() == ""){
				this.$("#emplyrId").focus();
				alert("아이디를 입력하십시오.");
				return;
			}
			this.doAction('<c:url value="/cmmn/gamIdDplctCnfirm.do" />', {checkId : this.$("#emplyrId").val()}, function(module, result) {
		 		if(result.resultCode == 0){
		 			if(result.usedCnt != "0"){
		 				alert("이미 사용중인 아이디가 존재합니다.");
		 				module.$("#emplyrId").focus();
		 				module.$("#checkId").val("");
		 			}else{
		 				if(confirm("해당 아이디로 사용하시겠습니까?")){
		 					module.$("#emplyrId").val(result.checkId);
		 					module.$("#checkId").val("ok");
		 					module.$("#emplyrId").attr("disabled","disabled");
		 				}else{
		 					module.$("#emplyrId").val("");
		 					module.$("#checkId").val("");
		 					module.$("#emplyrId").focus();
		 				}
		 			}
		 		}
		 	});
		break;

		// 목록
		case "listBtn":
			this.$("#userMngListTab").tabs("option", {active: 0});
			this.$("#userManageVO :input").val("");
			this.$("#cmd").val("insert");
		break;

		// 암호변경 팝업
		case "chgPwBtn":
			this.doExecuteDialog("changePassWordPopup", "업무사용자 암호변경", '<c:url value="/cmmn/popup/gamPopupChgPwView.do"/>', {emplyrId : this.$("#emplyrId").val(), uniqId: this.$("#uniqId").val()});
		break;

		// 취소
		case "cancelBtn":
			this.$("#userManageVO :input").val("");
			this.$("#cmd").val("insert");
		break;

		// 저장
		case "saveBtn":

			if(this.$("#checkId").val() == ""){
				alert("아이디 체크를 해주세요.");
				return;
			}
			this.$("#zip").val(this.$("#zip").val().replace(/\-/g,""));			
			if(!validateGamUserMng(this.$("#userManageVO")[0])) return;
			
			var inputVO = this.makeFormArgs("#userManageVO");
			if(this.$("#cmd").val() == "insert") {
			 	this.doAction('<c:url value="/cmmn/gamUserInsert.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == 0){
			 			module.$("#userMngListTab").tabs("option", {active: 0});
			 			module.$("#userManageVO :input").val("");
			 			var searchOpt = module.makeFormArgs("#userMngForm");
					 	module.$("#userMngList").flexOptions({params:searchOpt}).flexReload();
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/cmmn/gamUserSelectUpdt.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == 0){
			 			module.$("#userMngListTab").tabs("option", {active: 0});
			 			module.$("#userManageVO :input").val("");
			 			var searchOpt = module.makeFormArgs("#userMngForm");
					 	module.$("#userMngList").flexOptions({params:searchOpt}).flexReload();
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;

		// 삭제
		case "deleteBtn":

			if(confirm("삭제하시겠습니까?")){
				this.doAction('<c:url value="/cmmn/gamUserDelete.do" />', {uniqId: this.$("#uniqId").val()}, function(module, result) {
			 		if(result.resultCode == 0){
			 			module.$("#userMngListTab").tabs("option", {active: 0});
			 			module.$("#userManageVO :input").val("");
			 			var searchOpt = module.makeFormArgs("#userMngForm");
					 	module.$("#userMngList").flexOptions({params:searchOpt}).flexReload();
			 		}
			 		alert(result.resultMsg);
			 	});				
			}
		break;
		
		// 우편번호 검색
		case "searchZipBtn":
			this.doExecuteDialog("searcpZipcodePopup", "우편번호조회팝업", '<c:url value="/cmmn/popup/gamPopupSearchZipView.do"/>', {});
		break;
	}
};


/**
 * 탭 변경시 실행 이벤트
 */
 GamUserMngListModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		var row = this.$("#userMngList").selectedRows();
		if(row.length == 0) this.$("#cmd").val("insert");
		else this.$("#cmd").val("modify");
		break;
	}
};


/**
 * 팝업 close 이벤트
 */
 GamUserMngListModule.prototype.onClosePopup = function(popupId, msg, value){
	
	switch(popupId){
		case "searcpZipcodePopup":
			this.$("#homeadres").val(value["address"]);
			this.$("#zip").val(value["zip"]);
		break;
	
		case "changePassWordPopup":
			if(msg != "cancel"){
				alert("변경되었습니다.");	
			}
		break;
	
		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			throw 0;
		break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamUserMngListModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='<c:out value="${windowId}" />' />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="userMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<td>
				                <select id="sbscrbSttus" title="검색조건선택1">
				                    <option value="0" selected="selected">상태(전체)</option>
				                    <option value="A">가입신청</option>
				                    <option value="D">삭제</option>
				                    <option value="P">승인</option>
				                </select>&nbsp;
				                <select id="searchCondition" title="검색조건선택2">
				                    <option value="0">ID</option>
				                    <option value="1">Name</option>
				                </select>&nbsp;
				                <input id="searchKeyword" type="text" title="검색단어입력" />
							</td>
							<td><button id="searchBtn" class="submit">조회</button></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="userMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
		<ul>
			<li><a href="#tabs1" class="emdTab">사용자목록</a></li>
			<li><a href="#tabs2" class="emdTab">사용자상세</a></li>
		</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="userMngList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="addBtn">추가</button>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="userManageVO">
					<input type="hidden" id="cmd"/>
					<input type="hidden" id="checkId"/>
					<input type="hidden" id="uniqId" />
					<table class="searchPanel">
			            <tr>
			                <th width="20%" height="23" class="required_text">사용자아이디</th>
			                <td width="80%" >
			                    <input id="emplyrId" title="사용자아이디" size="20" maxlength="20" />
			                    &nbsp;&nbsp;<button id="checkUserId">중복아이디 검색</button>
			                    &nbsp;&nbsp;<button id="inputUserId">아이디 입력</button>
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >
			                    사용자이름
			                </th>
			                <td width="80%" >
			                    <input id="emplyrNm" title="사용자이름" type="text" size="20" maxlength="60" />
			                </td>
			            </tr>
			            <tr class="displayPassword">
			                <th width="20%" height="23" class="required_text">
			                    비밀번호
			                </th>
			                <td width="80%" >
			                    <input type="password" id="password" title="비밀번호" size="20" maxlength="20" />
			                </td>
			            </tr>
			            <tr class="displayPassword">
			                <th width="20%" height="23" class="required_text"  >
			                    비밀번호확인
			                </th>
			                <td width="80%" >
			                    <input id="password2" title="비밀번호확인" type="password" size="20" maxlength="20" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text"  >
			                    비밀번호힌트
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
			                    비밀번호정답
			                </th>
			                <td width="80%" >
			                    <input id="passwordCnsr" title="비밀번호정답"  size="50" maxlength="100" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">소속기관코드&nbsp;&nbsp;</th>
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
			                    <select id="orgnztId" title="조직아이디">
			                   		<c:forEach varStatus="var" items="${orgnztId_result}" var="result">
				                    	<option value="${result.code}" label="${result.codeNm}"/>
			                    	</c:forEach>
			                    </select>
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">직위명&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <input id="ofcpsNm" title="직위명" size="20" maxlength="50" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">사번&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <input id="emplNo" title="사번" size="20" maxlength="20" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">성별구분코드&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <select id="sexdstnCode" title="성별구분코드">
			                       	<c:forEach varStatus="var" items="${sexdstnCode_result}" var="result">
				                    	<option value="${result.code}" label="${result.codeNm}"/>
			                    	</c:forEach>
			                    </select>
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">생일&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <input id="brth" title="생일" size="20" maxlength="8" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">
			                    집전화번호
			                </th>
			                <td width="80%" >
			                    <input id="areaNo" title="areaNo" size="4" maxlength="4" />
			                    - <input title="homemiddleTelno" id="homemiddleTelno" size="4" maxlength="4" />
			                    - <input title="homeendTelno" id="homeendTelno" size="4" maxlength="4" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">사무실전화번호&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <input id="offmTelno" title="사무실전화번호" size="20" maxlength="15" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">팩스번호&nbsp;&nbsp;</th>
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
			                    이메일주소
			                </th>
			                <td width="80%">
			                    <input id="emailAdres" title="이메일주소" size="20" maxlength="50" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">
			                    우편번호
			                </th>
			                <td width="80%" >
			                    <input id="zip" type="text" title="우편번호" size="20" disabled="disabled"/>&nbsp;&nbsp;<button id="searchZipBtn">우편번호 검색</button>
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">
			                    주소
			                </th>
			                <td width="80%" >
			                    <input id="homeadres" title="주소" size="40" maxlength="100" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">상세주소&nbsp;&nbsp;</th>
			                <td width="80%" >
			                    <input id="detailAdres" title="상세주소" size="40" maxlength="50" />
			                </td>
			            </tr>
			            <tr>
			                <th width="20%" height="23" class="required_text">
			                    그룹아이디
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
			                <th width="20%" height="23" class="required_text">
			                    사용자상태코드
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
			                <th width="20%" height="23" class="required_text">사용자DN&nbsp;&nbsp;</th>
			                <td width="80%" ><input id="subDn" title="사용자DN" size="40" maxlength="100" /></td>
			            </tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="saveBtn">저장</button>
					<button id="listBtn">목록</button>
					<button id="chgPwBtn">암호변경</button>
					<button id="deleteBtn">삭제</button>
					<button id="cancelBtn">취소</button>
				</div>
			</div>
		</div>
	</div>
</div>