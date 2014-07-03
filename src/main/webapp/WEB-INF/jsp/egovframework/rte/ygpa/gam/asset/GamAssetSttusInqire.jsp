<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetSttusInqire.jsp
  * @Description : 자산정보현황조회
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
function GamAssetSttusInqireModule() {}

GamAssetSttusInqireModule.prototype = new EmdModule(1000, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamAssetSttusInqireModule.prototype.loadComplete = function() {

 // 테이블 설정 //
 this.$("#assetSttusInqireList").flexigrid({
     module: this,
     url: '<c:url value="/asset/gamAssetSttusInqireList.do"/>',
     dataType: 'json',
     colModel : [
				 {display:'항코드', name:'prtAtCode',width:40, sortable:false,align:'center'},
				 {display:'항코드명', name:'prtAtCodeNm',width:55, sortable:false,align:'center'},
				 {display:'관리번호', name:'rentMngNo',width:80, sortable:false,align:'center'},
				 {display:'신청업체', name:'entrpscd',width:60, sortable:false,align:'center'},
				 {display:'신청업체명', name:'entrpsNm',width:100, sortable:false,align:'left'},
                 {display:'자산코드', name:'assetsCdStr',width:55, sortable:false,align:'center'},
                 {display:'자산명', name:'gisAssetsNm',width:150, sortable:false,align:'left'},
                 {display:'사용시작일', name:'usagePdFrom',width:80, sortable:false,align:'center'},
                 {display:'사용종료일', name:'usagePdTo',width:80, sortable:false,align:'center'},
                 {display:'사용료', name:'fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'사용면적', name:'usageAr',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'감면사용료', name:'rdcxptFee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'재산구분', name:'gisAssetsPrprtySeCdNm',width:80, sortable:false,align:'left'},
                 {display:'위치', name:'gisAssetsLocCdNm',width:150, sortable:false,align:'left'},
                 {display:'부두', name:'gisAssetsQuayCdNm',width:150, sortable:false,align:'left'},
                 {display:'사용용도', name:'usagePrposCdNm',width:150, sortable:false,align:'left'},
                 {display:'사용목적', name:'usagePurps',width:150, sortable:false,align:'left'}
                 ],
     showTableToggleBtn: false,
     height: 'auto',
     preProcess: function(module,data) {
         module.$('#sumCnt').val(data.sumCnt);
         module.$('#sumAr').val(data.sumAr);
         module.$('#sumFee').val(data.sumFee);
         module.$('#sumRdcxptFee').val(data.sumRdcxptFee);

         return data;
     }
});


	// 오늘날짜로 기준일자 설정 처리
	var today = new Date();

	var serchYr = today.getFullYear();
	var serchMn = today.getMonth() + 1;

	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}

	var serchday = today.getDate();
	if(serchday < 10){
		serchday = "0" + serchday;
	}

	var displayDate = serchYr + "-" + serchMn + "-" + serchday;

	this.$("#sUsagePdFrom").val(displayDate);
	this.$("#sUsagePdTo").val(displayDate);
};

/**
* 정의 된 버튼 클릭 시
*/
GamAssetSttusInqireModule.prototype.onButtonClick = function(buttonId) {

 switch(buttonId) {

     // 조회
     case 'searchBtn':

    	 if(this.$("#sUsagePdFrom").val() == ""){
    		 alert("사용시작일을 선택하세요.");
    		 return;
    	 }
    	 if(this.$("#sUsagePdTo").val() == ""){
    		 alert("사용종료일을 선택하세요.");
    		 return;
    	 }

         var searchOpt=this.makeFormArgs('#gamAssetSttusInqireSearchForm');
         this.$('#assetSttusInqireList').flexOptions({params:searchOpt}).flexReload();

         break;

     case 'popupEntrpsInfo': // 업체팝업을 호출한다.(조회)
         var opts;

         this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
         break;

	// 자산코드 팝업
	case "searchPopupBtn":
		this.doExecuteDialog("searchGisCodePopup", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;

 }
};

GamAssetSttusInqireModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamAssetSttusInqireModule.prototype.loadData = function() {
 var searchOpt=this.makeFormArgs('#gamAssetSttusInqireSearchForm');
 //this.showAlert(searchOpt);
 this.$('#assetSttusInqireList').flexOptions({params:searchOpt}).flexReload();
};

GamAssetSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
 switch(newTabId) {
 case 'tabs1':
     break;
 case 'tabs2':
     break;
 }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamAssetSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
  switch (popupId) {
   case 'selectEntrpsInfoPopup':
       if (msg != 'cancel') {
           this.$('#sEntrpscd').val(value.entrpscd);
           this.$('#sEntrpsNm').val(value.entrpsNm);
       } else {
           alert('취소 되었습니다');
       }
       break;

   default:
       alert('알수없는 팝업 이벤트가 호출 되었습니다.');
       
       break;
   }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetSttusInqireSearchForm">
                <table class="searchPanel">
                    <tbody>
                        <tr>
                            <th style="width: 40px"><span class="label">항구분</span></th>
                            <td style="width: 230px">
                                <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM019 />
                            </td>

                            <th style="width: 40px"><span class="label">자산코드</span></th>
                            <td style="width: 120px">
                                <input id="sGisAssetsCd" type="text" class="gisAssetsCd"/>&nbsp;-&nbsp;
                                <input id="sGisAssetsSubCd" type="text" class="gisAssetsSubCd"/>
                            </td>

                            <th style="width: 40px"><span class="label">업체</span></th>
                            <td>
                            	<input id="sEntrpscd" type="text" size="6">
                	        <!--
                            	<input id="sEntrpsNm" type="text" size="12" disabled="disabled">&nbsp; &nbsp;
                    	     -->
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>

                            <th style="width: 40px"><span class="label">부두</span></th>
                            <td style="width: 100px">
                                <input id="sQuayCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM003 />
                            </td>
                            <td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
                         </tr>
                         <tr>
                            <th>기준일자</th>
                            <td><input id="sUsagePdFrom" type="text" class="emdcal"
                                size="8"> ~ <input id="sUsagePdTo" type="text"
                                class="emdcal" size="8"></td>

                            <th>승낙구분</th>
                            <td>
                                <select id="sPrmisnYn">
                                    <option value="" selected="selected">전체</option>
                                    <option value="Y">Y</option>
                                    <option value="N">N</option>
                                </select>
                            </td>

                            <th><span class="label">담당부서</span></th>
                            <td>
                                <input id="deptcd" class="ygpaDeptSelect" data-default-prompt="전체" />
                            </td>

                            <td></td>
                            <td></td>
                        </tr>
                        <!--
                        <tr>
                            <th>GIS자산코드</th>
                            <td><input id="sGisAssetsCd" type="text" size="5"></td>
                            <th>자산명</th>
                            <td><input id="sGisAssetsNm" type="text" size="8"></td>
                            <th>사용기간</th>
                            <td><input id="sUsagePdFrom" type="text" class="emdcal"
                                size="8"> ~ <input id="sUsagePdTo" type="text"
                                class="emdcal" size="8"></td>
                            <td rowSpan="2"><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                        -->
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

                <li><a href="#tabs1" class="emdTab">자산정보현황</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
				<table id="assetSttusInqireList" style="display:none" class="fillHeight"></table>

                <div class="emdControlPanel">
					<form id="form1">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="10%" height="20">자료수</th>
								<td><input type="text" size="8" id="sumCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">면적</th>
								<td><input type="text" size="16" id="sumAr" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">사용료</th>
								<td><input type="text" size="16" id="sumFee" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">감면사용료</th>
								<td><input type="text" size="16" id="sumRdcxptFee" class="ygpaNumber" disabled="disabled" /></td>
								<td>
									<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="assetSttusInqireList" data-style="default">맵조회</button>
								</td>
							</tr>
						</table>
					</form>
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
</div>