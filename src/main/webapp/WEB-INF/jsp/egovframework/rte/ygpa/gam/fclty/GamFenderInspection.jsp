<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFenderSttusInqire.jsp
 * @Description : 방충재 정기점검
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

<script type="text/javascript" src="<c:url value='js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script>

<script>

<%
/**
 * @FUNCTION NAME : GamFenderInspectionModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFenderInspectionModule() {}

GamFenderInspectionModule.prototype = new EmdModule(960, 550);

GamFenderInspectionModule.prototype.loadComplete = function() {
// 그리드 설정
	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fclty/gamFenderInspectionList.do',
		dataType : "json",
		colModel : [
					{display:"항구분",				name:"prtAtCodeNm",				width:60,		sortable:true,	align:"center"},
					{display:"연도",					name:"year",					width:60,		sortable:true,	align:"center"},
					{display:"종별",					name:"fcltsGbnNm",				width:60,		sortable:true,	align:"center"},
					{display:"관리 그룹 명",			name:"fcltsMngGroupNm",			width:150,		sortable:true,	align:"left"},
					{display:"종별",					name:"fcltsGbnNm",				width:80,		sortable:true,	align:"center"},
					{display:"규격",					name:"prtFcltyStndrd",			width:150,		sortable:true,	align:"center"},
					{display:"수량",					name:"prtPrtFcltyCnt",			width:60,		sortable:true,	align:"right",	displayFormat: 'number'},
					{display:"구분",					name:"fcltsSeNm",				width:80,		sortable:true,	align:"center"},
					{display:"종류",					name:"fcltsKndNm",				width:80,		sortable:true,	align:"center"},
					{display:"공사 일자",				name:"cnstDt",					width:80,		sortable:true,	align:"center"},
					{display:"준공 일자",				name:"bldDt",					width:80,		sortable:true,	align:"center"},
					{display:"운영사",				name:"owner",					width:110,		sortable:true,	align:"left"},
					{display:"위치",					name:"loc",						width:200,		sortable:true,	align:"left"}
					],
		showTableToggleBtn : false,
		height : 'auto'
	});

/* 	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

 */

 // 그리드 클릭
 	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainKeyValue = row.fcltsMngGroupNo;
		module._mainmode = "modify";
    });

 // 그리드 더블클릭
	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainKeyValue = row.fcltsMngGroupNo;
		module._mainmode = "modify";
		module.$("#mainTab").tabs("option", {active: 1});
	});



// 셀렉트 박스 비활성화
	this.$("#fcltsGbn").disable();
	this.$("#year").disable();


// 연도 셀렉트 박스 생성
	var toDate = new Date();
	var toYear = toDate.getFullYear();

	for(var i = toYear;i>=2010;i--){
		this.$(".year").append("<option value='" + i + "'>" + i + "년</option>");
	}

	/*
	this.$("#photoOne").on('change', function(){
		console.log('file upload');
		this.uploadSingleFile('/fclty/uploadFenderAttachFile.do', function(module, resp) {
			if(resp.resultCode!=0) {
				alert(resp.resultMsg);
				return;
			}

			console.log(resp.result);

			$.each(resp.result, function() {
			});
			if(resp.result!=null && resp.result.length>0) this._edited=true;
		});
	});
	<ul id="photoOneList" class="photoList">
		<li value="01"><span><img src="<c:url value="/images/egovframework/ygpa/gam/misc/TEST2.JPG"/>" /><button><i class="fa fa-trash-alt"></i></button></span></li>
	</ul>
	*/

	this._delPhotoOneList=[];
	this._delPhotoTwoList=[];
	this._delPhotoThreeList=[];

	this._photo1FileBuffer=[];
	this._photo2FileBuffer=[];
	this._photo3FileBuffer=[];

    var module=this;
	var removeImageClickEvent = function(e) {
		e.preventDefault();
		var btn = $(e.target).closest('button');
		var fileSn=btn.data('file-sn');
		var photo=btn.data('photo');
		if(fileSn!=undefined) {
			if(photo=="one") {
				module._delPhotoOneList[module._delPhotoOneList.length]=fileSn;
			}
			if(photo=="two") {
				module._delPhotoTwoList[module._delPhotoTwoList.length]=fileSn;
			}
			if(photo=="three") {
				module._delPhotoThreeList[module._delPhotoThreeList.length]=fileSn;
			}
		}
		else {
			var filename=btn.data('filename');
			if(photo=="one") {
				for(var i=0; i<module._photo1FileBuffer.length; i++) {
					if(module._photo1FileBuffer[i].name==filename) {
						delete module._photo1FileBuffer[i];
						btn.closest("li").remove();
						break;
					}
				}
			}
			else if(photo=="two") {
				for(var i=0; i<module._photo2FileBuffer.length; i++) {
					if(module._photo1FileBuffer[i].name==filename) {
						delete module._photo2FileBuffer[i];
						btn.closest("li").remove();
						break;
					}
				}
			}
			else if(photo=="three") {
				for(var i=0; i<module._photo3FileBuffer.length; i++) {
					if(module._photo1FileBuffer[i].name==filename) {
						delete module._photo3FileBuffer[i];
						btn.closest("li").remove();
						break;
					}
				}
			}

		}
	};

	$('ul.photoList > li > span > button').on('click', removeImageClickEvent);

	this.$('#photoOneFile').change(function(e){
        Array.prototype.push.apply(module._photo1FileBuffer, e.target.files);
        var html = '';
        $.each(e.target.files, function(index, file){
            const fileName = file.name;
            html += '<li value="new"><span>';
            html += '<img src="'+URL.createObjectURL(file)+'">'
            html += '<button data-photo="one" data-filename="'+fileName+'"><i class="fa fa-trash-alt"></i></button></span></li>';
            const fileEx = fileName.slice(fileName.indexOf(".") + 1).toLowerCase();
            if(fileEx != "jpg" && fileEx != "jpeg" && fileEx != "png" &&  fileEx != "gif" &&  fileEx != "bmp"){
                alert("파일은 (jpg, jpeg, png, gif, bmp) 형식만 등록 가능합니다.");
                return false;
            }
            module.$($(e.target).data('photo-list')).append(html);
        });
    	$('ul.photoList > li > span > button').off('click', removeImageClickEvent);
    	$('ul.photoList > li > span > button').on('click', removeImageClickEvent);
     });
	this.$('#photoTwoFile').change(function(){
        Array.prototype.push.apply(module._photo2FileBuffer, e.target.files);
        var html = '';
        $.each(e.target.files, function(index, file){
            const fileName = file.name;
            html += '<li value="new"><span>';
            html += '<img src="'+URL.createObjectURL(file)+'">'
            html += '<button data-photo="one" data-filename="'+fileName+'"><i class="fa fa-trash-alt"></i></button></span></li>';
            const fileEx = fileName.slice(fileName.indexOf(".") + 1).toLowerCase();
            if(fileEx != "jpg" && fileEx != "jpeg" && fileEx != "png" &&  fileEx != "gif" &&  fileEx != "bmp"){
                alert("파일은 (jpg, jpeg, png, gif, bmp) 형식만 등록 가능합니다.");
                return false;
            }
            module.$($(e.target).data('photo-list')).append(html);
        });
    	$('ul.photoList > li > span > button').off('click', removeImageClickEvent);
    	$('ul.photoList > li > span > button').on('click', removeImageClickEvent);
     });
	this.$('#photoThreeFile').change(function(){
        Array.prototype.push.apply(module._photo3FileBuffer, e.target.files);
        var html = '';
        $.each(e.target.files, function(index, file){
            const fileName = file.name;
            html += '<li value="new"><span>';
            html += '<img src="'+URL.createObjectURL(file)+'">'
            html += '<button data-photo="one" data-filename="'+fileName+'"><i class="fa fa-trash-alt"></i></button></span></li>';
            const fileEx = fileName.slice(fileName.indexOf(".") + 1).toLowerCase();
            if(fileEx != "jpg" && fileEx != "jpeg" && fileEx != "png" &&  fileEx != "gif" &&  fileEx != "bmp"){
                alert("파일은 (jpg, jpeg, png, gif, bmp) 형식만 등록 가능합니다.");
                return false;
            }
            module.$($(e.target).data('photo-list')).append(html);
        });
    	$('ul.photoList > li > span > button').off('click', removeImageClickEvent);
    	$('ul.photoList > li > span > button').on('click', removeImageClickEvent);
     });
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
GamFenderInspectionModule.prototype.onTabChange = function(newTabId, oldTabId) {
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

				this.$('#popupSpecFcltsMngGroupNo').enable();

				var toDate = new Date();
				var toYear = toDate.getFullYear();
				this.$("#year").enable();
				this.$('#year').val(toYear);

				//기본 데이터 설정
				// this.addData();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
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
GamFenderInspectionModule.prototype.onButtonClick = function(buttonId) {
	console.log("onButtonClick");
	switch (buttonId) {
		case 'btnAdd':
			this._mainmode = 'insert';
			this._mainKeyValue = '';
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
            var searchOpts = {'sYear':this.$("#year").val()};
			this.doExecuteDialog(buttonId, "시설물 관리 그룹 선택", '/popup/showFenderMngGroup.do', searchOpts);

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
GamFenderInspectionModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
		case 'popupSpecFcltsMngGroupNo':	// 버튼 parameter 값을 사용=> this.doExecuteDialog(buttonId, "시설물 관리 그룹 선택", '/popup/showFenderMngGroup.do', searchOpts);
			if (msg == 'ok') {
				this.$('#fcltsMngGroupNo').val(value.fcltsMngGroupNo);
				this.$('#fcltsMngGroupNm').val(value.fcltsMngGroupNm);
				this.$('#loc').val(value.loc);
				this.$('#fcltsGbn').val(value.fcltsGbn);
				this.$('#cnstDt').val(value.cnstDt);
				this.$('#bldDt').val(value.bldDt);
				this.$('#owner').val(value.owner);
				this.$('#cnstrtr').val(value.cnstrtr);
				this.$('#prtFcltyStndrd').val(value.prtFcltyStndrd);
				this.$('#prtPrtFcltyCnt').val(value.prtPrtFcltyCnt);

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
 GamFenderInspectionModule.prototype.onSubmit = function() {
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
 GamFenderInspectionModule.prototype.loadData = function() {
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
GamFenderInspectionModule.prototype.loadDetail = function(tabId) {
	console.log("loadDetail");
	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();	// 현재 선택된 row 값 가지고 오기
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);	// row[0] 번째 데이터를 detailForm에 넣기(자동으로 id 매칭하여 넣어 줌)

		this.$('ul.photoList').empty();		// remove photo list
		this.doAction('/fclty/gamFenderInspectionFileList.do', row[0], function(module, result) {
			console.log(result);
		});
//		this.makeDivValues('#detailForm', row[0]);

	}

}

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFenderInspectionModule.prototype.saveData = function() {
	console.log("saveData");

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
 	var module=this;

	var formData = new FormData();
	var fileCount=0;

	var inputVO = module.makeFormArgs("#detailForm");
	for(var k in inputVO) {
		formData.append(inputVO[k].name, inputVO[k].value);
	}

	for (var i = 0; i < this._photo1FileBuffer.length; i++) {
		var file = this._photo1FileBuffer[i];
		fileCount++;
		formData.append("photoOneFile[]", file);
	}
	for (var i = 0; i < this._photo2FileBuffer.length; i++) {
		var file = this._photo2FileBuffer[i];
		fileCount++;
		formData.append("photoTwoFile[]", file);
	}
	for (var i = 0; i < this._photo3FileBuffer.length; i++) {
		var file = this._photo3FileBuffer[i];
		fileCount++;
		formData.append("photoThreeFile[]", file);
	}

	for (var i = 0; i < this._delPhotoOneList.length; i++) {
		formData.append("delPhotoOne[]", this._delPhotoOneList[i]);
	}
	for (var i = 0; i < this._delPhotoTwoList.length; i++) {
		formData.append("delPhotoTwo[]", this._delPhotoTwoList[i]);
	}
	for (var i = 0; i < this._delPhotoThreeList.length; i++) {
		formData.append("delPhotoThree[]", this._delPhotoThreeList[i]);
	}

	var url = '';
	if (module._mainmode == "insert") { // 추가 버튼 눌렀을때 insert로 변경됨
		url = EMD.context_root+ '/fclty/gamInsertFenderInspection.do';
	}
	else {
		url = EMD.context_root+ '/fclty/gamUpdateFenderInspection.do';
	}
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url,
			true);
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
GamFenderInspectionModule.prototype.deleteData = function() {
	console.log("deleteData");

	if (confirm("삭제하시겠습니까?")) {
		//var deleteVO = row[0];
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/fclty/gamDeleteFenderInspection.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module._mainKeyValue = '';
				module.loadData();
			}
			alert(result.resultMsg);
		});
	}

}



var module_instance = new GamFenderInspectionModule();

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
				<li><a href="#listTab" class="emdTab">방중재 정기점검 목록</a></li>
				<li><a href="#detailTab" class="emdTab">방충재 정기점검 상세</a></li>
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
					<form id="detailForm" enctype="multipart/form-data" >
						<table class="summaryPanel" style="width:100%;">
							<tr>
								<th style="font-weight:bold; height:20px;">시설물 현황</th>
								<td style="text-align:right;">
									&nbsp;　　　&nbsp;
									&nbsp;　　　&nbsp;
									&nbsp;　　　&nbsp;
									&nbsp;　　　&nbsp;
									&nbsp;　　　&nbsp;
									&nbsp;　　　&nbsp;
								</td>
							</tr>
						</table>

						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:15%; height:18px;" >연도</th>
								<td  style="width:20%; height:18px;">
									<select id="year" class="year" tabindex=1 data-required="true">
										<option value="">선택</option>
									</select>
								</td>
								<th style="width:15%; height:18px;">시설물관리그룹</th>
								<td >
									<input type="text" size="14" id="fcltsMngGroupNo" maxlength="8" data-required="true" disabled="disabled"/>
									<input type="text" size="20" id="fcltsMngGroupNm" disabled="disabled"/>
									<button id="popupSpecFcltsMngGroupNo" class="popupButton">선택</button>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:18px;">위　　　　　　　　치</th>
								<td  style="width:20%; height:18px;">
									<input type="text" id="loc" data-column-id="loc" size="35" disabled="disabled" />
								</td>
								<th style="width:15%; height:18px;">시　설　물　　종　별</th>
								<td >
									<select id="fcltsGbn" data-column-id="fcltsGbn" >
										<option value="" selected>선택</option>
										<option value="1">1종</option>
										<option value="2">2종</option>
										<option value="3">1종/2종</option>
										<option value="9">기타</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:18px;" >공　　사　　기　　간</th>
								<td style="width:20%; height:18px;">
									<input type="text" id="cnstDt" data-column-id="cnstDt" size="25" class="emdcal"  disabled="disabled" />
								</td>
								<th style="width:15%; height:18px;">준　　공　　일　　자</th>
								<td>
									<input type="text" id="bldDt" data-column-id="bldDt" size="18" class="emdcal"  disabled="disabled" />
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:18px;">운　　　　영　　　사</th>
								<td style="width:20%; height:18px;" >
									<input type="text" id="owner" data-column-id="owner" size="35" disabled="disabled" />
								</td>
								<th style="width:15%; height:18px;">시　　　　공　　　자</th>
								<td >
									<input type="text" id="cnstrtr" data-column-id="cnstrtr" size="35" disabled="disabled" />
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:18px;">규　　　　  　　　 격</th>
								<td style="width:20%; height:18px;">
									<input type="text" id="prtFcltyStndrd" data-column-id="prtFcltyStndrd" size="35" disabled="disabled" />
								</td>
								<th style="width:15%; height:18px;">수　　　　  　　　 량</th>
								<td >
									<input type="text" id="prtPrtFcltyCnt" data-column-id="prtPrtFcltyCnt" class="ygpaNumber" size="10" disabled="disabled" />
								</td>
							</tr>
						</table>
						<table class="summaryPanel" style="width:100%;">
							<tr>
								<th style="font-weight:bold; height:20px;">점검 현황</th>
								<td style="text-align:right;">
									&nbsp;　　　&nbsp;
									&nbsp;　　　&nbsp;
									&nbsp;　　　&nbsp;
									&nbsp;　　　&nbsp;
									&nbsp;　　　&nbsp;
									&nbsp;　　　&nbsp;
								</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:20%; height:18px; text-align: center;">구분</th>
								<th style="width:20%; height:18px; text-align: center;">해빙기</th>
								<th style="width:20%; height:18px; text-align: center;">우기</th>
								<th style="width:20%; height:18px; text-align: center;">동절기</th>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">점검일자</th>
								<td  >
									<input type="text" id="chckDeOne" data-column-id="chckDeOne" tabindex=2 size="18" class="emdcal"  />
								</td>
								<td  >
									<input type="text" id="chckDeTwo" data-column-id="chckDeTwo" tabindex=8 size="18" class="emdcal"  />
								</td>
								<td  >
									<input type="text" id="chckDeThree" data-column-id="chckDeThree" tabindex=14 size="18" class="emdcal"  />
								</td>
							</tr>
							<tr>
								<th style="width:11%; height:18px;">점검자</th>
								<td >
									<input type="text" id="insctrOne" data-column-id="insctrOne" tabindex=3 size="35" />
								<td >
									<input type="text" id="insctrTwo" data-column-id="insctrTwo" tabindex=9 size="35" />
								</td>
								<td >
									<input type="text" id="insctrThree" data-column-id="insctrThree" tabindex=15 size="35" />
								</td>
							</tr>
							<tr>
								<th style="width:11%; height:18px;">점검내용</th>
								<td >
									<input type="text" id="chckCnOne" data-column-id="chckCnOne" tabindex=4 size="35" />
								</td>
								<td >
									<input type="text" id="chckCnTwo" data-column-id="chckCnTwo" tabindex=10 size="35" />
								</td>
								<td >
									<input type="text" id="chckCnThree" data-column-id="chckCnThree" tabindex=16 size="35" />
								</td>
							</tr>
							<tr>
								<th style="width:11%; height:18px;">상태평가</th>
								<td >
									<input type="text" id="sttusEvlOne" data-column-id="sttusEvlOne" tabindex=5 size="35" />
								</td>
								<td >
									<input type="text" id="sttusEvlTwo" data-column-id="sttusEvlTwo" tabindex=11 size="35" />
								</td>
								<td >
									<input type="text" id="sttusEvlThree" data-column-id="sttusEvlThree" tabindex=17 size="35" />
								</td>
							</tr>
							<tr>
								<th style="width:11%; height:18px;">사진</th>
								<td >
									<ul id="photoOneList" class="photoList">
									</ul>
									<input type="file" id="photoOneFile" data-photo-list="#photoOneList" tabindex=6 size="35" />
									<input type="hidden" id="photoOne" data-column-id="photoOne" />
								</td>
								<td >
									<ul id="photoTwoList" class="photoList">
									</ul>
									<input type="file" id="photoTwoFile" data-photo-list="#photoTwoList" tabindex=6 size="35" />
									<input type="hidden" id="photoTwo" data-column-id="photoTwo" />
								</td>
								<td >
									<ul id="photoThreeList" class="photoList">
									</ul>
									<input type="file" id="photoThreeFile" data-photo-list="#photoThreeList" tabindex=6 size="35" />
									<input type="hidden" id="photoThree" data-column-id="photoThree" />
								</td>
							</tr>
							<tr>
								<th style="width:11%; height:18px;">점검표</th>
								<td >
									<input type="file" id="chckTableOne" data-column-id="chckTableOne" tabindex=7 size="35" />
								</td>
								<td >
									<input type="file" id="chckTableTwo" data-column-id="chckTableTwo" tabindex=13 size="35" />
								</td>
								<td >
									<input type="file" id="chckTableThree" data-column-id="chckTableThree" tabindex=19 size="35" />
								</td>
							</tr>
						</table>
						<div style="vertical-align: bottom; text-align: right;">
							<button data-role="printPage" data-search-option="detailForm" data-url="/fclty/fenderInspectionPrint.do">인쇄</button>
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
