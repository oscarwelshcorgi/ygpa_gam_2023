<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupFcltsMngFeeMngPrmisn.jsp
  * @Description : 승낙 팝업
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.30  Lee          최초 생성
  *
  * author Lee
  * since 2014.11.30
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupFcltsMngFeeMngModule() {} GamPopupFcltsMngFeeMngModule

GamPopupFcltsMngFeeMngModule.prototype = new EmdPopupModule(380, 100);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupFcltsMngFeeMngModule.prototype.loadComplete = function() {

	this.resizable(true);
};

// 사용자 설정 함수 추가

GamPopupFcltsMngFeeMngModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnPrmisnExec':
		if( this.$('#chrgeKnd').val() == '' ) {
            alert("요금종류를 선택하십시오.");
            return;
        }

		if( confirm("고지 하시겠습니까?") ) {
			var inputVO=this.makeFormArgs('#gamPopupPrmisnForm');

	        this.doAction('/mngFee/gamUpdateFcltsMngFeeMngPrmisn.do', inputVO, function(module, result) {
	            alert(result.resultMsg);

	            module.closeDialog('ok', result.resultCode);
	        });
	    }

		break;
	case 'cancel':
		this.cancelDialog();
	}
};

GamPopupFcltsMngFeeMngModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
};

GamPopupFcltsMngFeeMngModule.prototype.loadData = function() {
	//var searchOpt=this.makeFormArgs('#gamPopupPrmisnForm');
 	//this.$('#grdInfoList').flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupFcltsMngFeeMngModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupPrmisnForm">
		    <input type="text" id="mngMt" value="<c:out value="${gamFcltsMngFeeMngInfo.mngMt }"/>"/>
		    <input type="text" id="mngFeeJobSe" value="<c:out value="${gamFcltsMngFeeMngInfo.mngFeeJobSe }"/>"/>
			<input type="text" id="mngSeq" value="<c:out value="${gamFcltsMngFeeMngInfo.mngSeq }"/>"/>

			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>요금종류</th>
                        <td>
                            <select id="chrgeKnd">
                                <option value="" selected="selected">선택</option>

                                <c:forEach  items="${chrgeKndCdList}" var="chrgeKndCdItem">
	                                <option value="${chrgeKndCdItem.code }">${chrgeKndCdItem.codeNm }</option>
	                            </c:forEach>
                            </select>
                        </td>
                        <td><button id=btnPrmisnExec class="submit">승낙</button></td>
                    </tr>
				</tbody>
		    </table>
		</form>
	</div>
</div>