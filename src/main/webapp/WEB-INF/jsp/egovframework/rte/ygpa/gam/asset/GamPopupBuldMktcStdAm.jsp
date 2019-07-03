<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupBuldMktcStdAm.jsp
  * @Description : 건축물 시가표준액 팝업
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
function GamPopupBuldMktcStdAmModule() {}

GamPopupBuldMktcStdAmModule.prototype = new EmdPopupModule(275, 300);

// 페이지가 호출 되었을때 호출 되는 함수
GamPopupBuldMktcStdAmModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#buldMktcStdAmList").flexigrid({
		module: this,
		url: '/asset/selectPopupBuldMktcStdAm.do',
		dataType: 'json',
		colModel : [
			{display:'연도', name:'stdyy',		width:60, sortable:true, align:'center'},
			{display:'순번', name:'sn',			width:60, sortable:true, align:'center'},
			{display:'금액', name:'mktcStdAm',	width:100, sortable:true, align:'center', displayFormat:'number'}
			],
		height: '200',
	});

	this.$("#buldMktcStdAmList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module.onButtonClick('btnOk');
	});
	
	this.loadData();
};

GamPopupBuldMktcStdAmModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'selectBupJungDongCodeList':
		this.loadData();
		break;
	case "btnOk":
		var row = this.$("#buldMktcStdAmList").selectedRows();
		if(row.length>0) {
			this.closeDialog("ok", row[0]);
		}
		else {
			alert("금액을 선택 하십시요.");
		}
		break;
	case "cancel":
		this.cancelDialog();
	}
};

GamPopupBuldMktcStdAmModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupBuldMktcStdAmModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#searchBuldMktcStdAm');
 	this.$('#buldMktcStdAmList').flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupBuldMktcStdAmModule();
</script>
<!-- 아래는 고정 -->
<div class="dialog fillHeight">
	<div class="emdPanel">
		<form id="searchBuldMktcStdAm">
			<input id="sAdstrdCode" type="hidden" value="<c:out value='${searchOpt.sAdstrdCode}' />" />
			<input id="sLnm" type="hidden" value="<c:out value='${searchOpt.sLnm}' />" />
			<input id="sSlno" type="hidden" value="<c:out value='${searchOpt.sSlno}' />" />
<!--  			<table style="width:100%;" class="searchPanel">
				<tbody>
					<tr>
						<td><button id="selectBupJungDongCodeList" class="submit">조회</button></td>
					</tr>
				</tbody>
			</table>
 -->			
		</form>
	</div>
	<div class="emdPanel fillHeight">
		<table id="buldMktcStdAmList" style="display:none" class="fillHeight"></table>
		<div class="emdControlPanel">
			<button id="btnOk">선택</button>
			<button id="cancel">취소</button>
		</div>
	</div>
</div>
