<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupFlawRprObjFclts.jsp
  * @Description : 하자보수 대상시설물 목록 정보 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.4  	HNJ          최초 생성
  *
  * author HNJ
  * since 2014.12.4
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupFlawRprObjFcltsModule() {}

GamPopupFlawRprObjFcltsModule.prototype = new EmdPopupModule(1000, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupFlawRprObjFcltsModule.prototype.loadComplete = function(qcMngObjFcltsList) {

	this._deleteObjFcltsList = [];
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
					{display:"상태",			name:"_updtId",				width:60,		sortable:false,		align:"center"},
					{display:"시설물관리번호",	name:"fcltsMngNo",	width:100,		sortable:true,		align:"center"},
					{display:"시설물명",		name:"prtFcltyNm",	width:150,		sortable:true,		align:"left"},
					{display:"하자유무",		name:"flawEnnc",	width:90,		sortable:true,		align:"center"},
					{display:"하자검사일자",	name:"flawExamDt",	width:100,		sortable:true,		align:"center"},
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
GamPopupFlawRprObjFcltsModule.prototype.objFcltsDataChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#grdInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#fcltsMngNo').is(target)) {
			row['fcltsMngNo'] = $(target).val();
			changed=true;
		}

		if(this.$('#flawExamDt').is(target)) {
			row['flawExamDt'] = $(target).val();
			changed=true;
		}
		if(this.$('#flawEnnc').is(target)) {
			row['flawEnnc'] = $(target).val();
			changed=true;
		}
		if(this.$('#rm').is(target)) {
			row['rm'] = $(target).val();
			changed=true;
		}
		if(this.$('#flawExamResult').is(target)) {
			row['flawExamResult'] = $(target).val();
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

//하자보수 대상 시설물 병합 리턴
GamPopupFlawRprObjFcltsModule.prototype.returnMergeData = function() {
	var resultList = this.$('#grdInfoList').flexGetData();
	var mergeData = {'resultList' : resultList, 'deleteObjFcltsList' : this._deleteObjFcltsList};
	this.closeDialog("ok", mergeData);
};

//하자보수 대상 시설물 추가
GamPopupFlawRprObjFcltsModule.prototype.addObjFcltsItem = function() {
	this.$('#gamPopupObjFcltsForm :input').val('');
	this.$("#grdInfoList").flexAddRow({'_updtId': 'I', 'fcltsMngGroupNo':'', 'fcltsJobSe':'', 'flawRprSeq':'', 'fcltsMngNo':'', 'prtFcltyNm':'', 'flawEnnc':'', 'flawExamDt':'', 'flawExamResult':'', 'rm':''});
	var allRows = this.$('#grdInfoList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#grdInfoList").selectRowId(selRowId);	
};

//하자보수 대상 시설물 데이터 삭제
GamPopupFlawRprObjFcltsModule.prototype.removeObjFcltsItem = function() {
	var rows = this.$("#grdInfoList").selectedRows();
    if(rows.length == 0){
        alert("하자보수대상 시설물목록에서 삭제할 행을 선택하십시오.");
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
GamPopupFlawRprObjFcltsModule.prototype.onButtonClick = function(buttonId) {
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
var popup_instance = new GamPopupFlawRprObjFcltsModule();
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
						<th>하자검사일자</th>
                        <td><input id="flawExamDt" type="text" style="width: 100px;" maxlength="10" class="emdcal EditItem"/></td>
                        <th>하자유무</th>
                        <td>
                        	<select id="flawEnnc" class="EditItem">
                        		<option value="">선택</option>
                        		<option value="Y">유</option>
                        		<option value="N">무</option>
                        	</select>
                        </td>
					</tr>
					<tr>
						<th>하자검사결과</th>
						<td colspan="7"><textarea id="flawExamResult" cols="120" rows="7" class="EditItem"></textarea></td>
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