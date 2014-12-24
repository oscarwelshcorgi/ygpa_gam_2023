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
				{display:"항구분",				name:"gisAssetsPrtAtName",		width:60,		sortable:false,		align:"center"},
				{display:"자산명",		 	name:"gisAssetsNm",		width:180,		sortable:false,		align:"left"},
				{display:"소재지", 			name:"gisAssetsLnms",		width:200,		sortable:false,		align:"left"},
				{display:"사용면적", 				name:"usageAr",				width:100, 		sortable:false,		align:"right" },
				{display:"자산면적",				name:"gisAssetsAr",			width:100,		sortable:false,		align:"right"},
				{display:"실제임대면적",			name:"gisAssetsRealRentAr",				width:100, 		sortable:false,		align:"right"},
				{display:"기간From",			name:"usagePdFrom",				width:80, 		sortable:false,		align:"center"},
				{display:"기간To",			name:"usagePdTo",				width:80, 		sortable:false,		align:"center"  }
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
		case "btnSearch": //조회
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
							<th>사용 기간</th>
                        	 <td>
                            	<input id="searchDtFr" type="text" class="emdcal" size="8"> ~
                            	<input id="searchDtTo" type="text" class="emdcal" size="8">
                             </td>
                              <td colspan="2"><button id="btnSearch" class="buttonSearch">조회</button></td>
							</tr>
							<tr>
							<th>시설명</th>
							<td><input id="searchFcltyNm" type="text" size=30/></td>
							<th>소재지</th>
							<td><input id="searchLoc" type="text" size=30/></td>
							
                             
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