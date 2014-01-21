<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @Class Name : GamPrtFcltyUseSttusInqire.jsp
 * @Description : 항만시설사용현황조회 (항만시설/일반부두/항만시설사용현황조회)
  * @Modification Information
  * 
  *   수정일          수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.01.10  Dev          최초 생성
  *
  * author Dev
  * since 2013.01.10
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamOperCodeModule() {}

GamOperCodeModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamOperCodeModule.prototype.loadComplete = function() {
	this.$('#prtAtCode').val('820');	// 기본 항코드 설정
	
	// 테이블 설정
	this.$("#operResultList").flexigrid({
		module: this,
		url: '<c:url value="/oper/gnrl/selectGamPrtFcltyUseSttusInqire.do"/>',
		dataType: 'json',
		colModel : [
			{display:'부두코드', name:'quayCd', width:50, sortable:true, align:'center'},
			{display:'항코드', name:'prtAtCode', width:50, sortable:true, align:'center'},
			{display:'부두명', name:'quayNm', width:70, sortable:true, align:'center'},
			{display:'부두길이', name:'quayLt', width:70, sortable:true, align:'right'},
			{display:'부두계획홀수', name:'quayPlandraft', width:100, sortable:true, align:'right'},
			{display:'부두사용홀수', name:'quayUsageDraft', width:100, sortable:true, align:'center'},
			{display:'부두수심', name:'quayDpwt', width:50, sortable:true, align:'right'},
			{display:'부두접안가능척수', name:'quayCsdhpPosblShipQy', width:100, sortable:true, align:'center'},
			{display:'부두방식', name:'quayMthd', width:50, sortable:true, align:'center'},
			{display:'부두하역능력', name:'quayLnlAblty', width:70, sortable:true, align:'center'},
			{display:'부두소유구분', name:'quayPosesnSe', width:70, sortable:true, align:'center'},
			{display:'부두TS여부', name:'quayTsYn', width:70, sortable:true, align:'center'},
			{display:'부두영역GIS코드', name:'quayRelmGiscd', width:100, sortable:true, align:'center'}
			],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: '320',
		preProcess: sucData
		//preProcess: function(module, data){alert(data.totalCount); return data;}
	});
	
	this.$("#operResultList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#operManageTab").tabs("option", {active: 1});	// 탭을 전환 한다.
		module.$('#quayCd').val(row['quayCd']);
		module.$('#prtAtCode2').val(row['prtAtCode']);
		module.$('#quayNm').val(row['quayNm']);
		module.$('#quayLt').val(row['quayLt']);
		module.$('#quayPlandraft').val(row['quayPlandraft']);
		module.$('#quayUsageDraft').val(row['quayUsageDraft']);
		module.$('#quayDpwt').val(row['quayDpwt']);
		// 해당하는 자산 목록을 불러온다/
		var searchOpt=module.makeFormArgs('#searchForm');
		//this.showAlert(searchOpt);
	 	module.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
	});
	
};

function sucData(module, data) {
    //alert(data.totalCount);
    module.$('#tValCnt').val(data.totalCount);
    module.$('#tObVal').val(data.searchOption.quayNm);
    return data;
}
		
// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
GamOperCodeModule.prototype.showModuleAlert = function(msg) {
	//this.getSelect(msg);	
	this.$('#prtCode').val(msg);
};

GamOperCodeModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'selectOperSearch':
		var searchOpt=this.makeFormArgs('#operForm');
	 	this.$('#operResultList').flexOptions({params:searchOpt}).flexReload(); 
		break;
	/*	
	case 'selectGisAssetCode':
		var searchOpt=this.makeFormArgs('#searchGisAssetCode');
	 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
		break;
	case 'addAssetGisCd':	// gis 자산 추가
		var row = this.$('#operResultList').selectedRows();
		this.$("#operManageTab").tabs("option", {active: 1});	// 탭을 전환 한다.
		if(row.length>0) {
			this.$('#searchGisErpAssetCls').val(row['assetCls']);
			this.$('#searchGisErpAssetNo').val(row['assetNo']);
			this.$('#searchGisErpAssetNoSeq').val(row['assetNoSeq']);
			// 해당하는 자산 목록을 불러온다/
			var searchOpt=this.makeFormArgs('#searchForm');
			//this.showAlert(searchOpt);
		 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
		}
		else {
			this.$('#searchGisErpAssetCls').val('');
			this.$('#searchGisErpAssetNo').val('');
			this.$('#searchGisErpAssetNoSeq').val('');
			alert('신규 자산을 등록 합니다.');
		}
		break;
	case 'addAssetGisCdItem':
		break;
	case 'removeAssetCdItem':
		break;
	case 'editAssetCd':
		break;
	*/
	}
};

GamOperCodeModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case 'tabs1':
		this.$('#searchViewStack')[0].changePanelId(0);
		break;
	/*
	case 'tabs2':
		this.$('#searchViewStack')[0].changePanelId(1);
		break;
	case 'tabs3':
		this.$('#searchViewStack')[0].changePanelId(1);
		break;
	*/
	}
};

GamOperCodeModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
};

GamOperCodeModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#operForm');
	//this.showAlert(searchOpt);
 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
//	this.$('#assetList').flexOptions(searchOpt).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamOperCodeModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId }'/>
<div class="window_main">
	<div class="emdPanel">
			<div id="searchViewStack" class="viewStack">
				<div class="viewPanel">
		<form id="operForm">
					<table style="width:100%;" class="searchPanel">
						<tbody>
							<tr>
								<th>자산구분</th>
								<td><select id="assetCls">
										<option value="" selected="selected">선택</option>
										<c:forEach items="${erpAssetClsList}" var="clsItem">
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
								<td rowSpan="2"><button id="selectOperSearch" class="submit">조회</button></td>
							</tr>
							<tr>
								<th>품명</th>
								<td colspan="3"><input id="itemName" type="text" size="36"></td>
								<th>부서-<input id="tValCnt" type="text" size="3">-<input id="tObVal" type="text" size="5">-</th>
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
		<div id="operManageTab" class="emdTabPanel" style="height:100%;" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">항만시설사용현황조회</a></li>
				<li><a href="#tabs2" class="emdTab">항만시설사용현황상세</a></li>
				<!--
				<li><a href="#tabs3" class="emdTab">자산사진</a></li>
				-->
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
				<div style="width: 100%; height: 100%; overflow:auto">
						<table id="operResultList" style="display:none"></table>
				</div>
				<!-- <div class="emdControlPanel"><button id="addAssetGisCd">자산등록</button></div> -->
			</div>
			
			<div id="tabs2" class="emdTabPage" style="overflow: scroll;" data-onactivate="onShowTab2Activate">
				<!-- 
				<table id="assetCodeList" style="display:none"></table>
				<div class="emdControlPanel"><button id="addAssetGisCdItem">추가</button><button id="removeAssetGisCd">삭제</button><button id="loadMap">지도보기</button></div>
				 -->
				<div style="width: 100%; height: 100%; overflow:auto">
				<table>
					<tr>
						<th><span class="label">부두코드</span></th>
						<td><input type="text" size="8" id="quayCd" /></td>
					</tr>
					<tr>
						<th><span class="label">항코드</span></th>
						<td><input type="text" size="8" id="prtAtCode2"/></td>
					</tr>
					<tr>
						<th><span class="label">부두명</span></th>
						<td><input type="text" size="8" id="quayNm"/></td>
					</tr>
					<tr>
						<th><span class="label">부두길이</span></th>
						<td><input type="text" size="8" id="quayLt"/></td>
					</tr>
					<tr>
						<th><span class="label">부두계획홀수</span></th>
						<td><input type="text" size="8" id="quayPlandraft"/></td>
					</tr>
					<tr>
						<th><span class="label">부두사용홀수</span></th>
						<td><input type="text" size="8" id="quayUsageDraft"/></td>
					</tr>
					<tr>
						<th><span class="label">부두수신</span></th>
						<td><input type="text" size="8" id="quayDpwt"/></td>
					</tr>
				</table>
				</div>
				<!-- 
				<div style="vertical-align: bottom; text-align: right;">
					<input type="reset" value="취소" class="input_1"> <input
						type="submit" value="저장" class="input_1">
				</div>
				 -->
			</div>
							
			<!-- 				
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;" data-onactivate="onShowTab3Activate">
				<table id="assetCodePhotoList" style="display:none"></table>
				<div class="emdControlPanel"><button id="addAssetGisPhoto">추가</button><button id="removeAssetGisPhoto">삭제</button></div>
				<div class="emdPanel" style="overflow:scroll"><img style="border: 1px solid #000; max-width:800px; max-height: 600px" src="<c:url value='images/egovframework/ygpa/gam/misc/TEST2.JPG'/>"></div>
			</div>
			 -->
			 
		</div>
	</div>
</div>
