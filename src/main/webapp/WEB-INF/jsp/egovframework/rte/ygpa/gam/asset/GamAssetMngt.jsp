<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetMngt.jsp
  * @Description : 자산코드관리 테스트 (Prototype)
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2013.10.29  장은성          최초 생성
  *
  * author 장은성
  * since 2013.10.29
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetCodeModule() {}

GamAssetCodeModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetCodeModule.prototype.loadComplete = function() {
	this.$('#prtAtCode').val('820');	// 기본 항코드 설정
	
	// 테이블 설정
	this.$("#erpAssetCodeList").flexigrid({
		module: this,
		url: '<c:url value="/asset/selectErpAssetCodeList.do"/>',
		dataType: 'json',
		colModel : [
			{display:'등록', name:'regYn', width:44, sortable:true, align:'left'},
			{display:'자산구분', name:'assetCls', width:66, sortable:true, align:'left'},
			{display:'자산번호', name:'assetNo', width:62, sortable:true, align:'right'},
			{display:'자산번호순번', name:'assetNoSeq', width:128, sortable:true, align:'left'},
			{display:'자산관리번호', name:'assetMngtNo', width:80, sortable:true, align:'right'},
			{display:'품목', name:'itemCls', width:32, sortable:true, align:'center'},
			{display:'품명', name:'itemName', width:200, sortable:true, align:'center'},
			{display:'취득일자', name:'buyDate', width:64, sortable:true, align:'center'},
			{display:'현재수량', name:'curQty', width:128, sortable:true, align:'right', displayFormat: 'number'},
			{display:'취득금액', name:'deprctnAmt', width:128, sortable:true, align:'right', displayFormat: 'number'},
			{display:'취득구분', name:'buyCls', width:40, sortable:true, align:'center'},
			{display:'구매구분', name:'purCls', width:40, sortable:true, align:'center'},
			{display:'구매용도', name:'purPurpose', width:200, sortable:true, align:'center'},
			{display:'구입처', name:'purCust', width:200, sortable:true, align:'center'},
			{display:'회계단위구분', name:'accUnitCls', width:16, sortable:true, align:'center'},
			{display:'프로젝트코드', name:'projectCd', width:64, sortable:true, align:'center'},
			{display:'장소코드', name:'placeCd', width:32, sortable:true, align:'center'},
			{display:'부서코드', name:'deptCd', width:64, sortable:true, align:'center'},
			{display:'사원번호', name:'empNo', width:80, sortable:true, align:'center'},
			{display:'모델명', name:'modelName', width:240, sortable:true, align:'center'},
			{display:'자산규격', name:'assetSize', width:200, sortable:true, align:'center'},
			{display:'제품일련번호', name:'productSeqNo', width:200, sortable:true, align:'center'},
			{display:'제조업체명', name:'makerName', width:200, sortable:true, align:'center'},
			{display:'계정코드', name:'accntCd', width:64, sortable:true, align:'center'},
			{display:'상각구분', name:'deprctnCls', width:8, sortable:true, align:'center'},
			{display:'자산내용년수', name:'assetFixTerm', width:128, sortable:true, align:'center'},
			{display:'변동구분', name:'changeCls', width:8, sortable:true, align:'center'},
			{display:'변동일자', name:'changeDate', width:64, sortable:true, align:'center'},
			{display:'변동금액', name:'changeAmt', width:128, sortable:true, align:'center'},
			{display:'사진이미지', name:'picImage', width:0, sortable:true, align:'center'},
			{display:'입력자코드', name:'inputEmpNo', width:80, sortable:true, align:'center'},
			{display:'입력일자', name:'inputDate', width:128, sortable:true, align:'center'},
			{display:'수정자코드', name:'updateEmpNo', width:80, sortable:true, align:'center'},
			{display:'수정일자', name:'updateDate', width:128, sortable:true, align:'center'}
			],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: '300'
	});
	
	this.$("#erpAssetCodeList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#assetManageTab").tabs("option", {active: 1});	// 탭을 전환 한다.
		module.$('#searchGisErpAssetCls').val(row['assetCls']);
		module.$('#searchGisErpAssetNo').val(row['assetNo']);
		module.$('#searchGisErpAssetNoSeq').val(row['assetNoSeq']);
		// 해당하는 자산 목록을 불러온다/
		var searchOpt=module.makeFormArgs('#searchForm');
		//this.showAlert(searchOpt);
	 	module.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
	});
	
	this.$("#erpAssetCodeList").on('onItemSelected', function(event, module, row, grid, param) {
		//alert('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is selected');
	});
	
	this.$("#erpAssetCodeList").on('onItemUnSelected', function(event, module, row, grid, param) {
		//alert('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is unselected');
	});
	
	this.$("#assetCodeList").flexigrid({
		url: '<c:url value="/asset/selectGisAssetCodeList.do"/>',
		dataType: 'json',
		colModel : [
			{display:'항코드', name:'GIS_ASSETS_PRT_AT_CODE', width:24, sortable:true, align:'center'},
			{display:'코드', name:'GIS_ASSETS_CD', width:24, sortable:true, align:'center'},
			{display:'SUB 코드', name:'GIS_ASSETS_SUB_CD', width:16, sortable:true, align:'center'},
			{display:'자산명', name:'GIS_ASSETS_NM', width:240, sortable:true, align:'center'},
			{display:'관리 부서 코드', name:'GIS_ASSETS_MNG_DEPT_CD', width:160, sortable:true, align:'center'},
			{display:'운영 부서 코드', name:'GIS_ASSETS_OPER_DEPT_CD', width:160, sortable:true, align:'center'},
			{display:'소재지', name:'GIS_ASSETS_LOCPLC', width:240, sortable:true, align:'left'},
			{display:'지번', name:'GIS_ASSETS_LNM', width:40, sortable:true, align:'center'},
			{display:'지번SUB', name:'GIS_ASSETS_LNM_SUB', width:40, sortable:true, align:'center'},
			{display:'면적', name:'GIS_ASSETS_AR', width:64, sortable:true, align:'center'},
			{display:'사용 여부', name:'GIS_ASSETS_USAGE_YN', width:8, sortable:true, align:'center'},
			{display:'취득가액', name:'GIS_ASSETS_ACQ_PRI', width:104, sortable:true, align:'right'},
			{display:'규격', name:'GIS_ASSETS_STNDRD', width:120, sortable:true, align:'center'},
			{display:'준공년도', name:'GIS_ASSETS_BLDDATE', width:32, sortable:true, align:'center'},
			{display:'준공 일자', name:'GIS_ASSETS_BLD_DT', width:128, sortable:true, align:'center'},
			{display:'등록자', name:'REG_USR', width:160, sortable:true, align:'center'},
			{display:'등록일자', name:'REGISTDT', width:128, sortable:true, align:'center'},
			{display:'수정자', name:'UPD_USR', width:160, sortable:true, align:'center'},
			{display:'수정일자', name:'UPDTDT', width:128, sortable:true, align:'center'},
			{display:'부두 그룹 코드', name:'GIS_ASSETS_QUAY_GROUP_CD', width:80, sortable:true, align:'center'},
			{display:'위치 코드', name:'GIS_ASSETS_LOC_CD', width:80, sortable:true, align:'center'},
			{display:'구분 코드', name:'GIS_ASSETS_SE_CD', width:80, sortable:true, align:'center'},
			{display:'재산 구분 코드', name:'GIS_ASSETS_PRPRTY_SE_CD', width:80, sortable:true, align:'center'},
			{display:'출자 방식', name:'GIS_ASSETS_INVSTMNT_MTHD', width:80, sortable:true, align:'center'},
			{display:'실제 임대 면적', name:'GIS_ASSETS_REAL_RENT_AR', width:64, sortable:true, align:'center'},
			{display:'도면 목록 등록 년도', name:'DRW_LST_REGIST_YEAR', width:32, sortable:true, align:'center'},
			{display:'도면 목록 순번', name:'DRW_LST_SEQ', width:32, sortable:true, align:'center'},
			{display:'가치 금액', name:'GIS_ASSETS_VAL_AMT', width:120, sortable:true, align:'center'},
			{display:'가치 조회 일자', name:'GIS_ASSETS_VAL_INQIRE_DT', width:64, sortable:true, align:'center'}
			],
		usepager: false,
//		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: '140'
	});
	
	this.$("#assetCodePhotoList").flexigrid({
		url: '<c:url value="/code/mngt/selectAssetCodeList.do"/>',
		dataType: 'json',
		colModel : [
				{display:'GIS 자산 항코드', name:'GIS_ASSETS_PRT_AT_CODE', width:24, sortable:true, align:'center'},
				{display:'GIS 자산 코드', name:'GIS_ASSETS_CD', width:24, sortable:true, align:'center'},
				{display:'GIS 자산 SUB 코드', name:'GIS_ASSETS_SUB_CD', width:16, sortable:true, align:'center'},
				{display:'사진 순번', name:'PHOTO_SEQ', width:16, sortable:true, align:'center'},
				{display:'사진 제목', name:'PHOTO_SJ', width:300, sortable:true, align:'center'},
				{display:'파일명', name:'FILENM_LOGIC', width:200, sortable:true, align:'left'},
				{display:'촬영 일시', name:'SHOT_DT', width:128, sortable:true, align:'center'},
				{display:'등록자', name:'REG_USR', width:160, sortable:true, align:'center'},
				{display:'등록일시', name:'REGIST_DT', width:128, sortable:true, align:'center'}
			],
		usepager: false,
//		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: '120'
	});
};
		
// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
GamAssetCodeModule.prototype.showModuleAlert = function(msg) {
	//this.getSelect(msg);	
	this.$('#prtCode').val(msg);
};

GamAssetCodeModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'selectErpAssetCode':
		var searchOpt=this.makeFormArgs('#searchErpAssetCode');
	 	this.$('#erpAssetCodeList').flexOptions({params:searchOpt}).flexReload(); 
		break;
		
	case 'selectGisAssetCode':
		var searchOpt=this.makeFormArgs('#searchGisAssetCode');
	 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
		break;
	case 'addAssetCd':
		break;
	case 'removeAssetCd':
		break;
	case 'editAssetCd':
		break;
	}
};

GamAssetCodeModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case 'tabs1':
		this.$('#searchViewStack')[0].changePanelId(0);
		break;
	case 'tabs2':
		this.$('#searchViewStack')[0].changePanelId(1);
		break;
	case 'tabs3':
		this.$('#searchViewStack')[0].changePanelId(1);
		break;
	}
};

GamAssetCodeModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
};

GamAssetCodeModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#searchErpAssetCode');
	//this.showAlert(searchOpt);
 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
//	this.$('#assetList').flexOptions(searchOpt).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetCodeModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId }'/>
<div class="window_main">
	<div class="emdPanel">
			<div id="searchViewStack" class="viewStack">
				<div class="viewPanel">
		<form id="searchErpAssetCode">
					<table style="width:100%;" class="searchPanel">
						<tbody>
							<tr>
								<th>자산구분</th>
								<td><select id="assetCls">
										<option value="" selected="selected">선택</option>
										<c:forEach  items="${erpAssetClsList}" var="clsItem">
											<option value="${clsItem.smCls }">${clsItem.smClsName }</option>
										</c:forEach>
								</select>
								</td>
								<th>품목구분</th>
								<td><select id="prprtyCd">
										<option value="" selected="selected">선택</option>
										<option value="A">건물</option>
										<option value="L">토지</option>
										<option value="S">공작물</option>
										<option value="w">창고</option>
										<option value="E">기타</option>
								</select></td>
								<th>취득일자</th>
								<td><input id="acqDateFrom" type="text" class="emdcal"
									size="8"> ~ <input id="acqDateTo" type="text"
									class="emdcal" size="8"></td>
								<th>자산번호</th>
								<td><input id="gisAssetCode" type="text" size="6">-<input
									id="gisAssetSubCode" type="text" size="6"></td>
								<td rowSpan="2"><button id="selectErpAssetCode" class="submit">조회</button></td>
							</tr>
							<tr>
								<th>품명</th>
								<td colspan="3"><input id="itemName" type="text" size="36"></td>
								<th>부서</th>
								<td width="200px" colspan="3"><select id="deptCd"><option value="" selected="selected">선택</option>
										<option value="GRP0001">재무회계팀</option>
										<option value="GRP0002">경영지원팀</option>
										<option value="GRP0003">경영기획팀</option>
										<option value="GRP0004">물류기획팀</option>
										<option value="GRP0005">항만운영팀</option>
										<option value="GRP0006">항만건설팀</option>
										<option value="GRP0007">항만시설팀</option>
										<option value="GRP0008">여수사업소</option>
										</select></td>

							</tr>
						</tbody>
					</table>
					</form>
					</div>
				<div class="viewPanel">
		<form id="searchGisAssetCode">
						<table class="searchPanel">
							<tbody>
							<tr>
								<th>청코드</th>
								<td><input id="prtAtCode" type="text" size="3"></td>
								<th>ERP 자산코드</th>
								<td><input id="searchGisErpAssetCls" type="text" size="1">-<input id="searchGisErpAssetNo" type="text" size="4">-<input id="searchGisErpAssetNoSeq" type="text" size="2"></td>
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
								<td rowSpan="2"><button id="selectGisAssetCode" class="submit">조회</button></td>
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
			</div>
	</div>

	<div class="emdPanel">
		<div id="assetManageTab" class="emdTabPanel" style="height:100%;" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">ERP자산정보</a></li>
				<li><a href="#tabs2" class="emdTab">GIS자산목록</a></li>
				<li><a href="#tabs3" class="emdTab">자산사진</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
				<div style="width: 100%; height: 100%; overflow:auto">
						<table id="erpAssetCodeList" style="display:none"></table>
				</div>
				<div class="emdControlPanel"><button id="addAssetGisCd">자산등록</button></div>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: scroll;" data-onactivate="onShowTab2Activate">
				<table id="assetCodeList" style="display:none"></table>
				<div class="emdControlPanel"><button id="addAssetGisCd">추가</button><button id="removeAssetGisCd">삭제</button><button id="loadMap">지도보기</button></div>
				<table>
					<tr>
						<th><span class="label">청코드</span></th>
						<td><select>
								<option selected="selected">620</option>
								<option>621</option>
								<option>622</option>
						</select></td>
					</tr>
					<tr>
						<th><span class="label">자산코드</span></th>
						<td><input type="text" size="8"></td>
					</tr>
					<tr>
						<th><span class="label">등록일</span></th>
						<td><input type="text" class="emdcal" size="8"></td>
					</tr>
					<tr>
						<th><span class="label">사용업체</span></th>
						<td><input type="text" size="8"></td>
					</tr>
					<tr>
						<th><span class="label">지도</span></th>
						<td><input type="file" style="margin-left: 5px;"
							multiple="multiple"></td>
					</tr>
					<tr>
						<th><span class="label">기타</span></th>
						<td><input type="text" size="50;"></td>
					</tr>
				</table>
				<div style="vertical-align: bottom; text-align: right;">
					<input type="reset" value="취소" class="input_1"> <input
						type="submit" value="저장" class="input_1">
				</div>
							</div>
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;" data-onactivate="onShowTab3Activate">
				<table id="assetCodePhotoList" style="display:none"></table>
				<div class="emdControlPanel"><button id="addAssetGisPhoto">추가</button><button id="removeAssetGisPhoto">삭제</button></div>
				<div class="emdPanel" style="overflow:scroll"><img style="border: 1px solid #000; max-width:800px; max-height: 600px" src="<c:url value='images/egovframework/ygpa/gam/misc/TEST2.JPG'/>"></div>
			</div>
		</div>
	</div>
</div>
