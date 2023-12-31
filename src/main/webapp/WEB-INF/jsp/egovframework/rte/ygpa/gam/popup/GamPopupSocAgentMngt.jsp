<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupSocAgentMngtInfo.jsp
  * @Description :  참여업체 추가/삭제 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.09.30  HNJ          최초 생성
  *
  * author HNJ
  * since 2014.09.30
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupSocAgentMngtModule() {}

GamPopupSocAgentMngtModule.prototype = new EmdPopupModule(1000, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupSocAgentMngtModule.prototype.loadComplete = function(socAgentMngtList) {




	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
					{display:'업체코드', 			name:'agentCode',	width:80, 		sortable:false,		align:'center'},
                    {display:'업체명', 			name:'firmKorNm',	width:160, 		sortable:false,		align:'center'},
                    {display:'허가원부일련번호', 	name:'constNo',		width:110, 		sortable:false,		align:'center'},
                    {display:'보전처리대상금액', 	name:'totalAmnt',	width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'보전처리누계액', 		name:'accFee',		width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'비고', 				name:'remark',		width:240, 		sortable:false,		align:'left'}
			],
		height: "300",
	});
	this.$("#grdInfoList").flexOptions({params:null}).flexReload();

	this.$("#grdInfoList").flexAddData({resultList: socAgentMngtList});
	
	
	
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
		module.$("#gamPopupSocAgentMngtForm input").val('');
		module.makeFormValues("#gamPopupSocAgentMngtForm", row);


	});

};
//속성 변경 된 경우 이벤트 실행
GamPopupSocAgentMngtModule.prototype.applyPhotoChanged = function(target) {
	var changed=false;
	var row={};
	// // console.log("change event occur");

	var selectRow = this.$('#grdInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#agentCode').is(target)) {
			row['agentCode'] = $(target).val();
			changed=true;
		}
		if(this.$('#firmKorNm').is(target)) {
			row['firmKorNm'] = $(target).val();
			changed=true;
		}
		if(this.$('#constNo').is(target)) {
			row['constNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#totalAmnt').is(target)) {
			row['totalAmnt'] = $(target).val();
			changed=true;
		}
		if(this.$('#accFee').is(target)) {
			row['accFee'] = $(target).val();
			changed=true;
		}
		if(this.$('#remark').is(target)) {
			row['remark'] = $(target).val();
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

GamPopupSocAgentMngtModule.prototype.onButtonClick = function(buttonId) {
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
		var searchOpt=this.makeFormArgs("#gamPopupSocAgentMngtForm");
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
		this.$('#gamPopupSocAgentMngtForm :input').val('');
		this.$("#grdInfoList").flexAddRow({'agentCode':'','firmKorNm':'','constNo':'','totalAmnt':'','accFee':'','remark':''});
		
		var all_rows = this.$('#grdInfoList').flexGetData();
		var sel_row_id = all_rows.length - 1;
		this.$("#grdInfoList").selectRowId(sel_row_id);
	break;
		case "btnRemove":
			this.removeGisAssetPhotoItem();
		break;
	}
};

GamPopupSocAgentMngtModule.prototype.removeGisAssetPhotoItem = function() {
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

    this.$("#gamPopupSocAgentMngtForm").find(":input").val("");
    this._editDataFile = null;
};

GamPopupSocAgentMngtModule.prototype.onSubmit = function() {
	//this.showAlert(this.$("#prtCode").val()+"을(를) 조회 하였습니다");
	this.loadData();
};

GamPopupSocAgentMngtModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupSocAgentMngtForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};


// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupSocAgentMngtModule();
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
		<form id="gamPopupSocAgentMngtForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>업체코드</th>
                        <td><input id="agentCode" type="text" style="width: 150px;" title="업체코드" maxlength="9" class="EditItem"/></td>
                        <th>업체명</th>
                        <td><input id="firmKorNm" type="text" style="width: 150px;" title="업체명" class="EditItem" readonly="readonly"/></td>
						<th>허가원부일련번호</th>
                        <td><input id="constNo" type="text" style="width: 150px;" title="허가원부일련번호" maxlength="6" class="EditItem"/></td>
					</tr>
					<tr>
                    	<th>보전처리대상금액</th>
                        <td><input id="totalAmnt" type="text" style="width: 150px;" title="보전처리대상금액" maxlength="13" class="ygpaNumber EditItem"/></td>
                        <th>보전처리누계액</th>
                        <td><input id="accFee" type="text" style="width: 150px;" title="보전처리누계액" maxlength="13" class="ygpaNumber EditItem"/></td>
						<th>비고</th>
                        <td><input id="remark" type="text" style="width: 150px;" title="비고" class="EditItem" maxlength="116"/></td>
					</tr>
				</tbody>
			</table>
		</form>
	    </div>

	</div>
</div>