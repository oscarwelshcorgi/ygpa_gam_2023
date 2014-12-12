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
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#constFcltySpecMngList").flexigrid({
		module: this,
		url: '/fclty/gamConstFcltySpecMngList.do',
		dataType: "json",
		colModel : [
					{display:"항코드",		name:"gisAssetsPrtAtCode",	width:80,		sortable:false,		align:"center"},
					{display:"항코드명",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"자산코드",		name:"gisAssetsDisplay",	width:80,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:200,		sortable:false,		align:"left"},
					{display:"시설코드", 		name:"gisPrtFcltyDisplay",	width:120,		sortable:false,		align:"center"},
					{display:"시설명",		name:"prtFcltyNm",			width:280,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:100,		sortable:false,		align:"center"}

			],
		height: "auto"
	});

	/* this.$("#fcltyinfo9").flexigrid({
		module: this,
		url: '/fclty/gamFcltyinfo9.do',
		dataType: "json",
		colModel : [
					{display:"구역",			name:"bound",			width:60,		sortable:false,		align:"center"},
					{display:"층구분",		name:"strySe",			width:60,		sortable:false,		align:"center"},
					{display:"면적",			name:"ar",				width:80,		sortable:false,		align:"center"},
					{display:"벽마감재",		name:"wallFnsh",		width:140,		sortable:false,		align:"center"},
					{display:"바닥마감재",		name:"flrFnsh",			width:140,		sortable:false,		align:"center"},
					{display:"천장",			name:"ceil",			width:140,		sortable:false,		align:"center"},
					{display:"사용용도",		name:"usagePrpos",		width:140,		sortable:false,		align:"center"},
					{display:"비고",			name:"rm",				width:140,		sortable:false,		align:"center"}
			],
		height: "auto"
	}); */
//	this.$("#fcltyinfo9").flexOptions({params:null}).flexReload();
// 	this.$("#constFcltySpecMngList").flexReload();
	this._fcltyItem = null;

	this.$("#constFcltySpecMngList").on("onItemSelected", function(event, module, row, grid, param) {
		module._cmd="modify";
	});


	this.$("#constFcltySpecMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._cmd="modify";
		
		module.$("#constFcltySpecMngListTab").tabs("option", {active: 1});	// 탭을 전환 한다.
		
	});


	this.$("#selectedGAM005").on("change", {module: this}, function(event) {
		event.data.module.$("#gisPrtFcltyCd").val($(this).val());
	});


	// 사진 정보 속성이 변경 된 경우 이벤트 실행
	this.$(".fileEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyFileChanged(event.target);
	});

	this.$(".text").bind("change keyup", {module: this}, function(event) {
		var limit_char = /[|]/;
		if(limit_char.test(event.target.value)){
			alert('|'+"(파이프) 특수문자는 사용 하실수 없습니다.");
			var rep= event.target.value.replace(limit_char,"");
			event.target.value = rep;
			return;
		}

// 		event.target.value

	});


	this.$("#fcltyFileList").flexigrid({
		module: this,
		url: '/fclty/gamConstFcltySpecFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",				width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",			width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",				width:240,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",			width:200,		sortable:true,		align:"left"},
					{display:"물리파일명",	name:"atchFileNmPhysicl",		width:200,		sortable:true,		align:"left"},
					{display:"작성일자",	name:"atchFileWritngDt",		width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});

	this.$("#fcltyFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#fcltyGisFileForm input").val('');
		module.makeFormValues("#fcltyGisFileForm", row);
		module._editDataFile = module.getFormValues("#fcltyGisFileForm", row);
		module._editRowFile = module.$("#fcltyFileList").selectedRowIds()[0];
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
				module.$("#previewImage").attr("src", "");
			}
		}
	});


};

GamConstFcltySpecMngModule.prototype.loadDetail = function() {

	var row = this.$('#constFcltySpecMngList').selectedRows();
	row = row[0];
	var prtFclty = [{ name: 'fcltsMngNo', value: row['fcltsMngNo'] }];

	if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
		this.$("#constFcltySpecMngListTab").tabs("option", {active: 0});
		alert('시설물 관리번호에 오류가 있습니다.');
		this._cmd = '';
		this.initDisplay();
		return;
	}

	// 건축시설 제원 처리
	this.doAction('/fclty/gamConstFcltySpecDetail.do', prtFclty, function(specModule, result) {
 		if(result.resultCode == "0"){
 			specModule.clearCodePage();
 			specModule._fcltyItem=result.result;
 			specModule.makeFormValues('#fcltyManageVO', result.result);	// 결과값을 채운다.
 			specModule.$("#titleFcltsMngNo").text(result.result["fcltsMngNo"]);	// 결과값을 채운다.

	 		specModule.$("#beforeGisPrtFcltyCd").val(specModule.$("#gisPrtFcltyCd").val());
            specModule.$("#beforeGisPrtFcltySeq").val(specModule.$("#gisPrtFcltySeq").val());
 		}
 		else {
 			//alert(result.resultMsg);
 		}

 		specModule.$("#gisCodePopupBtn").hide();
 		specModule.$("#selectedGAM005").disable();
 	});

	// 층별제원처리
	//this.$('#fcltyinfo9').flexOptions({params:prtFclty}).flexReload();

	// 첨부파일 처리
	this.$('#fcltyFileList').flexOptions({params:prtFclty}).flexReload();
	this.clearFilePage();

};

GamConstFcltySpecMngModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {

	if((newTabId=='tabs2' || newTabId=='tabs4') && this._cmd != 'insert') {
		if(this.$('#constFcltySpecMngList').selectedRowCount()!=1) {
			alert('건축시설 항목을 선택 하세요.');
			return false;
		}
	}
	/* if(newTabId=='tabs1'){
		this.$('#constFcltySpecMngList').selectRowId();
	} */

	return true;
};

GamConstFcltySpecMngModule.prototype.applyFileChanged = function(target) {
	var changed=false;
	var row={};
	// // console.log("change event occur");

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

/* GamConstFcltySpecMngModule.prototype.insertFcltyFloorSpec = function(fcltsMngNo) {

	var all_rows = this.$('#fcltyinfo9').flexGetData();

	for(i=0;i<all_rows.length;i++){
		all_rows[i]["fcltsMngNo"] = fcltsMngNo;
	}

	var inputFloorVO = [];
	inputFloorVO[inputFloorVO.length]={name: 'updateList', value :JSON.stringify(this.$('#fcltyinfo9').selectFilterData([{col: '_updtId', filter: 'U'}])) };
	inputFloorVO[inputFloorVO.length]={name: 'insertList', value: JSON.stringify(this.$('#fcltyinfo9').selectFilterData([{col: '_updtId', filter: 'I'}])) };
	inputFloorVO[inputFloorVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataFloorSpecList) };

	this.doAction('/fclty/gamFcltyFloorSpecSave.do', inputFloorVO, function(floorModule, floorResult) {
        if(floorResult.resultCode == 0){

        }
    });
}; */


GamConstFcltySpecMngModule.prototype.insertFcltySpecFile = function(fcltsMngNo) {

	var all_rows = this.$('#fcltyFileList').flexGetData();

	for(i=0;i<all_rows.length;i++){
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


/**
 * 정의 된 버튼 클릭 시
 */
GamConstFcltySpecMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			this._cmd="";
			var searchOpt=this.makeFormArgs("#fcltyForm");
		 	this.$("#constFcltySpecMngListTab").tabs("option", {active: 0});
		 	this.$("#constFcltySpecMngList").flexOptions({params:searchOpt}).flexReload();

		break;

		// 추가
		case "addBtn":
			this._cmd="insert";
			this.$('#fcltyFileList').flexEmptyData();
			//this.$('#fcltyinfo9').flexEmptyData();
			this.$("#fcltyManageVO :input").val("");
			this.$("#titleFcltsMngNo").text('');
			this.$("#selectedGAM005").enable();
			this.$("#gisCodePopupBtn").show();
			this.$("#constFcltySpecMngListTab").tabs("option", {active: 1});

		break;

		// 자산코드 팝업
		case "gisCodePopupBtn":
			this.doExecuteDialog("searchGisCodePopup", "자산코드", '/popup/showAssetsCd.do', {});
		break;

		// 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisCodePopup2", "자산코드", '/popup/showAssetsCd.do', {});
		break;
		
		// 시설물 분류코드(디테일 화면)
		case "searchFcltsClCd" :
			this.doExecuteDialog("selectFcltsClCd", "시설물 분류코드", '/popup/showFcltsClCd.do', { sFcltsClCdChar : this._prtFcltySe });			
			break;
			
		// 시설물관리그룹(디테일 화면)
		case "searchFcltsMngGroupNo":
			this.doExecuteDialog("selectFcltsMngGroup", "시설물 관리 그룹 번호", '/popup/showFcltsMngGroup.do', {});
			break;

		/* case "fcltyinfo9PopupBtn":
			var all_rows = this.$('#fcltyinfo9').flexGetData();
			for(i=0;i<all_rows.length;i++){
				all_rows[i]["_updtId"] = "";
			}
			console.log('debug');

			this.doExecuteDialog("fcltyinfo9Popup", "건축물현황", '/popup/fcltySpecinfo9ListPopup.do', {},all_rows);
		break; */

		// 저장
		case "saveBtn":
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
						//module.$("#constFcltySpecMngListTab").tabs("option", {active: 0});
						//module.$("#fcltyManageVO :input").val("");

						fcltsMngNo = result.fcltsMngNo;

						// 층별제원 입력/수정처리
						//module.insertFcltyFloorSpec(fcltsMngNo);

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
						//module.$("#constFcltySpecMngListTab").tabs("option", {active: 0});
						//module.$("#fcltyManageVO :input").val("");

						fcltsMngNo = result.fcltsMngNo;

						// 층별제원 입력/수정처리
						//module.insertFcltyFloorSpec(fcltsMngNo);

						// 첨부파일 입력/수정처리
						module.insertFcltySpecFile(fcltsMngNo);

			 		}
			 		alert(result.resultMsg);
			 	});

			 	this._cmd="";
			}

		break;

		// 삭제
		case "deleteBtn":
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
		// 파일 업로드
		case "btnUploadFile":

			// 사진을 업로드하고 업로드한 사진 목록을 result에 어레이로 리턴한다.
			this.uploadPfPhoto("uploadFile", function(module, result) {

				$.each(result, function(){
					module.$("#fcltyFileList").flexAddRow({_updtId:'I', atchFileSeq:"", fcltsMngNo:"", atchFileSe:"D", atchFileSeNm:"문서",  atchFileSj: "", atchFileNmLogic: this.logicalFileNm, atchFileNmPhysicl: this.physcalFileNm, atchFileWritngDt: ""});
				});
			}, "첨부파일 업로드");
			break;
		case 'btnDownloadFile':
			var selectRow = this.$('#fcltyFileList').selectedRows();
			if(selectRow.length > 0) {
				var row=selectRow[0];
				this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
			}
			break;

		case "btnRemoveFile":
			this.removefcltyFileItem();
		break;

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



GamConstFcltySpecMngModule.prototype.clearCodePage = function() {
	this.$('#fcltyManageVO :input').val('');
};

GamConstFcltySpecMngModule.prototype.clearFilePage = function() {
	this.$('#fcltyFileList').flexEmptyData();
	this.$('#fcltyGisFileForm :input[type=text]').val('');
	this.$('#previewImage').attr('src', '');
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
	
		/* case "tabs3":
			this._deleteDataFloorSpecList=[];
			break; */
	
		case "tabs4":
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

		// 조회화면
		case "searchGisCodePopup2":
			this.$("#searchAssetsCd").val(value["gisAssetsCd"]);
			this.$("#searchAssetsSubCd").val(value["gisAssetsSubCd"]);
		break;

		// 업체조회화면
		case "searchEntrpsCdPopup":
			this.$("#prtFcltyMngEntrpsCd").val(value["entrpscd"]);
			this.$("#prtFcltyMngEntrpsNm").val(value["entrpsNm"]);
		break;
		// 건축물현황
		/* case "fcltyinfo9Popup":
			if(msg == 'ok'){
				this.$("#fcltyinfo9").flexEmptyData();
			}
			this.$("#fcltyinfo9").flexAddData({resultList: value["inputVo"] });

			this._deleteDataFloorSpecList = value["deleteDataFloorSpecList"]; */

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
							<th>항코드</th>
							<td><input id="searchPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>자산코드</th>
							<td>
								<input id="searchAssetsCd" type="text" size="3" maxlength="3" title="검색조건" />&nbsp;-&nbsp;
								<input id="searchAssetsSubCd" type="text" size="2" maxlength="2" title="검색조건" />
								<button id="searchPopupBtn" class="popupButton">선택</button>
							</td>
							<th>건축시설코드</th>
							<td>
								<input id="searchFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM057" />
							</td>
							<td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>건축시설명</th>
							<td colspan="5"><input id="searchKeyword" type="text" size="72" maxlength="40" title="검색조건"  /></td>
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
				<!-- <li><a href="#tabs3" class="emdTab">건축시설 층별제원</a></li> -->
				<li><a href="#tabs4" class="emdTab">건축시설 첨부파일</a></li>
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
							<th width="12%" height="17" class="required_text">항코드</th>
							<td><input type="text" size="5" id="gisAssetsPrtAtCodeStr" disabled="disabled"/>  <input type="text" size="15" id="gisAssetsPrtAtName" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">GIS 자산코드</th>
							<td colspan="3">
								<input type="text" size="4" id="gisAssetsCd" disabled="disabled" data-required="true"/>-
								<input type="text" size="3" id="gisAssetsSubCd" disabled="disabled"/>-
								<input type="text" size="4" id="gisAssetsPrtAtCode" disabled="disabled"/>
								<button id="gisCodePopupBtn" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">GIS 자산명</th>
							<td><input type="text" size="23" id="gisAssetsNm" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">지번</th>
							<td>
								<input id="gisAssetsLnm" type="text" size="3" title="지번 앞자리" disabled="disabled" />&nbsp;-&nbsp;
								<input id="gisAssetsLnmSub" type="text" size="3" title="지번 뒷자리" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">소재지</th>
							<td><input id="gisAssetsLocplc" type="text" size="32" title="소재지" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설코드</th>
							<td>
								<input type="text" size="2" id="gisPrtFcltyCd" disabled="disabled" />&nbsp;-&nbsp;
								<input type="text" size="5" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
							<th width="12%" height="17" class="required_text">시설분류</th>
							<td>
								<input class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM057" id="selectedGAM005" data-required="true" data-column-id="gisPrtFcltyCd" />
								<input type="hidden" id="prtFcltySeNm"/>
							</td>
							<th width="12%" height="17" class="required_text">건축시설명</th>
							<td><input type="text" size="32" id="prtFcltyNm" maxlength="25" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<input type="text" size="14" id="fcltsMngGroupNo" disabled="disabled"/>
								<input type="text" size="40" id="fcltsMngGroupNoNm" disabled="disabled"/>
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
							<th width="12%" height="17" class="required_text">준공일자</th>
							<td><input type="text" size="15" id="bldDt" class="emdcal" /></td>
							<th width="12%" height="17" class="required_text">구조형식</th>
							<td colspan="3"><input type="text" size="75" id="strctFmt" maxlength="33" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">하자만료일자</th>
							<td><input type="text" size="15" id="flawEndDt" class="emdcal" /></td>
							<th width="12%" height="17" class="required_text">기초형식</th>
							<td colspan="3"><input type="text" size="75" id="baseFmt"  data-required="true" maxlength="33" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">연면적</th>
							<td><input id="ar" type="text" size="20" title="연면적" class="ygpaNumber" data-decimal-point="2" maxlength="10" /></td>
							<th width="12%" height="17" class="required_text">건축면적</th>
							<td><input id="archAr" type="text" size="20" title="건축면적" class="ygpaNumber" data-decimal-point="2" maxlength="10" /></td>
							<th width="12%" height="17" class="required_text">대지면적</th>
							<td><input type="text" size="20" id="plotAr" class="ygpaNumber" data-decimal-point="2" maxlength="10" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">주사용용도</th>
							<td colspan="5"><input type="text" size="124" id="mainUsagePrpos" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">주차면적</th>
							<td><input type="text" size="20" id="prkAr" class="ygpaNumber" data-decimal-point="2" maxlength="10" /></td>
							<th width="12%" height="17" class="required_text">옥내주차면적</th>
							<td><input type="text" size="20" id="isdPrkAr" class="ygpaNumber" data-decimal-point="2" maxlength="10" /></td>
							<th width="12%" height="17" class="required_text">옥외주차면적</th>
							<td><input id="osdPrkAr" type="text" size="20" title="옥외주차면적" class="ygpaNumber" data-decimal-point="2" maxlength="10" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">주차대수</th>
							<td><input id="prkCnt" type="text" size="20" title="주차대수" class="ygpaNumber" maxlength="5" /></td>
							<th width="12%" height="17" class="required_text">옥내주차대수</th>
							<td><input type="text" size="20" id="isdPrkCnt" class="ygpaNumber" maxlength="5" /></td>
							<th width="12%" height="17" class="required_text">옥외주차대수</th>
							<td><input type="text" size="20" id="osdPrkCnt" class="ygpaNumber" maxlength="5" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">배기닥트유무</th>
							<td>
								<select id="exhaustDuctEnnc">
									<option value="">선택</option>
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
							</td>
							<th width="12%" height="17" class="required_text">환기공조방식</th>
							<td colspan="3"><input type="text" size="75" id="vntltnArcndtMthd" maxlength="16" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">승강기대수승객용</th>
							<td><input type="text" size="20" id="liftCntPsngr" class="ygpaNumber" maxlength="5" /></td>
							<th width="12%" height="17" class="required_text">물탱크위치</th>
							<td colspan="3"><input type="text" size="75" id="wrtTankLoc" maxlength="33" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">승강기대수화물용</th>
							<td><input type="text" size="20" id="liftCntCargo" class="ygpaNumber" maxlength="5"/></td>
							<th width="12%" height="17" class="required_text">변전실위치</th>
							<td colspan="3"><input type="text" size="75" id="sbtLoc" maxlength="33"/></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">승강기대수비상용</th>
							<td><input type="text" size="20" id="liftCntEmgcy" class="ygpaNumber" maxlength="5" /></td>
							<th width="12%" height="17" class="required_text">유류저장시설위치</th>
							<td colspan="3"><input type="text" size="75" id="oilSavefcltyLoc" maxlength="33" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">냉방유무</th>
							<td>
								<select id="clngEnnc">
									<option value="">선택</option>
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
							</td>
							<th width="12%" height="17" class="required_text">냉방열원</th>
							<td><input type="text" size="20" id="clngSrc" maxlength="33" /></td>
							<th width="12%" height="17" class="required_text">오수정화시설위치</th>
							<td colspan="3"><input type="text" size="20" id="swgClupfcltyLoc" maxlength="33" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">승강기운영방식</th>
							<td colspan="5"><input type="text" size="124" id="liftOperMthd" maxlength="33" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">난방유무</th>
							<td>
								<select id="htngEnnc">
									<option value="">선택</option>
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
							</td>
							<th width="12%" height="17" class="required_text">난방열원</th>
							<td colspan="3"><input  id="htngSrc" type="text"  size="75" title="난방열원" maxlength="33" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">전기인입용량</th>
							<td><input id="elctyLeadInCapa" type="text"  size="20" title="전기인입용량" class="ygpaNumber" data-decimal-point="2" maxlength="8" /></td>
							<th width="12%" height="17" class="required_text">정화조형식</th>
							<td colspan="3"><input id="spictankFmt" type="text"  size="75" title="정화조형식" maxlength="100" maxlength="33" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">외장</th>
							<td colspan="5"><input id="fcg" type="text"  size="124" title="외장" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">내장</th>
							<td colspan="5"><input id="itr" type="text"  size="124" title="내장" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">천장</th>
							<td colspan="5"><input id="ceil" type="text"  size="124" title="천장" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">지붕</th>
							<td colspan="5"><input id="roof" type="text"  size="124" title="지붕" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">지붕방수</th>
							<td colspan="5"><input id="roofWtprf" type="text"  size="124" title="지붕방수" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설계공사명</th>
							<td colspan="5"><input id="planCnstNm" type="text"  size="124" title="설계공사명" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설계수행회사</th>
							<td colspan="5"><input id="planExcCmpny" type="text" size="124" title="설계수행회사" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설계시작일자</th>
							<td><input id="planBeginDt" type="text" class="emdcal" size="15" title="설계시작일자" /></td>
							<th width="12%" height="17" class="required_text">설계종료일자</th>
							<td><input id="planEndDt" type="text" class="emdcal" size="15" title="설계종료일자" /></td>
							<th width="12%" height="17" class="required_text">시공시작일자</th>
							<td><input id="cnstrctBeginDt" type="text" class="emdcal" size="15" title="시공시작일자" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시공공사명</th>
							<td colspan="5"><input id="cnstrctCnstNm" type="text"  size="124" title="시공공사명" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시공수행회사</th>
							<td colspan="5"><input id="cnstrctExcCmpny" type="text" size="124" title="시공수행회사" maxlength="65" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시공종료일자</th>
							<td><input id="cnstrctEndDt" type="text" class="emdcal" size="15" title="시공종료일자" /></td>
							<th width="12%" height="17" class="required_text">건축시설물분류코드</th>
							<td colspan="3">
								<input id="archFcltsClCd" type="text" size="20" title="건축시설물분류코드" maxlength="10" disabled="disabled" />
								<input id="archFcltsClCdNm" type="text" size="30" disabled="disabled" />
								<button id="searchFcltsClCd" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">비고</th>
							<td colSpan="5"><input id="rm" type="text" size="124" title="비고" maxlength="330" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">위치</th>
							<td colSpan="5"><input id="loc" type="text" size="124" title="위치" maxlength="50" /></td>
						</tr>

					</table>
				</form>
				<div class="emdControlPanel">
					<button id="registLocation">위치등록</button>
					<button id="gotoLocation">위치조회</button>
					<button id="saveBtn">저장</button>
				</div>
			</div>

			<!-- 건축시설 층별제원 -->
			<!-- <div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyinfo9" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="fcltyinfo9PopupBtn">추가/편집</button>
					<button id="saveBtn">저장</button>
				</div>
			</div> -->

			<!-- 건축시설 첨부파일 -->
			<div id="tabs4" class="emdTabPage" style="overflow: scroll;">
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
							<td><input id="atchFileSj" type="text" size="20" class="fileEditItem" maxlength="25" /></td>
							<th width="15%" height="23" class="required_text">작성일자</th>
							<td><input id="atchFileWritngDt" type="text" size="18" class="emdcal fileEditItem"  maxlength="10" readonly="readonly"/></td>
						</tr>
					</table>
				</form>
				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>