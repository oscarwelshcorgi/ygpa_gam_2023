<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupCtrtChangeMngt.jsp
  * @Description :  계약변경 추가/삭제 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.31  김종민          최초 생성
  *
  * author 김종민
  * since 2014.10.31
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupCtrtChangeMngtModule() {}

GamPopupCtrtChangeMngtModule.prototype = new EmdPopupModule(1000, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupCtrtChangeMngtModule.prototype.loadComplete = function(fcltyCtrtChangeList) {
	this._deleteCtrtChangeList = [];
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
                    {display:'변경일자', 		name:'changeDt', 		width:80,  sortable:true, align:'center'},
                    {display:'변경사유', 		name:'changeRsn', 		width:120, sortable:true, align:'center'},
                    {display:'변경계약시작일', 	name:'changeCtrtDtFrom',width:100, sortable:true, align:'center'},
                    {display:'변경계약종료일', 	name:'changeCtrtDtTo', 	width:100, sortable:true, align:'center'},
                    {display:'변경계약금액', 	name:'changeCtrtAmt', 	width:100, sortable:true, align:'right', displayFormat:'number'},
                    {display:'최종계약금액', 	name:'lastCtrtAmt', 	width:100, sortable:true, align:'right', displayFormat:'number'},
                    {display:'비고', 			name:'rm', 				width:250, sortable:true, align:'left'}
				],
		height: "270",
	});
	this.$("#grdInfoList").flexOptions({params:null}).flexReload();

	this.$("#grdInfoList").flexAddData({resultList: fcltyCtrtChangeList});
	
	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.ctrtChangeChanged(event.target);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamPopupCtrtChangeMngtForm input").val('');
		module.makeFormValues("#gamPopupCtrtChangeMngtForm", row);
	});
};

//속성 변경 된 경우 이벤트 실행
GamPopupCtrtChangeMngtModule.prototype.ctrtChangeChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#grdInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#changeDt').is(target)) {
			row['changeDt'] = $(target).val();
			changed=true;
		}
		if(this.$('#changeRsn').is(target)) {
			row['changeRsn'] = $(target).val();
			changed=true;
		}
		if(this.$('#changeCtrtPdFrom').is(target)) {
			row['changeCtrtPdFrom'] = $(target).val();
			changed=true;
		}
		if(this.$('#changeCtrtPdTo').is(target)) {
			row['changeCtrtPdTo'] = $(target).val();
			changed=true;
		}
		if(this.$('#changeCtrtAmt').is(target)) {
			row['changeCtrtAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#lastCtrtAmt').is(target)) {
			row['lastCtrtAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#rm').is(target)) {
			row['rm'] = $(target).val();
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

//계약변경 병합 리턴
GamPopupCtrtChangeMngtModule.prototype.returnData = function() {
	var resultList = this.$('#grdInfoList').flexGetData();
	var mergeData = {'resultList' : resultList, 'deleteCtrtChangeList' : this._deleteCtrtChangeList};
	this.closeDialog("ok", mergeData);
};

// 계약변경 추가
GamPopupCtrtChangeMngtModule.prototype.addCtrtChangeItem = function() {
	this.$('#gamPopupCtrtChangeMngtForm :input').val('');
	this.$("#grdInfoList").flexAddRow({'_updtId': 'I', 'ctrtNo':'', 'seq':'', 'changeDt':'','changeRsn':'','changeCtrtDtFrom':'','changeCtrtDtTo':'','changeCtrtAmt':'','lastCtrtAmt':'' ,'rm':''});
	var allRows = this.$('#grdInfoList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#grdInfoList").selectRowId(selRowId);
};

// 계약변경 삭제
GamPopupCtrtChangeMngtModule.prototype.removeCtrtChangeItem = function() {
	var rows = this.$("#grdInfoList").selectedRows();
    if(rows.length == 0){
        alert("계약변경목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#grdInfoList").selectedRowIds().length>0) {
    	for(var i=this.$("#grdInfoList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#grdInfoList").flexGetRow(this.$("#grdInfoList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteCtrtChangeList[this._deleteCtrtChangeList.length] = row;
			}
        	this.$("#grdInfoList").flexRemoveRow(this.$("#grdInfoList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamPopupCtrtChangeMngtForm :input").val("");
};

GamPopupCtrtChangeMngtModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case "btnOk":
			this.returnData();
			break;
		case "btnCancel":
			this.cancelDialog();
			break;
		case "btnAdd":
			this.addCtrtChangeItem();
			break;
		case "btnRemove":
			this.removeCtrtChangeItem();
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupCtrtChangeMngtModule();
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
		<form id="gamPopupCtrtChangeMngtForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>변경일자</th>
                        <td><input id="changeDt" type="text" style="width: 150px;" class="EditItem emdcal"/></td>
                    	<th>변경계약기간</th>
                        <td colspan="3">
                        	<input id="changeCtrtDtFrom" type="text" style="width: 150px;" class="EditItem emdcal"/>
                        	~<input id="changeCtrtDtTo" type="text" style="width: 150px;" class="EditItem emdcal"/>
                        </td>
					</tr>
					<tr>
                        <th>변경계약금액</th>
                        <td><input id="changeCtrtAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
						<th>최종계약금액</th>
                        <td colspan=><input id="lastCtrtAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
					</tr>
					<tr>
                        <th>변경사유</th>
                        <td><input id="changeRsn" type="text" style="width: 150px;" class="EditItem" maxlength="33"/></td>
                    	<th>비고</th>
                        <td>
                        	<input id="rm" type="text" style="width: 350px;" class="EditItem" maxlength="333"/>
                        </td>
					</tr>
				</tbody>
			</table>
		</form>
	    </div>

	</div>
</div>