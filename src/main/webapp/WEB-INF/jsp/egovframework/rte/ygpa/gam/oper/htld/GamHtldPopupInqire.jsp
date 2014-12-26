<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamHtldPopupInqire.jsp
  * @Description : 배후단지 임대현황 알림
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.06.17  sj          최초 생성
  *
  * author sj
  * since 2014.02.07
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetLndValInqireModule() {}

GamAssetLndValInqireModule.prototype = new EmdModule(500, 200);

//페이지가 호출 되었을때 호출 되는 함수
GamAssetLndValInqireModule.prototype.loadComplete = function() {
 this.$('#chk').on('click', {module: this}, function(event) {
		var d = new Date();
		d.setDate(d.getDate()+1);
		d.setHours(0);
	  	document.cookie = "ygpa_popup_c4=/oper/htld/gamHtldPopupInqire.do; expires=" +d.toUTCString();
	  	event.data.module.closeWindow();
	});
};

/**
* 정의 된 버튼 클릭 시
*/
GamAssetLndValInqireModule.prototype.onButtonClick = function(buttonId) {

 switch(buttonId) {
     case 'btnHtldRent':
    	 EMD.util.create_window('배후단지 계약 관리', '/oper/htld/gamHtldRentMngt.do', null);
    	 break;
     case 'btnHtldFeeMngt':
    	 EMD.util.create_window('배후단지 임대료 관리', '/oper/htld/gamHtldRentFeeMngt.do', null);
    	 break;
     case 'btnHtldRentFeePayDtlsMngt':
    	 EMD.util.create_window('배후단지 임대료 미납관리', '/oper/htld/gamHtldRentFeePaySttusMngt.do', null);
    	 break;
     case 'btnHtldUseExprInqire':
    	 EMD.util.create_window('배후단지 계약만기도래조회', '/oper/htld/gamHtldUseExprInqire.do', null);
    	 break;
 }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetLndValInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
    <div class="emdPanel fillHeight">
                    <table style="width:100%;" >
                        <tr>
				<th>* 임대 계약 신청 건수</th>
				<td><input id="totalResultCnt1" size="10" readonly
					value="<c:out value="${prmisnYnCnt}"/>"></td>
                            <td>
					<button id="btnHtldRent">배후단지 계약 관리</button>
                            </td>
                        </tr>
                        <tr>
				<th>* 고지도래자료 건수</th>
				<td><input id="totalResultCnt2" size="10" readonly
					value="<c:out value="${nticPdCnt}"/>"></td>
                            <td>
					<button id="btnHtldFeeMngt">배후단지 임대료 고지</button>
                            </td>
                        </tr>
                        <tr>
				<th>* 미수납자료 건수</th>
				<td><input id="totalResultCnt3" size="10" readonly
					value="<c:out value="${nhtIsueCnt}"/>"></td>
                            <td>
					<button id="btnHtldRentFeePayDtlsMngt">배후단지 임대료 미납관리</button>
                            </td>
                        </tr>
                        <tr>
				<th>* 계약만기 도래자료 건수</th>
				<td><input id="totalResultCnt4" size="10" readonly
					value="<c:out value="${rcivSeCnt}"/>"></td>
                            <td>
					<button id="btnHtldUseExprInqire">배후단지 계약만기도래 조회</button>
                            </td>
                        </tr>
                    </table>
                </div>
				<form>
					<div align="right">
						하루 동안 알람 창 닫기 <input type="checkbox" id="chk">
					</div>
				</form>
            </div>
