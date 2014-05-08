<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetDisUseMngt.jsp
  * @Description : 자산폐기등록
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.10  heroine          최초 생성
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
function GamAssetDisUseMngtModule() {}

GamAssetDisUseMngtModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetDisUseMngtModule.prototype.loadComplete = function() {

	// 테이블 설정 //
    this.$("#assetDisUseList").flexigrid({
        module: this,
        url: '<c:url value="/asset/gamSelectAssetDisUseList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'항코드', name:'gisAssetsPrtAtCode',width:60, sortable:false,align:'center'},
                    {display:'자산코드', name:'gisAssetsCode',width:66, sortable:false,align:'center'},
                    {display:'ERP 자산코드', name:'erpAssetCode',width:90, sortable:false,align:'center'},
                    {display:'자산명', name:'gisAssetsNm',width:160, sortable:false,align:'center'},
                    {display:'소재지', name:'gisAssetsLocplc',width:180, sortable:false,align:'center'},
                    {display:'지번', name:'gisAssetsLnmCode',width:58, sortable:false,align:'center'},
                    {display:'면적', name:'gisAssetsAr',width:64, sortable:false,align:'center'},
                    {display:'폐기', name:'erpAssetsDisuseRegistYn',width:44, sortable:false,align:'center'},
                    {display:'폐기사유', name:'erpAssetsDisuseRsn',width:200, sortable:false,align:'center'},
                    {display:'규격', name:'gisAssetsStndrd',width:100, sortable:false,align:'center'},
                    {display:'관리부서', name:'gisAssetsMngDeptCdNm',width:100, sortable:false,align:'center'},
                    {display:'운영부서', name:'gisAssetsOperDeptCdNm',width:100, sortable:false,align:'center'},
                    {display:'취득가액', name:'gisAssetsAcqPri',width:110, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'사용여부', name:'gisAssetsUsageYn',width:64, sortable:false,align:'center'},
                    {display:'준공년도', name:'gisAssetsBlddate',width:64, sortable:false,align:'center'},
                    {display:'준공일자', name:'gisAssetsBldDt',width:80, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module, data) {
        	$.each(data.resultList, function() {
        		this.gisAssetsCode=this.gisAssetsCd+"-"+this.gisAssetsSubCd;
        		this.erpAssetCode=this.erpAssetsSeCd+"-"+this.erpAssetsNo+"-"+this.erpAssetsNoSeq;
        		this.gisAssetsLnmCode = this.gisAssetsLnm;
        		if(this.gisAssetsLnmSub!=null && this.gisAssetsLnmSub.length!=0) {
        			this.gisAssetsLnmCode+="-"+this.gisAssetsLnmSub;
        		}
        	});
        	return data;
        }
    });

    this.$("#assetDisUseList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#assetDisUseListTab").tabs("option", {active: 1});    // 탭을 전환 한다.

        if(row!=null) {
            module.$('#gisAssetsSubCd').val(row['gisAssetsSubCd']);
            module.$('#gisAssetsCd').val(row['gisAssetsCd']);
            module.$('#gisAssetsNm').val(row['gisAssetsNm']);
            //module.$('#gisAssetsMngDeptCd').val(row['gisAssetsMngDeptCd']);
            //module.$('#gisAssetsOperDeptCd').val(row['gisAssetsOperDeptCd']);
            module.$('#gisAssetsMngDeptCdNm').val(row['gisAssetsMngDeptCdNm']);
            module.$('#gisAssetsOperDeptCdNm').val(row['gisAssetsOperDeptCdNm']);
            module.$('#gisAssetsLocplc').val(row['gisAssetsLocplc']);
            module.$('#gisAssetsLnm').val(row['gisAssetsLnm']);
            module.$('#gisAssetsLnmSub').val(row['gisAssetsLnmSub']);
            module.$('#gisAssetsPrtAtCode').val(row['gisAssetsPrtAtCode']);
            module.$('#gisAssetsAr').val(row['gisAssetsAr']);
            module.$('#gisAssetsUsageYn').val(row['gisAssetsUsageYn']);
            module.$('#gisAssetsAcqPri').val(row['gisAssetsAcqPri']);
            module.$('#gisAssetsStndrd').val(row['gisAssetsStndrd']);
            module.$('#gisAssetsBlddate').val(row['gisAssetsBlddate']);
            module.$('#gisAssetsBldDt').val(row['gisAssetsBldDt']);
            module.$('#gisAssetsRm').val(row['gisAssetsRm']);
            module.$('#regUsr').val(row['regUsr']);
            module.$('#registdt').val(row['registdt']);
            module.$('#updUsr').val(row['updUsr']);
            module.$('#updtdt').val(row['updtdt']);
            module.$('#gisAssetsQuayGroupCd').val(row['gisAssetsQuayGroupCd']);
            module.$('#gisAssetsLocCd').val(row['gisAssetsLocCd']);
            module.$('#gisAssetsSeCd').val(row['gisAssetsSeCd']);
            module.$('#gisAssetsPrprtySeCd').val(row['gisAssetsPrprtySeCd']);
            module.$('#gisAssetsInvstmntMthd').val(row['gisAssetsInvstmntMthd']);
            module.$('#gisAssetsGisCd').val(row['gisAssetsGisCd']);
            module.$('#gisAssetsRealRentAr').val(row['gisAssetsRealRentAr']);
            module.$('#drwLstRegistYear').val(row['drwLstRegistYear']);
            module.$('#drwLstSeq').val(row['drwLstSeq']);
            module.$('#gisAssetsValAmt').val(row['gisAssetsValAmt']);
            module.$('#gisAssetsValInqireDt').val(row['gisAssetsValInqireDt']);
            module.$('#erpAssetsSeCd').val(row['erpAssetsSeCd']);
            module.$('#erpAssetsNo').val(row['erpAssetsNo']);
            module.$('#erpAssetsNoSeq').val(row['erpAssetsNoSeq']);
            module.$('#erpAssetsDisuseRegistYn').val(row['erpAssetsDisuseRegistYn']);
            module.$('#erpAssetsDisuseRsn').val(row['erpAssetsDisuseRsn']);
            //throw 0;
        }
    });

};

/**
 * 정의 된 버튼 클릭 시
 */
 GamAssetDisUseMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
            var searchOpt=this.makeFormArgs('#gamAssetDisUseSearchForm');
            this.$("#assetDisUseListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
            this.$('#assetDisUseList').flexOptions({params:searchOpt}).flexReload();

            // console.log('select disuse assets list');
            break;

        // 자산폐기
        case 'disUseAssetBtn': // 팝업을 호출한다.
            var rows = this.$('#assetDisUseList').selectedRows();

            if(rows.length>=1) {
            	if(rows[0].erpAssetsDisuseRegistYn=='Y') {
            		alert('이미 폐기된 자산입니다.');
            		return;
            	}
            	var opts = {
                        'gisAssetsCd': rows[0]['gisAssetsCd'],
                        'gisAssetsSubCd': rows[0]['gisAssetsSubCd'],
                        'gisAssetsPrtAtCode': rows[0]['gisAssetsPrtAtCode']
                };

            	this.doExecuteDialog('updateDisUseAssetPopup', '자산폐기', '<c:url value="/asset/popup/showAssetDisUse.do"/>', opts);
            } else {
            	alert("목록에서 선택하십시오.");
            };

            break;
        case 'cancelDisUseAssetBtn':	// 자산 폐기를 취소한다.
            var rows = this.$('#assetDisUseList').selectedRows();

            if(rows.length>=1) {
            	if(rows[0].erpAssetsDisuseRegistYn!='Y') {
            		alert('폐기된 자산을 선택 하십시요.');
            		return;
            	}
            	var opts = {
                        'gisAssetsCd': rows[0]['gisAssetsCd'],
                        'gisAssetsSubCd': rows[0]['gisAssetsSubCd'],
                        'gisAssetsPrtAtCode': rows[0]['gisAssetsPrtAtCode'],
                        'erpAssetsDisuseRsn': '',
                        'erpAssetsDisuseRegistYn': 'N'
                };

            	if(confirm('폐기 처리를 취소 하시겠습니까?')) {
                  	this.doAction('<c:url value="/asset/gamUpdateAssetDisUse.do" />', opts, function(module, result) {
                        alert(result.resultMsg);
                        var searchOpt=module.makeFormArgs('#gamAssetDisUseSearchForm');
                        module.$('#assetDisUseList').flexOptions({params:searchOpt}).flexReload();
                    });
            	}
            } else {
            	alert("목록에서 선택하십시오.");
            };
            break;

    }
};

GamAssetDisUseMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamAssetDisUseMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamAssetDisUseSearchForm');
    //this.showAlert(searchOpt);
    this.$('#assetDisUseList').flexOptions({params:searchOpt}).flexReload();
};

GamAssetDisUseMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamAssetDisUseMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     case 'updateDisUseAssetPopup':
         if (msg != 'cancel') {
        	 console.log('disuse accept');
         	this.doAction('<c:url value="/asset/gamUpdateAssetDisUse.do" />', [{name: 'erpAssetsDisuseRsn', value:value.result.erpAssetsDisuseRsn}
         			, {name: 'erpAssetsDisuseRegistYn', value:'Y'}
         			, {name: 'gisAssetsPrtAtCode', value:value.result.gisAssetsPrtAtCode}
         			, {name: 'gisAssetsCd', value:value.result.gisAssetsCd}
         			, {name: 'gisAssetsSubCd', value:value.result.gisAssetsSubCd}
         			], function(module, result) {
                alert(result.resultMsg);
                var searchOpt=module.makeFormArgs('#gamAssetDisUseSearchForm');
                module.$('#assetDisUseList').flexOptions({params:searchOpt}).flexReload();
            });
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
var module_instance = new GamAssetDisUseMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetDisUseSearchForm">
						<table class="searchPanel">
							<tbody>
							<tr>
								<th>항구분</th>
								<td><input id="searchGisAssetsPrtAtCode" type="text" class="ygpaCmmnCd" data-column-id="gisAssetsPrtAtCode" data-code-id="GAM019" data-default-prompt="전체항" data-display-value="N" size="3"/></td>
								<th>ERP 자산코드</th>
								<td><input id="searchGisErpAssetCls" data-column-id="erpAssetCls" type="text" size="1">-<input id="searchGisErpAssetNo" data-column-id="erpAssetNo" type="text" size="4">-<input id="searchGisErpAssetNoSeq" data-column-id="erpAssetNoSeq" type="text" size="2"></td>
								<th>폐기여부</th>
								<td><input id="searchErpAssetsDisuseRegistYn" data-column-id="erpAssetsDisuseRegistYn" type="text" class="ygpaYnSelect" data-default-prompt='전체' /></td>
								<th>자산코드</th>
								<td><input id="searchGisAssetsCd" data-column-id="gisAssetsCd" type="text" size="3">-<input id="searchGisAssetsSubCd" data-column-id="gisAssetsSubCd" type="text" size="2"></td>
								<td rowSpan="3"><button id="searchBtn" class="submit">조회</button></td>
							</tr>
							<tr>
								<th>재산</th>
								<td><input id="searchGisAssetsPrprtySeCd" type="text" class="ygpaCmmnCd" data-column-id="gisAssetsPrprtySeCd" data-code-id="GAM001" data-default-prompt="전체"/></td>
								<th>위치</th>
								<td><input id="searchGisAssetsLocCd" type="text" class="ygpaCmmnCd" data-column-id="gisAssetsLocCd" data-code-id="GAM002" data-default-prompt="전체"/></td>
								<th>부두</th>
								<td><input id="searchGisAssetsQuayCd" type="text" class="ygpaCmmnCd" data-column-id="gisAssetsQuayCd" data-code-id="GAM003" data-default-prompt="전체"/></td>
								<th>관리부서</th>
								<td>
									<input id="searchMngDeptCd" data-column-id="mngDeptCd" class="ygpaDeptSelect" />
								</td>
							</tr>
							<tr>
								<th>자산명</th>
								<td><input id="searchGisAssetsNm" data-column-id="gisAssetsNm" type="text" size="16"></td>
								<th>소재지</th>
								<td><input id="searchGisAssetsLocPlc" data-column-id="gisAssetsLocplc" type="text" size="20"></td>
								<th>지번</th>
								<td><input id="searchGisAssetsLnm" data-column-id="gisAssetsLnm" type="text" size="4">-<input id="searchGisAssetsLnmSub" data-column-id="gisAssetsLnmSub" type="text" size="3"></td>
								<th>운영부서</th>
								<td>
									<input id="searchOperDeptCd" data-column-id="operDeptCd" class="ygpaDeptSelect" />
								</td>
							</tr>
						</tbody>
					</table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetDisUseListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">GIS자산 목록</a></li>
                <li><a href="#tabs2" class="emdTab">GIS자산 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="assetDisUseList" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel">
                    <table style="width:100%;" >
                        <tr>
                            <td>
                                <button id="disUseAssetBtn">자산폐기</button>
                                <button id="cancelDisUseAssetBtn">폐기취소</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">

                <!-- <div class="emdControlPanel"><button id="btnSaveItem">저장</button><button id="btnCancelItem">취소</button><button id="btnRemoveItem">삭제</button></div>  -->
                    <form id="gamAssetDisUseForm">
                        <input type="hidden" id="cmd"/>

                        <table>
                            <tr>
                                <th><span class="label">GIS 자산 코드</span></th>
                                <td><input type="text" size="20" id="gisAssetsCd" readonly/></td>
                                <th><span class="label">GIS 자산 SUB 코드</span></th>
                                <td><input type="text" size="20" id="gisAssetsSubCd" readonly/></td>
                                <th><span class="label">자산관리부서</span></th>
                                <td><input type="text" size="20" id="gisAssetsMngDeptCdNm" readonly/></td>
                            </tr>
                            <tr>
                                <!--
                                <th><span class="label">GIS 자산 관리 부서 코드</span></th>
                                <td><input type="text" size="10" id="gisAssetsMngDeptCd" readonly/></td>
                                <th><span class="label">GIS 자산 운영 부서 코드</span></th>
                                <td><input type="text" size="10" id="gisAssetsOperDeptCd" readonly/></td>
                                -->
                                <th><span class="label">자산운영부서</span></th>
                                <td><input type="text" size="20" id="gisAssetsOperDeptCdNm" readonly/></td>
                                <th><span class="label">GIS 자산 소재지</span></th>
                                <td><input type="text" size="20" id="gisAssetsLocplc" readonly/></td>
                                <th><span class="label">GIS 자산 지번</span></th>
                                <td><input type="text" size="20" id="gisAssetsLnm" readonly/></td>
                            </tr>
                            <tr>
                                <th><span class="label">GIS 자산 지번SUB</span></th>
                                <td><input type="text" size="20" id="gisAssetsLnmSub" readonly/></td>
                                <th><span class="label">GIS 자산 항코드</span></th>
                                <td><input type="text" size="20" id="gisAssetsPrtAtCode" readonly/></td>
                                <th><span class="label">GIS 자산 면적</span></th>
                                <td><input type="text" size="20" id="gisAssetsAr" readonly/></td>
                            </tr>
                            <tr>
                                <th><span class="label">GIS 자산 사용 여부</span></th>
                                <td><input type="text" size="20" id="gisAssetsUsageYn" readonly/></td>
                                <th><span class="label">GIS 자산 취득가액</span></th>
                                <td><input type="text" size="20" id="gisAssetsAcqPri" readonly/></td>
                                <th><span class="label">GIS 자산 규격</span></th>
                                <td><input type="text" size="20" id="gisAssetsStndrd" readonly/></td>
                            </tr>
                            <tr>
                                <th><span class="label">GIS 자산 준공년도</span></th>
                                <td><input type="text" size="20" id="gisAssetsBlddate" readonly/></td>
                                <th><span class="label">GIS 자산 준공 일자</span></th>
                                <td><input type="text" size="20" id="gisAssetsBldDt" readonly/></td>
                                <th><span class="label">GIS 자산 비고</span></th>
                                <td><input type="text" size="20" id="gisAssetsRm" readonly/></td>
                            </tr>
                            <tr>
                                <th><span class="label">등록자</span></th>
                                <td><input type="text" size="20" id="regUsr" readonly/></td>
                                <th><span class="label">등록일자</span></th>
                                <td><input type="text" size="20" id="registdt" readonly/></td>
                                <th><span class="label">수정자</span></th>
                                <td><input type="text" size="20" id="updUsr" readonly/></td>
                            </tr>
                            <tr>
                                <th><span class="label">수정일자</span></th>
                                <td><input type="text" size="20" id="updtdt" readonly/></td>
                                <th><span class="label">GIS 자산 부두 그룹 코드</span></th>
                                <td><input type="text" size="20" id="gisAssetsQuayGroupCd" readonly/></td>
                                <th><span class="label">GIS 자산 위치 코드</span></th>
                                <td><input type="text" size="20" id="gisAssetsLocCd" readonly/></td>
                            </tr>
                            <tr>
                                <th><span class="label">GIS 자산 구분 코드</span></th>
                                <td><input type="text" size="20" id="gisAssetsSeCd" readonly/></td>
                                <th><span class="label">GIS 자산 재산 구분 코드</span></th>
                                <td><input type="text" size="20" id="gisAssetsPrprtySeCd" readonly/></td>
                                <th><span class="label">GIS 자산 출자 방식</span></th>
                                <td><input type="text" size="20" id="gisAssetsInvstmntMthd" readonly/></td>
                            </tr>
                            <tr>
                                <th><span class="label">GIS 자산 GIS 코드</span></th>
                                <td><input type="text" size="20" id="gisAssetsGisCd" readonly/></td>
                                <th><span class="label">GIS 자산 실제 임대 면적</span></th>
                                <td><input type="text" size="20" id="gisAssetsRealRentAr" readonly/></td>
                                <th><span class="label">도면 목록 등록 년도</span></th>
                                <td><input type="text" size="20" id="drwLstRegistYear" readonly/></td>
                            </tr>
                            <tr>
                                <th><span class="label">도면 목록 순번</span></th>
                                <td><input type="text" size="20" id="drwLstSeq" readonly/></td>
                                <th><span class="label">GIS 자산 가치 금액</span></th>
                                <td><input type="text" size="20" id="gisAssetsValAmt" readonly/></td>
                                <th><span class="label">GIS 자산 가치 조회 일자</span></th>
                                <td><input type="text" size="20" id="gisAssetsValInqireDt" readonly/></td>
                            </tr>
                            <tr>
                                <th><span class="label">ERP 자산 구분 코드</span></th>
                                <td><input type="text" size="20" id="erpAssetsSeCd" readonly/></td>
                                <th><span class="label">ERP 자산 번호</span></th>
                                <td><input type="text" size="20" id="erpAssetsNo" readonly/></td>
                                <th><span class="label">ERP 자산 번호 순번</span></th>
                                <td><input type="text" size="20" id="erpAssetsNoSeq" readonly/></td>
                            </tr>
                            <tr>
                                <th><span class="label">ERP 자산 폐기 등록 여부</span></th>
                                <td><input type="text" size="20" id="erpAssetsDisuseRegistYn" readonly/></td>
                                <th><span class="label">ERP 자산 폐기 사유</span></th>
                                <td><input type="text" size="20" id="erpAssetsDisuseRsn" readonly/></td>
                                <th><span class="label">GIS 자산 항코드</span></th>
                                <td><input type="text" size="20" id="gisAssetsPrtAtCode" readonly/></td>
                            </tr>
                        </table>
                    </form>

                <!--
                <div style="vertical-align: bottom; text-align: right;">
                    <input type="reset" value="취소" class="input_1"> <input
                        type="submit" value="저장" class="input_1">
                </div>
                 -->
            </div>

        </div>
    </div>
</div>