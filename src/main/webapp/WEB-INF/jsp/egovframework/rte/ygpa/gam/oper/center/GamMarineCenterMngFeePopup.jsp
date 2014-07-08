<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamMarineCenterMngFee.jsp
  * @Description : 관리비 입력 팝업
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.07.07  장은성          최초 생성
  *
  * author 장은성
  * since 2014.07.07
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamMarineCenterMngFeePopupModule() {}

GamMarineCenterMngFeePopupModule.prototype = new EmdPopupModule(800, 460);

// 팝업이 호출 되었을때 호출 되는 함수
GamMarineCenterMngFeePopupModule.prototype.loadComplete = function(searchOpt) {
	this.resizable(true);
	var mngFeeStr = this.$('#mngFee').val();

	if(mngFeeStr.indexOf("|")>0) {
		var mngFeeVal = mngFeeStr.split("|");

		this.$('#mngFee1').val(Number(mngFeeVal[0]));
		this.$('#mngFee2').val(Number(mngFeeVal[1]));
		this.$('#mngFee3').val(Number(mngFeeVal[2]));
		this.$('#mngFee4').val(Number(mngFeeVal[3]));
		this.$('#mngFee5').val(Number(mngFeeVal[4]));
	}

	this.calcMngFee();

	this.calcMngFeeTotal();

	this.$('.mngFee').on('keyup', {module: this}, function(e) {
		e.data.module.calcMngFeeTotal();
	});

	this.$('.calcMngFee').on('keyup', {module: this}, function(e) {
		e.data.module.calcMngFee();
	});

	if(this.$('#mngFeeSj').val().length==0) {
		var mngFeeTitle='';
		var year = this.$('#mngFeeYrmt').val();
		var mt = year.substring(4, 6);
		var year = year.substring(0, 4);
		mngFeeTitle = year+' 년도  '+ mt+ ' 월분 관리비';
		this.$('#mngFeeSj').val(mngFeeTitle);
	}

	this.$('.calcMngFee').css('width', '80px');
	this.$('.mngFee').css('width', '80px');
//	console.log("popup loadcomplete");
};

GamMarineCenterMngFeePopupModule.prototype.calcMngFee = function() {
	var mngFeeAmount = 0;

	console.log("calc mngFee");

	$.each(this.$('.calcMngFee'), function() {
		mngFeeAmount+=Number($(this).val().replace(",", ""));
	});

	this.$('#mngFeeAmount').val($.number(mngFeeAmount));
	var usageAr = Number(this.$('#usageAr').val().replace(",", ""));
	this.$('#facilMngFee').val($.number(mngFeeAmount * usageAr / 18237));	// 개별 관리비
};

GamMarineCenterMngFeePopupModule.prototype.calcMngFeeTotal = function() {
	var mngFeeTotalAmount = 0;

	console.log("calc calcMngFeeTotal");

	$.each(this.$('.mngFee'), function() {
		mngFeeTotalAmount+=Number($(this).val().replace(",", ""));
	});

	this.$('#mngTotalFee').val($.number(mngFeeTotalAmount));	// 관리비 합계
};

// 사용자 설정 함수 추가
GamMarineCenterMngFeePopupModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnSaveMngFee':	// 관리비 저장
		var mngFee = this.$('#mngFee1').val().replace(',','') + '|'+this.$('#mngFee2').val().replace(',','') + '|'+this.$('#mngFee3').val().replace(',','') + '|'+this.$('#mngFee4').val().replace(',','') + '|'+this.$('#mngFee5').val().replace(',','');
		this.$('#mngFee').val(mngFee);
		var gamMarineCenterNticMngFeeVO=this.makeFormArgs("#gamMarineCenterNticMngFeeVO");

         this.doAction('<c:url value="/oper/center/updateMarineCenterMngFee.do" />', gamMarineCenterNticMngFeeVO, function(module, result) {
            if(result.resultCode=='0') {
        		module.closeDialog('result', 'ok');
            }
        });
		break;
	case "btnCancel":
		this.cancelDialog();
		break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamMarineCenterMngFeePopupModule();
</script>
<div class="dialog fillHeight">
	<div class="emdPanel fillHeight">
		<div class="emdPanel" class="fillHeight" style="vertical-align:middle;">
			<form id="gamMarineCenterNticMngFeeVO">
				<input id="prtAtCode" type="hidden" value="${mngFeeInfo.prtAtCode}" />
				<input id="mngYear" type="hidden" value="${mngFeeInfo.mngYear}" />
				<input id="mngNo" type="hidden" value="${mngFeeInfo.mngNo}" />
				<input id="mngCnt" type="hidden" value="${mngFeeInfo.mngCnt}" />
				<input id="nticCnt" type="hidden" value="${mngFeeInfo.nticCnt}" />
				<input id="chrgeKnd" type="hidden" value="${mngFeeInfo.chrgeKnd}" />
				<input id="mngFee" type="hidden" value="${mngFee.mngFee}" />
				<input id="mngFeeYrmt" type="hidden" value="${mngFeeInfo.mngFeeYrmt}" />
				<h2>관리비 내역</h2>
				<table class="editForm">
					<tbody>
	                        <tr>
	                            <th style="width: 70px">업체명</th>
	                            <td><input id="entrpscd" type="text" size="5" value="${mngFeeInfo.entrpscd}"><input id="entrpsNm" type="text" size="40" value="${mngFeeInfo.entrpsNm }"></td>
	                        </tr>
							<tr>
								<th>사용시설명</th>
								<td><input id="gisAssetNm" value="${mngFeeInfo.gisAssetsNm }"/></td>
	                        </tr>
							<tr>
								<th>사용면적</th>
								<td><input id="usageAr" value="${mngFeeInfo.grAr }"/></td>
	                        </tr>
							<tr>
	                            <th style="width: 70px">관리비 제목</th>
	                            <td><input id="mngFeeSj" type="text" size="50" value="${mngFee.mngFeeSj}"></td>
	                        </tr>
	                        <tr>
	                            <th>시설관리용역</th>
	                            <td><input id="mngFee1" class="ygpaNumber calcMngFee" /> 원</td>
	                        </tr>
	                        <tr>
	                            <th>방역소독/저수조</th>
	                            <td><input id="mngFee2" class="ygpaNumber calcMngFee" /> 원</td>
	                        </tr>
	                        <tr>
	                            <th>전기요금</th>
	                            <td><input id="mngFee3" class="ygpaNumber calcMngFee" /> 원</td>
	                        </tr>
	                        <tr>
	                            <th>도시가스</th>
	                            <td><input id="mngFee4" class="ygpaNumber calcMngFee" /> 원</td>
	                        </tr>
	                        <tr>
	                            <th>환경개선 부담금</th>
	                            <td><input id="mngFee5" class="ygpaNumber calcMngFee" /> 원</td>
	                        </tr>
	                        <tr>
								<th>시설유지 관리비 합계</th>
								<td><input id="mngFeeAmount" class="ygpaNumber" readonly="readonly" /></td>
	                        </tr>
					</tbody>
				</table>
				<h2>월 관리비</h2>
				<table class="editForm">
					<tbody>
	                        <tr>
	                        <tr>
	                            <th>관리비</th>
	                            <td><input id="facilMngFee" class="ygpaNumber mngFee" value="${mngFeeData.facilMngFee }"/> 원</td>
	                            <th>전기요금</th>
	                            <td><input id="elctyFee" class="ygpaNumber mngFee" value="${mngFeeData.elctyFee }"/> 원</td>
	                            <th>상하수도</th>
	                            <td><input id="waterFee" class="ygpaNumber mngFee" value="${mngFeeData.waterFee }"/> 원</td>
	                            <th>도시가스</th>
	                            <td><input id="gasFee" class="ygpaNumber mngFee" value="${mngFeeData.gasFee }"/> 원</td>
	                        </tr>
	                        <tr>
	                            <th>합  계</th>
	                            <td style="align:right" colspan="9"><input id="mngTotalFee" class="ygpaNumber" value="${mngFeeData.mngTotalFee }"/> 원</td>
	                        </tr>
					</tbody>
				</table>
			</form>
		</div>
		<div class="emdControlPanel" style="vertical-align:middle;">
			<button id="btnSaveMngFee" data-icon="ui-icon-info">저장</button>
			<button id="btnCancel" class="cancelButton">취소</button>
		</div>
	</div>
</div>