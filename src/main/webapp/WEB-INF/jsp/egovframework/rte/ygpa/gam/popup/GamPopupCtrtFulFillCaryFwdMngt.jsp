<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupCtrtFulFillCaryFwdMngt.jsp
  * @Description :  계약이행이월 추가/삭제 팝업 (Prototype)
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
function GamPopupCtrtFulFillCaryFwdMngtModule() {}

GamPopupCtrtFulFillCaryFwdMngtModule.prototype = new EmdPopupModule(1000, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupCtrtFulFillCaryFwdMngtModule.prototype.loadComplete = function(fcltyCtrtFulFillCaryFwdList) {
	this._deleteCtrtFulFillCaryFwdList = [];
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
                    {display:'이행이월년도', name:'fulfillCaryFwdYear', width:100, sortable:true, align:'center'},
                    {display:'이행금액', name:'fulfillAmt', width:200, sortable:true, align:'right', displayFormat:'number'},
                    {display:'이월금액', name:'caryFwdAmt', width:200, sortable:true, align:'right', displayFormat:'number'}
				],
		height: "300",
	});
	this.$("#grdInfoList").flexOptions({params:null}).flexReload();

	this.$("#grdInfoList").flexAddData({resultList: fcltyCtrtFulFillCaryFwdList});
	
	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.ctrtFulFillCaryFwdChanged(event.target);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamPopupCtrtFulFillCaryFwdMngtForm input").val('');
		module.makeFormValues("#gamPopupCtrtFulFillCaryFwdMngtForm", row);
	});
};

//속성 변경 된 경우 이벤트 실행
GamPopupCtrtFulFillCaryFwdMngtModule.prototype.ctrtFulFillCaryFwdChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#grdInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#fulfillCaryFwdYear').is(target)) {
			row['fulfillCaryFwdYear'] = $(target).val();
			changed=true;
		}
		if(this.$('#fulfillAmt').is(target)) {
			row['fulfillAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#caryFwdAmt').is(target)) {
			row['caryFwdAmt'] = $(target).val();
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


//계약이행이월 병합 리턴
GamPopupCtrtFulFillCaryFwdMngtModule.prototype.returnData = function() {
	var resultList = this.$('#grdInfoList').flexGetData();
	var mergeData = {'resultList' : resultList, 'deleteCtrtFulFillCaryFwdList' : this._deleteCtrtFulFillCaryFwdList};
	this.closeDialog("ok", mergeData);
};

// 계약이행이월 추가
GamPopupCtrtFulFillCaryFwdMngtModule.prototype.addCtrtFulFillCaryFwdItem = function() {
	this.$('#gamPopupCtrtFulFillCaryFwdMngtForm :input').val('');
	this.$("#grdInfoList").flexAddRow({'_updtId': 'I', 'ctrtNo':'', 'seq':'', 'fulfillCaryFwdYear':'','fulfillAmt':'','caryFwdAmt':''});
	var allRows = this.$('#grdInfoList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#grdInfoList").selectRowId(selRowId);
};

// 계약이행이월 삭제
GamPopupCtrtFulFillCaryFwdMngtModule.prototype.removeCtrtFulFillCaryFwdItem = function() {
	var rows = this.$("#grdInfoList").selectedRows();
    if(rows.length == 0){
        alert("계약이행이월목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#grdInfoList").selectedRowIds().length>0) {
    	for(var i=this.$("#grdInfoList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#grdInfoList").flexGetRow(this.$("#grdInfoList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteCtrtFulFillCaryFwdList[this._deleteCtrtFulFillCaryFwdList.length] = row;
			}
        	this.$("#grdInfoList").flexRemoveRow(this.$("#grdInfoList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamPopupCtrtFulFillCaryFwdMngtForm :input").val("");
};


GamPopupCtrtFulFillCaryFwdMngtModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case "btnOk":
			this.returnData();
			break;
		case "btnCancel":
			this.cancelDialog();
			break;
		case "btnAdd":
			this.addCtrtFulFillCaryFwdItem();
			break;
		case "btnRemove":
			this.removeCtrtFulFillCaryFwdItem();
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupCtrtFulFillCaryFwdMngtModule();
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
		<form id="gamPopupCtrtFulFillCaryFwdMngtForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>이행이월년도</th>
                        <td><input id="fulfillCaryFwdYear" type="text" style="width: 150px;" class="EditItem"/></td>
                        <th>변경계약금액</th>
                        <td><input id="fulfillAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
						<th>최종계약금액</th>
                        <td colspan=><input id="caryFwdAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
					</tr>
				</tbody>
			</table>
		</form>
	    </div>

	</div>
</div>