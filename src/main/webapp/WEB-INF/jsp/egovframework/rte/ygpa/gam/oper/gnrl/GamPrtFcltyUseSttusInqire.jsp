<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPrtFcltyUseSttusInqire.jsp
  * @Description : 항만시설사용현황현황조회 (항만시설/일반부두/항만시설사용현황현황조회)
  * @Modification Information
  * 
  *   수정일          수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.01.14  domh          최초 생성
  *
  * author domh
  * since 2014.01.14
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPrtFcltyUseSttusInqireModule() {}

GamPrtFcltyUseSttusInqireModule.prototype = new EmdModule(1100, 650);

// 페이지가 호출 되었을때 호출 되는 함수
GamPrtFcltyUseSttusInqireModule.prototype.loadComplete = function() {

    // 테이블 설정
    this.$("#prtFcltyUseSttusInqireList").flexigrid({
        module: this,
        url: '<c:url value="/oper/gnrl/selectPrtFcltyUseSttusInqireList.do" />',
        dataType: 'json',
        colModel : [
					{display:'항이름', name:'prtAtCodeNm',width:60, sortable:false,align:'center'}, 
                    {display:'관리번호', name:'rentMngNo',width:100, sortable:false,align:'center'}, 
                    {display:'업체명', name:'entrpsNm',width:170, sortable:false,align:'center'}, 
                    {display:'업체코드', name:'entrpscd',width:85, sortable:false,align:'center'}, 
                    {display:'총사용기간 시작', name:'grUsagePdFrom',width:100, sortable:false,align:'center'}, 
                    {display:'총사용기간 종료', name:'grUsagePdTo',width:100, sortable:false,align:'center'}, 
                    {display:'신청구분', name:'reqstSeCdNm',width:60, sortable:false,align:'center'}, 
                    {display:'승낙여부', name:'prmisnYn',width:60, sortable:false,align:'center'},
                    {display:'결재상태', name:'sanctnSttus',width:60, sortable:false,align:'center'},
                    {display:'총사용금액', name:'grFee',width:120, sortable:false,align:'center', displayFormat: 'number'}, 
                    {display:'총사용면적', name:'grAr',width:120, sortable:false,align:'center', displayFormat: 'number'}, 
                    {display:'최초신청일', name:'frstReqstDt',width:70, sortable:false,align:'center'}, 
                    {display:'최초승낙일', name:'frstPrmisnDt',width:90, sortable:false,align:'center'}, 
                    {display:'신청일자', name:'reqstDt',width:70, sortable:false,align:'center'},
                    //{display:'날짜', name:'dt',width:60, sortable:false,align:'center'},
                    {display:'승낙일자', name:'prmisnDt',width:70, sortable:false,align:'center'} 
					
                    /*
                    {display:'항이름', name:'prtAtCodeNm',width:60, sortable:false,align:'center'},
                    {display:'관리번호', name:'rentMngNo',width:100, sortable:false,align:'center'},
                    {display:'업체명', name:'entrpsNm',width:170, sortable:false,align:'center'},
                    {display:'업체코드', name:'entrpscd',width:60, sortable:false,align:'center'},
                    {display:'총사용기간 시작', name:'grUsagePdFrom',width:100, sortable:false,align:'center'},
                    {display:'총사용기간 종료', name:'grUsagePdTo',width:100, sortable:false,align:'center'},
                    {display:'신청구분', name:'reqstSeCdNm',width:60, sortable:false,align:'center'},
                    {display:'허가여부', name:'prmisnYn',width:60, sortable:false,align:'center'},
                    {display:'총사용료', name:'grFee',width:120, sortable:false,align:'center'},
                    {display:'총면적', name:'grAr',width:120, sortable:false,align:'center'},
                    {display:'최초 신청일', name:'frstReqstDt',width:70, sortable:false,align:'center'},
                    {display:'최초 허가일자', name:'frstPrmisnDt',width:90, sortable:false,align:'center'},
                    {display:'날짜', name:'dt',width:60, sortable:false,align:'center'},
                    {display:'허가일자', name:'prmisnDt',width:70, sortable:false,align:'center'},


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

            module.$("#prtFcltyUseSttusInqireListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
            
            return data;
        }
    });

    // 항만시설사용상세 테이블 설정
    this.$("#prtFcltyUseSttusInqireDetailList").flexigrid({
        module: this,
        url: '<c:url value="/oper/gnrl/selectPrtFcltyUseSttusInqireDetailList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'순번', name:'assetsUsageSeq',width:50, sortable:false,align:'center'},
                    {display:'항구분', name:'prtAtCodeNm',width:60, sortable:false,align:'center'},
                    {display:'항코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
                    {display:'시설코드', name:'assetsCdStr',width:60, sortable:false,align:'center'},
                    {display:'시설명', name:'gisAssetsNm',width:140, sortable:false,align:'center'},
                    {display:'사용시작', name:'usagePdFrom',width:70, sortable:false,align:'center'},
                    {display:'사용종료', name:'usagePdTo',width:70, sortable:false,align:'center'},
                    {display:'사용료', name:'fee',width:120, sortable:false,align:'center', displayFormat: 'number'},
                    {display:'사용면적', name:'usageAr',width:120, sortable:false,align:'center', displayFormat: 'number'},
                    //{display:'시설면적', name:'gisAssetsRealRentAr',width:120, sortable:false,align:'center', displayFormat: 'number'},
                    {display:'적용요율', name:'applcTariffNm',width:120, sortable:false,align:'center'},
                    //{display:'적용요율', name:'applcTariff',width:100, sortable:false,align:'center'},
                    {display:'면제구분', name:'exemptSeNm',width:100, sortable:false,align:'center'}

					/*
                    {display:'GIS 자산 코드', name:'gisAssetsCd',width:100, sortable:false,align:'center'},
                    {display:'GIS 자산 SUB 코드', name:'gisAssetsSubCd',width:130, sortable:false,align:'center'},
                    {display:'소재지', name:'gisAssetsLocplc',width:100, sortable:false,align:'center'},
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
           showTableToggleBtn: false,
           height: 'auto'
    });
    
    // 첨부파일 테이블 설정
    this.$("#prtFcltyUseSttusInqireFileList").flexigrid({
        module: this,
        url: '<c:url value="/oper/gnrl/gamSelectPrtFcltyUseSttusInqireFileList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'순번', name:'photoSeq', width:80, sortable:true, align:'center'},
					{display:'사진제목', name:'photoSj', width:300, sortable:true, align:'center'},
					{display:'파일명', name:'filenmLogic', width:200, sortable:true, align:'center'},
					{display:'촬영일시', name:'shotDt', width:120, sortable:true, align:'center'},
					{display:'사진설명', name:'photoDesc', width:280, sortable:true, align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    this.$("#prtFcltyUseSttusInqireList").on('onItemSelected', function(event, module, row, grid, param) {
    	module.$('#cmd').val('modify');
    	
    	module.$('#gamPrtFcltyUseSttusInqireForm :input').val('');
    	
    	module.makeFormValues('#gamPrtFcltyUseSttusInqireForm', row);
        module._editData=module.getFormValues('#gamPrtFcltyUseSttusInqireForm', row);
        module._editRow=module.$('#prtFcltyUseSttusInqireList').selectedRowIds()[0];
        
        //해당하는 항만시설사용상세 목록과 파일목록를 불러온다.
        module.$('#detailPrtAtCode').val(row['prtAtCode']);
        module.$('#prtAtCodeStr').val(row['prtAtCode']);
        module.$('#detailMngYear').val(row['mngYear']);
        module.$('#detailMngNo').val(row['mngNo']);
        module.$('#detailMngCnt').val(row['mngCnt']);
        
        if( row['prmisnYn'] == 'Y' ) {
        	module.$('#entrpscd').attr('readonly', true);
        	module.$('#popupEntrpsInfoInput').attr('disabled', 'disabled');
        }

        var searchOpt=module.makeFormArgs('#gamPrtFcltyUseSttusInqireForm');
        module.$('#prtFcltyUseSttusInqireDetailList').flexOptions({params:searchOpt}).flexReload();
        module.$('#prtFcltyUseSttusInqireFileList').flexOptions({params:searchOpt}).flexReload();
    });
    
    this.$("#prtFcltyUseSttusInqireDetailList").on('onItemSelected', function(event, module, row, grid, param) {
        //module.$('#btnApplyGisAssetsCode').prop('disabled', false);
        module.$('#gamPrtFcltyUseSttusInqireDetailForm :input').val('');
        
        module.makeFormValues('#gamPrtFcltyUseSttusInqireDetailForm', row);
        module._editData=module.getFormValues('#gamPrtFcltyUseSttusInqireDetailForm', row);
        module._editRow=module.$('#prtFcltyUseSttusInqireDetailList').selectedRowIds()[0];
    });
    
    this.$("#prtFcltyUseSttusInqireFileList").on('onItemSelected', function(event, module, row, grid, param) {
        module.makeFormValues('#gamPrtFcltyUseSttusInqireFileForm', row);
        module._editDataFile=module.getFormValues('#gamPrtFcltyUseSttusInqireFileForm', row);
        module._editRowFile=module.$('#prtFcltyUseSttusInqireFileList').selectedRowIds()[0];
        
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
    });

    this.$("#prtFcltyUseSttusInqireList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#prtFcltyUseSttusInqireListTab").tabs("option", {active: 1});    
        module.$('#cmd').val('modify');
        module.$('#gamPrtFcltyUseSttusInqireForm :input').val('');

        module.makeFormValues('#gamPrtFcltyUseSttusInqireForm', row);
        module._editData=module.getFormValues('#gamPrtFcltyUseSttusInqireForm', row);
        module._editRow=module.$('#prtFcltyUseSttusInqireList').selectedRowIds()[0];
        
        if(row!=null) {
            module.$('#cmd').val('modify');  
        }
    });
    
    this.$("#prtFcltyUseSttusInqireDetailList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#prtFcltyUseSttusInqireListTab").tabs("option", {active: 2});
        module.$('#gamPrtFcltyUseSttusInqireDetailForm :input').val('');

        module.makeFormValues('#gamPrtFcltyUseSttusInqireDetailForm', row);
        module._editData=module.getFormValues('#gamPrtFcltyUseSttusInqireDetailForm', row);
        module._editRow=module.$('#prtFcltyUseSttusInqireDetailList').selectedRowIds()[0];
        
        if(row!=null) {
            module.$('#detailCmd').val('modify');
        }
    });
    
    this.$('#sDateSearchGbn').on('change', {module: this}, function(event) {
		if( $(this).val() == '' ) {
			event.data.module.$('#sDateSearchValue').val("");
		}
    });
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamPrtFcltyUseSttusInqireModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
            var searchOpt=this.makeFormArgs('#gamPrtFcltyUseSttusInqireSearchForm');
            this.$('#prtFcltyUseSttusInqireList').flexOptions({params:searchOpt}).flexReload();

            break;

        // 최초신청
        case 'addPrtFcltyUseSttusInqireFirst':
            this.$("#prtFcltyUseSttusInqireListTab").tabs("option", {active: 1});  // 탭을 전환 한다.
            //this.$(":input").val('');
            this.$('#gamPrtFcltyUseSttusInqireForm :input').val("");
            this.$("#cmd").val('insert');

            break;

        // 연장신청
        case 'addPrtFcltyUseSttusInqireRenew':
            var rows = this.$('#prtFcltyUseSttusInqireList').selectedRows();

            if(rows.length>=1) {
                //this.$('#rPrtAtCode').val(row[0]['prtAtCode']);

                this.doAction('<c:url value="/oper/gnrl/gamInsertPrtFcltyUseSttusInqireRenew.do" />', rows[0], function(module, result) {

                    if(result.resultCode=='0') {

                        var searchOpt=module.makeFormArgs('#gamPrtFcltyUseSttusInqireSearchForm');
                        module.$('#prtFcltyUseSttusInqireList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);

                });
                //throw 0;

            } else {
                alert("연장신청할 업체를 선택하십시오.");
            }

            break;

        // 항만시설사용현황 저장
        case 'btnSaveItem':
            var inputVO=this.makeFormArgs('#gamPrtFcltyUseSttusInqireForm');
            if(this.$("#cmd").val()=='insert') {

            	this.doAction('<c:url value="/oper/gnrl/gamInsertPrtFcltyUseSttusInqireFirst.do" />', inputVO, function(module, result) {

                    if(result.resultCode=='0') {
                    	var searchOpt=module.makeFormArgs('#gamPrtFcltyUseSttusInqireForm');
                        module.$('#prtFcltyUseSttusInqireList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });
            }
            else {
                this.doAction('<c:url value="/oper/gnrl/gamUpdatePrtFcltyUseSttusInqire.do" />', inputVO, function(module, result) {
                	if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamPrtFcltyUseSttusInqireForm');
                        module.$('#prtFcltyUseSttusInqireList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });
            }
            break;

        // 항만시설사용현황 취소
        case 'btnCancelItem':
        	this.$('#gamPrtFcltyUseSttusInqireForm :input').val("");
            this.$("#cmd").val('insert');
            break;

        //항만시설사용현황삭제
        case 'btnRemoveItem':
            if( this.$('#cmd').val() == 'modify' ) {
                this.$('#detailPrmisnYn').val( this.$('#prmisnYn').val() );

                var inputVO=this.makeFormArgs('#gamPrtFcltyUseSttusInqireForm');

                this.doAction('<c:url value="/oper/gnrl/gamDeletePrtFcltyUseSttusInqire.do" />', inputVO, function(module, result) {

                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamPrtFcltyUseSttusInqireSearchForm');
                        module.$('#prtFcltyUseSttusInqireList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });

                this.$("#prtFcltyUseSttusInqireListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
                this.$('#gamPrtFcltyUseSttusInqireForm :input').val("");
                this.$("#cmd").val('insert');

            } else {
                alert("상세조회 후 삭제가 가능합니다.");
            }
            break;

        // 항만시설사용현황상세 신규등록
        case 'btnInsertItemDetail':

        	if( this.$('#prtAtCode').val() == '' ) {
        		alert("항만시설사용현황상세 조회후 등록이 가능합니다.");
        	} else {
        		this.$("#prtFcltyUseSttusInqireListTab").tabs("option", {active: 2});  // 탭을 전환 한다.
                this.$('#gamPrtFcltyUseSttusInqireDetailForm :input').val("");

                this.$("#detailCmd").val('insert');
                this.$('#detailPrtAtCode').val( this.$('#prtAtCode').val() );
                this.$('#detailMngYear').val( this.$('#mngYear').val() );
                this.$('#detailMngNo').val( this.$('#mngNo').val() );
                this.$('#detailMngCnt').val( this.$('#mngCnt').val() );
        	}

            break;

        // 항만시설사용현황상세 삭제
        case 'btnRemoveItemDetail':

            var rows = this.$('#prtFcltyUseSttusInqireDetailList').selectedRows();

            if(rows.length>=1) {
                this.doAction('<c:url value="/oper/gnrl/gamDeletePrtFcltyUseSttusInqireDetail.do" />', rows[0], function(module, result) {
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamPrtFcltyUseSttusInqireForm');
                        module.$('#prtFcltyUseSttusInqireDetailList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });

                this.$('#gamPrtFcltyUseSttusInqireDetailForm :input').val("");
                this.$("#detailCmd").val('insert');

            } else {
                alert("삭제할 정보를 선택하십시오.");
            }

            break;

        // 항만시설사용현황상세 저장
        case 'btnSaveItemDetail':

            var inputVO=this.makeFormArgs('#gamPrtFcltyUseSttusInqireDetailForm');
            if(this.$("#detailCmd").val()=='insert') {

                this.doAction('<c:url value="/oper/gnrl/gamInsertPrtFcltyUseSttusInqireDetail.do" />', inputVO, function(module, result) {

                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamPrtFcltyUseSttusInqireForm');
                        module.$('#prtFcltyUseSttusInqireDetailList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });
            }
            else {
            	this.doAction('<c:url value="/oper/gnrl/gamUpdatePrtFcltyUseSttusInqireDetail.do" />', inputVO, function(module, result) {
                    if(result.resultCode=='0') {
                    	var searchOpt=module.makeFormArgs('#gamPrtFcltyUseSttusInqireForm');
                        module.$('#prtFcltyUseSttusInqireDetailList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });
            }
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

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;

        case 'popupEntrpsInfoInput': // 팝업을 호출한다.(항만시설사용현황입력)
            var opts;

            this.doExecuteDialog('insertEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;

        case 'btnPrmisn': // 승낙 (허가)
            var rows = this.$('#prtFcltyUseSttusInqireList').selectedRows();

            if(rows.length>=1) {
            	var opts = {
                    'prtAtCode': rows[0]['prtAtCode'],
                    'mngYear': rows[0]['mngYear'],
                    'mngNo': rows[0]['mngNo'],
            	    'mngCnt': rows[0]['mngCnt']
                };

                this.doExecuteDialog('insertPrtFcltyUseSttusInqirePrmisnPopup', '승낙', '<c:url value="/oper/gnrl/popup/showPrtFcltyUseSttusInqirePrmisn.do"/>', opts);

            } else {
                alert("목록에서 선택하십시오.");
            }

            break;

        case 'btnPrmisnCancel': // 승낙취소 (허가취소)
            var rows = this.$('#prtFcltyUseSttusInqireList').selectedRows();

            if(rows.length>=1) {
                this.doAction('<c:url value="/oper/gnrl/gamUpdatePrtFcltyUseSttusInqirePrmisnCancel.do" />', rows[0], function(module, result) {
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamPrtFcltyUseSttusInqireForm');
                        module.$('#prtFcltyUseSttusInqireList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });
            } else {
                alert("목록에서 선택하십시오.");
            }

            break;

        case 'popupFcltyCd':    //GIS자산코드 팝업을 호출한다.
            var opts;

            this.doExecuteDialog('selectAssetsCdRentPopup', '시설 선택', '<c:url value="/popup/showAssetsCd.do"/>', opts);
            break;

    }
};

GamPrtFcltyUseSttusInqireModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamPrtFcltyUseSttusInqireModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamPrtFcltyUseSttusInqireSearchForm');
    //this.showAlert(searchOpt);
    this.$('#prtFcltyUseSttusInqireList').flexOptions({params:searchOpt}).flexReload();
};

GamPrtFcltyUseSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        var row = this.$('#prtFcltyUseSttusInqireList').selectedRows();
        if(row.length==0) {
            this.$('#cmd').val('insert');
        }
        else {
            this.$('#cmd').val('modify');
        }
        break;
    case 'tabs3':
    	var row = this.$('#prtFcltyUseSttusInqireDetailList').selectedRows();
        if(row.length==0) {
            this.$('#detailCmd').val('insert');
        }
        else {
            this.$('#detailCmd').val('modify');
        }
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamPrtFcltyUseSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
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
     case 'insertPrtFcltyUseSttusInqirePrmisnPopup':
    	 if (msg != 'cancel') {
    		 if( value == "0" ) {
    			 var searchOpt=this.makeFormArgs('#gamPrtFcltyUseSttusInqireSearchForm');
                 this.$('#prtFcltyUseSttusInqireList').flexOptions({params:searchOpt}).flexReload();
             }
         } else {
             alert('취소 되었습니다');
         }
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
             this.$('#gisAssetsQuayGroupCd').val(value.gisAssetsQuayGroupCd);
             this.$('#gisAssetsQuayCd').val(value.gisAssetsQuayCd);
             this.$('#gisAssetsAr').val(value.gisAssetsAr);
             this.$('#gisAssetsRealRentAr').val(value.gisAssetsRealRentAr);
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

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamPrtFcltyUseSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamPrtFcltyUseSttusInqireSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항구분</th>
                            <td>
                                <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" />
                            </td>
                            <th>부두구분</th>
                            <td >
                                <input id="sQuayCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM003" />
                            </td>
                            <th>관리번호</th>
                            <td>
                                <input id="sMngYear" type="text" size="4"> <input id="sMngNo" type="text" size="3"> <input id="sMngCnt" type="text" size="2">
                            </td>
                            <th>신청구분</th>
                            <td >
                                <input id="sReqstSeCd" class="ygpaCmmnCd" data-default-prompt="" data-code-id="GAM011" />
                            </td>
                            <td rowSpan="2"><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                        <tr>
                        	
                            <th>업체명</th>
                            <td>
                                <input id="sEntrpscd" type="text" size="3"><input id="sEntrpsNm" type="text" size="6" readonly> <button id="popupEntrpsInfo">업체</button>
                            </td>
                            <th>사용기간</th>
                            <td>
                            <input id="sGrUsagePdFrom" type="text" class="emdcal"
                                size="8"> ~ <input id="sGrUsagePdTo" type="text"
                                class="emdcal" size="8">
                            </td>
                            <th>신청/허가일자</th>
                            <td>
                            	<select id="sDateSearchGbn" onchange="$(this).trigger('change')">
                                    <option value="" selected="selected">선택</option>
                                    <option value="1">최초신청일</option>
                                    <option value="2">최초허가일</option>
                                    <option value="3">신청일자</option>
                                    <option value="4">허가일자</option>
                                </select>
                                <input id="sDateSearchValue" type="text" class="emdcal" size="10" >
                            </td>
                            <th>승낙여부</th>
                            <td >
                                <select id="sPrmisnYn">
                                    <option value="" selected="selected">전체</option>
                                    <option value="Y">Y</option>
                                    <option value="N">N</option>
                                </select>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="prtFcltyUseSttusInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">항만시설사용현황 목록</a></li>
                <li><a href="#tabs2" class="emdTab">항만시설사용현황 내역</a></li>
                <li><a href="#tabs3" class="emdTab">항만시설사용현황 상세내역</a></li>
                <li><a href="#tabs4" class="emdTab">첨부파일</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="prtFcltyUseSttusInqireList" style="display:none" class="fillHeight"></table>

                <div class="emdControlPanel">
                    <table style="width:100%;">
                        <tr>
                            <td>
                               <form id="form1">
                                   합계 : 
                                   자료수 <input id="totalResultCnt" size="15" style="text-align:right;" readonly>
                                   총면적 <input id="totalArea" type="text" size="15" style="text-align:right;" readonly>
                                   총사용료 <input id="totalUse" type="text" size="15" style="text-align:right;" readonly>원

                                   <input id="loginOrgnztId" type="hidden" value="<c:out value="${loginOrgnztId}"/>"/>
                                   <input id="loginUserId" type="hidden" value="<c:out value="${loginUserId}"/>"/>
                               </form>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                            	<!-- 
                                <button id="addPrtFcltyUseSttusInqireFirst">최초신청</button>
                                <button id="addPrtFcltyUseSttusInqireRenew">연장신청</button>
                                <button id="btnXXX1">신청삭제</button>
                                <button id="btnXXX2">결재요청</button>
                                <button id="btnPrmisn">사용승낙</button>
                                <button id="btnPrmisnCancel">승낙취소</button>
                                 -->
                                <button id="btnXXX3">맵조회</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">
                <div class="emdControlPanel"></div>
                    <form id="gamPrtFcltyUseSttusInqireForm">
                        <input type="hidden" id="cmd"/>

                        <table>
                            <tr>
                                <th><span class="label">항구분</span></th>
                                <!-- <td style="width: 350px"> -->
                                <td colspan="3">
	                                <!-- <input id="prtAtCode" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM019" /> -->
	                                <input type="text" size="10" id="prtAtCodeNm" readonly/>
	                                <input type="text" size="5" id="prtAtCode" readonly/>
                                </td>
                                <!-- 
                                <th><span class="label">담당부서</span></th>
                                <td>
                                    <input id="deptcd" class="ygpaDeptSelect" data-default-prompt="선택" />
                                </td>
                                 -->
                            </tr>
                            <tr>
                                <th><span class="label">관리번호</span></th>
                                <td colspan="3">
                                    <input type="text" size="4" id="mngYear" readonly/>-
                                    <input type="text" size="3" id="mngNo" readonly/>-
                                    <input type="text" size="2" id="mngCnt" readonly/>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">신청업체</span></th>
                                <td colspan="3">
                                    <input type="text" size="12" id="entrpscd" maxlength="10" readonly />
                                    <input type="text" size="25" id="entrpsNm" readonly/>
                                    <!-- <button id="popupEntrpsInfoInput">업체조회</button> -->
                                </td>
                            </tr>
                            <tr>
                                <th style="width: 100px"><span class="label">최초신청일자</span></th>
                                <td style="width: 350px"><input type="text" size="10" id="frstReqstDt" readonly /></td>
                                <th ><span class="label">신청일자</span></th>
                                <td><input type="text" size="10" id="reqstDt" readonly /></td>
                            </tr>
                            <tr>
                                <th><span class="label">승낙여부</span></th>
                                <td>
                                	<!-- 
                                    <select id="prmisnYn">
                                        <option value="" selected="selected">선택</option>
                                        <option value="Y">Y</option>
                                        <option value="N">N</option>
                                    </select>
                                     -->
                                     <input type="text" size="5" id="prmisnYn" style="text-align:center;" readonly/>
                                </td>
                                <th><span class="label">승낙일자</span></th>
                                <td><input type="text" size="10" id="prmisnDt" readonly /></td>
                            </tr>
                            <tr>
                                <th><span class="label">총사용기간</span></th>
                                <td>
                                    <input type="text" size="10" id="grUsagePdFrom" readonly/>
                                    ~
                                    <input type="text" size="10" id="grUsagePdTo" readonly/>
                                </td>
                                <th><span class="label">총사용면적</span></th>
                                <td><input type="text" size="10" class="ygpaNumber" id="grAr" style="text-align:right;" readonly /></td>
                            </tr>
                            <tr>
                                <th><span class="label">총사용료</span></th>
                                <td><input type="text" size="10" class="ygpaCurrency" id="grFee" style="text-align:right;" readonly /></td>
                                <th style="width: 100px"><span class="label">총감면사용료</span></th>
                                <td><input type="text" size="10" class="ygpaCurrency" id="grRdcxptFee" style="text-align:right;" readonly /></td>
                            </tr>
                            <tr>
                            	<!-- 
                                <th><span class="label">납부방법</span></th>
                                <td>
                                    <input id="payMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM043" />
                                </td>
                                 -->
                                 <input type="hidden" id="payMth" value="Pre" />	<!-- 선납 고정 -->
                                <th><span class="label">고지 방법</span></th>
                                <td>
                                    <!-- <input id="nticMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM008" /> -->
                                    <input type="text" size="10" id="nticMthNm" style="text-align:center;" readonly />
                                    
                                </td>
                                <th><span class="label">분납이자율</span></th>
                                <td>
                                	<input type="text" size="10" id="payinstIntrrate" style="text-align:right;" readonly />
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">비고</span></th>
                                <td colspan="3"><input type="text" size="50" id="rm" readonly /></td>
                            </tr>
                            <tr>
                                <th><span class="label">코멘트</span></th>
                                <td colspan="3"><input type="text" size="50" id="cmt" readonly /> <!-- <button id="btnSaveComment">코멘트저장</button>  --></td>
                            </tr>
                        </table>
                    </form>

                <!--
                <div style="vertical-align: bottom; text-align: right;">
                    <input type="reset" value="취소" class="input_1"> <input
                        type="submit" value="저장" class="input_1">
                </div>
                 -->

                 <table>
                    <tr>
                        <td height="30"></td>
                    </tr>
                 </table>
                 <table class="searchPanel">
                    <tbody>
                    <tr>
                        <th>항만시설사용현황 상세목록</th>
                    </tr>
                    </tbody>
                 </table>

                 <table id="prtFcltyUseSttusInqireDetailList" style="display:none"></table>

                 <table style="width:100%">
                 	<!-- 
                    <tr>
                        <td style="text-align:right" colspan="3"><button id="btnInsertItemDetail">항만시설상세추가</button><button id="btnRemoveItemDetail">항만시설상세삭제</button></td>
                    </tr>
                     -->
                    <tr>
                        <td><!-- <button id="xxxx">GIS 등록</button> --><button id="xxxx">위치조회</button></td>
                        <td width="100"></td>
                        <td style="text-align:right">
                        <!-- 
                        <button id="xxxx">결재요청</button><button id="xxxx">사용승낙</button>
                            <button id="xxxx">승낙취소</button><button id="btnRemoveItem">신청삭제</button></button><button id="btnSaveItem">신청저장</button>
                            <!- <button id="btnCancelItem">취소</button> ->
                          -->    
                        </td>
                    </tr>
                 </table>
            </div>

            <div id="tabs3" class="emdTabPage" style="overflow: scroll;">

                <!-- <div class="emdControlPanel"><button id="btnSaveItemDetail">저장</button></div> -->
                    <form id="gamPrtFcltyUseSttusInqireDetailForm">
                        <input type="hidden" id="detailCmd"/>
                        <input type="hidden" id="detailPrtAtCode"/>
                        <input type="hidden" id="detailMngYear"/>
                        <input type="hidden" id="detailMngNo"/>
                        <input type="hidden" id="detailMngCnt"/>
                        <input type="hidden" id="detailPrmisnYn"/>
                        <table >
                            <tr>
                                <th style="width: 100px"><span class="label">시설사용순번</span></th>
                                <td colspan="5"><input type="text" size="10" id="assetsUsageSeq" readonly/></td>
                            </tr>
                            <tr>
                                <th><span class="label">시설코드 </span></th>
                                <td><input type="text" size="3" id="gisAssetsPrtAtCode" readonly/>-<input type="text" size="3" id="gisAssetsCd" readonly/>-<input type="text" size="2" id="gisAssetsSubCd" readonly/>
                                    <!-- <button id="popupFcltyCd" class="popupButton">자산조회</button> -->
                                    </td>
                                <th><span class="label">시설명</span></th>
                                <td colspan="3"><input type="text" size="20" id="gisAssetsNm" disabled/></td>
                            </tr>
                            <tr>
                                <th><span class="label">소재지</span></th>
                                <td ><input type="text" size="50" id="gisAssetsLocplc" disabled/></td>
                                <th><span class="label">지번</span></th>
                                <td><input type="text" size="5" id="gisAssetsLnm" readonly />-<input type="text" size="3" id="gisAssetsLnmSub" disabled/></td>
                            </tr>
                            <!-- 
                            <tr>
                                <th><span class="label">부두그룹코드</span></th>
                                <td><input type="text" size="17" id="gisAssetsQuayGroupCd" disabled/></td>
                                <th><span class="label">부두코드</span></th>
                                <td colspan="3"><input type="text" size="17" id="gisAssetsQuayCd" disabled/></td>
                            </tr>
                             -->
                            <tr>
                                <th><span class="label">시설면적</span></th>
                                <td colspan="3"><input type="text" size="17" id="gisAssetsRealRentAr" disabled/></td>
                                <!-- 
                                <th style="width: 100px"><span class="label">공시지가목록</span></th>
                                <td colspan="3" style="text-align: left;">
                                	
                                    <select id="olnlpList">
                                        <option value="">선택</option>
                                        <c:forEach items="${olnlpList}" var="olnlpItem">
                                            <option value="${olnlpItem.code }">${olnlpItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                     
                                     &nbsp;
                                </td>
                                -->
                            </tr>
                            <tr>
                                <th><span class="label">사용기간</span></th>
                                <td colspan="5"><input type="text" size="10" id="usagePdFrom" readonly />~<input type="text" size="10" id="usagePdTo" readonly /></td>
                            </tr>
                            <tr>
                                <th><span class="label">공시지가</span></th>
                                <td><input type="text" size="17" class="ygpaCurrency" id="olnlp" style="text-align:right;" readonly /></td>
                                <th><span class="label">사용면적</span></th>
                                <td colspan="3"><input type="text" size="15" class="ygpaCurrency" id="usageAr" style="text-align:right;" readonly /></td>
                            </tr>
                            <tr>
                                <th><span class="label">적용요율</span></th>
                                <td>
                                	<!-- 
                                    <select id="applcTariff">
                                        <option value="" selected="selected">선택</option>
                                    </select>
                                     -->
                                    <!-- <input id="applcTariff" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM023" /> -->
                                    <input type="text" size="20" id="applcTariffNm" style="text-align:center;" readonly />
                                    <input type="text" size="14" id="applcTariffStr" style="text-align:center;" readonly />
                                </td>
                                <th><span class="label">적용방법</span></th>
                                <td colspan="3">
                                    <!-- <input id="applcMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM014" /> -->
                                    <input type="text" size="15" id="applcMthNm" style="text-align:center;" readonly />
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">면제구분</span></th>
                                <td colspan="5">
                                    <!-- <input id="exemptSe" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM009" /> -->
                                    <input type="text" size="20" id="exemptSeNm" style="text-align:center;" readonly/>
                                    <!-- <input type="text" size="17" id="exemptSeStr" readonly/> -->
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">면제기간</span></th>
                                <td colspan="5"><input type="text" size="10" id="exemptPdFrom" readonly />~<input type="text" size="10" id="exemptPdTo" readonly /></td>
                            </tr>
                            <tr>
                                <th><span class="label">면제사유</span></th>
                                <td colspan="5">
                                    <!-- <input id="exemptRsnCd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM017" /> -->
                                    <!-- <input type="text" size="20" id="exemptRsnCd" readonly /> -->
                                    <input type="text" size="33" id="exemptRsnCdNm" style="text-align:center;" readonly />
                                    <input type="text" size="15" id="exemptRsnCdStr" readonly/>
                                    <input type="text" size="57" id="exemptRsn" readonly />
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">사용료</span></th>
                                <td><input type="text" size="20" id="fee" class="ygpaCurrency" style="text-align:right;" readonly />원</td>
                                <th><span class="label">감면사용료</span></th>
                                <td colspan="3"><input type="text" size="20" class="ygpaCurrency" id="rdcxptFee" style="text-align:right;" readonly />원</td>
                            </tr>
                            <tr>
                                <th><span class="label">산출내역</span></th>
                                <td colspan="5"><input type="text" size="100" id="computDtls" readonly /></td>
                            </tr>
                            <tr>
                                <th><span class="label">사용목적</span></th>
                                <td colspan="5"><input type="text" size="100" id="usagePurps" readonly /></td>
                            </tr>
                            <tr>
                                <th><span class="label">사용내역</span></th>
                                <td colspan="5"><textarea cols="99" rows="4" size="100" id="usageDtls" readonly ></textarea></td>
                            </tr>
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
                        <td><!-- <button id="xxxx">GIS 등록</button> --><button id="xxxx">위치조회</button></td>
                        <td width="100"></td>
                        <td style="text-align:right">
                        <!-- <button id="xxxx">취소</button><button id="xxxx">항만시설상세적용</button> -->
                        &nbsp;
                        </td>
                    </tr>
                 </table>

            </div>

            <div id="tabs4" class="emdTabPage" style="overflow: scroll;">
                
                <table id="prtFcltyUseSttusInqireFileList" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel"><button id="btnUploadFile">업로드</button><button id="btnDownloadFile">다운로드</button><button id="btnRemoveFile">삭제</button></div>
                <form id="gamPrtFcltyUseSttusInqireFileForm">
                    <input type="hidden" id="photoPrtAtCode" data-column-id="prtAtCode"/>
                    <input type="hidden" id="photoMngYear" data-column-id="mngYear"/>
                    <input type="hidden" id="photoMngNo" data-column-id="mngNo"/>
                    <input type="hidden" id="photoMngCnt" data-column-id="mngCnt"/>
                    <input type="hidden" id="photoSeq" data-column-id="photoSeq"/>
                
                    <table>
                        <tr>
                            <th><span class="label">파일명</span></th>
                            <td>
                                <input id="filenmLogic" type="text" size="60" class="photoEditItem" disabled/>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="label">제 목</span></th>
                            <td>
                                <input id="photoSj" type="text" size="60" class="photoEditItem" maxlength="40" readonly />
                            </td>
                        </tr>
                        <tr>
                            <th><span class="label">사진설명</span></th>
                            <td>
                                <input id="photoDesc" type="text" size="60" class="photoEditItem" maxlength="90" readonly />
                            </td>
                        </tr>
                        <tr>
                            <th><span class="label">촬영일시</span></th>
                            <td>
                                <input id="shotDt" type="text" size="10" class="emdcal photoEditItem" readonly>
                            </td>
                        </tr>
                    </table>
                </form>
                    <button id="btnApplyPhotoData">첨부파일 적용</button>
                <div class="emdPanel fillHeight" style="overflow:scroll"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>

            </div>


        </div>
    </div>
</div>