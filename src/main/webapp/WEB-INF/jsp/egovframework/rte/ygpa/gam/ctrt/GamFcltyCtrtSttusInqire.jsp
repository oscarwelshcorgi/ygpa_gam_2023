<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltyCtrtSttusInqire.jsp
 * @Description : 시설물계약이력조회
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.28  HNJ          최초 생성
 *
 * author HNJ
 * since 2014.10.28
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<%-- <validator:javascript formName="gamFcltyCtrtSttusInqireSearchForm" method="validateGamFcltyCtrtSttusInqire" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="form1" method="validateGamFcltyCtrtSttusInqireDetail" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" /> --%>

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyCtrtSttusInqireModule() {}

GamFcltyCtrtSttusInqireModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyCtrtSttusInqireModule.prototype.loadComplete = function() {


 	// 계약이력 테이블 설정
    this.$("#fcltyCtrtSttusInqireList").flexigrid({
        module: this,
        url: '/ctrt/gamSelectFcltyCtrtSttusInqireList.do',
        dataType: 'json',
        colModel : [
                    {display:'거래관계', 				name:'dealRelate',			width:60, 		sortable:false,		align:'center'},
                    {display:'업종', 					name:'induty',				width:60, 		sortable:false,		align:'right'},
                    {display:'기업명', 				name:'entrpsNm',			width:150, 		sortable:false,		align:'left'},
                    {display:'주요품목', 				name:'stplPrdlst',			width:100, 		sortable:false,		align:'center'},
                    {display:'이전년도거래금액(원)', 		name:'prevCtrtAmt',			width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'거래금액(원)', 			name:'currCtrtAmt',			width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'사업자번호', 			name:'bsnmNo',				width:120, 		sortable:false,		align:'center'},
                    {display:'대표자', 				name:'rprsntv',				width:100, 		sortable:false,		align:'center'},
                    {display:'담당자', 				name:'charger',				width:100, 		sortable:false,		align:'center'},
                    {display:'직위', 					name:'chargerOfcPos',		width:80, 		sortable:false,		align:'center'},
                    {display:'전화', 					name:'tlphonNo',			width:100, 		sortable:false,		align:'center'},
                    {display:'휴대폰', 				name:'chargerMoblphonNo',	width:100, 		sortable:false,		align:'center'},
                    {display:'팩스', 					name:'faxNo',				width:100, 		sortable:false,		align:'center'},
                    {display:'이메일', 				name:'chargerEmail',		width:150, 		sortable:false,		align:'center'},
                    {display:'사업장주소', 			name:'roadnmAdres',			width:350, 		sortable:false,		align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
			//자료수 입력
            module.$('#totalCount').val($.number(data.totalCount));
            module.$('#sumPrevCtrtAmt').val($.number(data.sumPrevCtrtAmt));
            module.$('#sumCurrCtrtAmt').val($.number(data.sumCurrCtrtAmt));

            return data;
        }
    });
 	
 	// 연도 셀렉트 옵션에 뿌리기 + 기본값 설정
	this.addSelectOption();
 	
	// 셀렉트박스의 값이 변경시 검색을 위해 이전연도를 히든에 재설정
    this.$('#sCtrtYr').on('change', {module: this}, function(e) {
    	var prevCtrtYr = e.data.module.$('#sCtrtYr > option:selected').val() - 1;
    	e.data.module.$('#sPrevCtrtYr').val(prevCtrtYr);
	});

};


GamFcltyCtrtSttusInqireModule.prototype.addSelectOption = function() {
	
	var toDate = new Date();
	var toYear = toDate.getFullYear();
	
	var option = "";
	for(var i = 2000;i<=toYear;i++){
		option = option + "<option value='" + i + "'>" + i + "년</option>";
	}
	this.$("#sCtrtYr").append(option);
	
	// 셀렉트박스 기본값 설정 
	this.$('#sCtrtYr').val(toYear);
	// 검색을 위해 이전연도를 히든에 설정
	var serchPreYr = toYear - 1;
	this.$('#sPrevCtrtYr').val(serchPreYr);

};


/**
 * 정의 된 버튼 클릭 시
 */
 GamFcltyCtrtSttusInqireModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 시설물계약이력 엑셀 다운로드
		case 'btnFcltyCtrtSttusInqireListExcelDownload':
			this.$('#fcltyCtrtSttusInqireList').flexExcelDown('/ctrt/gamSelectFcltyCtrtSttusInqireExcel.do');
		break;
    }
};


GamFcltyCtrtSttusInqireModule.prototype.onSubmit = function() {
    this.loadData();
};

GamFcltyCtrtSttusInqireModule.prototype.loadData = function() {
    this.$("#fcltyCtrtSttusInqireListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamFcltyCtrtSttusInqireSearchForm');

    this.$('#fcltyCtrtSttusInqireList').flexOptions({params:searchOpt}).flexReload();

};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamFcltyCtrtSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value) {

    switch (popupId) {
     case 'selectEntrpsInfoPopup':
         if (msg != 'cancel') {
             this.$('#sRegistEntrpsCd').val(value.entrpscd);
             this.$('#sRegistEntrpsNm').val(value.entrpsNm);
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
var module_instance = new GamFcltyCtrtSttusInqireModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamFcltyCtrtSttusInqireSearchForm">
            
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>계약구분</th>
                            <td>
                                <select id="sCtrtSe">
                                    <option value="" selected="selected">전체</option>
                                    <option value="1">자체</option>
                                    <option value="2">조달</option>
                                </select>
                            </td>
                            <th width="10%">등록업체</th>
                            <td><input id="sEntrpsNm" type="text" size="70"></td>
                            <td rowspan="2">
								<button class="buttonSearch">조회</button>
                            </td>
                        </tr>
                        <tr>
                        	<th width="10%">계약연도</th>
                            <td>
                            	<select id="sCtrtYr"></select>
                                <input type="hidden" id="sPrevCtrtYr">
                            </td>
                        	<th width="10%">계약명</th>
                            <td>
                            	<input id="sCtrtNm" type="text" size="70">
                         	</td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="fcltyCtrtSttusInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">계약이력정보</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
                <table id="fcltyCtrtSttusInqireList" style="display:none" class="fillHeight"></table>
                <div id="fcltyCtrtSttusInqireListSum" class="emdControlPanel">
					<form id="fcltyCtrtSttusInqireListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="17%" height="25">자료수</th>
								<td><input type="text" size="13" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="17%" height="25">이전연도거래금액</th>
								<td><input type="text" size="24" id="sumPrevCtrtAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="17%" height="25">거래금액</th>
								<td><input type="text" size="24" id="sumCurrCtrtAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button data-role="printPage" data-search-option="gamFcltyCtrtSttusInqireSearchForm" data-url='/ctrt/gamSelectFcltyCtrtSttusInqirePrint.do'>계약이력인쇄</button> 
	                            	<button id="btnFcltyCtrtSttusInqireListExcelDownload">엑셀</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

        </div>
    </div>
</div>