<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupTrainPortPenaltyFee.jsp
  * @Description : 과태료 부과 팝업
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.04.24  eunsungj          최초 생성
  *
  * author eunsungj
  * since 2014.04.24
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupTrainPortPenaltyFeeModule() {}

GamPopupTrainPortPenaltyFeeModule.prototype = new EmdPopupModule(450, 126);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupTrainPortPenaltyFeeModule.prototype.loadComplete = function() {

	this.resizable(true);
	this.grFee = this.$('#grFee').val();
	var module=this;

	this.$('#penaltyFeeRate').change(function() {
		var n =Number($(this).val());
		if(!isNaN(n)) {
			var fee=module.grFee*n;
			module.$('#penaltyFee').val(fee);
		}
		else {
			$(this).val(0);
		}
	})
};

// 사용자 설정 함수 추가

GamPopupTrainPortPenaltyFeeModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnApplyPenaltyFee':
		var inputVO=this.makeFormArgs('#gamPopupPenaltyFeeForm');

		if( this.$('#penaltyFeeRsn').val() == '' ) {
            alert("과태료 부과 사유를 입력하십시오.");
        } else {
            this.closeDialog('ok', {penaltyRsn: this.$('#penaltyFeeRsn').val(), penaltyFeeRate: this.$('#penaltyFeeRate').val(), penaltyFee: this.$('#penaltyFee').val()});
        }
		break;
	case 'cancel':
		this.cancelDialog();
	}
};

GamPopupTrainPortPenaltyFeeModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
};

GamPopupTrainPortPenaltyFeeModule.prototype.loadData = function() {
	//var searchOpt=this.makeFormArgs('#gamPopupDisUseForm');
 	//this.$('#grdInfoList').flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupTrainPortPenaltyFeeModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupPenaltyFeeForm">
		    <input type="hidden" id="mngYear" value="<c:out value="${mngItem.mngYear }"/>"/>
		    <input type="hidden" id="mngNo" value="<c:out value="${mngItem.mngNo }"/>"/>
		    <input type="hidden" id="mngCnt" value="<c:out value="${mngItem.mngCnt }"/>"/>
		    <input type="hidden" id="grFee" value="<c:out value="${mngItem.grFee }"/>"/>

			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>과태료 부과 사유</th>
                        <td colspan="3">
                            <input type="text" size="30" id="penaltyFeeRsn"/>
                        </td>
                        <td rowspan="2"><button id=btnApplyPenaltyFee class="submit">과태료 부과</button></td>
                    </tr>
					<tr>
                        <th>과태료 요율</th>
                        <td>
                            <input type="text" size="5" id="penaltyFeeRate" class="ygpaNumber" data-decimal-point="2"/>
                        </td>
                        <th>금액</th>
                        <td>
                            <input type="text" size="12" id="penaltyFee" class="ygpaNumber"/>
                        </td>
                    </tr>
				</tbody>
		    </table>
		</form>
	</div>
</div>