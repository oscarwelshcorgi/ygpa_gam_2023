<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : loginError.jsp
  * @Description : 로그인 에러 페이지
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
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamErrorModule() {}

GamErrorModule.prototype = new EmdErrorModule();

// 페이지가 호출 되었을때 호출 되는 함수
GamErrorModule.prototype.loadComplete = function() {
	this.setMessage('${message}');
};

// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamErrorModule();
</script>
<h2><c:out value="${message }" /></h2>
