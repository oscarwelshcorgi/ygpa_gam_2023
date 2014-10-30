<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamCarMng.jsp
 * @Description : 차량 정보
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.09.22  Lee          최초 생성
 *
 * author Lee
 * since 2014.09.22
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
function GamGasUsageSttusMng() {}


GamGasUsageSttusMng.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamGasUsageSttusMng.prototype.loadComplete = function() {

    // 자산임대 테이블 설정
    this.$("#GasUsageSttusMng").flexigrid({
        module: this,
        url: '<c:url value="/mngFee/gamSelectGasUsageSttusMng.do" />',
        dataType: 'json',
        colModel : [
                    {display:'관리비 시설 코드', 	name:'mngFeeFcltyCd',		width:110, 		sortable:false,		align:'center'},
                    {display:'관리비 업무 구분', 	name:'mngFeeJobSe',		width:110, 		sortable:false,		align:'center'},
					{display:'사용 월', 			name:'usageMt',	width:110, 		sortable:false,		align:'center'},
                    {display:'전월 사용 량', 	name:'prevMtUsageQy',		width:110, 		sortable:false,		align:'right', displayFormat: 'number'},
                    {display:'당월 사용 량', 	name:'saidMtUsageQy',		width:110, 		sortable:false,		align:'right', displayFormat: 'number'},
                    {display:'적용 계수', 	name:'applcCoef',		width:110, 		sortable:false,		align:'right', displayFormat: 'number'},
                    {display:'순 사용 량', 	name:'netUsageQy',		width:110, 		sortable:false,		align:'right', displayFormat: 'number'},
                    /*
					{display:'등록자', 			name:'regUsr',	width:110, 		sortable:false,		align:'center'},
                    {display:'등록일시', 	name:'registDt',		width:110, 		sortable:false,		align:'center'}
                    */
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    this.$("#GasUsageSttusMng").on('onItemSelected', function(event, module, row, grid, param) {
    	module.$('#cmd').val('modify');
        module.$('#GasUsageSttusMngDetailForm :input').val('');
        module.makeFormValues('#GasUsageSttusMngDetailForm', row);
    	module.$('#oldCarRegistNo').val(module.$('#carRegistNo').val());
        module._editData=module.getFormValues('#GasUsageSttusMngDetailForm', row);
        module._editRow=module.$('#GasUsageSttusMng').selectedRowIds()[0];

    });
    this.$("#GasUsageSttusMng").on('onItemDoubleClick', function(event, module, row, grid, param) {
    	console.log('debug');
        module.$("#GasUsageSttusMngTab").tabs("option", {active: 1});
        module.$('#cmd').val('modify');
        module.makeFormValues('#GasUsageSttusMngDetailForm', row);
        module.$('#oldCarRegistNo').val(module.$('#carRegistNo').val());
        module._editData=module.getFormValues('#GasUsageSttusMngDetailForm', row);
        module._editRow=module.$('#GasUsageSttusMng').selectedRowIds()[0];
        if(row!=null) {
            module.$('#cmd').val('modify');
        }
    });

};


/**
 * 정의 된 버튼 클릭 시
 */
 GamGasUsageSttusMng.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
//         	if(!validateGamSocAgent(this.$('#gamSocAgentMngtSearchForm')[0])){
//         		return;
//         	}
			this.loadData();
            break;

       // 등록포맷으로 변환 -- 초기화 및 상태값 변경
       case 'btnAdd':
			this.$('#GasUsageSttusMngDetailForm :input').val('');
			this.$("#GasUsageSttusMngTab").tabs("option", {active: 1});
			this.$("#cmd").val("insert");
            break;


        // 신청저장
        case 'btnSaveItem':
			/*
        	if(!validateGamSocAgent(this.$('#gamSocAgentMngtSearchForm')[0])){
        		return;
        	}
        	if(!validateGamSocAgentDetail(this.$('#form1')[0])){
        		return;
        	}
        	*/

        	var inputVO = this.makeFormArgs("#GasUsageSttusMngDetailForm");

			if(this.$("#cmd").val() == "insert") {

			 	this.doAction('<c:url value="/mngFee/gamInsertGasUsageSttusMng.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#gamCarMngSearchForm");
						module.$("#GasUsageSttusMng").flexOptions({params:searchOpt}).flexReload();
						module.$("#GasUsageSttusMngTab").tabs("option", {active: 0});
						module.$("#GasUsageSttusMngDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/mngFee/gamUpdateGasUsageSttusMng.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#gamCarMngSearchForm");
						module.$("#GasUsageSttusMng").flexOptions({params:searchOpt}).flexReload();
						module.$("#GasUsageSttusMngTab").tabs("option", {active: 0});
						module.$("#GasUsageSttusMngDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}

            break;

        //차량 삭제
        case 'btnRemoveItem':
        case 'btnDel':
			/*
        	if(!validateGamSocAgent(this.$('#gamSocAgentMngtSearchForm')[0])){
        		return;
        	}
        	*/
        	if(this.$('#GasUsageSttusMng').selectedRowIds()[0] == undefined && this.$('#GasUsageSttusMng').selectedRowIds()[0] == null){
     	    	alert('목록을 선택 하십시오.');
     	    	return;
     	    }
        	if(confirm("삭제하시겠습니까?")){
				var inputVO = this.makeFormArgs("#GasUsageSttusMngDetailForm");
			 	this.doAction('<c:url value="/mngFee/gamDeleteGasUsageSttusMng.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#gamCarMngSearchForm");
			 			module.$("#GasUsageSttusMng").flexOptions({params:searchOpt}).flexReload();
						module.$("#GasUsageSttusMngTab").tabs("option", {active: 0});
						module.$("#GasUsageSttusMngDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
            break;


        case 'popupMngFeeFcltyCdF': // 팝업을 호출한다.(조회)
            /*
            var opts = {
                'gisAssetsPrtAtCode': this.$('#prtAtCode').val(),
                'gisAssetsCd': this.$('#gisAssetsCd').val(),
                'gisAssetsSubCd': this.$('#gisAssetsSubCd').val()
            };
            */
            var opts;

            this.doExecuteDialog('selectMngCodePopup', '시설 코드', '<c:url value="/popup/showMngCode.do"/>', opts);
            break;
    }
};


GamGasUsageSttusMng.prototype.onSubmit = function() {
    this.loadData();
};

GamGasUsageSttusMng.prototype.loadData = function() {
    this.$("#GasUsageSttusMngTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamCarMngSearchForm');
    this.$('#GasUsageSttusMng').flexOptions({params:searchOpt}).flexReload();

};

GamGasUsageSttusMng.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        break;

    }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamGasUsageSttusMng();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamCarMngSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>가스 사용년도</th>
                            <td>
                            	<select id="sUsageMt">
                                    <option value="">선택</option>
                                    <c:forEach items="${yearsList}" var="yearListItem">
                                        <option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>시설구분</th>
                            <td>
									<input type="text" size="10" id="sMngFeeFcltySeNm">
                            </td>
                            <td>
									<button id="searchBtn" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="GasUsageSttusMngTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">가스 사용현황 </a></li>
                <li><a href="#tabs2" class="emdTab">가스 사용현황 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
					 <table id="GasUsageSttusMng" style="display:none" class="fillHeight"></table>
                <div id="agentListSum" class="emdControlPanel">
					<form id="form2">
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                                <button id="btnAdd">가스사용 추가</button>
	                                <button id="btnDel">가스사용 삭제</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="GasUsageSttusMngDetailForm">
            	        <input type="hidden" id="cmd"/>
            	        <input type="hidden" id="oldMngFeeFcltySe"/>
                        <table class="detailPanel">
                             <tr>
								<th width="20%" height="18">사용 월</th>
                                <td >
	                                <select id="usageMtYear">
	                                    <option value="">선택</option>
	                                    <c:forEach items="${yearsList}" var="yearListItem">
	                                        <option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
	                                    </c:forEach>
	                                </select>
	                                <select id="usageMtMon">
	                                    <option value="">선택</option>
	                                    <c:forEach items="${monList}" var="monListItem">
	                                        <option value="${monListItem.code }">${monListItem.codeNm }</option>
	                                    </c:forEach>
	                                </select>
                                </td>
                                <td>
	                                	<input type="text" id="usageMt" >
	                            </td>
                             </tr>
                             <tr>
	                             <th width="20%" height="18">관리비 시설코드</th>
	                                <td ><input type="text" size="20" id="mngFeeFcltyCd" readonly="readonly"/></td>
								 <th width="20%" height="18">관리비 업무구분</th>
	                                <td ><input type="text" size="2" id="mngFeeJobSe" readonly="readonly"/>
	                                </td>
	                                <td>
	                                <button id="popupMngFeeFcltyCdF" class="buttonSave">시설코드 조회</button>
	                                </td>
	                             </tr>
                             <tr>
								<th width="20%" height="18">당월 사용 량</th>
                                <td ><input type="text" size="20" id="saidMtUsageQy" /></td>
								<th width="20%" height="18">전월 사용 량</th>
                                <td ><input type="text" size="20" id="prevMtUsageQy" /></td>
                                <td><button id="prevMtUsageQyChk" class="buttonSave">전월 사용량</button></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">적용 계수</th>
                                <td ><input type="text" size="20" id="applcCoef" /></td>
								<th width="20%" height="18">순 사용 량</th>
                                <td ><input type="text" size="20" id="netUsageQy" /></td>
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