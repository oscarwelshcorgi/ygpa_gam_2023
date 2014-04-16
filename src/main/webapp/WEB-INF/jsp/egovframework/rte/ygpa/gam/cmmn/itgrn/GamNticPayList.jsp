<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamNticPayList.jsp
  * @Description : 납부현황목록조회
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.02.07  kok          최초 생성
  *
  * author kok
  * since 2014.02.07
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamNticPayListModule() {}

GamNticPayListModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamNticPayListModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#nticPayList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/itgrn/gamNticPayListSelect.do" />',
		dataType: "json",
		colModel : [
				{display:"요금 종류",					name:"chrgeKndNm",				width:100,		sortable:false,		align:"center"},
				{display:"회계 년도",	 				name:"accnutYear",				width:60,		sortable:false,		align:"center"},
				{display:"고지번호", 				name:"nticno",					width:80,		sortable:false,		align:"center"},
				{display:"수납 구분명",				name:"rcivSeNm",				width:80,		sortable:false,		align:"center"},
				{display:"업체코드", 	 			name:"entrpscd",				width:80,		sortable:false,		align:"center"},
				{display:"고지금액",					name:"nticAmt",					width:80,		sortable:false,		align:"center"},
				{display:"시작일",					name:"beginDt",					width:80,		sortable:false,		align:"center"},
				{display:"종료일",					name:"endDt",					width:80,		sortable:false,		align:"center"},
				{display:"납부기한",					name:"payTmlmt",				width:100,		sortable:false,		align:"center"},
				{display:"고지일자",					name:"nticDt",					width:80,		sortable:false,		align:"center"},
				{display:"산출내역",					name:"computDtls",				width:80,		sortable:false,		align:"center"},
				{display:"금융기관 수납 일자",			name:"fnncInsttRcivDt",			width:80,		sortable:false,		align:"center"},
				{display:"등록일시",					name:"registDt",				width:80,		sortable:false,		align:"center"},
				{display:"관리 횟수",					name:"mngCnt",					width:1,		sortable:false,		align:"center"},
				{display:"관리 번호",					name:"mngNo",					width:1,		sortable:false,		align:"center"},
				{display:"관리 년도",					name:"mngYear",					width:1,		sortable:false,		align:"center"},
				{display:"고지 횟수",					name:"nticCnt",					width:1,		sortable:false,		align:"center"},
				{display:"수납구분",					name:"rcivSe",					width:1,		sortable:false,		align:"center"},
				{display:"항코드",					name:"prtAtCode",				width:1,		sortable:false,		align:"center"}
//				{display:"고지서 발부 여부",			name:"nhtIsueYn",				width:80,		sortable:false,		align:"center"},
//				{display:"회계 구분 코드",			name:"accnutSeCd",				width:100,		sortable:false,		align:"center"},
//				{display:"수납 이체 상태 코드"			name:"RCIV_TRANSFRSTTUSCD",		width:1,		sortable:false,		align:"center"},
//				{display:"최초 고지 일자" 			name:"gisAssetsCd",			width:1,		sortable:false,		align:"center"},
//				{display:"수납 일자"					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"불능 코드"					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"과오납 금액"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"임시 발행 번호"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"할인 금액"					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"할인 사유"					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"할인 코드"					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"우편 고지 유무"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"불납 사유 코드"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"전자 고지 결과"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"전자 고지 정보 조회 일자"	name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"정산 여부"					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"부가세"					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"부가세 여부"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"징수관 구분"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"원고지 요금 종류"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"원고지 회계 년도"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"원고지 번호"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"전자 세금 계산서 발행 여부"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"고지 방법"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"등록자"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"관리 년도"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"지로 수납처"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"지로 수납 구분"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"수수료"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"마감 여부"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"부서코드"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"담당자"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"작업 구분"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
			],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: "350"
	});
	
	// 테이블 설정
	this.$("#delayNticPayList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/itgrn/gamNticPayListSelect.do" />',
		dataType: "json",
		colModel : [
				{display:"요금 종류",					name:"chrgeKndNm",				width:100,		sortable:false,		align:"center"},
				{display:"회계 년도",	 				name:"accnutYear",				width:60,		sortable:false,		align:"center"},
				{display:"고지번호", 				name:"nticno",					width:80,		sortable:false,		align:"center"},
				{display:"수납 구분명",				name:"rcivSeNm",				width:80,		sortable:false,		align:"center"},
				{display:"업체코드", 	 			name:"entrpscd",				width:80,		sortable:false,		align:"center"},
				{display:"고지금액",					name:"nticAmt",					width:80,		sortable:false,		align:"center"},
				{display:"시작일",					name:"beginDt",					width:80,		sortable:false,		align:"center"},
				{display:"종료일",					name:"endDt",					width:80,		sortable:false,		align:"center"},
				{display:"납부기한",					name:"payTmlmt",				width:100,		sortable:false,		align:"center"},
				{display:"고지일자",					name:"nticDt",					width:80,		sortable:false,		align:"center"},
				{display:"산출내역",					name:"computDtls",				width:80,		sortable:false,		align:"center"},
				{display:"금융기관 수납 일자",			name:"fnncInsttRcivDt",			width:80,		sortable:false,		align:"center"},
				{display:"등록일시",					name:"registDt",				width:80,		sortable:false,		align:"center"},
				{display:"관리 횟수",					name:"mngCnt",					width:1,		sortable:false,		align:"center"},
				{display:"관리 번호",					name:"mngNo",					width:1,		sortable:false,		align:"center"},
				{display:"관리 년도",					name:"mngYear",					width:1,		sortable:false,		align:"center"},
				{display:"고지 횟수",					name:"nticCnt",					width:1,		sortable:false,		align:"center"},
				{display:"수납구분",					name:"rcivSe",					width:1,		sortable:false,		align:"center"},
				{display:"항코드",					name:"prtAtCode",				width:1,		sortable:false,		align:"center"}
//				{display:"고지서 발부 여부",			name:"nhtIsueYn",				width:80,		sortable:false,		align:"center"},
//				{display:"회계 구분 코드",			name:"accnutSeCd",				width:100,		sortable:false,		align:"center"},
//				{display:"수납 이체 상태 코드"			name:"RCIV_TRANSFRSTTUSCD",		width:1,		sortable:false,		align:"center"},
//				{display:"최초 고지 일자" 			name:"gisAssetsCd",			width:1,		sortable:false,		align:"center"},
//				{display:"수납 일자"					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"불능 코드"					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"과오납 금액"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"임시 발행 번호"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"할인 금액"					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"할인 사유"					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"할인 코드"					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"우편 고지 유무"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"불납 사유 코드"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"전자 고지 결과"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"전자 고지 정보 조회 일자"	name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"정산 여부"					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"부가세"					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"부가세 여부"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"징수관 구분"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"원고지 요금 종류"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"원고지 회계 년도"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"},
//				{display:"원고지 번호"				name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"전자 세금 계산서 발행 여부"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"고지 방법"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"등록자"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"관리 년도"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"지로 수납처"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"지로 수납 구분"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"수수료"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"마감 여부"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"부서코드"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"담당자"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
//				{display:"작업 구분"			name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
			],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: "350"
	});
	
	this.$("#nticPayList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#nticPayListTab").tabs("option", {active: 1});		// 탭을 전환 한다.
		
		var detailInput = {accnutYear:row["accnutYear"],mngCnt:row["mngCnt"],mngNo:row["mngNo"],mngYear:row["mngYear"],nticno:row["nticno"],nticCnt:row["nticCnt"],prtAtCode:row["prtAtCode"]};
		module.doAction('<c:url value="/cmmn/itgrn/gamNticPayListSelectView_.do" />', detailInput, function(module, result) {
			
			module.$("#chrgeKndNm").val(result.detail.chrgeKndNm);
			module.$("#accnutYear").val(result.detail.accnutYear);
			module.$("#nticno").val(result.detail.nticno);
			module.$("#accnutSeCd").val(result.detail.accnutSeCd);
			module.$("#displayEntrpscd").val(result.detail.entrpscd);
			module.$("#nticAmt").val(result.detail.nticAmt);
			module.$("#nticDt").val(result.detail.nticDt);
			module.$("#nhtIsueYn").val(result.detail.nhtIsueYn);
			module.$("#computDtls").val(result.detail.computDtls);
			module.$("#payTmlmt").val(result.detail.payTmlmt);
			module.$("#frstNticDt").val(result.detail.frstNticDt);
			module.$("#rcivTransfrSttusCd").val(result.detail.rcivTransfrSttusCd);
			module.$("#rcivDt").val(result.detail.rcivDt);
			module.$("#displayRcivSe").val(result.detail.rcivSe);
			module.$("#incpctyCd").val(result.detail.incpctyCd);
			module.$("#overrpayAmt").val(result.detail.overrpayAmt);
			module.$("#tmprIsuNo").val(result.detail.tmprIsuNo);
			module.$("#dscntAmt").val(result.detail.dscntAmt);
			module.$("#dscntRsn").val(result.detail.dscntRsn);
			module.$("#dscntCd").val(result.detail.dscntCd);
			module.$("#rcivSeNm").val(result.detail.rcivSeNm);
			module.$("#fnncInsttRcivDt").val(result.detail.fnncInsttRcivDt);
			module.$("#postNticEnnc").val(result.detail.postNticEnnc);
			module.$("#npymnRsnCd").val(result.detail.npymnRsnCd);
			module.$("#elctrnNticResult").val(result.detail.elctrnNticResult);
			module.$("#elctrnNticInfoInqireDt").val(result.detail.elctrnNticInfoInqireDt);
			module.$("#excclcYn").val(result.detail.excclcYn);
			module.$("#vat").val(result.detail.vat);
			module.$("#vatYn").val(result.detail.vatYn);
			module.$("#prcepturSe").val(result.detail.prcepturSe);
			module.$("#giroRcivPlace").val(result.detail.giroRcivPlace);
			module.$("#giroRcivSe").val(result.detail.giroRcivSe);
			module.$("#cmsn").val(result.detail.cmsn);
			module.$("#closYn").val(result.detail.closYn);
			module.$("#deptcd").val(result.detail.deptcd);
			module.$("#charger").val(result.detail.charger);
			module.$("#opertSe").val(result.detail.opertSe);
			module.$("#orginlNticChrgeKnd").val(result.detail.orginlNticChrgeKnd);
			module.$("#orginlNticAccnutYear").val(result.detail.orginlNticAccnutYear);
			module.$("#orginlNticNo").val(result.detail.orginlNticNo);
			module.$("#elctrnTaxbilIsuYn").val(result.detail.elctrnTaxbilIsuYn);	
			module.$("#displayBeginDt").val(result.detail.beginDt);
			module.$("#displayEndDt").val(result.detail.endDt);
			module.$("#nticMth").val(result.detail.nticMth);
			module.$("#regUsr").val(result.detail.regUsr);
			module.$("#registDt").val(result.detail.registDt);
			module.$("#updUsr").val(result.detail.updUsr);
			module.$("#updtDt").val(result.detail.updtDt);
			module.$("#nticCnt").val(result.detail.nticCnt);
			module.$("#prtAtCode").val(result.detail.prtAtCode);
			module.$("#mngYear").val(result.detail.mngYear);
			module.$("#mngNo").val(result.detail.mngNo);
			module.$("#mngCnt").val(result.detail.mngCnt);
	 	});
	});
};


/**
 * 정의 된 버튼 클릭 시
 */
GamNticPayListModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case "searchBtn":
			var searchOpt = this.makeFormArgs("#nticPayListForm");
		 	this.$("#nticPayList").flexOptions({params:searchOpt}).flexReload(); 
		break;
		
		// 세입리스트 엑셀 다운로드
		case 'btnNticPayListExcelDownload':
			this.$('#nticPayList').flexExcelDown();
		break;
		
		// 연체세입리스트 엑셀 다운로드
		case 'btnDelayNticPayListExcelDownload':
			this.$('#delayNticPayList').flexExcelDown();
		break;
		
		// 업체조회 팝업
		case "searchEntrpsCdBtn":
			this.doExecuteDialog("searchEntrpsCdPopup", "업체조회", '<c:url value="/popup/showEntrpsInfo.do"/>', {});
		break;
	}
};


/**
 * 탭 변경시 실행 이벤트
 */
 GamNticPayListModule.prototype.onTabChange = function(newTabId, oldTabId) {
	
	switch(newTabId) {
		case "tabs1": break;
		case "tabs2": break;
	}
};
/**
 * 팝업 close 이벤트
 */
GamNticPayListModule.prototype.onClosePopup = function(popupId, msg, value){
	
	switch(popupId){
		
		// 업체조회화면
		case "searchEntrpsCdPopup":
			this.$("#entrpscd").val(value["entrpscd"]);
			this.$("#entrpsNm").val(value["entrpsNm"]);
		break;
	
		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			throw 0;
		break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamNticPayListModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="nticPayListForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항코드</th>
                            <td width="15%">
                                <select id="prtAtCode">
                                    <option value="" selected="selected">선택</option>

                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.code }">${prtAtCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
							<th>고지일자</th>
                            <td>
                            	<input id="sGrUsagePdFrom" type="text" class="emdcal" size="10">
                            	 ~ 
                            	<input id="sGrUsagePdTo" type="text" class="emdcal" size="10">
                            </td>
                            <th>요금종류</th>
                            <td>
                                <input id="chrgeKndCd" type="text" size="10"> 
                                <input id="chrgeKndNm" type="text" size="10"> 
                                <button id="popupChrgeKndCd">요금</button>
                            </td>
                            <td rowspan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
                        </tr>
						<tr>
							<th>수납구분</th>
							<td>
								<select id="rcivSe">
                                    <option value="">선택</option>
                                    <c:forEach items="${rcivSeCdList}" var="rcivSeItem">
                                        <option value="${rcivSeItem.code }">${rcivSeItem.codeNm}</option>
                                    </c:forEach>
                                </select>
							</td>
							<th>회계년도</th>
							<td>
								<input type="text" size="10" id="fiscalYr"/>
							</td>
							<th>고지번호</th>
							<td>
								<input type="text" size="10" id="nticno"/>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel">
		<div id="nticPayListTab" class="emdTabPanel" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">세입</a></li>
				<li><a href="#tabs2" class="emdTab">연체세입</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="nticPayList" style="display:none"></table>
				<div class="emdControlPanel">
					<button id="btnNticPayListExcelDownload">엑셀</button>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage">
				<table id="delayNticPayList" style="display:none"></table>
				<div class="emdControlPanel">
					<button id="btnDelayNticPayListExcelDownload">엑셀</button>
				</div>
			</div>
		</div>
	</div>
</div>