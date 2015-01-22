<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
* @Class Name : GamMarineCenterRentMngt.jsp
* @Description : 마린센터임대목록관리
* @Modification Information
*
*   수정일         수정자                   수정내용
*  -------    --------    ---------------------------
*  2014.01.10  heroine     최초 생성
*
* author heroine
* since 2014.01.10
*
* Copyright (C) 2013 by LFIT  All right reserved.
*/
%>
<validator:javascript formName="gamAssetRent" method="validateGamAssetRent" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetRentDetail" method="validateGamAssetRentDetail" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetRentFile" method="validateGamAssetRentFile" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<!--
<validator:javascript formName="gamAssetRent" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetRentDetail" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetRentFile" staticJavascript="false" xhtml="true" cdata="false" />
 -->
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetRentMngtModule() {}

GamAssetRentMngtModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetRentMngtModule.prototype.loadComplete = function() {

    // 자산임대 테이블 설정
    this.$("#assetRentMngtList").flexigrid({
        module: this,
        url: '/oper/center/gamSelectMarineCenterRentList.do',
        dataType: 'json',
        colModel : [
					{display:'항코드', name:'prtAtCode',width:40, sortable:false,align:'center'},
                    {display:'항코드명', name:'prtAtCodeNm',width:55, sortable:false,align:'center'},
                    {display:'관리번호', name:'rentMngNo',width:80, sortable:false,align:'center'},
                    {display:'신청업체', name:'entrpscd',width:60, sortable:false,align:'center'},
                    {display:'신청업체명', name:'entrpsNm',width:100, sortable:false,align:'left'},
                    {display:'신청구분', name:'reqstSeCdNm',width:55, sortable:false,align:'center'},
                    {display:'고지방법', name:'nticMthNm',width:55, sortable:false,align:'center'},
                    {display:'신청일자', name:'reqstDt',width:80, sortable:false,align:'center'},
                    {display:'승낙여부', name:'prmisnYn',width:55, sortable:false,align:'center'},
                    {display:'승낙일자', name:'prmisnDt',width:80, sortable:false,align:'center'},
                    {display:'결재상태', name:'sanctnSttusNm',width:55, sortable:false,align:'center'},
                    {display:'총사용시작일', name:'grUsagePdFrom',width:80, sortable:false,align:'center'},
                    {display:'총사용종료일', name:'grUsagePdTo',width:80, sortable:false,align:'center'},
                    {display:'총면적', name:'grAr',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'총사용료', name:'grFee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'총감면사용료', name:'grRdcxptFee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'납부방법', name:'payMthNm',width:55, sortable:false,align:'center'},
                    {display:'분납이자율', name:'payinstIntrrateDisp',width:60, sortable:false,align:'center'},
                    {display:'최초신청일자', name:'frstReqstDt',width:80, sortable:false,align:'center'},
                    {display:'최초승낙일자', name:'frstPrmisnDt',width:80, sortable:false,align:'center'},
                    {display:'결재일시', name:'sanctnDt',width:110, sortable:false,align:'center'},
                    {display:'문서번호', name:'docNo',width:80, sortable:false,align:'left'},
                    {display:'비고', name:'rm',width:200, sortable:false,align:'left'}

                    /*
                    {display:'결재상태', name:'sanctnSttus',width:60, sortable:false,align:'center'},
                    {display:'항코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
                    {display:'관리년도', name:'mngYear',width:100, sortable:false,align:'center'},
                    {display:'관리 번호', name:'mngNo',width:60, sortable:false,align:'center'},
                    {display:'관리 횟수', name:'mngCnt',width:60, sortable:false,align:'center'},
                    {display:'신청 구분 코드', name:'reqstSeCd',width:60, sortable:false,align:'center'},
                    {display:'고지 방법', name:'nticMth',width:60, sortable:false,align:'center'},
                    {display:'문서 번호', name:'docNo',width:60, sortable:false,align:'center'},
                    {display:'비고', name:'rm',width:60, sortable:false,align:'center'},
                    {display:'코멘트', name:'cmt',width:60, sortable:false,align:'center'},
                    {display:'기타', name:'etc',width:60, sortable:false,align:'center'},
                    {display:'등록자', name:'regUsr',width:60, sortable:false,align:'center'},
                    {display:'등록일시', name:'registDt',width:60, sortable:false,align:'center'},
                    {display:'수정자', name:'updUsr',width:60, sortable:false,align:'center'},
                    {display:'수정일시', name:'updtDt',width:60, sortable:false,align:'center'},
                    {display:'총 감면 사용료', name:'grRdcxptFee',width:60, sortable:false,align:'center'},
                    {display:'GIS 코드', name:'gisCd',width:60, sortable:false,align:'center'},
                    {display:'부서코드', name:'deptcd',width:60, sortable:false,align:'center'},
                    {display:'납부방법', name:'payMth',width:60, sortable:false,align:'center'}
                    */
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            module.$('#totalResultCnt').val(data.totalCount);
            module.$('#totalArea').val(data.sumGrAr);
            module.$('#totalUse').val(data.sumGrFee);
            module.$('#totalGrRdcxptFee').val(data.sumGrRdcxptFee);
            $.each(data.resultList, function() {
            	this.payinstIntrrateDisp = this.payinstIntrrate+ ' %';
            });

            return data;
        }
    });

    // 자산임대상세 테이블 설정
    this.$("#assetRentDetailList").flexigrid({
        module: this,
        url: '/oper/center/gamSelectMarineCenterRentDetailList.do',
        dataType: 'json',
        colModel : [
                    {display:'항코드', name:'gisAssetsPrtAtCode',width:40, sortable:false,align:'center'},
                    {display:'항구분', name:'gisAssetsPrtAtCodeNm',width:55, sortable:false,align:'center'},
                    {display:'자산코드', name:'assetsCdStr',width:60, sortable:false,align:'center'},
                    {display:'자산명', name:'gisAssetsNm',width:140, sortable:false,align:'left'},
                    {display:'사용시작', name:'usagePdFrom',width:70, sortable:false,align:'center'},
                    {display:'사용종료', name:'usagePdTo',width:70, sortable:false,align:'center'},
                    {display:'사용료', name:'fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'사용면적', name:'usageAr',width:80, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'적용요율', name:'applcTariffNm',width:80, sortable:false,align:'center'},
                    {display:'면제구분', name:'exemptSeNm',width:60, sortable:false,align:'center'},
                    {display:'소재지', name:'gisAssetsLocplc',width:100, sortable:false,align:'center'}

                    /*
                    {display:'적용요율', name:'applcTariff',width:100, sortable:false,align:'center'},
                    {display:'GIS 자산 코드', name:'gisAssetsCd',width:100, sortable:false,align:'center'},
                    {display:'GIS 자산 SUB 코드', name:'gisAssetsSubCd',width:130, sortable:false,align:'center'},
                    {display:'지번', name:'gisAssetsLnm',width:100, sortable:false,align:'center'},
                    {display:'지번SUB', name:'gisAssetsLnmSub',width:100, sortable:false,align:'center'},
                    {display:'자산면적', name:'gisAssetsAr',width:100, sortable:false,align:'center'},
                    {display:'실제임대면적', name:'gisAssetsRealRentAr',width:100, sortable:false,align:'center'},
                    {display:'사용 목적', name:'usagePurps',width:100, sortable:false,align:'center'},
                    {display:'사용 내역', name:'usageDtls',width:100, sortable:false,align:'center'},
                    {display:'사용 용도 코드', name:'usagePrposCd',width:100, sortable:false,align:'center'},
                    {display:'면제구분', name:'exemptSe',width:100, sortable:false,align:'center'},
                    {display:'면제 사유 코드', name:'exemptRsnCd',width:100, sortable:false,align:'center'},
                    {display:'면제 사유', name:'exemptRsn',width:100, sortable:false,align:'center'},
                    {display:'면제 기간 FROM', name:'exemptPdFrom',width:100, sortable:false,align:'center'},
                    {display:'면제 기간 TO', name:'exemptPdTo',width:100, sortable:false,align:'center'},
                    {display:'산출 내역', name:'computDtls',width:100, sortable:false,align:'center'},
                    {display:'공시지가', name:'olnlp',width:100, sortable:false,align:'center'},
                    {display:'적용 방법', name:'applcMth',width:100, sortable:false,align:'center'},
                    {display:'포장 구분', name:'packSe',width:100, sortable:false,align:'center'},
                    {display:'업체 구분', name:'entrpsSe',width:100, sortable:false,align:'center'},
                    {display:'사용료 계산 구분', name:'feeCalcSe',width:100, sortable:false,align:'center'},
                    {display:'감면 사용료 계산 구분', name:'rdcxptFeeCalcSe',width:100, sortable:false,align:'center'},
                    {display:'감면 사용료', name:'rdcxptFee',width:100, sortable:false,align:'center'},
                    {display:'해지 일자', name:'trmnatDt',width:100, sortable:false,align:'center'},
                    {display:'해지 사유', name:'trmnatRsn',width:100, sortable:false,align:'center'},
                    {display:'GIS 코드', name:'gisCd',width:100, sortable:false,align:'center'},
                    {display:'등록자', name:'regUsr',width:100, sortable:false,align:'center'},
                    {display:'등록일시', name:'registDt',width:100, sortable:false,align:'center'},
                    {display:'수정자', name:'updUsr',width:100, sortable:false,align:'center'},
                    {display:'수정일시', name:'updtDt',width:100, sortable:false,align:'center'},
                    {display:'GIS 자산 항코드', name:'gisAssetsPrtAtCode',width:100, sortable:false,align:'center'},
                    {display:'관리 년도', name:'mngYear',width:100, sortable:false,align:'center'},
                    {display:'관리 번호', name:'mngNo',width:100, sortable:false,align:'center'},
                    {display:'관리 횟수', name:'mngCnt',width:100, sortable:false,align:'center'},
                    {display:'부두코드', name:'quayCd',width:100, sortable:false,align:'center'}
                    */
                    ],
        showTableToggleBtn: true,
        height: '102'
    });

    // 첨부파일 테이블 설정
    this.$("#assetRentFileList").flexigrid({
        module: this,
        url: '/oper/center/gamSelectMarineCenterRentFileList.do',
        dataType: 'json',
        colModel : [
                    {display:'순번', name:'photoSeq', width:80, sortable:true, align:'center'},
                    {display:'사진제목', name:'photoSj', width:200, sortable:true, align:'left'},
                    {display:'사진파일명', name:'filenmLogic', width:200, sortable:true, align:'left'},
                    {display:'사진설명', name:'photoDesc', width:250, sortable:true, align:'left'},
                    {display:'촬영일자', name:'shotDt', width:100, sortable:true, align:'center'}

                    /*
                    {display:'파일명(물리)', name:'filenmPhysicl', width:200, sortable:true, align:'left'},
                    {display:'항코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
                    {display:'관리년도', name:'mngYear',width:60, sortable:false,align:'center'},
                    {display:'관리번호', name:'mngNo',width:60, sortable:false,align:'center'},
                    {display:'관리횟수', name:'mngCnt',width:60, sortable:false,align:'center'},
                    {display:'등록자', name:'regUsr', width:160, sortable:true, align:'center'},
                    {display:'등록일', name:'registDt', width:160, sortable:true, align:'center'}
                    */
                    ],
        showTableToggleBtn: false,
        height: '160'
    });

    this.$("#assetRentMngtList").on('onItemSelected', function(event, module, row, grid, param) {
        module.$('#cmd').val('modify');

        module.$('#gamAssetRentForm :input').val('');

        module.makeFormValues('#gamAssetRentForm', row);
        module._editData=module.getFormValues('#gamAssetRentForm', row);
        module._editRow=module.$('#assetRentMngtList').selectedRowIds()[0];

        //해당하는 자산임대상세 목록과 파일목록를 불러온다.
        module.$('#detailPrtAtCode').val(row['prtAtCode']);
        module.$('#prtAtCodeStr').val(row['prtAtCode']);
        module.$('#detailMngYear').val(row['mngYear']);
        module.$('#detailMngNo').val(row['mngNo']);
        module.$('#detailMngCnt').val(row['mngCnt']);

        if( row['prmisnYn'] == 'Y' ) {
            module.$('#entrpscd').attr('readonly', true);
            module.$('#popupEntrpsInfoInput').attr('disabled', 'disabled');
        }

        var searchOpt=module.makeFormArgs('#gamAssetRentForm');
        module.$('#assetRentDetailList').flexOptions({params:searchOpt}).flexReload();
        module.$('#assetRentFileList').flexOptions({params:searchOpt}).flexReload();

        module.calcFirstPaymentAmount();	//  고지방법에 따른 1회차 사용료 적용
        module.loadEntrpsChargerList();	// 담당자 목록을 불러온다.

        //this._deleteDataFileList=[]; //삭제파일목록 초기화
    });

    this.$("#assetRentDetailList").on('onItemSelected', function(event, module, row, grid, param) {
        //module.$('#btnApplyGisAssetsCode').prop('disabled', false);
        module.$('#gamAssetRentDetailForm :input').val('');

        module.makeFormValues('#gamAssetRentDetailForm', row);
        module._editData=module.getFormValues('#gamAssetRentDetailForm', row);
        module._editRow=module.$('#assetRentDetailList').selectedRowIds()[0];

        module.loadEntrpsChargerList();	// 담당자 목록을 불러온다.

    });

    this.$("#assetRentFileList").on('onItemSelected', function(event, module, row, grid, param) {
        module.makeFormValues('#gamAssetRentFileForm', row);
        module._editDataFile=module.getFormValues('#gamAssetRentFileForm', row);
        module._editRowFile=module.$('#assetRentFileList').selectedRowIds()[0];

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
                module.$("#previewImage").attr('src', '');
            }
        }
    });

    this.$("#assetRentMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#assetRentListTab").tabs("option", {active: 1});
        module.$('#cmd').val('modify');
//      module.$('#gamAssetRentForm :input').val('');	// makeFormValues 에서 값을 지우므로 특별 한 일이 없는 한 각각 지우지 않는다. 클래스 특성을 반영 못 하는 경우가 생긴다

        module.makeFormValues('#gamAssetRentForm', row);
        module._editData=module.getFormValues('#gamAssetRentForm', row);
        module._editRow=module.$('#assetRentMngtList').selectedRowIds()[0];

        if(row!=null) {
            module.$('#cmd').val('modify');
        }

        module.calcFirstPaymentAmount();	//  고지방법에 따른 1회차 사용료 적용
        //this._deleteDataFileList=[]; //삭제파일목록 초기화
    });

    this.$("#assetRentDetailList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#assetRentListTab").tabs("option", {active: 2});
        module.$('#gamAssetRentDetailForm :input').val('');
        module.makeFormValues('#gamAssetRentDetailForm', row);
        module._editData=module.getFormValues('#gamAssetRentDetailForm', row);
        module._editRow=module.$('#assetRentDetailList').selectedRowIds()[0];

        module.loadOlnlpList(row);

        if(row!=null) {
            module.$('#detailCmd').val('modify');
        }
    });

    // 컴포넌트이 이벤트를 추가한다. (기존 코드 데이터에 선택 값이 onchange 안되는 점을 수정 함)
    this.$('#prtAtCode').on('change', {module: this}, function(event) {
        event.data.module.$('#prtAtCodeStr').val($(this).val());
        //alert($(this).getSelectedCodeLabel() + '이(가) 선택되었습니다.');
    });

    this.$('#applcTariff').on('change', {module: this}, function(event) {
/*     	if( $(this).val() == '1' ) {
    		event.data.module.$('#applcTariffStr').val("0.05");
    	} else if( $(this).val() == '2' ) {
    		event.data.module.$('#applcTariffStr').val("0.025");
    	} else if( $(this).val() == '3' ) {
    		event.data.module.$('#applcTariffStr').val("0.01");
    	} else {
    		event.data.module.$('#applcTariffStr').val("");
    	}
 */
        if( $(this).val() == '' ) {
            event.data.module.$('#applcTariffNm').val("");
        } else {
            event.data.module.$('#applcTariffNm').val($(this).getSelectedCodeLabel());
        }
    });

    this.$('#exemptSe').on('change', {module: this}, function(event) {
        event.data.module.$('#exemptSeStr').val($(this).val());

        if( $(this).val() != '1' ) {
            event.data.module.$('#exemptPdFrom').val("");
            event.data.module.$('#exemptPdTo').val("");
        }
    });

    this.$('#applcMth').on('change', {module: this}, function(event) {
        var m = event.data.module;
        m.onApplcMthChange(m.$('#applcMth').val());
    });

	// 국유재산법
	this.$('.nationAssetLaw').show();
	this.$('.tradePortLaw').hide();

    this.$('.calcInput').on('change keyup', {module: this}, function(event) {
        var m = event.data.module;
        m.onCalc();
    });

    this.$('#nticMth').on('change', {module: this}, function(event) {
        //alert($(this).val());
        if( $(this).val() != '' && $(this).val() != '1' ) {
        	event.data.module.$('#cofixList').val( event.data.module.$('#blceStdrIntrrate').val() );
        	event.data.module.$('#payinstIntrrate').val(Number(event.data.module.$('#cofixList').val()) * 100);
        } else {
        	event.data.module.$('#cofixList').val("");
        	event.data.module.$('#payinstIntrrate').val("");
        }
        // calc first payment amount
        event.data.module.calcFirstPaymentAmount();
    });

    this.$('#payinstIntrrate').on('change keyup', {module: this}, function(event) {
        event.data.module.calcFirstPaymentAmount();
    });
    this.$('#cofixList').on('change', {module: this}, function(event) {
    	//alert('||'+$(this).val()+'||');
        if( $(this).val() == '' ) {
        	event.data.module.$('#payinstIntrrate').val("");
        } else {
        	var payinstIntrrateCal = (Number($(this).val()) * 100) + "";

        	if(payinstIntrrateCal.length > 4) {
        		payinstIntrrateCal = payinstIntrrateCal.substring(0,5);
        		payinstIntrrateCal = Number(payinstIntrrateCal).toFixed(2);
        	}

        	event.data.module.$('#payinstIntrrate').val( payinstIntrrateCal );
        	var e = jQuery.Event( "change" );
        	e.data ={module: event.data.module};

        	event.data.module.$('#payinstIntrrate').trigger(e);
        }
    });

    this.$('#exemptRsnCd').on('change', {module: this}, function(event) {
        event.data.module.$('#exemptRsnCdStr').val($(this).val());
    });

    this.$(".photoEditItem").bind("keyup change", {module: this}, function(event) {
    	// // console.log("keyup or change event occur");
    	event.data.module.applyPhotoData();
    });

	var today = new Date();

	var serchYr = today.getFullYear();
	var serchMn = today.getMonth() + 1;

	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}

	var serchday = today.getDate();
	if(serchday < 10){
		serchday = "0" + serchday;
	}
	var searchEndDate = serchYr + "-" + serchMn + "-" + serchday;

	today.setMonth(today.getMonth() - 3);

	serchYr = today.getFullYear();
	serchMn = today.getMonth() + 1;
	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}
	serchday = today.getDate();
	if(serchday < 10){
		serchday = "0" + serchday;
	}

	var searchStartDate = serchYr + "-" + serchMn + "-" + serchday;

	this.$("#sGrUsagePdFrom").val(searchStartDate);
	this.$("#sGrUsagePdTo").val(searchEndDate);

};

GamAssetRentMngtModule.prototype.loadEntrpsChargerList = function() {
	var entrpsCd=this.$('#entrpscd').val();
	this.$('#chargerTlphonNo').text('');
	this.$('#chargerMoblphonNo').text('');
	if(entrpsCd!=null && entrpsCd.length>0) {
		//var loadOpt = [{name: 'entrpscd', value: entrpsCd}];
		var loadOpt = {'entrpscd': entrpsCd};
	    this.doAction('/asset/rent/selectEntrpsChargerList.do', loadOpt, function(module, result) {
	    	console.log('charger list load completed');
	        if(result.resultCode=='0') {
		       	 var selectCharger = module.$('#selectCharger');
		       	selectCharger.off('change');
		       	selectCharger.empty();
		       	selectCharger.append('<option value="">선택</option>')
		       	 $.each(result.resultList, function() {
		       		selectCharger.append('<option value="'+this.chargerNo+'" data-moblphonno="'+this.chargerMoblphonNo+'" data-tlphonno="'+this.chargerTlphonNo+'" data-fax="'+this.chargerFax+'" data-email="'+ +this.chargerEmail+'">'+this.chargerNm+'</option>')
		       	 });
		       	selectCharger.on('change', {module: module}, function(event) {
		       		var sel = $(this).children(':selected');
		       		var m = event.data.module;
		       		console.log('charger selected : '+sel);
		   			m.$('#chargerNo').val(sel.val());
		   			if(sel.val()!=null && sel.val()!='') {
		   				var tlNo=sel.data('tlphonno');
		   				if(sel.data('fax')!=null && sel.data('fax').length>0) {
		   					tlNo+= ', FAX:'+sel.data('fax');
		   				}
		   				m.$('#chargerTlphonNo').text(tlNo);
		   				m.$('#chargerMoblphonNo').text(sel.data('moblphonno'));
		   			}
		   		 });
		       	var selected = module.$('#chargerNo').val();
		       	if(selected!=null && selected!='') {
		       		selectCharger.val(selected);
		       		var sel = selectCharger.children(':selected');
		   			if(sel.val()!=null && sel.val()!='') {
		   				var tlNo=sel.data('tlphonno');
		   				if(sel.data('fax')!=null && sel.data('fax').length>0) {
		   					tlNo+= ' FAX:'+sel.data('fax');
		   				}
		   				module.$('#chargerTlphonNo').text(tlNo);
		   				module.$('#chargerMoblphonNo').text(sel.data('moblphonno'));
		   			}
		        }
	        }
	    });
	}
};

GamAssetRentMngtModule.prototype.calcFirstPaymentAmount = function() {
	var firstAmt=0;
	var nticMth=this.$('#nticMth').val();
	var totalAmount=Number(this.$('#grFee').val().replace(/,/gi, ""));
	var payinstIntrrate=Number(this.$('#payinstIntrrate').val().replace(/,/gi, ""))/100;
    var grUsagePdFrom = new Date(Date.parse(this.$('#grUsagePdFrom').val())); //총사용기간FROM
    var grUsagePdTo = new Date(Date.parse(this.$('#grUsagePdTo').val())); //총사용기간To
    var fromDt, toDt;
	var totalMonths = grUsagePdTo.getMonth() - grUsagePdFrom.getMonth()+1;
	var totalDays = Math.floor((grUsagePdTo-grUsagePdFrom) / (1000*60*60*24))+1;
    var nDays;
//    console.log("calc Start : "+ nticMth);
	switch(nticMth) {
	case '2':	// 반기납
		fromDt = grUsagePdFrom;
		toDt = new Date(fromDt);
		toDt.setMonth(fromDt.getMonth()+6);
//		toDt.setDate(1);
		toDt=toDt-(1000*60*60*24);
		if(6>=totalMonths) firstAmt=totalAmount;
		else {
			//nDays = Math.floor((toDt-fromDt) / (1000*60*60*24))+1;
			//firstAmt=Math.floor(totalAmount*nDays/totalDays/10)*10;
			firstAmt = totalAmount*6/totalMonths;
		}
		// 이자율 계산
		firstAmt += (totalAmount-firstAmt) * (payinstIntrrate) /2;
		firstAmt = Math.floor(firstAmt);
		break;
	case '3':	// 3분납
		fromDt = grUsagePdFrom;
		toDt = new Date(fromDt);
		if(grUsagePdFrom.getMonth()<4) {
			toDt.setMonth(4);
		}
		else if(grUsagePdFrom.getMonth()<8) {
			toDt.setMonth(8);
		}
		else  {
			toDt.setFullYear(toDt.getFullYear()+1);
			toDt.setMonth(0);
		}
//		toDt.setDate(1);
		toDt=toDt-(1000*60*60*24);
		if(4>=totalMonths) firstAmt=totalAmount;
		else {
//			nDays = Math.floor((toDt-fromDt) / (1000*60*60*24))+1;
			firstAmt=totalAmount*4/totalMonths;
		}
		// 이자율 계산
		firstAmt += (totalAmount-firstAmt) * (payinstIntrrate) /3;
		firstAmt = Math.floor(firstAmt);
		break;
	case '4':	// 분기납
		fromDt = grUsagePdFrom;
		toDt = new Date(fromDt);
		if(grUsagePdFrom.getMonth()<3) {
			toDt.setMonth(3);
		}
		else if(grUsagePdFrom.getMonth()<6) {
			toDt.setMonth(6);
		}
		else if(grUsagePdFrom.getMonth()<9) {
			toDt.setMonth(9);
		}
		else {
			toDt.setFullYear(toDt.getFullYear()+1);
			toDt.setMonth(0);
		}
//		toDt.setDate(1);
		toDt=toDt-(1000*60*60*24);
		if(3>=totalMonths) firstAmt=totalAmount;
		else {
			nDays = Math.floor((toDt-fromDt) / (1000*60*60*24))+1;
			firstAmt=Math.floor(totalAmount*3/totalMonths);
		}
		firstAmt += (totalAmount-firstAmt) * (payinstIntrrate) /4;
//		firstAmt = Math.floor(firstAmt/10)*10;
		break;
	case '5':	// 월납
		fromDt = grUsagePdFrom;
		toDt = new Date(fromDt);
		toDt.setMonth(grUsagePdFrom.getMonth()+1);
//		toDt.setDate(1);
		toDt=toDt-(1000*60*60*24);
		if(1>=totalMonths) firstAmt=totalAmount;
		else {
			nDays = Math.floor((toDt-fromDt) / (1000*60*60*24))+1;
			firstAmt=Math.floor(totalAmount*1/totalMonths);
//			firstAmt=totalAmount*1-firstAmt
		}
		firstAmt += (totalAmount-firstAmt) * (payinstIntrrate) /12;
		firstAmt = Math.floor(firstAmt);
		break;
	default:
		firstAmt=totalAmount;
		fromDt=grUsagePdFrom;
		toDt=grUsagePdFrom;
		nDays = Math.floor((grUsagePdTo-grUsagePdFrom) / (1000*60*60*24))+1;
		break;
	}
    console.log("calc first Amount : "+ firstAmt);

	this.$('#firstPayVal').val($.number(firstAmt));
	//this.$('#firstPayValStr').text('산출내역 : 사용기간 ['+EMD.util.getDate(fromDt)+'~'+EMD.util.getDate(toDt)+'] : 사용일수 : '+$.number(nDays)+' 일/ 전체일수 : ' +$.number(totalDays)+' 일 * 사용료 : '+$.number(totalAmount)+'원');
};

GamAssetRentMngtModule.prototype.onApplcMthChange = function(applcMth) {
	console.log("applc mth change");
	switch(applcMth) {
	case '1':	// 국유재산법
		this.$('.nationAssetLaw').show();
		this.$('.tradePortLaw').hide();
		break;
	case '2':	// 공사규정
		this.$('.nationAssetLaw').show();
		this.$('.tradePortLaw').hide();
		break;
	case '3':	// 입찰
		this.$('.nationAssetLaw').show();
		this.$('.tradePortLaw').hide();
		break;
	case '4':	// 무역항규정
		this.$('.nationAssetLaw').hide();
		this.$('.tradePortLaw').show();
		break;
	case '5':	// 임대계약서
		this.$('.nationAssetLaw').show();
		this.$('.tradePortLaw').hide();
		this.$('#applcTariff').val('0');
//		this.$('#applcTariff_select').f('0');
		break;
	default:	// 기타
		this.$('.nationAssetLaw').show();
		this.$('.tradePortLaw').hide();
		break;
	}
    this.onCalc();
}

GamAssetRentMngtModule.prototype.calcNationAssetLaw = function() {
    if( this.$('#olnlp').val() != '' && this.$('#usagePdFrom').val() != '' && this.$('#usagePdTo').val() != ''
        && this.$('#usageAr').val() != '' && this.$('#applcTariff').val() != '' && this.$('#exemptSe').val() != ''
    ) {
        var calFee      = 0;  //계산된 사용료
        var olnlp       = 0;  //공시지가
        var usageAr     = 0;  //사용면적
        var applcTariff = 0;  //적용요율(계산용)
        var applcTariffStr = "";  //적용요율
        var rdcxptFee   = 0;  //감면사용료
        var dayUseCnt   = 0;  //사용일수
        var usagePdFrom = ""; //사용기간 from
        var usagePdTo   = ""; //사용기간 to
        var exemptPdFrom = "";    // 면제기간
        var exemptPdTo = "";      // 면제기간
        var exemptCnt   = 0;      // 면제일수
        var exemptSe    = ""; //면제구분 0:면제없음, 1:일부면제, 2:전체면제

        olnlp = this.$('#olnlp').val().replace(/,/g, '')*1;
        usageAr = Number(this.$('#usageAr').val().replace(/,/g, ''));
        applcTariff = Number(this.$('#applcTariff').val().replace(/,/g, ''));
        applcTariffStr = this.$('#applcTariff').getSelectedCodeLabel();
        usagePdFrom = this.$('#usagePdFrom').val();
        usagePdTo = this.$('#usagePdTo').val();
        exemptPdFrom = this.$('#exemptPdFrom').val();
        exemptPdTo = this.$('#exemptPdTo').val();
        exemptSe = this.$('#exemptSe').val();

        var monfee=Math.round(olnlp*applcTariff/12);

        if( exemptSe == '1' ) {        // 일부면제
              if( exemptPdFrom == '' ) {
                  return;
              }
              if( exemptPdTo == '' ) {
                  return;
              }
        }

        if( exemptSe == '0' ) {               // 면제없음.
            rdcxptFee = 0;
        } else if( exemptSe == '1' ) {   // 일부면제
        	var dtFr = EMD.util.strToDate(exemptPdFrom);
        	var dtTo = EMD.util.strToDate(exemptPdTo);

        	var days = Math.round(Math.abs((dtTo-dtFr)/(1000*60*60*24)));

            /* 면제 일수 계산 */
            exemptCnt = Number(days);

            exemptMonths = this.calcMonth(dtFr, dtTo);

            rdcxptFee = monfee*usageAr*exemptMonths.month+monfee*usageAr*exemptMonths.day/exemptMonths.lastMonthDay;
        }

        /* 날짜계산 */

         var dtFr = EMD.util.strToDate(usagePdFrom);
        var dtTo = EMD.util.strToDate(usagePdTo);

            usageMonths = this.calcMonth(dtFr, dtTo);


        //(사용료 = 공시지가*((사용일수)/365)*사용면적)*적용요율 ? 감면사용료 )
        if( exemptSe == '2' ) {     // 전체면제 일 경우 사용료는 0
        	rdcxptFee = calFee;
        	exemptCnt = dayUseCnt;
            calFee = 0;
        } else {
        	calFee = monfee*usageAr*usageMonths.month+monfee*usageAr*usageMonths.day/usageMonths.lastMonthDay - rdcxptFee;
        }
        var calcStr="";
        if(usageMonths.month) {
        	if(usageMonths.day) {
        		calcStr="( 공시지가("+$.number(olnlp, false)+"원)*적용요율("+applcTariff*1000+"/1000)*사용면적("+$.number(usageAr, false)+"m²)*(사용개월수("+$.number(usageMonths.month, false)+"개월 "+usageMonths.day+"일/"+usageMonths.lastMonthDay+")";
        	}
        	else {
        		calcStr="( 공시지가("+$.number(olnlp, false)+"원)*적용요율("+applcTariff*1000+"/1000)*사용면적("+$.number(usageAr, false)+"m²)*(사용개월수("+$.number(usageMonths.month, false)+"개월)";
        	}
        }
        else {
    		calcStr="( 공시지가("+$.number(olnlp, false)+"원)*적용요율("+applcTariff*1000+"/1000)*사용면적("+$.number(usageAr, false)+"m²)*(사용개월수("+$.number(usageMonths.month, false)+"개월)";

        }
        if(rdcxptFee>0) {
			if(exemptMonths.month) {
				if(exemptMonths.day) {
		    		calcStr+="-( 면제개월수("+exemptMonths.month+"개월 "+exemptMonths.day+"일/"+exemptMonths.lastMonthDay+")";

				}
				else {
		    		calcStr+="-( 면제개월수("+exemptMonths.month+"개월)";
				}
			}
			else {
	    		calcStr+="-( 면제일수("+exemptMonths.day+"일/"+exemptMonths.lastMonthDay+")";
			}
			calcStr += " 면제 금액 : "+$.number(rdcxptFee, false)+"원)";
        }
        calcStr += " )";
    	this.$('#computDtls').val(calcStr);

/*         calFee = Math.floor(calFee/10)*10;
        rdcxptFee = Math.floor(rdcxptFee/10)*10;

        //(사용료 = 공시지가*((사용일수)/365)*사용면적)*적용요율 ? 감면사용료 )
        if( exemptSe == '2' ) {     // 전체면제 일 경우 사용료는 0
        	rdcxptFee = calFee;
        	exemptCnt = dayUseCnt;
            calFee = 0;
        } else {
            calFee = olnlp*dayUseCnt/365*usageAr*applcTariff - rdcxptFee;
        }
        this.$('#computDtls').val("( 공시지가("+$.number(olnlp, false)+"원)*사용면적("+$.number(usageAr, false)+"m²)*(사용일수("+$.number(dayUseCnt, false)+"일)-면제일수("+$.number(exemptCnt, false)+"일) ) / 365 * "+applcTariffStr);
 */
        calFee = Math.ceil(calFee/10)*10;
        rdcxptFee = Math.ceil(rdcxptFee/10)*10;

        this.$('#fee').val($.number(calFee));
        this.$('#rdcxptFee').val($.number(rdcxptFee));
    } else {
        var applcTariff = Number(this.$('#applcTariff').val().replace(/,/g, ''));
		if(applcTariff!=0) {
	    	this.$('#fee').val('');
	        this.$('#rdcxptFee').val('');
		}
    }

};

GamAssetRentMngtModule.prototype.calcMonth = function(dtFrom, dtTo) {
	var retval={month: 0, day: 0, lastMonthDay: 31};

	var months = 0;
	var dtCurr=new Date(dtFrom);

	dtTo.setDate(dtTo.getDate()+1);	// 날짜를 하루 뒤로 계산 한다.
	dtCurr.setMonth(dtCurr.getMonth()+1);
	for(;dtCurr<=dtTo;) {
		months++;
		dtCurr.setMonth(dtCurr.getMonth()+1);
	}
	var newMonth = new Date(dtCurr.getFullYear(), dtCurr.getMonth(), 1, 0, 0, 0);

	retval.lastMonthDay=new Date(newMonth-24*60*60*1000).getDate();

	dtCurr.setMonth(dtCurr.getMonth()-1);

	retval.month=months;
	retval.day = Math.floor(Math.abs((dtTo-dtCurr)/(1000*60*60*24)));

	return retval;
}

GamAssetRentMngtModule.prototype.calcTradePortLaw = function() {
    if( this.$('#usagePdFrom').val() != '' && this.$('#usagePdTo').val() != ''
        && this.$('#usageAr').val() != '' && this.$('#applcPrice').val() != '' && this.$('#exemptSe').val() != ''
    ) {
        var calFee      = 0;  //계산된 사용료
        var olnlp       = 0;  //공시지가
        var usageAr     = 0;  //사용면적
        var applcPrice = 0;  //적용단가
        var rdcxptFee   = 0;  //감면사용료
        var dayUseCnt   = 0;  //사용일수
        var usagePdFrom = ""; //사용기간 from
        var usagePdTo   = ""; //사용기간 to
        var exemptPdFrom = "";    // 면제기간
        var exemptPdTo = "";      // 면제기간
        var exemptMonths={};      // 면제개월 수
        var exemptSe    = ""; //면제구분 0:면제없음, 1:일부면제, 2:전체면제

        usageAr = Number(this.$('#usageAr').val().replace(/,/g, ''));
        applcPrice = Number(this.$('#applcPrice').val().replace(/,/g, ''));
        usagePdFrom = this.$('#usagePdFrom').val();
        usagePdTo = this.$('#usagePdTo').val();
        exemptPdFrom = this.$('#exemptPdFrom').val();
        exemptPdTo = this.$('#exemptPdTo').val();
        exemptSe = this.$('#exemptSe').val();

        if( exemptSe == '1' ) {        // 일부면제
              if( exemptPdFrom == '' ) {
                  return;
              }
              if( exemptPdTo == '' ) {
                  return;
              }
        }

        if( exemptSe == '0' ) {               // 면제없음.
            rdcxptFee = 0;
        } else if( exemptSe == '1' ) {   // 일부면제
        	var dtFr = EMD.util.strToDate(exemptPdFrom);
        	var dtTo = EMD.util.strToDate(exemptPdTo);

        	var days = Math.round(Math.abs((dtTo-dtFr)/(1000*60*60*24)));

            /* 면제 일수 계산 */
            exemptCnt = Number(days);

            exemptMonths = this.calcMonth(dtFr, dtTo);

            rdcxptFee = applcPrice * exemptMonths.month * usageAr +applcPrice * exemptMonths.day/exemptMonths.lastMonthDay * usageAr;
        }

        /* 날짜계산 */

             	var dtFr = EMD.util.strToDate(usagePdFrom);
        	var dtTo = EMD.util.strToDate(usagePdTo);

        	usageMonths = this.calcMonth(dtFr, dtTo);

        //(사용료 = 공시지가*((사용일수)/365)*사용면적)*적용요율 ? 감면사용료 )
        if( exemptSe == '2' ) {     // 전체면제 일 경우 사용료는 0
        	rdcxptFee = calFee;
        	exemptCnt = dayUseCnt;
            calFee = 0;
        } else {
        	calFee = applcPrice*usageMonths.month*usageAr+applcPrice*usageMonths.day/usageMonths.lastMonthDay*usageAr - rdcxptFee;
        }
        var calcStr="";
        if(usageMonths.month) {
        	if(usageMonths.day) {
        		calcStr="( 적용단가("+$.number(applcPrice, false)+"원)*사용면적("+$.number(usageAr, false)+"m²)*(사용개월수("+$.number(usageMonths.month, false)+"개월 "+usageMonths.day+"일/"+usageMonths.lastMonthDay+")";
        	}
        	else {
        		calcStr="( 적용단가("+$.number(applcPrice, false)+"원)*사용면적("+$.number(usageAr, false)+"m²)*(사용개월수("+$.number(usageMonths.month, false)+"개월)";
        	}
        }
        else {
    		calcStr="( 적용단가("+$.number(applcPrice, false)+"원)*사용면적("+$.number(usageAr, false)+"m²)*(사용개월수("+$.number(usageMonths.month, false)+"개월)";

        }
        if(rdcxptFee>0) {
			if(exemptMonths.month) {
				if(exemptMonths.day) {
		    		calcStr+="-( 면제개월수("+exemptMonths.month+"개월 "+exemptMonths.day+"일/"+exemptMonths.lastMonthDay+")";

				}
				else {
		    		calcStr+="-( 면제개월수("+exemptMonths.month+"개월)";
				}
			}
			else {
	    		calcStr+="-( 면제일수("+exemptMonths.day+"일/"+exemptMonths.lastMonthDay+")";
			}
			calcStr += " 면제 금액 : "+$.number(rdcxptFee, false)+"원)";
        }
        calcStr += " )";
    	this.$('#computDtls').val(calcStr);

        calFee = Math.floor(calFee/10)*10;
        rdcxptFee = Math.floor(rdcxptFee/10)*10;

        this.$('#fee').val($.number(calFee));
        this.$('#rdcxptFee').val($.number(rdcxptFee));
    } else {
        applcTariff = Number(this.$('#applcTariff').val().replace(/,/g, ''));
		if(applcTariff!=0) {
	    	this.$('#fee').val('');
	        this.$('#rdcxptFee').val('');
		}
    }
};

GamAssetRentMngtModule.prototype.onCalc = function() {
	switch(this.$('#applcMth').val()) {
	case '1':	// 국유재산법
		this.calcNationAssetLaw();
		break;
	case '2':	// 공사규정
		break;
	case '3':	// 입찰
		break;
	case '4':	// 무역항규정
		this.calcTradePortLaw();
		break;
	case '5':	// 임대계약서
		break;
	case '9':	// 기타
		break;
	}
};

GamAssetRentMngtModule.prototype.calcRentMasterValues = function() {
    /* 총사용료, 총면적 계산 시작 */
    var fee = 0;
    var rdcxptFee = 0;
    var usageAr = 0;
    var usagePdFrom = 0;
    var usagePdTo = 0;
    var minUsagePdFrom = 0;
    var maxUsagePdTo = 0;

    var detailList = this.$('#assetRentDetailList').flexGetData();

    if(detailList==undefined || detailList.length==0) {
        this.$('#grFee').val( "" ); //총사용료
        this.$('#grRdcxptFee').val( "" ); //총감면사용료
        this.$('#grAr').val( "" ); //총사용면적
        this.$('#grUsagePdFrom').val( "" ); //총사용기간FROM
        this.$('#grUsagePdTo').val( "" ); //총사용기간FROM
        }

    for( var i = 0 ; i < detailList.length ; i++ ) {
        var row = detailList[i];

        if( row['fee'] != '' && row['fee'] != null ) {
            var feeStr = row['fee']+"";
            feeStr = feeStr.replace(/,/g,"");
            fee += Number(feeStr);
        }

        if( row['rdcxptFee'] != '' && row['rdcxptFee'] != null ) {
            var rdcxptFeeStr = row['rdcxptFee']+"";
            rdcxptFeeStr = rdcxptFeeStr.replace(/,/g,"");
            rdcxptFee += Number(rdcxptFeeStr);
        }

        if( row['usageAr'] != '' && row['usageAr'] != null ) {
            var usageArStr = row['usageAr']+"";
            usageArStr = usageArStr.replace(/,/g,"");
            usageAr += Number(usageArStr);
        }

        if( row['usagePdFrom'] != '' && row['usagePdFrom'] != null ) {
            var usagePdFromStr = row['usagePdFrom']+"";
            usagePdFromStr = usagePdFromStr.replace(/-/g,"");
            usagePdFrom = Number(usagePdFromStr);

            if( minUsagePdFrom == 0 ) {
                minUsagePdFrom = usagePdFrom;
            } else {
                if( minUsagePdFrom > usagePdFrom ) {
                    minUsagePdFrom = usagePdFrom;
                }
            }
        }

        if( row['usagePdTo'] != '' && row['usagePdTo'] != null ) {
            var usagePdToStr = row['usagePdTo']+"";
            usagePdToStr = usagePdToStr.replace(/-/g,"");
            usagePdTo = Number(usagePdToStr);

            if( maxUsagePdTo == 0 ) {
                maxUsagePdTo = usagePdTo;
            } else {
                if( maxUsagePdTo < usagePdTo ) {
                    maxUsagePdTo = usagePdTo;
                }
            }
        }
    }

    this.$('#grFee').val( fee ); //총사용료
    this.$('#grRdcxptFee').val( rdcxptFee ); //총감면사용료
    this.$('#grAr').val( usageAr ); //총사용면적

    if( minUsagePdFrom > 0 ) {
        var minUsagePdFromStr = minUsagePdFrom+"";

        if( minUsagePdFromStr.length == 8 ) {
            minUsagePdFromStr = minUsagePdFromStr.substring(0,4) + "-" + minUsagePdFromStr.substring(4,6) + "-" + minUsagePdFromStr.substring(6,8);
            this.$('#grUsagePdFrom').val( minUsagePdFromStr ); //총사용기간FROM
        } else {
            this.$('#grUsagePdFrom').val( "" ); //총사용기간FROM
        }
    }

    if( maxUsagePdTo > 0 ) {
        var maxUsagePdToStr = maxUsagePdTo+"";

        if( maxUsagePdToStr.length == 8 ) {
            maxUsagePdToStr = maxUsagePdToStr.substring(0,4) + "-" + maxUsagePdToStr.substring(4,6) + "-" + maxUsagePdToStr.substring(6,8);
            this.$('#grUsagePdTo').val( maxUsagePdToStr ); //총사용기간FROM
        } else {
            this.$('#grUsagePdTo').val( "" ); //총사용기간FROM
        }
    }

    if( this.$('#assetRentDetailList').flexGetData().length == 0 ) {
        this.$('#grFee').val( "" ); //총사용료
        this.$('#grRdcxptFee').val( "" ); //총감면사용료
        this.$('#grAr').val( "" ); //총사용면적
        this.$('#grUsagePdFrom').val( "" ); //총사용기간FROM
        this.$('#grUsagePdTo').val( "" ); //총사용기간FROM
    }

    this.calcFirstPaymentAmount();
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamAssetRentMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
			this.loadData();
            break;

        // 최초신청
        case 'addAssetRentFirst':
            this.$("#assetRentListTab").tabs("option", {active: 1});  // 탭을 전환 한다.
            this.$('#gamAssetRentForm').find(':input').val('');
            this.$('#gamAssetRentDetailForm').find(':input').val('');
            this.$('#gamAssetRentFileForm').find(':input').val('');
            this.$('#selectCharger').empty();
            this.$('#chargerTlphonNo').text('');
            this.$('#chargerMoblphonNo').text('');

            //this.$("#assetRentDetailList").flexRemove();
            this.$("#assetRentDetailList").flexAddData({resultList:[]}); //그리드 초기화
            this.$("#assetRentFileList").flexAddData({resultList:[]}); //그리드 초기화
            this.$("#cmd").val('insert');

            this.$('#deptcd').val(this.$('#loginOrgnztId').val());
            this.$('#popupEntrpsInfoInput').removeAttr('disabled');
            this.$('#frstReqstDt').val(this.$('#currentDateStr').val());
            this.$('#reqstDt').val(this.$('#currentDateStr').val());

            this.$('#prtAtCode').val('622');
            this.$('#payMth').val('Pre');
            this.$('#nticMth').val('1');
            this.$('#taxtSe').val('2');
            this.$('#applcMth').val('1'); //적용방법
            this.$('#applcTariff').val('0.05'); //적용요율
            this.$('#exemptSe').val('0'); // 면제구분

            break;

        // 연장신청
        case 'addAssetRentRenew':
            var rows = this.$('#assetRentMngtList').selectedRows();

            /* if( rows[0]['quayGroupCd'] != 'P' ) {
                alert("해당 건은 자산임대관리 메뉴에서 연장신청이 불가능합니다.");
                return;
            } */

            if(rows.length>=1) {
                //this.$('#rPrtAtCode').val(row[0]['prtAtCode']);

                if( confirm("연장신청을 하시겠습니까?") ) {
                    this.doAction('/oper/center/gamInsertMarineCenterRentRenew.do', rows[0], function(module, result) {

                        if(result.resultCode=='0') {
                        	module.loadData();
                        }

                        alert(result.resultMsg);
                    });
                //
                }
            } else {
                alert("목록에서 연장신청할 업체를 선택하십시오.");
            }

            break;

        // 신청저장
        case 'btnSaveItem':

        	if(!validateGamAssetRent(this.$('#gamAssetRentForm')[0])) {
                return;
            }

            /* if( this.$("#cmd").val() != 'insert' && this.$('#quayGroupCd').val() != 'P' ) {
                alert("해당 건은 자산임대관리 메뉴에서 저장이 불가능합니다.");
                return;
            } */

            if( this.$('#prtAtCode').val() == '' ) {
                alert("항구분을 선택하십시오.");
                return;
            }

            if( this.$('#entrpscd').val() == '' ) {
                alert("신청업체를 선택하십시오.");
                return;
            }

            if( this.$('#frstReqstDt').val() == '' ) {
                alert("최초신청일자가 없습니다..");
                return;
            }

            if( this.$('#reqstDt').val() == '' ) {
                alert("신청일자가 없습니다..");
                return;
            }

            if( this.$('#payMth').val() == '' ) {
                alert("납부방법을 선택하십시오.");
                return;
            }

            if( this.$('#nticMth').val() == '' ) {
                alert("고지방법을 선택하십시오.");
                return;
            }

            if( this.$('#nticMth').val() == '1' && this.$('#payinstIntrrate').val() != '' && this.$('#payinstIntrrate').val() != '0' ) {
                alert("고지방법이 일괄납부인 경우는 분납이자율을 입력하지 마십시오.");
                return;
            }

            if( this.$('#nticMth').val() != '1' && this.$('#payinstIntrrate').val() == '' ) {
                alert("고지방법이 분납인 경우는 분납이자율을 입력하십시오.");
                return;
            }

            if( confirm("저장하시겠습니까?") ) {
                // 변경된 자료를 저장한다.
                var inputVO=[{name: 'test', value:'test hello'}];
                //var inputVO=[{}];

                //this._editData=this.getFormValues('#gamAssetRentDetailForm', this._editData);

                inputVO[inputVO.length]={name: 'updateList', value :JSON.stringify(this.$('#assetRentDetailList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

                inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#assetRentDetailList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

                inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataList) };

                if(this._deleteDataFileList == undefined ) {
                    this._deleteDataFileList=[];
                }

                inputVO[inputVO.length]={name: 'updateFileList', value :JSON.stringify(this.$('#assetRentFileList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

                inputVO[inputVO.length]={name: 'insertFileList', value: JSON.stringify(this.$('#assetRentFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

                inputVO[inputVO.length]={name: 'deleteFileList', value: JSON.stringify(this._deleteDataFileList) };

                //var otherForm=this.getFormValues('#gamAssetRentForm', {});  // 폼만 있을 경우

                this._editData2=this.getFormValues('#gamAssetRentForm', {_updtId:'I'});
                inputVO[inputVO.length]={name: 'form', value: JSON.stringify(this._editData2) };    // 폼의 데이터를 컨트롤러에 보낸다.

                //// console.log(inputVO);
                // 데이터를 저장 하고 난 뒤 리스트를 다시 로딩 한다.

                this.doAction('/oper/center/gamSaveMarineCenterRent.do', inputVO, function(module, result) {
                    if(result.resultCode == 0){
                    	module.loadData();
                    }
                    alert(result.resultMsg);
                });


                this.$("#assetRentListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
            }

            break;

        //신청삭제
        case 'btnRemoveItem':
            var rows = this.$('#assetRentMngtList').selectedRows();

            /* if( rows[0]['quayGroupCd'] != 'P' ) {
                alert("해당 건은 자산임대관리 메뉴에서 삭제가 불가능합니다.");
                return;
            } */

            if(rows.length == 0) {
                alert("자산임대목록에서 신청삭제할 행을 선택하십시오.");
            } else {
            	if( confirm("신청삭제를 하시겠습니까?") ) {
                    if( rows[0]['prmisnYn'] == null || rows[0]['prmisnYn'] == '' ) {
                        this.$('#detailPrmisnYn').val('N');
                    }

                    var inputVO=this.makeFormArgs('#gamAssetRentForm');

                    this.doAction('/oper/center/gamDeleteMarineCenterRent.do', inputVO, function(module, result) {

                        if(result.resultCode=='0') {
                        	module.loadData();
                        }

                        alert(result.resultMsg);
                    });

                    this.$("#assetRentListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
                    this.$('#gamAssetRentForm :input').val("");
                    this.$("#cmd").val('insert');
                }
            }

            break;

        //코멘트저장
        case 'btnSaveComment':
            var inputVO=this.makeFormArgs('#gamAssetRentForm');

            /* if( this.$('#quayGroupCd').val() != 'P' ) {
                alert("해당 건은 자산임대관리 메뉴에서 코멘트저장이 불가능합니다.");
                return;
            } */

            this.doAction('/oper/center/gamUpdateMarineCenterRentComment.do', inputVO, function(module, result) {
                if(result.resultCode=='0') {
                }

                alert(result.resultMsg);
            });

            break;

        //임대상세추가
        case 'btnInsertItemDetail':
        	 this.$("#assetRentListTab").tabs("option", {active: 2});  // 탭을 전환 한다.
             this.$('#gamAssetRentDetailForm').find(':input').val('');

        	 this.$('#olnlpList').empty();

             this.$("#detailCmd").val('insert');
             this.$('#detailPrtAtCode').val( this.$('#prtAtCode').val() );
             //this.$('#detailPrtAtCodeNm').val( this.$('#prtAtCodeNm').val() );
             this.$('#detailMngYear').val( this.$('#mngYear').val() );
             this.$('#detailMngNo').val( this.$('#mngNo').val() );
             this.$('#detailMngCnt').val( this.$('#mngCnt').val() );
             this.$('#applcMth').val('1'); //적용방법
             this.$('#applcTariff').val('0.05'); //적용요율
             this.$('#exemptSe').val('0'); // 면제구분

             this._editData=this.getFormValues('#gamAssetRentDetailForm', {_updtId:'I'});
//             this._editRow=this.$('#assetRentDetailList').flexGetData().length;
				this._editRow=null;

            break;

        // 자산임대상세 삭제 (Grid상에서만 삭제됨)
        case 'btnRemoveItemDetail':
            var rows = this.$('#assetRentDetailList').selectedRows();

            if(rows.length == 0) {
                alert("자산임대상세목록에서 삭제할 행을 선택하십시오.");
            } else {
                if(this.$('#assetRentDetailList').selectedRowIds().length>0) {
                    for(var i=this.$('#assetRentDetailList').selectedRowIds().length-1; i>=0; i--) {
                        var row=this.$('#assetRentDetailList').flexGetRow(this.$('#assetRentDetailList').selectedRowIds()[i]);

                        if(row._updtId==undefined || row._updtId!='I') {
                            this._deleteDataList[this._deleteDataList.length]=row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
                        }
                        this.$('#assetRentDetailList').flexRemoveRow(this.$('#assetRentDetailList').selectedRowIds()[i]);
                    }

                    /* 총사용료, 총면적 계산 시작 */
                    var fee = 0;
                    var rdcxptFee = 0;
                    var usageAr = 0;
                    var usagePdFrom = 0;
                    var usagePdTo = 0;
                    var minUsagePdFrom = 0;
                    var maxUsagePdTo = 0;

                    var detailList = this.$('#assetRentDetailList').flexGetData();

                    for( var i = 0 ; i < detailList.length ; i++ ) {
                        var row = detailList[i];

                        if( row['fee'] != '' && row['fee'] != null ) {
                            var feeStr = row['fee']+"";
                            feeStr = feeStr.replace(/,/g,"");
                            fee += Number(feeStr);
                        }

                        if( row['rdcxptFee'] != '' && row['rdcxptFee'] != null ) {
                            var rdcxptFeeStr = row['rdcxptFee']+"";
                            rdcxptFeeStr = rdcxptFeeStr.replace(/,/g,"");
                            rdcxptFee += Number(rdcxptFeeStr);
                        }

                        if( row['usageAr'] != '' && row['usageAr'] != null ) {
                            var usageArStr = row['usageAr']+"";
                            usageArStr = usageArStr.replace(/,/g,"");
                            usageAr += Number(usageArStr);
                        }

                        if( row['usagePdFrom'] != '' && row['usagePdFrom'] != null ) {
                            var usagePdFromStr = row['usagePdFrom']+"";
                            usagePdFromStr = usagePdFromStr.replace(/-/g,"");
                            usagePdFrom = Number(usagePdFromStr);

                            if( minUsagePdFrom == 0 ) {
                                minUsagePdFrom = usagePdFrom;
                            } else {
                                if( minUsagePdFrom > usagePdFrom ) {
                                    minUsagePdFrom = usagePdFrom;
                                }
                            }
                        }

                        if( row['usagePdTo'] != '' && row['usagePdTo'] != null ) {
                            var usagePdToStr = row['usagePdTo']+"";
                            usagePdToStr = usagePdToStr.replace(/-/g,"");
                            usagePdTo = Number(usagePdToStr);

                            if( maxUsagePdTo == 0 ) {
                                maxUsagePdTo = usagePdTo;
                            } else {
                                if( maxUsagePdTo < usagePdTo ) {
                                    maxUsagePdTo = usagePdTo;
                                }
                            }
                        }
                    }

                    this.$('#grFee').val( fee ); //총사용료
                    this.$('#grRdcxptFee').val( rdcxptFee ); //총감면사용료
                    this.$('#grAr').val( usageAr ); //총사용면적

                    if( minUsagePdFrom > 0 ) {
                        var minUsagePdFromStr = minUsagePdFrom+"";

                        if( minUsagePdFromStr.length == 8 ) {
                            minUsagePdFromStr = minUsagePdFromStr.substring(0,4) + "-" + minUsagePdFromStr.substring(4,6) + "-" + minUsagePdFromStr.substring(6,8);
                            this.$('#grUsagePdFrom').val( minUsagePdFromStr ); //총사용기간FROM
                        } else {
                            this.$('#grUsagePdFrom').val( "" ); //총사용기간FROM
                        }
                    }

                    if( maxUsagePdTo > 0 ) {
                        var maxUsagePdToStr = maxUsagePdTo+"";

                        if( maxUsagePdToStr.length == 8 ) {
                            maxUsagePdToStr = maxUsagePdToStr.substring(0,4) + "-" + maxUsagePdToStr.substring(4,6) + "-" + maxUsagePdToStr.substring(6,8);
                            this.$('#grUsagePdTo').val( maxUsagePdToStr ); //총사용기간FROM
                        } else {
                            this.$('#grUsagePdTo').val( "" ); //총사용기간FROM
                        }
                    }

                    if( this.$('#assetRentDetailList').flexGetData().length == 0 ) {
                    	this.$('#grFee').val( "" ); //총사용료
                        this.$('#grRdcxptFee').val( "" ); //총감면사용료
                        this.$('#grAr').val( "" ); //총사용면적
                    	this.$('#grUsagePdFrom').val( "" ); //총사용기간FROM
                    	this.$('#grUsagePdTo').val( "" ); //총사용기간FROM
                    }

                    /* 총사용료, 총면적 계산 종료 */
                }
            }

            this.$('#gamAssetRentDetailForm').find(':input').val('');
            this.$("#detailCmd").val('insert');

            break;

        // 자산임대상세 저장
        /*
        case 'btnSaveItemDetail':

            var inputVO=this.makeFormArgs('#gamAssetRentDetailForm');


            if(this.$("#detailCmd").val()=='insert') {

                this.doAction('/asset/rent/gamInsertAssetRentDetail.do', {aaa : "rrrrrrrrrrrrrr"}, function(module, result) {

                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamAssetRentForm');
                        module.$('#assetRentDetailList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });
            }
            else {
                this.doAction('/asset/rent/gamUpdateAssetRentDetail.do', inputVO, function(module, result) {
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamAssetRentForm');
                        module.$('#assetRentDetailList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });
            }
            break;
        */

        case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            /*
            var opts = {
                'gisAssetsPrtAtCode': this.$('#prtAtCode').val(),
                'gisAssetsCd': this.$('#gisAssetsCd').val(),
                'gisAssetsSubCd': this.$('#gisAssetsSubCd').val()
            };
            */
            var opts;

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', opts);
            break;

        case 'popupEntrpsInfoInput': // 팝업을 호출한다.(자산임대입력)
            var opts;

            this.doExecuteDialog('insertEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', opts);
            break;


        case 'btnPrmisn': // 사용승낙
            var rows = this.$('#assetRentMngtList').selectedRows();
            var row = this.$('#assetRentMngtList').selectedRows()[0];

            /* if( rows[0]['quayGroupCd'] != 'P' ) {
                alert("해당 건은 자산임대관리 메뉴에서 사용승낙이 불가능합니다.");
                return;
            } */

            if(rows.length>=1) {
            	if( row['prmisnYn'] == 'Y' ) {
                    alert("이미 사용승낙된 상태 입니다.");
                    return;
                }

                if( row['sanctnSttus'] != '1' ) {
                	if(!confirm("결재완료 되지 않았습니다. 결재 처리 되지 않은 자료를 사용승낙을 하시겠습니까?")) {
                        return;
                	}
/*                     alert("결재완료 상태가 아닙니다.");
                    return; */
                }

            	var opts = {
                    'prtAtCode': rows[0]['prtAtCode'],
                    'mngYear': rows[0]['mngYear'],
                    'mngNo': rows[0]['mngNo'],
                    'mngCnt': rows[0]['mngCnt'],
                    'taxtSe': rows[0]['taxtSe']
                };

                this.doExecuteDialog('insertAssetRentPrmisnPopup', '승낙', '/oper/center/popup/showMarineCenterRentPrmisn.do', opts);

            } else {
                alert("목록에서 선택하십시오.");
            }

            break;

        /*
        case 'btnPrmisn': // 사용승낙
            var rows = this.$('#assetRentMngtList').selectedRows();
            var row = this.$('#assetRentMngtList').selectedRows()[0];

            if( row['prmisnYn'] == 'Y' ) {
                alert("이미 사용승낙된 상태 입니다.");
                return;
            }

            if( row['sanctnSttus'] != '1' ) {
                alert("결재완료 상태가 아닙니다.");
                return;
            }

            if(rows.length>=1) {
                if( confirm("승낙을 하시겠습니까?") ) {
                    this.doAction('/asset/rent/gamUpdateAssetRentPrmisn.do', rows[0], function(module, result) {
                        if(result.resultCode=='0') {
                            var searchOpt=module.makeFormArgs('#gamAssetRentForm');
                            module.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
                        }

                        alert(result.resultMsg);
                    });
                }
            } else {
                alert("목록에서 선택하십시오.");
            }

            break;
        */

        case 'btnPrmisnCancel': // 승낙취소
            var rows = this.$('#assetRentMngtList').selectedRows();
            var row = this.$('#assetRentMngtList').selectedRows()[0];

            /* if( rows[0]['quayGroupCd'] != 'P' ) {
                alert("해당 건은 자산임대관리 메뉴에서 승낙취소가 불가능합니다.");
                return;
            } */

            if( row['prmisnYn'] != 'Y' ) {
                alert("승낙된 상태가 아닙니다.");
                return;
            }

            if(rows.length>=1) {
                if( confirm("승낙취소를 하시겠습니까?") ) {
                    this.doAction('/oper/center/gamUpdateMarineCenterRentPrmisnCancel.do', rows[0], function(module, result) {
                        if(result.resultCode=='0') {
                        	 module.loadData();
                        }

                        alert(result.resultMsg);
                    });
                }
            } else {
                alert("목록에서 선택하십시오.");
            }

            break;
        case 'btnNoticeAdit':	// 추가고지
        case 'btnNoticeAdit2':
            var rows = this.$('#assetRentMngtList').selectedRows();
            var row = this.$('#assetRentMngtList').selectedRows()[0];

            if( row['prmisnYn'] != 'Y' ) {
                alert("승낙된 상태가 아닙니다.");
                return;
            }

            if(rows.length>=1) {
                this.doExecuteDialog('insertLevReqestAdit', '추가 사용료 고지', '/oper/gnrl/popupLevReqestAdit.do', rows[0]);
            } else {
                alert("목록에서 선택하십시오.");
            }

            break;
        case 'popupFcltyCd':    //GIS자산코드 팝업을 호출한다.
            var opts;

            this.doExecuteDialog('selectAssetsCdRentPopup', '시설 선택', '/popup/showAssetsCd.do', opts);
            break;

        case 'btnRentDetailApply': //임대상세적용

        	if(!validateGamAssetRentDetail(this.$('#gamAssetRentDetailForm')[0])) {
                return;
            }

            if( this.$('#gisAssetsCd').val() == '' ) {
                alert("자산을 조회하여 선택하십시오.");
                return;
            }

            if( this.$('#usagePdFrom').val() == '' ) {
                alert("사용기간(시작)을 선택하십시오.");
                return;
            }

            if( this.$('#usagePdTo').val() == '' ) {
                alert("사용기간(종료)을 선택하십시오.");
                return;
            }

            if( this.$('#olnlp').val() == '' ) {
                alert("공시지가를 입력하십시오.");
                return;
            }

            if( this.$('#usageAr').val() == '' ) {
                alert("사용면적를 입력하십시오.");
                return;
            }

            if( this.$('#applcTariff').val() == '' ) {
                alert("적용요율을 선택하십시오.");
                return;
            }

            if( this.$('#applcMth').val() == '' ) {
                alert("적용방법을 선택하십시오.");
                return;
            }

            if( this.$('#exemptSe').val() == '' ) {
                alert("면제구분을 선택하십시오.");
                return;
            }

            if( this.$('#exemptSe').val() == '1' ) {
                if( this.$('#exemptPdFrom').val() == '' ) {
                    alert("면제기간(시작)을 선택하십시오.");
                    return;
                }
                if( this.$('#exemptPdTo').val() == '' ) {
                    alert("면제기간(종료)을 선택하십시오.");
                    return;
                }
            }

            if( this.$('#fee').val() == '' ) {
                alert("사용료를 입력하십시오.");
                return;
            }

            if(this._editData==null) return;   // 추가나 삭제가 없으면 적용 안됨 2014-03-11 추가
            this._editData=this.getFormValues('#gamAssetRentDetailForm', this._editData);
            //this._editData=this.getFormValues('#gamAssetRentDetailForm', this._editData);

            if(this._editRow!=null) {  // 이전에 _updtId 로 선택 한 것을 _editRow 로 변경 2014-03-14.001
            	this._editData._updtId!='I'
                if(this._editData._updtId!='I') this._editData._updtId='U';   // 삽입된 데이터가 아니면 업데이트 플래그를 추가한다.
                this.$('#assetRentDetailList').flexUpdateRow(this._editRow, this._editData);
                this._editRow=null;    // 편집 저장 하였으므로 로우 편집을 종료 한다.
            }
            else {
                this.$('#assetRentDetailList').flexAddRow(this._editData);
            }

            this.$('#gamAssetRentDetailForm').find(':input').val('');
            this._editData=null;       // 적용 이후 데이터 추가나 삭제 가 되지 않도록 편집 데이터를 제거 함/ 2014-03-11 추가

			this.calcRentMasterValues();

            /* 총사용료, 총면적 계산 종료 */

            this.$("#assetRentListTab").tabs("option", {active: 1});  // 탭을 전환 한다.

            break;

        case 'btnUploadFile':
            // 사진을 업로드하고 업로드한 사진 목록을 result에 어레이로 리턴한다.
            this.uploadFile('uploadPhoto', function(module, result) {
//              var userid=EMD.util.getLoginUserVO().userNm; 임시
                var userid='admin';
                $.each(result, function(){
                    //module.$('#assetRentFileList').flexAddRow({photoSj: '', filenmLogical: this.logicalFileNm, filenmPhyicl: this.physcalFileNm, regUsr: userid, registDt:  EMD.util.getTimeStamp()}); // 업로드 파일명이 physcalFileNm (물리명), logicalFileNm (논리명)으로 리턴 된다.
                    //module.$('#assetRentFileList').flexAddRow({prtAtCode: '', mngYear: '', mngNo: '', mngCnt: '', photoSeq: '', photoSj: '', filenmLogic: this.logicalFileNm, filenmPhysicl: this.physcalFileNm, shotDt: '', photoDesc: '', regUsr: '', registDt:  EMD.util.getTimeStamp()}); // 업로드 파일명이 physcalFileNm (물리명), logicalFileNm (논리명)으로 리턴 된다.
                    module.$('#assetRentFileList').flexAddRow({_updtId:'I', prtAtCode: '', mngYear: '', mngNo: '', mngCnt: '', photoSeq: '', photoSj:this.logicalFileNm.substring(0, this.logicalFileNm.lastIndexOf('.')) , filenmLogic: this.logicalFileNm, filenmPhysicl: this.physcalFileNm, shotDt: '', photoDesc: '', regUsr: '', registDt:  EMD.util.getTimeStamp()}); // 업로드 파일명이 physcalFileNm (물리명), logicalFileNm (논리명)으로 리턴 된다.
                });
            }, '첨부파일 업로드');

            //this._editDataFile=this.getFormValues('#gamAssetRentFileForm', {_updtId:'I'});
            //this._editRowFile=this.$('#assetRentFileList').flexGetData().length;

            break;

        case 'btnDownloadFile':
    		var selectRow = this.$('#assetRentFileList').selectedRows();
    		if(selectRow.length > 0) {
    			var row=selectRow[0];
    			this.downloadFile(row["filenmPhysicl"], row["filenmLogic"]);
    		}
    		break;
        case 'btnApplyPhotoData':
			this.applyPhotoData();
            break;

        // 파일 삭제 (Grid상에서만 삭제됨)
        case 'btnRemoveFile':
            var rows = this.$('#assetRentFileList').selectedRows();

            if(rows.length == 0) {
                alert("파일목록에서 삭제할 행을 선택하십시오.");
            } else {
                if(this.$('#assetRentFileList').selectedRowIds().length>0) {
                    for(var i=this.$('#assetRentFileList').selectedRowIds().length-1; i>=0; i--) {
                        var row=this.$('#assetRentFileList').flexGetRow(this.$('#assetRentFileList').selectedRowIds()[i]);

                        //alert( row._updtId );

                        if(row._updtId==undefined || row._updtId!='I') {
                            this._deleteDataFileList[this._deleteDataFileList.length]=row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
                        }
                        this.$('#assetRentFileList').flexRemoveRow(this.$('#assetRentFileList').selectedRowIds()[i]);
                        this.$("#previewImage").attr('src', '');
                    }
                }
            }

            this.$('#gamAssetRentFileForm').find(':input').val('');
            this._editDataFile = null;

            break;

        case 'btnEApproval':    // 전자결재 테스트
            if(this.$('#assetRentMngtList').selectedRowCount()>0) {

                //alert(EMD.context_root);

                var rows = this.$('#assetRentMngtList').selectedRows()[0];

                /* if( rows['quayGroupCd'] != 'P' ) {
                    alert("해당 건은 자산임대관리 메뉴에서 결재가 불가능합니다.");
                    return;
                } */

                if( rows['sanctnSttus'] == '1' || rows['sanctnSttus'] == '2' || rows['sanctnSttus'] == '5' ) {
                	alert("결재요청을 할수없는 상태 입니다.");
                	return;
                }

                if( confirm("결재요청을 하시겠습니까?") ) {
	                var opts = {
	                        type: 'ARU',
	                        prtAtCode: rows['prtAtCode'],
	                        mngYear: rows['mngYear'],
	                        mngNo: rows['mngNo'],
	                        mngCnt: rows['mngCnt']
	                };

	                /*
	                this.requestEApproval(opts);
	                alert("결재요청을 하였습니다.");
	                */
	                this.requestEApproval(opts, function(module, msg){
						alert(msg);
		                //재조회 안됨..
		                var searchOpt=module.makeFormArgs('#gamAssetRentForm');
		                module.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
                	});
                }
            } else {
            	alert("목록에서 결제할 건을 선택하십시오.");
            	return;
            }
            break;
        case 'btnMangeCharger': // 업체정보관리
       	 	EMD.util.create_window('업체정보 관리', '/code/gamCmpyInfoMngt.do', null, {entrpscd:this.$('#entrpscd').val()});
        	break;

        case 'btnMarineCenterRentMngtListExcelDownload':	// 엑셀 다운로드
        	this.$('#assetRentMngtList').flexExcelDown('/oper/center/selectMarineCenterRentMngtListExcel.do');
            break;
    }
};

GamAssetRentMngtModule.prototype.applyPhotoData = function() {

	if(!validateGamAssetRentFile(this.$('#gamAssetRentFileForm')[0])) {
        return;
    }

	var selectRow = this.$('#assetRentFileList').selectedRows();
	if(selectRow.length > 0) {

    var row=selectRow[0];
	  		var rowid=this.$("#assetRentFileList").selectedRowIds()[0];
	  		row=this.getFormValues('#gamAssetRentFileForm', row);
        if(row["_updtId"]!='I') row["_updtId"]='U';   // 삽입된 데이터가 아니면 업데이트 플래그를 추가한다.
        this.$('#assetRentFileList').flexUpdateRow(rowid, row);
    }
    else {
    }
};

GamAssetRentMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamAssetRentMngtModule.prototype.loadData = function() {
    this.$("#assetRentListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamAssetRentMngtSearchForm');
    this.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
	// console.log('debug');

};

GamAssetRentMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        var row = this.$('#assetRentMngtList').selectedRows();
        if(row.length==0) {
            this.$('#cmd').val('insert');
        }
        else {
            this.$('#cmd').val('modify');
        }

        if(oldTabId=='tabs1') {
        	this._deleteDataList=[];    // 삭제 목록 초기화
        	this._deleteDataFileList=[];    // 파일삭제 목록 초기화

        }
        break;
    case 'tabs3':
        var row = this.$('#assetRentDetailList').selectedRows();
        if(row.length==0) {
            this.$('#detailCmd').val('insert');
        }
        else {
            this.$('#detailCmd').val('modify');
            this._selectAssetsCd=row;
			this.onApplcMthChange(row[0]['applcMth']);
        }
        break;

    case 'tabs4':

        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
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
             this.loadEntrpsChargerList();	// 담당자 목록을 불러온다.
         } else {
             alert('취소 되었습니다');
         }
         break;
     case 'insertAssetRentPrmisnPopup':
         if (msg != 'cancel') {
             if( value == "0" ) {
					this.loadData();
             }
         } else {
             alert('취소 되었습니다');
         }
         break;
     case 'insertLevReqestAdit':
    	 break;
     case 'selectAssetsCdRentPopup':
         if (msg != 'cancel') {
             this.$('#gisAssetsPrtAtCode').val(value.gisAssetsPrtAtCode);
             this.$('#gisAssetsCd').val(value.gisAssetsCd);
             this.$('#gisAssetsSubCd').val(value.gisAssetsSubCd);
             this.$('#gisAssetsNm').val(value.gisAssetsNm);
             this.$('#gisAssetsLocplc').val(value.gisAssetsLocplc);
             this.$('#gisAssetsLnm').val(value.gisAssetsLnm);
             this.$('#gisAssetsLnmSub').val(value.gisAssetsLnmSub);
             this.$('#gisAssetsAr').val(value.gisAssetsAr);
             this.$('#usageAr').val(value.gisAssetsRealRentAr);
             this.$('#gisAssetsRealRentAr').val(value.gisAssetsRealRentAr);
             this.$('#gisAssetsPrtAtCodeNm').val(value.gisAssetsPrtAtCodeNm);
//              this.$('#quayCd').val(value.gisAssetsQuayCd);
             this.$('#assetsCdStr').val(value.gisAssetsCd + "-" + value.gisAssetsSubCd);

             this.loadOlnlpList(value);
         } else {
             alert('취소 되었습니다');
         }
         break;

     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');

         break;
     }
};

GamAssetRentMngtModule.prototype.loadOlnlpList = function(prtFcltyCd) {
    this.doAction('/asset/rent/selectOlnlpInfo.do', prtFcltyCd, function(module, result) {
        if(result.resultCode=='0') {
       	 var olnlplist = module.$('#olnlpList');
       	 olnlplist.off('change');
       	 olnlplist.empty();
   		 olnlplist.append('<option value="">공시지가선택</option>')
       	 $.each(result.resultList, function() {
       		 olnlplist.append('<option value="'+this.olnlp+'">'+this.olnlpApplyDates+'</option>')
       	 });
   		 olnlplist.on('change', {module: module}, function(event) {
   			 event.data.module.$('#olnlp').val($.number($(this).children(':selected').val()));
   			event.data.module.onCalc();
   		 });
   		olnlplist.find('option:eq(1)').attr("selected","selected");
   		module.$('#olnlp').val(olnlplist.find('option:eq(1)').val());
        }
    });
}

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetRentMngtModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetRentMngtSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>
                            <td>
                                <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" />
                            </td>
                            <th>신청구분</th>
                            <td width="100px">
                                <input id="sReqstSeCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM011 />
                            </td>
                            <th>신청업체</th>
                            <td>
                            	<input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;
                            	<input id="sEntrpsNm" type="text" size="15" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <th>사용용도</th>
                            <td>
                                <input id="sUsagePrposCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM007 />
                            </td>
                            <td rowSpan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                            <th>관리번호</th>
                            <td>
                                <input id="sMngYear" type="text" class="mngYear">
                                <input id="sMngNo" type="text" class="mngNo">
                                <input id="sMngCnt" type="text" class="mngCnt">
                            </td>
                            <th>승낙구분</th>
                            <td>
                                <select id="sPrmisnYn">
                                    <option value="" selected="selected">전체</option>
                                    <option value="Y">Y</option>
                                    <option value="N">N</option>
                                </select>
                            </td>
                            <th>사용기간</th>
                            <td>
                            <input id="sGrUsagePdFrom" type="text" class="emdcal"
                                size="8"> ~ <input id="sGrUsagePdTo" type="text"
                                class="emdcal" size="8">
                            </td>
                            <th>총면적</th>
                            <td>
                                <input id="sGrAr" type="text" size="8">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">마린센터임대 목록</a></li>
                <li><a href="#tabs2" class="emdTab">마린센터임대 내역</a></li>
                <li><a href="#tabs3" class="emdTab">마린센터임대 상세내역</a></li>
                <li><a href="#tabs4" class="emdTab">첨부파일</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
                <table id="assetRentMngtList" style="display:none" class="fillHeight"></table>

                <div class="emdControlPanel">
					<form id="form1">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="12%" height="20">자료수</th>
								<td><input type="text" size="7" id="totalResultCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">총면적</th>
								<td><input type="text" size="19" id="totalArea" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">총사용료</th>
								<td><input type="text" size="19" id="totalUse" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">총감면사용료</th>
								<td><input type="text" size="19" id="totalGrRdcxptFee" class="ygpaNumber" disabled="disabled" /></td>
								<td>
		                            <input id="loginOrgnztId" type="hidden" value="<c:out value="${loginOrgnztId}"/>"/>
		                            <input id="loginUserId" type="hidden" value="<c:out value="${loginUserId}"/>"/>
		                            <input id="currentDateStr" type="hidden" value="<c:out value="${currentDateStr}"/>"/>
		                            <input id="blceStdrIntrrate" type="hidden" value="<c:out value="${blceStdrIntrrate}"/>"/>
		                            <input id="blceStdrIntrrateShow" type="hidden" value="<c:out value="${blceStdrIntrrateShow}"/>"/>
		                        </td>
							</tr>
						</table>
    	               	<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                                <button id="addAssetRentFirst">최초신청</button>
	                                <button id="addAssetRentRenew">연장신청</button>
	                                <button id="btnRemoveItem">신청삭제</button>
	                                <button id="btnEApproval">결재요청</button>
	                                <button id="btnPrmisn">사용승낙</button>
	                                <button id="btnPrmisnCancel">승낙취소</button>
	                                <button id="btnMarineCenterRentMngtListExcelDownload">엑셀</button>
	                                <!-- <button id="btnNoticeAdit">추가고지</button> -->
	                                <!-- <button id="btnShowMap">맵조회</button> -->
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamAssetRentForm">
                        <input type="hidden" id="cmd"/>
                        <!-- <input type="hidden" id="quayGroupCd"/> -->

                        <table class="editForm">
                            <tr>
								<th width="10%" height="18">항코드</th>
                                <td>
                                    <input id="prtAtCode" class="ygpaCmmnCd" value="640" data-default-prompt="선택" data-code-id="GAM019" />
                                    <input type="text" size="4" id="prtAtCodeStr" disabled/>
                                </td>
								<th width="10%" height="18">담당부서</th>
                                <td>
                                    <input id="deptcd" class="ygpaDeptSelect" value="ORGNZT_0000000000002" data-default-prompt="선택" data-value="<c:out value="${loginOrgnztId}"/>" />
                                </td>
								<th width="10%" height="18">관리번호</th>
                                <td>
                                    <input type="text" size="5" id="mngYear" readonly/>-
                                    <input type="text" size="5" id="mngNo" readonly/>-
                                    <input type="text" size="5" id="mngCnt" readonly/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">최초신청일자</th>
                                <td><input type="text" size="20" id="frstReqstDt" disabled/></td>
								<th width="10%" height="18">신청일자</th>
                                <td><input type="text" class="emdcal" size="10" id="reqstDt" readonly/></td>
								<th width="10%" height="18">신청업체</th>
                                <td>
                                    <input type="text" size="8" id="entrpscd" maxlength="10" readonly/>
                                    <input type="text" size="29" id="entrpsNm" disabled/>
                                    <button id="popupEntrpsInfoInput" class="popupButton">선택</button>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">승낙여부</th>
                                <td>
                                    <select id="prmisnYn" disabled>
                                        <option value="">선택</option>
                                        <option value="Y">Y</option>
                                        <option value="N" selected="selected">N</option>
                                    </select>
                                </td>
								<th width="10%" height="18">승낙일자</th>
                                <td><input type="text" size="20" id="prmisnDt" disabled></td>
								<th width="10%" height="18">총사용기간</th>
                                <td>
                                    <input type="text" size="24" id="grUsagePdFrom" disabled/>~
                                    <input type="text" size="24" id="grUsagePdTo" disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">총사용면적</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="grAr" disabled/></td>
								<th width="10%" height="18">총사용료</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="grFee" disabled/></td>
								<th width="10%" height="18">총감면사용료</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="grRdcxptFee" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">납부방법</th>
                                <td>
                                    <input id="payMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM043" />
                                </td>
								<th width="10%" height="18">고지방법</th>
                                <td>
                                    <input id="nticMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM008" />
                                </td>
								<th width="10%" height="18">분납이자율</th>
                                <td>
                                    <input type="text" size="19" id="payinstIntrrate" maxlength="4" size="5"/>
                                    <select id="cofixList">
                                        <option value="">선택</option>
                                        <c:forEach items="${cofixList}" var="cofixListItem">
                                            <option value="${cofixListItem.code }">${cofixListItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">과세구분</th>
                                <td>
                                    <input id="taxtSe" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM016" />
                                </td>
								<th width="10%" height="18">첫회 사용료</th>
                                <td colspan="3">
                                	<input type="text" size="13" id="firstPayVal" class="skipValue" disabled="disabled"/> 원
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">코멘트</th>
                                <td colspan="5">
                                	<input type="text" size="116" id="cmt" maxlength="80"/>
                                	<button id="btnSaveComment">코멘트저장</button>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">비고</th>
                                <td colspan="5"><input type="text" size="133" id="rm" maxlength="90"/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">담당자</th>
                                <td>
									<input id="chargerNo" type="hidden" />
									<select id="selectCharger" class="skipValue">
                                        <option value="">선택</option>
                                    </select>
								</td>
								<th width="10%" height="18">담당자 전화</th>
                                <td>
                                	<span id="chargerTlphonNo"></span>
								</td>
								<th width="10%" height="18">휴대전화</th>
                                <td>
                                	<span id="chargerMoblphonNo" style="width:120px; display: inline-block;"></span>
                                    <button id="btnMangeCharger">담당자관리</button>
								</td>
							</tr>
                        </table>
                    </form>

	                 <table class="searchPanel">
	                    <tbody>
	                    <tr>
	                        <th width="70%">마린센터임대 상세목록</th>
	                        <th style="text-align:right">
	                        	<button id="btnInsertItemDetail" class="buttonAdd">임대상세추가</button>
	                        	<button id="btnRemoveItemDetail" class="buttonDelete">임대상세삭제</button>
	                        </th>
	                    </tr>
	                    </tbody>
	                 </table>

	                 <!-- <table id="assetRentDetailList" style="display:none" class="fillHeight"></table> -->
	                 <table id="assetRentDetailList" style="display:none"></table>

	                 <table style="width:100%">
<!--
	                    <tr>
	                        <td style="text-align:right" colspan="3"><button id="btnInsertItemDetail" class="buttonAdd">임대상세추가</button><button id="btnRemoveItemDetail" class="buttonDelete">임대상세삭제</button></td>
	                    </tr>
-->
	                    <tr>
	                        <td><!-- <button id="xxxx">GIS 등록</button><button id="xxxx">위치조회</button> --></td>
	                        <td width="100"></td>
	                        <td style="text-align:right"><button id="btnEApproval">결재요청</button><button id="btnPrmisn">사용승낙</button>
	                            <button id="btnPrmisnCancel">승낙취소</button><button id="btnRemoveItem" class="buttonDelete">신청삭제</button><button id="btnSaveItem" class="buttonSave">신청저장</button>
	                            <button id="btnNoticeAdit2">추가고지</button>
	                            <!-- <button id="btnCancelItem">취소</button>  -->
	                        </td>
	                    </tr>
	                 </table>
                 </div>
            </div>

            <div id="tabs3" class="emdTabPage" style="overflow: scroll;">

                <!-- <div class="emdControlPanel"><button id="btnSaveItemDetail">저장</button></div>  -->
                    <form id="gamAssetRentDetailForm">
                        <input type="hidden" id="detailCmd"/>
                        <input type="hidden" id="detailPrtAtCode" data-column-id="prtAtCode"/>

                        <input type="hidden" id="detailPrmisnYn"/>
                        <table class="editForm">
                            <tr>
								<th width="10%" height="18">자산사용순번</th>
                                <td><input type="text" size="26" id="assetsUsageSeq" disabled/></td>
								<th width="10%" height="18">항코드</th>
                                <td>
                                	<input type="text" size="4" id="dtlPrtAtCode" disabled/>
	                                <input type="text" size="12" id="dtlPrtAtCodeNm" disabled/>
                                </td>
								<th width="10%" height="18">관리번호</th>
                                <td>
									<input type="text" size="5" id="detailMngYear" data-column-id="mngYear" disabled/>-
									<input type="text" size="5" id="detailMngNo" data-column-id="mngNo" disabled/>-
									<input type="text" size="5" id="detailMngCnt" data-column-id="mngCnt" disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">자산코드</th>
                                <td>
                                	<input type="text" size="1" id="gisAssetsPrtAtCode" readonly/><input type="text" size="1" id="gisAssetsCd" readonly/><input type="text" size="1" id="gisAssetsSubCd" readonly/>
                                    <input type="hidden" id="assetsCdStr"/>
                                    <button id="popupFcltyCd" class="popupButton">선택</button>
                                </td>
								<th width="10%" height="18">자산면적</th>
                                <td><input type="text" size="21" class="ygpaNumber" id="gisAssetsAr" disabled/></td>
								<th width="10%" height="18">자산명</th>
                                <td><input type="text" size="44" id="gisAssetsNm" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">지번</th>
                                <td>
                                	<input type="text" size="10" id="gisAssetsLnm" disabled/> -
                                	<input type="text" size="10" id="gisAssetsLnmSub" disabled/>
                                </td>
								<th width="10%" height="18">소재지</th>
                                <td colspan="3"><input type="text" size="87" id="gisAssetsLocplc" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">실제임대면적</th>
                                <td><input type="text" size="26" class="ygpaNumber" id="gisAssetsRealRentAr" disabled/></td>
								<th width="10%" height="18">사용면적</th>
                                <td><input type="text" size="20" class="calcInput" id="usageAr" maxlength="8"/></td>
								<th width="10%" height="18">신청기간</th>
                                <td>
                                	<input type="text" class="emdcal calcInput" size="10" id="usagePdFrom" data-role="dtFrom" data-dt-to="usagePdTo" readonly/> ~
                                	<input type="text" class="emdcal calcInput" size="10" id="usagePdTo" data-role="dtTo" data-dt-from="usagePdFrom" readonly/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">적용방법</th>
                                <td colspan="5">
                                    <input size="17" id="applcMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM014" value="2"/>
                                </td>
                              </tr>
                             <tr class="nationAssetLaw">
                                <th width="10%" height="18">적용요율</th>
                                <td colspan="4">
                                    <!--
                                    <select id="applcTariff">
                                        <option value="" selected="selected">선택</option>
                                    </select>
                                     -->
                                    <input size="23" id="applcTariff" class="ygpaCmmnCd calcInput" data-default-prompt="선택" data-code-id="GAM023" />
                                    <!--
                                    <input type="text" size="14" id="applcTariffStr" readonly/>
                                     -->
                                    <input type="hidden" id="applcTariffNm"/>
                                </td>
                              </tr>
                            <tr class="nationAssetLaw">
								<th width="10%" height="18">공시지가목록</th>
                                <td colspan="3">
                                    <select id="olnlpList">
                                        <option value="">선택</option>
                                    </select>
                                </td>
								<th width="10%" height="18">공시지가</th>
                                <td><input type="text" size="25" class="ygpaNumber calcInput" id="olnlp" maxlength="13"/></td>
                            </tr>
                            <tr class="tradePortLaw">
                                <th width="10%" height="18">적용단가</th>
                                <td colspan="5"><input type="text" size="25" class="ygpaNumber calcInput" id="applcPrice" data-decimal-point="1" maxlength="13"/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">면제구분</th>
                                <td>
                                    <input size="17" id="exemptSe" class="ygpaCmmnCd calcInput" data-default-prompt="선택" data-code-id="GAM009" data-column-label-id='exemptSeNm'/>
                                </td>
								<th width="10%" height="18">면제기간</th>
                                <td colspan="3">
                                	<input type="text" class="emdcal calcInput" size="10" id="exemptPdFrom" data-role="dtFrom" data-dt-to="exemptPdTo" readonly/> ~
                                	<input type="text" class="emdcal calcInput" size="10" id="exemptPdTo" data-role="dtTo" data-dt-from="exemptPdFrom" readonly/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">면제사유코드</th>
                                <td colspan="3">
                                    <input size="50" id="exemptRsnCd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM017" />
                                    <!--
                                    <input type="text" size="15" id="exemptRsnCdStr" readonly/>
                                     -->
                                </td>
								<th width="10%" height="18">면제사유</th>
                                <td><input type="text" size="44" id="exemptRsn" maxlength="95"/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">감면사용료</th>
                                <td><input type="text" size="25" class="calcInput" id="rdcxptFee"/></td>
								<th width="10%" height="18">사용료</th>
                                <td colspan="3"><input type="text" size="20" class="ygpaCurrency" id="fee" /></td>
<!-- 								<th width="10%" height="18">부두코드</th>
                                <td>
                                	<input type="text" id="quayCd" size="10" disabled/>
                                	<input type="text" id="quayCdNm" size="32" disabled/>
                                </td> -->
                            </tr>
                            <tr>
								<th width="10%" height="18">산출내역</th>
                                <td colspan="5"><input type="text" size="120" id="computDtls" maxlength="200"/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">사용목적</th>
                                <td colspan="5"><input type="text" size="100" id="usagePurps" maxlength="200"/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">사용내역</th>
                                <td colspan="5"><input type="text" size="100" id="usageDtls" maxlength="45"/></td>
                            </tr>

                            <!--
                            <tr>
                                <th><span class="label">사용 용도 코드</span></th>
                                <td>
                                    <select id="usagePrposCd">
                                        <option value="" selected="selected">선택</option>

                                        <c:forEach  items="${usagePrposCdList}" var="usagePrposCdItem">
                                            <option value="${usagePrposCdItem.code }">${usagePrposCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">포장 구분</span></th>
                                <td>
                                    <select id="packSe">
                                        <option value="" selected="selected">선택</option>

                                        <c:forEach  items="${packSeCdList}" var="packSeCdItem">
                                            <option value="${packSeCdItem.code }">${packSeCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">업체 구분</span></th>
                                <td>
                                    <select id="entrpsSe">
                                        <option value="" selected="selected">선택</option>

                                        <c:forEach  items="${entrpsSeCdList}" var="entrpsSeCdItem">
                                            <option value="${entrpsSeCdItem.code }">${entrpsSeCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th><span class="label">사용료 계산 구분</span></th>
                                <td>
                                    <select id="feeCalcSe">
                                        <option value="" selected="selected">선택</option>

                                        <c:forEach  items="${feeCalcSeCdList}" var="feeCalcSeCdItem">
                                            <option value="${feeCalcSeCdItem.code }">${feeCalcSeCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">감면 사용료 계산 구분</span></th>
                                <td>
                                    <select id="rdcxptFeeCalcSe">
                                        <option value="" selected="selected">선택</option>

                                        <c:forEach  items="${rdcxptFeeCalcSeCdList}" var="rdcxptFeeCalcSeCdItem">
                                            <option value="${rdcxptFeeCalcSeCdItem.code }">${rdcxptFeeCalcSeCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>

                            </tr>
                            <tr>

                                <th><span class="label">해지 일자</span></th>
                                <td><input type="text" class="emdcal" size="10" id="trmnatDt"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">해지 사유</span></th>
                                <td><input type="text" size="10" id="trmnatRsn"/></td>
                                <th><span class="label">GIS 코드</span></th>
                                <td>
                                    <input type="text" size="10" id="detailGisCd"/>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">등록자</span></th>
                                <td><input type="text" size="10" id="detailRegUsr"/></td>
                                <th><span class="label">등록일시</span></th>
                                <td><input type="text" size="10" id="detailRegistDt"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">수정자</span></th>
                                <td><input type="text" size="10" id="detailUpdUsr"/></td>
                                <th><span class="label">수정일시</span></th>
                                <td><input type="text" size="10" id="updtDt"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">부두코드</span></th>
                                <td>
                                    <select id="quayCd">
                                        <option value="" selected="selected">선택</option>

                                        <c:forEach  items="${quayCdList}" var="quayCdItem">
                                            <option value="${quayCdItem.code }">${quayCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th><span class="label">XXX</span></th>
                                <td></td>
                            </tr>
                            -->
                        </table>
                    </form>

                <!--
                <div style="vertical-align: bottom; text-align: right;">
                    <input type="reset" value="취소" class="input_1"> <input
                        type="submit" value="저장" class="input_1">
                </div>
                -->

                <table style="width:100%">
                    <tr>
                        <td><!-- <button id="xxxx">GIS 등록</button><button id="xxxx">위치조회</button> --></td>
                        <td width="100"></td>
                        <td style="text-align:right">
						<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="assetRentDetailList" data-style="default">맵조회</button>
                        <button id="btnRentDetailApply">임대상세적용</button>
                        </td>
                    </tr>
                 </table>

            </div>

            <div id="tabs4" class="emdTabPage" style="overflow: scroll;">

                <table id="assetRentFileList" style="display:none"></table>
                <div class="emdControlPanel"><button id="btnUploadFile">업로드</button><button id="btnDownloadFile">다운로드</button><button id="btnRemoveFile">삭제</button></div>
                <form id="gamAssetRentFileForm">
                    <input type="hidden" id="photoPrtAtCode" data-column-id="prtAtCode"/>
                    <input type="hidden" id="photoMngYear" data-column-id="mngYear"/>
                    <input type="hidden" id="photoMngNo" data-column-id="mngNo"/>
                    <input type="hidden" id="photoMngCnt" data-column-id="mngCnt"/>
                    <input type="hidden" id="photoSeq" data-column-id="photoSeq"/>

                    <table class="editForm">
                        <tr>
							<th width="10%" height="18">사진제목</th>
                            <td>
                                <input id="photoSj" type="text" size="70" class="photoEditItem" maxlength="40"/>
                            </td>
							<th width="10%" height="18">촬영일자</th>
                            <td>
                                <input id="shotDt" type="text" size="15" class="emdcal photoEditItem" readonly>
                            </td>
                        </tr>
                        <tr>
							<th width="10%" height="18">사진파일명</th>
                            <td colspan="3">
                                <input id="filenmLogic" type="text" size="135" class="photoEditItem" disabled/>
                            </td>
                        </tr>
                        <tr>
							<th width="10%" height="18">사진설명</th>
                            <td colspan="3">
                                <input id="photoDesc" type="text" size="135" class="photoEditItem" maxlength="90">
                            </td>
                        </tr>
                    </table>
                </form>
                    <!-- <button id="btnApplyPhotoData">첨부파일 적용</button> -->
                <div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>

            </div>


        </div>
    </div>
</div>