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
<validator:javascript formName="gamMarineCenterRent" method="validateGamMarineCenterRent" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamMarineCenterRentDetail" method="validateGamMarineCenterRentDetail" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamMarineCenterRentFile" method="validateGamMarineCenterRentFile" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<!-- 
<validator:javascript formName="gamAssetRent" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetRentDetail" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetRentFile" staticJavascript="false" xhtml="true" cdata="false" />
 -->
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamMarineCenterRentMngtModule() {}

GamMarineCenterRentMngtModule.prototype = new EmdModule(1100, 650);

// 페이지가 호출 되었을때 호출 되는 함수
GamMarineCenterRentMngtModule.prototype.loadComplete = function() {

    // 자산임대 테이블 설정
    this.$("#marineCenterRentMngtList").flexigrid({ 
        module: this,
        url: '<c:url value="/oper/center/gamSelectMarineCenterRentList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'항이름', name:'prtAtCodeNm',width:60, sortable:false,align:'center'},
                    {display:'관리번호', name:'rentMngNo',width:100, sortable:false,align:'center'},
                    {display:'업체명', name:'entrpsNm',width:170, sortable:false,align:'center'},
                    {display:'업체코드', name:'entrpscd',width:90, sortable:false,align:'center'},
                    {display:'총사용기간 시작', name:'grUsagePdFrom',width:100, sortable:false,align:'center'},
                    {display:'총사용기간 종료', name:'grUsagePdTo',width:100, sortable:false,align:'center'},
                    {display:'신청구분', name:'reqstSeCdNm',width:60, sortable:false,align:'center'},
                    {display:'허가여부', name:'prmisnYn',width:60, sortable:false,align:'center'},
                    {display:'결재상태', name:'sanctnSttusNm',width:60, sortable:false,align:'center'},
                    {display:'총사용료', name:'grFee',width:120, sortable:false,align:'center', displayFormat: 'number'},
                    {display:'총면적', name:'grAr',width:120, sortable:false,align:'center', displayFormat: 'number'},
                    {display:'최초 신청일', name:'frstReqstDt',width:70, sortable:false,align:'center'},
                    {display:'최초 허가일자', name:'frstPrmisnDt',width:90, sortable:false,align:'center'},
                    //{display:'날짜', name:'dt',width:60, sortable:false,align:'center'},
                    {display:'허가일자', name:'prmisnDt',width:70, sortable:false,align:'center'}

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

            return data;
        }
    });

    // 자산임대상세 테이블 설정
    this.$("#marineCenterRentDetailList").flexigrid({
        module: this,
        url: '<c:url value="/oper/center/gamSelectMarineCenterRentDetailList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'순번', name:'assetsUsageSeq',width:50, sortable:false,align:'center'},
                    //{display:'항구분', name:'prtAtCodeNm',width:60, sortable:false,align:'center'},
                    //{display:'항코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
                    {display:'항구분', name:'gisAssetsPrtAtCodeNm',width:60, sortable:false,align:'center'},
                    {display:'항코드', name:'gisAssetsPrtAtCode',width:60, sortable:false,align:'center'},
                    {display:'자산코드', name:'assetsCdStr',width:60, sortable:false,align:'center'},
                    {display:'자산명', name:'gisAssetsNm',width:140, sortable:false,align:'center'},
                    {display:'사용시작', name:'usagePdFrom',width:70, sortable:false,align:'center'},
                    {display:'사용종료', name:'usagePdTo',width:70, sortable:false,align:'center'},
                    {display:'사용료', name:'fee',width:120, sortable:false,align:'center', displayFormat: 'number'},
                    {display:'사용면적', name:'usageAr',width:120, sortable:false,align:'center', displayFormat: 'number'},
                    {display:'적용요율', name:'applcTariffNm',width:120, sortable:false,align:'center'},
                    {display:'면제구분', name:'exemptSeNm',width:100, sortable:false,align:'center'}

                    /*
                    {display:'적용요율', name:'applcTariff',width:100, sortable:false,align:'center'},
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
    this.$("#marineCenterRentFileList").flexigrid({
        module: this,
        url: '<c:url value="/oper/center/gamSelectMarineCenterRentFileList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'순번', name:'photoSeq', width:80, sortable:true, align:'center'},
                    {display:'사진제목', name:'photoSj', width:300, sortable:true, align:'center'},
                    {display:'파일명', name:'filenmLogic', width:200, sortable:true, align:'center'},
                    {display:'촬영일시', name:'shotDt', width:120, sortable:true, align:'center'},
                    {display:'사진설명', name:'photoDesc', width:280, sortable:true, align:'center'}
                    
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
        height: 'auto'
    });

    this.$("#marineCenterRentMngtList").on('onItemSelected', function(event, module, row, grid, param) {
        module.$('#cmd').val('modify');

        module.$('#gamMarineCenterRentForm :input').val('');

        module.makeFormValues('#gamMarineCenterRentForm', row);
        module._editData=module.getFormValues('#gamMarineCenterRentForm', row);
        module._editRow=module.$('#marineCenterRentMngtList').selectedRowIds()[0];

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
        
        var searchOpt=module.makeFormArgs('#gamMarineCenterRentForm');
        module.$('#marineCenterRentDetailList').flexOptions({params:searchOpt}).flexReload();
        module.$('#marineCenterRentFileList').flexOptions({params:searchOpt}).flexReload();
        
        //this._deleteDataFileList=[]; //삭제파일목록 초기화
    });

    this.$("#marineCenterRentDetailList").on('onItemSelected', function(event, module, row, grid, param) {
        //module.$('#btnApplyGisAssetsCode').prop('disabled', false);
        module.$('#gamMarineCenterRentDetailForm :input').val('');
        
        module.makeFormValues('#gamMarineCenterRentDetailForm', row);
        module._editData=module.getFormValues('#gamMarineCenterRentDetailForm', row);
        module._editRow=module.$('#marineCenterRentDetailList').selectedRowIds()[0];
    });
    
    this.$("#marineCenterRentFileList").on('onItemSelected', function(event, module, row, grid, param) {
        module.makeFormValues('#gamMarineCenterRentFileForm', row);
        module._editDataFile=module.getFormValues('#gamMarineCenterRentFileForm', row);
        module._editRowFile=module.$('#marineCenterRentFileList').selectedRowIds()[0];
        
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
    
    this.$("#marineCenterRentMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#assetRentListTab").tabs("option", {active: 1});
        module.$('#cmd').val('modify');
        module.$('#gamMarineCenterRentForm :input').val('');
        
        module.makeFormValues('#gamMarineCenterRentForm', row);
        module._editData=module.getFormValues('#gamMarineCenterRentForm', row);
        module._editRow=module.$('#marineCenterRentMngtList').selectedRowIds()[0];

        if(row!=null) {
            module.$('#cmd').val('modify');
        }
        
        //this._deleteDataFileList=[]; //삭제파일목록 초기화
    });

    this.$("#marineCenterRentDetailList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#assetRentListTab").tabs("option", {active: 2});
        module.$('#gamMarineCenterRentDetailForm :input').val('');
        module.makeFormValues('#gamMarineCenterRentDetailForm', row);
        module._editData=module.getFormValues('#gamMarineCenterRentDetailForm', row);
        module._editRow=module.$('#marineCenterRentDetailList').selectedRowIds()[0];

        if(row!=null) {
            module.$('#detailCmd').val('modify');
        }
    });
    
    // 컴포넌트이 이벤트를 추가한다. (기존 코드 데이터에 선택 값이 onchange 안되는 점을 수정 함)
    this.$('#prtAtCode').on('change', {module: this}, function(event) {
        event.data.module.$('#prtAtCodeStr').val($(this).val());
        //alert($(this).getSelectedCodeLabel() + '이(가) 선택되었습니다.');
    });
 
    this.$('#olnlpList').on('change', {module: this}, function(event) {
        event.data.module.$('#olnlp').val($(this).val());
        //alert($(this).getSelectedCodeLabel() + '이(가) 선택되었습니다.');
    });
 
    this.$('#applcTariff').on('change', {module: this}, function(event) {
        if( $(this).val() == '1' ) {
            event.data.module.$('#applcTariffStr').val("0.005");
        } else if( $(this).val() == '2' ) {
            event.data.module.$('#applcTariffStr').val("0.0025");
        } else if( $(this).val() == '3' ) {
            event.data.module.$('#applcTariffStr').val("0.001");
        } else {
            event.data.module.$('#applcTariffStr').val("");
        }
        
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
    
    this.$('.calcInput').on('change', {module: this}, function(event) {
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
        }
    });
    
    
};


GamMarineCenterRentMngtModule.prototype.onCalc = function() {
    
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
        
        olnlp = Number(this.$('#olnlp').val());
        usageAr = Number(this.$('#usageAr').val());
        //applcTariff = Number(this.$('#applcTariff').val());
        applcTariffStr = Number(this.$('#applcTariff').val());
        //rdcxptFee = Number(this.$('#rdcxptFee').val());
        usagePdFrom = this.$('#usagePdFrom').val();
        usagePdTo = this.$('#usagePdTo').val();
        exemptPdFrom = this.$('#exemptPdFrom').val();
        exemptPdTo = this.$('#exemptPdTo').val();
        exemptSe = this.$('#exemptSe').val();
        
        
        if( applcTariffStr == '0' ) {
            this.$('#computDtls').val('');
            return;
        } else if( applcTariffStr == '1' ) {
            applcTariff = 0.005;
            this.$('#computDtls').val('사용료 = 공시지가*((사용일수)/365)*사용면적)*적용요율 - 감면사용료');
        } else if( applcTariffStr == '2' ) {
            applcTariff = 0.0025;
            this.$('#computDtls').val('사용료 = 공시지가*((사용일수)/365)*사용면적)*적용요율 - 감면사용료');
        } else if( applcTariffStr == '3' ) {
            applcTariff = 0.001;
            this.$('#computDtls').val('사용료 = 공시지가*((사용일수)/365)*사용면적)*적용요율 - 감면사용료');
        }
        
        if( exemptSe == '1' ) {        // 일부면제
              if( this.$('#exemptPdFrom').val() == '' ) {
                  alert("일부 면제의 경우 면제기간(시작)을 선택하십시오.");
                  return;
              }
              if( this.$('#exemptPdTo').val() == '' ) {
                  alert("일부 면제의 경우 면제기간(종료)을 선택하십시오.");
                  return;
              }
        }
        
        if( exemptSe == '0' ) {               // 면제없음.
            rdcxptFee = 0;
        } else if( exemptSe == '1' ) {   // 일부면제
            
            /* 면제 일수 계산 */
            var year3 = exemptPdFrom.substring(0,4);
            var month3 = exemptPdFrom.substring(5,7);
            var day3 = exemptPdFrom.substring(8,10);
            
            var year4 = exemptPdTo.substring(0,4);
            var month4 = exemptPdTo.substring(5,7);
            var day4 = exemptPdTo.substring(8,10);
            
            var e_st_day = new Date(year3, (month3-1), day3, 0, 0, 0);
            var e_mt_day = new Date(year4, (month4-1), day4, 0, 0, 0);
            
            var e_take1 = e_mt_day.getTime();
            var e_take2 = e_st_day.getTime();
            
            var e_how_day = Math.ceil((e_take1-e_take2)/24/60/60/1000); // 두 날짜 사이의 일수
            
            //exemptCnt = Number(e_how_day) + 1;
            exemptCnt = Number(e_how_day);
        
            rdcxptFee = olnlp * (exemptCnt / 365) * usageAr * applcTariff;
            
            //alert("감면사용료 => " + rdcxptFee);
            
        } else if( exemptSe == '2' ) {        // 전체면제
            //this.$('#rdcxptFee').val('0');
            rdcxptFee = 0;
        }
        
        /* 날짜계산 */
        var year = usagePdFrom.substring(0,4);
        var month = usagePdFrom.substring(5,7);
        var day = usagePdFrom.substring(8,10);
        
        var year2 = usagePdTo.substring(0,4);
        var month2 = usagePdTo.substring(5,7);
        var day2 = usagePdTo.substring(8,10);

        var st_day = new Date(year, (month-1), day, 0, 0, 0);
        var mt_day = new Date(year2, (month2-1), day2, 0, 0, 0);
        
        var take1 = mt_day.getTime();
        var take2 = st_day.getTime();
        
        var how_day = Math.ceil((take1-take2)/24/60/60/1000); // 두 날짜 사이의 일수
        
        //dayUseCnt = parseInt(how_day) + 1;
        dayUseCnt = parseInt(how_day);
        
        var cur_day = new Date(year, (month-1), day, 0, 0, 0); // 입력받은 첫번째 날짜
        //cur_day.setDate( cur_day.getDate() - 1 );
        cur_day.setYear( cur_day.getFullYear() + 1 );
        
        //(사용료 = 공시지가*((사용일수)/365)*사용면적)*적용요율 ? 감면사용료 )
        //if( exemptSe == '2' ) {     // 전체면제 일 경우 사용료는 0
            //calFee = 0;
        //} else {
            calFee = olnlp*((dayUseCnt/365)*usageAr)*applcTariff - rdcxptFee;
        //}
        
        //alert("계산후 => " + calFee);
        var calFeeStr = "" + calFee;
        
        if( calFeeStr.indexOf('.') >= 0 ) {
            calFeeStr = calFeeStr.substring(0,calFeeStr.indexOf('.'));
        } else {
            calFeeStr = calFeeStr;
        }
        
        this.$('#fee').val(calFeeStr);
        
        if( exemptSe == '0' ) {
            this.$('#rdcxptFee').val('0');
        } else if( exemptSe == '1' ) {
            var rdcxptFeeStr = "" + rdcxptFee;

            if( rdcxptFeeStr.indexOf('.') >= 0 ) {
                rdcxptFeeStr = rdcxptFeeStr.substring(0,rdcxptFeeStr.indexOf('.'));
            } else {
                rdcxptFeeStr = rdcxptFeeStr;
            }
            
            this.$('#rdcxptFee').val(rdcxptFeeStr);
        } else if( exemptSe == '2' ) {
            this.$('#rdcxptFee').val(calFeeStr);
            this.$('#fee').val('0');
        }
        
    } else {
        this.$('#fee').val(''); 
        this.$('#rdcxptFee').val('');
    }
    
};


/**
 * 정의 된 버튼 클릭 시
 */
 GamMarineCenterRentMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
            this.$("#assetRentListTab").tabs("option", {active: 0});

            var searchOpt=this.makeFormArgs('#gamMarineCenterRentMngtSearchForm');
            this.$('#marineCenterRentMngtList').flexOptions({params:searchOpt}).flexReload();

            break;

        // 최초신청
        case 'addMarineCenterRentFirst':
            this.$("#assetRentListTab").tabs("option", {active: 1});  // 탭을 전환 한다.
            this.$('#gamMarineCenterRentForm').find(':input').val('');
            this.$('#gamMarineCenterRentDetailForm').find(':input').val('');
            this.$('#gamMarineCenterRentFileForm').find(':input').val('');
            
            //this.$("#marineCenterRentDetailList").flexRemove();
            this.$("#marineCenterRentDetailList").flexAddData({resultList:[]}); //그리드 초기화
            this.$("#marineCenterRentFileList").flexAddData({resultList:[]}); //그리드 초기화
            this.$("#cmd").val('insert');

            this.$('#deptcd').val(this.$('#loginOrgnztId').val());

            this.$('#frstReqstDt').val(this.$('#currentDateStr').val());
            this.$('#reqstDt').val(this.$('#currentDateStr').val());

            //this.$('#prtAtCode').val("640");
            
            break;

        // 연장신청
        case 'addMarineCenterRentRenew':
            var rows = this.$('#marineCenterRentMngtList').selectedRows();

            if(rows.length>=1) {
                //this.$('#rPrtAtCode').val(row[0]['prtAtCode']);

                if( confirm("연장신청을 하시겠습니까?") ) {
                    this.doAction('<c:url value="/oper/center/gamInsertMarineCenterRentRenew.do" />', rows[0], function(module, result) {

                        if(result.resultCode=='0') {
                            var searchOpt=module.makeFormArgs('#gamMarineCenterRentMngtSearchForm');
                            module.$('#marineCenterRentMngtList').flexOptions({params:searchOpt}).flexReload();
                        }

                        alert(result.resultMsg);
                    });
                //throw 0;
                }
            } else {
                alert("목록에서 연장신청할 업체를 선택하십시오.");
            }

            break;

        // 신청저장
        case 'btnSaveItem':
            
            if(!validateGamMarineCenterRent(this.$('#gamMarineCenterRentForm')[0])) {
                return;
            }
            
            if( this.$('#prtAtCode').val() == '' ) {
                alert("항구분을 선택하십시오.");
                return;
            }
            
            if( this.$('#prtAtCode').val() != '640' ) {
                alert("항구분은 마린센터를 선택하십시오.");
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

                //this._editData=this.getFormValues('#gamMarineCenterRentDetailForm', this._editData);

                inputVO[inputVO.length]={name: 'updateList', value :JSON.stringify(this.$('#marineCenterRentDetailList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

                inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#marineCenterRentDetailList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

                inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataList) };
                
                if(this._deleteDataFileList == undefined ) {
                    this._deleteDataFileList=[];
                }
                
                inputVO[inputVO.length]={name: 'updateFileList', value :JSON.stringify(this.$('#marineCenterRentFileList').selectFilterData([{col: '_updtId', filter: 'U'}])) };

                inputVO[inputVO.length]={name: 'insertFileList', value: JSON.stringify(this.$('#marineCenterRentFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };

                inputVO[inputVO.length]={name: 'deleteFileList', value: JSON.stringify(this._deleteDataFileList) };
                
                //var otherForm=this.getFormValues('#gamMarineCenterRentForm', {});  // 폼만 있을 경우

                this._editData2=this.getFormValues('#gamMarineCenterRentForm', {_updtId:'I'});
                inputVO[inputVO.length]={name: 'form', value: JSON.stringify(this._editData2) };    // 폼의 데이터를 컨트롤러에 보낸다.

                //console.log(inputVO);
                // 데이터를 저장 하고 난 뒤 리스트를 다시 로딩 한다.

                this.doAction('<c:url value="/oper/center/gamSaveMarineCenterRent.do" />', inputVO, function(module, result) {
                    if(result.resultCode == 0){
                        var searchOpt=module.makeFormArgs('#gamMarineCenterRentForm');
                        module.$('#marineCenterRentMngtList').flexOptions({params:searchOpt}).flexReload();
                        //module.$('#marineCenterRentDetailList').flexReload();
                        module.$('#marineCenterRentDetailList').flexOptions({params:searchOpt}).flexReload();
                        module.$('#marineCenterRentFileList').flexOptions({params:searchOpt}).flexReload();
                    }
                    alert(result.resultMsg);
                });


                this.$("#assetRentListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
            }

            break;

        //신청삭제
        case 'btnRemoveItem':
            var rows = this.$('#marineCenterRentMngtList').selectedRows();

            if(rows.length == 0) {
                alert("자산임대목록에서 신청삭제할 행을 선택하십시오.");
            } else {
                if( confirm("신청삭제를 하시겠습니까?") ) {
                    if( rows[0]['prmisnYn'] == null || rows[0]['prmisnYn'] == '' ) {
                        this.$('#detailPrmisnYn').val('N');
                    }

                    var inputVO=this.makeFormArgs('#gamMarineCenterRentForm');

                    this.doAction('<c:url value="/oper/center/gamDeleteMarineCenterRent.do" />', inputVO, function(module, result) {

                        if(result.resultCode=='0') {
                            var searchOpt=module.makeFormArgs('#gamMarineCenterRentMngtSearchForm');
                            module.$('#marineCenterRentMngtList').flexOptions({params:searchOpt}).flexReload();
                        }

                        alert(result.resultMsg);
                    });

                    this.$("#assetRentListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
                    this.$('#gamMarineCenterRentForm :input').val("");
                    this.$("#cmd").val('insert');
                }
            }

            break;

        //코멘트저장
        case 'btnSaveComment':
            var inputVO=this.makeFormArgs('#gamMarineCenterRentForm');

            this.doAction('<c:url value="/oper/center/gamUpdateMarineCenterRentComment.do" />', inputVO, function(module, result) {
                if(result.resultCode=='0') {
                    var searchOpt=module.makeFormArgs('#gamMarineCenterRentMngtSearchForm');
                    module.$('#marineCenterRentMngtList').flexOptions({params:searchOpt}).flexReload();
                }

                alert(result.resultMsg);
            });

            break;

        //임대상세추가
        case 'btnInsertItemDetail':
            this.$("#assetRentListTab").tabs("option", {active: 2});  // 탭을 전환 한다.
                this.$('#gamMarineCenterRentDetailForm').find(':input').val('');

                this.$("#detailCmd").val('insert');
                this.$('#detailPrtAtCode').val( this.$('#prtAtCode').val() );
                //this.$('#detailPrtAtCodeNm').val( this.$('#prtAtCodeNm').val() );
                this.$('#detailMngYear').val( this.$('#mngYear').val() );
                this.$('#detailMngNo').val( this.$('#mngNo').val() );
                this.$('#detailMngCnt').val( this.$('#mngCnt').val() );

                this._editData=this.getFormValues('#gamMarineCenterRentDetailForm', {_updtId:'I'});
                this._editRow=this.$('#marineCenterRentDetailList').flexGetData().length;

            break;

        // 자산임대상세 삭제 (Grid상에서만 삭제됨)
        case 'btnRemoveItemDetail':
            var rows = this.$('#marineCenterRentDetailList').selectedRows();

            if(rows.length == 0) {
                alert("자산임대상세목록에서 삭제할 행을 선택하십시오.");
            } else {
                if(this.$('#marineCenterRentDetailList').selectedRowIds().length>0) {
                    for(var i=this.$('#marineCenterRentDetailList').selectedRowIds().length-1; i>=0; i--) {
                        var row=this.$('#marineCenterRentDetailList').flexGetRow(this.$('#marineCenterRentDetailList').selectedRowIds()[i]);

                        if(row._updtId==undefined || row._updtId!='I') {
                            this._deleteDataList[this._deleteDataList.length]=row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
                        }
                        this.$('#marineCenterRentDetailList').flexRemoveRow(this.$('#marineCenterRentDetailList').selectedRowIds()[i]);
                    }
                    
                    /* 총사용료, 총면적 계산 시작 */
                    var fee = 0;
                    var rdcxptFee = 0;
                    var usageAr = 0;
                    var usagePdFrom = 0;
                    var usagePdTo = 0;
                    var minUsagePdFrom = 0;
                    var maxUsagePdTo = 0;
                    
                    for( var i = 0 ; i < this.$('#marineCenterRentDetailList').flexGetData().length ; i++ ) {
                        var row = this.$('#marineCenterRentDetailList').flexGetRow(i);
                        
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
                    
                    if( this.$('#marineCenterRentDetailList').flexGetData().length == 0 ) {
                    	this.$('#grFee').val( "" ); //총사용료
                        this.$('#grRdcxptFee').val( "" ); //총감면사용료
                        this.$('#grAr').val( "" ); //총사용면적
                    	this.$('#grUsagePdFrom').val( "" ); //총사용기간FROM
                    	this.$('#grUsagePdTo').val( "" ); //총사용기간FROM
                    }
                    
                    /* 총사용료, 총면적 계산 종료 */
                    
                }
            }

            this.$('#gamMarineCenterRentDetailForm').find(':input').val('');
            this.$("#detailCmd").val('insert');

            break;

        // 자산임대상세 저장
        /*
        case 'btnSaveItemDetail':

            var inputVO=this.makeFormArgs('#gamMarineCenterRentDetailForm');


            if(this.$("#detailCmd").val()=='insert') {

                this.doAction('<c:url value="/oper/center/gamInsertMarineCenterRentDetail.do" />', {aaa : "rrrrrrrrrrrrrr"}, function(module, result) {

                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamMarineCenterRentForm');
                        module.$('#marineCenterRentDetailList').flexOptions({params:searchOpt}).flexReload();
                    }

                    alert(result.resultMsg);
                });
            }
            else {
                this.doAction('<c:url value="/oper/center/gamUpdateMarineCenterRentDetail.do" />', inputVO, function(module, result) {
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamMarineCenterRentForm');
                        module.$('#marineCenterRentDetailList').flexOptions({params:searchOpt}).flexReload();
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
            var rows = this.$('#marineCenterRentMngtList').selectedRows();
            var row = this.$('#marineCenterRentMngtList').selectedRows()[0];

            if(rows.length>=1) {
                if( row['prmisnYn'] == 'Y' ) {
                    alert("이미 사용승낙된 상태 입니다.");
                    return;
                }
                
                if( row['sanctnSttus'] != '1' ) {
                    alert("결재완료 상태가 아닙니다.");
                    return;
                }
                
                var opts = {
                    'prtAtCode': rows[0]['prtAtCode'],
                    'mngYear': rows[0]['mngYear'],
                    'mngNo': rows[0]['mngNo'],
                    'mngCnt': rows[0]['mngCnt']
                };

                this.doExecuteDialog('insertMarineCenterRentPrmisnPopup', '승낙', '<c:url value="/oper/center/popup/showMarineCenterRentPrmisn.do"/>', opts);

            } else {
                alert("목록에서 선택하십시오.");
            }

            break;
        
        /*
        case 'btnPrmisn': // 사용승낙
            var rows = this.$('#marineCenterRentMngtList').selectedRows();
            var row = this.$('#marineCenterRentMngtList').selectedRows()[0];
        
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
                    this.doAction('<c:url value="/oper/center/gamUpdateMarineCenterRentPrmisn.do" />', rows[0], function(module, result) {
                        if(result.resultCode=='0') {
                            var searchOpt=module.makeFormArgs('#gamMarineCenterRentForm');
                            module.$('#marineCenterRentMngtList').flexOptions({params:searchOpt}).flexReload();
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
            var rows = this.$('#marineCenterRentMngtList').selectedRows();
            var row = this.$('#marineCenterRentMngtList').selectedRows()[0];
            
            if( row['prmisnYn'] != 'Y' ) {
                alert("승낙된 상태가 아닙니다.");
                return;
            }
            
            if(rows.length>=1) {
                if( confirm("승낙취소를 하시겠습니까?") ) {
                    this.doAction('<c:url value="/oper/center/gamUpdateMarineCenterRentPrmisnCancel.do" />', rows[0], function(module, result) {
                        if(result.resultCode=='0') {
                            var searchOpt=module.makeFormArgs('#gamMarineCenterRentForm');
                            module.$('#marineCenterRentMngtList').flexOptions({params:searchOpt}).flexReload();
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
			
			if(!validateGamMarineCenterRentDetail(this.$('#gamMarineCenterRentDetailForm')[0])) {
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
            this._editData=this.getFormValues('#gamMarineCenterRentDetailForm', this._editData);
            //this._editData=this.getFormValues('#gamMarineCenterRentDetailForm', this._editData);
            
            if(this._editRow!=null) {  // 이전에 _updtId 로 선택 한 것을 _editRow 로 변경 2014-03-14.001
                
                if(this._editData._updtId!='I') this._editData._updtId='U';   // 삽입된 데이터가 아니면 업데이트 플래그를 추가한다.
                this.$('#marineCenterRentDetailList').flexUpdateRow(this._editRow, this._editData);
                this._editRow=null;    // 편집 저장 하였으므로 로우 편집을 종료 한다.
            }
            else {
                this.$('#marineCenterRentDetailList').flexAddRow(this._editData);
            }

            this.$('#gamMarineCenterRentDetailForm').find(':input').val('');
            this._editData=null;       // 적용 이후 데이터 추가나 삭제 가 되지 않도록 편집 데이터를 제거 함/ 2014-03-11 추가


			/* 총사용료, 총면적 계산 시작 */
            var fee = 0;
            var rdcxptFee = 0;
            var usageAr = 0;
            var usagePdFrom = 0;
            var usagePdTo = 0;
            var minUsagePdFrom = 0;
            var maxUsagePdTo = 0;
            
            for( var i = 0 ; i < this.$('#marineCenterRentDetailList').flexGetData().length ; i++ ) {
                var row = this.$('#marineCenterRentDetailList').flexGetRow(i);
                
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
            
            if( this.$('#marineCenterRentDetailList').flexGetData().length == 0 ) {
                this.$('#grFee').val( "" ); //총사용료
                this.$('#grRdcxptFee').val( "" ); //총감면사용료
                this.$('#grAr').val( "" ); //총사용면적
                this.$('#grUsagePdFrom').val( "" ); //총사용기간FROM
                this.$('#grUsagePdTo').val( "" ); //총사용기간FROM
            }
            
            /* 총사용료, 총면적 계산 종료 */

            this.$("#assetRentListTab").tabs("option", {active: 1});  // 탭을 전환 한다.

            break;

        case 'btnUploadFile':
            // 사진을 업로드하고 업로드한 사진 목록을 result에 어레이로 리턴한다.
            this.uploadFile('uploadPhoto', function(module, result) {
//              var userid=EMD.util.getLoginUserVO().userNm; 임시
                var userid='admin';
                $.each(result, function(){
                    //module.$('#marineCenterRentFileList').flexAddRow({photoSj: '', filenmLogical: this.logicalFileNm, filenmPhyicl: this.physcalFileNm, regUsr: userid, registDt:  EMD.util.getTimeStamp()}); // 업로드 파일명이 physcalFileNm (물리명), logicalFileNm (논리명)으로 리턴 된다.
                    //module.$('#marineCenterRentFileList').flexAddRow({prtAtCode: '', mngYear: '', mngNo: '', mngCnt: '', photoSeq: '', photoSj: '', filenmLogic: this.logicalFileNm, filenmPhysicl: this.physcalFileNm, shotDt: '', photoDesc: '', regUsr: '', registDt:  EMD.util.getTimeStamp()}); // 업로드 파일명이 physcalFileNm (물리명), logicalFileNm (논리명)으로 리턴 된다.
                    module.$('#marineCenterRentFileList').flexAddRow({_updtId:'I', prtAtCode: '', mngYear: '', mngNo: '', mngCnt: '', photoSeq: '', photoSj: '', filenmLogic: this.logicalFileNm, filenmPhysicl: this.physcalFileNm, shotDt: '', photoDesc: '', regUsr: '', registDt:  EMD.util.getTimeStamp()}); // 업로드 파일명이 physcalFileNm (물리명), logicalFileNm (논리명)으로 리턴 된다.
                });
            }, '첨부파일 업로드');
            
            //this._editDataFile=this.getFormValues('#gamMarineCenterRentFileForm', {_updtId:'I'});
            //this._editRowFile=this.$('#marineCenterRentFileList').flexGetData().length;
            
            break;
        
        case 'btnApplyPhotoData':
			
			if(!validateGamMarineCenterRentFile(this.$('#gamMarineCenterRentFileForm')[0])) {
                return;
            }
			
            if(this._editDataFile==null) return;   // 추가나 삭제가 없으면 적용 안됨 2014-03-11 추가
            this._editDataFile=this.getFormValues('#gamMarineCenterRentFileForm', this._editDataFile);
            
            //alert(this._editDataFile._updtId);
            
            if(this._editRowFile!=null) {  // 이전에 _updtId 로 선택 한 것을 _editRowFile 로 변경 2014-03-14.001
                if(this._editDataFile._updtId!='I') this._editDataFile._updtId='U';   // 삽입된 데이터가 아니면 업데이트 플래그를 추가한다.
                this.$('#marineCenterRentFileList').flexUpdateRow(this._editRowFile, this._editDataFile);
                this._editRowFile=null;    // 편집 저장 하였으므로 로우 편집을 종료 한다.
            }
            else {
                this.$('#marineCenterRentFileList').flexAddRow(this._editDataFile);
            }

            this.$('#gamMarineCenterRentFileForm').find(':input').val('');
            this._editDataFile=null;       // 적용 이후 데이터 추가나 삭제 가 되지 않도록 편집 데이터를 제거 함/ 2014-03-11 추가
            
            break;
        
        // 파일 삭제 (Grid상에서만 삭제됨)
        case 'btnRemoveFile':
            var rows = this.$('#marineCenterRentFileList').selectedRows();

            if(rows.length == 0) {
                alert("파일목록에서 삭제할 행을 선택하십시오.");
            } else {
                if(this.$('#marineCenterRentFileList').selectedRowIds().length>0) {
                    for(var i=this.$('#marineCenterRentFileList').selectedRowIds().length-1; i>=0; i--) {
                        var row=this.$('#marineCenterRentFileList').flexGetRow(this.$('#marineCenterRentFileList').selectedRowIds()[i]);
                        
                        //alert( row._updtId );
                        
                        if(row._updtId==undefined || row._updtId!='I') {
                            this._deleteDataFileList[this._deleteDataFileList.length]=row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
                        }
                        this.$('#marineCenterRentFileList').flexRemoveRow(this.$('#marineCenterRentFileList').selectedRowIds()[i]);
                    }
                }
            }

            this.$('#gamMarineCenterRentFileForm').find(':input').val('');
            this._editDataFile = null;

            break;    
            
        case 'btnEApproval':    // 전자결재 테스트
            if(this.$('#marineCenterRentMngtList').selectedRowCount()>0) {
                
                //alert(EMD.context_root);
                
                var rows = this.$('#marineCenterRentMngtList').selectedRows()[0];
                
                if( rows['sanctnSttus'] == '1' || rows['sanctnSttus'] == '2' || rows['sanctnSttus'] == '5' ) {
                    alert("결재요청을 할수없는 상태 입니다.");
                    return;
                }
                
                if( confirm("결재요청을 하시겠습니까?") ) {
                    var opts = {
                            type: 'ARUC',
                            prtAtCode: rows['prtAtCode'],
                            mngYear: rows['mngYear'],
                            mngNo: rows['mngNo'],
                            mngCnt: rows['mngCnt']
                    };
                    this.requestEApproval(opts);
                    
                    alert("결재요청을 하였습니다.");
                    
                    var searchOpt=module.makeFormArgs('#gamMarineCenterRentForm');
                    module.$('#marineCenterRentMngtList').flexOptions({params:searchOpt}).flexReload();
                }
            } else {
                alert("목록에서 결제할 건을 선택하십시오.");
                return;
            }
            break;

            
    }
};

GamMarineCenterRentMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamMarineCenterRentMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamMarineCenterRentMngtSearchForm');
    //this.showAlert(searchOpt);
    this.$('#marineCenterRentMngtList').flexOptions({params:searchOpt}).flexReload();
};

GamMarineCenterRentMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        var row = this.$('#marineCenterRentMngtList').selectedRows();
        if(row.length==0) {
            this.$('#cmd').val('insert');
        }
        else {
            this.$('#cmd').val('modify');
        }

        this._deleteDataList=[];    // 삭제 목록 초기화

        break;
    case 'tabs3':
        var row = this.$('#marineCenterRentDetailList').selectedRows();
        if(row.length==0) {
            this.$('#detailCmd').val('insert');
        }
        else {
            this.$('#detailCmd').val('modify');
        }
        break;
    
    case 'tabs4':
        this._deleteDataFileList=[];    // 파일삭제 목록 초기화
        
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamMarineCenterRentMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
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
     case 'insertMarineCenterRentPrmisnPopup':
         if (msg != 'cancel') {
             if( value == "0" ) {
                 var searchOpt=this.makeFormArgs('#gamMarineCenterRentMngtSearchForm');
                 this.$('#marineCenterRentMngtList').flexOptions({params:searchOpt}).flexReload();
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
             this.$('#gisAssetsPrtAtCodeNm').val(value.gisAssetsPrtAtCodeNm);
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

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamMarineCenterRentMngtModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamMarineCenterRentMngtSearchForm">
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
                                <input id="sEntrpscd" type="text" size="10"><input id="sEntrpsNm" type="text" size="10" readonly> <button id="popupEntrpsInfo">업체</button>
                            </td>
                            <th>사용용도</th>
                            <td>
                                <input id="sUsagePrposCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM007 />
                            </td>
                            <td rowSpan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
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
                <table id="marineCenterRentMngtList" style="display:none" class="fillHeight"></table>

                <div class="emdControlPanel">
                    <table style="width:100%;">
                        <tr>
                            <td>
                               <form id="form1">
                                   합계 :
                                   자료수 <input id="totalResultCnt" size="15" class="ygpaNumber" readonly>
                                   총면적 <input id="totalArea" type="text" size="15" class="ygpaNumber" readonly>
                                   총사용료 <input id="totalUse" type="text" size="15" class="ygpaCurrency" readonly>원

                                   <input id="loginOrgnztId" type="hidden" value="<c:out value="${loginOrgnztId}"/>"/>
                                   <input id="loginUserId" type="hidden" value="<c:out value="${loginUserId}"/>"/>
                                   <input id="currentDateStr" type="hidden" value="<c:out value="${currentDateStr}"/>"/>
                                   <input id="blceStdrIntrrate" type="hidden" value="<c:out value="${blceStdrIntrrate}"/>"/>
                                   <input id="blceStdrIntrrateShow" type="hidden" value="<c:out value="${blceStdrIntrrateShow}"/>"/>
                               </form>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                <button id="addMarineCenterRentFirst">최초신청</button>
                                <button id="addMarineCenterRentRenew">연장신청</button>
                                <button id="btnRemoveItem">신청삭제</button>
                                <button id="btnEApproval">결재요청</button>
                                <button id="btnPrmisn">사용승낙</button>
                                <button id="btnPrmisnCancel">승낙취소</button>
                                <button id="btnShowMap">맵조회</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">
                <div class="emdControlPanel"></div>
                    <form id="gamMarineCenterRentForm">
                        <input type="hidden" id="cmd"/>
                        <input type="hidden" id="quayGroupCd"/>

                        <table>
                            <tr>
                                <th><span class="label">항구분</span></th>
                                <td style="width: 350px">
                                    <input id="prtAtCode" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM019 data-value="640" />
                                    <input type="text" size="5" id="prtAtCodeStr" readonly/>
                                </td>
                                <th><span class="label">담당부서</span></th>
                                <td>
                                    <input id="deptcd" class="ygpaDeptSelect" data-default-prompt="선택" data-value="<c:out value="${loginOrgnztId}"/>" />
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
                                    <input type="text" size="5" id="entrpscd" maxlength="10" readonly/>
                                    <input type="text" size="25" id="entrpsNm" readonly/>
                                    <button id="popupEntrpsInfoInput" class="popupButton">업체조회</button>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">최초신청일자</span></th>
                                <td><input type="text" class="emdcal" size="10" id="frstReqstDt" readonly/></td>
                                <th><span class="label">신청일자</span></th>
                                <td><input type="text" class="emdcal" size="10" id="reqstDt" readonly/></td>
                            </tr>
                            <tr>
                                <th><span class="label">승낙여부</span></th>
                                <td>
                                    <select id="prmisnYn" disabled>
                                        <option value="" selected="selected">선택</option>
                                        <option value="Y">Y</option>
                                        <option value="N">N</option>
                                    </select>
                                </td>
                                <th><span class="label">승낙일자</span></th>
                                <td><input type="text" class="emdcal" size="10" id="prmisnDt" disabled></td>
                            </tr>
                            <tr>
                                <th><span class="label">총사용기간</span></th>
                                <td>
                                    <input type="text" size="10" id="grUsagePdFrom" disabled/>~
                                    <input type="text" size="10" id="grUsagePdTo" disabled/>
                                </td>
                                <th><span class="label">총사용면적</span></th>
                                <td><input type="text" size="10" class="ygpaNumber" id="grAr" disabled/></td>
                            </tr>
                            <tr>
                                <th><span class="label">총사용료</span></th>
                                <td><input type="text" size="10" class="ygpaCurrency" id="grFee" disabled/></td>
                                <th><span class="label">총감면사용료</span></th>
                                <td><input type="text" size="10" class="ygpaCurrency" id="grRdcxptFee" disabled/></td>
                            </tr>
                            <tr>
                                <th><span class="label">납부방법</span></th>
                                <td colspan="3">
                                    <input id="payMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM043 />
                                </td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">고지 방법</span></th>
                                <td>
                                    <input id="nticMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM008 />
                                </td>
                                <th><span class="label">분납이자율</span></th>
                                <td>
                                    <input type="text" size="10" id="payinstIntrrate" maxlength="4"/>
                                    <select id="cofixList">
                                        <option value="">선택</option>
                                        <c:forEach items="${cofixList}" var="cofixListItem">
                                            <option value="${cofixListItem.code }">${cofixListItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            
                            
                            <tr>
                                <th><span class="label">비고</span></th>
                                <td colspan="3"><input type="text" size="50" id="rm" maxlength="90"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">코멘트</span></th>
                                <td colspan="3"><input type="text" size="50" id="cmt" maxlength="90"/><button id="btnSaveComment">코멘트저장</button></td>
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

                 <!-- <table id="marineCenterRentDetailList" style="display:none" class="fillHeight"></table> -->
                 <table id="marineCenterRentDetailList" style="display:none"></table>

                 <table style="width:100%">
                    <tr>
                        <td style="text-align:right" colspan="3"><button id="btnInsertItemDetail" class="buttonAdd">임대상세추가</button><button id="btnRemoveItemDetail">임대상세삭제</button></td>
                    </tr>
                    <tr>
                        <td><button id="xxxx">GIS 등록</button><button id="xxxx">위치조회</button></td>
                        <td width="100"></td>
                        <td style="text-align:right"><button id="btnEApproval">결재요청</button><button id="btnPrmisn">사용승낙</button>
                            <button id="btnPrmisnCancel">승낙취소</button><button id="btnRemoveItem" class="buttonDelete">신청삭제</button><button id="btnSaveItem" class="buttonSave">신청저장</button>
                            <!-- <button id="btnCancelItem">취소</button>  -->
                        </td>
                    </tr>
                 </table>
            </div>

            <div id="tabs3" class="emdTabPage" style="overflow: scroll;">

                <!-- <div class="emdControlPanel"><button id="btnSaveItemDetail">저장</button></div>  -->
                    <form id="gamMarineCenterRentDetailForm">
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

                                <input type="hidden" id="gisAssetsPrtAtCodeNm" />
                                부두코드 : <input type="text" id="quayCd" readonly/>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">자산코드 </span></th>
                                <td><input type="hidden" id="gisAssetsPrtAtCode"/><input type="text" size="3" id="gisAssetsCd" readonly/>-<input type="text" size="2" id="gisAssetsSubCd" readonly/>
                                    <input type="hidden" id="assetsCdStr"/>
                                    <button id="popupFcltyCd" class="popupButton">자산조회</button></td>
                                <th><span class="label">자산명</span></th>
                                <td colspan="3"><input type="text" size="20" id="gisAssetsNm" disabled/></td>
                            </tr>
                            <tr>
                                <th><span class="label">소재지</span></th>
                                <td colspan="3"><input type="text" size="67" id="gisAssetsLocplc" disabled/></td>
                                <th><span class="label">지번</span></th>
                                <td><input type="text" size="5" id="gisAssetsLnm" disabled/>-<input type="text" size="3" id="gisAssetsLnmSub" disabled/></td>
                            </tr>
                            <tr>
                                <th><span class="label">자산면적</span></th>
                                <td style="width: 230px"><input type="text" size="17" class="ygpaNumber" id="gisAssetsAr" disabled/></td>
                                <th style="width: 80px"><span class="label">실제임대면적</span></th>
                                <td style="width: 170px"><input type="text" size="17" class="ygpaNumber" id="gisAssetsRealRentAr" disabled/></td>
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
                                <td colspan="5"><input type="text" class="emdcal calcInput" size="10" id="usagePdFrom" onkeyup="$(this).trigger('change')" readonly/>~<input type="text" class="emdcal calcInput" size="10" id="usagePdTo" onkeyup="$(this).trigger('change')" readonly/></td>
                            </tr>
                            <tr>
                                <th><span class="label">공시지가</span></th>
                                <td><input type="text" size="17" class="calcInput" id="olnlp" onkeyup="$(this).trigger('change')" maxlength="13"/></td>
                                <th><span class="label">사용면적</span></th>
                                <td colspan="3"><input type="text" size="17" class="calcInput" id="usageAr" onkeyup="$(this).trigger('change')" maxlength="8"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">적용요율</span></th>
                                <td>
                                    <!--
                                    <select id="applcTariff">
                                        <option value="" selected="selected">선택</option>
                                    </select>
                                     -->
                                    <input id="applcTariff" class="ygpaCmmnCd calcInput" data-default-prompt="선택" onkeyup="$(this).trigger('change')" data-code-id=GAM023 />

                                    <input type="text" size="14" id="applcTariffStr" readonly/>
                                    <input type="hidden" id="applcTariffNm"/>
                                </td>
                                <th><span class="label">적용방법</span></th>
                                <td colspan="3">
                                    <input id="applcMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM014 />
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">면제구분</span></th>
                                <td colspan="5">
                                    <input id="exemptSe" class="ygpaCmmnCd calcInput" data-default-prompt="선택" data-code-id=GAM009  data-column-label-id='exemptSeNm'/>
                                    <input type="text" size="17" id="exemptSeStr" readonly/>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">면제기간</span></th>
                                <td colspan="5"><input type="text" class="emdcal calcInput" size="10" id="exemptPdFrom" readonly/>~<input type="text" class="emdcal calcInput" size="10" id="exemptPdTo" readonly/></td>
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
                                <td><input type="text" size="20" class="ygpaCurrency" id="fee" />원</td>
                                <th><span class="label">감면사용료</span></th>
                                <td colspan="3"><input type="text" size="20" class="calcInput" id="rdcxptFee" onkeyup="$(this).trigger('change')"/>원</td>
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
                
                <table id="marineCenterRentFileList" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel"><button id="btnUploadFile">업로드</button><button id="btnDownloadFile">다운로드</button><button id="btnRemoveFile">삭제</button></div>
                <form id="gamMarineCenterRentFileForm">
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
                                <input id="photoSj" type="text" size="60" class="photoEditItem" maxlength="40"/>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="label">사진설명</span></th>
                            <td>
                                <input id="photoDesc" type="text" size="60" class="photoEditItem" maxlength="90">
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