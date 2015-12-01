<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
  * @Class Name : GamPopupRepairMngFileView.jsp
  * @Description : 하자보수 첨부 파일 VIEW 팝업 (Prototype)
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
 * @FUNCTION NAME : GamPopupRepairMngFileViewModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamPopupRepairMngFileViewModule() {}

GamPopupRepairMngFileViewModule.prototype = new EmdPopupModule(600, 440);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamPopupRepairMngFileViewModule.prototype.loadComplete = function(params) {

	this.resizable(true);

	if(params.status == "inqire"){
		this.$('#btnUpdate').hide();
		this.$('#atchFileSe').disable();
		this.$('#atchFileSj').disable();
		this.$('#atchFileRm').disable();
	}

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
		this.$('#atchFileSeq').val(params.atchFileSeq);
		this.$('#fcltsJobSe').val(params.fcltsJobSe);
		console.log(this.$('#atchFileSeq').val());
		this.$('#fcltsMngGroupNo').val(params.fcltsMngGroupNo);
		this.$('#flawRprSeq').val(params.flawRprSeq);

		var imageURL = params.imageURL;
		var searchVO = this.getFormValues('#detailForm');
		this.doAction('/popup/gamSelectRepairMngFileViewPk.do', searchVO, function(module, result){
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
GamPopupRepairMngFileViewModule.prototype.previewFile = function(argImageURL) {

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
GamPopupRepairMngFileViewModule.prototype.onButtonClick = function(buttonId) {

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
GamPopupRepairMngFileViewModule.prototype.processUpdate = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var atchFileSeq = this.$('#atchFileSeq').val();
	var atchFileSe = this.$('#atchFileSe').val();
	if (atchFileSeq == "") {
		alert('첨부 파일 번호가 부정확합니다.');
		return;
	}
 	if (atchFileSe != "D" && atchFileSe != "P" && atchFileSe != "C" && atchFileSe != "Z") {
		alert('첨부 파일 구분이 부정확합니다.');
		this.$("#atchFileSe").focus();
		return;
	}
	this.doAction('/popup/gamUpdateRepairMngFileView.do', inputVO, function(module, result) {
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
GamPopupRepairMngFileViewModule.prototype.processDownload = function() {

	var atchFileNmPhysicl = this.$('#atchFileNmPhysicl').val();
	var atchFileNmLogic = this.$('#atchFileNmLogic').val();
	if (atchFileNmPhysicl == "" || atchFileNmLogic == "") {
		alert('첨부 파일 명이 부정확합니다.');
		return;
	}
	/* this.downPfPhoto(atchFileNmPhysicl, atchFileNmLogic); */
	this.downloadSingleFile("/fcltyMng/downloadRepairAttachFile.do", atchFileNmPhysicl, atchFileNmLogic);

};

<%
/**
 * @FUNCTION NAME : processExit
 * @DESCRIPTION   : EXIT PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupRepairMngFileViewModule.prototype.processExit = function() {

	if (this._updateFlag == true) {
		var returnData = {
				'atchFileSeq':this.$('#atchFileSeq').val(),
				'atchFileSe':this.$('#atchFileSe').val(),
				'atchFileSeNm':this.$('#atchFileSeNm').val(),
				'atchFileSj':this.$('#atchFileSj').val(),
				'atchFileRm':this.$('#atchFileRm').val()
			};
		this.closeDialog("ok", returnData);
	} else {
		this.cancelDialog();
	}

};

var popup_instance = new GamPopupRepairMngFileViewModule();

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
						<input id="fcltsJobSe" type="hidden"/>
						<input id="fcltsMngGroupNo" type="hidden"/>
						<input id="flawRprSeq" type="hidden"/>

						<input id="atchFileNmLogic" type="hidden"/>
						<input id="atchFileNmPhysicl" type="hidden"/>
						<input id="atchFileNmLogic" type="hidden"/>

						<input id="atchFileSeq" type="text" size="15" disabled/>
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
				<tr>
					<th style="width:10%; height:18px;">파일 비고</th>
					<td colspan="3">
						<input id="atchFileRm" type="text" size="80" maxlength="200"/>
					</td>
				</tr>
			</table>
		</form>
		<!-- 12. DATA AREA (자료 영역) -->
		<div class="emdPanel fillHeight">
			<img id="previewImage" style="margin:1px; width:550px; height:260px; border:1px solid #000; max-width:550px; max-height:260px" src="">
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
