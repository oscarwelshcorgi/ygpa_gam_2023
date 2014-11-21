<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamCmpyCodePopup.jsp
  * @Description : 업체코드 조회 팝업
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2013.12.30  장은성          최초 생성
  *
  * author 장은성
  * since 2013.12.30
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamCmpyCodePopupModule() {}

GamCmpyCodePopupModule.prototype = new EmdPopupModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamCmpyCodePopupModule.prototype.loadComplete = function() {
	this.$('#prtAtCode').val('820');	// 기본 항코드 설정

	// 테이블 설정
	this.$("#cmpyCodeList").flexigrid({
		url: '/code/popup/selecterpCmpyCodeList.do',
		dataType: 'json',
		colModel : [
				{display:'기업규모', name:'FIRM_TP', width:8, sortable:true, align:'left'},
				{display:'기업규모명', name:'FIRM_TP_NM', width:64, sortable:true, align:'left'},
				{display:'사업자 등록 번호', name:'BZ_RGST_ID', width:96, sortable:true, align:'left'},
				{display:'상호명(한글명)', name:'FIRM_KOR_NM', width:240, sortable:true, align:'left'},
				{display:'상호명(영문명)', name:'FIRM_ENG_NM', width:240, sortable:true, align:'left'},
				{display:'성명(한글명)', name:'KOR_NM', width:96, sortable:true, align:'left'},
				{display:'성명(영문명)', name:'ENG_NM', width:96, sortable:true, align:'left'},
				{display:'업태', name:'BUSINESS', width:128, sortable:true, align:'left'},
				{display:'종목', name:'TP', width:128, sortable:true, align:'left'},
				{display:'전화번호', name:'PHONE_NO', width:112, sortable:true, align:'left'},
				{display:'우편번호', name:'ZIP_CODE', width:56, sortable:true, align:'left'},
				{display:'한글주소', name:'ADDR', width:640, sortable:true, align:'left'}
			],
		height: '300',
		singleSelect: true
	});

};

// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.

GamCmpyCodePopupModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'selectCmpyList':
		var searchOpt=this.makeFormArgs('#searchCmpyCode');
	 	this.$('#cmpyCodeList').flexOptions({params:searchOpt}).flexReload();
		break;
	case 'selectCmpyCd':
		this.returnValue(this.$('#cmpyCodeList').selectedRows());
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamCmpyCodePopupModule();
</script>
<!-- 아래는 고정 -->
<div class="popup_main">
	<div class="emdPanel">
		<form id="searchCmpyCode">
			<table style="width:100%;" class="searchPanel">
				<tbody>
					<tr>
						<th>업태</th>
						<td><select id="searchFirmBusiness">
								<option value="" selected="selected">선택</option>
								<c:forEach  items="${firmBusinessList}" var="business">
									<option value="${business }">${business }</option>
								</c:forEach>
						</select>
						</td>
						<th>업종</th>
						<td><input id="searchFirmTp" type="text" size="20"></td>
						<th>업체명</th>
						<td><input id="searchFirmNm" type="text" size="20"></td>
						<th>대표자명</th>
						<td><input id="searchNm" type="text" size="10"></td>
						<td rowSpan="2"><button id="selectCmpyList" class="submit">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="emdPanel">
		<table id="cmpyCodeList" style="display:none"></table>
		<div class="emdControlPanel"><button id="selectCmpyCd">선택</button></div>
	</div>
</div>
