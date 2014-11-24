<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupFcltySpecPopup.jsp
  * @Description : 건축시설 층별정보 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.11  	HNJ          최초 생성
  *
  * author HNJ
  * since 2014.11.11
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupEntrpsModule() {}

GamPopupEntrpsModule.prototype = new EmdPopupModule(1000, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupEntrpsModule.prototype.loadComplete = function(fcltyinfo9List) {



	this._deleteDataFloorSpecList = [];
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
					{display:"구역",			name:"bound",			width:60,		sortable:false,		align:"center"},
					{display:"층구분",		name:"strySe",			width:60,		sortable:false,		align:"center"},
					{display:"면적",			name:"ar",				width:80,		sortable:false,		align:"center"},
					{display:"벽마감재",		name:"wallFnsh",		width:140,		sortable:false,		align:"center"},
					{display:"바닥마감재",		name:"flrFnsh",			width:140,		sortable:false,		align:"center"},
					{display:"천장",			name:"ceil",			width:140,		sortable:false,		align:"center"},
					{display:"사용용도",		name:"usagePrpos",		width:140,		sortable:false,		align:"center"},
					{display:"비고",			name:"rm",				width:140,		sortable:false,		align:"center"}
			],
		height: "300",
	});
	//this.$("#grdInfoList").flexOptions({params:null}).flexReload();

		this.$("#grdInfoList").flexAddData({resultList: fcltyinfo9List});
/*
	this.$("#grdInfoList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.closeDialog("ok", row);
	});
 */
	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		//alert("row " + row["assetCls"]+"-"+row["assetNo"]+"-"+row["assetNoSeq"]+" is selected");
	});

	this.$("#grdInfoList").on("onItemUnSelected", function(event, module, row, grid, param) {
		//alert("row " + row["assetCls"]+"-"+row["assetNo"]+"-"+row["assetNoSeq"]+" is unselected");
	});


	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyPhotoChanged(event.target);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamPopupEntrpsForm input").val('');
		module.makeFormValues("#gamPopupEntrpsForm", row);


	});

	console.log('debug');

};
//속성 변경 된 경우 이벤트 실행
GamPopupEntrpsModule.prototype.applyPhotoChanged = function(target) {
	var changed=false;
	var row={};
	// // console.log("change event occur");

	var selectRow = this.$('#grdInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#bound').is(target)) {
			row['bound'] = $(target).val();
			changed=true;
		}
		if(this.$('#strySe').is(target)) {
			row['strySe'] = $(target).val();
			changed=true;
		}
		if(this.$('#ar').is(target)) {
			row['ar'] = $(target).val();
			changed=true;
		}
		if(this.$('#wallFnsh').is(target)) {
			row['wallFnsh'] = $(target).val();
			changed=true;
		}
		if(this.$('#flrFnsh').is(target)) {
			row['flrFnsh'] = $(target).val();
			changed=true;
		}
		if(this.$('#ceil').is(target)) {
			row['ceil'] = $(target).val();
			changed=true;
		}
		if(this.$('#usagePrpos').is(target)) {
			row['usagePrpos'] = $(target).val();
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

GamPopupEntrpsModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "btnEntrpsSearch":
		/*
		if(this.$("#entrpscd").val() == "" && this.$("#bizrno").val() == ""){
			if(this.$("#entrpsNm").val() == "" || this.$("#entrpsNm").val().length < 2){
				this.$("#entrpsNm").focus();
				alert("업체 명은 2자 이상 입력하십시오.");
				return;
			}
		}
		 */
		var searchOpt=this.makeFormArgs("#gamPopupEntrpsForm");
	 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
	 	throw 0;
		break;
	case "btnOk":
		var inputVO = this.$('#grdInfoList').flexGetData();
		var merge = {'inputVo':inputVO, 'deleteDataFloorSpecList':this._deleteDataFloorSpecList};

		this.closeDialog("ok", merge);
		break;
	case "cancel":
		this.cancelDialog();
	// 추가
	case "addBtn":
		this.$('#gamPopupEntrpsForm :input').val('');
		/*
		this._maxOlnlpSeq+=1;
		this._edited=false;
		this._editData={_updtId: "I", gisAssetsPrtAtCode: this._selectRow.gisAssetsPrtAtCode
				, gisAssetsCd: this._selectRow.gisAssetsCd
				, gisAssetsSubCd: this._selectRow.gisAssetsSubCd, olnlp: 0, olnlpSeq: EMD.util.leftPad(this._maxOlnlpSeq, '0', 2)};

		this.$("#gamPopupEntrpsForm").selectRowId(this._editRow);
		 */
		 
		 
		 this.$("#grdInfoList").flexAddRow({'_updtId': 'I','bound':'','strySe':'','ar':'','wallFnsh':'','flrFnsh':'','ceil':'','usagePrpos':'','rm':''});
		 var all_rows = this.$('#grdInfoList').flexGetData();
		 var sel_row_id = all_rows.length - 1;
		 this.$("#grdInfoList").selectRowId(sel_row_id);
	break;
		case "btnRemove":
			this.removeGisAssetPhotoItem();
		break;
	}
};

GamPopupEntrpsModule.prototype.removeGisAssetPhotoItem = function() {
	var rows = this.$("#grdInfoList").selectedRows();

    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }

    if(this.$("#grdInfoList").selectedRowIds().length>0) {
    	for(var i=this.$("#grdInfoList").selectedRowIds().length-1; i>=0; i--) {

    		var row = this.$("#grdInfoList").flexGetRow(this.$("#grdInfoList").selectedRowIds()[i]);

    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataFloorSpecList[this._deleteDataFloorSpecList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#grdInfoList").flexRemoveRow(this.$("#grdInfoList").selectedRowIds()[i]);

        	this._edited=true;
		}

    	alert("삭제되었습니다.");
	}

    this.$("#fcltyGisPhotoForm").find(":input").val("");
    this._editDataFile = null;
};

GamPopupEntrpsModule.prototype.onSubmit = function() {
	//this.showAlert(this.$("#prtCode").val()+"을(를) 조회 하였습니다");
	this.loadData();
};

GamPopupEntrpsModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupEntrpsForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};


// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupEntrpsModule();
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
		<form id="gamPopupEntrpsForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>구역</th>
                        <td><input id="bound" type="text" style="width: 150px;" title="구역" maxlength="20" class="EditItem"/></td>
                        <th>층구분</th>
                        <td><input id="strySe" type="text" style="width: 150px;" title="층구분" maxlength="20" class="EditItem"/></td>
						<th>면적</th>
                        <td><input id="ar" type="text" style="width: 150px;" title="면적" maxlength="20" class="EditItem"/></td>
                        <th>벽마감재</th>
                        <td ><input id="wallFnsh" type="text" style="width: 150px;" title="벽마감재" maxlength="20" class="EditItem"/></td>

					</tr>
					<tr>
                    	<th>바닥마감재</th>
						<td><input id="flrFnsh" type="text" style="width: 150px;" title="용도" maxlength="20" class="EditItem"/></td>
						<th>천장</th>
						<td><input id="ceil" type="text" style="width: 150px;" title="면적㎡" maxlength="20" class="EditItem"/></td>
						<th>사용용도</th>
						<td><input id="usagePrpos" type="text" style="width: 150px;" title="용도" maxlength="20" class="EditItem"/></td>
						<th>비고</th>
						<td><input id="rm" type="text" style="width: 150px;" title="면적㎡" maxlength="20" class="EditItem"/></td>
					</tr>
				</tbody>
			</table>
		</form>
	    </div>

	</div>
</div>