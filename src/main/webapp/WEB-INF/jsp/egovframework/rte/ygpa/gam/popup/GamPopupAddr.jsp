<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamBupJungDongMngt.jsp
  * @Description : 법정동 코드조회 팝업
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
function GamBupJungDongModule() {}

GamBupJungDongModule.prototype = new EmdPopupModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamBupJungDongModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#addrList").flexigrid({
		module: this,
		url: '/code/selectBupJungDongCodeList.do',
		dataType: 'json',
		colModel : [
			{display:'선택', name:'regYn', width:50, sortable:true, align:'left', displayFormat: 'checkbox'},
			{display:'법정동 코드', name:'bupjungdongCd', width:200, sortable:true, align:'center'},
			{display:'법정동 명', name:'bupjungdongNm', width:480, sortable:true, align:'center'}
			],
		height: '420',
	});

	this.$("#addrList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module.onButtonClick('btnOk');
	});
};

GamBupJungDongModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'selectBupJungDongCodeList':
		this.loadData();
		break;
	case "btnOk":
		var row = this.$("#addrList").selectedRows();
		if(row.length>0) {
			row[0].detailAddr=this.$('#detailAddr').val();
			this.closeDialog("ok", row[0]);
		}
		else {
			alert("주소를 선택 하십시요.");
		}
		break;
	case "cancel":
		this.cancelDialog();
	}
};

GamBupJungDongModule.prototype.onSubmit = function() {
	this.loadData();
};

GamBupJungDongModule.prototype.loadData = function() {
	if(this.$('#searchBupjungdongNm').val().length<2 && this.$('#searchBupjungdongCd').val().length<4) {
		alert('법정동 코드를 4자리 이상 입력 하거나 검색할 주소를 두자 이상 입력 하세요');
		this.$('#searchBupjungdongNm').addClass('ui-state-error');
		this.$('#searchBupjungdongCd').addClass('ui-state-error');
		return;
	}
	else {
		this.$('#searchBupjungdongNm').removeClass('ui-state-error');
		this.$('#searchBupjungdongCd').removeClass('ui-state-error');
	}
	var searchOpt=this.makeFormArgs('#searchBupjungDong');
 	this.$('#addrList').flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamBupJungDongModule();
</script>
<!-- 아래는 고정 -->
<div class="dialog fillHeight">
	<div class="emdPanel">
		<form id="searchBupjungDong">
			<table style="width:100%;" class="searchPanel">
				<tbody>
					<tr>
						<th>검색 주소</th>
						<td>
							<input id="searchBupjungdongNm" type="text" size="20" />
						</td>
						<th>법정동 코드</th>
						<td>
							<input id="searchBupjungdongCd" type="text" size="20" />
						</td>
						<td><button id="selectBupJungDongCodeList" class="submit">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="emdPanel fillHeight">
		<table id="addrList" style="display:none" class="fillHeight"></table>
		<div class="emdControlPanel">
				<table class="editForm" style="display: inline; float:left;">
					<tr>
					<th>나머지 주소 </th>
					<td>
					<input id="detailAddr" size="20" />
					</td></tr>
				</table>
				<button id="btnOk">주소입력</button>
				<button id="cancel">취소</button>
		</div>
	</div>
</div>
