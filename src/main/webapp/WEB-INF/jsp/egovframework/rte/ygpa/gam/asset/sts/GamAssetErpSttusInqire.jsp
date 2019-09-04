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

GamAssetErpSttusInqireModule.prototype = new EmdModule(1000, 800);

//페이지가 호출 되었을때 호출 되는 함수
GamAssetErpSttusInqireModule.prototype.loadComplete = function() {

 // 테이블 설정 //
 this.$("#dataList").flexigrid({
     module: this,
     url: '/asset/gamAssetErpSttusInqireList.do',
     dataType: 'json',
     colModel : [
                 {display:'예산과목', name:'nm',width:150, sortable:false, align:'left', },
                 {display:'예산금액', name:'bdAmt',width:140, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'징수금액', name:'collectAmt',width:140, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'예산대징수차액', name:'bdMiColAmt',width:140, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'수납금액', name:'receiptAmt',width:140, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'예산대수납차액', name:'bdMiRecAmt',width:140, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'당년도미수금', name:'uncollectAmt',width:130, sortable:false,align:'right', displayFormat: 'number'}

/*                  {display:'tp', name:'tp',width:100, sortable:false,align:'left'},
                 {display:'ID', name:'id',width:100, sortable:false,align:'right'},
                 {display:'PID', name:'pid',width:100, sortable:false,align:'right'},
                 {display:'입력여부', name:'inputCls',width:100, sortable:false,align:'left'},
                 {display:'예산코드', name:'bdItemCd',width:100, sortable:false,align:'right'}
*/

                 ],
/*                  usepager: true,
         		useRp: true,
         		rp: 24,
 */
    showTableToggleBtn: false,
    height: '353',
	preProcess: function(module, data) {
    	$.each(data.resultList, function() {
    		 switch(this.lv) {
    		 case 2:
    			this.nm = "&nbsp;&nbsp;"+this.nm;
    		     break;
    		 case 3:
    			this.nm = "&nbsp;&nbsp;&nbsp;&nbsp;"+this.nm;
    		     break;
    		 case 4:
    			this.nm = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+this.nm;
    		     break;
    		 case 5:
    			this.nm = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+this.nm;
    		     break;
    		 case 6:
    			this.nm = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+this.nm;
    		     break;
    		 }
    	});

    	return data;
  }

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

	/* 차트 추가 */
	this.chart1 =  new dhtmlXChart({
        view:"pie3D",
        container:this.$("#chart1").attr('id'),
		value: "#receiptAmt#",
		color: "#color#",
        tooltip: "#nm#"+":"+"#receiptAmt1#"+'원',
        legend:{
			width:170,
			align:"left",
			valign:"middle",
			template:"#nm#"
		},
        shadow:0
    });

	this.chart2 =  new dhtmlXChart({
        view:"pie3D",
        container:this.$("#chart2").attr('id'),
		value: "#receiptAmt#",
		color: "#color#",
        tooltip: "#nm#"+":"+"#receiptAmt1#"+"원",
        legend:{
			width:170,
			align:"left",
			valign:"middle",
			template:"#nm#"
		},
        shadow:0
    });

	this.chart3 =  new dhtmlXChart({
        view:"pie3D",
        container:this.$("#chart3").attr('id'),
		value: "#collectAmt#",
		color: "#color#",
        tooltip: "#nm#"+":"+"#collectAmt1#"+'원',
        legend:{
			width:170,
			align:"left",
			valign:"middle",
			template:"#nm#"
		},
        shadow:0
    });

	this.chart4 =  new dhtmlXChart({
        view:"pie3D",
        container:this.$("#chart4").attr('id'),
		value: "#collectAmt#",
		color: "#color#",
        tooltip: "#nm#"+":"+"#collectAmt1#"+'원',
        legend:{
			width:170,
			align:"left",
			valign:"middle",
			template:"#nm#"
		},
        shadow:0
    });

	this._collectAmt = [];
	this._receiptAmt = [];

};

/**
* 정의 된 버튼 클릭 시
*/
GamAssetErpSttusInqireModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

     // 조회
		case 'searchBtn':
			this.loadData();
			break;
 	}
};

GamAssetErpSttusInqireModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamAssetErpSttusInqireModule.prototype.loadData = function() {

	var module = this;
	var getColor1=function(code) {
		var color_codes = {
			"0":"#3366cc",
			"1":"#dc3912",
			"2":"#ff9900",
			"3":"#109618",
			"4":"#990099",
			"5":"#0099c6",
			"6":"#dd4477",
			"7":"#66aa00",
			"8":"#b82e2e",
			"9":"#316395",
			"10":"#994499",
			"11":"#22aa99",
			"12":"#aaaa11",
			"13":"#6633cc",
			"14":"#e67300",
			"15":"#8b0707",
			"16":"#651067",
			"17":"#329262",
			"18":"#5574a6",
			"19":"#3b3eac",
			"20":"#b77322"
		};
		return color_codes[code];
	};

	var searchOpt=this.makeFormArgs('#searchForm');
	this.$('#dataList').flexOptions({params:searchOpt}).flexReload({
		callback: function(data) {
       		var r=[];
       		var s=[];
       		var t=[];
       		var u=[];


       		for(var i=0; i<data.resultList.length; i++) {
       			var obj = data.resultList[i];
       			var _nm = obj.nm.toString().replace(/&nbsp;/gi,"").trim();

       			if(obj.pid == "100110000"){
					if(obj.receiptAmt > 0){
						r[r.length] = {
	       					nm: _nm,
	       					receiptAmt: obj.receiptAmt,
	       					receiptAmt1: obj.receiptAmt.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","),
	       					color: getColor1(String(r.length))
		       			}
					}
					if(obj.collectAmt > 0){
						t[t.length] = {
	       					nm: _nm,
	       					collectAmt: obj.collectAmt,
	       					collectAmt1: obj.collectAmt.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","),
	       					color: getColor1(String(t.length))
		       			}
					}

				}else if(obj.pid == "100120000"){
					if(obj.receiptAmt > 0){
		       			 s[s.length] = {
	       					nm: _nm,
	       					receiptAmt: obj.receiptAmt,
	       					receiptAmt1: obj.receiptAmt.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","),
	       					color: getColor1(String(s.length))
		       			 }
					}
					if(obj.collectAmt > 0){
		       			 u[u.length] = {
	       					nm: _nm,
	       					collectAmt: obj.collectAmt,
	       					collectAmt1: obj.collectAmt.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","),
	       					color: getColor1(String(u.length))
		       			 }
					}
       			 }
       		 }
       		module.chart3.clearAll();
       		module.chart4.clearAll();
       		module.chart3.parse(t, "json");
       		module.chart4.parse(u, "json");
       		module.$("#chartTab").tabs("option", {active: 1});	// 탭 이동
       		// receiptAmt
       		module.chart1.clearAll();
       		module.chart2.clearAll();
       		module.chart1.parse(r, "json");
       		module.chart2.parse(s, "json");
       		module.$("#chartTab").tabs("option", {active: 0});	// 탭 이동
		}
        });


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
                <li><a href="#tabs2" class="emdTab">징수</a></li>
                <li><a href="#tabs1" class="emdTab">수입</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
				<table style="width: 100%;">
					<tr>
						<td style="width: 50%;">
							<div id="chart1" style="width:480px; height:270px; border:1px solid #A4BED4;"></div>
						</td>
						<td style="width: 50%;">
							<div id="chart2" style="width:480px; height:270px; border:1px solid #A4BED4;"></div>
						</td>
					</tr>
					<tr>
						<td style="text-align: center">
						부두임대 사용료(단위 : 원)
						</td>
						<td style="text-align: center">
						항만시설사용료(단위 : 원)
						</td>
					</tr>
				</table>
			</div>
            <div id="tabs2" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
				<table style="width: 100%;">
					<tr>
						<td style="width: 50%;">
							<div id="chart3" style="width:480px; height:270px; border:1px solid #A4BED4;"></div>
						</td>
						<td style="width: 50%;">
							<div id="chart4" style="width:480px; height:270px; border:1px solid #A4BED4;"></div>
						</td>
					</tr>
					<tr>
						<td style="text-align: center">
						부두임대 사용료(단위 : 원)
						</td>
						<td style="text-align: center">
						항만시설사용료(단위 : 원)
						</td>
					</tr>
				</table>
			</div>
	    </div>
    </div>
</div>