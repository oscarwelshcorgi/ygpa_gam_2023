<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFenderSttusInqire.jsp
 * @Description : 방충재 현황
 * @Modification Information
 *
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2019.06.19  	 김재철          화면단 최초 생성
 *
 * author 김재철
 * since 2016.06.19
 *
**/
%>
<%
/******************************** SCRIPT START ********************************/
%>

<script>

<%
/**
 * @FUNCTION NAME : GamFenderDurabilityModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFenderDurabilityModule() {}

GamFenderDurabilityModule.prototype = new EmdModule(970, 550);

GamFenderDurabilityModule.prototype.loadComplete = function(params) {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fclty/gamFenderDurabilityList.do',
		dataType : "json",
		colModel : [
					{display:"순번",				name:"rn",					width:50,		sortable:true,	align:"center"},
					{display:"시설명",			name:"fcltsMngGroupNm",		width:150,		sortable:true,	align:"center"},
					{display:"방충재 명",			name:"prtFcltyNm",			width:200,		sortable:true,	align:"center"},
					{display:"규격",				name:"prtFcltyStndrd",		width:150,		sortable:true,	align:"center"},
					{display:"시행주체",			name:"owner",				width:150,		sortable:true,	align:"center"},
					{display:"시공자",			name:"cnstrtr",				width:120,		sortable:true,	align:"center"},
					{display:"내구연한",			name:"year",				width:100,		sortable:true,	align:"center"}
					],
		showTableToggleBtn : false,
		height : 'auto'
	});


	if (params != null) {
		this.$('#sPrtFcltyNm').val(params.prtFcltyNm);
	}

	this.loadData();

}

<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamFenderDurabilityModule.prototype.onSubmit = function() {
	this.$("#sFcltsMngGroupNo").val('');
	this.loadData();
}

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFenderDurabilityModule.prototype.loadData = function() {

	this.$("#mainTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();

}



<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamFenderDurabilityModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnExcelDownload':
			this.downloadExcel(buttonId);
			break;
	}
}

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamFenderDurabilityModule.prototype.downloadExcel = function(buttonId) {
	console.log("downloadExcel");

	var gridRowCount = 0;

	gridRowCount = this.$("#mainGrid").flexRowCount();

	if (gridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}

	var clone =	this.$('#mainGrid').clone();
	$(clone).find('th,td').each(function() {
		if($(this).css('display')=='none') {
			$(this).remove();
		}
		else {
			$(this).css('border-left', '0.1pt solid black');
			$(this).css('border-top', '0.1pt solid black');
			$(this).css('border-right', '0.1pt solid black');
			$(this).css('border-bottom', '0.1pt solid black');
		}
	});
	clone.find("tr:eq(0)").remove();
	clone.find("tr:eq(1)").remove();
	clone.find("tr:eq(0) td").css({"font-size":"15px","font-weight":"bold","background-color":"#BDBDBD","height":"35px"});
	clone.table2excel({
		filename: "방충재 내구연한 도래",
	});
};


var module_instance = new GamFenderDurabilityModule();

</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<!-- 11. SEARCH AREA (조회조건 영역) -->
	<div id="searchViewStack" class="emdPanel">
		<div class="viewPanel">
			<form id="searchForm">
				<input id="sFcltsMngGroupNo" data-column-id="sFcltsMngGroupNo" type="hidden" />
				<table style="width:100%;" class="searchPanel">
					<tbody>
						<tr>
							<th style="width:50px;">시설물　관리　그룹　명</th>
							<td>
								<input id="sFcltsMngGroupNm" data-column-id="sFcltsMngGroupNm" type="text" size="30" maxlength="30"/>
							</td>
							<th style="width:50px;">방충재명</th>
							<td>
								<input id="sPrtFcltyNm" data-column-id="sPrtFcltyNm" type="text" size="30" maxlength="30"/>
							</td>
							<td>
								<button class="buttonSearch">조회</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<!-- 2. DATA AREA (자료 영역) -->
	<div class="emdPanel fillHeight">
		<!-- 21. TAB AREA (탭 영역) -->
		<div id="mainTab" class="emdTabPanel fillHeight" >
			<!-- 211. TAB 정의 -->
			<ul>
				<li><a href="#listTab" class="emdTab">방중재 내구연한 목록</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
			</div>
			<div style="vertical-align: bottom; text-align: right;">
				<button id="btnExcelDownload" class="buttonExcel">엑셀 다운로드</button>
				<!-- data-search-option="detailForm" 의 경우 detailForm 폼안의  data-column-id에 정의된 이름으로 파라메터를 생성함.  -->
			</div>

		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
