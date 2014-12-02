<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupFlawExamUsrF.jsp
  * @Description : 유지보수 하자검사자 정보 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.1  	HNJ          최초 생성
  *
  * author HNJ
  * since 2014.12.1
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupFlawExamUsrFModule() {}

GamPopupFlawExamUsrFModule.prototype = new EmdPopupModule(1000, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupFlawExamUsrFModule.prototype.loadComplete = function(mntnRprObjFcltsF) {



	this._deleteDataRepairList = [];
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
					{display:"상태",			name:"_updtId",				width:60,		sortable:false,		align:"center"},
					{display:"순번",				name:"seq",					width:100,		sortable:false,		align:"center"},
					{display:"하자검사자",			name:"flawExamUsr",			width:250,		sortable:false,		align:"center"},
					{display:"하자검사일자",		name:"flawExamDt",			width:250,		sortable:false,		align:"center"},
					{display:"하자검사완료여부",		name:"flawExamComptYn",		width:250,		sortable:false,		align:"center"}
			],
		height: "300",
	});

	this.$("#grdInfoList").flexAddData({resultList: mntnRprObjFcltsF});

	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyDataChanged(event.target);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		if(row["_updtId"] != "I"){
			module.$('#fcltsMngNo').disable();
		}else{
			module.$('#fcltsMngNo').enable();
		}
		module.$("#gamPopupRepairForm input").val('');
		module.makeFormValues("#gamPopupRepairForm", row);
	});

	console.log('debug');

};
//속성 변경 된 경우 이벤트 실행
GamPopupFlawExamUsrFModule.prototype.applyDataChanged = function(target) {
	var changed=false;
	var row={};
	// // console.log("change event occur");

	var selectRow = this.$('#grdInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#seq').is(target)) {
			row['seq'] = $(target).val();
			changed=true;
		}
		if(this.$('#flawExamUsr').is(target)) {
			row['flawExamUsr'] = $(target).val();
			changed=true;
		}
		if(this.$('#flawExamDt').is(target)) {
			row['flawExamDt'] = $(target).val();
			changed=true;
		}
		if(this.$('#flawExamComptYn').is(target)) {
			row['flawExamComptYn'] = $(target).find('option:selected').text();
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

GamPopupFlawExamUsrFModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "btnRepairSearch":
		var searchOpt=this.makeFormArgs("#gamPopupRepairForm");
	 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
	 	throw 0;
		break;
	case "btnOk":
		var inputVO = this.$('#grdInfoList').flexGetData();
		var merge = {'inputVo':inputVO, 'deleteDataRepairList':this._deleteDataRepairList};

		this.closeDialog("ok", merge);
		break;
	case "cancel":
		this.cancelDialog();
	// 추가
	case "addBtn":
		this.$('#fcltsMngNo').enable();
		this.$('#gamPopupRepairForm :input').val('');

		this.$("#grdInfoList").flexAddRow({'_updtId': 'I','seq':'','flawExamUsr':'','flawExamDt':'','flawExamComptYn':''});
		var all_rows = this.$('#grdInfoList').flexGetData();
		var sel_row_id = all_rows.length - 1;
		this.$("#grdInfoList").selectRowId(sel_row_id);
	break;
		case "btnRemove":
			this.removeRepairItem();
		break;
	}
};

GamPopupFlawExamUsrFModule.prototype.removeRepairItem = function() {
	var rows = this.$("#grdInfoList").selectedRows();

    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }

    if(this.$("#grdInfoList").selectedRowIds().length>0) {
    	for(var i=this.$("#grdInfoList").selectedRowIds().length-1; i>=0; i--) {

    		var row = this.$("#grdInfoList").flexGetRow(this.$("#grdInfoList").selectedRowIds()[i]);

    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataRepairList[this._deleteDataRepairList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#grdInfoList").flexRemoveRow(this.$("#grdInfoList").selectedRowIds()[i]);

        	this._edited=true;
		}

    	alert("삭제되었습니다.");
	}

    //this.$("#gamPopupRepairForm").find(":input").val("");
    this._editDataFile = null;
};

GamPopupFlawExamUsrFModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupFlawExamUsrFModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupRepairForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};


// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupFlawExamUsrFModule();
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
		<form id="gamPopupRepairForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>순번</th>
                        <td><input id="seq" type="text" style="width: 150px;" title="순번" maxlength="20" class="EditItem"/></td>
                        <th>하자검사자</th>
                        <td><input id="flawExamUsr" type="text" style="width: 150px;" title="하자검사자" maxlength="20" class="EditItem"/></td>
						<th>하자검사일자</th>
                        <td><input id="flawExamDt" type="text" style="width: 150px;" title="하자검사일자" maxlength="20" class="emdcal EditItem"/></td>
                        <th>하자검사완료여부</th>
                        <td >
                        	<select id="flawExamComptYn" class="EditItem">
                        		<option value="">선택</option>
                        		<option value="Y">Y</option>
                        		<option value="N">N</option>
                        	</select>
                        </td>

					</tr>
				</tbody>
			</table>
		</form>
	    </div>

	</div>
</div>