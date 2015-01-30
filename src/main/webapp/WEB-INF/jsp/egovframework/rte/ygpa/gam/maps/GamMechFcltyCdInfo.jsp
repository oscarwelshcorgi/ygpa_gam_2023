<%@page import="org.springframework.util.Assert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:requestEncoding/>
<fmt:setLocale value="ko"/>
<script>
function GamMapPopupModule() {};

GamMapPopupModule.prototype = new EmdPopupInfoModule();

GamMapPopupModule.prototype.loadComplete = function() {
	var attachList=[];
	this.$('#imgPreview').click({module:this}, function(e) {
		console.log("image clicked");
		if(e.data.module._imgList.length>1) {
			e.data.module._imgNo++;
			if(e.data.module._imgNo>=e.data.module._imgList.length) {
				e.data.module._imgNo=0;
			}
			var imgURL = EMD.context_root+"/cmm/getPfImage.do?physicalFileNm="+e.data.module._imgList[e.data.module._imgNo];
			$(e.target).attr("src", imgURL);
		}
	});
	this.$("input[name='atchFileNmPhysicl']").each(function(){
		var fname=$(this).val();
		var exts=fname.split(".");
		var ext=exts[exts.length-1].toLowerCase();
		if(ext=="png"||ext=="jpg"||ext=="jpeg"||ext=="gif") {
			attachList[attachList.length]=fname;
			console.log(fname+" list added");
		}
		else {
			console.log(fname+" list canceled");
			console.log(this);
		}
	});
	this._imgNo=0;
	this._imgList=attachList;
	this.$('#imgPanel').append();
	console.log('popup module starting debugging.');
};

GamMapPopupModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'fcltyCdMngt':
        	EMD.util.create_window("기계시설코드 관리", "/fclty/gamMechFcltySpecMng.do", null, {
        		action: "prtFcltyInqire"
       			,gisPrtAtCode: this.$('#gisAssetsPrtAtCode').val()
       			,gisAssetsCd: this.$('#gisAssetsCd').val()
       			,gisAssetsSubCd: this.$('#gisAssetsSubCd').val()
       			,gisPrtFcltyCd: this.$('#gisPrtFcltyCd').val()
       			,gisPrtFcltySeq: this.$('#gisPrtFcltySeq').val()
       			,fcltySe: this.$('#fcltySe').val()
       			,fcltsMngNo: this.$('#fcltsMngNo').val()
			});
            break;
        case 'fcltyCdInqire':
        	EMD.util.create_window("기계시설코드 조회", "/fclty/gamMechFcltySpecInqire.do", null, {
        		action: "prtFcltyInqire"
       			,gisPrtAtCode: this.$('#gisAssetsPrtAtCode').val()
       			,gisAssetsCd: this.$('#gisAssetsCd').val()
       			,gisAssetsSubCd: this.$('#gisAssetsSubCd').val()
       			,gisPrtFcltyCd: this.$('#gisPrtFcltyCd').val()
       			,gisPrtFcltySeq: this.$('#gisPrtFcltySeq').val()
       			,fcltySe: this.$('#fcltySe').val()
       			,fcltsMngNo: this.$('#fcltsMngNo').val()
			});
        	break;
        case 'assignFeature':
        	EMD.util.create_window("기계 시설 제원 코드 지정", "/fclty/gamMechFcltySpecMng.do", null, {
        		action: "setFeature"
    			,feature: this.getFeature()
			});
        	break;
        case 'removeFeature':
        	if(this.getFeature()==null) {
        		alert('지도에 오류가 있습니다.');
        		return;
        	}

            this.removeFeatures('gisMechFclty', [this.getFeature()]);
        	break;
    }
};

var popupInfoModule = new GamMapPopupModule();
</script>
<input id="gisAssetsPrtAtCode" type="hidden" value="<c:out value='${fcltyCd.gisAssetsPrtAtCode }' />" />
<input id="gisAssetsCd" type="hidden" value="<c:out value='${fcltyCd.gisAssetsCd }' />" />
<input id="gisAssetsSubCd" type="hidden" value="<c:out value='${fcltyCd.gisAssetsSubCd }' />" />
<input id="gisPrtFcltyCd" type="hidden" value="<c:out value='${fcltyCd.gisPrtFcltyCd }' />" />
<input id="gisPrtFcltySeq" type="hidden" value="<c:out value='${fcltyCd.gisPrtFcltySeq }' />" />
<input id="fcltySe" type="hidden" value="<c:out value='${fcltyCd.fcltySe }' />" />
<input id="fcltsMngNo" type="hidden" value="<c:out value='${fcltyCd.fcltsMngNo }' />" />
<c:forEach var="imgfile" items="${fileList}">
	<input name="atchFileNmPhysicl" type="hidden" value="<c:out value='${imgfile.atchFileNmPhysicl }' />"/>
</c:forEach>
<c:if test="${resultCode!=0 }">
	<h2><c:out value="${resultMsg }" /></h2>
</c:if>
<c:if test="${resultCode==0 }">
	<c:if test="${fcltyCd==null }">
		<h2>시설정보가 없습니다.</h2>
		<c:if test="${fn:containsIgnoreCase(auth,'role_admin')||fn:containsIgnoreCase(auth,'role_manager') }">
			<button id="assignFeature">시설코드 지정</button>
			<button id="removeFeature">영역 삭제</button>
		</c:if>
	</c:if>
	<c:if test="${fcltyCd!=null }">
		<div class='prtFcltyInfo'>
			<h2>시설정보</h2>
			<table class='prtFcltyInfo'><tbody>
				<tr><th>시설명</th><td colspan="3"><c:out value="${fcltyCd.prtFcltyNm }" /></td></tr>
				<tr><th>시설분류</th><td colspan="3"><c:out value="${fcltyCd.prtFcltyCdNm }" /></td></tr>
				<tr><th>규격</th><td colspan="3"><c:out value="${fcltyCd.prtFcltyStndrd }" /></td></tr>
				<tr><th>연면적</th><td><fmt:formatNumber value="${fcltyCd.archAr }" maxIntegerDigits="3" maxFractionDigits="2" /> m²</td>
				<th>기초형식</th><td><c:out value="${fcltyCd.baseFmt }" /> m²</td></tr>
				<tr><th>준공일자</th><td colspan="3"><c:out value="${fcltyCd.bldDt }" /></td></tr>
				<c:if test="${fn:length(fileList)>0 }">
					<tr><td id="imgPanel" colspan="3"><img id="imgPreview" src="<c:url value='/cmm/getPfImage.do?physicalFileNm=${fileList[0].atchFileNmPhysicl }' />" style='width:300px;' /></td></tr>
				</c:if>
			</tbody></table>
			<c:if test="${fn:containsIgnoreCase(auth,'role_admin')||fn:containsIgnoreCase(auth,'role_manager') }">
				<button id="fcltyCdMngt" data-icon="ui-icon-newwin">기계시설제원 관리</button>
			</c:if>
			<button id="fcltyCdInqire" data-icon="ui-icon-newwin">기계시설제원 조회</button>
		</div>
	</c:if>
</c:if>
