<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
* @Class Name : GamPopupChgPwView.jsp
* @Description : 패스워드 변경팝업
* @Modification Information
*
*   수정일         수정자                   수정내용
*  -------    --------    ---------------------------
*  2014.01.29  kok          최초 생성
*
* author kok
* since 2013.01.29
*
* Copyright (C) 2013 by LFIT  All right reserved.
*/
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 * 공통 팝업이 아닌 팝업은 이 페이지와 같이 해당 패키지의 하단에 생성 할 것
 */
function GamPassWordChgPopupModule() {}

/* 팝업모듈 초기화 */
GamPassWordChgPopupModule.prototype = new EmdPopupModule(380, 220);

// 팝업이 호출 되었을때 호출 되는 함수
GamPassWordChgPopupModule.prototype.loadComplete = function() {

	this.resizable(true);
	/*
	// 테이블 설정
	this.$("#passwordChgPopupList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/popup/gamPopupProgramList.do" />',
		dataType: "json",
		colModel : [
					{display:"프로그램파일명",	name:"progrmFileNm",		width:200, 	sortable:false,		align:"center"},
					{display:"프로그램명", 		name:"progrmKoreanNm",		width:200, 	sortable:false,		align:"center"}
					],
		usepager: true,
		useRp: true,
		//rp: 24,
		showTableToggleBtn: false,
		height: "315"
	});

	this.$("#passwordChgPopupList").on('onItemDoubleClick', function(event, module, row, grid, param) {

		module.closeDialog('ok', row["progrmFileNm"]);
	});
	*/
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamPassWordChgPopupModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 수정
		case "changeBtn":

			if(this.$('#newPassword').val()!=this.$('#newPassword2').val()) {
				alert('입력한 암호가 서로 다릅니다.');
				return;
			}
			if(confirm("암호를 수정하시겠습니까?")){
				var inputVO = this.makeFormArgs("#passwordChgPopupForm");
				this.doAction('<c:url value="/cmmn/popup/gamUserPasswordUpdt.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == 0){
			 			//alert(result.resultMsg);
			 			module.closeDialog(); 			
			 		}
			 		else {
			 			alert(result.resultMsg);
			 			return;
			 		}
			 	});				
			}
		break;
		
		// 목록
		case "listBtn":
			this.closeDialog("cancel");
		break;
		
		// 취소
		case "closeBtn":
			this.$("#oldPassword").val("");
			this.$("#newPassword").val("");
			this.$("#newPasswor2").val("");
		break;

	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPassWordChgPopupModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<div class="viewStack">
			<form id="passwordChgPopupForm">
			<input type="hidden" id="uniqId" value="<c:out value='${uniqId }'/>"  />
				<table class="searchPanel">
					<tbody>
						<tr>
							<th width="20%" height="23" class="required_text">사용자아이디<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td width="80%"><input id="emplyrId" title="사용자아이디" size="20" maxlength="20" value="<c:out value="${emplyrId}" />"  disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">기존비밀번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td width="80%"><input id="oldPassword" title="사용자이름" type="password" size="20" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">비밀번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td width="80%"><input type="password" id="newPassword" title="비밀번호" size="20" maxlength="20" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">비밀번호확인<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td width="80%"><input id="newPassword2" title="비밀번호확인" type="password" size="20" maxlength="20" /></td>
						</tr>
					</tbody>
				</table>
				<div class="emdControlPanel">
					<button id="changeBtn">수정</button>
					<button id="closeBtn">취소</button>
					<button id="listBtn">닫기</button>
				</div>
			</form>
		</div>
	</div>
</div>