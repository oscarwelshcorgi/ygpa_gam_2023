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
                    {display:'계약금액', 		name:'ctrtAmt',				width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'},
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
	this._deleteCtrtScsbidInfoList = null;
	
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
        height: '270'
    });

	
	this.$("#gamCtrtJoinContrMngtForm").find(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.ctrtJoinContrChanged(event.target);
	});

	this.$("#fcltyCtrtJoinContrList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamCtrtJoinContrMngtForm input").val('');
		module.makeFormValues("#gamCtrtJoinContrMngtForm", row);
	});
	
    //계약하도급 리스트
    this.$("#fcltyCtrtSubCtrtList").flexigrid({
        module: this,
        url: '/ctrt/selectFcltyCtrtSubCtrtList.do',
        dataType: 'json',
        colModel : [
                    {display:'업체명', 	name:'entrpsNm', 		width:150, sortable:true, align:'center'},
                    {display:'대금지급합의',name:'moneyPymntAgree', width:90, sortable:true, align:'center'},
                    {display:'공종', 		name:'workClass', 		width:80, sortable:true, align:'center'},
                    {display:'하도급율', 	name:'subctrtRate', 	width:80, sortable:true, align:'right'},
                    {display:'원도급금액', 	name:'orginlContrAmt', 	width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'하도급계약금액',name:'subctrtCtrtAmt', width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'계약시작일', name:'ctrtDtFrom', 		width:100, sortable:true, align:'center'},
                    {display:'계약종료일', name:'ctrtDtTo', 		width:100, sortable:true, align:'center'}
                    ],
        showTableToggleBtn: false,
        height: '350'
    });

	this.$("#gamCtrtSubCtrtMngtForm").find(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.ctrtSubCtrtChanged(event.target);
	});

	this.$("#fcltyCtrtSubCtrtList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamCtrtSubCtrtMngtForm input").val('');
		module.makeFormValues("#gamCtrtSubCtrtMngtForm", row);
		module.$("#entrpsNm1").val(row["entrpsNm"]); // 공동도급의 업체명과 하도급의 업체명 text id를 다르게 처리.
		module.$("#subCtrtDtFrom").val(row["ctrtDtFrom"]); //디테일의 계약기간과 text id를 다르게 처리.
		module.$("#subCtrtDtTo").val(row["ctrtDtTo"]); //디테일의 계약기간과 text id를 다르게 처리.
	});
    
    //계약변경 리스트
    this.$("#fcltyCtrtChangeList").flexigrid({
        module: this,
        url: '/ctrt/selectFcltyCtrtChangeList.do',
        dataType: 'json',
        colModel : [
                    {display:'변경일자', 		name:'changeDt', 		width:80, sortable:true, align:'center'},
                    {display:'변경사유', 		name:'changeRsn', 		width:120, sortable:true, align:'center'},
                    {display:'변경계약시작일', 	name:'changeCtrtDtFrom',width:100, sortable:true, align:'center'},
                    {display:'변경계약종료일', 	name:'changeCtrtDtTo', 	width:100, sortable:true, align:'center'},
                    {display:'변경계약금액', 	name:'changeCtrtAmt', 	width:100, sortable:true, align:'right', displayFormat:'number'},
                    {display:'최종계약금액', 	name:'lastCtrtAmt', 	width:100, sortable:true, align:'right', displayFormat:'number'},
                    {display:'비고', 			name:'rm', 				width:250, sortable:true, align:'left'}
                    ],
        showTableToggleBtn: false,
        height: '350'
    });

	this.$("#gamCtrtChangeMngtForm").find(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.ctrtChangeChanged(event.target);
	});

	this.$("#fcltyCtrtChangeList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamCtrtChangeMngtForm input").val('');
		module.makeFormValues("#gamCtrtChangeMngtForm", row);
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
        height: '350'
    });

	this.$("#gamCtrtMoneyPymntMngtForm").find(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.ctrtMoneyPymntChanged(event.target);
	});

	this.$("#fcltyCtrtMoneyPymntList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamCtrtMoneyPymntMngtForm input").val('');
		module.makeFormValues("#gamCtrtMoneyPymntMngtForm", row);
		module.$("#rm1").val(row["rm"]); //계약변경의 비고와 text id를 다르게 처리.
	});
    
    //계약이행이월 리스트
    this.$("#fcltyCtrtFulFillCaryFwdList").flexigrid({
        module: this,
        url: '/ctrt/selectFcltyCtrtFulFillCaryFwdList.do',
        dataType: 'json',
        colModel : [
                    {display:'이행이월년도',name:'fulfillCaryFwdYear', 	width:100, sortable:true, align:'center'},
                    {display:'이행금액', 	name:'fulfillAmt', 			width:200, sortable:true, align:'right', displayFormat:'number'},
                    {display:'이월금액', 	name:'caryFwdAmt', 			width:200, sortable:true, align:'right', displayFormat:'number'}
                    ],
        showTableToggleBtn: false,
        height: '400'
    });

	this.$("#gamCtrtFulFillCaryFwdMngtForm").find(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.ctrtFulFillCaryFwdChanged(event.target);
	});

	this.$("#fcltyCtrtFulFillCaryFwdList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamCtrtFulFillCaryFwdMngtForm input").val('');
		module.makeFormValues("#gamCtrtFulFillCaryFwdMngtForm", row);
	});
    
    //계약낙찰정보 리스트
    this.$("#fcltyCtrtScsbidInfoList").flexigrid({
        module: this,
        url: '/ctrt/selectFcltyCtrtScsbidInfoList.do',
        dataType: 'json',
        colModel : [
                    {display:'낙찰순위', 	name:'scsbidRank',		width:90, sortable:false,align:'center'},
                    {display:'업체명', 	name:'entrpsNm',		width:150, sortable:false,align:'center'},
                    {display:'대표자', 	name:'rprsntv',			width:100, sortable:false,align:'center'},
                    {display:'사업자번호', 	name:'bsnmNo',			width:100, sortable:false,align:'center'},
                    {display:'전화번호', 	name:'tlphonNo',		width:100, sortable:false,align:'center'},
                    {display:'fax번호',	name:'faxNo',			width:100, sortable:false,align:'center'},
                    ],
        showTableToggleBtn: false,                    
        height: '380'
    });
    
	this.$("#gamCtrtScsbidInfoMngtForm").find(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.ctrtScsbidInfoChanged(event.target);
	});

	this.$("#fcltyCtrtScsbidInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#gamCtrtScsbidInfoMngtForm input").val('');
		module.makeFormValues("#gamCtrtScsbidInfoMngtForm", row);
		module.$("#entrpsNm2").val(row["entrpsNm"]); // 공동도급의 업체명과 하도급의 업체명 그리고 낙찰정보의 업체명 text id를 다르게 처리.
		module.$("#rprsntv2").val(row["rprsntv"]); // 공동도급의 대표자와 낙찰정보의 대표자 text id를 다르게 처리.
		module.$("#bsnmNo2").val(row["bsnmNo"]); // 공동도급의 사업자번호와 낙찰정보의 사업자번호 text id를 다르게 처리.
		module.$("#tlphonNo2").val(row["tlphonNo"]); // 공동도급의 전화번호와 낙찰정보의 전화번호 text id를 다르게 처리.
		module.$("#faxNo2").val(row["faxNo"]); // 공동도급의 팩스번호와 낙찰정보의 팩스번호 text id를 다르게 처리.
	});
    
};

//화면 및 변수 초기화
GamFcltyCtrtMngModule.prototype.initDisplay = function() {
	this._deleteCtrtJoinContrList = [];
	this._deleteCtrtSubCtrtList = [];
	this._deleteCtrtChangeList = [];
	this._deleteCtrtMoneyPymntList = [];
	this._deleteCtrtFulFillCaryFwdList = [];
	this._deleteCtrtScsbidInfoList = [];

	this.$('#gamFcltyCtrtMngDetailForm :input').val('');
	this.$("#fcltyCtrtJoinContrList").flexEmptyData();
	this.$("#fcltyCtrtSubCtrtList").flexEmptyData();
	this.$("#fcltyCtrtChangeList").flexEmptyData();
	this.$("#fcltyCtrtMoneyPymntList").flexEmptyData();
	this.$("#fcltyCtrtFulFillCaryFwdList").flexEmptyData();
	this.$("#fcltyCtrtScsbidInfoList").flexEmptyData();
	
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
	var rows = this.$('#fcltyCtrtMngList').selectedRows();
	
	if(rows.length > 0) {
		var row = rows[0];
		var opts = [{ name: 'sCtrtNo', value: row['ctrtNo']}];
		this.doAction('/ctrt/selectFcltyCtrtInfoDetail.do', opts, function(module, result) {
			if(result.resultCode == 0) {
				module.$('#gamFcltyCtrtMngDetailForm :input').val('');
				module.makeFormValues('#gamFcltyCtrtMngDetailForm', result.resultVO);
				module.$("#fcltyCtrtJoinContrList").flexOptions({params:opts}).flexReload();
				module.$("#fcltyCtrtSubCtrtList").flexOptions({params:opts}).flexReload();
				module.$("#fcltyCtrtChangeList").flexOptions({params:opts}).flexReload();
				module.$("#fcltyCtrtMoneyPymntList").flexOptions({params:opts}).flexReload();
				module.$("#fcltyCtrtFulFillCaryFwdList").flexOptions({params:opts}).flexReload();
				module.$("#fcltyCtrtScsbidInfoList").flexOptions({params:opts}).flexReload();
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
			module.saveCtrtScsbidInfoList();
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
			module.saveCtrtScsbidInfoList();
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
	 	this.doAction('/ctrt/deleteFcltyCtrtInfo.do', rows[0], function(module, result) {
	 		if(result.resultCode == "0") {
				module._cmd = "";
				module.initDisplay();
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}
};


//계약공동도급 속성변경 이벤트 처리
GamFcltyCtrtMngModule.prototype.ctrtJoinContrChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#fcltyCtrtJoinContrList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#entrpsNm').is(target)) {
			row['entrpsNm'] = $(target).val();
			changed=true;
		}
		if(this.$('#rprsntv').is(target)) {
			row['rprsntv'] = $(target).val();
			changed=true;
		}
		if(this.$('#qotaRate').is(target)) {
			row['qotaRate'] = $(target).val();
			changed=true;
		}
		if(this.$('#induty').is(target)) {
			row['induty'] = $(target).val();
			changed=true;
		}
		if(this.$('#stplPrdlst').is(target)) {
			row['stplPrdlst'] = $(target).val();
			changed=true;
		}
		if(this.$('#bsnmNo').is(target)) {
			row['bsnmNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#dealRelate').is(target)) {
			row['dealRelate'] = $(target).val();
			changed=true;
		}
		if(this.$('#tlphonNo').is(target)) {
			row['tlphonNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#faxNo').is(target)) {
			row['faxNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#postNo').is(target)) {
			row['postNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#roadnmAdres').is(target)) {
			row['roadnmAdres'] = $(target).val();
			changed=true;
		}
		if(this.$('#lnmAdres').is(target)) {
			row['lnmAdres'] = $(target).val();
			changed=true;
		}
		if(this.$('#charger').is(target)) {
			row['charger'] = $(target).val();
			changed=true;
		}
		if(this.$('#chargerOfcPos').is(target)) {
			row['chargerOfcPos'] = $(target).val();
			changed=true;
		}
		if(this.$('#chargerMoblphonNo').is(target)) {
			row['chargerMoblphonNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#chargerEmail').is(target)) {
			row['chargerEmail'] = $(target).val();
			changed=true;
		}

	}
	if(changed) {
		var rowid=this.$("#fcltyCtrtJoinContrList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltyCtrtJoinContrList').flexUpdateRow(rowid, row);
	}
};

//계약공동도급 추가
GamFcltyCtrtMngModule.prototype.addCtrtJoinContrItem = function() {
	this.$('#gamCtrtJoinContrMngtForm :input').val('');
	this.$("#fcltyCtrtJoinContrList").flexAddRow({'_updtId': 'I', 'ctrtNo':'', 'seq':'', 'entrpsNm':'','rprsntv':'','qotaRate':'','induty':'','stplPrdlst':'','bsnmNo':'' ,'dealRelate':'','tlphonNo':'','faxNo':'','postNo':'','roadnmAdres':'','lnmAdres':'','charger':'','chargerOfcPos':'','chargerMoblphonNo':'','chargerEmail':''});
	var allRows = this.$('#fcltyCtrtJoinContrList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#fcltyCtrtJoinContrList").selectRowId(selRowId);
};

// 계약공동도급 삭제
GamFcltyCtrtMngModule.prototype.removeCtrtJoinContrItem = function() {
	var rows = this.$("#fcltyCtrtJoinContrList").selectedRows();
    if(rows.length == 0){
        alert("계약공동도급목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#fcltyCtrtJoinContrList").selectedRowIds().length>0) {
    	for(var i=this.$("#fcltyCtrtJoinContrList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#fcltyCtrtJoinContrList").flexGetRow(this.$("#fcltyCtrtJoinContrList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteCtrtJoinContrList[this._deleteCtrtJoinContrList.length] = row;
			}
        	this.$("#fcltyCtrtJoinContrList").flexRemoveRow(this.$("#fcltyCtrtJoinContrList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamPopupCtrtJoinContrMngtForm :input").val("");
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

//계약하도급 속성변경 이벤트 처리
GamFcltyCtrtMngModule.prototype.ctrtSubCtrtChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#fcltyCtrtSubCtrtList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#entrpsNm1').is(target)) {
			row['entrpsNm'] = $(target).val();
			changed=true;
		}
		if(this.$('#moneyPymntAgree').is(target)) {
			row['moneyPymntAgree'] = $(target).val();
			changed=true;
		}
		if(this.$('#workClass').is(target)) {
			row['workClass'] = $(target).val();
			changed=true;
		}
		if(this.$('#subctrtRate').is(target)) {
			row['subctrtRate'] = $(target).val();
			changed=true;
		}
		if(this.$('#orginlContrAmt').is(target)) {
			row['orginlContrAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#subctrtCtrtAmt').is(target)) {
			row['subctrtCtrtAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#subCtrtDtFrom').is(target)) {
			row['ctrtDtFrom'] = $(target).val();
			changed=true;
		}
		if(this.$('#subCtrtDtTo').is(target)) {
			row['ctrtDtTo'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#fcltyCtrtSubCtrtList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltyCtrtSubCtrtList').flexUpdateRow(rowid, row);
	}
};

// 계약하도급 추가
GamFcltyCtrtMngModule.prototype.addCtrtSubCtrtItem = function() {
	this.$('#gamCtrtSubCtrtMngtForm :input').val('');
	this.$("#fcltyCtrtSubCtrtList").flexAddRow({'_updtId': 'I', 'ctrtNo':'', 'seq':'', 'entrpsNm':'','moneyPymntAgree':'','workClass':'','subctrtRate':'','orginlContrAmt':'','subctrtCtrtAmt':'' ,'ctrtDtFrom':'','ctrtDtTo':''});
	var allRows = this.$('#fcltyCtrtSubCtrtList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#fcltyCtrtSubCtrtList").selectRowId(selRowId);
};

// 계약하도급 삭제
GamFcltyCtrtMngModule.prototype.removeCtrtSubCtrtItem = function() {
	var rows = this.$("#fcltyCtrtSubCtrtList").selectedRows();
    if(rows.length == 0){
        alert("계약하도급목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#fcltyCtrtSubCtrtList").selectedRowIds().length>0) {
    	for(var i=this.$("#fcltyCtrtSubCtrtList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#fcltyCtrtSubCtrtList").flexGetRow(this.$("#fcltyCtrtSubCtrtList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteCtrtSubCtrtList[this._deleteCtrtSubCtrtList.length] = row;
			}
        	this.$("#fcltyCtrtSubCtrtList").flexRemoveRow(this.$("#fcltyCtrtSubCtrtList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamCtrtSubCtrtMngtForm :input").val("");
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

//계약변경 속성변경 이벤트 처리
GamFcltyCtrtMngModule.prototype.ctrtChangeChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#fcltyCtrtChangeList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#changeDt').is(target)) {
			row['changeDt'] = $(target).val();
			changed=true;
		}
		if(this.$('#changeRsn').is(target)) {
			row['changeRsn'] = $(target).val();
			changed=true;
		}
		if(this.$('#changeCtrtDtFrom').is(target)) {
			row['changeCtrtDtFrom'] = $(target).val();
			changed=true;
		}
		if(this.$('#changeCtrtDtTo').is(target)) {
			row['changeCtrtDtTo'] = $(target).val();
			changed=true;
		}
		if(this.$('#changeCtrtAmt').is(target)) {
			row['changeCtrtAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#lastCtrtAmt').is(target)) {
			row['lastCtrtAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#rm').is(target)) {
			row['rm'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#fcltyCtrtChangeList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltyCtrtChangeList').flexUpdateRow(rowid, row);
	}
};

// 계약변경 추가
GamFcltyCtrtMngModule.prototype.addCtrtChangeItem = function() {
	this.$('#gamCtrtChangeMngtForm :input').val('');
	this.$("#fcltyCtrtChangeList").flexAddRow({'_updtId': 'I', 'ctrtNo':'', 'seq':'', 'changeDt':'','changeRsn':'','changeCtrtDtFrom':'','changeCtrtDtTo':'','changeCtrtAmt':'','lastCtrtAmt':'' ,'rm':''});
	var allRows = this.$('#fcltyCtrtChangeList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#fcltyCtrtChangeList").selectRowId(selRowId);
};

// 계약변경 삭제
GamFcltyCtrtMngModule.prototype.removeCtrtChangeItem = function() {
	var rows = this.$("#fcltyCtrtChangeList").selectedRows();
    if(rows.length == 0){
        alert("계약변경목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#fcltyCtrtChangeList").selectedRowIds().length>0) {
    	for(var i=this.$("#fcltyCtrtChangeList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#fcltyCtrtChangeList").flexGetRow(this.$("#fcltyCtrtChangeList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteCtrtChangeList[this._deleteCtrtChangeList.length] = row;
			}
        	this.$("#fcltyCtrtChangeList").flexRemoveRow(this.$("#fcltyCtrtChangeList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamCtrtChangeMngtForm :input").val("");
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

//계약대금지급 속성변경 이벤트 처리
GamFcltyCtrtMngModule.prototype.ctrtMoneyPymntChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#fcltyCtrtMoneyPymntList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#pymntCl').is(target)) {
			row['pymntCl'] = $(target).val();
			changed=true;
		}
		if(this.$('#pymntDt').is(target)) {
			row['pymntDt'] = $(target).val();
			changed=true;
		}
		if(this.$('#thisTimeEstbAmt').is(target)) {
			row['thisTimeEstbAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#depositExcclcAmt').is(target)) {
			row['depositExcclcAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#pymntAmt').is(target)) {
			row['pymntAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#pymntAggrAmt').is(target)) {
			row['pymntAggrAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#rm1').is(target)) {
			row['rm'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#fcltyCtrtMoneyPymntList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltyCtrtMoneyPymntList').flexUpdateRow(rowid, row);
	}
};

// 계약대금지급 추가
GamFcltyCtrtMngModule.prototype.addCtrtMoneyPymntItem = function() {
	this.$('#gamCtrtMoneyPymntMngtForm :input').val('');
	this.$("#fcltyCtrtMoneyPymntList").flexAddRow({'_updtId': 'I', 'ctrtNo':'', 'seq':'', 'pymntCl':'','pymntDt':'','thisTimeEstbAmt':'','depositExcclcAmt':'','pymntAmt':'','pymntAggrAmt':'' ,'rm':''});
	var allRows = this.$('#fcltyCtrtMoneyPymntList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#fcltyCtrtMoneyPymntList").selectRowId(selRowId);
};

// 계약대금지급 삭제
GamFcltyCtrtMngModule.prototype.removeCtrtMoneyPymntItem = function() {
	var rows = this.$("#fcltyCtrtMoneyPymntList").selectedRows();
    if(rows.length == 0){
        alert("계약대금지급목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#fcltyCtrtMoneyPymntList").selectedRowIds().length>0) {
    	for(var i=this.$("#fcltyCtrtMoneyPymntList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#fcltyCtrtMoneyPymntList").flexGetRow(this.$("#fcltyCtrtMoneyPymntList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteCtrtMoneyPymntList[this._deleteCtrtMoneyPymntList.length] = row;
			}
        	this.$("#fcltyCtrtMoneyPymntList").flexRemoveRow(this.$("#fcltyCtrtMoneyPymntList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamCtrtMoneyPymntMngtForm :input").val("");
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

//계약이행이월 속성변경 이벤트 처리
GamFcltyCtrtMngModule.prototype.ctrtFulFillCaryFwdChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#fcltyCtrtFulFillCaryFwdList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#fulfillCaryFwdYear').is(target)) {
			row['fulfillCaryFwdYear'] = $(target).val();
			changed=true;
		}
		if(this.$('#fulfillAmt').is(target)) {
			row['fulfillAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#caryFwdAmt').is(target)) {
			row['caryFwdAmt'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#fcltyCtrtFulFillCaryFwdList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltyCtrtFulFillCaryFwdList').flexUpdateRow(rowid, row);
	}
};


// 계약이행이월 추가
GamFcltyCtrtMngModule.prototype.addCtrtFulFillCaryFwdItem = function() {
	this.$('#gamCtrtFulFillCaryFwdMngtForm :input').val('');
	this.$("#fcltyCtrtFulFillCaryFwdList").flexAddRow({'_updtId': 'I', 'ctrtNo':'', 'seq':'', 'fulfillCaryFwdYear':'','fulfillAmt':'','caryFwdAmt':''});
	var allRows = this.$('#fcltyCtrtFulFillCaryFwdList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#fcltyCtrtFulFillCaryFwdList").selectRowId(selRowId);
};

// 계약이행이월 삭제
GamFcltyCtrtMngModule.prototype.removeCtrtFulFillCaryFwdItem = function() {
	var rows = this.$("#fcltyCtrtFulFillCaryFwdList").selectedRows();
    if(rows.length == 0){
        alert("계약이행이월목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#fcltyCtrtFulFillCaryFwdList").selectedRowIds().length>0) {
    	for(var i=this.$("#fcltyCtrtFulFillCaryFwdList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#fcltyCtrtFulFillCaryFwdList").flexGetRow(this.$("#fcltyCtrtFulFillCaryFwdList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteCtrtFulFillCaryFwdList[this._deleteCtrtFulFillCaryFwdList.length] = row;
			}
        	this.$("#fcltyCtrtFulFillCaryFwdList").flexRemoveRow(this.$("#fcltyCtrtFulFillCaryFwdList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamCtrtFulFillCaryFwdMngtForm :input").val("");
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

//계약낙찰정보 속성변경 이벤트 처리
GamFcltyCtrtMngModule.prototype.ctrtScsbidInfoChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#fcltyCtrtScsbidInfoList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#scsbidRank').is(target)) {
			row['scsbidRank'] = $(target).val();
			changed=true;
		}
		if(this.$('#entrpsNm2').is(target)) {
			row['entrpsNm'] = $(target).val();
			changed=true;
		}
		if(this.$('#rprsntv2').is(target)) {
			row['rprsntv'] = $(target).val();
			changed=true;
		}
		if(this.$('#bsnmNo2').is(target)) {
			row['bsnmNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#tlphonNo2').is(target)) {
			row['tlphonNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#faxNo2').is(target)) {
			row['faxNo'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#fcltyCtrtScsbidInfoList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltyCtrtScsbidInfoList').flexUpdateRow(rowid, row);
	}
};

// 계약낙찰정보 추가
GamFcltyCtrtMngModule.prototype.addCtrtScsbidInfoItem = function() {
	this.$('#gamCtrtScsbidInfoMngtForm :input').val('');
	this.$("#fcltyCtrtScsbidInfoList").flexAddRow({'_updtId': 'I', 'ctrtNo':'', 'seq':'', 'scsbidRank': '', 'entrpsNm':'','rprsntv':'','bsnmNo':'' ,'tlphonNo':'','faxNo':''});
	var allRows = this.$('#fcltyCtrtScsbidInfoList').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#fcltyCtrtScsbidInfoList").selectRowId(selRowId);
};

// 계약낙찰정보 삭제
GamFcltyCtrtMngModule.prototype.removeCtrtScsbidInfoItem = function() {
	var rows = this.$("#fcltyCtrtScsbidInfoList").selectedRows();
    if(rows.length == 0){
        alert("계약공동도급목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#fcltyCtrtScsbidInfoList").selectedRowIds().length>0) {
    	for(var i=this.$("#fcltyCtrtScsbidInfoList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#fcltyCtrtScsbidInfoList").flexGetRow(this.$("#fcltyCtrtScsbidInfoList").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteCtrtScsbidInfoList[this._deleteCtrtScsbidInfoList.length] = row;
			}
        	this.$("#fcltyCtrtScsbidInfoList").flexRemoveRow(this.$("#fcltyCtrtScsbidInfoList").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamCtrtScsbidInfoMngtForm :input").val("");
};

//계약낙찰정보 병합저장
GamFcltyCtrtMngModule.prototype.saveCtrtScsbidInfoList = function() {
	var dataList = this.$("#fcltyCtrtScsbidInfoList").flexGetData();
	for(var i=0; i<dataList.length; i++) {
		dataList[i]["ctrtNo"] = this.$("#ctrtNo").val();
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#fcltyCtrtScsbidInfoList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#fcltyCtrtScsbidInfoList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteCtrtScsbidInfoList) };
    this.doAction('/ctrt/mergeFcltyCtrtScsbidInfo.do', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteCtrtScsbidInfoList = [];				    	
			var opts = [
		           		{name: 'sCtrtNo', value: module.$("#ctrtNo").val() }
			           ];
			module.$("#fcltyCtrtScsbidInfoList").flexOptions({params:opts}).flexReload();
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
        case 'btnCtrtJoinContrAdd' : //계약공동도급 추가
        	this.addCtrtJoinContrItem();
        	break;
        case 'btnCtrtJoinContrRemove' : //계약공동도급 삭제
        	this.removeCtrtJoinContrItem();
        	break;
        case 'btnCtrtSubCtrtAdd' : //계약하도급 추가
        	this.addCtrtSubCtrtItem();
        	break;
        case 'btnCtrtSubCtrtRemove' : //계약하도급 삭제
        	this.removeCtrtSubCtrtItem();
        	break;
        case 'btnCtrtChangeAdd' : //계약변경 추가
        	this.addCtrtChangeItem();
        	break;
        case 'btnCtrtChangeRemove' : //계약변경 삭제
        	this.removeCtrtChangeItem();
        	break;
        case 'btnCtrtMoneyPymntAdd' : //계약대금지급 추가
        	this.addCtrtMoneyPymntItem();
        	break;
        case 'btnCtrtMoneyPymntRemove' : //계약대금지급 삭제
        	this.removeCtrtMoneyPymntItem();
        	break;
        case 'btnCtrtFulFillCaryFwdAdd' : //계약이행이월 추가
        	this.addCtrtFulFillCaryFwdItem();
        	break;
        case 'btnCtrtFulFillCaryFwdRemove' : //계약이행이월 삭제
        	this.removeCtrtFulFillCaryFwdItem();
        	break;
        case 'btnCtrtScsbidInfoAdd' : //계약낙찰정보 추가
        	this.addCtrtScsbidInfoItem();
        	break;
        case 'btnCtrtScsbidInfoRemove' : //계약낙찰정보 삭제
        	this.removeCtrtScsbidInfoItem();
        	break;
        case 'popupEntrpsInfo': // 업체선택 팝업을 호출한다.(조회)
            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', opts);
            break;
        case 'popupEntrpsInfo2': // 업체선택 팝업을 호출한다.(계약정보관리)
            this.doExecuteDialog('selectEntrpsInfoPopup2', '업체 선택', '/popup/showEntrpsInfo.do', opts);
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
	    case 'tabs8':
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
                            <th width="10%" style="text-align:center;">계약명</th>
                            <td>
                            	<input id="sCtrtNm" type="text" size="25">
                         	</td>
							<th width="10%">계약일자</th>
                            <td>
                            	<input id="sCtrtFrDt" type="text" class="emdcal" size="12"> ~ 
                            	<input id="sCtrtToDt" type="text" class="emdcal" size="12">
                            </td>
                            
                            <td rowspan="2">
								<button id="btnSearch" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                        <tr>
                        	<th width="10%">등록업체</th>
                            <td colspan="3">
                            	<input id="sRegistEntrpsCd" type="text" size="7">&nbsp; &nbsp;
                         		<input id="sRegistEntrpsNm" type="text" size="27" disabled="disabled">&nbsp; &nbsp;
                         		<button id="popupEntrpsInfo" class="popupButton">선택</button>
                         	</td>
                         	<th width="10%">계약금액</th>
                            <td>
                            	<input id="sCtrtAmtFr" type="text" class="ygpaNumber" size="12">~
                            	<input id="sCtrtAmtTo" type="text" class="ygpaNumber" size="12">원
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
                <li><a href="#tabs8" class="emdTab">계약낙찰정보관리</a></li>
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
								<td><input type="text" size="17" id="sumPlanAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">예정금액</th>
								<td><input type="text" size="17" id="sumPrmtAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">낙찰금액</th>
								<td><input type="text" size="17" id="sumScsbidAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">기초금액</th>
								<td><input type="text" size="17" id="sumBaseAmt" class="ygpaNumber" disabled="disabled" /></td>
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
                                <td width="20%">
	                                <select id="ctrtSe">
	                                    <option value="" selected="selected">선택</option>
	                                    <option value="1">자체</option>
	                                    <option value="2">조달</option>
	                                </select>
                                </td>
                                <th width="10%" height="18">계약번호</th>
                                <td width="20%"><input type="text" size="20" id="ctrtNo" maxlength="100" disabled="disabled" /></td>
                                <th width="10%" height="18">계약명</th>
                                <td><input type="text" size="20" id="ctrtNm" maxlength="33" /></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">발주방식</th>
                                <td><input type="text" size="20" id="orderMthd" maxlength="33" /></td>
                                <th width="10%" height="18">계약방법</th>
                                <td><input type="text" size="20" id="ctrtMth" maxlength="2" /></td>
                                <th width="10%" height="18">입찰공고번호</th>
                                <td><input type="text" size="20" id="bidPblancNo" maxlength="100" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">입찰공고일자</th>
                                <td><input type="text" size="20" id="bidPblancDt" class="emdcal" /></td>
								<th width="10%" height="18">입찰일자</th>
                                <td><input type="text" size="20" id="bidDt" class="emdcal" /></td>
                            	<th width="10%" height="18">하자기간</th>
                                <td><input type="text" size="15" id="flawDtFrom" class="emdcal" /> ~ <input type="text" size="15" id="flawDtTo" class="emdcal" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">입찰방법</th>
                                <td><input type="text" size="20" id="bidMth" maxlength="33" /></td>
                            	<th width="10%" height="18">업무담당부서코드</th>
                                <td><input type="text" size="20" id="jobChrgDeptCd" maxlength="8"/></td>
                                <th width="10%" height="18">등록업체</th>
                                <td>
                                	<input type="text" size="10" id="registEntrpsCd" maxlength="10" /> 
                                	<input type="text" size="15" id="registEntrpsNm" disabled="disabled" /> 
                                	<button id="popupEntrpsInfo2" class="popupButton">선택</button> 
                                </td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">원인행위</th>
                                <td><input type="text" size="20" id="causeAct" maxlength="33" /></td>
								<th width="10%" height="18">설계금액</th>
                                <td><input type="text" size="20" id="planAmt" class="ygpaNumber" />원</td>
                                <th width="10%" height="18">예정금액</th>
                                <td><input type="text" size="20" id="prmtAmt" class="ygpaNumber" />원</td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">낙찰금액</th>
                                <td><input type="text" size="20" id="scsbidAmt" class="ygpaNumber" />원</td>
                                <th width="10%" height="18">낙찰율</th>
                                <td><input type="text" size="20" id="scsbidRate" class="ygpaNumber" data-decimal-point="5"/></td>
                                <th width="10%" height="18">기초금액</th>
                                <td><input type="text" size="20" id="baseAmt" class="ygpaNumber" />원</td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약일자</th>
                                <td><input type="text" size="20" id="ctrtDt" class="emdcal" /></td>
                            	<th width="10%" height="18">계약금액</th>
                                <td><input type="text" size="20" id="ctrtAmt" class="ygpaNumber" />원</td>
                                <th width="10%" height="18">계약기간</th>
                                <td><input type="text" size="15" id="ctrtDtFrom" class="emdcal" /> ~ <input type="text" size="15" id="ctrtDtTo" class="emdcal" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약보증금액</th>
                                <td><input type="text" size="20" id="ctrtGrntyAmt" class="ygpaNumber" />원</td>
                                <th width="10%" height="18">계약보증방법</th>
                                <td><input type="text" size="20" id="ctrtGrntyMth" maxlength="2" /></td>
								<th width="10%" height="18">조달공고번호</th>
                                <td><input type="text" size="20" id="prcuPblancNo" maxlength="100" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">감독자1</th>
                                <td><input type="text" size="20" id="intendant1" maxlength="3" /></td>
                                <th width="10%" height="18">감독자2</th>
                                <td><input type="text" size="20" id="intendant2" maxlength="3" /></td>
                                <th width="10%" height="18">감독자3</th>
                                <td><input type="text" size="20" id="intendant3" maxlength="3" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약검사일자</th>
                                <td><input type="text" size="20" id="ctrtExamDt" class="emdcal" /></td>
								<th width="10%" height="18">이월예산금액</th>
                                <td><input type="text" size="20" id="caryFwdBdgtAmt" class="ygpaNumber" />원</td>
                                <th width="10%" height="18">전자결재전송구분</th>
                                <td><input type="text" size="20" id="elctrnSanctnTrnsmisSe" maxlength="2"/></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">전자결재진행코드</th>
                                <td><input type="text" size="20" id="elctrnSanctnProgrsCd" maxlength="2" /></td>
                            	<th width="10%" height="18">전자결재전송일자</th>
                                <td><input type="text" size="20" id="elctrnSanctnTrnsmisDt" class="emdcal" /></td>
                                <th width="10%" height="18">전자결재연동정보</th>
                                <td><input type="text" size="20" id="elctrnSanctnInterlockInfo" maxlength="100"/></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">전자결재문서ID</th>
                                <td><input type="text" size="20" id="elctrnSanctnDocId" maxlength="100" /></td>
                                <th width="10%" height="18">승인일자</th>
                                <td><input type="text" size="20" id="confmDt" class="emdcal" /></td>
                            	<th width="10%" height="18">승인자코드</th>
                                <td><input type="text" size="20" id="confmerCd" maxlength="10" /></td>
                            </tr>
                            <tr>
                                <th width="10%" height="18">연대보증</th>
                                <td><input type="text" size="20" id="sldrtGrnty" maxlength="33" /></td>
                                <th width="10%" height="18">현장설명</th>
                                <td colspan="3"><input type="text" size="20" id="siteDesc" maxlength="33" /></td>
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
            <div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyCtrtJoinContrList" style="display:none"></table>
		        <div class="emdControlPanel">
		            <button id="btnCtrtJoinContrAdd">추가</button>
		            <button id="btnCtrtJoinContrRemove">삭제</button>
		            <button id="btnSave">저장</button>
		        </div>
				<form id="gamCtrtJoinContrMngtForm">
					<table class="searchPanel">
						<tbody>
							<tr>
		                        <th>업체명</th>
		                        <td><input id="entrpsNm" type="text" style="width: 150px;" class="EditItem" maxlength="33"/></td>
		                        <th>대표자</th>
		                        <td><input id="rprsntv" type="text" style="width: 150px;" class="EditItem" maxlength="33"/></td>
								<th>지분율</th>
		                        <td><input id="qotaRate" type="text" style="width: 150px;" class="EditItem ygpaNumber" data-decimal-point="5"/>%</td>
							</tr>
							<tr>
		                    	<th>업종</th>
		                        <td><input id="induty" type="text" style="width: 150px;" class="EditItem" maxlength="13"/></td>
		                        <th>주요품목</th>
		                        <td><input id="stplPrdlst" type="text" style="width: 150px;" class="EditItem" maxlength="13"/></td>
								<th>사업자번호</th>
		                        <td><input id="bsnmNo" type="text" style="width: 150px;" class="EditItem" maxlength="14"/></td>
							</tr>
							<tr>
		                    	<th>거래관계</th>
		                        <td><input id="dealRelate" type="text" style="width: 150px;" class="EditItem" maxlength="2"/></td>
		                        <th>전화번호</th>
		                        <td><input id="tlphonNo" type="text" style="width: 150px;" class="EditItem" maxlength="100"/></td>
								<th>팩스번호</th>
		                        <td><input id="faxNo" type="text" style="width: 150px;" class="EditItem" maxlength="100"/></td>
							</tr>
							<tr>
		                    	<th>담당자</th>
		                        <td><input id="charger" type="text" style="width: 150px;" class="EditItem" maxlength="6"/></td>
		                        <th>담당자직위</th>
		                        <td><input id="chargerOfcPos" type="text" style="width: 150px;" class="EditItem" maxlength="6"/></td>
								<th>담당자휴대폰번호</th>
		                        <td><input id="chargerMoblphonNo" type="text" style="width: 150px;" class="EditItem" maxlength="20"/></td>
							</tr>
							<tr>
		                    	<th>담당자이메일</th>
		                        <td><input id="chargerEmail" type="text" style="width: 150px;" class="EditItem" maxlength="50"/></td>
		                        <th>도로명 주소</th>
		                        <td colspan="3"><input id="roadnmAdres" type="text" style="width: 520px;" class="EditItem" maxlength="65"/></td>
							</tr>					
							<tr>
		                    	<th>우편번호</th>
		                        <td><input id="postNo" type="text" style="width: 150px;" class="EditItem" maxlength="7"/></td>
		                        <th>지번주소</th>
		                        <td colspan="3"><input id="lnmAdres" type="text" style="width: 520px;" class="EditItem" maxlength="65"/></td>
							</tr>
						</tbody>
					</table>
				</form>
            </div>
            
            <!-- 계약하도급관리 -->
            <div id="tabs4" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyCtrtSubCtrtList" style="display:none"></table>
		        <div class="emdControlPanel">
		            <button id="btnCtrtSubCtrtAdd">추가</button>
		            <button id="btnCtrtSubCtrtRemove">삭제</button>
		            <button id="btnSave">저장</button>
		        </div>
				<form id="gamCtrtSubCtrtMngtForm">
					<table class="searchPanel">
						<tbody>
							<tr>
		                        <th>업체명</th>
		                        <td><input id="entrpsNm1" type="text" style="width: 150px;" class="EditItem" maxlength="33"/></td>
		                        <th>대금지급합의</th>
		                        <td><input id="moneyPymntAgree" type="text" style="width: 150px;" class="EditItem" maxlength="2"/></td>
								<th>공종</th>
		                        <td><input id="workClass" type="text" style="width: 150px;" class="EditItem" maxlength="33"/></td>
							</tr>
							<tr>
		                    	<th>하도급율</th>
		                        <td><input id="subctrtRate" type="text" style="width: 150px;" class="EditItem ygpaNumber" data-decimal-point="5"/>%</td>
		                        <th>원도급금액</th>
		                        <td><input id="orginlContrAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
								<th>하도급계약금액</th>
		                        <td><input id="subctrtCtrtAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
							</tr>
							<tr>
		                    	<th>하도급계약기간</th>
		                        <td colspan="5">
		                        	<input id="subCtrtDtFrom" type="text" style="width: 150px;" class="EditItem emdcal"/>
		                        	~<input id="subCtrtDtTo" type="text" style="width: 150px;" class="EditItem emdcal"/>
		                        </td>
							</tr>
						</tbody>
					</table>
				</form>
            </div>
            
            <!-- 계약변경관리 -->
            <div id="tabs5" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyCtrtChangeList" style="display:none"></table>
		        <div class="emdControlPanel">
		            <button id="btnCtrtChangeAdd">추가</button>
		            <button id="btnCtrtChangeRemove">삭제</button>
		            <button id="btnSave">저장</button>
		        </div>
				<form id="gamCtrtChangeMngtForm">
					<table class="searchPanel">
						<tbody>
							<tr>
		                        <th>변경일자</th>
		                        <td><input id="changeDt" type="text" style="width: 150px;" class="EditItem emdcal"/></td>
		                    	<th>변경계약기간</th>
		                        <td colspan="3">
		                        	<input id="changeCtrtDtFrom" type="text" style="width: 150px;" class="EditItem emdcal"/>
		                        	~<input id="changeCtrtDtTo" type="text" style="width: 150px;" class="EditItem emdcal"/>
		                        </td>
							</tr>
							<tr>
		                        <th>변경계약금액</th>
		                        <td><input id="changeCtrtAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
								<th>최종계약금액</th>
		                        <td colspan=><input id="lastCtrtAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
							</tr>
							<tr>
		                        <th>변경사유</th>
		                        <td><input id="changeRsn" type="text" style="width: 150px;" class="EditItem" maxlength="33"/></td>
		                    	<th>비고</th>
		                        <td>
		                        	<input id="rm" type="text" style="width: 350px;" class="EditItem" maxlength="333"/>
		                        </td>
							</tr>
						</tbody>
					</table>
				</form>
            </div>
            
            <!-- 계약대금지급관리 -->
            <div id="tabs6" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyCtrtMoneyPymntList" style="display:none"></table>
		        <div class="emdControlPanel">
		            <button id="btnCtrtMoneyPymntAdd">추가</button>
		            <button id="btnCtrtMoneyPymntRemove">삭제</button>
		            <button id="btnSave">저장</button>
		        </div>
				<form id="gamCtrtMoneyPymntMngtForm">
					<table class="searchPanel">
						<tbody>
							<tr>
		                        <th>지급분류</th>
		                        <td><input id="pymntCl" type="text" style="width: 150px;" class="EditItem" maxlength="2"/></td>
		                        <th>지급일자</th>
		                        <td><input id="pymntDt" type="text" style="width: 150px;" class="EditItem  emdcal"/></td>
		                        <th>금회기성금액</th>
		                        <td><input id="thisTimeEstbAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
							</tr>
							<tr>
		                        <th>선금정산금액</th>
		                        <td><input id="depositExcclcAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
		                        <th>지급금액</th>
		                        <td><input id="pymntAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
		                        <th>지급누계금액</th>
		                        <td><input id="pymntAggrAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
							</tr>
							<tr>
		                    	<th>비고</th>
		                        <td colspan="3">
		                        	<input id="rm1" type="text" style="width: 475px;" class="EditItem" maxlength="333"/>
		                        </td>
							</tr>
						</tbody>
					</table>
				</form>
            </div>
                        
            <!-- 계약이행이월관리 -->
            <div id="tabs7" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyCtrtFulFillCaryFwdList" style="display:none"></table>
		        <div class="emdControlPanel">
		            <button id="btnCtrtFulFillCaryFwdAdd">추가</button>
		            <button id="btnCtrtFulFillCaryFwdRemove">삭제</button>
		            <button id="btnSave">저장</button>
		        </div>
				<form id="gamCtrtFulFillCaryFwdMngtForm">
					<table class="searchPanel">
						<tbody>
							<tr>
		                        <th>이행이월년도</th>
		                        <td><input id="fulfillCaryFwdYear" type="text" style="width: 150px;" class="EditItem" maxlength="4"/>년</td>
		                        <th>변경계약금액</th>
		                        <td><input id="fulfillAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
								<th>최종계약금액</th>
		                        <td colspan=><input id="caryFwdAmt" type="text" style="width: 150px;" class="EditItem ygpaNumber"/>원</td>
							</tr>
						</tbody>
					</table>
				</form>
            </div>

            <!-- 계약낙찰정보관리 -->
            <div id="tabs8" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyCtrtScsbidInfoList" style="display:none"></table>
		        <div class="emdControlPanel">
		            <button id="btnCtrtScsbidInfoAdd">추가</button>
		            <button id="btnCtrtScsbidInfoRemove">삭제</button>
		            <button id="btnSave">저장</button>
		        </div>
				<form id="gamCtrtScsbidInfoMngtForm">
					<table class="searchPanel">
						<tbody>
							<tr>
								<th>낙찰순위</th>
		                        <td><input id="scsbidRank" type="text" style="width: 150px;" class="EditItem ygpaNumber" maxlength="3"/></td>
		                        <th>업체명</th>
		                        <td><input id="entrpsNm2" type="text" style="width: 150px;" class="EditItem" maxlength="33"/></td>
		                        <th>대표자</th>
		                        <td><input id="rprsntv2" type="text" style="width: 150px;" class="EditItem" maxlength="33"/></td>
							</tr>
							<tr>
								<th>사업자번호</th>
		                        <td><input id="bsnmNo2" type="text" style="width: 150px;" class="EditItem" maxlength="14"/></td>
		                        <th>전화번호</th>
		                        <td><input id="tlphonNo2" type="text" style="width: 150px;" class="EditItem" maxlength="100"/></td>
								<th>팩스번호</th>
		                        <td><input id="faxNo2" type="text" style="width: 150px;" class="EditItem" maxlength="100"/></td>
							</tr>
						</tbody>
					</table>
				</form>
	        </div>
            
        </div>
    </div>
</div>