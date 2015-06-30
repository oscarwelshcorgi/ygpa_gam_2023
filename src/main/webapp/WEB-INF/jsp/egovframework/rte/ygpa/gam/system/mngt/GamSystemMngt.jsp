<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld"%>
<%--
  @Class Name : GamSystemMngt.jsp
  @Description : 시스템 관리
  @Modification Information

	수정일 			       수정자 		                      수정내용
	------------ 	----------	---------------------------
    2015.06.18  	eunsungj	 최초 생성

   author eunsungj
   since 2015.06.18

  Copyright (C) 2013 by LFIT  All right reserved.
--%>
<script>
<%--
 	아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
--%>
function GamSystemMngtModule() {}

<%--
	EmdModule을 상속하여 모듈 클래스를 정의한다.
--%>
GamSystemMngtModule.prototype = new EmdModule(800, 600);

<%--
	페이지가 호출 되었을때 호출 되는 함수
--%>
GamSystemMngtModule.prototype.loadComplete = function() {
    console.log('load complete');
};

<%--
버튼 클릭에 대한 이벤트 핸들러 (EmdModule에서 Overriding 된 함수임 모듈에서 자동으로 호출 됨)
스위치문 안에 코드가 길어지면 반드시 하위 함수로 분리 할 것.
    	case 문에 주석을 달때는 case 문 뒤에 붙일 것
--%>
 GamSystemMngtModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
	    case 'createLevReqest':
	        this.createLevReqest();
	        break;
    }
};

<%--
	사용료 고지 자료 생성
--%>
GamSystemMngtModule.prototype.createLevReqest = function() {
   if( confirm("고지자료를 생성 하시겠습니까?") ) {
		this.doAction('/system/mngt/createHtldRentMngtFirst.do', {}, function(module, result) {
		    if(result.resultCode == 0){
		    	console.log("load complete");
		    }
		    alert(result.resultMessage);
		});
    }
};

<%--
	EmdModule에서 Overriding 된 Submit 함수.
	모듈에서 엔터키를 입력 하거나 submitButton 클래스의 버튼이 눌려졌을때 호출되는 이벤트 함수. (포커스에 따라 동작 안될 때도 있음.)
--%>
GamSystemMngtModule.prototype.onSubmit = function() {
};

<%--
탭이 변경 되기 전에 호출되는 이벤트 핸들러 : 리턴값이 false이면 탭 변경이 취소되어 탭이 바뀌질 않는다.
--%>
GamSystemMngtModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        break;
    case 'tabs3':
        break;
      default:
        return true;
	}
	return true;
};

<%--
	탭이 변경 된 후 호출 되는 이벤트 핸들러
--%>
GamSystemMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        break;
    case 'tabs3':
        break;
    case 'tabs4':
        break;
        }
};

<%--
	팝업이 종료 될때 리턴 값이 오출 된다.	EmdModule에서 호출 함.
		popupId : 팝업 대화상자 아이디
		msg : 팝업에서 전송한 메시지 (취소는 cancel)
		value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
--%>
GamSystemMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');

         break;
     }
};

<%--
	다음 변수는 고정 적으로 정의 해야 함
	module_instance는 고정 변수 GamSystemMngtModule은 위에서 EmdModule을 상속 받는 이 윈도우의 모듈 함수로 정의 됨.
--%>
var module_instance = new GamSystemMngtModule();

</script>
<%--
	이 페이지를 로딩 하기 위한 window_id 변수. jsp를 호출 하는 model에 담겨서 와야 함.
--%>
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
    <div class="emdPanel fillHeight">
        <div id="systemMngtTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
            <ul>
                <li><a href="#tabs1" class="emdTab">기본기능</a></li>
                <li><a href="#tabs2" class="emdTab">변경이력</a></li>
                <li><a href="#tabs3" class="emdTab">권한관련</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;">
                <div class="emdControlPanel fillHeight">
					<form id="form1">
    	               	<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                                <button id="createLevReqest">배후단지 사용료 고지 생성</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage " style="overflow:scroll;">
                <div class="emdControlPanel">
                 </div>
            </div>
            <div id="tabs3" class="emdTabPage fillHeight" style="overflow: hidden;">
                <div class="emdControlPanel">
               	</div>
            </div>
        </div>
    </div>
</div>