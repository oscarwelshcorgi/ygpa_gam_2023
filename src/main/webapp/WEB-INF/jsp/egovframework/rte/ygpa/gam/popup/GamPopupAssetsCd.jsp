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

GamAssetCodePopupModule.prototype = new EmdPopupModule(700, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamAssetCodePopupModule.prototype.loadComplete = function(param) {

	this.resizable(true);

	// 테이블 설정

	this.$("#assetCodeList").flexigrid({
		module: this,
		url: '/popup/selectAssetCodeList.do',
		//dataType: "json",
		colModel : [
					{display:"순번", 					name:"rnum", 				width:60, 	sortable:true, align:"center"},
					{display:"항구분", 					name:"prtAtCodeName",	width:70, 	sortable:true, align:"center"},
					{display:"자산코드", 				name:"gisAssetsCode", 		width:70, 	sortable:true, align:"center"},
					{display:"자산명", 					name:"gisAssetsNm", 		width:130, 	sortable:true, align:"center"},
					{display:"소재지", 					name:"gisAssetsLocplcAll", 	width:200, 	sortable:true, align:"center"},
					{display:"면적",					name:"gisAssetsRealRentAr",	width:80, 	sortable:true, align:"right"}
					],
		height: '330',
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				this.gisAssetsLocplcAll=this.gisAssetsLocplc;
				if(this.gisAssetsLnm!=undefined && this.gisAssetsLnm!=null) {
					this.gisAssetsLocplcAll+=" "+this.gisAssetsLnm;
				if(this.gisAssetsLnmSub!=undefined && this.gisAssetsLnmSub!=null) {
						this.gisAssetsLocplcAll = this.gisAssetsLocplcAll+"-"+this.gisAssetsLnmSub;
				}
				}
				this.prtAtCodeName=this.gisAssetsPrtAtCodeNm+" ["+this.gisAssetsPrtAtCode+"]"
			});
			return data;
		}
	});

	this.$("#assetCodeList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.closeDialog("ok", row);
	});

	if(param!=undefined) {
		this.$('#gisAssetsPrprtySeCd').val(param.gisAssetsPrprtySeCd);
	}
};


// 사용자 설정 함수 추가
GamAssetCodePopupModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "selectGisAssetCode":
		var searchOpt=this.makeFormArgs("#searchPopupGisAssetCode");
	 	this.$("#assetCodeList").flexOptions({params:searchOpt}).flexReload();
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
	}
};

GamAssetCodePopupModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#searchGisAssetCode");
 	this.$("#assetCodeList").flexOptions({params:searchOpt}).flexReload();
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
						<th>항코드</th>
						<td><input id="gisAssetsPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
						<th>자산구분</th>
						<td><input id="gisAssetsPrprtySeCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM001"/></td>
						<th>자산명</th>
						<td><input id="gisAssetsNm" type="text" size="20" /></td>
						<td rowspan="2"><button id="selectGisAssetCode">조회</button></td>
					</tr>
					<tr>
						<th>소재지</th>
						<td colspan="3"><input id="gisAssetsLocplc" type="text" size="30"></td>
						<th>지번</th>
						<td><input id="gisAssetsLnm" type="text" size="4">-<input id="gisAssetsLnmSub" type="text" size="4"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="emdPanel fillHeight">
		<table id="assetCodeList" style="display: none"></table>
		<div class="emdControlPanel">
			<button id="btnOk">자산 선택</button>
			<button id="cancel">취소</button>
		</div>
	</div>
</div>