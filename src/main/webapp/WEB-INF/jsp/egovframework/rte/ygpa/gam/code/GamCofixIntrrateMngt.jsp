<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamCofixIntrrateMngt.jsp
  * @Description : 코픽스 이자율 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.03.03  장은성          최초 생성
  *
  * author 장은성
  * since 2015.03.03
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamCofixMngtModule() {}

GamCofixMngtModule.prototype = new EmdModule(350, 400);

// 페이지가 호출 되었을때 호출 되는 함수
GamCofixMngtModule.prototype.loadComplete = function() {
	this._deleteItem=[];
	// 테이블 설정
	this.$("#cofixList").flexigrid({
		module: this,
		url: '/code/selectCofixIntrrateList.do',
		dataType: 'json',
		colModel : [
			{display:'공시일자', name:'annodt', width:150, sortable:true, align:'center', displayFormat:'cal'},
			{display:'신규취급액기준', name:'newManipAmtStdrIntrrate', width:150, sortable:true, align:'right', displayFormat:'input-number', displayOption:'000.00'}
			],
		height: 'auto',
		preProcess: function(module, data) {
			return data;
		}
	});

	this.$("#cofixList").on('onLoadDataComplete', function(event, module, data) {
		module._deleteitem=[];
	});

	this.$("#cofixList").on('onItemSelected', function(event, module, row, grid, param) {
		module._selectedItem=row;
	});

	this.$("#cofixList").on('onItemUnSelected', function(event, module, row, grid, param) {
		module._selectedItem=null;
	});

    this.$("#cofixList").on('onCellEdited', function(event, module, row, rid, cid, oldVal) {
    	/*2015.12.22 김종민 수정..*/
        if((cid == 'annodt') && (row._updtId=="I")) {
        	var dateStr = String(row['annodt']);
        	row['objYrmt'] = dateStr.substring(0,4)+dateStr.substring(5,7);
        	module.$("#cofixList").flexUpdateRow(rid, row);
        }  
        if(row._updtId!="I") row._updtId="U";
    });
};

GamCofixMngtModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnSearchData':
		this.loadData();

		break;
	case 'btnSave':
		// 변경된 자료를 저장한다.
		var inputVO=[];
 		inputVO[inputVO.length]={name: '_uList', value :JSON.stringify(this.$('#cofixList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
		inputVO[inputVO.length]={name: '_cList', value: JSON.stringify(this.$('#cofixList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
		inputVO[inputVO.length]={name: '_dList', value: JSON.stringify(this._deleteItem) };
		// 데이터를 저장 하고 난 뒤 리스트를 다시 로딩 한다.

	 	this.doAction('/code/updateCofixIntrrateList.do', inputVO, function(module, result) {
	 		if(result.resultCode == 0){
	 			module.$('#cofixList').flexReload();
	 		}
	 		alert(result.resultMsg);
 		});
		break;
	case 'btnAddItem':
		this.$('#cofixList').flexAddRow({_updtId:'I'});
		break;
	case 'btnDelItem':
		var rows=this.$('#cofixList').selectedRowIds();
		if(rows.length>0) {
			for(var k in rows) {
				this._deleteItem[this._deleteItem.length] = this.$('#cofixList').flexGetRow(rows[k]);
				this.$('#cofixList').flexRemoveRow(rows[k]);
			}
		}
		break;
	case 'btnPrev':
		var ret=this.$('#cofixList').selectPrevRow();
		if(ret==false) {
			alert('선택된 자료가 없거나 맨 처음 자료입니다.');
		}
		break;
	case 'btnNext':
		var ret=this.$('#cofixList').selectNextRow();
		if(ret==false) {
			alert('선택된 자료가 없거나 맨 끝 자료입니다.');
		}
		break;
	}
};

GamCofixMngtModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#searchCofixData');
 	this.$('#cofixList').flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamCofixMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId }'/>
<div class="window_main">
	<div class="emdPanel">
		<form id="searchCofixData">
			<table style="width:100%;" class="searchPanel">
				<tbody>
					<tr>
					<!-- 
						<th>대상년월</th>
						<td>
							<input id="objYrmt" type="text" size="4" />
						</td>
						<th>공시기간</th>
					 -->	
						<td>
							<input id="searchDtFrom" type="text" size="8" class="emdcal"/>~
							<input id="searchDtTo" type="text" size="8" class="emdcal"/>
						</td>
						<td><button id="btnSearchData" class="submit">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="emdPanel fillHeight">
		<table id="cofixList" style="display:none" class="fillHeight"></table>
			<div class="emdControlPanel">
			<table style="width:300px;" class="searchPanel">
				<tbody>
					<tr>
						<td><button id="btnAddItem">추가</button><button id="btnDelItem">삭제</button><button id="btnSave">저장</button><!-- <button id="btnPrev">이전</button><button id="btnNext">다음</button> --></td>
					</tr>
				</tbody>
			</table>
			</div>
	</div>
</div>
