<%@ page import="java.net.URLDecoder"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamFcltyQcMngResultListReportHwp.jsp
  * @Description : 시설물 점검표 한글파일 출력
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.10.28    김종민          최초 생성
  *
  * author
  * since 
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<%
if(request.getAttribute("isHwp")!=null){
	String fileName = (String)request.getAttribute("filename");
	fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
	response.reset();
	response.setHeader("Content-Disposition", "attachment;filename=\""+fileName + "\"");
	response.setHeader("Content-Description", "JSP Generated Data");
	response.setHeader("Cache-control","private");
	response.setContentType("application/hwp; charset=UTF-8");
	
	String fileContents = (String)request.getAttribute("hwpML");

	out.clear();
	out.write(fileContents);
	out.flush();
}
%>