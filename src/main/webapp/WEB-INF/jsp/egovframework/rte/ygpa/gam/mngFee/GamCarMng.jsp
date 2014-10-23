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
function GamCarMngModule() {}

GamCarMngModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamCarMngModule.prototype.loadComplete = function() {

    // 자산임대 테이블 설정
    this.$("#CarMngList").flexigrid({
        module: this,
        url: '<c:url value="/mngFee/gamSelectCarMngList.do" />',
        dataType: 'json',
        colModel : [
					{display:'차량 등록 번호', 			name:'carRegistNo',	width:100, 		sortable:false,		align:'center'},
                    {display:'차량 명', 	name:'carNm',		width:80, 		sortable:false,		align:'center'},
                    {display:'차량 종류', 			name:'carKnd',	width:80, 		sortable:false,		align:'center'},
                    {display:'차량 용도', 			name:'carPrpos',	width:80, 		sortable:false,		align:'center'},
                    {display:'차대 번호', 	name:'carBodyNo',	width:160, 		sortable:false,		align:'center'},
                    {display:'차량 형식', 	name:'carFmt',	width:100, 		sortable:false,		align:'center'},
                    {display:'배기량', 	name:'exhaustqy',	width:70, 		sortable:false,		align:'center'},
                    {display:'연료 종류', 		name:'fuelKnd',		width:80, 		sortable:false,		align:'center'},
                    {display:'차량 연식', 				name:'carYrMdl',		width:80, 		sortable:false,		align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    this.$("#CarMngList").on('onItemSelected', function(event, module, row, grid, param) {
    	module.$('#cmd').val('modify');
        module.$('#CarMngListDetailForm :input').val('');
        module.makeFormValues('#CarMngListDetailForm', row);
    	module.$('#oldCarRegistNo').val(module.$('#carRegistNo').val());
        module._editData=module.getFormValues('#CarMngListDetailForm', row);
        module._editRow=module.$('#CarMngList').selectedRowIds()[0];

    });
    this.$("#CarMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
    	console.log('debug');
        module.$("#carMngListTab").tabs("option", {active: 1});
        module.$('#cmd').val('modify');
        module.makeFormValues('#CarMngListDetailForm', row);
        module.$('#oldCarRegistNo').val(module.$('#carRegistNo').val());
        module._editData=module.getFormValues('#CarMngListDetailForm', row);
        module._editRow=module.$('#CarMngList').selectedRowIds()[0];
        if(row!=null) {
            module.$('#cmd').val('modify');
        }
    });

};


/**
 * 정의 된 버튼 클릭 시
 */
 GamCarMngModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
//         	if(!validateGamSocAgent(this.$('#gamSocAgentMngtSearchForm')[0])){
//         		return;
//         	}
			this.loadData();
            break;

       // 등록포맷으로 변환 -- 초기화 및 상태값 변경
       case 'btnCarAdd':
			this.$('#CarMngListDetailForm :input').val('');
			this.$("#carMngListTab").tabs("option", {active: 1});
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

        	var inputVO = this.makeFormArgs("#CarMngListDetailForm");

			if(this.$("#cmd").val() == "insert") {

			 	this.doAction('<c:url value="/mngFee/gamInsertCarMngList.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#gamCarMngSearchForm");
						module.$("#CarMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#carMngListTab").tabs("option", {active: 0});
						module.$("#CarMngListDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/mngFee/gamUpdateCarMngList.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#gamCarMngSearchForm");
						module.$("#CarMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#carMngListTab").tabs("option", {active: 0});
						module.$("#CarMngListDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}

            break;

        //차량 삭제
        case 'btnRemoveItem':
        case 'btnCarDel':
			/*
        	if(!validateGamSocAgent(this.$('#gamSocAgentMngtSearchForm')[0])){
        		return;
        	}
        	*/
        	if(confirm("삭제하시겠습니까?")){
				var inputVO = this.makeFormArgs("#CarMngListDetailForm");
			 	this.doAction('<c:url value="/mngFee/gamDeleteCarMngList.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#gamCarMngSearchForm");
			 			module.$("#CarMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#carMngListTab").tabs("option", {active: 0});
						module.$("#CarMngListDetailForm :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
            break;

    }
};


GamCarMngModule.prototype.onSubmit = function() {
    this.loadData();
};

GamCarMngModule.prototype.loadData = function() {
    this.$("#carMngListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamCarMngSearchForm');
    this.$('#CarMngList').flexOptions({params:searchOpt}).flexReload();

};

GamCarMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        break;

    }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamCarMngModule();

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
                            <th>차량 등록번호</th>
                            <td>
									<input type="text" size="10" id="sCarRegistNo">
                            </td>
                            <th>차량 명</th>
                            <td>
									<input type="text" size="10" id="sCarNm">
                            </td>
                            <th>차대 번호</th>
                            <td>
                            		<input id="sCarBodyNo" type="text" size="15">
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
        <div id="carMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">차량정보 리스트</a></li>
                <li><a href="#tabs2" class="emdTab">차량정보 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
					 <table id="CarMngList" style="display:none" class="fillHeight"></table>
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
	                                <button id="btnCarAdd">차량 추가</button>
	                                <button id="btnCarDel">차량 삭제</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="CarMngListDetailForm">
            	        <input type="hidden" id="cmd"/>
            	        <input type="hidden" id="oldCarRegistNo"/>
                        <table class="detailPanel">
                            <tr>
								<th width="20%" height="18">차량 등록 번호</th>
                                <td ><input type="text" size="20" id="carRegistNo"/></td>
								<th width="20%" height="18">차량 종류</th>
                                <td ><input type="text" size="20" id="carKnd"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">차량 용도</th>
                                <td ><input type="text" size="20" id="carPrpos"/></td>
								<th width="20%" height="18">차량 명</th>
                                <td ><input type="text" size="20" id="carNm"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">차량 형식</th>
                                <td ><input type="text" size="20" id="carFmt"/></td>
								<th width="20%" height="18">차량 연식</th>
                                <td ><input type="text" size="20" id="carYrMdl"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">차대 번호</th>
                                <td ><input type="text" size="20" id="carBodyNo"/></td>
								<th width="20%" height="18">원동기 형식</th>
                                <td ><input type="text" size="20" id="turbineFmt"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">사용 본거지</th>
                                <td colspan="3"><input type="text" size="120" id="usageStrhld"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">소유자 주소</th>
                                <td colspan="3"><input type="text" size="120" id="ownerAdres"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">소유자 명</th>
                                <td ><input type="text" size="20" id="ownerNm"/></td>
								<th width="20%" height="18">차량 등록 일자</th>
                                <td ><input type="text" size="20" id="carRegistDt" class="emdcal"/></td>
                            </tr>
                             <tr>
								<th width="20%" height="18">등록 관청</th>
                                <td ><input type="text" size="20" id="registGovOfc"/></td>
								<th width="20%" height="18">제원 관리 번호</th>
                                <td ><input type="text" size="20" id="specMngNo"/></td>
                            </tr>
                             <tr>
								<th width="20%" height="18">차량 길이</th>
                                <td ><input type="text" size="20" id="carLt"/></td>
								<th width="20%" height="18">차량 너비</th>
                                <td ><input type="text" size="20" id="CarWd"/></td>
                            </tr>
                             <tr>
								<th width="20%" height="18">차량 높이</th>
                                <td ><input type="text" size="20" id="carHt"/></td>
								<th width="20%" height="18">차량 총 중량</th>
                                <td ><input type="text" size="20" id="carGrWqnt"/></td>
                            </tr>
                             <tr>
								<th width="20%" height="18">배기량</th>
                                <td ><input type="text" size="20" id="exhaustqy"/></td>
								<th width="20%" height="18">정격 출력</th>
                                <td ><input type="text" size="20" id="rateOutput"/></td>
                            </tr>
                             <tr>
								<th width="20%" height="18">승차 정원</th>
                                <td ><input type="text" size="20" id="rideQuotaCapa"/></td>
								<th width="20%" height="18">최대 적재 량</th>
                                <td ><input type="text" size="20" id="maxCapaQy"/></td>
                            </tr>
                             <tr>
								<th width="20%" height="18">기통 갯수</th>
                                <td ><input type="text" size="20" id="cylinderCnt"/></td>
								<th width="20%" height="18">연료 종류</th>
                                <td ><input type="text" size="20" id="fuelKnd"/></td>
                            </tr>
                             <tr>
								<th width="20%" height="18">연비</th>
                                <td ><input type="text" size="20" id="fuelEfft"/></td>
								<th width="20%" height="18">취득 가격</th>
                                <td ><input type="text" size="20" id="acqPrce"/></td>
                            </tr>
                             <tr>
								<th width="20%" height="18">검사 유효 시작일</th>
                                <td ><input type="text" size="20" id="examValidBeginDt" class="emdcal"/></td>
								<th width="20%" height="18">검사 유효 종료일</th>
                                <td ><input type="text" size="20" id="examValidEndDt" class="emdcal"/></td>
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