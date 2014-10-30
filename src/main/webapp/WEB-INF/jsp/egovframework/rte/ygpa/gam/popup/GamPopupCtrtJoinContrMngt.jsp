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
GamPopupCtrtJoinContrMngtModule.prototype.loadComplete = function(socAgentMngtList) {
	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '',
		dataType: "json",
		colModel : [
                    {display:'업체명', name:'entrpsNm',width:120, sortable:false,align:'center'},
                    {display:'대표자', name:'rprsntv',width:50, sortable:false,align:'center'},
                    {display:'지분율', name:'qotaRate',width:50, sortable:false,align:'center'},
                    {display:'업종', name:'induty',width:80, sortable:false,align:'center'},
                    {display:'주요품목', name:'stplPrdlst',width:100, sortable:false,align:'center'},
                    {display:'사업자번호', name:'bsnmNo',width:100, sortable:false,align:'center'},
                    {display:'거래관계', name:'dealRelate',width:80, sortable:false,align:'center'},
                    {display:'전화번호', name:'tlphonNo',width:100, sortable:false,align:'center'},
                    {display:'fax번호', name:'faxNo',width:100, sortable:false,align:'center'},
                    {display:'우편번호', name:'postNo',width:80, sortable:false,align:'center'},
                    {display:'도로명주소', name:'roadnmAdres',width:150, sortable:false,align:'center'},
                    {display:'지번주소', name:'lnmAdres',width:150, sortable:false,align:'center'},
                    {display:'담당자', name:'charger',width:50, sortable:false,align:'center'},
                    {display:'담당자직위', name:'chargerOfcPos',width:80, sortable:false,align:'center'},
                    {display:'담당자휴대폰번호', name:'chargerMoblphonNo',width:100, sortable:false,align:'center'},
                    {display:'담당자email', name:'chargerEmail',width:150, sortable:false,align:'center'}
				],
		height: "300",
	});
	this.$("#grdInfoList").flexOptions({params:null}).flexReload();

	this.$("#grdInfoList").flexAddData({resultList: fcltyCtrtJoinContrList});
	
	
	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
	});

	this.$("#grdInfoList").on("onItemUnSelected", function(event, module, row, grid, param) {
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
GamPopupCtrtJoinContrMngtModule.prototype.applyPhotoChanged = function(target) {
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

GamPopupCtrtJoinContrMngtModule.prototype.onButtonClick = function(buttonId) {
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
		/*
		this._maxOlnlpSeq+=1;
		this._edited=false;
		this._editData={_updtId: "I", gisAssetsPrtAtCode: this._selectRow.gisAssetsPrtAtCode
				, gisAssetsCd: this._selectRow.gisAssetsCd
				, gisAssetsSubCd: this._selectRow.gisAssetsSubCd, olnlp: 0, olnlpSeq: EMD.util.leftPad(this._maxOlnlpSeq, '0', 2)};

		this.$("#gamPopupSocAgentMngtForm").selectRowId(this._editRow);
		 */

		 this.$("#grdInfoList").flexAddRow({'agentCode':'','firmKorNm':'','constNo':'','totalAmnt':'','accFee':'','remark':''});
	break;
		case "btnRemove":
			this.removeGisAssetPhotoItem();
		break;
	}
};

GamPopupCtrtJoinContrMngtModule.prototype.removeGisAssetPhotoItem = function() {
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

GamPopupCtrtJoinContrMngtModule.prototype.onSubmit = function() {
	//this.showAlert(this.$("#prtCode").val()+"을(를) 조회 하였습니다");
	this.loadData();
};

GamPopupCtrtJoinContrMngtModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupSocAgentMngtForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};


// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupCtrtJoinContrMngtModule();
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
                        <td><input id="agentCode" type="text" style="width: 150px;" title="업체코드" maxlength="20" class="EditItem"/></td>
                        <th>업체명</th>
                        <td><input id="firmKorNm" type="text" style="width: 150px;" title="업체명" class="EditItem"/></td>
						<th>허가원부일련번호</th>
                        <td><input id="constNo" type="text" style="width: 150px;" title="허가원부일련번호" maxlength="20" class="EditItem"/></td>
					</tr>
					<tr>
                    	<th>보전처리대상금액</th>
                        <td><input id="totalAmnt" type="text" style="width: 150px;" title="보전처리대상금액" maxlength="20" class="EditItem"/></td>
                        <th>보전처리누계액</th>
                        <td><input id="accFee" type="text" style="width: 150px;" title="보전처리누계액" maxlength="20" class="EditItem"/></td>
						<th>비고</th>
                        <td><input id="remark" type="text" style="width: 150px;" title="비고" class="EditItem"/></td>
					</tr>
				</tbody>
			</table>
		</form>
	    </div>

	</div>
</div>