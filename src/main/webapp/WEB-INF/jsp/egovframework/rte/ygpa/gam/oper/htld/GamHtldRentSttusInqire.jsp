<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : GamHtldRentSttusInqire.jsp
 * @Description : 배후단지임대현황조회 
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
function GamHtldRentSttusInqireModule() {}

GamHtldRentSttusInqireModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamHtldRentSttusInqireModule.prototype.loadComplete = function() {

    // 자산임대 테이블 설정
    this.$("#htldRentSttusInqireList").flexigrid({
        module: this,
        url: '<c:url value="/oper/htld/selectHtldRentSttusInqireList.do" />',
        dataType: 'json',
        colModel : [
					{display:'항코드', name:'prtAtCode',width:40, sortable:false,align:'center'},
                    {display:'항코드명', name:'prtAtCodeNm',width:55, sortable:false,align:'center'},
                    {display:'관리번호', name:'rentMngNo',width:80, sortable:false,align:'center'},
                    {display:'신청업체', name:'entrpscd',width:60, sortable:false,align:'center'},
                    {display:'신청업체명', name:'entrpsNm',width:100, sortable:false,align:'left'},
                    {display:'신청구분', name:'reqstSeCdNm',width:55, sortable:false,align:'center'},
                    {display:'고지방법', name:'nticMthNm',width:55, sortable:false,align:'center'},
                    {display:'신청일자', name:'reqstDt',width:80, sortable:false,align:'center'},
                    {display:'승낙여부', name:'prmisnYn',width:55, sortable:false,align:'center'},
                    {display:'승낙일자', name:'prmisnDt',width:80, sortable:false,align:'center'},
                    {display:'결재상태', name:'sanctnSttusNm',width:55, sortable:false,align:'center'},
                    {display:'총사용시작일', name:'grUsagePdFrom',width:80, sortable:false,align:'center'},
                    {display:'총사용종료일', name:'grUsagePdTo',width:80, sortable:false,align:'center'},
                    {display:'총면적', name:'grAr',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'총사용료', name:'grFee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'총감면사용료', name:'grRdcxptFee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'납부방법', name:'payMthNm',width:55, sortable:false,align:'center'},
                    {display:'분납이자율', name:'payinstIntrrate',width:60, sortable:false,align:'center'},
                    {display:'최초신청일자', name:'frstReqstDt',width:80, sortable:false,align:'center'},
                    {display:'최초승낙일자', name:'frstPrmisnDt',width:80, sortable:false,align:'center'},
                    {display:'결재일시', name:'sanctnDt',width:110, sortable:false,align:'center'},
                    {display:'문서번호', name:'docNo',width:80, sortable:false,align:'left'},
                    {display:'비고', name:'rm',width:200, sortable:false,align:'left'}

                    /*
                    {display:'결재상태', name:'sanctnSttus',width:60, sortable:false,align:'center'},
                    {display:'항코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
                    {display:'관리년도', name:'mngYear',width:100, sortable:false,align:'center'},
                    {display:'관리 번호', name:'mngNo',width:60, sortable:false,align:'center'},
                    {display:'관리 횟수', name:'mngCnt',width:60, sortable:false,align:'center'},
                    {display:'신청 구분 코드', name:'reqstSeCd',width:60, sortable:false,align:'center'},
                    {display:'고지 방법', name:'nticMth',width:60, sortable:false,align:'center'},
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
                    {display:'부서코드', name:'deptcd',width:60, sortable:false,align:'center'},
                    {display:'납부방법', name:'payMth',width:60, sortable:false,align:'center'}
                    */
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            module.$('#totalCount').val(data.totalCount);
            module.$('#sumGrAr').val(data.sumGrAr);
            module.$('#sumGrFee').val(data.sumGrFee);
            module.$('#sumGrRdcxptFee').val(data.sumGrRdcxptFee);
            module.makeDivValues('#rentListSum', data);
            return data;
        }
    });

    // 첨부파일 테이블 설정
    this.$("#htldRentSttusInqireFileList").flexigrid({
        module: this,
        url: '<c:url value="/oper/htld/gamSelectHtldRentSttusInqireFileList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'순번', name:'photoSeq', width:80, sortable:true, align:'center'},
                    {display:'사진제목', name:'photoSj', width:200, sortable:true, align:'left'},
                    {display:'사진파일명', name:'filenmLogic', width:200, sortable:true, align:'left'},
                    {display:'사진설명', name:'photoDesc', width:250, sortable:true, align:'left'},
                    {display:'촬영일자', name:'shotDt', width:100, sortable:true, align:'center'}

                    /*
                    {display:'파일명(물리)', name:'filenmPhysicl', width:200, sortable:true, align:'left'},
                    {display:'항코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
                    {display:'관리년도', name:'mngYear',width:60, sortable:false,align:'center'},
                    {display:'관리번호', name:'mngNo',width:60, sortable:false,align:'center'},
                    {display:'관리횟수', name:'mngCnt',width:60, sortable:false,align:'center'},
                    {display:'등록자', name:'regUsr', width:160, sortable:true, align:'center'},
                    {display:'등록일', name:'registDt', width:160, sortable:true, align:'center'}
                    */
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    this.$("#htldRentSttusInqireList").on('onItemSelected', function(event, module, row, grid, param) {
    });

    this.$("#htldRentSttusInqireList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#htldRentSttusInqireListTab").tabs("option", {active: 1});
    });

    this.$("#htldRentSttusInqireFileList").on('onItemSelected', function(event, module, row, grid, param) {
        module.makeFormValues('#gamHtldRentSttusInqireFileForm', row);

        if(row.filenmPhysicl!=null || row.filenmPhysicl!='') {
            // 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
            var filenm=row['filenmPhysicl'];
            var ext=filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();
            if(ext=='jpg' || ext=='jpeg' || ext=='bmp' || ext=='png' || ext=='gif') {
                $imgURL = module.getImageUrl(filenm);
                module.$("#previewImage").fadeIn(400, function() {
                    module.$("#previewImage").attr('src', $imgURL);
                });
            }
            else {
                module.$("#previewImage").attr(src, '#');
            }
        }
    });

	this.$("#sGrUsagePdFrom").val(EMD.util.getDate(EMD.util.addMonths(-1)));
	this.$("#sGrUsagePdTo").val(EMD.util.getDate());

};


GamHtldRentSttusInqireModule.prototype.loadRentList = function() {
    this.$("#htldRentSttusInqireListTab").tabs("option", {active: 0});

    var searchOpt=this.makeFormArgs('#gamHtldRentSttusInqireSearchForm');
    this.$('#htldRentSttusInqireList').flexOptions({params:searchOpt}).flexReload();
    // console.log('load rent list');
};

GamHtldRentSttusInqireModule.prototype.loadDetailForm = function() {
     	var row = this.$('#htldRentSttusInqireList').selectedRows()[0];

		var detailParam = [
		               { name: 'prtAtCode', value: row.prtAtCode},
		               { name: 'mngYear', value: row.mngYear },
		               { name: 'mngNo', value: row.mngNo },
		               { name: 'mngCnt', value: row.mngCnt }
		             ];
		this.makeDivValues('#gamHtldRentSttusInqireForm', row); // 결과값을 채운다.

   	 	this.doAction('<c:url value="/oper/htld/selectHtldRentSttusInqireDetailList.do" />', detailParam, function(module, result) {
			if (result.resultCode == "0") {
				module.makeMultiDivValues('#gamHtldRentSttusInqireDetailForm',result.resultList , function(row) {
				} );	// 리스트 값을 채운다
			} else {
				alert(result.resultMsg);
			}
		});
};

GamHtldRentSttusInqireModule.prototype.loadFileList = function() {
	var row = this.$('#htldRentSttusInqireList').selectedRows()[0];

    this.$('#htldRentSttusInqireFileList').flexOptions({params:row}).flexReload();
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamHtldRentSttusInqireModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'searchBtn':		 // 조회
			this.loadRentList();
            break;

        case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            var opts = {
                'entrpscd': this.$('#sEntrpscd').val(),
                'entrpsNm': this.$('#sEntrpsNm').val(),
            };

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;

        case 'btnDownloadFile':
    		var selectRow = this.$('#htldRentSttusInqireFileList').selectedRows();
    		if(selectRow.length > 0) {
    			var row=selectRow[0];
    			this.downloadFile(row["filenmPhysicl"], row["filenmLogic"]);
    		}
    		break;
    }
};

GamHtldRentSttusInqireModule.prototype.onSubmit = function() {
    this.loadData();
};

GamHtldRentSttusInqireModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamHtldRentSttusInqireSearchForm');
    //this.showAlert(searchOpt);
    this.$('#htldRentSttusInqireList').flexOptions({params:searchOpt}).flexReload();
};

GamHtldRentSttusInqireModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	if(oldTabId=="tabs1" && this.$('#htldRentSttusInqireList').selectedRowCount()==0) {
		alert('먼저 항목을 선택 하시기 바랍니다.');
		return false;
	}
	return true;
};

GamHtldRentSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
    	this.loadDetailForm();
        break;
    case 'tabs4':
    	this.loadFileList();
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamHtldRentSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
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
         
         break;
     }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamHtldRentSttusInqireModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamHtldRentSttusInqireSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>
                            <td>
                                <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" />
                            </td>
                            <th>신청구분</th>
                            <td width="100px">
                                <input id="sReqstSeCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM011" />
                            </td>
                            <th>신청업체</th>
                            <td>
                            	<input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;
                            	<input id="sEntrpsNm" type="text" size="15" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <th>사용용도</th>
                            <td>
                                <input id="sUsagePrposCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM007" />
                            </td>
                            <td rowSpan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                            <th>관리번호</th>
                            <td>
                                <input id="sMngYear" type="text" size="2">
                                <input id="sMngNo" type="text" size="1">
                                <input id="sMngCnt" type="text" size="1">
                            </td>
                            <th>승낙구분</th>
                            <td>
                                <select id="sPrmisnYn">
                                    <option value="" selected="selected">전체</option>
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
                                <input id="sGrAr" type="text" size="8">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="htldRentSttusInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
            <ul>
                <li><a href="#tabs1" class="emdTab">배후단지임대현황 목록</a></li>
                <li><a href="#tabs2" class="emdTab">배후단지임대현황 내역</a></li>
                <li><a href="#tabs4" class="emdTab">첨부파일</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;">
                <table id="htldRentSttusInqireList" style="display:none" class="fillHeight"></table>

                <div id="rentListSum" class="emdControlPanel">
					<form id="form1">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="12%" height="20">자료수</th>
								<td><input type="text" size="6" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">총면적</th>
								<td><input type="text" size="18" id="sumGrAr" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">총사용료</th>
								<td><input type="text" size="18" id="sumGrFee" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">총감면사용료</th>
								<td><input type="text" size="18" id="sumGrRdcxptFee" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:hidden;">
            	<!-- <h2>배후단지임대 내역</h2> -->
					<table class="searchPanel">
					<tbody>
						<tr>
							<th>배후단지임대 내역</th>
						</tr>
					</tbody>
					</table>
                    <div id="gamHtldRentSttusInqireForm">
                        <table class="detailForm">
                            <tr>
								<th width="10%" height="18">항코드</th>
                                <td>
                                    <span data-column-id="prtAtCode" class="ygpaCmmnCd" data-code-id="GAM019"></span>
                                    (<span data-column-id="prtAtCode"></span>)
                                </td>
								<th width="10%" height="18">담당부서</th>
                                <td>
                                    <span data-column-id="deptcd" class="ygpaDeptSelect"></span>
                                </td>
								<th width="10%" height="18">관리번호</th>
                                <td>
                                    <span data-column-id="mngYear"></span>-
                                    <span data-column-id="mngNo" ></span>-
                                    <span data-column-id="mngCnt"></span>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">최초신청일자</th>
                                <td><span data-column-id="frstReqstDt"></span></td>
								<th width="10%" height="18">신청일자</th>
                                <td><span data-column-id="reqstDt"></span></td>
								<th width="10%" height="18">신청업체</th>
                                <td>
                                    <span data-column-id="entrpscd" ></span>
                                    <span data-column-id="entrpsNm"></span>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">승낙여부</th>
                                <td>
                                    <span data-column-id="prmisnYn"></span>
                                </td>
								<th width="10%" height="18">승낙일자</th>
                                <td><span data-column-id="prmisnDt"></span></td>
								<th width="10%" height="18">총사용기간</th>
                                <td>
                                    <span data-column-id="grUsagePdFrom"></span>~
                                    <span data-column-id="grUsagePdTo"></span>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">총사용면적</th>
                                <td><span data-column-id="grAr" class="ygpaNumber"></span></td>
								<th width="10%" height="18">총사용료</th>
                                <td><span data-column-id="grFee"  class="ygpaNumber"></span></td>
								<th width="10%" height="18">총감면사용료</th>
                                <td><span data-column-id="grRdcxptFee" class="ygpaNumber" ></span></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">납부방법</th>
                                <td>
                                    <span data-column-id="payMth"></span>
                                </td>
								<th width="10%" height="18">고지방법</th>
                                <td>
                                    <span data-column-id="nticMth"></span>
                                </td>
								<th width="10%" height="18">분납이자율</th>
                                <td>
                                    <span data-column-id="payinstIntrrate"></span>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">코멘트</th>
                                <td colspan="5">
                                	<span data-column-id="cmt"></span>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">비고</th>
                                <td colspan="5"><span data-column-id="rm"></span></td>
                            </tr>
                        </table>
                    </div>
            	<!-- <h2>배후단지임대 상세내역</h2> -->
					<table class="searchPanel">
					<tbody>
						<tr>
							<th>배후단지임대 상세내역</th>
						</tr>
					</tbody>
					</table>
                    <div id="gamHtldRentSttusInqireDetailForm">
                        <table class="detailPanel" style="width:100%;">
                        	<tr>
								<th>자산코드</th>
                                <td>
                                	<span data-column-id="gisAssetsPrtAtCode"></span>-
                                	<span data-column-id="gisAssetsCd"></span>-
                                	<span data-column-id="gisAssetsSubCd"></span>
                                </td>
								<th>자산명</th>
                                <td colspan="3"><span data-column-id="gisAssetsNm"></span></td>
								<th>자산면적</th>
                                <td><span data-column-id="gisAssetsAr" class="ygpaNumber"></span></td>
                            </tr>
                            <tr>
								<th>소재지</th>
                                <td colspan="5"><span data-column-id="gisAssetsLocplc"></span></td>
								<th>지번</th>
                                <td>
                                	<span data-column-id="gisAssetsLnm"></span>-
                                	<span data-column-id="gisAssetsLnmSub"></span>
                                </td>
                            </tr>
                            <tr>
								<th>공시지가</th>
                                <td><span data-column-id="olnlp" class="ygpaNumber"></span></td>
								<th>사용면적</th>
                                <td><span data-column-id="usageAr" class="ygpaNumber"></span></td>
								<th>사용기간</th>
                                <td colspan="3">
                                	<span data-column-id="usagePdFrom"></span>~
                                	<span data-column-id="usagePdTo"></span>
                                </td>
                            </tr>
                            <tr>
								<th>면제구분</th>
                                <td>
                                    <span data-column-id="exemptSe"></span>
                                    <span data-column-id="exemptSeNm"></span>
                                </td>
								<th>면제기간</th>
                                <td>
                                	<span data-column-id="exemptPdFrom"></span>~
                                	<span data-column-id="exemptPdTo"></span>
                                </td>
                            	<th>면제사유</th>
                                <td colspan="3"><span data-column-id="exemptRsn"></span></td>
                            </tr>
                            <tr>
								<th>적용요율</th>
                                <td>
                                    <span data-column-id="applcTariff"></span>
                                </td>
								<th>적용방법</th>
                                <td>
                                    <span data-column-id="applcMthNm"></span>
                                </td>
								<th>사용료</th>
                                <td><span data-column-id="fee"class="ygpaNumber" ></span></td>
								<th>감면사용료</th>
                                <td><span data-column-id="rdcxptFee" class="ygpaNumber" ></span></td>
                            </tr>
                            <tr>
								<th>산출내역</th>
                                <td colspan="7"><span data-column-id="computDtls"></span></td>
                            </tr>
                            <tr>
								<th>사용목적</th>
                                <td colspan="7"><span data-column-id="usagePurps"></span></td>
                            </tr>
                            <tr>
								<th>사용내역</th>
                                <td colspan="7"><span data-column-id="usageDtls"></span></td>
                            </tr>
                        </table>
                    </div>

            </div>

            <div id="tabs4" class="emdTabPage" style="overflow: scroll;">

                <table id="htldRentSttusInqireFileList" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel"><button id="btnDownloadFile">다운로드</button><!-- <button id="btnUploadFile">업로드</button><button id="btnRemoveFile">삭제</button> --></div>
                <form id="gamHtldRentSttusInqireFileForm">
                    <input type="hidden" id="photoPrtAtCode" data-column-id="prtAtCode"/>
                    <input type="hidden" id="photoMngYear" data-column-id="mngYear"/>
                    <input type="hidden" id="photoMngNo" data-column-id="mngNo"/>
                    <input type="hidden" id="photoMngCnt" data-column-id="mngCnt"/>
                    <input type="hidden" id="photoSeq" data-column-id="photoSeq"/>
					<!--
                    <table class="searchPanel">
                        <tr>
							<th width="10%" height="18">사진제목</th>
                            <td>
                                <input id="photoSj" type="text" size="83" disabled/>
                            </td>
							<th width="10%" height="18">촬영일자</th>
                            <td>
                                <input id="shotDt" type="text" size="20" disabled>
                            </td>
                        </tr>
                        <tr>
							<th width="10%" height="18">사진파일명</th>
                            <td colspan="3">
                                <input id="filenmLogic" type="text" size="135" disabled/>
                            </td>
                        </tr>
                        <tr>
							<th width="10%" height="18">사진설명</th>
                            <td colspan="3">
                                <input id="photoDesc" type="text" size="135" disabled/>
                            </td>
                        </tr>
                    </table>
                    -->
                </form>
                    <!-- <button id="btnApplyPhotoData">첨부파일 적용</button>  -->
                <div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>

            </div>
        </div>
    </div>
</div>