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
function CarRefuelSttusMngModule() {}

CarRefuelSttusMngModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
CarRefuelSttusMngModule.prototype.loadComplete = function() {

    // 자산임대 테이블 설정
    this.$("#CarRefuelSttusMngList").flexigrid({
        module: this,
        url: '<c:url value="/mngFee/gamSelectCarRefuelSttusMng.do" />',
        dataType: 'json',
        colModel : [
					{display:'연료 구분', 			name:'fuelKnd',	width:100, 		sortable:false,		align:'center'},
                    {display:'차량 명', 	name:'carNm',		width:100, 		sortable:false,		align:'center'},
                    {display:'차량 등록 번호', 			name:'carRegistNo',	width:100, 		sortable:false,		align:'center'},
                    {display:'합계', 	name:'total',		width:60, 		sortable:false,		align:'center'},
                    {display:'소계', 	name:'mtotal',		width:60, 		sortable:false,		align:'center'},
                    {display:'1월', 	name:'m1',		width:40, 		sortable:false,		align:'center'},
                    {display:'2월', 	name:'m2',		width:40, 		sortable:false,		align:'center'},
                    {display:'3월', 	name:'m3',		width:40, 		sortable:false,		align:'center'},
                    {display:'4월', 	name:'m4',		width:40, 		sortable:false,		align:'center'},
                    {display:'5월', 	name:'m5',		width:40, 		sortable:false,		align:'center'},
                    {display:'6월', 	name:'m6',		width:40, 		sortable:false,		align:'center'},
                    {display:'7월', 	name:'m7',		width:40, 		sortable:false,		align:'center'},
                    {display:'8월', 	name:'m8',		width:40, 		sortable:false,		align:'center'},
                    {display:'9월', 	name:'m9',		width:40, 		sortable:false,		align:'center'},
                    {display:'10월', 	name:'m10',		width:40, 		sortable:false,		align:'center'},
                    {display:'11월', 	name:'m11',		width:40, 		sortable:false,		align:'center'},
                    {display:'12월', 	name:'m12',		width:40, 		sortable:false,		align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    this.$("#CarRefuelSttusMngList").on('onItemSelected', function(event, module, row, grid, param) {
        module.$('#CarRefuelSttusMngListDetailForm :input').val('');
        module.makeFormValues('#CarRefuelSttusMngListDetailForm', row);
        module._editData=module.getFormValues('#CarRefuelSttusMngListDetailForm', row);
        module._editRow=module.$('#CarRefuelSttusMngList').selectedRowIds()[0];
    	module.$('#cmd').val('modify');

    });
    this.$("#CarRefuelSttusMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#carCarRefuelSttusMngListTab").tabs("option", {active: 1});
        module.makeFormValues('#CarRefuelSttusMngListDetailForm', row);
        module._editData=module.getFormValues('#CarRefuelSttusMngListDetailForm', row);
        module._editRow=module.$('#CarRefuelSttusMngList').selectedRowIds()[0];
        module.$('#cmd').val('modify');
        if(row!=null) {
            module.$('#cmd').val('modify');
        }
    });

    this.$('#sRefuelMt').on('change', {module: this}, function(event) {
        event.data.module.$('#refuelMt').val(event.data.module.$('#sRefuelMt').val());
    });

    /*
    this.$('input:checkbox').on('change', {module: this}, function(event) {
    	console.log('debug1');
    	if(event.data.module.$(this).is(":checked")){
    		event.data.module.$(this).attr("id","chk");
    	}else{
    		event.data.module.$(this).attr("id","Dechk");
    		alert(event.data.module.$(this).attr("id"));
    	}
    });
    */
};

CarRefuelSttusMngModule.prototype.drawChart = function() {
	/*
	var barChart =  new dhtmlXChart({
        view:"bar",
        container: this.$('#fuelChart'),
            value:"#sales#",
        color:"#66cc33",
                width:30,
                tooltip: "#year#",
                xAxis:{
            title:"Sales per year",
            template:"#year#"
        },
        yAxis:{
                        start:3,
                        end:9,
                        step:3,
                        template:"{obj}",
            title:"Sales,mil"
    	}
	});
	*/
}

/**
 * 정의 된 버튼 클릭 시
 */
CarRefuelSttusMngModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
//         	if(!validateGamSocAgent(this.$('#gamSocAgentMngtSearchForm')[0])){
//         		return;
//         	}

    	    this.$('#refuelMt').val(this.$('#sRefuelMt').val());
			this.loadData();
            break;

       case 'btnCarRefuelAdd': // 차량주유 추가
    	    if(this.$('#CarRefuelSttusMngList').selectedRowIds()[0] == undefined && this.$('#CarRefuelSttusMngList').selectedRowIds()[0] == null){
    	    	alert('차량을 선택을 하십시오.');
    	    	return;
    	    }
			this.$('#CarRefuelSttusMngListDetailForm :input').val('');
      		this.makeFormValues('#CarRefuelSttusMngListDetailForm', this.$("#CarRefuelSttusMngList").flexGetRow(this.$('#CarRefuelSttusMngList').selectedRowIds()[0]));
			this.$("#carCarRefuelSttusMngListTab").tabs("option", {active: 1});
			this.$("#cmd").val("insert");
            break;


        case 'btnSaveItem':	//저장
			/*
        	if(!validateGamSocAgent(this.$('#gamSocAgentMngtSearchForm')[0])){
        		return;
        	}
        	if(!validateGamSocAgentDetail(this.$('#form1')[0])){
        		return;
        	}
        	*/

        	var inputVO = this.makeFormArgs("#CarRefuelSttusMngListDetailForm");
			if(this.$("#cmd").val() == "insert") {

			 	this.doAction('<c:url value="/mngFee/gamInsertCarRefuelSttusMng.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#CarRefuelSttusMngSearchForm");
						module.$("#CarRefuelSttusMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#carCarRefuelSttusMngListTab").tabs("option", {active: 0});
						module.$("#CarRefuelSttusMngListDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/mngFee/gamUpdateCarRefuelSttusMng.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#CarRefuelSttusMngSearchForm");
						module.$("#CarRefuelSttusMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#carCarRefuelSttusMngListTab").tabs("option", {active: 0});
						module.$("#CarRefuelSttusMngListDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}

            break;

    }
};


CarRefuelSttusMngModule.prototype.onSubmit = function() {
    this.loadData();
};

CarRefuelSttusMngModule.prototype.loadData = function() {
    this.$("#carCarRefuelSttusMngListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#CarRefuelSttusMngSearchForm');
    this.$('input[name="check"]:checked').each(function(){
        searchOpt[searchOpt.length]={
        name: 'check',
        value: this.value
        };
       });

    this.$('#CarRefuelSttusMngList').flexOptions({params:searchOpt}).flexReload();
};

CarRefuelSttusMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
    	this.drawChart();
        break;

    }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new CarRefuelSttusMngModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="CarRefuelSttusMngSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>주유 년도</th>
                            <td>
                            	<select id="sRefuelMt">
                                    <option value="">선택</option>
                                    <c:forEach items="${yearsList}" var="yearListItem">
                                        <option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>

                            <th>차대 번호</th>
                            <td>
                            		<input id="sCarRegistNo" type="text" size="15">
                            </td>
                            <td rowspan="2">
									<button id="searchBtn" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                        <tr>
                        	<th>주유 구분</th>
                           		 <td colspan="4">
                           		 <!--
                           		  -->
									휘발류<input type="checkbox" size="10" name="check" style="vertical-align: middle;" value="휘발류" checked="checked" class="chk">
									경유<input type="checkbox" size="10" name="check" style="vertical-align: middle;" value="경유"	checked="checked"	class="chk">
									LPG<input type="checkbox" size="10" name="check" style="vertical-align: middle;" value="LPG"	checked="checked"	class="chk">
									전기<input type="checkbox" size="10" name="check" style="vertical-align: middle;" value="전기"	checked="checked"	class="chk">
									하이브리드<input type="checkbox" size="10" name="check" style="vertical-align: middle;" value="HYBRID"	checked="checked"	class="chk">
									기타<input type="checkbox" size="10" name="check" style="vertical-align: middle;" value="기타"	checked="checked"	class="chk">
                           		 </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="carCarRefuelSttusMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">차량주유 현황</a></li>
                <li><a href="#tabs2" class="emdTab">차량주유 현황 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
					 <table id="CarRefuelSttusMngList" style="display:none" class="fillHeight"></table>
                <div id="agentListSum" class="emdControlPanel">
					<form id="form2">
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                                <button id="btnCarRefuelAdd">차량주유 등록</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="CarRefuelSttusMngListDetailForm">
            	        <input type="hidden" id="cmd"/>
            	        <input type="hidden" id="refuelMt"/>
                        <table class="detailPanel">
                            <tr>
								<th width="20%" height="18">차량 등록 번호</th>
                                <td ><input type="text" size="20" id="carRegistNo" readonly="readonly"/></td>
								<th width="20%" height="18">연료 구분</th>
                                <td ><input type="text" size="20" id="fuelKnd" readonly="readonly"/></td>
								<th width="20%" height="18">차량 명</th>
                                <td ><input type="text" size="20" id="carNm" readonly="readonly"/></td>
                            </tr>
                        </table>
                        <table class="detailPanel" style="100%">
                            <tr>
	                            <th style="width:120px;">1월</th>
	                            <td style="width:120px;"><input type="text" size="10" id="m1"></td>
	                            <td rowspan="12">
	                            	<div id="fuelChart" style="width:280px;height:250px;border:1px solid #A4BED4;"></div>
	                            </td>
	                        <tr>
	                            <th>2월</th>
	                            <td><input type="text" size="10" id="m2">
	                        </td>
	                        <tr>
	                            <th>3월</th>
	                            <td><input type="text" size="10" id="m3">
	                        </td>
	                        <tr>
	                            <th>4월</th>
	                            <td><input type="text" size="10" id="m4">
                            </td>
	                        <tr>
	                            <th>5월</th>
	                            <td><input type="text" size="10" id="m5">
							</td>
	                        <tr>
	                            <th>6월</th>
	                            <td><input type="text" size="10" id="m6">
	                        </td>
	                        <tr>
	                        	<th>7월</th>
	                            <td><input type="text" size="10" id="m7">
	                       </td>
	                        <tr><th>8월</th>
	                            <td><input type="text" size="10" id="m8">
                            </tr>
                            <tr>
	                            <th>9월</th>
	                            <td><input type="text" size="10" id="m9">
	                         </td>
	                        <tr>
	                        	<th>10월</th>
	                            <td><input type="text" size="10" id="m10">
	                        </td>
	                        <tr>
	                        	<th>11월</th>
	                            <td><input type="text" size="10" id="m11">
	                        </td>
	                        <tr>
	                        	<th>12월</th>
	                            <td><input type="text" size="10" id="m12">
                            </tr>
                        </table>
                    </form>
	                 <table style="width:100%">
	                    <tr>
	                        <td width="100"></td>
	                        <td style="text-align:right">
	                        	<button id="btnSaveItem" class="buttonSave">저장</button>
	                        </td>
	                    </tr>
	                 </table>
                 </div>
            </div>
        </div>
    </div>
</div>