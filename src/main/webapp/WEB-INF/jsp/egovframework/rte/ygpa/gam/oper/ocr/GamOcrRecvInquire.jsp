<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld"%>
<%--
  @Class Name : GamOcrRecvInquire.jsp
  @Description : 포트미스 연계 OCR 수납처리 결과 조회
  @Modification Information

     수정일         수정자                   수정내용
    ------------ 	----------	---------------------------
    2017.08.25  	jongmin     최초 생성

   author jongmin
   since 2017.08.25

  Copyright (C) 2013 by LFIT  All right reserved.
--%>
<script>
<%--
 	아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
--%>
function GamOcrRecvInquireModule() {}

<%--
	EmdModule을 상속하여 모듈 클래스를 정의한다.
--%>
GamOcrRecvInquireModule.prototype = new EmdModule(1400, 600);

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdModule Override 및 이벤트 처리 정의 부분 시작	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	페이지가 호출 되었을때 호출 되는 함수
--%>
GamOcrRecvInquireModule.prototype.loadComplete = function() {
    this.$("#mainGrid").flexigrid({
        module: this,
        url: '/oper/ocr/selectOcrRecvInquireList.do',
        dataType: 'json',
        colModel : [
					{display:'항코드', name:'prtAtCode',width:40, sortable:false,align:'center'},
					{display:'요금종류', name:'feeTp',width:55, sortable:false,align:'center'},
					{display:'회계년도', name:'fiscalYr',width:55, sortable:false,align:'center'},
					{display:'고지번호', name:'billNo',width:60, sortable:false,align:'center'},
					{display:'연체횟수', name:'dlySerNo',width:55, sortable:false,align:'center'},
					{display:'고지일자', name:'billDt',width:80, sortable:false,align:'center'},
					{display:'이체일자', name:'ocrDt',width:80, sortable:false,align:'center'},
					{display:'거래처', name:'agentName',width:150, sortable:false,align:'center'},
					{display:'납부기한', name:'dueDate',width:80, sortable:false,align:'center'},
					{display:'수납일자', name:'rcvdDt',width:80, sortable:false,align:'center'},
					{display:'금액', name:'grFee',width:100, sortable:false,align:'right', displayFormat: 'number'},
					{display:'수수료', name:'grFee',width:50, sortable:false,align:'right', displayFormat: 'number'},
					{display:'세입처리결과', name:'revResultNm',width:100, sortable:false,align:'center'},
					{display:'수납처리결과', name:'retMsg',width:390, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
		preProcess: function(module, data) {
			return data;
	   	}        
    });

    this.$('#sRcvdPdFrom').val(EMD.util.getDate());
    this.$('#sRcvdPdTo').val(EMD.util.getDate());
    	        
    this.loadData();
};

<%--
	버튼 클릭에 대한 이벤트 핸들러 (EmdModule에서 Overriding 된 함수임 모듈에서 자동으로 호출 됨)
	스위치문 안에 코드가 길어지면 반드시 하위 함수로 분리 할 것.
    case 문에 주석을 달때는 case 문 뒤에 붙일 것
--%>
GamOcrRecvInquireModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case 'btnSearch': //조회
			this.loadData();
			break;
	}
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	사용자 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--
	loadData - 임대상세목록 조회
--%>
GamOcrRecvInquireModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#gamOcrRecvInquireSearchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
};

<%--
	다음 변수는 고정 적으로 정의 해야 함
	module_instance는 고정 변수 GamOcrRecvInquireModule은 위에서 EmdModule을 상속 받는 이 윈도우의 모듈 함수로 정의 됨.
--%>
var module_instance = new GamOcrRecvInquireModule();

</script>
<%--
	이 페이지를 로딩 하기 위한 window_id 변수. jsp를 호출 하는 model에 담겨서 와야 함.
--%>
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamOcrRecvInquireSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>수납기간</th>
                            <td width="300px">
                              <input id="sRcvdPdFrom" type="text" class="emdcal"
                                size="8" readonly> ~ <input id="sRcvdPdTo" type="text"
                                class="emdcal" size="8" readonly>
                            </td>
                            <th>항코드</th>
                            <td>
                                <input id="sPrtAtCode" type="text" size="4" maxlength="3"/>
                            </td>
                            <th>회계년도</th>
                            <td>
                                <input id="sFiscalYr" type="text" size="4" maxlength="4"/>
                            </td>
                            <td rowSpan="2"><button id="btnSearch" class="submit buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                            <th>이체기간</th>
                            <td>
                              <input id="sOcrPdFrom" type="text" class="emdcal"
                                size="8" readonly> ~ <input id="sOcrPdTo" type="text"
                                class="emdcal" size="8" readonly>
                            </td>
                            <th>요금종류</th>
                            <td>
                                <input id="sFeeTp" type="text" size="4" maxlength="2"/>
                            </td>
                            <th>고지번호</th>
                            <td>
                                <input id="sBillNo" type="text" size="6" maxlength="6"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

	<div class="emdPanel fillHeight">
        <table id="mainGrid" style="display:none" class="fillHeight"></table>
 	</div>
</div>