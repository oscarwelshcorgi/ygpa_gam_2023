<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
  /**
  * @Class Name : GamAssetCodeMngt.jsp
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
<validator:javascript formName="gamAssetCode" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetPhoto" staticJavascript="false" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetCodeModule() {}

GamAssetCodeModule.prototype = new EmdModule(915, 700);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetCodeModule.prototype.loadComplete = function() {
	this.$('#prtAtCode').val('620');	// 기본 항코드 설정s

	// 테이블 설정
	this.$("#erpAssetCodeList").flexigrid({
		module: this,
		url: '<c:url value="/asset/selectErpAssetCodeList.do"/>',
		dataType: 'json',
		colModel : [
			{display:'등록', name:'regYn', width:44, sortable:true, align:'left', displayFormat: 'checkbox'},
			{display:'ERP자산코드', name:'erpAssetCode', width:80, sortable:true, align:'left'},
			{display:'자산관리번호', name:'assetMngtNo', width:80, sortable:true, align:'right'},
			{display:'품목', name:'itemCls', width:32, sortable:true, align:'center'},
			{display:'품명', name:'itemNameAsset', width:200, sortable:true, align:'center'},
			{display:'취득일자', name:'buyDate', width:64, sortable:true, align:'center'},
			{display:'취득수량', name:'buyQty', width:128, sortable:true, align:'right', displayFormat: 'number'},
			{display:'취득단가', name:'buyPrice', width:128, sortable:true, align:'right', displayFormat: 'number'},
			{display:'취득구분', name:'buyCls', width:40, sortable:true, align:'center'},
			{display:'구매구분', name:'purCls', width:40, sortable:true, align:'center'},
			{display:'구매용도', name:'purPurpose', width:200, sortable:true, align:'center'},
			{display:'구입처', name:'purCust', width:200, sortable:true, align:'center'},
			{display:'회계단위구분', name:'accUnitCls', width:16, sortable:true, align:'center'},
			{display:'프로젝트코드', name:'projectCd', width:64, sortable:true, align:'center'},
			{display:'장소코드', name:'placeCd', width:32, sortable:true, align:'center'},
			{display:'부서코드', name:'deptCd', width:64, sortable:true, align:'center'},
			{display:'사원번호', name:'empNo', width:80, sortable:true, align:'center'},
			{display:'모델명', name:'modelName', width:240, sortable:true, align:'center'},
			{display:'자산규격', name:'assetSize', width:200, sortable:true, align:'center'},
			{display:'제품일련번호', name:'productSeqNo', width:200, sortable:true, align:'center'},
			{display:'제조업체명', name:'makerName', width:200, sortable:true, align:'center'},
			{display:'계정코드', name:'accntCd', width:64, sortable:true, align:'center'},
			{display:'상각구분', name:'deprctnCls', width:8, sortable:true, align:'center'},
			{display:'자산내용년수', name:'assetFixTerm', width:128, sortable:true, align:'center'},
			{display:'변동구분', name:'changeCls', width:8, sortable:true, align:'center'}
			],
		height: 'auto',
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				this.erpAssetCode=this.assetCls+'-'+this.assetNo+'-'+this.assetNoSeq;
			});
			return data;
		}
	});

	this.$("#erpAssetCodeList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		if(row['assetCls']==null || row['assetCls'].length==0) {
			alert('자산코드에 오류가 있습니다.');
			return;
		}
		if(row['assetNo']==null || row['assetNo'].length==0) {
			alert('자산코드에 오류가 있습니다.');
			return;
		}
		if(row['assetNoSeq']==null || row['assetNoSeq'].length==0) {
			alert('자산코드에 오류가 있습니다.');
			return;
		}

		this.deleteList = [];	// 삭제 목록 초기화
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#assetManageTab").tabs("option", {active: 1});	// 탭을 전환 한다.
		module.$('#searchErpAssetsCls').val(row['assetCls']);
		module.$('#searchErpAssetsNo').val(row['assetNo']);
		module.$('#searchErpAssetsNoSeq').val(row['assetNoSeq']);
		module._itemNameAsset = row['itemNameAsset'];// 자산명 저장
		// 해당하는 자산 목록을 불러온다/
		var searchOpt=module.makeFormArgs('#searchGisAssetCode');
		//this.showAlert(searchOpt);
	 	module.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
	});

	this.$("#erpAssetCodeList").on('onItemSelected', function(event, module, row, grid, param) {
		//module.$('#addAssetGisCd').attr('disabled', 'disabled');
		//alert('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is selected');
	});

	this.$("#erpAssetCodeList").on('onItemUnSelected', function(event, module, row, grid, param) {
//		module.$('#addAssetGisCd').removeAttr('disabled');
		//alert('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is unselected');
	});

	this.$("#assetCodeList").flexigrid({
		module: this,
		url: '<c:url value="/asset/selectGisAssetCodeList.do"/>',
		colModel : [
			{display:'항코드', name:'gisAssetsPrtAtCode', width:24, sortable:true, align:'center'},
			{display:'항구분', name:'prtAtCodeNm', width:40, sortable:true, align:'center'},
			{display:'코드', name:'gisAssetsCd', width:24, sortable:true, align:'center'},
			{display:'SUB 코드', name:'gisAssetsSubCd', width:16, sortable:true, align:'center'},
			{display:'자산명', name:'gisAssetsNm', width:240, sortable:true, align:'center'},
			{display:'관리 부서', name:'gisAssetsMngDeptCdNm', width:160, sortable:true, align:'center'},
			{display:'운영 부서', name:'gisAssetsOperDeptCdNm', width:160, sortable:true, align:'center'},
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
			{display:'도면 목록 순번', name:'drwLstSeq', width:32, sortable:true, align:'center'}
			],
		height: '140',
		preProcess: function(module, data) {
			module._deleteDataList=[];	// 삭제 리스트 초기화
			return data;	// 그대로 리턴 한다. 데이터 변경 없음
		}
	});

	this.$("#assetCodeList").on('onItemSelected', function(event, module, row, grid, param) {
		module.$('#editGisAssetCode :input').val('');	// 이전에 선택된 값을 지운다. 2014-03-18 추가
		//module.$('#btnApplyGisAssetsCode').prop('disabled', false);
		module.makeFormValues('#editGisAssetCode', row);
		module._editData=module.getFormValues('#editGisAssetCode', row);
		module._editRow=module.$('#assetCodeList').selectedRowIds()[0];

		console.log('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is selected');
	});

	this.$("#assetCodeList").on('onItemUnSelected', function(event, module, row, grid, param) {
		module.$('#editGisAssetCode :input').val('');	// 이전에 선택된 값을 지운다. 2014-03-18 추가
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

GamAssetCodeModule.prototype.addGisAssetItem = function() {
	this.$('#editGisAssetCode').find(':input').val('');
	this.$('#erpAssetsCls').val(this.$('#searchErpAssetsCls').val());
	this.$('#erpAssetsNo').val(this.$('#searchErpAssetsNo').val());
	this.$('#erpAssetsNoSeq').val(this.$('#searchErpAssetsNoSeq').val());
	this.$('#erpAssetsCls').attr('readonly', true);
	this.$('#erpAssetsNo').attr('readonly', true);
	this.$('#erpAssetsNoSeq').attr('readonly', true);

	this.$('#itemName').val(this._itemNameAsset);
	this.$('#itemName').attr('readonly', true);

	this.$('#gisAssetsPrtAtCode').focus();
	this._editData=this.getFormValues('#editGisAssetCode', {_updtId:'I'});
	this._editRow=null;
	// this.$('#btnApplyGisAssetCode').removeAttr('disabled');
};

GamAssetCodeModule.prototype.removeGisAssetItem = function() {
	if(this.$('#assetCodeList').selectedRowIds().length>0) {
		for(var i=this.$('#assetCodeList').selectedRowIds().length-1; i>=0; i--) {
			var row=this.$('#assetCodeList').flexGetRow(this.$('#assetCodeList').selectedRowIds()[i]);
			if(row._updtId==undefined || row._updtId!='I')  this._deleteDataList[this._deleteDataList.length]=row;	// 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			this.$('#assetCodeList').flexRemoveRow(this.$('#assetCodeList').selectedRowIds()[i]);
		}
	}
};

GamAssetCodeModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'selectErpAssetCode':
		/* if(this.$('#searchAssetCls').val()=='') {
			alert('자산구분을 선택 하세요');
			this.$('#searchAssetCls').addClass('ui-state-error');
			return;
		}
		else {
			this.$('#searchAssetCls').removeClass('ui-state-error');
		} */
		var searchOpt=this.makeFormArgs('#searchErpAssetCode');
	 	this.$('#erpAssetCodeList').flexOptions({params:searchOpt}).flexReload();
	 	throw 0;
		break;

	case 'selectGisAssetCode':
		if(this.$('#searchPrtAtCode').val()=='') {
			alert('항구분을 선택 하세요.');
			this.$('#searchPrtAtCode').addClass('ui-state-error');
			this.$('#searchPrtAtCode').focus();
		}
		else {
			this.$('#searchPrtAtCode').removeClass('ui-state-error');
		}
		if(this.$('#searchGisErpAssetCls').val()=='' || this.$('#searchGisErpAssetNo').val()=='' ) {
			alert('ERP 자산코드를 입력하거나 이전 탭에서 선택 하세요.');
			this.$('#searchGisErpAssetCls').addClass('ui-state-error');
			this.$('#searchGisErpAssetNo').addClass('ui-state-error');
			this.$('#searchGisErpAssetNoSeq').addClass('ui-state-error');
			this.$('#searchGisErpAssetCls').focus();
		}
		else {
			this.$('#searchGisErpAssetCls').removeClass('ui-state-error');
			this.$('#searchGisErpAssetNo').removeClass('ui-state-error');
			this.$('#searchGisErpAssetNoSeq').removeClass('ui-state-error');
		}
		var searchOpt=this.makeFormArgs('#searchGisAssetCode');
	 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
	 	throw 0;
		break;
	case 'addAssetGisCd':	// gis 자산 추가
		if(this.$('#erpAssetCodeList').selectedRowIds()>0) {
			var row = this.$('#erpAssetCodeList').selectedRows()[0];
			this.$("#assetManageTab").tabs("option", {active: 1});	// 탭을 전환 한다.
			this.$('#searchGisErpAssetCls').val(row['assetCls']);
			this.$('#searchGisErpAssetNo').val(row['assetNo']);
			this.$('#searchGisErpAssetNoSeq').val(row['assetNoSeq']);
			// 해당하는 자산 목록을 불러온다/
			var searchOpt=this.makeFormArgs('#searchForm');
			//this.showAlert(searchOpt);
		 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
			this._itemNameAsset = row['itemNameAsset'];// 자산명 저장
		 	this.addGisAssetItem();
		}
		else {
			alert('추가할 자산의 ERP 코드를 선택 하세요');
		}
		break;
	case 'addAssetGisCdItem':
		this.addGisAssetItem();
		break;
	case 'removeAssetGisCdItem':
		this.removeGisAssetItem();
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
		if(!validateGamAssetCode(this.$('#editGisAssetCode')[0])) {
			return;
		}
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
		// this.$('#btnApplyGisAssetCode').removeAttr('disabled');
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
		if(!validateGamAssetPhoto(this.$('#assetCodePhotoList')[0])) {
			return;
		}
		var rownum;
		var row = {};
		row=module.getFormValues('#editAssetGisPhotoForm', row);
		rownum=module.$('#assetCodeList').selectedRowIds()[0];

		this.$('#assetCodePhotoList').flexUpdateRow(rownum, row);
		break;

	case 'popupFcltyCd':
        var opts;

        this.doExecuteDialog('selectAssetsCdPopup', '시설 선택', '<c:url value="/popup/showAssetsCd.do"/>', opts);
		break;
	}
};

GamAssetCodeModule.prototype.onClosePopup = function(popupId, msg, value) {
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
     case 'selectAssetsCdPopup':
         if (msg != 'cancel') {
             this.$('#gisAssetsPrtAtCode').val(value.gisAssetsPrtAtCode);
             this.$('#gisAssetsCd').val(value.gisAssetsCd);
             this.$('#gisAssetsSubCd').val(value.gisAssetsSubCd);
             this.$('#gisAssetsNm').val(value.gisAssetsNm);
             this.$('#gisAssetsLocplc').val(value.gisAssetsLocplc);
             this.$('#gisAssetsLnm').val(value.gisAssetsLnm);
             this.$('#gisAssetsLnmSub').val(value.gisAssetsLnmSub);
             this.$('#gisAssetsAr').val(value.gisAssetsAr);
             this.$('#gisAssetsRealRentAr').val(value.gisAssetsRealRentAr);
             this.$('#prtAtCodeNm').val(value.gisAssetsPrtAtCodeNm);
             this.$('#quayCd').val(value.gisAssetsQuayCd);
             this.$('#assetsCdStr').val(value.gisAssetsCd + "-" + value.gisAssetsSubCd);
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
		this.$('#searchViewStack')[0].changePanelId(0);
		break;
	case 'tabs2':
		this.$('#searchViewStack')[0].changePanelId(1);
		break;
	case 'tabs3':
		this.$('#searchViewStack')[0].changePanelId(1);
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
			<div id="searchViewStack" class="viewStack">
				<div class="viewPanel">
		<form id="searchErpAssetCode">
					<table style="width:100%;" class="searchPanel">
						<tbody>
							<tr>
								<th>자산구분</th>
								<td><select id="searchAssetCls">
										<option value="" selected="selected">선택</option>
										<c:forEach  items="${erpAssetClsList}" var="clsItem">
											<option value="${clsItem.smCls }">${clsItem.smClsName }</option>
										</c:forEach>
								</select>
								</td>
								<th>자산번호</th>
								<td><input id="searchAssetNo" type="text" size="6">-<input
									id="searchAssetNoSeq" type="text" size="6"></td>
									<!--
								<th>품목구분</th>
								<td><select id="itemCls">
										<option value="" selected="selected">선택</option>
										<option value="A">건물</option>
										<option value="L">토지</option>
										<option value="S">공작물</option>
										<option value="w">창고</option>
										<option value="E">기타</option>
								</select></td>
								 -->
								<th>취득일자</th>
								<td><input id="searchStartDt" type="text" class="emdcal" size="8"> ~ <input id="searchEndDt" type="text" class="emdcal" size="8"></td>
								<td rowSpan="2"><button id="selectErpAssetCode" class="buttonSearch">조회</button></td>
							</tr>
							<tr>
								<th>품명</th>
								<td colspan="3"><input id="searchItemName" size="40"></td>
								<th>부서</th>
								<td width="200px" colspan="3"><input id="deptCd" class="ygpaDeptSelect" /></td>

							</tr>
						</tbody>
					</table>
					</form>
					</div>
				<div class="viewPanel">
					<form id="searchGisAssetCode">
						<table class="searchPanel">
							<tbody>
								<tr>
									<th>항구분</th>
									<td><input id="searchPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
									<th>ERP 자산코드</th>
									<td><input id="searchErpAssetsCls" type="text" size="1" maxlength="1">-<input id="searchErpAssetsNo" type="text" size="4" maxlength="4">-<input id="searchErpAssetsNoSeq" type="text" size="2" maxlength="2"></td>
									<td rowSpan="2"><button id="selectGisAssetCode" class="buttonSearch">조회</button></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="assetManageTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">ERP자산정보</a></li>
				<li><a href="#tabs2" class="emdTab">GIS자산목록</a></li>
				<li><a href="#tabs3" class="emdTab">자산사진</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
				<table id="erpAssetCodeList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel"><button id="addAssetGisCd">자산등록</button></div>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: scroll;" data-onactivate="onShowTab2Activate">
								<table id="assetCodeList" style="display:none"></table>
				<div class="emdControlPanel">
					<button id="loadMap">지도보기</button>
					<button id="btnAddGisMap">위치등록</button>
					<button id="addAssetGisCdItem">자산추가</button>
					<button id="removeAssetGisCdItem">삭제</button>
					<button id="btnSaveGisAssetsCode">저장</button>
				</div>
				<form id="editGisAssetCode" name="gisAssetCode">
				<table style="border: 1px black;">
					<tr>
						<th><span class="label">항구분</span></th>
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
								<input id="gisAssetsPrprtySeCd" class="ygpaCmmnCd" data-code-id='GAM001'>
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
						<th><span class="label">취득가액</span></th>
						<td colspan="3"><input type="text" size="16" id="gisAssetsAcqPri" class="ygpaCurrency" class="ygpaCurrency"> 원</td>
					</tr>
					<tr>
						<th><span class="label">면적</span></th>
						<td><input type="text" size="8" class="ygpaNumber" id="gisAssetsAr" data-column-id="gisAssetsAr" data-decimal-point="2"> m²</td>
						<th><span class="label">실제 임대 면적</span></th>
						<td><input type="text" size="8" id="gisAssetsRealRentAr" class="ygpaNumber" data-decimal-point="2"> m²</td>
						<th><span class="label">자산규격</span></th>
						<td><input type="text" size="20" id="gisAssetsStndrd"></td>
					</tr>
					<tr>
						<th><span class="label">관리부서</span></th>
						<td><input type="text" id="gisAssetsMngDeptCd" class="ygpaDeptSelect" data-default-prompt="없음"></td>
						<th><span class="label">운영부서</span></th>
						<td colspan="3"><input type="text" id="gisAssetsOperDeptCd" class="ygpaDeptSelect" data-default-prompt="없음"></td>
					</tr>
					<tr>
						<th><span class="label">준공년도</span></th>
						<td><input type="text" size="4" id="gisAssetsBlddate"></td>
						<th><span class="label">준공일자</span></th>
						<td colspan="3"><input type="text" size="16" class="emdcal" id="gisAssetsBldDt"></td>
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
