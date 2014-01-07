<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ErpAssetCdRegister.jsp
  * @Description : ErpAssetCd Register 화면
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

<c:set var="registerFlag" value="${empty erpAssetCdVO.assetCls ? '등록' : '수정'}"/>

<title> <c:out value="${registerFlag}"/> </title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>

<!--For Commons Validator Client Side-->
<!-- script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script -->
<!-- validator:javascript formName="erpAssetCdVO" staticJavascript="false" xhtml="true" cdata="false"/ -->

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.getElementById("detailForm").action = "<c:url value='/erpAssetCd/ErpAssetCdList.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 삭제 function */
function fn_egov_delete() {
   	document.getElementById("detailForm").action = "<c:url value='/erpAssetCd/deleteErpAssetCd.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 등록 function */
function fn_egov_save() {	
	frm = document.getElementById("detailForm");

	/* TODO Validation기능 보완 */
	
  	frm.action = "<c:url value="${registerFlag == '등록' ? '/erpAssetCd/addErpAssetCd.do' : '/erpAssetCd/updateErpAssetCd.do'}"/>";
    frm.submit();

}

// -->
</script>
</head>
<body>

<form:form commandName="erpAssetCdVO" name="detailForm" id="detailForm" >
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
			<th>ASSET_MNGT_NO</th>
			<td>
				<form:input path="assetMngtNo" cssClass="txt"/>
				&nbsp;<form:errors path="assetMngtNo" />
			</td>
		</tr>	
		<tr>
			<th>ITEM_CLS</th>
			<td>
				<form:input path="itemCls" cssClass="txt"/>
				&nbsp;<form:errors path="itemCls" />
			</td>
		</tr>	
		<tr>
			<th>ITEM_NAME</th>
			<td>
				<form:input path="itemName" cssClass="txt"/>
				&nbsp;<form:errors path="itemName" />
			</td>
		</tr>	
		<tr>
			<th>BUY_DATE</th>
			<td>
				<form:input path="buyDate" cssClass="txt"/>
				&nbsp;<form:errors path="buyDate" />
			</td>
		</tr>	
		<tr>
			<th>CUR_QTY</th>
			<td>
				<form:input path="curQty" cssClass="txt"/>
				&nbsp;<form:errors path="curQty" />
			</td>
		</tr>	
		<tr>
			<th>DEPRCTN_AMT</th>
			<td>
				<form:input path="deprctnAmt" cssClass="txt"/>
				&nbsp;<form:errors path="deprctnAmt" />
			</td>
		</tr>	
		<tr>
			<th>BUY_CLS</th>
			<td>
				<form:input path="buyCls" cssClass="txt"/>
				&nbsp;<form:errors path="buyCls" />
			</td>
		</tr>	
		<tr>
			<th>PUR_CLS</th>
			<td>
				<form:input path="purCls" cssClass="txt"/>
				&nbsp;<form:errors path="purCls" />
			</td>
		</tr>	
		<tr>
			<th>PUR_PURPOSE</th>
			<td>
				<form:input path="purPurpose" cssClass="txt"/>
				&nbsp;<form:errors path="purPurpose" />
			</td>
		</tr>	
		<tr>
			<th>PUR_CUST</th>
			<td>
				<form:input path="purCust" cssClass="txt"/>
				&nbsp;<form:errors path="purCust" />
			</td>
		</tr>	
		<tr>
			<th>ACC_UNIT_CLS</th>
			<td>
				<form:input path="accUnitCls" cssClass="txt"/>
				&nbsp;<form:errors path="accUnitCls" />
			</td>
		</tr>	
		<tr>
			<th>PROJECT_CD</th>
			<td>
				<form:input path="projectCd" cssClass="txt"/>
				&nbsp;<form:errors path="projectCd" />
			</td>
		</tr>	
		<tr>
			<th>PLACE_CD</th>
			<td>
				<form:input path="placeCd" cssClass="txt"/>
				&nbsp;<form:errors path="placeCd" />
			</td>
		</tr>	
		<tr>
			<th>DEPT_CD</th>
			<td>
				<form:input path="deptCd" cssClass="txt"/>
				&nbsp;<form:errors path="deptCd" />
			</td>
		</tr>	
		<tr>
			<th>EMP_NO</th>
			<td>
				<form:input path="empNo" cssClass="txt"/>
				&nbsp;<form:errors path="empNo" />
			</td>
		</tr>	
		<tr>
			<th>MODEL_NAME</th>
			<td>
				<form:input path="modelName" cssClass="txt"/>
				&nbsp;<form:errors path="modelName" />
			</td>
		</tr>	
		<tr>
			<th>ASSET_SIZE</th>
			<td>
				<form:input path="assetSize" cssClass="txt"/>
				&nbsp;<form:errors path="assetSize" />
			</td>
		</tr>	
		<tr>
			<th>PRODUCT_SEQ_NO</th>
			<td>
				<form:input path="productSeqNo" cssClass="txt"/>
				&nbsp;<form:errors path="productSeqNo" />
			</td>
		</tr>	
		<tr>
			<th>MAKER_NAME</th>
			<td>
				<form:input path="makerName" cssClass="txt"/>
				&nbsp;<form:errors path="makerName" />
			</td>
		</tr>	
		<tr>
			<th>REMARK1</th>
			<td>
				<form:input path="remark1" cssClass="txt"/>
				&nbsp;<form:errors path="remark1" />
			</td>
		</tr>	
		<tr>
			<th>REMARK2</th>
			<td>
				<form:input path="remark2" cssClass="txt"/>
				&nbsp;<form:errors path="remark2" />
			</td>
		</tr>	
		<tr>
			<th>ACCNT_CD</th>
			<td>
				<form:input path="accntCd" cssClass="txt"/>
				&nbsp;<form:errors path="accntCd" />
			</td>
		</tr>	
		<tr>
			<th>DEPRCTN_CLS</th>
			<td>
				<form:input path="deprctnCls" cssClass="txt"/>
				&nbsp;<form:errors path="deprctnCls" />
			</td>
		</tr>	
		<tr>
			<th>ASSET_FIX_TERM</th>
			<td>
				<form:input path="assetFixTerm" cssClass="txt"/>
				&nbsp;<form:errors path="assetFixTerm" />
			</td>
		</tr>	
		<tr>
			<th>CHANGE_CLS</th>
			<td>
				<form:input path="changeCls" cssClass="txt"/>
				&nbsp;<form:errors path="changeCls" />
			</td>
		</tr>	
		<tr>
			<th>CHANGE_DATE</th>
			<td>
				<form:input path="changeDate" cssClass="txt"/>
				&nbsp;<form:errors path="changeDate" />
			</td>
		</tr>	
		<tr>
			<th>CHANGE_AMT</th>
			<td>
				<form:input path="changeAmt" cssClass="txt"/>
				&nbsp;<form:errors path="changeAmt" />
			</td>
		</tr>	
		<tr>
			<th>PIC_IMAGE</th>
			<td>
				<form:input path="picImage" cssClass="txt"/>
				&nbsp;<form:errors path="picImage" />
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

