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

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#drwListMngtList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamDrwListMngtList.do" />',
		dataType: "json",
		colModel : [
				{display:"도면목록번호",	name:"drawListNumber",	width:80,	sortable:false,	align:"center"},
				{display:"도면목록명", 		name:"drwLstNm",		width:210,	sortable:false,	align:"left"},
				{display:"도면목록구분", 	name:"drwLstGisCdNm",	width:80,	sortable:false,	align:"center"},
				{display:"공사명",		name:"authnm",			width:220,	sortable:false,	align:"left"},
				{display:"시공자", 		name:"cnstrtr",			width:150,	sortable:false,	align:"left"},
				{display:"도면관리부서",	name:"drwLstMngDeptNm",	width:100,	sortable:false,	align:"left"},
				{display:"제출자", 		name:"sbmNm",			width:150,	sortable:false,	align:"left"},
				{display:"검토자", 		name:"exmNm",			width:150,	sortable:false,	align:"left"},
				{display:"도면구분", 		name:"drwLstSeCdNm",	width:80,	sortable:false,	align:"center"},
				{display:"등록자", 		name:"regUsr",			width:100,	sortable:false,	align:"left"},
				{display:"등록일시", 		name:"registDt",		width:120,	sortable:false,	align:"center"}
			],
		showTableToggleBtn: false,
		height: "auto",
		preProcess: function(module, data) {
			return data;
		}
	});

	this.$("#drwListMngtList").on("onItemSelected", function(event, module, row, grid, param) {
		module._cmd="modify";
	});

	this.$("#drwListMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module._cmd="modify";
		module.$("#drwListMngtListTab").tabs("option", {active: 1});		// 탭을 전환 한다.
	});

	this.$("#drwListPhotoList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamDrwListPhotoList.do" />',
		dataType: "json",
		colModel : [
				{display:"도면자료코드",	name:"drwDtaCd",		width:80,	sortable:false,	align:"center"},
				{display:"도면명", 		name:"drwNm",			width:200,	sortable:false,	align:"left"},
				{display:"도면파일명",		name:"drwFilenmLogic",	width:200,	sortable:false,	align:"left"},
				{display:"도면구분", 		name:"drwSeCd",		width:80,	sortable:false,	align:"center"},
				{display:"도면번호",		name:"drwNo",			width:50,	sortable:false,	align:"center"},
				{display:"축적",			name:"scl",				width:80,	sortable:false,	align:"left"},
				{display:"도면작성일",		name:"drwWritngDt",		width:70,	sortable:false,	align:"center"},
				{display:"도면변경일",		name:"drwChangedt",		width:70,	sortable:false,	align:"center"},
				{display:"도면변경내역",	name:"drwChangeDtls",	width:200,	sortable:false,	align:"left"},
				{display:"등록자", 		name:"regUsr",			width:100,	sortable:false,	align:"left"},
				{display:"등록일시", 		name:"registDt",		width:120,	sortable:false,	align:"center"}
			],
		usepager: true,
		useRp: true,
		showTableToggleBtn: false,
		height: "150",
		preProcess: function(module, data) {
			return data;
		}
	});

	this.$("#drwListPhotoList").on("onItemSelected", function(event, module, row, grid, param) {

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


	this.$("#drwListManageVO :input:visible").bind("change keyup", {module: this}, function(event) {
		event.data.module._edited=true;
	});

	this.$(".photoEditItem").bind("keyup change", {module: this}, function(event) {
		event.data.module.applyPhotoChanged(event.target);
	});
};

GamFcltyDrwListMngtModule.prototype.loadList = function() {
	var searchOpt=this.makeFormArgs("#drwListForm");
 	this.$("#drwListMngtList").flexOptions({params:searchOpt}).flexReload();
	this.$("#drwListMngtListTab").tabs("option", {active: 0});
// 	console.log("list loaded");
}

GamFcltyDrwListMngtModule.prototype.loadDetailData = function() {
	this._edited=false;
	this.clearCodePage();
	this.clearPhotoPage();
	if(this._cmd!="insert") {
		var row = this.$('#drwListMngtList').selectedRows()[0];

		this._loadDrwItem = row;

		var drwFclty = [
           { name: 'drwLstRegistYear', value: row['drwLstRegistYear'] },
           { name: 'drwLstSeq', value: row['drwLstSeq'] }
         ];

	 	this.doAction('<c:url value="/fclty/selectDrwListDetail.do" />', drwFclty, function(module, result) {
	 		if(result.resultCode == "0"){
	 			this._deleteDataFileList = [];    // 삭제 목록 초기화
	 			module.makeFormValues('#drwListManageVO', result.resultMaster);	// 결과값을 채운다.
	 			module.$("#drwListPhotoList").flexEmptyData();
	 			module.$("#drwListPhotoList").flexAddData({resultList: result.resultDetail});
	 		}
	 		else {
	 			alert(result.resultMsg);
	 		}
	 	});
	}
//	console.log("detail data loaded");
};

GamFcltyDrwListMngtModule.prototype.applyPhotoChanged = function(target) {
	if(this._editDataFile===undefined) return;
	if(this._editDataFile._updtId===undefined) this._editDataFile._updtId;
	if(this._editDataFile._updtId!='I') this._editDataFile._updtId='U';

	this._editDataFile=this.getFormValues('#drwListPhotoForm', this._editDataFile);

	this.edited=true;
	this.$('#drwListPhotoList').flexUpdateRow(this._editRowFile, this._editDataFile);
};

/**
 * 정의 된 버튼 클릭 시
 */
GamFcltyDrwListMngtModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			this.loadList();
		break;

		// 추가
		case "addBtn":
			this._cmd="insert";
			this.$("#drwListMngtListTab").tabs("option", {active: 1});
			this.$("#drwListManageVO :input").val("");
			this.$("#drwLstRegistYear").removeAttr("disabled");
		break;

		// 저장
		case "btnSaveFile":
		case "btnSaveFile2":
			if(!validateGamDrwListCode(this.$("#drwListManageVO")[0])) {
				this.$("#drwListMngtListTab").tabs("option", {active: 1});
				return;
			}

		 	var inputVO=[{}];

		 	if(this._deleteDataFileList == undefined) this._deleteDataFileList=[];

		 	this.$("#cmd").val(this._cmd);

			inputVO[inputVO.length]={name: "drwListMaster", value: JSON.stringify(this.getFormValues("#drwListManageVO", {})) };

		    inputVO[inputVO.length]={name: 'updateList', value :JSON.stringify(this.$('#drwListPhotoList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
		    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#drwListPhotoList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
		    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataFileList) };

		    this.doAction('<c:url value="/fclty/mergeDrwInfoMngt.do" />', inputVO, function(module, result) {
		        if(result.resultCode == 0){
			    	module.loadDetailData();
		        }
		        alert(result.resultMsg);
		    });
		break;
		// 삭제
		case "btnDeleteFile":
			if(this._cmd=="insert") {
				this.$("#drwListMngtListTab").tabs("option", {active: 0});
			}
			var row = this.$("#drwListMngtList").selectedRows();

			if(row.length == "0"){
				alert("삭제할 도면을 선택 하십시오.");
				return;
			}

			if(confirm("선택 한 도면 목록을 삭제하시겠습니까?")){

				var inputVO = {drwLstRegistYear:row[0]["drwLstRegistYear"], drwLstSeq:row[0]["drwLstSeq"]};
			 	this.doAction('<c:url value="/fclty/deleteDrwInfoMngt.do" />', inputVO, function(module, result) {
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
					module.$("#drwListPhotoList").flexAddRow({_updtId:"I", drwDtaCd: "", drwNm: "", drwFilenmLogic: this.logicalFileNm, drwFilenmPhysicl: this.physcalFileNm, drwSeCd: "", drwNo: "", drwWritngDt:"", drwChangedt:"", drwChangeDtls : "",drwLstRegistYear:module._loadDrwItem.drwLstRegistYear, drwLstSeq:module._loadDrwItem.drwLstSeq});
				});
			}, "도면파일 업로드");

		break;

		case "btnRemoveFile":
			this.removeGisAssetPhotoItem();
		case 'btnDownloadFile':
			var selectRow = this.$('#drwListPhotoList').selectedRows();
			if(selectRow.length > 0) {
				var row=selectRow[0];
				this.downloadFile(row["drwFilenmPhysicl"], row["drwFilenmLogic"]);
			}
		break;

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

    		if(this._deleteDataFileList==null) this._deleteDataFileList=[];

            if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataFileList[this._deleteDataFileList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#drwListPhotoList").flexRemoveRow(this.$("#drwListPhotoList").selectedRowIds()[i]);

        	this._edited=true;
		}

    	this.$("#previewImage").attr("src","");
	}

    this.$("#drwListPhotoForm").find(":input").val("");
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

/*
 * 탭 변경 전 호출 함수
 */
GamFcltyDrwListMngtModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
		if(this._edited) {
			if(!confirm('저장하지 않은 편집 항목이 있습니다. 탭을 이동하면 편집 한 항목이 저장 되지 않습니다 계속 하시겠습니까?')) {
				return false;
			}
		}
		break;
	case "tabs2":
	case "tabs3":
		var row = this.$('#drwListMngtList').selectedRowIds();
		if(row.length!=1 && this._cmd!="insert") {
			alert('도면 목록을 선택 하세요');
			return false;
		}
		break;
	}
	return true;
};

/**
 * 탭 변경시 실행 이벤트
 */
 GamFcltyDrwListMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
	case "tabs3":
		if(oldTabId!="tabs1") return;	// 이전 탭이 tabs1 이 아니면 로딩 안함
		this.loadDetailData();
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
							<th>등록년도/순번</th>
							<td>
								<input id="searchDrwLstRegistYear" type="text" size="4" maxlength="4" title="등록년도" />-
								<input id="searchDrwLstSeq" type="text" size="4" maxlength="4" title="등록순번" />
							</td>
							<th>도면명</th>
							<td><input id="searchDrwLstNm" type="text" size="40" title="도면명" /></td>
							<td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>관리부서</th>
							<td><input id="searchDeptCd" type="text" class="ygpaDeptSelect" data-default-prompt="전체"/></td>
							<th>공사명</th>
							<td><input id="searchAuthnm" type="text" size="40" title="공사명" /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="drwListMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
			<ul>
				<li><a href="#tabs1" class="emdTab">도면목록</a></li>
				<li><a href="#tabs2" class="emdTab">도면목록상세</a></li>
				<li><a href="#tabs3" class="emdTab">도면파일</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage fillHeight">
				<table id="drwListMngtList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
				<!--
					<button id="loadMap" data-flexi-grid="drwListMngtList">맵조회</button>
					 -->
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
					<table class="editForm">
						<tr>
							<th width="150" height="23" class="required_text">도면목록 등록년도<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td colspan="3"><input type="text" size="4" id="drwLstRegistYear" maxlength="4" />-<input type="text" size="4" id="drwLstSeq" disabled="disabled"/>     (등록년도를 입력하면 순번이 자동으로 부여됩니다.)</td>
						</tr>
						<tr>
							<th height="23" class="required_text">도면목록명</th>
							<td colspan="3"><input type="text" size="120" id="drwLstNm" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면목록구분</th>
							<td colspan="3"><input type="text" id="drwLstGisCd" class="ygpaCmmnCd" data-code-id="GAM047" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">공사명</th>
							<td colspan="3"><input type="text" size="120" id="authnm" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">시공자</th>
							<td colspan="3"><input type="text" size="120" id="cnstrtr" maxlength="30" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">제출자</th>
							<td colspan="3"><input type="text" size="120" id="sbmNm" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">검토자</th>
							<td colspan="3"><input type="text" size="120" id="exmNm" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면목록 관리부서</th>
							<td colspan="3"><input type="text" id="drwLstMngDeptCd" maxlength="20" class="ygpaDeptSelect" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면구분</th>
							<td colspan="3"><input type="text" id="drwLstSeCd" class="ygpaCmmnCd" data-code-id="GAM048" /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
				<!--
					<button id="btnEditMap">위치등록</button>
					<button id="btnRemoveMap">위치삭제</button>
					<button id="btnGotoMap">위치조회</button>
					 -->
					<button id="btnDeleteFile" class="buttonDelete">삭제</button>
					<button id="btnSaveFile" class="buttonSave">저장</button>
				</div>
			</div>

			<!-- 도면시설 사진 -->
			<div id="tabs3" class="emdTabPage" style="height:300px; overflow: scroll;">
				<table id="drwListPhotoList" style="display:none;"></table>
				<div class="emdControlPanel">
					<button id="btnUploadFile">업로드</button>
					<button id="btnDownloadFile">다운로드</button>
					<button id="btnRemoveFile">삭제</button>
					<button id="btnSaveFile2">저장</button>
				</div>
				<form id="drwListPhotoForm">
					<table class="searchPanel editForm">
						<tr>
							<th width="20%" height="23">도면자료코드</th>
							<td><input type="text" size="40" id="drwDtaCd" disabled="disabled" maxlength="20" /></td>
							<th width="20%" height="23" class="required_text">도면번호</th>
							<td><input type="text" size="8" id="drwNo" maxlength="8" class="photoEditItem"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면구분</th>
							<td><input type="text" id="drwSeCd" class="ygpaCmmnCd photoEditItem" data-code-id="GAM048" /></td>
							<th width="20%" height="23" class="required_text">축적</th>
							<td><input type="text" size="45" id="scl" maxlength="20" class="photoEditItem"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면명</th>
							<td colspan="3"><input type="text" size="120" id="drwNm" maxlength="40" class="photoEditItem"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면작성일자</th>
							<td><input type="text" id="drwWritngDt" class="emdcal photoEditItem" /></td>
							<th width="20%" height="23" class="required_text">도면변경일자</th>
							<td><input type="text" id="drwChangedt" class="emdcal photoEditItem" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면변경내역</th>
							<td colspan="3"><input type="text" size="120" id="drwChangeDtls" maxlength="200"  class="photoEditItem"/></td>
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