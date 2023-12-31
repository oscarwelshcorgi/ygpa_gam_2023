<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamElctyFcltyMngt.jsp
  * @Description : 전기시설사용목록조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.02.05  kok          최초 생성
  *  2014.06.16  sj          최초 생성
  *
  * author kok
  * since 2014.02.05
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyMngtModule() {}

GamFcltyMngtModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyMngtModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#fcltyMngtList").flexigrid({
		module: this,
		url: '/fclty/gamElctyFcltyMngtList.do',
		dataType: "json",
		colModel : [
					{display:"항코드",		name:"gisAssetsPrtAtCode",	width:40,		sortable:false,		align:"center"},
					{display:"항코드명",		name:"gisAssetsPrtAtName",	width:60,		sortable:false,		align:"center"},
					{display:"자산코드",		name:"gisAssetsDisplay",	width:60,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:120,		sortable:false,		align:"left"},
					{display:"시설코드", 	name:"gisPrtFcltyDisplay",	width:80,		sortable:false,		align:"center"},
					{display:"시설명",		name:"prtFcltyNm",			width:230,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:120,		sortable:false,		align:"left"},
// 					{display:"위치",		 	name:"gisAssetsLocNm",		width:120,		sortable:false,		align:"left"},
					{display:"시설규격",	name:"prtFcltyStndrd",		width:240,		sortable:false,		align:"left"},
					{display:"수량",  	name:"prtFcltyUnit",		width:80,		sortable:false,		align:"left"},
					{display:"시설담당",		name:"prtPrtFcltyMnger",	width:60,		sortable:false,		align:"center"},
// 					{display:"관리업체",		name:"prtFcltyMngEntrpsCd",	width:60,		sortable:false,		align:"center"},
// 					{display:"관리업체명", 		name:"prtFcltyMngEntrpsNm",	width:180,		sortable:false,		align:"left"},
					{display:"설치일자",		name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"},
					{display:"변경일자",		name:"prtFcltyChangeDt",	width:80,		sortable:false,		align:"center"}
			],
		height: "auto"
	});

	this.$("#fcltyMngtList").on("onItemSelected", function(event, module, row, grid, param) {

		module.makeDivValues('#fcltyManageVO', row); // 결과값을 채운다.
		module.$("#fcltyManageVO :input").val("");
		module.makeFormValues("#fcltyManageVO", row);
		module.getFormValues("#fcltyManageVO", row);
		module.$("#fcltyMngtList").selectedRowIds()[0];
		module.$("#cmd").val("modify");
		var searchOpt =
        [
         {	name: 'gisPrtFcltyCd' , value: module.$("#gisPrtFcltyCd").text()},
         {	name: 'gisPrtFcltySeq' , value: module.$("#gisPrtFcltySeq").text()},
         {  name: 'gisAssetsPrtAtCode' , value:  module.$("#gisAssetsPrtAtCode").text()},
         {	name: 'gisAssetsCd' , value:  module.$("#gisAssetsCd").text()},
         {  name: 'gisAssetsSubCd' , value:  module.$("#gisAssetsSubCd").text()},
         {	name: 'prtFcltySe' , value:  module.$("#prtFcltySe").text()}
         ];
        module.$("#fcltyPhotoList").flexOptions({params:searchOpt}).flexReload();
        module._fcltyItem = row;
	});

	this.$("#fcltyMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#fcltyMngtListTab").tabs("option", {active: 1});		// 탭을 전환 한다.
	});

	this.$("#fcltyPhotoList").flexigrid({
		module: this,
		url: '/fclty/gamElctyFcltyPhotoList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"prtFcltyPhotoSeq",	width:40,		sortable:true,		align:"center"},
					{display:"사진제목",	name:"photoSj",				width:160,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"filenmLogic",			width:160,		sortable:true,		align:"left"},
					{display:"물리파일명",	name:"filenmPhysicl",		width:160,		sortable:true,		align:"left"},
					{display:"파일설명",	name:"photoDesc",			width:200,		sortable:true,		align:"left"},
					{display:"촬영일시",	name:"shotDt",				width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});

	this.$("#fcltyPhotoList").on("onItemSelected", function(event, module, row, grid, param) {

		if(row.filenmPhysicl != null || row.filenmPhysicl != "") {

			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm = row["filenmPhysicl"];
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
};
this.$("#searchFcltyCd").on("change", {module: this}, function(event) {
	if($(this).val()==10){
		event.data.module.$("#searchFcltySeq").empty();
		event.data.module.$("#searchFcltySeq").append('<option value="">전체</option><option value="가로등" id="가로등">가로등</option><option value="조명탑" id="조명탑">조명탑</option><option value="신호등" id="신호등">신호등</option><option value="보안등" id="보안등">보안등</option><option value="기타" id="기타">기타</option>');

     }else if($(this).val()==11){
    	 event.data.module.$("#searchFcltySeq").empty();
    	 event.data.module.$("#searchFcltySeq").append('<option value="">전체</option><option value="이동식" id="이동식">이동식</option><option value="고정식" id="고정식">고정식</option><option value="기타" id="기타">기타</option>');

     }else if($(this).val()==12){
    	 event.data.module.$("#searchFcltySeq").empty();
    	 event.data.module.$("#searchFcltySeq").append('<option value="">전체</option><option value="태양광" id="태양광">태양광</option><option value="풍력" id="풍력">풍력</option><option value="지력" id="지력">지력</option><option value="수력" id="수력">수력</option><option value="기타" id="기타">기타</option>');

     }else{
    	 event.data.module.$("#searchFcltySeq").empty();
     	event.data.module.$("#searchFcltySeq").append('<option value="">전체</option><option value="기타" id="기타">기타</option>');
     }
});

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

		// 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisCodePopup2", "자산코드", '/popup/showAssetsCd.do', {});
		break;

		// 업체조회 팝업
		case "searchEntrpsCdBtn":
			this.doExecuteDialog("searchEntrpsCdPopup", "업체조회", '/popup/showEntrpsInfo.do', {});
		break;

		case "btnAddGisMap":
			if(this.$("#assetCodeList").selectedRowIds().length>0) {
				var row = this.$("#erpAssetCodeList").selectedRows();
				this.addGisAssetsCdMap("GAC", {"gisPrtAtCode": "620", "gisAssetsCd": "LNF", "gisAssetsSubCd": "01"});
			}
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

		case 'btnDownloadFile': //사진 다운로드
			var selectRow = this.$('#fcltyPhotoList').selectedRows();
			if(selectRow.length > 0) {
				var row=selectRow[0];
				this.downPfPhoto(row["filenmPhysicl"], row["filenmLogic"]);
			} else {
				alert("자료가 선택되지 않았습니다.");
			}
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
		break;
	}
};


/**
 * 팝업 close 이벤트
 */
GamFcltyMngtModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){

		// 조회화면
		case "searchGisCodePopup2":
			this.$("#searchAssetsCd").val(value["gisAssetsCd"]);
			this.$("#searchAssetsSubCd").val(value["gisAssetsSubCd"]);
		break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			
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
								<input id="searchAssetsCd" type="text" size="3" maxlength="3" title="검색조건" />&nbsp;-&nbsp;
								<input id="searchAssetsSubCd" type="text" size="2" maxlength="2" title="검색조건" />
								<button id="searchPopupBtn" class="popupButton">선택</button>
							</td>
							<th>전기시설코드</th>
							<td>
								<input id="searchFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM061" />&nbsp;-&nbsp;
								<input id="searchFcltySeq" type="text" size="4" maxlength="4" title="검색조건" />
							</td>
							<td rowSpan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>전기시설명</th>
							<td colspan="5"><input id="searchKeyword" type="text" size="60" maxlength="40" title="검색조건"  /></td>
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
		<div id="fcltyMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">전기시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">전기시설 상세</a></li>
				<li><a href="#tabs3" class="emdTab">전기시설 사진</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyMngtList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="fcltyMngtList" data-style="default">맵조회</button>
				</div>
			</div>

			<!-- 기계시설 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<div id="fcltyManageVO">

					<input type="hidden" id="cmd" />
			<!--
					<input type="hidden" id="laCrdnt" />
					<input type="hidden" id="loCrdnt" />
			-->
					<table class="detailForm"  style="width:100%;">
						<tr>
							<th width="15%" height="23" class="required_text">항코드</th>
							<td><span id="gisAssetsPrtAtCodeStr"></span></td>
							<th width="15%" height="23" class="required_text">항코드명</th>
							<td><span id="gisAssetsPrtAtName"></span></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">GIS 자산코드</th>
							<td>
								<span id="gisAssetsCd"></span>&nbsp;-&nbsp;
								<span id="gisAssetsSubCd"></span>&nbsp;-&nbsp;
								<span id="gisAssetsPrtAtCode"></span>
							</td>
							<th width="15%" height="23" class="required_text">GIS 자산명</th>
							<td><span id="gisAssetsNm"></span></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">소재지</th>
							<td><span id="gisAssetsLocplc"></span></td>
							<th width="15%" height="23" class="required_text">지번</th>
							<td>
								<span id="gisAssetsLnm"></span>&nbsp;-&nbsp;
								<span id="gisAssetsLnmSub"></span>
							</td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">전기시설코드</th>
							<td>
								<span id="gisPrtFcltyCd"></span>&nbsp;-&nbsp;
								<span id="gisPrtFcltySeq"></span>
							</td>

						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">시설분류</th>
							<td><span id="prtFcltySeNm"></span></td>
							<th width="15%" height="23" class="required_text">시설구분</th>
							<td><span id="gisPrtFcltyCdSub"></span></td>
							<!--
							<th width="15%" height="23" class="required_text">위치</th>
							<td><span id="gisAssetsLocNm"></span></td>
							 -->
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">시설규격</th>
							<td><span id="prtFcltyStndrd"></span></td>
							<th width="15%" height="23" class="required_text">시설수량</th>
							<td><span id="prtFcltyUnit"></span></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">시설담당</th>
							<td><span id="prtPrtFcltyMnger"></span></td>
							<th width="15%" height="23" class="required_text">전기시설명</th>
							<td><span id="prtFcltyNm"></span></td>
<!-- 							<th width="15%" height="23" class="required_text">관리업체코드</th> -->
<!-- 							<td><span id="prtFcltyMngEntrpsCd"></span></td> -->
<!-- 							<th width="15%" height="23" class="required_text">관리업체명</th> -->
<!-- 							<td><span id="prtFcltyMngEntrpsNm"></span></td> -->
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">설치일자</th>
							<td><span id="prtFcltyInstlDt"></span></td>
							<th width="15%" height="23" class="required_text">변경일자</th>
							<td><span id="prtFcltyChangeDt"></span></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">위도좌표</th>
							<td><span id="laCrdnt"></span></td>
							<th width="15%" height="23" class="required_text">경도좌표</th>
							<td><span id="loCrdnt"></span></td>
						</tr>
					</table>
				</div>
				<div class="emdControlPanel">
					<button id="gotoLocation">위치조회</button>
				</div>
			</div>

			<!-- 전기시설 사진 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyPhotoList" style="display:none" class="fillHeight"></table>
				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
				<div class="emdControlPanel">
					<button id="btnDownloadFile">다운로드</button>
				</div>
			</div>
		</div>
	</div>
</div>