<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamErpAssetCodeMngt.jsp
  * @Description : 자산취득 관리 프로그램
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
<validator:javascript formName="gamAssetCode" method="validateGamAssetCode" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetPhoto" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetCodeModule() {}

GamAssetCodeModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetCodeModule.prototype.loadComplete = function() {
	this._edited=false;	// 편집 상태를 저장 한다.

	this.$('#prtAtCode').val('620');	// 기본 항코드 설정s

	// 테이블 설정
	this.$("#erpAssetCodeList").flexigrid({
		module: this,
		url: '/asset/selectErpAssetCodeList.do',
		dataType: 'json',
		colModel : [
			{display:'자산<br>등록', name:'gisAssetsFlag', width:45, sortable:false, align:'center' },
			{display:'ERP자산코드', name:'erpAssetCode', width:80, sortable:true, align:'center'},
			{display:'자산관리번호', name:'assetMngtNo', width:80, sortable:true, align:'left'},
			{display:'품목', name:'itemCls', width:50, sortable:true, align:'center'},
			{display:'품명', name:'itemNameAsset', width:200, sortable:true, align:'left'},
			{display:'취득일자', name:'buyDate', width:70, sortable:true, align:'center'},
			{display:'취득수량', name:'buyQty', width:60, sortable:true, align:'right', displayFormat: 'number'},
			{display:'취득단가', name:'buyPrice', width:100, sortable:true, align:'right', displayFormat: 'number'},
			{display:'취득구분', name:'buyCls', width:50, sortable:true, align:'center'},
			{display:'구매구분', name:'purCls', width:50, sortable:true, align:'center'},
			{display:'구매용도', name:'purPurpose', width:200, sortable:true, align:'left'},
			{display:'구입처', name:'purCust', width:200, sortable:true, align:'left'},
			{display:'회계단위구분', name:'accUnitCls', width:80, sortable:true, align:'center'},
			{display:'프로젝트코드', name:'projectCd', width:80, sortable:true, align:'center'},
			{display:'장소코드', name:'placeCd', width:50, sortable:true, align:'center'},
			{display:'부서코드', name:'deptCd', width:50, sortable:true, align:'center'},
			{display:'사원번호', name:'empNo', width:80, sortable:true, align:'center'},
			{display:'모델명', name:'modelName', width:200, sortable:true, align:'left'},
			{display:'자산규격', name:'assetSize', width:200, sortable:true, align:'left'},
			{display:'제품일련번호', name:'productSeqNo', width:200, sortable:true, align:'left'},
			{display:'제조업체명', name:'makerName', width:200, sortable:true, align:'left'},
			{display:'계정코드', name:'accntCd', width:70, sortable:true, align:'center'},
			{display:'상각구분', name:'deprctnCls', width:60, sortable:true, align:'center'},
			{display:'자산내용년수', name:'assetFixTerm', width:80, sortable:true, align:'center'},
			{display:'변동구분', name:'changeCls', width:60, sortable:true, align:'center'}
			],
		height: 'auto',

		/* GIS 등록여부 추가(2019-06-16 jckim) */
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				/* this.erpAssetCode=this.assetCls+'-'+this.assetNo+'-'+this.assetNoSeq; */

				this.gisAssetsFlag=this.gisAssetsCnt>0?'등록':'미등록';
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

		module.$("#assetManageTab").tabs("option", {active: 1});	// 탭을 전환 한다.
	});

	this.$("#erpAssetCodeList").on('onItemSelected', function(event, module, row, grid, param) {
		//module.$('#addAssetGisCd').attr('disabled', 'disabled');
		//alert('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is selected');
	});

	this.$("#assetCodeList").flexigrid({
		module: this,
		url: '/asset/selectGisAssetCodeList.do',
		colModel : [
					{display:'맵', name:'gisFlag', width:24, sortable:false, align:'center', displayFormat: 'jqimg', skipxls: true},
					{display:'ERP자산코드', name:'erpAssets', width:80, sortable:true, align:'center'},
					{display:'항코드', name:'gisAssetsPrtAtCode', width:40, sortable:true, align:'center'},
					{display:'항코드명', name:'prtAtCodeNm', width:55, sortable:true, align:'center'},
					{display:'자산코드', name:'assetCode', width:55, sortable:true, align:'center'},
					{display:'자산명', name:'gisAssetsNm', width:150, sortable:true, align:'left'},
					{display:'재산', name:'gisAssetsPrprtyNm', width:80, sortable:true, align:'left'},
					{display:'위치', name:'gisAssetsLocNm', width:100, sortable:true, align:'left'},
					{display:'부두', name:'gisAssetsQuayNm', width:100, sortable:true, align:'left'},
					{display:'관리부서', name:'mngDeptNm', width:100, sortable:true, align:'center'},
					{display:'운영부서', name:'operDeptNm', width:100, sortable:true, align:'center'},
					{display:'소재지', name:'gisAssetsLocplc', width:180, sortable:true, align:'left'},
					{display:'지번', name:'lotcode', width:40, sortable:true, align:'center'},
					{display:'면적', name:'gisAssetsAr', width:80, sortable:true, align:'right', displayFormat: 'number'},
					{display:'실임대면적', name:'gisAssetsRealRentAr', width:80, sortable:true, align:'right', displayFormat:'number'},
					{display:'취득가액', name:'gisAssetsAcqPri', width:100, sortable:true, align:'right', displayFormat: 'number'},
		 			{display:'규격', name:'gisAssetsStndrd', width:120, sortable:true, align:'left'},
					{display:'준공년도', name:'gisAssetsBlddate', width:50, sortable:true, align:'center'},
					{display:'준공일자', name:'gisAssetsBldDt', width:80, sortable:true, align:'center'},
					{display:'사용', name:'gisAssetsUsageYn', width:40, sortable:true, align:'center'}


/* 					{display:'지목', name:'lndcgr', width:40, sortable:true, align:'center'},
					{display:'구매용도', name:'gisAssetsPrpos', width:40, sortable:true, align:'center'},
					{display:'품목구분코드', name:'prdlstSe', width:40, sortable:true, align:'center'},
					{display:'품목구분', name:'prdlstSeNm', width:40, sortable:true, align:'center'},
					{display:'회계구분코드', name:'fsse', width:40, sortable:true, align:'center'},
					{display:'회계구분', name:'fsseNm', width:40, sortable:true, align:'center'},
					{display:'대장가액', name:'regstrAmount', width:40, sortable:true, align:'center'},
					{display:'현재 대장가액', name:'nowRegstrAmount', width:40, sortable:true, align:'center'},
					{display:'출자금액', name:'invstmntAmount', width:40, sortable:true, align:'center'},
					{display:'시가표준액', name:'mktcStdAm', width:40, sortable:true, align:'center'},
					{display:'공시지가', name:'olnlp', width:40, sortable:true, align:'center'},
					{display:'연면적', name:'totar', width:40, sortable:true, align:'center'},
					{display:'수량', name:'qy', width:40, sortable:true, align:'center'},
					{display:'단위', name:'unit', width:40, sortable:true, align:'center'},
					{display:'주요시설', name:'mainFclts', width:40, sortable:true, align:'center'},
					{display:'당초 허가기간 시작', name:'bgnnPrmisnPdBegin', width:40, sortable:true, align:'center'},
					{display:'당초 허가기간 종료', name:'bgnnPrmisnPdEnd', width:40, sortable:true, align:'center'},
					{display:'변경 허가기간 시작', name:'changePrmisnPdBegin', width:40, sortable:true, align:'center'},
					{display:'변경 허가기간 종료', name:'changePrmisnPdEnd', width:40, sortable:true, align:'center'}
 */

			],
		height: 'auto',
		preProcess: function(module, data) {
			module._leftBuyPrice=module._buyPrice;
			$.each(data.resultList, function() {
				this.assetCode = this.gisAssetsCd+"-"+this.gisAssetsSubCd;
				this.lotcode = this.gisAssetsLnm;
				if(this.gisAssetsLnmSub!=null && this.gisAssetsLnmSub.length>0) {
					this.lotcode += "-"+this.gisAssetsLnmSub;
				}
				if(module._leftBuyPrice>0) {
					module._leftBuyPrice-=this.gisAssetsAcqPri;
					if(module._leftBuyPrice>0) module._leftBuyPrice=0;
				}
				this.assetCodeObj = {gisAssetsPrtAtcode: this.gisAssetsPrtAtcode, gisAssetsCd: this.gisAssetsCd, gisAssetsSubCd: this.gisAssetsSubCd};	// for show map

				/* GIS 등록여부 추가(2019-06-16 jckim) */
				this.gisFlag=this.gisStat>0?'flag':null;

			});
			return data;
		}
	});

	this.$("#assetCodeList").on('onItemSelected', function(event, module, row, grid, param) {
		/*
			일자 : 2018.07.05
			내용 : 신규가아닐경우 항구분 수정 하지 못하도록
		*/
		console.log("assetCodeList_onItemSelected");
		module.$('#gisAssetsPrtAtCode_select').attr('disabled', 'disabled');
		module.$('#gisAssetsPrtAtCode').attr('disabled', 'disabled');
		module.$('#gisAssetsSeCd_select').attr('disabled', 'disabled');
		module.$('#prdlstSe_select').attr('disabled', 'disabled');
		module.$('#fsse_select').attr('disabled', 'disabled');

		module.makeFormValues('#editGisAssetCode', row);
		module._editData=module.getFormValues('#editGisAssetCode', row);
		module._editRow=module.$('#assetCodeList').selectedRowIds()[0];
		
		gisAssetsSeCdChange(row.gisAssetsSeCd);

	});

	this.$("#assetCodeList").on('onLoadDataComplete', function(event, module, data, grid, param) {
		module._editRow=null;
		module._deleteDataList=[];
	});

	this.$("#assetCodePhotoList").flexigrid({
		module: this,
		url: '/asset/selectGisAssetPhotoList.do',
		colModel : [
				{display:'사진순번', name:'photoSeq', width:80, sortable:true, align:'center'},
				{display:'사진제목', name:'photoSj', width:300, sortable:true, align:'left'},
				{display:'파일명', name:'filenmLogic', width:200, sortable:true, align:'left'},
				{display:'촬영일자', name:'shotDt', width:120, sortable:true, align:'center'},
				{display:'등록자', name:'regUsr', width:155, sortable:true, align:'center'}
			],
		height: '120'
	});

	this.$("#assetCodePhotoList").on('onLoadDataComplete', function(event, module, data, grid, param) {
		module._edited=false;
		module._deleteDataList=[];
	});

	this.$("#assetCodePhotoList").on('onItemSelected', function(event, module, row, grid, param) {
		module.makeFormValues('#editAssetGisPhotoForm', row);
		module._editPhotoData=module.getFormValues('#editAssetGisPhotoForm', row);
		module._editPhotoRow=module.$('#assetCodePhotoList').selectedRowIds()[0];

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
//		// console.log('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is selected');
	});

	this.$("#assetCodePhotoList").on('onLoadDataComplete', function(event, module, data, grid, param) {
		module._editPhotoRow=null;
		module._deletePhotoList=[];
	});


	this.$('#gisAssetsLocCd').on('change', function() {
		//alert($(this).getSelectedCodeLabel() + '이(가) 선택되었습니다.');
	});

	// 사진 정보 속성이 변경 된 경우 이벤트 실행
	this.$(".photoEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyPhotoItem();
/* 		var m = event.data.module;
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
			var dtStr = m.$('#shotDt').val();
			m._editPhotoData.shotDt = dtStr;
		}
 */
	});

	this.$('#searchStartDt').val(EMD.util.getDate(EMD.util.addMonths(-1)));

	/**
	* 일자 : 2019-06-13
	* 작업 내용 : 자산등록 컬럼 초기화
	* 작업자 : jckim
	*/
	$('.gisAssetsSeCd2').hide();
	$('.gisAssetsSeCd1_hidden').hide();
	$('.mktcStdAm').hide();
	$('.invstmntAmount').hide();
	$('.qy').hide();
	$('.unit').hide();
	$('.totar').hide();
	$('.gisAssetsSeCd3').hide();


	this.$('#gisAssetsSeCd').attr('disabled', 'disabled');
	this.$('#prdlstSe').attr('disabled', 'disabled');
	this.$('#fsse').attr('disabled', 'disabled');
	
};

// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
GamAssetCodeModule.prototype.showModuleAlert = function(msg) {
	//this.getSelect(msg);
	this.$('#prtCode').val(msg);
};

GamAssetCodeModule.prototype.calcLeftBuyPrice = function() {
	var leftBuyPrice=this._leftBuyPrice;
	var data = this.$('#assetCodeList').flexGetData();
	for(var i=0; i<data.length; i++) {
		if(leftBuyPrice>0) {
			leftBuyPrice-=data[i].gisAssetsAcqPri;
			if(leftBuyPrice>0) leftBuyPrice=0;
		}
	}
	this._leftBuyPrice=leftBuyPrice;
};

GamAssetCodeModule.prototype.addGisAssetItem = function() {
	console.log("addGisAssetItem");
	this._edited=true;

	this.$('#editGisAssetCode :input').val('');

	this.$('#erpAssetsCls').val(this.$('#searchGisAssetErpAssetsCls').val());
	this.$('#erpAssetsNo').val(this.$('#searchGisAssetErpAssetsNo').val());
	this.$('#erpAssetsNoSeq').val(this.$('#searchGisAssetErpAssetsNoSeq').val());
	this.$('#erpAssetsCls').readonly(false);
	this.$('#erpAssetsNo').readonly(false);
	this.$('#erpAssetsNoSeq').readonly(false);

	this.$('#itemName').val(this._itemNameAsset);
	this.$('#gisAssetsAcqPri').val(this._leftBuyPrice);
	this.$('#itemName').disable();

	this.$('#gisAssetsPrtAtCode').enable();


	/**
	* 일자 : 2019-06-14
	* 작업 내용 : 자산구분, 품목구분, 회계구분, 구매용도 값 자동 입력
	* 작업자 : jckim
	*/
	if(this.$('#erpAssetCodeList').selectedRows().length > 0){
		/* 자산구분*/
		this.$('#gisAssetsSeCd').val(this.$('#erpAssetCodeList').selectedRows()[0].assetCls);
		/* 품목구분 */
		var _itemCls=this.$('#erpAssetCodeList').selectedRows()[0].itemCls;
		
		switch(_itemCls){
			case '000001':
			case '000002':
			case '002001':
			case '002002':
			case '002003':
			case '002004':
				this.$('#prdlstSe').val(_itemCls);
				break;
			default :
				this.$('#prdlstSe').val('');
		}
		/* 회계구문 */
		this.$('#fsse').val(this.$('#erpAssetCodeList').selectedRows()[0].accUnitCls);
		/* 구매용도 */
		this.$('#gisAssetsPrpos').val(this.$('#erpAssetCodeList').selectedRows()[0].purPurpose);
	}

	/*
		일자 : 2018.07.05
		내용 : 기본값 사용(Y)으로 지정
	*/
	this.$('#gisAssetsUsageYn').val("Y");

	this._editData=this.getFormValues('#editGisAssetCode', {_updtId:'I'});	// 데이터 추가
	this._editRow=null;
	// this.$('#btnApplyGisAssetCode').removeAttr('disabled');
};

GamAssetCodeModule.prototype.removeGisAssetItem = function() {
	if(this.$('#assetCodeList').selectedRowIds().length>0) {
		for(var i=this.$('#assetCodeList').selectedRowIds().length-1; i>=0; i--) {
			var row=this.$('#assetCodeList').flexGetRow(this.$('#assetCodeList').selectedRowIds()[i]);
			if(row._updtId==undefined || row._updtId!='I')  this._deleteDataList[this._deleteDataList.length]=row;	// 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			this.$('#assetCodeList').flexRemoveRow(this.$('#assetCodeList').selectedRowIds()[i]);
			this.$('#editGisAssetCode :input').val('');
			this._edited=true;
		}
		this.calcLeftBuyPrice();
	}
};

GamAssetCodeModule.prototype.saveGisAssetItem = function() {
	if( confirm("저장하시겠습니까?") ) {
	    // 변경된 자료를 저장한다.
	    var inputVO=[];
	    inputVO[inputVO.length]={name: 'updateList', value :JSON.stringify(this.$('#assetCodeList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

	    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#assetCodeList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

	    if(this._deleteDataList==undefined || this._deleteDataList==null) this._deleteDataList=[];

	    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataList) };

	    this.doAction('/asset/mergeGamErpGisAssetCodeMngt.do', inputVO, function(module, result) {
	        if(result.resultCode == 0){
	            var searchOpt=module.makeFormArgs('#searchGisAssetCode');
	            console.log("searchOpt: " + searchOpt);
	            module.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
	        }


			/*
				일자 : 2018.07.05
				내용 : 신규가아닐경우 항구분 수정 하지 못하도록
			*/
	        if(result.resultCode == 0)
	        {
	    		module.$('#gisAssetsPrtAtCode_select').attr('disabled', 'disabled');
	    		module.$('#gisAssetsPrtAtCode').attr('disabled', 'disabled');
	        }

	        module._edited=false;
	    });
	}
};

GamAssetCodeModule.prototype.removeGisAssetPhotoItem = function() {
	if(this.$('#assetCodePhotoList').selectedRowIds().length>0) {
		for(var i=this.$('#assetCodePhotoList').selectedRowIds().length-1; i>=0; i--) {
			var row=this.$('#assetCodePhotoList').flexGetRow(this.$('#assetCodePhotoList').selectedRowIds()[i]);
			if(row._updtId==undefined || row._updtId!='I')  this._deletePhotoList[this._deletePhotoList.length]=row;	// 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			this.$('#assetCodePhotoList').flexRemoveRow(this.$('#assetCodePhotoList').selectedRowIds()[i]);

			this.$("#previewImage").attr("src","");
	    	alert("삭제되었습니다.");
		}

		this._edited=true;
	}
};

GamAssetCodeModule.prototype.saveGisAssetPhotoItem = function() {
	if( confirm("사진 목록을 저장하시겠습니까?") ) {
	    // 변경된 자료를 저장한다.
	    var inputVO=[];
	    inputVO[inputVO.length]={name: 'updateList', value :JSON.stringify(this.$('#assetCodePhotoList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

	    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#assetCodePhotoList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

	    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deletePhotoList) };

	    this.doAction('/asset/mergeGamErpGisAssetPhotoMngt.do', inputVO, function(module, result) {
	        if(result.resultCode == 0){
	            var searchOpt=module.makeFormArgs('#searchGisAssetPhoto');
	            module.$('#assetCodePhotoList').flexOptions({params:searchOpt}).flexReload();
	        }
	        alert(result.resultMsg);
	        module._edited=false;
	    });
	}
};

GamAssetCodeModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'selectErpAssetCode':
		var searchOpt=this.makeFormArgs('#searchErpAssetCode');
	 	this.$('#erpAssetCodeList').flexOptions({params:searchOpt}).flexReload();
		break;

	case 'selectGisAssetCode':
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
		break;
	case 'selectAddr':
        this.doExecuteDialog('selectAddrPopup', '주소 입력', '/popup/showAddrPopup.do', []);
		break;
	case 'selectGisAssetPhoto':	// 자산 사진 조회
		if(this.$('#searchGisAssetPrtAtCode').val()=='') {
			alert('항구분을 선택 하세요.');
			this.$('#searchPrtAtCode').addClass('ui-state-error');
			this.$('#searchPrtAtCode').focus();
		}
		else {
			this.$('#searchPrtAtCode').removeClass('ui-state-error');
		}
		if(this.$('#searchGisErpAssetCls').val()=='' || this.$('#searchGisErpAssetNo').val()=='' ) {
			alert('ERP 자산코드를 입력하거나 이전 탭에서 자산 항목을 선택 하세요.');
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
		var searchOpt=this.makeFormArgs('#searchGisAssetPhoto');
	 	this.$('#assetCodePhotoList').flexOptions({params:searchOpt}).flexReload();
		break;
	case 'addAssetGisCd':	// gis 자산 추가
		if(this.$('#erpAssetCodeList').selectedRowIds()>0) {
			var row = this.$('#erpAssetCodeList').selectedRows()[0];
			this.$("#assetManageTab").tabs("option", {active: 1});	// 탭을 전환 한다.

/* flexReload 2회 호출로 탭전환시에만 조회하는 걸로 사용(2019-06-13) jckim */
/* 			this.$('#searchGisAssetErpAssetsCls').val(row['assetCls']);
			this.$('#searchGisAssetErpAssetsNo').val(row['assetNo']);
			this.$('#searchGisAssetErpAssetsNoSeq').val(row['assetNoSeq']);

			// 해당하는 자산 목록을 불러온다/
			var searchOpt=this.makeFormArgs('#searchForm');
			//this.showAlert(searchOpt);
		 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
 */
			this._itemNameAsset = row['itemNameAsset'];// 자산명 저장
		 	this.addGisAssetItem();
		}
		else {
			alert('추가할 자산의 ERP 코드를 선택 하세요');
		}
		break;
	case 'btnErpAssetCodeListExcelDownload':	// 엑셀 다운로드
		this.$('#erpAssetCodeList').flexExcelDown('/asset/selectErpAssetCodeListExcel.do', 'erpAssetCodeList.xls');
		break;
	case 'addAssetGisCdItem':
		this.$('#gisAssetsSeCd').enable();
		this.$('#prdlstSe').enable();
		this.$('#fsse').enable();
		
		this.addGisAssetItem();
		break;
	case 'removeAssetGisCdItem':
		this.removeGisAssetItem();
		break;
	case 'btnSaveGisAssetsCode':
		// 변경된 자료를 저장한다.
		this.saveGisAssetItem();
		break;
	case 'btnApplyGisAssetsCode':
		if(this.$('#gisAssetsSeCd').val()=='4'){
			this.$('#qy').val(this.$('#qy1').val());
			this.$('#unit').val(this.$('#unit1').val());
		}

		if(this._editRow==null && (this._editData==null || this._editData._updtId!='I')) {

			return;	// no action;
		}
		if(!validateGamAssetCode(this.$('#editGisAssetCode')[0])) {
			return;
		}
		this._editData=this.getFormValues('#editGisAssetCode', this._editData);
		if(this._editRow!=null) {
			if(this._editData._updtId!='I') this._editData._updtId='U';	// 삽입된 데이터가 아니면 업데이트 플래그를 추가한다.
			this.$('#assetCodeList').flexUpdateRow(this._editRow, this._editData);
			this._editRow=null;	// 편집 저장 하였으므로 로우 편집을 종료 한다.
		}
		else {
			if(this._editData._updtId=='I') {
				this.$('#assetCodeList').flexAddRow(this._editData);	// 추가 모드인 경우 데이터 추가
			}
		}
		this._editData={};
		this.calcLeftBuyPrice();

//		this.clearCodePage();

//		this.$('#btnApplyGisAssetsCode').attr('disabled', 'disabled');
		break;
	case 'btnCancelGisAssetsCode':
		this.$('#editGisAssetCode :input').val('');
		break;
	case 'removeAssetCdItem':
		break;
	case 'editAssetCd':
		break;
	case 'btnAddGisMap':
		if(this.$('#assetCodeList').selectedRowIds().length>0) {
			var row = this.$('#assetCodeList').selectedRows()[0];
			// test
			if(row['gisAssetsPrtAtCode']!=null && row['gisAssetsCd']!=null && row['gisAssetsSubCd']!=null
					&& row['gisAssetsPrtAtCode'].length==3 && row['gisAssetsCd'].length==3 && row['gisAssetsSubCd'].length==2) {
				this.addGisAssetsCdMap('GAC', {'gisPrtAtCode': row['gisPrtAtCode'], 'gisAssetsCd': row['gisAssetsCd'], 'gisAssetsSubCd': row['gisAssetsSubCd']});
			}
			else {
				alert('저장된 시설에만 위치 등록이 가능합니다.');
				// // console.log("error add gis feature");
			}
//			this.btnAddGisMap('GAC', {row.gisAssetsCd, row.gisAssetsSubCd});
		}
		break;

	case 'btnUploadFile':
		var selectRow = this.$('#assetCodeList').selectedRows();
		if(selectRow.length > 0) {
			var row=selectRow[0];
			if(row['gisAssetsPrtAtCode']==null || row['gisAssetsCd']==null || row['gisAssetsSubCd']==null) {
				alert('파일을 업로드 하기 전에 저장된 GIS 자산 목록을 선택 하십시요');
				return;
			}
			if(row['gisAssetsPrtAtCode'].length!=3 || row['gisAssetsCd'].length!=3 || row['gisAssetsSubCd'].length!=2) {
				alert('파일을 업로드 하기 전에 저장된 GIS 자산 목록을 선택 하십시요');
				return;
			}
			this.$('#searchGisAssetsPrtAtCode').val(row['gisAssetsPrtAtCode']);
			this.$('#searchGisAssetsCd').val(row['gisAssetsCd']);
			this.$('#searchGisAssetsSubCd').val(row['gisAssetsSubCd']);

			this._tempGisPrtAtCd=row['gisAssetsPrtAtCode'];
			this._tempGisAssetsCd=row['gisAssetsCd'];
			this._tempGisAssetsSubCd=row['gisAssetsSubCd'];

			this._edited=true;

		}
		else {
			alert('파일을 업로드 하기 전에 저장된 GIS 자산 목록을 선택 하십시요.');
			return;
		}

		var photoSj = this.$("#photoSj").val();
		var shotDt = this.$("#shotDt").val();
		var photoSeq = this.$("#assetCodePhotoList").flexGetData().length + 1;

		// 사진을 업로드하고 업로드한 사진 목록을 result에 어레이로 리턴한다.
		this.uploadFile('uploadPhoto', function(module, result) {
 			var userid=EMD.util.getLoginUserVO().id;
 			//var userid='TEST1';

//			var userid='admin';
			$.each(result, function(){
				module.$('#assetCodePhotoList').flexAddRow({
					_updtId:'I',
					gisAssetsPrtAtCode: module._tempGisPrtAtCd,
					gisAssetsCd: module._tempGisAssetsCd,
					gisAssetsSubCd: module._tempGisAssetsSubCd,
					photoSeq: photoSeq,
					photoSj: photoSj,
					filenmLogic: this.logicalFileNm,
					filenmPhysicl: this.physcalFileNm,
					shotDt: shotDt,
					regUsr: userid,
					registDt:  EMD.util.getTimeStamp()});	// 업로드 파일명이 physcalFileNm (물리명), logicalFileNm (논리명)으로 리턴 된다.
			});
		}, '시설사진 업로드');

		this.$("#photoSj").val("");
		this.$("#shotDt").val("");

		break;

	case 'btnDownloadFile':
		var selectRow = this.$('#assetCodePhotoList').selectedRows();
		if(selectRow.length > 0) {
			var row=selectRow[0];
			this.downloadFile(row["filenmPhysicl"], row["filenmLogic"]);
		}
		break;
	case 'btnApplyPhotoData':
		this.applyPhotoItem();
		break;
	case 'removeAssetGisPhoto':
		this.removeGisAssetPhotoItem();
		break;
	case 'saveAssetGisPhoto':
		this.saveGisAssetPhotoItem();
		break;
	case 'popupFcltyCd':
        this.doExecuteDialog('selectAssetsCdPopup', '시설 선택', '/popup/showAssetsCd.do', []);
		break;
	/* 건축물 시가표준액 */
	case 'selectMktcStdAm':
		
		if(this.$('#gisAssetsBupjungdongCd').val() == '' || this.$('#gisAssetsLnm').val() == ''){
			alert("주소, 지번을 선택해 주세요.")
			break;
		}
		var searchOpt = {"sAdstrdCode" : this.$('#gisAssetsBupjungdongCd').val(), "sLnm" : this.$('#gisAssetsLnm').val(),"sSlno" : this.$('#gisAssetsLnmSub').val()}
		
	    this.doExecuteDialog('selectMktcStdAm', '건축물 시가표준액', '/asset/showPopupBuldMktcStdAm.do', searchOpt);
	break;

	}
};

GamAssetCodeModule.prototype.applyPhotoItem = function() {
	var selectRow = this.$('#assetCodePhotoList').selectedRows();
	if(selectRow.length==0) return;
	if(!validateGamAssetPhoto(this.$('#editAssetGisPhotoForm')[0])) {
		return;
	}
	var rownum;
	var row = {};
	row=this.getFormValues('#editAssetGisPhotoForm', selectRow[0]);
	rownum=this.$('#assetCodePhotoList').selectedRowIds()[0];

	if(row["_updtId"]!='I') row["_updtId"]='U';
	this.$('#assetCodePhotoList').flexUpdateRow(rownum, row);

	// // console.log('_updtId : ' + row["_updtId"]);

	this._edited=true;
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
     case 'selectAddrPopup':
         if (msg != 'cancel') {
        	 // console.log("test");
             this.$('#gisAssetsBupjungdongCd').val(value.bupjungdongCd);
             this.$('#gisAssetsLocplc').val(value.bupjungdongNm+" "+value.detailAddr);
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
     case 'selectMktcStdAm':
         if (msg != 'cancel') {
             this.$('#mktcStdAm').val(value.mktcStdAm);
         } else {
             alert('취소 되었습니다');
         }
         break;

     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');
         //
         break;
     }
};

GamAssetCodeModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	if(this._edited) {
		return confirm('탭을 이동 하면 편집 한 내용이 저장 되지 않습니다. 계속 하시겠습니까?');
	}
	if(newTabId=="tabs3" && this.$('#assetCodeList').selectedRowCount()!=1) {
		alert('선택된 GIS 자산이 없습니다.');
		return false;
	}
	return true;
};

/*
GamAssetCodeModule.prototype.onUploadFileDone = function(uploadId, result) {
	$.each(result, function() {
		this.$)
	});
}
*/
GamAssetCodeModule.prototype.onTabChange = function(newTabId, oldTabId) {
	this._edited=false;
	switch(newTabId) {
	case 'tabs1':
		this.$('#searchViewStack')[0].changePanelId(0);
		break;
	case 'tabs2':
		this.$('#searchViewStack')[0].changePanelId(1);	// 조회 조건 변경
		var selectRow = this.$('#erpAssetCodeList').selectedRows();
		this.clearCodePage();
		if(selectRow.length > 0) {
			var row=selectRow[0];
			this.$('#searchGisAssetErpAssetsCls').val(row['assetCls']);
			this.$('#searchGisAssetErpAssetsNo').val(row['assetNo']);
			this.$('#searchGisAssetErpAssetsNoSeq').val(row['assetNoSeq']);

			this._buyPrice = row['buyPrice'];
			this._leftBuyPrice = this._buyPrice;
			// console.log('price : '+this._leftBuyPrice);
			this._itemNameAsset = row['itemNameAsset'];// 자산명 저장

			// 해당하는 자산 목록을 불러온다/
			var searchOpt=this.makeFormArgs('#searchGisAssetCode');
			//this.showAlert(searchOpt);
		 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();

		}


		break;
	case 'tabs3':
		this.$('#searchViewStack')[0].changePanelId(2);	// 조회 조건 변경
		var selectRow = this.$('#assetCodeList').selectedRows();
		this.clearPhotoPage();
		if(selectRow.length > 0) {
			var row=selectRow[0];
			this.$('#searchGisAssetsPrtAtCode').val(row['gisAssetsPrtAtCode']);
			this.$('#searchGisAssetsCd').val(row['gisAssetsCd']);
			this.$('#searchGisAssetsSubCd').val(row['gisAssetsSubCd']);

			this.selectPhotoList();
		}
		else {
			alert('선택된 GIS 자산이 없습니다.');
		}
		break;
	}
};

GamAssetCodeModule.prototype.clearCodePage = function() {
	this.$('#editGisAssetCode :input').val('');
	this.$('#assetCodeList').flexEmptyData();
};

GamAssetCodeModule.prototype.clearPhotoPage = function() {
	this.$('#assetCodePhotoList').flexEmptyData();
	this.$('#editAssetGisPhotoForm :input').val('');
	this.$('#previewImage').attr('src', '');
};

GamAssetCodeModule.prototype.selectPhotoList = function() {
	// 해당하는 자산 사진 목록을 불러온다/
	var searchOpt=this.makeFormArgs('#searchGisAssetPhoto');
	//this.showAlert(searchOpt);
	this.clearPhotoPage();

 	this.$('#assetCodePhotoList').flexOptions({params:searchOpt}).flexReload();
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


/**
* 일자 : 2019-06-13
* 작업 내용 : 자산구분 셀렉트 박스 체인지 이벤트 추가
* 작업자 : jckim
*/
$('#gisAssetsSeCd').change(function(){
	gisAssetsSeCdChange($(this).val());
});

function gisAssetsSeCdChange(item){
	switch(item) {
/* 		case '1':
			$('.gisAssetsSeCd1').show();
			$('.gisAssetsAr').show();
			$('.gisAssetsSeCd2').hide();
			$('.gisAssetsSeCd1_hidden').hide();
			$('.mktcStdAm').hide();
			$('.invstmntAmount').hide();
			$('.qy').hide();
			$('.unit').hide();
			$('.gisAssetsStndrd').show();
			$('.totar').hide();
			$('.gisAssetsSeCd3').hide();
			break;
 */
		case '2':
			$('.gisAssetsSeCd1_hidden').show();
			$('.gisAssetsSeCd1').hide();
			$('.gisAssetsAr').hide();
			$('.gisAssetsSeCd2').show();
			$('.mktcStdAm').show();
			$('.invstmntAmount').show();
			$('.qy').show();
			$('.unit').show();
			$('.gisAssetsStndrd').show();
			$('.totar').show();
			$('.gisAssetsSeCd3').hide();
			break;

		case '4':
			$('.gisAssetsSeCd1_hidden').show();
			$('.gisAssetsSeCd1').hide();
			$('.gisAssetsAr').show();
			$('.qy').hide();
			$('.unit').hide();
			$('.gisAssetsStndrd').hide();
			$('.totar').hide();
			$('.invstmntAmount').hide();
			$('.mktcStdAm').show();
			$('.gisAssetsSeCd3').show();
			break;

		default :
			$('.gisAssetsSeCd1').show();
			$('.gisAssetsAr').show();
			$('.gisAssetsSeCd2').hide();
			$('.gisAssetsSeCd1_hidden').hide();
			$('.mktcStdAm').hide();
			$('.invstmntAmount').hide();
			$('.qy').hide();
			$('.unit').hide();
			$('.gisAssetsStndrd').show();
			$('.totar').hide();
			$('.gisAssetsSeCd3').hide();
			break;
	}

/* 	this.$('.gisAssetsSeCd1').hide();
	this.$('.clean').text('');
 */

}


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
								<th>자산번호</th>
								<td><input id="searchAssetCls" type="text" size="1" maxlength="1">-
									<input id="searchAssetNo" type="text" size="4" maxlength="16">-
									<input id="searchAssetNoSeq" type="text" size="1" maxlength="16"></td>
								<th>취득일자</th>
								<td><input id="searchStartDt" type="text" class="emdcal" data-role="dtFrom" data-dt-to="searchEndDt" size="8"> ~ <input id="searchEndDt" type="text" class="emdcal" data-role="dtTo"  data-dt-from="searchStartDt" size="8"></td>
								<th>모델명</th>
								<td><input id="searchModelName" size="30"></td>
								<td rowSpan="2"><button id="selectErpAssetCode" class="buttonSearch">조회</button></td>
							</tr>
							<tr>
								<th>품명</th>
								<td colspan="3"><input id="searchItemName" size="30"></td>
								<th>규격</th>
								<td><input id="searchAssetSize" size="30"/></td>
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
									<th>ERP 자산코드</th>
									<td><input id="searchGisAssetErpAssetsCls" data-column-id="erpAssetsCls" type="text" size="1" maxlength="1">
										-<input id="searchGisAssetErpAssetsNo" data-column-id="erpAssetsNo" type="text" size="4" maxlength="4">
										-<input id="searchGisAssetErpAssetsNoSeq" data-column-id="erpAssetsNoSeq" type="text" size="2" maxlength="2"></td>
									<td><button id="selectGisAssetCode" class="buttonSearch">조회</button></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div class="viewPanel">
					<form id="searchGisAssetPhoto">
						<table class="searchPanel">
							<tbody>
								<tr>
									<th>항구분</th>
									<td><input id="searchGisAssetsPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" data-column-id="gisAssetsPrtAtCode" /></td>
									<th>자산코드</th>
									<td><input id="searchGisAssetsCd" data-column-id="gisAssetsCd" type="text" size="3" maxlength="3" style="text-transform: uppercase" >-<input id="searchGisAssetsSubCd" data-column-id="gisAssetsSubCd" type="text" size="2" maxlength="2"></td>
									<td><button id="selectGisAssetPhoto" class="buttonSearch">조회</button></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="assetManageTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
			<ul>
				<li><a href="#tabs1" class="emdTab">ERP자산정보</a></li>
				<li><a href="#tabs2" class="emdTab">GIS자산목록</a></li>
				<li><a href="#tabs3" class="emdTab">자산사진</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
				<table id="erpAssetCodeList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="addAssetGisCd">자산등록</button>
					<button class="buttonExcel" data-flexi-grid="erpAssetCodeList" data-url="<c:url value='/asset/selectErpAssetCodeListExcel.do' />">엑셀</button>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: scroll;" data-onactivate="onShowTab2Activate">
				<table id="assetCodeList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="assetCodeList" data-style="default">맵조회</button>
<!-- 					<button data-role="addFearutre" data-gis-layer="gisAssetsCd" data-flexi-grid="assetCodeList" data-code-id="assetCode">위치등록</button>
 -->					<button id="addAssetGisCdItem">자산추가</button>
					<button id="removeAssetGisCdItem">삭제</button>
					<button id="btnSaveGisAssetsCode">저장</button>
				</div>
				<form id="editGisAssetCode" name="gisAssetCode">
				<input id="gisAssetsBupjungdongCd" type="hidden"/>
				<table class="editForm">
					<colGroup>
						<col width="120" />
						<col width="200" />
						<col width="120" />
						<col width="200" />
						<col width="120" />
						<col width="200" />
					</colGroup>
					<tr>
						<th><span class="label">자산구분</span></th>
						<td>
							<input id="gisAssetsSeCd" class="ygpaCmmnCd" data-code-id='GAM013' data-column-label-id='prtAtCodeNm' disabled="disabled" >
						</td>
						<th><span class="label">품목 구분</span></th>
						<td>
							<input id="prdlstSe" class="ygpaCmmnCd" data-code-id='GAM073' disabled="disabled" >
						</td>
						<th><span class="label">회계 구분</span></th>
						<td>
							<input id="fsse" class="ygpaCmmnCd" data-code-id='GAM072' disabled="disabled" >
						</td>
					</tr>

					<tr>
						<th><span class="label">항구분</span></th>
						<td>
							<input id="gisAssetsPrtAtCode" class="ygpaCmmnCd" data-required='true' data-code-id='GAM019' data-column-label-id='prtAtCodeNm' data-display-value='N'  disabled="disabled" size='3' />
						</td>
						<th><span class="label">자산코드</span></th>
						<td><input type="text" size="3"  id="gisAssetsCd" disabled="disabled">-<input type="text" size="2"  id="gisAssetsSubCd" disabled="disabled"></td>
						<th><span class="label">ERP자산코드</span></th>
						<td><input type="text" size="1" id="erpAssetsCls" data-column-id="erpAssetsCls" readonly="readonly">-<input type="text" size="8" id="erpAssetsNo" readonly="readonly">-<input type="text" size="2" id="erpAssetsNoSeq" readonly="readonly"></td>
					</tr>
					<tr>
					</tr>
					<tr>

						<th><span class="label">ERP자산명</span></th>
						<td><input type="text" size="32" id="itemName" data-column-id="itemName" readonly="readonly"></td>
						<th><span class="label">구매용도</span></th>
						<td><input type="text" size="32" id="gisAssetsPrpos" data-column-id="gisAssetsPrpos" ></td>
						<th><span class="label">지목</span></th>
						<td><input type="text" size="32" id="gisAssetsLndcgr" data-column-id="gisAssetsLndcgr" ></td>
					</tr>
					<tr>
						<th><span class="label">재산구분</span></th>
						<td>
								<input id="gisAssetsPrprtySeCd" class="ygpaCmmnCd" data-code-id='GAM001' data-required="true" data-column-label-id='gisAssetsPrprtyNm'>
						</td>
						<th><span class="label">위치구분</span></th>
						<td>
							<input id="gisAssetsLocCd" class="ygpaCmmnCd" data-code-id='GAM002' data-required="true" data-column-label-id='gisAssetsLocNm'>
						</td>
						<th><span class="label">부두구분</span></th>
						<td>
							<input id="gisAssetsQuayCd" class="ygpaCmmnCd" data-code-id='GAM003' data-required="true" data-column-label-id='gisAssetsQuayNm'>
						</td>
					</tr>
					<tr>
						<th><span class="label">자산명</span></th>
						<td colspan="5"><input type="text" size="80" id="gisAssetsNm" data-required="true"></td>
					</tr>
					<tr>
						<th><span class="label">자산소재지</span></th>
						<td colspan="3"><input type="text" size="60" id="gisAssetsLocplc" readonly><button id="selectAddr" class="popupButton">주소조회</button></td>
						<th><span class="label">지번</span></th>
						<td><input type="text" size="4" id="gisAssetsLnm" maxlength="4">-<input type="text" size="3" id="gisAssetsLnmSub" maxlength="4"></td>
					</tr>

					<tr class="gisAssetsSeCd3">
						<th>
							<span class="label">수량</span>
						</th>
						<td class="">
							<input type="text" size="8" class="ygpaNumber" id="qy1" >
						</td>

						<th>
							<span class="label">단위</span>
						</th>
						<td class="">
							<input type="text" size="8" id="unit1" class="">
						</td>


						<th>
						</th>
						<td>
						</td>
					</tr>

					<tr class="">
						<th>
							<span class="label gisAssetsAr">면적(m²)</span>
							<span class="label qy">수량</span>
						</th>
						<td class="">
							<input type="text" size="8" class="ygpaNumber gisAssetsAr" id="gisAssetsAr" data-column-id="gisAssetsAr" data-decimal-point="2">
							<input type="text" size="8" class="ygpaNumber qy" id="qy" data-column-id="qy" >
						</td>

						<th>
							<span class="label gisAssetsSeCd1">실제 임대 면적(m²)</span>
							<span class="label unit">단위</span>
							<span class="label gisAssetsSeCd3">기존 대장가액</span>
						</th>
						<td class="">
							<input type="text" size="8" id="gisAssetsRealRentAr" class="ygpaNumber gisAssetsSeCd1" data-decimal-point="2">
							<input type="text" size="8" id="unit" class="unit">
							<input type="text" size="16" id="regstrAmount" class="ygpaCurrency gisAssetsSeCd3" >
						</td>


						<th>
							<span class="label gisAssetsStndrd">자산규격</span>
							<span class="label gisAssetsSeCd3">현재 대장가액</span>
						</th>
						<td>
							<input type="text" size="20" id="gisAssetsStndrd" class="gisAssetsStndrd">
							<input type="text" size="16" id="nowRegstrAmount" class="ygpaCurrency gisAssetsSeCd3" >
						</td>

					</tr>


					<tr class="">
						<th>
							<span class="label gisAssetsSeCd1">취득가액(원)</span>
							<span class="label totar">연면적(m²)</span>
							<span class="label gisAssetsSeCd3">당초 허가기간</span>

						</th>
						<td class="">
							<input type="text" size="16" id="gisAssetsAcqPri" class="ygpaCurrency gisAssetsSeCd1" >
							<input type="text" size="8" class="ygpaNumber totar" id="totar" data-column-id=totar data-decimal-point="2">
							<span class="gisAssetsSeCd3"><input type="" size="10" class="emdcal " id="bgnnPrmisnPdBegin">~<input type="text" size="10" class="emdcal " id="bgnnPrmisnPdEnd"></span>

						</td>

						<th>
							<span class="label invstmntAmount">출자금액(원)</span></span>
							<span class="label gisAssetsSeCd3">변경(연장) 허가기간</span>
						</th>
						<td>
							<input type="text" size="16" id="invstmntAmount" class="ygpaCurrency invstmntAmount" >
							<span class="gisAssetsSeCd3"><input type="text" size="10" class="emdcal " id="changePrmisnPdBegin">~<input type="text" size="10" class="emdcal " id="changePrmisnPdEnd"></span>
						</td>
						<th><span class="label mktcStdAm">시가표준액(원)</span></th>
						<td>
							<input type="text" size="16" id="mktcStdAm" class="ygpaCurrency mktcStdAm" >
							<button id="selectMktcStdAm" class="popupButton mktcStdAm">선택</button></td>
						</td>

					</tr>


					<tr class="gisAssetsSeCd1_hidden">
						<th><span class="label olnlp">공시지가(원)</span></th>
						<td><input type="text" size="16" id="olnlp" class="ygpaCurrency olnlp" ></td>
						<th><span class="label gisAssetsSeCd3">주요시설</span></th>
						<td><input type="text" size="30" id="mainFclts" class="gisAssetsSeCd3" ></td>
						<th></th>
						<td></td>
					</tr>


					<tr class="gisAssetsSeCd1">
						<th><span class="label">관리부서</span></th>
						<td><input type="text" id="gisAssetsMngDeptCd" class="ygpaDeptSelect" data-default-prompt="없음" data-column-label-id="mngDeptNm"></td>
						<th><span class="label">운영부서</span></th>
						<td><input type="text" id="gisAssetsOperDeptCd" class="ygpaDeptSelect"  data-default-prompt="없음" data-column-label-id="operDeptNm"></td>
						<th><span class="label">준공일자</span></th>
						<td ><input type="text" size="16" class="emdcal" id="gisAssetsBldDt"></td>
					</tr>





					<tr >
						<th><span class="label">비고</span></th>
						<td colspan="3"><textarea cols="60" rows="1" id="gisAssetsRm"></textarea></td>
						<th><span class="label">사용여부</span></th>
						<td>
							<select id="gisAssetsUsageYn" data-required="true">
									<option value="Y">사용</option>
									<option value="N">사용안함</option>
							</select>
						</td>
					</tr>
					<tr>
					</tr>
				</table>
				<div style="vertical-align: bottom; text-align: right;">
					<button id="btnCancelGisAssetsCode">취소</button>
					<button id="btnApplyGisAssetsCode">적용</button>
				</div>
				</form>
			</div>
			<div id="tabs3" class="emdTabPage" data-onactivate="onShowTab3Activate">
				<table id="assetCodePhotoList" style="display:none"></table>
				<div class="emdControlPanel">
					<button id="btnUploadFile">업로드</button>
					<button id="btnDownloadFile">다운로드</button>
					<button id="removeAssetGisPhoto">삭제</button>
					<button id="saveAssetGisPhoto">저장</button>
				</div>
				<form id="editAssetGisPhotoForm">
					<table class="editForm">
						<colGroup>
							<col width="120" />
							<col width="200" />
							<col width="120" />
							<col width="200" />
							<col width="120" />
							<col width="200" />
						</colGroup>
						<tr>
							<th><span class="label">제목</span></th>
							<td colspan="3">
								<input id="photoSj" type="text" size="60" class="photoEditItem" />
							</td>
							<th><span class="label">촬영일자</span></th>
							<td>
                            	<input id="shotDt" type="text" size="10"  class="emdcal photoEditItem">
			   					<!-- <button id="btnApplyPhotoData">적용</button> -->
							</td>
						</tr>
					</table>
				</form>
				<div class="emdPanel"><img id="previewImage" style="width:800px; border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>
