<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocAgent.jsp
 * @Description : 비관리청허가원부 (비관리청관리/비관리청허가원부)
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.09.19  lsl          최초 생성
 *
 * author lsl
 * since 2014.09.19
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<%-- <validator:javascript formName="gamAssetRent" method="validateGamAssetRent" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetRentDetail" method="validateGamAssetRentDetail" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetRentFile" method="validateGamAssetRentFile" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" /> --%>

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSocAgentMngtModule() {}

GamSocAgentMngtModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocAgentMngtModule.prototype.loadComplete = function() {

    // 자산임대 테이블 설정
    this.$("#socAgentMngtList").flexigrid({
        module: this,
        url: '<c:url value="/soc/gamSelectSocAgentList.do" />',
        dataType: 'json',
        colModel : [
					{display:"선택", 		name:"chkDel",		width:40, 	sortable:false,		align:"center", 	displayFormat:"checkbox"},
					{display:'업체코드', name:'agentCode',width:80, sortable:false,align:'center'},
                    {display:'업체명', name:'firmKorNm',width:160, sortable:false,align:'center'},
                    {display:'보전처리대상금액', name:'totalAmnt',width:150, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'보전처리누계액', name:'accFee',width:150, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'비고', name:'remark',width:260, sortable:false,align:'left'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
        	//그리드 상단 입력창에 정보 입력
        	module.$('#prtAtCode').val(data.socAgentInfo.prtAtCode);
        	module.$('#prtAtCodeStr').val(data.socAgentInfo.prtAtCode);
        	module.$('#agentOwner').val(data.socAgentInfo.agentOwner);
        	module.$('#socCnstNm').val(data.socAgentInfo.socCnstNm);
        	module.$('#aprvDt').val(data.socAgentInfo.aprvDt);
        	module.$('#cnstLoc').val(data.socAgentInfo.cnstLoc);
        	module.$('#perfDt').val(data.socAgentInfo.perfDt);
        	module.$('#agentCode').val(data.socAgentInfo.agentCode);
        	module.$('#agentName').val(data.socAgentInfo.agentName);
        	module.$('#cmplDt').val(data.socAgentInfo.cmplDt);
        	module.$('#agentAddr').val(data.socAgentInfo.agentAddr);
        	//module.$('#totalBuildFee').val(data.socAgentInfo.);
        	module.$('#totalAccFee').val($.number(data.socAgentInfo.accFee));
        	
        	//항만공사시행허가원부II 정보입력
        	module.$('#socObj').val(data.socAgentInfo.socObj);
        	module.$('#socGigian').val(data.socAgentInfo.socGigian);
        	module.$('#socPrivate').val(data.socAgentInfo.socPrivate);
        	module.$('#socNation').val(data.socAgentInfo.socNation);
        	module.$('#socWidth').val(data.socAgentInfo.socWidth);
        	module.$('#reserachAmnt').val(data.socAgentInfo.reserachAmnt);
        	module.$('#pureAmnt').val(data.socAgentInfo.pureAmnt);
        	module.$('#extraAmnt1').val(data.socAgentInfo.extraAmnt1);
        	module.$('#extraAmnt2').val(data.socAgentInfo.extraAmnt2);
        	module.$('#extraAmnt3').val(data.socAgentInfo.extraAmnt3);
        	module.$('#primeTxt').val(data.socAgentInfo.primeTxt);
        	module.$('#modifyTxt').val(data.socAgentInfo.modifyTxt);
        	module.$('#startDt').val(data.socAgentInfo.startDt);
        	module.$('#modifyDt1').val(data.socAgentInfo.modifyDt1);
        	module.$('#freefrDt').val(data.socAgentInfo.freefrDt);
        	module.$('#freetoDt').val(data.socAgentInfo.freetoDt);
        	module.$('#manageDt').val(data.socAgentInfo.manageDt);
        	module.$('#freeuseDt').val(data.socAgentInfo.freeuseDt);
        	module.$('#assetDt').val(data.socAgentInfo.assetDt);
        	module.$('#otherDt').val(data.socAgentInfo.otherDt);
        	
			//자료수, 합산금액 입력
            module.$('#totalCount').val($.number(data.totalCount));
            module.$('#sumTotalAmnt').val($.number(data.sumTotalAmnt));
            module.$('#sumAccFee').val($.number(data.sumAccFee));

            return data;
        }
    });

    

    this.$("#socAgentMngtList").on('onItemSelected', function(event, module, row, grid, param) {
        /* module.$('#cmd').val('modify');

        module.$('#gamAssetRentForm :input').val('');

        module.makeFormValues('#gamAssetRentForm', row);
        module._editData=module.getFormValues('#gamAssetRentForm', row);
        module._editRow=module.$('#socAgentMngtList').selectedRowIds()[0];

        //해당하는 자산임대상세 목록과 파일목록를 불러온다.
        module.$('#detailPrtAtCode').val(row['prtAtCode']);
        module.$('#prtAtCodeStr').val(row['prtAtCode']);
        module.$('#detailMngYear').val(row['mngYear']);
        module.$('#detailMngNo').val(row['mngNo']);
        module.$('#detailMngCnt').val(row['mngCnt']);

        if( row['prmisnYn'] == 'Y' ) {
            module.$('#entrpscd').attr('readonly', true);
            module.$('#popupEntrpsInfoInput').attr('disabled', 'disabled');
        }

        var searchOpt=module.makeFormArgs('#gamAssetRentForm');
        module.$('#socAgentDetailList').flexOptions({params:searchOpt}).flexReload();
        module.$('#socAgentFileList').flexOptions({params:searchOpt}).flexReload();

        module.calcFirstPaymentAmount();	//  고지방법에 따른 1회차 사용료 적용
        module.loadEntrpsChargerList();	// 담당자 목록을 불러온다. */

//        module.selectFeatureData('socAgentDetail', row, true);

        //this._deleteDataFileList=[]; //삭제파일목록 초기화
    });

    /* this.$("#socAgentDetailList").on('onItemSelected', function(event, module, row, grid, param) {
        //module.$('#btnApplyGisAssetsCode').prop('disabled', false);
        module.$('#gamAssetRentDetailForm :input').val('');

        module.makeFormValues('#gamAssetRentDetailForm', row);
        module._editData=module.getFormValues('#gamAssetRentDetailForm', row);
        module._editRow=module.$('#socAgentDetailList').selectedRowIds()[0];

        module.loadEntrpsChargerList();	// 담당자 목록을 불러온다.

    }); */

    
    /* this.$("#socAgentMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#socAgentListTab").tabs("option", {active: 1});
        module.$('#cmd').val('modify');
//        module.$('#gamAssetRentForm :input').val('');	// makeFormValues 에서 값을 지우므로 특별 한 일이 없는 한 각각 지우지 않는다. 클래스 특성을 반영 못 하는 경우가 생긴다

        module.makeFormValues('#gamAssetRentForm', row);
        module._editData=module.getFormValues('#gamAssetRentForm', row);
        module._editRow=module.$('#socAgentMngtList').selectedRowIds()[0];

        if(row!=null) {
            module.$('#cmd').val('modify');
        }

        module.calcFirstPaymentAmount();	//  고지방법에 따른 1회차 사용료 적용
        //this._deleteDataFileList=[]; //삭제파일목록 초기화
    }); */

    /* this.$("#socAgentDetailList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#socAgentListTab").tabs("option", {active: 2});
        module.$('#gamAssetRentDetailForm :input').val('');
        module.makeFormValues('#gamAssetRentDetailForm', row);
        module._editData=module.getFormValues('#gamAssetRentDetailForm', row);
        module._editRow=module.$('#socAgentDetailList').selectedRowIds()[0];

        module.loadOlnlpList(row);

        if(row!=null) {
            module.$('#detailCmd').val('modify');
        }
    }); */

    // 컴포넌트이 이벤트를 추가한다. (기존 코드 데이터에 선택 값이 onchange 안되는 점을 수정 함)
    /* this.$('#prtAtCode').on('change', {module: this}, function(event) {
        event.data.module.$('#prtAtCodeStr').val($(this).val());
        //alert($(this).getSelectedCodeLabel() + '이(가) 선택되었습니다.');
    });

   	var today = new Date();

	var serchYr = today.getFullYear();
	var serchMn = today.getMonth() + 1;

	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}

	var serchday = today.getDate();
	if(serchday < 10){
		serchday = "0" + serchday;
	}
	var searchEndDate = serchYr + "-" + serchMn + "-" + serchday;

	today.setMonth(today.getMonth() - 3);

	serchYr = today.getFullYear();
	serchMn = today.getMonth() + 1;
	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}
	serchday = today.getDate();
	if(serchday < 10){
		serchday = "0" + serchday;
	}
	this.$("#sEntrpscd").bind("keyup change", {module: this}, function(event) {
		if(event.data.module.$('#sEntrpscd').val() ==''){
			event.data.module.$('#sEntrpsNm').val('');
		}

    });

 	var searchOpt=this.makeFormArgs('#gamAssetRentMngtSearchForm');
	this.$('#socAgentMngtList').flexOptions({params:searchOpt}).flexReload(); */
};


/**
 * 정의 된 버튼 클릭 시
 */
 GamSocAgentMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
			this.loadData();
            break;

        
        // 신청저장
        case 'btnSaveItem':

        	/* if(!validateGamSocAgent(this.$('#gamAssetRentForm')[0])) {
                return;
            } */

            /* if( this.$("#cmd").val() != 'insert' && this.$('#quayGroupCd').val() != 'P' ) {
                alert("해당 건은 자산임대관리 메뉴에서 저장이 불가능합니다.");
                return;
            } */

            /* if( this.$('#prtAtCode').val() == '' ) {
                alert("항구분을 선택하십시오.");
                return;
            }

            if( this.$('#entrpscd').val() == '' ) {
                alert("신청업체를 선택하십시오.");
                return;
            }

            if( confirm("저장하시겠습니까?") ) {
                // 변경된 자료를 저장한다.
                var inputVO=[{name: 'test', value:'test hello'}];
                //var inputVO=[{}];

                //this._editData=this.getFormValues('#gamAssetRentDetailForm', this._editData);

                inputVO[inputVO.length]={name: 'updateList', value :JSON.stringify(this.$('#socAgentDetailList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

                inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#socAgentDetailList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

                inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataList) };

                if(this._deleteDataFileList == undefined ) {
                    this._deleteDataFileList=[];
                }

                inputVO[inputVO.length]={name: 'updateFileList', value :JSON.stringify(this.$('#socAgentFileList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

                inputVO[inputVO.length]={name: 'insertFileList', value: JSON.stringify(this.$('#socAgentFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

                inputVO[inputVO.length]={name: 'deleteFileList', value: JSON.stringify(this._deleteDataFileList) };

                //var otherForm=this.getFormValues('#gamAssetRentForm', {});  // 폼만 있을 경우

                this._editData2=this.getFormValues('#gamAssetRentForm', {_updtId:'I'});
                inputVO[inputVO.length]={name: 'form', value: JSON.stringify(this._editData2) };    // 폼의 데이터를 컨트롤러에 보낸다.

                //// console.log(inputVO);
                // 데이터를 저장 하고 난 뒤 리스트를 다시 로딩 한다.

                this.doAction('<c:url value="/oper/gnrl/gamSavePrtFcltyRentMngt.do" />', inputVO, function(module, result) {
                    if(result.resultCode == 0){
                    	module.loadData();
                    }
                    alert(result.resultMsg);
                });


                this.$("#socAgentListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
            } */

            break;

        //신청삭제
        case 'btnRemoveItem':
            //var rows = this.$('#socAgentMngtList').selectedRows();

            /* if( rows[0]['quayGroupCd'] != 'P' ) {
                alert("해당 건은 자산임대관리 메뉴에서 삭제가 불가능합니다.");
                return;
            } */

            /* if(rows.length == 0) {
                alert("자산임대목록에서 신청삭제할 행을 선택하십시오.");
            } else {
            	if( confirm("신청삭제를 하시겠습니까?") ) {
                    if( rows[0]['prmisnYn'] == null || rows[0]['prmisnYn'] == '' ) {
                        this.$('#detailPrmisnYn').val('N');
                    }

                    var inputVO=this.makeFormArgs('#gamAssetRentForm');

                    this.doAction('<c:url value="/oper/gnrl/gamDeletePrtFcltyRentMngt.do" />', inputVO, function(module, result) {

                        if(result.resultCode=='0') {
                        	module.loadData();
                        }

                        alert(result.resultMsg);
                    });

                    this.$("#socAgentListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
                    this.$('#gamAssetRentForm :input').val("");
                    this.$("#cmd").val('insert');
                }
            } */

            break;

        case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            /*
            var opts = {
                'gisAssetsPrtAtCode': this.$('#prtAtCode').val(),
                'gisAssetsCd': this.$('#gisAssetsCd').val(),
                'gisAssetsSubCd': this.$('#gisAssetsSubCd').val()
            };
            */
            var opts;

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;

    }
};


GamSocAgentMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamSocAgentMngtModule.prototype.loadData = function() {
    this.$("#socAgentListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamSocAgentMngtSearchForm');

    this.$('#socAgentMngtList').flexOptions({params:searchOpt}).flexReload();
	// console.log('debug');

};

GamSocAgentMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        /* var row = this.$('#socAgentMngtList').selectedRows();
        if(row.length==0) {
            this.$('#cmd').val('insert');
        }
        else {
            this.$('#cmd').val('modify');
        }

        if(oldTabId=='tabs1') {
        	this._deleteDataList=[];    // 삭제 목록 초기화
        	this._deleteDataFileList=[];    // 파일삭제 목록 초기화
        } */
        break;
    
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamSocAgentMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     case 'selectEntrpsInfoPopup':
         if (msg != 'cancel') {
             this.$('#sEntrpscd').val(value.entrpscd);
             this.$('#sEntrpsNm').val(value.entrpsNm);
			 this.loadData();
         } else {
             alert('취소 되었습니다');
         }
         break;
     case 'insertEntrpsInfoPopup':
         if (msg != 'cancel') {
             this.$('#entrpscd').val(value.entrpscd);
             this.$('#entrpsNm').val(value.entrpsNm);
             this.loadEntrpsChargerList();	// 담당자 목록을 불러온다.
         } else {
             alert('취소 되었습니다');
         }
         break;
     case 'insertAssetRentPrmisnPopup':
         if (msg != 'cancel') {
             if( value == "0" ) {
					this.loadData();
             }
         } else {
             alert('취소 되었습니다');
         }
         break;
     case 'insertLevReqestAdit':
    	 break;
     case 'selectAssetsCdRentPopup':
         if (msg != 'cancel') {
             this.$('#gisAssetsPrtAtCode').val(value.gisAssetsPrtAtCode);
             this.$('#gisAssetsCd').val(value.gisAssetsCd);
             this.$('#gisAssetsSubCd').val(value.gisAssetsSubCd);
             this.$('#gisAssetsNm').val(value.gisAssetsNm);
             this.$('#gisAssetsLocplc').val(value.gisAssetsLocplc);
             this.$('#gisAssetsLnm').val(value.gisAssetsLnm);
             this.$('#gisAssetsLnmSub').val(value.gisAssetsLnmSub);
             this.$('#gisAssetsAr').val(value.gisAssetsAr);
             this.$('#gisAssetsRealRentAr').val(value.gisAssetsRealRentAr);
             this.$('#gisAssetsPrtAtCodeNm').val(value.gisAssetsPrtAtCodeNm);

             this.$('#usageAr').val(value.gisAssetsRealRentAr);

             //this.$('#quayCd').val(value.gisAssetsQuayCd);
             this.$('#assetsCdStr').val(value.gisAssetsCd + "-" + value.gisAssetsSubCd);
             this._selectAssetsCd=value;

             this.loadOlnlpList(value);
         } else {
             alert('취소 되었습니다');
         }
         break;

     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');

         break;
     }
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSocAgentMngtModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocAgentMngtSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>
                            <td>
                                <!-- <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /> -->
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">전체</option>
                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtAtKorNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>공사준공년도</th>
                            <td width="100px">
                                <select id="sCmplYr">
                                    <option value="" selected="selected">년</option>
                                    <c:forEach  items="${yearsList}" var="yearsItem">
                                        <option value="${yearsItem }">${yearsItem }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>공사일련번호</th>
                            <td>
                            	<input id="sConstNo" type="text" size="15">
                            </td>
                            <td><button id="searchBtn" class="buttonSearch">조회</button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="socAgentListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">항만공사시행허가원부I</a></li>
                <li><a href="#tabs2" class="emdTab">항만공사시행허가원부II</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
                 <div class="emdControlPanel">
					<form id="form1">
    	               	<table class="detailForm"  style="width:100%;">
                            <tr>
                                <th width="16%">*공사항만코드</th>
                                <td>
                                	<!-- <input id="prtAtCode" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM019" /> -->
                                	<select id="prtAtCode">
	                                    <option value="" selected="selected">전체</option>
	                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                        <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtAtKorNm }</option>
	                                    </c:forEach>
	                                </select>
                                    <input type="text" size="4" id="prtAtCodeStr" disabled/>
                                </td>
                                <th width="16%">대표자</th>
                                <td><input type="text" id="agentOwner"></td>
                            </tr>
                            <tr>
                                <th width="16%">*공사명</th>
                                <td><input type="text" id="socCnstNm" size="55"></td>
                                <th width="16%">*공사승인일자</th>
                                <td><input id="aprvDt" type="text" class="emdcal" size="20"></td>
                            </tr>
                            <tr>
                                <th width="16%">위치</th>
                                <td><input type="text" id="cnstLoc" size="55"></td>
                                <th width="16%">*공사허가일자</th>
                                <td><input id="perfDt" type="text" class="emdcal" size="20"></td>
                            </tr>
                            <tr>
                                <th width="16%">*업체코드</th>
                                <td>
                                	<input id="agentCode" type="text" size="10">&nbsp; &nbsp;
	                            	<input id="agentName" type="text" size="15" disabled="disabled">&nbsp; &nbsp;
	                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                                </td>
                                <th width="16%">*공사준공일자</th>
                                <td><input id="cmplDt" type="text" class="emdcal" size="20"></td>
                            </tr>
                            <tr>
                                <th width="16%">주소</th>
                                <td colspan='3'><input input="text" id="agentAddr" size="120" ></td>
                            </tr>
                            <tr>
                                <th width="16%">*총공사금액</th>
                                <td><input type="text" id="totalBuildFee" class="ygpaNumber" size="55" ></td>
                                <th width="16%">보전처리누계액</th>
                                <td><input id="totalAccFee" type="text" class="ygpaNumber" size="20"></td>
                            </tr>
                        </table>
					</form>
                </div>
                
                <table id="socAgentMngtList" style="display:none" class="fillHeight"></table>
                
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
	                                <button id="btnSaveItem">저장</button>
	                                <button id="btnRemoveItem">삭제</button>
	                                <button id="btnPrintItem">출력</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamSocAgentForm">
                        <table class="detailPanel">
                            <tr>
								<th width="20%" height="18">목적</th>
                                <td colspan="3"><input type="text" size="120" id="socObj"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">시행기간</th>
                                <td colspan="3"><input type="text" size="120" id="socGigian"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">국가비귀속 대상시설</th>
                                <td colspan="3"><input type="text" size="120" id="socPrivate"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">국가귀속 대상시설</th>
                                <td colspan="3"><input type="text" size="120" id="socNation"/></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">시행면적</th>
                                <td colspan="3"><input type="text" size="120" id="socWidth" class="ygpaNumber" /></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">조사비</th>
                                <td><input type="text" size="45" id="reserachAmnt" class="ygpaNumber" /></td>
                                <th width="20%" height="18">순공사비</th>
                                <td><input type="text" size="45" id="pureAmnt" class="ygpaNumber" /></td>
                            </tr>
                            <tr>
								<th width="20%" height="18">기타비용</th>
                                <td colspan="3">
                                	<input type="text" size="30" id="extraAmnt1" maxlength="80" class="ygpaNumber"/>
                                	<input type="text" size="30" id="extraAmnt2" maxlength="80" class="ygpaNumber"/>
                                	<input type="text" size="30" id="extraAmnt3" maxlength="80" class="ygpaNumber"/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">주요허가조건</th>
                                <td colspan="3"><input type="text" size="120" id="primeTxt" maxlength="90"/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">변경사항</th>
                                <td colspan="3"><input type="text" size="120" id="modifyTxt" maxlength="90"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">착공일</th>
                                <td><input id="startDt" type="text" class="emdcal" size="20"></td>
                                <th width="20%" height="18">변경일자</th>
                                <td><input id="modifyDt1" type="text" class="emdcal" size="20"></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">무상사용허가기간</th>
                                <td colspan="3"><input id="freefrDt" type="text" class="emdcal" size="20"> ~ <input id="freetoDt" type="text" class="emdcal" size="20"></td>
							</tr>
							<tr>
								<th width="10%" height="18">시설관리권등록일</th>
                                <td colspan="3"><input id="manageDt" type="text" class="emdcal" size="20"></td>
							</tr>
							<tr>
								<th width="10%" height="18">무상사용승인일</th>
                                <td colspan="3"><input id="freeuseDt" type="text" class="emdcal" size="20"></td>
							</tr>
							<tr>
								<th width="10%" height="18">재산귀속일</th>
                                <td colspan="3"><input id="assetDt" type="text" class="emdcal" size="20"></td>
							</tr>
							<tr>
								<th width="10%" height="18">타인사용징수승인일</th>
                                <td colspan="3"><input id="otherDt" type="text" class="emdcal" size="20"></td>
							</tr>
                        </table>
                    </form>
	                 <table style="width:100%">
	                    <tr>
	                        <td width="100"></td>
	                        <td style="text-align:right">
	                            <button id="btnRemoveItem" class="buttonDelete">삭제</button>
	                            <button id="btnSaveItem" class="buttonSave">저장</button>
	                        </td>
	                    </tr>
	                 </table>
                 </div>
            </div>
        </div>
    </div>
</div>