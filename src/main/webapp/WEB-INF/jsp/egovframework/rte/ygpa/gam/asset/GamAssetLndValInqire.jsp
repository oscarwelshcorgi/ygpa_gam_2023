<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetLndValInqire.jsp
  * @Description : 자산부지공시지가조회
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
function GamAssetLndValInqireModule() {}

GamAssetLndValInqireModule.prototype = new EmdModule(1000, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamAssetLndValInqireModule.prototype.loadComplete = function() {
 
 // 테이블 설정 //       
 this.$("#assetLndValInqireList").flexigrid({
     module: this,
     url: '<c:url value="/asset/gamSelectAssetLndValInqireList.do"/>',
     dataType: 'json',
     colModel : [
                 {display:'자산면적', name:'gisAssetsAr',width:100, sortable:false,align:'center'},
                 {display:'공시지가', name:'olnlp',width:100, sortable:false,align:'center', displayFormat: 'number'},
                 {display:'면적대비 공시지가', name:'arOlnlp',width:130, sortable:false,align:'center', displayFormat: 'number'},
                 //{display:'GIS 자산 코드', name:'gisAssetsCd',width:100, sortable:false,align:'center'},
                 {display:'자산코드', name:'gisAssetsCdStr',width:100, sortable:false,align:'center'},
                 {display:'자산명', name:'gisAssetsNm',width:150, sortable:false,align:'center'},
                 {display:'자산소재지', name:'gisAssetsLocplc',width:150, sortable:false,align:'center'},
                 {display:'자산지번', name:'gisAssetsLnm',width:80, sortable:false,align:'center'},
                 {display:'자산사용여부', name:'gisAssetsUsageYn',width:100, sortable:false,align:'center'},
                 {display:'자산취득가액', name:'gisAssetsAcqPri',width:100, sortable:false,align:'center', displayFormat: 'number'},
                 {display:'자산규격', name:'gisAssetsStndrd',width:100, sortable:false,align:'center'},
                 {display:'자산준공년도', name:'gisAssetsBlddate',width:100, sortable:false,align:'center'},          
                 {display:'자산준공일자', name:'gisAssetsBldDt',width:100, sortable:false,align:'center'},          
                 {display:'자산비고', name:'gisAssetsRm',width:100, sortable:false,align:'center'},     
                 {display:'자산실제임대면적', name:'gisAssetsRealRentAr',width:100, sortable:false,align:'center', displayFormat: 'number'},
                 {display:'등록자', name:'regUsr',width:100, sortable:false,align:'center'},                   
                 {display:'등록일자', name:'registdt',width:100, sortable:false,align:'center'},                    
                 {display:'수정자', name:'updUsr',width:100, sortable:false,align:'center'},                                   
                 {display:'수정일자', name:'updtdt',width:100, sortable:false,align:'center'}                                  
                 ],
    showTableToggleBtn: false,
    height: 'auto'
 });
};
     
/**
* 정의 된 버튼 클릭 시
*/
GamAssetLndValInqireModule.prototype.onButtonClick = function(buttonId) {

 switch(buttonId) {

     // 조회
     case 'searchBtn':
         var searchOpt=this.makeFormArgs('#gamAssetLndValInqireSearchForm');
         this.$('#assetLndValInqireList').flexOptions({params:searchOpt}).flexReload();

         break;
 }
};

GamAssetLndValInqireModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamAssetLndValInqireModule.prototype.loadData = function() {
 var searchOpt=this.makeFormArgs('#gamAssetLndValInqireSearchForm');
 //this.showAlert(searchOpt);
 this.$('#assetLndValInqireList').flexOptions({params:searchOpt}).flexReload();
};

GamAssetLndValInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
 switch(newTabId) {
 case 'tabs1':
     break;
 case 'tabs2':
     break;
 }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetLndValInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetLndValInqireSearchForm">
                <table class="searchPanel">
                    <tbody>
                        <tr>
                            <th>GIS자산코드</th>
                            <td><input id="sGisAssetsCd" type="text" size="5"></td>
                            <th>GIS자산명</th>
                            <td><input id="sGisAssetsNm" type="text" size="8"></td>
                            <th>시작(종료)일자</th>
                            <td><input id="sBeginDt" type="text" class="emdcal"
                                size="8"> ~ <input id="sEndDt" type="text"
                                class="emdcal" size="8"></td>
                            <td rowSpan="2"><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                        <!-- 
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
                 
                <li><a href="#tabs1" class="emdTab">자산부지공시지가</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="assetLndValInqireList" style="display:none" class="fillHeight"></table>
               
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