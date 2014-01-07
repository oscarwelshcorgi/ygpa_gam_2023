<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPrtFcltyRntfeeInqire.jsp
  * @Description : 항만시설사용료조회 테스트 (Prototype)
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
function GamAssetCodeListModule() {}

GamAssetCodeListModule.prototype = new EmdModule();

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetCodeListModule.prototype.loadComplete = function() {
	this.$('#prtAtCode').val('820');	// 기본 항코드 설정
	
	this.$('#submitButton').button().click({parent: this}, function(event) {
		event.data.parent.onSubmit();
	});
	
	// 테이블 설정
	this.$("#assetCodeList").flexigrid({
		url: '<c:url value="/code/mngt/selectAssetCodeList.do"/>',
		dataType: 'json',
		colModel : [
			{display:'부두 항코드', name:'PRT_AT_CODE',width:100, sortable:false,align:'center'},
			{display:'그룹 코드', name:'QUAY_GROUP_CD',width:100, sortable:false,align:'center'},
			{display:'선석 시설코드',name:'BERTH_CD',width:140,sortable:false,align:'center'},
			{display:'선석명',name:'BERTH_NM',width:100,sortable:false,align:'center'},
			{display:'부두코드',name:'QUAY_CD',width:140,sortable:false,align:'center'},
			{display:'부두명',name:'QUAY_NAME',width:140,sortable:false,align:'center'},
			{display:'사용료', name:'FEE',width:200, sortable:false,align:'center'},
			{display:'사용 면적',name:'USAGE_AR',width:160,sortable:false,align:'center'}
			
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
GamAssetCodeListModule.prototype.showModuleAlert = function(msg) {
	//this.getSelect(msg);	
	this.$('#prtCode').val(msg);
}

GamAssetCodeListModule.prototype.onClick = function(e) {
	switch(e.target_id) {
	case 'addAssetCd':
		break;
	case 'removeAssetCd':
		break;
	case 'editAssetCd':
		break;
	}
}

GamAssetCodeListModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
}

GamAssetCodeListModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#searchForm');
	//this.showAlert(searchOpt);
 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
//	this.$('#assetList').flexOptions(searchOpt).flexReload();
}

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetCodeListModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId }'/>
<div class="window_main">
	<div class="emdPanel">
		<div class="viewStack">
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
	</div>

	<div class="emdPanel">
		<div id='' class="emdTabPanel">
			<ul>
				<li><a href="#tabs1" class="emdTab">시설사용료</a></li>
				<li><a href="#tabs2" class="emdTab">자산상세</a></li>
				<li><a href="#tabs3" class="emdTab">자산사진</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="assetCodeList" style="display:none"></table>
				<div class="emdControlPanel"><button id="loadMap">지도보기</button></div>
			</div>
			<div id="tabs2" class="emdTabPage">
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
			<div id="tabs3" class="emdTabPage">탭탭탭</div>

		</div>
	</div>
</div>
