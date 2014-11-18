<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPrtFcltyRentFeeSttusByFeeTpInqire.jsp
  * @Description : 항만시설요금종류별사용료현황조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.11  eunsungj          최초 생성
  *
  * author eunsungj
  * since 2014.11.11
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPrtFcltyRentFeeSttusByFeeTpInqireModule() {}

GamPrtFcltyRentFeeSttusByFeeTpInqireModule.prototype = new EmdModule(1000, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamPrtFcltyRentFeeSttusByFeeTpInqireModule.prototype.loadComplete = function() {

 // 테이블 설정 //
 this.$("#prtFcltyRentFeeSttusByFeeTpInqireList").flexigrid({
     module: this,
     url: '<c:url value="/oper/gnrl/selectPrtFcltyMtRentFeeSttusInqireList.do"/>',
     dataType: 'json',
     colModel : [
                 {display:'항코드', name:'prtAtCode',width:40, sortable:false,align:'center'},
                 {display:'항코드명', name:'prtKorNm',width:55, sortable:false,align:'center'},
                 {display:'사용년도', name:'usageYear',width:55, sortable:false,align:'center'},
                 {display:'자산코드', name:'gisAssetsCd',width:55, sortable:false,align:'center'},
                 {display:'자산부코드', name:'gisAssetsSubCd',width:70, sortable:false,align:'center'},
                 {display:'자산명', name:'prtFcltyNm',width:180, sortable:false,align:'left'},
                 {display:'전체사용료', name:'sumTotalFee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'1월사용료', name:'sum01Fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'2월사용료', name:'sum02Fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'3월사용료', name:'sum03Fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'4월사용료', name:'sum04Fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'5월사용료', name:'sum05Fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'6월사용료', name:'sum06Fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'7월사용료', name:'sum07Fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'8월사용료', name:'sum08Fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'9월사용료', name:'sum09Fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'10월사용료', name:'sum10Fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'11월사용료', name:'sum11Fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'12월사용료', name:'sum12Fee',width:100, sortable:false,align:'right', displayFormat: 'number'}
                 ],
     showTableToggleBtn: false,
     height: 'auto',
     preProcess: function(module,data) {
         module.$('#totSumCnt').val(data.totalCount);
         module.$('#totSumFee').val(data.totSumFee);
         return data;
     }

 });
 //로드될 때 사용기간에 오늘날짜 처리
	var today = new Date();

 	var pyear = today.getFullYear();
    var pmonth= today.getMonth() + 1;

    if (pmonth <= 1) {
        var today = new Date(pyear - 1, 12, 1);
    }
    else
    {
        var today = new Date(pyear, pmonth - 2, 1);
    }

 	var tomonth = ((today.getMonth() + 1) >= 10) ? '' + (today.getMonth() + 1) : '0' + (today.getMonth() + 1);
	var toyear =  '' + today.getFullYear();
	this.$('#sStartYr').val(toyear);
	this.$('#sStartMn').val(tomonth);
	this.$('#sEndYr').val(toyear);
	this.$('#sEndMn').val(tomonth);

	console.log('debug');
};

/**
* 정의 된 버튼 클릭 시
*/
GamPrtFcltyRentFeeSttusByFeeTpInqireModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

	    // 조회
	    case 'searchBtn':
			this.loadData();
	        break;

	    case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            var opts;

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;

   	     // 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisAssetsCodePopup", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;
	}
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamPrtFcltyRentFeeSttusByFeeTpInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {

	// 자산코드 조회
	case "searchGisAssetsCodePopup":
		this.$("#sAssetsCd").val(value["gisAssetsCd"]);
		this.$("#sAssetsSubCd").val(value["gisAssetsSubCd"]);
		break;
	case 'selectEntrpsInfoPopup':
       if (msg != 'cancel') {
           this.$('#sEntrpscd').val(value.entrpscd);
           this.$('#sEntrpsNm').val(value.entrpsNm);
       } else {
           alert('취소 되었습니다');
       }
       break;
	}
};

GamPrtFcltyRentFeeSttusByFeeTpInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPrtFcltyRentFeeSttusByFeeTpInqireModule.prototype.loadData = function() {
    if( this.$('#sStartYr').val() == '' ) {
    	alert("조회시작년을 선택하십시오.");
    	return;
    }
    if( this.$('#sStartMn').val() == '' ) {
    	alert("조회시작월을 선택하십시오.");
    	return;
    }
    if( this.$('#sEndYr').val() == '' ) {
    	alert("조회끝년을 선택하십시오.");
    	return;
    }
    if( this.$('#sEndMn').val() == '' ) {
    	alert("조회끝월을 선택하십시오.");
    	return;
    }
    var searchOpt=this.makeFormArgs('#searchForm');
    this.$('#prtFcltyRentFeeSttusByFeeTpInqireList').flexOptions({params:searchOpt}).flexReload();
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamPrtFcltyRentFeeSttusByFeeTpInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="searchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항구분</th>
                            <td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
                            <th>조회구분</th>
                            <td><select id="optFeeType">
                            	<option value="0">고지</option>
                            	<option value="1">수납</option>
                            </select></td>
                            <td rowSpan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                            <th>조회기간</th>
                            <td colspan="3">
		                       	<select id="sStartYr">
                                    <option value="" selected="selected">년도</option>
                                    <c:forEach  items="${yearsList}" var="yearItem">
                                        <option value="${yearItem }">${yearItem }년</option>
                                    </c:forEach>
                                </select>
                                <select id="sStartMn">
                                    <option value="" selected="selected">월</option>
                                    <option value="01">1월</option>
                                    <option value="02">2월</option>
                                    <option value="03">3월</option>
                                    <option value="04">4월</option>
                                    <option value="05">5월</option>
                                    <option value="06">6월</option>
                                    <option value="07">7월</option>
                                    <option value="08">8월</option>
                                    <option value="09">9월</option>
                                    <option value="10">10월</option>
                                    <option value="11">11월</option>
                                    <option value="12">12월</option>
                                </select>
                                &nbsp;~&nbsp;
		                       	<select id="sEndYr">
                                    <option value="" selected="selected">년도</option>
                                    <c:forEach  items="${yearsList}" var="yearItem">
                                        <option value="${yearItem}">${yearItem}</option>
                                    </c:forEach>
                                </select>
                                <select id="sEndMn">
                                    <option value="" selected="selected">월</option>
                                    <option value="01">1월</option>
                                    <option value="02">2월</option>
                                    <option value="03">3월</option>
                                    <option value="04">4월</option>
                                    <option value="05">5월</option>
                                    <option value="06">6월</option>
                                    <option value="07">7월</option>
                                    <option value="08">8월</option>
                                    <option value="09">9월</option>
                                    <option value="10">10월</option>
                                    <option value="11">11월</option>
                                    <option value="12">12월</option>
                                </select>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

	<div class="emdPanel fillHeight">
		<table id="prtFcltyRentFeeSttusByFeeTpInqireList" style="display: none" class="fillHeight"></table>
		<div class="emdControlPanel">
			<form id="form1">
				<table style="width: 100%;" class="summaryPanel">
					<tr>
						<th width="20%" height="23">자료수</th>
						<td><input type="text" size="30" id="totSumCnt" class="ygpaNumber" disabled="disabled" /></td>
						<th width="20%" height="23">전체사용료</th>
						<td><input type="text" size="50" id="totSumFee" class="ygpaNumber" disabled="disabled" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>