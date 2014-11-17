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
		url: '<c:url value="/fclty/selectInfoTechFcltySpecMngList.do" />',
		dataType: "json",
		colModel : [
					{display:"항코드",		name:"gisAssetsPrtAtCode",	width:40,		sortable:false,		align:"center"},
					{display:"항코드명",		name:"gisAssetsPrtAtName",	width:60,		sortable:false,		align:"center"},
					{display:"자산코드",		name:"gisAssetsDisplayCd",	width:60,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:120,		sortable:false,		align:"left"},
					{display:"시설코드", 	    name:"gisPrtFcltyDisplayCd",width:80,		sortable:false,		align:"center"},
					{display:"시설명",	    name:"prtFcltyNm",			width:230,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:120,		sortable:false,		align:"left"},
					{display:"시설규격",	    name:"prtFcltyStndrd",		width:240,		sortable:false,		align:"left"},
					{display:"시설단위",       name:"prtFcltyUnit",		width:80,		sortable:false,		align:"left"},
					{display:"설치일자",		name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"},
					{display:"변경일자",		name:"prtFcltyChangeDt",	width:80,		sortable:false,		align:"center"}
			],
		height: "auto"
	});

	this._cmd = '';
	this._deleteDataFileList = null;
	this._prtFcltySe = 'I';
	
	this.$("#infoTechFcltySpecMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
			alert('시설물 관리번호에 오류가 있습니다.');
			return;
		}
		module._cmd="modify";
		module.initDisplay();
		module.loadDetailData(row['fcltsMngNo']);
	});

	this.$("#selectGisPrtFcltyCd").on("change", {module: this}, function(event) {
		event.data.module.$("#gisPrtFcltyCd").val($(this).val());
	});
	
	this.$(".text").bind("change keyup", {module: this}, function(event) {
		var limit_char = /[|]/;
		if(limit_char.test(event.target.value)){
			alert('|'+"(파이프) 특수문자는 사용 하실수 없습니다.");
			var rep= event.target.value.replace(limit_char,"");
			event.target.value = rep;
			return;
		}
	});

	this.$("#fcltsFileList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/selectInfoTechFcltySpecFileList.do"/>',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"사진제목",	name:"atchFileSj",			width:160,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",		width:160,		sortable:true,		align:"left"},
					{display:"물리파일명",	name:"atchFileNmPhysicl",	width:160,		sortable:true,		align:"left"},
					{display:"작성일시",	name:"atchFileWritingDt",	width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});

	this.$("#fcltsFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#fcltsFileForm input").val('');
		module.makeFormValues("#fcltsFileForm", row);

		if(row.atchFileNmPhysicl != null || row.atchFileNmPhysicl != "") {
			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm = row["atchFileNmPhysicl"];
			var ext = filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();

			if(ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif"){
				$imgURL = module.getPfPhotoUrl(filenm);
				module.$("#previewImage").fadeIn(400, function() {
			    	module.$("#previewImage").attr("src", $imgURL);
			    });
			}else{
				module.$("#previewImage").attr(src, "#");
			}
		}
		
	});
	
	//첨부파일 정보 변화 이벤트 처리기
	this.$(".photoEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.atchFileInfoChanged(event.target);
	});
};

GamInfoTechFcltySpecMngModule.prototype.onSubmit = function() {
	this.loadData();
}

//시설목록 로드
GamInfoTechFcltySpecMngModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchInfoTechFcltySpecMngForm");
	this.$("#infoTechFcltySpecMngList").flexOptions({params:searchOpt}).flexReload();	
}

//시설재원데이터 로드
GamInfoTechFcltySpecMngModule.prototype.loadDetailData = function(fcltsMngNo) {
	var opts = [{name: 'fcltsMngNo', value: fcltsMngNo }];
	this.doAction('<c:url value="/fclty/selectInfoTechFcltySpecMngDetail.do" />', opts, function(module, result) { 
		if(result.resultCode == "0"){
			module.makeFormValues('#fcltyManageVO', result.result);
			module.$("#dispfcltsMngNo").text(module.$("#fcltsMngNo").val());
			module.loadFileData();
		}
		else {
			this._cmd="";
			module.$("#infoTechFcltySpecMngTab").tabs("option", {active: 0});
			alert(result.resultMsg);
		}
	});	
}

//시설 첨부파일 로드
GamInfoTechFcltySpecMngModule.prototype.loadFileData = function() {
	var searchOpt = [{name: 'sFcltsMngNo', value: this.$("#fcltsMngNo").val()}];
	this.$("#fcltsFileList").flexOptions({params:searchOpt}).flexReload();
}

// 화면 및 데이터 초기화 처리
GamInfoTechFcltySpecMngModule.prototype.initDisplay = function() {
	this._deleteDataFileList = [];
	this.$("#fcltyManageVO :input").val("");
	this.$("#dispfcltsMngNo").text("");
	this.$("#previewImage").attr("src", "#");
	if(this._cmd == "insert") {
		this.$("#selectGisPrtFcltyCd").enable();
		this.$("#searchGisCodeBtn2").show();
		this.$('#fcltsFileList').flexEmptyData();
		this.$("#infoTechFcltySpecMngTab").tabs("option", {active: 1});		
	} else if (this._cmd == "modify") {
		this.$("#selectGisPrtFcltyCd").disable();
		this.$("#searchGisCodeBtn2").hide();
		this.$("#infoTechFcltySpecMngTab").tabs("option", {active: 1});
	} else {
		this.$('#fcltsFileList').flexEmptyData();
		this.$("#fcltyManageVO :input").val("");
		this.$("#dispfcltsMngNo").text("");
		this.$("#selectGisPrtFcltyCd").enable();
		this.$("#searchGisCodeBtn2").show();
		this.$("#infoTechFcltySpecMngTab").tabs("option", {active: 0});
	}
}

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
		if(this.$('#atchFileWritingDt').is(target)) {
			row['atchFileWritingDt'] = $(target).val();
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
GamInfoTechFcltySpecMngModule.prototype.insertFcltsData = function(data) {
 	this.doAction('<c:url value="/fclty/insertInfoTechFcltySpecMngDetail.do" />', data, function(module, result) {
 		if(result.resultCode == "0"){
 			module._cmd = "modify";
 			module.$("#gisPrtFcltySeq").val(result.gisPrtFcltySeq);
			module.$("#fcltsMngNo").val(module.$("#gisAssetsPrtAtCode").val() + module.$("#gisAssetsCd").val() + module.$("#gisAssetsSubCd").val() + module.$("#gisPrtFcltyCd").val() + result.gisPrtFcltySeq + module._prtFcltySe);
			module.$("#dispfcltsMngNo").text(module.$("#fcltsMngNo").val());
			module.$("#selectGisPrtFcltyCd").disable();
			module.$("#searchGisCodeBtn2").hide();
			module.saveAtchFile(module.$("#fcltsMngNo").val());
 			module.loadData();
 		}
 		alert(result.resultMsg);
 	});	
}

//시설뮬 데이터 수정
GamInfoTechFcltySpecMngModule.prototype.updateFcltsData = function(data) { 
	this.doAction('<c:url value="/fclty/updateInfoTechFcltySpecMngDetail.do" />', data, function(module, result) {
		if(result.resultCode == "0"){
			module.saveAtchFile(module.$("#fcltsMngNo").val());
			module.loadData();
		}
		alert(result.resultMsg);
	});	
}

//시설물 데이터 삭제
GamInfoTechFcltySpecMngModule.prototype.deleteFcltsData = function(fcltsMngNo) { 
	var data = { 'fcltsMngNo': fcltsMngNo };
 	this.doAction('<c:url value="/fclty/deleteInfoTechFcltySpecMngDetail.do" />', data, function(module, result) {
 		if(result.resultCode == "0") {
			module._cmd = "";
			module.initDisplay();
 			module.loadData();
 		}
 		alert(result.resultMsg);
 	});
}

/**
 * 정의 된 버튼 클릭 시
 */
GamInfoTechFcltySpecMngModule.prototype.onButtonClick = function(buttonId) {
	var opts = null;
	switch(buttonId) {
		case "searchBtn": //조회
			this._cmd = "";
			this.initDisplay();
			this.loadData();
			break;
		
		// 자산코드 팝업(조회화면)
		case "searchGisCodeBtn":
			this.doExecuteDialog("selectGisCode", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
			break;

		// 자산코드 팝업(디테일 화면)
		case "searchGisCodeBtn2":
			this.doExecuteDialog("selectGisCode2", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
			break;

		// 시설물관리그룹(디테일 화면)
		case "searchFcltsMngGroupNo":
			this.doExecuteDialog("selectFcltsMngGroup", "시설물 관리 그룹 번호", '<c:url value="/popup/showFcltsMngGroup.do"/>', {});
			break;
			
		// 시설물 분류코드(디테일 화면)
		case "searchFcltsClCd" :
			this.doExecuteDialog("selectFcltsClCd", "시설물 분류코드", '<c:url value="/popup/showFcltsClCd.do"/>', { sFcltsClCdChar : this._prtFcltySe });			
			break;
			
		// 시설추가
		case "btnAdd":
			this._cmd = "insert";
			this.initDisplay();
			break;
		
		//시설삭제
		case "btnDelete" :
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
				this.deleteFcltsData(row['fcltsMngNo']); 
			}
			break;
			
		// 저장
		case "btnSave":
        	if(!validateFcltyManageVO(this.$('#fcltyManageVO')[0])){ 		
        		return;
        	}
			opts = this.makeFormArgs("#fcltyManageVO");
		 	if(this._cmd == "insert") {
		 		this.insertFcltsData(opts);
			} else if (this._cmd == "modify") { 
				this.updateFcltsData(opts);
			}			
			break;
					
		//파일업로드
		case "btnUploadFile":
			this.$('#atchFileSe').val('D');
			this.uploadPfPhoto("uploadPhoto", function(module, result) {
				$.each(result, function(){
					module.$("#fcltsFileList").flexAddRow({_updtId:'I', fcltsMngNo:module.$('#fcltsMngNo').val(), atchFileSe:'D', atchFileSeNm :'문서', atchFileNmLogic:this.logicalFileNm, atchFileNmPhysicl: this.physcalFileNm, atchFileWritingDt:''});
				});
			}, "정보통신시설파일 업로드");
			break;
			
		//파일다운로드			
		case "btnDownloadFile":
			var selectRow = this.$('#fcltsFileList').selectedRows();
			if(selectRow.length > 0) {
				var row=selectRow[0];
				this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
			}
			break;
						
		//파일삭제
		case "btnRemoveFile":
			this.removeAtchFileItem();
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

GamInfoTechFcltySpecMngModule.prototype.saveAtchFile = function(fcltsMngNo) {
	var fileList = this.$("#fcltsFileList").flexGetData();
	for(var i=0; i<fileList.length; i++) {
		fileList[i]["fcltsMngNo"] = fcltsMngNo;
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#fcltsFileList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#fcltsFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataFileList) };
    this.doAction('<c:url value="/fclty/mergeInfoTechFcltySpecAtchFile.do" />', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteDataFileList = [];				    	
			module.loadFileData();
        }
        else {
        	alert(result.resultMsg);
        }
    });	
}

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
    	this.$("#previewImage").attr("src","#");
    	alert("삭제되었습니다.");
	}
    this.$("#fcltsFileForm").find(":input").val("");
};

/**
 * 탭 변경시 실행 이벤트
 */
GamInfoTechFcltySpecMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		if((this._cmd != 'insert') && (this._cmd != 'modify')) {
			this.$("#infoTechFcltySpecMngTab").tabs("option", {active: 0});
		} 
		break;
	case "tabs3":
		if((this._cmd != 'insert') && (this._cmd != 'modify')) {
			this.$("#infoTechFcltySpecMngTab").tabs("option", {active: 0});
		} 
		break;
	}
};

/**
 * 팝업 close 이벤트
 */
GamInfoTechFcltySpecMngModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		// 조회화면
		case "selectGisCode":
			this.$("#sAssetsCd").val(value["gisAssetsCd"]);
			this.$("#sAssetsSubCd").val(value["gisAssetsSubCd"]);
			break;

		case "selectGisCode2":
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
							<th>항코드</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>자산코드</th>
							<td>
								<input id="sAssetsCd" type="text" size="3" maxlength="3" />&nbsp;-&nbsp;
								<input id="sAssetsSubCd" type="text" size="2" maxlength="2" />
								<button id="searchGisCodeBtn" class="popupButton">선택</button>
							</td>
							<th>정보통신시설분류</th>
							<td>
								<input id="sPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM059" />
							</td>
							<td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>정보통신시설명</th>
							<td colspan="5"><input id="sPrtFcltyNm" type="text" size="60" maxlength="40"  /></td>
						</tr>
					</tbody>
				</table>
				<!-- <div class="emdControlPanel">
					<button id="searchBtn">조회</button>
				</div> -->
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


			<!-- 건축시설 상세 -->
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
							<th width="12%" height="17" class="required_text">항코드</th>
							<td><input type="text" size="5" id="gisAssetsPrtAtCode" disabled="disabled"/>  <input type="text" size="5" id="gisAssetsPrtAtName" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">GIS 자산코드</th>
							<td colspan="3">
								<input type="text" size="2" id="gisAssetsCd" disabled="disabled" data-required="true"/>-
								<input type="text" size="1" id="gisAssetsSubCd" disabled="disabled"/>-
								<input type="text" size="2" id="gisAssetsPrtAtCode2" disabled="disabled"/>
								<button id="searchGisCodeBtn2" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">GIS 자산명</th>
							<td><input type="text" size="23" id="gisAssetsNm" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">지번</th>
							<td>
								<input id="gisAssetsLnm" type="text" size="3" title="지번 앞자리" disabled="disabled" />&nbsp;-&nbsp;
								<input id="gisAssetsLnmSub" type="text" size="2" title="지번 뒷자리" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">소재지</th>
							<td><input id="gisAssetsLocplc" type="text" size="32" title="소재지" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">정보통신시설코드</th>
							<td>
								<input type="text" size="2" id="gisPrtFcltyCd" disabled="disabled" />&nbsp;-&nbsp;
								<input type="text" size="3" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
							<th width="12%" height="17" class="required_text">시설분류</th>
							<td>
								<input class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM059" id="selectGisPrtFcltyCd" data-required="true" data-column-id="gisPrtFcltyCd"/>
								<input type="hidden" id="prtFcltySeNm" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">정보통신시설명</th>
							<td><input type="text" size="32" id="prtFcltyNm" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<input type="text" size="14" id="fcltsMngGroupNo" disabled="disabled"/>
								<input type="text" size="40" id="fcltsMngGroupNoNm" disabled="disabled"/>
								<button id="searchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설규격</th>
							<td colspan="3"><input id="prtFcltyStndrd" type="text" size="40" title="시설규격" /></td>
							<th width="12%" height="17" class="required_text">시설단위</th>
							<td><input id="prtFcltyUnit" type="text" size="10" title="시설단위" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설치일자</th>
							<td><input id="prtFcltyInstlDt" type="text" class="emdcal" size="20" title="설치일자" /></td>
							<th width="12%" height="17" class="required_text">변경일자</th>
							<td colspan="3"><input id="prtFcltyChangeDt" type="text" class="emdcal" size="20" title="변경일자" /></td>
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
							<th width="12%" height="17" class="required_text">품목명</th>
							<td colspan="3"><input id="prdlstNm" type="text" size="60" /></td>
							<th width="12%" height="17" class="required_text">수량</th>
							<td><input id="qy" type="text" size="10" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">모델</th>
							<td colspan="5"><input id="model" type="text" size="110" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">제조사</th>
							<td colspan="5"><input id="maker" type="text" size="60" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">세부내역</th>
							<td colspan="5"><input id="ptlrDtls" type="text" size="110" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">규격</th>
							<td colspan="5"><input id="stndrd" type="text" size="80" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설치구분</th>
							<td>
								<select id="instlSe">
                                    <option value=""></option>
                                    <option value="Y">Y</option>
                                    <option value="N">N</option>
                                </select>
                            </td>
							<th width="12%" height="17" class="required_text">설치번호</th>
							<td><input id="instlNo" type="text" size="10" /></td>
							<th width="12%" height="17" class="required_text">기능</th>
							<td><input id="func" type="text" size="20" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">제어방식</th>
							<td><input id="ctrlMthd" type="text" size="20" /></td>
							<th width="12%" height="17" class="required_text">설치규격</th>
							<td><input id="instlStndrd" type="text" size="20" /></td>
							<th width="12%" height="17" class="required_text">설치높이</th>
							<td><input id="instlHt" type="text" size="5" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">LAMP형식</th>
							<td><input id="lampFmt" type="text" size="40" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">위치</th>
							<td colspan="5"><input id="loc" type="text" size="110" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">비고</th>
							<td colspan="5"><input id="rm" type="text" size="110" /></td>
						</tr>
						<tr>							
							<th width="12%" height="17" class="required_text">정보통신시설물분류코드</th>
							<td colspan="5">
								<input id="infoCommFcltsClCd" type="text" size="20" disabled="disabled" />
								<input id="infoCommFcltsClCdNm" type="text" size="30" disabled="disabled" />
								<button id="searchFcltsClCd" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">건축시설물관리번호</th>
							<td colspan="5">
								<input id="archFcltsMngNo" type="text" size="20" disabled="disabled" />
								<input id="archFcltsMngNoNm" type="text" size="30" disabled="disabled" />
								<button id="searchArchFcltsMngNo" class="popupButton">선택</button>
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
							<td><input id="atchFileSj" type="text" size="20" class="photoEditItem" maxlength="40" /></td>
							<th width="15%" height="23" class="required_text">작성일자</th>
							<td><input id="atchFileWritingDt" type="text" size="18" class="emdcal photoEditItem" maxlength="10" readonly="readonly"/></td>
						</tr>
					</table>
				</form>
				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>