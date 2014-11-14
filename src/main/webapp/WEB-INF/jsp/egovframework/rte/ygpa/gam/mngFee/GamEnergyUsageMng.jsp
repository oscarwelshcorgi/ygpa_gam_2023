<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamEnergyUsageMng.jsp
 * @Description : 에너지 사용량 계수 관리
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

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamEnergyUsageMng() {}


GamEnergyUsageMng.prototype = new EmdModule(900, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamEnergyUsageMng.prototype.loadComplete = function() {

    this.$("#EnergyUsageMng").flexigrid({
        module: this,
        url: '<c:url value="/mngFee/gamSelectEnergyUsageMng.do" />',
        dataType: 'json',
        colModel : [
                    {display:'연료 코드', 			name:'fuelCd',				width:80, 		sortable:false,		align:'center'	},
                    {display:'연료 명', 			name:'fuelNm',				width:150, 		sortable:false,		align:'left'	},
                    {display:'에너지 단위', 		name:'energyUnit',			width:100, 		sortable:false,		align:'left'	},
					{display:'에너지 총발열량', 	name:'energyTotalCalVal',	width:100, 		sortable:false,		align:'right'	},
					{display:'에너지 순발열량',		name:'energyNetCalVal',		width:100, 		sortable:false,		align:'right'	},
					{display:'온실가스 단위', 		name:'grHseUnit',			width:100, 		sortable:false,		align:'left'	},
					{display:'온실가스 계수', 		name:'grHseCoef',			width:100, 		sortable:false,		align:'right'	}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            module.$('#totalCount').val(data.totalCount);
            module.$('#yearCount').val(data.yearCount);
            module.makeDivValues('#energyUsageListSum', data);
            return data;
        }
    });


    this.$("#EnergyUsageMng").on('onItemSelected', function(event, module, row, grid, param) {
    	module.$('#cmd').val('modify');
        module.$('#EnergyUsageMngDetailForm :input').val('');
        module.makeFormValues('#EnergyUsageMngDetailForm', row);
        module._editData=module.getFormValues('#EnergyUsageMngDetailForm', row);
        module._editRow=module.$('#EnergyUsageMng').selectedRowIds()[0];

    });
    this.$("#EnergyUsageMng").on('onItemDoubleClick', function(event, module, row, grid, param) {
    	//console.log('debug');
        module.$("#EnergyUsageMngTab").tabs("option", {active: 1});
        module.$('#cmd').val('modify');
        module.makeFormValues('#EnergyUsageMngDetailForm', row);
        module._editData=module.getFormValues('#EnergyUsageMngDetailForm', row);
        module._editRow=module.$('#EnergyUsageMng').selectedRowIds()[0];
        if(row!=null) {
            module.$('#cmd').val('modify');
        }
    });

    this._loadCheck=false; // LOAD 여부

    //console.log("debug");

};


/**
 * 정의 된 버튼 클릭 시
 */
 GamEnergyUsageMng.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
			this.loadData();
            break;

       // 등록포맷으로 변환 -- 초기화 및 상태값 변경
       case 'btnAdd':
			this.$('#EnergyUsageMngDetailForm :input').val('');
			this.$("#EnergyUsageMngTab").tabs("option", {active: 1});
			this.$("#cmd").val("insert");
            break;


        // 신청저장
        case 'btnSaveItem':
        	var inputVO = this.makeFormArgs("#EnergyUsageMngDetailForm");

			if(this.$("#cmd").val() == "insert") {

			 	this.doAction('<c:url value="/mngFee/gamInsertEnergyUsageMng.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#EnergyUsageMngSearchForm");
						module.$("#EnergyUsageMng").flexOptions({params:searchOpt}).flexReload();
						module.$("#EnergyUsageMngTab").tabs("option", {active: 0});
						module.$("#EnergyUsageMngDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/mngFee/gamUpdateEnergyUsageMng.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#EnergyUsageMngSearchForm");
						module.$("#EnergyUsageMng").flexOptions({params:searchOpt}).flexReload();
						module.$("#EnergyUsageMngTab").tabs("option", {active: 0});
						module.$("#EnergyUsageMngDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}

            break;

        //차량 삭제
        case 'btnRemoveItem':
        case 'btnDel':
        	if(this.$('#EnergyUsageMng').selectedRowIds()[0] == undefined && this.$('#EnergyUsageMng').selectedRowIds()[0] == null){
     	    	alert('목록을 선택 하십시오.');
     	    	return;
     	    }
        	if(confirm("삭제하시겠습니까?")){
				var inputVO = this.makeFormArgs("#EnergyUsageMngDetailForm");
			 	this.doAction('<c:url value="/mngFee/gamDeleteEnergyUsageMng.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#EnergyUsageMngSearchForm");
			 			module.$("#EnergyUsageMng").flexOptions({params:searchOpt}).flexReload();
						module.$("#EnergyUsageMngTab").tabs("option", {active: 0});
						module.$("#EnergyUsageMngDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
            break;

        case 'btnCopy':
            var sQueryMngYear = this.$('#sMngYear').val();
            var nYearCount = this.$('#yearCount').val()*1;

        	if(this._loadCheck == false){
     	    	alert('자료를 먼저 조회하십시오.');
     	    	return;
     	    }
        	if(nYearCount > 0){
     	    	alert('[' + sQueryMngYear + '년] 자료가 존재합니다.');
     	    	return;
     	    }
        	if(confirm("이전년도의 자료를 [" + sQueryMngYear + "년] 자료로 복사하시겠습니까?")){
	        	var inputVO = this.makeFormArgs("#EnergyUsageMngSearchForm");

			 	this.doAction('<c:url value="/mngFee/gamCopyEnergyUsageMng.do" />', inputVO, function(module, result) {
			 		alert(result.resultMsg);
			 		if(result.resultCode == "0"){
				 		//alert(result.resultMsg + '[' + result.resultCode + ']');
			 			var searchOpt = module.makeFormArgs("#EnergyUsageMngSearchForm");
						module.$("#EnergyUsageMng").flexOptions({params:searchOpt}).flexReload();
						module.$("#EnergyUsageMngTab").tabs("option", {active: 0});
						module.$("#EnergyUsageMngDetailForm :input").val("");
			 		}
			 	});
        	}

            break;
    }
};


GamEnergyUsageMng.prototype.onSubmit = function() {
    this.loadData();
};

GamEnergyUsageMng.prototype.loadData = function() {
    this.$("#EnergyUsageMngTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#EnergyUsageMngSearchForm');
    this.$('#EnergyUsageMng').flexOptions({params:searchOpt}).flexReload();
	this._loadCheck=true;
};

GamEnergyUsageMng.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        break;

    }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamEnergyUsageMng();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="EnergyUsageMngSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                           <th>에너지 사용년도</th>
                            <td>
                            	<select id="sMngYear">
                                    <option value="">선택</option>
                                    <c:forEach items="${yearsList}" var="yearListItem">
                                        <option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>연료 코드</th>
                            <td>
								<input type="text" size="10" id="sFuelCd">
                            </td>
                            <th>연료 명</th>
                            <td>
								<input type="text" size="20" id="sFuelNm">
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
        <div id="EnergyUsageMngTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">에너지 사용량</a></li>
                <li><a href="#tabs2" class="emdTab">에너지 사용량 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
					 <table id="EnergyUsageMng" style="display:none" class="fillHeight"></table>
                <div id="energyUsageListSum" class="emdControlPanel">
					<form id="form2">
						<table style="width:100%;" class="summaryPanel">
	                        <tr>
								<th width="10%" height="20">조회 자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">년도 자료수</th>
								<td><input type="text" size="12" id="yearCount" class="ygpaNumber" disabled="disabled" /></td>
	                            <td style="text-align: right">
	                                <button id="btnAdd">에너지 사용량 추가</button>
	                                <button id="btnDel">에너지 사용량 삭제</button>
	                                <button id="btnCopy">에너지 사용량 복사</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="EnergyUsageMngDetailForm">
            	        <input type="hidden" id="cmd"/>
            	        <input type="hidden" id="oldMngFeeFcltySe"/>
                        <table class="detailPanel" style="width:100%;">
                             <tr>
								<th width="20%" height="18">연료 코드</th>
                                <td ><input type="text" size="35" id="fuelCd" /></td>
								<th width="20%" height="18">연료 명</th>
                                <td ><input type="text" size="35" id="fuelNm" /></td>
                            </tr>
                             <tr>
                          	   <th width="20%" height="18">관리 년도</th>
                                <td ><input type="text" size="35" id="mngYear" /></td>
								<th width="20%" height="18">에너지 단위</th>
                                <td ><input type="text" size="35" id="energyUnit" /></td>

                            </tr>
                             <tr>
                         	    <th width="20%" height="18">에너지 총발열량</th>
                                <td ><input type="text" size="35" id="energyTotalCalVal" /></td>
								<th width="20%" height="18">에너지 순발열량</th>
                                <td ><input type="text" size="35" id="energyNetCalVal" /></td>

                            </tr>
                             <tr>
                             	<th width="20%" height="18">온실가스 단위</th>
                                <td ><input type="text" size="35" id="grHseUnit" /></td>
								<th width="20%" height="18">온실가스 계수</th>
                                <td ><input type="text" size="35" id="grHseCoef" /></td>
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