<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetRentMngt.jsp
  * @Description : 자산임대관리
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
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetRentMngtModule() {}

GamAssetRentMngtModule.prototype = new EmdModule(1100, 650);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetRentMngtModule.prototype.loadComplete = function() {

    // 자산임대 테이블 설정
    this.$("#assetRentMngtList").flexigrid({
        module: this,
        url: '<c:url value="/asset/rent/gamSelectAssetRentList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'항이름', name:'prtAtCodeNm',width:60, sortable:false,align:'center'}, 
                    {display:'관리번호', name:'rentMngNo',width:100, sortable:false,align:'center'}, 
                    {display:'업체명', name:'entrpsNm',width:170, sortable:false,align:'center'}, 
                    {display:'업체코드', name:'entrpscd',width:60, sortable:false,align:'center'}, 
                    {display:'총사용기간 시작', name:'grUsagePdFrom',width:100, sortable:false,align:'center'}, 
                    {display:'총사용기간 종료', name:'grUsagePdTo',width:100, sortable:false,align:'center'}, 
                    {display:'신청구분', name:'reqstSeCdNm',width:60, sortable:false,align:'center'}, 
                    {display:'허가여부', name:'prmisnYn',width:60, sortable:false,align:'center'}, 
                    {display:'총사용료', name:'grFee',width:120, sortable:false,align:'center', displayFormat: 'number'}, 
                    {display:'총면적', name:'grAr',width:120, sortable:false,align:'center', displayFormat: 'number'}, 
                    {display:'최초 신청일', name:'frstReqstDt',width:70, sortable:false,align:'center'}, 
                    {display:'최초 허가일자', name:'frstPrmisnDt',width:90, sortable:false,align:'center'}, 
                    {display:'날짜', name:'dt',width:60, sortable:false,align:'center'},
                    {display:'허가일자', name:'prmisnDt',width:70, sortable:false,align:'center'} 
                    
                    /*
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

            return data;
        }
    });

    // 자산임대상세 테이블 설정
    this.$("#assetRentDetailList").flexigrid({
        module: this,
        url: '<c:url value="/asset/rent/gamSelectAssetRentDetailList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'순번', name:'assetsUsageSeq',width:50, sortable:false,align:'center'},
                    {display:'항구분', name:'prtAtCodeNm',width:60, sortable:false,align:'center'},
                    {display:'항코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
                    {display:'자산코드', name:'assetsCdStr',width:60, sortable:false,align:'center'},
                    {display:'자산명', name:'gisAssetsNm',width:140, sortable:false,align:'center'},
                    {display:'사용시작', name:'usagePdFrom',width:70, sortable:false,align:'center'},
                    {display:'사용종료', name:'usagePdTo',width:70, sortable:false,align:'center'},
                    {display:'사용료', name:'fee',width:120, sortable:false,align:'center', displayFormat: 'number'},
                    {display:'사용면적', name:'usageAr',width:120, sortable:false,align:'center', displayFormat: 'number'},
                    {display:'적용요율', name:'applcTariff',width:100, sortable:false,align:'center'},
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
    this.$("#assetRentFileList").flexigrid({
        module: this,
        url: '<c:url value="/asset/rent/gamSelectAssetRentFileList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'순번', name:'assetsUsageSeq',width:100, sortable:false,align:'center'},
                    {display:'제목', name:'prtAtCodeNm',width:250, sortable:false,align:'center'},
                    {display:'파일명', name:'prtAtCode',width:250, sortable:false,align:'center'},
                    {display:'파일설명', name:'assetsCdStr',width:300, sortable:false,align:'center'}
                    
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
    
    this.$("#assetRentMngtList").on('onItemSelected', function(event, module, row, grid, param) {
        module.makeFormValues('#gamAssetRentForm', row);
        module._editData=module.getFormValues('#gamAssetRentForm', row);
        module._editRow=module.$('#assetRentMngtList').selectedRowIds()[0];
        
        //해당하는 자산임대상세 목록과 파일목록를 불러온다.
        module.$('#detailPrtAtCode').val(row['prtAtCode']);
        module.$('#prtAtCodeStr').val(row['prtAtCode']);
        module.$('#detailMngYear').val(row['mngYear']);
        module.$('#detailMngNo').val(row['mngNo']);
        module.$('#detailMngCnt').val(row['mngCnt']);

        var searchOpt=module.makeFormArgs('#gamAssetRentForm');
        module.$('#assetRentDetailList').flexOptions({params:searchOpt}).flexReload();
        module.$('#assetRentFileList').flexOptions({params:searchOpt}).flexReload();
    });
    
    this.$("#assetRentDetailList").on('onItemSelected', function(event, module, row, grid, param) {
        //module.$('#btnApplyGisAssetsCode').prop('disabled', false);
        module.makeFormValues('#gamAssetRentDetailForm', row);
        module._editData=module.getFormValues('#gamAssetRentDetailForm', row);
        module._editRow=module.$('#assetRentDetailList').selectedRowIds()[0];
    });

    this.$("#assetRentMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#assetRentListTab").tabs("option", {active: 1});    

        if(row!=null) {
            module.$('#cmd').val('modify');  
        }
    });
    
    this.$("#assetRentDetailList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#assetRentListTab").tabs("option", {active: 2});

        if(row!=null) {
            module.$('#detailCmd').val('modify');
        }
    });
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamAssetRentMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
            this.$("#assetRentListTab").tabs("option", {active: 0});
            
            var searchOpt=this.makeFormArgs('#gamAssetRentMngtSearchForm');
            this.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();

            break;

        // 최초신청
        case 'addAssetRentFirst':
            this.$("#assetRentListTab").tabs("option", {active: 1});  // 탭을 전환 한다.
            this.$('#gamAssetRentForm').find(':input').val('');
            //this.$("#assetRentDetailList").flexRemove();
            this.$("#cmd").val('insert');

            break;

        // 연장신청
        case 'addAssetRentRenew':
            var rows = this.$('#assetRentMngtList').selectedRows();

            if(rows.length>=1) {
                //this.$('#rPrtAtCode').val(row[0]['prtAtCode']);
                
                if( confirm("연장신청을 하시겠습니까?") ) {
                    this.doAction('<c:url value="/asset/rent/gamInsertAssetRentRenew.do" />', rows[0], function(module, result) {
    
                        if(result.resultCode=='0') {
                            var searchOpt=module.makeFormArgs('#gamAssetRentMngtSearchForm');
                            module.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
                        }
    
                        alert(result.resultMsg);
                    });
                //throw 0;
                }
            } else {
                alert("목록에서 연장신청할 업체를 선택하십시오.");
            }

            break;

        // 자산임대 저장
        case 'btnSaveItem':
            var inputVO=this.makeFormArgs('#gamAssetRentForm');
            if(this.$("#cmd").val()=='insert') {

                this.doAction('<c:url value="/asset/rent/gamInsertAssetRentFirst.do" />', inputVO, function(module, result) {

                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamAssetRentForm');
                        module.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });
            }
            else {
                this.doAction('<c:url value="/asset/rent/gamUpdateAssetRent.do" />', inputVO, function(module, result) {
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamAssetRentForm');
                        module.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });
            }
            break;

        //신청삭제
        case 'btnRemoveItem':
        	var rows = this.$('#assetRentMngtList').selectedRows();
            
            if(rows.length == 0) {
                alert("자산임대목록에서 신청삭제할 행을 선택하십시오.");
            } else {
	        	if( rows[0]['prmisnYn'] == null || rows[0]['prmisnYn'] == '' ) {
	        		this.$('#detailPrmisnYn').val('N');
	        		
	        		alert( this.$('#detailPrmisnYn').val() );
	        	}
	        	
	        	var inputVO=this.makeFormArgs('#gamAssetRentForm');
	            
                this.doAction('<c:url value="/asset/rent/gamDeleteAssetRent.do" />', inputVO, function(module, result) {

                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamAssetRentMngtSearchForm');
                        module.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });

                this.$("#assetRentListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
                this.$('#gamAssetRentForm :input').val("");
                this.$("#cmd").val('insert');
            }
            
            break;

        //임대상세추가
        case 'btnInsertItemDetail':
            if( this.$('#prtAtCode').val() == '' ) {
                alert("자산임대목록에서 등록할 행을 더블클릭한 후 시도하십시오.");
            } else {
                this.$("#assetRentListTab").tabs("option", {active: 2});  // 탭을 전환 한다.
                this.$('#gamAssetRentDetailForm').find(':input').val('');

                this.$("#detailCmd").val('insert');
                this.$('#detailPrtAtCode').val( this.$('#prtAtCode').val() );
                //this.$('#detailPrtAtCodeNm').val( this.$('#prtAtCodeNm').val() );
                this.$('#detailMngYear').val( this.$('#mngYear').val() );
                this.$('#detailMngNo').val( this.$('#mngNo').val() );
                this.$('#detailMngCnt').val( this.$('#mngCnt').val() );
                
                //this.$('#prtAtCodeNm').val( 'qqqq' );
                

                this._editData=this.getFormValues('#gamAssetRentDetailForm', {_updtId:'I'});
                this._editRow=this.$('#assetRentDetailList').flexGetData().length;
            }

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

                this.doAction('<c:url value="/asset/rent/gamInsertAssetRentDetail.do" />', {aaa : "rrrrrrrrrrrrrr"}, function(module, result) {

                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamAssetRentForm');
                        module.$('#assetRentDetailList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });
            }
            else {
                this.doAction('<c:url value="/asset/rent/gamUpdateAssetRentDetail.do" />', inputVO, function(module, result) {
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

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;

        case 'popupEntrpsInfoInput': // 팝업을 호출한다.(자산임대입력)
            var opts;

            this.doExecuteDialog('insertEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;

        case 'btnPrmisn': // 사용승낙
            var rows = this.$('#assetRentMngtList').selectedRows();
            
            if(rows.length>=1) {
                var opts = {
                    'prtAtCode': rows[0]['prtAtCode'],
                    'mngYear': rows[0]['mngYear'],
                    'mngNo': rows[0]['mngNo'],
                    'mngCnt': rows[0]['mngCnt']
                };

                this.doExecuteDialog('insertAssetRentPrmisnPopup', '승낙', '<c:url value="/asset/rent/popup/showAssetRentPrmisn.do"/>', opts);
                
            } else {
                alert("목록에서 선택하십시오.");
            }
        
            break;
        
        case 'btnPrmisnCancel': // 승낙취소
            var rows = this.$('#assetRentMngtList').selectedRows();
            
            if(rows.length>=1) {
                if( confirm("승낙취소를 하시겠습니까?") ) {
                    this.doAction('<c:url value="/asset/rent/gamUpdateAssetRentPrmisnCancel.do" />', rows[0], function(module, result) {
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
        
        case 'popupFcltyCd':    //GIS자산코드 팝업을 호출한다.
            var opts;
            
            this.doExecuteDialog('selectAssetsCdRentPopup', '시설 선택', '<c:url value="/popup/showAssetsCd.do"/>', opts);
            break;    
            
        case 'btnRentDetailApply': //임대상세적용
            
        	if(this._editData==null) return;   // 추가나 삭제가 없으면 적용 안됨 2014-03-11 추가
            this._editData=this.getFormValues('#gamAssetRentDetailForm', this._editData);
            //this._editData=this.getFormValues('#gamAssetRentDetailForm', this._editData);
            if(this._editRow!=null) {  // 이전에 _updtId 로 선택 한 것을 _editRow 로 변경 2014-03-14.001
                if(this._editData._updtId!='I') this._editData._updtId='U';   // 삽입된 데이터가 아니면 업데이트 플래그를 추가한다.
                this.$('#assetRentDetailList').flexUpdateRow(this._editRow, this._editData);
                this._editRow=null;    // 편집 저장 하였으므로 로우 편집을 종료 한다.
            }
            else {
                this.$('#assetRentDetailList').flexAddRow(this._editData);
            }
            
            this.$('#gamAssetRentDetailForm').find(':input').val('');
            this._editData=null;       // 적용 이후 데이터 추가나 삭제 가 되지 않도록 편집 데이터를 제거 함/ 2014-03-11 추가
        
            /*
        	if(this._editData==null) return;   // 추가나 삭제가 없으면 적용 안됨 2014-03-11 추가
            this._editData=this.getFormValues('#gamAssetRentDetailForm', this._editData);
            if(this._editData._updtId==undefined || this._editData._updtId!='I') {
                this._editData._updtId='U';
                this.$('#assetRentDetailList').flexUpdateRow(this._editRow, this._editData);
            }
            else {
                this.$('#assetRentDetailList').flexAddRow(this._editData);
            }
            this.$('#gamAssetRentDetailForm').find(':input').val('');
            this._editData=null;       // 적용 이후 데이터 추가나 삭제 가 되지 않도록 편집 데이터를 제거 함/ 2014-03-11 추가
            */
            
            
            /*
            this._editData=this.getFormValues('#gamAssetRentDetailForm', this._editData);
            if(this._editData._updtId==undefined || this._editData._updtId!='I') {
                this._editData._updtId='U';
                this.$('#assetRentDetailList').flexUpdateRow(this._editRow, this._editData);
            }
            else {
                this.$('#assetRentDetailList').flexAddRow(this._editData);
            }
            */
            
            
            
            break;
    }
};

GamAssetRentMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamAssetRentMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamAssetRentMngtSearchForm');
    //this.showAlert(searchOpt);
    this.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
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
        
        this._deleteDataList=[];    // 삭제 목록 초기화
        
        break;
    case 'tabs3':
        var row = this.$('#assetRentDetailList').selectedRows();
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
             this.$('#gisAssetsRealRentAr').val(value.gisAssetsRealRentAr);
             this.$('#prtAtCodeNm').val(value.gisAssetsPrtAtCodeNm);
             this.$('#gisAssetsQuayCd').val(value.gisAssetsQuayCd);
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
                                <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM019 />
                            </td>
                            <th>신청구분</th>
                            <td width="100px">
                                <input id="sReqstSeCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM011 />
                            </td>
                            <th>신청업체</th>
                            <td>
                                <input id="sEntrpscd" type="text" size="3"><input id="sEntrpsNm" type="text" size="6" readonly> <button id="popupEntrpsInfo">업체</button>
                            </td>
                            <th>사용용도</th>
                            <td>
                                <input id="sUsagePrposCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM007 />
                            </td>
                            <td rowSpan="2"><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                        <tr>
                            <th>관리번호</th>
                            <td>
                                <input id="sMngYear" type="text" size="4"> <input id="sMngNo" type="text" size="3"> <input id="sMngCnt" type="text" size="2">
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
                                <input id="sGrAr" type="text" size="5">
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
                <li><a href="#tabs1" class="emdTab">자산임대 목록</a></li>
                <li><a href="#tabs2" class="emdTab">자산임대 내역</a></li>
                <li><a href="#tabs3" class="emdTab">자산임대 상세내역</a></li>
                <li><a href="#tabs4" class="emdTab">첨부파일</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="assetRentMngtList" style="display:none" class="fillHeight"></table>

                <div class="emdControlPanel">
                    <table style="width:100%;">
                        <tr>
                            <td>
                               <form id="form1">
                                   합계 : 
                                   자료수 <input id="totalResultCnt" size="15" readonly>
                                   총면적 <input id="totalArea" type="text" size="15" readonly>
                                   총사용료 <input id="totalUse" type="text" size="15" readonly>원
                                   
                                   <input id="loginOrgnztId" type="hidden" value="<c:out value="${loginOrgnztId}"/>"/>
                                   <input id="loginUserId" type="hidden" value="<c:out value="${loginUserId}"/>"/> 
                               </form>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                <button id="addAssetRentFirst">최초신청</button>
                                <button id="addAssetRentRenew">연장신청</button>
                                <button id="btnRemoveItem">신청삭제</button>
                                <button id="btnXXX2">결재요청</button>
                                <button id="btnPrmisn">사용승낙</button>
                                <button id="btnPrmisnCancel">승낙취소</button>
                                <button id="btnXXX3">맵조회</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">
                <div class="emdControlPanel"></div>
                    <form id="gamAssetRentForm">
                        <input type="hidden" id="cmd"/>

                        <table>
                            <tr>
                                <th><span class="label">항구분</span></th>
                                <td style="width: 350px">
                                    <input id="prtAtCode" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM019 />
                                    <input type="text" size="5" id="prtAtCodeStr" readonly/>
                                </td>
                                <th><span class="label">담당부서</span></th>
                                <td>
                                    <input id="deptcd" class="ygpaDeptSelect" data-default-prompt="선택" />
                                </td>
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
                                    <input type="text" size="5" id="entrpscd" maxlength="10"/>
                                    <input type="text" size="25" id="entrpsNm" readonly/>
                                    <button id="popupEntrpsInfoInput">업체조회</button>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">최초신청일자</span></th>
                                <td><input type="text" class="emdcal" size="10" id="frstReqstDt"/></td>
                                <th><span class="label">신청일자</span></th>
                                <td><input type="text" class="emdcal" size="10" id="dt"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">승낙여부</span></th>
                                <td>
                                    <select id="prmisnYn">
                                        <option value="" selected="selected">선택</option>
                                        <option value="Y">Y</option>
                                        <option value="N">N</option>
                                    </select>
                                </td>
                                <th><span class="label">승낙일자</span></th>
                                <td><input type="text" class="emdcal" size="10" id="prmisnDt"></td>
                            </tr>
                            <tr>
                                <th><span class="label">총사용기간</span></th>
                                <td>
                                    <input type="text" class="emdcal" size="10" id="grUsagePdFrom"/>~
                                    <input type="text" class="emdcal" size="10" id="grUsagePdTo"/>
                                </td>
                                <th><span class="label">총사용면적</span></th>
                                <td><input type="text" size="10" id="grAr"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">총사용료</span></th>
                                <td><input type="text" size="10" id="grFee"/></td>
                                <th><span class="label">총감면사용료</span></th>
                                <td><input type="text" size="10" id="grRdcxptFee"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">납부방법</span></th>
                                <td>
                                    <input id="payMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM043 />
                                </td>
                                <th><span class="label">고지 방법</span></th>
                                <td>
                                    <input id="nticMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM008 />
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">비고</span></th>
                                <td colspan="3"><input type="text" size="50" id="rm"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">코멘트</span></th>
                                <td colspan="3"><input type="text" size="50" id="cmt"/><button id="btnSaveComment">코멘트저장</button></td>
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
                        <th>자산임대상세목록</th>
                    </tr>
                    </tbody>
                 </table>

                 <!-- <table id="assetRentDetailList" style="display:none" class="fillHeight"></table> -->
                 <table id="assetRentDetailList" style="display:none"></table>
                 
                 <table style="width:100%">
                    <tr>
                        <td style="text-align:right" colspan="3"><button id="btnInsertItemDetail">임대상세추가</button><button id="btnRemoveItemDetail">임대상세삭제</button></td>
                    </tr>
                    <tr>
                        <td><button id="xxxx">GIS 등록</button><button id="xxxx">위치조회</button></td>
                        <td width="100"></td>
                        <td style="text-align:right"><button id="xxxx">결재요청</button><button id="btnPrmisn">사용승낙</button>
                            <button id="btnPrmisnCancel">승낙취소</button><button id="btnRemoveItem">신청삭제</button><button id="btnSaveItem">신청저장</button>
                            <!-- <button id="btnCancelItem">취소</button>  -->
                        </td>
                    </tr>
                 </table>
            </div>

            <div id="tabs3" class="emdTabPage" style="overflow: scroll;">

                <!-- <div class="emdControlPanel"><button id="btnSaveItemDetail">저장</button></div>  -->
                    <form id="gamAssetRentDetailForm">
                        <input type="hidden" id="detailCmd"/>
                        <input type="hidden" id="detailPrtAtCode" data-column-id="prtAtCode"/>
                        <input type="hidden" id="detailMngYear" data-column-id="mngYear"/>
                        <input type="hidden" id="detailMngNo" data-column-id="mngNo"/>
                        <input type="hidden" id="detailMngCnt" data-column-id="mngCnt"/>
                        
                        <input type="hidden" id="detailPrmisnYn"/>
                        <table>
                            <tr>
                                <th style="width: 80px"><span class="label">자산사용순번</span></th>
                                <td colspan="5"><input type="text" size="10" id="assetsUsageSeq" readonly/>
                                
                                <input type="text" id="prtAtCodeNm" />
                                <input type="text" id="gisAssetsQuayCd" />
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">자산코드 </span></th>
                                <td><input type="text" size="3" id="gisAssetsPrtAtCode" readonly/>-<input type="text" size="3" id="gisAssetsCd" readonly/>-<input type="text" size="2" id="gisAssetsSubCd" readonly/>
                                    <button id="popupFcltyCd" class="popupButton">자산조회</button></td>
                                <th><span class="label">자산명</span></th>
                                <td colspan="3"><input type="text" size="20" id="gisAssetsNm" disabled/></td>
                            </tr>
                            <tr>
                                <th><span class="label">소재지</span></th>
                                <td colspan="3"><input type="text" size="67" id="gisAssetsLocplc" disabled/></td>
                                <th><span class="label">지번</span></th>
                                <td><input type="text" size="5" id="gisAssetsLnm"/>-<input type="text" size="3" id="gisAssetsLnmSub" disabled/></td>
                            </tr>
                            <tr>
                                <th><span class="label">자산면적</span></th>
                                <td style="width: 230px"><input type="text" size="17" id="gisAssetsAr" disabled/></td>
                                <th style="width: 80px"><span class="label">실제임대면적</span></th>
                                <td style="width: 170px"><input type="text" size="17" id="gisAssetsRealRentAr" disabled/></td>
                                <th style="width: 120px"><span class="label">공시지가목록</span></th>
                                <td>
                                    <select id="olnlpList">
                                        <option value="">선택</option>
                                        <c:forEach items="${olnlpList}" var="olnlpItem">
                                            <option value="${olnlpItem.code }">${olnlpItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">사용기간</span></th>
                                <td colspan="5"><input type="text" class="emdcal" size="10" id="usagePdFrom"/>~<input type="text" class="emdcal" size="10" id="usagePdTo"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">공시지가</span></th>
                                <td><input type="text" size="17" id="olnlp"/></td>
                                <th><span class="label">사용면적</span></th>
                                <td colspan="3"><input type="text" size="17" id="usageAr"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">적용요율</span></th>
                                <td>
                                    <!-- 
                                    <select id="applcTariff">  
                                        <option value="" selected="selected">선택</option>
                                    </select>
                                     -->
                                    <input id="applcTariff" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM023 />
                                    
                                    <input type="text" size="14" id="applcTariffStr"/>
                                </td>
                                <th><span class="label">적용방법</span></th>
                                <td colspan="3">
                                    <input id="applcMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM014 />
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">면제구분</span></th>
                                <td colspan="5">
                                    <input id="exemptSe" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM009  data-column-label-id='exemptSeNm'/>
                                    <input type="text" size="17" id="exemptSeStr" readonly/>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">면제기간</span></th>
                                <td colspan="5"><input type="text" class="emdcal" size="10" id="exemptPdFrom"/>~<input type="text" class="emdcal" size="10" id="exemptPdTo"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">면제사유</span></th>
                                <td colspan="5">
                                    <input id="exemptRsnCd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM017 />
                                    
                                    <input type="text" size="15" id="exemptRsnCdStr" readonly/>
                                    <input type="text" size="70" id="exemptRsn"/>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">사용료</span></th>
                                <td><input type="text" size="20" id="fee"/>원</td>
                                <th><span class="label">감면사용료</span></th>
                                <td colspan="3"><input type="text" size="20" id="rdcxptFee"/>원</td>
                            </tr>
                            <tr>
                                <th><span class="label">산출내역</span></th>
                                <td colspan="5"><input type="text" size="100" id="computDtls"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">사용목적</span></th>
                                <td colspan="5"><input type="text" size="100" id="usagePurps"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">사용내역</span></th>
                                <td colspan="5"><input type="text" size="100" id="usageDtls"/></td>
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
                        <td><button id="xxxx">GIS 등록</button><button id="xxxx">위치조회</button></td>
                        <td width="100"></td>
                        <td style="text-align:right"><button id="xxxx">취소</button><button id="btnRentDetailApply">임대상세적용</button>
                        </td>
                    </tr>
                 </table>

            </div>
            
            <div id="tabs4" class="emdTabPage" style="overflow: scroll;">
                <!-- <table id="assetRentFileList" style="display:none"  class="fillHeight"></table> -->
                <table id="assetRentFileList" style="display:none"></table>
                
                <div class="emdControlPanel"><!-- <button id="addAssetGisPhoto">추가</button><button id="removeAssetGisPhoto">삭제</button> --></div>
                <!-- 
                <div class="emdPanel" style="overflow:scroll"><img style="border: 1px solid #000; max-width:800px; max-height: 600px" src="<c:url value='images/egovframework/ygpa/gam/misc/TEST2.JPG'/>"></div>
                 -->
                 
                <div style="vertical-align: bottom; text-align: right;">
                    <button id="xxxx">업로드</button>
                    <button id="xxxx">다운로드</button>
                    <button id="xxxx">삭제</button>
                </div> 
                
                <table>
                    <tr height="30">
                        <td colspan="2"></td>
                    </tr>
                    <tr>
                        <th>
                            제목
                        </th>
                        <td>
                            <input type="text" size="130" id="xxx"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            파일설명
                        </th>
                        <td>
                            <input type="text" size="130" id="xxx"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right" colspan="2">
                            <button id="xxxx">적용</button>
                        </td>
                    </tr>
                </table>
                
                <table class="searchPanel">
                    <tbody>
                    <tr>
                        <th>미리보기</th>
                    </tr>
                    </tbody>
                </table> 
                <div class="emdPanel"><img style="border: 1px solid #000; max-width:800px; max-height: 600px" src="<c:url value='images/egovframework/ygpa/gam/misc/TEST2.JPG'/>"></div>
                
            </div>
            
            
        </div>
    </div>
</div>