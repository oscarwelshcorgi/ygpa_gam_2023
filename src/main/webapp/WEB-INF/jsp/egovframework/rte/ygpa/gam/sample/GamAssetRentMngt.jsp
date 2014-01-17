<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetRentMngt.jsp
  * @Description : 자산임대관리 테스트 (Prototype)
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
function GamAssetCodeModule() {}

GamAssetCodeModule.prototype = new EmdModule(800,500);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetCodeModule.prototype.loadComplete = function() {
	// 테이블 설정
	this.$("#assetCodeList").flexigrid({
		module: this,
		url: '<c:url value="/code/mngt/selectAssetCodeList.do"/>',
		dataType: 'json',
		colModel : [
			{display:'항코드', name:'PRT_AT_CODE',width:60, sortable:false,align:'center'},
			{display:'관리 코드', name:'MNG_Code',width:100, sortable:false,align:'center'},
			{display:'부서 구분코드', name:'ASSETS_RENT_DEPT_SE_CD',width:100, sortable:false,align:'center'},
			{display:'신청 업체코드',name:'MNG_CNT',width:100,sortable:false,align:'center'},
			{display:'신청 일자',name:'MNG_CNT',width:100,sortable:false,align:'center'},
			{display:'신청 구분코드',name:'MNG_CNT',width:100,sortable:false,align:'center'},
			{display:'총 면적',name:'MNG_CNT',width:100,sortable:false,align:'center'},
			{display:'총 사용료',name:'MNG_CNT',width:100,sortable:false,align:'center'},
			{display:'사용기간 From', name:'GR_USAGE_PD_FROM',width:100, sortable:false,align:'center'},
			{display:'사용기간 To', name:'GR_USAGE_PD_TO',width:100, sortable:false,align:'center'},
			{display:'고지 방법',name:'MNG_CNT',width:100,sortable:false,align:'center'},
			{display:'최초 허가일자',name:'MNG_CNT',width:100,sortable:false,align:'center'},
			{display:'허가 일자',name:'MNG_CNT',width:100,sortable:false,align:'center'},
			{display:'허가 여부',name:'MNG_CNT',width:100,sortable:false,align:'center'},
			{display:'등록자',name:'REG_USR',width:100,sortable:false,align:'center'},
			{display:'등록 일시',name:'REGIST_DT',width:100,sortable:false,align:'center'},
			{display:'수정자',name:'UPD_USR',width:100,sortable:false,align:'center'},
			{display:'수정 일시',name:'UPDT_DT',width:100,sortable:false,align:'center'},
			{display:'비고 ',name:'RM',width:200,sortable:false,align:'center'}
			],
/* 		searchitems : [
			{display: 'ISO', name : 'iso'},
			{display: 'Name', name : 'name', isdefault: true}
			],
		sortname: "iso",
		sortorder: "asc",
		 */
		usepager: false,
//		title: '자산목록',
//		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
//		width: '100%',
//		height: '100%'
/* 		onSubmit: addFormData, */
	});
};

// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
GamAssetCodeModule.prototype.showModuleAlert = function(msg) {
	this.$('#prtCode').val(msg);
};

GamAssetCodeModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'submitButton':
		throw 0;
		break;
	case 'popupFcltyCd':	// 팝업을 호출한다.
		var opts = {
			'gisAssetsPrtAtCode': this.$('#prtAtCode').val(),
			'gisAssetsCd': this.$('#gisAssetsCd').val(),
			'gisAssetsSubCd': this.$('#gisAssetsSubCd').val()
		};
		this.doExecuteDialog('selectAssetsPopup', '시설 선택', '<c:url value="/popup/showAssetsCd.do"/>', opts);
		break;
	case 'addAssetCd':
		break;
	case 'removeAssetCd':
		break;
	case 'editAssetCd':
		break;
	}
};

// 팝업이 종료 될때 리턴 값이 오출 된다.
// popupId : 팝업 대화상자 아이디
// msg : 팝업에서 전송한 메시지 (취소는 cancel)
// value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamAssetCodeModule.prototype.onClosePopup = function(popupId, msg, value)
	{
		switch (popupId) {
		case 'selectAssetsPopup':
			if (msg != 'cancel') {
				this.$('#prtAtCode').val(value.gisAssetsPrtAtCode);
				this.$('#gisAssetsCd').val(value.gisAssetsCd);
				this.$('#gisAssetsSubCd').val(value.gisAssetsSubCd);
				alert(value);
			} else {
				alert('취소 되었습니다');
			}
			break;
		default:
			alert('알수없는 팝업 이벤트가 호출 되었습니다.');
			throw 0;
			break;
		}
};

	GamAssetCodeModule.prototype.onSubmit = function() {
		this.loadData();
	};

	GamAssetCodeModule.prototype.loadData = function() {
		var searchOpt = this.makeFormArgs('#searchForm');
		this.$('#assetCodeList').flexOptions({
			params : searchOpt
		}).flexReload();
	};

	// 다음 변수는 고정 적으로 정의 해야 함
	var module_instance = new GamAssetCodeModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId }'/>
<div class="window_main">
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>청코드</th>
							<td><input id="prtAtCode" type="text" size="3"></td>
							<th>취득일자</th>
							<td><input id="acqDateFrom" type="text" class="emdcal"
								size="8"> ~ <input id="acqDateTo" type="text"
								class="emdcal" size="8"></td>
							<th>자산코드</th>
							<td><input id="gisAssetsCd" type="text" size="6">-<input
								id="gisAssetsSubCd" type="text" size="6"><button id="popupFcltyCd" class="popupButton">시설조회</button></td>
							<th>재산코드</th>
							<td><select id="prprtyCd">
									<option value="" selected="selected">선택</option>
									<option value="A">건물</option>
									<option value="L">토지</option>
									<option value="S">공작물</option>
									<option value="w">창고</option>
									<option value="E">기타</option>
							</select></td>
							<td rowSpan="2"><button id="submitButton">조회</button></td>
						</tr>
						<tr>
							<th>자산명</th>
							<td colspan="3"><input id="assetNm" type="text" size="36"></td>
							<th>관리부서</th>
							<td><select id="mngDeptCd"></select></td>
							<th>운영부서</th>
							<td><select id="operDeptCd"></select></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel">
		<div id='' class="emdTabPanel">
			<ul>
				<li><a href="#tabs1" class="emdTab">자산임대 관리</a></li>
				<li><a href="#tabs2" class="emdTab">자산상세</a></li>
				<li><a href="#tabs3" class="emdTab">자산사진</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="assetCodeList" style="display:none"></table>
				<div class="emdControlPanel"><button id="addAssetCd">자산추가</button><button id="removeAssetCd">삭제</button><button id="editAssetCd">편집</button><button id="toExcel">엑셀(XLS)</button><button id="loadMap">지도보기</button></div>
			</div>
			<div id="tabs2" class="emdTabPage">
				<table>
					<tr>
						<th><span class="label">청코드</span></th>
						<td><select>
								<option selected="selected">620</option>
								<option>621</option>
								<option>622</option>
						</select></td>
					</tr>
					<tr>
						<th><span class="label">자산코드</span></th>
						<td><input type="text" size="8"></td>
					</tr>
					<tr>
						<th><span class="label">등록일</span></th>
						<td><input type="text" class="emdcal" size="8"></td>
					</tr>
					<tr>
						<th><span class="label">사용업체</span></th>
						<td><input type="text" size="8"></td>
					</tr>
					<tr>
						<th><span class="label">지도</span></th>
						<td><input type="file" style="margin-left: 5px;"
							multiple="multiple"></td>
					</tr>
					<tr>
						<th><span class="label">기타</span></th>
						<td><input type="text" size="50;"></td>
					</tr>
				</table>
				<div style="vertical-align: bottom; text-align: right;">
					<input type="reset" value="취소" class="input_1"> <input
						type="submit" value="저장" class="input_1">
				</div>
			</div>
			<div id="tabs3" class="emdTabPage">탭탭탭</div>

		</div>
	</div>
</div>
