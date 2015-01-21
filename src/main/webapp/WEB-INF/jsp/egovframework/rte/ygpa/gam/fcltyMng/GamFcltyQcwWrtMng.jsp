<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyQcwWrtMng.jsp
  * @Description : 시설 점검 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.24  	김종민          최초 생성
  *
  * author 김종민
  * since 2014.11.24
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyQcwWrtMngModule() {
}

GamFcltyQcwWrtMngModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyQcwWrtMngModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#qcMngDtlsList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngDtlsList.do',
		dataType: "json",
		colModel : [
					{display:"관리그룹",		name:"fcltsMngGroupNm",		width:150,		sortable:false,		align:"center"},
					{display:"업무구분",		name:"fcltsJobSeNm",		width:90,		sortable:false,		align:"center"},
					{display:"점검관리순번",	name:"qcMngSeq",			width:90,		sortable:false,		align:"center"},
					{display:"점검관리명", 	    name:"qcMngNm",				width:200,		sortable:false,		align:"left"},
					{display:"시행년도",		name:"enforceYear",			width:60,		sortable:false,		align:"center"},
					{display:"점검구분",    	name:"qcSeNm",				width:90,		sortable:false,		align:"center"},
					{display:"점검진단자",    	name:"qcInspTpNm",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단일자",	name:"qcInspDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단구분",    name:"qcInspSeNm",			width:120,		sortable:false,		align:"center"},
					{display:"상태평가등급",	name:"sttusEvlLvlNm",		width:90,		sortable:false,		align:"center"},
					{display:"점검진단금액",	name:"qcInspAmt",			width:120,		sortable:false,		align:"right",	displayFormat: 'number'},
					{display:"점검시작일자",    name:"qcBeginDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검종료일자",	name:"qcEndDt",				width:90,		sortable:false,		align:"center"},
					{display:"점검진단기관명",	name:"qcInspInsttNm",		width:150,		sortable:false,		align:"left"},
					{display:"책임기술자명",	name:"responEngineerNm",	width:150,		sortable:false,		align:"left"},
			],
			height: "auto",
			preProcess : function(module,data) {
				module.$('#totalCount').val($.number(data.totalCount));
				return data;
			}
	});
	
	this._cmd = '';
	this._deleteAtchFileList = null;
	this._deleteObjFcltsList = null;
	this._deleteResultItemList = null;
	
	this.$("#qcMngDtlsList").on('onItemSelected', function(event, module, row, grid, param) {
		module._cmd = "modify";
	});
	
	this.$("#qcMngDtlsList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._cmd = "modify";
		module.$("#fcltyQcwWrtMngTab").tabs("option", {active: 1});
	});

	this.$("#qcMngObjFcltsList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngObjFcltsList.do',
		dataType: 'json',
		colModel : [
					{display:"시설물",		name:"prtFcltyNm",		width:150,		sortable:true,		align:"left"},
					{display:"상태평가등급",	name:"sttusEvlLvlNm",	width:90,		sortable:true,		align:"center"},
					{display:"점검자",		name:"inspector",		width:100,		sortable:true,		align:"left"},
					{display:"점검진단일자",	name:"qcInspDt",		width:100,		sortable:true,		align:"center"},
					{display:"비고",			name:"rm",				width:350,		sortable:true,		align:"left"}
			],
		height: "180"
	});
	
	this.$("#gamQcMngObjFcltsForm").find(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.qcMngObjFcltsChanged(event.target);
	});

	this.$("#qcMngObjFcltsList").on("onItemSelected", function(event, module, row, grid, param) {
		module.selectQcMngFcltsItem();
	});
	
	this.$("#qcMngResultItemList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngResultItemList.do',
		dataType: 'json',
		colModel : [
					{display:"점검상위항목",	name:"qcItemUpperNm",	width:300,		sortable:true,		align:"left"},
					{display:"점검항목",		name:"qcItemNm",		width:300,		sortable:true,		align:"left"},
					{display:"점검항목결과구분",	name:"inspResultChkNm",	width:150,		sortable:true,		align:"center"}
			],
		height: "230"
	});

	this.$("#gamQcMngResultItemForm").find(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.qcMngResultItemDataChanged(event.target);
	});

	this.$("#qcMngResultItemList").on("onItemSelected", function(event, module, row, grid, param) {
		module.selectQcMngResultItem();
	});
	
	this.$("#qcMngAtchFileList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngAtchFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"제목",		name:"atchFileSj",			width:200,		sortable:true,		align:"left"},
					{display:"파일명",	name:"atchFileNmLogic",		width:200,		sortable:true,		align:"left"},
			],
		height: "400"
	});

	this.$("#qcMngAtchFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.selectAtchFileItem();
	});
	
	//첨부파일 정보 변화 이벤트 처리기
	this.$(".photoEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.atchFileInfoChanged(event.target);
	});
	
	// 시설물관리그룹 검색조건 클릭시 초기화 처리
	this.$("#sFcltsMngGroupNo").bind("click", {module: this}, function(event) {
		event.data.module.$("#sFcltsMngGroupNo").val('');
		event.data.module.$("#sFcltsMngGroupNm").val('');
	});
	
	this.fillSelectBoxYear('#enforceYear'); //시행년도에 년수 채워넣기	
	this.fillSelectBoxYear('#sEnforceYear'); 		
};

//2000년부터 현재년도까지 select 박스에 채워넣는 함수.
GamFcltyQcwWrtMngModule.prototype.fillSelectBoxYear = function(id) {
	var curYear = (new Date()).getFullYear();
	for(var i=curYear; i>=2000; i--) {
		this.$(id).append('<option value="' + i + '">' + i + '년</option>');
	}
};

//화면 및 데이터 초기화
GamFcltyQcwWrtMngModule.prototype.initDisplay = function() {
	this._deleteAtchFileList = [];
	this._deleteObjFcltsList = [];
	this._deleteResultItemList = [];
	
	this.$("#fcltyQcwWrtMngVO :input").val("");
	this.$("#qcMngAtchFileForm :input").val("");
	
	this.$('#qcMngObjFcltsList').flexEmptyData();
	this.$('#qcMngResultItemList').flexEmptyData();
	this.$('#qcMngAtchFileList').flexEmptyData();
	
	this.$("#previewImage").removeAttr("src");
	
	if(this._cmd == "insert") {
		this.$("#fcltsMngGroupNo").enable();
		this.$("#fcltsJobSe").enable();
		this.$("#popupSearchFcltsMngGroup").show();
		this.$("#fcltyQcwWrtMngTab").tabs("option", {active: 1});		
	} else if (this._cmd == "modify") {
		this.$("#fcltsMngGroupNo").disable();
		this.$("#fcltsJobSe").disable();
		this.$("#popupSearchFcltsMngGroup").hide();
	} else {
		this.$("#fcltsJobSe").enable();
		this.$("#popupSearchFcltsMngGroup").show();
		this.$("#fcltyQcwWrtMngTab").tabs("option", {active: 0});
	}	
};

GamFcltyQcwWrtMngModule.prototype.onSubmit = function() {
	this.loadData();
};

//점검관리내역 조회
GamFcltyQcwWrtMngModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchFcltyQcwWrtMngForm");
	this.$("#qcMngDtlsList").flexOptions({params:searchOpt}).flexReload();
};

//점검관리목록 엑셀다운로드
GamFcltyQcwWrtMngModule.prototype.downloadExcel = function() {
	var rowCount = this.$("#qcMngDtlsList").flexRowCount();
	if (rowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#qcMngDtlsList').flexExcelDown('/fcltyMng/excelDownloadQcMngDtlsList.do');
};

//점검관리내역 데이터 조회
GamFcltyQcwWrtMngModule.prototype.loadDetailData = function() {
	var rows = this.$('#qcMngDtlsList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		var opts = [
	           		{name: 'sFcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	           		{name: 'sFcltsJobSe', value: row['fcltsJobSe'] },
	           		{name: 'sQcMngSeq', value: row['qcMngSeq'] }
		           ];
		this.doAction('/fcltyMng/selectQcMngDtlsDetail.do', opts, function(module, result) { 
			if(result.resultCode == "0"){
				module.makeFormValues('#fcltyQcwWrtMngVO', result.result);
				module.summaryDisplay();
				module.$("#qcMngObjFcltsList").flexOptions({params:opts}).flexReload();
				module.$("#qcMngAtchFileList").flexOptions({params:opts}).flexReload();
				module.$("#qcMngResultItemList").flexOptions({params:opts}).flexReload();
			}
			else {
				module._cmd="";
				module.initDisplay();
				alert(result.resultMsg);
			}
		});	
	}
};

//점검관리 대상물과 결과항목에 상세부분 출력
GamFcltyQcwWrtMngModule.prototype.summaryDisplay = function() {
	this.$("#summaryFcltsMngGroupNm1").text(this.$("#fcltsMngGroupNm").val());	
	this.$("#summaryQcMngNm1").text(this.$("#qcMngNm").val());	
	this.$("#summaryQcInspDtNm1").text(this.$("#qcInspDt").val());
	this.$("#summaryQcInspTpNm1").text((this.$("#qcInspTp").find('option:selected').text() == '선택') ? '' : this.$("#qcInspTp").find('option:selected').text());	
	this.$("#summaryQcSeNm1").text((this.$("#qcSe").find('option:selected').text() == '선택') ? '' : this.$("#qcSe").find('option:selected').text());
	this.$("#summaryQcInspSeNm1").text((this.$("#qcInspSe").find('option:selected').text() == '선택') ? '' : this.$("#qcInspSe").find('option:selected').text());	

	this.$("#summaryFcltsMngGroupNm2").text(this.$("#summaryFcltsMngGroupNm1").text());	
	this.$("#summaryQcMngNm2").text(this.$("#summaryQcMngNm1").text());	
	this.$("#summaryQcInspDtNm2").text(this.$("#summaryQcInspDtNm1").text());
	this.$("#summaryQcInspTpNm2").text(this.$("#summaryQcInspTpNm1").text());	
	this.$("#summaryQcSeNm2").text(this.$("#summaryQcSeNm1").text());
	this.$("#summaryQcInspSeNm2").text(this.$("#summaryQcInspSeNm1").text());	
};

//점검관리내역 필수입력 체크
GamFcltyQcwWrtMngModule.prototype.validateData = function() {
	if(this.$("#fcltsMngGroupNo").val() == '') {
		alert('관리그룹을 입력하세요.');
		return false;
	}
	if(this.$("#fcltsJobSe").val() == '') {
		alert('업무구분을 입력하세요.');
		return false;
	}
	if((this._cmd == 'modify') && (this.$("#qcMngSeq").val() == '')) {
		alert('잘못된 순번입니다.');
		return false;
	}
	return true;
};

//점검관리내역 삽입
GamFcltyQcwWrtMngModule.prototype.insertData = function() {
	var data = this.makeFormArgs("#fcltyQcwWrtMngVO");
 	this.doAction('/fcltyMng/insertQcMngDtls.do', data, function(module, result) {
 		if(result.resultCode == "0"){
 			module._cmd = "modify";
 			module.$("#qcMngSeq").val(result.qcMngSeq);
			module.saveQcMngObjFclts();
			module.saveQcMngResultItem();
			module.saveAtchFile();
 		}
 		alert(result.resultMsg);
 	});	
};

//점검관리내역 수정
GamFcltyQcwWrtMngModule.prototype.updateData = function() {
	var data = this.makeFormArgs("#fcltyQcwWrtMngVO");
 	this.doAction('/fcltyMng/updateQcMngDtls.do', data, function(module, result) {
 		if(result.resultCode == "0"){
 			module._cmd = "modify";
			module.saveQcMngObjFclts();
			module.saveQcMngResultItem();
			module.saveAtchFile();
 		}
 		alert(result.resultMsg);
 	});	
};

//점검관리내역 삽입 및 수정저장
GamFcltyQcwWrtMngModule.prototype.saveData = function() {
	if(this.validateData()) {
	 	if(this._cmd == "insert") {
	 		this.insertData();
		} else if (this._cmd == "modify") { 
			this.updateData();
		}
	}
};

//점검관리내역 삭제
GamFcltyQcwWrtMngModule.prototype.deleteData = function() {
	var rows = this.$("#qcMngDtlsList").selectedRows();
	if(rows.length <= 0){
		alert("삭제할 점검관리내역을 선택하십시오.");
		return;
	}
	if(confirm("점검관리내역을 삭제하시겠습니까?")) {
	 	this.doAction('/fcltyMng/deleteQcMngDtls.do', rows[0], function(module, result) {
	 		if(result.resultCode == "0") {
				module._cmd = "";
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}
};

//점검관리 대상 시설물 정보 변화 처리
GamFcltyQcwWrtMngModule.prototype.qcMngObjFcltsChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#qcMngObjFcltsList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#objMngFcltsMngNo').is(target)) {
			row['fcltsMngNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#objMngPrtFcltyNm').is(target)) {
			row['prtFcltyNm'] = $(target).val();
			changed=true;
		}
		if(this.$('#objMngSttusEvlLvl').is(target)) {
			row['sttusEvlLvl'] = $(target).val();
			row['sttusEvlLvlNm'] = $(target).find('option:selected').text();
			changed=true;
		}
		if(this.$('#objMngQcInspDt').is(target)) {
			row['qcInspDt'] = $(target).val();
			changed=true;
		}
		if(this.$('#objMngInspector').is(target)) {
			row['inspector'] = $(target).val();
			changed=true;
		}
		if(this.$('#objMngRm').is(target)) {
			row['rm'] = $(target).val();
			changed=true;
		}
		if(this.$('#objMngQcInspResult').is(target)) {
			row['qcInspResult'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#qcMngObjFcltsList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#qcMngObjFcltsList').flexUpdateRow(rowid, row);
	}
};

//점검관리 결과항목 존재유무 체크
GamFcltyQcwWrtMngModule.prototype.existQcMngFcltsItem = function(fcltsMngNo) {
	var rows = this.$('#qcMngObjFcltsList').flexGetData();
	var result = false;
	if(rows.length > 0) {
		var row = null;
		for(var i=0; i<rows.length; i++) {
			row = rows[i];
			if(row['fcltsMngNo'] == fcltsMngNo) {
				result = true;
				break;
			}
		}
	}
	return result;
};

//점검관리 대상시설물 항목선택
GamFcltyQcwWrtMngModule.prototype.selectQcMngFcltsItem = function() {
	var rows = this.$('#qcMngObjFcltsList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		this.$("#gamQcMngObjFcltsForm :input").val('');
		this.$("#objMngFcltsMngNo").val(row["fcltsMngNo"]); //row의 col명과 form의 id가 달라서 직접대입.
		this.$("#objMngPrtFcltyNm").val(row["prtFcltyNm"]);
		this.$("#objMngSttusEvlLvl").val(row["sttusEvlLvl"]);
		this.$("#objMngQcInspDt").val(row["qcInspDt"]);
		this.$("#objMngInspector").val(row["inspector"]);
		this.$("#objMngRm").val(row["rm"]);
		this.$("#objMngQcInspResult").val(row["qcInspResult"]);
		if(row['_updtId'] == 'I') {
			this.$("#popupSearchFcltsMngNo").show();
		} else {
			this.$("#popupSearchFcltsMngNo").hide();
		}
	}
};

//점검관리 대상 시설물 추가
GamFcltyQcwWrtMngModule.prototype.addQcMngObjFcltsItem = function() {
	this.$("#popupSearchFcltsMngNo").show();
	this.$('#gamQcMngObjFcltsForm :input').val('');
	this.$("#qcMngObjFcltsList").flexAddRow({'_updtId': 'I', 'fcltsMngGroupNo':'', 'fcltsJobSe':'', 'qcMngSeq':'', 'fcltsMngNo':'', 'sttusEvlLvl':'', 'sttusEvlLvlNm':'', 'qcInspDt':'', 'inspector':'', 'qcInspResult':'', 'rm':''});
	var allRows = this.$('#qcMngObjFcltsList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#qcMngObjFcltsList").selectRowId(selRowId);	
};

//점검관리 대상 시설물 데이터 삭제
GamFcltyQcwWrtMngModule.prototype.removeQcMngObjFcltsItem = function() {
	var rows = this.$("#qcMngObjFcltsList").selectedRows();
    if(rows.length == 0){
        alert("점검관리대상 시설물목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#qcMngObjFcltsList").selectedRowIds().length>0) {
    	for(var i=this.$("#qcMngObjFcltsList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#qcMngObjFcltsList").flexGetRow(this.$("#qcMngObjFcltsList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteObjFcltsList[this._deleteObjFcltsList.length] = row;
			}
        	this.$("#qcMngObjFcltsList").flexRemoveRow(this.$("#qcMngObjFcltsList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamQcMngObjFcltsForm").find(":input").val("");
};

//점검관리 대상시설물 병합저장
GamFcltyQcwWrtMngModule.prototype.saveQcMngObjFclts = function() {
	var dataList = this.$("#qcMngObjFcltsList").flexGetData();
	for(var i=0; i<dataList.length; i++) {
		dataList[i]["fcltsMngGroupNo"] = this.$("#fcltsMngGroupNo").val();
		dataList[i]["fcltsJobSe"] = this.$("#fcltsJobSe").val();
		dataList[i]["qcMngSeq"] = this.$("#qcMngSeq").val();		
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#qcMngObjFcltsList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#qcMngObjFcltsList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteObjFcltsList) };
    this.doAction('/fcltyMng/mergeQcMngObjFclts.do', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteObjFcltsList = [];				    	
			var opts = [
		           		{name: 'sFcltsMngGroupNo', value: module.$("#fcltsMngGroupNo").val() },
		           		{name: 'sFcltsJobSe', value: module.$("#fcltsJobSe").val() },
		           		{name: 'sQcMngSeq', value: module.$("#qcMngSeq").val() }
			           ];
			module.$("#qcMngObjFcltsList").flexOptions({params:opts}).flexReload();
			module.$('#gamQcMngObjFcltsForm :input').val('');
        }
        else {
        	alert(result.resultMsg);
        }
    });	
};

//점검관리 결과항목 정보 변화 처리
GamFcltyQcwWrtMngModule.prototype.qcMngResultItemDataChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#qcMngResultItemList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#qcItemCd').is(target)) {
			row['qcItemCd'] = $(target).val();
			changed=true;
		}
		if(this.$('#qcItemNm').is(target)) {
			row['qcItemNm'] = $(target).val();
			changed=true;
		}
		if(this.$('#inspResultChk').is(target)) {
			row['inspResultChk'] = $(target).val();
			row['inspResultChkNm'] = $(target).find('option:selected').text();
			changed=true;
		}
		if(this.$('#inspResultCn').is(target)) {
			row['inspResultCn'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#qcMngResultItemList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#qcMngResultItemList').flexUpdateRow(rowid, row);
	}
};

//점검관리 결과항목 존재유무 체크
GamFcltyQcwWrtMngModule.prototype.existQcMngResultItem = function(qcItemCd) {
	var rows = this.$('#qcMngResultItemList').flexGetData();
	var result = false;
	if(rows.length > 0) {
		for(var i=0; i<rows.length; i++) {
			var row = rows[i];
			if(row['qcItemCd'] == qcItemCd) {
				result = true;
				break;
			}
		}
	}
	return result;
};

//점검관리 결과항목 항목선택
GamFcltyQcwWrtMngModule.prototype.selectQcMngResultItem = function() {
	var rows = this.$('#qcMngResultItemList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		this.$("#gamQcMngResultItemForm :input").val('');
		this.makeFormValues("#gamQcMngResultItemForm", row);
	}
};

//점검관리 결과항목 추가(팝업)
GamFcltyQcwWrtMngModule.prototype.addQcMngResultItem = function() {
	if(this.$('#fcltsJobSe').val() != '') {
		this.doExecuteDialog('selectQcItemCd', '점검항목 선택', '/popup/showQcItemCdTreePopup.do', {}, {'fcltsJobSe' : this.$('#fcltsJobSe').val()});
	}
	else {
		alert('업무구분을 선택하십시오');
	}
};

//점검관리 결과항목 팝업에서 선택된 값들 추가
GamFcltyQcwWrtMngModule.prototype.selectedQcMngResultItems = function(selectedItems) {
	for(var i=0; i<selectedItems.length; i++) {
		var item = selectedItems[i];
		//항목코드가 현재 리스트에 중복되지 않은 것만 추가
		if(!this.existQcMngResultItem(item.qcItemCd)) {
			this.$("#qcMngResultItemList").flexAddRow({'_updtId': 'I', 'fcltsMngGroupNo':'', 'fcltsJobSe':'', 'qcMngSeq':'', 'qcItemCd':item.qcItemCd, 'qcItemNm':item.qcItemNm,  'qcItemUpperCd' : item.qcItemUpperCd, 'qcItemUpperNm' : item.qcItemUpperNm , 'seq':'', 'inspResultChk':'', 'inspResultChkNm':'', 'inspResultCn':''});
		}
	}		
	var allRows = this.$('#qcMngResultItemList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#qcMngResultItemList").selectRowId(selRowId);
};

//점검관리 결과항목 삭제
GamFcltyQcwWrtMngModule.prototype.removeQcMngResultItem = function() {
	var rows = this.$("#qcMngResultItemList").selectedRows();
    if(rows.length == 0){
        alert("점검관리 결과항목목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#qcMngResultItemList").selectedRowIds().length>0) {
    	for(var i=this.$("#qcMngResultItemList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#qcMngResultItemList").flexGetRow(this.$("#qcMngResultItemList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteResultItemList[this._deleteResultItemList.length] = row;
			}
        	this.$("#qcMngResultItemList").flexRemoveRow(this.$("#qcMngResultItemList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamQcMngResultItemForm").find(":input").val("");
};

//점검관리 결과항목 병합저장
GamFcltyQcwWrtMngModule.prototype.saveQcMngResultItem = function() {
	var dataList = this.$("#qcMngResultItemList").flexGetData();
	for(var i=0; i<dataList.length; i++) {
		dataList[i]["fcltsMngGroupNo"] = this.$("#fcltsMngGroupNo").val();
		dataList[i]["fcltsJobSe"] = this.$("#fcltsJobSe").val();
		dataList[i]["qcMngSeq"] = this.$("#qcMngSeq").val();		
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#qcMngResultItemList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#qcMngResultItemList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteResultItemList) };
    this.doAction('/fcltyMng/mergeQcMngResultItem.do', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteObjFcltsList = [];				    	
			var opts = [
		           		{name: 'sFcltsMngGroupNo', value: module.$("#fcltsMngGroupNo").val() },
		           		{name: 'sFcltsJobSe', value: module.$("#fcltsJobSe").val() },
		           		{name: 'sQcMngSeq', value: module.$("#qcMngSeq").val() }
			           ];
			module.$("#qcMngResultItemList").flexOptions({params:opts}).flexReload();
			module.$('#gamQcMngResultItemForm :input').val('');
        }
        else {
        	alert(result.resultMsg);
        }
    });	
};

//첨부파일 정보 변화 처리
GamFcltyQcwWrtMngModule.prototype.atchFileInfoChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#qcMngAtchFileList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#atchFileSe').is(target)) {
			row['atchFileSe'] = $(target).val();
			row['atchFileSeNm'] = $(target).find('option:selected').text();
			changed=true;
		}
		if(this.$('#atchFileSj').is(target)) {
			row['atchFileSj'] = $(target).val();
			changed=true;
		}
		if(this.$('#atchFileWritingDt').is(target)) {
			row['atchFileWritingDt'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#qcMngAtchFileList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#qcMngAtchFileList').flexUpdateRow(rowid, row);
	}
};

//첨부파일목록 병합저장
GamFcltyQcwWrtMngModule.prototype.saveAtchFile = function() {
	var fileList = this.$("#qcMngAtchFileList").flexGetData();
	for(var i=0; i<fileList.length; i++) {
		fileList[i]["fcltsMngGroupNo"] = this.$("#fcltsMngGroupNo").val();
		fileList[i]["fcltsJobSe"] = this.$("#fcltsJobSe").val();
		fileList[i]["qcMngSeq"] = this.$("#qcMngSeq").val();		
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#qcMngAtchFileList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#qcMngAtchFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteAtchFileList) };
    this.doAction('/fcltyMng/mergeQcMngAtchFile.do', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteAtchFileList = [];				    	
			var opts = [
		           		{name: 'sFcltsMngGroupNo', value: module.$("#fcltsMngGroupNo").val() },
		           		{name: 'sFcltsJobSe', value: module.$("#fcltsJobSe").val() },
		           		{name: 'sQcMngSeq', value: module.$("#qcMngSeq").val() }
			           ];
			module.$("#qcMngAtchFileList").flexOptions({params:opts}).flexReload();
			module.$('#qcMngAtchFileForm :input').val('');
        }
        else {
        	alert(result.resultMsg);
        }
    });	
};

//첨부파일내역 삭제
GamFcltyQcwWrtMngModule.prototype.removeAtchFileItem = function() {
	var rows = this.$("#qcMngAtchFileList").selectedRows();
    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#qcMngAtchFileList").selectedRowIds().length>0) {
    	for(var i=this.$("#qcMngAtchFileList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#qcMngAtchFileList").flexGetRow(this.$("#qcMngAtchFileList").selectedRowIds()[i]);
            if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteAtchFileList[this._deleteAtchFileList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#qcMngAtchFileList").flexRemoveRow(this.$("#qcMngAtchFileList").selectedRowIds()[i]);
		}
    	this.$("#previewImage").removeAttr("src");
    	alert("삭제되었습니다.");
	}
    this.$("#qcMngAtchFileForm").find(":input").val("");
};

//첨부파일 항목선택
GamFcltyQcwWrtMngModule.prototype.selectAtchFileItem = function() {
	var rows = this.$('#qcMngAtchFileList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		this.$("#qcMngAtchFileForm input").val('');
		this.makeFormValues("#qcMngAtchFileForm", row);
		if(row.atchFileNmPhysicl != null || row.atchFileNmPhysicl != "") {
			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm = row["atchFileNmPhysicl"];
			var ext = filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();
			if(ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif"){
				var imgURL = this.getPfPhotoUrl(filenm);
			    this.$("#previewImage").attr("src", imgURL);
			}else{
				this.$("#previewImage").removeAttr("src");
			}
		}
	}
};

//첨부파일 업로드
GamFcltyQcwWrtMngModule.prototype.uploadAtchFileItem = function() {
	this.$('#atchFileSe').val('D');
	this.uploadPfPhoto("uploadPhoto", function(module, result) {
		$.each(result, function(){
			module.$("#qcMngAtchFileList").flexAddRow({_updtId:'I', fcltsMngGroupNo:'', fcltsJobSe:'', qcMngSeq:'', atchFileSe:'D', atchFileSeNm :'문서', atchFileNmLogic:this.logicalFileNm, atchFileNmPhysicl: this.physcalFileNm, atchFileWritingDt:''});
		});
	}, "점검관리 첨부파일 업로드");	
};

//첨부파일 다운로드
GamFcltyQcwWrtMngModule.prototype.downloadAtchFileItem = function() {
	var selectRow = this.$('#qcMngAtchFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};

/**
 * 정의 된 버튼 클릭 시
 */
GamFcltyQcwWrtMngModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		//점검관리목록 조회
		case "btnSearch":
			this._cmd = '';
			this.initDisplay();
			this.loadData();
			break;
			
		//점검관리목록 엑셀 다운로드 
		case 'btnExcelDownload':
			this.downloadExcel();
			break;	
			
		//점검관리내역 추가			
		case "btnAdd" :
			this._cmd = 'insert';
			this.initDisplay();
			break;
		//점검관리내역 삭제	
		case "btnDelete" :
			this.deleteData();
			break;
			
		//점검관리내역 저장			
		case "btnSave" :
			this.saveData();
			break;
		
		//점검관리 대상 시설물 추가
		case "btnQcMngObjFcltsAdd" :
			this.addQcMngObjFcltsItem();
			break;

		//점검관리 대상 시설물 삭제
		case "btnQcMngObjFcltsRemove" :
			this.removeQcMngObjFcltsItem();
			break;
			
		//점검관리 결과항목 추가
		case "btnQcMngResultItemAdd" :
			this.addQcMngResultItem();
			break;
			
		//점검관리 결과항목 삭제
		case "btnQcMngResultItemRemove" :
			this.removeQcMngResultItem();
			break;
			
		//첨부파일업로드
		case "btnUploadFile":
			this.uploadAtchFileItem();
			break;
			
		//첨부파일다운로드			
		case "btnDownloadFile":
			this.downloadAtchFileItem();
			break;
						
		//첨부파일삭제
		case "btnRemoveFile":
			this.removeAtchFileItem();
			break;
						
		//시설물관리그룹선택(상세화면)
		case "popupSearchFcltsMngGroup":
			this.doExecuteDialog("selectFcltsMngGroup", "관리그룹 선택", '/popup/showFcltsMngGroup.do', {});
			break;

		//시설물관리그룹선택(조회화면)
		case "popupSearchFcltsMngGroup2":
			this.doExecuteDialog("selectFcltsMngGroup2", "관리그룹 선택", '/popup/showFcltsMngGroup.do', {});
			break;
			
		//시설물번호선택
		case "popupSearchFcltsMngNo":
			this.doExecuteDialog('selectFcltsMngNo', '시설물 선택', '/popup/showFcltsMngNo.do', {}, {'fcltsJobSe' : this.$('#fcltsJobSe').val()});
			break;
		
	}
};


/**
 * 탭 변경시 실행 이벤트
 */
GamFcltyQcwWrtMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._cmd == 'modify') {
		this.initDisplay();
		this.loadDetailData();
	}
	if(oldTabId == 'tabs2') {
		this.summaryDisplay();
	}
	switch(newTabId) {
		case "tabs1":
			break;
		case "tabs2":
		case "tabs3":
		case "tabs4":
		case "tabs5":
			if((this._cmd != 'insert') && (this._cmd != 'modify')) {
				this.$("#fcltyQcwWrtMngTab").tabs("option", {active: 0});
				alert('시설물점검항목을 선택하시거나 추가버튼을 누르세요.');
			} 
			break;
	}
};

/**
 * 팝업 close 이벤트
 */
GamFcltyQcwWrtMngModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		//시설물 관리 그룹(상세화면)
		case "selectFcltsMngGroup":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGroupNm").val(value["fcltsMngGroupNm"]);
			break;
		//시설물 관리 그룹(조회화면)
		case "selectFcltsMngGroup2":
			this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#sFcltsMngGroupNm").val(value["fcltsMngGroupNm"]);
			break;
		//시설물 선택
		case 'selectFcltsMngNo':
			if(this.existQcMngFcltsItem(value['fcltsMngNo'])) {
				alert('시설물이 이미 존재합니다.');
			} else {
	        	this.$('#objMngFcltsMngNo').val(value['fcltsMngNo']);
	        	this.$('#objMngPrtFcltyNm').val(value['prtFcltyNm']);
	        	this.$('#gamQcMngObjFcltsForm').find('.EditItem').trigger('change');
			}
    		break;
    	//점검항목선택
		case 'selectQcItemCd':
			this.selectedQcMngResultItems(value['selectedQcResultItemList']);
			break;
		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyQcwWrtMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyQcwWrtMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>관리그룹</th>
							<td>
								<input type="text" size="15" id="sFcltsMngGroupNo" title="시설물관리그룹넘버" />-
								<input type="text" size="17" id="sFcltsMngGroupNm" disabled="disabled" title="시설물관리그룹명"/>
								<button id="popupSearchFcltsMngGroup2" class="popupButton">선택</button>
							</td>
							<th></th>
							<td></td>
							<td rowspan="3"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>업무구분</th>
							<td>
								<select id="sFcltsJobSe" class="searchEditItem">
									<option value="">선택</option>
									<option value="E">전기시설</option>
									<option value="M">기계시설</option>
									<option value="C">토목시설</option>
									<option value="A">건축시설</option>
									<option value="I">정보통신시설</option>
								</select>
								<input id="sFcltsJobSeNm" type="hidden" />
							</td>
							<th>점검관리명</th>
							<td><input type="text" id="sQcMngNm" size="50" /></td>
						</tr>
						<tr>
							<th>점검구분</th>
							<td>
								<select id="sQcSe" class="searchEditItem">
                                    <option value="">선택</option>
                                    <option value="1">해빙기대비</option>
                                    <option value="2">풍수해대비</option>
                                    <option value="3">동절기대비</option>
                                    <option value="4">우기대비</option>
                                </select>
                                <input id="sQcSeNm" type="hidden" />
							</td>
							<th>시행년도</th>
							<th>
								<select id="sEnforceYear">
									<option value="">선택</option>
                                </select>
							</th>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltyQcwWrtMngTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">시설물점검목록</a></li>
				<li><a href="#tabs2" class="emdTab">시설물점검내역</a></li>
				<li><a href="#tabs3" class="emdTab">점검관리대상시설물</a></li>
				<li><a href="#tabs4" class="emdTab">점검관리결과</a></li>
				<li><a href="#tabs5" class="emdTab">점검관리첨부파일</a></li>
			</ul>
			
			<!-- 시설물점검목록 -->
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="qcMngDtlsList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:6%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled"/></td>
								<td style="text-align:right;">
									<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
									<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

			<!-- 시설물점검내역 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyQcwWrtMngVO">
				<div style="margin-bottom:10px;">
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17">관리그룹</th>
							<td colspan="3">
								<input type="hidden" id="fcltsMngGroupNo"/>
								<input type="text" size="40" id="fcltsMngGroupNm" disabled="disabled"/>
								<button id="popupSearchFcltsMngGroup" class="popupButton">선택</button>
							</td>
							<th width="12%" height="17">점검관리순번</th>
							<td>
								<input type="text" size="10" id="qcMngSeq" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">업무구분</th>
							<td>
								<select id="fcltsJobSe">
									<option value="">선택</option>
									<option value="A">건축시설</option>
									<option value="C">토목시설</option>
									<option value="E">전기시설</option>
									<option value="I">정보통신시설</option>
									<option value="M">기계시설</option>
                                </select>
							</td>
							<th width="12%" height="17">점검관리명</th>
							<td colspan="3">
								<input type="text" size="78" id="qcMngNm" maxlength="200" />
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">시행년도</th>
							<td>
								<!-- 년도 자동 주입 -->
								<select id="enforceYear">
									<option value="">선택</option>
                                </select>
							</td>
							<th width="12%" height="17">점검구분</th>
							<td>
								<select id="qcSe">
                                    <option value="">선택</option>
                                    <option value="1">해빙기대비</option>
                                    <option value="2">풍수해대비</option>
                                    <option value="3">동절기대비</option>
                                    <option value="4">우기대비</option>
                                </select>
							</td>
							<th width="12%" height="17">시행일자</th>
							<td><input id="qcInspDt" type="text" class="emdcal" size="20"/></td>
						</tr>
						<tr>
							<th width="12%" height="17">점검진단자</th>
							<td>
								<select id="qcInspTp">
                                    <option value="">선택</option>
                                    <option value="1">자체점검</option>
                                    <option value="2">용역점검</option>
                                </select>
							</td>
							<th width="12%" height="17">점검진단구분</th>
							<td>
								<select id="qcInspSe">
                                    <option value="">선택</option>
                                    <option value="1">정기점검</option>
                                    <option value="2">정밀점검</option>
                                    <option value="3">초기점검</option>
                                    <option value="4">긴급점검(손상)</option>
                                    <option value="5">긴급점검(특별)</option>
                                    <option value="6">정밀안전점검(정기)</option>
                                    <option value="7">정밀안전점검(긴급)</option>
                                    <option value="8">정밀안전점검(하자)</option>
                                    <option value="9">기타</option>
                                </select>
							</td>
							<th width="12%" height="17">점검진단금액</th>
							<td><input id="qcInspAmt" type="text" size="20" class="ygpaNumber"/></td>
						</tr>
						<tr>
							<th width="12%" height="17">상태평가등급</th>
							<td colspan="5">
								<select id="sttusEvlLvl">
                                    <option value="">선택</option>
                                    <option value="A">A</option>
                                    <option value="B">B</option>
                                    <option value="C">C</option>
                                    <option value="D">D</option>
                                    <option value="E">E</option>
                                    <option value="Z">불명</option>
                                </select>			
							</td>
						</tr>
						<tr>							
							<th width="12%" height="17">점검진단기관명</th>
							<td><input type="text" size="30" id="qcInspInsttNm" maxlength="60" /></td>
							<th width="12%" height="17">점검기간</th>
							<td colspan="3">
								<input id="qcBeginDt" type="text" class="emdcal" size="20"/> ~ 
								<input id="qcEndDt" type="text" class="emdcal" size="20"/>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">책임기술자명</th>
							<td colspan="5"><input type="text" size="30" id="responEngineerNm" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">점검진단결과</th>
							<td colspan="5"><textarea id="qcInspResult" cols="135" rows="8"></textarea></td>
						</tr>
						<tr>
							<th width="12%" height="17">조치내용</th>
							<td colspan="5"><textarea id="actionCn" cols="135" rows="8"></textarea></td>
						</tr>
						<tr>
							<th width="12%" height="17">비고</th>
							<td colspan="5"><input id="rm" type="text" size="137"/></td>
						</tr>
					</table>
				</div>
				</form>
				<div class="emdControlPanel">
					<button id="btnSave">저장</button>
				</div>
			</div>
			
			<!-- 점검관리대상시설물 -->			
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<div class="emdControlPanel">
					<table class="summaryPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th style="font-weight:bold;">점검관리 상세내역</th>
							</tr>
						</tbody>
					</table>
					<table class="detailPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th>관리그룹</th>
								<td><span id="summaryFcltsMngGroupNm1"></span></td>
								<th>점검관리명</th>
								<td><span id="summaryQcMngNm1"></span></td>
								<th>시행일자</th>
								<td><span id="summaryQcInspDtNm1"></span></td>
							</tr>
							<tr>
								<th>점검진단자</th>
								<td><span id="summaryQcInspTpNm1"></span></td>
								<th>점검구분</th>
								<td><span id="summaryQcSeNm1"></span></td>
								<th>점검진단구분</th>
								<td><span id="summaryQcInspSeNm1"></span></td>
							</tr>
						</tbody>
					</table>
					<table class="summaryPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th style="font-weight:bold;">점검관리 대상시설물</th>
							</tr>
						</tbody>
					</table>
				</div>
				<table id="qcMngObjFcltsList" style="display:none"></table>
		        <div class="emdControlPanel">
		            <button id="btnQcMngObjFcltsAdd">추가</button>
		            <button id="btnQcMngObjFcltsRemove">삭제</button>
		            <button id="btnSave">저장</button>
		        </div>
				<form id="gamQcMngObjFcltsForm">
					<table  class="detailPanel"  style="width:100%;">
						<tbody>
							<tr>
		                        <th>시설물</th>
		                        <td>
		                        	<input id="objMngFcltsMngNo" type="hidden" class="EditItem"/>
		                        	<input id="objMngPrtFcltyNm" type="text" style="width: 200px;" disabled="disabled" class="EditItem"/>
		                        	<button id="popupSearchFcltsMngNo" class="popupButton">선택</button>
		                    	</td>
		                        <th>상태평가등급</th>
		                        <td>
									<select id="objMngSttusEvlLvl" class="EditItem">
	                                    <option value="">선택</option>
	                                    <option value="A">A</option>
	                                    <option value="B">B</option>
	                                    <option value="C">C</option>
	                                    <option value="D">D</option>
	                                    <option value="E">E</option>
	                                    <option value="Z">불명</option>
	                                </select>
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>점검자</th>
		                        <td ><input id="objMngInspector" type="text" style="width: 150px;" maxlength="60" class="EditItem"/></td>
								<th>점검진단일자</th>
		                        <td><input id="objMngQcInspDt" type="text" style="width: 100px;" maxlength="10" class="emdcal EditItem"/></td>
							</tr>
							<tr>
								<th>점검진단결과</th>
								<td colspan="3"><textarea id="objMngQcInspResult" cols="133" rows="4" class="EditItem"></textarea></td>
							</tr>
							<tr>
								<th>비고</th>
								<td colspan="3"><input id="objMngRm" type="text" style="width: 759px;" maxlength="330" class="EditItem"/></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			
			<!-- 점검관리결과항목 -->
			<div id="tabs4" class="emdTabPage" style="overflow: scroll;">
				<div class="emdControlPanel">
					<table class="summaryPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th style="font-weight:bold;">점검관리 상세내역</th>
							</tr>
						</tbody>
					</table>
					<table class="detailPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th>관리그룹</th>
								<td><span id="summaryFcltsMngGroupNm2"></span></td>
								<th>점검관리명</th>
								<td><span id="summaryQcMngNm2"></span></td>
								<th>시행일자</th>
								<td><span id="summaryQcInspDtNm2"></span></td>
							</tr>
							<tr>
								<th>점검진단자</th>
								<td><span id="summaryQcInspTpNm2"></span></td>
								<th>점검구분</th>
								<td><span id="summaryQcSeNm2"></span></td>
								<th>점검진단구분</th>
								<td><span id="summaryQcInspSeNm2"></span></td>
							</tr>
						</tbody>
					</table>
					<table class="summaryPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th style="font-weight:bold;">점검관리 결과</th>
							</tr>
						</tbody>
					</table>
				</div>
				<table id="qcMngResultItemList" style="display:none"></table>
		        <div class="emdControlPanel">
		            <button id="btnQcMngResultItemAdd">추가</button>
		            <button id="btnQcMngResultItemRemove">삭제</button>
		            <button id="btnSave">저장</button>
		        </div>
				<form id="gamQcMngResultItemForm">
					<table  class="detailPanel"  style="width:100%;">
						<tbody>
							<tr>
		                        <th>점검항목</th>
		                        <td>
		                        	<input id="qcItemCd" type="hidden" class="EditItem"/>
		                        	<input id="qcItemNm" type="text" style="width: 300px;" disabled="disabled" class="EditItem"/>
		                        </td>							
		                        <th width="5%">점검결과구분</th>
		                        <td width="30%">
		                       		<select id="inspResultChk" class="EditItem">
										<option value="">선택</option>
										<option value="N">정상</option>
										<option value="W">요주의</option>
										<option value="X">불량</option>
									</select>
		                        </td>
							</tr>
							<tr>
								<th>점검결과내용</th>
								<td colspan="3"><textarea id="inspResultCn" cols="133" rows="5" class="EditItem"></textarea></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>			

			<!-- 점검관리첨부파일 -->
			<div id="tabs5" class="emdTabPage" style="overflow: scroll;">
				<table border="1">
					<tr>
						<td width="50%">
							<table id="qcMngAtchFileList" style="display:none" class="fillHeight"></table>
							<div class="emdControlPanel">
								<button id="btnUploadFile">업로드</button>
								<button id="btnDownloadFile">다운로드</button>
								<button id="btnRemoveFile">삭제</button>
								<button id="btnSave">저장</button>
							</div>
			
							<form id="qcMngAtchFileForm">
								<table class="searchPanel editForm">
									<tr>
										<th width="15%" height="23" class="required_text">파일구분</th>
										<td>
											<select id="atchFileSe" class="photoEditItem">
												<option value="" selected>선택</option>
												<option value="D">문서</option>
			                                    <option value="P">사진</option>
			                                    <option value="Z">기타</option>
			                                </select>
										</td>
										<th width="15%" height="23" class="required_text">파일제목</th>
										<td><input id="atchFileSj" type="text" size="45" class="photoEditItem" maxlength="25" /></td>
									</tr>
								</table>
							</form>
						</td>
						<td style="text-align:center;vertical-align:middle;">
							<img id="previewImage" style="border: 1px solid #000; max-width:300px; max-height: 300px" src="">
						</td>
					</tr>
				</table>
			</div>

		</div>
	</div>
</div>