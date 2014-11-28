<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupQcMngObjFclts.jsp
  * @Description : 점검관리 대상시설물 목록 정보 팝업 (Prototype)
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
function GamPopupQcMngObjFcltsModule() {}

GamPopupQcMngObjFcltsModule.prototype = new EmdPopupModule(1000, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupQcMngObjFcltsModule.prototype.loadComplete = function(qcMngObjFcltsList) {

	this._deleteObjFcltsList = [];
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
					{display:"시설물관리번호",	name:"fcltsMngNo",	width:100,		sortable:true,		align:"center"},
					{display:"시설물명",		name:"prtFcltyNm",	width:150,		sortable:true,		align:"center"},
					{display:"점검진단구분",	name:"qcInspSe",	width:90,		sortable:true,		align:"left"},
					{display:"점검진단일자",	name:"qcInspDt",	width:100,		sortable:true,		align:"left"},
					{display:"점검자",		name:"inspector",	width:100,		sortable:true,		align:"left"},
					{display:"비고",			name:"rm",			width:350,		sortable:true,		align:"left"}
			],
		height: "200",
	});

	this.$("#grdInfoList").flexAddData({resultList: qcMngObjFcltsList});

	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.objFcltsDataChanged(event.target);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamPopupObjFcltsForm input").val('');
		module.makeFormValues("#gamPopupObjFcltsForm", row);
	});
};

//속성 변경 된 경우 이벤트 실행
GamPopupQcMngObjFcltsModule.prototype.objFcltsDataChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#grdInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#fcltsMngNo').is(target)) {
			row['fcltsMngNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#qcInspSe').is(target)) {
			row['qcInspSe'] = $(target).val();
			changed=true;
		}
		if(this.$('#qcInspDt').is(target)) {
			row['qcInspDt'] = $(target).val();
			changed=true;
		}
		if(this.$('#inspector').is(target)) {
			row['inspector'] = $(target).val();
			changed=true;
		}
		if(this.$('#rm').is(target)) {
			row['rm'] = $(target).val();
			changed=true;
		}
		if(this.$('#qcInspResult').is(target)) {
			row['qcInspResult'] = $(target).val();
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

//점검관리 대상 시설물 병합 리턴
GamPopupQcMngObjFcltsModule.prototype.returnMergeData = function() {
	var resultList = this.$('#grdInfoList').flexGetData();
	var mergeData = {'resultList' : resultList, 'deleteObjFcltsList' : this._deleteObjFcltsList};
	this.closeDialog("ok", mergeData);
};

//점검관리 대상 시설물 추가
GamPopupQcMngObjFcltsModule.prototype.addObjFcltsItem = function() {
	this.$('#gamPopupObjFcltsForm :input').val('');
	this.$("#grdInfoList").flexAddRow({'_updtId': 'I', 'fcltsMngGroupNo':'', 'fcltsJobSe':'', 'qcMngSeq':'', 'fcltsMngNo':'', 'qcInspSe':'', 'qcInspDt':'', 'inspector':'', 'qcInspResult':'', 'rm':''});
	var allRows = this.$('#grdInfoList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#grdInfoList").selectRowId(selRowId);	
};

//점검관리 대상 시설물 데이터 삭제
GamPopupQcMngObjFcltsModule.prototype.removeObjFcltsItem = function() {
	var rows = this.$("#grdInfoList").selectedRows();
    if(rows.length == 0){
        alert("점검관리대상 시설물목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#grdInfoList").selectedRowIds().length>0) {
    	for(var i=this.$("#grdInfoList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#grdInfoList").flexGetRow(this.$("#grdInfoList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteObjFcltsList[this._deleteObjFcltsList.length] = row;
			}
        	this.$("#grdInfoList").flexRemoveRow(this.$("#grdInfoList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamPopupObjFcltsForm").find(":input").val("");
};

//정의된 버튼 클릭시
GamPopupQcMngObjFcltsModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case "btnOk":
			this.returnMergeData();
			break;
		case "btnCancel":
			this.cancelDialog();
		case "btnAdd":
			this.addObjFcltsItem();
			break;
		case "btnRemove":
			this.removeObjFcltsItem();
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupQcMngObjFcltsModule();
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
		<form id="gamPopupObjFcltsForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>시설물관리번호</th>
                        <td><input id="fcltsMngNo" type="text" style="width: 150px;" maxlength="20" class="EditItem"/></td>
                        <th>점검진단구분</th>
                        <td>
                       		<select id="qcInspSe" class="EditItem">
								<option value="">선택</option>
								<option value="E">전기시설물</option>
								<option value="M">기계시설물</option>
								<option value="C">토목시설물</option>
								<option value="A">건축시설물</option>
								<option value="I">정보통신시설물</option>
							</select>
                        </td>
						<th>점검진단일자</th>
                        <td><input id="qcInspDt" type="text" style="width: 100px;" maxlength="10" class="emdcal EditItem"/></td>
                        <th>점검자</th>
                        <td ><input id="inspector" type="text" style="width: 150px;" maxlength="60" class="EditItem"/></td>
					</tr>
					<tr>
						<th>점검진단결과</th>
						<td colspan="7"><textarea id="qcInspResult" cols="120" rows="7" class="EditItem"></textarea></td>
					</tr>
					<tr>
						<th>비고</th>
						<td colspan="7"><input id="rm" type="text" style="width: 500px;" maxlength="1000" class="EditItem"/></td>
					</tr>
				</tbody>
			</table>
		</form>
	    </div>

	</div>
</div>