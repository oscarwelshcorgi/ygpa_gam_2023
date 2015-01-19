<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamElctyFcltySpecInqire.jsp
  * @Description : 전기시설 제원 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.4  	정성현          최초 생성
  *
  * author 정성현
  * since 2014.12.5
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<validator:javascript formName="fcltyManageVO" method="validateFcltyManageVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamElctyFcltySpecInqireModule() {
}

GamElctyFcltySpecInqireModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamElctyFcltySpecInqireModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#elctyFcltySpecInqireList").flexigrid({
		module: this,
		url: '/fclty/selectElctyFcltySpecInqireList.do',
		dataType: "json",
		colModel : [
					{display:"항구분",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:200,		sortable:false,		align:"left"},
					{display:"시설명",	    name:"prtFcltyNm",			width:200,		sortable:false,		align:"left"},
					{display:"소재지",		name:"gisAssetsLocplc",		width:180,		sortable:false,		align:"left"},
					{display:"시설물관리그룹",	name:"fcltsMngGroupNoNm",	width:120,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:80,		sortable:false,		align:"center"},
					{display:"설치일자",		name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"}
			],
		height: "auto",
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			return data;
		}
	});

	this._cmd = '';
	this._prtFcltySe = 'E';

	this.$("#elctyFcltySpecInqireList").on('onItemSelected', function(event, module, row, grid, param) {
		module._cmd = "modify";
	});

	this.$("#elctyFcltySpecInqireList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._cmd = "modify";
		module.$("#elctyFcltySpecInqireTab").tabs("option", {active: 1});
	});

	
	this.$(".text").bind("change keyup", {module: this}, function(event) {
		var limit_char = /[|]/;
		if(limit_char.test(event.target.value)){
			alert('|'+"(파이프) 특수문자는 사용 하실수 없습니다.");
			var rep= event.target.value.replace(limit_char,"");
			event.target.value = rep;
			return;
		}
	});

	this.$("#fcltsFileList").flexigrid({
		module: this,
		url: '/fclty/selectElctyFcltySpecInqireFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",			width:200,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",		width:180,		sortable:true,		align:"left"},
			],
		height: "400"
	});

	this.$("#fcltsFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.selectAtchFileItem();
	});

	//첨부파일 정보 변화 이벤트 처리기
	this.$(".photoEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.atchFileInfoChanged(event.target);
	});

};

GamElctyFcltySpecInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

//시설목록 로드
GamElctyFcltySpecInqireModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchElctyFcltySpecInqireForm");
	this.$("#elctyFcltySpecInqireList").flexOptions({params:searchOpt}).flexReload();
	console.log(searchOpt);
};

//시설재원데이터 로드
GamElctyFcltySpecInqireModule.prototype.loadDetailData = function() {
	var selectRows = this.$('#elctyFcltySpecInqireList').selectedRows();
	if(selectRows.length > 0) {
		var row = selectRows[0];
		if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
			alert('시설물 관리번호에 오류가 있습니다.');
			this._cmd = '';
			this.initDisplay();
			return;
		}
		
		this.doAction('/fclty/selectElctyFcltySpecInqireDetail.do', row, function(module, result) {
			if(result.resultCode == "0"){
				module._fcltyManageVO=result.result;
				module.makeFormValues('#fcltyManageVO', module._fcltyManageVO);
			module.loadFileData();
			}
			else {
				this._cmd="";
				module.initDisplay();
				alert(result.resultMsg);
			}
		});
	}
};

//시설 첨부파일 로드
GamElctyFcltySpecInqireModule.prototype.loadFileData = function() {
	var searchOpt = [{name: 'sFcltsMngNo', value: this._fcltyManageVO['fcltsMngNo']}];
	this.$("#fcltsFileList").flexOptions({params:searchOpt}).flexReload();
};

// 화면 및 데이터 초기화 처리
GamElctyFcltySpecInqireModule.prototype.initDisplay = function() {
	this.$("#fcltyManageVO :input").val("");
	this.$('#fcltsFileList').flexEmptyData();
	this.$("#previewImage").attr("src", "#");
	 if (this._cmd != "modify") {
		this.$("#elctyFcltySpecInqireTab").tabs("option", {active: 0});
	}
};

//첨부파일 정보 변화 처리
GamElctyFcltySpecInqireModule.prototype.atchFileInfoChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#fcltsFileList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#atchFileSe').is(target)) {
			row['atchFileSe'] = $(target).val();
			row['atchFileSeNm'] = $(target).find('option:selected').text();
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
		var rowid=this.$("#fcltsFileList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltsFileList').flexUpdateRow(rowid, row);
	}
};

//첨부파일 항목선택
GamElctyFcltySpecInqireModule.prototype.selectAtchFileItem = function() {
	var rows = this.$('#fcltsFileList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		this.$("#fcltsFileForm input").val('');
		this.makeFormValues("#fcltsFileForm", row);
		if(row.atchFileNmPhysicl != null || row.atchFileNmPhysicl != "") {
			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm = row["atchFileNmPhysicl"];
			var ext = filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();
			if(ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif"){
				var imgURL = this.getPfPhotoUrl(filenm);
			    this.$("#previewImage").attr("src", imgURL);
			}else{
				this.$("#previewImage").removeAttr("src");
			}
		}
	}
};


//첨부파일 다운로드
GamElctyFcltySpecInqireModule.prototype.downloadAtchFileItem = function() {
	var selectRow = this.$('#fcltsFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};


GamElctyFcltySpecInqireModule.prototype.downloadExcel = function(buttonId) {

	var gridRowCount = 0;
	switch (buttonId) {
		case 'btnExcelDownload':
			gridRowCount = this.$("#elctyFcltySpecInqireList").flexRowCount();
			break;
		default:
			return;
	}
	if (gridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	switch (buttonId) {
		case 'btnExcelDownload':
			this.$('#elctyFcltySpecInqireList').flexExcelDown('/fclty/selectElctyFcltySpecInqireListExcel.do');
			break;
	}

};



/**
 * 정의 된 버튼 클릭 시
 */
GamElctyFcltySpecInqireModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
		case "btnSearch": //조회
			this._cmd = "";
			this.initDisplay();
			this.loadData();
			break;
	
			// 시설물관리그룹(조회 화면)
		case "popupSearchFcltsMngGroupNo":
			this.doExecuteDialog("selectFcltsMngGroup", "시설물그룹번호", '/popup/showFcltsMngGroup.do', {});
			break;
	
		// 시설물관리그룹(디테일 화면)
		case "popupSearchFcltsMngGroupNo2":
			this.doExecuteDialog("selectFcltsMngGroup2", "시설물그룹번호", '/popup/showFcltsMngGroup.do', {});
			break;
	
	
			//파일다운로드
		case "btnDownloadFile":
			this.downloadAtchFileItem();
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
				
		// 엑셀다운로드
		case "btnExcelDownload":
			this.downloadExcel(buttonId);
			break;
	}
};




/**
 * 탭 변경시 실행 이벤트
 */
GamElctyFcltySpecInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._cmd == 'modify') {
		this.initDisplay();
		this.$('#tabs2').scrollTop(0);
		this.loadDetailData();
	}
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		if(this._cmd != 'modify') {
			this.$("#elctyFcltySpecInqireTab").tabs("option", {active: 0});
			alert('먼저 전기시설항목을 선택 하세요.');
		}
		break;
	case "tabs3":
		if(this._cmd != 'modify') {
			this.$("#elctyFcltySpecInqireTab").tabs("option", {active: 0});
			alert('먼저 전기시설항목을 선택하세요.');
		}
		break;
	}
};

/**
 * 팝업 close 이벤트
 */
GamElctyFcltySpecInqireModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
	
	//조회화면
	case "selectFcltsMngGroup":
		this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
		this.$("#sFcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
		break;
		
	//디테일화면 
	case "selectFcltsMngGroup2":
		this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
		this.$("#fcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
		break;

	default:
		alert("알수없는 팝업 이벤트가 호출 되었습니다.");
		break;
}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamElctyFcltySpecInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchElctyFcltySpecInqireForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항구분</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>시설물관리그룹</th>
							<td colspan="3">
								<input id="sFcltsMngGroupNo" type="text" size="14" title="시설물관리그룹넘버" maxlength="14" />&nbsp;-&nbsp;
								<input id="sFcltsMngGroupNoNm" type="text" size="56" title="시설물관리그룹명" disabled="disabled" />
								<button id="popupSearchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
							<td rowspan="2"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>시설분류</th>
							<td><input id="sPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM061" /></td>
							<th>시설명</th>
							<td><input id="sPrtFcltyNm" type="text" size="30" maxlength="30" /></td>
							<th>소재지</th>
							<td><input id="sLoc" type="text" size="30" title="소재지" maxlength="30" /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="elctyFcltySpecInqireTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">전기시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">전기시설 제원</a></li>
				<li><a href="#tabs3" class="emdTab">전기시설 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="elctyFcltySpecInqireList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<table style="width:100%;">
						<tr>
							<th>자료수</th>
							<td><input type="text" id="totalCount" style="width:250px;text-align:right;"></td>
							<td style="text-align:right;">
								<button id="btnExcelDownload">엑셀 다운로드</button>
								<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="elctyFcltySpecInqireList" data-style="default">맵조회</button>
							</td>
						</tr>
					</table>
					
				</div>
			</div>


			<!-- 전기시설 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyManageVO">
				<div style="margin-bottom:10px;">
					<table class="searchPanel">
						<tbody>
							<tr>
								<th width="70%">전기시설 일반</th>
								<th>시설물관리번호 : <span id="fcltsMngNo"></span></th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">항　　구　　분</th>
							<td><input type="text" size="5" id="gisAssetsPrtAtCode" disabled="disabled"/>
							    <input type="text" size="23" id="gisAssetsPrtAtName" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">GIS 자 산 코 드</th>
							<td colspan="3">
								<input type="text" size="4" id="gisAssetsCd" disabled="disabled" data-required="true"/>-
								<input type="text" size="3" id="gisAssetsSubCd" disabled="disabled"/>-
								<input type="text" size="4" id="gisAssetsPrtAtCode2" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">GIS 자　산　명</th>
							<td><input type="text" size="30" id="gisAssetsNm" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">지　　　　　번</th>
							<td>
								<input id="gisAssetsLnm" type="text" size="4" title="지번 앞자리" disabled="disabled" />&nbsp;-&nbsp;
								<input id="gisAssetsLnmSub" type="text" size="7" title="지번 뒷자리" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">소　　재　　지</th>
							<td><input id="gisAssetsLocplc" type="text" size="30" title="소재지" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시　설　코　드</th>
							<td>
								<input type="text" size="2" id="gisPrtFcltyCd" disabled="disabled" />&nbsp;-&nbsp;
								<input type="text" size="5" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
							<th width="12%" height="17" class="required_text">시　설　분　류</th>
							<td>
								<input class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM061" id="selectGisPrtFcltyCd" data-required="true" data-column-id="gisPrtFcltyCd" disabled="disabled"/>
								<input type="hidden" id="prtFcltySeNm" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">전 기　시 설 명</th>
							<td><input type="text" size="30" id="prtFcltyNm" maxlength="25" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<input type="text" size="16" id="fcltsMngGroupNo" disabled="disabled"/>
								<input type="text" size="61" id="fcltsMngGroupNoNm" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시　설　규　격</th>
							<td colspan="3"><input id="prtFcltyStndrd" type="text" size="30" title="시설규격" maxlength="13" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">시　설　단　위</th>
							<td><input id="prtFcltyUnit" type="text" size="30" title="시설단위" maxlength="3" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설　치　일　자</th>
							<td><input id="prtFcltyInstlDt" type="text" class="emdcal" size="11" title="설치일자" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">변　경　일　자</th>
							<td colspan="3"><input id="prtFcltyChangeDt" type="text" class="emdcal" size="11" title="변경일자" disabled="disabled"/></td>
						</tr>
					</table>
				</div>
					<table class="searchPanel">
						<tbody>
							<tr>
								<th>전기시설 제원</th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">용　　　　　도</th>
							<td><input id="prpos" type="text" size="50" maxlength="33" title="용도" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">수　　　　　량</th>
							<td><input id="qy" type="text" size="50" class="ygpaNumber" title="수량" maxlength="5" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">규　　　　　격</th>
							<td><input id="stndrd" type="text" size="50" maxlength="16" title="규격" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">형　　　　　식</th>
							<td><input id="fmt" type="text" size="50" maxlength="33" title="형식" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">용　　　　　량</th>
							<td><input id="capa" type="text" size="50" maxlength="6" title="용량" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">전　　　　　압</th>
							<td><input id="volt" type="text" size="50" maxlength="6" title="전압" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">출　　　　　력</th>
							<td><input id="output" type="text" size="50" maxlength="6" title="출력" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">연 료　소 비 량</th>
							<td><input id="fuelConsum" type="text" size="50" class="ygpaNumber" data-decimal-point="2" title="연료소비량" maxlength="10" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">제　작　회　사</th>
							<td><input id="mfcCmpny" type="text" size="50" maxlength="33" title="제작회사" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">제　작　일　자</th>
							<td><input id="mfcDt" type="text" size="11" class="emdcal" title="제작일자" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">관　　리　　자</th>
							<td><input id="manager" type="text" size="50" maxlength="6" title="관리자" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">설　치　일　자</th>
							<td><input id="instlDt" type="text" size="11" class="emdcal" title="설치일자" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">사　용　업　체</th>
							<td colspan="3"><input id="usageEntrps" type="text" size="50" maxlength="33" title="사용업체" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">관　로　길　이</th>
							<td><input id="ductLineLt" type="text" size="50" class="ygpaNumber" data-decimal-point="2" title="관로길이" maxlength="8" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">조 명 탑　높 이</th>
							<td><input id="lightwrHt" type="text" size="50" class="ygpaNumber" data-decimal-point="2" title="조명탑높이" maxlength="6" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">공　급　전　원</th>
							<td><input id="spplyPwr" type="text" size="50" maxlength="6" title="공급전원" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">공　급　 T　R</th>
							<td><input id="spplyTr" type="text" size="50" maxlength="6" title="공급TR" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">결　선　방　식</th>
							<td><input id="fnlMthd" type="text" size="50" maxlength="6" title="결선방식" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">케 이 블　연 장</th>
							<td><input id="cableExt" type="text" size="50" class="ygpaNumber" data-decimal-point="2" title="케이블연장" maxlength="8" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">연　료　탱　크</th>
							<td><input id="fuelTank" type="text" size="50" class="ygpaNumber" data-decimal-point="2" title="연료탱크" maxlength="10" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">유　　　　　량</th>
							<td><input id="oilQty" type="text" size="50" class="ygpaNumber" data-decimal-point="2" title="유량" maxlength="10" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">한 전　변 전 소</th>
							<td colspan="3"><input id="korElecSubstn" type="text" size="50" maxlength="6" title="한전변전소" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">구　간　FROM</th>
							<td><input id="sectionFrom" type="text" size="50" maxlength="6" title="구간 FROM" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">구　간　T　O</th>
							<td><input id="sectionTo" type="text" size="50" maxlength="6" title="구간 TO" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">전　주　규　격</th>
							<td colspan="3"><input id="premainStndrd" type="text" size="50" maxlength="20" title="전주규격" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">전　주　높　이</th>
							<td><input id="premainHt" type="text" size="50" class="ygpaNumber" data-decimal-point="2" title="전주높이" maxlength="6" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">전　주　수　량</th>
							<td><input id="premainQy" type="text" size="50" class="ygpaNumber" title="전주수량" maxlength="5" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">등 기 구　규 격</th>
							<td><input id="lightappStndrd" type="text" size="50" maxlength="20" title="등기구규격" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">등 기 구　수 량</th>
							<td><input id="lightappQy" type="text" size="50" class="ygpaNumber" title="등기구수량" maxlength="5" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">등 기 구　형 식</th>
							<td colspan="3"><input id="lightappFmt" type="text" size="50" maxlength="6" title="등기구형식" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">LAMP　형　식</th>
							<td colspan="3"><input id="lampFmt" type="text" size="50" maxlength="6" title="LAMP형식" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">LAMP　용　량</th>
							<td><input id="lampCapa" type="text" size="50" maxlength="6" title="LAMP용량" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">LAMP　수　량</th>
							<td><input id="lampQy" type="text" size="50" class="ygpaNumber" title="LAMP수량" maxlength="5" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">조명탑등기구분류코드</th>
							<td><input id="lightwrLightappClcd" type="text" size="50" maxlength="10" title="조명탑등기구 분류코드" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">조명탑등기구수량</th>
							<td><input id="lightwrLightappQy" type="text" size="50" class="ygpaNumber" title="조명탑등기구수량" maxlength="5" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">조명탑LAMP분류코드</th>
							<td><input id="lightwrLampClcd" type="text" size="50" maxlength="10" title="조명탑 LAMP분류코드" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">조명탑LAMP수량</th>
							<td><input id="lightwrLampQy" type="text" size="50" class="ygpaNumber" title="조명탑 LAMP수량" maxlength="5" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">전기시설물분류코드</th>
							<td colspan="3">
								<input id="elctyFcltsClCd" type="text" size="20" disabled="disabled" />
								<input id="elctyFcltsClCdNm" type="text" size="28" disabled="disabled" />
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">건축시설물관리번호</th>
							<td colspan="3">
								<input id="archFcltsMngNo" type="text" size="20" disabled="disabled" />
								<input id="archFcltsMngNoNm" type="text" size="28" disabled="disabled" />
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">소　　재　　지</th>
							<td colspan="3"><input id="loc" type="text" size="135" maxlength="50" title="위치" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">비　　　　　고</th>
							<td colspan="3">
								<textarea rows="4" cols="133" id="rm" maxlength="333" disabled="disabled" ></textarea>
							</td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="gotoLocation">위치조회</button>
				</div>
			</div>

			<!-- 전기시설 첨부파일 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table>
					<tr>
						<td width="50%">
							<table id="fcltsFileList" style="display:none" class="fillHeight"></table>
							<div class="emdControlPanel">
								<button id="btnDownloadFile">다운로드</button>
							</div>
							
						</td>
						<td style="text-align:center;vertical-align:middle;">
							<img id="previewImage" style="border: 1px solid #000; max-width:300px; max-height: 300px" src="">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>