<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupRoadInfo.jsp
  * @Description : 도로명주소 조회 팝업
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.05.13  장은성          최초 생성
  *
  * author 장은성
  * since 2014.05.13
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamRoadInfoModule() {}

GamRoadInfoModule.prototype = new EmdPopupModule(550, 450);

// 페이지가 호출 되었을때 호출 되는 함수
GamRoadInfoModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#roadInfoList").flexigrid({
		module: this,
		url: '/code/selectRoadInfoList.do',
		dataType: 'json',
		colModel : [
			{display:'No', name:'rn', width:50, sortable:true, align:'right', displayFormat: 'number'},
			{display:'도로명 주소', name:'road', width:450, sortable:true, align:'left'}
			],
		height: '350',
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				this.road = this.roadNm+"<br>[지번] "+this.jibunNm;
			});
			return data;
		}
	});

	this.$("#roadInfoList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module.onButtonClick('btnOk');
	});
};

GamRoadInfoModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnSelect':
		this.loadData();
		break;
	case "btnOk":
		var row = this.$("#roadInfoList").selectedRows();
		if(row.length>0) {
			this.closeDialog("ok", row[0]);
		}
		else {
			alert("도로명 주소를 선택해주세요.");
		}
		break;
	case "cancel":
		this.cancelDialog();
	}
};

GamRoadInfoModule.prototype.onSubmit = function() {
	this.loadData();
};

GamRoadInfoModule.prototype.loadData = function() {
	if(this.$('#sRoadNm').val().length<2 ) {
		alert('검색어를 두자 이상 입력 하세요');
		this.$('#sRoadNm').addClass('ui-state-error');
		return;
	}
	else {
		this.$('#sRoadNm').removeClass('ui-state-error');
	}
	var searchOpt=this.makeFormArgs('#searchRoadInfo');
 	this.$('#roadInfoList').flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamRoadInfoModule();
</script>
<!-- 아래는 고정 -->
<div class="dialog fillHeight">
	<div class="emdPanel">
		<form id="searchRoadInfo">
			<table style="width:100%;" class="searchPanel">
				<tbody>
					<tr>
						<th style="width: 35px;">검색어</th>
						<td ><input id="sRoadNm" type="text" size="50" /></td>
						<td ><input id="sMainNo" type="text" size="5" /></td>
						<td ><input id="sSubNo" type="text" size="5" /></td>
						<td style="text-align: right;"><button id="btnSelect" class="submit">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="emdPanel fillHeight">
		<table id="roadInfoList" style="display:none" class="fillHeight"></table>
	</div>
</div>
