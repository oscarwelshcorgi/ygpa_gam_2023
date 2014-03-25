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

GamAssetEvlDtlsInqireModule.prototype = new EmdModule(1150, 580);

//페이지가 호출 되었을때 호출 되는 함수
GamAssetEvlDtlsInqireModule.prototype.loadComplete = function() {
 
 // 테이블 설정 //       
 this.$("#assetEvlDtlsInqireList").flexigrid({
     module: this,
     url: '<c:url value="/asset/gamSelectAssetEvlDtlsInqireList.do"/>',
     dataType: 'json',
     colModel : [
                 {display:'자산명', name:'gisAssetsNm',width:150, sortable:false,align:'center'},
                 {display:'자산소재지', name:'gisAssetsLocplc',width:200, sortable:false,align:'center'},
                 {display:'자산코드', name:'gisAssetsCd',width:100, sortable:false,align:'center'},
                 {display:'자산면적', name:'gisAssetsAr',width:100, sortable:false,align:'center'},
                 {display:'자산지번', name:'gisAssetsLnm',width:100, sortable:false,align:'center'},
                 {display:'자산지번SUB', name:'gisAssetsLnmSub',width:100, sortable:false,align:'center'},
                 {display:'자산항코드', name:'gisAssetsPrtAtCode',width:100, sortable:false,align:'center'},
                 {display:'자산사용 여부', name:'gisAssetsUsageYn',width:100, sortable:false,align:'center'},
                 {display:'자산취득가액', name:'gisAssetsAcqPri',width:100, sortable:false,align:'center'},
                 {display:'자산규격', name:'gisAssetsStndrd',width:100, sortable:false,align:'center'},
                 {display:'자산준공년도', name:'gisAssetsBlddate',width:100, sortable:false,align:'center'},          
                 {display:'자산준공일자', name:'gisAssetsBldDt',width:100, sortable:false,align:'center'},          
                 {display:'자산비고', name:'gisAssetsRm',width:100, sortable:false,align:'center'},
                 {display:'자산SUB코드', name:'gisAssetsSubCd',width:100, sortable:false,align:'center'},
                 {display:'자산관리부서코드', name:'gisAssetsMngDeptCd',width:100, sortable:false,align:'center'},
                 {display:'자산운영부서코드', name:'gisAssetsOperDeptCd',width:100, sortable:false,align:'center'}
                 
                 /*
                 {display:'등록자', name:'regUsr',width:100, sortable:false,align:'center'},                   
                 {display:'등록일자', name:'registdt',width:100, sortable:false,align:'center'},                    
                 {display:'수정자', name:'updUsr',width:100, sortable:false,align:'center'},                                   
                 {display:'수정일자', name:'updtdt',width:100, sortable:false,align:'center'},                                  
                 {display:'GIS 자산 부두 그룹 코드', name:'gisAssetsQuayGroupCd',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 위치 코드', name:'gisAssetsLocCd',width:100, sortable:false,align:'center'},                   
                 {display:'GIS 자산 구분 코드', name:'gisAssetsSeCd',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 재산 구분 코드', name:'gisAssetsPrprtySeCd',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 출자 방식', name:'gisAssetsInvstmntMthd',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 GIS 코드', name:'gisAssetsGisCd',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 실제 임대 면적', name:'gisAssetsRealRentAr',width:100, sortable:false,align:'center'},          
                 {display:'도면 목록 등록 년도', name:'drwLstRegistYear',width:100, sortable:false,align:'center'},         
                 {display:'도면 목록 순번', name:'drwLstSeq',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 가치 금액', name:'gisAssetsValAmt',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 가치 조회 일자', name:'gisAssetsValInqireDt',width:100, sortable:false,align:'center'},
                 {display:'ERP 자산 구분 코드', name:'erpAssetsSeCd',width:100, sortable:false,align:'center'},           
                 {display:'ERP 자산 번호', name:'erpAssetsNo',width:100, sortable:false,align:'center'},
                 {display:'ERP 자산 번호 순번', name:'erpAssetsNoSeq',width:100, sortable:false,align:'center'},
                 {display:'ERP 자산 폐기 등록 여부', name:'erpAssetsDisuseRegistYn',width:100, sortable:false,align:'center'},                      
                 {display:'ERP 자산 폐기 사유', name:'erpAssetsDisuseRsn',width:100, sortable:false,align:'center'},
                 {display:'부두코드', name:'gisAssetsQuayCd',width:100, sortable:false,align:'center'}
                 */
                 ],
     /*usepager: true,
     useRp: true,
     rp: 24,*/
     showTableToggleBtn: false,
     height: 'auto'
 });
};

this.$("#assetEvlDtlsInqireList").on("onItemSelected", function(event, module, row, grid, param) {
    //alert("row " + row["erpAssetsSeCd"]+"-"+row["erpAssetsNo"]+"-"+row["erpAssetsNoSeq"]+" is selected");
    
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
         
     // ERP 조회
     /*
     case 'erpSearchBtn':
    	 var rows = this.$('#assetEvlDtlsInqireList').selectedRows();

    	    if(rows.length>=1) {
		   	    this.doAction('<c:url value="/asset/gamSelectAssetEvlDtlsInqireErp.do" />', rows[0], function(module, result) {
	                if(result.resultCode=='0') {
	                	module.$('#thisTermIncreAmt').val(result.thisTermIncreAmt); 
	                	module.$('#bsThisCurAmt').val(result.bsThisCurAmt);
	                	module.$('#bsPreDeprctnSum').val(result.bsPreDeprctnSum);
	                	module.$('#bsNoDeprctnBal').val(result.bsNoDeprctnBal);
	                	module.$('#preEndAssetBuySum').val(result.preEndAssetBuySum);
	                	module.$('#assetBuyAmt').val(result.assetBuyAmt);
	                	module.$('#thisTermDeprctnAmt').val(result.thisTermDeprctnAmt);
	                	module.$('#curAmt').val(result.curAmt);
	                } else {
	                	alert(result.resultMsg);
	                }
	            });
	   	        
    	    } else {
                alert("목록에서 선택하십시오.");
            }

         break;
     */
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
                            <td><input id="sGisAssetsBlddate" type="text" size="5" value=""></td>
                            <th>재산구분</th>
                            <td><input id="sGisAssetsPrprtySeCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM001 /></td>
                            <th>위치</th>
                            <td><input id="sGisAssetsLocCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM002 /></td>
                            <th>부두</th>
                            <td><input id="sGisAssetsQuayCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM003 /></td>
                            <td><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                        <!-- 
                        <tr>
                            <th>GIS자산코드</th>
                            <td><input id="sGisAssetsCd" type="text" size="5"></td>
                            <th>GIS자산명</th>
                            <td><input id="sGisAssetsNm" type="text" size="8"></td>
                            <th>GIS자산가치 조회일자</th>
                            <td><input id="sGisAssetsValInqireDtFrom" type="text" class="emdcal"
                                size="8"> ~ <input id="sGisAssetsValInqireDtTo" type="text"
                                class="emdcal" size="8"></td>
                            <td rowSpan="2"><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                        
                        <tr>
                            <th>관리부서</th>
                            <td><select id="mngDeptCd"></select></td>
                            <th>운영부서</th>
                            <td><select id="operDeptCd"></select></td>
                        </tr>
                         -->
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentFeeListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <!-- 
                <li><a href="#tabs1" class="emdTab">자산정보현황 목록</a></li>
                <li><a href="#tabs2" class="emdTab">자산정보현황 상세</a></li>
                 -->
                 
                <li><a href="#tabs1" class="emdTab">자산가치평가내역</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="assetEvlDtlsInqireList" style="display:none" class="fillHeight"></table>

                <div class="emdControlPanel">
                    <table style="width:100%">
                        <form id="form1">
                        <tr>
                            <td colspan="8">
                                <!-- <button id="erpSearchBtn" class="submit">ERP 감가상가내역</button>  -->
                                <a href="#erpInfo" class="emdTab"><strong>ERP 감가상가 조회내역</strong></a>
                            </td>
                        </tr>
                        <tr>
                            <td>자산구분</td>
                            <td><input id="assetCls" size="15" readonly></td>
                            <td>자산번호</td>
                            <td><input id="assetNo" size="15" readonly></td>
                            <td>자산번호순번</td> 
                            <td colspan="3"><input id="assetNoSeq" size="15" readonly></td>
                        </tr>
                        <tr>
                            <td style="width: 160px">당기자산증가금액</td>
                            <td style="width: 120px"><input id="thisTermIncreAmt" size="15" readonly></td>
                            <td style="width: 130px">대차대조기말현재금액</td>
                            <td style="width: 120px"><input id="bsThisCurAmt" size="15" readonly></td>
                            <td style="width: 170px">대차대조전기말상각누계금액</td> 
                            <td><input id="bsPreDeprctnSum" size="15" readonly></td>
                            <td style="width: 120px">대차대조미상각잔액</td> 
                            <td><input id="bsNoDeprctnBal" size="15" readonly></td>
                        </tr>
                        <tr>
                            <td>전기말자본적지출금액누계</td>
                            <td><input id="preEndAssetBuySum" size="15" readonly></td>
                            <td>자본적지출금액</td> 
                            <td><input id="assetBuyAmt" size="15" readonly></td>
                            <td>당기상각금액</td> 
                            <td><input id="thisTermDeprctnAmt" size="15" readonly></td>
                            <td>잔존금액</td>
                            <td><input id="curAmt" size="15" readonly></td>
                        </tr>
                        </form>
                    </table>
                </div>
                <!-- 
                <div class="emdControlPanel">
                    <table style="width:100%;" >
                        <tr>
                            <td>
                               <form id="form1">
                                                                        합계
                                                                        자료수 <input id="totalResultCnt" size="5" readonly>
                                                                        사용료 <input id="sumFee" type="text" size="14" readonly>
                                                                        연체 <input id="sumArrrgAmt" type="text" size="14" readonly>
                                                                        부가세 <input id="sumVat" type="text" size="14" readonly>
                                                                        고지액 <input id="sumNticAmt" type="text" size="14" readonly>
                               </form>
                            </td>
                            <td>
                                <button id="saveNticListBtn">고지의뢰</button>
                                <button id="cancelNticListBtn">고지취소</button>
                            </td>
                        </tr>
                    </table>
                </div>
                 -->
            </div>
            
            <!-- 
            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">

                <div class="emdControlPanel">
                    <button id="btnSaveItem">저장</button><button id="btnCancelItem">취소</button><button id="saveNticDetailBtn">고지의뢰</button><button id="cancelNticDetailBtn">고지취소</button>
                    <form id="gamAssetRentFeeForm">
                        <input type="hidden" id="cmd"/>
                        
                        <table>
                            <tr>
                                <th><span class="label">고지 횟수</span></th>
                                <td><input type="text" size="10" id="nticCnt"/></td>
                                <th><span class="label">시설 구분</span></th>
                                <td><input type="text" size="10" id="fcltySe"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">요금 종류</span></th>
                                <td>
                                    <select id="chrgeKnd">
                                        <option value="">선택</option>
                                        <c:forEach items="${chrgeKndCdList}" var="chrgeKndItem">
                                            <option value="${chrgeKndItem.code }">${chrgeKndItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th><span class="label">업체코드</span></th>
                                <td><input type="text" size="10" id="entrpscd"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">고지번호</span></th>
                                <td><input type="text" size="10" id="nticno"/></td>
                                <th><span class="label">고지 일자</span></th>
                                <td><input type="text" size="10" id="nticDt"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">납부 기한</span></th>
                                <td><input type="text" size="10" id="payTmlmt"/></td>
                                <th><span class="label">공시지가</span></th>
                                <td><input type="text" size="10" id="olnlp"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">사용료</span></th>
                                <td><input type="text" size="10" id="fee"/></td>
                                <th><span class="label">부가세 여부</span></th>
                                <td>
                                    <select id="vatYn">
                                        <option value="" selected="selected">선택</option>
                                        <option value="Y">Y</option>
                                        <option value="N">N</option>
                                    </select>
                                </td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">부가세</span></th>
                                <td><input type="text" size="10" id="vat"/></td>
                                <th><span class="label">고지 금액</span></th>
                                <td><input type="text" size="10" id="nticAmt"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">비고</span></th>
                                <td><input type="text" size="10" id="rm"/></td>
                                <th><span class="label">수납 구분</span></th>
                                <td>
                                    <select id="rcivSe">
                                        <option value="">선택</option>
                                        <c:forEach items="${rcivSeCdList}" var="rcivSeItem">
                                            <option value="${rcivSeItem.code }">${rcivSeItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </form>
            </div>

        </div>
        -->
    </div>
</div>