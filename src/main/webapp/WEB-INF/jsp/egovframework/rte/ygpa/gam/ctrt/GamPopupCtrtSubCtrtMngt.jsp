<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupCtrtSubCtrtMngt.jsp
  * @Description :  계약하도급 추가/삭제 팝업 (Prototype)
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
function GamPopupCtrtSubCtrtMngtModule() {}

GamPopupCtrtSubCtrtMngtModule.prototype = new EmdPopupModule(1000, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupCtrtSubCtrtMngtModule.prototype.loadComplete = function(fcltyCtrtSubCtrtList) {
	this._deleteCtrtSubCtrtList = [];
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
                    {display:'업체명', 		name:'entrpsNm', 		width:150, sortable:true, align:'center'},
                    {display:'대금지급합의',	name:'moneyPymntAgree', width:90, sortable:true, align:'center'},
                    {display:'공증', 			name:'workClass', 		width:80, sortable:true, align:'center'},
                    {display:'하도급율', 		name:'subctrtRate', 	width:80, sortable:true, align:'right'},
                    {display:'원도급금액', 		name:'orginlContrAmt', 	width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'하도급계약금액', 	name:'subctrtCtrtAmt', 	width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'계약시작일', 		name:'ctrtDtFrom', 		width:100, sortable:true, align:'center'},
                    {display:'계약종료일', 		name:'ctrtDtTo', 		width:100, sortable:true, align:'center'}
				],
		height: "280",
	});
	this.$("#grdInfoList").flexOptions({params:null}).flexReload();

	this.$("#grdInfoList").flexAddData({resultList: fcltyCtrtSubCtrtList});
	
	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.ctrtSubCtrtChanged(event.target);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamPopupCtrtSubCtrtMngtForm input").val('');
		module.makeFormValues("#gamPopupCtrtSubCtrtMngtForm", row);
	});
};

//속성 변경 된 경우 이벤트 실행
GamPopupCtrtSubCtrtMngtModule.prototype.ctrtSubCtrtChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#grdInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#entrpsNm').is(target)) {
			row['entrpsNm'] = $(target).val();
			changed=true;
		}
		if(this.$('#moneyPymntAgree').is(target)) {
			row['moneyPymntAgree'] = $(target).val();
			changed=true;
		}
		if(this.$('#workClass').is(target)) {
			row['workClass'] = $(target).val();
			changed=true;
		}
		if(this.$('#subctrtRate').is(target)) {
			row['subctrtRate'] = $(target).val();
			changed=true;
		}
		if(this.$('#orginlContrAmt').is(target)) {
			row['orginlContrAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#subctrtCtrtAmt').is(target)) {
			row['subctrtCtrtAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#ctrtDtFrom').is(target)) {
			row['ctrtDtFrom'] = $(target).val();
			changed=true;
		}
		if(this.$('#ctrtDtTo').is(target)) {
			row['ctrtDtTo'] = $(target).val();
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


//계약하도급 병합 리턴
GamPopupCtrtSubCtrtMngtModule.prototype.returnData = function() {
	var resultList = this.$('#grdInfoList').flexGetData();
	var mergeData = {'resultList' : resultList, 'deleteCtrtSubCtrtList' : this._deleteCtrtSubCtrtList};
	this.closeDialog("ok", mergeData);
};

// 계약하도급 추가
GamPopupCtrtSubCtrtMngtModule.prototype.addCtrtSubCtrtItem = function() {
	this.$('#gamPopupCtrtSubCtrtMngtForm :input').val('');
	this.$("#grdInfoList").flexAddRow({'_updtId': 'I', 'ctrtNo':'', 'seq':'', 'entrpsNm':'','moneyPymntAgree':'','workClass':'','subctrtRate':'','orginlContrAmt':'','subctrtCtrtAmt':'' ,'ctrtDtFrom':'','ctrtDtTo':''});
	var allRows = this.$('#grdInfoList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#grdInfoList").selectRowId(selRowId);
};

// 계약하도급 삭제
GamPopupCtrtSubCtrtMngtModule.prototype.removeCtrtSubCtrtItem = function() {
	var rows = this.$("#grdInfoList").selectedRows();
    if(rows.length == 0){
        alert("계약하도급목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#grdInfoList").selectedRowIds().length>0) {
    	for(var i=this.$("#grdInfoList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#grdInfoList").flexGetRow(this.$("#grdInfoList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteCtrtSubCtrtList[this._deleteCtrtSubCtrtList.length] = row;
			}
        	this.$("#grdInfoList").flexRemoveRow(this.$("#grdInfoList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamPopupCtrtSubCtrtMngtForm :input").val("");
};

GamPopupCtrtSubCtrtMngtModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case "btnOk":
			this.returnData();
			break;
		case "btnCancel":
			this.cancelDialog();
			break;
		case "btnAdd":
			this.addCtrtSubCtrtItem();
			break;
		case "btnRemove":
			this.removeCtrtSubCtrtItem();
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupCtrtSubCtrtMngtModule();
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
		<form id="gamPopupCtrtSubCtrtMngtForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>업체명</th>
                        <td><input id="entrpsNm" type="text" style="width: 150px;" class="EditItem" maxlength="33"/></td>
                        <th>대금지급합의</th>
                        <td><input id="moneyPymntAgree" type="text" style="width: 150px;" class="EditItem" maxlength="2"/></td>
						<th>공증</th>
                        <td><input id="workClass" type="text" style="width: 150px;" class="EditItem" maxlength="33"/></td>
					</tr>
					<tr>
                    	<th>하도급율</th>
                        <td><input id="subctrtRate" type="text" style="width: 150px;" class="EditItem ygpaNumber" data-decimal-point="5"/>%</td>
                        <th>원도급금액</th>
                        <td><input id="orginlContrAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
						<th>하도급계약금액</th>
                        <td><input id="subctrtCtrtAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
					</tr>
					<tr>
                    	<th>하도급계약기간</th>
                        <td colspan="5">
                        	<input id="ctrtDtFrom" type="text" style="width: 150px;" class="EditItem emdcal"/>
                        	~<input id="ctrtDtTo" type="text" style="width: 150px;" class="EditItem emdcal"/>
                        </td>
					</tr>
				</tbody>
			</table>
		</form>
	    </div>

	</div>
</div>