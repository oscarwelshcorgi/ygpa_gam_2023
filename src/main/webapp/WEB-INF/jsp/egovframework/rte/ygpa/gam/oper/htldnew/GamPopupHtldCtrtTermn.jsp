<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  /**
  * @Class Name : GamPopupHtldCtrtTermn.jsp
  * @Description : 계약해지
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2016.5.11  Jongmin          최초 생성
  *
  * author Jongmin
  * since 2016.05.11
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
  */
--%>
<script>
function GamPopupHtldCtrtTermnModule() {}

GamPopupHtldCtrtTermnModule.prototype = new EmdPopupModule(500, 120);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupHtldCtrtTermnModule.prototype.loadComplete = function() {
	this.resizable(true);	
};

GamPopupHtldCtrtTermnModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnOk':
		if(confirm('계약해지를 하시겠습니까?')) {
			if(!EMD.util.isDate(this.$('#termnDt').val())) {
				alert('계약해지일을 입력하지 않았거나 날짜형식에 맞지 않습니다');
				return;
			}
			var value = this.getFormValues('#gamPopupHtldCtrtTermnForm');
			this.closeDialog("ok", value);
		}
		break;
	case 'btnCancel':
		this.cancelDialog();
		break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupHtldCtrtTermnModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupHtldCtrtTermnForm">
        	<table class="editForm" style="width:100%">
        		<tr>
					<th width="20%" height="18">계약 해지일</th>
					<td>
						<input type="text" size="12" id="termnDt" class="emdcal"/>
					</td>
					<th width="10%" height="18">해지 사유</th>
					<td>
						<input type="text" size="30" id="termnRsn" />
					</td>
				</tr>
			</table>
		</form>
		<table class="searchPanel fillHeight">
			<tbody>
				<tr>
					<th style="text-align:center">
						<button class="btnOk">확인</button>
						<button class="btnCancel">취소</button>
					</th>
				</tr>
			</tbody>
		</table>
	</div>
</div>