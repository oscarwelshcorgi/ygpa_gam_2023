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
	this.$("#assetCodeList").flexigrid({
		module: this,
		url: '/code/assets/selectGisAssetCodeList.do',
		colModel : [
			{display:'선택<div id="'+this.getId('title_chkRole')+'" style="padding-right:3px"></div>',name:'chkRole', width:40, sortable:false, align:'center', displayFormat: 'checkbox'},
			{display:'', name:'gisFlag', width:24, sortable:false, align:'center', displayFormat: 'jqimg'},
			{display:'항구분', name:'prtAtNm', width:75, sortable:false, align:'center'},
			{display:'자산코드', name:'assetCode', width:60, sortable:false, align:'center'},
			{display:'자산명', name:'gisAssetsNm', width:180, sortable:false, align:'left'},
			{display:'재산', name:'gisAssetsPrprtyNm', width:48, sortable:false, align:'center'},
			{display:'위치', name:'gisAssetsLocNm', width:110, sortable:false, align:'center'},
			{display:'부두', name:'gisAssetsQuayNm', width:94, sortable:false, align:'center'},
			{display:'소재지', name:'gisAssetsLocplc', width:120, sortable:false, align:'left'},
			{display:'지번', name:'lotcode', width:66, sortable:false, align:'center'},
			{display:'면적(m²)', name:'gisAssetsAr', width:80, sortable:false, align:'right', displayFormat: 'number', displayOption: '0,000.00'},
			{display:'실임대면적', name:'gisAssetsRealRentAr', width:80, sortable:false, align:'right', displayFormat:'number'},
			{display:'취득가액', name:'gisAssetsAcqPri', width:100, sortable:false, align:'right', displayFormat: 'number'},
			{display:'관리부서', name:'mngDeptNm', width:80, sortable:false, align:'center'},
			{display:'운영부서', name:'operDeptNm', width:80, sortable:false, align:'center'},
			{display:'사용여부', name:'gisAssetsUsageYn', width:86, sortable:false, align:'center'},
			{display:'GIS', name:'gisStat', width:40, sortable:true, align:'center'}
			],
		height: 'auto',
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				this.assetCode = this.gisAssetsCd+"-"+this.gisAssetsSubCd;
				this.lotcode = this.gisAssetsLnm;
				if(this.gisAssetsLnmSub!=null && this.gisAssetsLnmSub.length>0) {
					this.lotcode += "-"+this.gisAssetsLnmSub;
				}
				this.prtAtNm = this.prtAtCodeNm+' ['+this.gisAssetsPrtAtCode+']';
				this.gisFlag=this.gisStat>0?'flag':null;
			});
			return data;
		}
	});

	this.$('#title_chkRole').append('');

	this.$("#assetCodeList").on('onItemSelected', function(event, module, row, grid, param) {
		if(row==null) return;
		module.selectedItem = row;
		module._regMode = '';
//        module.selectFeatureData('assetRentDetail', row, true);
	});

	this.$("#assetCodeList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		if(row==null) return;
		module.selectedItem = row;
		module._regMode = '';

		module.$("#assetCodeTab").tabs("option", {
			active : 1
		}); // 탭을 전환 한다.
	});

	this.$("#assetCodePhotoList").flexigrid({
		module: this,
		url: '/code/assets/selectGisAssetPhotoList.do',
		dataType: 'json',
		colModel : [
					{display:'사진순번', name:'photoSeq', width:80, sortable:false, align:'center'},
					{display:'사진제목', name:'photoSj', width:300, sortable:false, align:'left'},
					{display:'파일명', name:'filenmLogic', width:200, sortable:false, align:'left'},
					{display:'촬영일자', name:'shotDt', width:120, sortable:false, align:'center'},
					{display:'등록자', name:'regUsr', width:155, sortable:false, align:'center'}
					],
		height: '120'
	});

	this._deletePhotoList = [];	// 삭제 사진 리스트

	this.$("#assetCodePhotoList").on("onLoadDataComplete", function(event, module, data, grid, param) {
		module._deletePhotoList=[];
		module._edited=false;
		module.$('#previewImage').attr('src', '#');

	});

	this.$("#assetCodePhotoList").on('onItemSelected', function(event, module, row, grid, param) {
		module.$('.emdcal').val('');
		module.makeFormValues('#editAssetGisPhotoForm', row);

		if(row.filenmPhysicl!=null || row.filenmPhysicl!='') {
			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm=row['filenmPhysicl'];
			var ext=filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();
			if(ext=='jpg' || ext=='jpeg' || ext=='bmp' || ext=='png' || ext=='gif') {
			    $imgURL = module.getUrl("/code/assets/getAssetImage.do?physicalFileNm=")+filenm;
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

	this.$('#gisAssetsLocCd').on('change', function() {
		//alert($(this).getSelectedCodeLabel() + '이(가) 선택되었습니다.');
	});

	// 사진 정보 속성이 변경 된 경우 이벤트 실행
	this.$('.photoEditItem').on('keyup change', {module: this}, function(event) {
		var m = event.data.module;
		var rowIdx=m.$('#assetCodePhotoList').selectedRowIds();

		if(rowIdx.length!=1) return;

		var row=m.$('#assetCodePhotoList').selectedRows()[0];

		row.photoSj = m.$('#photoSj').val();
		row.shotDt = m.$('#shotDt').val();
		if(row._updtId!="I") row._updtId = "U";

		m.$('#assetCodePhotoList').flexUpdateRow(rowIdx[0], row);
	});

	this.changeAssetPk=false;
	this.$('.changeAssetPk').on('change', {module: this}, function(event) {
		var m = event.data.module;
		if(!m.changeAssetpk) alert('주의! 키 값이 변경 됩니다.');
		m.changeAssetpk=true;
	});

	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다. _params 널체크가 귀찮아서

	this._params = params;	// 파라미터를 저장한다.

	switch(this._params.action) {
	case 'addLotcodeFeature': 	// 지번코드로부터 자산정보를 추가하여 저장한다.
		var lnm, lnmSub;
		lnm=parseInt(this._params.feature.attributes.BONBUN);
		lnmSub=parseInt(this._params.feature.attributes.BUBUN);
		//this.$('#searchGisAssetsPrprtySeCd').val('L');	// 자산 정보 주소가 토지로 검색한다.
		// 주소는 동이름으로 검색한다.
		var locplc=this._params.feature.data.locplc.split(' ');
		this.$('#searchGisAssetsLocPlc').val(locplc[locplc.length-1]);
		this.$('#searchGisAssetsLnm').val(lnm);
		this.$('#searchGisAssetsLnmSub').val(isNaN(lnmSub)?'':lnmSub);
		var searchOpt=this.makeFormArgs('#searchGisAssetCode');
	 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
	 	this.$('#btnMapSave').show();
	 	this.$('#btnMapSave2').show();
		break;
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
	case 'modifyFeature': // 기존 자산에 GIS 정보를 추가한다.
	 	console.log('set feature mode');
		break;
	case 'setFeature':
		var lnm, lnmSub;
		var addr=this._params.addr;
		if(addr.indexOf("전라남도")==0) {
			addr = addr.substring(5);
		}
		lnm=parseInt(this._params.lnm);
		lnmSub=parseInt(this._params.lnmSub);
		this.$('#searchGisAssetsLocPlc').val(addr);
		if(!isNaN(lnm) && lnm!=0) this.$('#searchGisAssetsLnm').val(lnm);
		if(!isNaN(lnmSub) && lnmSub!=0) this.$('#searchGisAssetsLnmSub').val(lnmSub);
		this.$('#searchGisAssetsPrprtySeCd').val('L');	// 자산 정보 주소가 토지로 검색한다.
		var searchOpt=this.makeFormArgs('#searchGisAssetCode');
	 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
	 	this.$('#btnMapSave').show();
	 	this.$('#btnMapSave2').show();
	 	//console.log('set feature mode');
	 	break;
	}

	//console.log('GamAssetCodeModule loadcomplete');
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
		// console.log('debug');
		break;
	case 'addAssetGisCdItem': // gis 자산 추가
		this._regMode = "I";
		this.$("#assetCodeTab").tabs("option", {
			active : 1
		}); // 탭을 전환 한다.
		this.$('#assetCodePhotoList').flexEmptyData();
		this.$('#editGisAssetCode :input').val('');
		if (this._params.action == "addLotcodeFeature") {
			// 지번 주소를 저장한다.
			var lnm, lnmSub;
			lnm = parseInt(this._params.feature.attributes.BONBUN);
			lnmSub = parseInt(this._params.feature.attributes.BUBUN);
			this.$('#gisAssetsLocplc')
					.val(this._params.feature.data.locplc);
			this.$('#gisAssetsLnm').val(lnm);
			this.$('#gisAssetsLnmSub').val(isNaN(lnmSub) ? '' : lnmSub);
			this.$('#gisAssetsBupjungdongCd').val(
					this._params.feature.attributes.PNU.substring(10));
			//this._params.feature.attributes.PNU
			//this._params.feature.attributes.BONBUN
			//this._params.feature.attributes.BUBUN
			//this._params.data.addr
		}
		break;
	case 'removeAssetGisCdItem':
				if (this.$('#assetCodeList').selectedRowIds().length != 1) {
					alert('삭제할 항목을 선택 하십시요');
					return;
				}
			if (confirm('선택한 자산을 삭제 하시겠습니까?')) {
				if (this.$('#assetCodeList').selectedRowIds().length > 0) {
					var row = this.$('#assetCodeList').selectedRows();
					var inputVO=[{name: 'deleteList', value :JSON.stringify(row) }];
					this.doAction('/code/assets/deleteGamGisAssetCodes.do', inputVO, function(module, result) {
						module.loadData();
					});
					this._editData = null;
					EMD.gis.removeAssetCdFeature(row);
				}
			}
		break;
	case 'storeAutoMapGenerate': // 지도 위치 자동 등록
	//		EMD.storeAutoMapGenerate
		break;
	case 'btnSaveGisAssetsCode':
		// 변경된 자료를 저장한다.
		// console.log(this._regMode);
		if (!validateGamAssetCode(this.$("#editGisAssetCode")[0]))
			return;
		//alert(this._regMode);
		var inputVO = this.makeFormArgs("#editGisAssetCode");
		if (this._regMode == "I") {
			this.doAction(
							'/code/assets/insertGamGisAssetCode.do',
							inputVO, function(module, result) {
								if (result.resultCode == "0") {
									switch (module._params.action) {
									case 'addLotcodeFeature':
										module.modifyFeatureCode('gisAssetsCd',
												module.selectedItem,
												module._params.feature);
										alert(result.resultMsg);
										// 창을 닫는다.
										//module.closeWindow();
										return;
									case 'addFeature':
									case 'modifyFeature':
										alert('저장된 코드를 다시 조회 하여 저장 해 주십시요');
										break;
									}
									var searchOpt = module.makeFormArgs("#searchGisAssetCode");
									module.$("#assetCodeTab").tabs("option", {active : 0});
									module.$("#assetCodeList").flexOptions(
											{
												params : searchOpt
											}).flexReload();
								}
								alert(result.resultMsg);
							});
		} else {
			this.doAction(
							'/code/assets/updateGamGisAssetCode.do',
							inputVO,
							function(module, result) {
								if (result.resultCode == "0") {
									if(module.changeAssetpk) {
										module.changeAssetpk=false;
										var oldCode=module.selectedItem;
										module.selectedItem=result.resultVo;
										module.loadDetail();
										module.changeFeatureAttrib('gisAssetsCd', oldCode, module.selectedItem);
									}
									else {
										if(module.selectedItem['_feature']==undefined) {
											switch (module._params.action) {
											case 'addLotcodeFeature':
												module.modifyFeatureCode('gisAssetsCd',
														module.selectedItem,
														module._params.feature);
												alert(result.resultMsg);
												// 창을 닫는다.
												module.closeWindow();
												return;
											case 'addFeature':
												break;
											case 'modifyFeature':
												module.changeFeatureAttrib('gisAssetsCd',
														module._params.feature.attributes,
														module.selectedItem
														);
												break;
											}
										}
										else {
											module.modifyFeatureCode('gisAssetsCd',
												module.selectedItem,
												module.selectedItem._feature);
										}
									}
								}
								alert(result.resultMsg);
							});
		}
		// 데이터를 저장 하고 난 뒤 리스트를 다시 로딩 한다.
		break;
	case 'btnApplyGisAssetsCode':	// 사용 안함
		this._editData = this.getFormValues('#editGisAssetCode',
				this._editData);
		if (this._editRow != null) { // 이전에 _updtId 로 선택 한 것을 _editRow 로 변경 2014-03-14.001
			if (this._editData._updtId != 'I')
				this._editData._updtId = 'U'; // 삽입된 데이터가 아니면 업데이트 플래그를 추가한다.
			this.$('#assetCodeList').flexUpdateRow(this._editRow,
					this._editData);
			this._editRow = null; // 편집 저장 하였으므로 로우 편집을 종료 한다.
		} else {
			this.$('#assetCodeList').flexAddRow(this._editData);
		}
		this.$('#editGisAssetCode').find(':input').val('');
		//		this.$('#btnApplyGisAssetsCode').attr('disabled', 'disabled');
		break;
	case 'btnCancelGisAssetsCode':
		this._regMode = 'I';
		this.$('#editGisAssetCode').find(':input').val('');
		this.$('#btnApplyGisAssetCode').removeAttr('disabled');
		break;
	case 'removeAssetGisCdDetailItem':
		if(confirm('선택한 자산을 삭제 하시겠습니까?')){
			if(this._editData==null) {
				EMD.gis.removeFeatureCode('gisAssetsCd', '_editData');
			}
			if (this._editData._updtId==null || this._editData._updtId!='I') {
				this.doAction('/code/assets/deleteGamGisAssetCode.do', this._editData, function(module, result) {
					module.loadData();
					module.$("#assetCodeTab").tabs("option", {
						active : 0
					});
				});
			}
			// EMD.gis.removeFeatureCode('gisAssetsCd', '_editData');
			this._editData = null;
		}
		break;
	case 'editAssetCd':
		break;
	case 'btnAddGisMap':
		if (this._params.action == 'addLotcodeFeature') {
			if (confirm('선택한 자산의 위치로 등록 하시겠습니까?')) {
				this.modifyAssetsCodeFeature(this._editData,
						this._params.feature);
			}
			return;
		}
		if (this._editData.gisAssetsPrtAtCode == null
				|| this._editData.gisAssetsCd == null
				|| this._editData.gisAssetsSubCd == null
				|| this._editData.gisAssetsPrtAtCode.length != 3
				|| this._editData.gisAssetsCd.length != 3
				|| this._editData.gisAssetsSubCd.length != 2) {
			alert('먼저 자료를 저장 하세요.');
			return;
		}
		this.addGisAssetsCdMap('AC', this._editData);
		break;
	case 'selectAddr':
        this.doExecuteDialog('selectAddrPopup', '주소 입력', '/popup/showAddrPopup.do', []);
		break;
	case 'btnUploadFile':
		/*
		// 사진을 업로드하고 업로드한 사진 목록을 result에 어레이로 리턴한다.
		this.uploadFile('uploadPhoto', function(module, result) {
			// 			var userid=EMD.util.getLoginUserVO().userNm; 임시
			var userid = EMD.user.userId();

			$.each(result, function() {
				module.$('#assetCodePhotoList').flexAddRow({
					_updtId : 'I',
					gisAssetsPrtAtCode: module.selectedItem.gisAssetsPrtAtCode,
					gisAssetsCd: module.selectedItem.gisAssetsCd,
					gisAssetsSubCd: module.selectedItem.gisAssetsSubCd,
					photoSj : this.logicalFileNm,
					filenmLogic : this.logicalFileNm,
					filenmPhysicl : this.physcalFileNm,
					regUsr : userid,
					registDt : EMD.util.getTimeStamp()
				}); // 업로드 파일명이 physcalFileNm (물리명), logicalFileNm (논리명)으로 리턴 된다.
			});
			if(result!=null && result.length>0) this._edited=true;

		}, '시설사진 업로드');
		*/
		this.uploadSingleFile('/code/assets/uploadAssetFile.do', function(module, resp) {
			if(resp.resultCode!=0) {
				alert(resp.resultMsg);
				return;
			}
			$.each(resp.result, function() {
				module.$('#assetCodePhotoList').flexAddRow({
					_updtId : 'I',
					gisAssetsPrtAtCode: module.selectedItem.gisAssetsPrtAtCode,
					gisAssetsCd: module.selectedItem.gisAssetsCd,
					gisAssetsSubCd: module.selectedItem.gisAssetsSubCd,
					photoSj : this.logicalFileNm,
					filenmLogic : this.logicalFileNm,
					filenmPhysicl : this.physcalFileNm,
					fileSize : this.size,
					regUsr : EMD.userinfo.name,
					registDt : EMD.util.getTimeStamp()
				}); // 업로드 파일명이 physcalFileNm (물리명), logicalFileNm (논리명)으로 리턴 된다.
			});
			if(resp.result!=null && resp.result.length>0) this._edited=true;
		});
		break;
	case 'saveAssetGisPhoto':	// 사진 목록 저장
		if( confirm("사진 목록을 저장하시겠습니까?") ) {
		    // 변경된 자료를 저장한다.
		    var inputVO=[];
		    inputVO[inputVO.length]={name: 'updateList', value :JSON.stringify(this.$('#assetCodePhotoList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

		    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#assetCodePhotoList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

		    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deletePhotoList) };

		    this.doAction('/code/assets/mergeGamGisAssetPhotoMngt.do', inputVO, function(module, result) {
		        if(result.resultCode == 0){
			    	module.loadPhotoList();
		        }
		        alert(result.resultMsg);
		    });
		}
		break;
	case 'removeAssetGisPhoto':	// 사진 삭제
		if (this.$('#assetCodePhotoList').selectedRowIds().length != 1) {
			alert('삭제할 항목을 선택 하십시요');
			return;
		}
		if( confirm("선택한 항목을 삭제 하시겠습니까?") ) {
			for(var i=this.$('#assetCodePhotoList').selectedRowIds().length-1; i>=0; i--) {
				var row=this.$('#assetCodePhotoList').flexGetRow(this.$('#assetCodePhotoList').selectedRowIds()[i]);
				if(row._updtId==undefined || row._updtId!='I')  this._deletePhotoList[this._deletePhotoList.length]=row;	// 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
				this.$('#assetCodePhotoList').flexRemoveRow(this.$('#assetCodePhotoList').selectedRowIds()[i]);
			}

			this._edited=true;
		}
		break;
	case 'btnApplyPhotoData':
		var rownum;
		var row = {};
		row = module.getFormValues('#editAssetGisPhotoForm', row);
		rownum = module.$('#assetCodePhotoList').selectedRowIds()[0];
		if(row._updtId!='I') row._updtId='U';
		this.$('#assetCodePhotoList').flexUpdateRow(rownum, row);

		break;
	case 'popupErpAssetList':
		var opts = null;
		var params = {
			erpAssetsCls : $('#erpAssetsCls').val(),
			erpAssetsNo : $('#erpAssetsNoSeq').val(),
			erpAssetsNoSeq : $('#erpAssetsNoSeq').val()
		};
		this.doExecuteDialog('selectSearchErpAssetsCdPopup', 'ERP자산코드 선택',
				'/popup/showErpAssetCd.do', opts, params);
		break;
	case 'popupFcltyCd':
		var opts = null;
		this.doExecuteDialog('selectSearchAssetsCdPopup', '시설 선택',
				'/popup/showAssetsCd.do', opts);
		break;
	case 'btnDownloadFile':
		var selectRow = this.$('#assetCodePhotoList').selectedRows();
		if(selectRow.length > 0) {
			var row=selectRow[0];
			this.downloadSingleFile("/code/assets/getAssetDown.do", row["filenmPhysicl"]);
		}
		break;
	case 'btnMapSave':
		switch (this._params.action) {
		case 'addLotcodeFeature':
			this.modifyFeatureCode('gisAssetsCd',
					this.selectedItem,
					this._params.feature);
			alert(result.resultMsg);
			// 창을 닫는다.
			//module.closeWindow();
			return;
		case 'addFeature':
			break;
		case 'modifyFeature':
			this.changeFeatureAttrib('gisAssetsCd',
					this._params.feature.attributes,
					this.selectedItem
					);
			break;
		case 'setFeature':
			this.setFeatureCode('gisAssetsCd',
					this.selectedItem,
					this._params.feature);
			break;
		}
		break;
	case 'btnGetAddress':
		this.applyAddrLotcode();
		break;
	case 'btnRegFile':
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
     case 'selectAddrPopup':
         if (msg != 'cancel') {
             this.$('#gisAssetsBupjungdongCd').val(value.bupjungdongCd);
             this.$('#gisAssetsLocplc').val(value.bupjungdongNm+" "+value.detailAddr);
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
			if (value == "0") {
				var searchOpt = this
						.makeFormArgs('#gamAssetRentMngtSearchForm');
				this.$('#assetRentMngtList').flexOptions({
					params : searchOpt
				}).flexReload();
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

		break;


	}
};

GamAssetCodeModule.prototype.loadDetail = function() {
	var assetCd = [
	               { name: 'gisAssetsPrtAtCode', value: this.selectedItem.gisAssetsPrtAtCode},
	               { name: 'gisAssetsCd', value: this.selectedItem.gisAssetsCd },
	               { name: 'gisAssetsSubCd', value: this.selectedItem.gisAssetsSubCd }
	             ];
  	 	this.doAction('/code/assets/selectGisAssetCodeByPk.do', assetCd, function(module, result) {
		if (result.resultCode == "0") {
			module.makeFormValues('#editGisAssetCode',
					result.result); // 결과값을 채운다.
			module.$('#beforeGisAssetsPrtAtCode').val(result.result.gisAssetsPrtAtCode);
			module.selectedItem=result.result;
			module._editData = result.result;
					module._state="";
					module._regMode="U";
		} else {
			alert(result.resultMsg);
		}
	});

};

<%--
	관리자용 주소 추가 함수
--%>
GamAssetCodeModule.prototype.applyAddrLotcode = function() {
	var d=this.$("#assetCodeList").selectedRows()[0];
	EMD.gis.findLotCode(d.gisAssetsBupjungdongCd, d.gisAssetsLnm, d.gisAssetsLnmSub, d.gisAssetsLnmMt=='Y', {data:d}, function(features, d) {
		if(features==null) console.log(d);
		else {
			if(features.length==0) {
				var pnu=d.data.gisAssetsBupjungdongCd+(d.data.gisAssetsLnmMt=="Y"?"2":"1")+EMD.util.leftPad(d.data.gisAssetsLnm, '0', 4)+EMD.util.leftPad(d.data.gisAssetsLnmSub||0, '0', 4);
				console.log('find pnu ="'+pnu+'"');
				EMD.gis.getLotcode("landCode", pnu, function(features, opt) {
					if(features.length>0) {
						for(f in features) {
							var feature=features[f];
							var polygon= feature.geometry.clone();
						      var attr={
						    		  PRT_AT_CODE: opt.data.gisAssetsPrtAtCode,
						    		  PRT_CD: opt.data.gisAssetsCd,
						    		  PRT_SUB_CD: opt.data.gisAssetsSubCd,
						    		  PRT_NM: opt.data.gisAssetsNm,
						    		  AR: opt.data.gisAssetsAr,
						    		  BJD_CODE: opt.data.gisAssetsBupjungdongCd
						      };
						      var vec=new OpenLayers.Feature.Vector(polygon, attr);
						      EMD.userLayer.gisAssetsCd.addFeatures(vec);
						      EMD.userLayer.gisAssetsCd.redraw();
						      EMD.protocols.gisAssetsCd.create(vec);
						      vec.state = OpenLayers.State.INSERT;

						      EMD.protocols.gisAssetsCd.commit([vec], {
							    	callback:function(resp) {
					    			    if(resp.error) {
					    			        console.log('error');
					    			        return -1;
					    			    }
					    			    // 추후 업데이트를 위해 서버로 부터 로딩한다.
					    			    //EMD.protocols.gisAssetsCd.read();

								    	var gisAssetsProj = new OpenLayers.Projection("EPSG:5186");
										  var polygon= resp.reqFeatures[0].geometry;
										  resp.reqFeatures[0].geometry = polygon.transform(
												  gisAssetsProj,EMD.map.getProjection()
									          );
				    					var ext = resp.reqFeatures[0].geometry.getBounds().clone();

				    					for(var i=1;i<resp.reqFeatures.length;i++) {
											  polygon= resp.reqFeatures[i].geometry;
											  resp.reqFeatures[i].geometry = polygon.transform(
													  gisAssetsProj,EMD.map.getProjection()
										          );

				    						ext.extend(resp.reqFeatures[i].geometry.getBounds());
				    					}
								    	EMD.userLayer.gisAssetsCd.addFeatures(resp.reqFeatures);
								    	EMD.userLayer.gisAssetsCd.redraw();
				    					EMD.map.zoomToExtent(ext);
							    	}
							    });
						}
					}
					else alert('위치를 서버에서 찾을 수 없습니다.');
				}, d);
				return;
			}
			$.each(features, function() {
				var feature=this;
		//	  var gisAssetsProj = new OpenLayers.Projection("EPSG:5186");

			  var polygon= feature.geometry.clone();
		      //var poly = polygon.transform(
//		    		  new, gisAssetsProj
	//	          );
		      var attr={
		    		  PRT_AT_CODE: d.data.gisAssetsPrtAtCode,
		    		  PRT_CD: d.data.gisAssetsCd,
		    		  PRT_SUB_CD: d.data.gisAssetsSubCd,
		    		  PRT_NM: d.data.gisAssetsNm,
		    		  AR: d.data.gisAssetsAr,
		    		  BJD_CODE: d.data.gisAssetsBupjungdongCd
		      };
		      var vec=new OpenLayers.Feature.Vector(polygon, attr);

		      EMD.userLayer.gisAssetsCd.addFeatures(vec);
		      EMD.userLayer.gisAssetsCd.redraw();
		      EMD.protocols.gisAssetsCd.create(vec);
		      vec.state = OpenLayers.State.INSERT;

		      EMD.protocols.gisAssetsCd.commit([vec], {
			    	callback:function(resp) {
	    			    if(resp.error) {
	    			        console.log('error');
	    			        return -1;
	    			    }
	    			    // 추후 업데이트를 위해 서버로 부터 로딩한다.
	    			    //EMD.protocols.gisAssetsCd.read();

				    	var gisAssetsProj = new OpenLayers.Projection("EPSG:5186");
						  var polygon= resp.reqFeatures[0].geometry;
						  resp.reqFeatures[0].geometry = polygon.transform(
								  gisAssetsProj,EMD.map.getProjection()
					          );
    					var ext = resp.reqFeatures[0].geometry.getBounds().clone();

    					for(var i=1;i<resp.reqFeatures.length;i++) {
							  polygon= resp.reqFeatures[i].geometry;
							  resp.reqFeatures[i].geometry = polygon.transform(
									  gisAssetsProj,EMD.map.getProjection()
						          );

    						ext.extend(resp.reqFeatures[i].geometry.getBounds());
    					}
				    	EMD.userLayer.gisAssetsCd.addFeatures(resp.reqFeatures);
				    	EMD.userLayer.gisAssetsCd.redraw();
    					EMD.map.zoomToExtent(ext);
			    	}
			    });
			});
		}
	});

	/*
	var codeList = this.$("#assetCodeList").flexGetData();
	for(code in codeList) {
		var d=codeList[code];
		EMD.gis.findLotCode(d.bjdCode, d.lnm, d.lnmSub, function(feature) {

		});
	}
	*/
};

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
		// console.log(this._regMode);
		if(this._regMode!="I") {
			this.loadDetail();
		}
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
	if(this._regMode!="I") {
		var assetCd = [
	               { name: 'gisAssetsPrtAtCode', value: this.selectedItem.gisAssetsPrtAtCode},
	               { name: 'gisAssetsCd', value: this.selectedItem.gisAssetsCd },
	               { name: 'gisAssetsSubCd', value: this.selectedItem.gisAssetsSubCd }
	             ];
	 	this.$('#assetCodePhotoList').flexOptions({params:assetCd}).flexReload();
	}
};

GamAssetCodeModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
};

GamAssetCodeModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs('#searchGisAssetCode');
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
								<td><input id="searchGisAssetsCd" data-column-id="gisAssetsCd" type="text" class="gisAssetsCd">-<input id="searchGisAssetsSubCd" data-column-id="gisAssetsSubCd" class="gisAssetsSubCd" type="text"></td>
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
									<input id="searchMngDeptCd" data-column-id="mngDeptCd" class="ygpaDeptSelect" data-default-prompt="전체" />
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
									<input id="searchOperDeptCd" data-column-id="operDeptCd" class="ygpaDeptSelect" data-default-prompt="전체" />
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
					<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="assetCodeList" data-style="default">맵조회</button>
<!-- 					<button data-role="addFearutre" data-gis-layer="gisAssetsCd" data-flexi-grid="assetCodeList" data-code-id="assetCode">위치등록</button> -->
					<button id="addAssetGisCdItem">자산추가</button>
					<button id="removeAssetGisCdItem">삭제</button>
					<button id="btnMapSave" style="display:none;">맵저장</button>
					<button data-role="printPage" data-search-option="searchGisAssetCode" data-url="<c:url value='/code/assets/selectGisAssetCodeListPrint.do'/>">인쇄</button>
					<c:if test="${isAdmin==true }">
					<button data-role="editMap" data-gis-layer="gisAssetsCd">맵편집</button>
					<button id="btnGetAddress">주소지정(Admin)</button>
					</c:if>
					<!-- <button id="storeAutoMapGenerate">위치등록(배치)</button> -->	<!-- 빌드 시 -->
				</div>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: scroll">
				<form id="editGisAssetCode" name="gisAssetCode">
				<table class="editForm">
					<tr>
						<th><span class="label">항구분</span></th>
						<td>
							<input id="gisAssetsPrtAtCode" class="ygpaCmmnCd" data-code-id='GAM019' data-column-label-id='prtAtCodeNm' data-display-code='P' data-required="true"/>
							<input type="hidden" id="beforeGisAssetsPrtAtCode" value="ddd">
						</td>
						<th><span class="label">자산코드</span></th>
						<td colspan="3">
							<input type="text" size="5"  id="gisAssetsCd" disabled="disabled">-
							<input type="text" size="5"  id="gisAssetsSubCd" disabled="disabled">
						</td>
					</tr>
					<!-- <tr>
						<th><span class="label">자산코드</span></th>
						<td colspan="5"><input type="text" size="3"  id="gisAssetsCd" disabled="disabled">-<input type="text" size="2"  id="gisAssetsSubCd" disabled="disabled"></td>
					</tr> -->
					<tr>
						<th><span class="label">ERP자산코드</span></th>
						<td>
							<input type="text" size="1" id="erpAssetsCls">-
							<input type="text" size="8" id="erpAssetsNo">-
							<input type="text" size="2" id="erpAssetsNoSeq">
							<button id="popupErpAssetList" class="popupButton">ERP코드조회</button>
						</td>
						<th><span class="label">ERP자산명</span></th>
						<td colspan="5"><input type="text" size="62" id="itemName" data-column-id="itemName" readonly="readonly"></td>
					</tr>
					<tr>
						<th><span class="label">재산구분</span></th>
						<td>
							<input id="gisAssetsPrprtySeCd" class="ygpaCmmnCd changeAssetPk" data-code-id='GAM001' data-required="true">
						</td>
						<th><span class="label">위치구분</span></th>
						<td>
							<input id="gisAssetsLocCd" class="ygpaCmmnCd changeAssetPk" data-code-id='GAM002' data-required="true">
						</td>
						<th><span class="label">부두구분</span></th>
						<td>
							<input id="gisAssetsQuayCd" class="ygpaCmmnCd changeAssetPk" data-code-id='GAM003' data-required="true">
						</td>
					</tr>
					<tr>
						<th><span class="label">자산명</span></th>
						<td colspan="5"><input type="text" size="133" id="gisAssetsNm" data-required="true"></td>
					</tr>
					<tr>
						<th><span class="label">자산소재지</span></th>
						<td colspan="3">
							<input type="text" size="72" id="gisAssetsLocplc"><button id="selectAddr" class="popupButton">주소조회</button>
							<input type="hidden" id="gisAssetsBupjungdongCd" />
						</td>
						<th><span class="label">지번</span></th>
						<td>
							<input type="text" size="7" id="gisAssetsLnm">-
							<input type="text" size="7" id="gisAssetsLnmSub">
						</td>
					</tr>
					<tr>
					</tr>
					<tr>
						<th><span class="label">자산구분</span></th>
						<td colspan="3">
							<input id="gisAssetsSeCd" class="ygpaCmmnCd" data-code-id='GAM013'>
						</td>
						<th><span class="label">취득가액</span></th>
						<td><input type="text" size="18" id="gisAssetsAcqPri" class="ygpaNumber"> 원</td>
					</tr>
					<tr>
						<th><span class="label">면적</span></th>
						<td><input type="text" size="18" class="ygpaNumber" id="gisAssetsAr" data-column-id="gisAssetsAr" data-decimal-point="2"> ㎡</td>
						<th><span class="label">실제임대면적</span></th>
						<td><input type="text" size="18" class="ygpaNumber" id="gisAssetsRealRentAr" class="ygpaCurrency" data-decimal-point="2"> ㎡</td>
						<th><span class="label">자산규격</span></th>
						<td><input type="text" size="22" id="gisAssetsStndrd"></td>
					</tr>
					<tr>
						<th><span class="label">관리부서</span></th>
						<td><input type="text" size="16" id="gisAssetsMngDeptCd" class="ygpaDeptSelect" data-default-prompt="없음"></td>
						<th><span class="label">운영부서</span></th>
						<td><input type="text" size="16" id="gisAssetsOperDeptCd" class="ygpaDeptSelect" data-default-prompt="없음"></td>
						<th><span class="label">사용여부</span></th>
						<td>
							<select id="gisAssetsUsageYn">
									<!-- <option value="" selected="selected">선택</option> -->
									<option value="Y">사용</option>
									<option value="N">사용안함</option>
							</select>
						</td>
					</tr>
					<tr>
						<th><span class="label">자산가치금액</span></th>
						<td><input type="text" size="13" id="gisAssetsValAmt" class="ygpaNumber"> 원 (조회일자 : <input type="text" size="10" class="emdcal" id="gisAssetsValInqireDt">) </td>
						<th><span class="label">준공년도</span></th>
						<td><input type="text" size="18" id="gisAssetsBlddate"></td>
						<th><span class="label">준공일자</span></th>
						<td><input type="text" size="16" class="emdcal" id="gisAssetsBldDt"></td>
					</tr>
					<tr>
						<th><span class="label">비고</span></th>
						<td colspan="5"><textarea cols="100" rows="3" id="gisAssetsRm"></textarea></td>
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
					<button data-role="addFeature" data-gis-layer="gisAssetsCd" data-value-name="selectedItem">맵편집</button>
					<button id="btnMapSave2" style="display:none;">맵저장</button>
<!-- 					<button data-role="addFearutre" data-gis-layer="gisAssetsCd" data-code-id="selectedItem">위치등록</button> -->
					<button id="btnCancelGisAssetsCode">취소</button>
					<button id="btnSaveGisAssetsCode">저장</button>
					<button id="removeAssetGisCdDetailItem">삭제</button>
				</div>
				</form>
							</div>
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;" data-onactivate="onShowTab3Activate">
				<table id="assetCodePhotoList" style="display:none"></table>
				<div class="emdControlPanel">
					<button id="btnUploadFile">업로드</button>
					<button id="btnDownloadFile">다운로드</button>
					<button id="removeAssetGisPhoto">삭제</button>
					<button id="saveAssetGisPhoto">저장</button>
				</div>
				<form id="editAssetGisPhotoForm" method="post" enctype="multipart/form-data">
					<table class="editForm">
                        <tr>
							<th width="10%" height="18"><span class="label">사진제목</span></th>
                            <td>
                                <input id="photoSj" type="text" size="90" class="photoEditItem" maxlength="40"/>
                            </td>
							<th width="10%" height="18"><span class="label">촬영일자</span></th>
                            <td>
                                <input id="shotDt" type="text" size="15" class="emdcal photoEditItem" readonly>
                            </td>
                        </tr>
                        <tr>
							<th width="10%" height="18"><span class="label">사진파일명</span></th>
                            <td colspan="3">
                                <input id="filenmLogic" type="text" size="60" class="photoEditItem" disabled/>
                            </td>
                        </tr>
					</table>
				</form>
<!--    					<button id="btnApplyPhotoData">적용</button> -->
				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>
