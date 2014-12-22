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
 * author 김종민
 * since 2014.10.06
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<validator:javascript formName="gamSocApplySearchForm" method="validateGamSocApply" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
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
        url: '/soc/gamSelectSocApplyList.do',
        dataType: 'json',
        colModel : [
					{display:'관리청코드', 	name:'prtAtCode',	width:80, sortable:false,align:'center'},
					{display:'관리청', 	name:'prtAtKorNm',	width:45, sortable:false,align:'center'},
                    {display:'준공년도', 	name:'cmplYr',		width:70, sortable:false,align:'center'},
                    {display:'공사번호', 	name:'constNo',		width:80, sortable:false,align:'center'},
                    {display:'요청청코드', 	name:'appPrtAtCode',width:80, sortable:false,align:'center'},
                    {display:'요청청', 	name:'appPrtAtKorNm',width:45, sortable:false,align:'center'},
                    {display:'요청업체코드',name:'appAgentCode',width:100, sortable:false,align:'left'},
                    {display:'요청업체명', 	name:'appAgentName',width:150, sortable:false,align:'left'},
                    {display:'횟수', 		name:'useNo',		width:45, sortable:false,align:'center'},
                    {display:'사용여부', 	name:'useYn',		width:70, sortable:false,align:'center'},
                    {display:'보전요청액', 	name:'exmpAmnt',	width:150, sortable:false,align:'right',displayFormat: 'number'},
                    {display:'보전기간시작일',name:'periodFr',	width:100, sortable:false,align:'center'},
                    {display:'보전기간종료일',name:'periodTo',	width:100, sortable:false,align:'center'},
                    {display:'신청일자', 	name:'applDate',	width:80, sortable:false,align:'center'},
                    {display:'조건', 		name:'exmpCond',	width:80, sortable:false,align:'center'},
                    {display:'적용요율',	name:'rateGubun',	width:80, sortable:false,align:'center'},
                    {display:'보전누계액', 	name:'exmpAcc',		width:200, sortable:false,align:'right',displayFormat: 'number'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            return data;
        }
    });
    
    this._cmd = '';
    
    // 당해시설 및 타인 사용료 징수 시 해당시설코드 설정
    this.$("#socApplyFacilList").flexigrid({
        module: this,
        url: '/soc/gamSelectSocApplyFacilList.do',
        dataType: 'json',
        colModel : [
                    {display:'시설코드', 	name:'facCode',		width:90, sortable:false,align:'center'},
                    {display:'시설하위코드',name:'facSubCode',	width:110, sortable:false,align:'center'},
                    {display:'시설명', 	name:'facKorNm',	width:260, sortable:false,align:'left'}
                    ],
        showTableToggleBtn: true,
        height: '300'
    });

    // 투자비보전처리대상 요금종류 설정
    this.$("#socApplyFeeList").flexigrid({
        module: this,
        url: '/soc/gamSelectSocApplyFeeList.do',
        dataType: 'json',
        colModel : [
                    {display:'요금코드', 	name:'feeTp', 		width:150, sortable:true, align:'center'},
                    {display:'요금명', 	name:'feeTpKorNm', 	width:310, sortable:true, align:'left'}
                    ],
        showTableToggleBtn: false,
        height: '300'
    });
    
    this.fillSelectBoxYear('#sCmplYr');
};

//2000년부터 현재년도까지 select 박스에 채워넣는 함수.
GamSocApplyModule.prototype.fillSelectBoxYear = function(id) {
	var curYear = (new Date()).getFullYear();
	for(var i=curYear; i>=2000; i--) {
		this.$(id).append('<option value="' + i + '">' + i + '</option>');
	}
};

//화면 및 변수 초기화
GamSocApplyModule.prototype.initDisplay = function() {
	this.$('#gamSocApplyDetailForm :input').val('');
	this.$("#socApplyList").flexEmptyData();
	this.$("#socApplyFacilList").flexEmptyData();
	this.$("#socApplyFeeList").flexEmptyData();
	this.$("#socApplyListTab").tabs("option", {active: 0});
	if(this._cmd == '' || this._cmd == 'insert') {
		this.$('#gamSocApplySearchForm :input').val('');
	} 
};

GamSocApplyModule.prototype.onSubmit = function() {
    this.loadData();
};

//비관리청 신청 데이터 로드
GamSocApplyModule.prototype.loadData = function() {
	if(!validateGamSocApply(this.$('#gamSocApplySearchForm')[0])){ 		
		return;
	}
	var opts = this.makeFormArgs('#gamSocApplySearchForm');
	this.doAction('/soc/gamSelectApplyDetailInquire.do', opts, function(module, result) {
		if(result.resultCode == 0) {
			module._cmd = 'modify';
			module.initDisplay();
			module.makeFormValues('#gamSocApplyDetailForm', result.resultVO);
			module.loadDetailData();
		}
		else {
			alert(result.resultMsg);
			module._cmd = '';
			module.initDisplay();
		}
	});
};

//비관리청 신청 디테일 목록 로드
GamSocApplyModule.prototype.loadDetailData = function() {
	var searchOpt = this.makeFormArgs("#gamSocApplySearchForm");
	this.$("#socApplyList").flexOptions({params:searchOpt}).flexReload();
	this.$("#socApplyFacilList").flexOptions({params:searchOpt}).flexReload();
	this.$("#socApplyFeeList").flexOptions({params:searchOpt}).flexReload();	
};

//화면에 존재하는 비관리청 신청 데이터 리턴
GamSocApplyModule.prototype.getApplyData = function () {
	var applyData = JSON.stringify(this.getFormValues("#gamSocApplyDetailForm"));
	var applyFacilList = JSON.stringify(this.$('#socApplyFacilList').flexGetData());
	var applyFeeList = JSON.stringify(this.$('#socApplyFeeList').flexGetData());
	
	var opts = [];
	
	opts[opts.length] = {name: 'applyData',value: applyData};
	opts[opts.length] = {name: 'applyFacilList',value: applyFacilList};
	opts[opts.length] = {name: 'applyFeeList',value: applyFeeList};
	
	return opts;
};

//비관리청 신청 삽입
GamSocApplyModule.prototype.insertData = function() {
	var opts = this.getApplyData();
	this.doAction('/soc/gamInsertSocApplyDetail.do', opts, function(module, result) {
		if(result.resultCode == 0) {
			module._cmd = 'modify';
		} 
		alert(result.resultMsg);
	});	
};

//비관리청 신청 수정
GamSocApplyModule.prototype.updateData = function() {
	var opts = this.getApplyData();
	this.doAction('/soc/gamUpdateSocApplyDetail.do', opts, function(module, result) {
		alert(result.resultMsg);
	});
};

//비관리청 신청 삽입 및 수정
GamSocApplyModule.prototype.saveData = function() {
	if(this._cmd == 'insert') {
    	if(!validateGamSocApply(this.$('#gamSocApplySearchForm')[0])){ 		
    		return;
    	}        	
    	this.$('#prtAtCode').val(this.$('#sPrtAtCode').val());
    	this.$('#cmplYr').val(this.$('#sCmplYr').val());
    	this.$('#constNo').val(this.$('#sConstNo').val());
    	this.$('#appPrtAtCode').val(this.$('#sAppPrtAtCode').val());
    	this.$('#appAgentCode').val(this.$('#sAppAgentCode').val());
    	this.$('#useNo').val(this.$('#sUseNo').val());
	}	
	if(!validateGamSocApply(this.$('#gamSocApplyDetailForm')[0])){ 		
		return;
	}        	
	if(this._cmd == 'insert') {
		this.insertData();
	} else if (this._cmd == 'modify') {
		this.updateData();
	}
};

//비관리청 신청 삭제
GamSocApplyModule.prototype.deleteData = function() { 
	if(this._cmd == 'modify') {
    	if(confirm('데이터를 삭제하시겠습니까?')) {
        	var opts = this.getApplyData();
        	this.doAction('/soc/gamDeleteSocApplyDetail.do', opts, function(module, result) {
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

//시설물 추가
GamSocApplyModule.prototype.addApplyFacilItem = function() {
	if((this._cmd == 'insert') || (this._cmd == 'modify')) {
		var opts = [{ name: 'prtAtCode', value: this.$('#sPrtAtCode').val()}];
		this.doExecuteDialog('selectFacInfo', '시설물 선택', '/popup/showSocFacCd.do', opts);        		
	} else {
		alert("추가나 조회를 선택한 후에 사용하세요.");
	}
};

//시설물 삭제
GamSocApplyModule.prototype.removeApplyFacilItem = function() {
	if((this._cmd == 'insert') || (this._cmd == 'modify')) {
		var rows = this.$("#socApplyFacilList").selectedRows();
	    if(rows.length == 0){
	        alert("시설물목록에서 삭제할 행을 선택하십시오.");
	        return;
	    }
	    if(this.$("#socApplyFacilList").selectedRowIds().length>0) {
	    	for(var i=this.$("#socApplyFacilList").selectedRowIds().length-1; i>=0; i--) {
	        	this.$("#socApplyFacilList").flexRemoveRow(this.$("#socApplyFacilList").selectedRowIds()[i]);
			}
		}
	} else {
		alert("추가나 조회를 선택한 후에 사용하세요.");
	}
};

//요금 추가
GamSocApplyModule.prototype.addApplyFeeItem = function() {
	if((this._cmd == 'insert') || (this._cmd == 'modify')) {
		var opts = [{ name: 'prtAtCode', value: this.$('#sPrtAtCode').val()}];
		this.doExecuteDialog('selectFeeInfo', '요금종류 선택', '/popup/showSocPayCd.do', opts);        		
	} else {
		alert("추가나 조회를 선택한 후에 사용하세요.");
	}
};

//요금 삭제
GamSocApplyModule.prototype.removeApplyFeeItem = function() {
	if((this._cmd == 'insert') || (this._cmd == 'modify')) {
		var rows = this.$("#socApplyFeeList").selectedRows();
	    if(rows.length == 0){
	        alert("시설물목록에서 삭제할 행을 선택하십시오.");
	        return;
	    }
	    if(this.$("#socApplyFeeList").selectedRowIds().length>0) {
	    	for(var i=this.$("#socApplyFeeList").selectedRowIds().length-1; i>=0; i--) {
	        	this.$("#socApplyFeeList").flexRemoveRow(this.$("#socApplyFeeList").selectedRowIds()[i]);
			}
		}
	} else {
		alert("추가나 조회를 선택한 후에 사용하세요.");
	}
};

/**
 * 정의 된 버튼 클릭 시
 */
GamSocApplyModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'btnSearch':
        	this.loadData();
            break;
        case 'btnNew' : //추가버튼 처리시
        	this._cmd = 'insert';
        	this.initDisplay();
        	break;
        case 'btnSave' : //저장
        	this.saveData();
        	break;
        case 'btnDelete' : //삭제
        	this.deleteData();
        	break;
        case 'popupApplyEntrpsInfo' : //면제요청업체 선택
			this.doExecuteDialog('selectApplyEntrpsInfo', '면제요청업체 선택', '/popup/showSocEntrpsInfo.do', {});
        	break;
        case 'popupEntrpsInfo' : //공사업체 선택
			this.doExecuteDialog('selectEntrpsInfo', '공사업체 선택', '/popup/showSocEntrpsInfo.do', {});
        	break;
        case 'popupSocApplyInfo' : //면제요청 선택
        	var opts = this.makeFormArgs('#gamSocApplySearchForm');
			this.doExecuteDialog('selectApplyInfo', '면제요청 선택', '/popup/showSocApplyInfo.do', opts);
        	break;
        case 'popupSocAgentInfo' : //허가원부 선택
			this.doExecuteDialog('selectAgentInfo', '허가원부 선택', '/popup/showSocAgentFInfo.do', {});
        	break;
        case 'btnAddApplyFacilItem' : //시설물 추가
        	this.addApplyFacilItem();
        	break;
        case 'btnRemoveApplyFacilItem' : //시설물 삭제
        	this.removeApplyFacilItem();
        	break;
        case 'btnAddApplyFeeItem' : //요금 추가
        	this.addApplyFeeItem();
        	break;
        case 'btnRemoveApplyFeeItem' : //요금 삭제 
        	this.removeApplyFeeItem();
        	break;
    }
};

GamSocApplyModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
GamSocApplyModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     case 'selectApplyInfo' : //면제요청 조회
	   	 this.$("#sAppAgentCode").val(value["appAgentCode"]);
	   	 this.$("#sAppAgentName").val(value["appAgentName"]);
	   	 this.$("#sUseNo").val(value["useNo"]);
	   	 this.$("#sAppPrtAtCode").val(value["appPrtAtCode"]);
   	 	 break;
     case 'selectApplyEntrpsInfo' : //공사업체 선택
    	 this.$("#sAppAgentCode").val(value["agentCode"]);
     	 this.$("#sAppAgentName").val(value["firmKorNm"]);
     	 break;
     case 'selectEntrpsInfo' : //공사업체 선택
    	 this.$("#agentCode").val(value["agentCode"]);
     	 this.$("#agentName").val(value["firmKorNm"]);
     	 break;
     case 'selectAgentInfo' : //허가원부 조회
	   	 this.$("#sPrtAtCode").val(value["prtAtCode"]);
	   	 this.$("#sCmplYr").val(value["cmplYr"]);
	   	 this.$("#sConstNo").val(value["constNo"]);
	   	 break;
     case 'selectFacInfo' : //시설물 추가
    	 this.$("#socApplyFacilList").flexAddRow({'facCode': value["facCode"],'facSubCode':value["facSubCode"],'facKorNm':value["facKorNm"]});
     	 break;
     case 'selectFeeInfo' : //요금종류 추가
    	 this.$("#socApplyFeeList").flexAddRow({'feeTp': value["feeTp"],'feeTpKorNm':value["feeTpKorNm"]});
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
                            <th>공사관리청</th>
                            <td>
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">선택</option>
                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.prtAtCode}">${prtAtCdItem.prtKorNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>공사준공년도</th>
                            <td width="250px">
                                <select id="sCmplYr">
                                    <option value="" selected="selected">선택</option>
                                </select>
                            </td>
                            <th>공사번호</th>
                            <td>
                            	<input id="sConstNo" type="text" size="6" maxlength="6">
                            </td>
                            <td style="text-align:right;">
				                <button id="popupSocAgentInfo" class="popupButton">허가원부</button>
                            </td>
                            <td  rowSpan="2">
								<button id="btnSearch" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                        <tr>
                            <th>면제요청청</th>
                            <td>
                                <select id="sAppPrtAtCode">
                                    <option value="" selected="selected">전체</option>
                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>면제요청업체</th>
                            <td>
                                <input id="sAppAgentCode" type="text" size="10" maxlength="9">
                            	<input id="sAppAgentName" type="text" size="6" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupApplyEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <th>요청횟수</th>
                            <td>
                            	<input id="sUseNo" type="text" size="6" maxlength="2">
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
                <li><a href="#tabs1" class="emdTab">업체신청 면제요청내역</a></li>
                <li><a href="#tabs2" class="emdTab">업체신청 면제요청세부</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
            	<div class="emdControlPanel">
					<form id="gamSocApplyDetailForm">
						<input type="hidden" id="prtAtCode"/>
						<input type="hidden" id="cmplYr" />
						<input type="hidden" id="constNo" />
						<input type="hidden" id="appPrtAtCode" />
						<input type="hidden" id="appAgentCode" />
						<input type="hidden" id="useNo" />
    	               	<table class="detailForm"  style="width:100%;">
                            <tr>
                                <th width="12%">보전처리신청금액</th>
                                <td width="38%"><input type="text" id="exmpAmnt" size="35" class="ygpaNumber" /></td>
                                <th>보전처리기간</th>
                                <td>
									<input id="periodFr" type="text" class="emdcal" size="15"> ~ <input id="periodTo" type="text" class="emdcal" size="15">
                                </td>
                            </tr>
                            <tr>
                                <th width="12%">보전처리누계액</th>
                                <td><input type="text" id="exmpAcc" size="35" class="ygpaNumber" /></td>
                                <th>보전처리조건</th>
                                <td>
                                	<select id="exmpCond">
                                		<option value="" selected="selected">선택</option>
	                                    <option value="1">금액</option>
										<option value="2">기간</option>
	                                </select>
                                </td>
                            </tr>
                            <tr>
                            	<th>공사업체</th>
                                <td>
                                	<input id="agentCode" type="text" size="10" maxlength="9">&nbsp; &nbsp;
	                            	<input id="agentName" type="text" size="30" disabled="disabled">&nbsp; &nbsp;
	                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                                </td>
                                <th>적용요율</th>
                                <td>
                                	<select id="rateGubun">
                                		<option value="" selected="selected">선택</option>
	                                    <option value="1">과거</option>
										<option value="2">현재</option>
	                                </select>
                                </td>
                            </tr>
                            <tr>
                                <th>특이사항</th>
                                <td colSpan="3"><input type="text" id="remark" size="140" maxlength="110"></td>
                            </tr>
                            <tr>
                                <th>공사명칭</th>
                                <td><input type="text" id="item" size="35" maxlength="150"></td>
                                <th>사용여부</th>
                                <td>
                                	<select id="useYn">
                                		<option value="" selected="selected">선택</option>
	                                    <option value="Y">보전처리개시</option>
										<option value="N">보전처리중지</option>
	                                </select>
                                </td>
                            </tr>
                            <tr>
                                <th>사업자등록번호</th>
                                <td><input type="text" id="bzRgstId" size="35" maxlength="12"></td>
                                <th>신청일자</th>
                                <td><input id="applDate" type="text" class="emdcal" size="15"></td>
                            </tr>
                        </table><br>
                        <table class="detailForm"  style="width:100%;">
                            <tr>
                            	<th width="2%"></th>
                                <th width="10%" style="text-align:right">외항접안</th>
                                <td><input type="text" id="r11Rate" class="ygpaNumber"></td>
                                <th width="10%" style="text-align:right">내항접안</th>
                                <td><input type="text" id="r12Rate" class="ygpaNumber"></td>
                                <th width="10%" style="text-align:right">외항정박</th>
                                <td><input type="text" id="r21Rate" class="ygpaNumber"></td>
                                <th width="10%" style="text-align:right">내항정박</th>
                                <td><input type="text" id="r22Rate" class="ygpaNumber"></td>
                                <th width="5%"></th>
                            </tr>
                        </table>
					</form>
                </div>
                <table id="socApplyList" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel">
					<form id="form1">
    	               	<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button id="btnNew">추가</button>
	                                <button id="btnSave">저장</button>
	                                <button id="btnDelete">삭제</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="fillHeight" style="overflow:scroll;">
                 <form id="gamSocApplyForm">
                    	<table style="width:100%;">
                    		<tr>
                    			<td width="50%" style="font-weight: bold;"> &gt; 당해시설 및 타인 사용료 징수시 해당시설코드(R1,R2 대상시설)</td>
                    			<td style="font-weight: bold;"> &gt; 투자비보전처리대상 요금종류</td>
                    		</tr>
        	               	<tr>
								<td style="padding-right:15px;">
									<table id="socApplyFacilList" style="display:none" class="fillHeight"></table>
			                        <table style="width:100%;">
				                        <tr>
				                            <td style="text-align: right">
				                                <button id="btnAddApplyFacilItem">추가</button>
				                                <button id="btnRemoveApplyFacilItem">삭제</button>
				                                <button id="btnSave">저장</button>
				                            </td>
				                        </tr>
									</table>
								</td>
								<td style="padding-right:15px;">
									<table id="socApplyFeeList" style="display:none" class="fillHeight"></table>
			                        <table style="width:100%;">
				                        <tr>
				                            <td style="text-align: right">
				                                <button id="btnAddApplyFeeItem">추가</button>
				                                <button id="btnRemoveApplyFeeItem">삭제</button>
				                                <button id="btnSave">저장</button>
				                            </td>
				                        </tr>
									</table>
								</td>
							</tr>
						</table>
                    </form>
            </div>
        </div>
    </div>
</div>