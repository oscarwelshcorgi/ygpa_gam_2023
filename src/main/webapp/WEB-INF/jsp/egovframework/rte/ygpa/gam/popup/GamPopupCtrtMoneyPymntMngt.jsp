<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupCtrtMoneyPymntMngt.jsp
  * @Description :  계약대금지급 추가/삭제 팝업 (Prototype)
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
function GamPopupCtrtMoneyPymntMngtModule() {}

GamPopupCtrtMoneyPymntMngtModule.prototype = new EmdPopupModule(1000, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupCtrtMoneyPymntMngtModule.prototype.loadComplete = function(fcltyCtrtMoneyPymntList) {
	this._deleteCtrtMoneyPymntList = [];
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
                    {display:'지급분류', 	 name:'pymntCl', 		width:80, sortable:true, align:'center'},
                    {display:'지급일자', 	 name:'pymntDt', 		width:80, sortable:true, align:'center'},
                    {display:'금회기성금액', name:'thisTimeEstbAmt',width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'선금정산금액', name:'depositExcclcAmt',width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'지급금액', 	 name:'pymntAmt', 		width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'지급누계금액', name:'pymntAggrAmt', 	width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'비고', 		 name:'rm', 			width:210, sortable:true, align:'left'}
				],
		height: "270",
	});
	this.$("#grdInfoList").flexOptions({params:null}).flexReload();

	this.$("#grdInfoList").flexAddData({resultList: fcltyCtrtMoneyPymntList});
	
	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.ctrtMoneyPymntChanged(event.target);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamPopupCtrtMoneyPymntMngtForm input").val('');
		module.makeFormValues("#gamPopupCtrtMoneyPymntMngtForm", row);
	});
};

//속성 변경 된 경우 이벤트 실행
GamPopupCtrtMoneyPymntMngtModule.prototype.ctrtMoneyPymntChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#grdInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#pymntCl').is(target)) {
			row['pymntCl'] = $(target).val();
			changed=true;
		}
		if(this.$('#pymntDt').is(target)) {
			row['pymntDt'] = $(target).val();
			changed=true;
		}
		if(this.$('#thisTimeEstbAmt').is(target)) {
			row['thisTimeEstbAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#depositExcclcAmt').is(target)) {
			row['depositExcclcAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#pymntAmt').is(target)) {
			row['pymntAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#pymntAggrAmt').is(target)) {
			row['pymntAggrAmt'] = $(target).val();
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


//계약대금지급 병합 리턴
GamPopupCtrtMoneyPymntMngtModule.prototype.returnData = function() {
	var resultList = this.$('#grdInfoList').flexGetData();
	var mergeData = {'resultList' : resultList, 'deleteCtrtMoneyPymntList' : this._deleteCtrtMoneyPymntList};
	this.closeDialog("ok", mergeData);
};

// 계약대금지급 추가
GamPopupCtrtMoneyPymntMngtModule.prototype.addCtrtMoneyPymntItem = function() {
	this.$('#gamPopupCtrtMoneyPymntMngtForm :input').val('');
	this.$("#grdInfoList").flexAddRow({'_updtId': 'I', 'ctrtNo':'', 'seq':'', 'pymntCl':'','pymntDt':'','thisTimeEstbAmt':'','depositExcclcAmt':'','pymntAmt':'','pymntAggrAmt':'' ,'rm':''});
	var allRows = this.$('#grdInfoList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#grdInfoList").selectRowId(selRowId);
};

// 계약대금지급 삭제
GamPopupCtrtMoneyPymntMngtModule.prototype.removeCtrtMoneyPymntItem = function() {
	var rows = this.$("#grdInfoList").selectedRows();
    if(rows.length == 0){
        alert("계약대금지급목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#grdInfoList").selectedRowIds().length>0) {
    	for(var i=this.$("#grdInfoList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#grdInfoList").flexGetRow(this.$("#grdInfoList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteCtrtMoneyPymntList[this._deleteCtrtMoneyPymntList.length] = row;
			}
        	this.$("#grdInfoList").flexRemoveRow(this.$("#grdInfoList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamPopupCtrtMoneyPymntMngtForm :input").val("");
};

GamPopupCtrtMoneyPymntMngtModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case "btnOk":
			this.returnData();
			break;
		case "btnCancel":
			this.cancelDialog();
			break;
		case "btnAdd":
			this.addCtrtMoneyPymntItem();
			break;
		case "btnRemove":
			this.removeCtrtMoneyPymntItem();
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupCtrtMoneyPymntMngtModule();
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
		<form id="gamPopupCtrtMoneyPymntMngtForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>지급분류</th>
                        <td><input id="pymntCl" type="text" style="width: 150px;" class="EditItem"/></td>
                        <th>지급일자</th>
                        <td><input id="pymntDt" type="text" style="width: 150px;" class="EditItem  emdcal"/></td>
                        <th>금회기성금액</th>
                        <td><input id="thisTimeEstbAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
					</tr>
					<tr>
                        <th>선금정산금액</th>
                        <td><input id="depositExcclcAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
                        <th>지급금액</th>
                        <td><input id="pymntAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
                        <th>지급누계금액</th>
                        <td><input id="pymntAggrAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
					</tr>
					<tr>
                    	<th>비고</th>
                        <td colspan="3">
                        	<input id="rm" type="text" style="width: 450px;" class="EditItem"/>
                        </td>
					</tr>
				</tbody>
			</table>
		</form>
	    </div>

	</div>
</div>