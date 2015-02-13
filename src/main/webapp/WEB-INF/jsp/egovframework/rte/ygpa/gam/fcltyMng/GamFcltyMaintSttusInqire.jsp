<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyMaintSttusInqire.jsp
  * @Description : 시설물 유지보수현황 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.8  HNJ          최초 생성
  *
  * author HNJ
  * since 2014.12.8
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<%
/******************************** SCRIPT START ********************************/
%>
<script>

<%
/**
 * @FUNCTION NAME : GamFcltyMaintSttusInqireModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltyMaintSttusInqireModule() {}

GamFcltyMaintSttusInqireModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintSttusInqireModule.prototype.loadComplete = function(params) {
	
	//console.log("GamFcltyMaintSttusInqireModule");
	// 테이블 설정
	this.$("#fcltyMaintSttusInqireList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyMaintSttusInqireList.do',
		dataType: "json",
		colModel : [
					{display:"시설물관리그룹", 		name:"fcltsMngGroupNm",			width:130, 		sortable:false,		align:"center"},
					{display:"유지보수공사명",		name:"mntnRprCnstNm",			width:250, 		sortable:false,		align:"left"},
					{display:"유지보수구분",		name:"mntnRprSeNm",				width:80, 		sortable:false,		align:"center"},
					{display:"시작일자",			name:"mntnRprCnstStartDt",		width:80, 		sortable:false,		align:"center"},
					{display:"종료일자",			name:"mntnRprCnstEndDt",		width:80, 		sortable:false,		align:"center"},
					{display:"공사금액", 			name:"mntnRprCnstAmt",			width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"유지보수예산", 		name:"mntnRprBdgt",				width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"유지보수부위", 		name:"mntnRprPart",				width:250, 		sortable:false,		align:"center"},
					{display:"시공자", 			name:"cnstrtr",					width:150, 		sortable:false,		align:"center"},
					{display:"계약명", 			name:"ctrtNm",					width:250, 		sortable:false,		align:"left"},
					{display:"유지보수정보", 		name:"mntnFcltsCnstInfo",		width:200, 		sortable:false,		align:"center"}
			],
		height: "auto",
		groupBy: "mntnFcltsCnstInfo",
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			module.$('#sumMntnRprCnstAmt').val($.number(data.sumMntnRprCnstAmt));
			module.$('#sumMntnRprBdgt').val($.number(data.sumMntnRprBdgt));
			return data;
		}
	});


	this.$("#mntnRprObjFcltsF").flexigrid({
		module: this,
		url: '/fcltyMng/selectMntnSttusRprObjFcltsFList.do',
		dataType: "json",
		colModel : [
					{display:"관리번호", 			name:"fcltsMngNo",			width:130, 		sortable:false,		align:"center"},
					{display:"시설명",			name:"prtFcltyNm",			width:250,		sortable:false,		align:"left"}
			],
		height: "295"
	});

 	this.$("#fcltyMaintSttusInqireList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.$("#fcltyMaintSttusInqireListTab").tabs("option", {active: 1});
	});
	
	
	// 시설물관리그룹 검색조건 클릭시 초기화 처리
	this.$("#sFcltsMngGroupNo").bind("click", {module: this}, function(event) {
		event.data.module.$("#sFcltsMngGroupNo").val('');
		event.data.module.$("#sFcltsMngGroupNoNm").val('');
	});
	
	// 공사계약 검색조건 클릭시 초기화 처리
	this.$("#sCtrtNo").bind("click", {module: this}, function(event) {
		event.data.module.$("#sCtrtNo").val('');
		event.data.module.$("#sCtrtNm").val('');
	});
	
	
	// 기본값 셋팅
	this.setDefaultParam();
	this.getMapInfoList(params);
};


<%
/**
 * @FUNCTION NAME : getMapInfoList
 * @DESCRIPTION   : 맵에서 유지보수 정보를 클릭할때 넘어오는 Param으로 리스트 가져오는 함수
 * @PARAMETER     
 *		1. fcltsMngGroupNo   : 시설물 관리 그룹 코드
 *		2. fcltsMngGroupNoNm : 시설물 관리 그룹 코드명
**/
%>
GamFcltyMaintSttusInqireModule.prototype.getMapInfoList = function(params){
	this._params=params;
	if(params!=null) {
		if(params.action!=null) {
			switch(params.action) {
				case "manage":
					this.$('#sFcltsMngGroupNo').val(this._params.fcltsMngGroupNo);
					this.$('#sFcltsMngGroupNoNm').val(this._params.fcltsMngGroupNoNm);
					
					this.loadData();
				break;
			}
		}
	}

};



<%
/**
 * @FUNCTION NAME : setDefaultParam
 * @DESCRIPTION   : 조회조건 및 기본값 셋팅 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintSttusInqireModule.prototype.setDefaultParam = function(){
	var toDate = new Date();
	var toYear = toDate.getFullYear();
	
	var toMonth = toDate.getMonth() + 1;
	if(toMonth < 10) toMonth = "0" + toMonth;
	
	var toDay = toDate.getDay();
	if(toDay < 10) toDay = "0" + toDay;
	
	this.$("#sMntnRprCnstStartDtFr").val(toYear + "-01-01");
	this.$("#sMntnRprCnstStartDtTo").val(toYear + "-" + toMonth + "-" + toDay);
	
	this.$("#sFcltsJobSe").val(EMD.userinfo["mngFcltyCd"]);
	
};


<%
/**
 * @FUNCTION NAME : imgPreview
 * @DESCRIPTION   : 선택한 첨부파일이 이미지이면 미리보기 보여주는 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintSttusInqireModule.prototype.imgPreview = function(){

	var selImg = this.$('#fcltyMaintFileList').val();
	if(selImg) {
		// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
		var ext = selImg.substring(selImg.lastIndexOf(".")+1).toLowerCase();

		if(ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif"){
			this.$('#previewHidden').append('<div id="'+this.getId("previewDialog")+'"><img id="'+this.getId("previewImage")+'" src=""/></div>');
			var imgURL = this.getPfPhotoUrl(selImg);
			this.$("#previewImage").attr("src", imgURL);
			
			this.$("#previewImage").bind('load', {module: this},function(event){
				event.data.module.$('#previewDialog').dialog({
					modal: true,
					maxWidth: 800,
					maxHeight: 600,
					resizable: false,
					draggable: true,
					width: 'auto',
					title: '이미지미리보기',
					buttons:[{text:"close", click: function() { $(this).dialog('close'); }}]
				});
			});   
			
		}else{
			this.$("#previewImage").removeAttr("src");
		}
	}
	
};


<%
/**
 * @FUNCTION NAME : validateDuration
 * @DESCRIPTION   : 유효성 있는 기간 체크
 * @PARAMETER     : 
	 1. startDate   : 시작일 문자열, 
	 2. endDate     : 종료일 문자열, 
	 3. startTitle  : 시작일 제목, 
	 4. endTitle    : 종료일 제목, 
	 5. startIgnore : 
		 5-1. true  : 시작일 필수입력사항 미체크,
		 5-2. false : 시작일 필수입력사항 체크 
	 6. endIgnore : 
		 6-1. true  : 종료일 필수입력사항 미체크,
		 6-2. false : 종료일 필수입력사항 체크 
	 7. equals      :
		 7-1. true  : 종료일이 시작일 보다 크거나 같으면 허용
		 7-2. false : 종료일이 시작일 보다 커야 허용
**/
%>
GamFcltyMaintSttusInqireModule.prototype.validateDuration = function(startDate, endDate, startTitle, endTitle, startIgnore, endIgnore, equals) {
	var result = false;
	if(((startDate == null) || (startDate == '')) && ((endDate == null) || (endDate == ''))) {
		return true;
	}
	if((endDate == null) || (endDate == '')) {
		if(!endIgnore) {
			alert(endTitle + '을(를) 입력하셔야 합니다.');
			return false;
		}
		result = true;
		if((startDate != null) && (startDate != '')) {
			result = EMD.util.isDate(startDate);
			if(!result) {
				alert(startTitle + '은(는) 날짜형식이 아닙니다.');
			}
		}
		return result;
	}
	if((startDate == null) || (startDate == '')) {
		if(startIgnore) {
			result = EMD.util.isDate(endDate);
			if(!result) {
				alert(endTitle + '은(는) 날짜형식이 아닙니다.');
			}
			return result;
		} else {
			alert(startTitle + '을(를) 입력하셔야 합니다.');
			return false;
		}
	}
	if(!EMD.util.isDate(startDate)) {
		alert(startTitle + '은(는) 날짜형식이 아닙니다.');
		return false;
	}
	if(!EMD.util.isDate(endDate)) {
		alert(endTitle + '은(는) 날짜형식이 아닙니다.');
		return false;
	}
	startDate = EMD.util.strToDate(startDate);
	endDate = EMD.util.strToDate(endDate);
	var compareResult = (startDate.getTime() > endDate.getTime()) ? -1 : 
							(startDate.getTime() == endDate.getTime()) ? 0 : 1;	
	result = (equals) ? (compareResult >= 0) : (compareResult > 0);
	if(!result) {
		alert(endTitle +'은(는) ' + startTitle + ((equals) ? '보다 같거나 커야합니다.' : '보다 커야합니다.'));
	}
	return result;
};


<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintSttusInqireModule.prototype.onSubmit = function(){
	
	if(!this.validateDuration(this.$('#sMntnRprCnstStartDtFr').val(), this.$('#sMntnRprCnstStartDtTo').val(),  
			'유지보수공사검색시작일', '유지보수공사검색종료일',  true,  true, true)) {
		return;
	}
	this.loadData();
};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintSttusInqireModule.prototype.loadData = function(){
	
	this.makeFormValues('#fcltyMaintSttusInqireListVO', {});
	
	this.$('#mntnRprObjFcltsF').flexEmptyData();
	
	this.$('#fcltyMaintFileList').empty();
	this.$('#fcltyMaintFileList').append('<option value="">선택</option>');
	
	this.$("#fcltyMaintSttusInqireListTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchFcltyMaintSttusInqireForm');
	this.$('#fcltyMaintSttusInqireList').flexOptions({params:searchOpt}).flexReload();
	
};




<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세정보를 가져옴.
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintSttusInqireModule.prototype.loadDetail = function(){
	
	var row = this.$('#fcltyMaintSttusInqireList').selectedRows();
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyMaintSttusInqireListTab").tabs("option", {active: 0});
		return;
	}
	
	row = row[0];
	var searchVO = [
	                { name: 'fcltsJobSe', value: row['fcltsJobSe'] },
	                { name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	                { name: 'mntnRprSeq', value: row['mntnRprSeq'] }
	               ];

	this.doAction('/fcltyMng/selectFcltyMaintSttusInqireDetail.do', searchVO, function(module, result) {
		if(result.resultCode == "0"){
			module.makeDivValues('#fcltyMaintSttusInqireListVO', result.result);
			
			var codeId = module.getCodeId(row['fcltsJobSe']);
			
			var codeVO = { name: 'codeId', value: codeId };
			searchVO.push(codeVO);
			
			module.$('#mntnRprObjFcltsF').flexOptions({params:searchVO}).flexReload();
			module.fillAtchFileList(searchVO);
		}else{
			module.$("#fcltyMaintSttusInqireListTab").tabs("option", {active: 0});
		}
    });
	
};


<%
/**
 * @FUNCTION NAME : fillAtchFileList
 * @DESCRIPTION   : 첨부파일 리스트를 select element에 채워넣기.
 * @PARAMETER     : 
 *   1. searchVO
 *     1-1. fcltsJobSe : 시설물업무구분
 *     1-2. fcltsMngGroupNo : 시설물 관리그룹번호
 *     1-3. mntnRprSeq : 유지보수 순번
**/
%>
GamFcltyMaintSttusInqireModule.prototype.fillAtchFileList = function(searchVO) {
	this.doAction('/fcltyMng/selectFcltyMaintSttusFileList.do', searchVO, function(module, result) {
		if(result.resultCode == "0"){
			module.$('#fcltyMaintFileList option').remove();
			module.$('#fcltyMaintFileList').append('<option value="">선택</option>');
			$.each(result.resultList, function(){
				module.$('#fcltyMaintFileList').append('<option value="' + this.atchFileNmPhysicl + '">' + this.atchFileNmLogic + '</option>');
			});
		}else{
			module.$("#fcltyMaintSttusInqireListTab").tabs("option", {active: 0});
		}
    });
};


<%
/**
 * @FUNCTION NAME : downloadFileData
 * @DESCRIPTION   : 파일 다운로드 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintSttusInqireModule.prototype.downloadFileData = function() {
	var selectFilePhysicl = this.$('#fcltyMaintFileList').val();
	var selectFileLogic = this.$('#fcltyMaintFileList').find('option:selected').text();

	if(selectFilePhysicl) {
		this.downPfPhoto(selectFilePhysicl, selectFileLogic);
	}else{
		alert('다운로드할 파일을 선택하세요.');
	}
};


<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 그리드리스트 다운로드 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintSttusInqireModule.prototype.downloadExcel = function(buttonId) {
	
	var rowCount = this.$('#fcltyMaintSttusInqireList').flexRowCount();
	if (rowCount <= 0) {
		alert('조회된 자료가 없습니다.');
		return;
	}
	this.$('#fcltyMaintSttusInqireList').flexExcelDown('/fcltyMng/selectFcltyMaintInqireListExcel.do');

};


<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
 GamFcltyMaintSttusInqireModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 엑셀다운로드
		case "btnExcelDownload":
			this.downloadExcel(buttonId);
		break;
		
		// 이미지미리보기
		case "btnPreviewFile":
			this.imgPreview();
		break;
		
		// 파일다운로드
		case "btnDownloadFile":
			this.downloadFileData();
		break;
		
		// 검색조건시설물관리그룹
		case "sSearchFcltsMngGroupNo":
			this.doExecuteDialog("sSelectFcltsMngGroup", "시설물 관리 그룹 번호 검색", '/popup/showFcltsMngGroup.do', {});
		break;
		
		// 검색조건계약번호
		case "sCtrtNoPopupBtn":
			this.doExecuteDialog("sSelectCtrtNo", "계약번호 검색", '/popup/popupCtrtNo.do', {});
		break;
	}
};


<%
/**
 * @FUNCTION NAME : onTabChange
 * @DESCRIPTION   : 탭이 변경 될때 호출된다. (태그로 정의 되어 있음)
 * @PARAMETER     :
 *   1. newTabId - NEW TAB ID
 *   2. oldTabId - OLD TAB ID
**/
%>
GamFcltyMaintSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1') {
		this.loadDetail();
	}
	
	switch(newTabId) {
		case "tabs1":
		break;

		case "tabs2":
			if(oldTabId == 'tabs1') {
				this.$("#tabs2").scrollTop(0);                                                                                                                                                                                                                                                                         
			}
		break;

	}
	
};


<%
/**
 * @FUNCTION NAME : onClosePopup
 * @DESCRIPTION   : CLOSE POPUP EVENT
 * @PARAMETER     :
 *   1. popupId  - POPUP ID
 *   2. msg      - MESSAGE
 *   3. value    - VALUE
**/
%>
 GamFcltyMaintSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){
		
		case "sSelectFcltsMngGroup":
			this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#sFcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
		break;
		
		case "sSelectCtrtNo":
			this.$("#sCtrtNo").val(value["ctrtNo"]);
			this.$("#sCtrtNm").val(value["ctrtNm"]);
		break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
		break;
	}
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyMaintSttusInqireModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyMaintSttusInqireForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>시설물관리그룹</th>
							<td>
								<input type="text" size="15" id="sFcltsMngGroupNo" title="시설물관리그룹넘버" />-
								<input type="text" size="17" id="sFcltsMngGroupNoNm" disabled="disabled" title="시설물관리그룹명"/>
								<button id="sSearchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
							<th>공사계약</th>
							<td>
								<input type="text" size="15" id="sCtrtNo" title="계약번호"/>-
								<input type="text" size="17" id="sCtrtNm" disabled="disabled" title="계약명"/>
								<button id="sCtrtNoPopupBtn" class="popupButton">선택</button>
							</td>
							<td rowspan="3"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>시설물업무구분</th>
							<td>
								<select id="sFcltsJobSe" title="시설물업무구분검색조건">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th>유지보수공사명</th>
							<td><input type="text" id="sMntnRprCnstNm" size="49" title="유지보수공사명검색조건" /></td>
						</tr>
						<tr>
							<th>유지보수구분</th>
							<td>
								<select id="sMntnRprSe" title="유지보수구분검색조건">
									<option value="">선택</option>
									<option value="1">개량</option>
									<option value="2">보수</option>
									<option value="3">보강</option>
									<option value="4">변경-증설</option>
									<option value="5">변경-구조변경</option>
									<option value="9">기타</option>
								</select>
							</td>
							<th>유지보수공사시작일</th>
							<td>
								<input id="sMntnRprCnstStartDtFr" type="text" class="emdcal" size="17" title="유지보수공사검색시작일" /> ~ <input id="sMntnRprCnstStartDtTo" type="text" class="emdcal" size="17" title="유지보수공사검색종료일" />
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltyMaintSttusInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">유지보수내역 목록</a></li>
				<li><a href="#tabs2" class="emdTab">유지보수내역 상세</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyMaintSttusInqireList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<table style="width:100%;">
						<tr>
							<th>자료수</th>
							<td><input type="text" id="totalCount" style="width:100px;text-align:right;" readonly="readonly"></td>
							<th>공사금액</th>
							<td><input type="text" id="sumMntnRprCnstAmt" style="width:100px;text-align:right;" readonly="readonly"></td>
							<th>유지보수예산</th>
							<td><input type="text" id="sumMntnRprBdgt" style="width:100px;text-align:right;" readonly="readonly"></td>
							<td style="text-align:right;">
								<button id="btnExcelDownload">엑셀 다운로드</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 유지보수내역 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyMaintSttusInqireListVO">
					<table class="editForm"  style="width:100%;">
					<tr>
						<td colspan="2" style="width:60%;">
							<table class="editForm"  style="width:100%;">
								<tr>
									<th width="100px" height="18" class="required_text" style="border-bottom:none;">시행년도</th>
									<td width="200px" style="border-bottom:none;"><span id="enforceYear" title="시행년도"></span></td>
									<th width="100px" height="18" class="required_text">시설물업무구분</th>
									<td>
										<span id="fcltsJobSeNm" title="시설물업무구분"></span>
										<input type="hidden" id="codeId">
									</td>
								</tr>
								<tr>
									<th height="18" class="required_text">유지보수구분</th>
									<td>
										<span id="mntnRprSeNm" title="유지보수구분"></span> 
										<span id="mntnSubRprSeNm" title="유지보수하위구분"></span>
									</td>
									<th height="18" class="required_text">계획이력구분</th>
									<td>
										<span id="planHistSeNm" title="계획이력구분"></span> 
									</td>
								</tr>
								<tr>
									<th height="17" class="required_text">시설물관리그룹</th>
									<td colspan="3">
										<span id="fcltsMngGroupNo" title="시설물관리그룹넘버" ></span> - <span id="fcltsMngGroupNoNm" title="시설물관리그룹명"></span>
									</td>
								</tr>
								<tr>
									<th height="18" class="required_text">계약번호</th>
									<td colspan="3">
										<span id="ctrtNo" title="계약번호"></span> - <span id="ctrtNm" title="계약명" ></span>
									</td>
									
								</tr>
								<tr>
									<th height="18" class="required_text">공사명</th>
									<td colspan="3"><span id="mntnRprCnstNm" title="공사명"></span></td>
								</tr>
								<tr>
									<th height="18" class="required_text">유지보수</th>
									<td colspan="3"><span id="mntnRprPart" title="유지보수"></span></td>
								</tr>
								<tr>
									<th height="18" class="required_text">공사기간</th>
									<td>
										<span id="mntnRprCnstStartDt" title="공사시작일자"></span>  ~  <span id="mntnRprCnstEndDt" title="공사종료일자"></span>
									</td>
									<th height="18" class="required_text">계약자</th>
									<td><span id="cnstrtr" title="계약자"></span></td>
								</tr>
								<tr>
									<th height="18" class="required_text">예산</th>
									<td><span id="mntnRprBdgt" title="예산" class="ygpaNumber"></span>원</td>
									<th height="18" class="required_text">계약금액</th>
									<td><span id="mntnRprCnstAmt" title="계약금액" class="ygpaNumber"></span>원</td>
								</tr>
								<tr>
									<th height="18" class="required_text">작성자</th>
									<td><span id="wrtUsr" title="작성자"></span></td>
									<th height="18" class="required_text">작성일</th>
									<td><span id="wrtDt" title="작성일"></span></td>
								</tr>
								<tr>
									<th height="18" class="required_text">유지보수내용</th>
									<td colspan="3"><span id="mntnRprCn" title="유지보수내용" ></span></td>
								</tr>
								<tr>
									<th height="18" class="required_text" style="border-bottom:none;">비고</th>
									<td colspan="3" style="border-bottom:none;"><span id="rm" title="비고"></span></td>
								</tr>
							</table>
						</td>
						<td style="border-bottom:none;">
							<table id="mntnRprObjFcltsF" style="display:none"></table>
						</td>
					</tr>
					<tr>
						<th height="18" class="required_text" style="width:95px;">첨부파일</th>
						<td>
							<select id="fcltyMaintFileList">
								<option value="">선택</option>
							</select> 
						</td>
						<td style="text-align:right;">
							<button id="btnPreviewFile">첨부파일 미리보기</button> 
							<div id="previewHidden" style="display: none;"></div>
							<button id="btnDownloadFile">다운로드</button> 
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
	</div>
</div>