<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ErpAssetDeprctnRegister.jsp
  * @Description : ErpAssetDeprctn Register 화면
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

<c:set var="registerFlag" value="${empty erpAssetDeprctnVO.deprctnYear ? '등록' : '수정'}"/>

<title> <c:out value="${registerFlag}"/> </title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>

<!--For Commons Validator Client Side-->
<!-- script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script -->
<!-- validator:javascript formName="erpAssetDeprctnVO" staticJavascript="false" xhtml="true" cdata="false"/ -->

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.getElementById("detailForm").action = "<c:url value='/erpAssetDeprctn/ErpAssetDeprctnList.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 삭제 function */
function fn_egov_delete() {
   	document.getElementById("detailForm").action = "<c:url value='/erpAssetDeprctn/deleteErpAssetDeprctn.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 등록 function */
function fn_egov_save() {	
	frm = document.getElementById("detailForm");

	/* TODO Validation기능 보완 */
	
  	frm.action = "<c:url value="${registerFlag == '등록' ? '/erpAssetDeprctn/addErpAssetDeprctn.do' : '/erpAssetDeprctn/updateErpAssetDeprctn.do'}"/>";
    frm.submit();

}

// -->
</script>
</head>
<body>

<form:form commandName="erpAssetDeprctnVO" name="detailForm" id="detailForm" >
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="title">
		<ul>
			<li><img src="<c:url value='/images/egovframework/rte/title_dot.gif'/>" alt="" /><c:out value="${registerFlag}"/></li>
		</ul>
	</div>
	<!-- // 타이틀 -->
	<div id="table">
	<table width="100%" border="1" cellpadding="0" cellspacing="0" >
		<colgroup>
			<col width="150"/>
			<col width=""/>
		</colgroup>
			
		<c:if test="${registerFlag == '수정'}">
	   <tr>
			<th>${model.attribute.get(0).name} *</th>
			<td>
				<form:input path="${model.attribute.get(0).ccName}" cssClass="essentiality" readonly="true" />
			</td>			
		</tr>	
		</c:if>
		<c:if test="${registerFlag == '등록'}">
	   <tr>
			<th>${model.attribute.get(0).name} *</th>
			<td>
				<form:input path="${model.attribute.get(0).ccName}" cssClass="txt" readonly="false" />
			</td>			
		</tr>	
		</c:if>		
		<tr>
			<th>REVAL_YEAR</th>
			<td>
				<form:input path="revalYear" cssClass="txt"/>
				&nbsp;<form:errors path="revalYear" />
			</td>
		</tr>	
		<tr>
			<th>THIS_END_BASE_VALUE_EVAL_AMT</th>
			<td>
				<form:input path="thisEndBaseValueEvalAmt" cssClass="txt"/>
				&nbsp;<form:errors path="thisEndBaseValueEvalAmt" />
			</td>
		</tr>	
		<tr>
			<th>THIS_TERM_INCRE_YEAR_MON</th>
			<td>
				<form:input path="thisTermIncreYearMon" cssClass="txt"/>
				&nbsp;<form:errors path="thisTermIncreYearMon" />
			</td>
		</tr>	
		<tr>
			<th>THIS_TERM_INCRE_AMT</th>
			<td>
				<form:input path="thisTermIncreAmt" cssClass="txt"/>
				&nbsp;<form:errors path="thisTermIncreAmt" />
			</td>
		</tr>	
		<tr>
			<th>BS_THIS_CUR_AMT</th>
			<td>
				<form:input path="bsThisCurAmt" cssClass="txt"/>
				&nbsp;<form:errors path="bsThisCurAmt" />
			</td>
		</tr>	
		<tr>
			<th>BS_PRE_DEPRCTN_SUM</th>
			<td>
				<form:input path="bsPreDeprctnSum" cssClass="txt"/>
				&nbsp;<form:errors path="bsPreDeprctnSum" />
			</td>
		</tr>	
		<tr>
			<th>BS_NO_DEPRCTN_BAL</th>
			<td>
				<form:input path="bsNoDeprctnBal" cssClass="txt"/>
				&nbsp;<form:errors path="bsNoDeprctnBal" />
			</td>
		</tr>	
		<tr>
			<th>PRE_END_ASSET_BUY_SUM</th>
			<td>
				<form:input path="preEndAssetBuySum" cssClass="txt"/>
				&nbsp;<form:errors path="preEndAssetBuySum" />
			</td>
		</tr>	
		<tr>
			<th>ASSET_BUY_AMT</th>
			<td>
				<form:input path="assetBuyAmt" cssClass="txt"/>
				&nbsp;<form:errors path="assetBuyAmt" />
			</td>
		</tr>	
		<tr>
			<th>THIS_END_ASSET_BUY_SUM</th>
			<td>
				<form:input path="thisEndAssetBuySum" cssClass="txt"/>
				&nbsp;<form:errors path="thisEndAssetBuySum" />
			</td>
		</tr>	
		<tr>
			<th>GNRL_DEPRCTN_RATE</th>
			<td>
				<form:input path="gnrlDeprctnRate" cssClass="txt"/>
				&nbsp;<form:errors path="gnrlDeprctnRate" />
			</td>
		</tr>	
		<tr>
			<th>THIS_TERM_DEPRCTN_AMT</th>
			<td>
				<form:input path="thisTermDeprctnAmt" cssClass="txt"/>
				&nbsp;<form:errors path="thisTermDeprctnAmt" />
			</td>
		</tr>	
		<tr>
			<th>PRE_END_CUR_BUY_AMT</th>
			<td>
				<form:input path="preEndCurBuyAmt" cssClass="txt"/>
				&nbsp;<form:errors path="preEndCurBuyAmt" />
			</td>
		</tr>	
		<tr>
			<th>CUR_AMT</th>
			<td>
				<form:input path="curAmt" cssClass="txt"/>
				&nbsp;<form:errors path="curAmt" />
			</td>
		</tr>	
		<tr>
			<th>INPUT_EMP_NO</th>
			<td>
				<form:input path="inputEmpNo" cssClass="txt"/>
				&nbsp;<form:errors path="inputEmpNo" />
			</td>
		</tr>	
		<tr>
			<th>INPUT_DATE</th>
			<td>
				<form:input path="inputDate" cssClass="txt"/>
				&nbsp;<form:errors path="inputDate" />
			</td>
		</tr>	
		<tr>
			<th>UPDATE_EMP_NO</th>
			<td>
				<form:input path="updateEmpNo" cssClass="txt"/>
				&nbsp;<form:errors path="updateEmpNo" />
			</td>
		</tr>	
		<tr>
			<th>UPDATE_DATE</th>
			<td>
				<form:input path="updateDate" cssClass="txt"/>
				&nbsp;<form:errors path="updateDate" />
			</td>
		</tr>	
	</table>
  </div>
	<div id="sysbtn">
		<ul>
			<li><span class="btn_blue_l"><a href="javascript:fn_egov_selectList();">List</a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" alt="" /></span></li>
			<li><span class="btn_blue_l"><a href="javascript:fn_egov_save();"><c:out value='${registerFlag}'/></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" alt="" /></span></li>
			<c:if test="${registerFlag == '수정'}">
			<li><span class="btn_blue_l"><a href="javascript:fn_egov_delete();">삭제</a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" alt="" /></span></li>
			</c:if>
			<li><span class="btn_blue_l"><a href="javascript:document.getElementById("detailForm").reset();">Reset</a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" alt="" /></span></li></ul>
	</div>
</div>
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form:form>
</body>
</html>

