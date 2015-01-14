<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamInfoTechFcltySpecMng.jsp
  * @Description : 정보통신시설 제원 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.17  	김종민          최초 생성
  *
  * author 김종민
  * since 2014.11.17
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<validator:javascript formName="fcltyManageVO" method="validateFcltyManageVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamInfoTechFcltySpecMngModule() {
}

GamInfoTechFcltySpecMngModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamInfoTechFcltySpecMngModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#infoTechFcltySpecMngList").flexigrid({
		module: this,
		url: '/fclty/selectInfoTechFcltySpecMngList.do',
		dataType: "json",
		colModel : [
					{display:"항분류",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:180,		sortable:false,		align:"left"},
					{display:"시설명",		name:"prtFcltyNm",			width:200,		sortable:false,		align:"left"},
					{display:"소재지",		name:"loc",					width:170,		sortable:false,		align:"left"},
					{display:"시설물관리그룹",	name:"fcltsMngGroupNoNm",	width:150,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:80,		sortable:false,		align:"left"},
					{display:"설치일자",		name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"}
			],
		height: "auto"
	});

	this._cmd = '';
	this._deleteDataFileList = null;
	this._prtFcltySe = 'I';

	this.$("#infoTechFcltySpecMngList").on('onItemSelected', function(event, module, row, grid, param) {
		module._cmd = "modify";
	});

	this.$("#infoTechFcltySpecMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._cmd = "modify";
		module.$("#infoTechFcltySpecMngTab").tabs("option", {active: 1});
	});

	this.$("#selectGisPrtFcltyCd").on("change", {module: this}, function(event) {
		event.data.module.$("#gisPrtFcltyCd").val($(this).val());
	});

	this.$("#fcltsFileList").flexigrid({
		module: this,
		url: '/fclty/selectInfoTechFcltySpecFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",			width:200,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",		width:180,		sortable:true,		align:"left"},
			],
		height: "400"
	});

	this.$("#fcltsFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.selectAtchFileItem();
	});

	//첨부파일 정보 변화 이벤트 처리기
	this.$(".photoEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.atchFileInfoChanged(event.target);
	});
};

GamInfoTechFcltySpecMngModule.prototype.onSubmit = function() {
	this.loadData();
};

//시설목록 로드
GamInfoTechFcltySpecMngModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchInfoTechFcltySpecMngForm");
	this.$("#infoTechFcltySpecMngList").flexOptions({params:searchOpt}).flexReload();
};

//시설재원데이터 로드
GamInfoTechFcltySpecMngModule.prototype.loadDetailData = function() {
	var rows = this.$('#infoTechFcltySpecMngList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
			alert('시설물 관리번호에 오류가 있습니다.');
			this._cmd = '';
			this.initDisplay();
			return;
		}
		this.doAction('/fclty/selectInfoTechFcltySpecMngDetail.do', row, function(module, result) {
			if(result.resultCode == "0"){
				module.makeFormValues('#fcltyManageVO', result.result);
				module.$("#dispfcltsMngNo").text(module.$("#fcltsMngNo").val());
				module.loadFileData();
			}
			else {
				module._cmd="";
				module.initDisplay();
				alert(result.resultMsg);
			}
		});
	}
};

//시설 첨부파일 로드
GamInfoTechFcltySpecMngModule.prototype.loadFileData = function() {
	var searchOpt = [{name: 'sFcltsMngNo', value: this.$("#fcltsMngNo").val()}];
	this.$("#fcltsFileList").flexOptions({params:searchOpt}).flexReload();
};

// 화면 및 데이터 초기화 처리
GamInfoTechFcltySpecMngModule.prototype.initDisplay = function() {
	this._deleteDataFileList = [];
	this.$("#fcltyManageVO :input").val("");
	this.$("#dispfcltsMngNo").text("");
	this.$("#previewImage").removeAttr("src");
	this.$('#fcltsFileList').flexEmptyData();
	if(this._cmd == "insert") {
		this.$("#selectGisPrtFcltyCd").enable();
		this.$("#popupSearchGisCode").show();
		this.$("#infoTechFcltySpecMngTab").tabs("option", {active: 1});
	} else if (this._cmd == "modify") {
		this.$("#selectGisPrtFcltyCd").disable();
		this.$("#popupSearchGisCode").hide();
	} else {
		this.$("#selectGisPrtFcltyCd").enable();
		this.$("#popupSearchGisCode").show();
		this.$("#infoTechFcltySpecMngTab").tabs("option", {active: 0});
	}
};

//첨부파일 정보 변화 처리
GamInfoTechFcltySpecMngModule.prototype.atchFileInfoChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#fcltsFileList').selectedRows();
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
		if(this.$('#atchFileWritngDt').is(target)) {
			row['atchFileWritngDt'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#fcltsFileList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltsFileList').flexUpdateRow(rowid, row);
	}
};

//시설물 데이터 삽입
GamInfoTechFcltySpecMngModule.prototype.insertData = function() {
	var data = this.makeFormArgs("#fcltyManageVO");
 	this.doAction('/fclty/insertInfoTechFcltySpecMngDetail.do', data, function(module, result) {
 		if(result.resultCode == "0"){
 			module._cmd = "modify";
 			module.$("#gisPrtFcltySeq").val(result.gisPrtFcltySeq);
			module.$("#fcltsMngNo").val(module.$("#gisAssetsPrtAtCode").val() + module.$("#gisAssetsCd").val() + module.$("#gisAssetsSubCd").val() + module.$("#gisPrtFcltyCd").val() + result.gisPrtFcltySeq + module._prtFcltySe);
			module.$("#dispfcltsMngNo").text(module.$("#fcltsMngNo").val());
			module.$("#selectGisPrtFcltyCd").disable();
			module.$("#popupSearchGisCode").hide();
			module.saveAtchFile();
 		}
 		alert(result.resultMsg);
 	});
};

//시설뮬 데이터 수정
GamInfoTechFcltySpecMngModule.prototype.updateData = function() {
	var data = this.makeFormArgs("#fcltyManageVO");
	this.doAction('/fclty/updateInfoTechFcltySpecMngDetail.do', data, function(module, result) {
		if(result.resultCode == "0"){
			module.saveAtchFile();
		}
		alert(result.resultMsg);
	});
};

//시설물 데이터 삽입 및 수정
GamInfoTechFcltySpecMngModule.prototype.saveData = function() {
	if(!validateFcltyManageVO(this.$('#fcltyManageVO')[0])){
		return;
	}
	if(this._cmd == "insert") {
		this.insertData();
	} else if (this._cmd == "modify") {
		this.updateData();
	}
};

//시설물 데이터 삭제
GamInfoTechFcltySpecMngModule.prototype.deleteData = function() {
	var rows = this.$("#infoTechFcltySpecMngList").selectedRows();
	if(rows.length <= 0){
		alert("삭제할 시설을 선택하십시오.");
		return;
	}
	if(confirm("시설정보을 삭제하시겠습니까?")) {
		var row = rows[0];
		if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
			alert('시설물 관리번호에 오류가 있습니다.');
			return;
		}
	 	this.doAction('/fclty/deleteInfoTechFcltySpecMngDetail.do', row, function(module, result) {
	 		if(result.resultCode == "0") {
				module._cmd = "";
				module.initDisplay();
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}
};

//첨부파일 데이터 저장
GamInfoTechFcltySpecMngModule.prototype.saveAtchFile = function() {
	var fcltsMngNo = this.$("#fcltsMngNo").val();
	var fileList = this.$("#fcltsFileList").flexGetData();
	for(var i=0; i<fileList.length; i++) {
		fileList[i]["fcltsMngNo"] = fcltsMngNo;
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#fcltsFileList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#fcltsFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataFileList) };
    this.doAction('/fclty/mergeCivilFcltySpecAtchFile.do', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteDataFileList = [];
			module.loadFileData();
        }
        else {
        	alert(result.resultMsg);
        }
    });
};

//첨부파일 정보 변화 처리
GamInfoTechFcltySpecMngModule.prototype.atchFileInfoChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#fcltsFileList').selectedRows();
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
		if(this.$('#atchFileWritngDt').is(target)) {
			row['atchFileWritngDt'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#fcltsFileList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltsFileList').flexUpdateRow(rowid, row);
	}
};

//첨부파일 항목선택
GamInfoTechFcltySpecMngModule.prototype.selectAtchFileItem = function() {
	var rows = this.$('#fcltsFileList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		this.$("#fcltsFileForm input").val('');
		this.makeFormValues("#fcltsFileForm", row);
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
GamInfoTechFcltySpecMngModule.prototype.uploadAtchFileItem = function() {
	this.$('#atchFileSe').val('D');
	this.uploadPfPhoto("uploadPhoto", function(module, result) {
		$.each(result, function(){
			module.$("#fcltsFileList").flexAddRow({_updtId:'I', fcltsMngNo:module.$('#fcltsMngNo').val(), atchFileSe:'D', atchFileSeNm :'문서', atchFileNmLogic:this.logicalFileNm, atchFileNmPhysicl: this.physcalFileNm, atchFileWritingDt:''});
		});
	}, "정보통신시설파일 업로드");
};

//첨부파일 다운로드
GamInfoTechFcltySpecMngModule.prototype.downloadAtchFileItem = function() {
	var selectRow = this.$('#fcltsFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};

//첨부파일 삭제
GamInfoTechFcltySpecMngModule.prototype.removeAtchFileItem = function() {
	var rows = this.$("#fcltsFileList").selectedRows();
    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#fcltsFileList").selectedRowIds().length>0) {
    	for(var i=this.$("#fcltsFileList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#fcltsFileList").flexGetRow(this.$("#fcltsFileList").selectedRowIds()[i]);
            if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataFileList[this._deleteDataFileList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#fcltsFileList").flexRemoveRow(this.$("#fcltsFileList").selectedRowIds()[i]);
		}
    	this.$("#previewImage").removeAttr("src");
    	alert("삭제되었습니다.");
	}
    this.$("#fcltsFileForm").find(":input").val("");
};

/**
 * 정의 된 버튼 클릭 시
 */
GamInfoTechFcltySpecMngModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case "btnSearch": //조회
			this._cmd = "";
			this.initDisplay();
			this.loadData();
			break;

		// 시설추가
		case "btnAdd":
			this._cmd = "insert";
			this.initDisplay();
			break;

		//시설삭제
		case "btnDelete" :
			this.deleteData();
			break;

		// 저장
		case "btnSave":
			this.saveData();
			break;

		//파일업로드
		case "btnUploadFile":
			this.uploadAtchFileItem();
			break;

		//파일다운로드
		case "btnDownloadFile":
			this.downloadAtchFileItem();
			break;

		//파일삭제
		case "btnRemoveFile":
			this.removeAtchFileItem();
			break;

		// 자산코드 팝업(디테일 화면)
		case "popupSearchGisCode":
			this.doExecuteDialog("selectGisCode", "자산코드", '/popup/showAssetsCd.do', {});
			break;

		// 시설물관리그룹(조회 화면)
		case "popupSearchFcltsMngGroupNo":
			this.doExecuteDialog("selectFcltsMngGroup", "시설물그룹번호", '/popup/showFcltsMngGroup.do', {});
			break;

		// 시설물관리그룹(디테일 화면)
		case "popupSearchFcltsMngGroupNo2":
			this.doExecuteDialog("selectFcltsMngGroup2", "시설물그룹번호", '/popup/showFcltsMngGroup.do', {});
			break;

		// 시설물 분류코드(디테일 화면)
		case "popupSearchFcltsClCd" :
			this.doExecuteDialog("selectFcltsClCd", "시설물분류코드", '/popup/showFcltsClCd.do', { sFcltsClCdChar : this._prtFcltySe });
			break;

		// 건축시설물 관리번호(디테일 화면)
		case "popupSearchArchFcltsMngNo":
			this.doExecuteDialog("selectArchFcltsMngNo", "건축시설번호", '/popup/showConsFcltyInfo.do', {});
			break;

		case "registLocation":	// 위치 등록
			var module=this;
			EMD.gis.addPrtFcltyMarker(this._fcltyItem, function(value) {
				module.$('#laCrdnt').val(value.laCrdnt);
				module.$('#loCrdnt').val(value.loCrdnt);
			});
			break;

		case "gotoLocation":	// 위치 조회
			if(this._fcltyItem.laCrdnt!=null && this._fcltyItem.laCrdnt!=null) {
				EMD.gis.goLocation(this._fcltyItem.laCrdnt, this._fcltyItem.loCrdnt);
				EMD.gis.selectPrtFclty(this._fcltyItem);
			} else if(this._fcltyItem.lat!=null && this._fcltyItem.lng!=null){
				EMD.gis.goLocation4326(this._fcltyItem.lat, this._fcltyItem.lng);
				EMD.gis.selectPrtFclty(this._fcltyItem);
			} else {
				alert("시설위치가 등록되지 않았습니다.");
			}
			break;
	}
};

/**
 * 탭 변경시 실행 이벤트
 */
GamInfoTechFcltySpecMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._cmd == 'modify') {
		this.initDisplay();
		this.$('#tab2').scrollTop(0);
		this.loadDetailData();
	}
	switch(newTabId) {
		case "tabs1":
			break;
		case "tabs2":
		case "tabs3":
			if((this._cmd != 'insert') && (this._cmd != 'modify')) {
				this.$("#infoTechFcltySpecMngTab").tabs("option", {active: 0});
				alert('정보통신시설항목을 선택하시거나 시설추가버튼을 누르세요.');
			}
			break;
	}
};

/**
 * 팝업 close 이벤트
 */
GamInfoTechFcltySpecMngModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		case "selectGisCode":
			this.$("#gisAssetsPrtAtCode").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsPrtAtCode2").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsPrtAtName").val(value["gisAssetsPrtAtCodeNm"]);
			this.$("#gisAssetsSubCd").val(value["gisAssetsSubCd"]);				// GIS SUB자산코드
			this.$("#gisAssetsCd").val(value["gisAssetsCd"]);					// GIS 자산코드
			this.$("#gisAssetsNm").val(value["gisAssetsNm"]);					// GIS 자산명
			this.$("#gisAssetsLocplc").val(value["gisAssetsLocplc"]); 			// 소재지
			this.$("#gisAssetsLnm").val(value["gisAssetsLnm"]);					// 지번
			this.$("#gisAssetsLnmSub").val(value["gisAssetsLnmSub"]);			// 서브지번
			break;

		case "selectFcltsClCd":
			this.$("#infoCommFcltsClCd").val(value["fcltsClCd"]);
			this.$("#infoCommFcltsClCdNm").val(value["fcltsClCdNm"]);
			break;

		case "selectArchFcltsMngNo":
			this.$("#archFcltsMngNo").val(value["fcltsMngNo"]);
			this.$("#archFcltsMngNoNm").val(value["prtFcltyNm"]);
			break;

		//조회화면
		case "selectFcltsMngGroup":
			this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#sFcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
			break;
			
		//디테일화면 
		case "selectFcltsMngGroup2":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
			break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamInfoTechFcltySpecMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchInfoTechFcltySpecMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항구분</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>시설물관리그룹</th>
							<td colspan="3">
								<input id="sFcltsMngGroupNo" type="text" size="14" maxlength="14" />&nbsp;-&nbsp;
								<input id="sFcltsMngGroupNoNm" type="text" size="56" disabled="disabled"/>
								<button id="popupSearchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
							<td rowspan="2"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>시설분류</th>
							<td><input id="sPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM059" /></td>
							<th>시설명</th>
							<td><input id="sPrtFcltyNm" type="text" size="30" maxlength="30" /></td>
							<th>소재지</th>
							<td><input id="sLoc" type="text" size="30" maxlength="30" /></td>							
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="infoTechFcltySpecMngTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">정보통신시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">정보통신시설 제원</a></li>
				<li><a href="#tabs3" class="emdTab">정보통신시설 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="infoTechFcltySpecMngList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnAdd">시설추가</button>
					<button id="btnDelete">시설삭제</button>
					<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="infoTechFcltySpecMngList" data-style="default">맵조회</button>
				</div>
			</div>

			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyManageVO">
				<div style="margin-bottom:10px;">
					<table class="searchPanel">
						<tbody>
							<tr>
								<th width="70%">정보통신시설 일반</th>
								<th>시설물관리번호 : <span id="dispfcltsMngNo"></span><input type="hidden" id="fcltsMngNo" /></th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">항　　구　　분</th>
							<td><input type="text" size="5" id="gisAssetsPrtAtCode" disabled="disabled"/>
							    <input type="text" size="23" id="gisAssetsPrtAtName" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">GIS 자 산 코 드</th>
							<td colspan="3">
								<input type="text" size="4" id="gisAssetsCd" disabled="disabled" data-required="true"/>-
								<input type="text" size="3" id="gisAssetsSubCd" disabled="disabled"/>-
								<input type="text" size="4" id="gisAssetsPrtAtCode2" disabled="disabled"/>
								<button id="popupSearchGisCode" class="popupButton">선택</button>
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
								<input class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM059" id="selectGisPrtFcltyCd" data-required="true" data-column-id="gisPrtFcltyCd"/>
								<input type="hidden" id="prtFcltySeNm" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">시　　설　　명</th>
							<td><input type="text" size="32" id="prtFcltyNm" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<input type="text" size="16" id="fcltsMngGroupNo" disabled="disabled"/>
								<input type="text" size="21" id="fcltsMngGroupNoNm" disabled="disabled"/>
								<button id="popupSearchFcltsMngGroupNo2" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시　설　규　격</th>
							<td colspan="3"><input id="prtFcltyStndrd" type="text" size="50" title="시설규격" maxlength="40" /></td>
							<th width="12%" height="17" class="required_text">시　설　단　위</th>
							<td><input id="prtFcltyUnit" type="text" size="32" title="시설단위" maxlength="10" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설　치　일　자</th>
							<td><input id="prtFcltyInstlDt" type="text" class="emdcal" size="11" title="설치일자" maxlength="11" /></td>
							<th width="12%" height="17" class="required_text">변　경　일　자</th>
							<td colspan="3"><input id="prtFcltyChangeDt" type="text" class="emdcal" size="11" title="변경일자" maxlength="11" /></td>
						</tr>
					</table>
				</div>
					<table class="searchPanel">
						<tbody>
							<tr>
								<th>정보통신시설 제원</th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">품　　목　　명</th>
							<td colspan="3"><input id="prdlstNm" type="text" size="50" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">모　　　　　델</th>
							<td><input id="model" type="text" size="50" maxlength="250" /></td>
							<th width="12%" height="17" class="required_text">제　　조　　사</th>
							<td><input id="maker" type="text" size="50" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">수　　　　　량</th>
							<td><input id="qy" type="text" size="50" class="ygpaNumber"/></td>
							<th width="12%" height="17" class="required_text">규　　　　　격</th>
							<td><input id="stndrd" type="text" size="50" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">세　부　내　역</th>
							<td colspan="3"><input id="ptlrDtls" type="text" size="50" maxlength="250" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설　치　구　분</th>
							<td colspan="3">
								<select id="instlSe">
                                    <option value="">선택</option>
                                    <option value="Y">Y</option>
                                    <option value="N">N</option>
                                </select>
                            </td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설　치　번　호</th>
							<td><input id="instlNo" type="text" size="50" maxlength="10" /></td>
							<th width="12%" height="17" class="required_text">기　　　　　능</th>
							<td><input id="func" type="text" size="50" maxlength="20" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설　치　높　이</th>
							<td><input id="instlHt" type="text" size="50" class="ygpaNumber" /></td>
							<th width="12%" height="17" class="required_text">설　치　규　격</th>
							<td><input id="instlStndrd" type="text" size="50" maxlength="20" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">제　어　방　식</th>
							<td><input id="ctrlMthd" type="text" size="50" maxlength="20" /></td>
							<th width="12%" height="17" class="required_text">LAMP　형　식</th>
							<td><input id="lampFmt" type="text" size="50" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물분류코드</th>
							<td colspan="3">
								<input id="infoCommFcltsClCd" type="text" size="30" disabled="disabled" />
								<input id="infoCommFcltsClCdNm" type="text" size="30" disabled="disabled" />
								<button id="popupSearchFcltsClCd" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">건축시설물관리번호</th>
							<td colspan="3">
								<input id="archFcltsMngNo" type="text" size="30" disabled="disabled" />
								<input id="archFcltsMngNoNm" type="text" size="30" disabled="disabled" />
								<button id="popupSearchArchFcltsMngNo" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">소　　재　　지</th>
							<td colspan="3"><input id="loc" type="text" size="135" maxlength="150" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">비　　　　　고</th>
							<td colspan="3">
								<textarea rows="4" cols="133" id="rm" maxlength="1000"></textarea>
							</td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="registLocation">위치등록</button>
					<button id="gotoLocation">위치조회</button>
					<button id="btnSave">저장</button>
				</div>
			</div>

			<!-- 정보통신시설 첨부파일 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table border="1">
					<tr>
						<td width="50%">
							<table id="fcltsFileList" style="display:none" class="fillHeight"></table>
							<div class="emdControlPanel">
								<button id="btnUploadFile">업로드</button>
								<button id="btnDownloadFile">다운로드</button>
								<button id="btnRemoveFile">삭제</button>
								<button id="btnSave">저장</button>
							</div>
			
							<form id="fcltsFileForm">
								<table class="searchPanel editForm">
									<tr>
										<th width="15%" height="23" class="required_text">파일구분</th>
										<td>
											<select id="atchFileSe" class="photoEditItem">
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