<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamMarineCenterRentNticMngt.jsp
  * @Description : 마린센터임대료납부관리
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
function GamMarineCenterRentNticMngtModule() {}

GamMarineCenterRentNticMngtModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamMarineCenterRentNticMngtModule.prototype.loadComplete = function() {

    // 테이블 설정 //
    this.$("#marineCenterRentNticList").flexigrid({
        module: this,
        url: '<c:url value="/oper/center/gamSelectMarineCenterRentFeePayDtlsMngtList.do" />',
        dataType: 'json',
        colModel : [
					{display:'항코드', name:'prtAtCode',width:40, sortable:false,align:'center'},
                    {display:'항코드명', name:'prtAtCodeNm',width:55, sortable:false,align:'center'},
					{display:'요금종류', name:'chrgeKnd',width:55, sortable:false,align:'center'},
					{display:'요금종류명', name:'chrgeKndNm',width:100, sortable:false,align:'left'},
					{display:'회계년도', name:'accnutYear',width:55, sortable:false,align:'center'},
					{display:'고지번호', name:'nticno',width:55, sortable:false,align:'center'},
					{display:'고지업체', name:'entrpscd',width:60, sortable:false,align:'center'},
					{display:'고지업체명', name:'entrpsNm',width:140, sortable:false,align:'left'},
					{display:'고지금액', name:'totalNticAmount',width:100, sortable:false,align:'right', displayFormat: 'number'},
					{display:'고지일자', name:'nticDt',width:80, sortable:false,align:'center'},
					{display:'납부기한', name:'payTmlmt',width:80, sortable:false,align:'center'},
                    {display:'사용료', name:'fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'부가세', name:'vat',width:100, sortable:false,align:'right', displayFormat: 'number'},
					{display:'수납구분', name:'rcivSe',width:55, sortable:false,align:'center'},
					{display:'수납일자', name:'rcivDt',width:80, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            module.$('#sumCnt').val(data.sumCnt);
            module.$('#sumNhtIsueAmt').val(data.sumNhtIsueAmt);
            module.$('#sumVat').val(data.sumVat);
            module.$('#sumPayAmt').val(data.sumPayAmt);
        	module.makeDivValues('#marineCenterRentNticListSum', data);
            return data;
        }
    });

    this.$("#marineCenterRentNticList").on('onItemSelected', function(event, module, row, grid, param) {
    });

    this.$("#marineCenterRentNticList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#marineCenterRentNticListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
    });

    this.$('#sNticDtFrom').val(EMD.util.getDate(EMD.util.addMonths(-1)));
    this.$('#sNticDtTo').val(EMD.util.getDate());

    this.$('#arrrgDetail :input').on('change keyup', {module: this}, function(event) {
    	event.data.module.calculateArrrgFee();
    });

    // console.log('loadCompleted');
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamMarineCenterRentNticMngtModule.prototype.onButtonClick = function(buttonId) {
     var opts=null;

    switch(buttonId) {
        // 조회
        case 'searchBtn':
        	this.loadData();
        	// console.log("debug");
            break;

        // 팝업을 호출한다.(업체)
        //case 'popupEntrpsInfoFeePay':
        case 'popupEntrpsInfo':
            var searchOpt=this.makeFormArgs('#gamMarineCenterRentFeePayDtlsSearchForm');
            this.doExecuteDialog('selectEntrpsInfoFeePayPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts, searchOpt);
            break;

        case 'btnUpdatePayDtls':	// 납부 현황 새로 고침
            this.doAction('<c:url value="/oper/center/updateMarineCenterRentFeePayDtlsMngtList.do" />', null, function(module, result) {

                if(result.resultCode=='0') {
                    var searchOpt=module.makeFormArgs('#gamMarineCenterRentFeePayDtlsSearchForm');
                    module.$('#marineCenterRentNticList').flexOptions({params:searchOpt}).flexReload();
                }

                alert(result.resultMsg);
            });
        	break;
        case 'btnNticArrrg':
            this.doExecuteDialog('nticArrrgPopup', '연체 일괄 고지', '<c:url value="/oper/center/showNticArrrgPopup.do"/>', opts);
        	break;
        case 'btnNticArrrgSingle':
			this.nticArrrgSingle();
        	break;
    }
};

GamMarineCenterRentNticMngtModule.prototype.nticArrrgSingle = function() {
	var rows = this.$('#marineCenterRentNticList').selectedRows();
	if(rows.length==0) {
		alert('연체 고지 할 항목을 선택 하세요.');
		return;
	}

	var row=rows[0];

	var arrrgRate=this.$('#arrrgRate').val();
	var applyPayDates=this.$('#applyPayDates').val();
	var arrrgAmt=this.$('#arrrgAmt').number(true).val();

	var nticDetail = [
	               { name: 'prtAtCode', value: row.prtAtCode},
	               { name: 'mngYear', value: row.mngYear },
	               { name: 'mngNo', value: row.mngNo },
	               { name: 'mngCnt', value: row.mngCnt },
	               { name: 'nticCnt', value: row.nticCnt },
	               { name: 'arrrgTariff', value: arrrgRate },
	               { name: 'arrrgPayDates', value: applyPayDates },
	               { name: 'arrrgAmt', value: arrrgAmt },
	             ];
	 	this.doAction('<c:url value="/oper/center/insertNticArrrg.do" />', nticDetail, function(module, result) {
		if (result.resultCode == "0") {
		} else {
		}
		alert(result.resultMsg);
	});
};

GamMarineCenterRentNticMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamMarineCenterRentNticMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamMarineCenterRentFeePayDtlsSearchForm');
    this.$("#marineCenterRentNticListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
    this.$('#marineCenterRentNticList').flexOptions({params:searchOpt}).flexReload();
};

GamMarineCenterRentNticMngtModule.prototype.loadDetailPage = function() {
	var row = this.$('#marineCenterRentNticList').selectedRows()[0];

	var nticDetail = [
	               { name: 'prtAtCode', value: row.prtAtCode},
	               { name: 'mngYear', value: row.mngYear },
	               { name: 'mngNo', value: row.mngNo },
	               { name: 'mngCnt', value: row.mngCnt },
	               { name: 'nticCnt', value: row.nticCnt }
	             ];
	 	this.doAction('<c:url value="/oper/center/selecMarineCenterRentFeePayDtlsMngtDetail.do" />', nticDetail, function(module, result) {
		if (result.resultCode == "0") {
			module.makeDivValues('#masterPayInfo', result.resultMaster); // 결과값을 채운다.
			module.makeMultiDivValues('#detailPayInfo',result.resultList, function(row) {
				if(row.currNticCnt=="Y") $(this).addClass("detailRowSelected");
				else {
					if($(this).hasClass("detailRowSelected")) $(this).removeClass("detailRowSelected");
				}
			} );	// 리스트 값을 채운다
			module.makeDivValues('#summaryPayInfo', result.resultSummary); // 결과값을 채운다.
			if(result.resultArrrg==undefined) {
				module.$('#arrrgDetail').hide();
			}
			else {
				module.nticAmt=result.resultArrrg.nticAmt*1;
				module.$('#arrrgDetail').show();
				module.makeDivValues('#arrrgDetail', result.resultArrrg); // 결과값을 채운다.
				module.makeFormValues('#arrrgDetailVO', result.resultArrrg); // 결과값을 폼에 채운다.
			}
		} else {
			alert(result.resultMsg);
		}
	});
		// tabs3 -- 연체목록을 채운다 
		
		var dlyList = [
		               { name: 'prtAtCode', value: row.prtAtCode},
		               { name: 'chrgeKnd', value: row.chrgeKnd },
		               { name: 'accnutYear', value: row.accnutYear },
		               { name: 'nticno', value: row.nticno }
		             ];
		this.doAction('<c:url value="/oper/center/selectMarineCenterRentNticMngtDlyList.do" />', dlyList, function(module, result) {
			if (result.resultCode == "0") {
				
				module.makeMultiDivValues('#marineCenterRentRentNticMngtListForm',result.resultList , function(row) {
				} );	// 리스트 값을 채운다

				module.makeDivValues('#marineCenterRentNticMngtSum', result.resultSummary); // 결과값을 채운다.

				
			} else {
				alert(result.resultMsg);
			}
		});
};

GamMarineCenterRentNticMngtModule.prototype.calculateArrrgFee = function() {
	// 연체료 계산
	// console.log('arrrg calc');

	var arrrgRate=this.$('#arrrgRate').val()/100;
	var applyPayDates=this.$('#applyPayDates').val()*1;
//	var arrrgAmt=$.number(this.$('#arrrgAmt').val(), false)*1;
	var arrrgAmtResult=0;

	if(applyPayDates<=0) {
		this.$('#arrrgAmt').val('-');
		return;
	}
	if(arrrgRate==0) {
		arrrgRate=0.12;
		this.$('#arrrgRate').val('12');
	}
	arrrgAmtResult = this.nticAmt+Math.round(this.nticAmt*applyPayDates/365*arrrgRate/10)*10;
	this.$('#arrrgAmt').val($.number(arrrgAmtResult));
};

GamMarineCenterRentNticMngtModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	if(newTabId=='tabs2' || newTabId=='tabs3') {
		if(this.$('#marineCenterRentNticList').selectedRowCount()!=1) {
			alert('상세 내역을 조회 할 납부 항목을 선택 하세요.');
			return false;
		}
	}
	return true;
};


GamMarineCenterRentNticMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
		this.loadDetailPage();
        break;
	case 'tabs3':
		this.$("#marineCenterRentNticMngtListTab").tabs("option", {active: 2});    // 탭을 전환 한다.
	    break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamMarineCenterRentNticMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
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
var module_instance = new GamMarineCenterRentNticMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamMarineCenterRentFeePayDtlsSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th style="width: 70px">항코드</th>
                            <td style="width: 170px"><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
                            <th style="width: 70px">고지업체</th>
                            <td>
                                <input type="text" size="6" id="sEntrpscd" maxlength="10"/>
                                <input type="text" size="15" id="sEntrpsNm" disabled/>
                                <button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <th style="width: 70px">납부구분</th>
                            <td><input data-column-id="rcivSe" class="ygpaCmmnCd" data-code-id="GAM025" data-default-prompt="전체"></td>
                            <td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
                        </tr>

                        <tr>
                            <th>관리번호</th>
                            <td>
                            	<input id="sMngYear" type="text" class="mngYear">
                                <input id="sMngNo" type="text" class="mngNo">
                                <input id="sMngCnt" type="text" class="mngCnt">
                            </td>
                            <th>고지일자</th>
                            <td><input id="sNticDtFrom" data-column-id="nticPdFrom" type="text" class="emdcal"size="8"> ~ <input id="sNticDtTo" data-column-id="nticPdTo" type="text"class="emdcal" size="8"></td>
                            <th>요금종류</th>
                            <td><input id="sChrgeKnd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM024" /></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="marineCenterRentNticListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
            <ul>
                <li><a href="#tabs1" class="emdTab">마린센터임대료납부현황 목록</a></li>
                <li><a href="#tabs2" class="emdTab">마린센터임대료납부현황 상세</a></li>
                <li><a href="#tabs3" class="emdTab">마린센터임대료연체현황 목록</a></li> 
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="marineCenterRentNticList" style="display:none" class="fillHeight"></table>
                <div id="marineCenterRentNticListSum" class="emdControlPanel">
					<form id="form1">
   	               	<table style="width:100%;" class="summaryPanel">
                		<tr>
                			<th width="10%" >자료수</th>
							<td><input type="text" size="6" id="sumCnt" class="ygpaNumber" disabled="disabled" /></td>
                			<th width="10%" >총고지금액</th>
							<td><input type="text" size="16" id="sumNhtIsueAmt" class="ygpaNumber" disabled="disabled" /></td>
                			<th width="10%" >부가세</th>
							<td><input type="text" size="16" id="sumVat" class="ygpaNumber" disabled="disabled" /></td>
                			<th width="10%" >총납부금액</th>
							<td><input type="text" size="16" id="sumPayAmt" class="ygpaNumber" disabled="disabled" /></td>
							<td><button id="btnUpdatePayDtls" data-icon="ui-icon-circle-check">납부확인</button></td>
							<!-- <td><button id="btnNticArrrg" data-icon="ui-icon-clock">연체일괄고지</button></td> -->
                		</tr>
                	</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">

                <div class="emdPanel">
					<table class="searchPanel">
					<tbody>
						<tr>
							<th>고지내역</th>
						</tr>
					</tbody>
					</table>
                	<!-- <h2>고지 내역</h2> -->
                    <table id="masterPayInfo" class="detailPanel">
                        <tr>
                        	<th style="width: 100px"><span class="label">항구분</span></th>
                            <td style="width: 180px"><span class="ygpaCmmnCd" data-code-id="GAM019" data-column-id="prtAtCode"></span> (<span data-column-id="prtAtCode"></span>)</td>
                            <th style="width: 100px"><span class="label">회계년도</span></th>
                            <td style="width: 80px"><span data-column-id="accnutYear"></span></td>
                            <th style="width: 60px"><span class="label">고지횟수</span></th>
                            <td style="width: 80px"><span data-column-id="nticCnt"></span></td>
                            <th style="width: 100px"><span class="label">고지번호</span></th>
                            <td style="width: 160px"><span data-column-id="nticno"></span></td>
                        </tr>
                        <tr>
                        	<th><span class="label">요금종류</span></th>
                            <td><span data-column-id="chrgeKndNm"></span> (<span data-column-id="chrgeKnd"></span>)</td>
                        	<th><span class="label">고지업체</span></th>
                            <td colspan="3"><span data-column-id="entrpsNm"></span> (<span data-column-id="entrpscd"></span>)</td>
                        	<th><span class="label">고지금액</span></th>
                            <td style="text-align:right;"><span data-column-id="nticAmt" class="ygpaNumber"></span> 원</td>
                        </tr>
                        <tr>
                            <th><span class="label">고지일자</span></th>
                            <td><span data-column-id="nticDt"></span></td>
                            <th><span class="label">납부기한일자</span></th>
                            <td colspan="5"><span data-column-id="payTmlmt"></span></td>
                        </tr>
                        <tr>
                        	<th><span class="label">수납구분</span></th>
                            <td><span data-column-id="rcivSe" class="ygpaCmmnCd" data-code-id="GAM025"></span></td>
                            <th><span class="label">수납일자</span></th>
                            <td colspan="5"><span data-column-id="rcivDt"></span></td>
                        </tr>
                    </table>
                    <div id="arrrgDetail">
					<table class="searchPanel">
					<tbody>
						<tr>
							<th width="90%">연체내역</th>
							<th style="text-align:right">
								<button id="btnNticArrrgSingle" data-icon="ui-icon-clock">연체고지</button>
							</th>
						</tr>
					</tbody>
					</table>
                	<!-- <h2>연체 내역</h2> -->
                    <form id="arrrgDetailVO">
                      <table id="arrrgDetailInfo" class="detailPanel">
                        <tr>
                        	<th><span class="label">연체일수</span></th>
                            <td width="125px"><span data-column-id="arrrgDates"></span></td>
                            <th>이체상태</th>
                            <td><span data-column-id="icheStatusNm"></span></td>
                            <th>결과코드</th>
                            <td colspan="3"><span data-column-id="rsltCode"></span></td>
                        </tr>
                        <tr>
                        	<th><span class="label">연체고지납부기한</span></th>
                            <td width="125px"><input id="newPayTmlmt" data-column-id="newPayTmlmt" class="emdcal" style="width:90px"></td>
                        	<th><span class="label">연체적용일수</span></th>
                            <td><input id="applyPayDates" data-column-id="applyPayDates" style="width:50px"/></td>
                            <th><span class="label">연체요율</span></th>
                            <td><input id="arrrgRate" data-column-id="arrrgRate" style="width:80px"/></td>
                        	<th><span class="label">연체금액</span></th>
                            <td><input id="arrrgAmt" data-column-id="arrrgAmt" class="ygpaNumber" style="width:110px"/></td>
                        </tr>
                    </table>
                    </form>
                    <!-- 
	                  <div class="emdControlPanel" style="vertical-align: middle;">
						<button id="btnNticArrrgSingle" data-icon="ui-icon-clock">연체고지</button>
					</div>
					-->
                    </div>

            	</div>
					<table class="searchPanel">
					<tbody>
						<tr>
							<th>전체사용료내역</th>
						</tr>
					</tbody>
					</table>
	               	<!-- <h2>전체 사용료 내역</h2> -->
               		<table class="detailPanel">
                    	<thead>
                    		<tr>
	                            <th style="text-align:center; width: 60px"><span class="label">회차</span></th>
	                            <th style="text-align:center; width: 80px"><span class="label">회계년도</span></th>
	                            <th style="text-align:center; width: 80px"><span class="label">고지번호</span></th>
	                        	<th style="text-align:center; width: 130px"><span class="label">요금종류</span></th>
	                        	<th style="text-align:center; width: 100px"><span class="label">고지금액</span></th>
	                            <th style="text-align:center; width: 80px"><span class="label">고지일자</span></th>
	                            <th style="text-align:center; width: 110px"><span class="label">납부기한일자</span></th>
	                        	<th style="text-align:center; width: 80px"><span class="label">수납구분</span></th>
	                            <th style="text-align:center; width: 80px"><span class="label">수납일자</span></th>
                            </tr>
                    	</thead>
                    	<tbody id="detailPayInfo" >
                    		<tr>
	                            <td style="text-align:center;"><span data-column-id="nticCnt"></span></td>
	                            <td style="text-align:center;"><span data-column-id="accnutYear"></span></td>
	                            <td style="text-align:center;"><span data-column-id="nticno"></span></td>
	                            <td style="text-align:left;"><span data-column-id="chrgeKndNm"></span> (<span data-column-id="chrgeKnd"></span>)</td>
	                            <td style="text-align:right;"><span data-column-id="fee" class="ygpaNumber"> 원</span></td>
	                            <td style="text-align:center;"><span data-column-id="nticDt"></span></td>
	                            <td style="text-align:center;"><span data-column-id="payTmlmt"></span></td>
		                        <td style="text-align:center;"><span data-column-id="rcivSe" class="ygpaCmmnCd" data-code-id="GAM025"></span></td>
	                            <td style="text-align:center;"><span data-column-id="rcivDt"></span></td>
                             </tr>
                    	</tbody>
                    </table>
                    <table id="summaryPayInfo" class="summaryPanel">
                        <tr>
                        	<th><span class="label">총 고지 금액</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="totalNticAmount" class="ygpaNumber"></span> 원</td>
                            <th><span class="label">총 납부 금액</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="feePayAmount" class="ygpaNumber"></span> 원</td>
                            <th><span class="label">관리비</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="feeA1" class="ygpaNumber"></span> 원</td>
                            <th><span class="label">이자</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="feeA3" class="ygpaNumber"></span> 원</td>
                            <th><span class="label">연체료</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="feeD1" class="ygpaNumber"></span> 원</td>
                            <th><span class="label">과태료</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="feeD2" class="ygpaNumber"></span> 원</td>
                        </tr>
                    </table>
			</div>
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
                <!-- <div class="emdControlPanel"><button id="btnSaveItem">저장</button><button id="btnCancelItem">취소</button><button id="btnRemoveItem">삭제</button></div>  -->
                    <form id="prtFcltyRentFeePaySttusMngtListForm">
                        <table class="detailForm"  style="width:100%;">
                            <tr>
                                <th width="16%">항코드</th>
                                <td><span id="prtAtCode" ></span></td>
                                <th width="16%">항코드명</th>
                                <td><span id="prtKorNm" ></span></td>
                                <th width="16%">회계년도</th>
                                <td><span id="fiscalYr" ></span></td>
                            </tr>
                            <tr>
                                <th width="16%">요금종류</th>
                                <td><span id="feeTp" ></span></td>
                                <th width="16%">요금종류명</th>
                                <td><span id="feeTpKorNm" ></span></td>
                                <th width="16%">고지번호</th>
                                <td><span id="billNo" ></span></td>
                            </tr>
                            <tr>
                                <th width="16%">업체코드</th>
                                <td><span id="agentCode" ></span></td>
                                <th width="16%">업체명</th>
                                <td><span id="firmKorNm" ></span></td>
                                <th width="16%">연체횟수</th>
                                <td><span id="dlySerNo" class="ygpaNumber" style="text-align:right;"></span></td>
                            </tr>
                            <tr>
                                <th width="16%">연체고지금액</th>
                                <td><span id="dlyBillAmnt" class="ygpaNumber" style="text-align:right;"></span></td>
                                <th width="16%">연체고지일자</th>
                                <td><span id="dlyBillDt" ></span></td>
                                <th width="16%">연체고지서발부여부</th>
                                <td><span id="dlyBillPrtYn" ></span></td>
                            </tr>
                            <tr>
                                <th width="16%">사업자등록번호</th>
                                <td><span id="bzRgstId" ></span></td>
                                <th width="16%">산출내역</th>
                                <td><span id="dlyBillRsn" ></span></td>
                                <th width="16%">연체납부기한</th>
                                <td><span id="dlyDueDt" ></span></td>
                            </tr>
                            <tr>
                                <th width="16%">최초고지일자</th>
                                <td><span id="firstBillDt" ></span></td>
                                <th width="16%">연체수납일자</th>
                                <td><span id="dlyRcvdDt" ></span></td>
                                <th width="16%">할인율코드</th>
                                <td><span id="dcRate" ></span></td>
                            </tr>
                            <tr>
                                <th width="16%">금융기관수납일자</th>
                                <td><span id="recptEpdt" ></span></td>
                                <th width="16%">시작일자</th>
                                <td><span id="strDate" ></span></td>
                                <th width="16%">종료일자</th>
                                <td><span id="endDate" ></span></td>
                            </tr>
                        </table>
                    </form>
                    <table style="width:100%;" id="prtFcltyRentFeePaySttusMngtSum" class="summaryPanel">
						<tr>
							<th width="30%" height="23">자료수</th>
							<td><span id="dpTotCnt" class="ygpaNumber" style="text-align:right;"></span></td>
							<th width="30%" height="23">연체고지금액합계</th>
							<td><span id="sumDlyBillAmnt" class="ygpaNumber" style="text-align:right;"></span></td>
						</tr>
					</table>
            </div>
        </div>
    </div>
</div>