<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamOpenApprovalRequest.jsp
  * @Description : 결재 처리 모듈 호출 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.03.19  eunsungj          최초 생성
  *
  * author eunsungj
  * since 2014.03.14
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<html lang="ko" xml:lang="ko">
  <head>
    <title>여수광양항만공사 - GIS기반 자산관리 시스템</title>
  <c:if test="${resultCode==0 }">
    <meta http-equiv="refresh" content="0; url=http://192.168.0.32/jsp/call/UcheckSancData.jsp?T=<c:out value="${tNo}"/>&E=<c:out value="${emplyrNo}"/>" />
    </c:if>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/reset.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/demo/jquery-ui-1.10.4.custom.css' />" />
    <style>
/*       html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
 */    </style>
	<script src="<c:url value='/js/jquery-1.10.2.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-migrate-1.2.1.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-ui.min.js'/>"></script>
  </head>
  <body>
  <c:if test="${resultCode!=0 }">
  		  <h2><c:out value="${resultMsg }"/> 에러코드 (<c:out value="${resultCode }"/>)</h2>
  </c:if>
  <c:if test="${resultCode==0 }">
	  <h2>결재 처리 모듈을 호출 중입니다...</h2>
  </c:if>
  </body>
</html>