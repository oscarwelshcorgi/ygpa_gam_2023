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
  *  2014.04.14  lsl          선석별 사용현황 조회처리 -- 기존 파일은 _처리 백업
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

GamFcltyUseSttusInqireModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyUseSttusInqireModule.prototype.loadComplete = function() {

    // 테이블 설정 //       
    this.$("#fcltyUseSttusInqireList").flexigrid({
        module: this,
        url: '<c:url value="/port_mis/gamSelectFcltyUseSttusInqireList.do" />',
        dataType: 'json',
        colModel : [      
					{display:'항코드', name:'prtAtCode',width:40, sortable:true,align:'center'},         
					{display:'항코드명', name:'prtKorNm',width:60, sortable:true,align:'center'},  
					{display:'선석코드', name:'facCode',width:60, sortable:true,align:'center'},                
					{display:'선석명', name:'facKorNm',width:180, sortable:true,align:'left'},         
					{display:'요금종류', name:'feeTp',width:60, sortable:true,align:'center'},  
					{display:'요금종류명', name:'feeTpKorNm',width:100, sortable:true,align:'left'},  
					{display:'면제금액', name:'exmpAmnt',width:100, sortable:true,align:'right' , displayFormat: 'number'},
					{display:'할인금액', name:'dcAmnt',width:100, sortable:true,align:'right' , displayFormat: 'number'},               
					{display:'고지금액', name:'billAmnt',width:110, sortable:true,align:'right' , displayFormat: 'number'}
                    ],
        showTableToggleBtn: false,
        height: '350',
        preProcess: function(module,data) {
            module.$('#totalResultCnt').val(data.dpTotCnt);
            module.$('#totalExmpAmnt').val(data.sumExmpAmnt);
            module.$('#totalDcAmnt').val(data.sumDcAmnt);
            module.$('#totalBillAmnt').val(data.sumBillAmnt);
      
            return data;
        }
    });

 	// 오늘로 텍스트박스 날짜 정의
	var today = new Date();
	
	var serchYr = today.getFullYear();
	var serchMn = today.getMonth() + 1;
	var serchDay = today.getDate();
	
	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}

	if(serchDay < 10){
		serchDay = "0" + serchDay;
	}
	
	var displayDate = serchYr + "-" + serchMn + "-" + serchDay;

	this.$("#sGrUsagePdFrom").val(displayDate);
	this.$("#sGrUsagePdTo").val(displayDate);
	
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamFcltyUseSttusInqireModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
        	
        	if( this.$('#sGrUsagePdFrom').val() == '' ) {
                alert("사용기간 시작일을 선택하십시오.");
                return;
            }
        	
            var searchOpt=this.makeFormArgs('#gamFcltyUseSttusInqireSearchForm');
            this.$('#fcltyUseSttusInqireList').flexOptions({params:searchOpt}).flexReload();

            break;

        // 팝업을 호출한다.(선석)     
        case 'popupFacilCd': 
        	var opts = this.makeFormArgs('#gamFcltyUseSttusInqireSearchForm');
            
            this.doExecuteDialog('selectFacilInfoPopup', '선석 선택', '<c:url value="/popup/showFacilInfo.do"/>', opts);
            break;
            
         // 팝업을 호출한다.(요금종류)     
        case 'popupChrgeKndCd': 
            //this.doExecuteDialog('selectEntrpsInfoFeePayPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
			var opts;
		
			this.doExecuteDialog('selectChrgeKndCd', '요금 선택',
					'<c:url value="/popup/showPayCd.do"/>', opts);
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
    case 'selectFacilInfoPopup' :
        if (msg != 'cancel') {
            this.$('#fac_code').val(value.facCode);
            this.$('#fac_sub_code').val(value.facSubCode);
            this.$('#fac_sub_kor_nm').val(value.facKorNm);
        } else {
            alert('취소 되었습니다');
        }
        break;
    case 'selectChrgeKndCd':
		if (msg != 'cancel') {
			this.$('#prtAtCode').val(value.prtAtCode);
			this.$('#chrgeKndCd').val(value.feeTp);
			this.$('#chrgeKndNm').val(value.feeTpKorNm);
		} else {
			alert('취소 되었습니다');
		}
		break;
   	default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');
         
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
                                <select id="prtAtCode">
                                    <option value="" selected="selected">선택</option>

                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.code }">${prtAtCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>사용기간</th>
                            <td>
                            <input id="sGrUsagePdFrom" type="text" class="emdcal"
                                size="10"> ~ <input id="sGrUsagePdTo" type="text"
                                class="emdcal" size="10">
                            </td>
                            <td rowspan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
                        </tr>
						<tr>
							<th style="width: 70px">선석</th>
                            <td>
                                <input id="fac_code" type="text" size="3">&nbsp; &nbsp; 
                                <input id="fac_sub_code" type="text" size="2">&nbsp; &nbsp; 
                                <input id="fac_sub_kor_nm" type="text" size="25" disabled="disabled">&nbsp; &nbsp; 
                                <button id="popupFacilCd" class="popupButton">선택</button>
                            </td>
                            <th>요금종류</th>
                            <td>
                                <input id="chrgeKndCd" type="text" size="6">&nbsp; &nbsp; 
                                <input id="chrgeKndNm" type="text" size="25" disabled="disabled">&nbsp; &nbsp; 
                                <button id="popupChrgeKndCd" class="popupButton">선택</button>
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

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
				<table id="fcltyUseSttusInqireList" style="display:none"></table>
				
				<div class="emdControlPanel">
					<form id="form1">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="12%" height="20">자료수</th>
								<td><input type="text" size="7" id="totalResultCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">면제금액합계</th>
								<td><input type="text" size="19" id="totalExmpAmnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">할인금액합계</th>
								<td><input type="text" size="19" id="totalDcAmnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">고지금액합계</th>
								<td><input type="text" size="19" id="totalBillAmnt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
                </div>
            </div>
		</div>
    </div>
</div>