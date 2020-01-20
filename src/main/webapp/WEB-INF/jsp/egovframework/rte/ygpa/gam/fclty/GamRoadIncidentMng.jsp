<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFenderSttusInqire.jsp
 * @Description :  유지보수
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

GamFenderMaintenanceModule.prototype = new EmdModule(900, 550);

GamFenderMaintenanceModule.prototype.loadComplete = function() {
// 그리드 설정
	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fclty/gamRoadIncidentMngList.do',
		dataType : "json",
		colModel : [
					{display:"항구분",				name:"gisAssetsPrtAtCodeNm",		width:60,		sortable:true,	align:"center"},
					{display:"년도",					name:"year",				width:80,		sortable:true,	align:"center"},
					{display:"관리 그룹",				name:"fcltsMngGroupNm",		width:100,		sortable:true,	align:"center"},
					{display:"시설명",				name:"prtFcltyNm",			width:120,		sortable:true,	align:"left"},
					{display:"시설 위치",				name:"prtLoc",				width:200,		sortable:true,	align:"left"},
					
					{display:"공사일자",				name:"",					width:80,		sortable:true,	align:"left"},
					{display:"준공일자",				name:"",					width:80,		sortable:true,	align:"left"},
						
					{display:"관리사",				name:"owner",					width:120,		sortable:true,	align:"left"}
			
					],					
		showTableToggleBtn : false,
		height : 'auto'
	});

	this.$("#roadIncidentList").flexigrid({
		module: this,
		url: '/fclty/gamRoadIncidentMngDetailList.do',
		dataType: "json",
		colModel : [
//			{display:"선택", 			name:"chkRole",				width:40, 		sortable:false,		align:"center", 	displayFormat:"checkbox"},
			{display:"년도",			name:"year",				width:80,		sortable:false,		align:"center"},
			{display:"사고",	 		name:"trafficAccident",		width:150,		sortable:false,		align:"left"},
			{display:"사망",			name:"dead",				width:80,		sortable:false,		align:"right"},
			{display:"중상",			name:"slander",				width:80,		sortable:false,		align:"right"},
			{display:"경상",			name:"slightMishap",		width:80,		sortable:false,		align:"right"},
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


	this.$("#roadIncidentList").on('onItemCheckboxClick', function(event, module, row, rid, cInd) {
//		console.log("check row");
	});
	
 	this.$("#roadIncidentList").on('onItemSelected', function(event, module, row, grid, param) {
		console.log("roadIncidentList onItemSelected");

		module.makeFormValues('#detailForm', row);
/* 		for(var key in row){
			module.$('#'+key).val(row[key]);
			console.log(row[key])
		} */
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

	this.$("#sDtFrom").val(toYear+"-"+toMonth+"-"+toDay);

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
				this.$('#roadIncidentList').flexEmptyData();

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
				this.$('#roadIncidentList').flexEmptyData();
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
			if (this._mainmode=="modify") {
				this.loadDetail('listTab');
				this.deleteData();
			}
			break;
		// 시설물 관리 그룹 선택 버튼 클릭
		case 'popupSpecFcltsMngGroupNo':
			this.doExecuteDialog(buttonId, "시설물 관리 그룹 선택", '/popup/showFenderMngGroup.do', null);

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
				this.$('#roadIncidentList').flexOptions({params:searchOpt}).flexReload();
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
//		this.makeFormValues('#detailForm', row[0]);	// row[0] 번째 데이터를 detailForm에 넣기(자동으로 id 매칭하여 넣어 줌)
//		this.$('#sFcltsMngGroupNo').val(this._mainKeyValue);



		this.$('ul.photoList').empty();		// remove photo list
		this.$('ul.attList').empty();		// remove photo list
		this.doAction('/fclty/gamRoadIncidentMngFileList.do', row[0], function(module, result) {
			var html='';
			var result1=result.resultPhoto;
			if(result1!=null) {
				for(var i=0; i<result1.length; i++) {
					var file=result1[i];
		            html += '<li><span>';
/* 		            html += '<a href="<c:url value="/cmm/fms/getImage.do?" />atchFileId='+file.atchFileId+'&fileSn='+file.fileSn+'" target="_blank"><img src="<c:url value="/cmm/fms/getImage.do?" />atchFileId='+file.atchFileId+'&fileSn='+file.fileSn+'"></a>'
 */
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
		this.$('#roadIncidentList').flexOptions({params:searchOpt}).flexReload();
		
		var dRow = this.$('#roadIncidentList').selectedRows();
		this.makeFormValues('#detailForm', dRow[0]);

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

 	var inputVO = [];
	var formData = new FormData();
	var fileCount=0;

	var detailForm = this.makeFormArgs("#detailForm",'object');

	detailForm["delPhoto"]=JSON.stringify(this._delPhotoList);
	detailForm["delCompetInspctWtnnc"]=JSON.stringify(this._delAttList);

	this.$('#roadIncidentList')[0].dgrid.selectRow(0);

 	inputVO[inputVO.length] = {name: 'detailForm', value :JSON.stringify(detailForm) };
 	inputVO[inputVO.length] = {name: 'insertMntnObjList', value :JSON.stringify(this.$('#roadIncidentList').selectFilterData([{col: 'chkRole', filter: true}])) };



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
		url = EMD.context_root+ '/fclty/gamInsertFenderMaintenance.do';
	}
	else {
		url = EMD.context_root+ '/fclty/gamUpdateFenderMaintenance.do';
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
		this.doAction('/fclty/gamDeleteFenderMaintenance.do', deleteVO, function(module, result) {
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
							<th>사고 기간</th>
							<td colspan="5">
	                            <input id="sDtFrom" type="text" class="emdcal" data-role="dtFrom" data-dt-to="sDtTo" size="8">
	                            <span id="hideUsageDt"> ~
	                            	<input id="sDtTo" type="text" class="emdcal" data-role="dtTo" data-dt-from="sDtFrom" size="8">
	                            </span>
                            </td>
							<td>
								<button class="buttonSearch">조회</button>
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
				<li><a href="#listTab" class="emdTab"> 사고내역 목록</a></li>
				<li><a href="#detailTab" class="emdTab"> 사고내역 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
<!-- 				<div class="emdControlPanel">
					<table style="width:100%;">
						<tr>
							<td style="text-align:right;">
								<button id="btnAdd" class="buttonAdd">추가</button>
							</td>
						</tr>
					</table>
				</div>
 -->				
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
											<th style="width:20%; height:25px;" colspan="2">시설물　관리 그룹　명</th>
											<td>
												<input type="hidden" id="fcltsMngGroupNo" data-column-id="fcltsMngGroupNo"/>
												<input type="text" id="fcltsMngGroupNm" data-column-id="fcltsMngGroupNm" size="25" disabled="disabled" />
												<button id="popupSpecFcltsMngGroupNo" class="popupButton">선택</button>
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:25px;" colspan="2">시　　　　설　　　　물</th>
											<td>
												<input type="hidden" id="fcltsMngNo" data-column-id="fcltsMngNo" size="35" disabled="disabled" />
												<input type="text" id="fcltsMngNm" data-column-id="fcltsMngNm" size="35" disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:25px;" colspan="2">교　　통　　사　　고</th>
											<td>
												<input type="text" id="trafficAccident" data-column-id="trafficAccident" size="35" disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th style="width:10%; height:25px;" rowspan="3">사고 유형</th>
											<th>사망</th>
											<td>
												<input type="text" id="dead" data-column-id="dead" size="35" disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th>중상</th>
											<td>
												<input type="text" id="slander" data-column-id="slander" size="35" disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th>경상</th>
											<td>
												<input type="text" id="slightMishap" data-column-id="slightMishap" size="35" disabled="disabled" />
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:25px;" colspan="2">위　　 　　 　　치</th>
											<td>
												<input type="text" id="loc" data-column-id="loc" size="35" disabled="disabled"/>
											</td>
										</tr>
										<tr>
											<th style="width:20%; height:25px;" colspan="2">사　　　　　　　　진</th>
											<td>
												<ul id="photoList" class="photoList">
												</ul>
												<input type="file" id="photoFile" data-photo-list="#photoList" class="skipValue" tabindex=6 size="35" />
												<input type="hidden" id="photo" data-column-id="photo" />
											</td>
										</tr>
									</table>
								</td>
								<td style="border-bottom:none;">
									<table id="roadIncidentList" style="display:none"></table>
								</td>
							</tr>
						</table>
						<div style="vertical-align: bottom; text-align: right;">
							<button id="btnAdd" >추가</button>
							<button id="btnSave">저장</button>
							<button id="btnDelete">삭제</button>
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
