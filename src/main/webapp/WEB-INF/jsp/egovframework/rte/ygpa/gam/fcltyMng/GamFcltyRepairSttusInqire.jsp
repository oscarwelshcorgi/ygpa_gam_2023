<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyRepairSttusInqire.jsp
  * @Description : 시설물 하자보수현황 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.9  HNJ          최초 생성
  *
  * author HNJ
  * since 2014.12.9
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
 * @FUNCTION NAME : GamFcltyRepairSttusInqireModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltyRepairSttusInqireModule() {
}

GamFcltyRepairSttusInqireModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairSttusInqireModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#fcltyRepairSttusInqireList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyRepairSttusInqireList.do',
		dataType: "json",
		colModel : [
					{display:"시설물관리그룹", 		name:"fcltsMngGoupNm",			width:130, 		sortable:false,		align:"center"},
					{display:"계약번호", 			name:"ctrtNo",					width:200, 		sortable:false,		align:"center"},
					{display:"계약명",			name:"flawRprNm",				width:250, 		sortable:false,		align:"left"},
					{display:"도급업체명",			name:"flawRprEntrpsNm",			width:250, 		sortable:false,		align:"left"},
					{display:"업무구분",			name:"fcltsJobSeNm",			width:80, 		sortable:false,		align:"center"},
					{display:"하자검사구분",		name:"flawExamSeNm",			width:80, 		sortable:false,		align:"center"},
					{display:"하자검사일자",		name:"flawExamDt",				width:80, 		sortable:false,		align:"center"},
					{display:"하자발생일자",		name:"flawOccrrncDt",			width:80, 		sortable:false,		align:"center"},
					{display:"하자보수유형",		name:"flawRprTyNm",				width:80, 		sortable:false,		align:"center"},
					{display:"하자보수기간",		name:"flawRprTerm",				width:160, 		sortable:false,		align:"center"},
					{display:"하자보수금액", 		name:"flawRprAmt",				width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"하자보수완료여부", 	name:"flawRprComptYn",			width:120, 		sortable:false,		align:"center"}
			],
		height: "auto",
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			module.$('#sumFlawRprAmt').val($.number(data.sumFlawRprAmt));
			return data;
		}
	});
	
	
	this.$("#flawRprSttusObjFcltsF").flexigrid({
		module: this,
		url: '/fcltyMng/selectFlawRprSttusObjFcltsF.do',
		dataType: 'json',
		colModel : [
					{display:"순번",			name:"rnum",			width:50,		sortable:false,		align:"center"},
					{display:"대상시설물",		name:"prtFcltyNm",		width:250,		sortable:false,		align:"left"},
					{display:"하자유무",		name:"flawEnnc",		width:90,		sortable:true,		align:"center"},
					{display:"하자검사일자",	name:"flawExamDt",		width:100,		sortable:true,		align:"center"},
					{display:"하자검사결과",	name:"flawExamResult",	width:350,		sortable:true,		align:"left"},
					{display:"비고",			name:"rm",				width:350,		sortable:true,		align:"left"}
			],
		height: "250"
	});

 	
 	this.$("#fcltyRepairSttusInqireList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.$("#fcltyRepairSttusInqireListTab").tabs("option", {active: 1});
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
GamFcltyRepairSttusInqireModule.prototype.getMapInfoList = function(params){
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
GamFcltyRepairSttusInqireModule.prototype.setDefaultParam = function(){
	var toDate = new Date();
	var toYear = toDate.getFullYear();
	
	var toMonth = toDate.getMonth() + 1;
	if(toMonth < 10) toMonth = "0" + toMonth;
	
	var toDay = toDate.getDay();
	if(toDay < 10) toDay = "0" + toDay;
	
	this.$("#sFlawRprStartDtFr").val(toYear + "-01-01");
	this.$("#sFlawRprStartDtTo").val(toYear + "-" + toMonth + "-" + toDay);
	
	this.$("#sFcltsJobSe").val(EMD.userinfo["mngFcltyCd"]);
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
GamFcltyRepairSttusInqireModule.prototype.validateDuration = function(startDate, endDate, startTitle, endTitle, startIgnore, endIgnore, equals) {
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
GamFcltyRepairSttusInqireModule.prototype.onSubmit = function(){
	if(!this.validateDuration(this.$('#sFlawRprStartDtFr').val(), this.$('#sFlawRprStartDtTo').val(),  
			'하자검사기간조회 시작일', '하자검사기간조회 종료일',  true,  true, true)) {
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
GamFcltyRepairSttusInqireModule.prototype.loadData = function(){

	// tabs2 항목 초기화
	this.makeFormValues('#fcltyRepairSttusInqireListVO', {});
	this.makeDivValues('#fcltyRepairSttusInqireListVO', {});
	
	// tabs3 그리드 초기화
	this.$('#flawRprSttusObjFcltsF').flexEmptyData();
	this.makeDivValues('#gamObjFcltsDetailForm',{});

	this.$("#fcltyRepairSttusInqireListTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchFcltyRepairSttusInqireForm');
	this.$('#fcltyRepairSttusInqireList').flexOptions({params:searchOpt}).flexReload();
	
};


<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : DATA LOAD (DETAIL)
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairSttusInqireModule.prototype.loadDetail = function(){
	var row = this.$('#fcltyRepairSttusInqireList').selectedRows();
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyRepairSttusInqireListTab").tabs("option", {active: 0});
		return;
	}
	
	row = row[0];
	var searchVO = [
	                { name: 'fcltsJobSe', value: row['fcltsJobSe'] },
	                { name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	                { name: 'flawRprSeq', value: row['flawRprSeq'] }
	               ];
	
	// tabs2 항목 데이타로딩
	this.doAction('/fcltyMng/selectFcltyRepairSttusInqireDetail.do', searchVO, function(module, result) {
		if(result.resultCode == "0"){
			
			module._gamObjFcltsDetailFormValues = result.result;
			module.makeFormValues('#fcltyRepairSttusInqireListVO', result.result);
			module.makeDivValues('#fcltyRepairSttusInqireListVO', result.result);
			
			// tabs3 그리드 리로드
			module.makeDivValues('#gamObjFcltsDetailForm',result.result);
			module.$('#flawRprSttusObjFcltsF').flexOptions({params:searchVO}).flexReload();
			// 첨부파일리스트
			module.fillAtchFileList(searchVO);
			
		}else{
			module.$("#fcltyRepairSttusInqireListTab").tabs("option", {active: 0});
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
GamFcltyRepairSttusInqireModule.prototype.fillAtchFileList = function(searchVO) {
	this.doAction('/fcltyMng/selectFcltyRepairSttusFileList.do', searchVO, function(module, result) {
		if(result.resultCode == "0"){
			module.$('#fcltyRepairFileList option').remove();
			module.$('#fcltyRepairFileList').append('<option value="">선택</option>');
			$.each(result.resultList, function(){
				module.$('#fcltyRepairFileList').append('<option value="' + this.atchFileNmPhysicl + '">' + this.atchFileNmLogic + '</option>');
			});
		}else{
			module.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
		}
    });
};


<%
/**
 * @FUNCTION NAME : imgPreview
 * @DESCRIPTION   : 선택한 첨부파일이 이미지이면 미리보기 보여주는 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairSttusInqireModule.prototype.imgPreview = function(){

	var selImg = this.$('#fcltyRepairFileList').val();
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
			alert('미리보기는 이미지파일만 가능합니다.');
		}
	}else{
		alert('미리보기할 파일을 선택하세요.');
	}
	
};

<%
/**
 * @FUNCTION NAME : downloadFileData
 * @DESCRIPTION   : 파일다운로드 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairSttusInqireModule.prototype.downloadFileData = function() {
	var selectFilePhysicl = this.$('#fcltyRepairFileList').val();
	var selectFileLogic = this.$('#fcltyRepairFileList').find('option:selected').text();

	if(selectFilePhysicl) {
		this.downPfPhoto(selectFilePhysicl, selectFileLogic);
	}else{
		alert('다운로드할 파일을 선택하세요.');
	}
};


<%
/**
 * @FUNCTION NAME : fillTitleData
 * @DESCRIPTION   : 대상시설물 탭에 상단 내용 채우는 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairSttusInqireModule.prototype.fillTitleData = function() {
	var changData = _gamObjFcltsDetailFormValues;
	this.makeDivValues('#gamObjFcltsDetailForm',changData);
	
};


<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 그리드리스트 다운로드 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairSttusInqireModule.prototype.downloadExcel = function(buttonId) {
	var rowCount = this.$('#fcltyRepairSttusInqireList').flexRowCount();
	if (rowCount <= 0) {
		alert('조회된 자료가 없습니다.');
		return;
	}
	this.$('#fcltyRepairSttusInqireList').flexExcelDown('/fcltyMng/selectFcltyRepairInqireListExcel.do');

};


<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
 GamFcltyRepairSttusInqireModule.prototype.onButtonClick = function(buttonId) {

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
GamFcltyRepairSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
		
		case "tabs3":
			// tabs2에서 수정사항발생시 반영 
			this.fillTitleData();
		break;
	}
	
};




// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyRepairSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyRepairSttusInqireForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>시설물업무구분</th>
							<td>
								<select id="sFcltsJobSe" title="시설물업무구분">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th>계약명</th>
							<td><input type="text" id="sFlawRprNm" size="50" title="하자보수명" /></td>
							<td rowspan="3"><button class="buttonSearch" title="조회">조회</button></td>
						</tr>
						<tr>
							<th>하자검사구분</th>
							<td>
								<select id="sFlawExamSe" title="하자검사구분">
									<option value="">선택</option>
									<option value="1">상반기</option>
									<option value="2">하반기</option>
								</select>
							</td>
							<th>하자검사기간</th>
							<td>
								<input id="sFlawRprStartDtFr" type="text" size="15" class="emdcal" title="하자검사일 검색시작일" /> ~ <input id="sFlawRprStartDtTo" type="text" size="15" class="emdcal" title="하자검사일 검색종료일" />
							</td>
						</tr>
						<tr>
							<th>시공업체명</th>
							<td colspan="3"><input type="text" id="sFlawRprEntrpsNm" size="70" title="시공업체" /></td>
							
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltyRepairSttusInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">하자보수내역 목록</a></li>
				<li><a href="#tabs2" class="emdTab">하자보수내역 상세</a></li>
				<li><a href="#tabs3" class="emdTab">하자보수대상시설물</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyRepairSttusInqireList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<table style="width:100%;">
						<tr>
							<th>자료수</th>
							<td><input type="text" id="totalCount" style="width:100px;text-align:right;" readonly="readonly"></td>
							<th>하자보수금액</th>
							<td><input type="text" id="sumFlawRprAmt" style="width:100px;text-align:right;" readonly="readonly"></td>
							<td style="text-align:right;">
								<button id="btnExcelDownload" class="buttonExcel">엑셀 다운로드</button>
							</td>
						</tr>
					</table>
					
				</div>
			</div>


			<!-- 하자보수내역 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyRepairSttusInqireListVO">
					<table class="editForm"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="3">
								<input type="text" size="18" id="fcltsMngGroupNo" disabled="disabled" title="시설물관리그룹넘버" />
								[ <span id="fcltsMngGoupNoNm" title="시설물관리그룹명"></span> ]
							</td>
							<th width="15%" height="23" class="required_text">시행년도</th>
							<td><input type="text" id="enforceYear" disabled="disabled" title="시행년도"></td>
							<th width="15%" height="23" class="required_text">시설물업무구분</th>
							<td>
								<input type="text" id="fcltsJobSeNm" disabled="disabled" title="시설물업무구분">
								<input type="hidden" id="fcltsJobSe">
							</td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">계약번호</th>
							<td colspan="3">
								<input type="text" size="20" id="ctrtNo" disabled="disabled" title="계약번호"/> 
								[ <span id="flawRprNm" title="계약명"></span> ]
							</td>
							<th width="15%" height="23" class="required_text">도급업체명</th>
							<td colspan="3"><input id="flawRprEntrpsNm" type="text" size="53" title="도급업체명" disabled="disabled" /></td>
						</tr>
						<tr>
							<th>하자검사구분</th>
							<td colspan="3">
								<input type="text" id="flawExamSeNm" disabled="disabled" title="하자검사구분">
								<input type="hidden" id="flawRprSeq" title="하자보수순번" />
							</td>
							<th width="15%" height="23" class="required_text">하자검사일자</th>
							<td colspan="3"><input id="flawExamDt" type="text" size="20" title="하자검사일자" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">검사자</th>
							<td colspan="3"><input type="text" size="40" id="flawExamUsr" disabled="disabled" title="검사자" /></td>
							<th width="15%" height="23" class="required_text">소속</th>
							<td><input type="text" size="18" id="flawExamUsrDept" disabled="disabled" title="소속" /></td>
							<th width="15%" height="23" class="required_text">직급 및 성명</th>
							<td><input type="text" size="18" id="flawExamUsrNm" disabled="disabled" title="직급 및 성명" /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">하자유무</th>
							<td><input type="text" id="flawEnnc" disabled="disabled" title="하자유무"></td>
							<th width="15%" height="23" class="required_text">하자발생일자</th>
							<td><input id="flawOccrrncDt" type="text" size="15" title="하자발생일자" disabled="disabled" /></td>
							<th width="15%" height="23" class="required_text">하자보수기간</th>
							<td colspan="3">
								<input id="flawRprStartDt" type="text" size="20" title="하자보수시작일자" disabled="disabled" /> ~ 
								<input id="flawRprEndDt" type="text" size="20" title="하자보수종료일자" disabled="disabled" />
							</td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">하자보수유형</th>
							<td><input type="text" id="flawRprTyNm" title="하자보수유형" disabled="disabled"></td>
							<th width="15%" height="23" class="required_text">하자보수금액</th>
							<td><input id="flawRprAmt" type="text" size="20" title="하자보수금액" class="ygpaNumber" disabled="disabled" /> 원</td>
							<th width="15%" height="23" class="required_text">하자보수완료여부</th>
							<td colspan="3"><input type="text" id="flawRprComptYn" title="하자보수완료여부" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">하자보수내용</th>
							<td colspan="7"><textarea id="flawRprContents" cols="143" rows="5" title="하자보수내용" disabled="disabled"></textarea></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">하자보수결과</th>
							<td colspan="7"><textarea id="flawExamResult" cols="143" rows="5" title="하자보수결과" disabled="disabled"></textarea></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">비고</th>
							<td colspan="7"><input id="rm" type="text" size="145" title="비고" disabled="disabled" /></td>
						</tr>
					</table>
				<div class="emdControlPanel">
					<button data-role="printPage" data-search-option="fcltyRepairSttusInqireListVO" data-url='/fcltyMng/selectFcltyRepairCheckMngPrint.do'>하자검사관리대장인쇄</button>
					<button data-role="printPage" data-search-option="fcltyRepairSttusInqireListVO" data-url='/fcltyMng/selectFcltyRepairCheckReportPrint.do'>하자검사조서인쇄</button>
					<button data-role="printPage" data-search-option="fcltyRepairSttusInqireListVO" data-url='/fcltyMng/selectFcltyRepairExpireCheckReportPrint.do'>하자만료검사조서인쇄</button>
				</div>
				</form>
			</div>
			
			<!-- 하자보수 대상 시설물 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<div class="emdControlPanel">
					<table class="summaryPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th style="font-weight:bold;">하자보수 상세내역</th>
							</tr>
						</tbody>
					</table>
					<form id="gamObjFcltsDetailForm">
						<table class="detailPanel"  style="width:100%;">
							<tbody>
								<tr>
									<th>시설물관리그룹</th>
									<td><span id="fcltsMngGoupNoNm"></span></td>
									<th>업무구분</th>
									<td><span id="fcltsJobSeNm"></span></td>
									<th>하자검사구분</th>
									<td><span id="flawExamSeNm"></span></td>
								</tr>
								<tr>
									<th>계약번호</th>
									<td><span id="ctrtNo"></span></td>
									<th>계약명</th>
									<td><span id="flawRprNm"></span></td>
									<th>도급업체명</th>
									<td><span id="flawRprEntrpsNm"></span></td>
								</tr>
							</tbody>
						</table>
					</form>
					<table class="summaryPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th style="font-weight:bold;">하자보수 대상시설물</th>
							</tr>
						</tbody>
					</table>
				</div>
				<table id="flawRprSttusObjFcltsF" style="display:none"></table>
				<table class="summaryPanel" style="width:100%;">
					<tbody>
						<tr>
							<th height="18" class="required_text">첨부파일</th>
							<td>
								<select id="fcltyRepairFileList">
									<option value="">선택</option>
								</select> 
							</td>
							<td colspan="6" style="text-align:right;">
								<button id="btnPreviewFile">첨부파일 미리보기</button> 
								<div id="previewHidden" style="display: none;"></div>
								<button id="btnDownloadFile">다운로드</button> 
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>