<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name :	GamFcltyUseUnuseSttusInqire.jsp
  * @Description : 시설물 사용/미사용시설 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.11.  정성현          최초 생성
  *
  * author 정성현
  * since 2014.12.11.
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyUseUnuseSttusInqireModule() {
}

GamFcltyUseUnuseSttusInqireModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyUseUnuseSttusInqireModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#fcltyUseUnuseSttusInqireList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyUseUnuseSttusInqireList.do',
		dataType: "json",
		colModel : [
				{display:"항코드",				name:"gisAssetsPrtAtCode",		width:60,		sortable:false,		align:"center"},
				{display:"시설구분",		 	name:"prtFcltySeNm",		width:100,		sortable:false,		align:"left"},
				{display:"시설코드", 			name:"gisPrtFcltyDisplayCd",		width:80,		sortable:false,		align:"center"},
				{display:"시설명",				name:"prtFcltyNm",			width:230,		sortable:false,		align:"left"},
				{display:"사용기간From",			name:"usagePdFrom",				width:80, 		sortable:false,		align:"center"},
				{display:"사용기간To",			name:"usagePdTo",				width:80, 		sortable:false,		align:"center"},
				{display:"업체코드",			name:"entrpsCd",				width:60, 		sortable:false,		align:"center"  },
				{display:"업체명", 				name:"entrpsNm",				width:100, 		sortable:false,		align:"right" },
				{display:"사용료", 				name:"fee",						width:240, 		sortable:false,		align:"center",  displayFormat: 'number'}
			],
		height: "auto"
	});
	};


GamFcltyUseUnuseSttusInqireModule.prototype.onSubmit = function() {
	this.loadData();
	};

	//시설목록 로드
GamFcltyUseUnuseSttusInqireModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchFcltyUseUnuseSttusInqireForm");
	this.$("#fcltyUseUnuseSttusInqireList").flexOptions({params:searchOpt}).flexReload();
	};




	/**
	 * 정의 된 버튼 클릭 시
	 */
GamFcltyUseUnuseSttusInqireModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
		case "searchBtn": //조회
// 			this._cmd = "";
// 			this.initDisplay();
			this.loadData();
			break;
	}
};

	// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyUseUnuseSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyUseUnuseSttusInqireForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항코드</th>
							<td><input id="searchPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>시설구분</th>
							<td><input id="searchFcltyCdSub" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM044" /></td>
							   <td rowSpan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
							</tr>
							<tr>
							  <th>기간</th>
                        	 <td>
                            	<input id="sSearchDtFr" type="text" class="emdcal" size="8"> ~
                            	<input id="sSearchDtTo" type="text" class="emdcal" size="8">
                             </td>
					</tr>
					</tbody>
				</table>

			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltyUseUnuseSttusInqireTab" class="emdTabPanel fillHeight">
			<ul>
				<li><a href="#tabs1" class="emdTab">시설물 사용/미사용시설 조회</a></li>

			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyUseUnuseSttusInqireList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">

					<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="fcltyUseUnuseSttusInqireList" data-style="default">맵조회</button>
				</div>
			</div>


		</div>
	</div>
</div>