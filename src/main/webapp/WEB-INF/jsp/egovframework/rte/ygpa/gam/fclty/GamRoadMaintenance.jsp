<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFenderSttusInqire.jsp
 * @Description : 방충재 유지보수
 * @Modification Information
 *
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2019.06.19  	 김재철          화면단 최초 생성
 *
 * author 김재철
 * since 2016.06.19
 *
**/
%>
<%
/******************************** SCRIPT START ********************************/
%>
<validator:javascript formName="gamFenderInspectionVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script>

<script>

<%
/**
 * @FUNCTION NAME : GamFenderMaintenanceModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFenderMaintenanceModule() {}

GamFenderMaintenanceModule.prototype = new EmdModule(960, 550);

GamFenderMaintenanceModule.prototype.loadComplete = function() {
// 그리드 설정
	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fclty/gamRoadMaintenanceList.do',
		dataType : "json",
		colModel : [
					{display:"항구분",		name:"prtAtCodeNm",				width:60,		sortable:true,	align:"center"},
					/* {display:"연도",			name:"year",					width:60,		sortable:true,	align:"center"},
					{display:"종별",			name:"fcltsGbnNm",				width:60,		sortable:true,	align:"center"}, */
					{display:"관리 그룹 명",	name:"fcltsMngGroupNm",			width:150,		sortable:true,	align:"center"},
					/* {display:"위치",			name:"loc",						width:200,		sortable:true,	align:"left"},
					{display:"시행주체",		name:"opertnMbyNm",				width:200,		sortable:true,	align:"left"}, */
					{display:"공사명칭",		name:"cntrwkNm",				width:150,		sortable:true,	align:"center"},
					{display:"보수공사일자",		name:"cntrwkBegin",				width:100,		sortable:true,	align:"center"},
					{display:"보수공사 준공일자",		name:"cntrwkEnd",				width:100,		sortable:true,	align:"center"},
					{display:"시공사",		name:"cnstrtr",					width:100,		sortable:true,	align:"center"},
					{display:"공사금액",		name:"cntrwkCt",				width:130,		sortable:true,	align:"right",	displayFormat: 'number'},
					{display:"하자기간",		name:"flawEndTerm",				width:200,		sortable:true,	align:"center"},
					{display:"사업책임자",		name:"bsnsRspnber",				width:200,		sortable:true,	align:"left"}
					],
		showTableToggleBtn : false,
		height : 'auto'
	});

	this.$("#mntnRprObjFcltsF").flexigrid({
		module: this,
		url: '/fclty/gamRoadMaintenanceDetailList.do',
		dataType: "json",
		colModel : [
			{display:"선택", 				name:"chkRole",				width:40, 		sortable:false,		align:"center", 	displayFormat:"checkbox"},
			{display:"시설명",			name:"prtFcltyNm",			width:200,		sortable:false,		align:"center"},
			{display:"보수위치",			name:"repairLoc",			width:200,		sortable:false,		align:"center", displayFormat: 'input'},
			{display:"보수내용",	 		name:"repairCn",			width:150,		sortable:false,		align:"center",	displayFormat: 'input'},
			{display:"보수상태",			name:"repairSttus",			width:150,		sortable:false,		align:"center", displayFormat: 'input'},
/* 			{display:"단위",	 			name:"prtFcltyUnit",			width:40,		sortable:false,		align:"left"},
			{display:"수량",	 			name:"prtPrtFcltyCnt",			width:40,		sortable:false,		align:"right",	displayFormat: 'number'},
			{display:"구조 형식",	 		name:"strctFmt",			width:150,		sortable:false,		align:"left"}
 			{display:"선석",				name:"berth",					width:40,		sortable:false,		align:"left"},
			{display:"방충재 종류",		 	name:"fenderKndCd",				width:100,		sortable:false,		align:"left"},
			{display:"방충재 배치 간격",		name:"fenderPmntItv",			width:120,		sortable:false,		align:"left"},
			{display:"방충재 형식",		 	name:"fenderFmt",				width:150,		sortable:false,		align:"left"}
*/
			],
		height: "360",
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				this.chkRole = this.chkRole==="TRUE";
			});
			return data;
		}
	});

/* 	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

 */

 // 그리드 클릭
 	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainmode = "modify";
    });

 // 그리드 더블클릭
	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = "modify";
		module.$("#mainTab").tabs("option", {active: 1});
	});


	this.$("#mntnRprObjFcltsF").on('onItemCheckboxClick', function(event, module, row, rid, cInd) {
//		console.log("check row");
	});


// 셀렉트 박스 비활성화
	this.$("#year").disable();


// 연도 셀렉트 박스 생성
	var toDate = new Date();
	var toYear = toDate.getFullYear();
	var toMonth = toDate.getMonth() + 1;
	var toDay = toDate.getDate();

	if(toMonth < 10){
		toMonth = "0" + toMonth;
	}
	if(toDay < 10){
		toDay = "0" + toDay;
	}


	for(var i = toYear;i>=2010;i--){
		this.$(".year").append("<option value='" + i + "'>" + i + "년</option>");
	}

	this.$("#sCntrwkBegin").val(toYear+"-"+toMonth+"-"+toDay);

	<%--
	// 이미지 파일 처리
	--%>
    var module=this;

	this._delPhotoList=[];

	this._photoFileBuffer=[];

    this.$('ul.photoList > li > span > button').on('click', function(e) {
    	module.removeImageClickEvent(e)
    	});

	this.$('#photoFile').change(function(e){
        Array.prototype.push.apply(module._photoFileBuffer, e.target.files);
        var html = '';
        $.each(e.target.files, function(index, file){
            const fileName = file.name;
            html += '<li value="new"><span>';
            html += '<img src="'+URL.createObjectURL(file)+'">'
            html += '<button data-filename="'+fileName+'"><i class="fa fa-trash-alt"></i></button></span></li>';
            const fileEx = fileName.slice(fileName.indexOf(".") + 1).toLowerCase();
            if(fileEx != "jpg" && fileEx != "jpeg" && fileEx != "png" &&  fileEx != "gif" &&  fileEx != "bmp"){
                alert("파일은 (jpg, jpeg, png, gif, bmp) 형식만 등록 가능합니다.");
                return false;
            }
            module.$($(e.target).data('photo-list')).append(html);
        });
    	module.$('ul.photoList > li > span > button').off('click', function(e) {
    		module.removeImageClickEvent(e)
    	});
    	module.$('ul.photoList > li > span > button').on('click', function(e) {
    		module.removeImageClickEvent(e)
    	});
     });

	<%--
	// 준공검사조서 파일 처리
	--%>
	this._delAttList=[];

	this._attFileBuffer=[];

    this.$('ul.attList > li > span > button').on('click', function(e) {
    	module.removeAttClickEvent(e)
   	});

	this.$('#attFile').change(function(e){
        Array.prototype.push.apply(module._attFileBuffer, e.target.files);
        var html = '';
        $.each(e.target.files, function(index, file){
            const fileName = file.name;
            html += '<li value="new"><span>';
            html += fileName;
            html += '<button data-filename="'+fileName+'"><i class="fa fa-trash-alt"></i></button></span></li>';
            const fileEx = fileName.slice(fileName.indexOf(".") + 1).toLowerCase();
            if(fileEx != "hwp" && fileEx != "doc" && fileEx != "pdf" &&  fileEx != "docx" &&  fileEx != "xls" &&  fileEx != "xlsx"){
                alert("파일은 (hwp, doc, pdf, docx, xls, xlsx) 형식만 등록 가능합니다.");
                return false;
            }
            module.$($(e.target).data('file-list')).append(html);
        });
    	module.$('ul.attList > li > span > button').off('click', function(e) {
    		module.removeAttClickEvent(e)
    	});
    	module.$('ul.attList > li > span > button').on('click', function(e) {
    		module.removeAttClickEvent(e)
    	});
     });

	this.loadData();

}

<%
/**
 * @FUNCTION NAME : onTabChange
 * @DESCRIPTION   : 탭이 변경 될때 호출된다. (태그로 정의 되어 있음)
 * @PARAMETER     :
 *   1. newTabId - NEW TAB ID
 *   2. oldTabId - OLD TAB ID
**/
%>
GamFenderMaintenanceModule.prototype.onTabChange = function(newTabId, oldTabId) {
	console.log("onTabChange");
	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mainmode == "modify") {	// 탭 1번 수정 때
				this.loadDetail(oldTabId);
				this.$('#popupSpecFcltsMngGroupNo').disable({disableClass:"ui-state-disabled"});
				this.$("#year").disable();


			} else if (this._mainmode == "insert") { // 탭 1번 추가 때
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.$('#mntnRprObjFcltsF').flexEmptyData();

				this.$('ul.photoList').empty();		// remove photo list
				this.$('ul.attList').empty();		// remove photo list

				this.$('#popupSpecFcltsMngGroupNo').enable();
				this.$('#popupSpecFcltsMngGroupNo').removeClass("ui-state-disabled");

				var toDate = new Date();
				var toYear = toDate.getFullYear();
				this.$("#year").enable();
				this.$('#year').val(toYear);

				//기본 데이터 설정
				// this.addData();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.$('#mntnRprObjFcltsF').flexEmptyData();
				this.$('#popupSpecFcltsMngGroupNo').disable({disableClass:"ui-state-disabled"});
			}
			break;
	}

}

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : 버튼클릭 호출된다.
 * @PARAMETER     :
 *   1. buttonId - buttonId
**/
%>
GamFenderMaintenanceModule.prototype.onButtonClick = function(buttonId) {
	console.log("onButtonClick");
	switch (buttonId) {
		case 'btnAdd':
			this._mainmode = 'insert';
			this.$("#mainTab").tabs("option", {active: 1});
			break;
		case 'btnSave':
			this.saveData();
			break;
		case 'btnDelete':
		case 'btnDelete1':
			if (this._mainmode=="modify") {
				this.loadDetail('listTab');
				this.deleteData();
			}
			break;
		// 시설물 관리 그룹 선택 버튼 클릭
		case 'popupSpecFcltsMngGroupNo':
			this.doExecuteDialog(buttonId, "시설물 관리 그룹 선택", '/popup/showRoadMngGroup.do', null);

			break;
	}
}

<%
/**
 * @FUNCTION NAME : onClosePopup
 * @DESCRIPTION   : 팝업 종료 될때 함수
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
 *   2. msg      - MESSAGE
 *   3. value    - VALUE
**/
%>
GamFenderMaintenanceModule.prototype.onClosePopup = function(popupId, msg, value) {
	console.log("onClosePopup");
	switch (popupId) {
		case 'popupSpecFcltsMngGroupNo':	// 버튼 parameter 값을 사용=> this.doExecuteDialog(buttonId, "시설물 관리 그룹 선택", '/popup/showFenderMngGroup.do', searchOpts);
			if (msg == 'ok') {
				this.$('#fcltsMngGroupNo').val(value.fcltsMngGroupNo);
				this.$('#fcltsMngGroupNm').val(value.fcltsMngGroupNm);
				this.$('#loc').val(value.loc);
				this.$('#fcltsGbnNm').val(value.fcltsGbnNm);
				this.$('#prtAtCodeNm').val(value.prtAtCodeNm);

				var searchOpt=[];
				searchOpt[searchOpt.length] = {name : "sFcltsMngGroupNo",	value : value.fcltsMngGroupNo };
				searchOpt[searchOpt.length] = {name : "sYear",	value : '1111' };
				searchOpt[searchOpt.length] = {name : "sSn",	value : '0' };
				this.$('#mntnRprObjFcltsF').flexOptions({params:searchOpt}).flexReload();
			}
			break;
	}
}



<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
 GamFenderMaintenanceModule.prototype.onSubmit = function() {
	this.$("#sFcltsMngGroupNo").val('');
	this.loadData();
}

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
 GamFenderMaintenanceModule.prototype.loadData = function() {
	this._mainmode='';
	this.$("#mainTab").tabs("option", {active: 0});	// 탭 이동
	var searchOpt=this.makeFormArgs('#searchForm');	// searchOpt = {"sYear":"", "sFcltsGbn":"", "sFcltsMngGroupNm":""}
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();	// 그리드 설정된 내용으로 로딩

}

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFenderMaintenanceModule.prototype.loadDetail = function(tabId) {
	console.log("loadDetail");
	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();	// 현재 선택된 row 값 가지고 오기
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);	// row[0] 번째 데이터를 detailForm에 넣기(자동으로 id 매칭하여 넣어 줌)
//		this.makeDivValues('#detailForm', row[0]);

//		this.$('#sFcltsMngGroupNo').val(this._mainKeyValue);



		this.$('ul.photoList').empty();		// remove photo list
		this.$('ul.attList').empty();		// remove photo list
		this.doAction('/fclty/gamRoadMaintenanceFileList.do', row[0], function(module, result) {
			var html='';
			var result1=result.resultPhoto;
			if(result1!=null) {
				for(var i=0; i<result1.length; i++) {
					var file=result1[i];
		            html += '<li><span>';
/* 		            html += '<a href="<c:url value="/cmm/fms/getImage.do?" />atchFileId='+file.atchFileId+'&fileSn='+file.fileSn+'" target="_blank"><img src="<c:url value="/cmm/fms/getImage.do?" />atchFileId='+file.atchFileId+'&fileSn='+file.fileSn+'"></a>'
 */console.log("download");
		            html += '<a href="<c:url value="/cmm/fms/FileDown.do?" />atchFileId='+file.atchFileId+'&fileSn='+file.fileSn+'" target="_blank">'+file.orignlFileNm+'</a>'
		            html += '<button data-photo="one" data-atch-file-id="'+file.atchFileId+'" data-file-sn="'+file.fileSn+'"><i class="fa fa-trash-alt"></i></button></span></li>';
				}
			}
			module.$('#photoList').append(html);
			module.$('ul.photoList > li > span > button').off('click', function(e) {
		    	module.removeImageClickEvent(e)
			});
			module.$('ul.photoList > li > span > button').on('click', function(e) {
		    	module.removeImageClickEvent(e)
			});

			module.$('#photoFile').val("");
			module._photoFileBuffer=[];
			module._delPhotoList=[];

			html='';
			var result1=result.resultCompetInspctWtnnc;
			if(result1!=null) {
				for(var i=0; i<result1.length; i++) {
					var file=result1[i];
		            html += '<li><span>';
		            html += '<a href="<c:url value="/cmm/fms/FileDown.do?" />atchFileId='+file.atchFileId+'&fileSn='+file.fileSn+'" target="_blank">'+file.orignlFileNm+'</a>'
		            html += '<button data-chktbl="one" data-atch-file-id="'+file.atchFileId+'" data-file-sn="'+file.fileSn+'"><i class="fa fa-trash-alt"></i></button></span></li>';
				}
			}
			module.$('#attList').append(html);
			module.$('ul.attList > li > span > button').off('click', function(e) {
		    	module.removeAttClickEvent(e)
			});
			module.$('ul.attList > li > span > button').on('click', function(e) {
		    	module.removeAttClickEvent(e)
			});

			module.$('#attFile').val("");
			module._attFileBuffer=[];
			module._delAttList=[];
		});

		var searchOpt=[];
		searchOpt[searchOpt.length] = {name : "sFcltsMngGroupNo",	value : row[0].fcltsMngGroupNo };
		searchOpt[searchOpt.length] = {name : "sYear",	value : row[0].year };
		searchOpt[searchOpt.length] = {name : "sSn",	value : row[0].sn };
//		var searchOpt = {name:"fcltsMngGroupNo", value : this._mainKeyValue};
		this.$('#mntnRprObjFcltsF').flexOptions({params:searchOpt}).flexReload();

	}



}


GamFenderMaintenanceModule.prototype.removeImageClickEvent = function(e) {
	e.preventDefault();
	var btn = $(e.target).closest('button');
	var fileSn=btn.data('file-sn');

	if(fileSn!=undefined) {
		this._delPhotoList[this._delPhotoList.length]=fileSn;
	}
	else {
		for(var i=0; i<this._photoFileBuffer.length; i++) {
			if(this._photoFileBuffer[i].name==filename) {
				delete this._photoFileBuffer[i];
				break;
			}
		}
	}
	btn.closest("li").remove();
};

GamFenderMaintenanceModule.prototype.removeAttClickEvent = function(e) {
	e.preventDefault();
	var btn = $(e.target).closest('button');
	var fileSn=btn.data('file-sn');

	if(fileSn!=undefined) {
		this._delAttList[this._delAttList.length]=fileSn;
	}
	else {
		for(var i=0; i<this._attFileBuffer.length; i++) {
			if(this._attFileBuffer[i].name==filename) {
				delete this._attFileBuffer[i];
				break;
			}
		}
	}
	btn.closest("li").remove();
};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFenderMaintenanceModule.prototype.saveData = function() {
//	console.log("saveData");

/* 	if (!validateGamFenderInspectionVO(this.$("#detailForm")[0])){
		return;
	} */
/*
	if (this.$('#year').val() == "") {
		alert('연도 코드가 부정확합니다.');
		this.$("#year").focus();
		return;
	}
	if (this.$('#fcltsMngGroupNo').val() == "") {
		alert('시설물 관리 그룹 코드가 부정확합니다.');
		this.$("#year").focus();
		return;
	}
 */
//	var inputVO = this.makeFormArgs("#detailForm");
 	var inputVO = [];
	var formData = new FormData();
	var fileCount=0;

	var detailForm = this.makeFormArgs("#detailForm",'object');

	detailForm["delPhoto"]=JSON.stringify(this._delPhotoList);
	detailForm["delCompetInspctWtnnc"]=JSON.stringify(this._delAttList);

	this.$('#mntnRprObjFcltsF')[0].dgrid.selectRow(0);

 	inputVO[inputVO.length] = {name: 'detailForm', value :JSON.stringify(detailForm) };
 	inputVO[inputVO.length] = {name: 'insertMntnObjList', value :JSON.stringify(this.$('#mntnRprObjFcltsF').selectFilterData([{col: 'chkRole', filter: true}])) };



	for(var k in inputVO) {
		formData.append(inputVO[k].name, inputVO[k].value);
	}
	for (var i = 0; i < this._photoFileBuffer.length; i++) {
		var file = this._photoFileBuffer[i];
		fileCount++;
		formData.append("photoFile["+i+"]", file);
	}
	for (var i = 0; i < this._attFileBuffer.length; i++) {
		var file = this._attFileBuffer[i];
		fileCount++;
		formData.append("competInspctWtnnc["+i+"]", file);
	}

	var module=this;
	var url = '';
	if (module._mainmode == "insert") { // 추가 버튼 눌렀을때 insert로 변경됨
		url = EMD.context_root+ '/fclty/gamInsertRoadMaintenance.do';
	}
	else {
		url = EMD.context_root+ '/fclty/gamUpdateRoadMaintenance.do';
	}
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url, true);
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var result = JSON.parse(this.response);
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		}
	};
	xhr.onprogress = function(e) {
		// console.log('upload '+ (e.loaded/e.total)*100+'%');
	};

	xhr.send(formData);
}

<%
/**
 * @FUNCTION NAME : deleteData
 * @DESCRIPTION   : 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamFenderMaintenanceModule.prototype.deleteData = function() {
	console.log("deleteData");

	if (confirm("삭제하시겠습니까?")) {
		//var deleteVO = row[0];
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/fclty/gamDeleteRoadMaintenance.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	}

}



var module_instance = new GamFenderMaintenanceModule();

</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<!-- 11. SEARCH AREA (조회조건 영역) -->
	<div id="searchViewStack" class="emdPanel">
		<div class="viewPanel">
			<form id="searchForm">
				<input id="sFcltsMngGroupNo" data-column-id="sFcltsMngGroupNo" type="hidden" />
				<table style="width:100%;" class="searchPanel">
					<tbody>
						<tr>
							<th>연도</th>
							<td>
								<select id="sYear" class="year">
									<option value="">선택</option>
								</select>
							</td>

							<th>시설물 종류</th>
							<td>
								<select id="sFcltsGbn" data-column-id="sFcltsGbn">
										<option value="" selected>선택</option>
										<option value="1">1종</option>
										<option value="2">2종</option>
										<option value="3">1종/2종</option>
										<option value="9">기타</option>
									</select>
							</td>
							<th>시설물　관리　그룹　명</th>
							<td>
								<input id="sFcltsMngGroupNm" data-column-id="sFcltsMngGroupNm" type="text" size="30" maxlength="80"/>
							</td>
							<td>
								<button class="buttonSearch">조회</button>
							</td>
						</tr>
						<tr>
							<th>유지보수 기간</th>
							<td colspan="5">
	                            <input id="sCntrwkBegin" type="text" class="emdcal" data-role="dtFrom" data-dt-to="sCntrwkEnd" size="8">
	                            <span id="hideUsageDt"> ~
	                            	<input id="sCntrwkEnd" type="text" class="emdcal" data-role="dtTo" data-dt-from="sCntrwkBegin" size="8">
	                            </span>
                            </td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<!-- 2. DATA AREA (자료 영역) -->
	<div class="emdPanel fillHeight">
		<!-- 21. TAB AREA (탭 영역) -->
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<!-- 211. TAB 정의 -->
			<ul>
				<li><a href="#listTab" class="emdTab">임항도로 유지보수 목록</a></li>
				<li><a href="#detailTab" class="emdTab">임항도로 유지보수 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div class="emdControlPanel">
					<table style="width:100%;">
						<tr>
							<td style="text-align:right;">
<!-- 	                                <button id="btnExcelDownload" class="buttonExcel">엑셀 다운로드</button> -->
								<button id="btnAdd" class="buttonAdd">추가</button>
								<button id="btnDelete" class="buttonDelete">삭제</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<input type="hidden" id="sn" data-column-id="sn"/>
						<table class="detailPanel"  style="width:100%;">
							<tr>
								<td colspan="2" style="width:40%; vertical-align: top;">
									<table class="detailPanel" style="width:100%;">
										<tr>
											<th style="width:20%; height:18px;">도　　　　로　　　　명</th>
											<td>
												<input type="hidden" id="fcltsMngGroupNo" data-column-id="fcltsMngGroupNo"/>
												<input type="text" id="fcltsMngGroupNm" data-column-id="fcltsMngGroupNm" size="35" disabled="disabled" />
												<button id="popupSpecFcltsMngGroupNo" class="popupButton">선택</button>
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;">보　　수　　위　　치</th>
											<td  >
												<input type="text" id="loc" data-column-id="loc" size="35" disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;">보　　수　　주　　체</th>
											<td  >
												<input type="text" id="owner" data-column-id="owner" size="35" disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;">공　　　　사　　　　명</th>
											<td>
												<input type="text" id="cntrwkNm" data-column-id="cntrwkNm" size="35" />
											</td>
										</tr>


										<tr>
											<th style="width:20%; height:18px;">공　　사　　기　　간</th>
											<td>
												<input type="text" id="cntrwkBegin" size="11" class="emdcal" />~
												<input type="text" id="cntrwkEnd" size="11" class="emdcal" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;" >공　　사　　내　　용</th>
											<td>
<!-- 												<input id="opertnMby" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM075"/> -->
												<input type="text" id="cntrwkCn" data-column-id="cntrwkCn" size="35"/>
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;" >시　　　　공　　　　사</th>
											<td>
												<input type="text" id="cnstrtr" data-column-id="cnstrtr" size="35" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;" >사　업	　책　임　자</th>
											<td>
												<input type="text" id="bsnsRspnber" data-column-id="bsnsRspnber" size="35" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;" >공　　　　사　　　　비</th>
											<td>
												<input type="text" id="cntrwkCt" class="ygpaNumber" data-column-id="cntrwkCt" size="35" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;" >하　　자　　기　　간</th>
											<td>
												<input type="text" id="bldDt" size="11" class="emdcal" />~
												<input type="text" id="flawEndDt" size="11" class="emdcal" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;" >사　　　　　　　　진</th>
											<td>
												<ul id="photoList" class="photoList">
												</ul>
												<input type="file" id="photoFile" data-photo-list="#photoList" class="skipValue" tabindex=6 size="35" />
												<input type="hidden" id="photo" data-column-id="photo" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:18px;" >준　공　검　사　조　서</th>
											<td>
												<ul id="attList" class="attList">
												</ul>
												<input type="file" id="attFile" data-file-list="#attList" class="skipValue" tabindex=7 size="35" />
												<input type="hidden" id="competInspctWtnnc" data-column-id="competInspctWtnnc" />
											</td>
										</tr>

									</table>
								</td>
								<td style="border-bottom:none;">
									<table id="mntnRprObjFcltsF" style="display:none"></table>
								</td>
							</tr>
						</table>
						<div style="vertical-align: bottom; text-align: right;">
							<button data-role="printPage" data-search-option="detailForm" data-url="/fclty/gamFenderMaintenancePrint.do">인쇄</button>
							<!-- data-search-option="detailForm" 의 경우 detailForm 폼안의  data-column-id에 정의된 이름으로 파라메터를 생성함.  -->
							<button id="btnSave">저장</button>
							<button id="btnDelete1">삭제</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
