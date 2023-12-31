<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ErpCmmnCdList.jsp
  * @Description : ErpCmmnCd List 화면
  * @Modification Information
  * 
  * @author 장은성
  * @since 2013-12-20
  * @version 1.0
  * @see
  *  
  * Copyright (C) All right reserved.
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>목록</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 수정 화면 function */


function fn_egov_select(smCls, bigClsCd) {
	document.getElementById("listForm").smCls.value = smCls;
	document.getElementById("listForm").bigClsCd.value = bigClsCd;
   	document.getElementById("listForm").action = "<c:url value='/erpCmmnCd/updateErpCmmnCdView.do'/>";
   	document.getElementById("listForm").submit();
}

/* 글 등록 화면 function */
function fn_egov_addView() {
   	document.getElementById("listForm").action = "<c:url value='/erpCmmnCd/addErpCmmnCdView.do'/>";
   	document.getElementById("listForm").submit();		
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.getElementById("listForm").pageIndex.value = pageNo;
	document.getElementById("listForm").action = "<c:url value='/erpCmmnCd/ErpCmmnCdList.do'/>";
   	document.getElementById("listForm").submit();
}

 // -->
</script>
</head>
<body>
<form:form commandName="searchVO" name="listForm" id="listForm" method="post">
	<input type="hidden" name="smCls" />
	<input type="hidden" name="bigClsCd" />
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="title">
		<ul>
			<li><img src="<c:url value='/images/egovframework/rte/title_dot.gif'/>" alt="title" /> List </li>
		</ul>
	</div>
	<!-- // 타이틀 -->
	<!-- List -->
	<div id="table">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<colgroup>
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
							</colgroup>		  
			<tr>
								<th align="center">SmClsName</th>
								<th align="center">RefCd</th>
								<th align="center">Remark</th>
								<th align="center">OutputOrder</th>
								<th align="center">UseCls</th>
								<th align="center">InputEmpNo</th>
								<th align="center">InputDate</th>
								<th align="center">UpdateEmpNo</th>
								<th align="center">UpdateDate</th>
								<th align="center">SmCls</th>
								<th align="center">BigClsCd</th>
							</tr>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
																			<td align="center" class="listtd"><c:out value="${result.smClsName}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.refCd}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.remark}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.outputOrder}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.useCls}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.inputEmpNo}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.inputDate}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.updateEmpNo}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.updateDate}"/>&nbsp;</td>
						<td align="center" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.smCls}"/>', '<c:out value="${result.bigClsCd}"/>')"><c:out value="${result.smCls}"/></a>&nbsp;</td>
						<td align="center" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.smCls}"/>', '<c:out value="${result.bigClsCd}"/>')"><c:out value="${result.bigClsCd}"/></a>&nbsp;</td>
				    			</tr>
			</c:forEach>
		</table>
	</div>
	<!-- /List -->
	<div id="paging">
		<ui:pagination paginationInfo = "${paginationInfo}"
				   type="image"
				   jsFunction="fn_egov_link_page"
				   />
		<form:hidden path="pageIndex" />
	</div>
	<div id="sysbtn1">
		<ul>
			<li>
				<div id="sysbtn">
					<span class="btn_blue_l"><a href="javascript:fn_egov_addView();">등록</a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" alt="" />
					</span>
				</div>
			</li>
		</ul>
	</div>
</div>
</form:form>
</body>
</html>
