<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltyCtrtMng.jsp
 * @Description : 시설물 계약관리
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.29    김종민        최초 생성
 *
 * author 김종민
 * since 2014.10.29
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<!--  
<validator:javascript formName="gamSocApplySearchForm" method="validateGamSocApply" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
-->

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyCtrtMngModule() {}

GamFcltyCtrtMngModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyCtrtMngModule.prototype.loadComplete = function() {
	//계약관리리스트
    this.$("#fcltyCtrtMngList").flexigrid({
        module: this,
        url: '/ctrt/selectFcltyCtrtMngList.do',
        dataType: 'json',
        colModel : [
					{display:'계약번호', 		name:'ctrtNo',				width:120, 		sortable:false,		align:'center'},
					{display:'구분', 			name:'ctrtSe',				width:60, 		sortable:false,		align:'center'},
                    {display:'공고번호', 		name:'bidPblancNo',			width:100, 		sortable:false,		align:'center'},
                    {display:'계약명', 		name:'ctrtNm',				width:300, 		sortable:false,		align:'left'},
                    {display:'계약일', 		name:'ctrtDt',				width:80, 		sortable:false,		align:'left'},
                    {display:'입찰공고일', 		name:'bidPblancDt',			width:80, 		sortable:false,		align:'center'},
                    {display:'입찰일', 		name:'bidDt',				width:80, 		sortable:false,		align:'center'},
                    {display:'등록업체코드', 	name:'registEntrpsCd',		width:100, 		sortable:false,		align:'left'},
                    {display:'등록업체명', 		name:'registEntrpsNm',		width:150, 		sortable:false,		align:'left'},
                    {display:'설계금액', 		name:'planAmt',				width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'예정금액', 		name:'prmtAmt',				width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'낙찰금액', 		name:'scsbidAmt',			width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'낙찰율', 		name:'scsbidRate',			width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'기초금액', 		name:'baseAmt',				width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
			//자료수, 합산금액 입력
            module.$('#totalCount').val($.number(data.totalCount));
            module.$('#sumPlanAmt').val($.number(data.sumPlanAmt));
            module.$('#sumPrmtAmt').val($.number(data.sumPrmtAmt));
            module.$('#sumScsbidAmt').val($.number(data.sumScsbidAmt));
            module.$('#sumBaseAmt').val($.number(data.sumBaseAmt));
            return data;
        }
    });
	
	this._cmd = '';
	this._deleteCtrtJoinContrList = null;
	this._deleteCtrtSubCtrtList = null;
	this._deleteCtrtChangeList = null;
	this._deleteCtrtMoneyPymntList = null;
	this._deleteCtrtFulFillCaryFwdList = null;
	
	this.$("#fcltyCtrtMngList").on("onItemSelected", function(event, module, row, grid, param) {
		module._cmd = "modify";
	});
	
	this.$("#fcltyCtrtMngList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module._cmd = "modify";
		module.$("#fcltyCtrtMngListTab").tabs("option", {active: 1});
	});
	
	//계약공용도급 리스트
    this.$("#fcltyCtrtJoinContrList").flexigrid({
        module: this,
        url: '/ctrt/selectFcltyCtrtJoinContrList.do',
        dataType: 'json',
        colModel : [
                    {display:'업체명', 	name:'entrpsNm',		width:120, sortable:false,align:'center'},
                    {display:'대표자', 	name:'rprsntv',			width:70, sortable:false,align:'center'},
                    {display:'지분율', 	name:'qotaRate',		width:70, sortable:false,align:'right'},
                    {display:'업종', 		name:'induty',			width:80, sortable:false,align:'center'},
                    {display:'주요품목', 	name:'stplPrdlst',		width:100, sortable:false,align:'center'},
                    {display:'사업자번호', 	name:'bsnmNo',			width:100, sortable:false,align:'center'},
                    {display:'거래관계', 	name:'dealRelate',		width:80, sortable:false,align:'center'},
                    {display:'전화번호', 	name:'tlphonNo',		width:100, sortable:false,align:'center'},
                    {display:'fax번호',	name:'faxNo',			width:100, sortable:false,align:'center'},
                    {display:'우편번호', 	name:'postNo',			width:80, sortable:false,align:'center'},
                    {display:'도로명주소', 	name:'roadnmAdres',		width:150, sortable:false,align:'center'},
                    {display:'지번주소', 	name:'lnmAdres',		width:150, sortable:false,align:'center'},
                    {display:'담당자', 	name:'charger',			width:70, sortable:false,align:'center'},
                    {display:'담당자직위', 	name:'chargerOfcPos',	width:120, sortable:false,align:'center'},
                    {display:'담당자휴대폰번호',name:'chargerMoblphonNo',width:150, sortable:false,align:'center'},
                    {display:'담당자email',name:'chargerEmail',	width:150, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    //계약하도급 리스트
    this.$("#fcltyCtrtSubCtrtList").flexigrid({
        module: this,
        url: '/ctrt/selectFcltyCtrtSubCtrtList.do',
        dataType: 'json',
        colModel : [
                    {display:'업체명', 	name:'entrpsNm', 		width:150, sortable:true, align:'center'},
                    {display:'대금지급합의',name:'moneyPymntAgree', width:90, sortable:true, align:'center'},
                    {display:'공증', 		name:'workClass', 		width:80, sortable:true, align:'center'},
                    {display:'하도급율', 	name:'subctrtRate', 	width:80, sortable:true, align:'right'},
                    {display:'원도급금액', 	name:'orginlContrAmt', 	width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'하도급계약금액',name:'subctrtCtrtAmt', width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'계약시작일', name:'ctrtDtFrom', 		width:100, sortable:true, align:'center'},
                    {display:'계약종료일', name:'ctrtDtTo', 		width:100, sortable:true, align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    //계약변경 리스트
    this.$("#fcltyCtrtChangeList").flexigrid({
        module: this,
        url: '/ctrt/selectFcltyCtrtChangeList.do',
        dataType: 'json',
        colModel : [
                    {display:'변경일자', 		name:'changeDt', 		width:80, sortable:true, align:'center'},
                    {display:'변경사유', 		name:'changeRsn', 		width:120, sortable:true, align:'center'},
                    {display:'변경계약시작일', 	name:'changeCtrtPdFrom',width:100, sortable:true, align:'center'},
                    {display:'변경계약종료일', 	name:'changeCtrtPdTo', 	width:100, sortable:true, align:'center'},
                    {display:'변경계약금액', 	name:'changeCtrtAmt', 	width:100, sortable:true, align:'right', displayFormat:'number'},
                    {display:'최종계약금액', 	name:'lastCtrtAmt', 	width:100, sortable:true, align:'right', displayFormat:'number'},
                    {display:'비고', 			name:'rm', 				width:250, sortable:true, align:'left'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    //계약대금지급 리스트
    this.$("#fcltyCtrtMoneyPymntList").flexigrid({
        module: this,
        url: '/ctrt/selectFcltyCtrtMoneyPymntList.do',
        dataType: 'json',
        colModel : [
                    {display:'지급분류', 		name:'pymntCl', 		width:80, sortable:true, align:'center'},
                    {display:'지급일자', 		name:'pymntDt', 		width:80, sortable:true, align:'center'},
                    {display:'금회기성금액', 	name:'thisTimeEstbAmt', width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'선금정산금액', 	name:'depositExcclcAmt',width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'지급금액', 		name:'pymntAmt', 		width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'지급누계금액', 	name:'pymntAggrAmt', 	width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'비고', 			name:'rm', 				width:210, sortable:true, align:'left'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    //계약이행이월 리스트
    this.$("#fcltyCtrtFulFillCaryFwdList").flexigrid({
        module: this,
        url: '/ctrt/selectFcltyCtrtFulFillCaryFwdList.do',
        dataType: 'json',
        colModel : [
                    {display:'이행이월년도',name:'fulfillCaryFwdYear', 	width:100, sortable:true, align:'left'},
                    {display:'이행금액', 	name:'fulfillAmt', 			width:200, sortable:true, align:'right', displayFormat:'number'},
                    {display:'이월금액', 	name:'caryFwdAmt', 			width:200, sortable:true, align:'right', displayFormat:'number'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });
};

//화면 및 변수 초기화
GamFcltyCtrtMngModule.prototype.initDisplay = function() {
	this._deleteCtrtJoinContrList = [];
	this._deleteCtrtSubCtrtList = [];
	this._deleteCtrtChangeList = [];
	this._deleteCtrtMoneyPymntList = [];
	this._deleteCtrtFulFillCaryFwdList = [];

	this.$('#gamFcltyCtrtMngDetailForm :input').val('');
	this.$("#fcltyCtrtJoinContrList").flexEmptyData();
	this.$("#fcltyCtrtSubCtrtList").flexEmptyData();
	this.$("#fcltyCtrtChangeList").flexEmptyData();
	this.$("#fcltyCtrtMoneyPymntList").flexEmptyData();
	this.$("#fcltyCtrtFulFillCaryFwdList").flexEmptyData();
	if(this._cmd == 'insert') {
		this.$('#ctrtNo').enable();
		this.$("#fcltyCtrtMngListTab").tabs("option", {active: 1});
	} else if (this._cmd == 'modify') {
		this.$('#ctrtNo').disable();
	} else {
		this.$('#ctrtNo').disable();
		this.$("#fcltyCtrtMngListTab").tabs("option", {active: 0});
	}
};

GamFcltyCtrtMngModule.prototype.onSubmit = function() {
    this.loadData();
};

//계약정보목록 로드
GamFcltyCtrtMngModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamFcltyCtrtMngSearchForm');
    this.$('#fcltyCtrtMngList').flexOptions({params:searchOpt}).flexReload();
};

//계약정보 데이터 로드
GamFcltyCtrtMngModule.prototype.loadDetailData = function() {
	var selectRows = this.$('#fcltyCtrtMngList').selectedRows();
	
	if(selectRows.length > 0) {
		var row = selectRows[0];
		var opts = [{ name: 'sCtrtNo', value: row['ctrtNo']}];
		this.doAction('/ctrt/selectFcltyCtrtInfoDetailInquire.do', opts, function(module, result) {
			if(result.resultCode == 0) {
				module.$('#gamFcltyCtrtMngDetailForm :input').val('');
				module.makeFormValues('#gamFcltyCtrtMngDetailForm', result.resultVO);
				module.$("#fcltyCtrtJoinContrList").flexOptions({params:opts}).flexReload();
				module.$("#fcltyCtrtSubCtrtList").flexOptions({params:opts}).flexReload();
				module.$("#fcltyCtrtChangeList").flexOptions({params:opts}).flexReload();
				module.$("#fcltyCtrtMoneyPymntList").flexOptions({params:opts}).flexReload();
				module.$("#fcltyCtrtFulFillCaryFwdList").flexOptions({params:opts}).flexReload();
			}
			else {
	        	module.$('#gamFcltyCtrtMngDetailForm :input').val('');
				alert(result.resultMsg);
			}
		});
	}
};

//계약정보 데이터 삽입
GamFcltyCtrtMngModule.prototype.insertData = function() {
	var data = this.makeFormArgs("#gamFcltyCtrtMngDetailForm");
 	this.doAction('/ctrt/insertFcltyCtrtInfo.do', data, function(module, result) {
 		if(result.resultCode == "0"){
 			module._cmd = "modify";
			module.saveCtrtJoinContrList();
			module.saveCtrtSubCtrtList();
			module.saveCtrtChangeList();
			module.saveCtrtMoneyPymntList();
			module.saveCtrtFulFillCaryFwdList();
			module.loadData();
 		}
 		alert(result.resultMsg);
 	});	
};

//계약정보 데이터 수정
GamFcltyCtrtMngModule.prototype.updateData = function() {
	var data = this.makeFormArgs("#gamFcltyCtrtMngDetailForm");
	this.doAction('/ctrt/updateFcltyCtrtInfo.do', data, function(module, result) {
		if(result.resultCode == 0) {
			module.saveCtrtJoinContrList();
			module.saveCtrtSubCtrtList();
			module.saveCtrtChangeList();
			module.saveCtrtMoneyPymntList();
			module.saveCtrtFulFillCaryFwdList();
			module.loadData();
		}
		alert(result.resultMsg);
	});	
};

//계약정보 데이터 저장(삽입 및 수정)
GamFcltyCtrtMngModule.prototype.saveData = function() {
	if(this.$('#ctrtNo').val() == '') {
		alert('계약번호를 입력하세요.');
		return;
	} 
	if(this._cmd == 'insert') {
		this.insertData();
	} else if (this._cmd == 'modify') {
		this.updateData();
	}
};

//계약정보 데이터 삭제
GamFcltyCtrtMngModule.prototype.deleteData = function() {
	if(confirm("계약정보를 삭제하시겠습니까?")) {
		var rows = this.$("#fcltyCtrtMngList").selectedRows();
		if(rows.length <= 0){
			alert("삭제할 계약정보를 선택하십시오.");
			return;
		}
		var row = rows[0];
		var opts = [{name: 'ctrtNo', value: row['ctrtNo'] }];
	 	this.doAction('/ctrt/deleteFcltyCtrtInfo.do', opts, function(module, result) {
	 		if(result.resultCode == "0") {
				module._cmd = "";
				module.initDisplay();
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}
};

//계약공동도급 수정 팝업
GamFcltyCtrtMngModule.prototype.showModifyCtrtJoinContrList = function() {
	var allRows = this.$('#fcltyCtrtJoinContrList').flexGetData();
	this.doExecuteDialog('updateCtrtJoinContr', '계약공동도급관리', '/popup/showCtrtJoinContrMngt.do', {}, allRows);
};

//계약공동도급 병합 저장
GamFcltyCtrtMngModule.prototype.saveCtrtJoinContrList = function() {
	var dataList = this.$("#fcltyCtrtJoinContrList").flexGetData();
	for(var i=0; i<dataList.length; i++) {
		dataList[i]["ctrtNo"] = this.$("#ctrtNo").val();
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#fcltyCtrtJoinContrList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#fcltyCtrtJoinContrList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteCtrtJoinContrList) };
    this.doAction('/ctrt/mergeFcltyCtrtJoinContr.do', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteCtrtJoinContrList = [];				    	
			var opts = [
		           		{name: 'sCtrtNo', value: module.$("#ctrtNo").val() }
			           ];
			module.$("#fcltyCtrtJoinContrList").flexOptions({params:opts}).flexReload();
        }
        else {
        	alert(result.resultMsg);
        }
    });	
};

//계약하도급 수정 팝업
GamFcltyCtrtMngModule.prototype.showModifyCtrtSubCtrtList = function() {
	var allRows = this.$('#fcltyCtrtSubCtrtList').flexGetData();
	this.doExecuteDialog('updateCtrtSubCtrt', '계약하도급관리', '/popup/showCtrtSubCtrtMngt.do', {}, allRows);
};

//계약하도급 병합저장
GamFcltyCtrtMngModule.prototype.saveCtrtSubCtrtList = function() {
	var dataList = this.$("#fcltyCtrtSubCtrtList").flexGetData();
	for(var i=0; i<dataList.length; i++) {
		dataList[i]["ctrtNo"] = this.$("#ctrtNo").val();
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#fcltyCtrtSubCtrtList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#fcltyCtrtSubCtrtList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteCtrtSubCtrtList) };
    this.doAction('/ctrt/mergeFcltyCtrtSubCtrt.do', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteCtrtSubCtrtList = [];				    	
			var opts = [
		           		{name: 'sCtrtNo', value: module.$("#ctrtNo").val() }
			           ];
			module.$("#fcltyCtrtSubCtrtList").flexOptions({params:opts}).flexReload();
        }
        else {
        	alert(result.resultMsg);
        }
    });	
};

//계약변경 수정 팝업
GamFcltyCtrtMngModule.prototype.showModifyCtrtChangeList = function() {
	var allRows = this.$('#fcltyCtrtChangeList').flexGetData();
	this.doExecuteDialog('updateCtrtChange', '계약변경관리', '/popup/showCtrtChangeMngt.do', {}, allRows);
};

//계약변경 병합저장
GamFcltyCtrtMngModule.prototype.saveCtrtChangeList = function() {
	var dataList = this.$("#fcltyCtrtChangeList").flexGetData();
	for(var i=0; i<dataList.length; i++) {
		dataList[i]["ctrtNo"] = this.$("#ctrtNo").val();
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#fcltyCtrtChangeList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#fcltyCtrtChangeList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteCtrtChangeList) };
    this.doAction('/ctrt/mergeFcltyCtrtChange.do', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteCtrtChangeList = [];				    	
			var opts = [
		           		{name: 'sCtrtNo', value: module.$("#ctrtNo").val() }
			           ];
			module.$("#fcltyCtrtChangeList").flexOptions({params:opts}).flexReload();
        }
        else {
        	alert(result.resultMsg);
        }
    });	
};

//계약대금지급 수정 팝업
GamFcltyCtrtMngModule.prototype.showModifyCtrtMoneyPymntList = function() {
	var allRows = this.$('#fcltyCtrtMoneyPymntList').flexGetData();
	this.doExecuteDialog('updateCtrtMoneyPymnt', '계약대금지급관리', '/popup/showCtrtMoneyPymntMngt.do', {}, allRows);
};

//계약대금지급 병합저장
GamFcltyCtrtMngModule.prototype.saveCtrtMoneyPymntList = function() {
	var dataList = this.$("#fcltyCtrtMoneyPymntList").flexGetData();
	for(var i=0; i<dataList.length; i++) {
		dataList[i]["ctrtNo"] = this.$("#ctrtNo").val();
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#fcltyCtrtMoneyPymntList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#fcltyCtrtMoneyPymntList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteCtrtMoneyPymntList) };
    this.doAction('/ctrt/mergeFcltyCtrtMoneyPymnt.do', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteCtrtMoneyPymntList = [];				    	
			var opts = [
		           		{name: 'sCtrtNo', value: module.$("#ctrtNo").val() }
			           ];
			module.$("#fcltyCtrtMoneyPymntList").flexOptions({params:opts}).flexReload();
        }
        else {
        	alert(result.resultMsg);
        }
    });	
};

//계약이행이월 수정 팝업
GamFcltyCtrtMngModule.prototype.showModifyCtrtFulFillCaryFwdList = function() {
	var allRows = this.$('#fcltyCtrtFulFillCaryFwdList').flexGetData();
	this.doExecuteDialog('updateCtrtFulFillCaryFwd', '계약이행이월관리', '/popup/showCtrtFulFillCaryFwdMngt.do', {}, allRows);
};

//계약이행이월 병합저장
GamFcltyCtrtMngModule.prototype.saveCtrtFulFillCaryFwdList = function() {
	var dataList = this.$("#fcltyCtrtFulFillCaryFwdList").flexGetData();
	for(var i=0; i<dataList.length; i++) {
		dataList[i]["ctrtNo"] = this.$("#ctrtNo").val();
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#fcltyCtrtFulFillCaryFwdList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#fcltyCtrtFulFillCaryFwdList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteCtrtFulFillCaryFwdList) };
    this.doAction('/ctrt/mergeFcltyCtrtFulFillCaryFwd.do', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteCtrtFulFillCaryFwdList = [];				    	
			var opts = [
		           		{name: 'sCtrtNo', value: module.$("#ctrtNo").val() }
			           ];
			module.$("#fcltyCtrtFulFillCaryFwdList").flexOptions({params:opts}).flexReload();
        }
        else {
        	alert(result.resultMsg);
        }
    });	
};

/**
 * 정의 된 버튼 클릭 시
 */
GamFcltyCtrtMngModule.prototype.onButtonClick = function(buttonId) {
	var opts = null;
    switch(buttonId) {
        case 'btnSearch': //조회
        	this._cmd = "";
        	this.initDisplay();
        	this.loadData();
            break;
        case 'btnAdd': //추가
        	this._cmd = 'insert';
        	this.initDisplay();
        	break;
        case 'btnSave': //저장
        	this.saveData();
        	break;
        case 'btnRemove' : //삭제
        	this.deleteData();
        	break;        	
        case 'popupEntrpsInfo': // 업체선택 팝업을 호출한다.(조회)
            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', opts);
            break;
        case 'popupEntrpsInfo2': // 업체선택 팝업을 호출한다.(계약정보관리)
            this.doExecuteDialog('selectEntrpsInfoPopup2', '업체 선택', '/popup/showEntrpsInfo.do', opts);
            break;
        case 'popupCtrtJoinContrUpdate': //계약공동도급관리 편집
        	this.showModifyCtrtJoinContrList();
        	break;
        case 'popupCtrtSubCtrtUpdate': //계약하도급관리 편집
        	this.showModifyCtrtSubCtrtList();
        	break;
        case 'popupCtrtChangeUpdate': //계약변경관리 편집
        	this.showModifyCtrtChangeList();
        	break;
        case 'popupCtrtMoneyPymntUpdate': //계약대금지급 편집
        	this.showModifyCtrtMoneyPymntList();
        	break;
        case 'popupCtrtFulFillCaryFwdUpdate': //계약이행이월 편집
        	this.showModifyCtrtFulFillCaryFwdList();
        	break;
    }
};

/**
 * 탭 변경시 실행 이벤트
 */
GamFcltyCtrtMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._cmd == 'modify') {
		this.initDisplay();
		this.loadDetailData();
	}
    switch(newTabId) {
	    case 'tabs1':
	        break;
	    case 'tabs2':
	    case 'tabs3':
	    case 'tabs4':
	    case 'tabs5':
	    case 'tabs6':
	    case 'tabs7':
			if((this._cmd != 'insert') && (this._cmd != 'modify')) {
				this.$("#fcltyCtrtMngListTab").tabs("option", {active: 0});
				alert('계약정보목록을 선택하시거나 추가버튼을 누르세요.');
			} 	    	
	        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamFcltyCtrtMngModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
		case 'selectEntrpsInfoPopup': //등록업체 선택(조회)
	        this.$('#sRegistEntrpsCd').val(value['entrpscd']);
	        this.$('#sRegistEntrpsNm').val(value['entrpsNm']);
	    	break;
		case 'selectEntrpsInfoPopup2': //등록업체 선택(목록)
	        this.$('#registEntrpsCd').val(value['entrpscd']);
	        this.$('#registEntrpsNm').val(value['entrpsNm']);
	    	break;
		case 'updateCtrtJoinContr' : //계약공동도급관리 편집
			this.$("#fcltyCtrtJoinContrList").flexEmptyData();
			this.$("#fcltyCtrtJoinContrList").flexAddData({resultList: value["resultList"]});
			this._deleteCtrtJoinContrList = value["deleteCtrtJoinContrList"];
	 		break;
		case 'updateCtrtSubCtrt' : //계약하도급관리 편집
			this.$("#fcltyCtrtSubCtrtList").flexEmptyData();
			this.$("#fcltyCtrtSubCtrtList").flexAddData({resultList: value["resultList"]});
			this._deleteCtrtSubCtrtList = value["deleteCtrtSubCtrtList"];
	 		break;
		case 'updateCtrtChange' : //계약변경관리 편집
			this.$("#fcltyCtrtChangeList").flexEmptyData();
			this.$("#fcltyCtrtChangeList").flexAddData({resultList: value["resultList"]});
			this._deleteCtrtChangeList = value["deleteCtrtChangeList"];
	 		break;
		case 'updateCtrtMoneyPymnt' : //계약변경관리 편집
			this.$("#fcltyCtrtMoneyPymntList").flexEmptyData();
			this.$("#fcltyCtrtMoneyPymntList").flexAddData({resultList: value["resultList"]});
			this._deleteCtrtMoneyPymntList = value["deleteCtrtMoneyPymntList"];
	 		break;
		case 'updateCtrtFulFillCaryFwd' : //계약이행이월 편집
			this.$("#fcltyCtrtFulFillCaryFwdList").flexEmptyData();
			this.$("#fcltyCtrtFulFillCaryFwdList").flexAddData({resultList: value["resultList"]});
			this._deleteCtrtFulFillCaryFwdList = value["deleteCtrtFulFillCaryFwdList"];
	 		break;
	 	default:
        	alert('알수없는 팝업 이벤트가 호출 되었습니다.');
        	break;
    }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyCtrtMngModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamFcltyCtrtMngSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>계약구분</th>
                            <td>
                                <select id="sCtrtSe">
                                    <option value="" selected="selected">전체</option>
                                    <option value="1">자체</option>
                                    <option value="2">조달</option>
                                </select>
                            </td>
                            <th width="10%">계약명</th>
                            <td>
                            	<input id="sCtrtNm" type="text" size="25">
                         	</td>
							<th width="10%">계약일</th>
                            <td>
                            	<input id="sCtrtFrDt" type="text" class="emdcal" size="10"> ~ 
                            	<input id="sCtrtToDt" type="text" class="emdcal" size="10">
                            </td>
                            
                            <td rowspan="2">
								<button id="btnSearch" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                        <tr>
                        	<th width="10%">등록업체</th>
                            <td colspan="3">
                            	<input id="sRegistEntrpsCd" type="text" size="7">&nbsp; &nbsp;
                         		<input id="sRegistEntrpsNm" type="text" size="20" disabled="disabled">&nbsp; &nbsp;
                         		<button id="popupEntrpsInfo" class="popupButton">선택</button>
                         	</td>
                         	<th width="10%">입찰일</th>
                            <td>
                            	<input id="sBidFrDt" type="text" class="emdcal" size="10"> ~ 
                            	<input id="sBidToDt" type="text" class="emdcal" size="10">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="fcltyCtrtMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">계약정보목록</a></li>
                <li><a href="#tabs2" class="emdTab">계약정보관리</a></li>
                <li><a href="#tabs3" class="emdTab">계약공동도급관리</a></li>
                <li><a href="#tabs4" class="emdTab">계약하도급관리</a></li>
                <li><a href="#tabs5" class="emdTab">계약변경관리</a></li>
                <li><a href="#tabs6" class="emdTab">계약대금지급관리</a></li>
                <li><a href="#tabs7" class="emdTab">계약이행이월관리</a></li>
            </ul>
			
			<!-- 계약정보목록 -->
            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
                <table id="fcltyCtrtMngList" style="display:none" class="fillHeight"></table>
                <div id="fcltyCtrtMngListSum" class="emdControlPanel">
					<form id="fcltyCtrtMngListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="8%" height="20">자료수</th>
								<td><input type="text" size="6" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">설계금액</th>
								<td><input type="text" size="16" id="sumPlanAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">예정금액</th>
								<td><input type="text" size="16" id="sumPrmtAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">낙찰금액</th>
								<td><input type="text" size="16" id="sumScsbidAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">기초금액</th>
								<td><input type="text" size="16" id="sumBaseAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
                </div>
                <div class="emdControlPanel">
					<form id="form1">
    	               	<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button id="btnAdd">추가</button>
	                                <button id="btnRemove">삭제</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>
			
			<!-- 계약정보관리 -->
            <div id="tabs2" class="emdTabPage fillHeight" style="overflow: hidden;" >
            	<div class="emdControlPanel">
					<form id="gamFcltyCtrtMngDetailForm">
    	               	<table class="detailForm"  style="width:100%;">
                            <tr>
								<th width="10%" height="18">계약구분</th>
                                <td width="15%">
	                                <select id="ctrtSe">
	                                    <option value="" selected="selected">선택</option>
	                                    <option value="1">자체</option>
	                                    <option value="2">조달</option>
	                                </select>
                                </td>
                                <th width="10%" height="18">계약번호</th>
                                <td width="18%"><input type="text" size="20" id="ctrtNo" maxlength="100" disabled="disabled" /></td>
                                <th width="10%" height="18">계약명</th>
                                <td><input type="text" size="35" id="ctrtNm" maxlength="100" /></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">발주방식</th>
                                <td><input type="text" size="12" id="orderMthd" maxlength="100" /></td>
                                <th width="10%" height="18">계약방법</th>
                                <td><input type="text" size="5" id="ctrtSe" maxlength="2" /></td>
                                <th width="10%" height="18">입찰공고번호</th>
                                <td><input type="text" size="20" id="bidPblancNo" maxlength="100" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">입찰공고일자</th>
                                <td><input type="text" size="12" id="bidPblancDt" class="emdcal" /></td>
								<th width="10%" height="18">입찰일자</th>
                                <td><input type="text" size="12" id="bidDt" class="emdcal" /></td>
                            	<th width="10%" height="18">하자기간</th>
                                <td><input type="text" size="12" id="flawPdFrom" class="emdcal" /> ~ <input type="text" size="12" id="flawPdTo" class="emdcal" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">입찰방법</th>
                                <td><input type="text" size="12" id="bidMth" maxlength="100" /></td>
                            	<th width="10%" height="18">업무담당부서코드</th>
                                <td><input type="text" size="12" id="jobChrgDeptCd" maxlength="8"/></td>
                                <th width="10%" height="18">등록업체</th>
                                <td>
                                	<input type="text" size="10" id="registEntrpsCd" maxlength="10" /> 
                                	<input type="text" size="25" id="registEntrpsNm" disabled="disabled" /> 
                                	<button id="popupEntrpsInfo2" class="popupButton">선택</button> 
                                </td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">원인행위</th>
                                <td><input type="text" size="12" id="causeAct" maxlength="100" /></td>
								<th width="10%" height="18">설계금액</th>
                                <td><input type="text" size="20" id="planAmt" class="ygpaNumber" />원</td>
                                <th width="10%" height="18">예정금액</th>
                                <td><input type="text" size="20" id="prmtAmt" class="ygpaNumber" />원</td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">낙찰금액</th>
                                <td><input type="text" size="20" id="scsbidAmt" class="ygpaNumber" />원</td>
                                <th width="10%" height="18">낙찰율</th>
                                <td><input type="text" size="12" id="scsbidRate" class="ygpaNumber" data-decimal-point="5"/></td>
                                <th width="10%" height="18">기초금액</th>
                                <td><input type="text" size="20" id="baseAmt" class="ygpaNumber" />원</td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약일자</th>
                                <td><input type="text" size="12" id="ctrtDt" class="emdcal" /></td>
                            	<th width="10%" height="18">계약금액</th>
                                <td><input type="text" size="20" id="ctrtAmt" class="ygpaNumber" />원</td>
                                <th width="10%" height="18">계약기간</th>
                                <td><input type="text" size="12" id="ctrtPdFrom" class="emdcal" /> ~ <input type="text" size="12" id="ctrtPdTo" class="emdcal" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약보증금액</th>
                                <td><input type="text" size="20" id="ctrtGrntyAmt" class="ygpaNumber" />원</td>
                                <th width="10%" height="18">계약보증방법</th>
                                <td><input type="text" size="5" id="ctrtGrntyMth" maxlength="2" /></td>
								<th width="10%" height="18">조달공고번호</th>
                                <td><input type="text" size="20" id="prcuPblancNo" maxlength="100" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">감독자1</th>
                                <td><input type="text" size="10" id="intendant1" maxlength="10" /></td>
                                <th width="10%" height="18">감독자2</th>
                                <td><input type="text" size="10" id="intendant2" maxlength="10" /></td>
                                <th width="10%" height="18">감독자3</th>
                                <td><input type="text" size="10" id="intendant3" maxlength="10" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약검사일자</th>
                                <td><input type="text" size="12" id="ctrtExamDt" class="emdcal" /></td>
								<th width="10%" height="18">이월예산금액</th>
                                <td><input type="text" size="20" id="caryFwdBdgtAmt" class="ygpaNumber" />원</td>
                                <th width="10%" height="18">전자결재전송구분</th>
                                <td><input type="text" size="5" id="elctrnSanctnTrnsmisSe" maxlength="2"/></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">전자결재진행코드</th>
                                <td><input type="text" size="5" id="elctrnSanctnProgrsCd" maxlength="2" /></td>
                            	<th width="10%" height="18">전자결재전송일자</th>
                                <td><input type="text" size="12" id="elctrnSanctnTrnsmisDt" class="emdcal" /></td>
                                <th width="10%" height="18">전자결재연동정보</th>
                                <td><input type="text" size="20" id="elctrnSanctnInterlockInfo" maxlength="100"/></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">전자결재문서ID</th>
                                <td><input type="text" size="20" id="elctrnSanctnDocId" maxlength="100" /></td>
                                <th width="10%" height="18">승인일자</th>
                                <td><input type="text" size="12" id="confmDt" class="emdcal" /></td>
                            	<th width="10%" height="18">승인자코드</th>
                                <td><input type="text" size="10" id="confmerCd" maxlength="10" /></td>
                            </tr>
                            <tr>
                                <th width="10%" height="18">연대보증</th>
                                <td><input type="text" size="20" id="sldrtGrnty" maxlength="100" /></td>
                                <th width="10%" height="18">현장설명</th>
                                <td colspan="3"><input type="text" size="60" id="siteDesc" maxlength="100" /></td>
                            </tr>
                        </table>
					</form>
                </div>
                <div class="emdControlPanel">
					<form id="form1">
    	               	<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                                <button id="btnSave">저장</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>
			
			<!-- 계약공동도급관리 -->
            <div id="tabs3" class="emdTabPage fillHeight" style="overflow: hidden;">
				<table id="fcltyCtrtJoinContrList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
				    <button id="popupCtrtJoinContrUpdate" class="popupButton">편집</button>
				    <button id="btnSave">저장</button>
				</div>
            </div>
            
            <!-- 계약하도급관리 -->
            <div id="tabs4" class="emdTabPage fillHeight" style="overflow: hidden;">
				<table id="fcltyCtrtSubCtrtList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
				    <button id="popupCtrtSubCtrtUpdate" class="popupButton">편집</button>
				    <button id="btnSave">저장</button>
				</div>
            </div>
            
            <!-- 계약변경관리 -->
            <div id="tabs5" class="emdTabPage fillHeight" style="overflow: hidden;">
				<table id="fcltyCtrtChangeList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
				    <button id="popupCtrtChangeUpdate" class="popupButton">편집</button>
				    <button id="btnSave">저장</button>
				</div>
            </div>
            
            <!-- 계약대금지급관리 -->
            <div id="tabs6" class="emdTabPage fillHeight" style="overflow: hidden;">
				<table id="fcltyCtrtMoneyPymntList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
				    <button id="popupCtrtMoneyPymntUpdate" class="popupButton">편집</button>
				    <button id="btnSave">저장</button>
				</div>
            </div>
                        
            <!-- 계약이행이월관리 -->
            <div id="tabs7" class="emdTabPage fillHeight" style="overflow: hidden;">
				<table id="fcltyCtrtFulFillCaryFwdList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
				    <button id="popupCtrtFulFillCaryFwdUpdate" class="popupButton">편집</button>
				    <button id="btnSave">저장</button>
				</div>
            </div>
            
        </div>
    </div>
</div>