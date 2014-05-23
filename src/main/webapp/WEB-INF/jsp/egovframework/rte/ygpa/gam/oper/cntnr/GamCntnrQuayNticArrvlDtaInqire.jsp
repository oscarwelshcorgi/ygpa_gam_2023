<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : GamCntnrQuayNticArrvlDtaInqire.jsp
 * @Description : 컨테이너부두임대고지도래현황조회 
 * @Modification Information
 *
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.01.14  domh     최초 생성
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
function GamCntnrQuayNticArrvlDtaInqireModule() {}

GamCntnrQuayNticArrvlDtaInqireModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamCntnrQuayNticArrvlDtaInqireModule.prototype.loadComplete = function() {

    // 항만시설사용 테이블 설정
    this.$("#cntnrQuayNticArrvlDtaInqireList").flexigrid({
        module: this,
        url: '<c:url value="/oper/cntnr/gamSelectCntnrQuayNticArrvlDtaInqireList.do" />',
        dataType: 'json',
        colModel : [
					{display:'항코드', name:'prtAtCode',width:40, sortable:false,align:'center'},
					{display:'항코드명', name:'prtAtCodeNm',width:55, sortable:false,align:'center'},
					{display:'관리번호', name:'rentMngNo',width:80, sortable:false,align:'center'},
					{display:'요금종류', name:'chrgeKnd',width:55, sortable:false,align:'center'},
					{display:'요금종류명', name:'chrgeKndNm',width:100, sortable:false,align:'left'},
					{display:'회계년도', name:'accnutYear',width:55, sortable:false,align:'center'},
					{display:'고지번호', name:'nticno',width:55, sortable:false,align:'center'},
					{display:'고지횟수', name:'nticCnt',width:55, sortable:false,align:'center'},
					{display:'고지일자', name:'nticDt',width:80, sortable:false,align:'center'},
					{display:'고지업체', name:'entrpscd',width:80, sortable:false,align:'center'},
					{display:'고지업체명', name:'entrpsNm',width:100, sortable:false,align:'left'},
					{display:'사용료', name:'fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
					{display:'부가세', name:'vat',width:100, sortable:false,align:'right', displayFormat: 'number'},
					{display:'과세구분', name:'vatYn',width:55, sortable:false,align:'center'},
					{display:'고지금액', name:'nticAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
					{display:'사용시작일', name:'nticPdFrom',width:80, sortable:false,align:'center'},
					{display:'사용종료일', name:'nticPdTo',width:80, sortable:false,align:'center'},
					{display:'고지방법', name:'nticMthNm',width:55, sortable:false,align:'center'},
					{display:'신청구분', name:'reqstSeCdNm',width:55, sortable:false,align:'center'},
					{display:'총면적', name:'grAr',width:100, sortable:false,align:'right', displayFormat: 'number'},
					{display:'총사용료', name:'grFee',width:100, sortable:false,align:'right', displayFormat: 'number'},
					{display:'신청일자', name:'reqstDt',width:80, sortable:false,align:'center'},
					{display:'최초신청일자', name:'frstReqstDt',width:80, sortable:false,align:'center'},
					{display:'최초승낙일자', name:'frstPrmisnDt',width:80, sortable:false,align:'center'},
					{display:'승낙일자', name:'prmisnDt',width:80, sortable:false,align:'center'},
					{display:'총사용시작일', name:'grUsagePdFrom',width:80, sortable:false,align:'center'},
					{display:'총사용종료일', name:'grUsagePdTo',width:80, sortable:false,align:'center'},
					{display:'총감면사용료', name:'grRdcxptFee',width:100, sortable:false,align:'right', displayFormat: 'number'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            module.$('#totalResultCnt').val(data.totalCount);
            module.$('#totalNticAmt').val(data.totalNticAmt);
            return data;
        }
    });

    

    this.$("#cntnrQuayNticArrvlDtaInqireList").on('onItemSelected', function(event, module, row, grid, param) {
        
    });

    

    this.$("#cntnrQuayNticArrvlDtaInqireList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#cntnrQuayNticArrvlDtaInqireListTab").tabs("option", {active: 1});

    });


    this.$('#exemptSe').on('change', {module: this}, function(event) {
    	event.data.module.$('#exemptSeNm').val($(this).val());

    });


    //로드될 때 사용기간에 오늘날짜 처리
 	var today = new Date();
 	var month = ((today.getMonth() + 1) >= 10) ? (today.getMonth() + 1) : '0' + (today.getMonth() + 1); 
 	var date = (today.getDate() >= 10) ? today.getDate() : '0' + today.getDate(); 
 	var sToday = today.getFullYear() + '-' + month + '-' + date;
    
    this.$('#sGrUsagePdFrom').val(sToday);
    this.$('#sGrUsagePdTo').val(sToday);
};

GamCntnrQuayNticArrvlDtaInqireModule.prototype.loadRentList = function() {
    this.$("#cntnrQuayNticArrvlDtaInqireListTab").tabs("option", {active: 0});

    var searchOpt=this.makeFormArgs('#gamCntnrQuayNticArrvlDtaInqireSearchForm');
    this.$('#cntnrQuayNticArrvlDtaInqireList').flexOptions({params:searchOpt}).flexReload();
};


GamCntnrQuayNticArrvlDtaInqireModule.prototype.loadDetailForm = function() {
 	var row = this.$('#cntnrQuayNticArrvlDtaInqireList').selectedRows()[0];

	var detailParam = [
	               { name: 'prtAtCode', value: row.prtAtCode},
	               { name: 'mngYear', value: row.mngYear },
	               { name: 'mngNo', value: row.mngNo },
	               { name: 'mngCnt', value: row.mngCnt }
	             ];
	this.makeDivValues('#gamCntnrQuayNticArrvlDtaInqireForm', row); // 결과값을 채운다.

	this.doAction('<c:url value="/oper/cntnr/gamSelectCntnrQuayNticArrvlDtaInqireDetailList.do" />', detailParam, function(module, result) {

		if (result.resultCode == "0") {
			
			module.makeMultiDivValues('#cntnrQuayNticArrvlDtaInqireDetailForm',result.resultList , function(row) {
			} );	// 리스트 값을 채운다
		} else {
			alert(result.resultMsg);
		}
	});
};





/**
 * 정의 된 버튼 클릭 시
 */
 GamCntnrQuayNticArrvlDtaInqireModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':            
            if( this.$('#sGrUsagePdFrom').val() == '' ) {
            	alert("고지도래기간(시작일)을 선택하십시오.");
            	return;
            }
            
            if( this.$('#sGrUsagePdTo').val() == '' ) {
                alert("고지도래기간(종료일)을 선택하십시오.");
                return;
            }
            
            this.loadRentList();

            break;

        
        
        case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            /*
            var opts = {
                'gisAssetsPrtAtCode': this.$('#prtAtCode').val(),
                'gisAssetsCd': this.$('#gisAssetsCd').val(),
                'gisAssetsSubCd': this.$('#gisAssetsSubCd').val()
            };
            */
            var opts;

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;

            
    }
};

GamCntnrQuayNticArrvlDtaInqireModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamCntnrQuayNticArrvlDtaInqireModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamCntnrQuayNticArrvlDtaInqireSearchForm');
    //this.showAlert(searchOpt);
    this.$('#cntnrQuayNticArrvlDtaInqireList').flexOptions({params:searchOpt}).flexReload();
};


GamCntnrQuayNticArrvlDtaInqireModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	if(oldTabId=="tabs1" && this.$('#cntnrQuayNticArrvlDtaInqireList').selectedRowCount()==0) {
		alert('먼저 항목을 선택 하시기 바랍니다.');
		return false;
	}
	return true;
};


GamCntnrQuayNticArrvlDtaInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
	    case 'tabs1':
	        break;
	    case 'tabs2':
	    	this.loadDetailForm();
	        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamCntnrQuayNticArrvlDtaInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
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
         throw 0;
         break;
     }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamCntnrQuayNticArrvlDtaInqireModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamCntnrQuayNticArrvlDtaInqireSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>
                            <td>
                                <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM019 />
                            </td>
                            <th>관리번호</th>
                            <td>
                                <input id="sMngYear" type="text" class="mngYear">
                                <input id="sMngNo" type="text" class="mngNo">
                                <input id="sMngCnt" type="text" class="mngCnt">
                            </td>
                            <th>신청업체</th>
                            <td>
                            	<input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;
                            	<input id="sEntrpsNm" type="text" size="25" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <td rowSpan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                            <th>신청구분</th>
                            <td width="100px">
                                <input id="sReqstSeCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM011 />
                            </td>
                            
                            <!-- 
                            <th>승낙구분</th>
                            <td>
                                <select id="sPrmisnYn">
                                    <option value="" selected="selected">전체</option>
                                    <option value="Y">Y</option>
                                    <option value="N">N</option>
                                </select>
                            </td>
                            -->
                            
                            <th>고지도래기간</th>
                            <td>
                            <input id="sGrUsagePdFrom" type="text" class="emdcal"
                                size="8" value="<c:out value="${grUsagePdFromStr}"/>" readonly> ~ <input id="sGrUsagePdTo" type="text"
                                class="emdcal" size="8" value="<c:out value="${grUsagePdToStr}"/>" readonly>
                            </td>

                            <th>요금종류</th>
                            <td>
                            <input id="sChrgeKnd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM024 />
                           <!--  
                                <input id="sChrgeKnd" type="text" size="6">&nbsp; &nbsp; 
                                <input id="sChrgeKndNm" type="text" size="25" disabled="disabled">&nbsp; &nbsp; 
                                <button id="popupChrgeKndCd" class="popupButton">선택</button>
                            -->
                                
                            </td>
                            
                            
                            <!-- 
                            <th>총면적</th>
                            <td>
                                <input id="sGrAr" type="text" size="5">
                            </td>
                             -->
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="cntnrQuayNticArrvlDtaInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">컨테이너부두임대(고지도래) 목록</a></li>
                <li><a href="#tabs2" class="emdTab">컨테이너부두임대 내역</a></li>
                <!-- <li><a href="#tabs3" class="emdTab">항만시설 상세내역</a></li>
                <li><a href="#tabs4" class="emdTab">첨부파일</a></li> -->
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="cntnrQuayNticArrvlDtaInqireList" style="display:none" class="fillHeight"></table>

                <div class="emdControlPanel">
					<form id="form1">
						<table style="width:100%;" class="summaryPanel">
							<tr>
								<th width="20%" height="23">자료수</th>
								<td><input type="text" size="30" id="totalResultCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="20%" height="23">고지금액</th>
								<td><input type="text" size="50" id="totalNticAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">
                <h2>컨테이너부두사용 내역</h2>
                    <div id="gamCntnrQuayNticArrvlDtaInqireForm">
                        <input type="hidden" id="cmd"/>
                        <input type="hidden" id="quayGroupCd"/>

                        <table class="detailForm" style="width:100%;">
                            <tr>
								<th width="15%" height="23">항코드/담당부서</th>
								<td>
									<span id="prtAtCode"></span>&nbsp;-&nbsp; 
									<span id="prtAtCodeNm"></span>&nbsp;／&nbsp;
									<span id="deptcdNm"></span>
								</td>
								<th width="15%" height="23">납부방법/고지방법</th>
								<td>
									<span id="payMthNm"></span>&nbsp;／&nbsp; 
									<span id="nticMthNm"></span>
								</td>
                            </tr>
                            <tr>
								<th width="15%" height="23">관리번호</th>
								<td>
									<span id="mngYear"></span>&nbsp;-&nbsp;
									<span id="mngNo"></span>&nbsp;-&nbsp;
									<span id="mngCnt"></span>
								</td>
								<th width="15%" height="23">신청업체</th>
								<td>
									<span id="entrpscd"></span>&nbsp;-&nbsp; 
									<span id="entrpsNm"></span>
								</td>
                            </tr>
                            <tr>
								<th width="15%" height="23">최초신청일자</th>
								<td><span id="frstReqstDt"></span></td>
								<th width="15%" height="23">신청일자</th>
								<td><span id="reqstDt"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">승낙여부</th>
								<td><span id="prmisnYn"></span></td>
								<th width="15%" height="23">승낙일자</th>
								<td><span id="prmisnDt"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">총사용기간</th>
								<td>
									<span id="grUsagePdFrom"></span>&nbsp;～&nbsp; 
									<span id="grUsagePdTo"></span>
								</td>
								<th width="15%" height="23">총사용면적</th>
								<td><span class="ygpaNumber" id="grAr"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">총사용료</th>
								<td><span class="ygpaNumber" id="grFee"></span></td>
								<th width="15%" height="23">총감면사용료</th>
								<td><span class="ygpaNumber" id="grRdcxptFee"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">비고</th>
								<td><span id="rm"></span></td>
								<th width="15%" height="23">코멘트</th>
								<td><span id="cmt"></span></td>
                            </tr>
                        </table>
                    </div>
				<h2>컨테이너부두사용 상세 내역</h2>

					<div id="cntnrQuayNticArrvlDtaInqireDetailForm">
                        <table class="detailPanel" style="width:100%;">
                        	<tr>
                        		<th width="15%" height="23">항코드</th>
                                <td>
                                	<span data-column-id="gisAssetsPrtAtCode"></span> 
                                	(<span data-column-id="prtAtCodeNm"></span>)
                                </td>
								<th width="15%" height="23">자산코드</th>
                                <td>
                                	<span data-column-id="gisAssetsCd"></span>-
                                	<span data-column-id="gisAssetsSubCd"></span>
                                </td>
								<th width="15%" height="23">자산명</th>
                                <td><span data-column-id="gisAssetsNm"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">사용시작</th>
                                <td><span data-column-id="usagePdFrom"></span></td>
								<th width="15%" height="23">사용종료</th>
                                <td><span data-column-id="usagePdTo"></span></td>
                                <th width="15%" height="23">사용료</th>
                                <td><span data-column-id="fee" class="ygpaNumber" ></span></td>
                            </tr>
                            <tr>
                            	<th width="15%" height="23">사용면적</th>
                                <td><span data-column-id="usageAr" class="ygpaNumber"></span></td>
								<th width="15%" height="23">적용요율</th>
                                <td>
                                    <span data-column-id="applcTariff"></span>
                                </td>
								<th width="15%" height="23">면제구분</th>
                                <td>
                                    <span data-column-id="exemptSe"></span>
                                    <span data-column-id="exemptSeNm"></span>
                                </td>
                            </tr>
                        </table>
                    </div>
            </div>
        </div>
    </div>
</div>