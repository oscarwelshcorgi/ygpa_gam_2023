<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetTypeValueSts.jsp
  * @Description : 자산종류별자산가치통계조회
  * @Modification heroine
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.14  heroine          최초 생성
  *
  * author heroine
  * since 2014.01.14
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetTypeValueStsModule() {}

GamAssetTypeValueStsModule.prototype = new EmdModule(1000, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamAssetTypeValueStsModule.prototype.loadComplete = function() {

 // 테이블 설정 //
 this.$("#assetTypeValueStsList").flexigrid({
     module: this,
     url: '<c:url value="/asset/gamSelectAssetTypeValueStsList.do"/>',
     dataType: 'json',
     colModel : [
                 {display:'상각연도', name:'deprctnYear',width:60, sortable:false,align:'center'},
                 {display:'재산종류', name:'gisAssetsPrprtySeNm',width:80, sortable:false,align:'left'},
                 {display:'자산구분', name:'gisAssetsSeNm',width:180, sortable:false,align:'left'},
                 {display:'부두명', name:'gisAssetsQuayNm',width:100, sortable:false,align:'left'},
                 {display:'재평가금액', name:'sumRevalAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'당기자산증가금액', name:'sumThisTermIncreAmt',width:110, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'대차대조기말현재금액', name:'sumBsThisCurAmt',width:130, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'대차대조전기말상각누계금액', name:'sumBsPreDeprctnSum',width:170, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'대차대조미상각잔액', name:'sumBsNoDeprctnBal',width:120, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'전기말자본적지출금액누계', name:'sumPreEndAssetBuySum',width:160, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'자본적지출금액', name:'sumAssetBuyAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'당기상각금액', name:'sumThisTermDeprctnAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'잔존금액', name:'sumCurAmt',width:100, sortable:false,align:'right', displayFormat: 'number'}
                 ],
     showTableToggleBtn: false,
     height: 'auto',
     preProcess: function(module,data) {

    	 module.$('#totalResultCnt').val($.number(data.totalCount));
         module.$('#sumRevalAmt').val($.number(data.sumRevalAmt));
         module.$('#sumThisTermIncreAmt').val($.number(data.sumThisTermIncreAmt));
         module.$('#sumBsThisCurAmt').val($.number(data.sumBsThisCurAmt));
         module.$('#sumBsPreDeprctnSum').val($.number(data.sumBsPreDeprctnSum));
         module.$('#sumBsNoDeprctnBal').val($.number(data.sumBsNoDeprctnBal));
         module.$('#sumPreEndAssetBuySum').val($.number(data.sumPreEndAssetBuySum));
         module.$('#sumAssetBuyAmt').val($.number(data.sumAssetBuyAmt));
         module.$('#sumThisTermDeprctnAmt').val($.number(data.sumThisTermDeprctnAmt));
         module.$('#sumCurAmt').val($.number(data.sumCurAmt));

         return data;
     }
 });
};

this.$("#assetTypeValueStsList").on("onItemSelected", function(event, module, row, grid, param) {
    //alert("row " + row["erpAssetsSeCd"]+"-"+row["erpAssetsNo"]+"-"+row["erpAssetsNoSeq"]+" is selected");



});

/**
* 정의 된 버튼 클릭 시
*/
GamAssetTypeValueStsModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

	    // 조회
	    case 'searchBtn':
	        var searchOpt=this.makeFormArgs('#gamAssetTypeValueStsSearchForm');
	        this.$('#assetTypeValueStsList').flexOptions({params:searchOpt}).flexReload();

	        break;

	    case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            var opts;

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;

	}
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamAssetTypeValueStsModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
	   case 'selectEntrpsInfoPopup':
	       if (msg != 'cancel') {
	           this.$('#sEntrpscd').val(value.entrpscd);
	           this.$('#sEntrpsNm').val(value.entrpsNm);
	       } else {
	           alert('취소 되었습니다');
	       }
	       break;
	}
};

GamAssetTypeValueStsModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamAssetTypeValueStsModule.prototype.loadData = function() {
 var searchOpt=this.makeFormArgs('#gamAssetTypeValueStsSearchForm');
 //this.showAlert(searchOpt);
 this.$('#assetTypeValueStsList').flexOptions({params:searchOpt}).flexReload();
};

GamAssetTypeValueStsModule.prototype.onTabChange = function(newTabId, oldTabId) {
 switch(newTabId) {
 case 'tabs1':
     break;
 case 'tabs2':
     break;
 }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetTypeValueStsModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetTypeValueStsSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th style="width:70px;">상각연도</th>
                            <td style="width:100px;">
                                <!-- <input id="sDeprctnYear" type="text" size="2">  -->
                                <select id="sDeprctnYear">
                                    <option value="">선택</option>
                                    <c:forEach items="${yearList}" var="yearListItem">
                                        <option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisYear}">selected</c:if> >${yearListItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
							<th>재산</th>
							<td><input id="sGisAssetsPrprtySeCd" class="ygpaCmmnCd" data-code-id="GAM001" data-default-prompt="전체"/></td>
							<th>부두</th>
							<td><input id="sGisAssetsQuayCd" class="ygpaCmmnCd" data-code-id="GAM003" data-default-prompt="전체"/></td>
							<th>자산구분</th>
							<td><input id="sGisAssetsSeCd" class="ygpaCmmnCd" data-code-id="GAM013" data-default-prompt="전체"/></td>
                            <td style="text-align:right;"><button id="searchBtn"  class="submit buttonSearch">조회</button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

	<div class="emdPanel fillHeight">
		<div id="assetRentFeeListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">자산종류별자산가치통계</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" data-onactivate="onShowTab1Activate">
            <table id="assetTypeValueStsList" style="display:none" class="fillHeight"></table>
            
				<div class="emdControlPanel">
					<table style="width:100%;" class="summaryPanel">
						<tr>
							<td style="width:650px;" colspan="4"> </td>
							<th style="width:165px;">자료수</th>
							<td style="width:200px;"><input id="totalResultCnt" type="text" size="15" class="ygpaNumber" readonly >개</td>
						</tr>
						<tr>
							<th style="width:120px;">재평가금액</th>
							<td style="width:200px;"><input id="sumRevalAmt" size="15" class="ygpaNumber" readonly >원</td>
							<th style="width:130px;">당기자산증가금액</th>
							<td style="width:200px;"><input id="sumThisTermIncreAmt" type="text" size="15" class="ygpaNumber" readonly >원</td>
							<th style="width:165px;">대차대조기말현재금액</th>
							<td style="width:200px;"><input id="sumBsThisCurAmt" type="text" size="15" class="ygpaNumber" readonly >원</td>
						</tr>
						<tr>
							<th>대차대조전기말상각누계금액</th>
							<td><input id="sumBsPreDeprctnSum" type="text" size="15" class="ygpaNumber" readonly>원</td>
							<th>대차대조미상각잔액</th>
							<td><input id="sumBsNoDeprctnBal" type="text" size="15" class="ygpaNumber" readonly>원</td>
							<th>전기말자본적지출금액 누계</th>
							<td><input id="sumPreEndAssetBuySum" type="text" size="15" class="ygpaNumber" readonly >원</td>
						</tr>
						<tr>
							<th>자본적지출금액</th>
							<td><input id="sumAssetBuyAmt" type="text" size="15" class="ygpaNumber" readonly >원</td>
							<th>당기상각금액</th>
							<td><input id="sumThisTermDeprctnAmt" type="text" size="15" class="ygpaNumber" readonly >원</td>
							<th>잔존금액</th>
							<td><input id="sumCurAmt" type="text" size="15" class="ygpaNumber" readonly >원</td>
						</tr>
					</table>
				</div>          
			</div>
		</div>
	</div>
</div>