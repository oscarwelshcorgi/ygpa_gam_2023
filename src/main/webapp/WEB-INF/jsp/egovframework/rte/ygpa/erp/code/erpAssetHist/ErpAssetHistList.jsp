<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ErpAssetHistList.jsp
  * @Description : ErpAssetHist List 화면
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


function fn_egov_select(changeDate, indexNo, assetCls, assetNo, assetNoSeq) {
	document.getElementById("listForm").changeDate.value = changeDate;
	document.getElementById("listForm").indexNo.value = indexNo;
	document.getElementById("listForm").assetCls.value = assetCls;
	document.getElementById("listForm").assetNo.value = assetNo;
	document.getElementById("listForm").assetNoSeq.value = assetNoSeq;
   	document.getElementById("listForm").action = "<c:url value='/erpAssetHist/updateErpAssetHistView.do'/>";
   	document.getElementById("listForm").submit();
}

/* 글 등록 화면 function */
function fn_egov_addView() {
   	document.getElementById("listForm").action = "<c:url value='/erpAssetHist/addErpAssetHistView.do'/>";
   	document.getElementById("listForm").submit();		
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.getElementById("listForm").pageIndex.value = pageNo;
	document.getElementById("listForm").action = "<c:url value='/erpAssetHist/ErpAssetHistList.do'/>";
   	document.getElementById("listForm").submit();
}

 // -->
</script>
</head>
<body>
<form:form commandName="searchVO" name="listForm" id="listForm" method="post">
	<input type="hidden" name="changeDate" />
	<input type="hidden" name="indexNo" />
	<input type="hidden" name="assetCls" />
	<input type="hidden" name="assetNo" />
	<input type="hidden" name="assetNoSeq" />
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
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
							</colgroup>		  
			<tr>
								<th align="center">AccUnitCls</th>
								<th align="center">ProjectCd</th>
								<th align="center">DeptCd</th>
								<th align="center">EmpNo</th>
								<th align="center">PlaceCd</th>
								<th align="center">ChangeCls</th>
								<th align="center">ExpCls</th>
								<th align="center">Qty</th>
								<th align="center">Remark</th>
								<th align="center">InputEmpNo</th>
								<th align="center">InputDate</th>
								<th align="center">UpdateEmpNo</th>
								<th align="center">UpdateDate</th>
								<th align="center">ChangeDate</th>
								<th align="center">IndexNo</th>
								<th align="center">AssetCls</th>
								<th align="center">AssetNo</th>
								<th align="center">AssetNoSeq</th>
							</tr>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
																																					<td align="center" class="listtd"><c:out value="${result.accUnitCls}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.projectCd}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.deptCd}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.empNo}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.placeCd}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.changeCls}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.expCls}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.qty}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.remark}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.inputEmpNo}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.inputDate}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.updateEmpNo}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.updateDate}"/>&nbsp;</td>
						<td align="center" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.changeDate}"/>', '<c:out value="${result.indexNo}"/>', '<c:out value="${result.assetCls}"/>', '<c:out value="${result.assetNo}"/>', '<c:out value="${result.assetNoSeq}"/>')"><c:out value="${result.changeDate}"/></a>&nbsp;</td>
						<td align="center" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.changeDate}"/>', '<c:out value="${result.indexNo}"/>', '<c:out value="${result.assetCls}"/>', '<c:out value="${result.assetNo}"/>', '<c:out value="${result.assetNoSeq}"/>')"><c:out value="${result.indexNo}"/></a>&nbsp;</td>
						<td align="center" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.changeDate}"/>', '<c:out value="${result.indexNo}"/>', '<c:out value="${result.assetCls}"/>', '<c:out value="${result.assetNo}"/>', '<c:out value="${result.assetNoSeq}"/>')"><c:out value="${result.assetCls}"/></a>&nbsp;</td>
						<td align="center" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.changeDate}"/>', '<c:out value="${result.indexNo}"/>', '<c:out value="${result.assetCls}"/>', '<c:out value="${result.assetNo}"/>', '<c:out value="${result.assetNoSeq}"/>')"><c:out value="${result.assetNo}"/></a>&nbsp;</td>
						<td align="center" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.changeDate}"/>', '<c:out value="${result.indexNo}"/>', '<c:out value="${result.assetCls}"/>', '<c:out value="${result.assetNo}"/>', '<c:out value="${result.assetNoSeq}"/>')"><c:out value="${result.assetNoSeq}"/></a>&nbsp;</td>
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
