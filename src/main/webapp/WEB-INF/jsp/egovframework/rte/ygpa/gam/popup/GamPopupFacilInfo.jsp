<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupFacilInfo.jsp
  * @Description : 선석정보 팝업 (Prototype)
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
function GamPopupFacilModule() {}

GamPopupFacilModule.prototype = new EmdPopupModule(640, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupFacilModule.prototype.loadComplete = function() {

	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '<c:url value="/popup/selectFacilInfoList.do"/>',
		dataType: "json",
		colModel : [
					{display:"항코드",			name:"prtAtCode", 	width:60, 		sortable:true, 		align:"center"},
					{display:"선석코드",			name:"facCode", 	width:60, 		sortable:true, 		align:"center"},
					{display:"선석부코드",			name:"facSubCode", 	width:60, 		sortable:true, 		align:"center"},
					{display:"선석명",	name:"facKorNm", 		width:200, 		sortable:true, 		align:"center"},
					{display:"선석길이",				name:"facLen", 		width:120, 		sortable:true, 		align:"center"}
			],
		height: "auto"
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

GamPopupFacilModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "btnFacilSearch":
		/* 
		if(this.$("#entrpscd").val() == "" && this.$("#bizrno").val() == ""){
			if(this.$("#entrpsNm").val() == "" || this.$("#entrpsNm").val().length < 2){
				this.$("#entrpsNm").focus();
				alert("업체 명은 2자 이상 입력하십시오.");
				return;
			}	
		}
		 */
		var searchOpt=this.makeFormArgs("#gamPopupFacilForm");
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

GamPopupFacilModule.prototype.onSubmit = function() {
	//this.showAlert(this.$("#prtCode").val()+"을(를) 조회 하였습니다");
	this.loadData();
};

GamPopupFacilModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupFacilForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupFacilModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupFacilForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>항구분</th>
                        <td>
                        	<select id="sPrtAtCode">
                                <option value="" selected="selected">선택</option>
                                <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                    <option value="${prtAtCdItem.code }">${prtAtCdItem.codeNm }</option>
                                </c:forEach>
                            </select>                        
                        </td>
						<th>선석명</th>
                        <td><input id="sFacKorNm" type="text" size="20" title="업체코드" maxlength="10" /></td>
                    </tr>
                    <tr>
                    	<th>선석코드</th>
						<td><input id="sFacCode" type="text" size="20" title="선석코드" maxlength="12" /></td>
                    	<th>선석부코드</th>
						<td><input id="sFacSubCode" type="text" size="20" title="선석부코드" maxlength="12" /></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="emdControlPanel">
			<button id="btnFacilSearch">조회</button>
		</div>
		
		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="btnOk">선석 선택</button>
            <button id="cancel">취소</button>
	        </div>
	    </div>

	</div>
</div>