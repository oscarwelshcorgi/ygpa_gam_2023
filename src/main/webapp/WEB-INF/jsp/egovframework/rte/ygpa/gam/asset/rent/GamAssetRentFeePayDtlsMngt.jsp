<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetRentFeePayDtlsMngt.jsp
  * @Description : 자산임대료납부관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014-02-05  heroin     최초 생성
  *
  * author heroin
  * since 2014-02-05
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetRentFeePayDtlsMngtModule() {}

GamAssetRentFeePayDtlsMngtModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetRentFeePayDtlsMngtModule.prototype.loadComplete = function() {

    // 테이블 설정 //
    this.$("#assetRentFeePayList").flexigrid({
        module: this,
        url: '<c:url value="/asset/rent/gamSelectAssetRentFeePayDtlsMngtList.do" />',
        dataType: 'json',
        colModel : [
					{display:'청코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
					{display:'회계년도', name:'accnutYear',width:64, sortable:false,align:'center'},
					{display:'고지번호', name:'nticno',width:60, sortable:false,align:'center'},
					{display:'요금', name:'chrgeKnd',width:40, sortable:false,align:'center'},
					{display:'요금종류', name:'chrgeKndNm',width:100, sortable:false,align:'center'},
					{display:'업체코드', name:'entrpscd',width:64, sortable:false,align:'center'},
					{display:'업체명', name:'entrpsNm',width:100, sortable:false,align:'center'},
					{display:'고지금액', name:'totalNticAmount',width:100, sortable:false,align:'right', displayFormat:'number'},
					{display:'고지일자', name:'nticDt',width:88, sortable:false,align:'center'},
					{display:'납부기한일자', name:'payTmlmt',width:100, sortable:false,align:'center'},
					{display:'수납구분', name:'rcivSe',width:44, sortable:false,align:'center'},
					{display:'수납일자', name:'rcivDt',width:88, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            module.$('#totalResultCnt').val(data.totalCount);
            module.$('#sumFee').val(data.sumFee);
            module.$('#sumVat').val(data.sumVat);
            module.$('#sumNticAmt').val(data.sumNticAmt);

            return data;
        }
    });

    this.$("#assetRentFeePayList").on('onItemSelected', function(event, module, row, grid, param) {
/*         module.$('#cmd').val('modify');

        module.$('#gamAssetRentFeePayForm :input').val('');

        module.makeFormValues('#gamAssetRentFeePayForm', row);
        module._editData=module.getFormValues('#gamAssetRentFeePayForm', row);
        module._editRow=module.$('#assetRentFeePayList').selectedRowIds()[0]; */
    });

    this.$("#assetRentFeePayList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#assetRentFeePayListTab").tabs("option", {active: 1});    // 탭을 전환 한다.

    });

};

/**
 * 정의 된 버튼 클릭 시
 */
 GamAssetRentFeePayDtlsMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
        	this.loadData();
            break;

        // 팝업을 호출한다.(업체)
        case 'popupEntrpsInfoFeePay':
            var opts;

            this.doExecuteDialog('selectEntrpsInfoFeePayPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;

        case 'btnUpdatePayDtls':	// 납부 현황 새로 고침
            this.doAction('<c:url value="/asset/rent/updateAssetRentFeePayDtlsMngtList.do" />', null, function(module, result) {

                if(result.resultCode=='0') {
                    var searchOpt=module.makeFormArgs('#gamAssetRentFeePayDtlsSearchForm');
                    module.$('#assetRentFeePayList').flexOptions({params:searchOpt}).flexReload();
                }

                alert(result.resultMsg);
            });
        	break;
        case 'btnNticArrrg':
            this.doExecuteDialog('nticArrrgPopup', '연체 일괄 고지', '<c:url value="/asset/rent/showNticArrrgPopup.do"/>', opts);
        	break;

    }
};

GamAssetRentFeePayDtlsMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamAssetRentFeePayDtlsMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamAssetRentFeePayDtlsSearchForm');
    this.$("#assetRentFeePayListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
    this.$('#assetRentFeePayList').flexOptions({params:searchOpt}).flexReload();
};

GamAssetRentFeePayDtlsMngtModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	if(newTabId=='tabs2') {
		if(this.$('#assetRentFeePayList').selectedRowCount()!=1) {
			alert('상세 내역을 조회 할 납부 항목을 선택 하세요.');
			return false;
		}
	}
	return true;
}


GamAssetRentFeePayDtlsMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
    	var row = this.$('#assetRentFeePayList').selectedRows()[0];

    	this.makeDivValues('#masterPayInfo', row); // 결과값을 채운다.
		var nticDetail = [
		               { name: 'prtAtCode', value: row.prtAtCode},
		               { name: 'mngYear', value: row.mngYear },
		               { name: 'mngNo', value: row.mngNo },
		               { name: 'mngCnt', value: row.mngCnt },
		               { name: 'nticCnt', value: row.nticCnt }
		             ];
   	 	this.doAction('<c:url value="/asset/rent/selectAssetRentFeePayDtlsMngtDetail.do" />', nticDetail, function(module, result) {
			if (result.resultCode == "0") {
				if(module.$('#detailPayInfo').find('table').length<result.resultList.length) {	// 테이블이 갯수가 작으면 늘린다.
					for(var i=0; i<module.$('#detailPayInfo').find('table').length; i<result.resultList.length; i++) {
						var tab=module.$('#detailPayInfo').find('table :eq(0)').clone();
						module.$('#detailPayInfo').append(tab);
					}
				}
				else if(module.$('#detailPayInfo').find('table').length>result.resultList.length) {	// 테이블이 크면 줄인다.
					while(module.$('#detailPayInfo').find('table').length>result.resultList.length) {
						module.$("#detailPayInfo").remove(module.$('#detailPayInfo:last-child'));
					}
				}
				for(var i=0; i<result.resultList.length; i++) {
					module.makeDivValues('#detailPayInfo:eq('+i+')',
							result.resultList[i]); // 결과값을 채운다.
				}
			} else {
				alert(result.resultMsg);
			}
		});
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamAssetRentFeePayDtlsMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
    case 'selectEntrpsInfoFeePayPopup':
        if (msg != 'cancel') {
            this.$('#sEntrpscd').val(value.entrpscd);
            this.$('#sEntrpsNm').val(value.entrpsNm);
        } else {
            alert('취소 되었습니다');
        }
        break;
    case 'nticArrrgPopup':
    	break;
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');
         throw 0;
         break;
     }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetRentFeePayDtlsMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetRentFeePayDtlsSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th style="width: 70px">항코드</th>
                            <td style="width: 170px"><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
                            <th style="width: 70px">업체명</th>
                            <td colspan="3"><input id="sEntrpscd" type="text" size="5"><input id="sEntrpsNm" type="text" size="20" readonly> <button id="popupEntrpsInfo">업체</button></td>
                            <td rowspan="2"><button id="searchBtn" *class="submit" class="buttonSearch">조회</button></td>
                        </tr>

                        <tr>
                            <th>관리번호</th>
                            <td><input id="sMngYear" type="text" size="3">-<input id="sMngNo" type="text" size="2">-<input id="sMngCnt" type="text" size="1"></td>
                            <th>고지일자</th>
                            <td><input id="sNticPdFrom" type="text" class="emdcal"size="8"> ~ <input id="sNticPdTo" type="text"class="emdcal" size="8"></td>
                            <th>요금종류</th>
                            <td><input id="sChrgeKnd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM024" /></td>
                        </tr>

                        <!--
                        <tr>
                            <th>항코드</th>
                            <td>
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">선택</option>

                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.code }">${prtAtCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>고지방법</th>
                            <td width="100px">
                                <select id="sNticMth">
                                    <option value="" selected="selected">선택</option>
                                    <c:forEach  items="${nticMthCdList}" var="nticMthCdItem">
                                        <option value="${nticMthCdItem.code }">${nticMthCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>부두</th>
                            <td>
                                <select id="sQuayCd">
                                    <option value="" selected="selected">선택</option>
                                    <c:forEach  items="${quayCdList}" var="quayCdItem">
                                        <option value="${quayCdItem.code }">${quayCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>수납구분</th>
                            <td>
                                <select id="sRcivSe">
                                    <option value="" selected="selected">선택</option>
                                    <c:forEach  items="${rcivSeCdList}" var="rcivSeCdItem">
                                        <option value="${rcivSeCdItem.code }">${rcivSeCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td rowSpan="2"><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                        <tr>
                            <th>조회업체</th>
                            <td>
                                <input id="sEntrpscd" type="text" size="3"><input id="sEntrpsNm" type="text" size="6" readonly> <button id="popupEntrpsInfoFeePay">업체</button>
                            </td>
                            <th>고지일자</th>
                            <td colspan="4">
                            <input id="sNticDtFrom" type="text" class="emdcal"
                                size="8"> ~ <input id="sNticDtTo" type="text"
                                class="emdcal" size="8">
                            </td>
                        </tr>
                         -->
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentFeePayListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
            <ul>
                <li><a href="#tabs1" class="emdTab">자산임대료납부 목록</a></li>
                <li><a href="#tabs2" class="emdTab">자산임대료납부 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <!-- <div style="width: 100%; height: 100%; overflow:auto">  -->
                        <table id="assetRentFeePayList" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel">
					<button id="btnUpdatePayDtls">납부확인</button>
					<button id="btnNticArrrg">연체고지</button>
                </div>
                <!-- </div>  -->

                <!--
                <div class="emdControlPanel">
                    <table style="width:100%;" >
                        <tr>
                            <td>
                               <form id="form1">
                                    합계
                                    자료수 <input id="totalResultCnt" size="5" readonly>
                                    사용료 <input id="sumFee" type="text" size="14" readonly>
                                    부가세 <input id="sumVat" type="text" size="14" readonly>
                                    고지액 <input id="sumNticAmt" type="text" size="14" readonly>
                               </form>
                            </td>
                            <td>
                                <button id="mapInfoBtn">맵정보</button>
                            </td>
                        </tr>
                    </table>
                </div>
                 -->
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">
				<div id="masterPayInfo" class="emdPanel">
                    <table class="detailPanel">
                        <tr>
                        	<th><span class="label">청코드</span></th>
                            <td style="width: 180px"><span data-column-id="prtAtCode"></span></td>
                            <th><span class="label">회계년도</span></th>
                            <td style="width: 180px"><span data-column-id="accnutYear"></span></td>
                            <th><span class="label">고지번호</span></th>
                            <td style="width: 180px"><span data-column-id="nticno"></span></td>
                        </tr>
                        <tr>
                        	<th><span class="label">업체</span></th>
                            <td style="width: 180px"><span data-column-id="entrpsNm"></span> (<span data-column-id="entrpscd"></span>)</td>
                        	<th><span class="label">요금</span></th>
                            <td style="width: 180px" colspan="3"><span data-column-id="chrgeKndNm"></span> (<span data-column-id="chrgeKnd"></span>)</td>
                        </tr>
                        <tr>
                        	<th><span class="label">고지금액</span></th>
                            <td style="width: 180px"><span data-column-id="totalNticAmount" class="ygpaNumber"></span></td>
                            <th><span class="label">고지일자</span></th>
                            <td style="width: 180px"><span data-column-id="nticDt"></span></td>
                            <th><span class="label">납부기한일자</span></th>
                            <td style="width: 180px"><span data-column-id="payTmlmt"></span></td>
                        </tr>
                        <tr>
                        	<th><span class="label">수납구분</span></th>
                            <td style="width: 180px"><span data-column-id="rcivSe" class="ygpaYnSelect" data-y-prompt="수납" data-n-prompt="미수납"></span></td>
                            <th><span class="label">수납일자</span></th>
                            <td style="width: 180px" colspan="3"><span data-column-id="rcivDt"></span></td>
                        </tr>
                    </table>
            	</div>
                <div id="detailPayInfo" class="emdPanel">
                    <table class="detailPanel">
                        <tr>
                        	<th><span class="label">청코드</span></th>
                            <td style="width: 180px"><span data-column-id="prtAtCode"></span></td>
                            <th><span class="label">회계년도</span></th>
                            <td style="width: 180px"><span data-column-id="accnutYear"></span></td>
                            <th><span class="label">고지번호</span></th>
                            <td style="width: 180px"><span data-column-id="nticno"></span></td>
                        </tr>
                        <tr>
                        	<th><span class="label">업체</span></th>
                            <td style="width: 180px"><span data-column-id="entrpsNm"></span> (<span data-column-id="entrpscd"></span>)</td>
                        	<th><span class="label">요금</span></th>
                            <td style="width: 180px" colspan="3"><span data-column-id="chrgeKndNm"></span> (<span data-column-id="chrgeKnd"></span>)</td>
                        </tr>
                        <tr>
                        	<th><span class="label">고지금액</span></th>
                            <td style="width: 180px"><span data-column-id="totalNticAmount" class="ygpaNumber"></span></td>
                            <th><span class="label">고지일자</span></th>
                            <td style="width: 180px"><span data-column-id="nticDt"></span></td>
                            <th><span class="label">납부기한일자</span></th>
                            <td style="width: 180px"><span data-column-id="payTmlmt"></span></td>
                        </tr>
                        <tr>
                        	<th><span class="label">수납구분</span></th>
                            <td style="width: 180px"><span data-column-id="rcivSe" class="ygpaYnSelect" data-y-prompt="수납" data-n-prompt="미수납"></span></td>
                            <th><span class="label">수납일자</span></th>
                            <td style="width: 180px"><span data-column-id="rcivDt"></span></td>
                        </tr>
                    </table>
            </div>

        </div>
    </div>
</div>