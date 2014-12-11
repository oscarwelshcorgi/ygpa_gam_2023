<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocAgent.jsp
 * @Description : 비관리청허가원부 (비관리청관리/비관리청허가원부)
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.09.19  lsl          최초 생성
 *
 * author lsl
 * since 2014.09.19
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<validator:javascript formName="gamSocAgentMngtSearchForm" method="validateGamSocAgent" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="form1" method="validateGamSocAgentDetail" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSocAgentMngtModule() {}

GamSocAgentMngtModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocAgentMngtModule.prototype.loadComplete = function() {

    // 자산임대 테이블 설정
    this.$("#socAgentMngtList").flexigrid({
        module: this,
        url: '/soc/gamSelectSocAgentList.do',
        dataType: 'json',
        colModel : [
					{display:'업체코드', 			name:'agentCode',	width:80, 		sortable:false,		align:'center'},
                    {display:'업체명', 			name:'firmKorNm',	width:160, 		sortable:false,		align:'center'},
                    {display:'허가원부일련번호', 	name:'constNo',		width:110, 		sortable:false,		align:'center'},
                    {display:'보전처리대상금액', 	name:'totalAmnt',	width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'보전처리누계액', 		name:'accFee',		width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'비고', 				name:'remark',		width:240, 		sortable:false,		align:'left'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
        	
        	module._socAgentInfo = data.socAgentInfo;
        	
        	//그리드 상단 입력창에 정보 입력
        	if(data.socAgentInfo){
        		if(data.resultCode == '0'){
	        		module.makeFormValues('#form1',data.socAgentInfo);
		        	
		        	//항만공사시행허가원부II 정보입력
	        		module.makeFormValues('#gamSocAgentForm',data.socAgentInfo);
        		}
	        	
        		module.$("#cmd").val("modify");
        		
       		}else{
       			//console.log('debug');
       			if(data.resultCode == '0'){
	       			module.makeFormValues('#form1',{});
	       			//항만공사시행허가원부II 초기화
	        		module.makeFormValues('#gamSocAgentForm',{});
       			}
	        	module.$("#cmd").val("insert");
       			
       		}
        	
			//자료수, 합산금액 입력
            module.$('#totalCount').val($.number(data.totalCount));
            module.$('#sumTotalAmnt').val($.number(data.sumTotalAmnt));
            module.$('#sumAccFee').val($.number(data.sumAccFee));

            return data;
        }
    });
    
    
	// 연도 셀렉트 옵션에 뿌리기
	var toDate = new Date();
	var toYear = toDate.getFullYear();
	
	var option = "";
	for(var i = 2000;i<=toYear;i++){
		option = option + "<option value='" + i + "'>" + i + "년</option>";
	}
	this.$("#sCmplYr").append(option);
	
	this.$("#cmplYr").append(option);

};


GamSocAgentMngtModule.prototype.makeRegiFormat = function() {
	
	this.makeFormValues('#gamSocAgentMngtSearchForm',{});
    this.makeFormValues('#form1',{});
    this.makeFormValues('#form2',{});
    this.makeFormValues('#gamSocAgentForm',{});
    
    this.$('#socAgentMngtList').flexEmptyData();
    
    this.$("#cmd").val("insert");

};


GamSocAgentMngtModule.prototype.saveItem = function() {
	
	if(!validateGamSocAgentDetail(this.$('#form1')[0])){ 		
		return;
	}

	var inputVO = [];
	
	var all_rows = JSON.stringify(this.$('#socAgentMngtList').flexGetData());
	var updateData = JSON.stringify(this.getFormValues("#form1"));
	var updateData1 = JSON.stringify(this.getFormValues("#gamSocAgentForm"));
	
	inputVO[inputVO.length] = {name: 'updateList',value: all_rows};
	inputVO[inputVO.length] = {name: 'updateData',value: updateData};
	inputVO[inputVO.length] = {name: 'updateData1',value: updateData1};
	
	if(this.$("#cmd").val() == "insert") {
	 	this.doAction('/soc/gamInsertSocAgentList.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			var searchOpt = module.makeFormArgs("#gamSocAgentMngtSearchForm");
				module.$("#socAgentMngtList").flexOptions({params:searchOpt}).flexReload();
				module.$("#socAgentListTab").tabs("option", {active: 0});
	 		}
	 		alert(result.resultMsg);
	 	});
	}else{
	 	this.doAction('/soc/gamUpdateSocAgentList.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			var searchOpt = module.makeFormArgs("#gamSocAgentMngtSearchForm");
				module.$("#socAgentMngtList").flexOptions({params:searchOpt}).flexReload();
				module.$("#socAgentListTab").tabs("option", {active: 0});
	 		}
	 		alert(result.resultMsg);
	 	});
	}

};


GamSocAgentMngtModule.prototype.removeItem = function() {
	
	if(!validateGamSocAgent(this.$('#gamSocAgentMngtSearchForm')[0])){ 		
		return;
	}
	
	var inputVO = [];
	var searchData = JSON.stringify(this.getFormValues("#form1"));

	inputVO[inputVO.length] = {name: 'searchData',value: searchData};
	this.doAction('/soc/gamDeleteSocAgentList.do', inputVO, function(module, result) {
 		if(result.resultCode == "0"){
 			var searchOpt = module.makeFormArgs("#gamSocAgentMngtSearchForm");
			module.$("#socAgentMngtList").flexOptions({params:searchOpt}).flexReload();
			module.$("#socAgentListTab").tabs("option", {active: 0});
 		}
 		alert(result.resultMsg);
 	});

};


/**
 * 정의 된 버튼 클릭 시
 */
 GamSocAgentMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {
            
		// 등록포맷으로 변환 -- 초기화 및 상태값 변경
		case 'btnRegiItem':
			this.makeRegiFormat();
			break;
       
        // 신청저장
        case 'btnSaveItem':
			this.saveItem();
            break;

        //신청삭제
        case 'btnRemoveItem':
        	if( confirm("삭제하시겠습니까?") ) {
        		this.removeItem();
        	}
            break;
            
        case 'popupSocAgentFInfo': // 허가원부선택 팝업을 호출한다.(조회)
            var opts;
            this.doExecuteDialog('selectSocAgentFInfoPopup', '허가원부선택', '/popup/showSocAgentFInfo.do', opts);
            break;

        case 'popupEntrpsInfo': // 업체선택 팝업을 호출한다.(조회)
            var opts;
            this.doExecuteDialog('selectSocEntrpsInfoPopup', '업체 선택', '/popup/showSocEntrpsInfo.do', opts);
            break;
            
        case 'btnPopupSaveSocAgent':
    		var all_rows = this.$('#socAgentMngtList').flexGetData();
    		this.doExecuteDialog("addSocAgentPopup", "항만공사시행허가원부추가", '/popup/showSocAgent.do', {},all_rows);
            break;

    }
};


GamSocAgentMngtModule.prototype.onSubmit = function() {
	if(!validateGamSocAgent(this.$('#gamSocAgentMngtSearchForm')[0])){ 		
		return;
	}
    this.loadData();
};

GamSocAgentMngtModule.prototype.loadData = function() {
	
    this.$("#socAgentListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamSocAgentMngtSearchForm');

    this.$('#socAgentMngtList').flexOptions({params:searchOpt}).flexReload();

};

GamSocAgentMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
GamSocAgentMngtModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'selectSocEntrpsInfoPopup':
			if (msg != 'cancel') {
			    this.$('#agentCode').val(value.agentCode);
			    this.$('#agentName').val(value.firmKorNm);
			//this.loadData();
			} else {
			    alert('취소 되었습니다');
			}
			break;
		case 'selectSocAgentFInfoPopup':
			if (msg != 'cancel') {
			    this.$('#sPrtAtCode').val(value.prtAtCode);
			    this.$('#sCmplYr').val(value.cmplYr);
			    this.$('#sConstNo').val(value.constNo);
				this.loadData();
			} else {
			    alert('취소 되었습니다');
			}
			break;
		case 'addSocAgentPopup':
			if(this['_socAgentInfo']==undefined){
				this['_socAgentInfo'] = null;
			}
			this.$("#socAgentMngtList").flexEmptyData();
			this.$("#socAgentMngtList").flexAddData({resultList: value, socAgentInfo:this._socAgentInfo });
			
			var all_rows = this.$('#socAgentMngtList').flexGetData();
			
			var sumAccFee = 0;
			for(i=0;i<all_rows.length;i++){
				sumAccFee = parseInt(sumAccFee) + parseInt(all_rows[i]["accFee"]);
			}
			
			this.$('#accFee').val($.number(sumAccFee));
			 
			break;
		default:
			alert('알수없는 팝업 이벤트가 호출 되었습니다.');
			break;
		}
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSocAgentMngtModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocAgentMngtSearchForm">
            
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>
                            <td>
                                <!-- <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /> -->
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">전체</option>
                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>공사준공년도</th>
                            <td width="100px">
                                <select id="sCmplYr">
                                    <option value="" >선택</option>
                                </select>
                            </td>
                            <th>공사일련번호</th>
                            <td>
                            	<input id="sConstNo" type="text" size="15">
                            </td>
                            <td style="text-align:right;">
				                <button id="popupSocAgentFInfo" class="popupButton">허가원부선택</button>
                            </td>
                            <td>
								<button class="buttonSearch">조회</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="socAgentListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">항만공사시행허가원부I</a></li>
                <li><a href="#tabs2" class="emdTab">항만공사시행허가원부II</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
                 <div class="emdControlPanel">
					<form id="form1">
						<input type="hidden" id="cmd" value="insert" />
    	               	<table class="detailForm"  style="width:100%;">
    	               		<tr>
	                            <th>*항코드</th>
	                            <td>
	                                <!-- <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /> -->
	                                <select id="prtAtCode">
	                                    <option value="" selected="selected">전체</option>
	                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                        <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
	                                    </c:forEach>
	                                </select>
	                            </td>
	                            <th>공사준공년도</th>
	                            <td width="100px">
	                                <select id="cmplYr">
	                                    <option value="" >선택</option>
	                                </select>
	                            </td>
	                            <th>공사일련번호</th>
	                            <td>
	                            	<input id="constNo" type="text" size="15" maxlength="6">
	                            </td>
	                        </tr>
                            <tr>
                                <th width="16%">*공사항만코드</th>
                                <td colspan="3">
                                	<select id="socPrtAtCode">
	                                    <option value="" selected="selected">전체</option>
	                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                        <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
	                                    </c:forEach>
	                                </select>
                                    <!-- <input type="text" size="4" id="prtAtCodeStr" disabled/> -->
                                </td>
                                <th width="16%">대표자</th>
                                <td><input type="text" id="agentOwner" maxlength="26"></td>
                            </tr>
                            <tr>
                                <th width="16%">*공사명</th>
                                <td colspan="3"><input type="text" id="socCnstNm" size="55" maxlength="26"></td>
                                <th width="16%">*공사승인일자</th>
                                <td><input id="aprvDt" type="text" class="emdcal" size="20"></td>
                            </tr>
                            <tr>
                                <th width="16%">위치</th>
                                <td colspan="3"><input type="text" id="cnstLoc" size="55" maxlength="26"></td>
                                <th width="16%">*공사허가일자</th>
                                <td><input id="perfDt" type="text" class="emdcal" size="20"></td>
                            </tr>
                            <tr>
                                <th width="16%">*업체코드</th>
                                <td colspan="3">
                                	<input id="agentCode" type="text" size="10" maxlength="9">&nbsp; &nbsp;
	                            	<input id="agentName" type="text" size="15">&nbsp; &nbsp;
	                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                                </td>
                                <th width="16%">*공사준공일자</th>
                                <td><input id="cmplDt" type="text" class="emdcal" size="20"></td>
                            </tr>
                            <tr>
                                <th width="16%">주소</th>
                                <td colspan='5'><input type="text" id="agentAddr" size="120" maxlength="26"></td>
                            </tr>
                            <tr>
                                <th width="16%">*총공사금액</th>
                                <td colspan="3"><input type="text" id="totalAmnt" class="ygpaNumber" size="55" maxlength="13" ></td>
                                <th width="16%">보전처리누계액</th>
                                <td><input id="accFee" type="text" class="ygpaNumber" size="20" maxlength="13" disabled="disabled"></td>
                            </tr>
                        </table>
                        <table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button id="btnRegiItem">등록</button>
	                                <button id="btnSaveItem">저장</button>
	                                <button id="btnRemoveItem">삭제</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
                
                <table id="socAgentMngtList" style="display:none" class="fillHeight"></table>
                
                <div id="agentListSum" class="emdControlPanel">
					<form id="form2">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="17%" height="20">자료수</th>
								<td><input type="text" size="6" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="22%" height="20">총보전처리대상금액</th>
								<td><input type="text" size="18" id="sumTotalAmnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="22%" height="20">총보전처리누계액</th>
								<td><input type="text" size="18" id="sumAccFee" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                                <button id="btnPopupSaveSocAgent">행추가/삭제</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamSocAgentForm">
                        <table class="detailPanel">
                            <tr>
								<th width="20%" height="18">목적</th>
                                <td colspan="3"><input type="text" size="120" id="socObj" maxlength="26"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">시행기간</th>
                                <td colspan="3"><input type="text" size="120" id="socGigian" maxlength="26"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">국가비귀속 대상시설</th>
                                <td colspan="3"><input type="text" size="120" id="socPrivate" maxlength="26"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">국가귀속 대상시설</th>
                                <td colspan="3"><input type="text" size="120" id="socNation" maxlength="26"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">시행면적</th>
                                <td colspan="3"><input type="text" size="120" id="socWidth" class="ygpaNumber" maxlength="26" /></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">조사비</th>
                                <td><input type="text" size="45" id="reserachAmnt" class="ygpaNumber" maxlength="13"/></td>
                                <th width="20%" height="18">순공사비</th>
                                <td><input type="text" size="45" id="pureAmnt" class="ygpaNumber" maxlength="13"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">기타비용</th>
                                <td colspan="3">
                                	<input type="text" size="30" id="extraAmnt1" class="ygpaNumber" maxlength="13"/>
                                	<input type="text" size="30" id="extraAmnt2" class="ygpaNumber" maxlength="13"/>
                                	<input type="text" size="30" id="extraAmnt3" class="ygpaNumber" maxlength="13"/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">주요허가조건</th>
                                <td colspan="3"><input type="text" size="120" id="primeTxt" maxlength="33"/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">변경사항</th>
                                <td colspan="3"><input type="text" size="120" id="modifyTxt" maxlength="33"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">착공일</th>
                                <td><input id="startDt" type="text" class="emdcal" size="20"></td>
                                <th width="20%" height="18">변경일자</th>
                                <td><input id="modifyDt1" type="text" class="emdcal" size="20"></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">무상사용허가기간</th>
                                <td colspan="3"><input id="freefrDt" type="text" class="emdcal" size="20"> ~ <input id="freetoDt" type="text" class="emdcal" size="20"></td>
							</tr>
							<tr>
								<th width="10%" height="18">시설관리권등록일</th>
                                <td colspan="3"><input id="manageDt" type="text" class="emdcal" size="20"></td>
							</tr>
							<tr>
								<th width="10%" height="18">무상사용승인일</th>
                                <td colspan="3"><input id="freeuseDt" type="text" class="emdcal" size="20"></td>
							</tr>
							<tr>
								<th width="10%" height="18">재산귀속일</th>
                                <td colspan="3"><input id="assetDt" type="text" class="emdcal" size="20"></td>
							</tr>
							<tr>
								<th width="10%" height="18">타인사용징수승인일</th>
                                <td colspan="3"><input id="otherDt" data-column-id="otherDt" type="text" class="emdcal" size="20"></td>
							</tr>
                        </table>
                    </form>
	                 <table style="width:100%">
	                    <tr>
	                        <td width="100"></td>
	                        <td style="text-align:right">
	                        	<button id="btnSaveItem" class="buttonSave">저장</button>
	                            <button id="btnRemoveItem" class="buttonDelete">삭제</button>
	                        </td>
	                    </tr>
	                 </table>
                 </div>
            </div>
        </div>
    </div>
</div>