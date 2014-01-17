<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupAssetsCd.jsp
  * @Description : 자산코드 팝업 (Prototype)
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
function GamAssetCodePopupModule() {}

GamAssetCodePopupModule.prototype = new EmdPopupModule(640, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamAssetCodePopupModule.prototype.loadComplete = function() {

	this.resizable(true);

	// 테이블 설정

	this.$("#assetCodeList").flexigrid({
		module: this,
		url: '<c:url value="/popup/selectAssetCodeList.do"/>',
		dataType: 'json',
		colModel : [
			{display:'항코드', name:'gisAssetsPrtAtCode', width:60, sortable:true, align:'center'},
			{display:'시설코드', name:'gisAssetsCode', width:70, sortable:true, align:'center'},
			{display:'시설명', name:'gisAssetsNm', width:160, sortable:true, align:'center'},
			{display:'소재지', name:'GIS_ASSETS_LOCPLC  gisAssetsLocplc', width:160, sortable:true, align:'left'},
			{display:'지번', name:'gisAssetsLnm', width:40, sortable:true, align:'center'},
			{display:'지번SUB', name:'gisAssetsLnmSub', width:40, sortable:true, align:'center'},
			{display:'면적', name:'gisAssetsAr', width:64, sortable:true, align:'center'}
			],
		usepager: false,
//		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: '300'
	});

	this.$("#assetCodeList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.closeDialog('ok', row);
	});

	this.$("#assetCodeList").on('onItemSelected', function(event, module, row, grid, param) {
		//alert('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is selected');
	});

	this.$("#assetCodeList").on('onItemUnSelected', function(event, module, row, grid, param) {
		//alert('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is unselected');
	});

};

// 사용자 설정 함수 추가

GamAssetCodePopupModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'selectGisAssetCode':
		var searchOpt=this.makeFormArgs('#searchPopupGisAssetCode');
	 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
	 	throw 0;
		break;
	case 'btnOk':
		var row = this.$('#assetCodeList').selectedRows();
		if(row.length>0) {
			this.closeDialog('ok', row[0]);
		}
		else {
			alert('먼저 입력 하고자 하는 항목을 선택 하십시요.');
		}
		break;
	case 'cancel':
		this.cancelDialog();
	}
};

GamAssetCodePopupModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
};

GamAssetCodePopupModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#searchGisAssetCode');
 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamAssetCodePopupModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="searchPopupGisAssetCode">
						<table class="searchPanel">
							<tbody>
							<tr>
								<th>청코드</th>
								<td><input id="gisAssetsPrtAtCode" type="text" size="3" value="${searchOpt.gisAssetsPrtAtCode }"></td>
								<th>자산코드</th>
								<td><input id="gisAssetsCd" type="text" size="1" value="${searchOpt.gisAssetsCd }">-<input id="gisAssetsSubCd" type="text" size="4" value="${searchOpt.gisAssetsSubCd }"></td>
								<th>자산명</th>
								<td colspan="3"><input id="assetNm" type="text" size="10"></td>
								<td rowSpan="2"><button id="selectGisAssetCode" class="submit">조회</button></td>
							</tr>
							<tr>
								<th>자산 주소</th>
								<td colspan="3"><input id="assetNm" type="text" size="40"></td>
								<th>재산분류</th>
								<td><select id="prprtyCd">
										<option value="" selected="selected">선택</option>
										<option value="A">건물</option>
										<option value="L">토지</option>
										<option value="S">공작물</option>
										<option value="w">창고</option>
										<option value="E">기타</option>
								</select></td>
							</tr>
						</tbody>
					</table>
		</form>
				</div>

	<div class="emdPanel">
		<div style="width: 100%; height: 100%; overflow: auto">
			<table id="assetCodeList" style="display: none"></table>
		</div>
		<div class="emdControlPanel">
			<button id="btnOk">시설 선택</button>
			<button id="cancel">취소</button>
		</div>
	</div>
</div>
