<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamNticArrrgPopup.jsp
  * @Description : 연체세입처리 팝업
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.03.14  장은성          최초 생성
  *
  * author 장은성
  * since 2014.03.14
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamNticArrrgPopupModule() {}

GamNticArrrgPopupModule.prototype = new EmdPopupModule(1000, 620);

// 팝업이 호출 되었을때 호출 되는 함수
GamNticArrrgPopupModule.prototype.loadComplete = function(searchOpt) {

	this.resizable(true);

	// 그리드 설정
    this.$("#marineCenterRentFeeArrrgList").flexigrid({
        module: this,
        url: '<c:url value="/oper/center/selectNticArrrgList.do" />',
        dataType: 'json',
        colModel : [
			{display:'청코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
			{display:'회계년도', name:'accnutYear',width:64, sortable:false,align:'center'},
			{display:'요금종류', name:'chrgeKndNmDisp',width:110, sortable:false,align:'center'},
			{display:'고지번호', name:'nticno',width:60, sortable:false,align:'center'},
			{display:'회계구분', name:'accnutYear',width:64, sortable:false,align:'center'},
			{display:'업체코드', name:'entrpscd',width:64, sortable:false,align:'center'},
			{display:'업체명', name:'entrpsNm',width:100, sortable:false,align:'center'},
			{display:'고지금액', name:'nticAmt',width:100, sortable:false,align:'right', displayFormat:'number'},
			{display:'고지일자', name:'nticDt',width:88, sortable:false,align:'center'},
			{display:'납부기한일자', name:'payTmlmt',width:100, sortable:false,align:'center'},
			{display:'이체상태', name:'icheStatusNm',width:100, sortable:false,align:'center'},
			{display:'결과코드', name:'rsltCode',width:100, sortable:false,align:'center'},
			{display:'연체일수', name:'arrrgDates',width:60, sortable:false,align:'right', displayFormat:'number'},
			{display:'연체요율', name:'arrrgRate',width:60, sortable:false,align:'right', displayFormat:'number'},
			{display:'연체금액', name:'arrrgAmt',width:100, sortable:false,align:'right', displayFormat:'number'},
			{display:'원고지번호', name:'nticno',width:60, sortable:false,align:'center'}
        ],
        showTableToggleBtn: false,
        height: '440',
        preProcess: function(module,data) {
        	$.each(data.resultList, function() {
        		this.chrgeKndNmDisp = this.chrgeKndNm + "("+this.chrgeKnd+")";
        	});

            return data;
        }
    });

	this.$("#marineCenterRentFeeArrrgList").on("onItemDoubleClick", function(event, module, row, grid, param) {

		// 이벤트내에선 모듈에 대해 선택한다.
		module.closeDialog("ok", row);
	});

	this._searchOpts = searchOpt;

	this.$('#arrrgRate').val("12");
	// console.log("popup loadcomplete");
};


// 사용자 설정 함수 추가
GamNticArrrgPopupModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "btnSearch":
		if(this.$('#prtAtCode').val()=="") {
			alert('항구분을 선택 하십시요.');
			this.$('#prtAtcode_select').addClass('ui-state-error');
			return;
		}
		else {
			this.$('#prtAtcode_select').removeClass('ui-state-error');
		}
		this.loadData();
		break;
	case 'btnSaveArrrg':	// 연체 일괄 고지
		if(confirm("조회된 건에 대해서 연체 일괄 고지를 하시겠습니까?")) {
			var searchOpt=this.makeFormArgs("#gamNticArrrgListVO");

	        this.doAction('<c:url value="/oper/center/insertNticArrrgList.do" />', searchOpt, function(module, result) {

	            if(result.resultCode=='0') {
	        		this.loadData();
	            }

	        	alert(result.resultMsg);
	        });
		}
		break;
	case "cancel":
		this.cancelDialog();
	}

};

GamNticArrrgPopupModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamNticArrrgListVO");
 	this.$("#marineCenterRentFeeArrrgList").flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamNticArrrgPopupModule();
</script>
<div class="dialog fillHeight">
	<div class="emdPanel">
		<form id="gamNticArrrgListVO">
			<table class="searchPanel">
				<tbody>
                        <tr>
                            <th style="width: 70px">항코드</th>
                            <td style="width: 80px"><input id="prtAtCode" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM019" /></td>
                            <th style="width: 70px">업체명</th>
                            <td><input id="entrpscd" type="text" size="5"><input id="sEntrpsNm" type="text" size="10"> <button id="popupEntrpsInfo" class="popupButton">업체</button></td>
							<th style="vertical-align: middle;">연체료율 (연 %)</th>
							<td><input id="arrrgRate" class="ygpaNumber" data-column-id="arrrgRate" size="4"/></td>
                            <td rowspan="2"><button id="btnSearch" class="buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                            <th>요금종류</th>
                            <td><input id="chrgeKnd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM024" /></td>
                            <th>납부기한</th>
                            <td><input id="payTmlmtFrom" type="text" class="emdcal" size="8"> ~ <input id="payTmlmtTo" type="text"class="emdcal" size="8"></td>
                            <th>수납 구분</th>
                            <td><input id="rcivSe" class="ygpaCmmnCd" data-code-id="GAM025" data-value="0"/></td>
                        </tr>
				</tbody>
			</table>
		</form>
		</div>
	<div class="emdPanel fillHeight">
		<table id="marineCenterRentFeeArrrgList" style="display: none" class="fillHeight"></table>
		<div class="emdControlPanel" style="vertical-align:middle;">
			<button id="btnSaveArrrg" data-icon="ui-icon-info">연체 고지</button>
		</div>
	</div>
</div>