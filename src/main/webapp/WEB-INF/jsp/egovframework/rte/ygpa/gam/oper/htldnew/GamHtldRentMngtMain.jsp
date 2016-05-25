<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld"%>
<%--
  @Class Name : GamHtldRentMngtMain.jsp
  @Description : 배후단지임대관리메인화면
  @Modification Information

     수정일         수정자                   수정내용
    ------------ 	----------	---------------------------
    2016.04.25  	jongmin     최초 생성

   author jongmin
   since 2016.04.25

  Copyright (C) 2013 by LFIT  All right reserved.
--%>
<script>
<%--
 	아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
--%>
function GamHtldRentMngtMainModule() {}

<%--
	EmdModule을 상속하여 모듈 클래스를 정의한다.
--%>
GamHtldRentMngtMainModule.prototype = new EmdModule(1820, 810);

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdModule Override 및 이벤트 처리 정의 부분 시작	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	페이지가 호출 되었을때 호출 되는 함수
--%>
GamHtldRentMngtMainModule.prototype.loadComplete = function() {
    this.$("#mainGrid").flexigrid({
        module: this,
        url: '/oper/htldnew/selectHtldRentDetailList.do',
        dataType: 'json',
        colModel : [
                    {display:'구역', name:'boundNm',width:120, sortable:false,align:'center'},
                    {display:'입주기업', name:'entrpsNm',width:170, sortable:false,align:'center'},
                    {display:'임대차계약', name:'detailPdStr',width:170, sortable:false,align:'center'},
                    {display:'입주면적(㎡)', name:'rentArStr',width:135, sortable:false,align:'right'},
                    {display:'적용임대료', name:'applcRntfeeStr',width:130, sortable:false,align:'right'},
                    {display:'실적평가임대료', name:'aseRntfeeStr',width:90, sortable:false,align:'right'},
                    {display:'실적평가적용기간', name:'asePd',width:170, sortable:false,align:'center'},
                    {display:'구분', name:'paySeNm',width:45, sortable:false,align:'center'},
    				{display:'임대료', name:'rntfee',width:120, sortable:false,align:'right', displayFormat: 'input-number'},
    				{display:'분납이자', name:'payinstIntr',width:100, sortable:false,align:'right', displayFormat: 'input-number'},
    				{display:'공급가액', name:'supAmt',width:120, sortable:false,align:'right', displayFormat: 'input-number'},
    				{display:'부가세', name:'vat',width:90, sortable:false,align:'right', displayFormat: 'input-number'},
    				{display:'납부금액', name:'payAmt',width:120, sortable:false,align:'right', displayFormat: 'input-number'},
    				{display:'비고', name:'rm',width:140, sortable:false,align:'left', displayFormat: 'input'},
                    {display:'상태', name:'nticMthNm',width:60, sortable:false,align:'left'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        mergeRows: 'boundNm,entrpsNm,detailPdStr',
		preProcess: function(module, data) {
			module.$('#cofixIntrrate').val(data.cofixIntrrate);
			$.each(data.resultList, function() {
				module.initDataRow(this);
	     	});
	     	return data;
	   	}        
    });
    
    // 임대상세목록의 선택한 셀의 위치를 알고 싶을 때 
    this.$("#mainGrid").on('onSelectedRow', function(event, module, row, rid, cid) {
    	module.onMainGridSelectedRow(row, rid, cid);
    });
	
    var today = new Date();
    var searchYear = today.getFullYear();
    var searchMonth = today.getMonth() + 1;
    if(searchMonth < 10) searchMonth = '0' + searchMonth;
    var searchDay = today.getDate();
    if(searchDay < 10) searchDay = '0' + searchDay;
    var searchDt = searchYear + '-' + searchMonth + '-' + searchDay;
    this.$('#sNticDt').val(searchDt);
    
    this.loadData();
};

<%--
	버튼 클릭에 대한 이벤트 핸들러 (EmdModule에서 Overriding 된 함수임 모듈에서 자동으로 호출 됨)
	스위치문 안에 코드가 길어지면 반드시 하위 함수로 분리 할 것.
    case 문에 주석을 달때는 case 문 뒤에 붙일 것
--%>
GamHtldRentMngtMainModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case 'btnSearch': //조회
			this.loadData();
			break;
		case 'btnIntrrateMngt': //분납이자율관리
			this.doExecuteDialog('intrrateMngtPopup', 'Cofix 이자율 관리', '/popup/showCofixIntrrateMngt.do', {});
			break;
		case 'btnAddRentContract': //계약등록
			EMD.util.create_window('gamHtldRentContract', '배후단지 임대계약', '/oper/htldnew/gamHtldRentCtrt.do', null, null, this);
       		break;
		case 'btnExecNticIssue':  //고지
			break;
		case 'btnPrintNticIssue':  //고지서출력
			break;
		case 'btnNticIssueHist':  //고지내역
			break;
		case 'btnAddNticIssue':  //추가고지
			this.doExecuteDialog('addNticIssueHistPopup', '배후단지 임대료 추가고지', '/popup/showHtldAddNticIssue.do', {});
			break;
	}
};

<%--
	팝업이 종료 될때 리턴 값이 호출 된다.	EmdModule에서 호출 함.
		popupId : 팝업 대화상자 아이디
		msg : 팝업에서 전송한 메시지 (취소는 cancel)
		value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
--%>
GamHtldRentMngtMainModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
		case 'bizAssessRegPopup':
			if (msg != 'cancel') {
				this.loadData();
			}
			break;
		case 'intrrateMngtPopup':
			if (msg != 'cancel') {
				this.loadData();
			}
			break;
	}
};

<%--
	자식창이 종료 될때 호출 된다. EmdModule에서 호출 함.
		module : 자식창 모듈
		message : 자식창이 종료될 때 넘겨온 메시지
--%>
GamHtldRentMngtMainModule.prototype.closeChildWindow = function(module, message) {
	switch (message) {
		case 'SAVE_RENTCONTRACT':
			this.loadData();
			break;
	}	
};

<%--
	그리드의 해당 row를 선택할 때 호출 된다. EmdModule의 onSelectedRow에서 호출 함.
		row : 선택 row
		rid : 선택된 row id
		cid : 선택된 cell id
--%>
GamHtldRentMngtMainModule.prototype.onMainGridSelectedRow = function(row, rid, cid) {
	switch(cid) {
		case 'entrpsNm':
			EMD.util.create_window('gamHtldRentContract', '배후단지 임대계약', '/oper/htldnew/gamHtldRentCtrt.do', null, {'searchRow' : row}, this);
			break;
		case 'aseRntfeeStr':
		case 'asePd':
			this.doExecuteDialog('bizAssessRegPopup', '실적평가 임대료', '/popup/showHtldBizAssess.do', {}, {'searchRow' : row, 'searchNticDt' : this.$('#sNticDt').val()} );
			break;
	}	
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	사용자 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--
	loadData - 임대상세목록 조회
--%>
GamHtldRentMngtMainModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#gamHtldRentListSearchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
};

<%--
	initDataRow - 각 row 데이터 초기화
	params :
		row - 그리드 row
--%>
GamHtldRentMngtMainModule.prototype.initDataRow = function(row) {
	if(row.rntfeeSe == '0') {
		row.detailPdStr = row.detailPdBegin + '~' + row.detailPdEnd;
	} else {
		row.detailPdStr = row.rntfeeSeNm;
	}
	if(row.rentArSe != '0') {
		row.rentArStr = (row.rentArSe != '3') ? row.rentArStr + '/' + row.rentArSeNm : row.rentArSeNm;  
	}
	if(row.priceSe == '2') {
		row.applcRntfeeStr += '원/월';
	}
	if(row.aseRntfee == 0) {
		row.aseRntfeeStr = '';
	}
	if(row.aseApplcBegin != void(0)) {
		row.asePd = row.aseApplcBegin + '~' + row.aseApplcEnd;
	}
};

<%--
	다음 변수는 고정 적으로 정의 해야 함
	module_instance는 고정 변수 GamHtldRentMngtMainModule은 위에서 EmdModule을 상속 받는 이 윈도우의 모듈 함수로 정의 됨.
--%>
var module_instance = new GamHtldRentMngtMainModule();

</script>
<%--
	이 페이지를 로딩 하기 위한 window_id 변수. jsp를 호출 하는 model에 담겨서 와야 함.
--%>
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamHtldRentListSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th width="4%">고지(예정)일자</th>
                            <td width="8%"><input id="sNticDt" type="text" class="emdcal" size="8"></td>
                            <th width="4%">납부구분</th>
                            <td width="8%">
                            	<!-- <input id="sPaySe" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM008" value=""/> -->
                            	<select id="sPaySe">
                            		<option value="">전체</option>
                            		<option value="4">분기납</option>
                            		<option value="6">연납</option>
                            	</select>
                            </td>
                            <th width="4%">계약해지유무</th>
                            <td width="8%">
                            	<select id="sTermnYn">
                            		<option value="">전체</option>
                            		<option value="Y">유</option>
                            		<option value="N">무</option>
                            	</select>	
                            </td>
                            <th width="4%">분납이자율</th>
                            <td width="8%"><input type="text" size="6" class="ygpaNumber" data-decimal-point="2" id="cofixIntrrate" disabled/>&nbsp; %</td>
                            <td width="7%"><button id="btnIntrrateMngt">분납이자율관리</button></td>
                            <td width="35%"></td>
                            <td width="10%"><button id="btnSearch" class="buttonSearch">조회</button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

	<div class="emdPanel fillHeight">
	<table id="mainGrid" style="display:none" class="fillHeight"></table>
 		<div class="emdControlPanel">
			<table style="width:100%;">
				<tr>
					<td style="text-align: right">
                       <button id="btnAddRentContract">계약등록</button>
                       <button id="btnExecNticIssue">고지</button>
                       <button id="btnPrintNticIssue" >고지서출력</button>
                       <button id="btnNticIssueHist" >고지이력</button>
                       <button id="btnAddNticIssue">추가고지</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>