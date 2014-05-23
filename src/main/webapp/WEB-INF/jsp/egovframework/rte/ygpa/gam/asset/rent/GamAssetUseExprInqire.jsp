<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetUseExprInqire.jsp
  * @Description : 자산임대만기도래자료조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.10  heroine     최초 생성
  *
  * author heroine
  * since 2014.01.10
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetUseExprInqireModule() {}

GamAssetUseExprInqireModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetUseExprInqireModule.prototype.loadComplete = function() {
    this.$("#assetUseExprInqireList").flexigrid({
        module: this,
        url: '<c:url value="/asset/rent/gamSelectAssetUseExprInqireList.do" />',
        dataType: 'json',
        colModel : [
					{display:'항코드', name:'prtAtCode',width:40, sortable:false,align:'center'},
                    {display:'항코드명', name:'prtAtCodeNm',width:55, sortable:false,align:'center'},
                    {display:'관리번호', name:'rentMngNo',width:80, sortable:false,align:'center'},
                    {display:'신청업체', name:'entrpscd',width:80, sortable:false,align:'center'},
                    {display:'신청업체명', name:'entrpsNm',width:100, sortable:false,align:'left'},
                    {display:'신청구분', name:'reqstSeCdNm',width:55, sortable:false,align:'center'},
                    {display:'고지방법', name:'nticMthNm',width:55, sortable:false,align:'center'},
                    {display:'총면적', name:'grAr',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'총사용료', name:'grFee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'신청일자', name:'reqstDt',width:80, sortable:false,align:'center'},
                    {display:'최초신청일자', name:'frstReqstDt',width:80, sortable:false,align:'center'},
                    {display:'최초승낙일자', name:'frstPrmisnDt',width:80, sortable:false,align:'center'},
                    {display:'승낙일자', name:'prmisnDt',width:80, sortable:false,align:'center'},
                    {display:'총사용시작일', name:'grUsagePdFrom',width:80, sortable:false,align:'center'},
                    {display:'총사용종료일', name:'grUsagePdTo',width:80, sortable:false,align:'center'},
                    {display:'총감면사용료', name:'grRdcxptFee',width:100, sortable:false,align:'right', displayFormat: 'number'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            module.makeDivValues('#useListSum', data);
            return data;        	
        }
    });

    this.$("#assetUseExprInqireList").on('onItemSelected', function(event, module, row, grid, param) {
    });

    this.$("#assetUseExprInqireList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#assetUseExprInqireListTab").tabs("option", {active: 1});
    });

	this.$("#sGrUsagePdFrom").val(EMD.util.getDate());
	this.$("#sGrUsagePdTo").val(EMD.util.getDate());
};

GamAssetUseExprInqireModule.prototype.loadUseList = function() {
    this.$("#assetUseExprInqireListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamAssetUseExprInqireSearchForm');
    this.$('#assetUseExprInqireList').flexOptions({params:searchOpt}).flexReload();
    console.log('load use list');
};

GamAssetUseExprInqireModule.prototype.loadDetailForm = function() {
     	var row = this.$('#assetUseExprInqireList').selectedRows()[0];

		var detailParam = [
		               { name: 'prtAtCode', value: row.prtAtCode},
		               { name: 'mngYear', value: row.mngYear },
		               { name: 'mngNo', value: row.mngNo },
		               { name: 'mngCnt', value: row.mngCnt }
		             ];
		this.makeDivValues('#gamAssetUseExprInqireForm', row); // 결과값을 채운다.

   	 	this.doAction('<c:url value="/asset/rent/gamSelectAssetUseExprInqireDetailList.do" />', detailParam, function(module, result) {
			if (result.resultCode == "0") {
				module.makeMultiDivValues('#gamAssetUseExprInqireDetailForm',result.resultList , function(row) {
				} );	// 리스트 값을 채운다
			} else {
				alert(result.resultMsg);
			}
		});
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamAssetUseExprInqireModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'searchBtn':
			this.loadUseList();
            break;
         // 자산코드 팝업
		case "popupGisCode":
			this.doExecuteDialog("selectGisCodePopup", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;

        case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            var opts = {
                'entrpscd': this.$('#sEntrpscd').val(),
                'entrpsNm': this.$('#sEntrpsNm').val(),
            };

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;
    }
};

GamAssetUseExprInqireModule.prototype.onSubmit = function() {
    this.loadData();
};

GamAssetUseExprInqireModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamAssetUseExprInqireSearchForm');
    this.$('#assetUseExprInqireList').flexOptions({params:searchOpt}).flexReload();
};

GamAssetUseExprInqireModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	if(oldTabId=="tabs1" && this.$('#assetUseExprInqireList').selectedRowCount()==0) {
		alert('먼저 항목을 선택 하시기 바랍니다.');
		return false;
	}
	return true;
};

GamAssetUseExprInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
    	this.loadDetailForm();
    	break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamAssetUseExprInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
    
 // 자산코드 조회
	case "selectGisCodePopup":
		this.$("#searchAssetsCd").val(value["gisAssetsCd"]);
		this.$("#searchAssetsSubCd").val(value["gisAssetsSubCd"]);
		break;
     case 'selectEntrpsInfoPopup':
         if (msg != 'cancel') {
             this.$('#sEntrpscd').val(value.entrpscd);
             this.$('#sEntrpsNm').val(value.entrpsNm);
         } else {
             alert('취소 되었습니다');
         }
         break;
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');
         throw 0;
         break;
     }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetUseExprInqireModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetUseExprInqireSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>
                            <td>
                                <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM019 />
                            </td>
                            <th>관리번호</th>
                            <td>
                                <input id="sMngYear" type="text" class="mngYear">
                                <input id="sMngNo" type="text" class="mngNo">
                                <input id="sMngCnt" type="text" class="mngCnt">
                            </td>
                            <th>신청업체</th>
                            <td>
                            	<input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;
                            	<input id="sEntrpsNm" type="text" size="25" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <td rowSpan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                            <th>신청구분</th>
                            <td width="100px">
                                <input id="sReqstSeCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM011 />
                            </td>
                            <th>만기도래기간</th>
                            <td>
                            <input id="sGrUsagePdFrom" type="text" class="emdcal"
                                size="8" value="<c:out value="${grUsagePdFromStr}"/>" readonly> ~ <input id="sGrUsagePdTo" type="text"
                                class="emdcal" size="8" value="<c:out value="${grUsagePdToStr}"/>" readonly>
                            </td>
                            <th>사용용도</th>
                            <td>
                                <input id="sUsagePrposCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM007 />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetUseExprInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">자산임대(만기도래) 목록</a></li>
                <li><a href="#tabs2" class="emdTab">자산임대 내역</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="assetUseExprInqireList" style="display:none" class="fillHeight"></table>

                <div id="useListSum" class="emdControlPanel">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="12%" height="20">자료수</th>
								<td><span data-column-id="totalCount" class="ygpaNumber"></span></td>
								<th width="12%" height="20">총면적</th>
								<td><span data-column-id="sumGrAr" class="ygpaNumber"></span></td>
								<th width="12%" height="20">총사용료</th>
								<td><span data-column-id="sumGrFee" class="ygpaNumber"></span></td>
								<th width="12%" height="20">총감면사용료</th>
								<td><span data-column-id="sumGrRdcxptFee" class="ygpaNumber"></span></td>
							</tr>
						</table>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">
                <h2>자산 임대 내역</h2>    
                	<div id="gamAssetUseExprInqireForm">
                        <table class="detailForm" style="width:100%;">
                            <tr>
								<th width="15%" height="23">항코드/담당부서</th>
								<td width="33%">
									<span data-column-id="prtAtCode"></span>&nbsp;-&nbsp; 
									<span data-column-id="prtAtCode" class="ygpaCmmnCd" data-code-id="GAM019"></span>&nbsp;／&nbsp;
									<span data-column-id="deptcdNm"></span>
								</td>
								<th width="15%" height="23">납부방법/고지방법</th>
								<td width="33%">
									<span data-column-id="payMthNm"></span>&nbsp;／&nbsp; 
									<span data-column-id="nticMthNm"></span>
								</td>
                            </tr>
                            <tr>
								<th width="15%" height="23">관리번호</th>
								<td>
									<span data-column-id="mngYear"></span>&nbsp;-&nbsp;
									<span data-column-id="mngNo"></span>&nbsp;-&nbsp;
									<span data-column-id="mngCnt"></span>
								</td>
								<th width="15%" height="23">신청업체</th>
								<td>
									<span data-column-id="entrpscd"></span>&nbsp;-&nbsp; 
									<span data-column-id="entrpsNm"></span>
								</td>
                            </tr>
                            <tr>
								<th width="15%" height="23">최초신청일자</th>
								<td><span data-column-id="frstReqstDt"></span></td>
								<th width="15%" height="23">신청일자</th>
								<td><span data-column-id="reqstDt"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">승낙여부</th>
								<td><span data-column-id="prmisnYn"></span></td>
								<th width="15%" height="23">승낙일자</th>
								<td><span data-column-id="prmisnDt"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">총사용기간</th>
								<td>
									<span data-column-id="grUsagePdFrom"></span>&nbsp;～&nbsp; 
									<span data-column-id="grUsagePdTo"></span>
								</td>
								<th width="15%" height="23">총사용면적</th>
								<td><span data-column-id="grAr" class="ygpaNumber" ></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">총사용료</th>
								<td><span data-column-id="grFee" class="ygpaNumber" ></span></td>
								<th width="15%" height="23">총감면사용료</th>
								<td><span data-column-id="grRdcxptFee" class="ygpaNumber" ></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">비고</th>
								<td><span data-column-id="rm"></span></td>
								<th width="15%" height="23">코멘트</th>
								<td><span data-column-id="cmt"></span></td>
                            </tr>
                        </table>
                    </div>
                 <h2>자산 임대 상세 내역</h2>
                 	<div id="gamAssetUseExprInqireDetailForm">
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th width="15%" height="23">항코드</th>
								<td>
									<span data-column-id="gisAssetsPrtAtCode"></span>
									(<span data-column-id="prtAtCodeNm"></span>)
								</td>
								<th width="15%" height="23">자산코드</th>
								<td>
									<span data-column-id="gisAssetsCd"></span>-
									<span data-column-id="gisAssetsSubCd"></span>
								</td>
								<th width="15%" height="23">자산명</th>
								<td><span data-column-id="gisAssetsNm"></span></td>
							</tr>
							<tr>
								<th width="15%" height="23">사용시작</th>
								<td><span data-column-id="usagePdFrom"></span></td>
								<th width="15%" height="23">사용종료</th>
								<td><span data-column-id="usagePdTo"></span></td>
								<th width="15%" height="23">사용료</th>
								<td><span data-column-id="fee" class="ygpaNumber" ></span></td>
							</tr>
							<tr>
								<th width="15%" height="23">사용면적</th>
								<td><span data-column-id="usageAr" class="ygpaNumber"></span></td>
								<th width="15%" height="23">적용요율</th>
								<td>
									<span data-column-id="applcTariff"></span>
								</td>
								<th width="15%" height="23">면제구분</th>
								<td>
									<span data-column-id="exemptSe"></span>-
									<span data-column-id="exemptSeNm"></span>
								</td>
							</tr>
						</table>
                    </div>
                 	
            </div>

        </div>
    </div>
</div>