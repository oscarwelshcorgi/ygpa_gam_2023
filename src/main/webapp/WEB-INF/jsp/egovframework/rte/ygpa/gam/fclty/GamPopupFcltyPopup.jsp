<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupEntrpsInfo.jsp
  * @Description : 업체정보 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.22  heroine          최초 생성
  *
  * author heroine
  * since 2014.01.22
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




	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
					{display:"순번",		name:"rnum",	width:60,		sortable:false,		align:"center"},
					{display:"구분",		name:"no1",	width:100,		sortable:false,		align:"center"},
					{display:"층별",		name:"no2",	width:100,		sortable:false,		align:"center"},
					{display:"구조",		name:"no3",	width:180,		sortable:false,		align:"center"},
					{display:"용도",		name:"no4",	width:280,		sortable:false,		align:"center"},
					{display:"면적㎡",		name:"no5",	width:140,		sortable:false,		align:"center"}
			],
		height: "300",
	});
	this.$("#grdInfoList").flexOptions({params:null}).flexReload();

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

};
//속성 변경 된 경우 이벤트 실행
GamPopupEntrpsModule.prototype.applyPhotoChanged = function(target) {
	var changed=false;
	var row={};
	// // console.log("change event occur");

	var selectRow = this.$('#grdInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#rnum').is(target)) {
			row['rnum'] = $(target).val();
			changed=true;
		}
		if(this.$('#no1').is(target)) {
			row['no1'] = $(target).val();
			changed=true;
		}
		if(this.$('#no2').is(target)) {
			row['no2'] = $(target).val();
			changed=true;
		}
		if(this.$('#no3').is(target)) {
			row['no3'] = $(target).val();
			changed=true;
		}
		if(this.$('#no4').is(target)) {
			row['no4'] = $(target).val();
			changed=true;
		}
		if(this.$('#no5').is(target)) {
			row['no5'] = $(target).val();
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
// 		inputVO[inputVO.length]={name: 'resultList', value :JSON.stringify(this.$('#grdInfoList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
// 		inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#grdInfoList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
		/*
		var row = this.$("#grdInfoList").selectedRows();
		if(row.length>0) {
			this.closeDialog("ok", row[0]);
		}
		else {
			alert("먼저 입력 하고자 하는 항목을 선택 하십시요.");
		}
		 */

		 this.closeDialog("ok", inputVO);
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

		 this.$("#grdInfoList").flexAddRow({'no1':'','no2':'','no3':'','no4':'','no5':'','rnum':''});
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
                        <th>순번</th>
                        <td><input id="rnum" type="text" style="width: 150px;" title="순번" maxlength="20" class="EditItem"/></td>
                        <th>구분</th>
                        <td><input id="no1" type="text" style="width: 150px;" title="구분" maxlength="20" class="EditItem"/></td>
						<th>층별</th>
                        <td><input id="no2" type="text" style="width: 150px;" title="층별" maxlength="20" class="EditItem"/></td>
                        <th>구조</th>
                        <td ><input id="no3" type="text" style="width: 150px;" title="구조" maxlength="40" class="EditItem"/></td>

					</tr>
					<tr>
                    	<th>용도</th>
						<td colspan="3"><input id="no4" type="text" style="width: 410px;" title="용도" maxlength="40" class="EditItem"/></td>
						<th>면적㎡</th>
						<td colspan="3"> <input id="no5" type="text" style="width: 150px;" title="면적㎡" maxlength="20" class="EditItem"/></td>
					</tr>
				</tbody>
			</table>
		</form>
	    </div>

	</div>
</div>