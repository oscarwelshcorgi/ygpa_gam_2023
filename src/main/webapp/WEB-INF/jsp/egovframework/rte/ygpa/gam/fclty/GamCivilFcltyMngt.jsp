<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamCivilFcltyMngt.jsp
  * @Description : 토목시설사용목록관리
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
<validator:javascript formName="gamFcltyCode" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamFcltyPhoto" staticJavascript="false" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyMngtModule() {}

GamFcltyMngtModule.prototype = new EmdModule(840,510);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyMngtModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#fcltyMngtList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamCivilFcltyMngtList.do" />',
		dataType: "json",
		colModel : [
				{display:"항코드",		 			name:"gisAssetsPrtAtCode",	width:40,		sortable:false,		align:"center"},
				{display:"항이름",		 			name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
				{display:"자산코드",		 			name:"gisAssetsDisplay",	width:60,		sortable:false,		align:"center"},
				{display:"토목시설코드", 				name:"gisPrtFcltyDisplay",	width:80,		sortable:false,		align:"center"},
				{display:"시설명",	 				name:"prtFcltyNm",			width:100,		sortable:false,		align:"center"},
				{display:"위치",		 				name:"gisAssetsLocCd",		width:40,		sortable:false,		align:"center"},
				{display:"구조",		 				name:"prtFcltyStndrd",		width:100,		sortable:false,		align:"center"},
				{display:"시설단위", 	 			name:"prtFcltyUnit",		width:80,		sortable:false,		align:"center"},
				{display:"관리업체 명", 	 			name:"prtFcltyMngEntrpsNm",	width:80,		sortable:false,		align:"center"},
				{display:"관리업체 코드",	 			name:"prtFcltyMngEntrpsCd",	width:80,		sortable:false,		align:"center"},
				{display:"설치일자",					name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"},
				{display:"변경일자",					name:"prtFcltyChangeDt",	width:80,		sortable:false,		align:"center"}
			],
		height: "auto"
	});
	
	this.$("#fcltyMngtList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#fcltyManageVO :input").val("");
		module.makeFormValues("#fcltyManageVO", row);
		module.getFormValues("#fcltyManageVO", row);
		module.$("#fcltyMngtList").selectedRowIds()[0];
		module.$("#cmd").val("modify");
		module.$("#gisCodePopupBtn").hide();
		module.$(".selectedGAM005").hide();
		
        var searchOpt=module.makeFormArgs("#fcltyManageVO");
        module.$("#fcltyPhotoList").flexOptions({params:searchOpt}).flexReload();
	});
	
	this.$("#selectedGAM005").on("change", {module: this}, function(event) {
		event.data.module.$("#gisPrtFcltyCd").val($(this).val());
	});

	this.$("#fcltyMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#fcltyMngtListTab").tabs("option", {active: 1});		// 탭을 전환 한다.
	});
	
	// 사진 정보 속성이 변경 된 경우 이벤트 실행
	this.$(".photoEditItem").on("change", {module: this}, function(event) {
		
		var m = event.data.module;
		
		if(m._editPhotoRow == null) return;
		if(m._editPhotoData == null) return;

		if(m._editPhotoData._updt == null || m._editPhotoData._updt == "") m._editPhotoData._updt = "U";
		else m._editPhotoData._updt = "I";

		if(m.$("#photoSj")==event.target){
			m._editPhotoData.photoSj = $(event.target).val();
		}else{
			var dtStr = m.$("#shotDt").val()+" "+m.$("#shotTime").val();
			m._editPhotoData.shotDt = dtStr;
		}
	});
	
	this.$("#fcltyPhotoList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamCivilFcltyPhotoList.do"/>',
		dataType: 'json',
		colModel : [
				{display:"사진 순번",			name:"prtFcltyPhotoSeq",	width:80,		sortable:true,		align:"center"},
				{display:"사진 제목",			name:"photoSj",				width:300,		sortable:true,		align:"center"},
				{display:"파일명",			name:"filenmLogic",			width:200,		sortable:true,		align:"left"},
				{display:"파일 설명",			name:"photoDesc",			width:200,		sortable:true,		align:"left"},
				{display:"촬영 일시",			name:"shotDt",				width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});
	
	this.$("#fcltyPhotoList").on("onItemSelected", function(event, module, row, grid, param) {
		
		module.makeFormValues("#fcltyGisPhotoForm", row);
		module._editDataFile = module.getFormValues("#fcltyGisPhotoForm", row);
		module._editRowFile = module.$("#fcltyPhotoList").selectedRowIds()[0];

		if(row.filenmPhysicl != null || row.filenmPhysicl != "") {
			
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
	});
};


/**
 * 정의 된 버튼 클릭 시
 */
GamFcltyMngtModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case "searchBtn":
			var searchOpt=this.makeFormArgs("#fcltyForm");
		 	this.$("#fcltyMngtListTab").tabs("option", {active: 0});
		 	this.$("#fcltyMngtList").flexOptions({params:searchOpt}).flexReload();
		break;
		
		// 추가
		case "addBtn":
			this.$("#fcltyMngtListTab").tabs("option", {active: 1});
			this.$("#fcltyManageVO :input").val("");
			this.$("#cmd").val("insert");
			this.$(".selectedGAM005").show();
			this.$("#gisCodePopupBtn").show();
		break;

		// 자산코드 팝업
		case "gisCodePopupBtn":
			this.doExecuteDialog("searchGisCodePopup", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;

		// 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisCodePopup2", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;

		// 업체조회 팝업
		case "searchEntrpsCdBtn":
			this.doExecuteDialog("searchEntrpsCdPopup", "업체조회", '<c:url value="/popup/showEntrpsInfo.do"/>', {});
		break;
			
		// 저장
		case "saveBtn":

			if(!validateGamFcltyCode(this.$("#fcltyManageVO")[0])) return;
			
		 	var inputVO=[{}];
		 	
		 	if(this._deleteDataFileList == undefined) this._deleteDataFileList=[];
		 	
			inputVO[inputVO.length]={name: "insertFileList", value: JSON.stringify(this.$("#fcltyPhotoList").selectFilterData([{col: "_updtId", filter: "I"}])) };
			inputVO[inputVO.length]={name: "updateFileList", value :JSON.stringify(this.$("#fcltyPhotoList").selectFilterData([{col: "_updtId", filter: "U"}])) };
			inputVO[inputVO.length]={name: "deleteFileList", value: JSON.stringify(this._deleteDataFileList) };
			inputVO[inputVO.length]={name: "form", value: JSON.stringify(this.getFormValues("#fcltyManageVO", {})) };

			// 날짜 설정
			this.$("#prtFcltyInstlDt").val(this.$("#prtFcltyInstlDt").val().replace(/\-/g,""));
			this.$("#prtFcltyChangeDt").val(this.$("#prtFcltyChangeDt").val().replace(/\-/g,""));

		 	if(this.$("#cmd").val() == "insert") {
			 	this.doAction('<c:url value="/fclty/gamCivilFcltyInsert.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#fcltyForm");
						module.$("#fcltyMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#fcltyMngtListTab").tabs("option", {active: 0}); 
						module.$("#fcltyManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			       
			 	this.doAction('<c:url value="/fclty/gamCivilFcltyUpdate.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#fcltyForm");
						module.$("#fcltyMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#fcltyMngtListTab").tabs("option", {active: 0}); 
						module.$("#fcltyManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
		
		// 삭제
		case "deleteBtn":
			var row = this.$("#fcltyMngtList").selectedRows();

			if(row.length == "0"){
				alert("삭제할 시설을 선택 하십시오.");
				return;
			}
			
			if(confirm("선택 한 토목 시설을 삭제하시겠습니까?")){
				
				var inputVO = {gisAssetsCd:row[0]["gisAssetsCd"], gisPrtFcltySeq:row[0]["gisPrtFcltySeq"], gisAssetsPrtAtCode:row[0]["gisAssetsPrtAtCode"], gisAssetsSubCd:row[0]["gisAssetsSubCd"], gisPrtFcltyCd:row[0]["gisPrtFcltyCd"]};
			 	this.doAction('<c:url value="/fclty/gamCivilFcltyDelete.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#fcltyForm");
						module.$("#fcltyMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#fcltyMngtListTab").tabs("option", {active: 0}); 
						module.$("#fcltyManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
		
		case "btnAddGisMap":
			if(this.$("#assetCodeList").selectedRowIds().length>0) {
				var row = this.$("#erpAssetCodeList").selectedRows();
				this.addGisAssetsCdMap("GAC", {"gisPrtAtCode": "620", "gisAssetsCd": "LNF", "gisAssetsSubCd": "01"});
			}
		break;

		
		// 파일 업로드
		case "btnUploadFile":
			
			// 사진을 업로드하고 업로드한 사진 목록을 result에 어레이로 리턴한다.
			this.uploadFile("uploadPhoto", function(module, result) {
				
				var userid = "admin";
				
				$.each(result, function(){
					module.$("#fcltyPhotoList").flexAddRow({_updtId:'I', prtFcltyPhotoSeq: "", photoSj: "", filenmLogic: this.logicalFileNm, filenmPhysicl: this.physcalFileNm, shotDt: "", photoDesc : ""});
				});
			}, "시설사진 업로드");
			
		break;
		
		case "btnRemoveFile":
        	
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
				}
			}

            this.$("#fcltyGisPhotoForm").find(":input").val("");
            this._editDataFile = null;
		break;

		
		// 파일 적용
		case "btnApplyPhotoData":

			if(this.$("#filenmLogic").val() == "") {
                alert("첨부파일목록에서 선택하십시오.");
                return;
            }
			
			if(this.$("#shotDt").val() != ""){
				var tempDate = this.$("#shotDt").val().replace(/\-/g,"");
				if(tempDate.length != 8) {
	                alert("날짜 입력 형식이 잘못되었습니다.");
	                return;
	            }				
			}

			
			if(this._editDataFile == null) return;
            this._editDataFile = this.getFormValues("#fcltyGisPhotoForm", this._editDataFile);
            if(this._editRowFile != null) {
                if(this._editDataFile._updtId != "I") this._editDataFile._updtId = "U";
                this.$("#fcltyPhotoList").flexUpdateRow(this._editRowFile, this._editDataFile);
                this._editRowFile = null;
            }else{
                this.$("#fcltyPhotoList").flexAddRow(this._editDataFile);
            }

            this.$("#fcltyGisPhotoForm :input").val("");
            this._editDataFile = null;
            
		break;
	}
};


/**
 * 탭 변경시 실행 이벤트
 */
 GamFcltyMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		break;
	case "tabs3":
		this._deleteDataFileList = [];    // 삭제 목록 초기화
		break;
	}
};


/**
 * 팝업 close 이벤트
 */
GamFcltyMngtModule.prototype.onClosePopup = function(popupId, msg, value){
	
	switch(popupId){
		
		// 상세화면
		case "searchGisCodePopup":
			this.$("#gisAssetsPrtAtCode").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsSubCd").val(value["gisAssetsSubCd"]);				// GIS SUB자산코드
			this.$("#gisAssetsCd").val(value["gisAssetsCd"]);					// GIS 자산코드
			this.$("#gisAssetsNm").val(value["gisAssetsNm"]);					// GIS 자산명
			
			this.$("#gisAssetsLocplc").val(value["gisAssetsLocplc"]); 			// 소재지
			this.$("#gisAssetsLnm").val(value["gisAssetsLnm"]);					// 지번
			this.$("#gisAssetsLnmSub").val(value["gisAssetsLnmSub"]);			// 서브지번
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
	
		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			throw 0;
		break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyMngtModule();
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
								<input id="searchAssetsCd" type="text" size="3" maxlength="3" title="검색조건" disabled="disabled"/>&nbsp;-&nbsp;
								<input id="searchAssetsSubCd" type="text" size="2" maxlength="2" title="검색조건" disabled="disabled"/>
								<button id="searchPopupBtn">선택</button>
							</td>
							<th>토목시설코드</th>
							<td>
								<input id="searchFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM005" />&nbsp;-&nbsp;
								<input id="searchFcltySeq" type="text" size="4" maxlength="4" title="검색조건" />
							</td>
						</tr>
						<tr>
							<th>토목시설 명</th>
							<td colspan="5"><input id="searchKeyword" type="text" size="40" maxlength="40" title="검색조건"  /></td>
						</tr>
					</tbody>
				</table>
				<div class="emdControlPanel">
					<button id="searchBtn">조회</button>
				</div>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltyMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">토목시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">토목시설 상세</a></li>
				<li><a href="#tabs3" class="emdTab">토목시설 사진</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyMngtList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="addBtn">시설 추가</button>
					<button id="deleteBtn">시설 삭제</button>
					<button id="mapSearch">맵 조회</button>
				</div>
			</div>
			
			<!-- 토목시설 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyManageVO">
					<input type="hidden" id="cmd" />
					<!-- 신경 쓸 필요 없다 함.2014-03-07 					
					<input type="hidden" id="prtFcltyGisCd" /> -->
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산 코드</th>
							<td>
								<input type="text" size="8" id="gisAssetsCd" disabled="disabled"/>&nbsp;-&nbsp;
								<input type="text" size="6" id="gisAssetsSubCd" disabled="disabled"/>&nbsp;-&nbsp;
								<input type="text" size="8" id="gisAssetsPrtAtCode" disabled="disabled"/>
							</td>
							<td colspan="2"><button id="gisCodePopupBtn">자산코드</button></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산명</th>
							<td colspan="3"><input type="text" size="80" id="gisAssetsNm" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">소재지</th>
							<td><input id="gisAssetsLocplc" type="text" size="40" title="소재지" disabled="disabled" /></td>
							<th width="20%" height="23" class="required_text">지번</th>
							<td>
								<input id="gisAssetsLnm" type="text" size="5" title="지번 앞자리" disabled="disabled" />&nbsp;-&nbsp;
								<input id="gisAssetsLnmSub" type="text" size="4" title="지번 뒷자리" disabled="disabled" />
							</td>
						</tr>
						<tr class="selectedGAM005">
							<th width="20%" height="23" class="required_text">시설분류</th>
							<td colspan="3">
								<input class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM005" id="selectedGAM005"/>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">토목시설 코드</th>
							<td colspan="3">
								<input type="text" size="10" id="gisPrtFcltyCd" disabled="disabled" />&nbsp;-&nbsp;<input type="text" size="20" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">토목시설 명</th>
							<td colspan="3"><input type="text" size="80" id="prtFcltyNm" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">토목시설 규격</th>
							<td><input type="text" size="30" id="prtFcltyStndrd" maxlength="80" /></td>
							<th width="20%" height="23" class="required_text">단위</th>
							<td><input type="text" size="30" id="prtFcltyUnit" maxlength="10" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">관리 업체</th>
							<td>
								<input type="text" size="10" id="prtFcltyMngEntrpsCd" disabled="disabled"/>
								<input type="text" size="20" id="prtFcltyMngEntrpsNm" disabled="disabled"/>&nbsp;&nbsp;
							</td>
							<td colspan="2">
								<button id="searchEntrpsCdBtn">업체조회</button>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">설치일자</th>
							<td colspan="3"><input id="prtFcltyInstlDt" type="text" class="emdcal" size="20" title="설치일자" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">변경일자</th>
							<td colspan="3"><input id="prtFcltyChangeDt" type="text" class="emdcal" size="20" title="변경일자" /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="insertGIS">GIS 등록</button>
					<button id="searchPosition">위치조회</button>
					<button id="saveBtn">저장</button>
				</div>
			</div>
			
			<!-- 토목시설 사진 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyPhotoList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnUploadFile">업로드</button>
					<button id="btnDownloadFile">다운로드</button>
					<button id="btnRemoveFile">삭제</button>
				</div>
				<form id="fcltyGisPhotoForm">
					<table>
						<tr>
							<th><span class="label">제 목</span></th>
							<td><input id="photoSj" type="text" size="60" class="photoEditItem" maxlength="40" /></td>
						</tr>
						<tr>
							<th><span class="label">촬영일자</span></th>
							<td><input id="shotDt" type="text" size="10" class="emdcal photoEditItem"  maxlength="10" /></td>
						</tr>
						<tr>
							<th><span class="label">사진 설명</span></th>
							<td><input id="photoDesc" type="text" size="60"  class="photoEditItem" maxlength="100" /></td>
						</tr>
					</table>
				</form>
				<button id="btnApplyPhotoData">적용</button>
				<div class="emdPanel fillHeight" style="overflow:scroll"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>