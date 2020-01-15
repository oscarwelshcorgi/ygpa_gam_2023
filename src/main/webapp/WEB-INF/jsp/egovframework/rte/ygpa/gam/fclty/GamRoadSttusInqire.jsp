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
 * @FUNCTION NAME : GamFenderSttusInqireModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFenderSttusInqireModule() {}

GamFenderSttusInqireModule.prototype = new EmdModule(960, 550);

GamFenderSttusInqireModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fclty/gamFenderMngGroupList.do',
		dataType : "json",
		colModel : [
					{display:"항구분",				name:"prtAtCodeNm",				width:60,		sortable:true,	align:"center"},
					{display:"관리 그룹 명",			name:"fcltsMngGroupNm",			width:150,		sortable:true,	align:"left"},
					{display:"종별",					name:"fcltsGbnNm",				width:80,		sortable:true,	align:"center"},
					{display:"구분",					name:"fcltsSeNm",				width:80,		sortable:true,	align:"center"},
					{display:"종류",					name:"fcltsKndNm",				width:80,		sortable:true,	align:"center"},
					{display:"공사 일자",				name:"cnstDt",					width:80,		sortable:true,	align:"center"},
					{display:"준공 일자",				name:"bldDt",					width:80,		sortable:true,	align:"center"},
					{display:"운영사",					name:"owner",					width:110,		sortable:true,	align:"left"},
					{display:"위치",					name:"loc",						width:200,		sortable:true,	align:"left"}
					],
		showTableToggleBtn : false,
		height : 'auto'
	});

/* 	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

 */
 	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainKeyValue = row.fcltsMngGroupNo;
    });

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainKeyValue = row.fcltsMngGroupNo;
		module.$("#mainTab").tabs("option", {active: 1});
	});


	this.$("#fenderSttusInqireList").flexigrid({
		module: this,
		url: '/fclty/gamFenderSttusInqireList.do',
		dataType: "json",
		colModel : [
			{display:"순번",				name:"rnum",					width:40,		sortable:false,		align:"right"},
			{display:"항만시설 명",			name:"prtFcltyNm",				width:200,		sortable:false,		align:"left"},
			{display:"규격",				name:"prtFcltyStndrd",			width:150,		sortable:false,		align:"left"},
			//{display:"단위",	 			name:"prtFcltyUnit",			width:40,		sortable:false,		align:"left"},
			{display:"수량",	 			name:"prtPrtFcltyCnt",			width:40,		sortable:false,		align:"right",	displayFormat: 'number'},
			{display:"구조 형식",	 		name:"strctFmt",				width:150,		sortable:false,		align:"left"},
			{display:"선석",				name:"berth",					width:40,		sortable:false,		align:"left"},
			//{display:"방충재 종류",		 	name:"fenderKndCd",				width:100,		sortable:false,		align:"left"},
			//{display:"방충재 배치 간격",		name:"fenderPmntItv",			width:120,		sortable:false,		align:"left"},
			{display:"방충재 형식",		 	name:"fenderFmt",				width:150,		sortable:false,		align:"left"}
			],
		height: "360",
		preProcess: function(module, data) {
			var _qy1 = 0;
			var _qy2 = 0;
			var _qy3 = 0;

			var _stndrd1='';
			var _stndrd2='';
			var _stndrd3='';

			$.each(data.resultList, function() {
				if(_stndrd1 == '' || _stndrd1 == this.prtFcltyStndrd){
					_stndrd1 = this.prtFcltyStndrd;
					_qy1 += Number(this.prtPrtFcltyCnt);
				}
				else if(_stndrd2 == '' || _stndrd2 ==this.prtFcltyStndrd){
					_stndrd2 = this.prtFcltyStndrd;
					_qy2 += Number(this.prtPrtFcltyCnt);
				}
				else if(_stndrd3 == '' || _stndrd3 ==this.prtFcltyStndrd){
					_stndrd3 = this.prtFcltyStndrd;
					_qy3 += Number(this.prtPrtFcltyCnt);
				}

			});

			module.$("#qy").val(_qy1);
			module.$("#stndrd").val(_stndrd1);
			if(_stndrd3 ==''){
				module.$("#etcQy").val(_qy2);
				module.$("#etcStndrd").val(_stndrd2);
			}
			else{
				module.$("#etcQy").val(String(_qy2)+', '+String(_qy3));
				module.$("#etcStndrd").val(_stndrd2+', '+_stndrd3);

			}
			return data;
		}
	});


	this.$("#fcltsGbn").disable();

}

GamFenderSttusInqireModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'fenderSttusInqirePrint':
			var param = {sFcltsMngGroupNo:this._mainKeyValue};
			this.printPayNotice('/fclty/fenderSttusInqirePrint.do', param);
			break;
	}
}

<%
/**
 * @FUNCTION NAME : onTabChange
 * @DESCRIPTION   : 탭이 변경 될때 호출된다. (태그로 정의 되어 있음)
 * @PARAMETER     :
 *   1. newTabId - NEW TAB ID
 *   2. oldTabId - OLD TAB ID
**/
%>
GamFenderSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			this.loadDetail(oldTabId);
			break;
	}

}

<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamFenderSttusInqireModule.prototype.onSubmit = function() {
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
GamFenderSttusInqireModule.prototype.loadData = function() {

	this.$("#mainTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();

}

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFenderSttusInqireModule.prototype.loadDetail = function(tabId) {

	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);
//		this.makeDivValues('#detailForm', row[0]);


		this.$('#sFcltsMngGroupNo').val(this._mainKeyValue);
		var searchOpt=this.makeFormArgs('#searchForm');
//		searchOpt[searchOpt.length] = {name : "sFcltsMngGroupNo",	value : this._mainKeyValue };
		console.log(this._mainKeyValue);
//		var searchOpt = {name:"fcltsMngGroupNo", value : this._mainKeyValue};
		this.$('#fenderSttusInqireList').flexOptions({params:searchOpt}).flexReload();
	}

}

var module_instance = new GamFenderSttusInqireModule();

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
							<th>시설물 종류</th>
							<td>
								<select id="sFcltsGbn" data-column-id="sFcltsGbn">
										<option value="" selected>선택</option>
										<option value="1">1종</option>
										<option value="2">2종</option>
										<option value="3">1종/2종</option>
										<option value="9">기타</option>
									</select>
							</td>
							<th>시설물　관리　그룹　명</th>
							<td>
								<input id="sFcltsMngGroupNm" data-column-id="sFcltsMngGroupNm" type="text" size="30" maxlength="80"/>
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
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<!-- 211. TAB 정의 -->
			<ul>
				<li><a href="#listTab" class="emdTab">방중재 현황 목록</a></li>
				<li><a href="#detailTab" class="emdTab">방충재 현황 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
			</div>
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<input type="hidden" id="fcltsMngGroupNo" data-column-id="fcltsMngGroupNo"/>
						<table class="detailPanel"  style="width:100%;">
							<tr>
								<td colspan="2" style="width:40%; vertical-align: top;">
									<table class="detailPanel" style="width:100%;">
										<tr>
											<th style="width:20%; height:18px;">시설물　관리 그룹　명</th>
											<td  >
												<input type="text" id="fcltsMngGroupNm" data-column-id="fcltsMngGroupNm" size="35" disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;">위　　　　　　　　치</th>
											<td  >
												<input type="text" id="loc" data-column-id="loc" size="35" disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;">시　설　물　　종　별</th>
											<td style="width:20%; height:18px;" >
												<select id="fcltsGbn" data-column-id="fcltsGbn" >
													<option value="" selected>선택</option>
													<option value="1">1종</option>
													<option value="2">2종</option>
													<option value="3">1종/2종</option>
													<option value="9">기타</option>
												</select>
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;" >공　　사　　기　　간</th>
											<td>
												<input type="text" id="cnstDt" data-column-id="cnstDt" size="25" class="emdcal"  disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;">준　　공　　일　　자</th>
											<td>
												<input type="text" id="bldDt" data-column-id="bldDt" size="18" class="emdcal"  disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;">운　　　　영　　　사</th>
											<td  >
												<input type="text" id="owner" data-column-id="owner" size="35" disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;">시　　　　공　　　자</th>
											<td >
												<input type="text" id="cnstrtr" data-column-id="cnstrtr" size="35" disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;">규　　　　  　　　 격</th>
											<td >
												<input type="text" id="stndrd" data-column-id="stndrd" size="35" disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;">수　　　　  　　　 량</th>
											<td >
												<input type="text" id="qy" data-column-id="qy" class="ygpaNumber" style="text-align: right;" size="10" disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;">기　　타　　규　　격</th>
											<td >
												<input type="text" id="etcStndrd" data-column-id="etcStndrd"  size="35" disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;">기　　타　　수　　량</th>
											<td >
												<input type="text" id="etcQy" data-column-id="etcQy" class="ygpaNumber" style="text-align: right;" size="10" disabled="disabled" />
											</td>
										</tr>
									</table>
								</td>
								<td style="border-bottom:none;">
									<table id="fenderSttusInqireList" style="display:none"></table>
								</td>
							</tr>
						</table>
						<div style="vertical-align: bottom; text-align: right;">
							<button data-role="printPage" data-search-option="detailForm" data-url="/fclty/fenderSttusInqirePrint.do">인쇄</button>
							<button data-role="gridXlsDown" data-flexi-grid="fenderSttusInqireList" data-xls-name="방충재 리스트.xls" data-xls-title="방충재 리스트">엑셀</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
