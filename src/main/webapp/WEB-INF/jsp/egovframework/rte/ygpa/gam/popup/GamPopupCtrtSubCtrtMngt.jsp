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
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
                    {display:'업체명', name:'entrpsNm', width:150, sortable:true, align:'center'},
                    {display:'대금지급합의', name:'moneyPymntAgree', width:90, sortable:true, align:'center'},
                    {display:'공증', name:'workClass', width:80, sortable:true, align:'center'},
                    {display:'하도급율', name:'subctrlRate', width:80, sortable:true, align:'center'},
                    {display:'원도급금액', name:'originalContrAmt', width:120, sortable:true, align:'right'},
                    {display:'하도급계약금액', name:'subctrtCtrtAmt', width:120, sortable:true, align:'right'},
                    {display:'계약기간from', name:'ctrtDtFrom', width:100, sortable:true, align:'center'},
                    {display:'계약기간to', name:'ctrtDtTo', width:100, sortable:true, align:'center'}
				],
		height: "280",
	});
	this.$("#grdInfoList").flexOptions({params:null}).flexReload();

	this.$("#grdInfoList").flexAddData({resultList: fcltyCtrtSubCtrtList});
	
	
	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
	});

	this.$("#grdInfoList").on("onItemUnSelected", function(event, module, row, grid, param) {
	});

	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.ctrtJoinContrChanged(event.target);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamPopupCtrtSubCtrtMngtForm input").val('');
		module.makeFormValues("#gamPopupCtrtSubCtrtMngtForm", row);
	});
};

//속성 변경 된 경우 이벤트 실행
GamPopupCtrtSubCtrtMngtModule.prototype.ctrtJoinContrChanged = function(target) {
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
		if(this.$('#subctrlRate').is(target)) {
			row['subctrlRate'] = $(target).val();
			changed=true;
		}
		if(this.$('#originalContrAmt').is(target)) {
			row['originalContrAmt'] = $(target).val();
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


// 사용자 설정 함수 추가

GamPopupCtrtSubCtrtMngtModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "btnEntrpsSearch":
		var searchOpt=this.makeFormArgs("#gamPopupCtrtSubCtrtMngtForm");
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
		this.$('#gamPopupCtrtSubCtrtMngtForm :input').val('');
		this.$("#grdInfoList").flexAddRow({'entrpsNm':'','rprsntv':'','qotaRate':'','induty':'','stplPrdlst':'','bsnmNo':'' ,'dealRelate':'','tlphonNo':'','faxNo':'','postNo':'','roadnmAdres':'','lnmAdres':'','charger':'','chargerOfcPos':'','chargerMoblphonNo':'','chargerEmail':''});
	break;
		case "btnRemove":
			this.removeCtrtSubCtrtItem();
		break;
	}
};

GamPopupCtrtSubCtrtMngtModule.prototype.removeCtrtSubCtrtItem = function() {
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

    this.$("#gamPopupCtrtSubCtrtMngtForm").find(":input").val("");
    this._editDataFile = null;
};

GamPopupCtrtSubCtrtMngtModule.prototype.onSubmit = function() {
	//this.showAlert(this.$("#prtCode").val()+"을(를) 조회 하였습니다");
	this.loadData();
};

GamPopupCtrtSubCtrtMngtModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupCtrtSubCtrtMngtForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};


// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupCtrtSubCtrtMngtModule();
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
		<form id="gamPopupCtrtSubCtrtMngtForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>업체명</th>
                        <td><input id="entrpsNm" type="text" style="width: 150px;" class="EditItem"/></td>
                        <th>대금지급합의</th>
                        <td><input id="moneyPymntAgree" type="text" style="width: 150px;" class="EditItem"/></td>
						<th>공증</th>
                        <td><input id="workClass" type="text" style="width: 150px;" class="EditItem"/></td>
					</tr>
					<tr>
                    	<th>하도급율</th>
                        <td><input id="subctrlRate" type="text" style="width: 150px;" class="EditItem"/></td>
                        <th>원도급금액</th>
                        <td><input id="originalContrAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/></td>
						<th>하도급계약금액</th>
                        <td><input id="subctrtCtrtAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/></td>
					</tr>
					<tr>
                    	<th>계약기간</th>
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