<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyRepairMng.jsp
  * @Description : 시설물하자보수관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.20  HNJ          최초 생성
  *
  * author HNJ
  * since 2014.11.20
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="fcltyRepairMngVO" method="validateFcltyRepairMngVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<%
/******************************** SCRIPT START ********************************/
%>

<script>
<%
/**
 * @FUNCTION NAME : GamFcltyRepairMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltyRepairMngModule() {}

GamFcltyRepairMngModule.prototype = new EmdModule(1000,740);	// 초기 시작 창크기 지정

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.loadComplete = function(params) {
	this._mainmode = "";
	this._deleteObjFcltsList=[];
	this._deleteDataRepairList=[];
	this._deleteDataFileList=[];

	//console.log('GamFcltyRepairMngModule');

	this.$("#fcltyRepairMngList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyRepairMngList.do',
		dataType: "json",
		colModel : [
					{display:'선택<div id="'+this.getId('title_chkRole')+'" style="padding-right:3px"></div>',name:'chkRole', width:40, sortable:false, align:'center', displayFormat: 'checkbox', skipxls: true},
					{display:"시설물관리그룹",	name:"fcltsMngGroupNm",			width:160, 		sortable:false,		align:"left"},
					//{display:"계약번호",			name:"ctrtNo",					width:120, 		sortable:false,		align:"center"},
					{display:"업무구분",			name:"fcltsJobSeNm",			width:80, 		sortable:false,		align:"center"},
					{display:"공사명",			name:"flawRprNm",				width:250, 		sortable:false,		align:"left"},
					{display:"도급업체명",		name:"flawRprEntrpsNm",			width:250, 		sortable:false,		align:"left"},
					{display:"도급금액",			name:"flawContrAmt",			width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"계약일자",			name:"ctrtDt",					width:80, 		sortable:false,		align:"center"},
					{display:"준공일자",			name:"bldDt",				width:80, 		sortable:false,		align:"center"},
					{display:"하자검사구분",		name:"flawExamSeNm",			width:80, 		sortable:false,		align:"center"},
					{display:"하자검사일자",		name:"flawExamDt",				width:80, 		sortable:false,		align:"center"},
					{display:"하자발생일자",		name:"flawOccrrncDt",			width:80, 		sortable:false,		align:"center"},
					{display:"하자보수유형",		name:"flawRprTyNm",				width:80, 		sortable:false,		align:"center"},
					{display:"하자보수기간",		name:"flawRprTerm",				width:160, 		sortable:false,		align:"center"},
					{display:"하자보수금액",		name:"flawRprAmt",				width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"하자보수완료여부",	name:"flawRprComptYn",			width:120, 		sortable:false,		align:"center"}
			],
		height: "auto",
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			module.$('#sumFlawRprAmt').val($.number(data.sumFlawRprAmt));
			return data;
		}
	});

	this.$('#title_chkRole').append('');

	this.$("#flawRprObjFcltsF").flexigrid({
		module: this,
		url: '/fcltyMng/selectFlawRprObjFcltsF.do',
		dataType: 'json',
		colModel : [
					{display:"순번",			name:"rnum",			width:50,		sortable:false,		align:"center"},
					{display:"대상시설물",		name:"prtFcltyNm",		width:250,		sortable:false,		align:"left"},
					{display:"하자유무",		name:"flawEnnc",		width:80,		sortable:true,		align:"center"},
					{display:"하자검사일자",	name:"flawExamDt",		width:100,		sortable:true,		align:"center"},
					{display:"하자검사결과",	name:"flawExamResult",	width:460,		sortable:true,		align:"left"}
			],
		height: "200"
	});


	this.$("#fcltyRepairFileList").flexigrid({
		module : this,
		url : '/fcltyMng/selectFcltyRepairFileList.do',
		dataType : 'json',
		colModel : [
					{display:"번호",		name:"atchFileSeq",			width:65,		sortable:false,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:90,		sortable:false,		align:"center"},
					{display:"파일명",		name:"atchFileNmLogic",		width:300,		sortable:false,		align:"left"}
					],
		height: "330",

	});
	this.$("#fcltyRepairFileList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.showFcltsAtchFileViewPopup();
	});
	this.$("#fcltyRepairFileList").on('onItemSelected', function(event, module, row, grid, param) {
		/* module.$("#atchFileSeq").val(row['atchFileSeq']);
		console.log(module.$("#atchFileSeq").val()); */
		module.makeFormValues("#fileListForm", row);
	});
 	this.$("#fcltyRepairMngList").on('onItemSelected', function(event, module, row, grid, param) {
 		module._mainmode = 'modify';
		module._mainFcltsMngGroupNo = row.fcltsMngGroupNo;
		module._mainFcltsJobSe = row.fcltsJobSe;
		module._mainFlawRprSeq = row.flawRprSeq;
	});

 	this.$("#fcltyRepairMngList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module._mainmode="modify";
		module._mainFcltsMngGroupNo = row.fcltsMngGroupNo;
		module._mainFcltsJobSe = row.fcltsJobSe;
		module._mainFlawRprSeq = row.flawRprSeq;
		module.$("#fcltyRepairMngListTab").tabs("option", {active: 1});
	});

 	this.$("#flawRprObjFcltsF").on('onItemSelected', function(event, module, row, grid, param) {
 		module.applyObjDataChanged();
	});


 	this.$(".objFcltsEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.objFcltsDataChanged(event.target);
	});

	this.$("#printSe").bind("change keyup", {module: this}, function(event) {
		event.data.module.setPrintSe();
	});

	this._mainmode = "query";
	this._mainFcltsMngGroupNo = "";
	this._mainFcltsJobSe = "";
	this._mainFlawRprSeq = "";

	if (EMD.userinfo.mngFcltyCd != null && EMD.userinfo.mngFcltyCd != "*") {
		this.$('#sFcltsJobSe').val(EMD.userinfo.mngFcltyCd);
		this.$('#sFcltsJobSe').disable();

	}
	// 기본값 셋팅
	this.setDefaultParam();
	this.applySelectYear();
	this.getMapInfoList(params);

};

GamFcltyRepairMngModule.prototype.showFcltsAtchFileViewPopup = function() {

	var row = this.$('#fcltyRepairFileList').selectedRows()[0];
	var selImg = row['atchFileNmPhysicl'];
	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var fcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
	var flawRprSeq = this.$('#flawRprSeq').val();
	var atchFileSeq = this.$('#photoAtchFileSeq').val();
	if (atchFileSeq == "") {
		alert("사진을 저장하셔야 파일설명을 입력하실수 있습니다.");
		return;
	}
	if (selImg != "") {
		var imageURL = this.getUrl("/fcltyMng/fdown/getRepairAttachFile.do?physicalFileNm=")+selImg;
	}
    var searchOpts = {
		'atchFileSeq':atchFileSeq,
		'fcltsJobSe': fcltsJobSe,
		'fcltsMngGroupNo' : fcltsMngGroupNo ,
		'flawRprSeq' : flawRprSeq,
		'imageURL':imageURL

    };
	this.doExecuteDialog('popupFcltsAtchFileView', '하자보수 첨부파일 보기', '/popup/showRepairMngFileViewPopup.do', null, searchOpts);

};

<%
/**
 * @FUNCTION NAME : setPrintSe
 * @DESCRIPTION   : 셀렉트박스에 선택된 출력구분에 따른 url 세팅
 * @PARAMETER     : none

**/
%>

GamFcltyRepairMngModule.prototype.setPrintSe = function(){

	if (this.$("#printSe").val() == 'print'){
		this.$("#mngPrint").data('url','/fcltyMng/selectFcltyRepairCheckMngPrint.do');
		this.$("#mngPrint").data('role','printPage');
		this.$("#chkPrint").data('url','/fcltyMng/selectFcltyRepairCheckReportPrint.do');
		this.$("#chkPrint").data('role','printPage');
		this.$("#expPrint").data('url','/fcltyMng/selectFcltyRepairExpireCheckReportPrint.do');
		this.$("#expPrint").data('role','printPage');
	} else if (this.$("#printSe").val() == 'hwp'){
		this.$("#mngPrint").data('url','/fcltyMng/selectFcltyRepairCheckMngHwp.do');
		this.$("#mngPrint").data('role','printDown');
		this.$("#mngPrint").data('filename','검사관리대장.hwp');

		this.$("#chkPrint").data('url','/fcltyMng/selectFcltyRepairCheckReportHwp.do');
		this.$("#chkPrint").data('role','printDown');

		//this.$("#chkPrint").data('url','/fcltyMng/selectFcltyRepairCheckReportHwp2.do');
		//this.$("#chkPrint").data('role','hwpPage');

		this.$("#chkPrint").data('filename','하자검사조서.hwp');
		this.$("#expPrint").data('url','/fcltyMng/selectFcltyRepairExpireCheckReportHwp.do');
		this.$("#expPrint").data('role','printDown');
		this.$("#expPrint").data('filename','만료검사조서.hwp');
	}

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
GamFcltyRepairMngModule.prototype.getMapInfoList = function(params){

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
GamFcltyRepairMngModule.prototype.setDefaultParam = function(){
	var toDate = new Date();
	var toYear = toDate.getFullYear();

	var toMonth = toDate.getMonth() + 1;
	if(toMonth < 10) toMonth = "0" + toMonth;

	var toDay = toDate.getDate();
	if(toDay < 10) toDay = "0" + toDay;

	this.$("#sFlawRprStartDtFr").val(toYear + "-01-01");
	this.$("#sFlawRprStartDtTo").val(toYear + "-" + toMonth + "-" + toDay);

	this.$("#sFcltsJobSe").val(EMD.userinfo["mngFcltyCd"]);
};


<%
/**
 * @FUNCTION NAME : applySelectYear
 * @DESCRIPTION   : Select Element에 2000년 부터 현재년도까지 채워 넣는 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.applySelectYear = function(){
	var toDate = new Date();
	var toYear = toDate.getFullYear();

	for(var i = toYear;i>=1980;i--){
		this.$("#enforceYear").append("<option value='" + i + "'>" + i + "년</option>");
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
GamFcltyRepairMngModule.prototype.validateDuration = function(startDate, endDate, startTitle, endTitle, startIgnore, endIgnore, equals) {
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
GamFcltyRepairMngModule.prototype.onSubmit = function(){
	if(!this.validateDuration(this.$('#sFlawRprStartDtFr').val(), this.$('#sFlawRprStartDtTo').val(),
			'하자검사기간조회 시작일', '하자검사기간조회 종료일',  true,  true, true)) {
		return;
	}
	this._mainmode = "query";
	this._mainFcltsMngGroupNo = "";
	this._mainFcltsJobSe = "";
	this._mainFlawRprSeq = "";
	this.loadData();
};


<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.loadData = function(){

	// tabs2 항목 초기화
	this.makeFormValues('#fcltyRepairMngListVO', {});
	this.$("#fcltyRepairFileList").flexEmptyData();
	// tabs3 그리드 초기화
	this.$('#flawRprObjFcltsF').flexEmptyData();
	this.$('#gamObjFcltsForm input').val('');
	this.$('#gamObjFcltsForm textarea').val('');
	this.makeDivValues('#gamObjFcltsDetailForm',{});

	this.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchFcltyRepairMngForm');
	this.$('#fcltyRepairMngList').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : DATA LOAD (DETAIL)
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.loadDetail = function(){
	var row = this.$('#fcltyRepairMngList').selectedRows();
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
		return;
	}

	row = row[0];
	var searchVO = {
            'fcltsJobSe': row['fcltsJobSe'],
            'fcltsMngGroupNo': row['fcltsMngGroupNo'],
            'flawRprSeq': row['flawRprSeq']
	};

	// tabs2 항목 데이타로딩
	this.doAction('/fcltyMng/selectFcltyRepairMngDetail.do', searchVO, function(module, result) {
		if(result.resultCode == "0"){
			module.makeFormValues('#fcltyRepairMngListVO', result.result);

			// tabs3 그리드 리로드
			module.makeDivValues('#gamObjFcltsDetailForm',result.result);
			module.$('#flawRprObjFcltsF').flexOptions({params:searchVO}).flexReload();
			module.$('#fcltyRepairFileList').flexOptions({params:searchVO}).flexReload();
			//			module.fillAtchFileList(searchVO);
			// 인쇄 구분
			module.setPrintSe();
		}else{
			module.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
		}
    });
};

<%
/**
 * @FUNCTION NAME : firstData
 * @DESCRIPTION   : FIRST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.firstData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainFcltsMngGroupNo == "" || this._mainFcltsJobSe == "" || this._mainFlawRprSeq == "") {
		return;
	}
	var rows = this.$("#fcltyRepairMngList").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		return;
	}
	var firstRowIndex = 0;
	var row = rows[firstRowIndex];
	var fcltsMngGroupNo = row["fcltsMngGroupNo"];
	var fcltsJobSe = row["fcltsJobSe"];
	var flawRprSeq = row["flawRprSeq"];
	if (fcltsMngGroupNo != "" && fcltsJobSe != "" && flawRprSeq != "") {
		this.$("#fcltyRepairMngList").selectFilterRow([{col:"fcltsMngGroupNo", filter:fcltsMngGroupNo},
													   {col:"fcltsJobSe", filter:fcltsJobSe},
													   {col:"flawRprSeq", filter:flawRprSeq}]);
		this._mainmode = 'modify';
		this._mainFcltsMngGroupNo = fcltsMngGroupNo;
		this._mainFcltsJobSe = fcltsJobSe;
		this._mainFlawRprSeq = flawRprSeq;
		this.loadDetail();
	}

};

<%
/**
 * @FUNCTION NAME : prevData
 * @DESCRIPTION   : PREVIOUS DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.prevData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainFcltsMngGroupNo == "" || this._mainFcltsJobSe == "" || this._mainFlawRprSeq == "") {
		return;
	}
	var rows = this.$("#fcltyRepairMngList").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var prevRowIndex = -1;
	var fcltsMngGroupNo = "";
	var fcltsJobSe = "";
	var flawRprSeq = "";
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		fcltsMngGroupNo = row["fcltsMngGroupNo"];
		fcltsJobSe = row["fcltsJobSe"];
		flawRprSeq = row["flawRprSeq"];
		if (this._mainFcltsMngGroupNo == fcltsMngGroupNo && this._mainFcltsJobSe == fcltsJobSe && this._mainFlawRprSeq == flawRprSeq) {
			prevRowIndex = i - 1;
			break;
		}
	}
	if (prevRowIndex < 0) {
		alert("첫번째 자료 입니다!");
		return;
	}
	if (prevRowIndex >= gridRowCount) {
		alert("자료 위치가 부정확합니다!");
		return;
	}
	var row = rows[prevRowIndex];
	fcltsMngGroupNo = row["fcltsMngGroupNo"];
	fcltsJobSe = row["fcltsJobSe"];
	flawRprSeq = row["flawRprSeq"];
	if (fcltsMngGroupNo != "" && fcltsJobSe != "" && flawRprSeq != "") {
		this.$("#fcltyRepairMngList").selectFilterRow([{col:"fcltsMngGroupNo", filter:fcltsMngGroupNo},
													   {col:"fcltsJobSe", filter:fcltsJobSe},
													   {col:"flawRprSeq", filter:flawRprSeq}]);
		this._mainmode = 'modify';
		this._mainFcltsMngGroupNo = fcltsMngGroupNo;
		this._mainFcltsJobSe = fcltsJobSe;
		this._mainFlawRprSeq = flawRprSeq;
		this.loadDetail();
	}

};

<%
/**
 * @FUNCTION NAME : nextData
 * @DESCRIPTION   : NEXT DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.nextData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainFcltsMngGroupNo == "" || this._mainFcltsJobSe == "" || this._mainFlawRprSeq == "") {
		return;
	}
	var rows = this.$("#fcltyRepairMngList").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var nextRowIndex = -1;
	var fcltsMngGroupNo = "";
	var fcltsJobSe = "";
	var flawRprSeq = "";
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		fcltsMngGroupNo = row["fcltsMngGroupNo"];
		fcltsJobSe = row["fcltsJobSe"];
		flawRprSeq = row["flawRprSeq"];
		if (this._mainFcltsMngGroupNo == fcltsMngGroupNo && this._mainFcltsJobSe == fcltsJobSe && this._mainFlawRprSeq == flawRprSeq) {
			nextRowIndex = i + 1;
			break;
		}
	}
	if (nextRowIndex < 0) {
		alert("자료 위치가 부정확합니다!");
		return;
	}
	if (nextRowIndex >= gridRowCount) {
		alert("마지막 자료 입니다!");
		return;
	}
	var row = rows[nextRowIndex];
	fcltsMngGroupNo = row["fcltsMngGroupNo"];
	fcltsJobSe = row["fcltsJobSe"];
	flawRprSeq = row["flawRprSeq"];
	if (fcltsMngGroupNo != "" && fcltsJobSe != "" && flawRprSeq != "") {
		this.$("#fcltyRepairMngList").selectFilterRow([{col:"fcltsMngGroupNo", filter:fcltsMngGroupNo},
													   {col:"fcltsJobSe", filter:fcltsJobSe},
													   {col:"flawRprSeq", filter:flawRprSeq}]);
		this._mainmode = 'modify';
		this._mainFcltsMngGroupNo = fcltsMngGroupNo;
		this._mainFcltsJobSe = fcltsJobSe;
		this._mainFlawRprSeq = flawRprSeq;
		this.loadDetail();
	}

};

<%
/**
 * @FUNCTION NAME : lastData
 * @DESCRIPTION   : LAST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.lastData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainFcltsMngGroupNo == "" || this._mainFcltsJobSe == "" || this._mainFlawRprSeq == "") {
		return;
	}
	var rows = this.$("#fcltyRepairMngList").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var lastRowIndex = gridRowCount - 1;
	var row = rows[lastRowIndex];
	var fcltsMngGroupNo = row["fcltsMngGroupNo"];
	var fcltsJobSe = row["fcltsJobSe"];
	var flawRprSeq = row["flawRprSeq"];
	if (fcltsMngGroupNo != "" && fcltsJobSe != "" && flawRprSeq != "") {
		this.$("#fcltyRepairMngList").selectFilterRow([{col:"fcltsMngGroupNo", filter:fcltsMngGroupNo},
													   {col:"fcltsJobSe", filter:fcltsJobSe},
													   {col:"flawRprSeq", filter:flawRprSeq}]);
		this._mainmode = 'modify';
		this._mainFcltsMngGroupNo = fcltsMngGroupNo;
		this._mainFcltsJobSe = fcltsJobSe;
		this._mainFlawRprSeq = flawRprSeq;
		this.loadDetail();
	}

};

<%-- <%
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
GamFcltyRepairMngModule.prototype.fillAtchFileList = function(searchVO) {
	this.doAction('/fcltyMng/selectFcltyRepairFileList.do', searchVO, function(module, result) {
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
}; --%>


<%
/**
 * @FUNCTION NAME : imgPreview
 * @DESCRIPTION   : 선택한 첨부파일이 이미지이면 미리보기 보여주는 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.imgPreview = function(){

	var row = this.$('#fcltyRepairFileList').selectedRows()[0];
	var selImg = row['atchFileNmPhysicl'];
	console.log(selImg);
	if(selImg !=null || selImg != '' ) {
		// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
		var ext = selImg.substring(selImg.lastIndexOf(".")+1).toLowerCase();

		if(ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif"){
			this.$('#previewHidden').append('<div id="'+this.getId("previewDialog")+'"><img id="'+this.getId("previewImage")+'" src=""/></div>');
			var imgURL = this.getUrl("/fcltyMng/fdown/getRepairAttachFile.do?physicalFileNm=")+selImg;
			this.$("#previewImage").attr("src", imgURL);

			this.$("#previewImage").bind('load', {module: this}, function(event){
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
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : tabs1에서 추가 버튼 클릭시
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.addData = function() {

	this._mainmode="insert";
	this._mainFcltsMngGroupNo = "";
	this._mainFcltsJobSe = "";
	this._mainFlawRprSeq = "";

	// tabs2 초기화
	this.makeFormValues('#fcltyRepairMngListVO', {});
	this.$("#searchFcltsMngGroupNo").show();
	this.$("#fcltsJobSe").enable();
	this.$("#fcltsJobSe").val(EMD.userinfo["mngFcltyCd"]);
	var toYear = new Date().getFullYear();
	this.$("#enforceYear").val(toYear);
	this.$("#fcltyRepairFileList").flexEmptyData();

	// tabs3 초기화
	this.$("#flawRprObjFcltsF").flexEmptyData();

	this.$("#fcltyRepairMngListTab").tabs("option", {active: 1});

};


<%
/**
 * @FUNCTION NAME : deleteData
 * @DESCRIPTION   : 선택 DATA 삭제
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.deleteData = function() {

	var row = this.$('#fcltyRepairMngList').selectedRows();
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
		return;
	}

	if(confirm("삭제하시겠습니까?")){
		row = row[0];
		var inputVO = { 'fcltsJobSe': row['fcltsJobSe'],'fcltsMngGroupNo': row['fcltsMngGroupNo'],'flawRprSeq': row['flawRprSeq'] };
	 	this.doAction('/fcltyMng/deleteFcltyRepairMng.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module._mainmode = "query";
	 			module._mainFcltsMngGroupNo = "";
	 			module._mainFcltsJobSe = "";
	 			module._mainFlawRprSeq = "";
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	};

};


<%
/**
 * @FUNCTION NAME : applyObjDataChanged
 * @DESCRIPTION   : 하자보수대상시설물 그리드 선택시 하위폼 데이타 처리
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.applyObjDataChanged = function(){

	var row = this.$('#flawRprObjFcltsF').selectedRows();
	row = row[0];

	if(row['_updtId']!='I'){
		this.$('#searchFcltsMngNo').hide();
	}else{
		this.$('#searchFcltsMngNo').show();
	}

	this.$('#oFcltsMngNo').val(row['fcltsMngNo']);
	this.$('#prtFcltyNm').val(row['prtFcltyNm']);
	this.$('#oFlawExamDt').val(row['flawExamDt']);
	this.$('#oFlawEnnc').val(row['flawEnnc']);
	this.$('#oFlawExamResult').val(row['flawExamResult']);
	this.$('#oRm').val(row['rm']);

};


<%
/**
 * @FUNCTION NAME : objFcltsDataChanged
 * @DESCRIPTION   : 하자보수대상시설물 하위폼 정보변경시 그리드 적용
 * @PARAMETER     : target
**/
%>
GamFcltyRepairMngModule.prototype.objFcltsDataChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#flawRprObjFcltsF').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#oFcltsMngNo').is(target)) {
			row['fcltsMngNo'] = $(target).val();
			changed=true;
		}

		if(this.$('#prtFcltyNm').is(target)) {
			row['prtFcltyNm'] = $(target).val();
			changed=true;
		}

		if(this.$('#oFlawExamDt').is(target)) {
			row['flawExamDt'] = $(target).val();
			changed=true;
		}
		if(this.$('#oFlawEnnc').is(target)) {
			row['flawEnnc'] = $(target).val();
			changed=true;
		}
		if(this.$('#oRm').is(target)) {
			row['rm'] = $(target).val();
			changed=true;
		}
		if(this.$('#oFlawExamResult').is(target)) {
			row['flawExamResult'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#flawRprObjFcltsF").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#flawRprObjFcltsF').flexUpdateRow(rowid, row);
	}
};


<%
/**
 * @FUNCTION NAME : addObjFcltsItem
 * @DESCRIPTION   : 하자보수 대상 시설물 추가
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.addObjFcltsItem = function() {
	this.$('#gamObjFcltsForm :input').val('');
	this.$('#searchFcltsMngNo').show();
	this.$("#flawRprObjFcltsF").flexAddRow({'_updtId': 'I', 'fcltsMngGroupNo':'', 'fcltsJobSe':'', 'flawRprSeq':'', 'fcltsMngNo':'', 'flawEnnc':'', 'flawExamDt':'', 'flawExamResult':'', 'rm':''});
	var allRows = this.$('#flawRprObjFcltsF').flexGetData();
	console.log(allRows);
	var selRowId = allRows.length - 1;
	this.$("#flawRprObjFcltsF").selectRowId(selRowId);
};


<%
/**
 * @FUNCTION NAME : delObjFcltsItem
 * @DESCRIPTION   : 하자보수 대상 시설물 데이터 삭제
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.delObjFcltsItem = function() {
	var rows = this.$("#flawRprObjFcltsF").selectedRows();
    if(rows.length == 0){
        alert("하자보수대상 시설물목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#flawRprObjFcltsF").selectedRowIds().length>0) {
    	for(var i=this.$("#flawRprObjFcltsF").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#flawRprObjFcltsF").flexGetRow(this.$("#flawRprObjFcltsF").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteObjFcltsList[this._deleteObjFcltsList.length] = row;
			}
        	this.$("#flawRprObjFcltsF").flexRemoveRow(this.$("#flawRprObjFcltsF").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamObjFcltsForm").find(":input").val("");
};


<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : DATA 저장
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.saveData = function() {

	if(!validateFcltyRepairMngVO(this.$("#fcltyRepairMngListVO")[0])){
		this.$("#fcltyRepairMngListTab").tabs("option", {active: 1});
		return;
	}

	if(!this.validateDuration(this.$('#enforceYear').val() + '-01-01', this.$('#flawExamDt').val(),
			'시행년도', '하자검사일자', false, true, true)) {
		return;
	}

	if(!this.validateDuration(this.$('#enforceYear').val() + '-01-01', this.$('#flawOccrrncDt').val(),
			'시행년도', '하자발생일자', false, true, true)) {
		return;
	}

	if(!this.validateDuration(this.$('#enforceYear').val() + '-01-01', this.$('#flawRprStartDt').val(),
			'시행년도', '하자보수시작일', false, true, true)) {
		return;
	}

	if(!this.validateDuration(this.$('#flawExamDt').val(), this.$('#flawOccrrncDt').val(),
			'하자검사일자', '하자발생일자',  true, true, true)) {
		return;
	}

	if(!this.validateDuration(this.$('#flawExamDt').val(), this.$('#flawRprStartDt').val(),
			'하자검사일자', '하자보수시작일',  true, true, true)) {
		return;
	}

	if(!this.validateDuration(this.$('#flawOccrrncDt').val(), this.$('#flawRprStartDt').val(),
			'하자발생일자', '하자보수시작일',  true, true, false)) {
		return;
	}

	if(!this.validateDuration(this.$('#flawRprStartDt').val(), this.$('#flawRprEndDt').val(),
			'하자보수시작일', '하자보수종료일',  false, false, true)) {
		return;
	}


	var inputVO = this.makeSaveParam();

	if(this._mainmode == "insert") {
	 	this.doAction('/fcltyMng/insertFcltyRepairMng.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module.$("#fcltsJobSe").disable();
	 			module.$("#searchFcltsMngGroupNo").hide();
	 			module.$("#flawRprSeq").val(result.flawRprSeq);

	 			module._mainmode = "modify";
	 			module._mainFcltsMngGroupNo = module.$("#fcltsMngGroupNo").val();
	 			module._mainFcltsJobSe = module.$("#fcltsJobSe").val();
	 			module._mainFlawRprSeq = module.$("#flawRprSeq").val();
	 		}
	 		alert(result.resultMsg);
	 		//module.loadDetail();
	 		//this.$("#fcltyRepairMngListTab").tabs("option", {active: 1});
	 	});
	}else{
	 	this.doAction('/fcltyMng/updateFcltyRepairMng.do', inputVO, function(module, result) {

	 		if(result.resultCode == "0"){
	 		module.loadDetail();
	 		this.$("#fcltyRepairMngListTab").tabs("option", {active: 1});
	 			}
	 		alert(result.resultMsg);
	 	});

	 	};

};


<%
/**
 * @FUNCTION NAME : makeSaveParam
 * @DESCRIPTION   : 입력 수정에 필요한 DATA 가공
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.makeSaveParam = function() {

	var inputVO = [];
 	inputVO[inputVO.length] = {name: 'fcltyRepairMngListVO', value :JSON.stringify(this.makeFormArgs("#fcltyRepairMngListVO",'object')) };
 	inputVO[inputVO.length]=  {name: 'insertRepairFileList', value: JSON.stringify(this.$('#fcltyRepairFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

 	if(this._deleteRepairFileList == undefined ) {
         this._deleteRepairFileList=[];
     }
 	inputVO[inputVO.length]=  {name: 'deleteRepairFileList', value: JSON.stringify(this._deleteRepairFileList)};

 	// 조건은 수정시에만 필요한 데이타 형식
 	if(this._mainmode == "modify") {
 		var all_rows = this.$('#flawRprObjFcltsF').flexGetData();
 		for(var i=0;i<all_rows.length;i++){
 			all_rows[i]["fcltsJobSe"] = this.$("#fcltsJobSe").val();
 			all_rows[i]["fcltsMngGroupNo"] = this.$("#fcltsMngGroupNo").val();
 			all_rows[i]["flawRprSeq"] = this.$("#flawRprSeq").val();
 		}

		inputVO[inputVO.length]={name: 'updateObjList', value :JSON.stringify(this.$('#flawRprObjFcltsF').selectFilterData([{col: '_updtId', filter: 'U'}])) };
	 	inputVO[inputVO.length]={name: 'deleteObjList', value: JSON.stringify(this._deleteObjFcltsList) };
 	}
 	inputVO[inputVO.length] = {name: 'insertObjList', 		 value: JSON.stringify(this.$('#flawRprObjFcltsF').selectFilterData([{col: '_updtId', filter: 'I'}])) };

	return inputVO;
};


<%
/**
 * @FUNCTION NAME : makeSelectArgs
 * @DESCRIPTION   : 셀렉트 option value, text json 생성함수 - 여기서는 첨부파일
 * @PARAMETER     : SELECT_ID
**/
%>
GamFcltyRepairMngModule.prototype.makeSelectArgs = function(selId) {
	var optionValues = [];
	this.$(selId + ' option').each(function() {
		if($(this).val()){
			optionValues.push({"atchFileNmPhysicl":$(this).val(), "atchFileNmLogic":$(this).eq(0).text()});
		}
	});

	return optionValues;
};


<%
/**
 * @FUNCTION NAME : atchFileUpload
 * @DESCRIPTION   : 첨부파일 업로드
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.atchFileUpload = function() {
	this.uploadSingleFile('/fcltyMng/uploadRepairAttachFile.do', function(module, resp) {
		if(resp.resultCode!=0) {
			alert(resp.resultMsg);
			return;
		}
		$.each(resp.result, function() {
        	module.$('#fcltyRepairFileList').flexAddRow({
        		_updtId: 'I',
        		atchFileSj : "",
        		atchFileSeNm : "",
        		atchFileNmLogic: this.logicalFileNm,
        		atchFileNmPhysicl: this.physcalFileNm
    		});
		});
		if(resp.result!=null && resp.result.length>0) this._edited=true;
	});
};


<%
/**
 * @FUNCTION NAME : atchFileRemove
 * @DESCRIPTION   : 첨부파일제거
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.atchFileRemove = function() {
    var rows = this.$('#fcltyRepairFileList').selectedRows();

    if(rows.length == 0) {
        alert("파일목록에서 삭제할 행을 선택하십시오.");
    } else {
        if(this.$('#fcltyRepairFileList').selectedRowIds().length>0) {
            for(var i=this.$('#fcltyRepairFileList').selectedRowIds().length-1; i>=0; i--) {
                var row=this.$('#fcltyRepairFileList').flexGetRow(this.$('#fcltyRepairFileList').selectedRowIds()[i]);
                //alert( row._updtId );

                if(row._updtId==undefined || row._updtId!='I') {
                    this._deleteRepairFileList[this._deleteRepairFileList.length]=row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
                }
                this.$('#fcltyRepairFileList').flexRemoveRow(this.$('#fcltyRepairFileList').selectedRowIds()[i]);
                this.$("#previewImage").attr('src', '');
            }
        }
    }

    //this.$('#gamAssetRentFileForm').find(':input').val('');
    this._editDataFile = null;
};


<%
/**
 * @FUNCTION NAME : downloadFileData
 * @DESCRIPTION   : 파일다운로드 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.downloadFileData = function() {

	var selectRow = this.$('#fcltyRepairFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		console.log(row["atchFileNmPhysicl"],row["atchFileNmLogic"]);
		this.downloadSingleFile("/fcltyMng/downloadRepairAttachFile.do", row["atchFileNmPhysicl"],row["atchFileNmLogic"]);
	};

};



<%
/**
 * @FUNCTION NAME : fillTitleData
 * @DESCRIPTION   : 대상시설물 탭에 상단 내용 채우는 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.fillTitleData = function() {
	var changData = this.makeFormArgs('#fcltyRepairMngListVO');
	this.makeFormValues('#gamObjFcltsDetailForm',changData);
	this.makeDivValues('#gamObjFcltsDetailForm',changData);

	// 셀렉트박스는 한글처리
	var fcltsJobSeNm = this.$('#fcltsJobSe').find('option:selected').text();
	var flawExamSeNm = this.$('#flawExamSe').find('option:selected').text();

	this.$('#objFcltsJobSeNm').text(fcltsJobSeNm);
	this.$('#objFlawExamSeNm').text(flawExamSeNm);

};


<%
/**
 * @FUNCTION NAME : existRepairFcltsItem
 * @DESCRIPTION   : 유지보수 대상시설물 존재유무 체크
 * @PARAMETER     :
 * 		1. fcltsMngNo : 시설물관리번호
**/
%>
GamFcltyRepairMngModule.prototype.existRepairFcltsItem = function(fcltsMngNo) {
	var rows = this.$('#flawRprObjFcltsF').flexGetData();
	var result = false;
	if(rows.length > 0) {
		var row = null;
		for(var i=0; i<rows.length; i++) {
			row = rows[i];
			if(row['fcltsMngNo'] == fcltsMngNo) {
				result = true;
				break;
			}
		}
	}
	return result;
};


<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 그리드리스트 다운로드 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.downloadExcel = function() {

	var rowCount = this.$('#fcltyRepairMngList').flexRowCount();
	if (rowCount <= 0) {
		alert('조회된 자료가 없습니다.');
		return;
	}
	this.$('#fcltyRepairMngList').flexExcelDown('/fcltyMng/selectFcltyRepairMngListExcel.do');

};

<%
/**
 * @FUNCTION NAME : selectedHwpDownload
 * @DESCRIPTION   : 선택한 하자데이터 한글문서 작성 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.selectedHwpDownload = function() {
	var filter = [{ 'col': 'chkRole', 'filter': true}];
	var downList = this.$("#fcltyRepairMngList").selectFilterData(filter);

	if (downList.length <= 0) {
		alert('다운로드할 항목을 선택 하십시요');
		return;
	}

	var url = '/fcltyMng/selectFcltyRepairCheckReportListHwp.do';
	var param = {};
	param['downList'] = JSON.stringify(downList);
	param['filename'] = '하자검사조서리스트.hwp';
	$.fileDownload(EMD.context_root+url, {data:param, httpMethod:"POST"});
};


<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamFcltyRepairMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 대상시설물 추가
		case "addObjItemBtn":
			this.addObjFcltsItem();
		break;

		// 대상시설물 삭제
		case "delObjItemBtn":
			this.delObjFcltsItem();
		break;

		// 추가
		case "addBtn":
			this.addData();
		break;

		// 저장
		case "saveBtn":
			this.saveData();


		break;

		// 삭제
		case "deleteBtn":
			this.deleteData();
		break;

		// 이미지미리보기
		case "btnPreviewFile":
			this.imgPreview();
		break;

		// 파일업로드
		case "btnUploadFile":
			this.atchFileUpload();
		break;

		// 파일다운로드
		case "btnDownloadFile":
			this.downloadFileData();
		break;

		// 파일삭제
		case "btnRemoveFile":
			this.atchFileRemove();
		break;

		// 엑셀다운로드
		case "btnExcelDownload":
			this.downloadExcel();
		break;

		// 대상시설물
		case "searchFcltsMngNo":
			this.doExecuteDialog("selectFcltsMngNo", "대상시설물 관리번호", '/popup/showFcltsMngNo.do', {}, {'fcltsJobSe' : this.$('#fcltsJobSe').val()});
		break;

		// 시설물관리그룹
		case "searchFcltsMngGroupNo":
			this.doExecuteDialog("selectFcltsMngGroup", "시설물 관리 그룹 번호", '/popup/showFcltsMngGroup.do', {});
		break;

		// 계약번호
		case "ctrtNoPopupBtn":
			this.doExecuteDialog("selectCtrtNo", "계약번호", '/popup/popupCtrtNo.do', {});
		break;

		// 선택데이터 한글문서 다운로드
		case 'btnSelectedHwpDownload':
			this.selectedHwpDownload();
			break;

	    case 'btnFirstData':
	    	this.firstData();
			break;
	    case 'btnPrevData':
	    	this.prevData();
			break;
	    case 'btnNextData':
	    	this.nextData();
			break;
	    case 'btnLastData':
	    	this.lastData();
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
GamFcltyRepairMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._mainmode == 'modify') {
		this.loadDetail();
	}

	switch(newTabId) {
		case "tabs1":

		break;

		case "tabs2":
			if((this._mainmode != 'insert') && (this._mainmode != 'modify')) {
				this.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
				alert('하자보수 항목을 선택 하세요.');
			}

			if(oldTabId == 'tabs1') {
				this.$("#tabs2").scrollTop(0);
				this._deleteRepairFileList=[];    // 파일삭제 목록 초기화
			}
			if(this._mainmode=="modify"){
				this.$("#searchFcltsMngGroupNo").hide();
				this.$("#fcltsJobSe").disable();
			}
		break;

		case "tabs3":
			if((this._mainmode != 'insert') && (this._mainmode != 'modify')) {
				this.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
				alert('하자보수 항목을 선택 하세요.');
			}
			if(this._mainmode=="modify"){
				// tabs2에서 수정사항발생시 반영
				this.fillTitleData();
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
GamFcltyRepairMngModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){

		case "selectFcltsMngNo":
			if(this.existRepairFcltsItem(value['fcltsMngNo'])) {
				alert('대상시설물이 이미 존재합니다.');
			} else {
				this.$("#oFcltsMngNo").val(value["fcltsMngNo"]);
				this.$("#prtFcltyNm").val(value["prtFcltyNm"]);

				// 대상시설물 팝업에서 상태값 변경시 그리드 적용
				this.$(".objFcltsEditItem").trigger("change");
			}
		break;

		case "selectFcltsMngGroup":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
		break;

		case "selectCtrtNo":
			this.$("#ctrtNo").val(value["ctrtNo"]);
			this.$("#flawRprNm").val(value["ctrtNm"]);
			this.$("#flawRprEntrpsNm").val(value["entrpsNm"]);
			this.$("#ctrtDt").val(value["ctrtDt"]);
			this.$("#flawContrAmt").val(value["ctrtAmt"]);
			//this.$("#bldDt").val(value["bldDt"]);
		break;

		case 'popupFcltsAtchFileView':
			if (msg == 'ok') {
				this.loadDetail();
/*******************************************************************************
				var atchFileNo = this.$('#atchFileNo').val();
				if (atchFileNo == value.atchFileNo) {
					this.$('#atchFileSe').val(value.atchFileSe);
					this.$('#atchFileSeNm').val(value.atchFileSeNm);
					this.$('#atchFileSj').val(value.atchFileSj);
					this.$('#atchFileRm').val(value.atchFileRm);
					var selectRow = this.$('#fcltyRepairFileList').selectedRows();
					if(selectRow.length > 0) {
						var row = selectRow[0];
						row['atchFileSeNm'] = value.atchFileSeNm;
						row['atchFileSe'] = value.atchFileSe;
						row['atchFileSj'] = value.atchFileSj;
						row['atchFileRm'] = value.atchFileRm;
						var rowid = this.$("#fcltyRepairFileList").selectedRowIds()[0];
						this.$('#fcltyRepairFileList').flexUpdateRow(rowid, row);
					}
				}
*******************************************************************************/
			}
			break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
		break;
	}
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyRepairMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyRepairMngForm">
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
							<td rowspan="2"><button class="buttonSearch" title="조회">조회</button></td>
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
								<input id="sFlawRprStartDtFr" type="text" class="emdcal" size="15" title="하자검사일 검색시작일" /> ~ <input id="sFlawRprStartDtTo" type="text" class="emdcal" size="15" title="하자검사일 검색종료일" />
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>




	<div class="emdPanel fillHeight">
		<div id="fcltyRepairMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">하자보수내역 목록</a></li>
				<li><a href="#tabs2" class="emdTab">하자보수내역 상세</a></li>
				<li><a href="#tabs3" class="emdTab">하자보수대상시설물</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyRepairMngList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<table style="width:100%;">
						<tr>
							<th>자료수</th>
							<td><input type="text" id="totalCount" style="width:100px;text-align:right;" readonly="readonly"></td>
							<th>하자보수금액</th>
							<td><input type="text" id="sumFlawRprAmt" style="width:100px;text-align:right;" readonly="readonly"></td>
							<td style="text-align:right;">
								<!-- <button data-role="printPage" data-search-option="searchFcltyRepairMngForm" data-url='/fcltyMng/selectFcltyRepairCheckResultPrint.do'>하자검사결과인쇄</button> -->
								<!-- <button data-role="printDown" data-search-option="searchFcltyRepairMngForm" data-filename="검사조서.hwp" data-url='/fcltyMng/selectFcltyRepairCheckResultPrint.do'>H　W　P</button> -->
								<button id="addBtn" class="buttonAdd">　　추　가　　</button>
								<button id="deleteBtn" class="buttonDelete">　　삭　제　　</button>
								<button id="btnExcelDownload" class="buttonExcel">엑셀 다운로드</button>
								<button id="btnSelectedHwpDownload" >선택 하자검사조서 다운로드</button>
							</td>
						</tr>
					</table>

				</div>
			</div>


			<!-- 하자보수내역 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyRepairMngListVO">
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">하 자 검 사 정 보</th>
							<td style="text-align:right;">
								<button id="btnFirstData">처음 자료</button>
								<button id="btnPrevData">이전 자료</button>
								<button id="btnNextData">다음 자료</button>
								<button id="btnLastData">마지막 자료</button>
							</td>
						</tr>
					</table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">시설물관리그룹</th>
							<td colspan="3">
								<input type="text" size="20" id="fcltsMngGroupNo" disabled="disabled"/>-
								<input type="text" size="30" id="fcltsMngGroupNoNm" disabled="disabled"/>
								<button id="searchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
							<th style="width:10%; height:18px;">계　약　번　호</th>
							<td colspan="3">
								<input type="text" size="32" id="ctrtNo" disabled="disabled"/>
								<button id="ctrtNoPopupBtn" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">업　무　구　분</th>
							<td>
								<input type="hidden" id="fcltsJobSeNm"/>
								<select id="fcltsJobSe">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th style="width:10%; height:18px;">시　행　년　도</th>
							<td>
								<select id="enforceYear">
									<option value="">선택</option>
								</select>
							</td>
							<th style="width:10%; height:18px;">하자검사　구분</th>
							<td colspan="3">
								<input type="hidden" id="flawExamSeNm"/>
								<select id="flawExamSe">
									<option value="">선택</option>
									<option value="1">상반기</option>
									<option value="2">하반기</option>
									<option value="3">만료검사</option>
								</select>
								<input type="hidden" id="flawRprSeq"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">공　　사　　명</th>
							<td colspan="3">
								<input type="text" size="63" id="flawRprNm" />
							</td>
							<th style="width:10%; height:18px;">준　공　일　자</th>
							<td>
								<input type="text" size="15" id="bldDt" class="emdcal"/>
							</td>
							<th style="width:10%; height:18px;">계　약　일　자</th>
							<td colspan="3">
								<input type="text" size="15" id="ctrtDt" class="emdcal"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">도　급　업　체</th>
							<td colspan="3">
								<input type="text" size="63" id="flawRprEntrpsNm"/>
							</td>

							<th style="width:10%; height:18px;">도　　급　　액</th>
							<td colspan="3">
								<input id="flawContrAmt" type="text" size="20"  class="ygpaNumber" maxlength="16"/> 원
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">하자 검사 일자</th>
							<td>
								<input type="text" size="15" id="flawExamDt" class="emdcal"/>
							</td>
							<th style="width:10%; height:18px;">하　자　유　무</th>
							<td>
								<select id="flawEnnc">
									<option value="">선택</option>
									<option value="Y">유　　　　　</option>
									<option value="N">무　　　　　</option>
								</select>
							</td>
							<th style="width:10%; height:18px;">검　사　자　1</th>
							<td>
								<input id="flawExamUsrCls" type="text" size="3" />
								급&nbsp;
								<input id="flawExamUsr" type="text" size="8" />
							</td>
							<th style="width:10%; height:18px;">검　사　자　2</th>
							<td>
								<input id="flawExamUsrCls2" type="text" size="3" />
								급&nbsp;
								<input id="flawExamUsr2" type="text" size="8" />
							</td>
						</tr>
					</table>
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">하 자 보 수 정 보</th>
							<td style="text-align:right;">
								<div id="previewHidden" style="display: none;"></div>
								<button id="btnUploadFile" class="buttonAdd">파일 추가</button>
								<button id="btnDownloadFile">파일 다운로드</button>
								<button id="btnRemoveFile" class="buttonDelete">파일 삭제</button>
								<button id="btnPreviewFile">파일 미리보기</button>
							</td>
						</tr>
					</table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">하자 발생 일자</th>
							<td colspan="3">
								<input id="flawOccrrncDt" type="text" size="22" class="emdcal"/>
							</td>
							<td rowspan="8" style="padding-left:4px;">
								<table id="fcltyRepairFileList" style="display:none;"></table>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">하자 보수 기간</th>
							<td colspan="3">
								<input id="flawRprStartDt" type="text" size="24" class="emdcal"/>
								&nbsp;　~　&nbsp;
								<input id="flawRprEndDt" type="text" size="24" class="emdcal"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">하자 보수 유형</th>
							<td>
								<select id="flawRprTy">
									<option value="">선택</option>
									<option value="O">자체　　　　</option>
									<option value="S">용역　　　　</option>
								</select>
							</td>
							<th style="width:10%; height:18px;">하자 보수 금액</th>
							<td>
								<input id="flawRprAmt" type="text" size="22" title="하자보수금액" class="ygpaNumber" maxlength="16"/> 원
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">하자 보수 완료</th>
							<td colspan="3">
								<select id="flawRprComptYn">
									<option value="">선택</option>
									<option value="Y">완료　　　　</option>
									<option value="N">미완료　　　</option>
								</select>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">하자 보수 내용</th>
							<td colspan="3"><textarea id="flawRprContents" cols="65" rows="5" maxlength="4000"></textarea></td>
						</tr>
						<!--
						<tr>
							<th style="width:10%; height:18px;">하자 보수 결과</th>
							<td colspan="3"><textarea id="flawExamResult" cols="65" rows="5" title="하자보수결과" maxlength="4000"></textarea></td>
						</tr>
						-->
						<tr>
							<th style="width:10%; height:18px;">비　　　　　고</th>
							<td colspan="3"><textarea id="rm" cols="65" rows="5" maxlength="4000"></textarea></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
				<form id="fileListForm">
				<input type="hidden" id="photoFcltsJobSe" data-column-id="fcltsJobSe"/>
				<input type="hidden" id="photoFcltsMngGroupNo" data-column-id="fcltsMngGroupNo"/>
				<input type="hidden" id="photoFlawRprSeq" data-column-id="flawRprSeq"/>
				<input type="hidden" id="photoAtchFileSeq" data-column-id="atchFileSeq"/>
				<input id="atchFileSe" type="hidden"/>
				<input id="atchFileSeNm" type="hidden"/>
				<input id="atchFileSj" type="hidden"/>
				<input id="atchFileRm" type="hidden"/>
				<input type="hidden" id="photoAtchFileNmLogic" data-column-id="atchFileNmLogic"/>
				<input type="hidden" id="atchFileNmPhysicl" data-column-id="atchFileNmPhysicl"/>

				</form>
					<div>
						<!--
							<select id="printSe" title="출력구분">
								<option value="hwp">한글문서</option>
								<option value="print">인쇄</option>
							</select>
						-->
						<button id="chkPrint"  data-role="printDown"  data-filename="하자검사조서.hwp" data-search-option="fcltyRepairMngListVO" data-url="/fcltyMng/selectFcltyRepairCheckReportHwp.do">하자검사조서 다운로드</button>
						<!-- <button id="mngPrint"  data-search-option="fcltyRepairMngListVO">하자검사관리대장 출력</button>
						<button id="expPrint"  data-search-option="fcltyRepairMngListVO">하자만료검사조서 출력</button> -->
						<button id="addBtn" class="buttonAdd">　　추　가　　</button>
						<button id="deleteBtn" class="buttonDelete">　　삭　제　　</button>
						<button id="saveBtn" class="buttonSave">  저 장  </button>
				</div>
			</div>
		</div>
			<!-- 하자보수 대상 시설물 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<div class="emdControlPanel">

					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">하 자 검 사 정 보</th>
						</tr>
					</table>
					<form id="gamObjFcltsDetailForm">
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:18px;">시설물관리그룹</th>
								<td colspan="3">
									<input type="text" size="20" data-column-id="fcltsMngGroupNo" disabled="disabled"/>-
									<input type="text" size="43" data-column-id="fcltsMngGroupNoNm" disabled="disabled"/>
								</td>
								<th style="width:10%; height:18px;">시　행　년　도</th>
								<td>
									<input type="text" size="21" data-column-id="enforceYear" disabled="disabled"/>
								</td>
								<th style="width:10%; height:18px;">업　무　구　분</th>
								<td>
									<input type="text" size="21" data-column-id="fcltsJobSeNm" disabled="disabled"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">계　약　번　호</th>
								<td colspan="3">
									<input type="text" size="20" data-column-id="ctrtNo" disabled="disabled"/>-
									<input type="text" size="43" data-column-id="flawRprNm" disabled="disabled"/>
								</td>
								<th style="width:10%; height:18px;">도　급　업　체</th>
								<td colspan="3">
									<input type="text" size="65" data-column-id="flawRprEntrpsNm" disabled="disabled"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">하자검사　구분</th>
								<td>
									<input type="text" size="14" data-column-id="flawExamSeNm" disabled="disabled"/>
								</td>
								<th style="width:10%; height:18px;">소속/직급-성명</th>
								<td>
									<input type="text" size="15" data-column-id="flawExamUsrDept" disabled="disabled"/>
									<input type="text" size="15" data-column-id="flawExamUsrNm" disabled="disabled"/>
								</td>
								<th style="width:10%; height:18px;">검　　사　　자</th>
								<td>
									<input type="text" size="21" data-column-id="flawExamUsr" disabled="disabled"/>
								</td>
								<th style="width:10%; height:18px;">하자 검사 일자</th>
								<td>
									<input type="text" size="21" data-column-id="flawExamDt" disabled="disabled"/>
								</td>
							</tr>
						</table>
					</form>

					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">하 자 검 사　대 상 시 설 물</th>
							<td style="text-align:right;">
								<button id="addObjItemBtn" class="buttonAdd">  추 가 </button>
					            <button id="delObjItemBtn" class="buttonDelete">  삭 제  </button>
							</td>
						</tr>
					</table>
				</div>

				<table id="flawRprObjFcltsF" style="display:none"></table>
				<div class="emdControlPanel">
					<form id="gamObjFcltsForm">
						<table class="searchPanel">
							<tbody>
								<tr>
			                        <th>대상시설물</th>
			                        <td>
			                        	<input id="oFcltsMngNo" type="text" style="width: 130px;" title="시설물관리번호" maxlength="20" class="objFcltsEditItem" disabled="disabled"/>
			                        	<input id="prtFcltyNm" type="text" style="width: 175px;" title="시설명" maxlength="20" class="objFcltsEditItem" disabled="disabled"/>
			                        	<button id="searchFcltsMngNo" class="popupButton">선택</button>
			                        </td>
									<th>하자검사일자</th>
			                        <td><input id="oFlawExamDt" type="text" style="width: 100px;" maxlength="10" class="emdcal objFcltsEditItem"/></td>
			                        <th>하자유무</th>
			                        <td>
			                        	<select id="oFlawEnnc" class="objFcltsEditItem">
			                        		<option value="">선택</option>
			                        		<option value="Y">유</option>
			                        		<option value="N">무</option>
			                        	</select>
			                        </td>
								</tr>
								<tr>
									<th>하자검사결과</th>
									<td colspan="7"><textarea id="oFlawExamResult" cols="149" rows="5" class="objFcltsEditItem" maxlength="1333"></textarea></td>
								</tr>
								<tr>
									<th>비고</th>
									<td colspan="7"><input id="oRm" type="text" size="151" maxlength="333" class="objFcltsEditItem"/></td>
								</tr>
<!--							<tr>
									<th height="18" class="required_text">첨부파일</th>
									<td>
										<select id="fcltyRepairFileList">
											<option value="">선택</option>
										</select>
									</td>
									<td colspan="6" style="text-align:right;">
										<button id="btnPreviewFile">첨부파일 미리보기</button>
										<div id="previewHidden" style="display: none;"></div>
										<button id="btnUploadFile">업로드</button>
										<button id="btnDownloadFile">다운로드</button>
										<button id="btnRemoveFile" class="buttonDelete"> 삭 제 </button>
									</td>
								</tr> -->
							</tbody>
						</table>
					</form>
					<button id="addBtn" class="buttonAdd">　　추　가　　</button>
					<button id="deleteBtn" class="buttonDelete">　　삭 제　　</button>
					<button id="saveBtn" class="buttonSave">  저 장  </button>
				</div>
			</div>
		</div>
	</div>
</div>