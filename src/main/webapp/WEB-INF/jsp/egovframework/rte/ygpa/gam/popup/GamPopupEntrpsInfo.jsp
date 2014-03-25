<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupEntrpsInfo.jsp
  * @Description : 업체정보 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.22  heroine          최초 생성
  *
  * author heroine
  * since 2014.01.22
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupEntrpsModule() {}

GamPopupEntrpsModule.prototype = new EmdPopupModule(640, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupEntrpsModule.prototype.loadComplete = function() {

	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '<c:url value="/popup/selectEntrpsInfoList.do"/>',
		dataType: "json",
		colModel : [
					{display:"업체코드",			name:"entrpscd", 	width:60, 		sortable:true, 		align:"center"},
					{display:"업체 명",			name:"entrpsNm", 	width:120, 		sortable:true, 		align:"center"},
					//{display:"업체 유형",			name:"entrpsTy", 	width:60, 		sortable:true, 		align:"center"},
					{display:"사업자 구분",		name:"bsnmSe", 		width:100, 		sortable:true, 		align:"center"},
					{display:"대표자 명",			name:"rprsntvNm", 	width:60, 		sortable:true, 		align:"center"},
					{display:"사업자등록번호",	name:"bizrno", 		width:120, 		sortable:true, 		align:"center"},
					//{display:"법인등록번호",		name:"cprregistno", width:60, 		sortable:true, 		align:"center"},
					{display:"업종",				name:"induty", 		width:100, 		sortable:true, 		align:"center"},
					{display:"업태",				name:"bizcnd", 		width:100, 		sortable:true, 		align:"center"}
					//{display:"전화번호",			name:"tlphonNo", 	width:60, 		sortable:true, 		align:"center"},
					//{display:"우편번호",			name:"zip", 		width:60, 		sortable:true, 		align:"center"},
					//{display:"주소",				name:"adres", 		width:60, 		sortable:true, 		align:"center"},
					//{display:"팩스",				name:"fax", 		width:60, 		sortable:true, 		align:"center"}
			],
		showTableToggleBtn: false,
	    height: 'auto'
	});

	this.$("#grdInfoList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.closeDialog("ok", row);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		//alert("row " + row["assetCls"]+"-"+row["assetNo"]+"-"+row["assetNoSeq"]+" is selected");
	});

	this.$("#grdInfoList").on("onItemUnSelected", function(event, module, row, grid, param) {
		//alert("row " + row["assetCls"]+"-"+row["assetNo"]+"-"+row["assetNoSeq"]+" is unselected");
	});

};

// 사용자 설정 함수 추가

GamPopupEntrpsModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "btnEntrpsSearch":
		var searchOpt=this.makeFormArgs("#gamPopupEntrpsForm");
	 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
	 	throw 0;
		break;
	case "btnOk":
		var row = this.$("#grdInfoList").selectedRows();
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

GamPopupEntrpsModule.prototype.onSubmit = function() {
	//this.showAlert(this.$("#prtCode").val()+"을(를) 조회 하였습니다");
	this.loadData();
};

GamPopupEntrpsModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupEntrpsForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupEntrpsModule();
</script>
<div class="dialog fillHeight">
	<div class="emdPanel">
		<form id="gamPopupEntrpsForm">
			<table class="searchPanel">
				<tbody>
					<tr>
						<th>업체코드</th>
                        <td><input id="entrpscd" type="text" size="7" title="업체코드" /></td>
                        <th>업체 명</th>
                        <td><input id="entrpsNm" type="text" size="10" title="업체 명" /></td>
                        <th>업체 유형</th>
                        <td><input id="bsnmSe" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM020" size="10" /></td>
                        <td rowspan="2"><button id="btnEntrpsSearch">조회</button></td>
					</tr>
                    <tr>
                    	<th>사업자 구분</th>
						<td><input id="entrpsTy" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM018" size="10" /></td>
                        <th>업종</th>
                        <td><input type="text" id="induty" title="업종" size="10" /></td>
                        <th>업태</th>
                        <td><input type="text" id="bizcnd" title="업태" size="10" /></td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<!-- 
		<div class="emdControlPanel">
			<button id="btnEntrpsSearch">조회</button>
		</div>
		
		<table id="grdInfoList" style="display: none"></table>
		<div class="emdControlPanel">
			<button id="btnOk">업체 선택</button>
			<button id="cancel">취소</button>
		</div>
		-->
		
		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="btnOk">업체 선택</button>
            <button id="cancel">취소</button>
	        </div>
	    </div>
		
		
	</div>
</div>