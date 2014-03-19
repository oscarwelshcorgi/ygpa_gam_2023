<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : EapGwCallInterfaceMngt.jsp
  * @Description : 전자결재 테스트
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.03.17  장은성          최초 생성
  *
  * author 장은성
  * since 2014.03.17
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function EapGwCallInterfaceModule() {}

EapGwCallInterfaceModule.prototype = new EmdModule(730, 600);

// 페이지가 호출 되었을때 호출 되는 함수
EapGwCallInterfaceModule.prototype.loadComplete = function() {
	// 테이블 설정
	this.$("#eapGwCallInterfaceList").flexigrid({
		module: this,
		url: '<c:url value="/eap/selectEapGwCallInterfaceList.do"/>',
		dataType: 'json',
		colModel : [
			{display:'결재번호', name:'tNo', width:100, sortable:true, align:'center'},
			{display:'사원코드', name:'empCd', width:80, sortable:true, align:'center'},
			{display:'시스템', name:'sysId', width:60, sortable:true, align:'center'},
			{display:'문서아이디', name:'docId', width:75, sortable:true, align:'center'},
			{display:'연동키값', name:'miskey', width:175, sortable:true, align:'center'},
			{display:'결재요청자IP', name:'ip', width:100, sortable:true, align:'center'},
			{display:'결재상태', name:'testEa', width:70, sortable:true, align:'center'}
			],
		height: 'auto'
	});

	this.$("#eapGwCallInterfaceList").on('onItemSelected', function(event, module, row, grid, param) {
		//alert('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is selected');
	});
};

// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
EapGwCallInterfaceModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'selectGwCallInterfaceList':
		this.onSubmit();
		break;
	case 'btnActionTestEApproval':	// 결재 테스트
		var row = this.$('#eapGwCallInterfaceList').selectedRows();
		if(row.length>0) {
			row=row[0];
			this.doAction('<c:url value="/eap/updateGwCallInterface.do"/>', [{name: 'tNo', value: row['tNo']}, {name: 'miskey', value: row['miskey']}, {name: 'testEa', value: '1'}, {name: 'empCd', value: row['empCd']}], function(module) {
				alert('결재 되었습니다.');
				module.submit();
			});	// 결재 액션 실행
		}
		break;
	case 'btnActionTestEApprovalGoOn':	// 상신 테스트
		var row = this.$('#eapGwCallInterfaceList').selectedRows();
		if(row.length>0) {
			row=row[0];
			this.doAction('<c:url value="/eap/updateGwCallInterface.do"/>', [{name: 'tNo', value: row['tNo']}, {name: 'miskey', value: row['miskey']}, {name: 'testEa', value: '5'}, {name: 'empCd', value: row['empCd']}], function(module) {
				alert('상신 되었습니다.');
				module.submit();
			});	// 결재 액션 실행
		}
		break;
	case 'btnActionTestEApprovalReturn':	// 반송 테스트
		var row = this.$('#eapGwCallInterfaceList').selectedRows();
		if(row.length>0) {
			row=row[0];
			this.doAction('<c:url value="/eap/updateGwCallInterface.do"/>', [{name: 'tNo', value: row['tNo']}, {name: 'miskey', value: row['miskey']}, {name: 'testEa', value: '0'}, {name: 'empCd', value: row['empCd']}], function(module) {
				alert('반송 되었습니다.');
				module.submit();
			});	// 결재 액션 실행
		}
		break;
	case 'btnDeleteTestEApproval':	// 삭제
		var row = this.$('#eapGwCallInterfaceList').selectedRows();
		if(row.length>0) {
			row=row[0];
			this.doAction('<c:url value="/eap/deleteEapGwCallInterface.do"/>', [{name: 'tNo', value: row['tNo']}, {name: 'empCd', value: row['empCd']}], function(module) {
				alert('삭제 되었습니다.');
			 	module.onSubmit();
			});	// 결재 액션 실행
		}
		break;
	}
};

EapGwCallInterfaceModule.prototype.onSubmit = function() {
	/* if(this.$('#testEa').val()=='') {
		alert('결재상태를 선택 하세요');
		this.$('#testEa_select').addClass('ui-state-error');
		return;
	}
	else {
		this.$('#testEa_select').removeClass('ui-state-error');
	} */
	var searchOpt=this.makeFormArgs('#searchGwCallInterfaceForm');
 	this.$('#eapGwCallInterfaceList').flexOptions({params:searchOpt}).flexReload();
 	throw 0;
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new EapGwCallInterfaceModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId }'/>
<div class="window_main">
	<div class="emdPanel">
		<form id="searchGwCallInterfaceForm">
			<table style="width: 100%;" class="searchPanel">
				<tbody>
					<tr>
						<th>등록일시</th>
						<td><input id="searchTNo" type="text" size="20" /></td>
						<th>결재구분</th>
						<td><input id="testEa" type="text" class="ygpaCmmnCd" data-code-id="GAM046" data-default-prompt="선택" /></td>
						<td><button id="selectGwCallInterfaceList" class="submit">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="emdPanel fillHeight">
		<table id="eapGwCallInterfaceList" style="display: none"
			class="fillHeight"></table>
		<div class="emdControlPanel">
			<button id="btnActionTestEApproval">결재</button>
			<button id="btnActionTestEApprovalGoOn">상신</button>
			<button id="btnActionTestEApprovalReturn">반송</button>
			<button id="btnDeleteTestEApproval">삭제</button>
		</div>
	</div>
</div>
