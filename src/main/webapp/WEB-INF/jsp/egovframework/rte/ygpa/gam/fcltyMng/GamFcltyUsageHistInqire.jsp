<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyUsageHistInqire.jsp
  * @Description : 시설물 사용이력 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014. 12. 11.		LFIT		최초 생성
  *
  * author LFIT
  * since 2014.12.11
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyUsageHistInqireModule() {
}

GamFcltyUsageHistInqireModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

//페이지가 호출 되었을때 호출 되는 함수
GamFcltyUsageHistInqireModule.prototype.loadComplete = function(params) {
	// 테이블 설정
	this.$("#gamFcltyUsageHistInqireList").flexigrid({
		module: this,
		url: '/fcltyMng/gamFcltyUsageHistInqireList.do',
		dataType: "json",
		colModel : [
					{display : '항코드',			name : 'gisAssetsPrtAtCode', 		width : 50, 	sortable : false, 	align : 'center'},
					{display : '항코드명',			name : 'prtAtCodeNm', 				width : 60, 	sortable : false, 	align : 'center'},
					{display : '자산코드',			name : 'gisAssetsDisplay',		width : 80, 	sortable : false, 	align : 'center'},
					{display : '자산명',			name : 'gisAssetsNm', 				width : 200, 	sortable : false, 	align : 'left'},
					{display : '사용시작일자',		name : 'usagePdFrom', 					width : 100, 	sortable : false, 	align : 'center'},
					{display : '사용종료일자',		name : 'usagePdTo', 					width : 100, 	sortable : false, 	align : 'center'},
					{display : '업체코드',			name : 'entrpsSe', 					width : 80, 	sortable : false, 	align : 'center'},
					{display : '업체명',			name : 'entrpsNm', 					width : 120, 	sortable : false, 	align : 'left'},
					{display : '사용료',			name : 'fee', 						width : 100, 	sortable : false, 	align : 'right', displayFormat : 'number'},
					{display : '임대정보',			name : 'RentInfo', 					width : 60, 	sortable : false, 	align : 'center'},
					{display : '제원정보',			name : 'SpecInfo', 					width : 60, 	sortable : false, 	align : 'center'}
			],
		height: "auto"
	});
};
/**
 * 정의 된 버튼 클릭시
 */
 GamFcltyUsageHistInqireModule.prototype.onButtonClick = function(buttonId){
	switch(buttonId){
		// 조회
		case "searchBtn":
			var searchOpt=this.makeFormArgs("#fcltyForm");
			this.$("#gamFcltyUsageHistInqireList").flexOptions({params:searchOpt}).flexReload();
		break;
		
		// 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisCodePopup2", "자산코드", '/popup/showAssetsCd.do', {});
		break;
		
		//등록업체 선택
		case 'popupEntrpsInfo' :
			this.doExecuteDialog('selectEntrpsInfo', '등록업체 선택', '/popup/showSocEntrpsInfo.do', {});
        break; 
	}	
};

/**
 * 팝업 close 이벤트
 */
 GamFcltyUsageHistInqireModule.prototype.onClosePopup = function(popupId, msg, value){

		switch(popupId){

		// 자산코드 조회
		case "searchGisCodePopup2":
			this.$("#searchAssetsCd").val(value["gisAssetsCd"]);
			this.$("#searchAssetsSubCd").val(value["gisAssetsSubCd"]);
		break;

		// 업체 조회
		case 'selectEntrpsInfo' : 
	    	 this.$("#EntrpsCd").val(value["agentCode"]);
	    	 this.$("#EntrpsNm").val(value["firmKorNm"]);
		 break;
		
		// 
		case "selectFcltsClCd":
			this.$("#sRegistEntrpsCd").val(value["fcltsClCd"]);
			this.$("#sRegistEntrpsNm").val(value["fcltsClCdNm"]);			
		break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
		break;
	}
};
//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyUsageHistInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="fcltyForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항코드</th>
							<td><input id="searchPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>자산코드</th>
							<td>
								<input id="searchAssetsCd" type="text" size="3" maxlength="3" title="검색조건" />&nbsp;-&nbsp;
								<input id="searchAssetsSubCd" type="text" size="2" maxlength="2" title="검색조건" />
								<button id="searchPopupBtn" class="popupButton">선택</button>
							</td>
							<th width="10%">등록업체</th>
                            <td colspan="3">
                            	<input id="EntrpsCd" type="text" size="7">&nbsp; &nbsp;
                         		<input id="EntrpsNm" type="text" size="20" disabled="disabled">&nbsp; &nbsp;
                         		<button id="popupEntrpsInfo" class="popupButton">선택</button>
                         	</td>
							<td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>시설명</th>
							<td colspan="3"><input id="searchKeyword" type="text" size="50" maxlength="40" title="검색조건"  /></td>
							<th>기간</th>
                            <td>
                            	<input id="sSearchDtFr" type="text" class="emdcal" size="8"> ~ 
                            	<input id="sSearchDtTo" type="text" class="emdcal" size="8">
                            </td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="Tab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">사용이력정보</a></li>
            </ul>
            
            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="gamFcltyUsageHistInqireList" style="display:none" class="fillHeight"></table>
			</div>
		</div>
	</div>
	
</div>