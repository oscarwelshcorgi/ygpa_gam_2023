<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamCntnrQuayRentSttusInqire.jsp
  * @Description : 컨테이너부두임대현황조회 
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
function GamCntnrQuayRentSttusInqireModule() {}

GamCntnrQuayRentSttusInqireModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamCntnrQuayRentSttusInqireModule.prototype.loadComplete = function() {

    // 테이블 설정
    this.$("#cntnrQuayRentSttusInqireList").flexigrid({
        module: this,
        url: '<c:url value="/oper/cntnr/selectCntnrQuayRentSttusInqireList.do" />',
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
                    {display:'분납이자율', name:'payinstIntrrate',width:60, sortable:false,align:'center'},
                    {display:'최초신청일자', name:'frstReqstDt',width:80, sortable:false,align:'center'},
                    {display:'최초승낙일자', name:'frstPrmisnDt',width:80, sortable:false,align:'center'},
                    {display:'결재일시', name:'sanctnDt',width:110, sortable:false,align:'center'},
                    {display:'문서번호', name:'docNo',width:80, sortable:false,align:'left'},
                    {display:'비고', name:'rm',width:200, sortable:false,align:'left'}
					
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
            module.$('#totalGrRdcxptFee').val(data.sumGrRdcxptFee);

            module.$("#cntnrQuayRentSttusInqireListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
            
            return data;
        }
    });

    // 컨테이너부두임대상세 테이블 설정
    this.$("#cntnrQuayRentSttusInqireDetailList").flexigrid({
        module: this,
        url: '<c:url value="/oper/cntnr/selectCntnrQuayRentSttusInqireDetailList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'순번', name:'assetsUsageSeq',width:30, sortable:false,align:'center'},
                    {display:'항코드', name:'dtlPrtAtCode',width:40, sortable:false,align:'center'},
                    {display:'항코드명', name:'dtlPrtAtCodeNm',width:55, sortable:false,align:'center'},
                    {display:'자산코드', name:'assetsCdStr',width:60, sortable:false,align:'center'},
                    {display:'자산명', name:'gisAssetsNm',width:140, sortable:false,align:'left'},
                    {display:'사용시작', name:'usagePdFrom',width:70, sortable:false,align:'center'},
                    {display:'사용종료', name:'usagePdTo',width:70, sortable:false,align:'center'},
                    {display:'사용료', name:'fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'사용면적', name:'usageAr',width:80, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'적용요율', name:'applcTariffNm',width:80, sortable:false,align:'center'},
                    {display:'면제구분', name:'exemptSeNm',width:60, sortable:false,align:'center'}

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
           height: '115'
    });
    
    // 첨부파일 테이블 설정
    this.$("#cntnrQuayRentSttusInqireFileList").flexigrid({
        module: this,
        url: '<c:url value="/oper/cntnr/gamSelectCntnrQuayRentSttusInqireFileList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'순번', name:'photoSeq', width:80, sortable:true, align:'center'},
                    {display:'사진제목', name:'photoSj', width:200, sortable:true, align:'left'},
                    {display:'사진파일명', name:'filenmLogic', width:200, sortable:true, align:'left'},
                    {display:'사진설명', name:'photoDesc', width:250, sortable:true, align:'left'},
                    {display:'촬영일자', name:'shotDt', width:100, sortable:true, align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    this.$("#cntnrQuayRentSttusInqireList").on('onItemSelected', function(event, module, row, grid, param) {
    	module.$('#cmd').val('modify');
    	
    	module.$('#gamCntnrQuayRentSttusInqireForm :input').val('');
    	
    	module.makeFormValues('#gamCntnrQuayRentSttusInqireForm', row);
        module._editData=module.getFormValues('#gamCntnrQuayRentSttusInqireForm', row);
        module._editRow=module.$('#cntnrQuayRentSttusInqireList').selectedRowIds()[0];
        
        //해당하는 컨테이너부두임대상세 목록과 파일목록를 불러온다.
        module.$('#detailPrtAtCode').val(row['prtAtCode']);
        module.$('#prtAtCodeStr').val(row['prtAtCode']);
        module.$('#detailMngYear').val(row['mngYear']);
        module.$('#detailMngNo').val(row['mngNo']);
        module.$('#detailMngCnt').val(row['mngCnt']);
        
        if( row['prmisnYn'] == 'Y' ) {
        	module.$('#entrpscd').attr('readonly', true);
        	module.$('#popupEntrpsInfoInput').attr('disabled', 'disabled');
        }

        var searchOpt=module.makeFormArgs('#gamCntnrQuayRentSttusInqireForm');
        module.$('#cntnrQuayRentSttusInqireDetailList').flexOptions({params:searchOpt}).flexReload();
        module.$('#cntnrQuayRentSttusInqireFileList').flexOptions({params:searchOpt}).flexReload();
    });
    
    this.$("#cntnrQuayRentSttusInqireDetailList").on('onItemSelected', function(event, module, row, grid, param) {
        //module.$('#btnApplyGisAssetsCode').prop('disabled', false);
        module.$('#gamCntnrQuayRentSttusInqireDetailForm :input').val('');
        
        module.makeFormValues('#gamCntnrQuayRentSttusInqireDetailForm', row);
        module._editData=module.getFormValues('#gamCntnrQuayRentSttusInqireDetailForm', row);
        module._editRow=module.$('#cntnrQuayRentSttusInqireDetailList').selectedRowIds()[0];
    });
    
    this.$("#cntnrQuayRentSttusInqireFileList").on('onItemSelected', function(event, module, row, grid, param) {
        module.makeFormValues('#gamCntnrQuayRentSttusInqireFileForm', row);
        module._editDataFile=module.getFormValues('#gamCntnrQuayRentSttusInqireFileForm', row);
        module._editRowFile=module.$('#cntnrQuayRentSttusInqireFileList').selectedRowIds()[0];
        
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

    this.$("#cntnrQuayRentSttusInqireList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#cntnrQuayRentSttusInqireListTab").tabs("option", {active: 1});    
        module.$('#cmd').val('modify');
        module.$('#gamCntnrQuayRentSttusInqireForm :input').val('');

        module.makeFormValues('#gamCntnrQuayRentSttusInqireForm', row);
        module._editData=module.getFormValues('#gamCntnrQuayRentSttusInqireForm', row);
        module._editRow=module.$('#cntnrQuayRentSttusInqireList').selectedRowIds()[0];
        
        if(row!=null) {
            module.$('#cmd').val('modify');  
        }
    });
    
    this.$("#cntnrQuayRentSttusInqireDetailList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#cntnrQuayRentSttusInqireListTab").tabs("option", {active: 2});
        module.$('#gamCntnrQuayRentSttusInqireDetailForm :input').val('');

        module.makeFormValues('#gamCntnrQuayRentSttusInqireDetailForm', row);
        module._editData=module.getFormValues('#gamCntnrQuayRentSttusInqireDetailForm', row);
        module._editRow=module.$('#cntnrQuayRentSttusInqireDetailList').selectedRowIds()[0];
        
        if(row!=null) {
            module.$('#detailCmd').val('modify');
        }
    });
    
    this.$('#sDateSearchGbn').on('change', {module: this}, function(event) {
		if( $(this).val() == '' ) {
			event.data.module.$('#sDateSearchValue').val("");
		}
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

/**
 * 정의 된 버튼 클릭 시
 */
 GamCntnrQuayRentSttusInqireModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
        	//2014-4-22 추가
        	if( this.$('#sGrUsagePdFrom').val() == '' ) {
                alert("사용기간을 입력하세요.");
                return;
            }
        	if( this.$('#sGrUsagePdTo').val() == '' ) {
                alert("사용기간을 입력하세요.");
                return;
            }
			//
            var searchOpt=this.makeFormArgs('#gamCntnrQuayRentSttusInqireSearchForm');
			this.$("#cntnrQuayRentSttusInqireListTab").tabs("option", {active: 0});
            this.$('#cntnrQuayRentSttusInqireList').flexOptions({params:searchOpt}).flexReload();
            break;

        // 최초신청
        case 'addCntnrQuayRentSttusInqireFirst':
            this.$("#cntnrQuayRentSttusInqireListTab").tabs("option", {active: 1});  // 탭을 전환 한다.
            //this.$(":input").val('');
            this.$('#gamCntnrQuayRentSttusInqireForm :input').val("");
            this.$("#cmd").val('insert');

            break;

        // 연장신청
        case 'addCntnrQuayRentSttusInqireRenew':
            var rows = this.$('#cntnrQuayRentSttusInqireList').selectedRows();

            if(rows.length>=1) {
                //this.$('#rPrtAtCode').val(row[0]['prtAtCode']);

                this.doAction('<c:url value="/oper/cntnr/gamInsertCntnrQuayRentSttusInqireRenew.do" />', rows[0], function(module, result) {

                    if(result.resultCode=='0') {

                        var searchOpt=module.makeFormArgs('#gamCntnrQuayRentSttusInqireSearchForm');
                        module.$('#cntnrQuayRentSttusInqireList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);

                });
                //throw 0;

            } else {
                alert("연장신청할 업체를 선택하십시오.");
            }

            break;

        // 컨테이너부두임대현황 저장
        case 'btnSaveItem':
            var inputVO=this.makeFormArgs('#gamCntnrQuayRentSttusInqireForm');
            if(this.$("#cmd").val()=='insert') {

            	this.doAction('<c:url value="/oper/cntnr/gamInsertCntnrQuayRentSttusInqireFirst.do" />', inputVO, function(module, result) {

                    if(result.resultCode=='0') {
                    	var searchOpt=module.makeFormArgs('#gamCntnrQuayRentSttusInqireForm');
                        module.$('#cntnrQuayRentSttusInqireList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });
            }
            else {
                this.doAction('<c:url value="/oper/cntnr/gamUpdateCntnrQuayRentSttusInqire.do" />', inputVO, function(module, result) {
                	if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamCntnrQuayRentSttusInqireForm');
                        module.$('#cntnrQuayRentSttusInqireList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });
            }
            break;

        // 컨테이너부두임대현황 취소
        case 'btnCancelItem':
        	this.$('#gamCntnrQuayRentSttusInqireForm :input').val("");
            this.$("#cmd").val('insert');
            break;

        //컨테이너부두임대현황삭제
        case 'btnRemoveItem':
            if( this.$('#cmd').val() == 'modify' ) {
                this.$('#detailPrmisnYn').val( this.$('#prmisnYn').val() );

                var inputVO=this.makeFormArgs('#gamCntnrQuayRentSttusInqireForm');

                this.doAction('<c:url value="/oper/cntnr/gamDeleteCntnrQuayRentSttusInqire.do" />', inputVO, function(module, result) {

                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamCntnrQuayRentSttusInqireSearchForm');
                        module.$('#cntnrQuayRentSttusInqireList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });

                this.$("#cntnrQuayRentSttusInqireListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
                this.$('#gamCntnrQuayRentSttusInqireForm :input').val("");
                this.$("#cmd").val('insert');

            } else {
                alert("상세조회 후 삭제가 가능합니다.");
            }
            break;

        // 컨테이너부두임대현황상세 신규등록
        case 'btnInsertItemDetail':

        	if( this.$('#prtAtCode').val() == '' ) {
        		alert("컨테이너부두임대현황상세 조회후 등록이 가능합니다.");
        	} else {
        		this.$("#cntnrQuayRentSttusInqireListTab").tabs("option", {active: 2});  // 탭을 전환 한다.
                this.$('#gamCntnrQuayRentSttusInqireDetailForm :input').val("");

                this.$("#detailCmd").val('insert');
                this.$('#detailPrtAtCode').val( this.$('#prtAtCode').val() );
                this.$('#detailMngYear').val( this.$('#mngYear').val() );
                this.$('#detailMngNo').val( this.$('#mngNo').val() );
                this.$('#detailMngCnt').val( this.$('#mngCnt').val() );
        	}

            break;

        // 컨테이너부두임대현황상세 삭제
        case 'btnRemoveItemDetail':

            var rows = this.$('#cntnrQuayRentSttusInqireDetailList').selectedRows();

            if(rows.length>=1) {
                this.doAction('<c:url value="/oper/cntnr/gamDeleteCntnrQuayRentSttusInqireDetail.do" />', rows[0], function(module, result) {
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamCntnrQuayRentSttusInqireForm');
                        module.$('#cntnrQuayRentSttusInqireDetailList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });

                this.$('#gamCntnrQuayRentSttusInqireDetailForm :input').val("");
                this.$("#detailCmd").val('insert');

            } else {
                alert("삭제할 정보를 선택하십시오.");
            }

            break;

        // 컨테이너부두임대현황상세 저장
        case 'btnSaveItemDetail':

            var inputVO=this.makeFormArgs('#gamCntnrQuayRentSttusInqireDetailForm');
            if(this.$("#detailCmd").val()=='insert') {

                this.doAction('<c:url value="/oper/cntnr/gamInsertCntnrQuayRentSttusInqireDetail.do" />', inputVO, function(module, result) {

                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamCntnrQuayRentSttusInqireForm');
                        module.$('#cntnrQuayRentSttusInqireDetailList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });
            }
            else {
            	this.doAction('<c:url value="/oper/cntnr/gamUpdateCntnrQuayRentSttusInqireDetail.do" />', inputVO, function(module, result) {
                    if(result.resultCode=='0') {
                    	var searchOpt=module.makeFormArgs('#gamCntnrQuayRentSttusInqireForm');
                        module.$('#cntnrQuayRentSttusInqireDetailList').flexOptions({params:searchOpt}).flexReload();
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

        case 'popupEntrpsInfoInput': // 팝업을 호출한다.(컨테이너부두임대현황입력)
            var opts;

            this.doExecuteDialog('insertEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;

        case 'btnPrmisn': // 승낙 (허가)
            var rows = this.$('#cntnrQuayRentSttusInqireList').selectedRows();

            if(rows.length>=1) {
            	var opts = {
                    'prtAtCode': rows[0]['prtAtCode'],
                    'mngYear': rows[0]['mngYear'],
                    'mngNo': rows[0]['mngNo'],
            	    'mngCnt': rows[0]['mngCnt']
                };

                this.doExecuteDialog('insertCntnrQuayRentSttusInqirePrmisnPopup', '승낙', '<c:url value="/oper/cntnr/popup/showCntnrQuayRentSttusInqirePrmisn.do"/>', opts);

            } else {
                alert("목록에서 선택하십시오.");
            }

            break;

        case 'btnPrmisnCancel': // 승낙취소 (허가취소)
            var rows = this.$('#cntnrQuayRentSttusInqireList').selectedRows();

            if(rows.length>=1) {
                this.doAction('<c:url value="/oper/cntnr/gamUpdateCntnrQuayRentSttusInqirePrmisnCancel.do" />', rows[0], function(module, result) {
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamCntnrQuayRentSttusInqireForm');
                        module.$('#cntnrQuayRentSttusInqireList').flexOptions({params:searchOpt}).flexReload();
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
            
        case 'btnDownloadFile':
    		var selectRow = this.$('#cntnrQuayRentSttusInqireFileList').selectedRows();
    		if(selectRow.length > 0) {
    			var row=selectRow[0];
    			this.downloadFile(row["filenmPhysicl"], row["filenmLogic"]);
    		}
    		break;

    }
};

GamCntnrQuayRentSttusInqireModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamCntnrQuayRentSttusInqireModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamCntnrQuayRentSttusInqireSearchForm');
    //this.showAlert(searchOpt);
    this.$('#cntnrQuayRentSttusInqireList').flexOptions({params:searchOpt}).flexReload();
};

GamCntnrQuayRentSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        var row = this.$('#cntnrQuayRentSttusInqireList').selectedRows();
        if(row.length==0) {
            this.$('#cmd').val('insert');
        }
        else {
            this.$('#cmd').val('modify');
        }
        break;
    case 'tabs3':
    	var row = this.$('#cntnrQuayRentSttusInqireDetailList').selectedRows();
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
GamCntnrQuayRentSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
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
     case 'insertCntnrQuayRentSttusInqirePrmisnPopup':
    	 if (msg != 'cancel') {
    		 if( value == "0" ) {
    			 var searchOpt=this.makeFormArgs('#gamCntnrQuayRentSttusInqireSearchForm');
                 this.$('#cntnrQuayRentSttusInqireList').flexOptions({params:searchOpt}).flexReload();
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
var module_instance = new GamCntnrQuayRentSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamCntnrQuayRentSttusInqireSearchForm">
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
                            	<input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;
                            	<input id="sEntrpsNm" type="text" size="15" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <th>사용용도</th>
                            <td>
                                <input id="sUsagePrposCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM007 />
                            </td>
                            <td rowSpan="2"><button id="searchBtn" *class="submit" class="buttonSearch">조회</button></td>
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
        <div id="cntnrQuayRentSttusInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">컨테이너부두임대현황 목록</a></li>
                <li><a href="#tabs2" class="emdTab">컨테이너부두임대현황 내역</a></li>
                <li><a href="#tabs3" class="emdTab">컨테이너부두임대현황 상세내역</a></li>
                <li><a href="#tabs4" class="emdTab">첨부파일</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="cntnrQuayRentSttusInqireList" style="display:none" class="fillHeight"></table>

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
                                <!--
	                                <button id="addAssetRentFirst">최초신청</button>
	                                <button id="addAssetRentRenew">연장신청</button>
	                                <button id="btnRemoveItem">신청삭제</button>
	                                <button id="btnEApproval">결재요청</button>
	                                <button id="btnPrmisn">사용승낙</button>
	                                <button id="btnPrmisnCancel">승낙취소</button>
	                                <button id="btnShowMap">맵조회</button>
								-->
                                 <button id="addCntnrQuayRentSttusInqireRenew">연장신청</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">
                <!-- <div class="emdControlPanel"></div>-->
                    <form id="gamCntnrQuayRentSttusInqireForm">
                        <input type="hidden" id="cmd"/>
                        <!-- <input type="hidden" id="quayGroupCd"/> -->

                        <table class="searchPanel">
                            <tr>
								<th width="10%" height="18">항코드</th>
                                <td>
                                    <input id="prtAtCode" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM019 disabled/>
                                    <input type="text" size="4" id="prtAtCodeStr" disabled/>
                                </td>
								<th width="10%" height="18">담당부서</th>
                                <td>
                                    <input type="text" size="20" id="deptcdNm" disabled/>
                                </td>
								<th width="10%" height="18">관리번호</th>
                                <td>
                                    <input type="text" size="5" id="mngYear" disabled/>-
                                    <input type="text" size="5" id="mngNo" disabled/>-
                                    <input type="text" size="5" id="mngCnt" disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">최초신청일자</th>
                                <td><input type="text" size="20" id="frstReqstDt" disabled/></td>
								<th width="10%" height="18">신청일자</th>
                                <td><input type="text" size="20" id="reqstDt" disabled/></td>
								<th width="10%" height="18">신청업체</th>
                                <td>
                                    <input type="text" size="10" id="entrpscd" disabled/>
                                    <input type="text" size="40" id="entrpsNm" disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">승낙여부</th>
                                <td>
                                    <input type="text" size="20" id="prmisnYn" disabled/>
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
                                <td><input type="text" size="53" class="ygpaNumber" id="grRdcxptFee" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">납부방법</th>
                                <td>
                                    <input type="text" size="5" id="payMth" disabled/>
                                    <input type="text" size="12" id="payMthNm" disabled/>
                                </td>
								<th width="10%" height="18">고지방법</th>
                                <td>
                                    <input type="text" size="5" id="nticMth" disabled/>
                                    <input type="text" size="12" id="nticMthNm" disabled/>
                                </td>
								<th width="10%" height="18">분납이자율</th>
                                <td>
                                    <input type="text" size="53" id="payinstIntrrate" disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">코멘트</th>
                                <td colspan="5">
                                	<input type="text" size="134" id="cmt" maxlength="90" disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">비고</th>
                                <td colspan="5"><input type="text" size="134" id="rm" disabled/></td>
                            </tr>
                        </table>
                    </form>

                 <table class="searchPanel">
                    <tbody>
                    <tr>
                        <th>컨테이너부두임대 상세목록</th>
                    </tr>
                    </tbody>
                 </table>

                 <table id="cntnrQuayRentSttusInqireDetailList" style="display:none"></table>

                 <table style="width:100%">
                 	<!-- 
                    <tr>
                        <td style="text-align:right" colspan="3"><button id="btnInsertItemDetail">컨테이너부두임대상세추가</button><button id="btnRemoveItemDetail">컨테이너부두임대상세삭제</button></td>
                    </tr>
                     -->
                    <!--
                    <tr>
                        <td><button id="xxxx">GIS 등록</button><button id="xxxx">위치조회</button></td>
                        <td width="100"></td>
                        <td style="text-align:right">
                        <button id="xxxx">결재요청</button><button id="xxxx">사용승낙</button>
                            <button id="xxxx">승낙취소</button><button id="btnRemoveItem">신청삭제</button></button><button id="btnSaveItem">신청저장</button>
                            <button id="btnCancelItem">취소</button>
                        </td>
                    </tr>
                    -->
                 </table>
            </div>

            <div id="tabs3" class="emdTabPage" style="overflow: scroll;">

                <!-- <div class="emdControlPanel"><button id="btnSaveItemDetail">저장</button></div> -->
                    <form id="gamCntnrQuayRentSttusInqireDetailForm">
                        <input type="hidden" id="detailCmd"/>
                        <input type="hidden" id="detailPrtAtCode"/>
                        <input type="hidden" id="detailPrmisnYn"/>
                        <table class="searchPanel">
                            <tr>
								<th width="10%" height="18">자산사용순번</th>
                                <td><input type="text" size="20" id="assetsUsageSeq" disabled/></td>
								<th width="10%" height="18">항코드</th>
                                <td>
                                	<input type="text" size="5" id="dtlPrtAtCode" disabled/>
	                                <input type="text" size="13" id="dtlPrtAtCodeNm" disabled/>
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
                                	<input type="text" size="5" id="gisAssetsPrtAtCode" disabled/>-
                                	<input type="text" size="4" id="gisAssetsCd" disabled/>-
                                	<input type="text" size="4" id="gisAssetsSubCd" disabled/>
                                    <input type="hidden" id="assetsCdStr"/>
                                </td>
								<th width="10%" height="18">자산면적</th>
                                <td><input type="text" size="21" class="ygpaNumber" id="gisAssetsAr" disabled/></td>
								<th width="10%" height="18">자산명</th>
                                <td><input type="text" size="52" id="gisAssetsNm" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">지번</th>
                                <td>
                                	<input type="text" size="8" id="gisAssetsLnm" disabled/>-
                                	<input type="text" size="8" id="gisAssetsLnmSub" disabled/>
                                </td>
								<th width="10%" height="18">소재지</th>
                                <td colspan="3"><input type="text" size="94" id="gisAssetsLocplc" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">실제임대면적</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="gisAssetsRealRentAr" disabled/></td>
								<th width="10%" height="18">사용면적</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="usageAr" disabled/></td>
								<th width="10%" height="18">사용기간</th>
                                <td>
                                	<input type="text" size="24" id="usagePdFrom" disabled/>~
                                	<input type="text" size="24" id="usagePdTo" disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">적용요율</th>
                                <td>
                                    <input type="text" size="5" id="applcTariff" disabled/>
                                    <input type="text" size="12" class="ygpaNumber" id="applcTariffNm" disabled/>
                                </td>
								<th width="10%" height="18">적용방법</th>
                                <td>
                                    <input type="text" size="5" id="applcMth" disabled/>
                                    <input type="text" size="13" id="applcMthNm" disabled/>
                                </td>
								<th width="10%" height="18">공시지가목록</th>
                                <td>
                                	<input type="text" size="52" id="empty" disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">공시지가</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="olnlp" disabled/></td>
								<th width="10%" height="18">면제구분</th>
                                <td>
                                    <input type="text" size="5" id="exemptSe" disabled/>
                                    <input type="text" size="13" id="exemptSeNm" disabled/>
                                </td>
								<th width="10%" height="18">면제기간</th>
                                <td>
                                	<input type="text" size="24" id="exemptPdFrom" disabled/>~
                                	<input type="text" size="24" id="exemptPdTo" disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">면제사유코드</th>
                                <td colspan="3">
                                    <input type="text" size="5" id="exemptRsnCd" disabled/>
                                    <input type="text" size="53" id="exemptRsnCdNm" disabled/>
                                </td>
								<th width="10%" height="18">면제사유</th>
                                <td><input type="text" size="52" id="exemptRsn" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">사용료</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="fee" disabled/></td>
								<th width="10%" height="18">감면사용료</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="rdcxptFee" disabled/></td>
								<th width="10%" height="18">부두코드</th>
                                <td>
                                	<input type="text" id="quayCd" size="15" disabled/>
                                	<input type="text" id="quayCdNm" size="34" disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">산출내역</th>
                                <td colspan="5"><input type="text" size="134" id="computDtls" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">사용목적</th>
                                <td colspan="5"><input type="text" size="134" id="usagePurps" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">사용내역</th>
                                <td colspan="5"><input type="text" size="134" id="usageDtls" disabled/></td>
                            </tr>
                        </table>
                    </form>

                <!--
                <div style="vertical-align: bottom; text-align: right;">
                    <input type="reset" value="취소" class="input_1"> <input
                        type="submit" value="저장" class="input_1">
                </div>
                <table style="width:100%">
                    <tr>
                        <td><button id="xxxx">GIS 등록</button><button id="xxxx">위치조회</button></td>
                        <td width="100"></td>
                        <td style="text-align:right">
                        <button id="xxxx">취소</button><button id="xxxx">컨테이너부두임대상세적용</button>
                        &nbsp;
                        </td>
                    </tr>
                </table>
                -->

            </div>

            <div id="tabs4" class="emdTabPage" style="overflow: scroll;">
                
                <table id="cntnrQuayRentSttusInqireFileList" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel"><button id="btnDownloadFile">다운로드</button></div>
                <form id="gamCntnrQuayRentSttusInqireFileForm">
                    <input type="hidden" id="photoPrtAtCode" data-column-id="prtAtCode"/>
                    <input type="hidden" id="photoMngYear" data-column-id="mngYear"/>
                    <input type="hidden" id="photoMngNo" data-column-id="mngNo"/>
                    <input type="hidden" id="photoMngCnt" data-column-id="mngCnt"/>
                    <input type="hidden" id="photoSeq" data-column-id="photoSeq"/>
                
                    <table class="searchPanel">
                        <tr>
							<th width="10%" height="18">사진제목</th>
                            <td>
                                <input id="photoSj" type="text" size="83" disabled/>
                            </td>
							<th width="10%" height="18">촬영일자</th>
                            <td>
                                <input id="shotDt" type="text" size="20" disabled>
                            </td>
                        </tr>
                        <tr>
							<th width="10%" height="18">사진파일명</th>
                            <td colspan="3">
                                <input id="filenmLogic" type="text" size="135" disabled/>
                            </td>
                        </tr>
                        <tr>
							<th width="10%" height="18">사진설명</th>
                            <td colspan="3">
                                <input id="photoDesc" type="text" size="135" disabled/>
                            </td>
                        </tr>
                    </table>
                </form>
                <div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>

            </div>
        </div>
    </div>
</div>