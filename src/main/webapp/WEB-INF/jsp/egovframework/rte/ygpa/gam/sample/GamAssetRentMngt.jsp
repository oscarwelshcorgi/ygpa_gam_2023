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

GamAssetCodeModule.prototype = new EmdModule(800,600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetCodeModule.prototype.loadComplete = function() {
	// 테이블 설정
	this.$('#assetRentList').flexigrid({
		module: this,
		url: '<c:url value="/asset/rent/gamSelectAssetRentList.do"/>',
		dataType: 'json',
		colModel : [
			{display:'항코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
			{display:'항구분', name:'prtAtCodeNm',width:60, sortable:false,align:'center'},
			{display:'관리코드', name:'mngNumber',width:110, sortable:false,align:'center'},
			{display:'신청 업체코드',name:'entrpscd',width:100,sortable:false,align:'center'},
			{display:'업체명',name:'entrpsNm',width:120,sortable:false,align:'center'},
			{display:'부두명',name:'quayNm',width:120,sortable:false,align:'center'},
			{display:'신청 일자',name:'reqstDt',width:100,sortable:false,align:'center'},
			{display:'신청구분',name:'reqstSeCdNm',width:100,sortable:false,align:'center'},
			{display:'결재상태',name:'sanctnSttusNm',width:100,sortable:false,align:'center'},
			{display:'총 면적',name:'grAr',width:100,sortable:false,align:'center'},
			{display:'총 사용료',name:'grFee',width:100,sortable:false,align:'center'},
			{display:'감면 사용료',name:'grRdcxptFee',width:100,sortable:false,align:'center'},
			{display:'사용기간 From', name:'grUsagePdFrom',width:100, sortable:false,align:'center'},
			{display:'사용기간 To', name:'grUsagePdTo',width:100, sortable:false,align:'center'},
			{display:'고지 방법',name:'nticMth',width:100,sortable:false,align:'center'},
			{display:'허가 일자',name:'prmisnDt',width:100,sortable:false,align:'center'},
			{display:'허가 여부',name:'prmisnYn',width:100,sortable:false,align:'center'},
			{display:'결재일시',name:'sanctnDt',width:100,sortable:false,align:'center'}
			],
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				this.mngNumber = this.mngYear+'-'+this.mngNo+'-'+this.mngCnt;	// 관리 코드를 지정
				this.quayNm = '일반부두';	// 부두명조회 하지 않아서 임의로 부두명을 지정 함
				switch(this.sanctnSttus) {	// 결재상태를 정의한다. 조회 시 공통코드를 참조 할 수 있으나 이렇게도 처리가 가능함
				case '0':
					this.sanctnSttusNm='반송';
					break;
				case '1':
					this.sanctnSttusNm='결재완료';
					break;
				case '2':
					this.sanctnSttusNm='결재요청중';
					break;
				case '5':
					this.sanctnSttusNm='상신';
					break;
				default:
					this.sanctnSttusNm='';
				}
			});
			return data;
		}
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
		var searchOpt=this.makeFormArgs('#searchForm');
	 	this.$('#assetRentList').flexOptions({params:searchOpt}).flexReload();
		
		break;
	case 'btnNewRequest':
		break;
	case 'btnExtRequest':
		break;
	case 'btnDelRequest':
		break;
	case 'btnViewMap':
		break;
	case 'clearForm1':
		this.$('#editForm :input').val("");
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
	case 'btnUseConfirm':	// 사용승낙 테스트
		if(this.$('#assetRentList').selectedRowCount()>0) {
				var rows = this.$('#assetRentList').selectedRows()[0];
				rows['chrgeKnd']='K2';	// 테스트 요금 코드
			this.doAction('<c:url value="/sample/asset/gamAssetRentUsePerm.do" />', rows, function(module, result) {
	            if(result.resultCode=='0') {
	                var searchOpt=module.makeFormArgs('#searchForm');
	                module.$('#assetRentList').flexOptions({params:searchOpt}).flexReload();
	            }
	            alert(result.resultMsg);
	        });
		}
		break;
	case 'btnCancelConfirm':
		if(this.$('#assetRentList').selectedRowCount()>0) {
			var rows = this.$('#assetRentList').selectedRows()[0];
			this.doAction('<c:url value="/sample/asset/gamAssetRentCancelUsePerm.do" />', rows, function(module, result) {
	            if(result.resultCode=='0') {
	                var searchOpt=module.makeFormArgs('#searchForm');
	                module.$('#assetRentList').flexOptions({params:searchOpt}).flexReload();
	            }
	            alert(result.resultMsg);
	        });
		}
	break;
	case 'removeAssetCd':
		break;
	case 'editAssetCd':
		break;
	case 'btnEApproval':	// 전자결재 테스트
		if(this.$('#assetRentList').selectedRowCount()>0) {
			var rows = this.$('#assetRentList').selectedRows()[0];
			var opts = {
					type: 'ARUC',
		            prtAtCode: rows['prtAtCode'],
		            mngYear: rows['mngYear'],
		            mngNo: rows['mngNo'],
		            mngCnt: rows['mngCnt']
			};
			this.requestEApproval(opts, function(){alert('결재완료');});
		}
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
			
			break;
		}
};

	GamAssetCodeModule.prototype.onSubmit = function() {
		this.loadData();
	};

	GamAssetCodeModule.prototype.loadData = function() {
		var searchOpt = this.makeFormArgs('#searchForm');
		this.$('#assetRentList').flexOptions({
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
							<td rowSpan="2"><button id="submitButton" class="buttonSearch">조회</button></td>
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

	<div class="emdPanel fillHeight">
		<div class="emdTabPanel fillHeight">
			<ul>
				<li><a href="#tabs1" class="emdTab">자산임대 관리</a></li>
				<li><a href="#tabs2" class="emdTab">자산상세</a></li>
				<li><a href="#tabs3" class="emdTab">자산사진</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="assetRentList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnNewRequest">최초신청</button>
					<button id="btnExtRequest">연장신청</button>
					<button id="btnDelRequest">신청삭제</button>
					<button id="btnEApproval">결재요청</button>
					<button id="btnUseConfirm">사용승낙</button>
					<button id="btnCancelConfirm">승낙취소</button>
					<button id="btnViewMap">맵조회</button>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage">
				<form id="editForm">
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
					<button id="clearForm1" class="cancelButton">취소</button>
					<button id="saveForm1" class="submit" >저장</button>
				</div>
				</form>
			</div>
			<div id="tabs3" class="emdTabPage">탭탭탭</div>

		</div>
	</div>
</div>
