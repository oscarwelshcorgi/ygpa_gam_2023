<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupSearchZipView.jsp
  * @Description : 우편번호 검색
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.28  kok          최초 생성
  *
  * author kok
  * since 2013.01.28
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 * 공통 팝업이 아닌 팝업은 이 페이지와 같이 해당 패키지의 하단에 생성 할 것
 */
function GamSearchZipPopupModule() {}

/* 팝업모듈 초기화 */
GamSearchZipPopupModule.prototype = new EmdPopupModule(640, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamSearchZipPopupModule.prototype.loadComplete = function() {

	this.resizable(true);
	
	// 테이블 설정
	this.$("#searchZipPopupList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/popup/gamCcmZipSearchList.do" />',
		dataType: "json",
		colModel : [
					{display:"순번",			name:"rnum",		width:40, 	sortable:false,		align:"center"},
					{display:"우편번호",		name:"zip",			width:80, 	sortable:false,		align:"center"},
					{display:"주소",			name:"address",		width:240, 	sortable:false,		align:"center"},
					{display:"번지주소",		name:"lnbrDongHo",	width:140, 	sortable:false,		align:"center"}
					/*{display:"시",	 		name:"ctprvnNm",	width:300, 	sortable:false,		align:"center"},
					{display:"군구", 		name:"signguNm",	width:300, 	sortable:false,		align:"center"},
					{display:"동읍면", 		name:"emdNm",		width:300, 	sortable:false,		align:"center"},
					{display:"주소", 		name:"liBuldNm",	width:300, 	sortable:false,		align:"center"},
					{display:"주소", 		name:"lnbrDongHo",	width:300, 	sortable:false,		align:"center"}*/
					],
		usepager: true,
		useRp: true,
		//rp: 24,
		showTableToggleBtn: false,
		height: "315"
	});

	this.$("#searchZipPopupList").on('onItemDoubleClick', function(event, module, row, grid, param) {

		module.closeDialog('ok', row);
	});
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamSearchZipPopupModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			if(this.$("#searchKeyword").val() == ""){
			/*	alert("검색값을 입력하세요.");
				this.$("#searchKeyword").focus();
				return;
				*/
			}
			var searchOpt = this.makeFormArgs("#searchZipPopupForm");
		 	this.$("#searchZipPopupList").flexOptions({params:searchOpt}).flexReload();
		 	throw 0;
		break;

	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamSearchZipPopupModule();

function selectDisplay(){

	// 초기화
	this.$("#searchCondition").hide();
	this.$("#searchCondition2").hide();
	
	if(this.$("#searchList").val() == "1") this.$("#searchCondition").show();
	else this.$("#searchCondition2").show();
}
</script>
<div class="dialog">
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchZipPopupForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<td>
								<select id="searchList" class="select" title="searchList" onchange="selectDisplay();">
									<option value="1">주소</option>
									<option value="2">도로명주소</option>
								</select>
								<select id="searchCondition" class="select" title="searchCondition">
								   <option value="1">우편번호</option>
								   <option value="2">시도명</option>
								   <option value="3">시군구명</option>
								   <option value="4">읍면동명</option>
								   <option value="5">리건물명</option>
								</select>
							   	<select id="searchCondition2" class="select" title="searchCondition" style="display:none">
								   <option value="1">우편번호</option>
								   <option value="2">시도명</option>
								   <option value="3">시군구명</option>
								   <option value="4">도로명</option>
								   <option value="5">건물명</option>
								   <option value="6">상세건물명</option>
								</select>
						    	<input id="searchKeyword" type="text" size="20" maxlength="20" title="입력창">
							</td>
						</tr>
					</tbody>
				</table>
			</form>
			<div class="emdControlPanel">
				<button id="searchBtn">조회</button>
			</div>
		</div>
	</div>
	
	<div class="emdPanel">
		<div style="width: 100%; height: 100%; overflow: auto">
			<table id="searchZipPopupList" style="display:none"></table>
		</div>
	</div>
</div>