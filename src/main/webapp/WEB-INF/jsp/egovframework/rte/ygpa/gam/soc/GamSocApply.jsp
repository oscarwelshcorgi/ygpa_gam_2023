<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocApply.jsp
 * @Description : 비관리청 신청
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.06    김종민        최초 생성
 *
 * author HNJ
 * since 2014.09.30
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<%-- <validator:javascript formName="gamSocApply" method="validateGamAssetRent" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamSocApplyDetail" method="validateGamAssetRentDetail" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamSocApplyFile" method="validateGamAssetRentFile" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" /> --%>
<!--
<validator:javascript formName="gamSocApply" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamSocApplyDetail" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamSocApplyFile" staticJavascript="false" xhtml="true" cdata="false" />
 -->
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSocApplyModule() {}

GamSocApplyModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocApplyModule.prototype.loadComplete = function() {

    // 업체신청 면제요청목록 설정
    this.$("#socApplyList").flexigrid({
        module: this,
        url: '<c:url value="/oper/gnrl/gamSelectPrtFcltyRentMngtList.do" />',
        dataType: 'json',
        colModel : [
					{display:'관리청코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
					{display:'관리청', name:'prtAtKorNm',width:45, sortable:false,align:'center'},
                    {display:'준공년도', name:'cmplYr',width:55, sortable:false,align:'center'},
                    {display:'공사번호', name:'constNo',width:80, sortable:false,align:'center'},
                    {display:'요청청코드', name:'appPrtAtCode',width:60, sortable:false,align:'center'},
                    {display:'요청청', name:'appPrtAtKorNm',width:45, sortable:false,align:'center'},
                    {display:'요청업체코드', name:'appAgentCode',width:100, sortable:false,align:'left'},
                    {display:'요청업체명', name:'appAgentName',width:150, sortable:false,align:'left'},
                    {display:'횟수', name:'useNo',width:45, sortable:false,align:'center'},
                    {display:'사용여부', name:'useYn',width:55, sortable:false,align:'center'},
                    {display:'보전요청액', name:'exmpAmnt',width:80, sortable:false,align:'center'},
                    {display:'보전기간시작일', name:'periodFr',width:80, sortable:false,align:'center'},
                    {display:'보전기간종료일', name:'periodTo',width:80, sortable:false,align:'center'},
                    {display:'신청일자', name:'applDate',width:80, sortable:false,align:'center'},
                    {display:'조건', name:'exmpCond',width:80, sortable:false,align:'center'},
                    {display:'보전누계액', name:'exmpAcc',width:80, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            return data;
        }
    });

    // 당해시설 및 타인 사용료 징수 시 해당시설코드 설정
    this.$("#socApplyFcltyList").flexigrid({
        module: this,
        url: '<c:url value="/oper/gnrl/gamSelectPrtFcltyRentMngtDetailList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'삭제', name:'chkApplyFclty',width:40, sortable:false,align:'center', displayFormat:"checkbox"},
                    {display:'시설코드', name:'facCode',width:50, sortable:false,align:'center'},
                    {display:'시설하위코드', name:'facSubCode',width:80, sortable:false,align:'center'},
                    {display:'시설명', name:'facKorNm',width:230, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: true,
        height: '400px'
    });

    // 투자비보전처리대상 요금종류 설정
    this.$("#socApplyChrgeKndList").flexigrid({
        module: this,
        url: '<c:url value="/oper/gnrl/gamSelectPrtFcltyRentMngtFileList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'삭제', name:'chkApplyChrgeKnd', width:40, sortable:true, align:'center', displayFormat:"checkbox"},
                    {display:'요금코드', name:'feeTp', width:100, sortable:true, align:'left'},
                    {display:'요금명', name:'feeTpKorNm', width:280, sortable:true, align:'left'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    this.$("#socApplyList").on('onItemSelected', function(event, module, row, grid, param) {
    });

};

/**
 * 정의 된 버튼 클릭 시
 */
GamSocApplyModule.prototype.onButtonClick = function(buttonId) {
	var opts = null;
    switch(buttonId) {
        case 'searchBtn':
			break;
        case 'popupSocApplyInfo' : //면제요청업체 선택
        	opts = this.makeFormArgs('#gamSocApplySearchForm');
			this.doExecuteDialog('selectApplyInfo', '면제요청 선택',
					'<c:url value="/popup/showSocApplyInfo.do"/>', opts);
        	break;
        case 'popupSocAgentInfo' : //허가원부 선택
			this.doExecuteDialog('selectAgentInfo', '허가원부 선택',
					'<c:url value="/popup/showSocAgentFInfo.do"/>', opts);
        	break;			
    }
};

GamSocApplyModule.prototype.onSubmit = function() {
    this.loadData();
};

GamSocApplyModule.prototype.loadData = function() {
    this.$("#socApplyListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamSocApplySearchForm');
    this.$('#socApplyList').flexOptions({params:searchOpt}).flexReload();
};

GamSocApplyModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        var row = this.$('#socApplyList').selectedRows();
        if(row.length==0) {
            this.$('#cmd').val('insert');
        }
        else {
            this.$('#cmd').val('modify');
        }

        if(oldTabId=='tabs1') {
        	this._deleteDataList=[];    // 삭제 목록 초기화
        	this._deleteDataFileList=[];    // 파일삭제 목록 초기화
        }
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamSocApplyModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     case 'selectApplyInfo' : //면제요청 조회
	   	 this.$("#sAppAgentCode").val(value["appAgentCode"]);
	   	 this.$("#sAppAgentName").val(value["appAgentName"]);
	   	 this.$("#sUseNo").val(value["useNo"]);
	   	 this.$("#sAppPrtAtCode").val(value["appPrtAtCode"]);
   	 	 break;
     case 'selectAgentInfo' : //허가원부 조회
	   	 this.$("#sPrtAtCode").val(value["prtAtCode"]);
	   	 this.$("#sCmplYr").val(value["cmplYr"]);
	   	 this.$("#sConstNo").val(value["constNo"]);
	   	 break;
	 default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');
         break;
     }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSocApplyModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocApplySearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>공사관리청코드</th>
                            <td>
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">전체</option>
                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>공사준공년도</th>
                            <td width="250px">
                                <select id="sCmplYr">
                                    <option value="" selected="selected">년</option>
                                    <c:forEach  items="${yearsList}" var="yearsItem">
                                        <option value="${yearsItem }">${yearsItem }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>공사일련번호</th>
                            <td>
                            	<input id="sConstNo" type="text" size="15">
                            </td>
                            <td style="text-align:right;">
				                <button id="popupSocAgentInfo" class="popupButton">허가원부선택</button>
                            </td>
                            <td  rowSpan="2">
								<button id="searchBtn" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                        <tr>
                            <th>면제요청청코드</th>
                            <td>
                                <select id="sAppPrtAtCode">
                                    <option value="" selected="selected">전체</option>
                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>면제요청업체코드</th>
                            <td>
                                <input id="sAppAgentCode" type="text" size="6">&nbsp; &nbsp;
                            	<input id="sAppAgentName" type="text" size="10" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <th>요청횟수</th>
                            <td>
                            	<input id="sUseNo" type="text" size="15">
                            </td>
                            <td style="text-align:right;">
				                <button id="popupSocApplyInfo" class="popupButton">면제요청</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="socApplyListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">업체신청 면제요청목록</a></li>
                <li><a href="#tabs2" class="emdTab">업체신청 면제요청내역</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
            	<div class="emdControlPanel">
					<form id="form1">
    	               	<table class="detailForm"  style="width:100%;">
                            <tr>
                                <th width="16%">보전처리신청금액</th>
                                <td><input type="text" id="exmpAmnt" size="20" ></td>
                                <th width="16%">보전처리누계액</th>
                                <td><input type="text" id="exmpAcc" size="20" disabled="disabled"></td>
                            </tr>
                            <tr>
                                <th width="16%">보전처리기간</th>
                                <td>
									<input id="periodFr" type="text" class="emdcal" size="20"> ~ <input id="periodTo" type="text" class="emdcal" size="20">
                                </td>
                                <th width="16%">보전처리조건</th>
                                <td>
                                	<select id="exmpCond">
	                                    <option value="1" selected="selected">금액</option>
										<option value="2">기간</option>
	                                </select>
                                </td>
                            </tr>
                            <tr>
                            	<th width="16%">공사업체</th>
                                <td>
                                	<input id="agentCode" type="text" size="10">&nbsp; &nbsp;
	                            	<input id="agentName" type="text" size="15" disabled="disabled">&nbsp; &nbsp;
	                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                                </td>
                                <th width="16%">적용요율</th>
                                <td>
                                	<select id="rateGubun">
	                                    <option value="1" selected="selected">과거</option>
										<option value="2">현재</option>
	                                </select>
                                </td>
                            </tr>
                            <tr>
                                <th width="16%">특이사항</th>
                                <td colSpan="3"><input type="text" id="remark" size="120"></td>
                            </tr>
                            <tr>
                                <th width="16%">공사명칭</th>
                                <td><input type="text" id="item" size="55"></td>
                                <th width="16%">사용여부</th>
                                <td>
                                	<select id="useYn">
	                                    <option value="Y" selected="selected">보전처리개시</option>
										<option value="N">보전처리중지</option>
	                                </select>
                                </td>
                            </tr>
                            <tr>
                                <th width="16%">사업자등록번호</th>
                                <td><input type="text" id="bzRgstId" size="20" ></td>
                                <th width="16%">신청일자</th>
                                <td><input id="applDate" type="text" class="emdcal" size="10"></td>
                            </tr>
                        </table><br>
                        <table class="detailForm"  style="width:100%;">
                            <tr>
                                <th width="16%">외항접안</th>
                                <td><input type="text" id="r11Rate"></td>
                                <th width="16%">내항접안</th>
                                <td><input type="text" id="r12Rate"></td>
                                <th width="16%">외항정박</th>
                                <td><input type="text" id="r21Rate"></td>
                                <th width="16%">내항정박</th>
                                <td><input type="text" id="r22Rate"></td>
                            </tr>
                        </table>
					</form>
                </div>
                <table id="socApplyList" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel">
					<form id="form1">
						<!--
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="12%" height="20">자료수</th>
								<td><input type="text" size="15" id="totalResultCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">보전처리요청액</th>
								<td><input type="text" size="32" id="totalExmpAmnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">보전처리누계액</th>
								<td><input type="text" size="32" id="totalExmpAcc" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
						-->
    	               	<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                                <button id="btnSave">저장</button>
	                                <button id="btnRemove">삭제</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamSocApplyForm">
                    	<table style="width:100%;">
                    		<tr>
                    			<td width="50%" style="font-weight: bold;"> &gt; 당해시설 및 타인 사용료 징수시 해당시설코드(R1,R2 대상시설)</td>
                    			<td style="font-weight: bold;"> &gt; 투자비보전처리대상 요금종류</td>
                    		</tr>
        	               	<tr>
								<td style="padding-right:15px;">
									<table id="socApplyFcltyList" style="display:none" class="fillHeight"></table>
			                        <table style="width:100%;">
				                        <tr>
				                            <td style="text-align: right">
				                                <button id="btnAddFcltyItem">행추가</button>
				                                <button id="btnRemoveFcltyItem">행삭제</button>
				                            </td>
				                        </tr>
									</table>
								</td>
								<td style="padding-right:15px;">
									<table id="socApplyChrgeKndList" style="display:none" class="fillHeight"></table>
			                        <table style="width:100%;">
				                        <tr>
				                            <td style="text-align: right">
				                                <button id="btnAddApplyChrgeKndItem">행추가</button>
				                                <button id="btnRemoveApplyChrgeKndItem">행삭제</button>
				                            </td>
				                        </tr>
									</table>
								</td>
							</tr>
						</table>
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right;padding-right:15px;">
	                                <button id="btnSaveDetailItem">저장</button>
	                            </td>
	                        </tr>
						</table>
                    </form>
                 </div>
            </div>
        </div>
    </div>
</div>