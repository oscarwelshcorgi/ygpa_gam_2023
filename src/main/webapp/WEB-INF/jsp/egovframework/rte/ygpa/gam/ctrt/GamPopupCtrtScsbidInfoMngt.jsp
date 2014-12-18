<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupCtrtScsbidInfoMngt.jsp
  * @Description :  계약낙찰정보 추가/삭제 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.18  김종민          최초 생성
  *
  * author 김종민
  * since 2014.12.18
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupCtrtScsbidInfoMngtModule() {}

GamPopupCtrtScsbidInfoMngtModule.prototype = new EmdPopupModule(1000, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupCtrtScsbidInfoMngtModule.prototype.loadComplete = function(fcltyCtrtScsbidInfoList) {
	this._deleteCtrtScsbidInfoList = [];
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
                    {display:'낙찰순위', 	name:'scsbidRank',		width:90, sortable:false,align:'center'},
                    {display:'업체명', 	name:'entrpsNm',		width:150, sortable:false,align:'center'},
                    {display:'대표자', 	name:'rprsntv',			width:100, sortable:false,align:'center'},
                    {display:'사업자번호', 	name:'bsnmNo',			width:100, sortable:false,align:'center'},
                    {display:'전화번호', 	name:'tlphonNo',		width:100, sortable:false,align:'center'},
                    {display:'fax번호',	name:'faxNo',			width:100, sortable:false,align:'center'},
				],
		height: "300",
	});
	this.$("#grdInfoList").flexOptions({params:null}).flexReload();

	this.$("#grdInfoList").flexAddData({resultList: fcltyCtrtScsbidInfoList});
	
	
	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.ctrtJoinContrChanged(event.target);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamPopupCtrtScsbidInfoMngtForm input").val('');
		module.makeFormValues("#gamPopupCtrtScsbidInfoMngtForm", row);
	});
};

//속성 변경 된 경우 이벤트 실행
GamPopupCtrtScsbidInfoMngtModule.prototype.ctrtJoinContrChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#grdInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#scsbidRank').is(target)) {
			row['scsbidRank'] = $(target).val();
			changed=true;
		}
		if(this.$('#entrpsNm').is(target)) {
			row['entrpsNm'] = $(target).val();
			changed=true;
		}
		if(this.$('#rprsntv').is(target)) {
			row['rprsntv'] = $(target).val();
			changed=true;
		}
		if(this.$('#bsnmNo').is(target)) {
			row['bsnmNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#tlphonNo').is(target)) {
			row['tlphonNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#faxNo').is(target)) {
			row['faxNo'] = $(target).val();
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

// 계약공동도급 병합 리턴
GamPopupCtrtScsbidInfoMngtModule.prototype.returnData = function() {
	var resultList = this.$('#grdInfoList').flexGetData();
	var mergeData = {'resultList' : resultList, 'deleteCtrtScsbidInfoList' : this._deleteCtrtScsbidInfoList};
	this.closeDialog("ok", mergeData);
};

// 계약공동도급 추가
GamPopupCtrtScsbidInfoMngtModule.prototype.addCtrtScsbidInfoItem = function() {
	this.$('#gamPopupCtrtScsbidInfoMngtForm :input').val('');
	this.$("#grdInfoList").flexAddRow({'_updtId': 'I', 'ctrtNo':'', 'seq':'', 'scsbidRank': '', 'entrpsNm':'','rprsntv':'','bsnmNo':'' ,'tlphonNo':'','faxNo':''});
	var allRows = this.$('#grdInfoList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#grdInfoList").selectRowId(selRowId);
};

// 계약공동도급 삭제
GamPopupCtrtScsbidInfoMngtModule.prototype.removeCtrtScsbidInfoItem = function() {
	var rows = this.$("#grdInfoList").selectedRows();
    if(rows.length == 0){
        alert("계약공동도급목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#grdInfoList").selectedRowIds().length>0) {
    	for(var i=this.$("#grdInfoList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#grdInfoList").flexGetRow(this.$("#grdInfoList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteCtrtScsbidInfoList[this._deleteCtrtScsbidInfoList.length] = row;
			}
        	this.$("#grdInfoList").flexRemoveRow(this.$("#grdInfoList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamPopupCtrtScsbidInfoMngtForm :input").val("");
};

GamPopupCtrtScsbidInfoMngtModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case "btnOk":
			this.returnData();
			break;
		case "btnCancel":
			this.cancelDialog();
			break;
		case "btnAdd":
			this.addCtrtScsbidInfoItem();
			break;
		case "btnRemove":
			this.removeCtrtScsbidInfoItem();
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupCtrtScsbidInfoMngtModule();
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
		<form id="gamPopupCtrtScsbidInfoMngtForm">
			<table class="searchPanel">
				<tbody>
					<tr>
						<th>낙찰순위</th>
                        <td><input id="scsbidRank" type="text" style="width: 150px;" class="EditItem ygpaNumber" maxlength="3"/></td>
                        <th>업체명</th>
                        <td><input id="entrpsNm" type="text" style="width: 150px;" class="EditItem" maxlength="100"/></td>
                        <th>대표자</th>
                        <td><input id="rprsntv" type="text" style="width: 150px;" class="EditItem" maxlength="100"/></td>
					</tr>
					<tr>
						<th>사업자번호</th>
                        <td><input id="bsnmNo" type="text" style="width: 150px;" class="EditItem" maxlength="14"/></td>
                        <th>전화번호</th>
                        <td><input id="tlphonNo" type="text" style="width: 150px;" class="EditItem" maxlength="100"/></td>
						<th>팩스번호</th>
                        <td><input id="faxNo" type="text" style="width: 150px;" class="EditItem" maxlength="100"/></td>
					</tr>
				</tbody>
			</table>
		</form>
	    </div>

	</div>
</div>