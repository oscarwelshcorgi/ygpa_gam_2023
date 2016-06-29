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
	                 {display:'번호',						name:'rnum',width:40, sortable:false,align:'center'},
	                 {display:'항명',						name:'prtAtCodeNm',width:0, sortable:false,align:'center'},
	                 {display:'업체명',						name:'entrpsNm',width:120, sortable:false,align:'center'},
	                 {display:'사업자등록번호',				name:'bizrno',width:100, sortable:false,align:'center'},
	                 {display:'대표자',						name:'rprsntvNm',width:60, sortable:false,align:'center'},
	                 {display:'위치',						name:'gisAssetsLocplc',width:160, sortable:false,align:'center'},
	                 {display:'사용목적',					name:'usagePurps',width:160, sortable:false,align:'center'},
	                 {display:'종목',						name:'gisAssetsPrprtySeCdNm',width:40, sortable:false,align:'center'},
	                 {display:'사용구분',					name:'reqstSeCdNm',width:60, sortable:false,align:'center'},
	                 {display:'면적',						name:'grAr',width:60, sortable:false,align:'right', displayFormat: 'number'},
	                 {display:'사용료(원)/ 공급가액 기준',	name:'grFee',width:100, sortable:false,align:'right', displayFormat: 'number'},
	                 {display:'4년전',	name:'pass4',width:60, sortable:false,align:'right', displayFormat: 'number'},
	                 {display:'3년전',	name:'pass3',width:60, sortable:false,align:'right', displayFormat: 'number'},
	                 {display:'2년전',	name:'pass2',width:60, sortable:false,align:'right', displayFormat: 'number'},
	                 {display:'1년전',	name:'pass1',width:60, sortable:false,align:'right', displayFormat: 'number'},
	                 {display:'현재년도',	name:'currYearFee',width:60, sortable:false,align:'right', displayFormat: 'number'},
	                 {display:'최초사용',					name:'frstUsagePdFrom',width:80, sortable:false,align:'center'},
	                 {display:'시작',						name:'grUsagePdFrom',width:80, sortable:false,align:'center'},
	                 {display:'종료',						name:'grUsagePdTo',width:80, sortable:false,align:'center'},
	                 {display:'일수',						name:'usageDays',width:60, sortable:false,align:'center'},
	                 {display:'담당자',						name:'prmisnUsrNm',width:80, sortable:false,align:'center'}
	                 ],
	     showTableToggleBtn: false,
        groupBy: "prtAtCodeNm",
	     height: 'auto',
	     preProcess: function(module,data) {
	    	 module.$('#totalCount').val(data.totalCount);
	    	 var cyear = Number(module.$('#referYear').val());
	    	 module.$("#mainGrid")[0].grid.setColumnLabel(15, ""+cyear);
	    	 cyear--;
	    	 module.$("#mainGrid")[0].grid.setColumnLabel(14, ""+cyear);
	    	 cyear--;
	    	 module.$("#mainGrid")[0].grid.setColumnLabel(13, ""+cyear);
	    	 cyear--;
	    	 module.$("#mainGrid")[0].grid.setColumnLabel(12, ""+cyear);
	    	 cyear--;
	    	 module.$("#mainGrid")[0].grid.setColumnLabel(11, ""+cyear);
//	    	 data.resultList=data.resultList.splice(0,20);
	         return data;
	     }

	 });

	 this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		});

	 this.$('#referYear').val(new Date().getFullYear());
	 var cyear = Number(this.$('#referYear').val());
 	 this.$("#mainGrid")[0].grid.setColumnLabel(15, ""+cyear);
	cyear--;
	 this.$("#mainGrid")[0].grid.setColumnLabel(14, ""+cyear);
		cyear--;
	 this.$("#mainGrid")[0].grid.setColumnLabel(13, ""+cyear);
		cyear--;
	 this.$("#mainGrid")[0].grid.setColumnLabel(12, ""+cyear);
		cyear--;
	 this.$("#mainGrid")[0].grid.setColumnLabel(11, ""+cyear);
};

GamPrtFcltyUsageConfmInqireModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'searchBtn':
			this.loadConfmInqireList();
            break;
         // 자산코드 팝업
		case "popupGisCode":
			this.doExecuteDialog("selectGisCodePopup", "자산코드", '/popup/showAssetsCd.do', {});
		break;

        case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            var opts = {
                'entrpscd': this.$('#sEntrpscd').val(),
                'entrpsNm': this.$('#sEntrpsNm').val(),
            };

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', opts);
            break;

		case 'btnExcelDownload':	// 엑셀 다운로드
			this.downloadExcel(buttonId);
			break;

    }
};

GamPrtFcltyUsageConfmInqireModule.prototype.loadConfmInqireList = function() {

    var searchOpt=this.makeFormArgs('#searchForm');
    this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
    // console.log('load rent list');
};

GamPrtFcltyUsageConfmInqireModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/oper/gnrl/downloadPrtFcltyUageConfmInqireXlsList.do');

};


var module_instance = new GamPrtFcltyUsageConfmInqireModule();
</script>


<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<!-- 11. SEARCH AREA (조회조건 영역) -->
	<div id="searchViewStack" class="emdPanel">
		<form id="searchForm">
			<table style="width:100%;" class="searchPanel">
				<tbody>
					<tr>
						<th>항구분</th>
						<td>
							<input id="prtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019"/>
						</td>
						<th>기준년도</th>
						<td>
							<input id="referYear"/>
						</td>
						<td>
							<button id="searchBtn" class="buttonSearch">조회</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="emdPanel fillHeight">
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