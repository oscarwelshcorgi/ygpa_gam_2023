<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamFcltyUseSttusInqire.jsp
  * @Description : 항만시설사용현황조회(포트미스정보)
  * @Modification Information
  * 
  *   수정일          수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.01.14  domh          최초 생성
  *  2014.04.14  domh          선석별 사용현황 조회처리 -- 기존 파일은 _처리 백업
  *
  * author domh
  * since 2014.01.14
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyUseSttusInqireModule() {}

GamFcltyUseSttusInqireModule.prototype = new EmdModule(1000, 550);

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyUseSttusInqireModule.prototype.loadComplete = function() {

    // 테이블 설정 //       
    this.$("#fcltyUseSttusInqireList").flexigrid({
        module: this,
        url: '<c:url value="/port_mis/gamSelectFcltyUseSttusInqireList.do" />',
        dataType: 'json',
        colModel : [      
					{display:'청코드', name:'prtAtCode',width:100, sortable:false,align:'center'},         
					{display:'선석코드', name:'fac_sub_code',width:100, sortable:false,align:'center'},                
					{display:'선석명', name:'fac_kor_nm',width:100, sortable:false,align:'center'},         
					{display:'요금종류코드', name:'fee_tp',width:100, sortable:false,align:'center'},  
					{display:'면제금액', name:'exmpAmnt',width:100, sortable:false,align:'center'},
					{display:'할인금액', name:'dcAmnt',width:100, sortable:false,align:'center'},               
					{display:'고지금액', name:'billAmnt',width:100, sortable:false,align:'center'},         
					{display:'생성일자', name:'prct_dt',width:120, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            /* module.$('#totalResultCnt').val(data.totalCount);
            module.$('#sumFee').val(data.sumFee);
            module.$('#sumVat').val(data.sumVat);
            module.$('#sumNticAmt').val(data.sumNticAmt); */
            
            return data;
        }
    });

    this.$("#fcltyUseSttusInqireList").on('onItemSelected', function(event, module, row, grid, param) {
        /* module.$('#cmd').val('modify');

        module.$('#gamFcltyUseSttusInqireForm :input').val('');

        module.makeFormValues('#gamFcltyUseSttusInqireForm', row);
        module._editData=module.getFormValues('#gamFcltyUseSttusInqireForm', row);
        module._editRow=module.$('#fcltyUseSttusInqireList').selectedRowIds()[0]; */
    });
    
    this.$("#fcltyUseSttusInqireList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
       // module.$("#fcltyUseSttusInqireListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
        
    });

};

/**
 * 정의 된 버튼 클릭 시
 */
 GamFcltyUseSttusInqireModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
            var searchOpt=this.makeFormArgs('#gamFcltyUseSttusInqireSearchForm');
            this.$('#fcltyUseSttusInqireList').flexOptions({params:searchOpt}).flexReload();

            break;

        // 팝업을 호출한다.(업체)     
        case 'popupBerthCd': 
            var opts;

            //this.doExecuteDialog('selectEntrpsInfoFeePayPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;
            
         // 팝업을 호출한다.(요금종류)     
        case 'popupChrgeKndCd': 
            var opts;

            //this.doExecuteDialog('selectEntrpsInfoFeePayPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;
            
    }
};

GamFcltyUseSttusInqireModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamFcltyUseSttusInqireModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamFcltyUseSttusInqireSearchForm');
    //this.showAlert(searchOpt);
    this.$('#fcltyUseSttusInqireList').flexOptions({params:searchOpt}).flexReload();
};

GamFcltyUseSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
GamFcltyUseSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
    case 'selectEntrpsInfoFeePayPopup':
        if (msg != 'cancel') {
            this.$('#sEntrpscd').val(value.entrpscd);
            this.$('#sEntrpsNm').val(value.entrpsNm);
        } else {
            alert('취소 되었습니다');
        }
        break;
         
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');
         throw 0;
         break;
     }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyUseSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamFcltyUseSttusInqireSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th style="width: 70px">항코드</th>
                            <td style="width: 400px">
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">선택</option>

                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.code }">${prtAtCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>사용기간</th>
                            <td>
                            <input id="sGrUsagePdFrom" type="text" class="emdcal"
                                size="8"> ~ <input id="sGrUsagePdTo" type="text"
                                class="emdcal" size="8">
                            </td>
                            <td rowspan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
                        </tr>
						<tr>
							<th style="width: 70px">선석</th>
                            <td>
                                <input id="fac_code" type="text" size="10"> 
                                <input id="fac_sub_code" type="text" size="10"> 
                                <input id="fac_kor_nm" type="text" size="10"> 
                                <button id="popupBerthCd">선석</button>
                            </td>
                            <th>요금종류</th>
                            <td>
                                <input id="chrgeKndCd" type="text" size="10"> 
                                <button id="popupChrgeKndCd">요금</button>
                            </td>
						</tr>
                        
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="fcltyUseSttusInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">항만시설사용현황조회</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
				<table id="fcltyUseSttusInqireList" style="display:none" class="fillHeight"></table>
            </div>

    </div>
</div>