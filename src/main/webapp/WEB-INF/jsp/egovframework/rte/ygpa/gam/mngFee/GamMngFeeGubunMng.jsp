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
function GamMngFeeGubunMng() {}


GamMngFeeGubunMng.prototype = new EmdModule(600, 500);

// 페이지가 호출 되었을때 호출 되는 함수
GamMngFeeGubunMng.prototype.loadComplete = function() {

    // 자산임대 테이블 설정
    this.$("#MngFeeGubunMng").flexigrid({
        module: this,
        url: '<c:url value="/mngFee/gamSelectMngFeeGubunMng.do" />',
        dataType: 'json',
        colModel : [
					{display:'시설구분', 		name:'mngFeeFcltySe',		width:70, 		sortable:false,		align:'center'},
                    {display:'시설구분 명', 	name:'mngFeeFcltySeNm',		width:150, 		sortable:false,		align:'left'},
					{display:'등록자', 			name:'regUsr',				width:100, 		sortable:false,		align:'center'},
                    {display:'등록일시', 		name:'registDt',			width:150, 		sortable:false,		align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    this.$("#MngFeeGubunMng").on('onItemSelected', function(event, module, row, grid, param) {
    	module.$('#cmd').val('modify');
    	module.$('#mngFeeFcltySe').attr('readonly','readonly');
        module.$('#MngFeeGubunMngDetailForm :input').val('');
        module.makeFormValues('#MngFeeGubunMngDetailForm', row);
    	module.$('#oldCarRegistNo').val(module.$('#carRegistNo').val());
        module._editData=module.getFormValues('#MngFeeGubunMngDetailForm', row);
        module._editRow=module.$('#MngFeeGubunMng').selectedRowIds()[0];

    });
    this.$("#MngFeeGubunMng").on('onItemDoubleClick', function(event, module, row, grid, param) {
    	console.log('debug');
        module.$("#MngFeeGubunMngTab").tabs("option", {active: 1});
        module.$('#cmd').val('modify');
        module.makeFormValues('#MngFeeGubunMngDetailForm', row);
        module.$('#oldCarRegistNo').val(module.$('#carRegistNo').val());
        module._editData=module.getFormValues('#MngFeeGubunMngDetailForm', row);
        module._editRow=module.$('#MngFeeGubunMng').selectedRowIds()[0];
        if(row!=null) {
            module.$('#cmd').val('modify');
        }
    });

};


/**
 * 정의 된 버튼 클릭 시
 */
 GamMngFeeGubunMng.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
//         	if(!validateGamSocAgent(this.$('#gamSocAgentMngtSearchForm')[0])){
//         		return;
//         	}
			this.loadData();
            break;

       // 등록포맷으로 변환 -- 초기화 및 상태값 변경
       case 'btnGubunAdd':
			this.$('#MngFeeGubunMngDetailForm :input').val('');
			this.$('#mngFeeFcltySe').removeAttr('readonly');
			this.$("#MngFeeGubunMngTab").tabs("option", {active: 1});
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

        	var inputVO = this.makeFormArgs("#MngFeeGubunMngDetailForm");

			if(this.$("#cmd").val() == "insert") {

			 	this.doAction('<c:url value="/mngFee/gamInsertMngFeeGubunMng.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#gamCarMngSearchForm");
						module.$("#MngFeeGubunMng").flexOptions({params:searchOpt}).flexReload();
						module.$("#MngFeeGubunMngTab").tabs("option", {active: 0});
						module.$("#MngFeeGubunMngDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/mngFee/gamUpdateMngFeeGubunMng.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#gamCarMngSearchForm");
						module.$("#MngFeeGubunMng").flexOptions({params:searchOpt}).flexReload();
						module.$("#MngFeeGubunMngTab").tabs("option", {active: 0});
						module.$("#MngFeeGubunMngDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}

            break;

        //차량 삭제
        case 'btnRemoveItem':
        case 'btnGubunDel':
			/*
        	if(!validateGamSocAgent(this.$('#gamSocAgentMngtSearchForm')[0])){
        		return;
        	}
        	*/
        	if(confirm("삭제하시겠습니까?")){
				var inputVO = this.makeFormArgs("#MngFeeGubunMngDetailForm");
			 	this.doAction('<c:url value="/mngFee/gamDeleteMngFeeGubunMng.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#gamCarMngSearchForm");
			 			module.$("#MngFeeGubunMng").flexOptions({params:searchOpt}).flexReload();
						module.$("#MngFeeGubunMngTab").tabs("option", {active: 0});
						module.$("#MngFeeGubunMngDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
            break;

        case 'idChk':  //id 중복 확인

        	if(this.$("#mngFeeFcltySe").val() == ""){
				this.$("#mngFeeFcltySe").focus();
				alert("코드를 입력하십시오.");
				return;
			}
			this.doAction('<c:url value="/mngFee/gamcheckSeFeeGubunMng.do" />', {checkSe : this.$("#mngFeeFcltySe").val()}, function(module, result) {
		 		if(result.resultCode == 0){
		 			if(result.checkSeCnt != "0"){
		 				alert("이미 사용중인 코드가 존재합니다.");
		 				module.$("#mngFeeFcltySe").focus();
		 				module.$("#mngFeeFcltySe").val("");
		 			}else{
		 				if(confirm("해당 코드를 사용하시겠습니까?")){
		 					module.$("#mngFeeFcltySe").val(result.checkSe);
// 		 					module.$("#checkId").val("ok");
		 					module.$("#mngFeeFcltySe").attr("readonly","readonly");
		 				}else{
		 					module.$("#mngFeeFcltySe").val("");
// 		 					module.$("#checkId").val("");
		 					module.$("#mngFeeFcltySe").focus();
		 				}
		 			}
		 		}
		 	});

        	break;
    }
};


GamMngFeeGubunMng.prototype.onSubmit = function() {
    this.loadData();
};

GamMngFeeGubunMng.prototype.loadData = function() {
    this.$("#MngFeeGubunMngTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamCarMngSearchForm');
    this.$('#MngFeeGubunMng').flexOptions({params:searchOpt}).flexReload();

};

GamMngFeeGubunMng.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        break;

    }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamMngFeeGubunMng();

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
                            <th>시설 구분</th>
                            <td>
									<input type="text" size="10" id="sMngFeeFcltySe" maxlength="2">
                            </td>
                            <th>시설 구분 명</th>
                            <td>
									<input type="text" size="10" id="sMngFeeFcltySeNm" maxlength="20">
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
        <div id="MngFeeGubunMngTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">관리비 시설구분</a></li>
                <li><a href="#tabs2" class="emdTab">관리비 시설구분 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
					 <table id="MngFeeGubunMng" style="display:none" class="fillHeight"></table>
                <div id="agentListSum" class="emdControlPanel">
					<form id="form2">
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                                <button id="btnGubunAdd">추가</button>
	                                <button id="btnGubunDel">삭제</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="MngFeeGubunMngDetailForm">
            	        <input type="hidden" id="cmd"/>
            	        <input type="hidden" id="oldMngFeeFcltySe"/>
                        <table class="detailPanel">
                             <tr>
								<th width="20%" height="18">시설 구분</th>
                                <td ><input type="text" size="20" id="mngFeeFcltySe" maxlength="2"/></td>
                                <td><button id="idChk">중복체크</button>
                            </tr>
                            <tr>
								<th width="20%" height="18">시설 구분 명</th>
                                <td colspan="2"><input type="text" size="20" id="mngFeeFcltySeNm" maxlength="20"/></td>
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