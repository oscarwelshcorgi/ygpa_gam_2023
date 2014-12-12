<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamInfoTechFcltySpecInqire.jsp
  * @Description : 정보통신시설 제원 조회
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
function GamInfoTechFcltySpecInqireModule() {
}

GamInfoTechFcltySpecInqireModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamInfoTechFcltySpecInqireModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#infoTechFcltySpecInqireList").flexigrid({
		module: this,
		url: '/fclty/selectInfoTechFcltySpecInqireList.do',
		dataType: "json",
		colModel : [
					{display:"항코드",		name:"gisAssetsPrtAtCode",	width:80,		sortable:false,		align:"center"},
					{display:"항코드명",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"자산코드",		name:"gisAssetsDisplayCd",	width:80,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:200,		sortable:false,		align:"left"},
					{display:"시설코드", 	    name:"gisPrtFcltyDisplayCd",width:80,		sortable:false,		align:"center"},
					{display:"시설명",		name:"prtFcltyNm",			width:280,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:100,		sortable:false,		align:"left"},
					{display:"시설규격",	    name:"prtFcltyStndrd",		width:230,		sortable:false,		align:"left"},
					{display:"시설단위",  	    name:"prtFcltyUnit",		width:80,		sortable:false,		align:"left"},
					{display:"설치일자",		name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"},
					{display:"변경일자",		name:"prtFcltyChangeDt",	width:80,		sortable:false,		align:"center"}
			],
		height: "auto"
	});

	this._cmd = '';
	this._deleteDataFileList = null;
	this._prtFcltySe = 'C';

	this.$("#infoTechFcltySpecInqireList").on('onItemSelected', function(event, module, row, grid, param) {
		module._cmd = "modify";
	});

	this.$("#infoTechFcltySpecInqireList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._cmd = "modify";
		module.$("#infoTechFcltySpecInqireTab").tabs("option", {active: 1});
	});

	this.$("#selectGisPrtFcltyCd").on("change", {module: this}, function(event) {
		event.data.module.$("#gisPrtFcltyCd").val($(this).val());
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
		url: '/fclty/selectInfoTechFcltySpecInqireFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",			width:160,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",		width:160,		sortable:true,		align:"left"},
					{display:"물리파일명",	name:"atchFileNmPhysicl",	width:160,		sortable:true,		align:"left"},
					{display:"작성일자",	name:"atchFileWritngDt",	width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});

	this.$("#fcltsFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#fcltsFileForm input").val('');
		module.makeFormValues("#fcltsFileForm", row);

		if(row.atchFileNmPhysicl != null || row.atchFileNmPhysicl != "") {
			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm = row["atchFileNmPhysicl"];
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

	//첨부파일 정보 변화 이벤트 처리기
	this.$(".photoEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.atchFileInfoChanged(event.target);
	});

	console.log('just.');
};

GamInfoTechFcltySpecInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

//시설목록 로드
GamInfoTechFcltySpecInqireModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchInfoTechFcltySpecInqireForm");
	this.$("#infoTechFcltySpecInqireList").flexOptions({params:searchOpt}).flexReload();
};

//시설재원데이터 로드
GamInfoTechFcltySpecInqireModule.prototype.loadDetailData = function() {
	var selectRows = this.$('#infoTechFcltySpecInqireList').selectedRows();
	if(selectRows.length > 0) {
		var row = selectRows[0];
		if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
			alert('시설물 관리번호에 오류가 있습니다.');
			this._cmd = '';
			this.initDisplay();
			return;
		}
		var opts = [{name: 'fcltsMngNo', value: row['fcltsMngNo'] }];
		this.doAction('/fclty/selectInfoTechFcltySpecInqireDetail.do', opts, function(module, result) {
			if(result.resultCode == "0"){
				module._fcltyManageVO=result.result;
				module.makeDivValues('#fcltyManageVO', module._fcltyManageVO);

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
GamInfoTechFcltySpecInqireModule.prototype.loadFileData = function() {
	var searchOpt = [{name: 'sFcltsMngNo', value: this._fcltyManageVO['fcltsMngNo']}];
	this.$("#fcltsFileList").flexOptions({params:searchOpt}).flexReload();
};

// 화면 및 데이터 초기화 처리
GamInfoTechFcltySpecInqireModule.prototype.initDisplay = function() {
	this._deleteDataFileList = [];
	this.$("#fcltyManageVO :input").val("");
	this.$('#fcltsFileList').flexEmptyData();
	this.$("#previewImage").attr("src", "#");
	if(this._cmd == "insert") {
		this.$("#selectGisPrtFcltyCd").enable();
		this.$("#searchGisCodeBtn2").show();
		this.$("#infoTechFcltySpecInqireTab").tabs("option", {active: 1});
	} else if (this._cmd == "modify") {
		this.$("#selectGisPrtFcltyCd").disable();
		this.$("#searchGisCodeBtn2").hide();
	} else {
		this.$("#fcltyManageVO :input").val("");
		this.$("#selectGisPrtFcltyCd").enable();
		this.$("#searchGisCodeBtn2").show();
		this.$("#infoTechFcltySpecInqireTab").tabs("option", {active: 0});
	}
};

//첨부파일 정보 변화 처리
GamInfoTechFcltySpecInqireModule.prototype.atchFileInfoChanged = function(target) {
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


/**
 * 정의 된 버튼 클릭 시
 */
GamInfoTechFcltySpecInqireModule.prototype.onButtonClick = function(buttonId) {
	var opts = null;
	switch(buttonId) {
		case "searchBtn": //조회
			this._cmd = "";
			this.initDisplay();
			this.loadData();
			break;

		// 자산코드 팝업(조회화면)
		case "searchGisCodeBtn":
			this.doExecuteDialog("selectGisCode", "자산코드", '/popup/showAssetsCd.do', {});
			break;

		// 자산코드 팝업(디테일 화면)
		case "searchGisCodeBtn2":
			this.doExecuteDialog("selectGisCode2", "자산코드", '/popup/showAssetsCd.do', {});
			break;



		//파일다운로드
		case "btnDownloadFile":
			var selectRow = this.$('#fcltsFileList').selectedRows();
			if(selectRow.length > 0) {
				var row=selectRow[0];
				this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
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
	}
};




/**
 * 탭 변경시 실행 이벤트
 */
GamInfoTechFcltySpecInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
			this.$("#infoTechFcltySpecInqireTab").tabs("option", {active: 0});
			alert('먼저 정보통신 시설항목을 선택 하세요.');
		}
		break;
	case "tabs3":
		if(this._cmd != 'modify') {
			this.$("#infoTechFcltySpecInqireTab").tabs("option", {active: 0});
			alert('먼저 정보통신 항목을 선택하세요.');
		}
		break;
	}
};

/**
 * 팝업 close 이벤트
 */
GamInfoTechFcltySpecInqireModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		// 조회화면
		case "selectGisCode":
			this.$("#sAssetsCd").val(value["gisAssetsCd"]);
			this.$("#sAssetsSubCd").val(value["gisAssetsSubCd"]);
			break;

		case "selectGisCode2":
			this.$("#gisAssetsPrtAtCode").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsPrtAtCode2").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsPrtAtName").val(value["gisAssetsPrtAtCodeNm"]);
			this.$("#gisAssetsSubCd").val(value["gisAssetsSubCd"]);				// GIS SUB자산코드
			this.$("#gisAssetsCd").val(value["gisAssetsCd"]);					// GIS 자산코드
			this.$("#gisAssetsNm").val(value["gisAssetsNm"]);					// GIS 자산명
			this.$("#gisAssetsLocplc").val(value["gisAssetsLocplc"]); 			// 소재지
			this.$("#gisAssetsLnm").val(value["gisAssetsLnm"]);					// 지번
			this.$("#gisAssetsLnmSub").val(value["gisAssetsLnmSub"]);			// 서브지번
			break;

		case "selectFcltsClCd":
			this.$("#cvlEngFcltsClCd").val(value["fcltsClCd"]);
			this.$("#cvlEngFcltsClCdNm").val(value["fcltsClCdNm"]);
			break;

		case "selectFcltsMngGroup":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
			break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamInfoTechFcltySpecInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchInfoTechFcltySpecInqireForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항코드</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>자산코드</th>
							<td>
								<input id="sAssetsCd" type="text" size="3" maxlength="3" />&nbsp;-&nbsp;
								<input id="sAssetsSubCd" type="text" size="2" maxlength="2" />
								<button id="searchGisCodeBtn" class="popupButton">선택</button>
							</td>
							<th>정보통신시설분류</th>
							<td>
								<input id="sPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM059" />
							</td>
							<td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>정보통신시설명</th>
							<td colspan="5"><input id="sPrtFcltyNm" type="text" size="60" maxlength="40"  /></td>
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
		<div id="infoTechFcltySpecInqireTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">정보통신시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">정보통신시설 제원</a></li>
				<li><a href="#tabs3" class="emdTab">정보통신시설 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="infoTechFcltySpecInqireList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="infoTechFcltySpecInqireList" data-style="default">맵조회</button>
				</div>
			</div>


			<!-- 건축시설 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyManageVO">
				<div style="margin-bottom:10px;">
					<table class="searchPanel">
						<tbody>
							<tr>
								<th width="70%">정보통신시설 일반</th>
								<th>시설물관리번호 : <span id="fcltsMngNo"></span></th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">항코드</th>
							<td><span id="gisAssetsPrtAtCode" ></span>  <span id="gisAssetsPrtAtName" ></span></td>
							<th width="12%" height="17" class="required_text">GIS 자산코드</th>
							<td colspan="3">
								<span id="gisAssetsCd"  data-required="true"></span>-
								<span id="gisAssetsSubCd" ></span>-
								<span id="gisAssetsPrtAtCode2" ></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">GIS 자산명</th>
							<td><span id="gisAssetsNm"></span></td>
							<th width="12%" height="17" class="required_text">지번</th>
							<td>
								<span id="gisAssetsLnm"  title="지번 앞자리"></span>&nbsp;-&nbsp;
								<span id="gisAssetsLnmSub" title="지번 뒷자리"></span>
							</td>
							<th width="12%" height="17" class="required_text">소재지</th>
							<td><span id="gisAssetsLocplc" title="소재지"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설코드</th>
							<td>
								<span id="gisPrtFcltyCd"></span>&nbsp;-&nbsp;
								<span id="gisPrtFcltySeq"></span>
							</td>
							<th width="12%" height="17" class="required_text">시설분류</th>
							<td>
								<span class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM059" id="selectGisPrtFcltyCd" data-required="true" data-column-id="gisPrtFcltyCd"></span>

							</td>
							<th width="12%" height="17" class="required_text">정보통신시설명</th>
							<td><span id="prtFcltyNm"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<span id="fcltsMngGroupNo" ></span>
								<span id="fcltsMngGroupNoNm" ></span>

							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설규격</th>
							<td colspan="3"><span id="prtFcltyStndrd" title="시설규격" ></span></td>
							<th width="12%" height="17" class="required_text">시설단위</th>
							<td><span id="prtFcltyUnit"  title="시설단위"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설치일자</th>
							<td><span id="prtFcltyInstlDt" class="emdcal" title="설치일자" ></span></td>
							<th width="12%" height="17" class="required_text">변경일자</th>
							<td colspan="3"><span id="prtFcltyChangeDt" class="emdcal" title="변경일자" ></span></td>
						</tr>
					</table>
				</div>
					<table class="searchPanel">
						<tbody>
							<tr>
								<th>정보통신시설 제원</th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">품목명</th>
							<td colspan="3"><span id="prdlstNm"></span></td>
							<th width="12%" height="17" class="required_text">수량</th>
							<td><span id="qy" class="ygpaNumber"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">모델</th>
							<td colspan="5"><span id="model"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">제조사</th>
							<td colspan="5"><span id="maker"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">세부내역</th>
							<td colspan="5"><span id="ptlrDtls"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">규격</th>
							<td colspan="5"><span id="stndrd"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설치구분</th>
							<td><span id="instlSe"> </span></td>
							<th width="12%" height="17" class="required_text">설치번호</th>
							<td><span id="instlNo" ></span></td>
							<th width="12%" height="17" class="required_text">기능</th>
							<td><span id="func"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">제어방식</th>
							<td><span id="ctrlMthd"></span></td>
							<th width="12%" height="17" class="required_text">설치규격</th>
							<td><span id="instlStndrd"></span></td>
							<th width="12%" height="17" class="required_text">설치높이</th>
							<td><span id="instlHt" class="ygpaNumber" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">LAMP형식</th>
							<td><span id="lampFmt"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">위치</th>
							<td colspan="5"><span id="loc"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">비고</th>
							<td colspan="5"><span id="rm"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">정보통신시설물분류코드</th>
							<td colspan="5">
								<span id="infoCommFcltsClCd"></span>
								<span id="infoCommFcltsClCdNm"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">건축시설물관리번호</th>
							<td colspan="5">
								<span id="archFcltsMngNo" ></span>
								<span id="archFcltsMngNoNm"></span>
							</td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="gotoLocation">위치조회</button>
				</div>
			</div>

			<!-- 정보통신시설 첨부파일 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltsFileList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
				<button id="btnDownloadFile">다운로드</button>
				</div>

				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>