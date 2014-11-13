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
function GamMngFeeCodeMng() {}


GamMngFeeCodeMng.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamMngFeeCodeMng.prototype.loadComplete = function() {

    // 자산임대 테이블 설정
    this.$("#MngFeeCodeMng").flexigrid({
        module: this,
        url: '<c:url value="/mngFee/gamSelectMngFeeCodeMng.do" />',
        dataType: 'json',
        colModel : [
					{display:'관리비 시설 코드', 	name:'mngFeeFcltyCd',	width:110, 		sortable:false,		align:'center'},
					{display:'관리비 시설 명', 		name:'mngFeeFcltyNm',	width:150, 		sortable:false,		align:'left'},
                    {display:'관리비 시설 구분', 	name:'mngFeeFcltySeNm',	width:110, 		sortable:false,		align:'left'},
                    {display:'관리비 업무 구분', 	name:'mngFeeJobSeNm',	width:110, 		sortable:false,		align:'left'},
					{display:'등록자', 				name:'regUsr',			width:100, 		sortable:false,		align:'center'},
                    {display:'등록일시', 			name:'registDt',		width:150, 		sortable:false,		align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    this.$("#MngFeeCodeMng").on('onItemSelected', function(event, module, row, grid, param) {
    	module.$('#cmd').val('modify');
    	module.$('#mngFeeFcltyCd').attr('readonly','readonly');
        module.$('#MngFeeCodeMngDetailForm :input').val('');
        module.makeFormValues('#MngFeeCodeMngDetailForm', row);
        module.makeDivValues('#MngFeeCodeMngDetailForm', row);
        module._editData=module.getFormValues('#MngFeeCodeMngDetailForm', row);
        module._editRow=module.$('#MngFeeCodeMng').selectedRowIds()[0];

    });
    this.$("#MngFeeCodeMng").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#MngFeeCodeMngTab").tabs("option", {active: 1});
        module.$('#cmd').val('modify');
        module.makeFormValues('#MngFeeCodeMngDetailForm', row);
        module.makeDivValues('#MngFeeCodeMngDetailForm', row);
        module._editData=module.getFormValues('#MngFeeCodeMngDetailForm', row);
        module._editRow=module.$('#MngFeeCodeMng').selectedRowIds()[0];
        if(row!=null) {
            module.$('#cmd').val('modify');
        }
    });

};


/**
 * 정의 된 버튼 클릭 시
 */
 GamMngFeeCodeMng.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
//         	if(!validateGamSocAgent(this.$('#gamSocAgentMngtSearchForm')[0])){
//         		return;
//         	}
			this.loadData();
            break;

       case 'btnCodeAdd':
			this.$('#MngFeeCodeMngDetailForm :input').val('');
			this.$("#MngFeeCodeMngTab").tabs("option", {active: 1});
			this.$("#cmd").val("insert");
			this.$('#mngFeeFcltyCd').removeAttr('readonly');

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

        	var inputVO = this.makeFormArgs("#MngFeeCodeMngDetailForm");

			if(this.$("#cmd").val() == "insert") {

			 	this.doAction('<c:url value="/mngFee/gamInsertMngFeeCodeMng.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#gamCarMngSearchForm");
						module.$("#MngFeeCodeMng").flexOptions({params:searchOpt}).flexReload();
						module.$("#MngFeeCodeMngTab").tabs("option", {active: 0});
						module.$("#MngFeeCodeMngDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/mngFee/gamUpdateMngFeeCodeMng.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#gamCarMngSearchForm");
						module.$("#MngFeeCodeMng").flexOptions({params:searchOpt}).flexReload();
						module.$("#MngFeeCodeMngTab").tabs("option", {active: 0});
						module.$("#MngFeeCodeMngDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}

            break;

        case 'btnRemoveItem':
        case 'btnCodeDel':
			/*
        	if(!validateGamSocAgent(this.$('#gamSocAgentMngtSearchForm')[0])){
        		return;
        	}
        	*/
        	if(confirm("삭제하시겠습니까?")){
				var inputVO = this.makeFormArgs("#MngFeeCodeMngDetailForm");
			 	this.doAction('<c:url value="/mngFee/gamDeleteMngFeeCodeMng.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#gamCarMngSearchForm");
			 			module.$("#MngFeeCodeMng").flexOptions({params:searchOpt}).flexReload();
						module.$("#MngFeeCodeMngTab").tabs("option", {active: 0});
						module.$("#MngFeeCodeMngDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
            break;

    }
};


GamMngFeeCodeMng.prototype.onSubmit = function() {
    this.loadData();
};

GamMngFeeCodeMng.prototype.loadData = function() {
    this.$("#MngFeeCodeMngTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamCarMngSearchForm');
    this.$('#MngFeeCodeMng').flexOptions({params:searchOpt}).flexReload();

};

GamMngFeeCodeMng.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        break;

    }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamMngFeeCodeMng();

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
                            <th>관리비 시설 코드</th>
                            <td>
									<input type="text" size="10" id="sMngFeeFcltySe">
                            </td>
                            <th>관리비 시설 명</th>
                            <td>
									<input type="text" size="20" id="sMngFeeFcltySeNm">
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
        <div id="MngFeeCodeMngTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">관리비 시설코드</a></li>
                <li><a href="#tabs2" class="emdTab">관리비 시설코드 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
					 <table id="MngFeeCodeMng" style="display:none" class="fillHeight"></table>
                <div id="agentListSum" class="emdControlPanel">
					<form id="form2">
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                                <button id="btnCodeAdd">추가</button>
	                                <button id="btnCodeDel">삭제</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="MngFeeCodeMngDetailForm">
            	        <input type="hidden" id="cmd"/>
            	        <input type="hidden" id="oldMngFeeFcltySe"/>
                        <table class="detailPanel" style="width:100%">
                             <tr>
								<th width="20%" height="18">시설 코드</th>
                                <td ><input type="text" size="20" id="mngFeeFcltyCd" maxlength="4"/></td>
								<th width="20%" height="18">시설 업무 구분</th>
                                <td >
                                	<select id="mngFeeJobSe">
                                		<option value="M">마린센터</option>
                                		<option value="E">전기시설</option>
                                	</select>
                                	<!--
                                	<input type="text" size="10" id="mngFeeJobSe" maxlength="1"/>
                                	 -->
                                	<span data-column-id="mngFeeJobSeNm"></span>
                                </td>
                            </tr>
                             <tr>
								<th width="20%" height="18">시설명</th>
                                <td ><input type="text" size="20" id="mngFeeFcltyNm" maxlength="20"/></td>
								<th width="20%" height="18">시설 구분</th>
                                <td >
                                	<input type="text" size="10" id="mngFeeFcltySe" maxlength="2" />
                                	<span data-column-id="mngFeeFcltySeNm"></span>
                                </td>
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