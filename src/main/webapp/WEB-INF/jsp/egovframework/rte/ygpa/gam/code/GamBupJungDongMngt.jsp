<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamBupJungDongMngt.jsp
  * @Description : 법정동 코드조회 테스트 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.03.11  장은성          최초 생성
  *
  * author 장은성
  * since 2014.03.11
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamBupJungDongModule() {}

GamBupJungDongModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamBupJungDongModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#addrList").flexigrid({
		module: this,
		url: '<c:url value="/code/selectBupJungDongCodeList.do"/>',
		dataType: 'json',
		colModel : [
			{display:'선택', name:'regYn', width:50, sortable:true, align:'left', displayFormat: 'checkbox'},
			{display:'법정동 코드', name:'bupjungdongCd', width:200, sortable:true, align:'center'},
			{display:'법정동 명', name:'bupjungdongNm', width:480, sortable:true, align:'center'}
			],
		height: 'auto',
	});

	this.$("#addrList").on('onItemSelected', function(event, module, row, grid, param) {
		module._selectedItem=row;
		//module.$('#showBupjungdongLocation').attr('disabled', 'disabled');
		//alert('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is selected');
	});

	this.$("#addrList").on('onItemUnSelected', function(event, module, row, grid, param) {
		module._selectedItem=null;
		//module.$('#showBupjungdongLocation').removeAttr('disabled');
		//alert('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is unselected');
	});

	/*
	this.$("#erpAssetCodeList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#assetManageTab").tabs("option", {active: 1});	// 탭을 전환 한다.
		module.$('#searchGisErpAssetCls').val(row['assetCls']);
		module.$('#searchGisErpAssetNo').val(row['assetNo']);
		module.$('#searchGisErpAssetNoSeq').val(row['assetNoSeq']);
		// 해당하는 자산 목록을 불러온다/
		var searchOpt=module.makeFormArgs('#searchForm');
		//this.showAlert(searchOpt);
	 	module.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
	});
 */
};

// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
GamBupJungDongModule.prototype.showModuleAlert = function(msg) {
	//this.getSelect(msg);
	this.$('#prtCode').val(msg);
};

GamBupJungDongModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'selectBupJungDongCodeList':
		this.loadData();
	 	throw 0;
		break;
	case 'showBupjungdongLocation':
		if(this._selectedItem==null) {
			alert('먼저 법정동 코드를 선택 하세요');
			return;
		}
		this.showBupJungDongCodeLocation(this._selectedItem['bupjungdongCd']);
		/*

	case 'selectGisAssetCode':
		var searchOpt=this.makeFormArgs('#searchGisAssetCode');
	 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
		break;
	case 'addAssetGisCd':	// gis 자산 추가
		var row = this.$('#erpAssetCodeList').selectedRows();
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
		this.$('#editGisAssetCode').find(':input').val('');
		this.$('#erpAssetsCls').val(this.$('#searchGisErpAssetCls').val());
		this.$('#erpAssetsNo').val(this.$('#searchGisErpAssetNo').val());
		this.$('#erpAssetsNoSeq').val(this.$('#searchGisErpAssetNoSeq').val());
		this.$('#erpAssetsCls').attr('readonly', true);
		this.$('#erpAssetsNo').attr('readonly', true);
		this.$('#erpAssetsNoSeq').attr('readonly', true);
		this.$('#gisAssetsPrtAtCode').focus();
		this._editData=this.getFormValues('#editGisAssetCode', {_updtId:'I'});
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
		}
		break;
	case 'btnSaveGisAssetsCode':
		// 변경된 자료를 저장한다.
		var inputVO=[{name: 'test', value:'test hello'}];
 		inputVO[inputVO.length]={name: 'updateList', value :JSON.stringify(this.$('#assetCodeList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
		inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#assetCodeList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
		inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataList) };
		console.log(inputVO);
		// 데이터를 저장 하고 난 뒤 리스트를 다시 로딩 한다.

	 	this.doAction('<c:url value="/sample/mergeAssetCodeList.do" />', inputVO, function(result) {
	 		if(result.resultCode == 0){
	 			this.$('#assetCodeList').flexReload();
	 		}
	 		alert(result.resultMsg);
 		});
		break;
	case 'btnApplyGisAssetsCode':
		this._editData=this.getFormValues('#editGisAssetCode', this._editData);
		if(this._editData._updtId==undefined || this._editData._updtId!='I') {
			this._editData._updtId='U';
			this.$('#assetCodeList').flexUpdateRow(this._editRow, this._editData);
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
		break; */
	}
};

GamBupJungDongModule.prototype.onTabChange = function(newTabId, oldTabId) {
	/* switch(newTabId) {
	case 'tabs1':
		this.$('#searchViewStack')[0].changePanelId(0);
		break;
	case 'tabs2':
		this.$('#searchViewStack')[0].changePanelId(1);
		this._deleteDataList=[];	// 삭제 리스트 초기화
		break;
	case 'tabs3':
		this.$('#searchViewStack')[0].changePanelId(1);
		break;
	} */
};

GamBupJungDongModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
};

GamBupJungDongModule.prototype.loadData = function() {
	if(this.$('#searchBupjungdongNm').val().length<2 && this.$('#searchBupjungdongCd').val().length<4) {
		alert('법정동 코드를 4자리 이상 입력 하거나 검색할 주소를 두자 이상 입력 하세요');
		this.$('#searchBupjungdongNm').addClass('ui-state-error');
		this.$('#searchBupjungdongCd').addClass('ui-state-error');
		return;
	}
	else {
		this.$('#searchBupjungdongNm').removeClass('ui-state-error');
		this.$('#searchBupjungdongCd').removeClass('ui-state-error');
	}
	var searchOpt=this.makeFormArgs('#searchBupjungDong');
 	this.$('#addrList').flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamBupJungDongModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId }'/>
<div class="window_main">
	<div class="emdPanel">
		<form id="searchBupjungDong">
			<table style="width:100%;" class="searchPanel">
				<tbody>
					<tr>
						<th>검색 주소</th>
						<td>
							<input id="searchBupjungdongNm" type="text" size="20" />
						</td>
						<th>법정동 코드</th>
						<td>
							<input id="searchBupjungdongCd" type="text" size="20" />
						</td>
						<td><button id="selectBupJungDongCodeList" class="submit">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="emdPanel fillHeight">
		<table id="addrList" style="display:none" class="fillHeight"></table>
			<div class="emdControlPanel">
				<form id="gotoArea">
			<table style="width:300px;" class="searchPanel">
				<tbody>
					<tr>
						<th>지번</th>
						<td>
							<input id="landCd" type="text" size="4" />
							-
							<input id="landSubCd" type="text" size="3" />
						</td>
						<td><button id="showBupjungdongLocation" class="submit">위치 조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
			</div>
	</div>
</div>
