<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyQcSttusInqire.jsp
  * @Description : 시설 점검 현황 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.1.09  	김종민          최초 생성
  *
  * author 김종민
  * since 2014.1.09
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
 * @FUNCTION NAME : GamFcltyQcSttusInqireModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltyQcSttusInqireModule() {
}

GamFcltyQcSttusInqireModule.prototype = new EmdModule(1000,750);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcSttusInqireModule.prototype.loadComplete = function(params) {
	this._mainmode = '';
	
	this._qcResultList = null;
	this._qcresultmode = '';
	
	this.$('#mainGrid').flexigrid({
		module: this,
		url: '/fcltyMng/selectQcSttusDtlsList.do',
		dataType: 'json',
		colModel : [
					{display:"관리그룹",		name:"fcltsMngGroupNm",		width:150,		sortable:false,		align:"center"},
					{display:"업무구분",		name:"fcltsJobSeNm",		width:90,		sortable:false,		align:"center"},
					{display:"점검관리명", 	    name:"qcMngNm",				width:200,		sortable:false,		align:"left"},
					{display:"시행년도",		name:"enforceYear",			width:60,		sortable:false,		align:"center"},
					{display:"점검구분",    	name:"qcSeNm",				width:90,		sortable:false,		align:"center"},
					{display:"점검진단자",    	name:"qcInspTpNm",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단일자",	name:"qcInspDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단구분",    name:"qcInspSeNm",			width:120,		sortable:false,		align:"center"},
					{display:"상태평가등급",	name:"sttusEvlLvlNm",		width:90,		sortable:false,		align:"center"},
					{display:"점검진단금액",	name:"qcInspAmt",			width:120,		sortable:false,		align:"right",	displayFormat: 'number'},
					{display:"점검시작일자",    name:"qcBeginDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검종료일자",	name:"qcEndDt",				width:90,		sortable:false,		align:"center"},
					{display:"점검진단기관명",	name:"qcInspInsttNm",		width:150,		sortable:false,		align:"left"},
					{display:"책임기술자명",	name:"responEngineerNm",	width:150,		sortable:false,		align:"left"},
			],
		height: 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			return data;
		}
	});

	this.$('#mainGrid').on('onLoadDataComplete', function(event, module, data) {
		module.loadDataComplete();
	});

	this.$('#mainGrid').on('onItemSelected', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module.setControlStatus();
	});
	
	this.$('#mainGrid').on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module.setControlStatus();
		module.$('#mainTab').tabs('option', {active: 1});
	});

	this.$('#qcObjFcltsGrid').flexigrid({
		module: this,
		url: '/fcltyMng/selectQcSttusObjFcltsList.do',
		dataType: 'json',
		colModel : [
					{display:"선택",		name:"chkRole",		width:40,	sortable:false,	align:"center", displayFormat:"checkbox"},
					{display:"점검시설명",	name:"prtFcltyNm",	width:245,	sortable:false,	align:"left"},
					{display:"시설분류",	name:"gisPrtFcltyNm",	width:150,	sortable:false,	align:"left"},
			],
		height: '450',
		groupBy: 'gisPrtFcltyNm',
		preProcess : function(module,data) {
			$.each(data.resultList, function() {
				this.chkRole = this.chkRole === 'TRUE';
			});
			return data;
		}		
	});
	
	this.$('#sFcltsMngGroupNo').bind('click', {module: this}, function(event) {
		event.data.module.$('#sFcltsMngGroupNo').val('');
		event.data.module.$('#sFcltsMngGroupNm').val('');
	});
	
	this.$('#fcltsJobSe').bind('change', {module: this}, function(event) {
		event.data.module.loadQcSubDataList();
	});
	
	this.$('#sQcInspSe').bind('change', {module: this}, function(event) {
		event.data.module.checkSearchQcInspSe();
	});
	this.$('#sQcSe').disable();
	
	this.setControlStatus();

	this.fillSelectBoxYear('#sEnforceYear');
	this.$('#sEnforceYear').val((new Date()).getFullYear());
	this.$('#sFcltsJobSe').val(EMD.userinfo.mngFcltyCd);
	
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
GamFcltyQcSttusInqireModule.prototype.getMapInfoList = function(params){
	this._params=params;
	if(params!=null) {
		if(params.action!=null) {
			switch(params.action) {
				case "manage":
					this.$('#sFcltsMngGroupNo').val(this._params.fcltsMngGroupNo);
					this.$('#sFcltsMngGroupNm').val(this._params.fcltsMngGroupNoNm);
					
					this.loadData();
				break;
			}
		}
	}

};

<%
/**
 * @FUNCTION NAME : fillSelectBoxYear
 * @DESCRIPTION   : Select Element에 2000년 부터 현재년도까지 채워 넣는 함수
 * @PARAMETER     : Select Element ID
**/
%>
GamFcltyQcSttusInqireModule.prototype.fillSelectBoxYear = function(id) {
	var curYear = (new Date()).getFullYear();
	for(var i=curYear; i>=2000; i--) {
		this.$(id).append('<option value="' + i + '">' + i + '년</option>');
	}
};

<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcSttusInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcSttusInqireModule.prototype.loadData = function() {
	this.$('#mainTab').tabs('option', {active: 0});
	var searchOpt = this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
};

<%
/**
 * @FUNCTION NAME : loadDataComplete
 * @DESCRIPTION   : DATA LOAD COMPLETE(LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcSttusInqireModule.prototype.loadDataComplete = function() {
	this._mainmode = 'listed';
	this.makeFormValues('#detailForm', {});
	this.setControlStatus();
};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcSttusInqireModule.prototype.downloadExcel = function() {
	var rowCount = this.$('#mainGrid').flexRowCount();
	if (rowCount <= 0) {
		alert('조회된 자료가 없습니다.');
		return;
	}
	this.$('#mainGrid').flexExcelDown('/fcltyMng/excelDownloadQcSttusDtlsList.do');
};

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세정보를 가져옴.
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcSttusInqireModule.prototype.loadDetail = function() {
	var rows = this.$('#mainGrid').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		var opts = [
	           		{name: 'sFcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	           		{name: 'sFcltsJobSe', value: row['fcltsJobSe'] },
	           		{name: 'sQcMngSeq', value: row['qcMngSeq'] }
		           ];
		this.doAction('/fcltyMng/selectQcSttusDtlsDetail.do', opts, function(module, result) { 
			if(result.resultCode == '0'){
				module.makeDivValues('#detailForm', result.detailData);
				module.makeFormValues('#detailForm', result.detailData);
				module.fillAtchFileList(result.atchFileList);
				module.loadQcSubDataList();
				module.setPrintUrl();
			}
			else {
				module._mainmode = 'listed';
				module.setControlStatus();
				alert(result.resultMsg);
			}
		});
	} else {
		alert('조회할 데이터를 선택하세요.');
	}
};

<%
/**
 * @FUNCTION NAME : checkSearchQcInspSe
 * @DESCRIPTION   : 점검진단구분값에 따른 점검진단 enable/disable 설정(조회 조건)
 * @PARAMETER     : NONE
**/
%>
 GamFcltyQcSttusInqireModule.prototype.checkSearchQcInspSe = function() {
	var value = this.$('#sQcInspSe').val();
	if(value == '1' || value == '4' || value == '5') {
		this.$('#sQcSe').enable();
	} 
	else {
		this.$('#sQcSe').val('');
		this.$('#sQcSe').disable();
	}
};

<%
/**
 * @FUNCTION NAME : setPrintUrl
 * @DESCRIPTION   : 업무구분에 따른 인쇄 url 셋팅
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcSttusInqireModule.prototype.setPrintUrl = function() {
	
	if(this.$('#fcltsJobSe').val() == 'A') {
		this.$('#btnPrint').data('url','/fcltyMng/selectFcltyQcSttusPrintA.do');
	}
	if(this.$('#fcltsJobSe').val() == 'C') {
		this.$('#btnPrint').data('url','/fcltyMng/selectFcltyQcSttusPrintC.do');
	}
	if(this.$('#fcltsJobSe').val() == 'E') {
		this.$('#btnPrint').data('url','/fcltyMng/selectFcltyQcSttusPrintE.do');
	}
	if(this.$('#fcltsJobSe').val() == 'I') {
		this.$('#btnPrint').data('url','/fcltyMng/selectFcltyQcSttusPrintI.do');
	}
	if(this.$('#fcltsJobSe').val() == 'M') {
		if(this.$('#mechFcltsSe').val() == "1"){
			this.$('#btnPrint').data('url','/fcltyMng/selectFcltyQcSttusPrintM1.do');
		}else{
			// 기계설비 점검표 인쇄
			this.$('#btnPrint').data('url','/fcltyMng/selectFcltyQcSttusPrintM2.do');
		}
	}
};

<%
/**
 * @FUNCTION NAME : setControlStatus
 * @DESCRIPTION   : 컨트롤 상태 변경
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcSttusInqireModule.prototype.setControlStatus = function() {
	if(this._mainmode == 'modify') {
		this.$('#btnPrint').enable();
		this.$('#btnPrint').removeClass('ui-state-disabled');
		this.$('#btnDownloadFile').enable();
		this.$('#btnDownloadFile').removeClass('ui-state-disabled');
		this.$('#btnPreviewFile').enable();
		this.$('#btnPreviewFile').removeClass('ui-state-disabled');
		
		if(this._qcresultmode == 'loaded') {
			this.$('#popupViewQcResultItem').enable();
			this.$('#popupViewQcResultItem').removeClass('ui-state-disabled');
		} else {
			this.$('#popupViewQcResultItem').disable({disableClass:'ui-state-disabled'});
		}
	}
	else {
		this.$('#atchFile option').remove();
		this.$('#atchFile').append('<option value="">선택</option>');
		this.$('#btnPrint').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDownloadFile').disable({disableClass:'ui-state-disabled'});
		this.$('#btnPreviewFile').disable({disableClass:'ui-state-disabled'});
		this.$('#popupViewQcResultItem').disable({disableClass:'ui-state-disabled'});
		this.$('#qcObjFcltsGrid').flexEmptyData();
	} 
};

<%
/**
 * @FUNCTION NAME : loadQcSubDataList
 * @DESCRIPTION   : 점검관리결과항목과 대상물 리스트 로드
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcSttusInqireModule.prototype.loadQcSubDataList = function() {
	var searchVO = [
	                { name: 'sFcltsJobSe', value: this.$('#fcltsJobSe').val() },
	                { name: 'sFcltsMngGroupNo', value: this.$('#fcltsMngGroupNo').val() },
	                { name: 'sQcMngSeq', value: this.$('#qcMngSeq').val() },
	                { name: 'sGamCode', value: this.getGamCode() }
	               ];
	
	if(this.$('#fcltsJobSe').val() == 'M') {
		if(this.$('#mechFcltsSe').val() == '1') {
			searchVO[searchVO.length] = { name: 'sMechCdStartChar', value: 'M02' };
			this.$('#sMechCdStartChar').val('M02');
		} else {
			searchVO[searchVO.length] = { name: 'sMechCdStartChar', value: 'M01' };
			this.$('#sMechCdStartChar').val('M01');
		}
		this.$('#mechFcltsSeNm').show();
	} else {
		this.$('#mechFcltsSeNm').hide();
	}
	
	this._qcResultList = null;
	this._qcresultmode = '';
	this.setControlStatus();
	this.doAction('/fcltyMng/selectQcSttusResultItemList.do', searchVO, function(module, result) {
 		if(result.resultCode == '0') {
 			module._qcResultList = result.resultList;
 			module._qcresultmode = 'loaded';
 			module.setControlStatus();
 			module.$('#qcObjFcltsGrid').flexOptions({params:searchVO}).flexReload();
 		}
 		else {
 			alert(result.resultMsg);
 		}
	});
};

<%
/**
 * @FUNCTION NAME : getGamCode
 * @DESCRIPTION   : 업무구분에 따른 시설분류 대분류코드 얻기
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcSttusInqireModule.prototype.getGamCode = function() {
	var result = '';
	switch(this.$('#fcltsJobSe').val()) {
	case 'A' : result = 'GAM066'; break;
	case 'C' : result = 'GAM070'; break;
	case 'E' : result = 'GAM068'; break;
	case 'I' : result = 'GAM069'; break;
	case 'M' : result = 'GAM067'; break;
	}
	return result;
};

<%
/**
 * @FUNCTION NAME : fillAtchFileList
 * @DESCRIPTION   : 첨부파일 리스트를 select element에 채워넣기.
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcSttusInqireModule.prototype.fillAtchFileList = function(atchFileList) {
	var module = this;
	this.$('#atchFile option').remove();
	this.$('#atchFile').append('<option value="">선택</option>');
	$.each(atchFileList, function(index){
		module.$('#atchFile').append('<option value="' + atchFileList[index].atchFileNmPhysicl + '">' + atchFileList[index].atchFileNmLogic + '</option>');
	});
};

<%
/**
 * @FUNCTION NAME : atchFileDownload
 * @DESCRIPTION   : 첨부파일 다운로드
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcSttusInqireModule.prototype.atchFileDownload = function() {
	if(this.$('#atchFile').val() != '') {
		this.downPfPhoto(this.$('#atchFile').val(), this.$('#atchFile').find('option:selected').text());
	} 
	else {
		alert('첨부파일을 선택해주십시오.');
	} 
};

<%
/**
* @FUNCTION NAME : showPreviewImage
* @DESCRIPTION : 첨부파일 미리보기 대화상자
* @PARAMETER : NONE
**/
%>
GamFcltyQcSttusInqireModule.prototype.showPreviewImage = function(fileName){
	if(fileName != '') {
		var ext = fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
		if(ext == 'jpg' || ext == 'jpeg' || ext == 'bmp' || ext == 'png' || ext == 'gif')
		{
			this.$('#previewDialogArea').append(
				'<div id="' + this.getId('previewDialog') + '">' + 
				'<img id="' + this.getId('previewImage') + '" src=""/>' + 
				'</div>');
			var imgURL = this.getPfPhotoUrl(fileName);
			this.$("#previewImage").attr('src', imgURL);
			this.$("#previewImage").bind('load', {module: this}, function(event) {
				event.data.module.$('#previewDialog').dialog({
					modal: true,
					maxWidth: 800,
					maxHeight: 600,
					resizable: false,
			 		draggable: true,
			 		width: 'auto',
			 		title: '이미지미리보기',
			 		buttons:[{text:"close", click: function() { 
			 						$(this).dialog('close');
			 					}
			 				}]
			 	});
			}); 
		}
		else {
			alert('이미지 파일이 아닙니다.');
		}
	}
	else {
		alert('첨부파일을 선택하십시오.');
	}
};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamFcltyQcSttusInqireModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case 'btnSearch':
			this.loadData();
			break;
			
		case 'btnExcelDownload':
			this.downloadExcel();
			break;
					
		case 'btnDownloadFile' :
			this.atchFileDownload();
			break;

		case 'btnPreviewFile' :
			this.showPreviewImage(this.$('#atchFile').val());
			break;
			
		case 'popupViewQcResultItem' :
			this.doExecuteDialog(	
									'viewQcResultItem' 
									, '점검결과항목 보기' 
									, '/popup/showQcMngResultItemPopup.do' 
									, {} 
									, { 	
										'fcltsJobSeNm' : this.$('#fcltsJobSeNm').val()
										, 'fcltsMngGroupNm' : this.$('#fcltsMngGroupNm').val()
										, 'qcResultList' : this._qcResultList
										, 'popupMode' : 'view'
									}
								);
			break;
			
		case 'popupSearchFcltsMngGroup':
			this.doExecuteDialog(	
									'selectSearchFcltsMngGroup' 
									, '관리그룹 선택'
									, '/popup/showFcltsMngGroup.do'
									, {}
								);
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
GamFcltyQcSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		case 'viewQcResultItem':
			break;
		case 'selectSearchFcltsMngGroup':
			this.$('#sFcltsMngGroupNo').val(value['fcltsMngGroupNo']);
			this.$('#sFcltsMngGroupNm').val(value['fcltsMngGroupNm']);
			break;
		default:
			alert('알수없는 팝업 이벤트가 호출 되었습니다.');
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
GamFcltyQcSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if(this._mainmode == 'modify' && oldTabId == 'listTab') {
				this.loadDetail();
			}
			break;
	}
};

var module_instance = new GamFcltyQcSttusInqireModule();

</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 1. SEARCH AREA (조회조건 영역) -->
	<div id="searchViewStack" class="emdPanel">
		<div class="viewStack">
			<form id="searchForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>관　리　그　룹</th>
							<td>
								<input type="text" size="15" id="sFcltsMngGroupNo"/>-
								<input type="text" size="17" id="sFcltsMngGroupNm" disabled="disabled"/>
								<button id="popupSearchFcltsMngGroup" class="popupButton">선택</button>
							</td>
							<th>업　무　구　분</th>
							<td>
								<select id="sFcltsJobSe" class="searchEditItem">
									<option value="">선택</option>
									<option value="E">전기시설</option>
									<option value="M">기계시설</option>
									<option value="C">토목시설</option>
									<option value="A">건축시설</option>
									<option value="I">정보통신시설</option>
								</select>
								<input id="sFcltsJobSeNm" type="hidden" />
							</td>
							<td rowspan="3"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th height="17">점검　진단　구분</th>
							<td>
								<select id="sQcInspSe">
		                        	<option value="">선택</option>
		                            <option value="1">정기점검</option>
		                            <option value="2">정밀점검</option>
		                            <option value="3">초기점검</option>
		                            <option value="4">긴급점검(손상)</option>
		                            <option value="5">긴급점검(특별)</option>
		                            <option value="6">정밀안전점검(정기)</option>
		                            <option value="7">정밀안전점검(긴급)</option>
		                            <option value="8">정밀안전점검(하자)</option>
		                            <option value="9">기타</option>
		                        </select>
							</td>
							<th>점　검　구　분</th>
							<td>
								<select id="sQcSe" class="searchEditItem">
                                    <option value="">선택</option>
                                    <option value="1">해빙기대비</option>
                                    <option value="2">풍수해대비</option>
                                    <option value="3">동절기대비</option>
                                    <option value="4">우기대비</option>
                                </select>
                                <input id="sQcSeNm" type="hidden" />
							</td>
						</tr>
						<tr>
							<th>시　행　년　도</th>
							<td>
								<select id="sEnforceYear">
									<option value="">선택</option>
                                </select>
							</td>
							<th>점검　관리　명</th>
							<td><input type="text" id="sQcMngNm" size="50" /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 2. DATA AREA (자료 영역) -->
	<div class="emdPanel fillHeight">
		<!-- 2.1. TAB AREA (탭 영역) -->
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<!-- 2.1.1. TAB 정의 -->
			<ul>
				<li><a href="#listTab" class="emdTab">시설물점검목록</a></li>
				<li><a href="#detailTab" class="emdTab">시설물점검내역</a></li>
			</ul>
			
			<!-- 2.1.2. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage" style="overflow: hidden;">
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:6%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled"/></td>
								<td style="text-align:right;">
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

			<!-- 2.1.3. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow: hidden;">
				<!-- 2.1.3.1 Layout Table -->
				<table style="width:100%;" class="editForm">
					<tr>
						<td width="70%">
							<!-- 2.1.3.1.1 Element Table -->
							<form id="detailForm">
							<table  class="detailPanel"  style="width:100%;">
								<tr>
									<th width="14%">관　리　그　룹</th>
									<td colspan="3">
										<input id="fcltsMngGroupNm" type="text" size="40" disabled="disabled"/>
										<input type="hidden" id="fcltsMngGroupNo"/>
										<input type="hidden" id="fcltsJobSe"/>
										<input type="hidden" id="qcMngSeq"/>
										<input type="hidden" id="mechFcltsSe"/>
										<input type="hidden" id="sMechCdStartChar"/>
									</td>
								</tr>
								<tr>
									<th width="14%" height="17">업　무　구　분</th>
									<td width="40%">
										<input id="fcltsJobSeNm" type="text" size="24" disabled="disabled"/>
									</td>
									<th width="14%" height="17">점검　진단　예산</th>
									<td width="32%"><input id="qcInspBdgt" type="text" size="20" class="ygpaNumber" disabled="disabled"/> 원</td>
								</tr>
								<tr>
									<th height="17">점검　관리　명</th>
									<td>
										<input id="qcMngNm" type="text" size="35" disabled="disabled"/>
									</td>
									<th height="17">계획　이력　구분</th>
									<td>
										<input id="planHistSeNm" type="text" size="20" disabled="disabled"/>
									</td>
								</tr>
								<tr>
									<th height="17">점검　진단　구분</th>
									<td>
										<input id="qcInspSeNm" type="text" size="20" disabled="disabled"/>
										<input id="qcInspSe" type="hidden"/>
									</td>
									<th height="17">점　검　구　분</th>
									<td>
										<input id="qcSeNm" type="text" size="20" disabled="disabled"/>
										<input id="qcSe" type="hidden" />
									</td>
								</tr>
								<tr>
									<th height="17">시　행　년　도</th>
									<td>									
										<input id="enforceYear" type="text" size="12" disabled="disabled"/>년
									</td>
									<th height="17">점검　진단　자</th>
									<td>
										<input id="qcInspTpNm" type="text" size="20" disabled="disabled"/>
										<input id="qcInspTp" type="hidden" />
									</td>
								</tr>
								<tr>
									<th height="17">시　행　일　자</th>
									<td>
										<input id="qcInspDt" type="text" size="12" disabled="disabled"/>
									</td>
									<th height="17">점검　진단　금액</th>
									<td>
										<input id="qcInspAmt" type="text" size="20" class="ygpaNumber" disabled="disabled"/> 원
									</td>
								</tr>
								<tr>
									<th height="17">상태　평가　등급</th>
									<td>
										<input id="sttusEvlLvlNm" type="text" size="10" disabled="disabled"/>
									</td>
									<th height="17">점검　진단　기관명</th>
									<td>
										<input id="qcInspInsttNm" type="text" size="20" disabled="disabled"/>
									</td>
								</tr>
								<tr>
									<th height="17">점　검　기　간</th>
									<td>
										<input id="qcBeginDt" type="text" size="12" disabled="disabled"/> ~ 
										<input id="qcEndDt" type="text" size="12" disabled="disabled"/>
									</td>
									<th height="17">책임　기술자　명</th>
									<td>
										<input type="text" size="20" id="responEngineerNm" disabled="disabled"/>
									</td>
								</tr>
								<tr>
									<th height="17">작　성　일　자</th>
									<td>
										<input id="wrtDt" type="text" size="12" disabled="disabled"/> 
									</td>
									<th height="17">작　　성　　자</th>
									<td><input id="wrtUsr" type="text" size="20" disabled="disabled" /></td>
								</tr>
								<tr>
									<th height="17">점검　진단　결과</th>
									<td colspan="3">
										<textarea id="qcInspResult" cols="88" rows="5" disabled="disabled"></textarea>
										<button id="popupViewQcResultItem" class="popupButton">점검결과항목보기</button>
										<input id="mechFcltsSeNm" type="text" size="20" disabled="disabled" />
									</td>
								</tr>
								<tr>
									<th height="17">점　검　내　용</th>
									<td colspan="3">
										<textarea id="actionCn" cols="88" rows="5" disabled="disabled"></textarea>
									</td>
								</tr>
								<tr>
									<th height="17">비　　　　　고</th>
									<td colspan="3">
										<input id="rm" type="text" size="90" disabled="disabled" />
									</td>
								</tr>
							</table>
							</form>
						</td>
						<td width="30%">
							<!-- 2.1.3.1.2 Grid Table -->
							<table  class="detailPanel"  style="width:100%;">
								<tr>
									<td>
										<table id="qcObjFcltsGrid" style="display:none" class="fillHeight"></table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table class="editForm" style="width:100%">
					<tr>
						<th width="11%" height="20">첨　부　파　일</th>
						<td>
							<select id="atchFile">
                            </select>
						</td>
					</tr>
				</table>
				<div class="emdSummaryPanel">
					<button id="btnDownloadFile">다운로드</button>
					<button id="btnPreviewFile">첨부파일미리보기</button>
					<div style="text-align:right">
						<button id="btnPrint" class="" data-role="printPage" data-search-option="detailForm">인　쇄　</button>
						<button id="btnHwp" data-role="printDown" data-filename="검사조서.hwp" data-search-option="detailForm">H　W　P 　</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%
/******************************** UI       END ********************************/
%>
