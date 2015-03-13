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
  *  2015.01.08 eunsungj	부두 그룹 코드 추가
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

GamUserMngListModule.prototype = new EmdModule(800, 500);

// 페이지가 호출 되었을때 호출 되는 함수
GamUserMngListModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#userMngList").flexigrid({
		module: this,
		url: '/adm/usrmngt/selectUserManageList.do',
		dataType: "json",
		colModel : [
			{display:"No", 			name:"rn",				width:30, 	sortable:true,		align:"center"},
			{display:"아이디", 		name:"userId",			width:100, 	sortable:true,		align:"center"},
			{display:"사용자이름", 		name:"userNm",			width:90, 	sortable:true,		align:"left"},
			{display:"관리부두", 		name:"quayGroupNm",		width:100, 	sortable:true,		align:"left"},
			{display:"사용자이메일", 	name:"emailAdres",		width:220, 	sortable:true,		align:"left"},
			{display:"전화번호", 		name:"allTelno",		width:150, 	sortable:false,		align:"center"},
			{display:"등록일", 		name:"sbscrbDe",		width:100, 	sortable:true,		align:"center"},
			{display:"가입상태",		name:"sttus",			width:140,	sortable:false,		align:"center"}
		],
		height: "auto"
	});

	this.$('#userMngList').on("onItemSelected", function(event, module, row) {
		module._mode="modify";
		module.setButtonState();
	});
	this.$("#userMngList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module._mode="modify";
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#userMngListTab").tabs("option", {active: 1});		// 탭을 전환 한다.
	});

	this._mode="";
	this.setButtonState();

//	console.log('GamUserMng load completed.');
};


/**
 * 정의 된 버튼 클릭 시
 */
GamUserMngListModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			this.loadData();
		break;

		// 추가
		case "btnAddUser":
			this._mode="insert";
			this._checkId=false;
			this.$("#userMngListTab").tabs("option", {active: 1});
		break;

		// 아이디 입력
		case "inputUserId":
			this.$("#emplyrId").removeAttr("disabled");
			this.$("#emplyrId").focus();
			this.$("#checkId").val("");
			break;
		case "checkUserId": // 중복아이디 검색
			this.checkUserId();
			break;
		case "listBtn": // 목록
			this.$("#userMngListTab").tabs("option", {active: 0});
			this.$("#userManageVO :input").val("");
			this._mode = "insert";
			break;
		case "btnChangePW": // 암호변경 팝업
			this.doExecuteDialog("changePassWordPopup", "업무사용자 암호변경", '/cmmn/popup/gamPopupChgPwView.do', {emplyrId : this.$("#emplyrId").val(), uniqId: this.$("#uniqId").val()});
			break;
		case "btnCancel": // 취소
			this.$("#userManageVO :input").val("");
			this._mode="insert";
			break;
		case "btnSaveUserData": // 저장
			this.saveUserData();
			break;
		case "btnDeleteUser": // 삭제
			if(confirm("삭제하시겠습니까?")){
				this.doAction('/cmmn/gamUserDelete.do', {uniqId: this.$("#uniqId").val()}, function(module, result) {
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
			this.doExecuteDialog("searcpZipcodePopup", "우편번호조회팝업", '/cmmn/popup/gamPopupSearchZipView.do', {});
			break;
	}
};

GamUserMngListModule.prototype.checkUserId = function() {
	if(this.$("#emplyrId").val() == ""){
		this.$("#emplyrId").focus();
		alert("아이디를 입력하십시오.");
		return;
	}
	this.doAction('/cmmn/gamIdDplctCnfirm.do', {checkId : this.$("#emplyrId").val()}, function(module, result) {
 		if(result.resultCode == 0){
 			if(result.usedCnt != "0"){
 				alert("이미 사용중인 아이디가 존재합니다.");
 				module.$("#emplyrId").focus();
 				module._checkId=false;
 			}else{
 				if(confirm("해당 아이디로 사용하시겠습니까?")){
 					module.$("#emplyrId").val(result.checkId);
 					module._checkId=true;
 					module.$("#emplyrId").attr("disabled","disabled");
 				}else{
 					module.$("#emplyrId").val("");
 					module._checkId=false;
 					module.$("#emplyrId").focus();
 				}
 			}
 		}
 	});
};

GamUserMngListModule.prototype.onSubmit = function() {
	this.loadData();
};

<%--
	데이터를 로딩한다.
--%>
GamUserMngListModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#userMngForm");
	this.$("#userMngListTab").tabs("option", {active: 0});
 	this.$("#userMngList").flexOptions({params:searchOpt}).flexReload();
	this._mode="";
	this.setButtonState();
};

GamUserMngListModule.prototype.saveUserData = function() {
	if(this._mode=="insert"){
		if(!this._checkId) {
			EMD.util.showMessage(this.$('#checkUserId')[0], "아이디 체크를 해주세요.");
			return;
		}
		if(this.$("#password").val() == ""){
			EMD.util.showMessage(this.$("#password")[0], "비밀번호를 입력 해주세요.");
			this.$("#password").focus();
			return;
		}
		if(this.$("#password2").val() == ""){
			EMD.util.showMessage(this.$("#password2")[0], "비밀번호 확인를 입력 해주세요.");
			this.$("#password2").focus();
			return;
		}
		if(this.$('#password').val()!=this.$('#password2').val()) {
			EMD.util.showMessage(this.$("#password")[0], "입력한 암호가 서로 다릅니다.");
			this.$("#password").focus();
			return;
		}
	}
	this.$("#zip").val(this.$("#zip").val().replace(/\-/g,""));

	if(!validateGamUserMng(this.$("#userManageVO")[0])) return;

	var inputVO = this.makeFormArgs("#userManageVO");
	if(this._mode == "insert") {
	 	this.doAction('/adm/usrmngt/insertUser.do', inputVO, function(module, result) {
	 		if(result.resultCode == 0){
	 			module.$("#userMngListTab").tabs("option", {active: 0});
	 			module.$("#userManageVO :input").val("");
	 			var searchOpt = module.makeFormArgs("#userMngForm");
			 	module.$("#userMngList").flexOptions({params:searchOpt}).flexReload();
			 	this._mode="";
	 		}
	 		alert(result.resultMsg);
	 	});
	}else{
	 	this.doAction('/adm/usrmngt/updateUser.do', inputVO, function(module, result) {
	 		if(result.resultCode == 0){
	 			var inputVO = module.makeFormArgs("#userManageVO");
	 			module.loadDetail(inputVO);
			 	this._mode="";
	 		}
	 		alert(result.resultMsg);
	 	});
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
		var row = null;
		if(this._mode!="insert") {
			row = this.$("#userMngList").selectedRows();
			if(row.length == 0) this._mode="insert";
			else this._mode="modify";
		}
		if(this._mode=="modify" && row!=null && row.length>0) {
			this.loadDetail(row[0]);
		}
		else {
			this._mode="insert";
			this.setButtonState();
			this.makeFormValues('#userManageVO', {});
		}
		break;
	}
};

GamUserMngListModule.prototype.loadDetail = function(row) {
	this.doAction('/adm/usrmngt/selectUserDetail.do', {uniqId: row["uniqId"]}, function(module, result) {
		if(result.userManageVO.zip){
			result.userManageVO.zipSet = result.userManageVO.zip.substring(0,3) + "-" + result.userManageVO.zip.substring(3);
		}
		module.makeFormValues('#userManageVO', result.userManageVO);

		module._checkId=true;
		module.setButtonState();
 	});
};

GamUserMngListModule.prototype.setButtonState = function() {
	var tab_active = this.$('#userMngListTab').tabs('option', 'active');
	switch(tab_active) {
	case 0:
		var row = this.$("#userMngList").selectedRows();
		if(row.length==0) {
			this.$("button[data-cmd='btnDeleteUser']").hide();
		}
		else {
			this.$("button[data-cmd='btnDeleteUser']").show();
		}
		break;
	case 1:
		if(this._mode=="insert") {
			this.$("#btnChangePW").hide();
			this.$("#emplyrId").enable();
			this.$("#tdpassword").css('display', '');
			this.$("button[data-cmd='btnDeleteUser']").hide();
		}
		else {
			this.$("#btnChangePW").show();
			this.$("#emplyrId").disable();
			this.$("#tdpassword").css('display', 'none');
			this.$("button[data-cmd='btnDeleteUser']").show();
		}
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
		<form id="userMngForm">
			<table class="searchPanel">
				<tbody>
					<tr>
						<th>
							사용자 신청 상태
						</th>
						<td>
			                <select id="sbscrbSttus" title="검색조건선택1">
			                    <option value="0" selected="selected">상태(전체)</option>
			                    <option value="A">가입신청</option>
			                    <option value="D">삭제</option>
			                    <option value="P">승인</option>
			                </select>
		                </td>
						<th>
							검색 조건
						</th>
		                <td>
			                <select id="searchCondition" title="검색조건선택2">
			                    <option value="0">ID</option>
			                    <option value="1">사용자이름</option>
			                </select>&nbsp;
			                <input id="searchKeyword" type="text" title="검색단어입력" />
						</td>
						<td><button id="searchBtn" class="submit buttonSearch" >조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
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
					<button id="btnAddUser">추가</button>
					<button data-cmd="btnDeleteUser">삭제</button>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="userManageVO">
					<input type="hidden" id="uniqId" />
					<table class="editForm" style="width:100%;">
			            <tr>
			                <th>사용자 아이디</th>
			                <td colspan="3">
			                    <input id="emplyrId" title="사용자아이디" size="20" maxlength="20" />
			                    <button id="checkUserId">중복아이디 검사</button>
			                    <button id="inputUserId">아이디 입력</button>
			                </td>
			            </tr>
			            <tr>
			                <th>사용자이름</th>
			                <td>
			                    <input id="emplyrNm" title="사용자이름" type="text" size="10" maxlength="60"  data-required="true" />
			                </td>
			                <th>직위명</th>
			                <td >
			                    <input data-column-id="ofcpsNm" title="직위명" size="20" maxlength="50" />
			                </td>
			            </tr>
			            <tr>
			                <th>사번</th>
			                <td >
			                    <input data-column-id="emplNo" title="사번" size="6" maxlength="20" data-required="true" />
			                </td>
			                <th>관리부두</th>
			                <td>
			                    <input id="quayGroupCd" title="관리부두" class="ygpaCmmnCd" data-code-id="GAM063" type="text" size="10" data-default-prompt="없음" />
			                </td>
			            </tr>
			            <tr id="tdpassword">
			                <th>비밀번호</th>
			                <td >
			                    <input type="password" id="password" title="비밀번호" size="20" maxlength="20" data-required="true"/>
			                </td>
			                <th class="required_text">비밀번호확인</th>
			                <td >
			                    <input id="password2" title="비밀번호확인" type="password" size="20" maxlength="20"  data-required="true" />
			                </td>
			            </tr>
			            <tr>
			                <th>
			                   	담당 부서
			                </th>
			                <td >
				                <input id="orgnztId" class="ygpaDeptSelect" size="20" data-default-prompt="선택" data-required="true" />
			                </td>
			                <th>관리시설</th>
			                <td>
			                    <input id="mngFcltyCd" title="관리시설" class="ygpaCmmnCd" data-code-id="GAM065" type="text" size="10" data-default-prompt="없음" />
			                </td>
			            </tr>
			            <tr>
			                <th class="required_text">사무실전화번호</th>
			                <td >
			                    <input id="offmTelno" title="사무실전화번호" size="20" maxlength="15" />
			                </td>
			                <th>팩스번호</th>
			                <td >
			                    <input id="fxnum" title="팩스번호" size="20" maxlength="15" />
			                </td>
			            </tr>
			            <tr>
			                <th class="required_text">핸드폰번호</th>
			                <td >
			                    <input id="moblphonNo" title="핸드폰번호" size="20" maxlength="15" />
			                </td>
			                <th>집전화번호</th>
			                <td>
			                    <input id="areaNo" title="areaNo" size="5" maxlength="4" />
			                    - <input title="homemiddleTelno" id="homemiddleTelno" size="5" maxlength="4" />
			                    - <input title="homeendTelno" id="homeendTelno" size="5" maxlength="4" />
			                </td>
			            </tr>
			            <tr>
			                <th class="required_text">
                    			우편번호
			                </th>
			                <td >
			                    <input id="zip" type="text" title="우편번호" size="7" disabled="disabled"/>
			                    <button id="searchZipBtn">우편번호 검색</button>
			                </td>
			                <th>
			                   	 이메일주소
			                </th>
			                <td>
			                    <input id="emailAdres" title="이메일주소" size="30" maxlength="50" />
			                </td>
			            </tr>
			            <tr>
			                <th class="required_text">
						                    주소
			                </th>
			                <td >
			                    <input id="homeadres" title="주소" size="30" maxlength="100" />
			                </td>
			                <th class="required_text">상세주소</th>
			                <td >
			                    <input id="detailAdres" title="상세주소" size="30" maxlength="50" />
			                </td>
			            </tr>
			            <tr>
			                <th class="required_text">
                    			사용자상태코드
			                </th>
			                <td colspan="3">
			                	<input id="emplyrSttusCode" title="사용자 상태" class="ygpaCmmnCd" data-required="true" data-code-id="COM013"/>
			                </td>
			            </tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="btnSaveUserData">저장</button>
					<button id="btnChangePW">암호변경</button>
					<button data-cmd="btnDeleteUser">삭제</button>
					<button id="btnCancel">취소</button>
				</div>
			</div>
		</div>
	</div>
</div>