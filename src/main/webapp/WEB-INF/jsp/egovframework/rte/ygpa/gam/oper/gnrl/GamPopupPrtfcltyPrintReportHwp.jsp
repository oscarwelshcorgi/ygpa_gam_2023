<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupPrtFcltyRentMngtPrmisn.jsp
  * @Description : 승낙 팝업
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.24  domh          최초 생성
  *
  * author domh
  * since 2014.01.24
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
  */
%>
<style>
table tbody tr td{
    height: 25px;
}
.btnOk{
    text-align: right;
    margin-top: 10px;
}

</style>

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupReportHwpModule() {} GamPopupReportHwpModule

//GamPopupReportHwpModule.prototype = new EmdPopupModule(300, 260);
GamPopupReportHwpModule.prototype = new EmdPopupModule(500, 210);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupReportHwpModule.prototype.loadComplete = function() {

	this.resizable(true);
	console.log("GamPopupReportHwpModule");
	
};

// 사용자 설정 함수 추가

GamPopupReportHwpModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnOk':
			var inputVO={"check1":this.$("#check1").is(":checked"),"check2":this.$("#check2").is(":checked"),"check3":this.$("#check3").is(":checked"),"check4":this.$("#check4").is(":checked"),"check5":this.$("#check5").is(":checked"),"other" : this.$("#other").val()};
			this.closeDialog('ok', inputVO );
/* 			var inputVO=this.makeFormArgs('#popupPrtfcltyForm');

	        //this.doAction('/oper/gnrl/gamInsertPrtFcltyRentMngtPrmisn.do', inputVO, function(module, result) {
	        this.doAction('/oper/gnrl/gamUpdatePrtFcltyRentMngtPrmisn.do', inputVO, function(module, result) {
	            alert(result.resultMsg);

	            module.closeDialog('ok', result.resultCode);
	        }); */

		break;
	case 'cancel':
		this.cancelDialog();
	}
};



// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupReportHwpModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="popupPrtfcltyForm">
			<table class="editForm">
				<tbody>
					<tr>
                        <td>
                        	<label for="check1">
	                        	<input type="checkbox" size="10" id="check1" class="chk">
	                        	<spring:message code='gam.oper.gnrl.rentFeeReportHwp1' />
                        	</label>
                        </td>
					</tr>
					<tr>
                        <td>
                        	<label for="check2">
	                        	<input type="checkbox" size="10" id="check2" class="chk">
                        		<spring:message code='gam.oper.gnrl.rentFeeReportHwp2' />
                        	</label>
                        </td>
					</tr>
					<tr>
                        <td>
                        	<label for="check3">
	                        	<input type="checkbox" size="10" id="check3" class="chk">
	                        	<spring:message code='gam.oper.gnrl.rentFeeReportHwp3' />
	                        </label>
                        </td>
					</tr>
<%-- 					<tr>
                        <td>
                        	<label for="check4">
	                        	<input type="checkbox" size="10" id="check4" class="chk">
	                        	<spring:message code='gam.oper.gnrl.rentFeeReportHwp4' />
	                        </label>
                        </td>
					</tr>
					<tr>
                        <td>
                        	<label for="check5">
	                        	<input type="checkbox" size="10" id="check5" class="chk">
	                        	<spring:message code='gam.oper.gnrl.rentFeeReportHwp5' />
                        	</label>
                        </td>
					</tr> --%>
					<tr>
                        <td>
                        	<label for="other">
                        		기타 <input type="text" id="other" size="40"/>
                        	</label>
                        </td>
					</tr>
				</tbody>
		    </table>
		    <div class="btnOk">
		    	<button id="btnOk" class="submit">다운로드</button>
		    </div>
		</form>
	</div>
</div>