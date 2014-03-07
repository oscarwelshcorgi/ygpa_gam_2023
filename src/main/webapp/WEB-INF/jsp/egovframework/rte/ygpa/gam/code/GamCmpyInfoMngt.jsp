<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamCmpyInfoMngt.jsp
  * @Description : 업체정보관리 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.03.05  kok          최초 생성
  *
  * author kok
  * since 2014.03.05
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamCmpyInfoMngtModule() {}

GamCmpyInfoMngtModule.prototype = new EmdModule(840, 540);

// 페이지가 호출 되었을때 호출 되는 함수
GamCmpyInfoMngtModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#cmpyInfoMngtList").flexigrid({
		module: this,
		url: '<c:url value="/code/gamCmpyInfoMngtList.do" />',
		dataType: "json",
		colModel : [
					{display:"업체코드", 		name:"entrpscd",		width:60, 	sortable:false,		align:"center"},
					{display:"업체명", 			name:"entrpsNm",		width:200, 	sortable:false,		align:"center"},
					{display:"업체유형", 		name:"entrpsTy",		width:50, 	sortable:false,		align:"center"},
					{display:"사업자구분", 		name:"bsnmSe",			width:100, 	sortable:false,		align:"center"},
					{display:"대표자명", 		name:"rprsntvNm",		width:80, 	sortable:false,		align:"center"},
					{display:"사업자등록번호", 	name:"bizrno",			width:80, 	sortable:false,		align:"center"},
					{display:"법인등록번호", 		name:"cprregistno",		width:80, 	sortable:false,		align:"center"},
					{display:"업종", 			name:"induty",			width:40, 	sortable:false,		align:"center"},
					{display:"업태", 			name:"bizcnd",			width:40, 	sortable:false,		align:"center"},
					{display:"전화번호", 		name:"tlphonNo",		width:80, 	sortable:false,		align:"center"},
					{display:"팩스", 			name:"fax",				width:80, 	sortable:false,		align:"center"},
					{display:"우편번호", 		name:"zip",				width:60, 	sortable:false,		align:"center"},
					{display:"주소", 			name:"adres",			width:120, 	sortable:false,		align:"center"},
					{display:"등록자", 			name:"regUsr",			width:40, 	sortable:false,		align:"center"},
					{display:"등록일자", 		name:"registDt",		width:60, 	sortable:false,		align:"center"},
					],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: "220",
	});
	
	this.$("#cmpyInfoMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#cmpyInfoMngtListTab").tabs("option", {active: 1});			// 탭을 전환 한다.

		module.doAction('<c:url value="/code/cmpyInfoMngtDetail.do" />', {entrpscd: row["entrpscd"]}, function(module, result) {

			// value 설정
			module.$("#cmd").val("modify");
			module.$("#entrpscd").val(result.detail.entrpscd);										// 업체ID(hidden)
			module.$("#entrpsNm").val(result.detail.entrpsNm);										// 업체명
			module.$("#bizrno").val(result.detail.bizrno);											// 사업자등록번호
			module.$("#entrpsTy").val(result.detail.entrpsTy).attr("selected","selected");			// 업체유형
			module.$("#bsnmSe").val(result.detail.bsnmSe).attr("selected","selected");				// 사업자구분
			module.$("#cprregistno").val(result.detail.cprregistno);								// 법인등록번호
			module.$("#induty").val(result.detail.induty);											// 업종
			module.$("#bizcnd").val(result.detail.bizcnd);											// 업태
			module.$("#tlphonNo").val(result.detail.tlphonNo);										// 전화번호
			module.$("#fax").val(result.detail.fax);												// 팩스
			module.$("#zip").val(result.detail.zip);												// 우편번호
			module.$("#adres").val(result.detail.adres);											// 주소
	 	});
	});
};
		

/**
 * 정의 된 버튼 클릭 시
 */
GamCmpyInfoMngtModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case "searchBtn":
			var searchOpt = this.makeFormArgs("#cmpyInfoMngtForm");
		 	this.$("#cmpyInfoMngtList").flexOptions({params:searchOpt}).flexReload(); 
		break;
	
		// 업체조회 팝업
		case "searchEntrpsCdBtn":
			this.doExecuteDialog("searchEntrpsCdPopup", "업체조회", '<c:url value="/popup/showEntrpsInfo.do"/>', {});
		break;
	
		// 추가
		case "addBtn":
			this.$("#cmpyInfoMngtListTab").tabs("option", {active: 1});
			this.$("#cmpyInfoMngtManageVO :input").val("");
			this.$("#cmd").val("insert");
		break;

		// 업체정보 담당자 정보 추가
		case "chargerAddBtn":
			this.$("#cmpyInfoMngtListTab").tabs("option", {active: 2});
			this.$("#cmpyChargerMngtManageVO :input").val("");
			this.$("#chargerCmd").val("insert");
		break;
			
		// 저장
		case "saveBtn":

		 	var inputVO = this.makeFormArgs("#cmmnCodeDetailManageVO");
			if(this.$("#cmd").val() == "insert") {
			 	this.doAction('<c:url value="/code/gamCcmCmmnDetailCodeRegist.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmpyInfoMngtForm");
						module.$("#cmpyInfoMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmpyInfoMngtListTab").tabs("option", {active: 0}); 
						module.$("#cmmnCodeDetailManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/code/gamCcmCmmnDetailCodeModify.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmpyInfoMngtForm");
						module.$("#cmpyInfoMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmpyInfoMngtListTab").tabs("option", {active: 0}); 
						module.$("#cmmnCodeDetailManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
		
		
		// 업체담당자 정보 저장
		case "chargerSaveBtn":

		 	var inputVO = this.makeFormArgs("#cmpyChargerMngtManageVO");
			if(this.$("#chargerCmd").val() == "insert") {
				this.$("#chargerEntrpscd").val(this.$("#entrpscd").val());
				
			 	this.doAction('<c:url value="/code/gamCmpyChargerMngtRegist.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			alert("0");
			 			/*
			 			var searchOpt = module.makeFormArgs("#cmpyInfoMngtForm");
						module.$("#cmpyInfoMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmpyInfoMngtListTab").tabs("option", {active: 0}); 
						module.$("#cmmnCodeDetailManageVO :input").val("");
						*/
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/code/gamCmpyChargerMngtModify.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmpyInfoMngtForm");
						module.$("#cmpyInfoMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmpyInfoMngtListTab").tabs("option", {active: 0}); 
						module.$("#cmmnCodeDetailManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
		
		// 삭제
		case "deleteBtn":
			if(confirm("삭제하시겠습니까?")){
				var inputVO = this.makeFormArgs("#cmmnCodeDetailManageVO");
			 	this.doAction('<c:url value="/code/gamCcmCmmnDetailCodeRemove.do" />', {codeId : this.$("#codeId").val(), code : this.$("#code").val()}, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmpyInfoMngtForm");
						module.$("#cmpyInfoMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmpyInfoMngtListTab").tabs("option", {active: 0}); 
						module.$("#cmmnCodeDetailManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
	}
};


GamCmpyInfoMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
		case "tabs1":
		break;
	
		case "tabs2":
			var row = this.$("#cmpyInfoMngtList").selectedRows();
			if(row.length == 0){
				this.$("#cmd").val("insert");
			}else{
				this.$("#cmd").val("modify");
			}
		break;
	}
};


/**
 * 팝업 close 이벤트
 */
 GamCmpyInfoMngtModule.prototype.onClosePopup = function(popupId, msg, value){
	
	switch(popupId){
		
		// 상세화면
		case "searchGisCodePopup":
			this.$("#gisAssetsPrtAtCode").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsSubCd").val(value["gisAssetsSubCd"]);
			this.$("#gisAssetsCd").val(value["gisAssetsCd"]);
			
			this.$("#gisAssetsLocplc").val(value["gisAssetsLocplc"]); 			// 소재지
			this.$("#gisAssetsLnm").val(value["gisAssetsLnm"]);					// 지번
			this.$("#gisAssetsLnmSub").val(value["gisAssetsLnmSub"]);			// 서브지번
		break;

		// 조회화면
		case "searchGisCodePopup2":
			this.$("#searchAssetsCd").val(value["gisAssetsCd"]);
			this.$("#searchAssetsSubCd").val(value["gisAssetsSubCd"]);
		break;

		// 업체조회화면
		case "searchEntrpsCdPopup":
			this.$("#searchEntrpsCd").val(value["entrpscd"]);
			this.$("#searchEntrpsNm").val(value["entrpsNm"]);
		break;
	
		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			throw 0;
		break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamCmpyInfoMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="cmpyInfoMngtForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>업체 명</th>
							<td>
								<input type="text" size="7" id="searchEntrpsCd" />&nbsp;-&nbsp;
								<input type="text" size="20" id="searchEntrpsNm" />&nbsp;<button id="searchEntrpsCdBtn">업체조회</button>
							</td>
							<th>업체유형</th>
							<td width="10%">  		
								<select id="searchCondition" class="select" title="searchCondition">
									<option selected="selected">&nbsp;&nbsp;전체&nbsp;&nbsp;</option>
									<option value="C">대리점</option>
									<option value="E">기타</option>
									<option value="F">하역사명</option>
									<option value="S">선사</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>사업자번호</th>
							<td>
								<input type="text" size="10" id="searchEntrpsCd" />
							</td>
							<th>사업자구분</th>
							<td width="10%">  		
								<select id="searchCondition" class="select" title="searchCondition">
									<option selected="selected">&nbsp;&nbsp;전체&nbsp;&nbsp;</option>
									<option value="620">여수</option>
									<option value="621">여천</option>
									<option value="622">광양</option>
									<option value="625">하동항</option>
									<option value="626">월내분실</option>
									<option value="627">중흥분실</option>
									<option value="629">광양항</option>
									<option value="630">여수권역항</option>
									<option value="640">마린센터</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="emdControlPanel">
					<button id="searchBtn">조회</button>
				</div>
			</form>
		</div>
	</div>

	<div class="emdPanel">
		<div id="cmpyInfoMngtListTab" class="emdTabPanel" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">업체정보 목록</a></li>
				<li><a href="#tabs2" class="emdTab">업체정보 상세</a></li>
				<li><a href="#tabs3" class="emdTab">업체담당자 정보</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="cmpyInfoMngtList" style="display:none"></table>
				<div class="emdControlPanel">
					<button id="addBtn">추가</button>
					<button id="deleteBtn">삭제</button>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage" style="height:300px; overflow: scroll;">
				<form id="cmpyInfoMngtManageVO">
					<input type="hidden" id="cmd"/>
					<input type="hidden" id="entrpscd"/>
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">업체 명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="entrpsNm" /></td>
							<th width="20%" height="23" class="required_text">사업자등록번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="10" id="bizrno"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">업체 유형<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td>
								<select id="entrpsTy">
									<option value="C">대리점</option>
									<option value="E">기타</option>
									<option value="F">하역사명</option>
									<option value="S">선사</option>
									<option value="A">A</option>
									<option value="W">W</option>
								</select>
							</td>
							<th width="20%" height="23" class="required_text">사업자구분<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td>
								<select id="bsnmSe">
									<option value="1">세금계산서</option>
									<option value="2">고지납부</option>
									<option value="3">계약</option>
									<option value="4">영업</option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">법인등록번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td colspan="3"><input type="text" id="cprregistno" size="13" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">업종<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td>
								<select id="induty">
									<option value="11.0">내 항 화 물</option>
									<option value="12.0">외 항 화 물</option>
									<option value="21.0">내 항 여 객</option>
									<option value="22.0">외 항 여 객</option>
									<option value="23.0">국내 해운 대리점</option>
									<option value="24.0">국제 해운 대리점</option>
									<option value="31.0">항 만 하 역 업</option>
									<option value="32.0">항 만 용 역 업</option>
									<option value="33.0">물 품 공 급 업</option>
									<option value="34.0">선 박 급 유 업</option>
									<option value="35.0">예 선 업</option>
									<option value="36.0">컨테이너 수리업</option>
									<option value="37.0">검 수 업</option>
									<option value="38.0">검 량 감 정 업</option>
									<option value="41.0">화 물 운 송 주 선 업</option>
									<option value="42.0">해 운 중 개 업</option>
									<option value="43.0">대 리 점</option>
									<option value="44.0">선 박 대 여 업</option>
									<option value="45.0">선 박 관 리 업</option>
									<option value="46.0">도 선 업</option>
									<option value="81.0">부 선 관련 업체</option>
									<option value="82.0">100톤미만 자영업</option>
									<option value="83.0">공사작업 관련업</option>
									<option value="84.0">수산 관련 업체</option>
									<option value="91.0">항 내 운 항</option>
									<option value="92.0">조 선 소</option>
									<option value="93.0">외국선사국내지사</option>
									<option value="94.0">원 양 어 업</option>
									<option value="95.0">연 안 어 업</option>
									<option value="96.0">선 박 수 리</option>
									<option value="99.0">기 타</option>
								</select>
							</td>
							<th width="20%" height="23" class="required_text">업태<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="bizcnd"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">전화번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="tlphonNo" /></td>
							<th width="20%" height="23" class="required_text">팩스<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="fax"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">우편번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="zip" /></td>
							<th width="20%" height="23" class="required_text">주소<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="adres"/></td>
						</tr>
					</table>
					<table id="cmpyInfoMngtList" style="display:none"></table>
				</form>
				<div class="emdControlPanel">
					<button id="chargerAddBtn">추가</button>
					<button id="deleteBtn">편집</button>
					<button id="deleteBtn">삭제</button>
					<button id="deleteBtn">업체정보 저장</button>
				</div>
			</div>
			
			
			<!-- 업체담당자 정보 -->
			<div id="tabs3" class="emdTabPage" style="height:300px; overflow: scroll;">
				<form id="cmpyChargerMngtManageVO">
					<input type="hidden" id="chargerCmd"/>
					<input type="hidden" id="chargerEntrpscd"/>
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">담당자 명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="chargerNm" /></td>
							<th width="20%" height="23" class="required_text">담당자 직위<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="10" id="chargerOfcPos"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">부서<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td colspan="3"><input type="text" size="10" id="chargerDept"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">담당 업무<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td>
								<select id="chrgJob">
									<option value="1">세금계산서</option>
									<option value="2">고지납부</option>
									<option value="3">계약</option>
									<option value="4">영업</option>
								</select>
							</td>
							<th width="20%" height="23" class="required_text">관리부서<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td>
								<select class="select" id="mngDeptCd">
									<c:forEach var="result" items="${ogrnztId_result}" varStatus="status">
										<option value='<c:out value="${result.codeId}"/>'><c:out value="${result.codeNm}"/></option>
									</c:forEach>			  		   
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">휴대폰<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="chargerMoblphonNo" /></td>
							<th width="20%" height="23" class="required_text">전화번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="chargerTlphonNo"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">팩스<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="chargerFax" /></td>
							<th width="20%" height="23" class="required_text">이메일<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="chargerEmail"/></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="chargerSaveBtn">적용</button>
				</div>
			</div>
		</div>
	</div>
</div>