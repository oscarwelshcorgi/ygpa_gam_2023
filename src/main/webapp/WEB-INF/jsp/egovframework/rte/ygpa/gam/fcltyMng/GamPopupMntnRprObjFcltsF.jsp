<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupMntnRprObjFcltsF.jsp
  * @Description : 유지보수 대상시설물 정보 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.26  	HNJ          최초 생성
  *
  * author HNJ
  * since 2014.11.26
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupMntnRprObjFcltsFModule() {}

GamPopupMntnRprObjFcltsFModule.prototype = new EmdPopupModule(1000, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupMntnRprObjFcltsFModule.prototype.loadComplete = function(mntnRprObjFcltsF) {



	this._deleteDataMaintList = [];
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
					{display:"상태",			name:"_updtId",				width:60,		sortable:false,		align:"center"},
					{display:"관리번호",			name:"fcltsMngNo",			width:60,		sortable:false,		align:"center"},
					{display:"유지보수공법",		name:"mntnRprCnstMth",		width:80,		sortable:false,		align:"center"},
					{display:"단위",				name:"unit",				width:80,		sortable:false,		align:"center"},
					{display:"수량",				name:"qy",					width:140,		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"단가",				name:"price",				width:140,		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"공사금액",			name:"mntnRprCnstAmt",		width:140,		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"비고",				name:"rm",					width:200,		sortable:false,		align:"center"}
			],
		height: "300",
	});

	this.$("#grdInfoList").flexAddData({resultList: mntnRprObjFcltsF});

	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyDataChanged(event.target);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamPopupMaintForm input").val('');
		module.makeFormValues("#gamPopupMaintForm", row);
	});

	console.log('debug');

};
//속성 변경 된 경우 이벤트 실행
GamPopupMntnRprObjFcltsFModule.prototype.applyDataChanged = function(target) {
	var changed=false;
	var row={};
	// // console.log("change event occur");

	var selectRow = this.$('#grdInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#fcltsMngNo').is(target)) {
			row['fcltsMngNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#mntnRprCnstMth').is(target)) {
			row['mntnRprCnstMth'] = $(target).val();
			changed=true;
		}
		if(this.$('#unit').is(target)) {
			row['unit'] = $(target).val();
			changed=true;
		}
		if(this.$('#qy').is(target)) {
			row['qy'] = $(target).val();
			changed=true;
		}
		if(this.$('#price').is(target)) {
			row['price'] = $(target).val();
			changed=true;
		}
		if(this.$('#mntnRprCnstAmt').is(target)) {
			row['mntnRprCnstAmt'] = $(target).val();
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

GamPopupMntnRprObjFcltsFModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "btnMaintSearch":
		var searchOpt=this.makeFormArgs("#gamPopupMaintForm");
	 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
	 	throw 0;
		break;
	case "btnOk":
		var inputVO = this.$('#grdInfoList').flexGetData();
		var merge = {'inputVo':inputVO, 'deleteDataMaintList':this._deleteDataMaintList};

		this.closeDialog("ok", merge);
		break;
	case "cancel":
		this.cancelDialog();
	// 추가
	case "addBtn":
		this.$('#gamPopupMaintForm :input').val('');

		this.$("#grdInfoList").flexAddRow({'_updtId': 'I','fcltsMngNo':'','mntnRprCnstMth':'','unit':'','qy':'','price':'','mntnRprCnstAmt':'','rm':''});
		var all_rows = this.$('#grdInfoList').flexGetData();
		var sel_row_id = all_rows.length - 1;
		this.$("#grdInfoList").selectRowId(sel_row_id);
	break;
		case "btnRemove":
			this.removeMaintItem();
		break;
	}
};

GamPopupMntnRprObjFcltsFModule.prototype.removeMaintItem = function() {
	var rows = this.$("#grdInfoList").selectedRows();

    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }

    if(this.$("#grdInfoList").selectedRowIds().length>0) {
    	for(var i=this.$("#grdInfoList").selectedRowIds().length-1; i>=0; i--) {

    		var row = this.$("#grdInfoList").flexGetRow(this.$("#grdInfoList").selectedRowIds()[i]);

    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataMaintList[this._deleteDataMaintList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#grdInfoList").flexRemoveRow(this.$("#grdInfoList").selectedRowIds()[i]);

        	this._edited=true;
		}

    	alert("삭제되었습니다.");
	}

    this.$("#fcltyGisPhotoForm").find(":input").val("");
    this._editDataFile = null;
};

GamPopupMntnRprObjFcltsFModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupMntnRprObjFcltsFModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupMaintForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};


// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupMntnRprObjFcltsFModule();
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
		<form id="gamPopupMaintForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>관리번호</th>
                        <td><input id="fcltsMngNo" type="text" style="width: 150px;" title="관리번호" maxlength="20" class="EditItem"/></td>
                        <th>유지보수공법</th>
                        <td><input id="mntnRprCnstMth" type="text" style="width: 150px;" title="유지보수공법" maxlength="20" class="EditItem"/></td>
						<th>단위</th>
                        <td><input id="unit" type="text" style="width: 150px;" title="단위" maxlength="20" class="EditItem"/></td>
                        <th>수량</th>
                        <td ><input id="qy" type="text" style="width: 150px;" title="수량" maxlength="20" class="EditItem"/></td>

					</tr>
					<tr>
                    	<th>단가</th>
						<td><input id="price" type="text" style="width: 150px;" title="단가" maxlength="20" class="EditItem"/></td>
						<th>공사금액</th>
						<td><input id="mntnRprCnstAmt" type="text" style="width: 150px;" title="공사금액" maxlength="20" class="EditItem"/></td>
						<th>비고</th>
						<td colspan="3"><input id="rm" type="text" style="width: 380px;" title="비고" maxlength="20" class="EditItem"/></td>
					</tr>
				</tbody>
			</table>
		</form>
	    </div>

	</div>
</div>