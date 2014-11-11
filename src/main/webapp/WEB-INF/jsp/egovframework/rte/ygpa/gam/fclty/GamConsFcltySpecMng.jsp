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
<%-- <validator:javascript formName="gamFcltyCode" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamFcltyPhoto" staticJavascript="false" xhtml="true" cdata="false" /> --%>
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
		url: '<c:url value="/fclty/gamConstFcltySpecMngList.do" />',
		dataType: "json",
		colModel : [
					{display:"항코드",		name:"gisAssetsPrtAtCode",	width:40,		sortable:false,		align:"center"},
					{display:"항코드명",		name:"gisAssetsPrtAtName",	width:60,		sortable:false,		align:"center"},
					{display:"자산코드",		name:"gisAssetsDisplay",	width:60,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:200,		sortable:false,		align:"left"},
					{display:"건축시설코드", 	name:"gisPrtFcltyDisplay",	width:80,		sortable:false,		align:"center"},
					{display:"건축시설명",		name:"prtFcltyNm",			width:280,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:100,		sortable:false,		align:"left"}

			],
		height: "auto"
	});

	this.$("#fcltyinfo9").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamFcltyinfo9.do" />',
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
	});
//	this.$("#fcltyinfo9").flexOptions({params:null}).flexReload();
// 	this.$("#constFcltySpecMngList").flexReload();
	this._fcltyItem = null;


	this.$("#constFcltySpecMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		if(row==null) return;
			if(row['gisAssetsPrtAtCode']==null || row['gisAssetsPrtAtCode'].length==0) {
				alert('자산코드에 오류가 있습니다.');
				return;
			}
			if(row['gisAssetsCd']==null || row['gisAssetsCd'].length==0) {
				alert('자산코드에 오류가 있습니다.');
				return;
			}
			if(row['gisAssetsSubCd']==null || row['gisAssetsSubCd'].length==0) {
				alert('자산코드에 오류가 있습니다.');
				return;
			}
			if(row['gisPrtFcltyCd']==null || row['gisPrtFcltyCd'].length==0) {
				alert('시설코드에 오류가 있습니다.');
				return;
			}
			if(row['gisPrtFcltySeq']==null || row['gisPrtFcltySeq'].length==0) {
				alert('시설코드에 오류가 있습니다.');
				return;
			}
			if(row['prtFcltySe']==null || row['prtFcltySe'].length==0) {
				alert('시설코드에 오류가 있습니다.');
				return;
			}

			module._cmd="modify";
			module.$("#constFcltySpecMngListTab").tabs("option", {active: 1});	// 탭을 전환 한다.
	});


	this.$("#selectedGAM005").on("change", {module: this}, function(event) {
		event.data.module.$("#gisPrtFcltyCd").val($(this).val());
	});


	// 사진 정보 속성이 변경 된 경우 이벤트 실행
	this.$(".photoEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyPhotoChanged(event.target);
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


	this.$("#fcltyPhotoList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamConstFcltySpecPhotoList.do"/>',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"prtFcltyPhotoSeq",	width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"prtFcltyPhotoSeq",	width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"photoSj",				width:240,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"filenmLogic",			width:200,		sortable:true,		align:"left"},
					{display:"물리파일명",	name:"filenmPhysicl",		width:200,		sortable:true,		align:"left"},
					{display:"생성일시",	name:"shotDt",				width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});

	this.$("#fcltyPhotoList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#fcltyGisPhotoForm input").val('');
		module.makeFormValues("#fcltyGisPhotoForm", row);
		module._editDataFile = module.getFormValues("#fcltyGisPhotoForm", row);
		module._editRowFile = module.$("#fcltyPhotoList").selectedRowIds()[0];

		if(row.filenmPhysicl != null || row.filenmPhysicl != "") {

			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm = row["filenmPhysicl"];
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


};

GamConstFcltySpecMngModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	if((newTabId=='tabs2' || newTabId=='tabs3' || newTabId=='tabs4') && this._cmd != 'insert') {
		if(this.$('#constFcltySpecMngList').selectedRowCount()!=1) {
			alert('상세 내역을 조회 할 건축시설 항목을 선택 하세요.');
			return false;
		}
	}
	return true;
};

GamConstFcltySpecMngModule.prototype.applyPhotoChanged = function(target) {
	var changed=false;
	var row={};
	// // console.log("change event occur");

	var selectRow = this.$('#fcltyPhotoList').selectedRows();
	
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#photoSj').is(target)) {
			row['photoSj'] = $(target).val();
			changed=true;
		}
		if(this.$('#shotDt').is(target)) {
			row['shotDt'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#fcltyPhotoList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltyPhotoList').flexUpdateRow(rowid, row);
	}
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
			this.$('#fcltyPhotoList').flexEmptyData();
			this.$('#fcltyinfo9').flexEmptyData();
			this.$("#constFcltySpecMngListTab").tabs("option", {active: 1});
			this.$("#fcltyManageVO :input").val("");
			this.$("#selectedGAM005_select").show();
			this.$("#prtFcltySeNm").hide();
			//this.$("#gisCodePopupBtn").show();
		break;

		// 자산코드 팝업
		case "gisCodePopupBtn":
			this.doExecuteDialog("searchGisCodePopup", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;

		// 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisCodePopup2", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;

		
		case "fcltyinfo9PopupBtn":
			var all_rows = this.$('#fcltyinfo9').flexGetData();
			this.doExecuteDialog("fcltyinfo9Popup", "건축물현황", '/popup/fcltySpecinfo9ListPopup.do', {},all_rows);
		break;

		// 저장
		case "saveBtn":
			//if(!validateGamFcltyCode(this.$("#fcltyManageVO")[0])) return;
			var inputVO = this.makeFormArgs("#fcltyManageVO");

		 	if(this._cmd == "insert") {
			 	this.doAction('<c:url value="/fclty/gamConstFcltySpecInsert.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#fcltyForm");
						module.$("#constFcltySpecMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#constFcltySpecMngListTab").tabs("option", {active: 0});
						module.$("#fcltyManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/fclty/gamConstFcltySpecUpdate.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#fcltyForm");
						module.$("#constFcltySpecMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#constFcltySpecMngListTab").tabs("option", {active: 0});
						module.$("#fcltyManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
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

				var inputVO = {gisAssetsCd:row[0]["gisAssetsCd"], gisPrtFcltySeq:row[0]["gisPrtFcltySeq"], gisAssetsPrtAtCode:row[0]["gisAssetsPrtAtCode"], gisAssetsSubCd:row[0]["gisAssetsSubCd"], gisPrtFcltyCd:row[0]["gisPrtFcltyCd"]};
			 	this.doAction('<c:url value="/fclty/gamConstFcltySpecDelete.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#fcltyForm");
						module.$("#constFcltySpecMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#constFcltySpecMngListTab").tabs("option", {active: 0});
						module.$("#fcltyManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
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
			console.log('kkk');
			// 사진을 업로드하고 업로드한 사진 목록을 result에 어레이로 리턴한다.
			this.uploadPfPhoto("uploadPhoto", function(module, result) {

				var userid = "admin";
				
				$.each(result, function(){
					module.$("#fcltyPhotoList").flexAddRow({_updtId:'I', gisAssetsPrtAtCode: module._fcltyItem.gisAssetsPrtAtCode, gisAssetsCd: module._fcltyItem.gisAssetsCd, gisAssetsSubCd: module._fcltyItem.gisAssetsSubCd, prtFcltySe:'C', gisPrtFcltyCd: module._fcltyItem.gisPrtFcltyCd, gisPrtFcltySeq: module._fcltyItem.gisPrtFcltySeq, prtFcltyPhotoSeq: "", photoSj: "", filenmLogic: this.logicalFileNm, filenmPhysicl: this.physcalFileNm, shotDt: "", photoDesc : ""});
				});
			}, "첨부파일 업로드");
			break;
		case 'btnDownloadFile':
			var selectRow = this.$('#fcltyPhotoList').selectedRows();
			if(selectRow.length > 0) {
				var row=selectRow[0];
				this.downPfPhoto(row["filenmPhysicl"], row["filenmLogic"]);
			}
			break;

		case "btnRemoveFile":
			this.removeGisAssetPhotoItem();
		break;
		case 'btnSaveFile':	// 저장
		
			if(!this.$("#fileDiv").val()){
				alert('파일구분을 선택하세요.');
				return;
			}
			if( confirm("사진 목록을 저장하시겠습니까?") ) {
			    // 변경된 자료를 저장한다.
			    var inputVO=[];
			    console.log(inputVO[inputVO.length]);
			    inputVO[inputVO.length]={name: 'updateList', value :JSON.stringify(this.$('#fcltyPhotoList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
			    
			    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#fcltyPhotoList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

			    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataFileList) };

			    this.doAction('<c:url value="/fclty/mergeGamConstFcltySpecPhotoMngt.do" />', inputVO, function(module, result) {
			        if(result.resultCode == 0){
				    	module.loadPhotoList();
			        }
			        alert(result.resultMsg);
			    });
			}
			break;
			
		case 'saveBtnFloor':	// 저장
			if( confirm("층별제원목록을 저장하시겠습니까?") ) {
			    // 변경된 자료를 저장한다.
				var row = this.$("#constFcltySpecMngList").selectedRows();

				row=row[0];
				
				var inputVO = [];
				var searchOpt = JSON.stringify({'fcltsMngNo':row['fcltsMngNo']});
				var all_rows = JSON.stringify(this.$('#fcltyinfo9').flexGetData());
				alert(searchOpt);
				alert(all_rows);
				inputVO[inputVO.length] = {name: 'updateList',value: all_rows};
				inputVO[inputVO.length] = {name: 'searchOpt',value: searchOpt};

			    this.doAction('<c:url value="/fclty/gamFcltyFloorSpecSave.do" />', inputVO, function(module, result) {
			        if(result.resultCode == 0){
			        	alert(result.resultMsg);
			        }
			        
			    });
			}
			
			break;
		
	}
};

GamConstFcltySpecMngModule.prototype.removeGisAssetPhotoItem = function() {
	var rows = this.$("#fcltyPhotoList").selectedRows();

    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    
    if(this.$("#fcltyPhotoList").selectedRowIds().length>0) {
    	for(var i=this.$("#fcltyPhotoList").selectedRowIds().length-1; i>=0; i--) {

    		var row = this.$("#fcltyPhotoList").flexGetRow(this.$("#fcltyPhotoList").selectedRowIds()[i]);

            if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataFileList[this._deleteDataFileList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#fcltyPhotoList").flexRemoveRow(this.$("#fcltyPhotoList").selectedRowIds()[i]);

        	this._edited=true;
		}

    	this.$("#previewImage").attr("src","");
    	alert("삭제되었습니다.");
	}

    this.$("#fcltyGisPhotoForm").find(":input").val("");
    this._editDataFile = null;
};



GamConstFcltySpecMngModule.prototype.clearCodePage = function() {
	this.$('#fcltyManageVO :input').val('');
};

GamConstFcltySpecMngModule.prototype.clearPhotoPage = function() {
	this.$('#fcltyPhotoList').flexEmptyData();
	this.$('#fcltyGisPhotoForm :input').val('');
	this.$('#previewImage').attr('src', '');
};

GamConstFcltySpecMngModule.prototype.loadPhotoList = function() {
	var row = this.$('#constFcltySpecMngList').selectedRows();
	if(row.length <= 0 || this._cmd == "insert") {
 		this.clearPhotoPage();
		return;
	}
	row=row[0];
	var searchOpt = [
	                { name: 'gisAssetsPrtAtCode', value: row['gisAssetsPrtAtCode'] },
	                { name: 'gisAssetsCd', value: row['gisAssetsCd'] },
	                { name: 'gisAssetsSubCd', value: row['gisAssetsSubCd'] },
	                { name: 'gisPrtFcltyCd', value: row['gisPrtFcltyCd'] },
	                { name: 'gisPrtFcltySeq', value: row['gisPrtFcltySeq'] },
	                { name: 'prtFcltySe', value: row['prtFcltySe'] }
	              ];
	this.clearPhotoPage();

 	this.$('#fcltyPhotoList').flexOptions({params:searchOpt}).flexReload();
};
/**
 * 탭 변경시 실행 이벤트
 */
 GamConstFcltySpecMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":

		var row = this.$('#constFcltySpecMngList').selectedRows();

		if(row.length == 1 && this._cmd != "insert"){

			row=row[0];
			var prtFclty = [
			                { name: 'gisAssetsPrtAtCode', value: row['gisAssetsPrtAtCode'] },
			                { name: 'gisAssetsCd', value: row['gisAssetsCd'] },
			                { name: 'gisAssetsSubCd', value: row['gisAssetsSubCd'] },
			                { name: 'gisPrtFcltyCd', value: row['gisPrtFcltyCd'] },
			                { name: 'gisPrtFcltySeq', value: row['gisPrtFcltySeq'] },
			                { name: 'prtFcltySe', value: row['prtFcltySe'] }
			              ];
			
			this.doAction('<c:url value="/fclty/gamConstFcltySpecDetail.do" />', prtFclty, function(module, result) {
	   	 		if(result.resultCode == "0"){
	   	 			module.clearCodePage();
	   	 			module._fcltyItem=result.result;
	   	 			module.makeFormValues('#fcltyManageVO', result.result);	// 결과값을 채운다.
	   	 			module.$("#titleFcltsMngNo").text(result.result["fcltsMngNo"]);	// 결과값을 채운다.

	    	 		module.$("#beforeGisPrtFcltyCd").val(module.$("#gisPrtFcltyCd").val());
	                module.$("#beforeGisPrtFcltySeq").val(module.$("#gisPrtFcltySeq").val());
	   	 		}
	   	 		else {
	   	 			//alert(result.resultMsg);
	   	 		}
	   	 		
	   	 		module.$("#gisCodePopupBtn").hide();
	   	 		module.$("#selectedGAM005").disable();
	   	 	});
		}else{
			this.clearCodePage();
			
			this.$("#gisCodePopupBtn").show();
   	 		this.$("#selectedGAM005").enable();
		}

		break;
		
		
	case "tabs3":
		
		var row = this.$('#constFcltySpecMngList').selectedRows();
		
		if(row.length == 1 && this._cmd == "modify"){
			row=row[0];
			var prtFclty = [
			                { name: 'fcltsMngNo', value: row['fcltsMngNo'] }
			              ];
		     	 	
		    this.$('#fcltyinfo9').flexOptions({params:prtFclty}).flexReload();
		}else{
			this.$('#fcltyinfo9').flexEmptyData();
		}
		
		break;
		
	case "tabs4":
		this._deleteDataFileList=[];
		this.loadPhotoList();
		
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
		case "fcltyinfo9Popup":
			this.$("#fcltyinfo9").flexAddData({resultList: value });

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
								<input id="searchFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM057" />&nbsp;-&nbsp;
							</td>
							<td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>건축시설명</th>
							<td colspan="5"><input id="searchKeyword" type="text" size="60" maxlength="40" title="검색조건"  /></td>
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
				<li><a href="#tabs3" class="emdTab">건축시설 층별제원</a></li>
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
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
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
							<td><input type="text" size="5" id="gisAssetsPrtAtCodeStr" disabled="disabled"/>  <input type="text" size="5" id="gisAssetsPrtAtName" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td>
								<input type="text" size="7" id="fcltsMngGroupNo" disabled="disabled"/>
								<button id="fcltyCodePopupBtn" class="popupButton">선택</button>
							</td>
							<th width="12%" height="17" class="required_text">GIS 자산코드</th>
							<td>
								<input type="text" size="2" id="gisAssetsCd" disabled="disabled" data-required="true"/>-
								<input type="text" size="1" id="gisAssetsSubCd" disabled="disabled"/>-
								<input type="text" size="2" id="gisAssetsPrtAtCode" disabled="disabled"/>
								<button id="gisCodePopupBtn" class="popupButton">선택</button>
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
							<th width="12%" height="17" class="required_text">건축시설코드</th>
							<td>
								<input type="text" size="2" id="gisPrtFcltyCd" disabled="disabled" />&nbsp;-&nbsp;
								<input type="text" size="3" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
							<th width="12%" height="17" class="required_text">시설분류</th>
							<td>
								<input class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM057" id="selectedGAM005" data-required="true" data-column-id="gisPrtFcltyCd" disabled="disabled" />
								<input type="hidden" id="prtFcltySeNm" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">건축시설명</th>
							<td colspan="3"><input type="text" size="32" id="prtFcltyNm" maxlength="80" /></td>
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
							<td colspan="3"><input type="text" size="75" id="strctFmt" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">하자만료일자</th>
							<td><input type="text" size="15" id="flawEndDt" class="emdcal" /></td>
							<th width="12%" height="17" class="required_text">기초형식</th>
							<td colspan="3"><input type="text" size="75" id="baseFmt"  data-required="true"/></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">연면적</th>
							<td><input id="ar" type="text" size="20" title="연면적"  /></td>
							<th width="12%" height="17" class="required_text">건축면적</th>
							<td><input id="archAr" type="text" size="20" title="건축면적"  /></td>
							<th width="12%" height="17" class="required_text">대지면적</th>
							<td><input type="text" size="20" id="plotAr"  /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">주사용용도</th>
							<td colspan="5"><input type="text" size="124" id="mainUsagePrpos" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">주차면적</th>
							<td><input type="text" size="20" id="prkAr" maxlength="80" /></td>
							<th width="12%" height="17" class="required_text">옥내주차면적</th>
							<td><input type="text" size="20" id="isdPrkAr" maxlength="80" /></td>
							<th width="12%" height="17" class="required_text">옥외주차면적</th>
							<td><input id="osdPrkAr" type="text" size="20" title="옥외주차면적" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">주차대수</th>
							<td><input id="prkCnt" type="text" size="20" title="주차대수" /></td>
							<th width="12%" height="17" class="required_text">옥내주차대수</th>
							<td><input class="text" type="text" size="20" id="isdPrkCnt" maxlength="40" /></td>
							<th width="12%" height="17" class="required_text">옥외주차대수</th>
							<td><input class="text" type="text" size="20" id="osdPrkCnt" maxlength="10" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">배기닥트유무</th>
							<td><input class="text" type="text" size="20" id="exhaustDuctEnnc" maxlength="40" /></td>
							<th width="12%" height="17" class="required_text">환기공조방식</th>
							<td colspan="3"><input class="text" type="text" size="75" id="vntltnArcndtMthd" maxlength="10" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">승강기대수승객용</th>
							<td><input class="text" type="text" size="20" id="liftCntPsngr" maxlength="40" /></td>
							<th width="12%" height="17" class="required_text">물탱크위치</th>
							<td colspan="3"><input class="text" type="text" size="75" id="wrtTankLoc" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">승강기대수화물용</th>
							<td><input class="text" type="text" size="20" id="liftCntCargo" maxlength="40" /></td>
							<th width="12%" height="17" class="required_text">변전실위치</th>
							<td colspan="3"><input class="text" type="text" size="75" id="sbtLoc" maxlength="10" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">승강기대수비상용</th>
							<td><input class="text" type="text" size="20" id="liftCntEmgcy" maxlength="40" /></td>
							<th width="12%" height="17" class="required_text">유류저장시설위치</th>
							<td colspan="3"><input class="text" type="text" size="75" id="oilSavefcltyLoc" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">냉방유무</th>
							<td><input class="text" type="text" size="20" id="clngEnnc" maxlength="40" /></td>
							<th width="12%" height="17" class="required_text">냉방열원</th>
							<td><input class="text" type="text" size="20" id="clngSrc" maxlength="40" /></td>
							<th width="12%" height="17" class="required_text">오수정화시설위치</th>
							<td colspan="3"><input class="text" type="text" size="20" id="swgClupfcltyLoc" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">승강기운영방식</th>
							<td colspan="5"><input class="text" type="text" size="124" id="liftOperMthd" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">난방유무</th>
							<td><input id="htngEnnc" type="text"  size="20" title="난방유무" /></td>
							<th width="12%" height="17" class="required_text">난방열원</th>
							<td colspan="3"><input  id="htngSrc" type="text"  size="75" title="난방열원" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">전기인입용량</th>
							<td><input id="elctyLeadInCapa" type="text"  size="20" title="전기인입용량" /></td>
							<th width="12%" height="17" class="required_text">정화조형식</th>
							<td colspan="3"><input id="spictankFmt" type="text"  size="75" title="정화조형식" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">외장</th>
							<td colspan="5"><input id="fcg" type="text"  size="124" title="외장" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">내장</th>
							<td colspan="5"><input id="itr" type="text"  size="124" title="내장" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">천장</th>
							<td colspan="5"><input id="ceil" type="text"  size="124" title="천장" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">지붕</th>
							<td colspan="5"><input id="roof" type="text"  size="124" title="지붕" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">지붕방수</th>
							<td colspan="5"><input id="roofWtprf" type="text"  size="124" title="지붕방수" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설계공사명</th>
							<td colspan="5"><input id="planCnstNm" type="text"  size="124" title="설계공사명" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설계수행회사</th>
							<td colspan="5"><input id="planExcCmpny" type="text" size="124" title="설계수행회사" /></td>
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
							<td colspan="5"><input id="cnstrctCnstNm" type="text"  size="124" title="시공공사명" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시공수행회사</th>
							<td colspan="5"><input id="cnstrctExcCmpny" type="text" size="124" title="시공수행회사" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시공종료일자</th>
							<td><input id="cnstrctEndDt" type="text" class="emdcal" size="15" title="시공종료일자" /></td>
							<th width="12%" height="17" class="required_text">시설물관리번호</th>
							<td><input id="fcltsMngNo" type="text" size="20" title="시설물관리번호" /></td>
							<th width="12%" height="17" class="required_text">건축시설물분류코드</th>
							<td><input id="archFcltsClCd" type="text" size="20" title="건축시설물분류코드" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">비고</th>
							<td colSpan="5"><input id="rm" type="text" size="124" title="비고" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">위치</th>
							<td colSpan="5"><input id="loc" type="text" size="124" title="위치" /></td>
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
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyinfo9" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button class="text" id="fcltyinfo9PopupBtn">추가/편집</button>
					<button id="saveBtnFloor">저장</button>
				</div>
			</div>

			<!-- 건축시설 첨부파일 -->
			<div id="tabs4" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyPhotoList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnUploadFile">업로드</button>
					<button id="btnDownloadFile">다운로드</button>
					<button id="btnRemoveFile">삭제</button>
					<button id="btnSaveFile">저장</button>
				</div>
				<form id="fcltyGisPhotoForm">
					<table class="searchPanel editForm">
						<tr>
							<th width="15%" height="23" class="required_text">파일구분</th>
							<td>
								<select id="fileDiv">
									<option value="">선택</option>
                                    <option value="P">사진</option>
                                    <option value="D">문서</option>
                                    <option value="Z">기타</option>
                                </select>
							</td>
							<th width="15%" height="23" class="required_text">파일제목</th>
							<td><input id="photoSj" type="text" size="20" class="photoEditItem" maxlength="40" /></td>
							<th width="15%" height="23" class="required_text">생성일자</th>
							<td><input id="shotDt" type="text" size="18" class="emdcal photoEditItem"  maxlength="10" readonly="readonly"/></td>
						</tr>
					</table>
				</form>
				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>