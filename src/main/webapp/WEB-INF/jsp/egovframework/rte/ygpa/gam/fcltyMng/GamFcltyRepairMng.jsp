<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyRepairRepairMng.jsp
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
<%-- <validator:javascript formName="gamFcltyRepairCode" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamFcltyRepairPhoto" staticJavascript="false" xhtml="true" cdata="false" /> --%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyRepairMngtModule() {
}

GamFcltyRepairMngtModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyRepairMngtModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#fcltyMngMngtList").flexigrid({
		module: this,
		url: '<c:url value="/fcltyMng/selectFcltyRepairMngList.do" />',
		dataType: "json",
		colModel : [
					{display:"시행년도", 			name:"enforceYear",				width:60, 		sortable:false,		align:"center"},
					{display:"계약번호", 			name:"ctrtNo",					width:200, 		sortable:false,		align:"center"},
					{display:"하자보수순번", 		name:"mntnRprSeq",				width:120, 		sortable:false,		align:"center"},
					{display:"하자보수구분",		name:"mntnRprSe",				width:80, 		sortable:false,		align:"left"},
					{display:"하자보수공사명",		name:"mntnRprCnstNm",			width:250, 		sortable:false,		align:"left"},
					{display:"공사금액", 			name:"mntnRprCnstAmt",			width:150, 		sortable:false,		align:"center"},
					{display:"하자보수예산", 		name:"mntnRprBdgt",				width:150, 		sortable:false,		align:"center"},
					{display:"하자보수부위", 		name:"mntnRprPart",				width:250, 		sortable:false,		align:"center"}
			],
		height: "auto"
	});


	this.$("#mntnRprObjFcltsF").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamFcltyinfo9.do" />',
		dataType: "json",
		colModel : [
					{display:"관리번호",			name:"bound",			width:60,		sortable:false,		align:"center"},
					{display:"하자보수공법",		name:"strySe",			width:80,		sortable:false,		align:"center"},
					{display:"단위",			name:"ar",				width:80,		sortable:false,		align:"center"},
					{display:"수량",		name:"wallFnsh",		width:140,		sortable:false,		align:"center"},
					{display:"단가",		name:"flrFnsh",			width:140,		sortable:false,		align:"center"},
					{display:"공사금액",			name:"ceil",			width:140,		sortable:false,		align:"center"},
					{display:"비고",			name:"rm",				width:200,		sortable:false,		align:"center"}
			],
		height: "auto"
	});



 	this.$("#fcltyFileList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamConstFcltySpecFileList.do"/>',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",				width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",			width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",				width:240,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",			width:200,		sortable:true,		align:"left"},
					{display:"물리파일명",	name:"atchFileNmPhysicl",		width:200,		sortable:true,		align:"left"},
					{display:"생성일시",	name:"atchFileWritngDt",		width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});

	


};



GamFcltyRepairMngtModule.prototype.removeGisAssetPhotoItem = function() {
	var rows = this.$("#fcltyMngPhotoList").selectedRows();

    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }

    if(this.$("#fcltyMngPhotoList").selectedRowIds().length>0) {
    	for(var i=this.$("#fcltyMngPhotoList").selectedRowIds().length-1; i>=0; i--) {

    		var row = this.$("#fcltyMngPhotoList").flexGetRow(this.$("#fcltyMngPhotoList").selectedRowIds()[i]);

            if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataFileList[this._deleteDataFileList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#fcltyMngPhotoList").flexRemoveRow(this.$("#fcltyMngPhotoList").selectedRowIds()[i]);

        	this._edited=true;
		}

    	this.$("#previewImage").attr("src","");
    	alert("삭제되었습니다.");
	}

    this.$("#fcltyMngGisPhotoForm").find(":input").val("");
    this._editDataFile = null;
};



GamFcltyRepairMngtModule.prototype.clearCodePage = function() {
	this.$('#fcltyMngManageVO :input').val('');
};

GamFcltyRepairMngtModule.prototype.clearPhotoPage = function() {
	this.$('#fcltyMngPhotoList').flexEmptyData();
	this.$('#fcltyMngGisPhotoForm :input').val('');
	this.$('#previewImage').attr('src', '');
};

GamFcltyRepairMngtModule.prototype.loadPhotoList = function() {
	var row = this.$('#fcltyMngMngtList').selectedRows();
	if(row.length <= 0) {
 		this.clearPhotoPage();
		return;
	}
	row=row[0];
	var searchOpt = [
	                { name: 'gisAssetsPrtAtCode', value: row['gisAssetsPrtAtCode'] },
	                { name: 'gisAssetsCd', value: row['gisAssetsCd'] },
	                { name: 'gisAssetsSubCd', value: row['gisAssetsSubCd'] },
	                { name: 'gisPrtFcltyRepairCd', value: row['gisPrtFcltyRepairCd'] },
	                { name: 'gisPrtFcltyRepairSeq', value: row['gisPrtFcltyRepairSeq'] },
	                { name: 'prtFcltyRepairSe', value: row['prtFcltyRepairSe'] }
	              ];
	this.clearPhotoPage();

 	this.$('#fcltyMngPhotoList').flexOptions({params:searchOpt}).flexReload();
};
/**
 * 탭 변경시 실행 이벤트
 */
 GamFcltyRepairMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		console.log('debug');
		if(this._cmd!="insert") {
			var row = this.$('#fcltyMngMngtList').selectedRows();
			if(row.length <= 0) {
		 		this.clearCodePage();
				this.$("#prtFcltyRepairSeNm").hide();
		 		if(this._params.action!=null || this._params.action=='prtFcltyRepairMngt') {
		 			var prtFcltyRepair = [
		 			                { name: 'gisAssetsPrtAtCode', value: this._params.gisPrtAtCode },
		 			                { name: 'gisAssetsCd', value: this._params.gisAssetsCd },
		 			                { name: 'gisAssetsSubCd', value: this._params.gisAssetsSubCd },
		 			                { name: 'gisPrtFcltyRepairCd', value: this._params.gisPrtFcltyRepairCd },
		 			                { name: 'gisPrtFcltyRepairSeq', value: this._params.gisPrtFcltyRepairSeq },
		 			                { name: 'prtFcltyRepairSe', value: this._params.prtFcltyRepairSe }
		 			              ];
		 	     	 	this.doAction('<c:url value="/fcltyMng/gamElctyFcltyRepairDetail.do" />', prtFcltyRepair, function(module, result) {
		 	     	 		if(result.resultCode == "0"){
		 	     	 			module.clearCodePage();
		 	     	 			module._fcltyMngItem=result.result;
		 	     	 			module.makeFormValues('#fcltyMngManageVO', result.result);	// 결과값을 채운다.
		 	     	 			module.$("#beforeGisPrtFcltyRepairCd").val(module.$("#gisPrtFcltyRepairCd").val());
			                    module.$("#beforeGisPrtFcltyRepairSeq").val(module.$("#gisPrtFcltyRepairSeq").val());

			                    if(result.result.gisPrtFcltyRepairCd==10){
			                    	module.$("#gisPrtFcltyRepairCdSub").empty();
			                    	module.$("#gisPrtFcltyRepairCdSub").append('<option value="가로등" id="가로등">가로등</option><option value="조명탑" id="조명탑">조명탑</option><option value="신호등" id="신호등">신호등</option><option value="보안등" id="보안등">보안등</option><option value="기타" id="기타">기타</option>');

			                     }else if(result.result.gisPrtFcltyRepairCd==11){
			                     	module.$("#gisPrtFcltyRepairCdSub").empty();
			                     	module.$("#gisPrtFcltyRepairCdSub").append('<option value="이동식" id="이동식">이동식</option><option value="고정식" id="고정식">고정식</option><option value="기타" id="기타">기타</option>');

			                     }else if(result.result.gisPrtFcltyRepairCd==12){
			                     	module.$("#gisPrtFcltyRepairCdSub").empty();
			                     	module.$("#gisPrtFcltyRepairCdSub").append('<option value="태양광" id="태양광">태양광</option><option value="풍력" id="풍력">풍력</option><option value="지력" id="지력">지력</option><option value="수력" id="수력">수력</option><option value="기타" id="기타">기타</option>');

			                     }else{
			                     	module.$("#gisPrtFcltyRepairCdSub").empty();
			                     	module.$("#gisPrtFcltyRepairCdSub").append('<option value="기타" id="기타">기타</option>');
			                     }
			                    module.$("#gisPrtFcltyRepairCdSub").find("#"+result.result.gisPrtFcltyRepairCdSub).attr("selected", "selected");
		 	     	 		}
		 	     	 		else {
		 	     	 			alert(result.resultMsg);
		 	     	 		}
		 	     	 	});
	 	     	 	}
				return;
			}
			row=row[0];
			var prtFcltyRepair = [
			                { name: 'gisAssetsPrtAtCode', value: row['gisAssetsPrtAtCode'] },
			                { name: 'gisAssetsCd', value: row['gisAssetsCd'] },
			                { name: 'gisAssetsSubCd', value: row['gisAssetsSubCd'] },
			                { name: 'gisPrtFcltyRepairCd', value: row['gisPrtFcltyRepairCd'] },
			                { name: 'gisPrtFcltyRepairSeq', value: row['gisPrtFcltyRepairSeq'] },
			                { name: 'prtFcltyRepairSe', value: row['prtFcltyRepairSe'] }
			              ];
	     	 	this.doAction('<c:url value="/fcltyMng/gamElctyFcltyRepairDetail.do" />', prtFcltyRepair, function(module, result) {
	     	 		if(result.resultCode == "0"){
	     	 			module.clearCodePage();
	     	 			module._fcltyMngItem=result.result;
	     	 			module.makeFormValues('#fcltyMngManageVO', result.result);	// 결과값을 채운다.
	     	 			module.$("#beforeGisPrtFcltyRepairCd").val(module.$("#gisPrtFcltyRepairCd").val());
	                    module.$("#beforeGisPrtFcltyRepairSeq").val(module.$("#gisPrtFcltyRepairSeq").val());

	                    if(result.result.gisPrtFcltyRepairCd==10){
	                    	module.$("#gisPrtFcltyRepairCdSub").empty();
	                    	module.$("#gisPrtFcltyRepairCdSub").append('<option value="가로등" id="가로등">가로등</option><option value="조명탑" id="조명탑">조명탑</option><option value="신호등" id="신호등">신호등</option><option value="보안등" id="보안등">보안등</option><option value="기타" id="기타">기타</option>');

	                     }else if(result.result.gisPrtFcltyRepairCd==11){
	                     	module.$("#gisPrtFcltyRepairCdSub").empty();
	                     	module.$("#gisPrtFcltyRepairCdSub").append('<option value="이동식" id="이동식">이동식</option><option value="고정식" id="고정식">고정식</option><option value="기타" id="기타">기타</option>');

	                     }else if(result.result.gisPrtFcltyRepairCd==12){
	                     	module.$("#gisPrtFcltyRepairCdSub").empty();
	                     	module.$("#gisPrtFcltyRepairCdSub").append('<option value="태양광" id="태양광">태양광</option><option value="풍력" id="풍력">풍력</option><option value="지력" id="지력">지력</option><option value="수력" id="수력">수력</option><option value="기타" id="기타">기타</option>');

	                     }else{
	                     	module.$("#gisPrtFcltyRepairCdSub").empty();
	                     	module.$("#gisPrtFcltyRepairCdSub").append('<option value="기타" id="기타">기타</option>');
	                     }
	                    module.$("#gisPrtFcltyRepairCdSub").find("#"+result.result.gisPrtFcltyRepairCdSub).attr("selected", "selected");
	     	 		}
	     	 		else {
	     	 			alert(result.resultMsg);
	     	 		}
	     	 	});
		}
		else if(this._cmd=="insert") {
	 			this.clearCodePage();
		}
		break;
	case "tabs3":
		this._deleteDataFileList=[];
		if(this._cmd!="insert") {
			var row = this.$('#fcltyMngMngtList').selectedRows();
			if(row.length <= 0) {
		 		this.clearPhotoPage();
		 		if(this._params.action!=null || this._params.action=='prtFcltyRepairMngt') {
		 			var prtFcltyRepair = [
		 			                { name: 'gisAssetsPrtAtCode', value: this._params.gisPrtAtCode },
		 			                { name: 'gisAssetsCd', value: this._params.gisAssetsCd },
		 			                { name: 'gisAssetsSubCd', value: this._params.gisAssetsSubCd },
		 			                { name: 'gisPrtFcltyRepairCd', value: this._params.gisPrtFcltyRepairCd },
		 			                { name: 'gisPrtFcltyRepairSeq', value: this._params.gisPrtFcltyRepairSeq },
		 			                { name: 'prtFcltyRepairSe', value: this._params.prtFcltyRepairSe }
		 			              ];
		 		 	this.$('#fcltyMngPhotoList').flexOptions({params:prtFcltyRepair}).flexReload();
	 	     	 	}
				return;
			}
			row=row[0];
			var prtFcltyRepair = [
			                { name: 'gisAssetsPrtAtCode', value: row['gisAssetsPrtAtCode'] },
			                { name: 'gisAssetsCd', value: row['gisAssetsCd'] },
			                { name: 'gisAssetsSubCd', value: row['gisAssetsSubCd'] },
			                { name: 'gisPrtFcltyRepairCd', value: row['gisPrtFcltyRepairCd'] },
			                { name: 'gisPrtFcltyRepairSeq', value: row['gisPrtFcltyRepairSeq'] },
			                { name: 'prtFcltyRepairSe', value: row['prtFcltyRepairSe'] }
			              ];
	     	 	this.doAction('<c:url value="/fcltyMng/gamElctyFcltyRepairDetail.do" />', prtFcltyRepair, function(module, result) {
	     	 		if(result.resultCode == "0"){
	     	 			module.clearPhotoPage();
	     	 			module._fcltyMngItem=result.result;
	     	 			module.makeFormValues('#fcltyMngManageVO', result.result);	// 결과값을 채운다.
	     	 		}
	     	 		else {
	     	 			alert(result.resultMsg);
	     	 		}
	     	 	});
				this.loadPhotoList();
		}
		else if(this._cmd=="insert") {
			this.clearPhotoPage();
		}
		break;
	}
};

/**
 * 팝업 close 이벤트
 */
GamFcltyRepairMngtModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){

		// 상세화면
		case "searchGisCodePopup":
			this.$("#gisAssetsPrtAtCodeStr").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsPrtAtCode").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsPrtAtName").val(value["gisAssetsPrtAtCodeNm"]);
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
			this.$("#prtFcltyRepairMngEntrpsCd").val(value["entrpscd"]);
			this.$("#prtFcltyRepairMngEntrpsNm").val(value["entrpsNm"]);
		break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			
		break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyRepairMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="fcltyMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>시설물업무구분</th>
							<td>
								<select id="ttt">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th>하자보수명</th>
							<td><input type="text" id="searchFcltyRepairCd" size="50" /></td>
							<td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>하자보수구분</th>
							<td>
								<select id="ttt">
									<option value="">선택</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th>하자보수공사시작일</th>
							<td>
								<input id="planBeginDt" type="text" class="emdcal" size="15" /> ~ <input id="planBeginDt" type="text" class="emdcal" size="15" />
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
		<div id="fcltyMngMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">하자보수내역 목록</a></li>
				<li><a href="#tabs2" class="emdTab">하자보수내역 상세</a></li>
				<li><a href="#tabs3" class="emdTab">하자보수 대상시설물</a></li>
				<li><a href="#tabs4" class="emdTab">하자보수 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyMngMngtList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="addBtn">시설추가</button>
					<button id="deleteBtn">시설삭제</button>
				</div>
			</div>


			<!-- 하자보수내역 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyMngManageVO1">
					<table class="editForm"  style="width:100%;">
						<tr>
							<th width="15%" height="23" class="required_text">시행년도</th>
							<td>
								<select id="gisAssetsPrtAtName">
									<option value="2014">2014</option>
									<option value="2013">2013</option>
								</select>
							</td>
							<th width="15%" height="23" class="required_text">시설물업무구분</th>
							<td><input type="text" size="20" id="gisAssetsPrtAtName" /></td>
							<th width="15%" height="23" class="required_text">하자보수구분</th>
							<td><input type="text" size="20" id="gisAssetsPrtAtName" /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">계약번호</th>
							<td colspan="3"><input type="text" size="80" id="gisAssetsPrtAtName" /></td>
							<th width="15%" height="23" class="required_text">하자보수순번</th>
							<td><input type="text" size="20" id="gisAssetsNm" /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">공사명</th>
							<td colspan="5"><input id="gisAssetsLocplc" type="text" size="125" title="소재지"  /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">하자보수부위</th>
							<td colspan="5"><input id="gisAssetsLocplc" type="text" size="125" title="소재지"  /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">예산</th>
							<td colspan="3"><input id="gisAssetsLocplc" type="text" size="80" title="소재지"  /></td>
							<th width="15%" height="23" class="required_text">공사시작일자</th>
							<td><input id="gisAssetsLocplc" type="text" size="20" title="소재지"  /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">공사금액</th>
							<td colspan="3"><input id="gisAssetsLocplc" type="text" size="80" title="소재지"  /></td>
							<th width="15%" height="23" class="required_text">공사종료일자</th>
							<td><input id="gisAssetsLocplc" type="text" size="20" title="소재지"  /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">설계자</th>
							<td><input id="gisAssetsLocplc" type="text" size="20" title="소재지"  /></td>
							<th width="15%" height="23" class="required_text">시공자</th>
							<td colspan="3"><input id="gisAssetsLocplc" type="text" size="80" title="소재지"  /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">책임기술자</th>
							<td><input id="gisAssetsLocplc" type="text" size="20" title="소재지"  /></td>
							<th width="15%" height="23" class="required_text">공사감독자</th>
							<td colspan="3"><input id="gisAssetsLocplc" type="text" size="80" title="소재지"  /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">하자보수내용</th>
							<td colspan="5"><textarea id="gisAssetsLocplc" cols="130" rows="10"></textarea></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">비고</th>
							<td colspan="5"><input id="gisAssetsLocplc" type="text" size="125" title="소재지"  /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="saveBtn">저장</button>
				</div>
			</div>
			
			<!-- 하자보수 대상 시설물 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="mntnRprObjFcltsF" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button class="text" id="mntnRprObjFcltsFPopupBtn">추가/편집</button>
					<button id="saveBtn">저장</button>
				</div>
			</div>

			<!-- 하자보수내역 첨부파일 -->
			<div id="tabs4" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyFileList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnUploadFile">업로드</button>
					<button id="btnDownloadFile">다운로드</button>
					<button id="btnRemoveFile">삭제</button>
					<button id="btnSaveFile">저장</button>
				</div>
				<form id="fcltyMngGisPhotoForm">
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
<!-- 				<button id="btnApplyPhotoData">적용</button>-->
				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>