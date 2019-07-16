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
function GamAssetSeSttusInqireModule() {}

GamAssetSeSttusInqireModule.prototype = new EmdModule(650, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamAssetSeSttusInqireModule.prototype.loadComplete = function() {

 // 테이블 설정 //
 this.$("#dataList1").flexigrid({
     module: this,
     url: '/asset/gamAssetSeSttusInqireList.do',
     dataType: 'json',
     colModel : [
                 {display:'구분', name:'gisAssetsSeCdNm',width:200, sortable:false,align:'center'},
	             {display:'건수', name:'count',width:200, sortable:false,align:'right'},
                 {display:'금액', name:'price',width:200, sortable:false,align:'right'}
                 ],
/*                  usepager: true,
         		useRp: true,
         		rp: 24,
 */
    showTableToggleBtn: false,
    height: '200'
 });

 this.$("#dataList2").flexigrid({
     module: this,
     url: '/asset/gamAssetSeSttusInqireList1.do',
     dataType: 'json',
     colModel : [
		         {display:'구분', name:'prdlstSeNm',width:40, sortable:false,align:'center'},
		         {display:'건수', name:'count',width:55, sortable:false,align:'right'},
		         {display:'금액', name:'price',width:50, sortable:false,align:'right'}
                 ],
    showTableToggleBtn: true,
    height: '200'
 });

 this.$("#dataList3").flexigrid({
     module: this,
     url: '/asset/gamAssetSeSttusInqireList2.do',
     dataType: 'json',
     colModel : [
		         {display:'구분', name:'prdlstSeNm',width:40, sortable:false,align:'center'},
		         {display:'건수', name:'count',width:55, sortable:false,align:'right'},
		         {display:'금액', name:'price',width:50, sortable:false,align:'right'}
                 ],
    showTableToggleBtn: true,
    height: '200'
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

	this.chart1 =  new dhtmlXChart({
        view:"pie",
        container:this.$("#chart1").attr('id'),
		value: "#count#",
		color: "#color#",
        label:"#gisAssetsSeCdNm#",
        shadow:0
    });

	this.chart2 =  new dhtmlXChart({
        view:"pie",
        container:this.$("#chart2").attr('id'),
		value: "#price#",
		color: "#color#",
        label:"#gisAssetsSeCdNm#",
        shadow:0
    });

	this.chart3 =  new dhtmlXChart({
        view:"pie",
        container:this.$("#chart3").attr('id'),
		value: "#count#",
		color: "#color#",
        label:"#prdlstSeNm#",
        shadow:0
    });

	this.chart4 =  new dhtmlXChart({
        view:"pie",
        container:this.$("#chart4").attr('id'),
		value: "#price#",
		color: "#color#",
        label:"#prdlstSeNm#",
        shadow:0
    });

	this.chart5 =  new dhtmlXChart({
        view:"pie",
        container:this.$("#chart5").attr('id'),
		value: "#count#",
		color: "#color#",
        label:"#prdlstSeNm#",
        shadow:0
    });

	this.chart6 =  new dhtmlXChart({
        view:"pie",
        container:this.$("#chart6").attr('id'),
		value: "#price#",
		color: "#color#",
        label:"#prdlstSeNm#",
        shadow:0
    });

	this._tabId='tabs1';
};

/**
* 정의 된 버튼 클릭 시
*/
GamAssetSeSttusInqireModule.prototype.onButtonClick = function(buttonId) {

 switch(buttonId) {

     // 조회
     case 'searchBtn':
/*
    	 if(this.$("#sSearchDtFrom").val() == ""){
    		 alert("기준일을 선택하세요.");
    		 return;
    	 }
 */
		this.loadData();
        this.$("#mainTab").tabs("option", {active: 0});	// 탭 이동
        break;

 }
};

GamAssetSeSttusInqireModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

	this.loadData();
};

GamAssetSeSttusInqireModule.prototype.loadData = function() {
	 var searchOpt=this.makeFormArgs('#searchForm');
	 var module = this;
	 var getColor1=function(code) {
		 var color_codes = {
			 "1": "#f00",
			 "2": "#00f",
			 "4": "#0f0"
			 };
		 return color_codes[code]||'#aaa';
		 };
	 var getColor2=function(code) {
		 var color_codes = {
			 "1": "#f00",
			 "2": "#00f",
			 "4": "#0f0"
			 };
		 return color_codes[code]||'#aaa';
		 };

		switch(this._tabId) {
		case 'tabs1':
			this.$('#dataList1').flexOptions({params:searchOpt}).flexReload({
	        	 callback: function(data) {
	        		 console.log(data);
	        		 var r=[];

	        		 for(var i=0; i<data.resultList.length; i++) {
	        			 var obj = data.resultList[i];
	        			 r[r.length] = {
	        					 gisAssetsSeCdNm: obj.gisAssetsSeCdNm,
	        					 count: obj.count,
	        					 price: Number(obj.price.replace(/,/g,"")),
	        					 color: getColor1(obj.gisAssetsSeCd)
	        			 }
	        		 }
	        		 console.log(r);
	        		 module.chart1.clearAll();
	        		 module.chart2.clearAll();
	        		 module.chart1.parse(r, "json");
	        		 module.chart2.parse(r, "json");
	        	 }
	         });
			break;
		case 'tabs2':
			this.$('#dataList2').flexOptions({params:searchOpt}).flexReload({
        	 callback: function(data) {
        		 console.log(data);
        		 var r=[];

        		 for(var i=0; i<data.resultList.length; i++) {
        			 var obj = data.resultList[i];
        			 r[r.length] = {
        					 prdlstSeNm: obj.prdlstSeNm,
        					 count: obj.count,
        					 price: Number(obj.price.replace(/,/g,"")),
        					 color: getColor2(obj.prdlstSe)
        			 }
        		 }
        		 console.log(r);
        		 module.chart3.clearAll();
        		 module.chart4.clearAll();
        		 module.chart3.parse(r, "json");
        		 module.chart4.parse(r, "json");
        	 }
         });
		break;
		case 'tabs3':
			this.$('#dataList3').flexOptions({params:searchOpt}).flexReload({
        	 callback: function(data) {
        		 console.log(data);
        		 var r=[];

        		 for(var i=0; i<data.resultList.length; i++) {
        			 var obj = data.resultList[i];
        			 r[r.length] = {
        					 prdlstSeNm: obj.prdlstSeNm,
        					 count: obj.count,
        					 price: Number(obj.price.replace(/,/g,"")),
        					 color: getColor2(obj.prdlstSe)
        			 }
        		 }
        		 console.log(r);
        		 module.chart5.clearAll();
        		 module.chart6.clearAll();
        		 module.chart5.parse(r, "json");
        		 module.chart6.parse(r, "json");
        	 }
         });
		break;
		}
};

GamAssetSeSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	 var searchOpt=this.makeFormArgs('#searchForm');
	 var module = this;
	 var getColor1=function(code) {
		 var color_codes = {
			 "1": "#f00",
			 "2": "#00f",
			 "4": "#0f0"
			 };
		 return color_codes[code]||'#aaa';
	 };
	 var getColor2=function(code) {
		 var color_codes = {
			 "1": "#f00",
			 "2": "#00f",
			 "4": "#0f0"
			 };
		 return color_codes[code]||'#aaa';
	 };
	switch(newTabId) {
		case 'tabs1':
			this._tabId='tabs1';
			this.loadData();
		    break;
		case 'tabs2':
			this._tabId='tabs2';
			this.loadData();
			/*
			var searchOpt=this.makeFormArgs('#searchForm');
		    this.$('#dataList2').flexOptions({params:searchOpt}).flexReload();
		    */
		    break;
		case 'tabs3':
			this._tabId='tabs3';
			this.loadData();
/*			var searchOpt=this.makeFormArgs('#searchForm');
		    this.$('#dataList3').flexOptions({params:searchOpt}).flexReload();
		    */
		    break;
	}
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetSeSttusInqireModule();
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
                            <th>항코드</th>
                            <td>
                                <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM019 />
                            </td>
<!--                             <th>GIS자산코드</th>
                            <td><input id="sGisAssetsCd" type="text" size="5"></td>
                            <th>GIS자산명</th>
                            <td><input id="sGisAssetsNm" type="text" size="20"></td>

                            <th>조회기준일자</th>
                            <td><input id="sSearchDtFrom" type="text" class="emdcal" size="8">~
                            <input id="sSearchDtTo" type="text" class="emdcal" size="8">
-->
                            <td><button id="searchBtn" class="submit buttonSearch">조회</button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>


	<div class="emdPanel fillHeight">
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">총괄</a></li>
				<li><a href="#tabs2" class="emdTab">출자 자산</a></li>
				<li><a href="#tabs3" class="emdTab">수익허가 자산</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="dataList1" style="display:none;" class="fillHeight"></table>

				<div class="emdPanel" style="height: 300px">
					<table style="width: 100%;">
						<tr>
							<td style="width: 50%;">
								<div id="chart1" style="width:300px; height:270px; border:1px solid #A4BED4;"></div>
							</td>
							<td style="width: 50%;">
								<div id="chart2" style="width:300px; height:270px; border:1px solid #A4BED4;"></div>
							</td>
						</tr>
						<tr>
							<td style="width: 50%;text-align: center;">
							총괄 건수
							</td>
							<td style="width: 50%;text-align: center;">
							총괄 금액
							</td>
						</tr>
					</table>
			    </div>
			</div>
			<div id="tabs2" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="dataList2" style="display:none;" class="fillHeight"></table>

				<div class="emdPanel" style="height: 300px">
					<table style="width: 100%;">
						<tr>
							<td style="width: 50%;">
								<div id="chart3" style="width:300px; height:270px; border:1px solid #A4BED4;"></div>
							</td>
							<td style="width: 50%;">
								<div id="chart4" style="width:300px; height:270px; border:1px solid #A4BED4;"></div>
							</td>
						</tr>
						<tr>
							<td style="width: 50%;text-align: center;">
							출자자산 건수
							</td>
							<td style="width: 50%;text-align: center;">
							출자자산 금액
							</td>
						</tr>
					</table>
			    </div>
			</div>
			<div id="tabs3" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="dataList3" style="display:none;" class="fillHeight"></table>

				<div class="emdPanel" style="height: 300px">
					<table style="width: 100%;">
						<tr>
							<td style="width: 50%;">
								<div id="chart5" style="width:300px; height:270px; border:1px solid #A4BED4;"></div>
							</td>
							<td style="width: 50%;">
								<div id="chart6" style="width:300px; height:270px; border:1px solid #A4BED4;"></div>
							</td>
						</tr>
						<tr>
							<td style="width: 50%;text-align: center;">
							수익허가자산 건수
							</td>
							<td style="width: 50%;text-align: center;">
							수익허가자산 금액
							</td>
						</tr>
					</table>
			    </div>
			</div>

		</div>
	</div>


<!--
	<div class="emdPanel">
		<table id="dataList" style="display:none"></table>
	</div>
	<div class="emdPanel">
		<table style="width: 100%;">
			<tr>
				<td style="width: 33%;">
				차트1
				</td>
				<td style="width: 33%;">
				차트2
				</td>
				<td style="width: 33%;">
				차트3
				</td>
			</tr>
			<tr>
				<td style="width: 33%;">
				차트1
				</td>
				<td style="width: 33%;">
				차트2
				</td>
				<td style="width: 33%;">
				차트3
				</td>

			</tr>

		</table>
    </div>
  -->
</div>