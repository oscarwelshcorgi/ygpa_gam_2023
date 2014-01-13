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
		module: this,
		url: '<c:url value="/cmmn/gamProgramListManageSelect.do" />',
		dataType: 'json',
		colModel : [
					{display:'수정일자',			name:'updtDt',			width:96,		sortable:false,		align:'center'},
					{display:'프로그램파일명', 	name:'progrmFileNm',	width:80, 		sortable:false,		align:'left'},
					{display:'프로그램명', 		name:'progrmKoreanNm',	width:100, 		sortable:false,		align:'center'},
					{display:'프로그램경로', 	name:'progrmStrePath',	width:60, 		sortable:false,		align:'left'},
					{display:'URL', 			name:'url',				width:200, 		sortable:false,		align:'left'},
					{display:'프로그램설명',		name:'progrmDc',		width:160,		sortable:false,		align:'center'}
					],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: '300'
	});

	this.$("#progListMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#programListTab").tabs("option", {active: 1});	// 탭을 전환 한다.

		if(row!=null) {
			module.$('#cmd').val('modify');	 // 더블클릭한 아이템을 수정한다
			module.$('#progrmFileNm').val(row['progrmFileNm']);
			module.$('#progrmStrePath').val(row['progrmStrePath']);
			module.$('#progrmKoreanNm').val(row['progrmKoreanNm']);
			module.$('#URL').val(row['url']);
			module.$('#progrmDc').val(row['progrmDc']);
			throw 0;
		}
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
		case 'addItem':
			this.$("#programListTab").tabs("option", {active: 1});	// 탭을 전환 한다.
			break;

		case 'removeItem':
			throw 0;
			var rows = this.$('#progListMngList').selectedRows();
			if(rows.length>=1) {
				this.doAction('<c:url value="/cmmn/gamProgramListManageDelete.do" />', rows[0], function(result) {
			 		if(result.resultCode=='0') {
						var searchOpt=this.makeFormArgs('#progListMngForm');
					 	this.$('#progListMngList').flexOptions({params:searchOpt}).flexReload();
			 			alert('삭제되었습니다.');
			 		}
			 		else {
			 			alert(result.resultMsg);
			 		}
				});
			}
			break;
		// 저장
		case 'btnSaveItem':
			var inputVO=this.makeFormArgs('#progrmManageVO');
		 	this.doAction('<c:url value="/cmmn/gamProgramListDetailSelectUpdt.do" />', inputVO, function(result) {
		 		if(result.resultCode=='0') {
		 			alert('저장되었습니다.');
		 		}
		 		else {
		 			alert(result.resultMsg);
		 		}
		 	});
			break;
	}
};

GamProgListMngModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
};

GamProgListMngModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#progListMngForm');
	//this.showAlert(searchOpt);
 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
//	this.$('#assetList').flexOptions(searchOpt).flexReload();
};

GamProgListMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case 'tabs1':
		break;
	case 'tabs2':
		var row = this.$('#progListMngList').selectedRows();
		if(row.length==0) {
			this.$('#cmd').val('insert');
		}
		else {
			this.$('#cmd').val('modify');
			/* this.$('#progrmFileNm').val(row[0]['progrmFileNm']);
			this.$('#progrmStrePath').val(row[0]['progrmStrePath']);
			this.$('#progrmKoreanNm').val(row[0]['progrmKoreanNm']);
			this.$('#url').val(row[0]['url']);
			this.$('#progrmDc').val(row[0]['progrmDc']); */
		}
		break;
	}
};

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
		<div id="programListTab" class="emdTabPanel" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">프로그램목록</a></li>
				<li><a href="#tabs2" class="emdTab">프로그램상세</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="progListMngList" style="display:none"></table>
				<div class="emdControlPanel">
					<button id="addItem">추가</button>
					<button id="removeItem">삭제</button>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: scroll;">
				<form id="progrmManageVO">
					<input type="hidden" id="cmd"/>
					<table class="tableForm">
						<tr>
							<th><span class="label">프로그램파일명</span></th>
							<td><input type="text" size="80" id="progrmFileNm"/></td>
						</tr>
						<tr>
							<th><span class="label">한글명</span></th>
							<td><input type="text" size="80" id="progrmKoreanNm"/></td>
						</tr>
						<tr>
							<th><span class="label">프로그램경로</span></th>
							<td><input type="text" size="80" id="progrmStrePath"/></td>
						</tr>
						<tr>
							<th><span class="label">URL</span></th>
							<td><input type="text" size="80" id="URL"/></td>
						</tr>
						<tr>
							<th><span class="label">프로그램설명</span></th>
							<td><textarea cols="80" rows="10" id="progrmDc"></textarea></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel"><button id="btnSaveItem">저장</button><button id="canclProgram">취소</button></div>
			</div>
		</div>
	</div>
</div>