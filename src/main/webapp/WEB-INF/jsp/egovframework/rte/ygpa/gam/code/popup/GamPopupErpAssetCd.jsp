<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupErpAssetCd.jsp
  * @Description : ERP자산코드 팝업
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2013.11.15  장은성          최초 생성
  *
  * author 장은성
  * since 2014.04.10
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamErpAssetCodePopupModule() {}

GamErpAssetCodePopupModule.prototype = new EmdPopupModule(600, 460);

// 팝업이 호출 되었을때 호출 되는 함수
GamErpAssetCodePopupModule.prototype.loadComplete = function(params) {
	this.resizable(true);

	// 테이블 설정
	this.$("#erpAssetCodeList").flexigrid({
		module: this,
		url: '/asset/selectErpAssetCodeList.do',
		dataType: 'json',
		colModel : [
			{display:'ERP자산코드', name:'erpAssetCode', width:80, sortable:true, align:'left'},
			{display:'자산관리번호', name:'assetMngtNo', width:80, sortable:true, align:'right'},
			{display:'품목', name:'itemCls', width:32, sortable:true, align:'center'},
			{display:'품명', name:'itemNameAsset', width:180, sortable:true, align:'center'},
			{display:'구매용도', name:'purPurpose', width:180, sortable:true, align:'center'},
			{display:'취득수량', name:'buyQty', width:80, sortable:true, align:'right', displayFormat: 'number'},
			{display:'자산규격', name:'assetSize', width:200, sortable:true, align:'center'},
			{display:'취득단가', name:'buyPrice', width:128, sortable:true, align:'right', displayFormat: 'number'},
			{display:'취득일자', name:'buyDate', width:64, sortable:true, align:'center'}
			],
		height:'280',
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				this.erpAssetCode=this.assetCls+'-'+this.assetNo+'-'+this.assetNoSeq;
			});
			return data;
		}
	});

	this.$("#erpAssetCodeList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.closeDialog("ok", row);
	});
};


// 사용자 설정 함수 추가
GamErpAssetCodePopupModule.prototype.onButtonClick = function(buttonId) {
 	switch(buttonId) {
	case "selectErpAssetCode":
		var searchOpt=this.makeFormArgs("#searchErpAssetCode");
	 	this.$("#erpAssetCodeList").flexOptions({params:searchOpt}).flexReload();
	 	
		break;
	case "btnOk":
		var row = this.$("#assetCodeList").selectedRows();
		if(row.length>0) {
			this.closeDialog("ok", row[0]);
		}
		else {
			alert("먼저 입력 하고자 하는 항목을 선택 하십시요.");
		}
		break;
	case "cancel":
		this.cancelDialog();
		break;
	}
};

GamErpAssetCodePopupModule.prototype.loadData = function() {
 	var searchOpt=this.makeFormArgs("#searchGisAssetCode");
 	this.$("#assetCodeList").flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamErpAssetCodePopupModule();
</script>
<div class="dialog fillHeight">
	<div class="emdPanel">
		<form id="searchErpAssetCode">
			<table style="width: 100%;" class="searchPanel">
				<tbody>
					<tr>
						<th>자산구분</th>
						<td><select id="searchAssetCls">
								<option value="" selected="selected">선택</option>
								<c:forEach items="${erpAssetClsList}" var="clsItem">
									<option value="${clsItem.smCls}">${clsItem.smClsName}</option>
								</c:forEach>
						</select></td>
						<th>자산번호</th>
						<td><input id="searchAssetNo" type="text" size="6">-<input
							id="searchAssetNoSeq" type="text" size="6"></td>
						<td rowSpan="2"><button id="selectErpAssetCode" class="buttonSearch">조회</button></td>
					</tr>
					<tr>
						<th>품명(구매용도)</th>
						<td colspan="3"><input id="searchItemName" size="40"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="emdPanel fillHeight">
		<table id="erpAssetCodeList" style="display: none" class="fillHeight"></table>
		<div class="emdControlPanel">
			<button id="btnOk">선택</button>
			<button id="cancel">취소</button>
		</div>
	</div>
</div>