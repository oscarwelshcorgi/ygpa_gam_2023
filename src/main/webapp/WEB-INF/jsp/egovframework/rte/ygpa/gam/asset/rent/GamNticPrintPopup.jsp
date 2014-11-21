<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamNticPrintPopup.jsp
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
function GamNticPrintPopupModule() {}

GamNticPrintPopupModule.prototype = new EmdPopupModule(1000, 620);

// 팝업이 호출 되었을때 호출 되는 함수
GamNticPrintPopupModule.prototype.loadComplete = function() {

	this.resizable(true);

	// 테이블 설정

    this.$("#assetRentFeePayList").flexigrid({
        module: this,
        url: '/asset/rent/gamSelectAssetRentFeePayDtlsMngtList.do',
        dataType: 'json',
        colModel : [
					{display:'일련번호', name:'intSeq',width:100, sortable:false,align:'center'},
					{display:'처리구분', name:'delKind',width:100, sortable:false,align:'center'},
					{display:'징수일자(ERP)', name:' jingsuDate',width:100, sortable:false,align:'center'},
					{display:'수납일자(ERP)', name:'sunapDate',width:100, sortable:false,align:'center'},
					{display:'청코드', name:'prtAtCode',width:100, sortable:false,align:'center'},
					{display:'요금종류', name:'feeTp',width:100, sortable:false,align:'center'},
					{display:'회계년도', name:'fiscalYr',width:100, sortable:false,align:'center'},
					{display:'고지번호', name:'billNo',width:100, sortable:false,align:'center'},
					{display:'회계구분코드', name:'accntCode',width:100, sortable:false,align:'center'},
					{display:'업체코드', name:'agentCode',width:100, sortable:false,align:'center'},
					{display:'고지금액', name:'billAmnt',width:100, sortable:false,align:'center'},
					{display:'고지일자', name:'billDt',width:100, sortable:false,align:'center'},
					{display:'고지서발부여부', name:'billPrtYn',width:100, sortable:false,align:'center'},
					{display:'납부기한일자', name:'dueDate',width:100, sortable:false,align:'center'},
					{display:'수납일자', name:'rcvdDt',width:100, sortable:false,align:'center'},
					{display:'수납구분', name:'rcvdTp',width:100, sortable:false,align:'center'},
					{display:'불능코드', name:'rsltCode',width:100, sortable:false,align:'center'},
					{display:'면제 ( 보전 ) 금액', name:'exmpAmnt',width:100, sortable:false,align:'center'},
					{display:'과오납금액', name:'overAmnt',width:100, sortable:false,align:'center'},
					{display:'할인금액', name:'dcAmnt',width:100, sortable:false,align:'center'},
					{display:'할인사유', name:'dcCode',width:100, sortable:false,align:'center'},
					{display:'할인코드', name:'dcRate',width:100, sortable:false,align:'center'},
					{display:'금융기관 수납일자', name:'recptEpdt',width:100, sortable:false,align:'center'},
					{display:'전자고지 결과', name:'elctBillRslt',width:100, sortable:false,align:'center'},
					{display:'전자고지 정보조회일자', name:'bullInfoInqrDtime',width:100, sortable:false,align:'center'}

					/*
					{display:'산출내역', name:'amntRsn',width:100, sortable:false,align:'center'},
					{display:'사업자등록번호', name:'bzRgstId',width:100, sortable:false,align:'center'},
					{display:'사업장명', name:'agentName',width:100, sortable:false,align:'center'},
					{display:'정산여부', name:'last',width:100, sortable:false,align:'center'},
					{display:'부가세여부', name:'vatYn',width:100, sortable:false,align:'center'},
					{display:'지로번호', name:'jiroNo',width:100, sortable:false,align:'center'},
					{display:'전자납부번호', name:'elecPayNo',width:100, sortable:false,align:'center'},
					{display:'고객관리번호', name:'customerMngtNo',width:100, sortable:false,align:'center'},
					{display:'고객주소', name:'customerAddr',width:100, sortable:false,align:'center'},
					{display:'납부자성명', name:'payName',width:100, sortable:false,align:'center'}
					*/
                    ],
        showTableToggleBtn: false,
        height: '480',
        preProcess: function(module,data) {
            module.$('#totalResultCnt').val(data.totalCount);
            module.$('#sumFee').val(data.sumFee);
            module.$('#sumVat').val(data.sumVat);
            module.$('#sumNticAmt').val(data.sumNticAmt);

            return data;
        }
    });

	this.$("#assetRentFeePayList").on("onItemDoubleClick", function(event, module, row, grid, param) {

		// 이벤트내에선 모듈에 대해 선택한다.
		module.closeDialog("ok", row);
	});

	this.$('#printpage').button().click(function() {
		w=window.open();
		w.document.write($('.printPage').html());
		w.print();
		w.close();
	});

};


// 사용자 설정 함수 추가
GamNticPrintPopupModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "selectGisAssetCode":
		this.loadData();
		break;
	case "btnOk":
		rows = this.$('#assetRentMngtList').selectedRows();
		if(rows.length>0 && confirm("선택한 건에 대해서 연체 고지를 하시겠습니까?")) {
	        this.doAction('/asset/rent/mergeNticArrrgList.do', rows[0], function(module, result) {

	            if(result.resultCode=='0') {
	                var searchOpt=module.makeFormArgs('#gamAssetRentMngtSearchForm');
	                module.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
	            }

//	            alert(result.resultMsg);
	        	alert("연체 고지 되었습니다.");
	        });
		}
		break;
	case "cancel":
		this.cancelDialog();
	}

};

GamNticPrintPopupModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamAssetRentFeePayDtlsSearchForm");
 	this.$("#assetRentFeePayList").flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamNticPrintPopupModule();
</script>
<div class="dialog fillHeight">
<div class="emdPanel fillHeight">
	<a id="printpage" >인쇄</a>
	<div class="printPage">
	<img src="<c:url value='/images/egovframework/ygpa/gam/misc/giro.png'/>" style="width: 100%; height:100%" >
	<div class="page-break"></div>
	</div>
</div>
</div>