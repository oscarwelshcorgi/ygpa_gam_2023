<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamSendMesgListMngt.jsp
  * @Description : SNS 메세지 목록 관리(공통기능)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.04.14  김종민     최초 생성
  *
  * author 김종민
  * since 2014.04.14
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSendMesgListMngtModule() {}

GamSendMesgListMngtModule.prototype = new EmdModule(1000, 600);

//선택한 순번을 저장하는 전역 변수
var g_seqNum;

// 페이지가 호출 되었을때 호출 되는 함수
GamSendMesgListMngtModule.prototype.loadComplete = function() {

    // SMS 메세지 목록 테이블 설정
    this.$("#sendMesgMngtList").flexigrid({
        module: this,
        url: '<c:url value="/cmmn/sms/gamSelectSendMesgList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'SMS순번', name:'smsSeq',width:50, sortable:false,align:'center'},
                    {display:'수신번호', name:'recptnNo',width:100, sortable:false,align:'center'},
                    {display:'회신번호', name:'replyNo',width:100, sortable:false,align:'center'},
                    {display:'내용', name:'cn',width:150, sortable:false,align:'center'},
                    {display:'전송일자', name:'trnsmisDt',width:100, sortable:false,align:'center'},
                    {display:'전송상태', name:'trnsmisSttus',width:60, sortable:false,align:'center'},
                    {display:'관리년도', name:'mngYear',width:60, sortable:false,align:'center'},
                    {display:'관리번호', name:'mngNo',width:50, sortable:false,align:'center'},
                    {display:'관리횟수', name:'mngCnt',width:50, sortable:false,align:'center'},
                    {display:'등록자', name:'regUsr',width:80, sortable:false,align:'center'},
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            return data;
        }
    });

    this.$("#sendMesgMngtList").on('onItemSelected', function(event, module, row, grid, param) {
        if(row==null) return;
       		g_seqNum = row['smsSeq'];
    });
    
    
    // 컴포넌트이 이벤트를 추가한다. (기존 코드 데이터에 선택 값이 onchange 안되는 점을 수정 함)
    this.$('#prtAtCode').on('change', {module: this}, function(event) {
        event.data.module.$('#prtAtCodeStr').val($(this).val());
        //alert($(this).getSelectedCodeLabel() + '이(가) 선택되었습니다.');
    });
    
    
 	// 오늘 날짜로 전송기간 설정 처리
	var today = new Date();
	
	var serchYr = today.getFullYear();
	var serchMn = today.getMonth() + 1;
	
	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}
	
	var serchday = today.getDate();
	
	var displayDate = serchYr + "-" + serchMn + "-" + serchday;

	this.$("#searchDTFrom").val(displayDate);
	this.$("#searchDTTo").val(displayDate);
     
};

/**
 * 정의 된 버튼 클릭 시
 */
GamSendMesgListMngtModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
    	// 조회
        case 'searchBtn':
        	if( this.$('#searchDTFrom').val() == '' ) {
                alert("전송조회시작일을 선택하십시오.");
                return;
            }
        	if( this.$('#searchDTTo').val() == '' ) {
                alert("전송조회종료일을 선택하십시오.");
                return;
            }
        	var id, ret='';
        	id = '#transmisSttus';
        	ret = '';
        	for(var i=0; i<4; i++) {
       			var idseq = id + i;
       			if(this.$(idseq).is(':checked')) {
       				ret = ret + this.$(idseq).val() + ', ';
       			}
        	}
        	this.$(id).val(ret);
            var searchOpt=this.makeFormArgs('#GamSendMesgListMngtSearchForm');
            this.$('#sendMesgMngtList').flexOptions({params:searchOpt}).flexReload();
            break;
            
        // 재전송
        case 'retransmitBtn':
        	if(g_seqNum == '') { 
        		return;  
        	}
        	this.$('#smsSeq').val(g_seqNum);
        	g_seqNum = '';
        	var searchOpt = this.makeFormArgs('#GemSmsRetransmitForm');
        	this.doAction('<c:url value="/cmmn/sms/smsRetransmit.do" />', searchOpt, function(module, result) {
        		if(result.resultCode == "0"){
        			alert('재전송 신청이 완료되었습니다.');
        			searchOpt = module.makeFormArgs('#GamSendMesgListMngtSearchForm');        			
        			module.$('#sendMesgMngtList').flexOptions({params:searchOpt}).flexReload();
    	 		}
    	 		else {
    	 			alert(result.resultMsg);
    	 		}
    	 	});
            break;
    }
};

GamSendMesgListMngtModule.prototype.onSubmit = function() {
    this.loadData();
};

GamSendMesgListMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#GamSendMesgListMngtSearchForm');
    this.$('#sendMesgMngtList').flexOptions({params:searchOpt}).flexReload();
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSendMesgListMngtModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="GamSendMesgListMngtSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>
                            <td>
                                <input id="prtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" />
                            </td>
                            <th>전송기간</th>
                            <td>
                                <input id="searchDTFrom" type="text" class="emdcal"
                                	size="8" value="<c:out value="${grUsagePdFromStr}"/>" readonly> ~ <input id="searchDTTo" type="text"
                                	class="emdcal" size="8" value="<c:out value="${grUsagePdToStr}"/>" readonly>
                            </td>
                            <th>관리년도</th>
                            <td>
                                <input id="mngYear" type="text" size="4">
                            </td>
                            <th>관리번호</th>
                            <td>
                            	<input id="mngNo" type="text" size="3">
                            </td>
                            <th>관리 횟수</th>
                            <td>
                            	<input id="mngCnt" type="text" size="2">
                            </td>
                            <td rowSpan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                            <th>전송상태</th>
                            <td colspan="9">
                            	<input type="hidden" id="transmisSttus"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            	<span>전송중</span> <input id="transmisSttus0" type="checkbox" value="0"> 
                                <span>전송완료</span> <input id="transmisSttus1" type="checkbox" value="1">
                                <span>전송실패</span> <input id="transmisSttus2" type="checkbox" value="4">
                                <span>전송취소</span> <input id="transmisSttus3" type="checkbox" value="9">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="prtFcltyUseExprInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">SNS 메세지 목록 관리</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="sendMesgMngtList" style="display:none" class="fillHeight">
                </table>

                <div class="emdControlPanel">
                    <table style="width:100%;">
                        <tr>
                            <td style="text-align: right">
                                <button id="retransmitBtn">재전송</button>
                            </td>
                        </tr>
                    </table>
                </div>
               	<form id="GemSmsRetransmitForm">
        			<input type="hidden" id="smsSeq" />
        		</form>
            </div>
        </div>
    </div>
</div>