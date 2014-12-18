<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocExmpMngt.jsp
 * @Description : 비관리청 면제관리
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.09.22  김종민          최초 생성
 *
 * author 김종민
 * since 2014.09.22
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<validator:javascript formName="gamSocExmpMngtSearchForm" method="validateGamSocExmpMngt" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSocExmpMngtModule() {}

GamSocExmpMngtModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocExmpMngtModule.prototype.loadComplete = function() {
	this.fillSelectBoxYear('#sFiscalYr');
	this._cmd = '';
	this.initDisplay();
};

//2000년부터 현재년도까지 select 박스에 채워넣는 함수.
GamSocExmpMngtModule.prototype.fillSelectBoxYear = function(id) {
	var curYear = (new Date()).getFullYear();
	for(var i=curYear; i>=2000; i--) {
		this.$(id).append('<option value="' + i + '">' + i + '</option>');
	}
};

GamSocExmpMngtModule.prototype.onSubmit = function() {
    this.loadData();
};

//면제관리 데이터 로드
GamSocExmpMngtModule.prototype.loadData = function() { 
	if(!validateGamSocExmpMngt(this.$('#gamSocExmpMngtSearchForm')[0])){ 		
		return;
	}
	var opts = this.makeFormArgs('#gamSocExmpMngtSearchForm');
	this.doAction('/soc/selectSocExmpMngtDetail.do', opts, function(module, result) {
		module.$('#btnNewSocNo').hide();
		if(result.resultCode == 0) {
			module._cmd = 'modify';
			module.$('#gamSocExmpMngtForm :input').val('');
			module.makeFormValues('#gamSocExmpMngtForm', result.resultVO);
			module.$('#tempAppPrtAtCode').val(result.resultVO.appPrtAtCode);
		}
		else {
			module._cmd = '';
			module.$('#gamSocExmpMngtForm :input').val('');
			alert(result.resultMsg);
		}
	});	
};

//화면 및 변수 초기화
GamSocExmpMngtModule.prototype.initDisplay = function() {
	this.$('#btnNewSocNo').hide();
	this.$('#gamSocExmpMngtSearchForm :input').val('');
	this.$('#gamSocExmpMngtForm :input').val('');
	if(this._cmd == 'insert') {
		this.$('#btnNewSocNo').show();
	}
};

//새로운 soc 번호 생성.
GamSocExmpMngtModule.prototype.nextSocNo = function() { 
	if(this.$('#sAppPrtAtCode').val() == '') {
		alert('항코드를 선택하세요.');
		return;
	}
	if(this.$('#sFeeTp').val() == '') {
		alert('요금종류코드를 입력하세요.');
		return;
	}
	if(this.$('#sFiscalYr').val() == '') {
		alert('회계년도를 입력하세요.');
		return;
	}
	var opts = this.makeFormArgs('#gamSocExmpMngtSearchForm');
	this.doAction('/soc/selectSocExmpMngtGetNextSocNo.do', opts, function(module, result) {
		if(result.resultCode == 0) {
			module.$('#sSocNo').val(result.nextSocNo);
			module.$('#appPrtAtCode').val(module.$('#sAppPrtAtCode').val());
			module.$('#tempAppPrtAtCode').val(module.$('#sAppPrtAtCode').val());
			module.$('#feeTp').val(module.$('#sFeeTp').val());
			module.$('#fiscalYr').val(module.$('#sFiscalYr').val());
			module.$('#socNo').val(module.$('#sSocNo').val());
		}
		else {
			alert(result.resultMsg);
		}
	});
};

//면제관리데이터 삽입
GamSocExmpMngtModule.prototype.insertData = function() {
	var opts = this.makeFormArgs('#gamSocExmpMngtForm');
	this.doAction('/soc/insertSocExmpMngtDetail.do', opts, function(module, result) {
		if(result.resultCode == 0) {
    		module.$('#btnNewSocNo').hide();
		}
		alert(result.resultMsg);
	});	
};

//면제관리데이터 수정
GamSocExmpMngtModule.prototype.updateData = function() {
	var opts = this.makeFormArgs('#gamSocExmpMngtForm');
	this.doAction('/soc/updateSocExmpMngtDetail.do', opts, function(module, result) {
		alert(result.resultMsg);
	});	
};

//면제관리데이터 저장
GamSocExmpMngtModule.prototype.saveData = function() {
	if(this._cmd == 'insert') {
    	if(!validateGamSocExmpMngt(this.$('#gamSocExmpMngtSearchForm')[0])){ 		
    		return;
    	}
	}
	if(this.$('#prtAtCode').val() == '') {
		alert('공사관리청을 선택하세요.');
		return;
	}
	if(this.$('#cmplYr').val() == '') {
		alert('공사준공년도를 입력하세요.');
		return;
	}
	if(this.$('#constNo').val() == '') {
		alert('공사일련번호를 입력하세요.');
		return;
	}
	if(this.$('#appPrtAtCode').val() == '') {
		alert('면제요청청을 선택하세요.');
		return;
	}
	if(this.$('#useNo').val() == '') {
		alert('신청횟수를 입력하세요.');
		return;
	}
	if(this.$('#appAgentCode').val() == '') {
		alert('신청업체를 선택하세요.');
		return;
	}
	if(this._cmd == 'insert') {
		this.insertData();
	} else if(this._cmd == 'modify') {
		this.updateData();
	}
};

//면제관리데이터 삭제
GamSocExmpMngtModule.prototype.deleteData = function() {
	if((this._cmd == 'modify')) {
    	if(confirm('데이터를 삭제하시겠습니까?')) {
			opts = this.makeFormArgs('#gamSocExmpMngtForm');
        	this.doAction('/soc/deleteSocExmpMngtDetail.do', opts, function(module, result) {
        		if(result.resultCode == 0) {
            		module._cmd = '';
            		module.initDisplay();
        		}
        		alert(result.resultMsg);
        	});
    	}
	}
	else {
		alert('삭제할 데이터를 조회하세요.');
	}	
};

GamSocExmpMngtModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'btnSearch': //조회
        	this.loadData();
            break;
        case 'btnAdd' : //추가버튼
			this._cmd = 'insert';
        	this.initDisplay();
        	break;
        case 'btnNewSocNo' : //신규관리번호생성
        	this.nextSocNo();
        	break;
        case 'btnSave' : //저장
			this.saveData();
        	break;
        case 'btnDelete' : //삭제
        	this.deleteData();
        	break;
        case 'popupChrgeKndCd' : //요금코드조회
        	var opts = { prtAtCode : this.$('#sAppPrtAtCode').val() };
			this.doExecuteDialog('selectChrgeKndCd', '요금 선택', '/popup/showSocPayCd.do', opts);
        	break;
        case 'popupApplyInfo' : //신청업체(투자비보전신청업체) 조회
			this.doExecuteDialog('selectApplyInfo', '투자비보전 신청업체 선택', '/popup/showSocApplyInfo.do', {});
        	break;
        case 'popupEntrpsInfo' : //면제업체 조회버튼
			this.doExecuteDialog('selectEntrpsInfo', '업체 선택', '/popup/showSocEntrpsInfo.do', {});
        	break;
        case 'popupFacCd' : //시설코드조회
        	var opts = { prtAtCode : this.$('#exmpPrtAtCode').val() };
        	this.doExecuteDialog('selectFacilCd', '시설 선택', '/popup/showSocFacCd.do', opts);
        	break;
        case 'popupVsslCd' : //선박호출부호조회
			this.doExecuteDialog('selectVsslCd', '선박 선택', '/popup/showSocVsslCd.do', {});
        	break;
	}
};

GamSocExmpMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamSocExmpMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
	    case 'selectChrgeKndCd' : //요금조회
	    	this.$("#sAppPrtAtCode").val(value["prtAtCode"]);
	    	this.$("#sFeeTp").val(value["feeTp"]);
	    	this.$("#sFeeTpKorNm").val(value["feeTpKorNm"]);
	    	break;
	    case 'selectApplyInfo' : //신청업체(투자비 보전 신청업체) 조회
	    	this.$("#appAgentCode").val(value["appAgentCode"]);
	    	this.$("#appAgentName").val(value["appAgentName"]);
	    	this.$("#useNo").val(value["useNo"]);
	    	this.$("#cmplYr").val(value["cmplYr"]);
	    	this.$("#constNo").val(value["constNo"]);
	    	this.$("#appPrtAtCode").val(value["appPrtAtCode"]);
	    	this.$("#prtAtCode").val(value["prtAtCode"]);
	    	break;
	    case 'selectEntrpsInfo' : //면제업체 조회
	    	this.$("#exmpAgentCode").val(value["agentCode"]);
	    	this.$("#exmpAgentName").val(value["firmKorNm"]);
	    	break;
	    case 'selectFacilCd' : //시설조회
	    	this.$("#facCode").val(value["facCode"]);
	    	this.$("#facSubCode").val(value["facSubCode"]);
	    	this.$("#facKorNm").val(value["facKorNm"]);
	    	break;
	    case 'selectVsslCd' : //선박 호출부호 조회
	    	this.$("#callLetter").val(value["callLetter"]);
	    	this.$("#callLetterNm").val(value["vsslKorNm"]); 
	    	break;
	    default:
	        alert('알수없는 팝업 이벤트가 호출 되었습니다.');
	        break;
     }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSocExmpMngtModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main"> 

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocExmpMngtSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>
                            <td>
                                <select id="sAppPrtAtCode">
	                            	<option value="" selected="selected">선택</option>
	                                <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                    <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
	                                </c:forEach>
	                            </select>
                            </td>
                            <th>요금종류코드</th>
                            <td>
                                <input id="sFeeTp" type="text" size="2" />
                                <input type="text" size="20" id="sFeeTpKorNm" disabled/>
                                <button id="popupChrgeKndCd" class="popupButton">선택</button>
                            </td>
                            <td rowspan="2"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>                            
                            <th>회계년도</th>
                            <td width="100px">
                                <select id="sFiscalYr">
                                	<option value="" selected="selected">선택</option>
                                </select>
                            </td>                            
                            <th>관리번호</th>
                            <td>
                                <input id="sSocNo" type="text" size="8" maxlength="6" />&nbsp; &nbsp;
                                <button id="btnNewSocNo" class="popupButton">신규관리번호생성</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">비관리청 면제관리</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamSocExmpMngtForm">
                        <table class="editForm">
                        	<tr>
                        		<th colspan="6" >조회내역</th>
                        	</tr>
                            <tr>
								<th width="10%" height="18">공사관리청</th>
                                <td>
                            		<input id="cmd" type="hidden"/>
                                	<input id="tempAppPrtAtCode" type="hidden" />
                                	<input id="feeTp" type="hidden"/>
                                	<input id="fiscalYr" type="hidden"/>
                                	<input id="socNo" type="hidden" />
                                	<select id="prtAtCode">
	                                    <option value="" selected="selected">선택</option>
	                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                        <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
	                                    </c:forEach>
	                                </select>                                    
                                </td>
								<th width="10%" height="18">공사준공년도</th>
                                <td>
                                    <input type="text" size="6" id="cmplYr" maxlength="4"/>
                                </td>
								<th width="10%" height="18">공사일련번호</th>
                                <td>
                                    <input type="text" size="8" id="constNo" maxlength="6"/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">면제요청청</th>
                                <td>
                                	<select id="appPrtAtCode">
	                                    <option value="" selected="selected">선택</option>
	                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                        <option value="${prtAtCdItem.prtAtCode}">${prtAtCdItem.prtKorNm}</option>
	                                    </c:forEach>
	                                </select>                                    
                                </td>
								<th width="10%" height="18">신청횟수</th>
                                <td><input type="text" size="3" id="useNo" maxlength="2" /></td>
								<th width="10%" height="18">신청업체</th>
                                <td>
                                    <input type="text" size="11" id="appAgentCode" maxlength="9"/>
                                    <input type="text" size="25" id="appAgentName" disabled/>
                                    <button id="popupApplyInfo" class="popupButton">투자비보전신청업체선택</button>
                                </td>
                            </tr>
                        	<tr>
                        		<td colspan="6" height="18">
                        		</td>
                        	</tr>
                        	<tr>
                        		<th colspan="6">투자비보전처리 상세 정보</th>
                        	</tr>
                            <tr>
								<th width="10%" height="18">면제처리청</th>
                                <td colspan="5">
                                	<select id="exmpPrtAtCode">
	                                    <option value="" selected="selected">선택</option>
	                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                        <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
	                                    </c:forEach>
	                                </select>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">면제업체</th>
                                <td colspan="3">
                                    <input type="text" size="11" id="exmpAgentCode" maxlength="9"/>
                                    <input type="text" size="25" id="exmpAgentName" disabled/>
                                    <button id="popupEntrpsInfo" class="popupButton">선택</button>
                                </td>
								<th width="10%" height="18">면제유형</th>
                                <td>
                                	<select id="exmpType">
                                    	<option value="" selected="selected">선택</option>
                                    	<option value="1">당해시설</option>
                                    	<option value="2">타인사용료</option>
                                    	<option value="3">다른시설</option>
                                	</select>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">외내항구분</th>
                                <td colspan="5">
                                    <input type="text" size="2" id="inOut" maxlength="1" />
                                    <input type="text" size="10" id="inOutName" disabled/>
                                    <span>선박관리 : 1.외항, 2.내항 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 화물관리 : 1.외입, 2.외출, 3.내입, 4.내출, 5.항내운입, 6.항내운출</span>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">호출부호</th>
                                <td colspan="3">
                                    <input type="text" size="11" id="callLetter" maxlength="9" />
                                    <input type="text" size="25" id="callLetterNm" disabled/>
                                    <button id="popupVsslCd" class="popupButton">선택</button>
                                </td>
								<th width="10%" height="18">입항횟수</th>
                                <td>
                                    <input type="text" size="6" id="yr" maxlength="4"/>
                                    <input type="text" size="5" id="serNo" maxlength="3" />
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">시설코드</th>
                                <td colspan="3">
                                    <input type="text" size="5" id="facCode" maxlength="3" />
                                    <input type="text" size="4" id="facSubCode" maxlength="2" />
                                    <input type="text" size="24" id="facKorNm" disabled/>
                                    <button id="popupFacCd" class="popupButton">선택</button>
                                </td>
								<th width="10%" height="18">고지일자</th>
                                <td>
                                	<input id="billDt" type="text" class="emdcal" size="12" />
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">입출항일자</th>
                                <td colspan="3">
                                    <input id="ioDt" type="text" class="emdcal" size="12" />
                                </td>
								<th width="10%" height="18">면제금액</th>
                                <td>
                                	<input type="text" size="20" id="exmpAmnt" class="ygpaNumber" /> 원
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">기본료</th>
                                <td colspan="3">
                                    <input type="text" size="16" id="standardFee" class="ygpaNumber" /> 원
                                </td>
								<th width="10%" height="18">고지번호</th>
                                <td>
                                	<input type="text" size="9" id="billNo" maxlength="6" />
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">운임톤</th>
                                <td colspan="3">
                                    <input type="text" size="16" id="realTn" class="ygpaNumber" />
                                </td>
								<th width="10%" height="18">접수자</th>
                                <td>
                                	<select id="jingsuja">
                                    	<option value="" selected="selected">선택</option>
                                    	<option value="PAT">항만공사</option>
                                    	<option value="MAP">해양항만청</option>
                                    	<option value="LGO">지자체</option>
                                	</select>
                                </td>
                            </tr>                            
                            <tr>
								<th width="10%" height="18">비고</th>
                                <td colspan="5">
                                    <input type="text" size="120" id="remark" maxlength="350"/>
                                </td>
                            </tr>                            
                        </table>
                    </form>

	                 <table style="width:100%">
	                    <tr>
	                        <td width="100"></td>
	                        <td style="text-align:right">
	                        	<button id="btnAdd">추가</button>
	                            <button id="btnSave" class="buttonSave">저장</button>
	                            <button id="btnDelete" class="buttonDelete">삭제</button>
	                        </td>
	                    </tr>
	                 </table>
                 </div>
            </div>
        </div>
    </div>
</div>