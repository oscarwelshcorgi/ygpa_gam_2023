<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
   * @Class Name : GamCntnrQuayPdRntfeeSttusInqire.jsp
  * @Description : 컨테이너부두기간별사용료현황조회 테스트 (Prototype)
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2013.11.15  장은성          최초 생성
  *
  * author 장은성
  * since 2013.11.15
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetEvlDtlsInqiryModule() {}

GamAssetEvlDtlsInqiryModule.prototype = new EmdModule();

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetEvlDtlsInqiryModule.prototype.loadComplete = function() {
	this.$('#prtAtCode').val('622');	// 기본 항코드 설정
	
	this.$('#submitButton').button().click({parent: this}, function(event) {
		event.data.parent.onSubmit();
	});
	
	// 테이블 설정
	this.$("#assetEvlDtlsList").flexigrid({
		url: '/asset/inqry/selectAssetEvlDtlsInqryList.do',
		dataType: 'json',
		colModel : [
				{display:'부두 항코드', name:'PRT_AT_CODE',width:100, sortable:false,align:'center'},
			{display:'임대 업체구분', name:'ENTRPS_SE',width:100, sortable:false,align:'center'},
			{display:'그룹 코드', name:'QUAY_GROUP_CD',width:100, sortable:false,align:'center'},
			{display:'선석 시설코드',name:'BERTH_CD',width:140,sortable:false,align:'center'},
			{display:'선석명',name:'BERTH_NM',width:100,sortable:false,align:'center'},
			{display:'부두코드',name:'QUAY_CD',width:140,sortable:false,align:'center'},
			{display:'부두명',name:'QUAY_NAME',width:140,sortable:false,align:'center'},
			{display:'사용료', name:'FEE',width:200, sortable:false,align:'center'},
			{display:'사용 통계 월',name:'USAGE_MT',width:160,sortable:false,align:'center'},
			{display:'기간별 사용료',name:'FEE',width:160,sortable:false,align:'center'},
			{display:'사용 면적',name:'USAGE_AR',width:160,sortable:false,align:'center'},
			{display:'사용 기간 FROM',name:'USAGE_PD_FROM',width:160,sortable:false,align:'center'},
			{display:'사용 기간 TO',name:'USAGE_PD_TO',width:160,sortable:false,align:'center'},
			{display:'면제 구분',name:'EXEMPT_SE',width:160,sortable:false,align:'center'},
			{display:'면제 구분 구분',name:'EXEMPT_RSN_CD',width:160,sortable:false,align:'center'},
			{display:'면제 사유',name:'EXEMPT_RSN',width:160,sortable:false,align:'center'},
			{display:'면제 기간 From',name:'EXEMPT_PD_FROM',width:160,sortable:false,align:'center'},
			{display:'면제 기간 To',name:'EXEMPT_PD_TO',width:160,sortable:false,align:'center'},
			{display:'등록자',name:'REG_USR',width:160,sortable:false,align:'center'},
			{display:'등록 일시',name:'REGIST_DT',width:160,sortable:false,align:'center'},
			{display:'수정자',name:'UPD_USR',width:160,sortable:false,align:'center'},
			{display:'수정 일시',name:'UPDT_DT',width:160,sortable:false,align:'center'}
			],
/* 		searchitems : [
			{display: 'ISO', name : 'iso'},
			{display: 'Name', name : 'name', isdefault: true}
			],
		sortname: "iso",
		sortorder: "asc",
		 */
		usepager: false,
//		title: '자산목록',
//		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
//		width: '100%',
//		height: '100%'
/* 		onSubmit: addFormData, */
	});
};
		
// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
GamAssetEvlDtlsInqiryModule.prototype.showModuleAlert = function(msg) {
	//this.getSelect(msg);	
	this.$('#prtCode').val(msg);
}

GamAssetEvlDtlsInqiryModule.prototype.onClick = function(e) {
	switch(e.target_id) {
	case 'addAssetCd':
		break;
	case 'removeAssetCd':
		break;
	case 'editAssetCd':
		break;
	}
}

GamAssetEvlDtlsInqiryModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
}

GamAssetEvlDtlsInqiryModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#searchForm');
	//this.showAlert(searchOpt);
 	this.$('#assetEvlDtlsList').flexOptions({params:searchOpt}).flexReload(); 
//	this.$('#assetList').flexOptions(searchOpt).flexReload();
}

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetEvlDtlsInqiryModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId }'/>
<div class="window_main">
	<div class="emdPanel">
			<form id="searchForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>청코드</th>
							<td><input id="prtAtCode" type="text" size="3"></td>
							<th>취득일자</th>
							<td><input id="acqDateFrom" type="text" class="emdcal"
								size="8"> ~ <input id="acqDateTo" type="text"
								class="emdcal" size="8"></td>
							<th>자산코드</th>
							<td><input id="gisAssetCode" type="text" size="6">-<input
								id="gisAssetSubCode" type="text" size="6"><button id="popupFcltyCd" class="popupButton">시설조회</button></td>
							<th>재산코드</th>
							<td><select id="prprtyCd">
									<option value="" selected="selected">선택</option>
									<option value="A">건물</option>
									<option value="L">토지</option>
									<option value="S">공작물</option>
									<option value="w">창고</option>
									<option value="E">기타</option>
							</select></td>
							<td rowSpan="2"><button id="submitButton" href="#">조회</button></td>
						</tr>
						<tr>
							<th>자산명</th>
							<td colspan="3"><input id="assetNm" type="text" size="36"></td>
							<th>관리부서</th>
							<td><select id="mngDeptCd"></select></td>
							<th>운영부서</th>
							<td><select id="operDeptCd"></select></td>
						</tr>
					</tbody>
				</table>
			</form>
	</div>

	<div class="emdPanel">
				<table id="assetEvlDtlsList" style="display:none"></table>
				<div class="emdSummaryPanel"><div class="summaryLabel">조회 갯수 : </div><div id="totalCnt" class="summaryResult">0 개 로드 됨</div><div class="summaryLabel">자산가액 합계 : </div><div id="totalValAmount" class="summaryResult">0 원</div><div class="summaryLabel">취득가액 합계 : </div><div id="totalRecAmount" class="summaryResult">0 원</div></div>

				<div class="emdControlPanel"><button id="toExcel">엑셀(XLS) 저장</button><button id="loadMap">맵조회</button></div>
	</div>
</div>
