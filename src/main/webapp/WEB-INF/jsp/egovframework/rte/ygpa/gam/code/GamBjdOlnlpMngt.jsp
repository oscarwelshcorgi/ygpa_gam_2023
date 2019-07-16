<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamOlnlpMngt.jsp
  * @Description : 공시지가 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.03.07  kok          최초 생성
  *
  * author kok
  * since 2014.03.07
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamOlnlpCode" staticJavascript="false" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamOlnlpMngtModule() {}

GamOlnlpMngtModule.prototype = new EmdModule(864,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamOlnlpMngtModule.prototype.loadComplete = function() {

	// 공시지가 등록현황 목록
	this.$("#olnlpList").flexigrid({
		module: this,
		url: '/code/selectBjdOlnlpList.do',
		dataType: "json",
		colModel : [
				{display:'', name:'state', width:30, sortable:true, align:'center'},
				{display:'기준년도', name:'olnlpApplcYear', width:80, sortable:true, align:'center'},
				{display:'소재지', name:'bupjungdongNm', width:300, sortable:true, align:'center'},
				{display:'산', name:'mt', width:30, sortable:true, align:'center'},
				{display:'지번', name:'lnm', width:100, sortable:true, align:'center'},
				{display:"공시지가", 	name:"olnlp", width:100, sortable:true,	align:"right", displayFormat: "input-number"}
			],
		height: "auto",
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				this['_updtId']=this.updtId;
			});
			return data;
		}
	});

	this._edited=false;
	this._deleteList=[];

	this.$("#olnlpList").on("onLoadDataComplete", function(event, module, data) {
		module._deleteList=[];
		module._edited=false;
	});

	this.$("#olnlpList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
	});


    this.$("#olnlpList").on('onCellEdited', function(event, module, row, rid, cid) {
        // 이벤트내에선 모듈에 대해 선택한다.
        if(cid=="olnlp") {
            if(row._updtId!="I") {
            	row._updtId="U";
            }
        	row.state="*";
            module.$("#olnlpList").flexUpdateRow(rid, row);
            module._edited=true;
        }
    });


	var dt = new Date();

	var year = dt.getFullYear();
	for(var i=year+2; i>year-10; i--) {
		this.$("#olnlpApplcYear").append("<option value='"+i+"'>"+i+"</option>");
	}
	this.$("#olnlpApplcYear").val(year);

	console.log("gamOlnlpLoad complete");

};

/**
 * 정의 된 버튼 클릭 시
 */
GamOlnlpMngtModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			this.loadData();
		break;
		case "insertBjdAddr":
			if(this.$('#locplc').val()=="") {
				EMD.util.showMessage(this.$('#btnSearchAddr'), "주소를 검색하여 입력 하십시요.");
				return;
			}
			if(this.$('#lnm').val()=="") {
				EMD.util.showMessage(this.$('#lnm'), "지번을 입력 하십시요.");
				return;
			}
			var lnm=EMD.util.leftPad(this.$('#lnm').val(), '0', 4);
			var lnmSub=EMD.util.leftPad(this.$('#lnmSub').val()||'0', '0', 4);
			var landCode=lnm+'-'+lnmSub;
			var data = {
					_updtId:"I",
					state: "*",
					olnlpApplcYear: this.$('#olnlpApplcYear').val(),
					bupjungdongCd: this.$('#bupjungdongCd').val(),
					bupjungdongNm: this.$('#locplc').val(),
					mt: this.$('#mt').attr("checked")=="checked"?"Y":"N",
					lnm: landCode
			};
			this.$('#olnlpList').flexAddRow(data);

			break;
		// 삭제
		case "btnDelete":
			var rows = this.$("#olnlpList").selectedRows();

			if(rows.length==0) {
				alert('삭제할 공시지가 항목을 선택 하십시요.');
				return;
			}
			if(confirm("선택 한 공시지가를 삭제하시겠습니까?")){
				var row=rows[0];
				if(row["_updtId"]!="I") {
					this._deleteList[this._deleteList.length]=row;
				}
				this.$('#olnlpList').flexRemoveRow(this.$('#olnlpList').selectedRowIds()[0]);
			}
		break;

		// 자산코드 팝업
		case "btnSearchAddr":
	        this.doExecuteDialog('selectAddrPopup', '주소 입력', '/popup/showAddrPopup.do', []);
		break;

		// 저장
		case "btnSave":
			this.saveOlnlp();
			break;

		case 'btnApplcOlnlp':
			this.applcOlnlp();
			break;

		case 'btnBjdOlnlpLink':
			if(confirm("OPENAPI 공시지가 정보를 연계합니다.")){
				this.bjdOlnlpLink();
			}

			break;
	}
};

GamOlnlpMngtModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#olnlpForm");
 	this.$("#olnlpList").flexOptions({params:searchOpt}).flexReload();
};

GamOlnlpMngtModule.prototype.saveOlnlp = function() {
	if( confirm("변경된 목록을 저장하시겠습니까?") ) {
	    // 변경된 자료를 저장한다.
	    var inputVO=[];
	    inputVO[inputVO.length]={name: 'updateList', value :JSON.stringify(this.$('#olnlpList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

	    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#olnlpList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

	    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteList) };

	    this.doAction('/code/mergeGamBjdOlnlpMngt.do', inputVO, function(module, result) {
	        if(result.resultCode == 0){
	        	module.loadData();
	        }
	        alert(result.resultMsg);
	    });
	}
};

GamOlnlpMngtModule.prototype.applcOlnlp = function() {
	if(this._edited) {
		confirm("변경된 내용이 있습니다. 저장 후에 적용하시기 바랍니다.");
		return;
	}
    this.doAction('/code/applyBjdOlnlpMngt.do', [], function(module, result) {
        if(result.resultCode == 0){
        }
        alert(result.resultMsg);
    });
};

/**
 * 2019-07-11
 * 공시지가 OPEN API 연동
 */
GamOlnlpMngtModule.prototype.bjdOlnlpLink = function() {
    this.doAction('/code/selectBjdOlnlpLink.do', [], function(module, result) {
        if(result.resultCode == 0){
        }
        alert(result.resultMsg);
    });
};

/**
 * 팝업 close 이벤트
 */
GamOlnlpMngtModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){
    	case 'selectAddrPopup':
        if (msg != 'cancel') {
            this.$('#bupjungdongCd').val(value.bupjungdongCd);
            this.$('#locplc').val(value.bupjungdongNm+" "+value.detailAddr);
        } else {
            alert('취소 되었습니다');
        }
        break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");

		break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamOlnlpMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="olnlpForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>기준년도</th>
							<td>
								<select id="olnlpApplcYear">
								</select>
							</td>
							<th>소재지</th>
							<td>
								<input id="searchLoc" type="text" size="50" title="소재지" />
							</td>
							<th>지번</th>
							<td>
								<input id="searchLnm" type="text" size="5" title="지번 검색"  />&nbsp;-&nbsp;
								<input id="searchLnmSub" type="text" size="5" title="지번 검색"  />
							</td>
							<td>
								<button id="searchBtn">조회</button>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- <div class="emdControlPanel">
				</div> -->
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="olnlpMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
			<ul>
				<li><a href="#tabs1" class="emdTab">공시지가목록</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="olnlpList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<form id="bjdAddrForm">
					<input type="hidden" id="bupjungdongCd" size="30" />
					<input id="locplc" size="30" disabled="disabled"/><button id="btnSearchAddr">주소검색</button>
					<input id="mt" type="checkbox" title="산" /> 산
					<input id="lnm" size="4" />
					<input id="lnmSub" size="4" />
					<button id="insertBjdAddr" class="buttonAdd">주소추가</button>
					<button id="btnDelete">삭제</button>
					<button id="btnSave">저장</button>
					</form>
					<button id="btnApplcOlnlp">자산에 공시지가적용</button>
					<button id="btnBjdOlnlpLink">openapi 연계</button>
					<!--
					<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="olnlpList" data-style="default">맵조회</button>
					<button id="insertExcel" class="buttonExcel">엑셀등록</button>
					 -->
				</div>
			</div>
		</div>
	</div>
</div>
