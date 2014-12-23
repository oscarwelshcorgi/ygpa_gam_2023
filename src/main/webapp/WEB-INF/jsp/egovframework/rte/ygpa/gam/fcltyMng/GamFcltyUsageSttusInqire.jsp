<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	/**
	 * @Class Name : GamFcltyUsageMng.jsp
	 * @Description : 시설물 사용현황 조회
	 * @Modification Information
	 *
	 *   수정일          수정자                수정내용
	 *  -----------    --------    ---------------------------
	 *  2014.12.08		jckim       최초 생성
	 *
	 * author jckim
	 * since 2014.12.08
	 *
	 * Copyright (C) 2013 by LFIT  All right reserved.
	 */
%>
<script>

<%
/**
 * @FUNCTION NAME : GamFcltyUsageMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
	function GamFcltyUsageSttusInqireModule() {
	}

	GamFcltyUsageSttusInqireModule.prototype = new EmdModule(1000, 600);

	// 페이지가 호출 되었을때 호출 되는 함수
<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
 GamFcltyUsageSttusInqireModule.prototype.loadComplete = function() {
	//	console.log("aaaaa");
	// 자산임대 테이블 설정
	this.$("#mainGrid").flexigrid({
		module : this,
		url : '<c:url value="/fcltyMng/gamFcltyUsageSttusInqireList.do" />',
		dataType : 'json',
		colModel : [ {display : '항코드', name : 'prtAtCode', width : 50, sortable : false, align : 'center'},
		             {display : '관리번호', name : 'mngYearNo', width : 100, sortable : false, align : 'center'},
		             {display : 'GIS 코드', name : 'gisAssets', width : 100, sortable : false, align : 'center'},
		             {display : '문서번호', name : 'docNo', width : 100, sortable : false, align : 'center'},
		             {display : '고지방법', name : 'nticMth', width : 60, sortable : false, align : 'center'},
		             {display : '사용목적', name : 'usagePurps', width : 140, sortable : false, align : 'center'},
		             {display : '사용내역', name : 'usageDtls', width : 120, sortable : false, align : 'center'},
		             {display : '면적', name : 'usageAr', width : 80, sortable : false, align : 'right', displayFormat : 'number'},
		             {display : '금액', name : 'fee', width : 100, sortable : false, align : 'right', displayFormat : 'number'},
		             {display : '허가일', name : 'prmisnDt', width : 90, sortable : false, align : 'center'}

		             /*{display : '항코드', name : 'prtAtCode', width : 40, sortable : false, align : 'center', displayFormat : 'number'},*/
		],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
			module.makeDivValues('#listSumForm', data);
			return data;
		}
	});
	this.$("#sUsagePdFrom").val(EMD.util.getDate(EMD.util.addMonths(-1)));
	this.$("#sUsagePdTo").val(EMD.util.getDate());

/* 클릭 더블클릭 주석처리
		// 마우스 클릭
		this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
			module._mode = 'modify';
		});

	// 마우스 더블 킬릭
		this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
			module._mode = 'modify';
			module.$("#mainTab").tabs("option", {active: 1});
		});
*/
/*
		this.$("#prtFcltyUseSttusInqireFileList").on(
				'onItemSelected',
				function(event, module, row, grid, param) {
					module.makeFormValues('#gamPrtFcltyUseSttusInqireFileForm',
							row);

					if (row.filenmPhysicl != null || row.filenmPhysicl != '') {
						// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
						var filenm = row['filenmPhysicl'];
						var ext = filenm.substring(filenm.lastIndexOf(".") + 1)
								.toLowerCase();
						if (ext == 'jpg' || ext == 'jpeg' || ext == 'bmp'
								|| ext == 'png' || ext == 'gif') {
							$imgURL = module.getImageUrl(filenm);
							module.$("#previewImage").fadeIn(400, function() {
								module.$("#previewImage").attr('src', $imgURL);
							});
						} else {
							module.$("#previewImage").attr(src, '#');
						}
					}
				});

		this.$("#sGrUsagePdFrom").val(EMD.util.getDate(EMD.util.addMonths(-1)));
		this.$("#sGrUsagePdTo").val(EMD.util.getDate());
*/
	};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>

GamFcltyUsageSttusInqireModule.prototype.onButtonClick = function(buttonId) {
		switch (buttonId) {
/*
			case 'btnAdd':
	        	this.addData();
				break;
	        case 'btnSave':
	        	this.saveData();
				break;
			case 'btnDelete':
				this.deleteData();
				break;
			case 'btnIdCheck':
				this.checkId();
				break;
*/
			case 'btnExcelDownload':
				this.downloadExcel();
				break;
		}

	};


<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
	//////////// 확인 부분 ?
	GamFcltyUsageSttusInqireModule.prototype.onSubmit = function() {
		this.loadData();
	};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
	//////////// 확인 부분 ?
/*
	V.prototype.loadData = function() {
		this.$("#mainTab").tabs("option", {active: 0});
		var searchOpt=this.makeFormArgs('#searchForm');
		this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
	};
*/
<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>

GamFcltyUsageMngModule.prototype.downloadExcel = function() {

	var totalCount = Number(this.$('#totalCount').val().replace(/,/gi, ""));
	if (totalCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelMngFeeGubunMng.do');

};



// 수정해야 하는 부분
alert('1');
	GamFcltyUsageSttusInqireModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
		if (oldTabId == "tabs1"
				&& this.$('mainGrid').selectedRowCount() == 0) {
			alert('먼저 항목을 선택 하시기 바랍니다.');
			return false;
		}
		return true;
	};
	alert('2');

/*
	GamFcltyUsageSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {s
		console.log(oldTabId);
		switch (newTabId) {
		case 'tabs1':
			console.log("aaaa");
			break;
		case 'tabs2':
			alert("aaaaa");
			break;
		}
	};
*/
	/*
	//팝업이 종료 될때 리턴 값이 오출 된다.
	//popupId : 팝업 대화상자 아이디
	//msg : 팝업에서 전송한 메시지 (취소는 cancel)
	//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
	GamFcltyUsageMngModule.prototype.onClosePopup = function(popupId, msg, value) {
	    switch (popupId) {
	     case 'selectEntrpsInfoPopup':
	         if (msg != 'cancel') {
	             this.$('#sEntrpscd').val(value.entrpscd);
	             this.$('#sEntrpsNm').val(value.entrpsNm);
	         } else {
	             alert('취소 되었습니다');
	         }
	         break;
	     default:
	         alert('알수없는 팝업 이벤트가 호출 되었습니다.');

	         break;
	     }
	};
	 */
	// 다음 변수는 고정 적으로 정의 해야 함
	var module_instance = new GamFcltyUsageSttusInqireModule();

</script>

<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

	<div id="searchViewStack" class="emdPanel">
		<div class="viewPanel">
			<form id="searchForm">
				<table style="width: 100%;" class="searchPanel">
					<tbody>
						<tr>
							<th>항코드</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd"
								data-default-prompt="전체" data-code-id="GAM019" /></td>

							<td rowSpan="2" width="20%"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>사용기간</th>
							<td colspan="1"><input id="sUsagePdFrom" type="text" class="emdcal"
								size="8"> ~ <input id="sUsagePdTo" type="text"
								class="emdcal" size="8"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
<!-- 		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">  -->
		<div id="mainTab" class="emdTabPanel fillHeight">
			<ul>
				<li><a href="#tabs1" class="emdTab">시설물 사용현황 목록</a></li>
				<li><a href="#tabs2" class="emdTab">시설물 사용현황 내역</a></li>
			</ul>

			<!-- TAB 1 AREA (LIST) -->
			<div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th width="20%" height="20">조회 자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align: right">
<!--
									<button data-cmd="btnAdd">추가</button>
									<button data-cmd="btnDelete">삭제</button>
 -->
	                                <button data-cmd="btnExcelDownload">엑셀다운로드</button>

								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>



			<!-- 탭2 -->
			<div id="tabs2" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<table class="detailPanel" style="width:100%">
						2

<!--
							<tr>
								<th width="20%" height="25">시설 구분</th>
								<td><input type="text" size="25" id="mngFeeFcltySe" maxlength="2"/></td>
								<td><button id="btnIdCheck">시 설 구 분 중 복 여 부 검 사</button>
							</tr>
							<tr>
								<th width="20%" height="25">시설 구분 명</th>
								<td colspan="2"><input type="text" size="70" id="mngFeeFcltySeNm" maxlength="20"/></td>
							</tr>
							<tr>
								<th width="20%" height="25">등록자</th>
                               	<td><span data-column-id="regUsr"></span></td>
								<td><span data-column-id="registDt"></span></td>
							</tr>
							<tr>
								<th width="20%" height="25">수정자</th>
                               	<td><span data-column-id="updUsr"></span></td>
								<td><span data-column-id="updtDt"></span></td>
							</tr>
						</table>
					</form>
					<table style="width:100%">
						<tr>
							<td width="100"></td>
							<td style="text-align:right">
								<button data-cmd="btnSave" class="buttonSave">저장</button>
								<button data-cmd="btnDelete" class="buttonDelete">삭제</button>
							</td>
						</tr>
					</table>
-->
					</table>
				</form>

				</div>
			</div>

		</div>
	</div>
</div>