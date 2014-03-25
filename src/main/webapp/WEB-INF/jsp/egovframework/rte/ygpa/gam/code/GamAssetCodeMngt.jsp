<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetCodeModule() {}

GamAssetCodeModule.prototype = new EmdModule(830, 700);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetCodeModule.prototype.loadComplete = function() {
	this.$('#prtAtCode').val('620');	// 기본 항코드 설정

	this.$("#assetCodeList").flexigrid({
		module: this,
		url: '<c:url value="/asset/selectGisAssetCodeList.do"/>',
		colModel : [
			{display:'항코드', name:'gisAssetsPrtAtCode', width:24, sortable:true, align:'center'},
			{display:'항구분', name:'prtAtCodeNm', width:40, sortable:true, align:'center'},
			{display:'코드', name:'gisAssetsCd', width:24, sortable:true, align:'center'},
			{display:'SUB 코드', name:'gisAssetsSubCd', width:16, sortable:true, align:'center'},
			{display:'자산명', name:'gisAssetsNm', width:240, sortable:true, align:'center'},
			{display:'관리 부서 코드', name:'gisAssetsMngDeptCd', width:160, sortable:true, align:'center'},
			{display:'운영 부서 코드', name:'gisAssetsOperDeptCd', width:160, sortable:true, align:'center'},
			{display:'소재지', name:'gisAssetsLocplc', width:240, sortable:true, align:'left'},
			{display:'지번', name:'gisAssetsLnm', width:40, sortable:true, align:'center'},
			{display:'지번SUB', name:'gisAssetsLnmSub', width:40, sortable:true, align:'center'},
			{display:'면적', name:'gisAssetsAr', width:64, sortable:true, align:'center'},
			{display:'사용 여부', name:'gisAssetsUsageYn', width:8, sortable:true, align:'center'},
			{display:'취득가액', name:'gisAssetsAcqPri', width:104, sortable:true, align:'right'},
			{display:'규격', name:'gisAssetsStndrd', width:120, sortable:true, align:'center'},
			{display:'준공년도', name:'gisAssetsBlddate', width:32, sortable:true, align:'center'},
			{display:'준공 일자', name:'gisAssetsBldDt', width:128, sortable:true, align:'center'},
			{display:'등록자', name:'regUsr', width:160, sortable:true, align:'center'},
			{display:'등록일자', name:'registdt', width:128, sortable:true, align:'center'},
			{display:'수정자', name:'updUsr', width:160, sortable:true, align:'center'},
			{display:'수정일자', name:'updtdt', width:128, sortable:true, align:'center'},
			{display:'부두 그룹 코드', name:'gisAssetsQuayGroupCd', width:80, sortable:true, align:'center'},
			{display:'위치 코드', name:'gisAssetsLocCd', width:80, sortable:true, align:'center'},
			{display:'구분 코드', name:'gisAssetsSeCd', width:80, sortable:true, align:'center'},
			{display:'재산 구분 코드', name:'gisAssetsPrprtySeCd', width:80, sortable:true, align:'center'},
			{display:'출자 방식', name:'gisAssetsInvstmntMthd', width:80, sortable:true, align:'center'},
			{display:'실제 임대 면적', name:'gisAssetsRealRentAr', width:64, sortable:true, align:'center'},
			{display:'도면 목록 등록 년도', name:'drwLstRegistYear', width:32, sortable:true, align:'center'},
			{display:'도면 목록 순번', name:'drwLstSeq', width:32, sortable:true, align:'center'},
			{display:'가치 금액', name:'gisAssetsValAmt', width:120, sortable:true, align:'center'},
			{display:'가치 조회 일자', name:'gisAssetsValInqireDt', width:64, sortable:true, align:'center'}
			],
		height: 'auto'
	});

	this.$("#assetCodeList").on('onItemSelected', function(event, module, row, grid, param) {
		//module.$('#editGisAssetCode :input').val('');	// 이전에 선택된 값을 지운다. 2014-03-18 추가
		//module.$('#btnApplyGisAssetsCode').prop('disabled', false);
		module.makeFormValues('#editGisAssetCode', row);
		module._editData=module.getFormValues('#editGisAssetCode', row);
		module._editRow=module.$('#assetCodeList').selectedRowIds()[0];

		console.log('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is selected');
	});

	this.$("#assetCodeList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		//module.$('#editGisAssetCode :input').val('');	// 이전에 선택된 값을 지운다. 2014-03-18 추가 (다시 삭제 탭이 변경 될때 지운다.)
		//module.$('#btnApplyGisAssetsCode').prop('disabled', false);
		module.makeFormValues('#editGisAssetCode', row);
		module._editData=module.getFormValues('#editGisAssetCode', row);
		module._editRow=module.$('#assetCodeList').selectedRowIds()[0];

		module.$()
		console.log('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is selected');
	});

	this.$("#assetCodePhotoList").flexigrid({
		module: this,
		dataType: 'json',
		colModel : [
				{display:'사진 순번', name:'photoSeq', width:80, sortable:true, align:'center'},
				{display:'사진 제목', name:'photoSj', width:300, sortable:true, align:'center'},
				{display:'파일명', name:'filenmLogical', width:200, sortable:true, align:'left'},
				{display:'촬영 일시', name:'shotDt', width:120, sortable:true, align:'center'},
				{display:'등록자', name:'regUse', width:160, sortable:true, align:'center'}
			],
		height: '120'
	});

	this.$("#assetCodePhotoList").on('onItemSelected', function(event, module, row, grid, param) {
		module.makeFormValues('#editAssetGisPhotoForm', row);
		module._editPhotoData=module.getFormValues('#editAssetGisPhotoForm', row);
		module._editPhotoRow=module.$('#assetCodePhotoList').selectedRowIds()[0];

		if(row.filenmPhyicl!=null || row.filenmPhyicl!='') {
			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm=row['filenmPhyicl'];
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

	// 사진 정보 속성이 변경 된 경우 이벤트 실행
	this.$('.photoEditItem').on('change', {module: this}, function(event) {
		var m = event.data.module;
		if(m._editPhotoRow==null) return;

		if(m._editPhotoData==null) return;

		if(m._editPhotoData._updt==null || m._editPhotoData._updt=='') {
			 m._editPhotoData._updt='U';
		}
		else {
			m._editPhotoData._updt='I';
		}

		if(m.$('#photoSj')==event.target) {	// 제목 변경
			m._editPhotoData.photoSj = $(event.target).val();
		}
		else {	// 날짜 시간 변경
			var dtStr = m.$('#shotDate').val()+' '+m.$('#shotTime').val();
			m._editPhotoData.shotDt = dtStr;
		}

	});
};

// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
GamAssetCodeModule.prototype.showModuleAlert = function(msg) {
	//this.getSelect(msg);
	this.$('#prtCode').val(msg);
};

GamAssetCodeModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'selectGisAssetCode':
		var searchOpt=this.makeFormArgs('#searchGisAssetCode');
	 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
	 	throw 0;
		break;
	case 'addAssetGisCd':	// gis 자산 추가
		this.$("#assetManageTab").tabs("option", {active: 1});	// 탭을 전환 한다.
		if(row.length>0) {
			this.$('#searchGisErpAssetCls').val(row[0]['assetCls']);
			this.$('#searchGisErpAssetNo').val(row[0]['assetNo']);
			this.$('#searchGisErpAssetNoSeq').val(row[0]['assetNoSeq']);
			// 해당하는 자산 목록을 불러온다/
			var searchOpt=this.makeFormArgs('#searchForm');
			//this.showAlert(searchOpt);
		 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
		}
		else {
			this.$('#searchGisErpAssetCls').val('');
			this.$('#searchGisErpAssetNo').val('');
			this.$('#searchGisErpAssetNoSeq').val('');
			alert('신규 자산을 등록 합니다.');
		}
		break;
	case 'addAssetGisCdItem':
		this._editData = {_updtId: 'I'};
		this._editRow=this.$('#assetCodeList').flexGetData().length;
		this.$('#btnApplyGisAssetCode').removeAttr('disabled');
		break;
	case 'removeAssetGisCdItem':
		if(this.$('#assetCodeList').selectedRowIds().length>0) {
			for(var i=this.$('#assetCodeList').selectedRowIds().length-1; i>=0; i--) {
				var row=this.$('#assetCodeList').flexGetRow(this.$('#assetCodeList').selectedRowIds()[i]);
				if(row._updtId==undefined || row._updtId!='I')  this._deleteDataList[this._deleteDataList.length]=row;	// 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
				this.$('#assetCodeList').flexRemoveRow(this.$('#assetCodeList').selectedRowIds()[i]);
			}
			this._editData=null;
		}
		break;
	case 'btnSaveGisAssetsCode':
		// 변경된 자료를 저장한다.
		var inputVO={};
		inputVO.updateList = this.$('#assetCodeList').selectFilterData([{col: '_updtId', filter: 'U'}]);
		inputVO.insertList = this.$('#assetCodeList').selectFilterData([{col: '_updtId', filter: 'I'}]);
		inputVO.deleteList = this._deleteDataList;
		console.log(inputVO);
		// 데이터를 저장 하고 난 뒤 리스트를 다시 로딩 한다.
		break;
	case 'btnApplyGisAssetsCode':
		this._editData=this.getFormValues('#editGisAssetCode', this._editData);
		if(this._editRow!=null) {	// 이전에 _updtId 로 선택 한 것을 _editRow 로 변경 2014-03-14.001
			if(this._editData._updtId!='I') this._editData._updtId='U';	// 삽입된 데이터가 아니면 업데이트 플래그를 추가한다.
			this.$('#assetCodeList').flexUpdateRow(this._editRow, this._editData);
			this._editRow=null;	// 편집 저장 하였으므로 로우 편집을 종료 한다.
		}
		else {
			this.$('#assetCodeList').flexAddRow(this._editData);
		}
		this.$('#editGisAssetCode').find(':input').val('');
//		this.$('#btnApplyGisAssetsCode').attr('disabled', 'disabled');
		break;
	case 'btnCancelGisAssetsCode':
		this.$('#editGisAssetCode').find(':input').val('');
		this.$('#btnApplyGisAssetCode').removeAttr('disabled');
		break;
	case 'removeAssetCdItem':
		break;
	case 'editAssetCd':
		break;
	case 'btnAddGisMap':
		if(this.$('#assetCodeList').selectedRowIds().length>0) {
			var row = this.$('#erpAssetCodeList').selectedRows();
			// test
			this.addGisAssetsCdMap('GAC', {'gisPrtAtCode': '620', 'gisAssetsCd': 'LNF', 'gisAssetsSubCd': '01'});
//			this.btnAddGisMap('GAC', {row.gisAssetsCd, row.gisAssetsSubCd});
		}
		break;

	case 'btnUploadFile':
		// 사진을 업로드하고 업로드한 사진 목록을 result에 어레이로 리턴한다.
		this.uploadFile('uploadPhoto', function(module, result) {
// 			var userid=EMD.util.getLoginUserVO().userNm; 임시
			var userid='admin';
			$.each(result, function(){
				module.$('#assetCodePhotoList').flexAddRow({photoSj: '', filenmLogical: this.logicalFileNm, filenmPhyicl: this.physcalFileNm, regUsr: userid, registDt:  EMD.util.getTimeStamp()});	// 업로드 파일명이 physcalFileNm (물리명), logicalFileNm (논리명)으로 리턴 된다.
			});
		}, '시설사진 업로드');
		break;

	case 'btnApplyPhotoData':
		var rownum;
		var row = {};
		row=module.getFormValues('#editAssetGisPhotoForm', row);
		rownum=module.$('#assetCodeList').selectedRowIds()[0];

		this.$('#assetCodePhotoList').flexUpdateRow(rownum, row);
		break;

	case 'popupFcltyCd':
        var opts;

        this.doExecuteDialog('selectSearchAssetsCdPopup', '시설 선택', '<c:url value="/popup/showAssetsCd.do"/>', opts);
		break;
	}
};

GamAssetRentMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     case 'selectEntrpsInfoPopup':
         if (msg != 'cancel') {
             this.$('#sEntrpscd').val(value.entrpscd);
             this.$('#sEntrpsNm').val(value.entrpsNm);
         } else {
             alert('취소 되었습니다');
         }
         break;
     case 'insertEntrpsInfoPopup':
         if (msg != 'cancel') {
             this.$('#entrpscd').val(value.entrpscd);
             this.$('#entrpsNm').val(value.entrpsNm);
         } else {
             alert('취소 되었습니다');
         }
         break;
     case 'insertAssetRentPrmisnPopup':
         if (msg != 'cancel') {
             if( value == "0" ) {
                 var searchOpt=this.makeFormArgs('#gamAssetRentMngtSearchForm');
                 this.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
             }
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
	switch(newTabId) {
	case 'tabs1':
//		this.$('#searchViewStack')[0].changePanelId(0);
		break;
	case 'tabs2':
//		this.$('#searchViewStack')[0].changePanelId(1);
		this._deleteDataList=[];	// 삭제 리스트 초기화
		if(this._editData!=null) {
			module.$('#editGisAssetCode :input').val('');	// 이전에 선택된 값을 지운다. 2014-03-18 추가
			module.makeFormValues('#editGisAssetCode', this._editData);
		}
		else {
			alert('자산 목록을 먼저 선택 하세요');
		}
		break;
	case 'tabs3':
		this._deleteDataList=[];	// 삭제 리스트 초기화
		if(this._editData!=null) {
			module.$('#editAssetGisPhotoForm :input').val('');	// 이전에 선택된 값을 지운다. 2014-03-18 추가
			module.makeFormValues('#editAssetGisPhotoForm', this._editData);
		}
		else {
			alert('자산 목록을 먼저 선택 하세요');
		}
//		this.$('#searchViewStack')[0].changePanelId(1);
		break;
	}
};

GamAssetCodeModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
};

GamAssetCodeModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#searchErpAssetCode');
	//this.showAlert(searchOpt);
 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
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
								<th>청코드</th>
								<td><input id="searchPrtAtCode" type="text" data-cmmn-cd="GAM019" class="ygpaCmmnCd" /></td>
								<th>ERP 자산코드</th>
								<td><input id="searchGisErpAssetCls" type="text" size="1">-<input id="searchGisErpAssetNo" type="text" size="4">-<input id="searchGisErpAssetNoSeq" type="text" size="2"></td>
								<th>자산코드</th>
								<td><input id="searchGisAssetCode" type="text" size="3">-<input
									id="searchGisAssetSubCode" type="text" size="2"><button id="popupFcltyCd" class="popupButton">시설조회</button></td>
								<th>재산코드</th>
								<td><select id="prprtyCd">
										<option value="" selected="selected">선택</option>
										<option value="A">건물</option>
										<option value="L">토지</option>
										<option value="S">공작물</option>
										<option value="w">창고</option>
										<option value="E">기타</option>
								</select></td>
								<td rowSpan="2"><button id="selectGisAssetCode" class="buttonSearch">조회</button></td>
							</tr>
							<tr>
								<th>자산명</th>
								<td colspan="3"><input id="assetNm" type="text" size="36"></td>
								<th>관리부서</th>
								<td>
									<input id="mngDeptCd" class="ygpaDeptSelect" />
								</td>
								<th>운영부서</th>
								<td>
								<input id="operDeptCd" class="ygpaDeptSelect" />
								</td>
							</tr>
						</tbody>
					</table>
		</form>
	</div>

	<div class="emdPanel fillHeight">
		<div id="assetManageTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">GIS자산목록</a></li>
				<li><a href="#tabs2" class="emdTab">GIS자산정보상세</a></li>
				<li><a href="#tabs3" class="emdTab">자산사진</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage fillHeight" data-onactivate="onShowTab2Activate">
				<table id="assetCodeList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="loadMap">지도보기</button>
					<button id="btnAddGisMap">위치등록</button>
					<button id="addAssetGisCdItem">자산추가</button>
					<button id="removeAssetGisCdItem">삭제</button>
					<button id="btnSaveGisAssetsCode">저장</button>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: scroll">
				<form id="editGisAssetCode" name="gisAssetCode">
				<table style="border: 1px black;">
					<tr>
						<th><span class="label">청코드</span></th>
						<td colspan="5">
							<input id="gisAssetsPrtAtCode" class="ygpaCmmnCd" data-code-id='GAM019' data-column-label-id='prtAtCodeNm'' data-display-code='P' />
						</td>
					</tr>
					<tr>
						<th><span class="label">자산코드</span></th>
						<td colspan="5"><input type="text" size="3"  id="gisAssetsCd" disabled="disabled">-<input type="text" size="2"  id="gisAssetsSubCd" disabled="disabled"></td>
					</tr>
					<tr>
						<th><span class="label">ERP자산코드</span></th>
						<td><input type="text" size="1" id="erpAssetsCls" data-column-id="erpAssetsSeCd">-<input type="text" size="8" id="erpAssetsNo">-<input type="text" size="2" id="erpAssetsNoSeq"></td>
						<th><span class="label">ERP자산명</span></th>
						<td colspan="5"><input type="text" size="40" id="itemName" data-column-id="itemName"></td>
					</tr>
					<tr>
						<th><span class="label">재산구분</span></th>
						<td>
								<input id="gisAssetsPrtPtySeCd" class="ygpaCmmnCd" data-code-id='GAM001'>
						</td>
						<th><span class="label">위치구분</span></th>
						<td>
							<input id="gisAssetsLocCd" class="ygpaCmmnCd" data-code-id='GAM002'>
						</td>
						<th><span class="label">부두구분</span></th>
						<td>
							<input id="gisAssetsQuayCd" class="ygpaCmmnCd" data-code-id='GAM003'>
						</td>
					</tr>
					<tr>
						<th><span class="label">자산명</span></th>
						<td colspan="5"><input type="text" size="80" id="gisAssetsNm"></td>
					</tr>
					<tr>
						<th><span class="label">자산소재지</span></th>
						<td colspan="3"><input type="text" size="60" id="gisAssetsLocplc"></td>
						<th><span class="label">지번</span></th>
						<td><input type="text" size="4" id="gisAssetsLnm">-<input type="text" size="3" id="GisAssetsLnmSub"></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<th><span class="label">자산구분</span></th>
						<td>
							<input id="gisAssetsSeCd" class="ygpaCmmnCd" data-code-id='GAM013'>
						</td>
						<th><span class="label">출자 방식</span></th>
						<td>
							<select id="gisAssetsInvstmntMthd">
									<option value="" selected="selected">선택</option>
									<c:forEach  items="${assetsInvstmntMthdList}" var="assetsInvstmntMthd">
										<option value="${assetsInvstmntMthd.codeId }">${assetsInvstmntMthd.codeNm }</option>
									</c:forEach>
							</select>
						</td>
						<th><span class="label">취득가액</span></th>
						<td><input type="text" size="16" id="gisAssetsAcqPri" class="ygpaCurrency" class="ygpaCurrency"> 원</td>
					</tr>
					<tr>
						<th><span class="label">면적</span></th>
						<td><input type="text" size="8" class="ygpaNumber" id="gisAssetsAr" data-column-id="gisAssetsAr" data-decimal-point="2"> m^2</td>
						<th><span class="label">실제 임대 면적</span></th>
						<td><input type="text" size="8" id="gisAssetsRealRentAr" class="ygpaCurrency"> m^2</td>
						<th><span class="label">자산규격</span></th>
						<td><input type="text" size="20" id="gisAssetsStndrd"></td>
					</tr>
					<tr>
						<th><span class="label">관리부서</span></th>
						<td colspan="2"><input type="text" size="16" id="gisAssetsMngDeptCd" class="ygpaDeptSelect" data-default-prompt="없음"></td>
						<th><span class="label">운영부서</span></th>
						<td colspan="2"><input type="text" size="16" id="gisAssetsOperDeptCd" class="ygpaDeptSelect" data-default-prompt="없음"></td>
					</tr>
					<tr>
						<th><span class="label">자산 가치 금액</span></th>
						<td><input type="text" size="16" id="gisAssetsValAmt" class="ygpaCurrency"> 원 (조회일자 : <input type="text" size="16" class="emdcal" id="gisAssetsValInqireDt">)</td>
						<th><span class="label">준공년도</span></th>
						<td><input type="text" size="4" id="gisAssetsBlddate"></td>
						<th><span class="label">준공일자</span></th>
						<td><input type="text" size="16" class="emdcal" id="gisAssetsBldDt"></td>
					</tr>
					<tr>
						<th><span class="label">비고</span></th>
						<td colspan="5"><textarea cols="60" rows="4" id="gisAssetsRm"></textarea></td>
					</tr>
					<tr>
						<th><span class="label">사용여부</span></th>
						<td>
							<select id="gisAssetsUsageYn">
									<option value="" selected="selected">선택</option>
									<option value="Y">사용</option>
									<option value="N">사용안함</option>
							</select>
						</td>
						<th><span class="label">등록자</span></th>
						<td><input type="text" size="20" disabled="disabled" id="regUsr"></td>
						<th><span class="label">등록일</span></th>
						<td><input type="text" class="emdcal" size="16" disabled="disabled" id="registdt" ></td>
					</tr>
				</table>
				<div style="vertical-align: bottom; text-align: right;">
					<button id="btnCancelGisAssetsCode">취소</button>
					<button id="btnApplyGisAssetsCode">적용</button>
				</div>
				</form>
							</div>
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;" data-onactivate="onShowTab3Activate">
				<table id="assetCodePhotoList" style="display:none"></table>
				<div class="emdControlPanel"><button id="btnUploadFile">업로드</button><button id="btnDownloadFile">다운로드</button><button id="removeAssetGisPhoto">삭제</button></div>
				<form id="editAssetGisPhotoForm">
					<table>
						<tr>
							<th><span class="label">제 목</span></th>
							<td>
								<input id="photoSj" type="text" size="60" class="photoEditItem" />
							</td>
						</tr>
						<tr>
							<th><span class="label">촬영일자</span></th>
							<td>
                            	<input id="shotDate" type="text" size="10"  class="emdcal photoEditItem">&nbsp;<input id="shotTime" type="text" size="5"  class="photoEditItem emdTime"/>
							</td>
						</tr>
					</table>
				</form>
   					<button id="btnApplyPhotoData">적용</button>
				<div class="emdPanel fillHeight" style="overflow:scroll"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>
