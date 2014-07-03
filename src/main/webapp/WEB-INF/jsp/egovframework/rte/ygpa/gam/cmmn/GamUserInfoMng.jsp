<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamUserInfoMng.jsp
  * @Description : 사용자 정보 수정 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2013.10.29  kok          최초 생성
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
function GamUserInfoModule() {}

GamUserInfoModule.prototype = new EmdModule();

// 페이지가 호출 되었을때 호출 되는 함수
GamUserInfoModule.prototype.loadComplete = function() {
	this.doAction('<c:url value="/cmmn/selectGamUserInfo.do" />', {}, function(module, result) {
 		if(result.resultCode == 0){
 			module.makeFormValues('#userManageVO', result.userManageVO);
 		}
 		else {
 			alert('사용자 정보를 불러오지 못했습니다. 로그아웃 이후 다시 로그인 하여 사용해 주시기 바랍니다. 문제가 계속 되면 관리자에게 문의 하십시요.');
 		}
 	});
};
		
/**
 * 정의 된 버튼 클릭 시
 */
GamUserInfoModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
		// 암호변경 팝업
		case "chgPwBtn":
			this.doExecuteDialog("changePassWordPopup", "업무사용자 암호변경", '<c:url value="/cmmn/popup/gamPopupChgPwView.do"/>', {emplyrId : this.$("#emplyrId").val(), uniqId: this.$("#uniqId").val()});
		break;

		// 저장
		case "saveBtn":
			this.$("#zip").val(this.$("#zip").val().replace(/\-/g,""));
			if(!validateGamUserMng(this.$("#userManageVO")[0])) return;
		 	this.doAction('<c:url value="/cmmn/gamUserInfoUpdt.do" />', inputVO, function(module, result) {
		 		if(result.resultCode == 0){
		 		}
		 		alert(result.resultMsg);
		 	});
			break;
		// 우편번호 검색
		case "searchZipBtn":
			this.doExecuteDialog("searcpZipcodePopup", "우편번호조회팝업", '<c:url value="/cmmn/popup/gamPopupSearchZipView.do"/>', {});
		break;
	}
};

GamUserInfoModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
};

GamUserInfoModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#authorGrpMngForm');
	//this.showAlert(searchOpt);
 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
//	this.$('#assetList').flexOptions(searchOpt).flexReload();
};

/**
 * 팝업 close 이벤트
 */
 GamUserInfoModule.prototype.onClosePopup = function(popupId, msg, value){
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
			
		break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamUserInfoModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<div class="emdPanel fillHeight">
				<form id="userManageVO">
					<input type="hidden" id="cmd"/>
					<input type="hidden" id="checkId"/>
					<input type="hidden" id="uniqId" />
					<table class="editForm">
			            <tr>
			                <th width="15%" height="23">사용자 아아디</th>
			                <td colspan="3">
			                    <input id="emplyrId" title="사용자아이디" size="48" maxlength="20" />
			                    &nbsp; &nbsp;<button id="checkUserId">중복아이디 검사</button>
			                    &nbsp; &nbsp;<button id="inputUserId">아이디 입력</button>
			                </td>
			            </tr>
			            <tr>
			                <th width="15%" height="23">사용자이름</th>
			                <td width="35%">
			                    <input id="emplyrNm" title="사용자이름" type="text" size="48" maxlength="60"  data-required="true" />
			                </td>
			                <th width="15%" height="23">집전화번호</th>
			                <td width="35%">
			                    <input id="areaNo" title="areaNo" size="5" maxlength="4" />
			                    - <input title="homemiddleTelno" id="homemiddleTelno" size="5" maxlength="4" />
			                    - <input title="homeendTelno" id="homeendTelno" size="5" maxlength="4" />
			                </td>
			            </tr>
			            <tr id="tdpassword">
			                <th width="15%" height="23">비밀번호</th>
			                <td width="35%" >
			                    <input type="password" id="password" title="비밀번호" size="48" maxlength="20" data-required="true"/>
			                </td>
			                <th width="15%" height="23" class="required_text">비밀번호확인</th>
			                <td width="35%" >
			                    <input id="password2" title="비밀번호확인" type="password" size="48" maxlength="20"  data-required="true" />
			                </td>
			            </tr>
			            <tr>
			                <th width="15%" height="23" class="required_text">사무실전화번호&nbsp;&nbsp;</th>
			                <td width="35%" >
			                    <input id="offmTelno" title="사무실전화번호" size="48" maxlength="15" />
			                </td>
			                <th width="15%" height="23">팩스번호&nbsp;&nbsp;</th>
			                <td width="35%" >
			                    <input id="fxnum" title="팩스번호" size="48" maxlength="15" />
			                </td>
			            </tr>
			            <tr>
			                <th width="15%" height="23">비밀번호힌트</th>
			                <td width="35%" >
			                    <select id="passwordHint" title="비밀번호힌트"  data-required="true">
			                    	<c:forEach varStatus="var" items="${passwordHint_result}" var="result">
				                    	<option value="${result.code}" label="${result.codeNm}"/>
			                    	</c:forEach>
			                    </select>
			                </td>
			                <th width="15%" height="23" class="required_text">핸드폰번호&nbsp;&nbsp;</th>
			                <td width="35%" >
			                    <input id="moblphonNo" title="핸드폰번호" size="48" maxlength="15" />
			                </td>
			            </tr>
			            <tr>
			                <th width="15%" height="23">비밀번호정답</th>
			                <td width="35%" >
			                    <input id="passwordCnsr" title="비밀번호정답" size="48" maxlength="100"  data-required="true" />
			                </td>
			                <th width="15%" height="23">
			                    이메일주소
			                </th>
			                <td width="35%">
			                    <input id="emailAdres" title="이메일주소" size="48" maxlength="50" />
			                </td>
			            </tr>
			            <tr>
			                <th width="15%" height="23" class="required_text">소속기관코드&nbsp;&nbsp;</th>
			                <td width="35%" >
			                    <select id="insttCode" title="소속기관코드"  data-required="true">
				                    <c:forEach varStatus="var" items="${insttCode_result}" var="result">
				                    	<option value="${result.code}" label="${result.codeNm}"/>
			                    	</c:forEach>
			                    </select>
			                </td>
			                <th width="15%" height="23" class="required_text">
			                    우편번호
			                </th>
			                <td width="35%" >
			                    <input id="zip" type="text" title="우편번호" size="27" disabled="disabled"/>&nbsp;&nbsp;
			                    <button id="searchZipBtn">우편번호 검색</button>
			                </td>
			            </tr>
			            <tr>
			                <th width="15%" height="23" class="required_text">조직아이디&nbsp;&nbsp;</th>
			                <td width="35%" >
			                    <select id="orgnztId" title="조직아이디">
			                   		<c:forEach varStatus="var" items="${orgnztId_result}" var="result">
				                    	<option value="${result.code}" label="${result.codeNm}"/>
			                    	</c:forEach>
			                    </select>
			                </td>
			                <th width="15%" height="23" class="required_text">
			                    주소
			                </th>
			                <td width="35%" >
			                    <input id="homeadres" title="주소" size="48" maxlength="100" />
			                </td>
			            </tr>
			            <tr>
			                <th width="15%" height="23" class="required_text">직위명&nbsp;&nbsp;</th>
			                <td width="35%" >
			                    <input id="ofcpsNm" title="직위명" size="48" maxlength="50" />
			                </td>
			                <th width="15%" height="23" class="required_text">상세주소&nbsp;&nbsp;</th>
			                <td width="35%" >
			                    <input id="detailAdres" title="상세주소" size="48" maxlength="50" />
			                </td>
			            </tr>
			            <tr>
			                <th width="15%" height="23" class="required_text">사번&nbsp;&nbsp;</th>
			                <td width="35%" >
			                    <input id="emplNo" title="사번" size="48" maxlength="20" />
			                </td>
			                <th width="15%" height="23" class="required_text">
			                    그룹아이디
			                </th>
			                <td width="35%" >
			                    <select id="groupId" title="그룹아이디" data-required="true">
			                        <c:forEach varStatus="var" items="${groupId_result}" var="result">
				                    	<option value="${result.code}" label="${result.codeNm}"/>
			                    	</c:forEach>
			                    </select>
			                </td>
			            </tr>
			            <tr>
			                <th width="15%" height="23" class="required_text">성별구분코드&nbsp;&nbsp;</th>
			                <td width="35%" >
			                    <select id="sexdstnCode" title="성별구분코드">
			                       	<c:forEach varStatus="var" items="${sexdstnCode_result}" var="result">
				                    	<option value="${result.code}" label="${result.codeNm}"/>
			                    	</c:forEach>
			                    </select>
			                </td>
			                <th width="15%" height="23" class="required_text">
			                    사용자상태코드
			                </th>
			                <td width="35%" >
			                    <select id="emplyrSttusCode" title="사용자상태코드" data-required="true">
			                        <c:forEach varStatus="var" items="${emplyrSttusCode_result}" var="result">
				                    	<option value="${result.code}" label="${result.codeNm}"/>
			                    	</c:forEach>
			                    </select>
			                </td>
			            </tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="chgPwBtn">암호변경</button>
					<button id="saveBtn">저장</button>
				</div>
	</div>
</div>