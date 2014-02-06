<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @Class Name : GamCntnrQuayRentfeeInqire.jsp
  * @Description : 컨테이너부두임대료조회 (항만시설/컨테이너부두/컨테이너부두임대료조회)
  * @Modification Information
  * 
  *   수정일          수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.01.14  domh          최초 생성
  *
  * author domh
  * since 2014.01.14
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamOperCodeModule() {}

GamOperCodeModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamOperCodeModule.prototype.loadComplete = function() {
	this.$('#prtAtCode').val('820');	// 기본 항코드 설정
	
	// 테이블 설정
	this.$("#operResultList").flexigrid({
		module: this,
		url: '<c:url value="/oper/htld/selectGamHtldRentfeeMngt.do"/>',
		dataType: 'json',
		colModel : [
			{display:'항코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
			{display:'관리 번호(조합)', name:'rentMngNo',width:60, sortable:false,align:'center'},
			{display:'관리년도', name:'mngYear',width:100, sortable:false,align:'center'},
			{display:'관리 번호', name:'mngNo',width:60, sortable:false,align:'center'},                                                
			{display:'관리 횟수', name:'mngCnt',width:60, sortable:false,align:'center'},                                              
			{display:'업체코드', name:'entrpscd',width:60, sortable:false,align:'center'},                                           
			{display:'날짜', name:'dt',width:60, sortable:false,align:'center'},                                                                     
			{display:'신청 구분 코드', name:'reqstSeCd',width:60, sortable:false,align:'center'},                                 
			{display:'총 면적', name:'grAr',width:60, sortable:false,align:'center'},                                                           
			{display:'총 사용료', name:'grFee',width:60, sortable:false,align:'center'},                                                        
			{display:'고지 방법', name:'nticMth',width:60, sortable:false,align:'center'},                                            
			{display:'최초 허가 일자', name:'frstPrmisnDt',width:60, sortable:false,align:'center'},                           
			{display:'허가 일자', name:'prmisnDt',width:60, sortable:false,align:'center'},                                          
			{display:'허가 여부', name:'prmisnYn',width:60, sortable:false,align:'center'},                                          
			{display:'총 사용 기간 FROM', name:'grUsagePdFrom',width:60, sortable:false,align:'center'},                     
			{display:'총 사용 기간 TO', name:'grUsagePdTo',width:60, sortable:false,align:'center'},                           
			{display:'문서 번호', name:'docNo',width:60, sortable:false,align:'center'},                                                
			{display:'비고', name:'rm',width:60, sortable:false,align:'center'},                                                         
			{display:'코멘트', name:'cmt',width:60, sortable:false,align:'center'},                                                          
			{display:'기타', name:'etc',width:60, sortable:false,align:'center'},                                                       
			{display:'등록자', name:'regUsr',width:60, sortable:false,align:'center'},                                                
			{display:'등록일시', name:'registDt',width:60, sortable:false,align:'center'},                                                       
			{display:'수정자', name:'updUsr',width:60, sortable:false,align:'center'},                                                
			{display:'수정일시', name:'updtDt',width:60, sortable:false,align:'center'},                                                           
			{display:'총 감면 사용료', name:'grRdcxptFee',width:60, sortable:false,align:'center'},                                         
			{display:'GIS 코드', name:'gisCd',width:60, sortable:false,align:'center'},                                               
			{display:'부서코드', name:'deptcd',width:60, sortable:false,align:'center'}
			],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: '290',
		preProcess: returnData
	});
	
	this.$("#operResultList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#operManageTab").tabs("option", {active: 1});	// 탭을 전환 한다.
		module.$('#prtAtCode2').val(row['prtAtCode']);
		module.$('#mngYear').val(row['mngYear']);
		module.$('#mngNo').val(row['mngNo']);
		module.$('#entrpscd').val(row['entrpscd']);
		module.$('#dt').val(row['dt']);
		module.$('#reqstSeCd').val(row['reqstSeCd']);
		// 해당하는 자산 목록을 불러온다/
		var searchOpt=module.makeFormArgs('#searchForm');
		//this.showAlert(searchOpt);
	 	module.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
	});
	
};

function returnData(module, data) {
    module.$('#totalRetCnt').val(data.resultInfo.sumCnt);
	module.$('#totalArea').val(data.resultInfo.sumGrAr);
	module.$('#totalFee').val(data.resultInfo.sumGrFee);
    return data;
}
		
// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
GamOperCodeModule.prototype.showModuleAlert = function(msg) {
	//this.getSelect(msg);	
	this.$('#prtCode').val(msg);
};

GamOperCodeModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	
	case 'selectOperSearch':
		var searchOpt=this.makeFormArgs('#operForm');
	 	this.$('#operResultList').flexOptions({params:searchOpt}).flexReload(); 
		break;
	
	}
};

GamOperCodeModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	
	case 'tabs1':
		this.$('#searchViewStack')[0].changePanelId(0);
		break;
	/*
	case 'tabs2':
		this.$('#searchViewStack')[0].changePanelId(1);
		break;
	case 'tabs3':
		this.$('#searchViewStack')[0].changePanelId(1);
		break;
	*/
	}
};

GamOperCodeModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
};

GamOperCodeModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#operForm');
	//this.showAlert(searchOpt);
 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
//	this.$('#assetList').flexOptions(searchOpt).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamOperCodeModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId }'/>
<div class="window_main">
	<div class="emdPanel">
		<div id="searchViewStack" class="viewStack">
			<div class="viewPanel">
			<form id="operForm">
				<table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>   
                            <td>
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">선택</option>
                                    <option value="P01">P01</option>
                                    <option value="P02">P02</option>
                                   	<!-- 
                                    <c:forEach  items="${erpAssetClsList}" var="clsItem">
                                        <option value="${clsItem.smCls }">${clsItem.smClsName }</option>
                                    </c:forEach>
                                     -->
                                </select>
                            </td>
                            <th>신청구분</th>
                            <td>
                                <select id="sReqstSeCd">
                                    <option value="" selected="selected">선택</option>
                                    <option value="A">최초</option>
                                    <option value="L">연장</option>
                                </select>
                            </td>
                            <th>신청업체</th>
                            <td>
                                <input id="sEntrpscd" type="text" size="6">
                            </td>
                            <th>사용용도</th>
                            <td>
                                <select id="assetCls">
                                    <option value="" selected="selected">선택</option>
                                    <option value="11">사용용도1</option>
                                    <option value="22">사용용도2</option>
                                </select>
                            </td>
                            <td rowSpan="2"><button id="selectOperSearch" class="submit">조회</button></td>
                        </tr>
                        <tr>
                            <th>관리번호</th>
                            <td>
                                <input id="sMngNo" type="text" size="4"> <input id="itemName" type="text" size="3"> <input id="itemName" type="text" size="2">
                            </td>
                            <th>승낙구분</th>
                            <td width="200px">
                                <select id="sPrmisnYn">
                                    <option value="" selected="selected">선택</option>
                                    <option value="Y">Y</option>
                                    <option value="N">N</option>
                                </select>
                            </td>
                            <th>사용기간</th>
                            <td>
                            <input id="sGrUsagePdFrom" type="text" class="emdcal"
                                size="8"> ~ <input id="sGrUsagePdTo" type="text"
                                class="emdcal" size="8">
                            </td>
                            <th>총면적</th>
                            <td>
                                <input id="sGrAr" type="text" size="5">
                            </td>
                        </tr>
                    </tbody>
                </table>
			</form>
			</div>
		</div>
	</div>

	<div class="emdPanel">
		<div id="operManageTab" class="emdTabPanel" style="height:100%;" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">컨테이너부두임대료조회</a></li>
				<li><a href="#tabs2" class="emdTab">컨테이너부두임대료상세</a></li>
				<!--
				<li><a href="#tabs3" class="emdTab">자산사진</a></li>
				-->
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
				<div style="width: 100%; height: 100%; overflow:auto">
						<table id="operResultList" style="display:none"></table>
				</div>
				<div class="emdControlPanel">
                    <table style="width:100%;" >
                        <tr>
                            <td>
						       <form id="form1">             
						                    합계
						                    자료수 <input id="totalRetCnt" size="7" readonly>
						                    총면적 <input id="totalArea" type="text" size="7" readonly>
						                    총사용료 <input id="totalFee" type="text" size="7" readonly>원
						       </form>
                            </td>
                            <td>
                            	<!-- 
                                <button id="addAssetRentRenew">연장신청</button>
                                <button id="addAssetRentFirst">최초신청</button>
                                 -->
                            </td>
                        </tr>
                    </table>
                </div>
			</div>
			
			<div id="tabs2" class="emdTabPage" style="overflow: scroll;" data-onactivate="onShowTab2Activate">
				<!-- 
				<table id="assetCodeList" style="display:none"></table>
				<div class="emdControlPanel"><button id="addAssetGisCdItem">추가</button><button id="removeAssetGisCd">삭제</button><button id="loadMap">지도보기</button></div>
				 -->
				<div style="width: 100%; height: 100%; overflow:auto">
				<table height="200">
					<tr>
						<th><span class="label">항코드</span></th>
						<td><input type="text" size="8" id="prtAtCode2" /></td>
					</tr>
					<tr>
						<th><span class="label">관리년도</span></th>
						<td><input type="text" size="8" id="mngYear"/></td>
					</tr>
					<tr>
						<th><span class="label">관리번호</span></th>
						<td><input type="text" size="8" id="mngNo"/></td>
					</tr>
					<tr>
						<th><span class="label">업체코드</span></th>
						<td><input type="text" size="8" id="entrpscd"/></td>
					</tr>
					<tr>
						<th><span class="label">날짜</span></th>
						<td><input type="text" size="8" id="dt"/></td>
					</tr>
					<tr>
						<th><span class="label">신청구분코드</span></th>
						<td><input type="text" size="8" id="reqstSeCd"/></td>
					</tr>
				</table>
				</div>
				<!-- 
				<div style="vertical-align: bottom; text-align: right;">
					<input type="reset" value="취소" class="input_1"> <input
						type="submit" value="저장" class="input_1">
				</div>
				 -->
			</div>
							
			<!-- 				
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;" data-onactivate="onShowTab3Activate">
				<table id="assetCodePhotoList" style="display:none"></table>
				<div class="emdControlPanel"><button id="addAssetGisPhoto">추가</button><button id="removeAssetGisPhoto">삭제</button></div>
				<div class="emdPanel" style="overflow:scroll"><img style="border: 1px solid #000; max-width:800px; max-height: 600px" src="<c:url value='images/egovframework/ygpa/gam/misc/TEST2.JPG'/>"></div>
			</div>
			 -->
			 
		</div>
	</div>
</div>
