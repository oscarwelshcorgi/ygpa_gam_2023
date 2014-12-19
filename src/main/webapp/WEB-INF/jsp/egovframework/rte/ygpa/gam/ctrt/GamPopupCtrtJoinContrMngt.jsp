<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupCtrtJoinContrMngt.jsp
  * @Description :  계약공동도급 추가/삭제 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.30  김종민          최초 생성
  *
  * author 김종민
  * since 2014.10.30
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupCtrtJoinContrMngtModule() {}

GamPopupCtrtJoinContrMngtModule.prototype = new EmdPopupModule(1000, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupCtrtJoinContrMngtModule.prototype.loadComplete = function(fcltyCtrtJoinContrList) {
	this._deleteCtrtJoinContrList = [];
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
                    {display:'업체명', 		name:'entrpsNm',	width:120, sortable:false,align:'center'},
                    {display:'대표자', 		name:'rprsntv',		width:70, sortable:false,align:'center'},
                    {display:'지분율', 		name:'qotaRate',	width:70, sortable:false,align:'right'},
                    {display:'업종', 			name:'induty',		width:80, sortable:false,align:'center'},
                    {display:'주요품목', 		name:'stplPrdlst',	width:100, sortable:false,align:'left'},
                    {display:'사업자번호', 		name:'bsnmNo',		width:100, sortable:false,align:'center'},
                    {display:'거래관계', 		name:'dealRelate',	width:80, sortable:false,align:'left'},
                    {display:'전화번호', 		name:'tlphonNo',	width:100, sortable:false,align:'center'},
                    {display:'fax번호', 		name:'faxNo',		width:100, sortable:false,align:'center'},
                    {display:'우편번호', 		name:'postNo',		width:80, sortable:false,align:'center'},
                    {display:'도로명주소', 		name:'roadnmAdres',	width:150, sortable:false,align:'left'},
                    {display:'지번주소', 		name:'lnmAdres',	width:150, sortable:false,align:'left'},
                    {display:'담당자', 		name:'charger',		width:50, sortable:false,align:'center'},
                    {display:'담당자직위', 		name:'chargerOfcPos',	width:80, sortable:false,align:'center'},
                    {display:'담당자휴대폰번호', 	name:'chargerMoblphonNo',width:100, sortable:false,align:'center'},
                    {display:'담당자email', 	name:'chargerEmail',	width:150, sortable:false,align:'left'}
				],
		height: "200",
	});
	this.$("#grdInfoList").flexOptions({params:null}).flexReload();

	this.$("#grdInfoList").flexAddData({resultList: fcltyCtrtJoinContrList});
	
	
	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.ctrtJoinContrChanged(event.target);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamPopupCtrtJoinContrMngtForm input").val('');
		module.makeFormValues("#gamPopupCtrtJoinContrMngtForm", row);
	});
};

//속성 변경 된 경우 이벤트 실행
GamPopupCtrtJoinContrMngtModule.prototype.ctrtJoinContrChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#grdInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#entrpsNm').is(target)) {
			row['entrpsNm'] = $(target).val();
			changed=true;
		}
		if(this.$('#rprsntv').is(target)) {
			row['rprsntv'] = $(target).val();
			changed=true;
		}
		if(this.$('#qotaRate').is(target)) {
			row['qotaRate'] = $(target).val();
			changed=true;
		}
		if(this.$('#induty').is(target)) {
			row['induty'] = $(target).val();
			changed=true;
		}
		if(this.$('#stplPrdlst').is(target)) {
			row['stplPrdlst'] = $(target).val();
			changed=true;
		}
		if(this.$('#bsnmNo').is(target)) {
			row['bsnmNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#dealRelate').is(target)) {
			row['dealRelate'] = $(target).val();
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
		if(this.$('#postNo').is(target)) {
			row['postNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#roadnmAdres').is(target)) {
			row['roadnmAdres'] = $(target).val();
			changed=true;
		}
		if(this.$('#lnmAdres').is(target)) {
			row['lnmAdres'] = $(target).val();
			changed=true;
		}
		if(this.$('#charger').is(target)) {
			row['charger'] = $(target).val();
			changed=true;
		}
		if(this.$('#chargerOfcPos').is(target)) {
			row['chargerOfcPos'] = $(target).val();
			changed=true;
		}
		if(this.$('#chargerMoblphonNo').is(target)) {
			row['chargerMoblphonNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#chargerEmail').is(target)) {
			row['chargerEmail'] = $(target).val();
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
GamPopupCtrtJoinContrMngtModule.prototype.returnData = function() {
	var resultList = this.$('#grdInfoList').flexGetData();
	var mergeData = {'resultList' : resultList, 'deleteCtrtJoinContrList' : this._deleteCtrtJoinContrList};
	this.closeDialog("ok", mergeData);
};

// 계약공동도급 추가
GamPopupCtrtJoinContrMngtModule.prototype.addCtrtJoinContrItem = function() {
	this.$('#gamPopupCtrtJoinContrMngtForm :input').val('');
	this.$("#grdInfoList").flexAddRow({'_updtId': 'I', 'ctrtNo':'', 'seq':'', 'entrpsNm':'','rprsntv':'','qotaRate':'','induty':'','stplPrdlst':'','bsnmNo':'' ,'dealRelate':'','tlphonNo':'','faxNo':'','postNo':'','roadnmAdres':'','lnmAdres':'','charger':'','chargerOfcPos':'','chargerMoblphonNo':'','chargerEmail':''});
	var allRows = this.$('#grdInfoList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#grdInfoList").selectRowId(selRowId);
};

// 계약공동도급 삭제
GamPopupCtrtJoinContrMngtModule.prototype.removeCtrtJoinContrItem = function() {
	var rows = this.$("#grdInfoList").selectedRows();
    if(rows.length == 0){
        alert("계약공동도급목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#grdInfoList").selectedRowIds().length>0) {
    	for(var i=this.$("#grdInfoList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#grdInfoList").flexGetRow(this.$("#grdInfoList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteCtrtJoinContrList[this._deleteCtrtJoinContrList.length] = row;
			}
        	this.$("#grdInfoList").flexRemoveRow(this.$("#grdInfoList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamPopupCtrtJoinContrMngtForm :input").val("");
};

GamPopupCtrtJoinContrMngtModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case "btnOk":
			this.returnData();
			break;
		case "btnCancel":
			this.cancelDialog();
			break;
		case "btnAdd":
			this.addCtrtJoinContrItem();
			break;
		case "btnRemove":
			this.removeCtrtJoinContrItem();
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupCtrtJoinContrMngtModule();
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
		<form id="gamPopupCtrtJoinContrMngtForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>업체명</th>
                        <td><input id="entrpsNm" type="text" style="width: 150px;" class="EditItem" maxlength="33"/></td>
                        <th>대표자</th>
                        <td><input id="rprsntv" type="text" style="width: 150px;" class="EditItem" maxlength="33"/></td>
						<th>지분율</th>
                        <td><input id="qotaRate" type="text" style="width: 150px;" class="EditItem ygpaNumber" data-decimal-point="5"/>%</td>
					</tr>
					<tr>
                    	<th>업종</th>
                        <td><input id="induty" type="text" style="width: 150px;" class="EditItem" maxlength="13"/></td>
                        <th>주요품목</th>
                        <td><input id="stplPrdlst" type="text" style="width: 150px;" class="EditItem" maxlength="13"/></td>
						<th>사업자번호</th>
                        <td><input id="bsnmNo" type="text" style="width: 150px;" class="EditItem" maxlength="14"/></td>
					</tr>
					<tr>
                    	<th>거래관계</th>
                        <td><input id="dealRelate" type="text" style="width: 150px;" class="EditItem" maxlength="2"/></td>
                        <th>전화번호</th>
                        <td><input id="tlphonNo" type="text" style="width: 150px;" class="EditItem" maxlength="100"/></td>
						<th>팩스번호</th>
                        <td><input id="faxNo" type="text" style="width: 150px;" class="EditItem" maxlength="100"/></td>
					</tr>
					<tr>
                    	<th>담당자</th>
                        <td><input id="charger" type="text" style="width: 150px;" class="EditItem" maxlength="6"/></td>
                        <th>담당자직위</th>
                        <td><input id="chargerOfcPos" type="text" style="width: 150px;" class="EditItem" maxlength="6"/></td>
						<th>담당자휴대폰번호</th>
                        <td><input id="chargerMoblphonNo" type="text" style="width: 150px;" class="EditItem" maxlength="20"/></td>
					</tr>
					<tr>
                    	<th>담당자이메일</th>
                        <td><input id="chargerEmail" type="text" style="width: 150px;" class="EditItem" maxlength="50"/></td>
                        <th>도로명 주소</th>
                        <td colspan="3"><input id="roadnmAdres" type="text" style="width: 520px;" class="EditItem" maxlength="65"/></td>
					</tr>					
					<tr>
                    	<th>우편번호</th>
                        <td><input id="postNo" type="text" style="width: 150px;" class="EditItem" maxlength="7"/></td>
                        <th>지번주소</th>
                        <td colspan="3"><input id="lnmAdres" type="text" style="width: 520px;" class="EditItem" maxlength="65"/></td>
					</tr>
				</tbody>
			</table>
		</form>
	    </div>

	</div>
</div>