<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamProgListMng.jsp
  * @Description : 프로그램 목록 관리
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.01.09  권옥경          최초 생성
  *
  * author 권옥경
  * since 2014.01.09
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamProgListMngModule() {}

GamProgListMngModule.prototype = new EmdModule();

// 페이지가 호출 되었을때 호출 되는 함수
GamProgListMngModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#progListMngList").flexigrid({
		url: '<c:url value="/cmmn/gamProgramListManageSelect.do" />',
		dataType: 'json',
		colModel : [
					{display:'수정일자',			name:'UPDT_DT',			width:96,		sortable:false,		align:'center'},
					{display:'프로그램파일명', 	name:'progrmFileNm',	width:60, 		sortable:false,		align:'left'},
					{display:'프로그램명', 		name:'progrmKoreanNm',	width:100, 		sortable:false,		align:'center'},
					{display:'URL', 			name:'url',				width:200, 		sortable:false,		align:'left'},
					{display:'프로그램설명',		name:'progrmDc',		width:160,		sortable:false,		align:'center'}
					],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: '300'
	});
};
		
/**
 * 정의 된 버튼 클릭 시
 */
 GamProgListMngModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case 'searchBtn':
			var searchOpt=this.makeFormArgs('#progListMngForm');
		 	this.$('#progListMngList').flexOptions({params:searchOpt}).flexReload(); 
			break;
		
		// 신규저장
		case 'insertBtn':
			var searchOpt=this.makeFormArgs('#searchGisAssetCode');
		 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
			break;
		
		// 삭제
		case 'deleteBtn':
			var searchOpt=this.makeFormArgs('#searchGisAssetCode');
		 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
			break;
	}
};

GamProgListMngModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
}

GamProgListMngModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#progListMngForm');
	//this.showAlert(searchOpt);
 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
//	this.$('#assetList').flexOptions(searchOpt).flexReload();
}

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamProgListMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<div class="emdPanel">
		<div class="viewStack">
			<form id="progListMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>프로그램 명</th>
							<td><input id="searchKeyword" name="searchKeyword" type="text" size="60"></td>
						</tr>
					</tbody>
				</table>
				<div class="emdTabPage">
					<div class="emdControlPanel">
						<button id="searchBtn">조회</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="emdPanel">
		<div class="emdTabPanel">
			<div class="emdTabPage">
				<table id="progListMngList" style="display:none"></table>
			</div>
		</div>
	</div>
</div>