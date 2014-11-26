<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyQcwWrtMngMng.jsp
  * @Description : 시설 점검 관리
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
function GamFcltyQcwWrtMngModule() {
}

GamFcltyQcwWrtMngModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyQcwWrtMngModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#qcMngDtlsList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngDtlsList.do',
		dataType: "json",
		colModel : [
					{display:"관리그룹번호",	name:"fcltsMngGroupNo",		width:100,		sortable:false,		align:"center"},
					{display:"업무구분",		name:"fcltsJobSe",			width:60,		sortable:false,		align:"center"},
					{display:"점검관리순번",	name:"qcMngSeq",			width:90,		sortable:false,		align:"center"},
					{display:"점검관리명", 	    name:"qcMngNm",				width:120,		sortable:false,		align:"center"},
					{display:"시행년도",		name:"enforceYear",			width:60,		sortable:false,		align:"left"},
					{display:"점검시작일자",    name:"qcBeginDt",			width:90,		sortable:false,		align:"left"},
					{display:"점검종료일자",	name:"qcEndDt",				width:90,		sortable:false,		align:"center"},
					{display:"점검진단구분",    name:"qcInspSe",			width:90,		sortable:false,		align:"left"},
					{display:"점검진단일자",	name:"qcInspDt",			width:120,		sortable:false,		align:"left"},
					{display:"점검진단기관명",	name:"qcInspInsttNm",		width:120,		sortable:false,		align:"left"},
					{display:"책임기술자명",	name:"responEngineerNm",	width:120,		sortable:false,		align:"left"},
					{display:"점검진단예산",	name:"qcInspBdgt",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단금액",	name:"qcInspAmt",			width:90,		sortable:false,		align:"center"},
					{display:"상태평가등급",	name:"sttusEvlLvl",			width:90,		sortable:false,		align:"center"},
					{display:"조치구분",		name:"actionSe",			width:60,		sortable:false,		align:"center"}
			],
		height: "auto"
	});

	this.$("#qcMngObjFcltsList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngObjFcltsList.do',
		dataType: 'json',
		colModel : [
					{display:"시설물관리번호",	name:"fcltsMngNo",	width:100,		sortable:true,		align:"center"},
					{display:"시설물명",		name:"prtFcltyNm",	width:150,		sortable:true,		align:"center"},
					{display:"점검진단구분",	name:"qcInspSe",	width:90,		sortable:true,		align:"left"},
					{display:"점검진단일자",	name:"qcInspDt",	width:100,		sortable:true,		align:"left"},
					{display:"점검자",		name:"inspector",	width:100,		sortable:true,		align:"left"},
					{display:"비고",			name:"rm",			width:350,		sortable:true,		align:"left"}
			],
		height: "auto"
	});

	this.$("#qcMngAtchFileList").flexigrid({
		module: this,
		url: '/fclty/selectQcMngAtchFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"제목",		name:"atchFileSj",			width:160,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",		width:160,		sortable:true,		align:"left"},
					{display:"물리파일명",	name:"atchFileNmPhysicl",	width:160,		sortable:true,		align:"left"},
					{display:"작성일시",	name:"atchFileWritingDt",	width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});

	this.$("#qcMngResultItemList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngResultItemList.do',
		dataType: 'json',
		colModel : [
					{display:"점검항목코드",	name:"qcItemCd",		width:100,		sortable:true,		align:"center"},
					{display:"점검항목",		name:"qcItem",			width:150,		sortable:true,		align:"center"},
					{display:"점검상위항목",	name:"qcItemUpper",		width:150,		sortable:true,		align:"center"},
					{display:"순번",			name:"seq",				width:90,		sortable:true,		align:"left"},
					{display:"점검항목결과구분",	name:"inspResultChk",	width:120,		sortable:true,		align:"left"}
			],
		height: "auto"
	});
	
};

//화면 및 데이터 초기화
GamFcltyQcwWrtMngModule.prototype.initDisplay = function() {
	
}

GamFcltyQcwWrtMngModule.prototype.onSubmit = function() {
	this.loadData();
}

//점검관리내역 조회
GamFcltyQcwWrtMngModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchFcltyQcwWrtMngForm");
	this.$("#qcMngDtlsList").flexOptions({params:searchOpt}).flexReload();	
}

//점검관리내역 데이터 조회
GamFcltyQcwWrtMngModule.prototype.loadDetailData = function() {
	var selectRows = this.$('#qcMngDtlsList').selectedRows();
	if(selectRows.length > 0) {
		var row = selectRows[0];
		var opts = [{name: 'fcltsMngNo', value: row['fcltsMngNo'] }];
		this.doAction('/fclty/selectCivilFcltySpecMngDetail.do', opts, function(module, result) { 
			if(result.resultCode == "0"){
				module.makeFormValues('#fcltyManageVO', result.result);
				module.$("#dispfcltsMngNo").text(module.$("#fcltsMngNo").val());
				module.loadFileData();
			}
			else {
				this._cmd="";
				module.initDisplay();
				alert(result.resultMsg);
			}
		});	
	}
}

/**
 * 정의 된 버튼 클릭 시
 */
GamFcltyQcwWrtMngModule.prototype.onButtonClick = function(buttonId) {
	var opts = null;
	switch(buttonId) {
		case "searchBtn": //조회
			this.initDisplay();
			this.loadData();
			break;
	}
};

GamFcltyQcwWrtMngModule.prototype.saveAtchFile = function(fcltsMngNo) {
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

GamFcltyQcwWrtMngModule.prototype.removeAtchFileItem = function() {
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
GamFcltyQcwWrtMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		break;
	case "tabs3":
		break;
	}
};

/**
 * 팝업 close 이벤트
 */
GamFcltyQcwWrtMngModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		// 조회화면
		case "selectGisCode":
			break;
			
		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyQcwWrtMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyQcwWrtMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>시설물업무구분</th>
							<td>
								<select id="sFcltsJobSe">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th>점검관리명</th>
							<td><input type="text" id="sQcMngNm" size="50" /></td>
							<td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>점검진단구분</th>
							<td>
								<select id="sQcInspSe">
									<option value="">선택</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th>점검기간</th>
							<td>
								<input id="sQcBeginDt" type="text" class="emdcal" size="15" /> ~ <input id="sQcEndDt" type="text" class="emdcal" size="15" />
							</td>
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
		<div id="fcltyQcwWrtMngTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">시설물점검목록</a></li>
				<li><a href="#tabs2" class="emdTab">시설물점검내역</a></li>
				<li><a href="#tabs3" class="emdTab">점검관리대상시설물</a></li>
				<li><a href="#tabs4" class="emdTab">점검관리첨부파일</a></li>
				<li><a href="#tabs5" class="emdTab">점검관리결과</a></li>
			</ul>
			
			<!-- 시설물점검목록 -->
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="qcMngDtlsList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnAdd">점검추가</button>
					<button id="btnDelete">점검삭제</button>
				</div>
			</div>

			<!-- 시설물점검내역 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyQcwWrtMngVO">
				<div style="margin-bottom:10px;">
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17">시설물관리그룹</th>
							<td colspan="3">
								<input type="text" size="14" id="" disabled="disabled"/>
								<input type="text" size="30" id="" disabled="disabled"/>
								<button id="searchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
							<th width="12%" height="17">점검관리순번</th>
							<td>
								<input type="text" size="30" id="" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">시설물업무구분</th>
							<td>
								<select id="a1">
                                    <option value="">선택</option>
                                </select>
							</td>
							<th width="12%" height="17">점검관리명</th>
							<td colspan="3">
								<input type="text" size="60" id="gisAssetsNm" />
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">점검진단일자</th>
							<td>
								<input type="text" size="2" id="gisPrtFcltyCd" disabled="disabled" />&nbsp;-&nbsp;
								<input type="text" size="3" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
							<th width="12%" height="17">점검진단구분</th>
							<td>
								<select id="aa">
                                    <option value="">선택</option>
                                </select>
							</td>
							<th width="12%" height="17">점검진단명</th>
							<td><input type="text" size="32" id="prtFcltyNm" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">책임기술자명</th>
							<td><input type="text" size="32" id="prtFcltyNm" maxlength="80" /></td>
							<th width="12%" height="17">점검시작일자</th>
							<td><input id="prtFcltyInstlDt" type="text" class="emdcal" size="20"/></td>
							<th width="12%" height="17">점검종료일자</th>
							<td colspan="3"><input id="prtFcltyChangeDt" type="text" class="emdcal" size="20"/></td>
						</tr>
						<tr>
							<th width="12%" height="17">점검진단예산</th>
							<td><input id="prtFcltyStndrd" type="text" size="20"/></td>
							<th width="12%" height="17">점검진단금액</th>
							<td><input id="prtFcltyUnit" type="text" size="20"/></td>
							<th width="12%" height="17">상태평가등급</th>
							<td><input id="prtFcltyUnit" type="text" size="10"/></td>
						</tr>
						<tr>
							<th width="12%" height="17">조치구분</th>
							<td colspan="5">
								<select id="aa">
                                    <option value="">선택</option>
                                </select>							
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">조치내용</th>
							<td colspan="5"><textarea id="prtFcltyStndrd" cols="120" rows="10"></textarea></td>
						</tr>
						<tr>
							<th width="12%" height="17">비고</th>
							<td colspan="5"><input id="prtFcltyUnit" type="text" size="110"/></td>
						</tr>
					</table>
				</div>
				</form>
				<div class="emdControlPanel">
					<button id="btnSave">저장</button>
				</div>
			</div>
			
			<!-- 점검관리대상시설물 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="qcMngObjFcltsList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnRemoveFile">추가/삭제</button>
					<button id="btnSave">저장</button>
				</div>
				<form id="fcltsFileForm">
				</form>
				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
			
			<!-- 점검관리첨부파일 -->
			<div id="tabs4" class="emdTabPage" style="overflow: scroll;">
				<table id="qcMngAtchFileList" style="display:none" class="fillHeight"></table>
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
			
			<!-- 점검관리결과 -->
			<div id="tabs5" class="emdTabPage" style="overflow: scroll;">
				<table id="qcMngResultItemList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnRemoveFile">추가/삭제</button>
					<button id="btnSave">저장</button>
				</div>
				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>			
		</div>
	</div>
</div>