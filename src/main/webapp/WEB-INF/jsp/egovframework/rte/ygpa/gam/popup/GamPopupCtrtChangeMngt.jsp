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
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
                    {display:'변경일자', name:'changeDt', width:80, sortable:true, align:'center'},
                    {display:'변경사유', name:'changeRsn', width:120, sortable:true, align:'center'},
                    {display:'변경계약기간from', name:'changeCtrtPdFrom', width:100, sortable:true, align:'center'},
                    {display:'변경계약기간to', name:'changeCtrtPdTo', width:100, sortable:true, align:'center'},
                    {display:'변경계약금액', name:'changeCtrtAmt', width:100, sortable:true, align:'right', displayFormat:'number'},
                    {display:'최종계약금액', name:'lastCtrtAmt', width:100, sortable:true, align:'right', displayFormat:'number'},
                    {display:'비고', name:'rm', width:250, sortable:true, align:'left'}
				],
		height: "270",
	});
	this.$("#grdInfoList").flexOptions({params:null}).flexReload();

	this.$("#grdInfoList").flexAddData({resultList: fcltyCtrtChangeList});
	
	
	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
	});

	this.$("#grdInfoList").on("onItemUnSelected", function(event, module, row, grid, param) {
	});

	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.ctrtJoinContrChanged(event.target);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamPopupCtrtChangeMngtForm input").val('');
		module.makeFormValues("#gamPopupCtrtChangeMngtForm", row);
	});
};

//속성 변경 된 경우 이벤트 실행
GamPopupCtrtChangeMngtModule.prototype.ctrtJoinContrChanged = function(target) {
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


// 사용자 설정 함수 추가

GamPopupCtrtChangeMngtModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "btnEntrpsSearch":
		var searchOpt=this.makeFormArgs("#gamPopupCtrtChangeMngtForm");
	 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
	 	throw 0;
		break;
	case "btnOk":
		var inputVO = this.$('#grdInfoList').flexGetData();
		this.closeDialog("ok", inputVO);
		break;
	case "cancel":
		this.cancelDialog();
	// 추가
	case "addBtn":
		this.$('#gamPopupCtrtChangeMngtForm :input').val('');
		this.$("#grdInfoList").flexAddRow({'changeDt':'','changeRsn':'','changeCtrtPdFrom':'','changeCtrtPdTo':'','changeCtrtAmt':'','lastCtrtAmt':'' ,'rm':''});
	break;
		case "btnRemove":
			this.removeCtrtChangeItem();
		break;
	}
};

GamPopupCtrtChangeMngtModule.prototype.removeCtrtChangeItem = function() {
	var rows = this.$("#grdInfoList").selectedRows();

    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }

    if(this.$("#grdInfoList").selectedRowIds().length>0) {
    	for(var i=this.$("#grdInfoList").selectedRowIds().length-1; i>=0; i--) {

    		var row = this.$("#grdInfoList").flexGetRow(this.$("#grdInfoList").selectedRowIds()[i]);

        	this.$("#grdInfoList").flexRemoveRow(this.$("#grdInfoList").selectedRowIds()[i]);

        	this._edited=true;
		}

    	alert("삭제되었습니다.");
	}

    this.$("#gamPopupCtrtChangeMngtForm").find(":input").val("");
    this._editDataFile = null;
};

GamPopupCtrtChangeMngtModule.prototype.onSubmit = function() {
	//this.showAlert(this.$("#prtCode").val()+"을(를) 조회 하였습니다");
	this.loadData();
};

GamPopupCtrtChangeMngtModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupCtrtChangeMngtForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};


// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupCtrtChangeMngtModule();
</script>
<div class="dialog">
	<div class="emdPanel">

		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="addBtn">추가</button>
	            <button id="btnRemove">삭제</button>
	            <button id="btnOk">확인</button>
            	<button id="cancel">취소</button>
	        </div>
		<form id="gamPopupCtrtChangeMngtForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>변경일자</th>
                        <td><input id="changeDt" type="text" style="width: 150px;" class="EditItem emdcal"/></td>
                        <th>변경사유</th>
                        <td><input id="changeRsn" type="text" style="width: 200px;" class="EditItem"/></td>
					</tr>
					<tr>
                    	<th>변경기간</th>
                        <td colspan="3">
                        	<input id="changeCtrtPdFrom" type="text" style="width: 150px;" class="EditItem emdcal"/>
                        	~<input id="changeCtrtPdTo" type="text" style="width: 150px;" class="EditItem emdcal"/>
                        </td>
					</tr>
					<tr>
                        <th>변경계약금액</th>
                        <td><input id="changeCtrtAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
						<th>최종계약금액</th>
                        <td colspan=><input id="lastCtrtAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
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