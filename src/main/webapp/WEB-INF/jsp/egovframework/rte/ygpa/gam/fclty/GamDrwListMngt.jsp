<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamDrwListMngt.jsp
  * @Description : 도면시설사용목록관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.02.05  kok          최초 생성
  *
  * author kok
  * since 2014.02.05
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamDrwListCode" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamDrwListPhoto" staticJavascript="false" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyDrwListMngtModule() {}

GamFcltyDrwListMngtModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyDrwListMngtModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.
	console.log(params);

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#drwListMngtList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamDrwListMngtList.do" />',
		dataType: "json",
		colModel : [
				{display:"도면목록번호",			name:"drawListNumber",		width:80,		sortable:false,		align:"center"},
				{display:"도면 목록 명", 			name:"drwLstNm",			width:250,		sortable:false,		align:"center"},
				{display:"공사 명",				name:"authnm",				width:230,		sortable:false,		align:"center"},
				{display:"시공자", 				name:"cnstrtr",				width:150,		sortable:false,		align:"center"},
				{display:"도면 관리 부서 명",		name:"drwLstMngDeptNm",		width:150,		sortable:false,		align:"center"}
			],
		showTableToggleBtn: false,
		height: "auto",
		preProcess: function(module, data) {
			
			console.log(module);
			console.log(data);
			return data;
		}
	});

	this.$("#drwListMngtList").on("onItemSelected", function(event, module, row, grid, param) {
		
		/* 
		module.$("#drwListManageVO :input").val("");

		module.makeFormValues("#drwListManageVO", row);
		module._editInfoData = module.getFormValues("#drwListManageVO", row);
		module._editInfoRow = module.$("#drwListMngtList").selectedRowIds()[0];
		module.$("#cmd").val("modify");
		module.$("#drwLstRegistYear").attr("disabled","disabled");
		var searchOpt = module.makeFormArgs("#drwListManageVO");
        module.$("#drwListPhotoList").flexOptions({params:searchOpt}).flexReload();
         */
	});

	this.$("#drwListMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#drwListMngtListTab").tabs("option", {active: 1});		// 탭을 전환 한다.
	});

	// 사진 정보 속성이 변경 된 경우 이벤트 실행
// 	this.$(".photoEditItem").on("change", {module: this}, function(event) {

// 		var m = event.data.module;

// 		if(m._editPhotoRow == null) return;
// 		if(m._editPhotoData == null) return;

// 		if(m._editPhotoData._updt == null || m._editPhotoData._updt == "") m._editPhotoData._updt = "U";
// 		else m._editPhotoData._updt = "I";

// 		if(m.$("#photoSj") == event.target){
// 			m._editPhotoData.photoSj = $(event.target).val();
// 		}else{
// 			var dtStr = m.$("#shotDt").val()+" "+m.$("#shotTime").val();
// 			m._editPhotoData.shotDt = dtStr;
// 		}
// 	});

	this.$("#drwListPhotoList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamDrwListPhotoList.do" />',
		dataType: "json",
		colModel : [
				{display:"도면 자료 코드",		name:"drwDtaCd",			width:150,		sortable:false,		align:"center"},
				{display:"년도",		name:"drwLstRegistYear",			width:150,		sortable:false,		align:"center"},
				{display:"순번",		name:"drwLstSeq",			width:150,		sortable:false,		align:"center"},
				{display:"도면 명", 				name:"drwNm",				width:160,		sortable:false,		align:"center"},
				{display:"도면 파일명",			name:"drwFilenmLogic",		width:120,		sortable:false,		align:"center"},
				{display:"도면 구분", 			name:"drwSeCd",				width:100,		sortable:false,		align:"center"},
				{display:"도면 번호",				name:"drwNo",				width:100,		sortable:false,		align:"center"},
				{display:"도면 작성 일자",		name:"drwWritngDt",			width:100,		sortable:false,		align:"center"},
				{display:"도면 변경일",			name:"drwChangedt",			width:100,		sortable:false,		align:"center"}
			],
		usepager: true,
		useRp: true,
		showTableToggleBtn: false,
		height: "150",
		preProcess: function(module, data) {
			console.log(module);
			module._DrwItem=module;
			console.log(data);
			return data;
		}
	});

	this.$("#drwListPhotoList").on("onItemSelected", function(event, module, row, grid, param) {

		module.$("#drwListPhotoForm :input").val("");
		module.makeFormValues("#drwListPhotoForm", row);
		module._editDataFile = module.getFormValues("#drwListPhotoForm", row);
		module._editRowFile = module.$("#drwListPhotoList").selectedRowIds()[0];

	/*	if(row.filenmPhysicl != null || row.filenmPhysicl != "") {

			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm = row["filenmPhysicl"];
			var ext = filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();

			if(ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif"){

				$imgURL = module.getImageUrl(filenm);
				module.$("#previewImage").fadeIn(400, function() {
			    	module.$("#previewImage").attr("src", $imgURL);
			    });
			}else{
				module.$("#previewImage").attr(src, "#");
			}
		}
	*/
	});
	
	this.$(".photoEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyPhotoChanged(event.target);
	});
};

GamFcltyDrwListMngtModule.prototype.applyPhotoChanged = function(target) {
	var changed=false;
	var row={};
	// console.log("change event occur");

	var selectRow = this.$('#drwListPhotoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#drwDtaCd').is(target)) {
			row['drwDtaCd'] = $(target).val();
			changed=true;
		}
		if(this.$('#drwNo').is(target)) {
			row['drwNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#drwSeCd').is(target)) {
			row['drwSeCd'] = $(target).val();
			changed=true;
		}
		if(this.$('#drwNm').is(target)) {
			row['drwNm'] = $(target).val();
			changed=true;
		}
		if(this.$('#drwWritngDt').is(target)) {
			row['drwWritngDt'] = $(target).val();
			changed=true;
		}
		if(this.$('#drwChangedt').is(target)) {
			row['drwChangedt'] = $(target).val();
			changed=true;
		}
		if(this.$('#drwChangeDtls').is(target)) {
			row['drwChangeDtls'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#drwListPhotoList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#drwListPhotoList').flexUpdateRow(rowid, row);
	}
};


/**
 * 정의 된 버튼 클릭 시
 */
GamFcltyDrwListMngtModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			var searchOpt=this.makeFormArgs("#drwListForm");
		 	this.$("#drwListMngtListTab").tabs("option", {active: 0});
		 	this.$("#drwListMngtList").flexOptions({params:searchOpt}).flexReload();
		break;

		// 추가
		case "addBtn":
			this.$("#drwListMngtListTab").tabs("option", {active: 1});
			this.$("#drwListManageVO :input").val("");
			this.$("#cmd").val("insert");
			this.$("#drwLstRegistYear").removeAttr("disabled");
		break;

		// 저장
		case "saveBtn":

			if(!validateGamDrwListCode(this.$("#drwListManageVO")[0])) return;

		 	var inputVO=[{}];

		 	if(this._deleteDataFileList == undefined) this._deleteDataFileList=[];

			inputVO[inputVO.length]={name: "insertFileList", value: JSON.stringify(this.$("#drwListPhotoList").selectFilterData([{col: "_updtId", filter: "I"}])) };
			inputVO[inputVO.length]={name: "updateFileList", value :JSON.stringify(this.$("#drwListPhotoList").selectFilterData([{col: "_updtId", filter: "U"}])) };
			inputVO[inputVO.length]={name: "deleteFileList", value: JSON.stringify(this._deleteDataFileList) };
			inputVO[inputVO.length]={name: "form", value: JSON.stringify(this.getFormValues("#drwListManageVO", {})) };

		 	if(this.$("#cmd").val() == "insert") {
			 	this.doAction('<c:url value="/fclty/gamDrwInfoListMngInsert.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#drwListForm");
						module.$("#drwListMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#drwListMngtListTab").tabs("option", {active: 0});
						module.$("#drwListManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/fclty/gamDrwListMngUpdate.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#drwListForm");
						module.$("#drwListMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#drwListMngtListTab").tabs("option", {active: 0});
						module.$("#drwListManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;

		// 삭제
		case "deleteBtn":
			var row = this.$("#drwListMngtList").selectedRows();

			if(row.length == "0"){
				alert("삭제할 도면을 선택 하십시오.");
				return;
			}

			if(confirm("선택 한 건축 시설을 삭제하시겠습니까?")){

				var inputVO = {drwLstRegistYear:row[0]["drwLstRegistYear"], drwLstSeq:row[0]["drwLstSeq"]};
			 	this.doAction('<c:url value="/fclty/gamDrwListMngDelete.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#drwListForm");
						module.$("#drwListMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#drwListMngtListTab").tabs("option", {active: 0});
						module.$("#drwListManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;

		// 파일 업로드
		case "btnUploadFile":

			// 사진을 업로드하고 업로드한 사진 목록을 result에 어레이로 리턴한다.
			this.uploadFile("uploadPhoto", function(module, result) {

				var userid = "admin";

				$.each(result, function(){
					module.$("#drwListPhotoList").flexAddRow({_updtId:"I", drwDtaCd: "", drwNm: "", drwFilenmLogic: this.logicalFileNm, drwFilenmPhysicl: this.physcalFileNm, drwSeCd: "", drwNo: "", drwWritngDt:"", drwChangedt:"", drwChangeDtls : "",drwLstRegistYear:module._DrwItem.drwLstRegistYear, drwLstSeq:module._DrwItem.drwLstSeq});
				});
			}, "도면파일 업로드");

		break;

		case "btnRemoveFile":
			this.removeGisAssetPhotoItem();
		break;

		case 'btnSaveFile':	// 저장
			if( confirm("사진 목록을 저장하시겠습니까?") ) {
			    // 변경된 자료를 저장한다.
			    var inputVO=[];
			    inputVO[inputVO.length]={name: 'updateList', value :JSON.stringify(this.$('#drwListPhotoList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

			    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#drwListPhotoList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

			    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataFileList) };

			    this.doAction('<c:url value="/fclty/mergeDrwInfoPhotoMngt.do" />', inputVO, function(module, result) {
			        if(result.resultCode == 0){
				    	module.loadPhotoList();
			        }
			        alert(result.resultMsg);
			    });
			}
			break;

		// 파일 적용
		
		/*
		case "btnApplyPhotoData":

			if(this._editDataFile == null){
				alert("적용할 파일이 없습니다.");
				return;
			}

			if(!validateGamDrwListPhoto(this.$("#drwListPhotoForm")[0])) return;

            this._editDataFile = this.getFormValues("#drwListPhotoForm", this._editDataFile);
            if(this._editRowFile != null) {
                if(this._editDataFile._updtId != "I") this._editDataFile._updtId = "U";
                this.$("#drwListPhotoList").flexUpdateRow(this._editRowFile, this._editDataFile);
                this._editRowFile = null;
            }else{
                this.$("#drwListPhotoList").flexAddRow(this._editDataFile);
            }

            this.$("#drwListPhotoForm :input").val("");
            this._editDataFile = null;

		break;
		*/
		
	}
};
GamFcltyDrwListMngtModule.prototype.removeGisAssetPhotoItem = function() {
	var rows = this.$("#drwListPhotoList").selectedRows();

    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }

    if(this.$("#drwListPhotoList").selectedRowIds().length>0) {
    	for(var i=this.$("#drwListPhotoList").selectedRowIds().length-1; i>=0; i--) {

    		var row = this.$("#drwListPhotoList").flexGetRow(this.$("#drwListPhotoList").selectedRowIds()[i]);

            if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataFileList[this._deleteDataFileList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#drwListPhotoList").flexRemoveRow(this.$("#drwListPhotoList").selectedRowIds()[i]);

        	this._edited=true;
		}

    	this.$("#previewImage").attr("src","");
    	alert("삭제되었습니다.");
	}

    this.$("#drwListPhotoForm").find(":input").val("");
    this._editDataFile = null;
};

GamFcltyDrwListMngtModule.prototype.clearCodePage = function() {
	this.$('#drwListManageVO :input').val('');
};

GamFcltyDrwListMngtModule.prototype.clearPhotoPage = function() {
	this.$('#drwListPhotoList').flexEmptyData();
	this.$('#drwListPhotoForm :input').val('');
};

GamFcltyDrwListMngtModule.prototype.loadPhotoList = function() {
	var row = this.$('#drwListMngtList').selectedRows();
	if(row.length <= 0) {
		// console.log(row.length);
 		this.clearPhotoPage();
		return;
	}
	row=row[0];
	var searchOpt = [
	                { name: 'drwLstRegistYear', value: row['drwLstRegistYear'] },
					{ name: 'drwLstSeq', value: row['drwLstSeq'] }
	              ];
	this.clearPhotoPage();

 	this.$('#drwListPhotoList').flexOptions({params:searchOpt}).flexReload();
};
/**
 * 탭 변경시 실행 이벤트
 */
 GamFcltyDrwListMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		if(this._cmd!="insert") {
			var row = this.$('#drwListMngtList').selectedRows();
			if(row.length <= 0) {
		 		this.clearCodePage();
		 		if(this._params.action!=null || this._params.action=='prtFcltyMngt') {
		 			var prtFclty = [
									{ name: 'drwLstRegistYear', value: row['drwLstRegistYear'] },
									{ name: 'drwLstSeq', value: row['drwLstSeq'] }
		 			              ];
		 	     	 	this.doAction('<c:url value="/fclty/gamDrwFcltyDetail.do" />', prtFclty, function(module, result) {
		 	     	 		if(result.resultCode == "0"){
		 	     	 			module.clearCodePage();
		 	     	 			module._fcltyItem=result.result;
		 	     	 			module.makeFormValues('#fcltyManageVO', result.result);	// 결과값을 채운다.
		 	     	 		}
		 	     	 		else {
		 	     	 			alert(result.resultMsg);
		 	     	 		}
		 	     	 	});
	 	     	 	}
				return;
			}
			row=row[0];
			console.log(row);
			var drwFclty = [
			                { name: 'drwLstRegistYear', value: row['drwLstRegistYear'] },
			                { name: 'drwLstSeq', value: row['drwLstSeq'] }
			              ];
	     	 	this.doAction('<c:url value="/fclty/gamDrwFcltyDetail.do" />', drwFclty, function(module, result) {
	     	 		if(result.resultCode == "0"){
	     	 			module.clearCodePage();
	     	 			module._DrwItem=result.result;
	     	 			module.makeFormValues('#drwListManageVO', result.result);	// 결과값을 채운다.
	     	 		}
	     	 		else {
	     	 			alert(result.resultMsg);
	     	 		}
	     	 	});
		}
		else if(this._cmd=="insert") {
	 			this.clearCodePage();
		}
		break;
	case "tabs3":
		this._deleteDataFileList = [];    // 삭제 목록 초기화
		if(this._cmd!="insert") {
			var row = this.$('#drwListMngtList').selectedRows();
			if(row.length <= 0) {
		 		this.clearPhotoPage();
		 		if(this._params.action!=null || this._params.action=='prtFcltyMngt') {
		 			var drwFclty = [
		 			                 { name: 'drwLstRegistYear', value: row['drwLstRegistYear'] },
			              		     { name: 'drwLstSeq', value: row['drwLstSeq'] }
		 			              ];
		 		 	this.$('#drwListPhotoList').flexOptions({params:drwFclty}).flexReload();
	 	     	 	}
				return;
			}
					row=row[0];
					var drwFclty = [
			              		     { name: 'drwLstRegistYear', value: row['drwLstRegistYear'] },
			              		     { name: 'drwLstSeq', value: row['drwLstSeq'] }
			              ]; 
	     	 	this.doAction('<c:url value="/fclty/gamDrwFcltyDetail.do" />', drwFclty, function(module, result) {
	     	 		if(result.resultCode == "0"){
	     	 			module.clearPhotoPage();
	     	 			module._fcltyItem=result.result;
	     	 			module.makeFormValues('#drwListManageVO', result.result);	// 결과값을 채운다.
	     	 		}
	     	 		else {
	     	 			alert(result.resultMsg);
	     	 		}
	     	 	});
	     	 	this.loadPhotoList();
		}
		else if(this._cmd=="insert") {
	 			this.clearPhotoPage();
		}
		//this.loadPhotoList();
		break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyDrwListMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="drwListForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>도면 목록 등록 년도</th>
							<td><input id="searchDrwLstRegistYear" type="text" size="4" maxlength="4" title="등록년도" />-<input id="searchDrwLstSeq" type="text" size="4" maxlength="4" title="등록순번" /></td>
							<th>도면 명</th>
							<td><input id="searchDrwLstNm" type="text" size="40" title="도면명" /></td>
							<td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>공사 명</th>
							<td><input id="searchAuthnm" type="text" size="20" maxlength="4" title="공사 명" /></td>
							<th>관리부서</th>
							<td><input id="searchDeptCd" type="text" class="ygpaDeptSelect" data-default-prompt="전체"/></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="drwListMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">도면목록</a></li>
				<li><a href="#tabs2" class="emdTab">도면목록상세</a></li>
				<li><a href="#tabs3" class="emdTab">도면파일</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage fillHeight">
				<table id="drwListMngtList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="showMap">맵조회</button>
					<button id="addBtn">추가</button>
					<button id="deleteBtn">삭제</button>
				</div>
			</div>

			<!-- 도면시설 상세 -->
			<div id="tabs2" class="emdTabPage" style="height:300px; overflow: scroll;">
				<form id="drwListManageVO" style="height:350px;">
					<input type="hidden" id="cmd" />
					<input type="hidden" id="laCrdnt" />
					<input type="hidden" id="loCrdnt" />
					<table class="searchPanel">
						<tr>
							<th width="150" height="23" class="required_text">도면 목록 등록 년도<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td colspan="3"><input type="text" size="4" id="drwLstRegistYear" maxlength="4" />-<input type="text" size="4" id="drwLstSeq" disabled="disabled"/><p>등록년도를 입력하면 순번이 자동으로 부여됩니다.</p></td>
						</tr>
						<tr>
							<th height="23" class="required_text">도면 목록 명</th>
							<td colspan="3"><input type="text" size="40" id="drwLstNm" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면 구분</th>
							<td colspan="3"><input type="text" id="drwLstGisCd" class="ygpaCmmnCd" data-code-id="GAM047" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">공사명</th>
							<td colspan="3"><input type="text" size="40" id="authnm" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">시공자</th>
							<td colspan="3"><input type="text" size="40" id="cnstrtr" maxlength="30" /></td>
						</tr>
						<tr>
							<th height="23" class="required_text">제출자</th>
							<td><input type="text" size="30" id="sbmNm" maxlength="40"/></td>
							<th width="20%" height="23">검토자</th>
							<td><input type="text" size="30" id="exmNm" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면 목록 관리 부서 코드</th>
							<td colspan="3"><input type="text" id="drwLstMngDeptCd" maxlength="20" class="ygpaDeptSelect" /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="btnEditMap">도면위치등록</button>
					<button id="btnRemoveMap">도면위치삭제</button>
					<button id="btnGotoMap">도면위치조회</button>
					<button id="deleteBtn">도면목록삭제</button>
					<button id="saveBtn">도면목록저장</button>
				</div>
			</div>

			<!-- 도면시설 사진 -->
			<div id="tabs3" class="emdTabPage" style="height:300px; overflow: scroll;">
				<table id="drwListPhotoList" style="display:none;"></table>
				<div class="emdControlPanel">
					<button id="btnUploadFile">업로드</button>
					<button id="btnDownloadFile">다운로드</button>
					<button id="btnRemoveFile">삭제</button>
					<button id="btnSaveFile">저장</button>
				</div>
				<form id="drwListPhotoForm">
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23">도면 자료 코드</th>
							<td colspan="3"><input type="text" size="40" id="drwDtaCd" disabled="disabled" maxlength="20" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면번호</th>
							<td><input type="text" size="8" id="drwNo" maxlength="8"  class="photoEditItem"/></td>
							<th width="20%" height="23" class="required_text">도면 구분</th>
							<td><input type="text" id="drwSeCd" class="ygpaCmmnCd photoEditItem" data-code-id="GAM048" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면명</th>
							<td colspan="3"><input type="text" size="40" id="drwNm" maxlength="40" class="photoEditItem"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면작성일자</th>
							<td><input type="text" id="drwWritngDt" class="emdcal photoEditItem"/></td>
							<th width="20%" height="23" class="required_text">도면 변경일</th>
							<td><input type="text" id="drwChangedt" class="emdcal photoEditItem"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면 변경 내역</th>
							<td colspan="3"><input type="text" size="60" id="drwChangeDtls" maxlength="200" class="photoEditItem"/></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
<!-- 					<button id="btnApplyPhotoData">적용</button> -->
				</div>
			</div>
		</div>
	</div>
</div>