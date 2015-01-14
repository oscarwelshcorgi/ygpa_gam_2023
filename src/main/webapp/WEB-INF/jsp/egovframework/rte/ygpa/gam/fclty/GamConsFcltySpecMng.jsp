<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamConstFcltySpecMng.jsp
  * @Description : 건축시설 제원 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.4  	HNJ          최초 생성
  *
  * author HNJ
  * since 2014.11.4
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="fcltyManageVO" method="validateFcltyManageVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamConstFcltySpecMngModule() {
}

GamConstFcltySpecMngModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamConstFcltySpecMngModule.prototype.loadComplete = function(params) {

	this._fcltyItem = null;

	// 테이블 설정
	this.$("#constFcltySpecMngList").flexigrid({
		module: this,
		url: '/fclty/gamConstFcltySpecMngList.do',
		dataType: "json",
		colModel : [
					{display:"항분류",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:200,		sortable:false,		align:"left"},
					{display:"시설명",		name:"prtFcltyNm",			width:200,		sortable:false,		align:"left"},
					{display:"소재지",		name:"loc",					width:180,		sortable:false,		align:"left"},
					{display:"시설물관리그룹",	name:"fcltsMngGroupNoNm",	width:120,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:80,		sortable:false,		align:"center"},
					{display:"준공일자",		name:"bldDt",				width:80,		sortable:false,		align:"center"}

			],
		height: "auto"
	});

	this.$("#constFcltySpecMngList").on("onItemSelected", function(event, module, row, grid, param) {
		module._cmd="modify";
	});

	this.$("#constFcltySpecMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._cmd="modify";
		module.$("#constFcltySpecMngListTab").tabs("option", {active: 1});	// 탭을 전환 한다.
	});

	this.$("#sFcltsMngGroupNo").bind("keyup change", {module: this}, function(event) {
		event.data.module.getFcltsMngGroupNoNm();
	});

	this.$("#selectedGAM005").on("change", {module: this}, function(event) {
		event.data.module.$("#gisPrtFcltyCd").val($(this).val());
	});


	// 사진 정보 속성이 변경 된 경우 이벤트 실행
	this.$(".fileEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyFileChanged(event.target);
	});

	this.$("#fcltyFileList").flexigrid({
		module: this,
		url: '/fclty/gamConstFcltySpecFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",				width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",			width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",				width:200,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",			width:180,		sortable:true,		align:"left"},
			],
		height: "400"
	});

	this.$("#fcltyFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.imagePreview();
	});


};

GamConstFcltySpecMngModule.prototype.onSubmit = function() {
	this.loadData();
};


GamConstFcltySpecMngModule.prototype.loadData = function() {
	this._cmd="";
	this.makeFormValues('#fcltyManageVO', {});
	var searchOpt=this.makeFormArgs("#fcltyForm");
 	this.$("#constFcltySpecMngListTab").tabs("option", {active: 0});
 	this.$("#constFcltySpecMngList").flexOptions({params:searchOpt}).flexReload();
};

GamConstFcltySpecMngModule.prototype.imagePreview = function() {
	
	var row = this.$('#fcltyFileList').selectedRows();
	row = row[0];

	this.$("#fcltyGisFileForm input").val('');
	this.makeFormValues("#fcltyGisFileForm", row);
	this._editDataFile = this.getFormValues("#fcltyGisFileForm", row);
	this._editRowFile = this.$("#fcltyFileList").selectedRowIds()[0];
	if(row.atchFileNmPhysicl != null || row.atchFileNmPhysicl != "") {

		// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
		var filenm = row["atchFileNmPhysicl"];
		var ext = filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();

		if(ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif"){

			$imgURL = this.getPfPhotoUrl(filenm);
			//this.$("#previewImage").fadeIn(400, function() {
		    	this.$("#previewImage").attr("src", $imgURL);
		    //});
		}else{
			this.$("#previewImage").removeAttr("src");
		}
	}
};

GamConstFcltySpecMngModule.prototype.loadDetail = function() {

	var row = this.$('#constFcltySpecMngList').selectedRows();
	row = row[0];
	console.log(row);
	if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
		this.$("#constFcltySpecMngListTab").tabs("option", {active: 0});
		alert('시설물 관리번호에 오류가 있습니다.');
		this._cmd = '';
		this.initDisplay();
		return;
	}

	// 건축시설 제원 처리
	var prtFclty = [{ name: 'fcltsMngNo', value: row['fcltsMngNo'] }];
	this.doAction('/fclty/gamConstFcltySpecDetail.do', prtFclty, function(specModule, result) {
 		if(result.resultCode == "0"){
 			specModule.clearCodePage();
 			specModule._fcltyItem=result.result;
 			specModule.makeFormValues('#fcltyManageVO', result.result);	// 결과값을 채운다.
 			specModule.$("#titleFcltsMngNo").text(result.result["fcltsMngNo"]);	// 결과값을 채운다.

	 		specModule.$("#beforeGisPrtFcltyCd").val(specModule.$("#gisPrtFcltyCd").val());
            specModule.$("#beforeGisPrtFcltySeq").val(specModule.$("#gisPrtFcltySeq").val());
 		}
 	
 		specModule.$("#gisCodePopupBtn").hide();
 		specModule.$("#selectedGAM005").disable();
 	});

	// 첨부파일 처리
	this.$('#fcltyFileList').flexOptions({params:prtFclty}).flexReload();
	this.clearFilePage();

};

GamConstFcltySpecMngModule.prototype.addFcltyMode = function() {
	this._cmd="insert";
	this.$('#fcltyFileList').flexEmptyData();
	this.$("#fcltyManageVO :input").val("");
	this.$("#titleFcltsMngNo").text('');
	this.$("#selectedGAM005").enable();
	this.$("#gisCodePopupBtn").show();
	this.$("#constFcltySpecMngListTab").tabs("option", {active: 1});
};


GamConstFcltySpecMngModule.prototype.saveFcltyData = function() {
	if(!validateFcltyManageVO(this.$('#fcltyManageVO')[0])){
		return;
	}
	var inputVO = this.makeFormArgs("#fcltyManageVO");
	var fcltsMngNo="";
	// 건축시설제원 입력/수정처리
 	if(this._cmd == "insert") {
	 	this.doAction('/fclty/gamConstFcltySpecInsert.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			var searchOpt = module.makeFormArgs("#fcltyForm");
				module.$("#constFcltySpecMngList").flexOptions({params:searchOpt}).flexReload();

				fcltsMngNo = result.fcltsMngNo;

				// 첨부파일 입력/수정처리
				module.insertFcltySpecFile(fcltsMngNo);
	 		}
	 		alert(result.resultMsg);
	 	});
	}else{
	 	this.doAction('/fclty/gamConstFcltySpecUpdate.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			var searchOpt = module.makeFormArgs("#fcltyForm");
				module.$("#constFcltySpecMngList").flexOptions({params:searchOpt}).flexReload();

				fcltsMngNo = result.fcltsMngNo;

				// 첨부파일 입력/수정처리
				module.insertFcltySpecFile(fcltsMngNo);

	 		}
	 		alert(result.resultMsg);
	 	});

	 	this._cmd="";
	}
};


GamConstFcltySpecMngModule.prototype.insertFcltySpecFile = function(fcltsMngNo) {

	var all_rows = this.$('#fcltyFileList').flexGetData();

	for(var i=0;i<all_rows.length;i++){
		all_rows[i]["fcltsMngNo"] = fcltsMngNo;
	}

	var inputFileVO=[];
	inputFileVO[inputFileVO.length]={name: 'updateList', value :JSON.stringify(this.$('#fcltyFileList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
	inputFileVO[inputFileVO.length]={name: 'insertList', value: JSON.stringify(this.$('#fcltyFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
	inputFileVO[inputFileVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataFileList) };

    this.doAction('/fclty/mergeGamConstFcltySpecFileMngt.do', inputFileVO, function(fileModule, fileResult) {
        if(fileResult.resultCode == 0){

        }
    });

};

GamConstFcltySpecMngModule.prototype.deleteFcltyData = function() {
	var row = this.$("#constFcltySpecMngList").selectedRows();

	if(row.length == "0"){
		alert("삭제할 시설을 선택 하십시오.");
		return;
	}

	if(confirm("선택한 건축시설을 삭제하시겠습니까?")){
		row=row[0];

		var inputVO = { 'fcltsMngNo': row['fcltsMngNo'] };
	 	this.doAction('/fclty/gamConstFcltySpecDelete.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			var searchOpt = module.makeFormArgs("#fcltyForm");
				module.$("#constFcltySpecMngList").flexOptions({params:searchOpt}).flexReload();
				module.$("#constFcltySpecMngListTab").tabs("option", {active: 0});
				module.$("#fcltyManageVO :input").val("");
	 		}
	 		alert(result.resultMsg);
	 	});

	 	this._cmd="";
	}
};


GamConstFcltySpecMngModule.prototype.removefcltyFileItem = function() {
	var rows = this.$("#fcltyFileList").selectedRows();

    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }

    if(this.$("#fcltyFileList").selectedRowIds().length>0) {
    	for(var i=this.$("#fcltyFileList").selectedRowIds().length-1; i>=0; i--) {

    		var row = this.$("#fcltyFileList").flexGetRow(this.$("#fcltyFileList").selectedRowIds()[i]);

            if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataFileList[this._deleteDataFileList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#fcltyFileList").flexRemoveRow(this.$("#fcltyFileList").selectedRowIds()[i]);

        	this._edited=true;
		}

    	this.$("#previewImage").attr("src","");
    	alert("삭제되었습니다.");
	}

    this.$("#fcltyGisFileForm").find(":input").val("");
    this._editDataFile = null;
};


GamConstFcltySpecMngModule.prototype.uploadFile = function() {
	// 사진을 업로드하고 업로드한 사진 목록을 result에 어레이로 리턴한다.
	this.uploadPfPhoto("uploadFile", function(module, result) {

		$.each(result, function(){
			module.$("#fcltyFileList").flexAddRow({_updtId:'I', atchFileSeq:"", fcltsMngNo:"", atchFileSe:"D", atchFileSeNm:"문서",  atchFileSj: "", atchFileNmLogic: this.logicalFileNm, atchFileNmPhysicl: this.physcalFileNm, atchFileWritngDt: ""});
		});
	}, "첨부파일 업로드");
};

GamConstFcltySpecMngModule.prototype.downloadFile = function() {
	var selectRow = this.$('#fcltyFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};

GamConstFcltySpecMngModule.prototype.registLocation = function() {
	var module=this;
	EMD.gis.addPrtFcltyMarker(this._fcltyItem, function(value) {
		module.$('#laCrdnt').val(value.laCrdnt);
		module.$('#loCrdnt').val(value.loCrdnt);
	});
};

GamConstFcltySpecMngModule.prototype.gotoLocation = function() {
	if(this._fcltyItem.laCrdnt!=null && this._fcltyItem.laCrdnt!=null) {
		EMD.gis.goLocation(this._fcltyItem.laCrdnt, this._fcltyItem.loCrdnt);
		EMD.gis.selectPrtFclty(this._fcltyItem);
	} else if(this._fcltyItem.lat!=null && this._fcltyItem.lng!=null){
		EMD.gis.goLocation4326(this._fcltyItem.lat, this._fcltyItem.lng);
		EMD.gis.selectPrtFclty(this._fcltyItem);
	} else {
		alert("시설위치가 등록되지 않았습니다.");
	}
};


GamConstFcltySpecMngModule.prototype.clearCodePage = function() {
	this.$('#fcltyManageVO :input').val('');
};

GamConstFcltySpecMngModule.prototype.clearFilePage = function() {
	this.$('#fcltyFileList').flexEmptyData();
	this.$('#fcltyGisFileForm :input[type=text]').val('');
	this.$('#previewImage').attr('src', '');
};

<%
/**
 * @FUNCTION NAME : getFcltsMngGroupNoNm
 * @DESCRIPTION   : 조회조건 시설물관리그룹 명을 지운다.
 * @PARAMETER     : NONE
**/
%>
GamConstFcltySpecMngModule.prototype.getFcltsMngGroupNoNm = function() {
	var sFcltsMngGroupNo = this.$('#sFcltsMngGroupNo').val();
	if (sFcltsMngGroupNo.length != 14) {
		this.$('#sFcltsMngGroupNoNm').val('');
	}

};

GamConstFcltySpecMngModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {

	if((newTabId=='tabs2' || newTabId=='tabs3') && this._cmd != 'insert') {
		if(this.$('#constFcltySpecMngList').selectedRowCount()!=1) {
			alert('건축시설 항목을 선택 하세요.');
			return false;
		}
	}

	return true;
};

GamConstFcltySpecMngModule.prototype.applyFileChanged = function(target) {
	var changed=false;
	var row={};

	var selectRow = this.$('#fcltyFileList').selectedRows();

	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#atchFileSe').is(target)) {
			row['atchFileSeNm'] = $(target).find('option:selected').text();
			row['atchFileSe'] = $(target).val();
			changed=true;
		}
		if(this.$('#atchFileSj').is(target)) {
			row['atchFileSj'] = $(target).val();
			changed=true;
		}
		if(this.$('#atchFileWritngDt').is(target)) {
			row['atchFileWritngDt'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#fcltyFileList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltyFileList').flexUpdateRow(rowid, row);
	}
};


/**
 * 정의 된 버튼 클릭 시
 */
GamConstFcltySpecMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 추가
		case "addBtn":
			this.addFcltyMode();
		break;
		
		// 저장
		case "saveBtn":
			this.saveFcltyData();
		break;

		// 삭제
		case "deleteBtn":
			this.deleteFcltyData();
		break;
		
		// 파일 업로드
		case "btnUploadFile":
			this.uploadFile();
		break;
		
		// 파일 다운로드
		case 'btnDownloadFile':
			this.downloadFile();
		break;

		case "btnRemoveFile":
			this.removefcltyFileItem();
		break;
		
		// 위치 등록
		case "registLocation":	
			this.registLocation();
		break;
		
		// 위치 조회
		case "gotoLocation":	
			this.gotoLocation();
		break;
		
		// 자산코드 팝업
		case "gisCodePopupBtn":
			this.doExecuteDialog("searchGisCodePopup", "자산코드", '/popup/showAssetsCd.do', {});
		break;

		// 검색조건 시설물 관리 그룹 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("sSelectFcltsMngGroup", "시설물 관리 그룹 번호", '/popup/showFcltsMngGroup.do', {});
		break;
		
		// 시설물 분류코드(디테일 화면)
		case "searchFcltsClCd" :
			this.doExecuteDialog("selectFcltsClCd", "시설물 분류코드", '/popup/showFcltsClCd.do', { sFcltsClCdChar : this._prtFcltySe });			
		break;
			
		// 시설물관리그룹(디테일 화면)
		case "searchFcltsMngGroupNo":
			this.doExecuteDialog("selectFcltsMngGroup", "시설물 관리 그룹 번호", '/popup/showFcltsMngGroup.do', {});
		break;
	}
};


/**
 * 탭 변경시 실행 이벤트
 */
 GamConstFcltySpecMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	 if(oldTabId == 'tabs1' && this._cmd == 'modify') {
		 this.loadDetail();
	}
	switch(newTabId) {
		case "tabs1":
			break;
		case "tabs2":
			if(oldTabId == 'tabs1') {
				this.$("#tabs2").scrollTop(0);
			}
			if((this._cmd != 'insert') && (this._cmd != 'modify')) {
				this.$("#constFcltySpecMngListTab").tabs("option", {active: 0});
				alert('건축시설 항목을 선택 하세요.');
			} 
			break;
	
		case "tabs3":
			this._deleteDataFileList=[];
			if((this._cmd != 'insert') && (this._cmd != 'modify')) {
				this.$("#constFcltySpecMngListTab").tabs("option", {active: 0});
				alert('건축시설 항목을 선택 하세요.');
			} 
			break;
	}
};

/**
 * 팝업 close 이벤트
 */
GamConstFcltySpecMngModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){

		// 상세화면
		case "searchGisCodePopup":
			this.$("#gisAssetsPrtAtCodeStr").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsPrtAtCode").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsPrtAtName").val(value["gisAssetsPrtAtCodeNm"]);
			this.$("#gisAssetsSubCd").val(value["gisAssetsSubCd"]);				// GIS SUB자산코드
			this.$("#gisAssetsCd").val(value["gisAssetsCd"]);					// GIS 자산코드
			this.$("#gisAssetsNm").val(value["gisAssetsNm"]);					// GIS 자산명

			this.$("#gisAssetsLocplc").val(value["gisAssetsLocplc"]); 			// 소재지
			this.$("#gisAssetsLnm").val(value["gisAssetsLnm"]);					// 지번
			this.$("#gisAssetsLnmSub").val(value["gisAssetsLnmSub"]);			// 서브지번

			if(this._cmd!="insert") alert('변경된 내용은 페이지를 새로고침을 해야 반영 됩니다.');
		break;

		// 검색조건 시설물 관리 그룹 
		case "sSelectFcltsMngGroup":
			this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#sFcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
		break;

		// 업체조회화면
		case "searchEntrpsCdPopup":
			this.$("#prtFcltyMngEntrpsCd").val(value["entrpscd"]);
			this.$("#prtFcltyMngEntrpsNm").val(value["entrpsNm"]);
		break;

		case "selectFcltsClCd":
			this.$("#archFcltsClCd").val(value["fcltsClCd"]);
			this.$("#archFcltsClCdNm").val(value["fcltsClCdNm"]);			
			break;
			
		case "selectFcltsMngGroup":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
			break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");

		break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamConstFcltySpecMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="fcltyForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항구분</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>시설물관리그룹</th>
							<td colspan="3">
								<input id="sFcltsMngGroupNo" type="text" size="14" title="시설물관리그룹넘버" maxlength="14" />&nbsp;-&nbsp;
								<input id="sFcltsMngGroupNoNm" type="text" size="56" title="시설물관리그룹명" disabled="disabled" />
								<button id="searchPopupBtn" class="popupButton">선택</button>
							</td>
							<td rowspan="2"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>시설분류</th>
							<td><input id="sPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM057" /></td>
							<th>시설명</th>
							<td><input id="sPrtFcltyNm" type="text" size="30" title="시설명" maxlength="30" /></td>
							<th>소재지</th>
							<td><input id="sLoc" type="text" size="30" title="소재지" maxlength="30" /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="constFcltySpecMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
			<ul>
				<li><a href="#tabs1" class="emdTab">건축시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">건축시설 제원</a></li>
				<li><a href="#tabs3" class="emdTab">건축시설 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="constFcltySpecMngList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="addBtn">시설추가</button>
					<button id="deleteBtn">시설삭제</button>
					<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="constFcltySpecMngList" data-style="default">맵조회</button>
				</div>
			</div>


			<!-- 건축시설 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: auto;">
				<form id="fcltyManageVO">
				<input type="hidden" id="beforeGisPrtFcltyCd">
          		<input type="hidden" id="beforeGisPrtFcltySeq">
          		<input type="hidden" id="fcltsMngNo">

				<div style="margin-bottom:10px;">
					<table class="searchPanel">
						<tbody>
							<tr>
								<th width="70%">건축시설 일반</th>
								<th>시설물관리번호 : <span id="titleFcltsMngNo"></span></th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">항　　코　　드</th>
							<td><input type="text" size="5" id="gisAssetsPrtAtCodeStr" disabled="disabled"/>
								<input type="text" size="23" id="gisAssetsPrtAtName" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">GIS 자 산 코 드</th>
							<td colspan="3">
								<input type="text" size="4" id="gisAssetsCd" disabled="disabled" data-required="true"/>-
								<input type="text" size="3" id="gisAssetsSubCd" disabled="disabled"/>-
								<input type="text" size="4" id="gisAssetsPrtAtCode" disabled="disabled"/>
								<button id="gisCodePopupBtn" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">GIS 자　산　명</th>
							<td><input type="text" size="30" id="gisAssetsNm" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">지　　　　　번</th>
							<td>
								<input id="gisAssetsLnm" type="text" size="4" title="지번 앞자리" disabled="disabled" />&nbsp;-&nbsp;
								<input id="gisAssetsLnmSub" type="text" size="7" title="지번 뒷자리" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">소　　재　　지</th>
							<td><input id="gisAssetsLocplc" type="text" size="32" title="소재지" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시　설　코　드</th>
							<td>
								<input type="text" size="2" id="gisPrtFcltyCd" disabled="disabled" />&nbsp;-&nbsp;
								<input type="text" size="5" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
							<th width="12%" height="17" class="required_text">시　설　분　류</th>
							<td>
								<input class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM057" id="selectedGAM005" data-required="true" data-column-id="gisPrtFcltyCd" />
								<input type="hidden" id="prtFcltySeNm" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">건 축　시 설 명</th>
							<td><input type="text" size="32" id="prtFcltyNm" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<input type="text" size="16" id="fcltsMngGroupNo" disabled="disabled"/>
								<input type="text" size="21" id="fcltsMngGroupNoNm" disabled="disabled"/>
								<button id="searchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
						</tr>
					</table>
				</div>
					<table class="searchPanel">
						<tbody>
							<tr>
								<th>건축시설 제원</th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17">준　공　일　자</th>
							<td><input type="text" size="11" id="bldDt" class="emdcal" maxlength="8" /></td>
							<th width="12%" height="17">구　조　형　식</th>
							<td><input type="text" size="50" id="strctFmt" maxlength="33" /></td>
						</tr>
						
						<tr>
							<th width="12%" height="17">하자 만 료 일 자</th>
							<td><input type="text" size="11" id="flawEndDt" class="emdcal" maxlength="8" /></td>
							<th width="12%" height="17">기　초　형　식</th>
							<td><input type="text" size="50" id="baseFmt"  data-required="true" maxlength="33" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">대　지　면　적</th>
							<td><input type="text" size="50" id="plotAr" class="ygpaNumber" data-decimal-point="2" maxlength="10" /></td>
							<th width="12%" height="17">건　축　면　적</th>
							<td><input id="archAr" type="text" size="50" title="건축면적" class="ygpaNumber" data-decimal-point="2" maxlength="10" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">연　　면　　적</th>
							<td><input id="ar" type="text" size="50" title="연면적" class="ygpaNumber" data-decimal-point="2" maxlength="10" /></td>
							<th width="12%" height="17">주　사 용 용 도</th>
							<td><input type="text" size="50" id="mainUsagePrpos" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">주　차　면　적</th>
							<td><input type="text" size="50" id="prkAr" class="ygpaNumber" data-decimal-point="2" maxlength="10" /></td>
							<th width="12%" height="17">주　차　대　수</th>
							<td><input type="text" size="50" id="prkCnt" class="ygpaNumber" maxlength="5" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">옥외 주 차 면 적</th>
							<td><input type="text" size="50" id="osdPrkAr" class="ygpaNumber" data-decimal-point="2" maxlength="10" /></td>
							<th width="12%" height="17">옥외 주 차 대 수</th>
							<td><input type="text" size="50" id="osdPrkCnt" class="ygpaNumber" maxlength="5" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">옥내 주 차 면 적</th>
							<td><input type="text" size="50" id="isdPrkAr" class="ygpaNumber" data-decimal-point="2" maxlength="10" /></td>
							<th width="12%" height="17">옥내 주 차 대 수</th>
							<td><input type="text" size="50" id="isdPrkCnt" class="ygpaNumber" maxlength="10" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">배기 닥 트 유 무</th>
							<td>
								<select id="exhaustDuctEnnc">
									<option value="">선택</option>
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
							</td>
							<th width="12%" height="17">환기 공 조 방 식</th>
							<td><input type="text" size="50" id="vntltnArcndtMthd" maxlength="16" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">승강기운영방식</th>
							<td><input type="text" size="50" id="liftOperMthd" maxlength="33" /></td>
							<th width="12%" height="17">승강기대수승객용</th>
							<td><input type="text" size="50" id="liftCntPsngr" class="ygpaNumber" maxlength="5" /></td>
						</tr>
						<tr>	
							<th width="12%" height="17">승강기대수비상용</th>
							<td><input type="text" size="50" id="liftCntEmgcy" class="ygpaNumber" maxlength="5" /></td>
							<th width="12%" height="17">승강기대수화물용</th>
							<td><input type="text" size="50" id="liftCntCargo" class="ygpaNumber" maxlength="5" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">전기 인 입 용 량</th>
							<td><input id="elctyLeadInCapa" type="text" size="50" title="전기인입용량" class="ygpaNumber" data-decimal-point="2" maxlength="8" /></td>
							<th width="12%" height="17">변 전 실　위 치</th>
							<td><input type="text" size="50" id="sbtLoc"  /></td>
						</tr>
						<tr>
							<th width="12%" height="17">정 화 조　형 식</th>
							<td><input type="text"  size="50" id="spictankFmt" maxlength="100" /></td>
							<th width="12%" height="17">오수정화시설위치</th>
							<td><input type="text" size="50" id="swgClupfcltyLoc" maxlength="33" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">물 탱 크　위 치</th>
							<td><input type="text" size="50" id="wrtTankLoc" maxlength="33" /></td>
							<th width="12%" height="17">유류저장시설위치</th>
							<td><input type="text" size="50" id="oilSavefcltyLoc" maxlength="33" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">냉　방　유　무</th>
							<td>
								<select id="htngEnnc">
									<option value="">선택</option>
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
							</td>
							<th width="12%" height="17">냉　방　열　원</th>
							<td><input type="text" size="50" id="clngSrc" maxlength="33" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">난　방　유　무</th>
							<td>
								<select id="clngEnnc">
									<option value="">선택</option>
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
							</td>
							<th width="12%" height="17">난　방　열　원</th>
							<td><input  id="htngSrc" type="text"  size="50" maxlength="33" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">외　　　　　장</th>
							<td><input id="fcg" type="text"  size="50" maxlength="65" /></td>
							<th width="12%" height="17">내　　　　　장</th>
							<td><input id="itr" type="text"  size="50" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">천　　　　　장</th>
							<td><input id="ceil" type="text"  size="50" maxlength="65" /></td>
							<th width="12%" height="17">지　　　　　붕</th>
							<td><input id="roof" type="text"  size="50" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">지　붕　방　수</th>
							<td colspan="3"><input id="roofWtprf" type="text"  size="50" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">설 계　공 사 명</th>
							<td><input id="planCnstNm" type="text"  size="50" maxlength="65" /></td>
							<th width="12%" height="17">설계 수 행 회 사</th>
							<td><input id="planExcCmpny" type="text" size="50" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">설계 시 작 일 자</th>
							<td><input id="planBeginDt" type="text" class="emdcal" size="11" maxlength="8" /></td>
							<th width="12%" height="17">설계 종 료 일 자</th>
							<td><input id="planEndDt" type="text" class="emdcal" size="11" maxlength="8" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">시 공　공 사 명</th>
							<td><input id="cnstrctCnstNm" type="text"  size="50" title="시공공사명" maxlength="65" /></td>
							<th width="12%" height="17">시공 수 행 회 사</th>
							<td><input id="cnstrctExcCmpny" type="text" size="50" title="시공수행회사" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">시공 시 작 일 자</th>
							<td><input id="cnstrctBeginDt" type="text" class="emdcal" size="11" title="시공시작일자" maxlength="8" /></td>
							<th width="12%" height="17">시공 종 료 일 자</th>
							<td><input id="cnstrctEndDt" type="text" class="emdcal" size="11" title="시공종료일자" maxlength="8" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">건축시설물분류코드</th>
							<td colspan="3">
								<input id="archFcltsClCd" type="text" size="30" title="건축시설물분류코드" maxlength="10" disabled="disabled" />
								<input id="archFcltsClCdNm" type="text" size="30" disabled="disabled" />
								<button id="searchFcltsClCd" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">소　　재　　지</th>
							<td colSpan="3"><input id="loc" type="text" size="135" title="소재지" maxlength="50" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">비　　　　　고</th>
							<td colSpan="3">
								<textarea rows="4" cols="133" id="rm" maxlength="330"></textarea>
							</td>
						</tr>

					</table>
				</form>
				<div class="emdControlPanel">
					<button id="registLocation">위치등록</button>
					<button id="gotoLocation">위치조회</button>
					<button id="saveBtn">저장</button>
				</div>
			</div>

			<!-- 건축시설 첨부파일 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table>
					<tr>
						<td width="50%">
							<table id="fcltyFileList" style="display:none" class="fillHeight"></table>
							<div class="emdControlPanel">
								<button id="btnUploadFile">업로드</button>
								<button id="btnDownloadFile">다운로드</button>
								<button id="btnRemoveFile">삭제</button>
								<button id="saveBtn">저장</button>
							</div>
			
							<form id="fcltyGisFileForm">
								<table class="searchPanel editForm">
									<tr>
										<th width="15%" height="23" class="required_text">파일구분</th>
										<td>
											<select id="atchFileSe" class="fileEditItem">
												<option value="D">문서</option>
			                                    <option value="P">사진</option>
			                                    <option value="Z">기타</option>
			                                </select>
										</td>
										<th width="15%" height="23" class="required_text">파일제목</th>
										<td><input id="atchFileSj" type="text" size="45" class="fileEditItem" maxlength="25" /></td>
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