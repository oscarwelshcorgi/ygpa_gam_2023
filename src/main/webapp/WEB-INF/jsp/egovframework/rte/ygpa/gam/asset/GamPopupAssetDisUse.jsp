<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupAssetDisUse.jsp
  * @Description : 자산폐기 팝업
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.29  heroine          최초 생성
  *
  * author heroine
  * since 2014.01.29
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupAssetDisUseModule() {}

GamPopupAssetDisUseModule.prototype = new EmdPopupModule(450, 110);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupAssetDisUseModule.prototype.loadComplete = function() {

	this.resizable(true);
};

// 사용자 설정 함수 추가

GamPopupAssetDisUseModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnDisUseExec':
		var inputVO=this.makeFormArgs('#gamPopupDisUseForm');

		if( this.$('#erpAssetsDisuseRsn').val() == '' ) {
            alert("자산폐기사유를 입력하십시오.");
        } else {
        	this.doAction('<c:url value="/asset/gamUpdateAssetDisUse.do" />', inputVO, function(module, result) {

                alert(result.resultMsg);

                module.closeDialog('ok', result.resultCode);
            });
        }

		break;
	case 'cancel':
		this.cancelDialog();
	}
};

GamPopupAssetDisUseModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
};

GamPopupAssetDisUseModule.prototype.loadData = function() {
	//var searchOpt=this.makeFormArgs('#gamPopupDisUseForm');
 	//this.$('#grdInfoList').flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupAssetDisUseModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupDisUseForm">
		    <input type="hidden" id="gisAssetsCd" value="<c:out value="${gamAssetDisUseMngtVO.gisAssetsCd }"/>"/>
		    <input type="hidden" id="gisAssetsSubCd" value="<c:out value="${gamAssetDisUseMngtVO.gisAssetsSubCd }"/>"/>
		    <input type="hidden" id="gisAssetsPrtAtCode" value="<c:out value="${gamAssetDisUseMngtVO.gisAssetsPrtAtCode }"/>"/>

			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>자산폐기사유</th>
                        <td>
                            <input type="text" size="30" id="erpAssetsDisuseRsn"/>
                        </td>
                        <td><button id=btnDisUseExec class="submit">폐기</button></td>
                    </tr>
				</tbody>
		    </table>
		</form>
	</div>
</div>