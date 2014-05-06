<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamAssetMngt.jsp
  * @Description : 자산코드관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2013.10.29  장은성          최초 생성
  *
  * author 장은성
  * since 2013.10.29
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamAssetCode" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetPhoto" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetCodeModule() {}

GamAssetCodeModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetCodeModule.prototype.loadComplete = function(params) {
	this.$('#prtAtCode').val('620');	// 기본 항코드 설정

	this.$("#assetCodeList").flexigrid({
		module: this,
		url: '<c:url value="/code/assets/selectGisAssetCodeInqireList.do"/>',
		colModel : [
			{display:'항코드', name:'gisAssetsPrtAtCode', width:24, sortable:false, align:'center'},
			{display:'항구분', name:'prtAtCodeNm', width:40, sortable:false, align:'center'},
			{display:'자산코드', name:'assetCode', width:56, sortable:false, align:'center'},
			{display:'자산명', name:'gisAssetsNm', width:180, sortable:false, align:'center'},
			{display:'재산', name:'gisAssetsPrprtyNm', width:80, sortable:false, align:'center'},
			{display:'위치', name:'gisAssetsLocNm', width:80, sortable:false, align:'center'},
			{display:'부두', name:'gisAssetsQuayNm', width:80, sortable:false, align:'center'},
			{display:'관리 부서', name:'mngDeptNm', width:80, sortable:false, align:'center'},
			{display:'운영 부서', name:'operDeptNm', width:80, sortable:false, align:'center'},
			{display:'소재지', name:'gisAssetsLocplc', width:180, sortable:false, align:'left'},
			{display:'지번', name:'lotcode', width:40, sortable:false, align:'center'},
			{display:'면적', name:'gisAssetsAr', width:64, sortable:false, align:'right', displayFormat: 'number'},
			{display:'실임대면적', name:'gisAssetsRealRentAr', width:64, sortable:false, align:'right', displayFormat:'number'},
			{display:'취득가액', name:'gisAssetsAcqPri', width:80, sortable:false, align:'right', displayFormat: 'number'},
/* 			{display:'규격', name:'gisAssetsStndrd', width:120, sortable:false, align:'center'},
			{display:'준공년도', name:'gisAssetsBlddate', width:32, sortable:false, align:'center'},
			{display:'준공 일자', name:'gisAssetsBldDt', width:128, sortable:false, align:'center'}, */
			{display:'사용', name:'gisAssetsUsageYn', width:30, sortable:false, align:'center'}
			],
		height: 'auto',
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				this.assetCode = this.gisAssetsCd+"-"+this.gisAssetsSubCd;
				this.lotcode = this.gisAssetsLnm;
				if(this.gisAssetsLnmSub!=null && this.gisAssetsLnmSub.length>0) {
					this.lotcode += "-"+this.gisAssetsLnmSub;
				}
			});
			return data;
		}
	});

	this.$("#assetCodeList").on('onItemSelected', function(event, module, row, grid, param) {
	});

	this.$("#assetCodeList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		if(row==null) return;
		module.selectedItem = row;
		module.$("#assetCodeTab").tabs("option", {
			active : 1
		}); // 탭을 전환 한다.
	});

	this.$("#assetCodePhotoList").flexigrid({
		module: this,
		url: '<c:url value="/code/assets/selectGisAssetCodeInqirePhotoList.do"/>',
		dataType: 'json',
		colModel : [
				{display:'사진 순번', name:'photoSeq', width:80, sortable:false, align:'center'},
				{display:'사진 제목', name:'photoSj', width:300, sortable:false, align:'center'},
				{display:'파일명', name:'filenmLogic', width:200, sortable:false, align:'left'},
				{display:'촬영 일시', name:'shotDt', width:120, sortable:false, align:'center'},
				{display:'등록자', name:'regUse', width:160, sortable:false, align:'center'}
			],
		height: '120'
	});

	this.$("#assetCodePhotoList").on("onLoadDataComplete", function(event, module, data, grid, param) {
		module._edited=false;
		module.$('#previewImage').attr('src', '#');
		throw 0;
	});

	this.$("#assetCodePhotoList").on('onItemSelected', function(event, module, row, grid, param) {
		module.$('.emdCal').val('');
		module.makeFormValues('#editAssetGisPhotoForm', row);

		if(row.filenmPhysicl!=null || row.filenmPhysicl!='') {
			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm=row['filenmPhysicl'];
			var ext=filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();
			if(ext=='jpg' || ext=='jpeg' || ext=='bmp' || ext=='png' || ext=='gif') {
			    $imgURL = module.getImageUrl(filenm);
			    module.$("#previewImage").fadeIn(400, function() {
			    	module.$("#previewImage").attr('src', $imgURL);
			    });
			}
			else {
				module.$("#previewImage").attr(src, '#');
			}
		}
//		console.log('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is selected');
	});

	this.$('#gisAssetsLocCd').on('change', function() {
		//alert($(this).getSelectedCodeLabel() + '이(가) 선택되었습니다.');
	});

	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다. _params 널체크가 귀찮아서

	this._params = params;	// 파라미터를 저장한다.

	switch(this._params.action) {
	case 'prtFcltyInqire':

//		this.$('#searchGisAssetsPrtAtCode').val(this._params.gisPrtAtCode);
//		this.$('#searchGisAssetsCd').val(this._params.gisAssetsCd);
//		this.$('#searchGisAssetsSubCd').val(this._params.gisAssetsSubCd);
		this.selectedItem = {
				gisAssetsPrtAtCode: this._params.gisPrtAtCode,
				gisAssetsCd: this._params.gisAssetsCd,
				gisAssetsSubCd: this._params.gisAssetsSubCd,
		};
		this.$("#assetCodeTab").tabs("option", {
			active : 1
		}); // 탭을 전환 한다.
		break;
	}

};

	// 사용자 설정 함수 추가
	// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
	GamAssetCodeModule.prototype.showModuleAlert = function(msg) {
		//this.getSelect(msg);
		this.$('#prtCode').val(msg);
	};

	GamAssetCodeModule.prototype.onButtonClick = function(buttonId) {
		switch (buttonId) {
		case 'selectGisAssetCode':
			var searchOpt = this.makeFormArgs('#searchGisAssetCode');
			this.$('#assetCodeList').flexOptions({
				params : searchOpt
			}).flexReload();
			this.$("#assetCodeTab").tabs("option", {
				active : 0
			}); // 탭 전환
			throw 0;
			break;
		case 'popupFcltyCd':
			var opts = null;
			this.doExecuteDialog('selectSearchAssetsCdPopup', '시설 선택',
					'<c:url value="/popup/showAssetsCd.do"/>', opts);
			break;
		}
	};

	GamAssetCodeModule.prototype.onClosePopup = function(popupId, msg, value) {
		switch (popupId) {
		case 'selectSearchErpAssetsCdPopup':
			if (msg != 'cancel') {
				this.$('#erpAssetsCls').val(value.assetCls);
				this.$('#erpAssetsNo').val(value.assetNo);
				this.$('#erpAssetsNoSeq').val(value.assetNoSeq);
				this.$('#itemName').val(value.itemNameAsset);
			} else {
				alert('취소 되었습니다');
			}
			break;
		case 'selectEntrpsInfoPopup':
			if (msg != 'cancel') {
				this.$('#sEntrpscd').val(value.entrpscd);
				this.$('#sEntrpsNm').val(value.entrpsNm);
			} else {
				alert('취소 되었습니다');
			}
			break;
		case 'selectSearchAssetsCdPopup':
			if (msg != 'cancel') {
				this.$('#searchPrtAtCode').val(value.gisPrtAssetsCd);
				this.$('#searchGisAssetCode').val(value.gisAssetsCd);
				this.$('#searchGisAssetSubCode').val(value.gisAssetsSubCd);
			} else {
				alert('취소 되었습니다');
			}
			break;

		default:
			alert('알수없는 팝업 이벤트가 호출 되었습니다.');
			throw 0;
			break;
		}
	};

	/*
	 GamAssetCodeModule.prototype.onUploadFileDone = function(uploadId, result) {
	 $.each(result, function() {
	 this.$)
	 });
	 }
	 */
	GamAssetCodeModule.prototype.onTabChange = function(newTabId, oldTabId) {
		switch (newTabId) {
		case 'tabs1':
			//		this.$('#searchViewStack')[0].changePanelId(0);
			break;
		case 'tabs2':
			this.$('#editGisAssetCode :input').val(''); // 폼의 값을 모두 지운다.
			if(this.selectedItem==null) {
				return;
			}
			var assetCd = [
			               { name: 'gisAssetsPrtAtCode', value: this.selectedItem.gisAssetsPrtAtCode},
			               { name: 'gisAssetsCd', value: this.selectedItem.gisAssetsCd },
			               { name: 'gisAssetsSubCd', value: this.selectedItem.gisAssetsSubCd }
			             ];
	   	 	this.doAction('<c:url value="/code/assets/selectGisAssetCodeInqireByPk.do" />', assetCd, function(module, result) {
				if (result.resultCode == "0") {
					module.makeFormValues('#editGisAssetCode',
							result.result); // 결과값을 채운다.
					module._editData = result.result;
							module._state="";
				} else {
					alert(result.resultMsg);
				}
						});
			break;
		case 'tabs3':
			this.loadPhotoList();
 			break;
		}
	};

	GamAssetCodeModule.prototype.loadPhotoList = function() {
		this.$('#editAssetGisPhotoForm :input').val(''); // 폼의 값을 모두 지운다.
		if(this.selectedItem==null) {
			return;
		}
		var assetCd = [
	               { name: 'gisAssetsPrtAtCode', value: this.selectedItem.gisAssetsPrtAtCode},
	               { name: 'gisAssetsCd', value: this.selectedItem.gisAssetsCd },
	               { name: 'gisAssetsSubCd', value: this.selectedItem.gisAssetsSubCd }
	             ];
	 	this.$('#assetCodePhotoList').flexOptions({params:assetCd}).flexReload();
	}

	GamAssetCodeModule.prototype.onSubmit = function() {
		//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
		this.loadData();
	};

	GamAssetCodeModule.prototype.loadData = function() {
		var searchOpt = this.makeFormArgs('#searchErpAssetCode');
		//this.showAlert(searchOpt);
		this.$('#assetCodeList').flexOptions({
			params : searchOpt
		}).flexReload();
		this.$("#assetCodeTab").tabs("option", {
			active : 0
		}); // 탭을 전환 한다.
		//	this.$('#assetList').flexOptions(searchOpt).flexReload();
	};

	// 다음 변수는 고정 적으로 정의 해야 함
	var module_instance = new GamAssetCodeModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId }'/>
<div class="window_main">
	<div class="emdPanel">
		<form id="searchGisAssetCode">
						<table class="searchPanel">
							<tbody>
							<tr>
								<th>항구분</th>
								<td><input id="searchGisAssetsPrtAtCode" type="text" class="ygpaCmmnCd" data-column-id="gisAssetsPrtAtCode" data-code-id="GAM019" data-default-prompt="전체항" data-display-value="N" size="3"/></td>
								<th>ERP 자산코드</th>
								<td colspan="3"><input id="searchGisErpAssetCls" data-column-id="erpAssetCls" type="text" size="1">-<input id="searchGisErpAssetNo" data-column-id="erpAssetNo" type="text" size="4">-<input id="searchGisErpAssetNoSeq" data-column-id="erpAssetNoSeq" type="text" size="2"></td>
								<th>자산코드</th>
								<td><input id="searchGisAssetsCd" data-column-id="gisAssetsCd" type="text" size="3">-<input id="searchGisAssetsSubCd" data-column-id="gisAssetsSubCd" type="text" size="2"></td>
								<td rowSpan="3"><button id="selectGisAssetCode" class="buttonSearch">조회</button></td>
							</tr>
							<tr>
								<th>재산</th>
								<td><input id="searchGisAssetsPrprtySeCd" type="text" class="ygpaCmmnCd" data-column-id="gisAssetsPrprtySeCd" data-code-id="GAM001" data-default-prompt="전체"/></td>
								<th>위치</th>
								<td><input id="searchGisAssetsLocCd" type="text" class="ygpaCmmnCd" data-column-id="gisAssetsLocCd" data-code-id="GAM002" data-default-prompt="전체"/></td>
								<th>부두</th>
								<td><input id="searchGisAssetsQuayCd" type="text" class="ygpaCmmnCd" data-column-id="gisAssetsQuayCd" data-code-id="GAM003" data-default-prompt="전체"/></td>
								<th>관리부서</th>
								<td>
									<input id="searchMngDeptCd" data-column-id="mngDeptCd" class="ygpaDeptSelect" />
								</td>
							</tr>
							<tr>
								<th>자산명</th>
								<td><input id="searchGisAssetsNm" data-column-id="gisAssetsNm" type="text" size="16"></td>
								<th>소재지</th>
								<td><input id="searchGisAssetsLocPlc" data-column-id="gisAssetsLocplc" type="text" size="20"></td>
								<th>지번</th>
								<td><input id="searchGisAssetsLnm" data-column-id="gisAssetsLnm" type="text" size="4">-<input id="searchGisAssetsLnmSub" data-column-id="gisAssetsLnmSub" type="text" size="3"></td>
								<th>운영부서</th>
								<td>
									<input id="searchOperDeptCd" data-column-id="operDeptCd" class="ygpaDeptSelect" />
								</td>
							</tr>
						</tbody>
					</table>
		</form>
	</div>

	<div class="emdPanel fillHeight">
		<div id="assetCodeTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">GIS자산목록</a></li>
				<li><a href="#tabs2" class="emdTab">GIS자산정보상세</a></li>
				<li><a href="#tabs3" class="emdTab">자산사진</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage fillHeight" data-onactivate="onShowTab2Activate">
				<table id="assetCodeList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="loadMap">맵조회</button>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: scroll">
				<form id="editGisAssetCode" name="gisAssetCode">
				<table class="editForm">
					<tr>
						<th><span class="label">항구분</span></th>
						<td>
							<input id="gisAssetsPrtAtCode" class="ygpaCmmnCd" data-code-id='GAM019' data-column-label-id='prtAtCodeNm' data-display-code='P' disabled="disabled" />
						</td>
						<th><span class="label">자산코드</span></th>
						<td colspan="3"><input type="text" size="3"  id="gisAssetsCd" disabled="disabled">-<input type="text" size="2"  id="gisAssetsSubCd" disabled="disabled"></td>
					</tr>
					<!-- <tr>
						<th><span class="label">자산코드</span></th>
						<td colspan="5"><input type="text" size="3"  id="gisAssetsCd" disabled="disabled">-<input type="text" size="2"  id="gisAssetsSubCd" disabled="disabled"></td>
					</tr> -->
					<tr>
						<th><span class="label">ERP자산코드</span></th>
						<td><input type="text" size="1" id="erpAssetsCls" data-column-id="erpAssetsSeCd" disabled="disabled" >-<input type="text" size="8" id="erpAssetsNo" disabled="disabled" >-<input type="text" size="2" id="erpAssetsNoSeq" disabled="disabled">
						</td>
						<th><span class="label">ERP자산명</span></th>
						<td colspan="5"><input type="text" size="40" id="itemName" data-column-id="itemName" disabled="disabled" ></td>
					</tr>
					<tr>
						<th><span class="label">재산구분</span></th>
						<td>
								<input id="gisAssetsPrprtySeCd" class="ygpaCmmnCd" data-code-id='GAM001' disabled="disabled">
						</td>
						<th><span class="label">위치구분</span></th>
						<td>
							<input id="gisAssetsLocCd" class="ygpaCmmnCd" data-code-id='GAM002' disabled="disabled">
						</td>
						<th><span class="label">부두구분</span></th>
						<td>
							<input id="gisAssetsQuayCd" class="ygpaCmmnCd" data-code-id='GAM003' disabled="disabled">
						</td>
					</tr>
					<tr>
						<th><span class="label">자산명</span></th>
						<td colspan="5"><input type="text" size="80" id="gisAssetsNm" disabled="disabled"></td>
					</tr>
					<tr>
						<th><span class="label">자산소재지</span></th>
						<td colspan="3"><input type="text" size="80" id="gisAssetsLocplc" disabled="disabled"><input type="hidden" id="gisAssetsBupjungdongCd" /></td>
						<th><span class="label">지번</span></th>
						<td><input type="text" size="4" id="gisAssetsLnm" disabled="disabled">-<input type="text" size="3" id="gisAssetsLnmSub" disabled="disabled"></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<th><span class="label">자산구분</span></th>
						<td colspan="3">
							<input id="gisAssetsSeCd" class="ygpaCmmnCd" data-code-id='GAM013' disabled="disabled">
						</td>
						<th><span class="label">취득가액</span></th>
						<td><input type="text" size="16" id="gisAssetsAcqPri" class="ygpaCurrency" class="ygpaCurrency" disabled="disabled"> 원</td>
					</tr>
					<tr>
						<th><span class="label">면적</span></th>
						<td><input type="text" size="8" class="ygpaNumber" id="gisAssetsAr" data-column-id="gisAssetsAr" data-decimal-point="2" disabled="disabled"> m^2</td>
						<th><span class="label">실제 임대 면적</span></th>
						<td><input type="text" size="8" id="gisAssetsRealRentAr" class="ygpaCurrency" disabled="disabled"> m^2</td>
						<th><span class="label">자산규격</span></th>
						<td><input type="text" size="20" id="gisAssetsStndrd" disabled="disabled"></td>
					</tr>
					<tr>
						<th><span class="label">관리부서</span></th>
						<td><input type="text" size="16" id="gisAssetsMngDeptCd" class="ygpaDeptSelect"  disabled="disabled"></td>
						<th><span class="label">운영부서</span></th>
						<td><input type="text" size="16" id="gisAssetsOperDeptCd" class="ygpaDeptSelect"  disabled="disabled"></td>
						<th><span class="label">사용여부</span></th>
						<td>
							<select id="gisAssetsUsageYn"  disabled="disabled">
									<option value="Y">사용</option>
									<option value="N">사용안함</option>
							</select>
						</td>
					</tr>
					<tr>
						<th><span class="label">자산 가치 금액</span></th>
						<td><input type="text" size="16" id="gisAssetsValAmt" class="ygpaCurrency" disabled="disabled"> 원 (조회일자 : <input type="text" size="16" id="gisAssetsValInqireDt" disabled="disabled">)</td>
						<th><span class="label">준공년도</span></th>
						<td><input type="text" size="4" id="gisAssetsBlddate" disabled="disabled"></td>
						<th><span class="label">준공일자</span></th>
						<td><input type="text" size="16" id="gisAssetsBldDt" disabled="disabled"></td>
					</tr>
					<tr>
						<th><span class="label">비고</span></th>
						<td colspan="5"><textarea cols="133" rows="3" id="gisAssetsRm" disabled="disabled"></textarea></td>
					</tr>
					<!-- <tr>
						<th><span class="label">사용여부</span></th>
						<td colspan="5">
							<select id="gisAssetsUsageYn">
									<option value="" selected="selected">선택</option>
									<option value="Y">사용</option>
									<option value="N">사용안함</option>
							</select>
						</td>
					</tr> -->
				</table>
				<div style="vertical-align: bottom; text-align: right;">
					<button id="btnAddGisMap">위치조회</button>
				</div>
				</form>
							</div>
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;" data-onactivate="onShowTab3Activate">
				<table id="assetCodePhotoList" style="display:none"></table>
				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
				<div class="emdControlPanel">
					<button id="btnDownloadFile">다운로드</button>
				</div>
			</div>
		</div>
	</div>
</div>
