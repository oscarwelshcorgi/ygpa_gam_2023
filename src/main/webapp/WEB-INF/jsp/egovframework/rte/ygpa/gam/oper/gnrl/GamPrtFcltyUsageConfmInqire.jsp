<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPrtFcltyUsageConfmInqireModule() {}

GamPrtFcltyUsageConfmInqireModule.prototype = new EmdModule(1600, 800);

//페이지가 호출 되었을때 호출 되는 함수
GamPrtFcltyUsageConfmInqireModule.prototype.loadComplete = function() {

	// 테이블 설정 //
	 this.$("#mainGrid").flexigrid({
	     module: this,
	     url: '/oper/gnrl/selectPrtFcltyUageConfmInqireList.do',
	     dataType: 'json',
	     colModel : [
	                 {display:'번호',						name:'',width:40, sortable:false,align:'center'},
	                 {display:'업체명',						name:'',width:150, sortable:false,align:'center'},
	                 {display:'사업자등록번호',				name:'',width:120, sortable:false,align:'center'},
	                 {display:'대표자',						name:'',width:80, sortable:false,align:'center'},
	                 {display:'위치',						name:'',width:200, sortable:false,align:'center'},
	                 {display:'사용목적',					name:'',width:200, sortable:false,align:'center'},
	                 {display:'종목',						name:'',width:40, sortable:false,align:'center'},
	                 {display:'사용구분',					name:'',width:60, sortable:false,align:'center'},
	                 {display:'면적',						name:'',width:100, sortable:false,align:'center'},
	                 {display:'사용료(원)/ 공급가액 기준',	name:'',width:200, sortable:false,align:'center'},
	                 {display:'최초사용',					name:'',width:80, sortable:false,align:'center'},
	                 {display:'시작',						name:'',width:80, sortable:false,align:'center'},
	                 {display:'종료',						name:'',width:80, sortable:false,align:'center'},
	                 {display:'일수',						name:'',width:60, sortable:false,align:'center'},
	                 {display:'담당자',						name:'',width:80, sortable:false,align:'center'}
	                 ],
	     showTableToggleBtn: false,
	     height: 'auto',
	     preProcess: function(module,data) {
	         return data;
	     }

	 });

	 this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
			module.selectData();
		});

	console.log('debug');
};

var module_instance = new GamPrtFcltyUsageConfmInqireModule();
</script>


<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<!-- 11. SEARCH AREA (조회조건 영역) -->
	<div id="searchViewStack" class="emdPanel">
		<div class="viewPanel">
			<form id="searchForm">
				<table style="width:100%;" class="searchPanel">
					<tbody>
						<tr>
							<th>항구분</th>
							<td>
								<input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019"/>
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
			<!-- 211. TAB AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:8%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="8" id="totalCount" class="ygpaNumber" disabled="disabled"/></td>
								<td style="text-align:right;">
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀 다운로드</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>