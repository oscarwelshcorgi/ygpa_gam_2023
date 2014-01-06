﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿function gfn_Init()
{
	gfn_ResizeInit(global.window.width,global.window.height);
	if ( G_IsBrowser == "N" ) {
		YGPA_SES = getReg("GlobalVal");
		JSESSIONID = getReg("GlobalVal");
		
		YGAM_SES = getReg("GlobalVal");
	}
	alert("YGPA_SES="+YGPA_SES +":JSESSIONID="+JSESSIONID);
}
 
function gfn_Fin()
{
	YGPA_SES = null;
	JSESSIONID = null;
	
	YGAM_SES = null;
	
	CloseSession();
}



function fnSetVoInfo(objFrm, arrayInputDs)
{
		gdsVoInfo.clearData();
		var row = 0;
		for( var i=0; i<length(arrayInputDs); i++){
			row = gdsVoInfo.addrow();
			gdsVoInfo.setcolumn(row, "InputDs", arrayInputDs[i][0]);
			gdsVoInfo.setcolumn(row, "voClass", arrayInputDs[i][2]);
		}
}

function fnCmSyncTr(objFrm, svcid, strController, arrayInputDs, outputDs, params, callbackFnc)
{
	// inputDs :서버, 클라이언트, VO
	// outputDs :클라이언트, 서버
	//	var arrayInputDs = [
	//					["ds_input","ds_input","egovframework.com.cmm.ComDefaultVO"]
	//					,["ds_input_two","ds_input2","egovframework.com.ipa.cmm.ISComDefaultVO"]
	//				   ];
	//  var strOutputDs = "ds_output=ds_output ds_output2=ds_outputpage";
	fnSetVoInfo(objFrm, arrayInputDs);
	
	var row = 0;
	var inputDs = "";
	
	if ( callbackFnc == null || callbackFnc == "" ) callbackFnc = "fnCallback";
	
	for( var i=0; i<length(arrayInputDs); i++){
		inputDs += arrayInputDs[i][0] +"=" + arrayInputDs[i][1] + " ";
	}
	//strParam = "voClass='egovframework.rte.sample.service.SampleVO'";
	http.Sync = true;
	transaction(svcid, 
				"svc::" + G_CtxRoot + strController, 
				"ds_voInfo=gdsVoInfo " + inputDs, 
				outputDs,
				params,
				callbackFnc);
	http.Sync = false;			
				 
}

function fnCmTr(objFrm, svcid, strController, arrayInputDs, outputDs, params, callbackFnc)
{
	// inputDs :서버, 클라이언트, VO
	// outputDs :클라이언트, 서버
	//	var arrayInputDs = [
	//					["ds_input","ds_input","egovframework.com.cmm.ComDefaultVO"]
	//					,["ds_input_two","ds_input2","egovframework.com.ipa.cmm.ISComDefaultVO"]
	//				   ];
	//  var strOutputDs = "ds_output=ds_output ds_output2=ds_outputpage";
	fnSetVoInfo(objFrm, arrayInputDs);
	
	var row = 0;
	var inputDs = "";
	
	if ( callbackFnc == null || callbackFnc == "" ) callbackFnc = "fnCallback";
	
	for( var i=0; i<length(arrayInputDs); i++){
		inputDs += arrayInputDs[i][0] +"=" + arrayInputDs[i][1] + " ";
	}
	//strParam = "voClass='egovframework.rte.sample.service.SampleVO'";
	//http.Sync = true;
	transaction(svcid, 
				"svc::" + G_CtxRoot + strController, 
				"ds_voInfo=gdsVoInfo " + inputDs, 
				outputDs,
				params,
				callbackFnc);
	//http.Sync = false;			
				 
}
function gfn_showProg(objFrm,sMsg)
{
//alert(GetDeviceInfo("CYScreen")+"/"+GetDeviceInfo("CXScreen"));
//	var lv_left = (GetDeviceInfo("CXScreen")-260)/2 - (this.width-264)/2;
//	var lv_top  = (GetDeviceInfo("CYScreen")-140)/2 - (this.height-140)/2;
	var lv_left = (this.width-264)/2;
	var lv_top  = (this.height-140)/2;
	var lv_swf  = G_Server + "/images/egovframework/ipa/cmm/ing.gif";
	var lv_cts  = "";
	var obj = object(objFrm.id+".divProg");
	
	lv_cts  = "<Contents>\n";
	lv_cts += "		<Image Height='100' Id='imgProg' ImageID='ing_top' TabStop='false' Width='260'></Image>\n";
	lv_cts += "		<Image Height='34' Id='imgFlash' ImageID='" + lv_swf + "' TabStop='false' Top='102' Width='260'></Image>\n";
	lv_cts += "		<Static Align='Center' Color='user20' Font='돋움,9,Bold' Height='23' Id='lbl_msg' Left='94' Text='" + sMsg + "' ";
	lv_cts += "Top='43' VAlign='Middle' Width='133'></Static>\n";
	lv_cts += "</Contents>";
//alert(isValidObject(obj));
	if(!isValidObject(obj)){
		objFrm.Create("Div","divProg","Border='Flat' BorderColor='user5' Height='140' Left='" + lv_left + "' Top='" + lv_top + "' Width='264'");
		divProg.Contents = lv_cts;
	}
}
function gfn_hideProg(objFrm)
{
	var obj = object(objFrm.id+".divProg");
	if(isValidObject(obj)){
		objFrm.Destroy("divProg");
	}
}
/*********************************************************************
 * 기능 : null check
 * Parameters : strVal
 * Returns : true/false
 * 예제 : gfn_IsNull(edt_temp.Text);
 *********************************************************************/
function gfn_IsNull(strVal)
{
	if(strVal == null || length(toString(trim(strVal))) == 0)
		return true;
	else
		return false;
}
/*********************************************************************
 * 기능 	  : Dataset CleadData
 * Parameters : dsObj		: Dataset Name
 * optAdd     : AddRow 여부
 * atrP       : 채워넣을 문자
 * Returns    : 
 * 예제 : gfn_ClearData(dsObj,optAdd,strP);
 *********************************************************************/
function gfn_ClearData(dsObj,optAdd,strP)
{
	dsObj.ClearData();
	var v_colType;
	if(optAdd)
	{
		dsObj.AddRow();
		for(var i=0;i<dsObj.ColCount;i++)
		{
			v_colType = toUpper(dsobj.GetColType(dsObj.GetColID(dsObj.GetColIDXbyorder(i))));
			if(strP <> null || length(strP) > 0)
			{
				if(v_colType == "STRING"){
					dsObj.SetColumn(0,dsObj.GetColID(dsObj.GetColIDXbyorder(i)),strP);
				}else{
					dsObj.SetColumn(0,dsObj.GetColID(dsObj.GetColIDXbyorder(i)),toNumber(strP));
				}
			}
			else
			{
				if(v_colType == "STRING"){
					dsObj.SetColumn(0,dsObj.GetColID(dsObj.GetColIDXbyorder(i)),"");
				}else{
					dsObj.SetColumn(0,dsObj.GetColID(dsObj.GetColIDXbyorder(i)),0);
				}
			}
		}
	}
}
/***********************************************************************
 * 기능       : Field의 값에 대한 Null Check를 한다.
 * Parameters : controlID   - String
 *              controlName - String
 * Returns    : Boolean (성공:true, 실패:false)
 * 예제       : gfn_CheckField("edt_temp1|edt_temp2","예제1|예제2")
 *********************************************************************/
function gfn_CheckField(controlID, controlNM)
{
	//controlID占쏙옙 controlNM: 짝占쏙옙 占승아억옙 占싼댐옙,
	var arrID = split(controlID,"|");
	var arrNM = split(controlNM,"|");
	
	var objID;

	for(var i=0; i<length(arrID); i++)
	{
		objID = object(trim(arrID[i]));
		if(gfn_IsNull(objID.value))
		{
			alert("필수항목입니다. [" + trim(arrNM[i]) + "] 를(을) 입력하세요.");
			objID.setFocus();
			return false;
		}
	}
	return true;
}

/*********************************************************************
 * 기능 : 그리드 Sorting(클릭한 셀에대한 소팅처리 후, 그리드 헤드에 Sort Mark 세팅)
 * Parameters : obj		: 그리드 Object
 *				nCell	: 소팅할 셀( -1 : 정렬후 검색버튼을 눌러 헤더 초기화할때 활용 )
 * Returns :
 * 사용법 : 그리드 헤드 클릭 또는 더블클릭 이벤트 or 기타 Method에서 호출
 * 예제 : gfn_GridSort(obj, nCell);
 * 		  gfn_GridSort(obj, -1);
 ********************************************************************
// Grid 정렬  Method
function gfn_GridSort(obj, nCell) {
	var strSortType = "";
	if ( nCell>=0 ) strSortType = obj.GetCellProp("head", nCell, "text");
	var ds_obj = object(obj.BindDataset);
	
	strSortType = right(strSortType, 1);
	
	for (var i=0; i<obj.GetCellCount("head"); i++) {
		if ( nCell < 0 ) { // header init
			obj.SetCellProp("head", i, "text", replace(obj.GetCellProp("head", i, "text"), "▼"));
			obj.SetCellProp("head", i, "text", replace(obj.GetCellProp("head", i, "text"), "▲"));
		} else {
			obj.SetCellProp("head", i, "text", replace(replace(obj.GetCellProp("head", i, "text"), "▲"), "▼"));
		}
	}
	
	if ( nCell < 0  ) return;
	
	if ( strSortType == "▲") {
		obj.SetCellProp("head", nCell, "text", obj.GetCellProp("head", nCell, "text")+"▼");
		ds_obj.Sort(obj.GetCellProp("body", nCell, "colid")+":D");
	} else {
		obj.SetCellProp("head", nCell, "text", obj.GetCellProp("head", nCell, "text")+"▲");
		ds_obj.Sort(obj.GetCellProp("body", nCell, "colid")+":A");
	}
}
*/

/*********************************************************************
 * 기능 : head cell index 로 body cell index를 찾아온다.
 * Parameters : objGrid		Grid Component
 *				nCell		Head Cell Index
 *				strBand		body 또는 summary
 * Returns :	number		head cell 매핑되는 body cell
 * 예제 : grd_fn_GetHeadToBodyCell(objGrid,5,"body")
 *********************************************************************/
function grd_fn_GetHeadToBodyCell(objGrid,nCell,strBand)
{
	if(length(strBand) == 0) strBand = "body";

	var nBodyCell = objGrid.GetCellProp("head",nCell,"col");

	for(var nCellIdx = 0; nCellIdx < objGrid.GetCellCount(strBand); nCellIdx++) {
		if(nBodyCell == objGrid.GetCellProp(strBand,nCellIdx,"col")) {
			nBodyCell = nCellIdx;
			nCellIdx = objGrid.GetCellCount(strBand);
		}
	}

	return nBodyCell;
}
/*********************************************************************
 * 기능 : 숫자를 3자리씩 끊어 콤마를 삽입한다.
 * Parameters : 
 *				sValue - Input String
 * Returns :	String - 콤마 삽입된 문자열
 * 예제 : gfn_setComma("123456");
 *********************************************************************/
function gfn_setComma(sValue)
{
	var tmp = trim(replace(sValue,","));
	var lgt = length(tmp);
	if(lgt > 3)
	{
		var v_arrCnt;
		if(lgt%3 == 0)	v_arrCnt = lgt/3;
		else	v_arrCnt = truncate(lgt / 3)+1;
		var v_arrTmp = array(v_arrCnt);
		for(var i=v_arrCnt; i>0; i--)
		{
			v_arrTmp[i] = right(tmp,3);
			tmp = left(tmp,length(tmp)-3);
		}
		tmp = "";
		for(var i=0; i<v_arrCnt; i++)
		{
			tmp += v_arrTmp[i+1] + ",";
		}
		tmp = left(tmp,length(tmp)-1);
		return tmp;
	}
	else
		return tmp;
}
/*********************************************************************
 * 기능       : 긴 문자열 지정된 갯수만큼 보이고 .. 처리
 * Parameters : 
 *				strVal - 값
 *				intNum - 자리수
 * Returns 	  : ...추가된 문자열
 * 예제       : gfn_PointString("가나다라asdasd",10);
 *********************************************************************/
function gfn_PointString(strVal,intNum)
{
	if(isdigit(intNum))
	{
		alert("숫자 타입이 아닙니다.");
		return "";
	}
	atrVal = trim(strVal);
	if(lengthb(strVal) <= intNum)	return strVal;
	var lv_ret = midb(strVal,0,intNum) + "...";
	return lv_ret;
}
/*********************************************************************
 * 占쏙옙占?      : Data Preview
 * Parameters : 
 *				ds_name  - 占쏙옙占쏙옙占싶셋몌옙
 * Returns 	  : 
 * 占쏙옙f       : gfn_debug("ds_tmp");
 *********************************************************************/
function gfn_debug(ds_name)
{
	Dialog("MAIN::Debug.xml","DS_NAME='"+ds_name+"'",710,420);
}
/*********************************************************************
 * 기능       : 주민등록번호 유효성 체크
 * Parameters : 
 *              strNo	- 주민번호
 * Returns    :	true/false
 * 예제       : gfn_CheckJumin("12345612345678")
 ********************************************************************/
function gfn_CheckJumin(strNo)
{
	var cBit = 0;
	var sCode="234567892345";

	strNo = replace(strNo,"-","");
	if(!isdigit(strNo))
	{
		alert("숫자형식이 아닙니다.");
		return false;
	}
	if(length(strNo) != 13){
		gfn_Message("56107");
		return false;
	}
	
	for(var i = 0; i < 12; i++) {
		cBit = cBit + toNumber(substr(strNo,i,1)) * toNumber(substr(sCode,i,1));
	}

	cBit = 11-(cBit%11);
	cBit = cBit%10;

	if(toNumber(substr(strNo,12,1)) == cBit) {
		return true;
	} else {

		gfn_Message("56107");
		return false;
//		return true;
	}
}
/*********************************************************************
 * 기능       : 법인번호 유효성 체크
 * Parameters : 
 *              strCorpNo	- 법인번호
 * Returns    :	true/false
 * 예제       : gfn_CheckCorpNo("12345612345678")
 ********************************************************************/
function gfn_CheckCorpNo(strCorpNo)
{
	var lv_CorpNo,No_Chk;
	
	lv_CorpNo = replace(strCorpNo, " ", "");
	lv_CorpNo = replace(lv_CorpNo, "-", "");
	lv_CorpNo = replace(lv_CorpNo, "/", "");
	
	if (length(lv_CorpNo) != 13){
		gfn_Message("56105");
		return false;
	}
	else{
		No_Chk = No_Chk + toNumber(mid( lv_CorpNo, 0, 1 )) * 1;
		No_Chk = No_Chk + toNumber(mid( lv_CorpNo, 1, 1 )) * 2;
		No_Chk = No_Chk + toNumber(mid( lv_CorpNo, 2, 1 )) * 1;
		No_Chk = No_Chk + toNumber(mid( lv_CorpNo, 3, 1 )) * 2;
		No_Chk = No_Chk + toNumber(mid( lv_CorpNo, 4, 1 )) * 1;
		No_Chk = No_Chk + toNumber(mid( lv_CorpNo, 5, 1 )) * 2;
		No_Chk = No_Chk + toNumber(mid( lv_CorpNo, 6, 1 )) * 1;
		No_Chk = No_Chk + toNumber(mid( lv_CorpNo, 7, 1 )) * 2;
		No_Chk = No_Chk + toNumber(mid( lv_CorpNo, 8, 1 )) * 1;
		No_Chk = No_Chk + toNumber(mid( lv_CorpNo, 9, 1 )) * 2;
		No_Chk = No_Chk + toNumber(mid( lv_CorpNo, 10, 1 )) * 1;
		No_Chk = No_Chk + toNumber(mid( lv_CorpNo, 11, 1 )) * 2;
		
		No_Chk = No_Chk % 10;
		No_Chk = 10 - No_Chk;
		
		if (No_Chk > 9){
			No_Chk = 0;
		}
		
		if (toNumber(No_Chk) == toNumber(mid(lv_CorpNo, 12, 1))){
			return true;
		}
		else{
//			
			gfn_Message("56105");
			return false;
//			return true;
		}
	}
}
/*********************************************************************
 * 기능       : 사업자번호 유효성 체크
 * Parameters : 
 *              strBizNo	- 사업자번호
 * Returns    :	true/false
 * 예제       : gfn_CheckBizNo("1234561234")
 ********************************************************************/
function gfn_CheckBizNo(strBizNo)
{
    strBizNo = replace(strBizNo, "-", "");
    strBizNo = replace(strBizNo, "/", "");
    strBizNo = replace(strBizNo, " ", "");

    if (strBizNo.length!=10) {
		gfn_Message("56106");
		return false;
    }

    var nSumMod = 0;

    nSumMod += ParseInt(strBizNo.substr(0,1));
    nSumMod += ParseInt(strBizNo.substr(1,1)) * 3 % 10;
    nSumMod += ParseInt(strBizNo.substr(2,1)) * 7 % 10;
    nSumMod += ParseInt(strBizNo.substr(3,1)) * 1 % 10;
    nSumMod += ParseInt(strBizNo.substr(4,1)) * 3 % 10;
    nSumMod += ParseInt(strBizNo.substr(5,1)) * 7 % 10;
    nSumMod += ParseInt(strBizNo.substr(6,1)) * 1 % 10;
    nSumMod += ParseInt(strBizNo.substr(7,1)) * 3 % 10;
    nSumMod += floor(ParseInt(strBizNo.substr(8,1)) * 5 / 10);
    nSumMod += ParseInt(strBizNo.substr(8,1)) * 5 % 10;
    nSumMod += ParseInt(strBizNo.substr(9,1));

    if(nSumMod%10 != 0) {
//		gfn_Message("56106");
		return false;
    }
    return true;
}
 
 
 
 function gfn_Error(Errcd,ErrMsg)
 {
 	if(toNumber(Errcd) >= 0)
 		return;
 		
 	
 	if(Errcd == "-9999"){	// 재로그인
 		gfn_Fin();
 		UserNotify(1,"");
 	}else if(Errcd == "-3"){	// 재로그인
 		gfn_Fin();
 		UserNotify(1,"");
 	}
 }

/*********************************************************************
 * 기능       : Message Box
 * Parameters : 
 *				strCD      - 에러코드
 *				optVal     - 에러메세지
 * Returns 	  : 
 * 예제       : gfn_Message("1101","에러")
 *에러코드 첫자 -- 1:Question  2: Error  3: Ok 
 *********************************************************************/
 function gfn_Message(strCD, optVal, winType)
 {

	 var numCD;
	 if(IsDigit(strCD)){
		numCD = strCD;
	 }else{
		numCD = toNumber(strCD);
	 }

 //strCD = -3085550569;
 //alert(strCD);

 	if(numCD >= 0){
 		return;
 	}else if(numCD == -8888){	//  로그아웃
		alert(optVal);
 		gfn_Fin();
		UserNotify(-8888,"");
		return;
	}else if(numCD == -9999){	//  로그아웃
		alert(optVal);
		gfn_Fin();
		UserNotify(-9999,"");
		return;
	}else if(numCD == -9090){	//  전용브라우저 실행
		UserNotify(-9090,"");
		return;
	}else if(numCD == -3){	// 재로그인
		alert(optVal);
		gfn_Fin();
		UserNotify(-3,"");
		return;
	}else if(-2085550569 >= numCD 
		&& -4000000000 < numCD
	){
		alert(optVal);
		return;
	}else {
 		SetWaitCursor(false);
 			
 		var lv_left = (GetDeviceInfo("CXScreen")-310)/2;
 		var lv_top  = (GetDeviceInfo("CYScreen")-130)/2;
 	
 		var lv_msg = replace(optVal," ","&nbsp");
 		//var lv_gb = left(strCD,1);
 		var lv_icon;
 		//ERROR	, WARN, Question, Information	
 		if ( numCD == -1  ) lv_icon = "E";
 		else if ( numCD == -2  ) lv_icon = "W";
 		else if ( numCD == -4  ) lv_icon = "I";
 		else if ( numCD == -5  ) lv_icon = "Q";
 		else lv_icon = "I";
 		
 		//if(lv_gb == "1")	lv_icon = "Q";
 		//else if(lv_gb == "2")	lv_icon = "E";
 		//else	lv_icon = "I";
 				
 		var str_arg = "MSG=" + UrlEncode(lv_msg) + " ICON=" + lv_icon;
 				
 		var lv_ret = "";
 		if( winType == "Open" ){
 			lv_ret = Open(G_Server + "/message_miplatform.do",str_arg,310,130,"CloseFlag=false TitleBar=false",lv_left,lv_top);
 		}else{
 			lv_ret = Dialog(G_Server + "/message_miplatform.do",str_arg,310,130,"CloseFlag=false TitleBar=false",lv_left,lv_top);
 		}
 		return lv_ret;
 	}
 }
 
 /*===============================================================
 = 기능 : Grid 일괄 Check 처리 (공통)
 = 인수 : obj	Grid Component ID
 				 Col 	Check Column ID
 = 예제 : lfn_GridSelChk(obj , Col)
 ===============================================================*/
 function lfn_GridSelChk(objGrd, chk_col){
 
 	if(objGrd.Editable == false) return;
 	var objDs = objGrd.BindDataset;
 	
 	if (objGrd.GetCellProp("head",0, "text") == 1){
 		objGrd.SetCellProp("head",0, "text", "0");
 		lfn_SetGrdChk(0, objDs, chk_col);		
 	} else {
 		objGrd.SetCellProp("head",0, "text", "1");
 		lfn_SetGrdChk(1, objDs, chk_col);
 	}
 }

 /*===============================================================
 = 기능 : Dataset Check Toggle 처리 (공통)
 = 인수 : Value		Grid Component ID
 				 Dataset 	Check Column ID
 				 col		 	Check Column ID
 = 예제 : lfn_SetGrdChk(Value, Dataset , Col)
 ===============================================================*/
 function lfn_SetGrdChk(chk_val, objDs, chk_col){
 	for (i=0; i<object(objDs).RowCount(); i++){
 		object(objDs).SetColumn(i, chk_col, chk_val);
 	}
 }
 
 /*===============================================================
 = 기능 : Popup 처리 (공통)
 = 인수 : strComponentID : "Div" or "PopupDiv"
 		  obj : object
 		  objname : 팝업창 이름
 		  url : URL
 		  width : 팝업창 넓이
 		  height : 팝업창 높이
 = 예제 : gfn_CreatePopup("Div", obj, objname, url, width, height) 
 = 수정 : 2009.11.30 POCIS 팀 
 ===============================================================*/
 function gfn_CreatePopup(strComponentID, obj, objname, url, width, height)  {
	var iXPos = ClientToScreenX(obj, 0);
	var iyPos = ClientToScreenY(obj, obj.height);
	if(!isValidObject(object(objname))){
		Create(strComponentID, objname ,'width="'+width+'" height="'+height+'" border="Flat" url="'+url+'"'); 
		if ( strComponentID == "PopupDiv" ) object(objname).TrackPopup(iXPos, iyPos);		
	}else{
		if ( strComponentID == "PopupDiv" ) object(objname).TrackPopup(iXPos, iyPos);		
	}
 } 
 
 /*===============================================================
 = 기능 : DataSet Info (공통)
 		  DataSet의 컬럼과 데이터를 alert 으로 표시한다.
 		  개발할 때에만 DataSet 확인용으로 사용할것!!!
 = 인수 : ds : DataSet Object
  		  rowStart(optional) : 출력을 시작할 row index (0부터 시작)
  		  rowCount(optional) : 출력할 row 갯수
 = 반환 : DataSet 정보 String 반환
 = 예제 : gfn_GetDSInfo(dataSet), gfn_GetDSInfo(dataSet, 0, 10) 
 = 수정 : 2009.12.03 POCIS 팀 
 ===============================================================*/
 function gfn_GetDSInfo(ds, rowStart, rowCount) {
	var idxStart = 0;
	var idxEnd = ds.GetRowCount();
	
	var strMsg = "";
	var strMsg2 = "";
	
	if ( rowStart != null && rowStart > 0 ) {
		idxStart = rowStart;
	}
	
	if ( rowCount != null && rowCount > 0 ) {
		if ( idxEnd < rowStart+rowCount ) {
			alert("rowCount too large");
			strMsg = ("요청한 마지막 ROW INDEX가 DATASET의 마지막 ROW INDEX보다 큽니다. DATASET의 마지막 ROW INDEX까지만 출력합니다.");
		} else {
			idxEnd = rowStart+rowCount;
			//if ( rowStart == null && rowCount == null
		}
	}
	if ( rowCount == null && idxEnd > 5 ) {
		idxEnd = 5;
		strMsg2 = "출력갯수를 지정하지 않아 최초 5개 까지만 출력합니다.\r\n";
	}
 	if ( ds.GetRowCount() > 0 ) {
 		strMsg = strMsg2 + "[row : " + idxStart + "][type : " + ds.GetRowType(idxStart) + "] ==============================\r\n";
 		for( var i=idxStart ; i < idxEnd ; i++ ) {
 			for( var j=0 ; j < ds.GetColCount() ; j++ ) {
 				strMsg += "[" + ds.GetColId(j) + "]\t : [" + ds.GetColumn(i,j) + "]\r\n";
 			}
 			if ( idxEnd > (i+1) ) {
 				strMsg += "\r\n";
 				strMsg += "[row : " + (i+1) + "][type : " + ds.GetRowType(i+1) + "] ==============================\r\n";
 			}
 		}
 	}
 	return strMsg;
 }

 /*===============================================================
 = 기능 : change display mode (공통)
 		  수정 또는 조회 화면으로 변경할 경우 form내의 component의 Enable 또는 ReadOnly를 변경.
		  *** ReadOnly는 !bMode, Enable은 bMode. ***
		  Edit, MaskEdit, Spin, TextArea 는 ReadOnly로 처리.
		  Calendar, CheckBox, Combo, Grid, ListBox, Radio 는 Enable로 처리.
		  Components안에 하위 Components가 있을경우에도 지원가능.
		  (예, 폼안에 여러개의 Div 또는 Tap일 경우에도 상위 폼만 지정하면 하위 속성도 변경됨)
 = 인수 : obj : components(form or div)
  		  bMode : boolean(변경하려는 특성값)
 = 예제 : gfn_chgDisplayMode( frmMasterPlanDetail, true )
		  gfn_chgDisplayMode( Div0, false)
 = 수정 : 2010.01.08 POCIS 팀 
 ===============================================================*/
function gfn_ChgDisplayMode( obj, bMode ) {
	var comps 	= obj.Components;	
	for( i=0; i<comps.Count; i++ ){
		//trace(comps[i].GetType());
		switch ( comps[i].GetType() ) {
			case 'Edit' :
				comps[i].ReadOnly = !bMode;
				break;
			case 'MaskEdit' :
				comps[i].ReadOnly = !bMode;
				break;
			case 'Spin' :
				comps[i].ReadOnly = !bMode;
				break;
			case 'TextArea' :
				comps[i].ReadOnly = !bMode;
				break;
			case 'Calendar' :
				comps[i].Enable = bMode;
				break;
			case 'CheckBox' :
				comps[i].Enable = bMode;
				break;
			case 'Combo' :
				comps[i].Enable = bMode;
				break;
			case 'Grid' :
				comps[i].Enable = bMode;
				break;
			case 'ListBox' :
				comps[i].Enable = bMode;
				break;
			case 'Radio' :
				comps[i].Enable = bMode;
				break;
			case 'Div' :
				gfn_chgDisplayMode(object(comps[i].Id), bMode);
				break;
			case 'Tab' :
				gfn_chgDisplayMode(object(comps[i].Id), bMode);
				break;
			case 'TabPage' :
				gfn_chgDisplayMode(object(comps[i].Id), bMode);
				break;
		}
	}
}

/*********************************************************************
 * 기능       : 이메일주소 체크
 * Parameters : 
 *              sValue	- 이메일주소
 * Returns    :	true/false
 * 예제       : gfn_CheckEmail("abc@ccc.co.cc")
 ********************************************************************/
function gfn_CheckEmail(sValue)
{
	var sReturnValue = false;
	var sTmp = "";
	var sRegExp = "[a-z0-9]+[a-z0-9.,]+@[a-z0-9]+[a-z0-9.,]+\\.[a-z0-9]+";
	
	var regexp = CreateRegExp(sRegExp,"ig");
	sTmp = regexp.Exec(sValue);
	
	if ( sTmp == null )
			sReturnValue = false;
	else
	{
		if ( ( sTmp.index == 0 ) && (sTmp.length == sValue.length ) )
			sReturnValue = true;
		else
			sReturnValue = false;
	}
	
	return sReturnValue;
}


/*********************************************************************
 * 기능       : 로그아웃 수행
 ********************************************************************/

function gfn_Logout()
{
	//gfn_Error("-9999","");
	gfn_Message("-9999","logout");
}


/*********************************************************************
 * 기능       : 홈으로 이동
 ********************************************************************/
 
function gfn_Home()
{
	gfn_Message("-8888","gohome");
}


/*********************************************************************
* 기능       : 레포트 3.0 출력 함수
* Parameters : 
*              reportName	- 레포트 이름
*              arrayReports	- 레포트 구성 배열
*              arrayParams	- 레포트 파라미터 배열
* 예제       : fnCmReport("templet/templet1", arrayReports, arrayParams);
********************************************************************/
﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿function fnCmReport(reportName, arrayReports, arrayParams)
{

	//예제
	//var reportName = "templet/templet1";
	// 커넥션타입, 커넥션이름, path or xmldata, root, 
	//var arrayReports = 	[
	//						["http","XML1","/ipa/uss/umt/user/ISUserCmmCode.do","root/dataset/record"]
	//						,["memo","XML2",ds_list.SaveXML(),"root/dataset/record"]
	//						,["memo","XML5",xmldata,"gubun/rpt1/rexrow"]
	//					];
	//var arrayParams = 	[
	//						["name","value"]
	//						,["name1","value1"]
	//						,["name2","value2"]
	//					];
	//fnCmReport(reportName, arrayReports, arrayParams);

	
	var data="";
	var param="";
	for( var i=0; i<length(arrayReports); i++){
		if (i!=0) data += "*";
		data += arrayReports[i][0] + "^" + arrayReports[i][1] +"^"+arrayReports[i][2]+"^"+arrayReports[i][3];
	}
	for( var i=0; i<length(arrayParams); i++){
		if (i!=0) param += "*";
		param += arrayParams[i][0] + "^" + arrayParams[i][1];
	}	
	
	//alert(data);
	var url=G_Server+"/report_miplatform.do";
	//Create("Div","detailDiv",'width="600" height="800" top="50" left="50" url="'+url+'"'); 
	var iXPos = ClientToScreenX(this, 0);
	var iyPos = ClientToScreenY(this, 30);
	
	
	
	Dialog(url,"rptname="+UrlEncode(reportName)  + " data="+UrlEncode(data) +" param="+UrlEncode(param),900,700,"",iXPos,iyPos);	
	
}
/*********************************************************************
* 기능       : 레포트 2.5 출력 함수
* Parameters : 
*              reportName	- 레포트 이름
*              arrayReports	- 레포트 구성 배열
*              arrayParams	- 레포트 파라미터 배열
* 예제       : fnCmReport("templet/templet1", arrayReports, arrayParams);
********************************************************************/
//arrayReports 사용하지 않음. pdk ship 2010.9.8

﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿function fnCmReport25(reportName, arrayReports, arrayParams, previewType)
{
	fnCmReport25OnType(reportName, arrayParams, previewType, "XML", "vector/map");
}

//dataType = "XMLSTR", xPath = "root/dataset/record"
﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿function fnCmReport25OnType(reportName, arrayParams, previewType, dataType, xPath, xmlData)
{
	divReport.Url = "";


	var param="";
	var tmpParam = "";
	
	for( var i=0; i<length(arrayParams); i++){
		if (i!=0) param += "*";
		
		tmpParam = replace(arrayParams[i][1],"%","$");
		//trace(tmpParam);
		
		param += arrayParams[i][0] + "^" + tmpParam;
	}	
	

	divReport.Url = G_Server + "/report_miplatform25.do";
	
	divReport.UserData = "rptname="+reportName  + "&param="+param + "&previewType="+previewType +"&dataType="+dataType+"&xPath="+xPath+"&xmlData="+UrlEncode(xmlData);
	//alert(divReport.UserData);
}

/*====================================================================
= 기능 : File 생성 (Write)
= 인수 : file_url			file Path
				 str_param		Parameter (Cookie)
				 nRow					Dataset Row Position 
				 strCol				Column ID (Progress 설정)
				 objState			Progress 표시 Grid
= Return : Result/Message/file Size				 
=====================================================================*/
function gfn_FileWrite(httpfileObj,file_url, str_param, nRow, strCol, objState, upload_packetSize, remote_url, saveproperty){
	

			trace("common :: gfn_FileWrite :: file_url :: " + file_url);
	var rtn_arr = Array(3);
	rtn_arr[0] = "FAIL";
	if (length(file_url) < 7) {
		rtn_arr[1] = "파일을 선택하지 않았습니다.";
		return rtn_arr;
	}
	
	if (length(str_param) < 1){
		rtn_arr[1] = "쿠키값을 찾을 수 없습니다.";
		return rtn_arr;
	} 

	
	var write_size;
	var tot_write_size;
	var file_size;

	file_size = httpfileObj.GetFileSizeLocal(file_url);
	
	//httpfileObj.CookieParam = str_param;
	httpFileObj.CookieParam = saveproperty+"^"+UrlEncode(str_Param);

//trace("httpfileObj.CookieParam : " + httpfileObj.CookieParam);
//trace("remote_url : " + remote_url);
//trace("file_url : " + file_url);
//trace("remote_url : " + remote_url);

	ret = httpfileObj.OpenFile(remote_url, file_url, "PUT");
	
	if( ret < 0 )	{
		rtn_arr[0] = "FAIL";
		rtn_arr[1] = "OpenFile Failed :: " + httpfileObj.ErrorMsg;
		return rtn_arr;
	}

	if (IsValidObject(objState)){
		var objDs = objState.BindDataset;
		var readpercent;
		object(objDs).SetColumn(nRow, "filesize",round(file_size/1024)); 
		object(objDs).SetColumn(nRow, "filesizebyte",file_size); 
		while(1) {
			write_size = httpfileObj.WriteFile(upload_packetSize);
//			trace(write_size);
			tot_write_size += write_size;
			
			readpercent = truncate((tot_write_size / file_size) * 100,1);
			
			object(objDs).SetColumn(nRow, strCol, readpercent); 
			object(objDs).SetColumn(nRow, "progsize",round(tot_write_size/1024)); 
			
			if( write_size < upload_packetSize )	break;
		}
		
	} else {
		while(1)	{
			write_size = httpfileObj.WriteFile(upload_packetSize);	
			if( write_size < upload_packetSize )	break;
		}
	}

	httpfileObj.CloseFile();

	if (isExistVar("objState",true)){
		if ( write_size == -1 )	{
			rtn_arr[0] = "FAIL";
			rtn_arr[1] = httpfileObj.ErrorMsg;
			return rtn_arr;
		}
	}

	var rtn_val = httpfileObj.CookieParam;

	var str_sp = split2(rtn_val,";","=");
	var tmp_a;
	
	for ( var i = 0 ; i < str_sp.length() ; i++ )
	{
		tmp_a = str_sp[i];
		if ( tmp_a[0].pos("FileParam") > -1 )
		{
			rtn_arr[0] = left(tmp_a[1],4);
			rtn_arr[1] = right(tmp_a[1], length(tmp_a[1]) -6);
		}	
	}	
	
	return rtn_arr;
}

/*====================================================================
= 기능 : File Read
= 인수 : file_url			file Path
				 str_cookie		Cookie (parameter)
				 str_size			File Size
				 nRow					Dataset Row Position
				 strCol				Column ID (progress 설정)
				 objState			progress 설정 Grid
= Return : Result/Message/file Size				 
=====================================================================*/

function gfn_FileRead(httpfileObj,downfilename, uploadFileName, str_size, nRow, strCol, objState, download_packetSize, remote_url){

	  

	var rtn_arr = Array(2);
	rtn_arr[0] = "FAIL";

//	if (length(quote(file_url)) < 7) {
//		rtn_arr[1] = "Not Found Seleced File!";
//		return rtn_arr;
//	}
	
//	if (length(quote(str_cookie)) < 1){
//		rtn_arr[1] = "Not Found CookieParam!";
//		return rtn_arr;
//	}

	if (length(quote(str_size)) < 1 || str_size == 0){
		rtn_arr[1] = "Not Found File Size!";
		return rtn_arr;
	}


	var read_size;
	var tot_read_size;
	var file_size;

	//HttpFile0.SessionParam = SMSESSION;
	//httpfileObj.CookieParam = str_cookie;
	httpFileObj.CookieParam = UrlEncode(uploadFileName);

	
	file_size = str_size;
//trace("remote_url::"+remote_url);	
//trace("file_url::"+file_url);
	ret = httpfileObj.OpenFile(remote_url, downfilename, "GET");
	
	if( ret < 0 ) {
		rtn_arr[0] = "FAIL";
		rtn_arr[1] = "OpenFile Fail ::" + httpfileObj.ErrorMsg;
		return rtn_arr;
	}
	
	tot_read_size = 0;	

	if (IsValidObject(objState)){
		var objDs = objState.BindDataset;
		var readpercent;

		while(1) {
			read_size = httpfileObj.ReadFile(download_packetSize);
//trace(read_size);
			tot_read_size += read_size;
			
			readpercent = truncate((toNumber(tot_read_size) / toNumber(file_size)) * 100,1);
			
			object(objDs).SetColumn(nRow, strCol, readpercent); 
			
			if( (read_size == 0) || ( read_size == -1) ) break;
		}
		
	} else {
		while(1) {
			read_size = httpfileObj.ReadFile(download_packetSize);
			if( (read_size == 0) || ( read_size == -1) ) break;
		}
	}
	
	httpfileObj.CloseFile();
	
	if ( read_size == -1 )	{
		rtn_arr[0] = "FAIL";
		rtn_arr[1] = httpfileObj.ErrorMsg;
		return rtn_arr;
	}
	
	var rtn_val = httpfileObj.CookieParam;
	var str_sp = split2(rtn_val,";","=");
	var tmp_a;
	
	for ( var i = 0 ; i < str_sp.length() ; i++ )
	{
		tmp_a = str_sp[i];
		if ( tmp_a[0].pos("FileParam") > -1 )
		{
			rtn_arr[0] = left(tmp_a[1],4);
			rtn_arr[1] = right(tmp_a[1], length(tmp_a[1]) -6);
		}	
	}	

	return rtn_arr;
}

function gfn_newWindow(frmNM, frmURL) 
{
	if (trim(length(frmURL)) < 1) 
	{
		return;
	}

	var objWin = AllWindows(frmNM);
	var strArg = "";

//	global.mainmenu.lfn_setMenu(frmNM);

	if (objWin[0] == null) 
	{
		NewWindow(frmNM, frmURL, strArg, "", "", "OpenStyle=Max", -1, -1);
		 //NewWindow(frmNM, frmURL, strArg, "", "", "", -1, -1);
	} 
	else 
	{
		if (objWin[0].MDIStatus == "Min") 
		{
			objWin[0].MDIStatus = "Normal";
		}
		g_activeFrm = objWin[0];
		objWin[0].SetFocus();
	}
}


/*********************************************************************
 * 기능       : 정규식 전환
 * Parameters : 
 *              sValue	- 값
 *              sPattern	- ###-####-#### ('#' = 문자)
 * Returns    :	true/false
 * 예제       : gfn_CheckEmail("abc@ccc.co.cc")
 ********************************************************************/
function gfn_simpleFormatReplace(sPattern, sValue)
{

	
	for(var i=0; i<sPattern.length; i++){
	
		if( sPattern.substr(i, 1) <> "#" ) {
			//trace(sPattern.substr(i, 1));
		
			var addStr = sPattern.substr(i, 1);
			var addPos = i;
			
			sValue = SubStr(sValue, 0, addPos) + addStr + SubStr(sValue, addPos, sValue.length-1);
		}

	}

	return sValue;
	

}


/*********************************************************************
 * 기능       : Message Box
 * Parameters : 
 *				
 * Returns 	  : 
 * 예제       : 
 *********************************************************************/
 function gfn_MovingInfomation(msgText)
 {
 	var sForm = GetTopWindow();
	if(sForm != null && sForm != ""){
		//trace(sForm);
		//return sForm.MDIStatus;
	}

	if ( IsValidObject(sForm) ) {
		if ( IsValidObject(sForm.MsgDiv) ) {
			if ( IsValidObject(sForm.MsgDiv.MsgStatic) ) {
				sForm.MsgDiv.MsgStatic.Text = msgText;
				sForm.MsgDiv.MsgDivSign_OnMouseOver();
			
			}
		}
	}

	
 }
 
 
 /*********************************************************************
 * 기능 : 시작일 종료일 유효성체크
 * Parameters : S_Date=>시작일자
	           E_Date=>종료일자
 * Returns : 
 * 예제 : gfn_Date_Chk(S_Date, E_Date)
		: gfn_Date_Chk("Calendar0","Calendar1");
 *********************************************************************/
 function gfn_Date_Chk(S_Date, E_Date)
{
	
	if(length(object(S_Date).value)<= 0)
	{
		alert("시작일부터 입력하세요");
		object(S_Date).setfocus();
	}
	
	if (length(object(E_Date).value)>0)
	{
		if(tonumber(object(S_Date).value) > tonumber(object(E_Date).value))
		{
			alert("종료일이 시작일보다 작습니다.");
			object(E_Date).Text="";
			object(E_Date).setFocus();
			
			return false;
		}
	}
}

 /*********************************************************************
 * 기능 : 폼의 텍스트 박스 초기화
 * Parameters : objForm=>폼ID(this)
 * Returns : 
 * 예제 : gfn_Fominit(objForm)
		: gfn_Fominit(this);
 *********************************************************************/
function gfn_FormClearinit(objForm)
{
	var comp = null;
	for ( var i = 0 ; i < objForm.Components.Count ; i++ )
	{	
		comp =  objForm.Components[i];
		if ( objForm.Components[i].IsComposite() )
		{
			if(comp.GetType() =="Div"){
				if(comp.GetFirstComponent() == null){
					continue;
				}
				clearFieldsOnDiv(objForm, comp.GetFirstComponent().getForm());
			}
			else if(comp.GetType() == "Tab"){
				clearFieldsOnTab(comp);
			}
			continue;
		}
		 clearCompValue(comp);
	}
}
	
//Div안에 있는 컴포넌트
function clearFieldsOnDiv( p_formObj, p_divObj) {
	
	if ( Length(p_divObj.Components) < 1 )
		return true;
	var comp = null;
	for ( var c = 0; c < Length(p_divObj.Components); c++ ) {
		comp = p_divObj.components(c);
			if(comp.GetType() == "Div"){
				if(comp.GetFirstComponent() == null){
					continue;
				}
				clearFieldsOnDiv(p_formObj,comp.GetFirstComponent().getForm());
			}else if(comp.GetType() == "Tab"){
				clearFieldsOnTab(comp);
			}
		clearCompValue(comp);
	}
	return true;
}
//tab안에 있는 컴포넌트
function clearFieldsOnTab(p_tabObj) {

	if ( p_tabObj.TabCount < 1 )
		return true;
		
	var tabPg;	

	for(var x=0;x<p_tabObj.TabCount;x++){
				tabPg =p_tabObj.GetItem(x);

		var comp = null;
		for ( var c = 0; c < Length(tabPg.Components); c++ ) {
			comp = tabPg.components(c);
				if(comp.GetType() == "Div"){
					if(comp.GetFirstComponent() == null){
						continue;
					}
				clearFieldsOnDiv(p_formObj,comp.GetFirstComponent().getForm());
				}else if(comp.GetType() == "Tab"){
					clearFieldsOnTab(comp);
				}
		clearCompValue(comp);
		}
	}
		return true;
}

//해당 컴포넌트 클리어 셋팅
//해당 컴포넌트 OnKeyDown이벤트 발생
function clearCompValue(p_comp){
			switch(p_comp.GetType()){
				case "Checkbox":
					p_comp.value = p_comp.FalseValue;
					p_comp.OnKeyDown="gfn_SchComp1_OnKeyDown";
					break;
				case "Combo":
					p_comp.index = 0;
					p_comp.OnKeyDown="gfn_SchComp1_OnKeyDown";
					break;
				case "Edit":
					p_comp.value = "";
					p_comp.OnKeyDown="gfn_SchComp2_OnKeyDown";
					break;	
				case "Calendar":
					p_comp.value = "";
					p_comp.OnKeyDown="gfn_SchComp1_OnKeyDown";
					break;	
				case "MaskEdit":
					p_comp.value = "";
					p_comp.OnKeyDown="gfn_SchComp2_OnKeyDown";
					break;	
				case "TextArea":				
					p_comp.value = "";
					p_comp.OnKeyDown="gfn_SchComp2_OnKeyDown";
					break;
				default:
				
			}
}

 /*********************************************************************
 /*********************************************************************
 * 기능 : 폼의 컴포넌트 엔터시 다음 포커스로 이동
 * Parameters : 
 * Returns : 
 * 예제 : gfn_SchComp_OnKeyDown
		: edtCallLetter.OnKeyDown="SchComp_OnKeyDown"; 
 *********************************************************************/
 //MaxLength가 Property가 없는 컴포넌트(Combo, checkbox, calender)
function gfn_SchComp1_OnKeyDown(obj,nChar,bShift,bCtrl, bAlt,LLParam,HLParam) {
	//엔터키 입력시
	if (nChar == 13 ) GetNextComponent(true).setFocus();
}
//MaxLength가 Property가 있는  컴포넌트(edit, maskedit, textarea)
function gfn_SchComp2_OnKeyDown(obj,nChar,bShift,bCtrl, bAlt,LLParam,HLParam) {
	//엔터키 입력시
	if (nChar == 13 ) GetNextComponent(true).setFocus();
	if (Length(obj.value)=obj.MaxLength)GetNextComponent(true).setFocus();
}
	

/**********************************************************************************************
//                                 Copyright 2006 Tobesoft
// -. 사용 방법gfn_SubFormResizeProc
  #include "lib::FormResize.js"
  function form_OnLoadCompleted(obj)
  {
	gfn_ResizeInit(1024,768);
  }
// -. 주의 사항
// Running 중간에 Component를 Destory하는 처리가 있으면 Resize처리가 오 작동 할 수 있습니다.
// gfn_ResizeInit();  호출후에 Component 위치 및 size를 조정하면 Resize시에 조정된 위치및 size가 무시됩니다.   
// Tab 인경우 Url Link 처리 되면서 Preload에서 false하면 로드되지 전에 gfn_ResizeInit하면
// Resize할 Control를 등록하지 못하여 로드되지 않아서 처리가 안되게 되어 있습니다.
**********************************************************************************************/

var fv_nResizeProcCnt = 0;
var fv_FirstResize = false;
var fv_orgWidth;
var fv_orgHeight;

var fv_forgWidth;
var fv_forgHeight;

var fv_ArrHorzPosition = Array();
var fv_ArrVertPosition = Array();


var fv_ArrSubHorzPosition = Array();
var fv_ArrSubVertPosition = Array();
var fv_SubSeq = -1;


var fv_SuborgWidth = Array();
var fv_SuborgHeight = Array();

var fv_SubforgWidth = Array();
var fv_SubforgHeight = Array();


function gfn_ResizeInit(nWidth,nHeight)
{
	this.OnSize = "gfn_frm_OnSize";
		
	fv_orgWidth   = nWidth;
	fv_orgHeight  = nHeight;
	fv_forgWidth  = nWidth;
	fv_forgHeight = nHeight;
	var seq = 0;
	
//	trace("0.0 =====" + this.id + " =====" + this.Components.Count);
	fv_SubSeq = -1;
	for ( var i = 0 ; i < this.Components.Count ; i++ )
	{
		if (  ( this.Components[i].GetType() == "Dataset" ) ||
			  ( this.Components[i].GetType() == "File" ) ||	
			  ( this.Components[i].GetType() == "FileDialog" ) ||	
			  ( this.Components[i].GetType() == "PopupDiv" ) 
		   )	
			continue;
		fv_ArrHorzPosition[seq] = Components[i].left;
		fv_ArrVertPosition[seq] = Components[i].top;
		seq++;
		fv_ArrHorzPosition[seq] = Components[i].right;
		fv_ArrVertPosition[seq] = Components[i].bottom;
		seq++;
		//trace("1.0 ===== " + i + " : " + Components[i].id );
//	trace("fv_ArrSubVertPosition :" + seq + "===" + fv_ArrHorzPosition);
		if ( Components[i].IsComposite() )
		{
			gfn_SubFormResizeInit(Components[i]);
		}
		
//		trace("1.0 =====" + Components[i].id + "==== End");
	}
	
	//alert(this.width + "///" + this.height);
	fv_FirstResize = true;
	//setTimer(1,100);
	gfn_frm_OnSize(this,this.width,this.height);

}

function gfn_SubFormResizeInit(obj)
{
	var seq = 0;
	var tmpArrayHorz = Array();
	var tmpArrayVert = Array();
	fv_SubSeq++;
	var subseq = fv_SubSeq;
	fv_SuborgWidth[subseq] = obj.width;
	fv_SuborgHeight[subseq] = obj.height;

	fv_SubforgWidth[subseq] = obj.width;
	fv_SubforgHeight[subseq] = obj.height;

	//trace("2.0 =====" + obj.id + " : " + obj.Components.Count + "--------" + subseq );
	for ( var i = 0 ; i < obj.Components.Count ; i++ )
	{
		if (  ( obj.Components[i].GetType() == "Dataset" ) ||
			  ( obj.Components[i].GetType() == "File" ) ||	
			  ( obj.Components[i].GetType() == "FileDialog" ) ||	
			  ( obj.Components[i].GetType() == "PopupDiv" ) 
		   )	
			continue;
		tmpArrayHorz[seq] = obj.Components[i].left;
		tmpArrayVert[seq] = obj.Components[i].top;
		seq++;
		tmpArrayHorz[seq] = obj.Components[i].right;
		tmpArrayVert[seq] = obj.Components[i].bottom;
		seq++;
		if ( obj.Components[i].IsComposite() )
		{
		//trace("2.1 SubFormInit : " + obj.Components[i].id);
			gfn_SubFormResizeInit(obj.Components[i]);
		}
	}
	fv_ArrSubHorzPosition[subseq] = tmpArrayHorz;
	fv_ArrSubVertPosition[subseq] = tmpArrayVert;	

	//trace("set :" + subseq + "===" + fv_ArrSubHorzPosition[subseq]);
//	trace("2====end ");
}

function gfn_ResizeProc(nCx,nCy)
{
	fv_SubSeq = -1;
	var seq = 0;
	var nWidthRate;
	var nHeightRate;
	var bProcSizeFlag = true;
	//수정
	var bHeightFixFlag = false;
	var bWidthFixFlag = false;
	
//trace("5.0 ===== " + fv_SubSeq + " -- " + this.id + " : " + this.Components.Count);	
	for ( var i = 0 ; i < this.Components.Count ; i++ )
	{
		if (  ( this.Components[i].GetType() == "Dataset" ) ||
			  ( this.Components[i].GetType() == "File" ) ||	
			  ( this.Components[i].GetType() == "FileDialog" ) ||	
			  ( this.Components[i].GetType() == "PopupDiv" ) 
		   )	
			continue;
			
		
//trace("5.1 ===== " + this.Components[i].id);	
		bProcSizeFlag = true;
		bHeightFixFlag = false;
		bWidthFixFlag = false;
		
		
		if ( this.Components[i].GetType() == "Button" )  bProcSizeFlag = false;
		else if ( this.Components[i].GetType() == "Image" )
		{
			if ( this.Components[i].FillType == "NONE" ) bProcSizeFlag = false;
		}

		//수정
		if ( ToLower(this.Components[i].id)=="img_top") bProcSizeFlag = false;
		if ( ToLower(this.Components[i].id)=="img_top_shape") bHeightFixFlag = true;
		if ( this.Components[i].GetType() == "Image" ) {
			if ( ToLower(this.Components[i].ImageID) =="tit_bg") bHeightFixFlag = true;
		}
		if ( ToLower(this.Components[i].id)=="imgHome") bHeightFixFlag = true;
		if ( ToLower(this.Components[i].id)=="menu_bar_bg") bHeightFixFlag = true;
		if ( ToLower(this.Components[i].id)=="mnuBar") bProcSizeFlag = false;
		//if ( this.Components[i].id="img_Bottom") bProcSizeFlag = false;
		
		//왼쪽 늘어남
		if ( ToLower(this.Components[i].id)=="img_hello") bWidthFixFlag = true;
		if ( ToLower(this.Components[i].id)=="img_admin") bWidthFixFlag = true;
		if ( ToLower(this.Components[i].id)=="img_close") bWidthFixFlag = true;
		if ( ToLower(this.Components[i].id)=="img_home") bWidthFixFlag = true;
		if ( ToLower(this.Components[i].id)=="img_myinfo") bWidthFixFlag = true;
		if ( ToLower(this.Components[i].id)=="btn_search") bWidthFixFlag = true;
		if ( ToLower(this.Components[i].id)=="btn_print") bWidthFixFlag = true;
		//if ( this.Components[i].id="=img_Copyright") bHeightFixFlag = true;
		
		//if ( this.Components[i].id=="div_TreeMenu") bWidthFixFlag = true;
		
		//trace(Components[i].id);
		if ( this.Components[i].GetType() != "TabPage" )
		{		    
			if ( !bProcSizeFlag ) // 위치를 중앙으로 조정
			{	
				nWidthRate  = parseInt( (ToNumber(fv_ArrHorzPosition[seq]) * ToNumber(nCx)) / ToNumber(fv_forgWidth) );
				nHeightRate = parseInt( (ToNumber(fv_ArrVertPosition[seq]) * ToNumber(nCy)) / ToNumber(fv_forgHeight) );
				seq++;
				nWidthRate  = nWidthRate + parseInt( ((ToNumber(fv_ArrHorzPosition[seq]) * ToNumber(nCx)) / ToNumber(fv_forgWidth) - nWidthRate)/2) - parseInt(this.Components[i].Width/2);
				nHeightRate = nHeightRate + parseInt( ((ToNumber(fv_ArrVertPosition[seq]) * ToNumber(nCy)) / ToNumber(fv_forgHeight) - nHeightRate)/2) - parseInt(this.Components[i].Height/2);
				
				if ( nWidthRate < 0 ) nWidthRate = 0;
				if ( nHeightRate < 0 ) nHeightRate = 0;
				
				if (bWidthFixFlag) {
					this.Components[i].left = nWidthRate;
				}
				//this.Components[i].top = nHeightRate;
//trace("1111---" + bProcSizeFlag + "====" + this.Components[i].id + " : " + this.Components[i].left + "top = " + this.Components[i].top);
				
				seq++;

			}
			else // size 조정 처리.
			{
				nWidthRate  = parseInt( (ToNumber(fv_ArrHorzPosition[seq]) * ToNumber(nCx)) / ToNumber(fv_forgWidth) );
				nHeightRate = parseInt( (ToNumber(fv_ArrVertPosition[seq]) * ToNumber(nCy)) / ToNumber(fv_forgHeight) );
				//수정
				//this.Components[i].left = nWidthRate;
				if ( nWidthRate < 0 ) nWidthRate = 0;
				if ( nHeightRate < 0 ) nHeightRate = 0;

				if ( this.Components[i].id=="img_Bottom") {
					this.Components[i].top = nHeightRate;
				}
				seq++;
				nWidthRate  = parseInt( (ToNumber(fv_ArrHorzPosition[seq]) * ToNumber(nCx)) / ToNumber(fv_forgWidth) );
				nHeightRate = parseInt( (ToNumber(fv_ArrVertPosition[seq]) * ToNumber(nCy)) / ToNumber(fv_forgHeight) );
	
				if (!bWidthFixFlag) this.Components[i].right = nWidthRate;			
				if (!bHeightFixFlag) this.Components[i].bottom = nHeightRate;				
				
//trace("1111---" + bProcSizeFlag + "====" + this.Components[i].id + " : " + this.Components[i].left + "top = " + this.Components[i].top);

				seq++;
			 }
		 }
		 else
		 {
			seq++;
			seq++;
		 }
		 //if ( this.Components[i].GetType() == "Grid" ) this.Components[i].FitToArea();
			//trace("5.2 ==== "+this.Components[i].id + "::::" + this.Components[i].IsComposite());
		 if ( this.Components[i].IsComposite() )
		 {

			gfn_SubFormResizeProc(this.Components[i]);
			//trace("5.2 ==== "+this.Components[i].id + "*** END");
		 }
//trace("5.1 ===== " + this.Components[i].id + " ***** End");	
	}
}

function gfn_SubFormResizeProc(obj)
{
	
	var nCx = obj.width;
	var nCy = obj.height;
	var seq = 0;
	var nWidthRate;
	var nHeightRate;
	var bProcSizeFlag = true;
	var tmpArrayHorz;
	var tmpArrayVert;
	fv_SubSeq++;
	var subseq = fv_SubSeq;
	fv_SuborgWidth[subseq] = obj.width;
	fv_SuborgHeight[subseq] = obj.height;

	tmpArrayHorz = fv_ArrSubHorzPosition[subseq];
	tmpArrayVert = fv_ArrSubVertPosition[subseq];	
	
	var loopCnt = 	tmpArrayHorz.length()/2;
	
	//수정
	var bTopFixRate = false;
	
	//trace(obj.id + ": " + subseq + " ====" + fv_ArrSubHorzPosition[subseq]);
//	trace("5.2.1 ===== " + obj.id + ":" + loopCnt);
	
//	for ( var i = 0 ; i < loopCnt ; i++ )
//	{
	var i,j = 0;
	var subObj;
	
	while (i < loopCnt )
	{
		
		//수정
		//if (obj.Components[i].id != "grd_MainTreeMenu") {
		
			//trace("5.2.1.1 ===== " + obj.Components[i].id  );
			
			if (  ( obj.Components[j].GetType() == "Dataset" ) ||
				  ( obj.Components[j].GetType() == "File" ) ||	
				  ( obj.Components[j].GetType() == "FileDialog" ) ||	
				  ( obj.Components[j].GetType() == "PopupDiv" ) 
			   )	
			{
				j++;
				continue;
			}
			
			bProcSizeFlag = true;
			bTopFixRate = false;
			
			
			if ( obj.Components[i].GetType() == "Button" )  bProcSizeFlag = false;		
			else if ( obj.Components[i].GetType() == "Image" )
			{	
				if ( obj.Components[i].FillType == "NONE" ) bProcSizeFlag = false;
			}
			
			//수정
			if ( obj.Components[i].id == "img_MenuHide") bTopFixRate = true;
			
			//trace(obj.Components[i].id + "===" + obj.Components[i].GetType());
			if ( obj.Components[i].GetType() != "TabPage" )
			{
				if ( !bProcSizeFlag ) // 위치를 중앙으로 조정
				{
					nWidthRate  = parseInt( (ToNumber(tmpArrayHorz[seq]) * ToNumber(nCx)) / ToNumber(fv_SubforgWidth[subseq]) );
					nHeightRate = parseInt( (ToNumber(tmpArrayVert[seq]) * ToNumber(nCy)) / ToNumber(fv_SubforgHeight[subseq]) );
					seq++;
					nWidthRate  = nWidthRate + parseInt( ((ToNumber(tmpArrayHorz[seq]) * ToNumber(nCx)) / ToNumber(fv_SubforgWidth[subseq]) - nWidthRate)/2) - parseInt(obj.Components[i].Width/2);
					nHeightRate = nHeightRate + parseInt( ((ToNumber(tmpArrayVert[seq]) * ToNumber(nCy)) / ToNumber(fv_SubforgHeight[subseq]) - nHeightRate)/2) - parseInt(obj.Components[i].Height/2);

					if ( nWidthRate < 0 ) nWidthRate = 0;
					if ( nHeightRate < 0 ) nHeightRate = 0;
					
					obj.Components[i].left = nWidthRate;
					
					//수정.탑고정
					if (!bTopFixRate) {
						obj.Components[i].top = nHeightRate;
					}
					//trace(bProcSizeFlag + "====" + obj.Components[i].id + " : " + obj.Components[i].left + "top = " + obj.Components[i].top);
					
					seq++;
				}
				else // size 조정 처리.
				{
					nWidthRate  = parseInt( (ToNumber(tmpArrayHorz[seq]) * ToNumber(nCx)) / ToNumber(fv_SubforgWidth[subseq]) );
					nHeightRate = parseInt( (ToNumber(tmpArrayVert[seq]) * ToNumber(nCy)) / ToNumber(fv_SubforgHeight[subseq]) );
					obj.Components[i].left = nWidthRate;
					obj.Components[i].top = nHeightRate;
					seq++;
					nWidthRate  = parseInt( (ToNumber(tmpArrayHorz[seq]) * ToNumber(nCx)) / ToNumber(fv_SubforgWidth[subseq]) );
					nHeightRate = parseInt( (ToNumber(tmpArrayVert[seq]) * ToNumber(nCy)) / ToNumber(fv_SubforgHeight[subseq]) );

					if ( nWidthRate < 0 ) nWidthRate = 0;
					if ( nHeightRate < 0 ) nHeightRate = 0;

					obj.Components[i].right = nWidthRate;	
					
					if (ToLower(obj.id) != "div_search") {
						obj.Components[i].bottom = nHeightRate;				
					}
					//trace(bProcSizeFlag + "====" + obj.Components[i].id + " : " + obj.Components[i].left + "top = " + obj.Components[i].top);
					
					seq++;
				 }
			 }
			 else
			 {
				seq++;
				seq++;
			 }
			 //if ( obj.Components[i].GetType() == "Grid" ) obj.Components[i].FitToArea();
			 if ( obj.Components[i].IsComposite() )
			 {
					//trace("5.2.1.1 Sub===== " + obj.Components[i].id );
					gfn_SubFormResizeProc(obj.Components[i]);
			 }
		 
		 
			i++;
		 
//			trace("5.2.1.1 ===== " + obj.Components[i].id  + " END");
		 }
		 
	//}
	
	if ( obj.GetType() != "TabPage" )
		obj.ResizeScroll();	
	
}

function gfn_frm_OnSize(obj,nCx,nCy,nState)
{
	if ( ( fv_orgWidth == nCx ) && ( fv_orgHeight == nCy ) ) return;

	fv_nResizeProcCnt++;
	
	if ( fv_nResizeProcCnt > 1 )
	{
		fv_nResizeProcCnt--;
		return;
	}
	
	var GapW;
	var GapH;
	
	if ( !fv_FirstResize )
	{
		fv_orgWidth = nCx;
		fv_orgHeight = nCy;
		fv_FirstResize = true;
		fv_nResizeProcCnt--;
		return;
	}

//trace("4.0 ===== onsize : " + this.id);
    gfn_ResizeProc(nCx,nCy);
	ResizeScroll();
	fv_orgWidth = nCx;
	fv_orgHeight = nCy;
	fv_nResizeProcCnt--;
}