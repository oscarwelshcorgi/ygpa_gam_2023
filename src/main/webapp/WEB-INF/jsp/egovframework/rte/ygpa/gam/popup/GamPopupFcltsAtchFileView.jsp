<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
  * @Class Name : GamPopupFcltsAtchFileView.jsp
  * @Description : 시설물 첨부 파일 VIEW 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.02.26  ACEWOLF          최초 생성
  *
  * author ACEWOLF
  * since 2014.01.22
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
 **/
%>

<%
/******************************** SCRIPT START ********************************/
%>

<script>

<%
/**
 * @FUNCTION NAME : GamPopupFcltsAtchFileViewModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamPopupFcltsAtchFileViewModule() {}

GamPopupFcltsAtchFileViewModule.prototype = new EmdPopupModule(600, 440);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsAtchFileViewModule.prototype.loadComplete = function(params) {

	this.resizable(true);

	this.$('#atchFileSe').on('change',{module:this}, function(event){
		var module = event.data.module;
		var atchFileSe = $(this).val();
		if (atchFileSe == "D") {
			module.$('#atchFileSeNm').val("문서");
		} else if (atchFileSe == "P") {
			module.$('#atchFileSeNm').val("사진");
		} else if (atchFileSe == "C") {
			module.$('#atchFileSeNm').val("도면");
		} else if (atchFileSe == "Z") {
			module.$('#atchFileSeNm').val("기타");
		} else {
			module.$('#atchFileSeNm').val("");
		}
	});

	this._updateFlag = false;
	if (params != null) {
		this.$('#atchFileNo').val(params.atchFileNo);
		var imageURL = params.imageURL;
		var searchVO = this.getFormValues('#detailForm');
		this.doAction('/popup/gamSelectFcltsAtchFileViewPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#detailForm', result.result);
				module.makeDivValues('#detailForm', result.result);
				this.$('#btnUpdate').enable();
				this.$('#btnUpdate').removeClass('ui-state-disabled');
				this.$('#btnDownload').enable();
				this.$('#btnDownload').removeClass('ui-state-disabled');
				module.previewFile(imageURL);
			} else {
				module.makeFormValues('#detailForm', {});
				module.makeDivValues('#detailForm', {});
				module.previewFile("");
				module.$('#btnUpdate').disable({disableClass:"ui-state-disabled"});
				module.$('#btnDownload').disable({disableClass:"ui-state-disabled"});
			}
		});
	} else {
		module.$('#btnUpdate').disable({disableClass:"ui-state-disabled"});
		module.$('#btnDownload').disable({disableClass:"ui-state-disabled"});
	}

};

<%
/**
 * @FUNCTION NAME : previewFile
 * @DESCRIPTION   : FILE PREVIEW
 * @PARAMETER     :
 *   1. argImageURL - IMAGE URL
**/
%>
GamPopupFcltsAtchFileViewModule.prototype.previewFile = function(argImageURL) {

	if (argImageURL != "") {
		var atchFileNmPhysicl = this.$('#atchFileNmPhysicl').val();
		if (atchFileNmPhysicl != null || atchFileNmPhysicl != "") {
			var ext = atchFileNmPhysicl.substring(atchFileNmPhysicl.lastIndexOf(".")+1).toLowerCase();
			if (ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif") {
				this.$("#previewImage").attr("src", argImageURL);
			} else {
				this.$("#previewImage").removeAttr("src");
			}
		}
	} else {
		this.$('#previewImage').attr("src", "");
	}

};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamPopupFcltsAtchFileViewModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnUpdate':
	    	this.processUpdate();
			break;
		case 'btnDownload':
	    	this.processDownload();
			break;
	    case 'btnExit':
	    	this.processExit();
			break;
	}

};

<%
/**
 * @FUNCTION NAME : processUpdate
 * @DESCRIPTION   : UPDATE PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsAtchFileViewModule.prototype.processUpdate = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var atchFileNo = this.$('#atchFileNo').val();
	var atchFileSe = this.$('#atchFileSe').val();
	if (atchFileNo == "") {
		alert('첨부 파일 번호가 부정확합니다.');
		return;
	}
	if (atchFileSe != "D" && atchFileSe != "P" && atchFileSe != "C" && atchFileSe != "Z") {
		alert('첨부 파일 구분이 부정확합니다.');
		this.$("#atchFileSe").focus();
		return;
	}
	this.doAction('/popup/gamUpdateFcltsAtchFileView.do', inputVO, function(module, result) {
		if (result.resultCode == "0") {
			module._updateFlag = true;
		}
		alert(result.resultMsg);
	});

};

<%
/**
 * @FUNCTION NAME : processDownload
 * @DESCRIPTION   : FILE DOWNLOAD
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsAtchFileViewModule.prototype.processDownload = function() {

	var atchFileNmPhysicl = this.$('#atchFileNmPhysicl').val();
	var atchFileNmLogic = this.$('#atchFileNmLogic').val();
	if (atchFileNmPhysicl == "" || atchFileNmLogic == "") {
		alert('첨부 파일 명이 부정확합니다.');
		return;
	}
	this.downPfPhoto(atchFileNmPhysicl, atchFileNmLogic);

};

<%
/**
 * @FUNCTION NAME : processExit
 * @DESCRIPTION   : EXIT PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsAtchFileViewModule.prototype.processExit = function() {

	if (this._updateFlag == true) {
		var returnData = {
				'atchFileNo':this.$('#atchFileNo').val(),
				'atchFileSe':this.$('#atchFileSe').val(),
				'atchFileSeNm':this.$('#atchFileSeNm').val(),
				'atchFileSj':this.$('#atchFileSj').val()
			};
		this.closeDialog("ok", returnData);
	} else {
		this.cancelDialog();
	}

};

var popup_instance = new GamPopupFcltsAtchFileViewModule();

</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<div class="dialog">
	<div class="emdPanel">
		<!-- 11. DATA AREA (자료 영역) -->
		<form id="detailForm">
			<table class="detailPanel" style="width:100%;">
				<tr>
					<th style="width:10%; height:18px;">파일 번호</th>
					<td>
						<input id="atchFileNmPhysicl" type="hidden"/>
						<input id="atchFileDirNo" type="hidden"/>
						<input id="atchFileDataSe" type="hidden"/>
						<input id="atchFileJobSe" type="hidden"/>
						<input id="atchFileFcltsMngNo" type="hidden"/>
						<input id="atchFileFcltsMngSeq" type="hidden"/>
						<input id="atchFileNmLogic" type="hidden"/>
						<input id="atchFileNo" type="text" size="15" disabled/>
					</td>
					<th style="width:10%; height:18px;">파일 구분</th>
					<td>
						<input id="atchFileSeNm" type="hidden"/>
						<select id="atchFileSe">
							<option value="D">문서</option>
							<option value="P">사진</option>
							<option value="C">도면</option>
							<option value="Z">기타</option>
                           </select>
					</td>
				</tr>
				<tr>
					<th style="width:10%; height:18px;">파일 설명</th>
					<td colspan="3">
						<input id="atchFileSj" type="text" size="80" maxlength="200"/>
					</td>
				</tr>
			</table>
		</form>
		<!-- 12. DATA AREA (자료 영역) -->
		<div class="emdPanel fillHeight">
			<img id="previewImage" style="margin:1px; width:550px; height:280px; border:1px solid #000; max-width:550px; max-height:280px" src="">
			<div class="emdControlPanel">
				<button id="btnUpdate" class="buttonSave">저장</button>
				<button id="btnDownload">다운로드</button>
				<button id="btnExit">종료</button>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
