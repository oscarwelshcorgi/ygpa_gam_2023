<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamCustTpSalesSttutsCreat.jsp
  * @Description : 고객군별 통계 프로그램
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.04.10  lsl          최초 생성
  *
  * author lsl
  * since 2013.10.29
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<%-- <validator:javascript formName="gamAssetCode" method="validateGamAssetCode" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetPhoto" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" /> --%>
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="gamCustTpSalesSttutsCreatSearchForm" method="gamCustTpSalesSttutsCreatSearchForm" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamCustTpSalesSttutsCreat() {}

GamCustTpSalesSttutsCreat.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamCustTpSalesSttutsCreat.prototype.loadComplete = function() {

	this._edited=false;	// 편집 상태를 저장 한다.

	this.$('#prtAtCode').val('620');	// 기본 항코드 설정s

	// 테이블 설정
	this.$("#gamCustTpSalesSttutsCreatList").flexigrid({
		module: this,
		url: '<c:url value="/port_mis/selectgamCustTpSalesSttutsCreatList.do"/>',
		dataType: 'json',
		colModel : [
			{display:'항코드', name:'prtcd', width:80, sortable:true, align:'left'},
			{display:'년월', name:'yrmt', width:180, sortable:true, align:'right'},
			{display:'max(생성일자)', name:'maxupdt', width:180, sortable:true, align:'center'},
			{display:'count(*)(항코드,년월일,그룹바이)', name:'cnt', width:250, sortable:true, align:'center'}
			],
		height: 'auto',
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				this.erpAssetCode=this.assetCls+'-'+this.assetNo+'-'+this.assetNoSeq;
			});
			return data;
		}
	});


	this.$("#gamCustTpSalesSttutsCreatList").on('onItemSelected', function(event, module, row, grid, param) {
		//module.$('#addAssetGisCd').attr('disabled', 'disabled');
		//alert('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is selected');
	});

	this.$("#gamCustTpEnpSalesSttutsList").flexigrid({
		module: this,
		url: '<c:url value="/port_mis/selectErpStatisticsList.do"/>',
		colModel : [
					{display:'항코드', name:'prtcd', width:80, sortable:true, align:'center'},
					{display:'업체명', name:'firmKorNm', width:210, sortable:true, align:'center'},
					{display:'년월', name:'yrmt', width:150, sortable:true, align:'center'},
					{display:'요금종류', name:'feeTpKorNm', width:120, sortable:true, align:'center'},
					{display:'매출액', name:'costval', width:100, sortable:true, align:'center'}
			],
		height: '110',
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

	this.$("#gamCustTpEnpSalesSttutsList").on('onItemSelected', function(event, module, row, grid, param) {
		/* module.makeFormValues('#editGisAssetCode', row);
		module._editData=module.getFormValues('#editGisAssetCode', row);
		module._editRow=module.$('#gamCustTpEnpSalesSttutsList').selectedRowIds()[0]; */
	});

	this.$("#gamCustTpEnpSalesSttutsList").on('onLoadDataComplete', function(event, module, data, grid, param) {
		/* module._editRow=null;
		module._deleteDataList=[]; */
	});

	this.$("#gamCustTpShipEnpSalesSttutsList").flexigrid({
		module: this,
		url: '<c:url value="/port_mis/selectShipErpStatisticsList.do"/>',
		colModel : [
				{display:'항코드', name:'prtcd', width:80, sortable:true, align:'center'},
				{display:'업체명', name:'firmKorNm', width:210, sortable:true, align:'center'},
				{display:'년월', name:'yrmt', width:150, sortable:true, align:'left'},
				{display:'요금종류', name:'feeTpKorNm', width:120, sortable:true, align:'center'},
				{display:'매출액', name:'costval', width:100, sortable:true, align:'center'}
			],
		height: '120'
	});

	this.$("#gamCustTpShipEnpSalesSttutsList").on('onItemSelected', function(event, module, row, grid, param) {
		/* module.makeFormValues('#editAssetGisPhotoForm', row);
		module._editPhotoData=module.getFormValues('#editAssetGisPhotoForm', row);
		module._editPhotoRow=module.$('#gamCustTpShipEnpSalesSttutsList').selectedRowIds()[0];

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
		} */
//		console.log('row ' + row['assetCls']+'-'+row['assetNo']+'-'+row['assetNoSeq']+' is selected');
	});

	this.$("#gamCustTpShipEnpSalesSttutsList").on('onLoadDataComplete', function(event, module, data, grid, param) {
		/* module._editPhotoRow=null;
		module._deletePhotoList=[]; */
	});


	/* this.$('#gisAssetsLocCd').on('change', function() {
		//alert($(this).getSelectedCodeLabel() + '이(가) 선택되었습니다.');
	}); */

	// 사진 정보 속성이 변경 된 경우 이벤트 실행
	/* this.$('.photoEditItem').on('change', {module: this}, function(event) {
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

	}); */
};



// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
GamCustTpSalesSttutsCreat.prototype.showModuleAlert = function(msg) {
	//this.getSelect(msg);
	this.$('#prtCode').val(msg);
};


GamCustTpSalesSttutsCreat.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		
        case 'gamCustTpSalesSttutsCreatSearchForm':

	            if( this.$('#grStartYr').val() == '' ) {
	                alert("조회 년을 선택하십시오.");
	                return;
	            }
	            if( this.$('#grStartMn').val() == '' ) {
	                alert("조회 월을 선택하십시오.");
	                return;
	            }

				var searchOpt = this
						.makeFormArgs('#gamCustTpSalesSttutsCreatSearchForm');
				this.$('#gamCustTpSalesSttutsCreatList').flexOptions({
					params : searchOpt
				}).flexReload();

			break;
			
		case 'gamCustTpEnpSalesSttutsSearchForm':
			
			if( this.$('#eGrStartYr').val() == '' ) {
                alert("조회시작 년을 선택하십시오.");
                return;
            }
            if( this.$('#eGrStartMn').val() == '' ) {
                alert("조회시작 월을 선택하십시오.");
                return;
            }
            if( this.$('#sEntrpscd').val() == '' ) {
                alert("업체코드를 입력하십시오.");
                return;
            }
            if( this.$('#sEntrpsNm').val() == '' ) {
                alert("업체명을 입력하십시오.");
                return;
            }
			
			var searchOpt = this
					.makeFormArgs('#gamCustTpEnpSalesSttutsSearchForm');
			this.$('#gamCustTpEnpSalesSttutsList').flexOptions({
				params : searchOpt
			}).flexReload();

			break;

		case 'gamCustTpShipEnpSalesSttutsSearchForm':

			if( this.$('#sEgrStartYr').val() == '' ) {
                alert("조회시작 년을 선택하십시오.");
                return;
            }
            if( this.$('#sEgrStartMn').val() == '' ) {
                alert("조회시작 월을 선택하십시오.");
                return;
            }
            if( this.$('#sEntrpscd').val() == '' ) {
                alert("업체코드를 입력하십시오.");
                return;
            }
            if( this.$('#sEntrpsNm').val() == '' ) {
                alert("업체명을 입력하십시오.");
                return;
            }
			
			var searchOpt = this
					.makeFormArgs('#gamCustTpShipEnpSalesSttutsSearchForm');
			this.$('#gamCustTpShipEnpSalesSttutsList').flexOptions({
				params : searchOpt
			}).flexReload();

			break;

		case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
			/*
			var opts = {
			    'gisAssetsPrtAtCode': this.$('#prtAtCode').val(),
			    'gisAssetsCd': this.$('#gisAssetsCd').val(),
			    'gisAssetsSubCd': this.$('#gisAssetsSubCd').val()
			};
			 */
			var opts;

			this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택',
					'<c:url value="/popup/showEntrpsInfo.do"/>', opts);
			break;

		}
	};

	GamCustTpSalesSttutsCreat.prototype.onClosePopup = function(popupId, msg,
			value) {
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
				this.$('#assetsCdStr').val(
						value.gisAssetsCd + "-" + value.gisAssetsSubCd);
			} else {
				alert('취소 되었습니다');
			}
			break;

		default:
			alert('알수없는 팝업 이벤트가 호출 되었습니다.');
			// throw 0;
			break;
		}
	};

	GamCustTpSalesSttutsCreat.prototype.onTabChangeBefore = function(newTabId,
			oldTabId) {
		if (this._edited) {
			return confirm('탭을 이동 하면 편집 한 내용이 저장 되지 않습니다. 계속 하시겠습니까?');
		}
	}

	/*
	 GamCustTpSalesSttutsCreat.prototype.onUploadFileDone = function(uploadId, result) {
	 $.each(result, function() {
	 this.$)
	 });
	 }
	 */
	GamCustTpSalesSttutsCreat.prototype.onTabChange = function(newTabId,
			oldTabId) {
		this._edited = false;
		switch (newTabId) {
		case 'tabs1':
			this.$('#searchViewStack')[0].changePanelId(0);
			break;
		case 'tabs2':
			this.$('#searchViewStack')[0].changePanelId(1); // 조회 조건 변경
			var selectRow = this.$('#gamCustTpSalesSttutsCreatList')
					.selectedRows();
			this.clearCodePage();
			if (selectRow.length > 0) {
				var row = selectRow[0];
				this.$('#searchGisAssetErpAssetsCls').val(row['assetCls']);
				this.$('#searchGisAssetErpAssetsNo').val(row['assetNo']);
				this.$('#searchGisAssetErpAssetsNoSeq').val(row['assetNoSeq']);
				this._itemNameAsset = row['itemNameAsset'];// 자산명 저장

				// 해당하는 자산 목록을 불러온다/
				var searchOpt = this.makeFormArgs('#searchGisAssetCode');
				//this.showAlert(searchOpt);
				this.$('#gamCustTpEnpSalesSttutsList').flexOptions({
					params : searchOpt
				}).flexReload();
			}
			break;
		case 'tabs3':
			this.$('#searchViewStack')[0].changePanelId(2); // 조회 조건 변경
			var selectRow = this.$('#gamCustTpEnpSalesSttutsList')
					.selectedRows();
			this.clearPhotoPage();
			if (selectRow.length > 0) {
				var row = selectRow[0];
				this.$('#searchGisAssetsPrtAtCode').val(
						row['gisAssetsPrtAtCode']);
				this.$('#searchGisAssetsCd').val(row['gisAssetsCd']);
				this.$('#searchGisAssetsSubCd').val(row['gisAssetsSubCd']);

				this.selectPhotoList();
			} else {
				//alert('선택된 GIS 자산이 없습니다.');
			}
			break;
		}
	};

	/* GamCustTpSalesSttutsCreat.prototype.clearCodePage = function() {
	 this.$('#editGisAssetCode :input').val('');
	 this.$('#gamCustTpEnpSalesSttutsList').flexEmptyData();
	 }; */

	GamCustTpSalesSttutsCreat.prototype.onSubmit = function() {
		//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
		this.loadData();
	};

	GamCustTpSalesSttutsCreat.prototype.loadData = function() {
		var searchOpt = this.makeFormArgs('#searchErpAssetCode');
		//this.showAlert(searchOpt);
		this.$('#gamCustTpEnpSalesSttutsList').flexOptions({
			params : searchOpt
		}).flexReload();
		//	this.$('#assetList').flexOptions(searchOpt).flexReload();
	};

	// 다음 변수는 고정 적으로 정의 해야 함
	var module_instance = new GamCustTpSalesSttutsCreat();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId }'/>
<div class="window_main">
	<div class="emdPanel">
			<div id="searchViewStack" class="viewStack">
				<div class="viewPanel">
					<form id="gamCustTpSalesSttutsCreatSearchForm">
		                <table style="width:100%;" class="searchPanel">
		                    <tbody>
		                        <tr>
		                            <th>항코드</th>
		                            <td width="30%">
		                                <select id="prtAtCode">
		                                    <option value="" selected="selected">선택</option>
		
		                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
		                                        <option value="${prtAtCdItem.code }">${prtAtCdItem.codeNm }</option>
		                                    </c:forEach>
		                                </select>
		                            </td>
		                            <th>조회년월</th>
		                            <td>
		                            	<select id="grStartYr">
		                                    <option value="" selected="selected">년도</option>
		
		                                    <c:forEach  items="${yearsList}" var="yearItem">
		                                        <option value="${yearItem }">${yearItem }</option>
		                                    </c:forEach>
		                                </select>
		                                <select id="grStartMn">
		                                    <option value="" selected="selected">월</option>
		
		                                    <c:forEach  items="${monthsList}" var="monthsItem">
		                                        <option value="${monthsItem }">${monthsItem }</option>
		                                    </c:forEach>
		                                </select>
		                            </td>
		                            <td rowspan="2"><button id="gamCustTpSalesSttutsCreatSearchForm" class="submit buttonSearch">조회</button></td>
		                        </tr>
		                    </tbody>
		                </table>
		            </form>
				</div>
				<div class="viewPanel">
					 <form id="gamCustTpEnpSalesSttutsSearchForm">
		                <table style="width:100%;" class="searchPanel">
		                    <tbody>
		                        <tr>
		                            <th>항코드</th>
		                            <td width="30%">
		                                <select id="prtAtCode">
		                                    <option value="" selected="selected">선택</option>
		
		                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
		                                        <option value="${prtAtCdItem.code }">${prtAtCdItem.codeNm }</option>
		                                    </c:forEach>
		                                </select>
		                            </td>
		                            <th>조회기간</th>
		                            <td>
		                            	<select id="eGrStartYr">
		                                    <option value="" selected="selected">년도</option>
		
		                                    <c:forEach  items="${yearsList}" var="yearItem">
		                                        <option value="${yearItem }">${yearItem }</option>
		                                    </c:forEach>
		                                </select>
		                                <select id="eGrStartMn">
		                                    <option value="" selected="selected">월</option>
		
		                                    <c:forEach  items="${monthsList}" var="monthsItem">
		                                        <option value="${monthsItem }">${monthsItem }</option>
		                                    </c:forEach>
		                                </select>
		                                 ~ 
		                                <select id="eGrEndYr">
		                                    <option value="" selected="selected">년도</option>
		
		                                    <c:forEach  items="${yearsList}" var="yearItem">
		                                        <option value="${yearItem }">${yearItem }</option>
		                                    </c:forEach>
		                                </select>
		                                <select id="eGrEndMn">
		                                    <option value="" selected="selected">월</option>
		
		                                    <c:forEach  items="${monthsList}" var="monthsItem">
		                                        <option value="${monthsItem }">${monthsItem }</option>
		                                    </c:forEach>
		                                </select>
		                            </td>
		                            <td rowspan="2"><button id="gamCustTpEnpSalesSttutsSearchForm" class="submit buttonSearch">조회</button></td>
		                        </tr>
								<tr>
									<th>요금종류</th>
		                            <td>
		                                <input id="chrgeKndCd" type="text" size="10">
		                            </td>
		                            <th>업체명</th>
		                            <td>
		                                <input id="entrpsCd" type="text" size="10"><input id="sEntrpsNm" type="text" size="10" readonly> <button id="popupEntrpsInfo">업체</button>
		                            </td>
								</tr>
		                    </tbody>
		                </table>
		            </form>
				</div>
				<div class="viewPanel">
					 <form id="gamCustTpShipEnpSalesSttutsSearchForm">
		                <table style="width:100%;" class="searchPanel">
		                    <tbody>
		                        <tr>
		                            <th>항코드</th>
		                            <td width="30%">
		                                <select id="prtAtCode">
		                                    <option value="" selected="selected">선택</option>
		
		                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
		                                        <option value="${prtAtCdItem.code }">${prtAtCdItem.codeNm }</option>
		                                    </c:forEach>
		                                </select>
		                            </td>
		                            <th>조회기간</th>
		                            <td>
		                            	<select id="sEgrStartYr">
		                                    <option value="" selected="selected">년도</option>
		
		                                    <c:forEach  items="${yearsList}" var="yearItem">
		                                        <option value="${yearItem }">${yearItem }</option>
		                                    </c:forEach>
		                                </select>
		                                <select id="sEgrStartMn">
		                                    <option value="" selected="selected">월</option>
		
		                                    <c:forEach  items="${monthsList}" var="monthsItem">
		                                        <option value="${monthsItem }">${monthsItem }</option>
		                                    </c:forEach>
		                                </select>
		                                 ~ 
		                                <select id="sEgrEndYr">
		                                    <option value="" selected="selected">년도</option>
		
		                                    <c:forEach  items="${yearsList}" var="yearItem">
		                                        <option value="${yearItem }">${yearItem }</option>
		                                    </c:forEach>
		                                </select>
		                                <select id="sEgrEndMn">
		                                    <option value="" selected="selected">월</option>
		
		                                    <c:forEach  items="${monthsList}" var="monthsItem">
		                                        <option value="${monthsItem }">${monthsItem }</option>
		                                    </c:forEach>
		                                </select>
		                            </td>
		                            <td rowspan="2"><button id="gamCustTpShipEnpSalesSttutsSearchForm" class="submit buttonSearch">조회</button></td>
		                        </tr>
								<tr>
									<th>요금종류</th>
		                            <td>
		                                <input id="chrgeKndCd" type="text" size="10">
		                            </td>
		                            <th>업체명</th>
		                            <td>
		                                <input id="entrpsCd" type="text" size="10"><input id="sEntrpsNm" type="text" size="10" readonly> <button id="popupEntrpsInfo">업체</button>
		                            </td>
								</tr>
		                    </tbody>
		                </table>
		            </form>
				</div>
			</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="portMisManageTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
			<ul>
				<li><a href="#tabs1" class="emdTab">매출액통계생성</a></li>
				<li><a href="#tabs2" class="emdTab">업체별매출액통계</a></li>
				<li><a href="#tabs3" class="emdTab">선사별매출액통계</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
				<table id="gamCustTpSalesSttutsCreatList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<form id="gamCustTpShipEnpSalesSttutsCreatForm">
		                <table style="width:100%;" class="searchPanel">
		                    <tbody>
		                        <tr>
		                            <th>항코드</th>
		                            <td width="30%">
		                                <select id="sPrtAtCode">
		                                    <option value="" selected="selected">선택</option>
		
		                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
		                                        <option value="${prtAtCdItem.code }">${prtAtCdItem.codeNm }</option>
		                                    </c:forEach>
		                                </select>
		                            </td>
		                            <th>생성연월</th>
		                            <td>
		                            	<select id="grCreatYr">
		                                    <option value="" selected="selected">년도</option>
		
		                                    <c:forEach  items="${yearsList}" var="yearItem">
		                                        <option value="${yearItem }">${yearItem }</option>
		                                    </c:forEach>
		                                </select>
		                                <select id="grCreatMn">
		                                    <option value="" selected="selected">월</option>
		
		                                    <c:forEach  items="${monthsList}" var="monthsItem">
		                                        <option value="${monthsItem }">${monthsItem }</option>
		                                    </c:forEach>
		                                </select>
		                            </td>
		                            <td><button id="createBtn" class="submit">생성</button></td>
		                        </tr>
		                    </tbody>
		                </table>
		            </form>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: scroll;" data-onactivate="onShowTab2Activate">
				<table id="gamCustTpEnpSalesSttutsList" style="display:none;" class="fillHeight"></table>
				
			</div>
			<div id="tabs3" class="emdTabPage" data-onactivate="onShowTab3Activate">
				<table id="gamCustTpShipEnpSalesSttutsList" style="display:none" class="fillHeight"></table>
				
			</div>
		</div>
	</div>
</div>
