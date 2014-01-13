<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamUserInsert.jsp
  * @Description : 사용자관리등록
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
		height: '800'
	});
};
/*
// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
GamUserMngListModule.prototype.showModuleAlert = function(msg) {
	//this.getSelect(msg);	
	this.$('#prtCode').val(msg);
}
*/

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
		
		// 취소
		case 'cancelBtn':
			self.close(); 
			break;
	}
};
/*
GamUserMngListModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
}

GamUserMngListModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#searchForm');
	//this.showAlert(searchOpt);
 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
//	this.$('#assetList').flexOptions(searchOpt).flexReload();
}
*/
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamUserMngListModule();
</script>
<validator:javascript formName="userManageVO" staticJavascript="false" xhtml="true" cdata="false" />
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="userMngForm">
				<table class="searchPanel">
		            <tr>
		                <th width="20%" height="23" class="required_text">사용자아이디<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
		                <td width="80%" >
		                    <input id="emplyrId" title="사용자아이디" size="20" maxlength="20" />
		                    <a href="#LINK" onclick="fnIdCheck();">
		                        <img src="<c:url value='/images/egovframework/com/cmm/icon/search.gif' />" alt="" />(중복아이디 검색)
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
		                        <option value="0" label="--선택하세요--"/>
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
		                        <option value="0" label="--선택하세요--"/>
		                    </select>
		                </td>
		            </tr>
		            <tr>
		                <th width="20%" height="23" class="required_text"  >조직아이디&nbsp;&nbsp;</th>
		                <td width="80%" >
		                    <select id="orgnztId" name="orgnztId" title="조직아이디">
		                        <option value="0" label="--선택하세요--"/>
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
		                        <option value="0" label="--선택하세요--"/>
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
		                        <option value="0" label="--선택하세요--"/>
		                    </select>
		                </td>
		            </tr>
		            <tr>
		                <th width="20%" height="23" class="required_text"  >
		                    사용자상태코드<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />
		                </th>
		                <td width="80%" >
		                    <select id="emplyrSttusCode" title="사용자상태코드">
		                        <option value="0" label="--선택하세요--"/>
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
				<div class="emdTabPage">
					<div class="emdControlPanel">
						<a href="#" data-role="LoadModule" data-url="<c:url value='/cmmn/gamUserInsert.do'/>"><button id="insertBtn">등록</button></a>
						<button id="listBtn">목록</button>
						<button id="cancelBtn">취소</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>