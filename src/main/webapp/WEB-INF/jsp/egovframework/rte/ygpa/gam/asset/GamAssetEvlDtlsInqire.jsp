<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetEvlDtlsInqire.jsp
  * @Description : 자산가치평가내역조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.02.07  heroine          최초 생성
  *
  * author heroine
  * since 2014.02.07
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetEvlDtlsInqireModule() {}

GamAssetEvlDtlsInqireModule.prototype = new EmdModule(1000, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamAssetEvlDtlsInqireModule.prototype.loadComplete = function() {

 // 테이블 설정 //
 this.$("#assetEvlDtlsInqireList").flexigrid({
     module: this,
     url: '<c:url value="/asset/gamSelectAssetEvlDtlsInqireList.do"/>',
     dataType: 'json',
     colModel : [
                 {display:'항이름', name:'gisAssetsPrtAtCodeNm',width:80, sortable:false,align:'center'},
                 {display:'자산코드', name:'gisAssetsCdStr',width:60, sortable:false,align:'center'},
                 {display:'재산구분', name:'gisAssetsPrprtySeCdNm',width:80, sortable:false,align:'center'},
                 {display:'위치', name:'gisAssetsLocCdNm',width:150, sortable:false,align:'center'},
                 {display:'부두', name:'gisAssetsQuayCdNm',width:150, sortable:false,align:'center'},
                 {display:'자산명', name:'gisAssetsNm',width:150, sortable:false,align:'center'},
                 {display:'재평가금액', name:'revalAmt',width:140, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'당기자산증가금액', name:'thisTermIncreAmt',width:140, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'대차대조기말현재금액', name:'bsThisCurAmt',width:140, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'대차대조전기말상각누계금액', name:'bsPreDeprctnSum',width:180, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'대차대조미상각잔액', name:'bsNoDeprctnBal',width:140, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'전기말자본적지출금액누계', name:'preEndAssetBuySum',width:180, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'자본적지출금액', name:'assetBuyAmt',width:140, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'당기상각금액', name:'thisTermDeprctnAmt',width:140, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'잔존금액', name:'curAmt',width:140, sortable:false,align:'right', displayFormat: 'number'}
                 ],
     showTableToggleBtn: false,
     height: 'auto',
     preProcess: function(module,data) {
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

this.$("#assetEvlDtlsInqireList").on("onItemSelected", function(event, module, row, grid, param) {
    //alert("row " + row["erpAssetsSeCd"]+"-"+row["erpAssetsNo"]+"-"+row["erpAssetsNoSeq"]+" is selected");
    /*
     if(row!=null) {
         var erpAssetsSeCd = "";
         var erpAssetsNo = "";
         var erpAssetsNoSeq = "";

         erpAssetsSeCd = row['erpAssetsSeCd'];
         erpAssetsNo = row['erpAssetsNo'];
         erpAssetsNoSeq = row['erpAssetsNoSeq'];

         var inputVO = {erpAssetsSeCd: erpAssetsSeCd, erpAssetsNo: erpAssetsNo, erpAssetsNoSeq: erpAssetsNoSeq};

    	 module.doAction('<c:url value="/asset/gamSelectAssetEvlDtlsInqireErp.do" />', inputVO, function(module, result) {

             if(result.resultCode=='0') {
            	 module.$('#assetCls').val(result.assetCls);
            	 module.$('#assetNo').val(result.assetNo);
            	 module.$('#assetNoSeq').val(result.assetNoSeq);
                 module.$('#thisTermIncreAmt').val(result.thisTermIncreAmt);
                 module.$('#bsThisCurAmt').val(result.bsThisCurAmt);
                 module.$('#bsPreDeprctnSum').val(result.bsPreDeprctnSum);
                 module.$('#bsNoDeprctnBal').val(result.bsNoDeprctnBal);
                 module.$('#preEndAssetBuySum').val(result.preEndAssetBuySum);
                 module.$('#assetBuyAmt').val(result.assetBuyAmt);
                 module.$('#thisTermDeprctnAmt').val(result.thisTermDeprctnAmt);
                 module.$('#curAmt').val(result.curAmt);
             } else {
                 module.$('#assetCls').val('');
                 module.$('#assetNo').val('');
                 module.$('#assetNoSeq').val('');
                 module.$('#thisTermIncreAmt').val('');
                 module.$('#bsThisCurAmt').val('');
                 module.$('#bsPreDeprctnSum').val('');
                 module.$('#bsNoDeprctnBal').val('');
                 module.$('#preEndAssetBuySum').val('');
                 module.$('#assetBuyAmt').val('');
                 module.$('#thisTermDeprctnAmt').val('');
                 module.$('#curAmt').val('');

                 alert(result.resultMsg);
             }
         });
     } else {
    	 alert("선택된 항목이 없습니다.");
     }
    */
});

/**
* 정의 된 버튼 클릭 시
*/
GamAssetEvlDtlsInqireModule.prototype.onButtonClick = function(buttonId) {

 switch(buttonId) {

     // 조회
     case 'searchBtn':
         var searchOpt=this.makeFormArgs('#gamAssetEvlDtlsInqireSearchForm');
         this.$('#assetEvlDtlsInqireList').flexOptions({params:searchOpt}).flexReload();

         break;

 }
};

GamAssetEvlDtlsInqireModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamAssetEvlDtlsInqireModule.prototype.loadData = function() {
 var searchOpt=this.makeFormArgs('#gamAssetEvlDtlsInqireSearchForm');
 //this.showAlert(searchOpt);
 this.$('#assetEvlDtlsInqireList').flexOptions({params:searchOpt}).flexReload();
};

GamAssetEvlDtlsInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
 switch(newTabId) {
 case 'tabs1':
     break;
 case 'tabs2':
     break;
 }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetEvlDtlsInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetEvlDtlsInqireSearchForm">
                <table class="searchPanel">
                    <tbody>
                        <tr>
                            <th>상각연도</th>
                            <td>
                                <!-- <input id="sGisAssetsBlddate" type="text" size="5" value="">  -->

                                <select id="sGisAssetsBlddate">
                                    <option value="">선택</option>
                                    <c:forEach items="${yearList}" var="yearListItem">
                                        <option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisYear}">selected</c:if> >${yearListItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>재산구분</th>
                            <td><input id="sGisAssetsPrprtySeCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM001 /></td>
                            <th>위치</th>
                            <td><input id="sGisAssetsLocCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM002 /></td>
                            <th>부두</th>
                            <td><input id="sGisAssetsQuayCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM003 /></td>
                            <td><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentFeeListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">자산가치평가내역</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="assetEvlDtlsInqireList" style="display:none" class="fillHeight"></table>

                <div class="emdControlPanel">
                    <table style="width:100%;" class="summaryPanel">
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
                    <div class="emdControlPanel">
						<button id="loadMap" data-flexi-grid="assetEvlDtlsInqireList">맵조회</button>
						<button id="btnErpAssetCodeListExcelDownload">엑셀</button>
						<!--<button id="printGrid" data-flexi-grid="assetEvlDtlsInqireList">인쇄</button> -->
					</div>

					</div>

            </div>

    </div>
</div>