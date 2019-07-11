<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamTrainPortPopupInqire.jsp
  * @Description : 철송장 정보현황알림
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.06.17  sj          최초 생성
  *
  * author sj
  * since 2014.02.07
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFenderPopupInqireModule() {}

GamFenderPopupInqireModule.prototype = new EmdModule(400, 200);

//페이지가 호출 되었을때 호출 되는 함수
GamFenderPopupInqireModule.prototype.loadComplete = function() {

 // 테이블 설정 //
};

/**
* 정의 된 버튼 클릭 시
*/
GamFenderPopupInqireModule.prototype.onButtonClick = function(buttonId) {

 switch(buttonId) {

     // 조회
/*      case 'searchBtn':
         var searchOpt=this.makeFormArgs('#gamAssetLndValInqireSearchForm');
         this.$('#assetLndValInqireList').flexOptions({params:searchOpt}).flexReload();

         break;
 */
     case 'btnDurability':
    	 EMD.util.create_window('gamTrainPortRentMngt', '방충재 내구연한 도래', '/fclty/gamFenderDurability.do', null);
    	 break;
     case 'btnBad':
    	 EMD.util.create_window('gamTrainPortRentFeeMngt', '방충재 유지보수 관리', '/fclty/gamFenderMaintenance.do', null);
    	 break;
 }
};

/* GamFenderPopupInqireModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamFenderPopupInqireModule.prototype.loadData = function() {
 var searchOpt=this.makeFormArgs('#gamAssetLndValInqireSearchForm');
 //this.showAlert(searchOpt);
 this.$('#assetLndValInqireList').flexOptions({params:searchOpt}).flexReload();
};
 */
GamFenderPopupInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
 switch(newTabId) {
 case 'tabs1':
     break;
 }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFenderPopupInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">



    <div class="emdPanel fillHeight">
        <div id="assetRentFeeListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">방중재정보현황알림</a></li>
            </ul>
            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <div class="emdControlPanel">
                   <table style="width:100%;" >
                        <form id="form1">
                        <tr>
                            <th>
                                * 내구연한 도래
                            </th>
                            <td>
                                <input id="totalResultCnt1" size="10" readonly value="<c:out value="${durabilityCount}"/>">
                            </td>
                            <td>
                                <button id="btnDurability">방충재 내구연한 도래</button>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                * 불량/파손
                            </th>
                            <td>
                                <input id="totalResultCnt2" size="10" readonly value="<c:out value="${badCount}"/>">
                            </td>
                            <td>
                                <button id="btnBad">방충재 유지보수 관리</button>
                            </td>
                        </tr>
                        </form>
                    </table>
                </div>
				<form>
					<div align="right">
						하루 동안 알람 창 닫기 <input type="checkbox" id="chk">
					</div>
				</form>
            </div>
    </div>
</div>