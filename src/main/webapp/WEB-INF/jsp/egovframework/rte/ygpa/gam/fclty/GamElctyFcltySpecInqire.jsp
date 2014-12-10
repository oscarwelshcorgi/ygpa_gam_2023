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
					{display:"항코드",		name:"gisAssetsPrtAtCode",	width:50,		sortable:false,		align:"center"},
					{display:"항코드명",		name:"gisAssetsPrtAtName",	width:60,		sortable:false,		align:"center"},
					{display:"자산코드",		name:"gisAssetsDisplayCd",	width:60,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:130,		sortable:false,		align:"left"},
					{display:"시설코드", 	    name:"gisPrtFcltyDisplayCd",width:80,		sortable:false,		align:"center"},
					{display:"시설명",		name:"prtFcltyNm",			width:230,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:120,		sortable:false,		align:"left"},
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

	this.$("#elctyFcltySpecInqireList").on('onItemSelected', function(event, module, row, grid, param) {
		module._cmd = "modify";
	});

	this.$("#elctyFcltySpecInqireList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._cmd = "modify";
		module.$("#elctyFcltySpecInqireTab").tabs("option", {active: 1});
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
		url: '/fclty/selectElctyFcltySpecInqireFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",			width:160,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",		width:160,		sortable:true,		align:"left"},
					{display:"물리파일명",	name:"atchFileNmPhysicl",	width:200,		sortable:true,		align:"left"},
					{display:"작성일자",	name:"atchFileWritngDt",	width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});

	this.$("#fcltsFileList").on("onItemSelected", function(event, module, row, grid, param) {

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

GamElctyFcltySpecInqireModule.prototype.onSubmit = function() {
this.loadData();

console.log('debug');
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
		var opts = [{name: 'fcltsMngNo', value: row['fcltsMngNo'] }];
		this.doAction('/fclty/selectElctyFcltySpecInqireDetail.do', opts, function(module, result) {
			if(result.resultCode == "0"){
				module._fcltyManageVO=result.result;
				module.makeDivValues('#fcltyManageVO', module._fcltyManageVO);
			module.loadFileData();
			console.log("DEBUG");
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
	this._deleteDataFileList = [];
	this.$("#fcltyManageVO :input").val("");
	this.$('#fcltsFileList').flexEmptyData();
	this.$("#previewImage").attr("src", "#");
	if(this._cmd == "insert") {
		this.$("#selectGisPrtFcltyCd").enable();
		this.$("#searchGisCodeBtn2").show();
		this.$("#elctyFcltySpecInqireTab").tabs("option", {active: 1});
	} else if (this._cmd == "modify") {
		this.$("#selectGisPrtFcltyCd").disable();
		this.$("#searchGisCodeBtn2").hide();
	} else {
		this.$("#fcltyManageVO :input").val("");
		this.$("#selectGisPrtFcltyCd").enable();
		this.$("#searchGisCodeBtn2").show();
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


/**
 * 정의 된 버튼 클릭 시
 */
GamElctyFcltySpecInqireModule.prototype.onButtonClick = function(buttonId) {
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
/*
			 EmdModule.prototype.downPfPhoto = function(b, c) {
			    $.fileDownload(EMD.context_root + "/download/pfDownloadFile.do", {
			        data: {
			            requestedFile: b,
			            downloadFileName: c
			        },
			        httpMethod: "POST"
			    })
			};
 */
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
							<th>항코드</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>자산코드</th>
							<td>
								<input id="sAssetsCd" type="text" size="3" maxlength="3" />&nbsp;-&nbsp;
								<input id="sAssetsSubCd" type="text" size="2" maxlength="2" />
								<button id="searchGisCodeBtn" class="popupButton">선택</button>
							</td>
							<th>전기시설분류</th>
							<td>
								<input id="searchFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM061" />
							</td>
							<td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>전기시설명</th>
							<td colspan="5"><input id="sPrtFcltyNm" type="text" size="60" maxlength="40"  /></td>
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

					<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="elctyFcltySpecInqireList" data-style="default">맵조회</button>
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
							<th width="12%" height="17" class="required_text">항코드</th>
							<td><span id="gisAssetsPrtAtCode"></span>  <span id="gisAssetsPrtAtName"></span></td>

							<th width="12%" height="17" class="required_text">GIS 자산코드</th>
							<td colspan="3">
								<span id="gisAssetsCd"></span>-
								<span id="gisAssetsSubCd"></span>-
								<span id="gisAssetsPrtAtCode2"></span>
								<button id="searchGisCodeBtn2" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">GIS 자산명</th>
							<td><span id="gisAssetsNm"></span></td>
							<th width="12%" height="17" class="required_text">지번</th>
							<td>
								<span id="gisAssetsLnm" title="지번 앞자리"></span>&nbsp;-&nbsp;
								<span id="gisAssetsLnmSub"title="지번 뒷자리"></span>
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
								<span class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM061" id="selectGisPrtFcltyCd" data-required="true" data-column-id="gisPrtFcltyCd"></span>

							</td>
							<th width="12%" height="17" class="required_text">전기시설명</th>
							<td><span id="prtFcltyNm"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<span id="fcltsMngGroupNo"></span>
								<span id="fcltsMngGroupNoNm"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설규격</th>
							<td colspan="3"><span id="prtFcltyStndrd" title="시설규격"></span></td>
							<th width="12%" height="17" class="required_text">시설단위</th>
							<td><span id="prtFcltyUnit"title="시설단위"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설치일자</th>
							<td><span id="prtFcltyInstlDt" title="설치일자" ></span></td>
							<th width="12%" height="17" class="required_text">변경일자</th>
							<td colspan="3"><span id="prtFcltyChangeDt" title="변경일자" ></span></td>
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
							<th width="12%" height="17" class="required_text">용도</th>
							<td colspan="5"><span id="prpos" title="용도"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">용량</th>
							<td><span id="capa" title="용량"></span></td>
							<th width="12%" height="17" class="required_text">전압</th>
							<td><span id="volt"  title="전압" ></span></td>
							<th width="12%" height="17" class="required_text">출력</th>
							<td><span id="output" title="출력"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">형식</th>
							<td colspan="5"><span id="fmt" title="형식" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">규격</th>
							<td colspan="3"><span id="stndrd"title="규격"></span></td>
							<th width="12%" height="17" class="required_text">수량</th>
							<td><span id="qy" title="수량"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설치일자</th>
							<td><span id="instlDt" title="설치일자"></span></td>
							<th width="12%" height="17" class="required_text">제작일자</th>
							<td><span id="mfcDt" title="제작일자" ></span></td>
							<th width="12%" height="17" class="required_text">관리자</th>
							<td><span id="manager"title="관리자" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">제작회사</th>
							<td colspan="5"><span id="mfcCmpny"title="제작회사" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">사용업체</th>
							<td colspan="5"><span id="usageEntrps" title="사용업체" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">관로길이</th>
							<td><span id="ductLineLt"class="ygpaNumber" data-decimal-point="2" title="관로길이" ></span></td>
							<th width="12%" height="17" class="required_text">케이블연장</th>
							<td><span id="cableExt" class="ygpaNumber" data-decimal-point="2" title="케이블연장" ></span></td>
							<th width="12%" height="17" class="required_text">조명탑높이</th>
							<td><span id="lightwrHt" class="ygpaNumber" data-decimal-point="2" title="조명탑높이" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">연료소비량</th>
							<td><span id="fuelConsum" class="ygpaNumber" data-decimal-point="2" title="연료소비량" ></span></td>
							<th width="12%" height="17" class="required_text">연료탱크</th>
							<td><span id="fuelTank" class="ygpaNumber" data-decimal-point="2"  title="연료탱크" ></span></td>
							<th width="12%" height="17" class="required_text">유량</th>
							<td><span id="oilQty" class="ygpaNumber" data-decimal-point="2" title="유량" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">결선방식</th>
							<td><span id="fnlMthd" title="결선방식" ></span></td>
							<th width="12%" height="17" class="required_text">공급전원</th>
							<td><span id="spplyPwr" title="공급전원" ></span></td>
							<th width="12%" height="17" class="required_text">공급TR</th>
							<td><span id="spplyTr" title="공급TR" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">한전변전소</th>
							<td><span id="korElecSubstn" title="한전변전소" ></span></td>
							<th width="12%" height="17" class="required_text">구간 FROM</th>
							<td><span id="sectionFrom" title="구간 FROM" ></span></td>
							<th width="12%" height="17" class="required_text">구간 TO</th>
							<td><span id="sectionTo" title="구간 TO" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">전주높이</th>
							<td><span id="premainHt" class="ygpaNumber" data-decimal-point="2" title="전주높이" ></span></td>
							<th width="12%" height="17" class="required_text">전주규격</th>
							<td><span id="premainStndrd" title="전주규격" ></span></td>
							<th width="12%" height="17" class="required_text">전주수량</th>
							<td><span id="premainQy" class="ygpaNumber" title="전주수량" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">등기구형식</th>
							<td><span id="lightappFmt" title="등기구형식" ></span></td>
							<th width="12%" height="17" class="required_text">등기구규격</th>
							<td><span id="lightappStndrd" title="등기구규격" ></span></td>
							<th width="12%" height="17" class="required_text">등기구수량</th>
							<td><span id="lightappQy" class="ygpaNumber" title="등기구수량" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">LAMP형식</th>
							<td><span id="lampFmt"title="LAMP형식" ></span></td>
							<th width="12%" height="17" class="required_text">LAMP용량</th>
							<td><span id="lampCapa" title="LAMP용량" ></span></td>
							<th width="12%" height="17" class="required_text">LAMP수량</th>
							<td><span id="lampQy" class="ygpaNumber" title="LAMP수량" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">조명탑등기구수량</th>
							<td><span id="lightwrLightappQy"class="ygpaNumber" title="조명탑등기구수량" ></span></td>
							<th width="12%" height="17" class="required_text">조명탑등기구 분류코드</th>
							<td><span id="lightwrLightappClcd" title="조명탑등기구 분류코드" ></span></td>
							<th width="12%" height="17" class="required_text">조명탑 LAMP수량</th>
							<td><span id="lightwrLampQy" class="ygpaNumber" title="조명탑 LAMP수량" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">조명탑 LAMP분류코드</th>
							<td colspan="5"><span id="lightwrLampClcd" title="조명탑 LAMP분류코드" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">비고</th>
							<td colspan="5"><span id="rm" title="비고" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">위치</th>
							<td colspan="5"><span id="loc" title="위치" ></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">전기시설물분류코드</th>
							<td colspan="5">
								<span id="elctyFcltsClCd" ></span>
								<span id="elctyFcltsClCdNm"></span>

							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">건축시설물관리번호</th>
							<td colspan="5">
								<span id="archFcltsMngNo" ></span>
								<span id="archFcltsMngNoNm" ></span>

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
				<table id="fcltsFileList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">

					<button id="btnDownloadFile">다운로드</button>
			</div>

				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>