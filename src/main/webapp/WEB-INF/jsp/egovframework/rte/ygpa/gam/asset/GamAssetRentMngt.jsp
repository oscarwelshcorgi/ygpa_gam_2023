<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetRentMngt.jsp
  * @Description : 자산임대관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.10  정윤후          최초 생성
  *
  * author 정윤후
  * since 2014.01.10
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetRentMngtModule() {}

GamAssetRentMngtModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetRentMngtModule.prototype.loadComplete = function() {

    // 테이블 설정
    this.$("#assetRentMngtList").flexigrid({
        module: this,
        url: '<c:url value="/asset/selectAssetRentList.do" />',
        dataType: 'json',
        colModel : [
					{display:'항코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
					{display:'관리 번호(조합)', name:'rentMngNo',width:60, sortable:false,align:'center'},
					{display:'관리년도', name:'mngYear',width:100, sortable:false,align:'center'},
					{display:'관리 번호', name:'mngNo',width:60, sortable:false,align:'center'},                                                
					{display:'관리 횟수', name:'mngCnt',width:60, sortable:false,align:'center'},                                              
					{display:'업체코드', name:'entrpscd',width:60, sortable:false,align:'center'},                                           
					{display:'날짜', name:'dt',width:60, sortable:false,align:'center'},                                                                     
					{display:'신청 구분 코드', name:'reqstSeCd',width:60, sortable:false,align:'center'},                                 
					{display:'총 면적', name:'grAr',width:60, sortable:false,align:'center'},                                                           
					{display:'총 사용료', name:'grFee',width:60, sortable:false,align:'center'},                                                        
					{display:'고지 방법', name:'nticMth',width:60, sortable:false,align:'center'},                                            
					{display:'최초 허가 일자', name:'frstPrmisnDt',width:60, sortable:false,align:'center'},                           
					{display:'허가 일자', name:'prmisnDt',width:60, sortable:false,align:'center'},                                          
					{display:'허가 여부', name:'prmisnYn',width:60, sortable:false,align:'center'},                                          
					{display:'총 사용 기간 FROM', name:'grUsagePdFrom',width:60, sortable:false,align:'center'},                     
					{display:'총 사용 기간 TO', name:'grUsagePdTo',width:60, sortable:false,align:'center'},                           
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
					{display:'부서코드', name:'deptcd',width:60, sortable:false,align:'center'}
                    ],
        usepager: true,
        useRp: true,
        rp: 24,
        showTableToggleBtn: false,
        height: '290',
        preProcess: function(module,data) {
        	module.$('#totalResultCnt').val(data.totalCount);
        	module.$('#totalArea').val(data.sumGrAr);
        	module.$('#totalUse').val(data.sumGrFee);
            
            return data;
        }
    });
    
    // 테이블 설정
    this.$("#assetRentDetailList").flexigrid({  
        module: this,
        url: '<c:url value="/asset/selectAssetRentDetailList.do" />',  
        dataType: 'json',
        colModel : [
					{display:'자산 사용 순번', name:'assetsUsageSeq',width:100, sortable:false,align:'center'},                          
					{display:'GIS 자산 코드', name:'gisAssetsCd',width:100, sortable:false,align:'center'},
					{display:'GIS 자산 SUB 코드', name:'gisAssetsSubCd',width:130, sortable:false,align:'center'},
					{display:'사용 면적', name:'usageAr',width:100, sortable:false,align:'center'},
					{display:'사용 기간 FROM', name:'usagePdFrom',width:100, sortable:false,align:'center'},
					{display:'사용 기간 TO', name:'usagePdTo',width:100, sortable:false,align:'center'},
					{display:'사용 목적', name:'usagePurps',width:100, sortable:false,align:'center'},
					{display:'사용 내역', name:'usageDtls',width:100, sortable:false,align:'center'},
					{display:'사용 용도 코드', name:'usagePrposCd',width:100, sortable:false,align:'center'},
					{display:'면제 구분', name:'exemptSe',width:100, sortable:false,align:'center'},
					{display:'면제 사유 코드', name:'exemptRsnCd',width:100, sortable:false,align:'center'},
					{display:'면제 사유', name:'exemptRsn',width:100, sortable:false,align:'center'},
					{display:'면제 기간 FROM', name:'exemptPdFrom',width:100, sortable:false,align:'center'},
					{display:'면제 기간 TO', name:'exemptPdTo',width:100, sortable:false,align:'center'},
					{display:'산출 내역', name:'computDtls',width:100, sortable:false,align:'center'},
					{display:'공시지가', name:'olnlp',width:100, sortable:false,align:'center'},
					{display:'적용 요율', name:'applcTariff',width:100, sortable:false,align:'center'},
					{display:'적용 방법', name:'applcMth',width:100, sortable:false,align:'center'},
					{display:'포장 구분', name:'packSe',width:100, sortable:false,align:'center'},
					{display:'업체 구분', name:'entrpsSe',width:100, sortable:false,align:'center'},
					{display:'사용료 계산 구분', name:'feeCalcSe',width:100, sortable:false,align:'center'},
					{display:'감면 사용료 계산 구분', name:'rdcxptFeeCalcSe',width:100, sortable:false,align:'center'},
					{display:'감면 사용료', name:'rdcxptFee',width:100, sortable:false,align:'center'},
					{display:'사용료', name:'fee',width:100, sortable:false,align:'center'},
					{display:'해지 일자', name:'trmnatDt',width:100, sortable:false,align:'center'},
					{display:'해지 사유', name:'trmnatRsn',width:100, sortable:false,align:'center'},
					{display:'GIS 코드', name:'gisCd',width:100, sortable:false,align:'center'},
					{display:'등록자', name:'regUsr',width:100, sortable:false,align:'center'},
					{display:'등록일시', name:'registDt',width:100, sortable:false,align:'center'},
					{display:'수정자', name:'updUsr',width:100, sortable:false,align:'center'},
					{display:'수정일시', name:'updtDt',width:100, sortable:false,align:'center'},
					{display:'항코드', name:'prtAtCode',width:100, sortable:false,align:'center'},
					{display:'GIS 자산 항코드', name:'gisAssetsPrtAtCode',width:100, sortable:false,align:'center'},
					{display:'관리 년도', name:'mngYear',width:100, sortable:false,align:'center'},
					{display:'관리 번호', name:'mngNo',width:100, sortable:false,align:'center'},
					{display:'관리 횟수', name:'mngCnt',width:100, sortable:false,align:'center'}
                    ],
        usepager: true,
        useRp: true,
        rp: 24,
        showTableToggleBtn: false,
        height: '140'
    });

    this.$("#assetRentMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#assetRentListTab").tabs("option", {active: 1});    // 탭을 전환 한다.

        if(row!=null) {
            module.$('#cmd').val('modify');  // 더블클릭한 아이템을 수정한다
            module.$('#prtAtCode').val(row['prtAtCode']);
            module.$('#mngYear').val(row['mngYear']);
            module.$('#mngNo').val(row['mngNo']);
            module.$('#mngCnt').val(row['mngCnt']);
            //throw 0;
            
            // 해당하는 자산임대상세 목록을 불러온다.
            module.$('#detailPrtAtCode').val(row['prtAtCode']);
            module.$('#detailMngYear').val(row['mngYear']);
            module.$('#detailMngNo').val(row['mngNo']);
            module.$('#detailMngCnt').val(row['mngCnt']);
            
            var searchOpt=module.makeFormArgs('#gamAssetRentMngtDetailForm');
            module.$('#assetRentDetailList').flexOptions({params:searchOpt}).flexReload();
            
        }
    });
    
    this.$("#assetRentDetailList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#assetRentListTab").tabs("option", {active: 2});    // 탭을 전환 한다.

        if(row!=null) {
            module.$('#cmd').val('modify');  // 더블클릭한 아이템을 수정한다
            module.$('#assetsUsageSeq').val(row['assetsUsageSeq']);
            //module.$('#mngYear').val(row['mngYear']);
            //module.$('#mngNo').val(row['mngNo']);
            //module.$('#mngCnt').val(row['mngCnt']);
            //throw 0;
        }
    });
    

};

/**
 * 정의 된 버튼 클릭 시
 */
 GamAssetRentMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
            var searchOpt=this.makeFormArgs('#gamAssetRentMngtForm');
            this.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
            //this.$('#assetRentMngtSum').flexOptions({params:searchOpt}).flexReload();
            
            /*
            var inputVO=this.makeFormArgs('#gamAssetRentVO');
            this.doAction('<c:url value="/asset/selectAssetRentSum.do" />', inputVO, function(result) {
                
                alert("result.textCount => " + result.textCount);
                
                if(result.resultCode=='0') {
                    alert('조회되었습니다. 등록');
                    //alert(result.resultMsg);
                }
                else {
                    //alert(result.resultMsg);
                }
            });
            */
            
            //this.showAlert("<c:out value="${totalCount}"/>");
            break;

        // 최초신청
        case 'addAssetRentFirst':
        	
        	this.$("#assetRentListTab").tabs("option", {active: 1});  // 탭을 전환 한다.
            
            this.$(":input").val('');
            this.$("#cmd").val('insert');
            
            break;
            
        // 연장신청
        case 'addAssetRentRenew':
            //this.$("#assetRentListTab").tabs("option", {active: 1});  // 탭을 전환 한다.
            
            //선택된 그리드 로우의 필드값
            var rows = this.$('#assetRentMngtList').selectedRows();
            var row = this.$('#assetRentMngtList').selectedRows();
            
            if(rows.length>=1) {
            	alert("연장신청 시작!!");
            	
            	
            	alert(row[0]['prtAtCode']);
            	alert(row[0]['mngYear']);
            	alert(row[0]['mngNo']);
            	alert(row[0]['mngCnt']);
            	
            	this.$('#rPrtAtCode').val(row[0]['prtAtCode']);
                this.$('#rMngYear').val(row[0]['mngYear']);
                this.$('#rMngNo').val(row[0]['mngNo']);
                this.$('#rMngCnt').val(row[0]['mngCnt']);
            	
            	/*
            	this.$('#rPrtAtCode').val(row[0]['prtAtCode']);
            	this.$('#rMngYear').val(row[0]['mngYear']);
            	this.$('#rMngNo').val(row[0]['mngNo']);
            	this.$('#rMngCnt').val(row[0]['mngCnt']);
            	*/
            	
            	this.doAction('<c:url value="/asset/gamInsertAssetRentRenew.do" />', rows[0], function(result) {
                    if(result.resultCode=='0') {
                    	
                        var searchOpt=this.makeFormArgs('#gamAssetRentMngtRenewForm');
                        this.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
                    }
                    else {
                    	alert(result.resultMsg);
                    }
                });
                //throw 0;

            } else {
            	alert("연장신청할 업체를 선택하십시오.");
            }
            
            break;

        case 'canclProgram':
            this.$(":input").val('');
            this.$("#cmd").val('insert');
            break;
        case 'removeItem':
            throw 0;
            var rows = this.$('#assetRentMngtList').selectedRows();
            if(rows.length>=1) {
                this.doAction('<c:url value="/cmmn/gamProgramListManageDelete.do" />', rows[0], function(result) {
                    if(result.resultCode=='0') {
                        var searchOpt=this.makeFormArgs('#progListMngForm');
                        this.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
                        alert('삭제되었습니다.');
                    }
                    else {
                        alert(result.resultMsg);
                    }
                });
            }
            break;
        // 저장
        case 'btnSaveItemFirst':
        	var inputVO=this.makeFormArgs('#gamAssetRentVO');
            if(this.$("#cmd").val()=='insert') {
            	
            	alert("11");
            	
            	this.doAction('<c:url value="/asset/gamInsertAssetRentFirst.do" />', inputVO, function(result) {
                    if(result.resultCode=='0') {
                    	alert('저장되었습니다. 등록');
                    }
                    else {
                        alert(result.resultMsg);
                    }
                });
            }
            else {
                this.doAction('<c:url value="/cmmn/gamProgramListDetailSelectUpdt.do" />', inputVO, function(result) {
                    if(result.resultCode=='0') {
                        alert('저장되었습니다. 수정');
                    }
                    else {
                        alert(result.resultMsg);
                    }
                });
            }
            break;
    }
};

GamAssetRentMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
    this.loadData();
};

GamAssetRentMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#progListMngForm');
    //this.showAlert(searchOpt);
    this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
    
//  this.$('#assetList').flexOptions(searchOpt).flexReload();
};

GamAssetRentMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        var row = this.$('#assetRentMngtList').selectedRows();
        if(row.length==0) {
            this.$('#cmd').val('insert');
        }
        else {
            this.$('#cmd').val('modify');
            /* this.$('#progrmFileNm').val(row[0]['progrmFileNm']);
            this.$('#progrmStrePath').val(row[0]['progrmStrePath']);
            this.$('#progrmKoreanNm').val(row[0]['progrmKoreanNm']);
            this.$('#url').val(row[0]['url']);
            this.$('#progrmDc').val(row[0]['progrmDc']); */
        }
        break;
    case 'tabs3':
        break;
    }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetRentMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
    
    <!-- 
    <div id="searchViewStack" class="emdPanel">
        <div class="viewStack">
            <form id="progListMngForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>프로그램 명</th>
                            <td><input id="searchKeyword" name="searchKeyword" type="text" size="60"></td>
                        </tr>
                    </tbody>
                </table>
                <div class="emdTabPage">
                    <div class="emdControlPanel">
                        <button id="searchBtn">조회</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    -->
    
    <div id="searchViewStack" class="viewStack">
        <div class="viewPanel">
            <form id="gamAssetRentMngtForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>   
                            <td>
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">선택</option>
                                    <option value="P01">P01</option>
                                    <option value="P02">P02</option>
                                    <c:forEach  items="${erpAssetClsList}" var="clsItem">
                                        <option value="${clsItem.smCls }">${clsItem.smClsName }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>신청구분</th>
                            <td>
                                <select id="sReqstSeCd">
                                    <option value="" selected="selected">선택</option>
                                    <option value="A">최초</option>
                                    <option value="L">연장</option>
                                </select>
                            </td>
                            <th>신청업체</th>
                            <td>
                                <input id="sEntrpscd" type="text" size="6">
                            </td>
                            <th>사용용도</th>
                            <td>
                                <select id="assetCls">
                                    <option value="" selected="selected">선택</option>
                                    <option value="11">사용용도1</option>
                                    <option value="22">사용용도2</option>
                                </select>
                            </td>
                            <td rowSpan="2"><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                        <tr>
                            <th>관리번호</th>
                            <td>
                                <input id="sMngNo" type="text" size="4"> <input id="itemName" type="text" size="3"> <input id="itemName" type="text" size="2">
                            </td>
                            <th>승낙구분</th>
                            <td width="200px">
                                <select id="sPrmisnYn">
                                    <option value="" selected="selected">선택</option>
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
                                <input id="sGrAr" type="text" size="5">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel">
        <div id="assetRentListTab" class="emdTabPanel" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">자산임대 조회내역</a></li>
                <li><a href="#tabs2" class="emdTab">자산임대 상세조회 목록내역</a></li>
                <li><a href="#tabs3" class="emdTab">자산임대 상세조회내역</a></li>
            </ul>
            
            <!-- 
            <div id="tabs1" class="emdTabPage"> 
                <div style="width: 100%; height: 100%; overflow:auto">
                    <table id="assetRentMngtList" style="display:none"></table>
                </div>
                <div class="emdControlPanel">
                    <button id="addItem">추가</button>
                    <button id="removeItem">삭제</button>
                </div>
            </div>
             -->
            
            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <div style="width: 100%; height: 100%; overflow:auto">
                        <table id="assetRentMngtList" style="display:none"></table>
                </div>
                <div class="emdControlPanel">
                    <table style="width:100%;" >
                        <tr>
                            <td>
						       <form id="form1">             
						                    합계
						                    자료수 <input id="totalResultCnt" size="7" readonly>
						                    총면적 <input id="totalArea" type="text" size="7" readonly>
						                    총사용료 <input id="totalUse" type="text" size="7" readonly>원
						       </form>
                            </td>
                            <td>
                                <button id="addAssetRentRenew">연장신청</button>
                                <button id="addAssetRentFirst">최초신청</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            
            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">
                
                <div class="emdControlPanel"><button id="btnSaveItemFirst">저장</button><button id="canclProgram">취소</button></div>
	                <form id="gamAssetRentVO">
	                    <input type="hidden" id="cmd"/>
	                    <table>
	                        <tr>
	                            <th><span class="label">항코드</span></th>
	                            <td><input type="text" size="80" id="prtAtCode"/></td>
	                        </tr>
	                        <tr>
	                            <th><span class="label">관리 년도</span></th>
	                            <td><input type="text" size="80" id="mngYear"/></td>
	                        </tr>
	                        <tr>
	                            <th><span class="label">관리 번호</span></th>
	                            <td><input type="text" size="80" id="mngNo"/></td>
	                        </tr>
	                        <tr>
	                            <th><span class="label">관리 횟수</span></th>
	                            <td><input type="text" size="80" id="mngCnt"/></td>
	                        </tr>
	                        <!-- 
	                        <tr>
	                            <th><span class="label">프로그램설명</span></th>
	                            <td><textarea cols="80" rows="10" id="progrmDc"></textarea></td>
	                        </tr>
	                         -->
	                    </table>
	                </form>
	                
	                <form id="gamAssetRentMngtDetailForm">
                        <input type="hidden" id="detailMngYear"/>
                        <input type="hidden" id="detailMngNo"/>
                        <input type="hidden" id="detailMngCnt"/>
                        <input type="hidden" id="detailPrtAtCode"/>
                    </form>    
                    
                    <form id="gamAssetRentMngtRenewForm">
                        <input type="text" id="rPrtAtCode"/>연장
                        <input type="text" id="rMngYear"/>
                        <input type="text" id="rMngNo"/>
                        <input type="text" id="rMngCnt"/>
                    </form> 
                <!-- 
                <div style="vertical-align: bottom; text-align: right;">
                    <input type="reset" value="취소" class="input_1"> <input
                        type="submit" value="저장" class="input_1">
                </div>
                 -->
                 
                 <table>
                    <tr>
                        <td height="30">
                            &nbsp;
                        </td>
                    </tr>
                 </table>
                 
                 <table id="assetRentDetailList" style="display:none"></table>
            </div>
            
            <div id="tabs3" class="emdTabPage" style="overflow: scroll;">
                
                <div class="emdControlPanel"><button id="btnSaveItemFirst">저장</button><button id="canclProgram">취소</button></div>
                    <form id="gamAssetRentVO">
                        <input type="hidden" id="cmd"/>
                        <table>
                            <tr>
                                <th><span class="label">자산사용순번</span></th>
                                <td><input type="text" size="80" id="assetsUsageSeq"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">상세2</span></th>
                                <td><input type="text" size="80" id="mngYear"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">관리 번호</span></th>
                                <td><input type="text" size="80" id="mngNo"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">관리 횟수</span></th>
                                <td><input type="text" size="80" id="mngCnt"/></td>
                            </tr>
                            <!-- 
                            <tr>
                                <th><span class="label">프로그램설명</span></th>
                                <td><textarea cols="80" rows="10" id="progrmDc"></textarea></td>
                            </tr>
                             -->
                        </table>
                    </form>
                    
                    <form id="gamAssetRentMngtDetailForm">
                        <input type="hidden" id="detailMngYear"/>
                        <input type="hidden" id="detailMngNo"/>
                        <input type="hidden" id="detailMngCnt"/>
                        <input type="hidden" id="detailPrtAtCode"/>
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