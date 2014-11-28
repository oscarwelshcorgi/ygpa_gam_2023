<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupQcMngResultItem.jsp
  * @Description : 점검관리 결과항목 목록 정보 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.28  	김종민          최초 생성
  *
  * author 김종민
  * since 2014.11.28
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupQcMngResultItemModule() {}

GamPopupQcMngResultItemModule.prototype = new EmdPopupModule(1000, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupQcMngResultItemModule.prototype.loadComplete = function(qcMngResultItemList) {

	this._deleteResultItemList = [];
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
					{display:"점검항목코드",	name:"qcItemCd",		width:100,		sortable:true,		align:"center"},
					{display:"점검항목",		name:"qcItem",			width:150,		sortable:true,		align:"center"},
					{display:"순번",			name:"seq",				width:90,		sortable:true,		align:"left"},
					{display:"점검항목결과구분",	name:"inspResultChk",	width:120,		sortable:true,		align:"left"}
			],
		height: "300",
	});

	this.$("#grdInfoList").flexAddData({resultList: qcMngResultItemList});

	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.objFcltsDataChanged(event.target);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamPopupResultItemForm input").val('');
		module.makeFormValues("#gamPopupResultItemForm", row);
	});
};

//속성 변경 된 경우 이벤트 실행
GamPopupQcMngResultItemModule.prototype.objFcltsDataChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#grdInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#qcItemCd').is(target)) {
			row['qcItemCd'] = $(target).val();
			changed=true;
		}
		if(this.$('#inspResultChk').is(target)) {
			row['inspResultChk'] = $(target).val();
			changed=true;
		}
		if(this.$('#inspResultCn').is(target)) {
			row['inspResultCn'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#grdInfoList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#grdInfoList').flexUpdateRow(rowid, row);
	}
};

//점검관리 결과항목 병합 리턴
GamPopupQcMngResultItemModule.prototype.returnMergeData = function() {
	var resultList = this.$('#grdInfoList').flexGetData();
	var mergeData = {'resultList' : resultList, 'deleteResultItemList' : this._deleteResultItemList};
	this.closeDialog("ok", mergeData);
};

//점검관리 결과항목 추가
GamPopupQcMngResultItemModule.prototype.addResultItem = function() {
	this.$('#gamPopupResultItemForm :input').val('');
	this.$("#grdInfoList").flexAddRow({'_updtId': 'I', 'fcltsMngGroupNo':'', 'fcltsJobSe':'', 'qcMngSeq':'', 'qcItemCd':'', 'seq':'', 'inspResultChk':'', 'inspResultCn':''});
	var allRows = this.$('#grdInfoList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#grdInfoList").selectRowId(selRowId);	
};

//점검관리 결과항목 삭제
GamPopupQcMngResultItemModule.prototype.removeResultItem = function() {
	var rows = this.$("#grdInfoList").selectedRows();
    if(rows.length == 0){
        alert("점검관리 결과항목목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#grdInfoList").selectedRowIds().length>0) {
    	for(var i=this.$("#grdInfoList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#grdInfoList").flexGetRow(this.$("#grdInfoList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteResultItemList[this._deleteResultItemList.length] = row;
			}
        	this.$("#grdInfoList").flexRemoveRow(this.$("#grdInfoList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamPopupResultItemForm").find(":input").val("");
};

//정의된 버튼 클릭시
GamPopupQcMngResultItemModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case "btnOk":
			this.returnMergeData();
			break;
		case "btnCancel":
			this.cancelDialog();
		case "btnAdd":
			this.addResultItem();
			break;
		case "btnRemove":
			this.removeResultItem();
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupQcMngResultItemModule();
</script>
<div class="dialog">
	<div class="emdPanel">

		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="btnAdd">추가</button>
	            <button id="btnRemove">삭제</button>
	            <button id="btnOk">확인</button>
            	<button id="btnCancel">취소</button>
	        </div>
		<form id="gamPopupResultItemForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>점검항목코드</th>
                        <td><input id="qcItemCd" type="text" style="width: 150px;" maxlength="20" class="EditItem"/></td>
                        <th>순번</th>
                        <td><input id="seq" type="text" style="width: 100px;" disabled="disabled" /></td>
                        <th>점검결과구분</th>
                        <td>
                       		<select id="inspResultChk" class="EditItem">
								<option value="">선택</option>
								<option value="1">구분1</option>
								<option value="2">구분2</option>
								<option value="3">구분3</option>
								<option value="4">구분4</option>
								<option value="5">구분5</option>
							</select>
                        </td>
					</tr>
					<tr>
						<th>점검결과내용</th>
						<td colspan="7"><textarea id="inspResultCn" cols="120" rows="7" class="EditItem"></textarea></td>
					</tr>
				</tbody>
			</table>
		</form>
	    </div>

	</div>
</div>