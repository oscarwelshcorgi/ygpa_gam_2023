<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetLndValInqire.jsp
  * @Description : 자산부지공시지가조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.02.07  heroine          최초 생성
  *
  * author heroine
  * since 2014.02.07
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetErpSttusInqireModule() {}

GamAssetErpSttusInqireModule.prototype = new EmdModule(1000, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamAssetErpSttusInqireModule.prototype.loadComplete = function() {

 // 테이블 설정 //
 this.$("#dataList").flexigrid({
     module: this,
     url: '/asset/gamAssetErpSttusInqireList.do',
     dataType: 'json',
     colModel : [
                 {display:'예산과목', name:'nm',width:150, sortable:false,align:'left'},
                 {display:'예산금액', name:'bdAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'징수금액', name:'collectAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'예산대징수차액', name:'bdMiColAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'수납금액', name:'receiptAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'예산대수납차액', name:'bdMiRecAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'당년도미수금', name:'uncollectAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 
                 {display:'tp', name:'tp',width:100, sortable:false,align:'left'},
                 {display:'PID', name:'pid',width:100, sortable:false,align:'right'},
                 {display:'ID', name:'id',width:100, sortable:false,align:'right'},
                 {display:'입력여부', name:'inputCls',width:100, sortable:false,align:'left'},
                 {display:'예산코드', name:'bdItemCd',width:100, sortable:false,align:'right'}
                 
                 ],
/*                  usepager: true,
         		useRp: true,
         		rp: 24,
 */
    showTableToggleBtn: false,
    height: '353'
/*     preProcess: function(module, data) {
    	$.each(data.resultList, function() {
    		this.gisAssetsCode = this.gisAssetsCd+"-"+this.gisAssetsSubCd;
    		this.gisAssetsLnmCode = this.gisAssetsLnm;
    		if(this.gisAssetsLnm!=null && this.gisAssetsLnmSub) this.gisAssetsLnmCode+="-"+this.gisAssetsLnmSub;
    	});

        module.$('#sumCnt').val(data.sumCnt);
        module.$('#sumArOlnlp').val(data.sumArOlnlp);
        module.$('#sumGisAssetsAcqPri').val(data.sumGisAssetsAcqPri);
    	return data;
    }
 */     
 });


	// 오늘 날짜로 시작(종료)일 설정처리
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

	var displayDate = serchYr + "-" + serchMn + "-" + serchday;

	this.$("#sSearchDtFrom").val(displayDate);
	this.$("#sSearchDtTo").val(displayDate);
	
	
	for(var i = serchYr;i>=2010;i--){
		this.$(".year").append("<option value='" + i + "'>" + i + "년</option>");
	}
};

/**
* 정의 된 버튼 클릭 시
*/
GamAssetErpSttusInqireModule.prototype.onButtonClick = function(buttonId) {

 switch(buttonId) {

     // 조회
     case 'searchBtn':

/*     	 if(this.$("#sSearchDtFrom").val() == ""){
    		 alert("기준일을 선택하세요.");
    		 return;
    	 }
 */
         var searchOpt=this.makeFormArgs('#searchForm');
         this.$('#dataList').flexOptions({params:searchOpt}).flexReload();

         break;

 }
};

GamAssetErpSttusInqireModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamAssetErpSttusInqireModule.prototype.loadData = function() {
 var searchOpt=this.makeFormArgs('#searchForm');
 //this.showAlert(searchOpt);
 this.$('#dataList').flexOptions({params:searchOpt}).flexReload();
};

GamAssetErpSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
 switch(newTabId) {
 case 'tabs1':
     break;
 case 'tabs2':
     break;
 }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetErpSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="searchForm">
                <table class="searchPanel">
                    <tbody>
                        <tr>
<!--
                            <th>항코드</th>
                            <td>
                                <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM019 />
                            </td>
                             <th>GIS자산코드</th>
                            <td><input id="sGisAssetsCd" type="text" size="5"></td>
                            <th>GIS자산명</th>
                            <td><input id="sGisAssetsNm" type="text" size="20"></td>
 -->                            
 							<th>연도</th>
							<td>
								<select id="sYear" class="year">
								</select>
							</td>
                            <th>조회기준일자</th>
                            <td><input id="sSearchDtFrom" type="text" class="emdcal" size="8">~
                            <input id="sSearchDtTo" type="text" class="emdcal" size="8">
                            <td><button id="searchBtn" class="submit buttonSearch">조회</button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
	
	<div class="emdPanel">
		<table id="dataList" style="display:none"></table>
	</div>
	
     <div class="emdPanel fillHeight">
        <div id="chartTab" class="emdTabPanel" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">수입</a></li>
                <li><a href="#tabs2" class="emdTab">징수</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
				<table style="width: 100%;">
					<tr>
						<td style="width: 50%;">
						부두임대 사용료(PID:100110000), receiptAmt
						</td>
						<td style="width: 50%;">
						항만시설사용료(PID:100120000), receiptAmt
						</td>
					</tr>
					<tr>
						<td style="text-align: center">
						부두임대 사용료
						</td>
						<td style="text-align: center">
						항만시설사용료
						</td>
					</tr>
				</table>
			</div>
            <div id="tabs2" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
				<table style="width: 100%;">
					<tr>
						<td style="width: 50%;">
						부두임대 사용료(PID:100110000), collectAmt
						</td>
						<td style="width: 50%;">
						항만시설사용료(PID:100120000), collectAmt
						</td>
					</tr>
					<tr>
						<td style="text-align: center">
						부두임대 사용료
						</td>
						<td style="text-align: center">
						항만시설사용료
						</td>
					</tr>
				</table>
			</div>
	    </div>
    </div>
</div>