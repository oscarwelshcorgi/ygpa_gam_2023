/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.service.impl;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.impl.GamPrtFcltyRentFeeMngtDao;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 4. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 4. 24.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamNticRequestMngtService")
public class GamNticRequestMngtServiceImpl extends AbstractServiceImpl implements
		GamNticRequestMngtService {

    /* 부가세 */
    /** 부가세율 0% */
    public static final BigDecimal VAT_RATE0 = BigDecimal.ZERO;
    /** 부가세율 10% */
    public static final BigDecimal VAT_RATE10 = new BigDecimal("10");

    public static final int INSERT_MODE     = 5;    //등록 모드
    /** 삭제 모드 */
    public static final int DELETE_MODE     = 6;    //삭제 모드

    /* WORK CODE */
    /** 선박입출항 V */
    public static final String WORK_CODE_V = "V";
    /** 화물 C */
    public static final String WORK_CODE_C = "C";
    /** 기타 Z */
    public static final String WORK_CODE_Z = "Z";
    /** 항만부지 P */
    public static final String WORK_CODE_P = "P";
    /** TOC T */
    public static final String WORK_CODE_T = "T";

    /* 연체료 계산 기준일자 */
    /** 20090801 */
    public static final String REV_STD_DATE_20090801 = "20090801";
    /** 20031230 */
    public static final String REV_STD_DATE_20031230 = "20031230";
    /** 20000131 */
    public static final String REV_STD_DATE_20000131 = "20000131";


    /* 연체 최대 기간 */
    /** 60개월 */
    public static final BigDecimal REV_MAX_MONTH = new BigDecimal("60");
    /** 1800일 */
    public static final BigDecimal REV_MAX_DAY = new BigDecimal("1800");

    /* 사용료 및 납부기한 상수 */
    /** 납부기한 15일 */
    public static final int TERM_FOR_PAYMENT15 = 15;
    /** 납부기한 30일 */
    public static final int TERM_FOR_PAYMENT30 = 30;
    /** 사용료 부과 기준 최저 금액 */
    public static final BigDecimal MINIMUM_FEE1000 = new BigDecimal("1000");

	protected Log logger = LogFactory.getLog(this.getClass());

    @Resource(name="gamNticRequestMngtDAO")
    private GamNticRequestMngtDAO gamNticRequestMngtDAO;
    
    @Resource(name="gamPrtFcltyRentFeeMngtDao")
    private GamPrtFcltyRentFeeMngtDao gamPrtFcltyRentFeeMngtDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#sendNticRequest(java.util.Map)
	 */
	@Override
	public void sendNticRequest(Map<String, Object> vo) throws Exception {
        Calendar cal = Calendar.getInstance();
        Map map = gamNticRequestMngtDAO.selectNticNoAccnutYear(vo);
//		vo.put("accnutYear", Integer.toString(cal.get(Calendar.YEAR)));	// 회계년도를 입력한다.
        vo.put("accnutYear", map.get("accnutYear"));
		vo.put("nticno", map.get("nticno"));
		vo.put("nhtIsueYn", "Y");
		Map map2 = gamNticRequestMngtDAO.selectReimNticNoAccnutYear(vo);
		vo.put("reimFeeNticno", map2.get("reimFeeNticno"));
		/*
		if(!"".equals(vo.get("intrRate"))) {	// 이자율이 입력 되어 있으면 이자에 대해 고지서를 생성한다.
			// 이자 생성
			String intrAmnt = gamNticRequestMngtDAO.selectLevReqestInterest(vo);	// 이자를 산출한다.

			vo.put("intrAmnt", intrAmnt);	// 이자 고지
			gamNticRequestMngtDAO.updateLevReqestIssueYn(vo);
			gamNticRequestMngtDAO.insertNticRequestRevCollF(vo);
			gamNticRequestMngtDAO.insertNticRequestInterestRevCollF(vo);
		}
		else {
		*/
			gamNticRequestMngtDAO.updateLevReqestIssueYn(vo);
			gamNticRequestMngtDAO.insertNticRequestRevCollF(vo);
			/*
		}
		*/

	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#cancelNticRequest(java.util.Map)
	 */
	@Override
	public void cancelNticRequest(Map<String, Object> vo) throws Exception {
		Map map =gamNticRequestMngtDAO.selectNticRequestRcvdTp(vo);	// 수납 여부를 조회한다.
		if("3".equals((String)map.get("rcvdTp"))) {	// 수납 여부 확인
			throw processException("fail.cancelNticIssue.msg");
		}
		vo.put("nhtPrintYn", "N");
		if("Y".equals((String)map.get("billPrtYn"))) {
			egiroPrintCancel(vo);    // 고지가 된 경우 고지 취소를 한다. 2014-08-13 eunsungj.
			gamNticRequestMngtDAO.updateRevCollFBillPrintYn(vo);
		}
		gamNticRequestMngtDAO.deleteNticRequestRevCollF(vo);	// 고지정보를 삭제한다.
		vo.put("accnutYear", "");
		vo.put("nticno", "");
		vo.put("nhtIsueYn", "N");
		gamNticRequestMngtDAO.updateLevReqestIssueYn(vo);	// 고지를 취소한다.
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void egiroPrint(Map map) throws Exception{
    	Map mapResult = new HashMap();
    	Date date = new Date();
    	SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
    	String currentDate = formater.format(date);

    	logger.debug("******************** 전자고지체계구축 고지출력 시작 !!!! ******************** \n * map : " + map);

    	Map revCollF = gamNticRequestMngtDAO.selectRevCollF(map);

    	String strPrtAtCode = (String)revCollF.get("prtAtCode") ;
    	String strPrtAtNum = "" ;
    	//String strCustomerNum = (String)map.get("customerNum") ;
    	String strCustomerNum = "" ;
    	BigDecimal bdAmount = new BigDecimal(revCollF.get("billAmnt").toString());
    	BigDecimal bdBillSumAmnt = new BigDecimal(revCollF.get("billSumAmnt").toString());
    	String strDueDate = (String)revCollF.get("dueDate") ;
    	String strCloseDate = (String)revCollF.get("dueDate") ;
    	String strBillDate =  (String)revCollF.get("billDt") ;
    	String strGiroNum = "" ;
    	String strEgiroNum = "" ;
    	String strAgentCode = (String)revCollF.get("agentCode") ;
    	String strBillType = "" ;
    	String strBzRgstId = (String)revCollF.get("bzRgstId") ;
    	String strBillYyyymm = strBillDate.substring(0, 6) ;
    	String strKorNm = "" ;		// SELECT SHP_OWOP_F
    	String strAddr = "" ;		// SELECT SHP_OWOP_F
    	String strFeeTp = (String)revCollF.get("feeTp") ;
    	String strFiscalYr = (String)revCollF.get("fiscalYr") ;
    	String strBillNo = (String)revCollF.get("billNo") ;
    	String strDlySerNo = (String)revCollF.get("dlySerNo");
    	String strFeeTpNum = "" ;

    	if ( "620".equals(strPrtAtCode) ) {			// 여수
    		strPrtAtNum = "1" ;
    		strGiroNum = "6374604" ;
    	} else if ( "621".equals(strPrtAtCode) ) {	// 여천
    		strPrtAtNum = "2" ;
    		strGiroNum = "6374604" ;
    	} else if ( "622".equals(strPrtAtCode) ) {	// 광양
    		strPrtAtNum = "3" ;
    		strGiroNum = "6374594" ;
    	}

    	// 연체건인지 아닌지를 판단
    	if( "".equals(strDlySerNo) || strDlySerNo == null ) {
    		strDlySerNo = "00" ;
    		strBillType = "0" ;
    		strCustomerNum = "0" ;
    	} else {
    		strBillType = "1" ;
    		strCustomerNum = strDlySerNo ;
    		strDueDate = (String)revCollF.get("dlyDueDt");
    	}

    	mapResult.put("prtAtCode", strPrtAtCode );
    	mapResult.put("feeTp", strFeeTp );
    	mapResult.put("fiscalYr", strFiscalYr );
    	mapResult.put("billNo", strBillNo );
    	mapResult.put("dlySerNo", strDlySerNo );

    	// 당일 고지발행건인지 아닌지 확인
    	Map egiroMap = gamNticRequestMngtDAO.getWorkDtSysdateInfo(mapResult);

    	if( egiroMap == null || egiroMap.isEmpty() ){

        	// 대표자명, 주소를 찾아오는 쿼리
        	Map agentMap = gamNticRequestMngtDAO.getEgiroAgentInfo(revCollF);
        	strKorNm = (String)agentMap.get("korNm");
        	strAddr = (String)agentMap.get("addr");

        	// 요금종류코드(3자리)
        	Map feeMap = gamNticRequestMngtDAO.getEgiroFeeTpMap(mapResult);
        	strFeeTpNum = (String)feeMap.get("feeTpMap");

        	strEgiroNum = strPrtAtNum + strFeeTpNum + strFiscalYr.substring(2, 4) + strBillNo + strDlySerNo ;

        	mapResult.put("customerNum", strCustomerNum );
        	mapResult.put("amount", bdBillSumAmnt );
        	mapResult.put("amouuntAf", bdBillSumAmnt );
        	mapResult.put("dueDate", strDueDate.substring(0, 8) );
        	mapResult.put("closeDate", strCloseDate.substring(0, 8) );
        	mapResult.put("giroNum", strGiroNum );
        	mapResult.put("egiroNum", strEgiroNum );
        	mapResult.put("agentCode", strAgentCode );
        	mapResult.put("billType", strBillType );
        	mapResult.put("korNm", strKorNm );
        	mapResult.put("bzRgstId", strBzRgstId );
        	mapResult.put("billYyyymm", strBillYyyymm );
        	mapResult.put("addr", strAddr );
        	mapResult.put("printDt", currentDate);
        	mapResult.put("cancelDt", "");

        	Map MakeDigit = gamNticRequestMngtDAO.getSfMakeDigit(mapResult);
        	mapResult.put("makedigit", (String)MakeDigit.get("makedigit"));

        	// 전자고지체계구축 고지출력
        	gamNticRequestMngtDAO.insertEgiroPrint(mapResult);
    	} else {
    		logger.debug("*** [고지서출력취소하지 않고 연속적 출력이므로 EGIRO 에  INSERT 불가함] ***");
    	}

    	logger.debug("******************** 전자고지체계구축 고지출력 끝 ********************");

    	//return mapResult ;
    }

    /**
     * 연체료 분리 고지 처리
     * @param map
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void egiroPrint2(Map map) throws Exception{
    	Map mapResult = new HashMap();
    	String currentDate = "";
    	String strKorNm = "" ;
    	String strAddr = "" ;
    	String strFeeTpNum = "" ;
    	String strEgiroNum = "" ;

    	logger.debug("******************** 전자고지체계구축 고지출력 시작 (사용료 연체 분리 고지) !!!! ******************** \n * map : " + map);

    	EgovMap egiroInfo = gamNticRequestMngtDAO.selectEgiroInfoByPk(map);

    	if(egiroInfo==null || egiroInfo.isEmpty()) {
    		logger.warn("고지서 출력을 위한 자료를 찾을 수 없습니다.");
    		throw processException("fail.request.msg");
    	}
    	mapResult.putAll(egiroInfo);

    	// 당일 고지발행건인지 아닌지 확인
    	Map egiroMap = gamNticRequestMngtDAO.getWorkDtSysdateInfo(mapResult);

    	if( egiroMap != null && !egiroMap.isEmpty() ){
    		gamNticRequestMngtDAO.updateEgiroPrintCancel(mapResult);
    		// 연체 고지 삭제
    		String feeTp=(String)mapResult.get("feeTp");
    		String dlySerNo=(String)mapResult.get("dlySerNo");
    		mapResult.put("feeTp", "UG");
    		mapResult.put("dlySerNo", mapResult.get("arrrgNo"));
        	egiroMap = gamNticRequestMngtDAO.getWorkDtSysdateInfo(mapResult);

    		gamNticRequestMngtDAO.updateEgiroPrintCancel(egiroMap);
    	}

    	if( egiroMap == null || egiroMap.isEmpty() ){

    		currentDate=(String)egiroInfo.get("currentDate");
        	// 대표자명, 주소를 찾아오는 쿼리
        	Map agentMap = gamNticRequestMngtDAO.getEgiroAgentInfo(mapResult);
        	strKorNm = (String)agentMap.get("korNm");
        	strAddr = (String)agentMap.get("addr");

        	// 요금종류코드(3자리)
        	Map feeMap = gamNticRequestMngtDAO.getEgiroFeeTpMap(mapResult);
        	strFeeTpNum = (String)feeMap.get("feeTpMap");

        	strEgiroNum = (String)egiroInfo.get("prtAtNum") + strFeeTpNum + ((String)egiroInfo.get("fiscalYr")).substring(2, 4) + (String)egiroInfo.get("billNo") + (String)egiroInfo.get("dlySerNo") ;

        	mapResult.put("egiroNum", strEgiroNum );
        	mapResult.put("korNm", strKorNm );
        	mapResult.put("addr", strAddr );
        	mapResult.put("printDt", currentDate);
        	mapResult.put("cancelDt", "");

        	Map MakeDigit = gamNticRequestMngtDAO.getSfMakeDigit(mapResult);

        	mapResult.put("makedigit", (String)MakeDigit.get("makedigit"));

        	// 전자고지체계구축 고지출력 (원금 재 고지)
        	gamNticRequestMngtDAO.insertEgiroPrint2(mapResult);
        	// 연체료 고지
        	mapResult.put("dlySerNo", mapResult.get("arrrgNo"));
        	mapResult.put("billType", "1");
        	mapResult.put("customerNo", mapResult.get("arrrgNo"));
        	mapResult.put("amount", mapResult.get("arrrgAmt"));
        	mapResult.put("amountAf", mapResult.get("arrrgAmt"));

        	gamNticRequestMngtDAO.insertEgiroPrint2(mapResult);

    	}

    	logger.debug("******************** 전자고지체계구축 고지출력 끝 ********************");

    	//return mapResult ;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void egiroPrintCancel(Map map) throws Exception{
    	Map mapResult = new HashMap();

    	// (서버)현재날짜
    	Date date = new Date();
    	SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
    	String currentDate = formater.format(date);

    	logger.debug("******************** 전자고지체계구축 고지출력취소 시작 !!!! ******************** \n * map : " + map);

    	Map revCollF = gamNticRequestMngtDAO.selectRevCollF(map);
    	/*** 변수세팅 시작 ***/
    	String strPrtAtCode = (String)revCollF.get("prtAtCode") ;
    	String strPrtAtNum = "" ;
    	//String strCustomerNum = (String)map.get("customerNum") ;
    	String strCustomerNum = "" ;
    	BigDecimal bdAmount = new BigDecimal(revCollF.get("billAmnt").toString());
    	BigDecimal bdBillSumAmnt = new BigDecimal(revCollF.get("billSumAmnt").toString());
    	String strDueDate = (String)revCollF.get("dueDate") ;
    	String strCloseDate = (String)revCollF.get("dueDate") ;
    	String strBillDate =  (String)revCollF.get("billDt") ;
    	String strGiroNum = "" ;
    	String strEgiroNum = "" ;
    	String strAgentCode = (String)revCollF.get("agentCode") ;
    	String strBillType = "" ;
    	String strBzRgstId = (String)revCollF.get("bzRgstId") ;
    	String strBillYyyymm = strBillDate.substring(0, 6) ;
    	String strKorNm = "" ;		// SELECT SHP_OWOP_F
    	String strAddr = "" ;		// SELECT SHP_OWOP_F
    	String strFeeTp = (String)revCollF.get("feeTp") ;
    	String strFiscalYr = (String)revCollF.get("fiscalYr") ;
    	String strBillNo = (String)revCollF.get("billNo") ;
    	String strDlySerNo = (String)revCollF.get("dlySerNo");
    	String strFeeTpNum = "" ;

    	if ( "620".equals(strPrtAtCode) ) {			// 여수
    		strPrtAtNum = "1" ;
    		strGiroNum = "6374604" ;
    	} else if ( "621".equals(strPrtAtCode) ) {	// 여천
    		strPrtAtNum = "2" ;
    		strGiroNum = "6374604" ;
    	} else if ( "622".equals(strPrtAtCode) ) {	// 광양
    		strPrtAtNum = "3" ;
    		strGiroNum = "6374594" ;
    	}

    	// 연체건인지 아닌지를 판단
    	if( "".equals(strDlySerNo) || strDlySerNo == null ) {
    		strDlySerNo = "00" ;
    		strBillType = "0" ;
    		strCustomerNum = "0" ;
    	} else {
    		strBillType = "1" ;
    		strCustomerNum = strDlySerNo ;
    	}

    	// 대표자명, 주소를 찾아오는 쿼리
    	Map agentMap = gamNticRequestMngtDAO.getEgiroAgentInfo(revCollF);
    	strKorNm = (String)agentMap.get("korNm");
    	strAddr = (String)agentMap.get("addr");

    	mapResult.put("prtAtCode", strPrtAtCode );
    	mapResult.put("feeTp", strFeeTp );
    	mapResult.put("fiscalYr", strFiscalYr );
    	mapResult.put("billNo", strBillNo );
    	mapResult.put("dlySerNo", strDlySerNo );

    	// 요금종류코드(3자리)
    	Map feeMap = gamNticRequestMngtDAO.getEgiroFeeTpMap(revCollF);
    	strFeeTpNum = (String)feeMap.get("feeTpMap");

    	strEgiroNum = strPrtAtNum + strFeeTpNum + strFiscalYr.substring(2, 4) + strBillNo + strDlySerNo ;
    	/*** 변수세팅 끝 ***/

    	// 고지발행건인지 아닌지 확인
    	Map egiroMap = gamNticRequestMngtDAO.getWorkDtInfo(mapResult);
    	if( egiroMap == null || egiroMap.isEmpty() ){
    		logger.debug("*** [고지서가 출력된 적이 없는 데이터이므로 고지서출력취소 자체가 불가함] ***");
    	} else {
        	String egiroWorkDt 		= (String)egiroMap.get("workDt") ;
        	String egiroCancelDt 	= (String)egiroMap.get("cancelDt") ;
        	String egiroPrintDt 	= (String)egiroMap.get("printDt") ;
        	String egiroTranId 		= (String)egiroMap.get("tranid") ;
        	String egiroCurrDt = (String)egiroMap.get("currDt") ;
        	String currAfterOneDay	= "";
        	//logger.debug(" *** egiroMap : " + egiroMap);
        	//logger.debug(" *** currentDate : " + currentDate);
        	//logger.debug(" *** egiroWorkDt : " + egiroWorkDt);
        	//logger.debug(" *** egiroCancelDt : " + egiroCancelDt);
        	//logger.debug(" *** currentDate.equals(egiroWorkDt) : " + currentDate.equals(egiroWorkDt) );
        	//logger.debug(" *** (egiroCancelDt == null || .equals(egiroCancelDt)) : " + (egiroCancelDt == null || "".equals(egiroCancelDt)) );

        	mapResult.put("tranId", egiroTranId );
        	mapResult.put("cancelDt", currentDate );

        	// 전자고지체계구축 고지출력취소
        	if( egiroCurrDt.equals(egiroWorkDt) && (egiroCancelDt == null || "".equals(egiroCancelDt)) ){
        		// 당일고지발행 했다가 취소했을 경우(고지건이 취소시 이미 생성되어 있으므로 UPDATE)
        		gamNticRequestMngtDAO.updateEgiroPrintCancel(mapResult);
        	} else {
        		mapResult.put("printDt", "" );
        		mapResult.put("customerNum", strCustomerNum );
        		mapResult.put("amount", bdBillSumAmnt );
        		mapResult.put("amouuntAf", bdBillSumAmnt );
        		mapResult.put("dueDate", strDueDate.substring(0, 8) );
        		mapResult.put("closeDate", strCloseDate.substring(0, 8) );
        		mapResult.put("giroNum", strGiroNum );
        		mapResult.put("egiroNum", strEgiroNum );
        		mapResult.put("agentCode", strAgentCode );
        		mapResult.put("billType", strBillType );
        		mapResult.put("korNm", strKorNm );
        		mapResult.put("bzRgstId", strBzRgstId );
        		mapResult.put("billYyyymm", strBillYyyymm );
        		mapResult.put("addr", strAddr );

            	Map MakeDigit = gamNticRequestMngtDAO.getSfMakeDigit(mapResult);
            	mapResult.put("makedigit", (String)MakeDigit.get("makedigit"));

        		// 전일 고지서 발행건을 당일 취소할 경우(당일 WORK_DT 인 고지건이 없으므로 INSERT)
        		gamNticRequestMngtDAO.insertEgiroPrint(mapResult);
        	}
    	}

    	logger.debug("******************** 전자고지체계구축 고지출력취소 끝 !!!! ********************");
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#sendUnpaidRequest(java.util.Map)
	 */
	@Override
	public void sendUnpaidRequest(Map<String, Object> vo) throws Exception {
    	String	strMagamCheck	= null;
		String	strDlyRcvdDt	= (String)vo.get("dlyRcvdDt");
		String	strDlyBillDt	= (String)vo.get("dlyBillDt");
		Map mapMagamInfo = new HashMap();
		mapMagamInfo.put("prtAtCode", vo.get("prtAtCode"));
		mapMagamInfo.put("magamCheckDt", strDlyBillDt);

		mapMagamInfo = gamNticRequestMngtDAO.selectPortmisMagamInfo(mapMagamInfo);	// 마감 체크

		if(mapMagamInfo!=null) {
			strMagamCheck = (String)mapMagamInfo.get("magamCheck");
			if("Y".equals(strMagamCheck)){
				processException("fail.closeMonthCannotNotice.msg");
			}
		}
		// 자동이체건의 경우는 사용료 내역 송신 상태로 변경해줌. 이라고 포트미스에 써있으나 포트미스 연계가 아니므로 안됨
//    	if(gamNticRequestMngtDAO.selectPortRevAnlrveLevMgtFeeIcheFCnt(vo) > 0){
//    		map.put("icheStatus", "1");
//    	}

		String dlySerNo= gamNticRequestMngtDAO.selectUnpaidMaxNo(vo);
		vo.put("dlySerNo", dlySerNo);

		// 기존 연체 정보 조회
		List list = gamNticRequestMngtDAO.selectLevReqestArrrgAmt(vo);

		BigDecimal newArrrgAmt = new BigDecimal((String)vo.get("dlyBillAmnt"));

		if(list.size()>0) {
			Map map = (Map)list.get(0);
			String payPassCheck = (String)map.get("payPassCheck");
			if("P".equals(payPassCheck)) {
				processException("fail.nticArrg.confirm");
			}
			BigDecimal nticAmt = (BigDecimal)map.get("nticAmt");
			BigDecimal arrrgAmt = (BigDecimal)map.get("arrrgAmt");
			if(arrrgAmt.compareTo(BigDecimal.ZERO)>0) {
				newArrrgAmt = newArrrgAmt.subtract(arrrgAmt);	// 이 금액을 추가 고지 한다.
				// 추가 고지 한다.
			}
			vo.put("dBillAmnt", newArrrgAmt);
		}
		else vo.put("dBillAmnt", newArrrgAmt);

		gamNticRequestMngtDAO.insertNticRequestRevCollFC1(vo);

		vo.put("rcivSe", "1");	// 연체 상태 세팅
		gamNticRequestMngtDAO.updateLevReqestArrrgAmt(vo);	// 변경된 연체 금액을 저장한다.

//		gamNticRequestMngtDAO.removeNticRequestRevCollFC1(vo);
//		gamNticRequestMngtDAO.insertNticRequestRevCollFC1(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#updateNticPrintYn(java.util.Map)
	 */
	@Override
	public void updateNticPrintState(Map<String, Object> vo) throws Exception {
		Map map = gamNticRequestMngtDAO.selectLevRequestByPk(vo);
		gamNticRequestMngtDAO.updateLevReqestNhtPrintYn(vo);
		if(map.get("arrrgNo")!=null) {
			// 연체 고지
			gamNticRequestMngtDAO.updateUnpaidFBillPrintYn(vo);
		}
		else {
			gamNticRequestMngtDAO.updateRevCollFBillPrintYn(vo);
		}
		// 2014-08-13 전자고지 출력
		if("Y".equals(vo.get("nhtPrintYn"))) {
	    	egiroPrint(vo);
		}
		else {
			egiroPrintCancel(vo);
		}
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#updateNticPrintYn(java.util.Map)
	 */
	@Override
	public void updateNticPrintState2(Map<String, Object> vo) throws Exception {
		Map map = gamNticRequestMngtDAO.selectLevRequestByPk(vo);
		gamNticRequestMngtDAO.updateLevReqestNhtPrintYn(vo);
		if(map.get("arrrgNo")!=null) {
			// 연체 고지
			gamNticRequestMngtDAO.updateUnpaidFBillPrintYn(vo);
		}
		else {
			processException("fail.request.msg");
		}
    	egiroPrint2(vo);
	}

    /**
     * String 또는 Date로 전달받은 날짜를 Calendar로 변환하여 리턴한다.
     * date가 null이면 오늘 날짜를 리턴함.
     * date 타입이 String일 경우 8 또는 12자리이어야 한다. 그렇지 않으면 오늘 날짜를 리턴함.
     * @param date - 날짜
     * @return Calendar - Calendar로 변환된 날짜
     * @exception Exception
     */

    public Calendar getCalendarDate(Object date) throws Exception {
        Calendar retCal = Calendar.getInstance();
        Date tDate;
        String DATE_FORMAT = "yyyyMMdd";
        String DATETIME_FORMAT = "yyyyMMddHHmm";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        java.text.SimpleDateFormat sdfTime = new java.text.SimpleDateFormat(DATETIME_FORMAT);

        if ( date instanceof Date ) {
            retCal.setTime( (Date) date );
        } else if ( date instanceof String ) {
            if ( ((String) date).length() == 8 ) {
                //String으로 넘어온 일자를 Date형으로 바꿈
                tDate = sdf.parse((String) date);
                //Date형의 일자를 Calendar형으로 바꿈
                retCal.setTime( tDate );
            } else if ( ((String) date).length() == 12 ) {
                //String으로 넘어온 일자를 Date형으로 바꿈
                tDate = sdfTime.parse((String) date);
                //Date형의 일자를 Calendar형으로 바꿈
                retCal.setTime( tDate );
            } else {
                if (logger.isDebugEnabled()) logger.debug("전달받은 날짜가 없거나 8, 12자리가 아님, 오늘 날짜기준으로 구함 date = "+date);
            }
        } else {
            if (logger.isDebugEnabled()) logger.debug("전달받은 날짜가 없거나 8, 12자리가 아님, 오늘 날짜기준으로 구함 date = "+date);
        }


        return retCal;
    }

    /**
     * 전달된 두 날짜의 년, 월, 일 차이를 리턴한다.
     * @param Map - String dt_fr, String dt_to
     * @return Map - diff_year, diff_month, diff_day
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public Map getDiffDates(Map map) throws Exception {
        Map searchMap  = new HashMap();

        searchMap.put("dt_fr", ((String) map.get("dt_fr")).substring(0, 8));
        searchMap.put("dt_to", ((String) map.get("dt_to")).substring(0, 8));

//        return getDiffDates((Map)searchMap);
        return null;
    }
    /**
     * 오늘 날짜를 이용하여 회계년도를 구한다.(세입자료항목 중 회계년도 구할 때 사용)
     * @param 없음
     * @return Map - fiscal_yr 회계년도
     * @exception Exception
     */
    public Map getFiscalYr() throws Exception {
        //if (logger.isDebugEnabled()) logger.debug("---------- IMPL getFiscalYr start ----------");
        Map retMap = new HashMap();
        Calendar cal = Calendar.getInstance();

        retMap.put( "fiscal_yr", Integer.toString(cal.get(Calendar.YEAR)) );

        if (logger.isDebugEnabled()) logger.debug("---------- IMPL getFiscalYr end ---------- 결정된 회계년도 = " + retMap);
        return retMap;
    }

    /**
     * 납부기한을 구한다.
     * @param pIoDate - 입출항일자
     * @param pTerm - 기간(일) TERM_FOR_PAYMENT15, TERM_FOR_PAYMENT30
     * @return Map - due_date 납부기한
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public Map getDueDate(int pTerm) throws Exception {
        Map retMap = new HashMap();
        Calendar cal = Calendar.getInstance();
        Date tIoDate, tDueDate;
        String dueDate;
        String DATE_FORMAT = "yyyyMMdd";
        String DATETIME_FORMAT = "yyyyMMddHHmm";
        String DATETIMESEC_FORMAT = "yyyyMMddHHmmss";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        java.text.SimpleDateFormat sdfTime;

        //납부기한 = 현재날짜 + 기간(일)
        if ( pTerm == TERM_FOR_PAYMENT15 ) {
            cal.add(Calendar.DATE, TERM_FOR_PAYMENT15);
        } else if ( pTerm == TERM_FOR_PAYMENT30 ) {
            cal.add(Calendar.DATE, TERM_FOR_PAYMENT30);
        } else {
            cal.add(Calendar.DATE, TERM_FOR_PAYMENT15);
        }

        //Calendar형의 납부기한을 Date형으로 바꿈
        tDueDate = cal.getTime();
        //Date형의 납부기한을 String형으로 바꿈
        dueDate = sdf.format(tDueDate);

        retMap.put( "due_date", dueDate );
        return retMap;
    }

    /**
     * 연체료, 산출내역 (을)를 얻는다.
     * @param Map - bill_amnt 원고지금액, payTmlmt 원고지납부기한, nticDt 원고지일자, dlyPayTmlmt 연체납부기한, dly_bill_dt 연체고지일자
     * , chrgeKnd 원고지요금종류, nticno 원고지번호
     * @return Map - dly_bill_amnt 연체료, dly_bill_rsn 연체료 산출내역
     * @exception Exception
     */
    public Map calculateDlyBillAmnt(Map map) throws Exception {
        if (logger.isDebugEnabled()) logger.debug("---------- IMPL calculateDlyBillAmnt start map ---------- = " + map);
        Map retMap = new HashMap();     //리턴 맵
        Map searchMap = new HashMap();
        Map dlyTermMap = new HashMap();

        BigDecimal dlyBillAmnt = null;     //연체료
        StringBuffer dlyBillRsn = new StringBuffer();    //연체료 산출내역

        BigDecimal stdBillAmnt;             //연체료 계산 기준 금액
        BigDecimal termMonth, termDay;      //연체월 수, 연체일 수
        BigDecimal dlyRate = null;                 //연체이율

        BigDecimal billAmnt = new BigDecimal( map.get("nticAmt").toString() );
        //Calendar billDt = p2FrgCmmService.getCalendarDate(map.get("nticDt"));
        Calendar billDt = getCalendarDate(map.get("nticDt"));
        String feeTp  = (String) map.get("chrgeKnd");
        String billNo = (String) map.get("nticno");

        //연체료 계산 기준 금액 ( VAT가 없는 고지금액은 고지금액 그대로, VAT가 있는 고지금액은 원금으로 연체료를 계산해야 한다. )
        //그러나, 부가세 전 원금을 정확히 얻을 수 없으므로, 기준금액은 아래의 식으로 얻는다. (VAT있는 고지금액은 원단위를 올림으로 처리한다.)
        stdBillAmnt = billAmnt.abs();
        stdBillAmnt = stdBillAmnt.multiply(VAT_RATE10);
        stdBillAmnt = stdBillAmnt.divide(new BigDecimal("100"), 0, RoundingMode.CEILING);
        //stdBillAmnt = stdBillAmnt.setScale(0, RoundingMode.CEILING);    //소수점 올림
        stdBillAmnt = stdBillAmnt.multiply(VAT_RATE10);


        //연체월, 일 수 계산 ; 원고지납부기한과 연체납부기한 사이
        searchMap.put("dt_fr", map.get("payTmlmt"));
        searchMap.put("dt_to", map.get("dlyPayTmlmt"));
        dlyTermMap = getDiffDates(searchMap);
        //dlyTermMap = p2FrgCmmService.getDiffDates(searchMap);

        if(dlyTermMap!=null) {
	        termMonth = (BigDecimal) dlyTermMap.get("diff_month");
	        termDay   = (BigDecimal) dlyTermMap.get("diff_day");
        }
        else {
        	termMonth = BigDecimal.ZERO;
        	termDay = BigDecimal.ZERO;
        }

        //if ( billDt.compareTo(p2FrgCmmService.getCalendarDate(p2CmmIPAService.REV_STD_DATE_20090801)) >= 0 ) {  //2009.08.01 이후
            //연체월, 일 수 재계산 ; 원고지납부기한과 연체고지일자 사이
            searchMap.put("dt_fr", map.get("payTmlmt"));
            searchMap.put("dt_to", map.get("dly_bill_dt"));
            //dlyTermMap = p2FrgCmmService.getDiffDates(searchMap);
            dlyTermMap = getDiffDates(searchMap);

            if(dlyTermMap!=null) {
	            termMonth = (BigDecimal) dlyTermMap.get("diff_month");
	            termDay   = (BigDecimal) dlyTermMap.get("diff_day");
            }
            else {
            	termMonth = BigDecimal.ZERO;
            	termDay = BigDecimal.ZERO;
            }

            //최대연체기간 확인
            /*if ( termMonth.compareTo(p2CmmIPAService.REV_MAX_MONTH) > 0
                    || termDay.compareTo(p2CmmIPAService.REV_MAX_DAY) >= 0 ) {
                termMonth = p2CmmIPAService.REV_MAX_MONTH;
                termDay   = p2CmmIPAService.REV_MAX_DAY;
            }*/
            if ( termMonth.compareTo(REV_MAX_MONTH) > 0
                    || termDay.compareTo(REV_MAX_DAY) >= 0 ) {
                termMonth = REV_MAX_MONTH;
                termDay   = REV_MAX_DAY;
            }

            //100만원 미만일 경우 무조건 연 12% 적용
            if ( billAmnt.compareTo(new BigDecimal("1000000")) < 0 ) {
                dlyRate = new BigDecimal("0.12");       //12%

            } else {    //나머지는 연체 기간에 따라 차등적용
                if ( termMonth.compareTo(new BigDecimal("1")) < 0 ) {
                    dlyRate = new BigDecimal("0.12");   //12%
                } else if ( termMonth.compareTo(new BigDecimal("3")) < 0 ) {
                    dlyRate = new BigDecimal("0.13");   //13%
                } else if ( termMonth.compareTo(new BigDecimal("6")) < 0 ) {
                    dlyRate = new BigDecimal("0.14");   //14%
                } else {
                    dlyRate = new BigDecimal("0.15");   //15%
                }
            }

            dlyBillAmnt = stdBillAmnt.multiply(dlyRate);
            dlyBillAmnt = dlyBillAmnt.multiply(termDay);
            dlyBillAmnt = dlyBillAmnt.divide(new BigDecimal("365"), 0, RoundingMode.FLOOR);  //소수점 버림
            //dlyBillAmnt = dlyBillAmnt.setScale(0, RoundingMode.FLOOR);

            if (logger.isDebugEnabled()) logger.debug("\n=====>dlyBillAmnt33 = " + dlyBillAmnt);

            dlyBillRsn = dlyBillRsn.append(feeTp)
                                   .append(" ")
                                   .append(billNo)
                                   .append("가산금-(")
                                   .append(stdBillAmnt.toPlainString())
                                   .append("*")
                                   .append(dlyRate.toPlainString())
                                   .append("*")
                                   .append(termDay.toPlainString())
                                   .append("/365)");
        //} else if ( billDt.compareTo(p2FrgCmmService.getCalendarDate(p2CmmIPAService.REV_STD_DATE_20031230)) >= 0 ) {   //2003.12.30 이후

        dlyBillAmnt = dlyBillAmnt.setScale(-1, RoundingMode.FLOOR);    //1원단위 버림
        dlyBillAmnt = dlyBillAmnt.setScale(0);

        if (logger.isDebugEnabled()) logger.debug("\n=====>stdBillAmnt = " + stdBillAmnt);
        if (logger.isDebugEnabled()) logger.debug("\n=====>termMonth = " + termMonth);
        if (logger.isDebugEnabled()) logger.debug("\n=====>termDay = " + termDay);
        if (logger.isDebugEnabled()) logger.debug("\n=====>dlyRate = " + dlyRate);
        if (logger.isDebugEnabled()) logger.debug("\n=====>dly_bill_amnt = " + dlyBillAmnt);
        if (logger.isDebugEnabled()) logger.debug("\n=====>dly_bill_rsn = " + dlyBillRsn);


        retMap.put("dly_bill_amnt", dlyBillAmnt);
        retMap.put("dly_bill_rsn", dlyBillRsn);

        if (logger.isDebugEnabled()) logger.debug("\n=====> IMPL calculateDlyBillAmnt end retMap ---------- = " + retMap);

        /* BigDecimal truncate 테스트 */
        /*BigDecimal tmp = new BigDecimal("123456.789");
        tmp = tmp.setScale(1, BigDecimal.ROUND_DOWN);
        if (logger.isDebugEnabled()) logger.debug("BigDecimal 1;"+tmp);
        tmp = tmp.setScale(0, BigDecimal.ROUND_DOWN);
        if (logger.isDebugEnabled()) logger.debug("BigDecimal 0;"+tmp);
        tmp = tmp.setScale(-1, BigDecimal.ROUND_DOWN);
        if (logger.isDebugEnabled()) logger.debug("BigDecimal -1;"+tmp);*/
        return retMap;
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#sendMultiUnpaidRequest(java.util.List)
	 */
	@Override
	public void sendMultiUnpaidRequest(List list) throws Exception {
		//
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#cancelUnpaidRequest(java.util.Map)
	 */
	@Override
	public void cancelUnpaidRequest(Map<String, Object> vo) throws Exception {

//    	Map mapUnpaidInfo = revArrrgAnlrveLevMgtDAO.selectPortRevArrrgAnlrveLevMgtOverMaxDlySerNo(map);
//    	if(mapUnpaidInfo != null){
//    		String strOverMaxDlySerNo = (String)mapUnpaidInfo.get("overMaxDlySerNo");
//    		if(!"00".equals(strOverMaxDlySerNo)){
//    			// alert("연체횟수 99 (으)로 고지된 연체 자료가 존재 합니다.\n먼저 삭제 해 주십시오.");
//    			String strMsg = "연체횟수 " + strOverMaxDlySerNo + " (으)로 고지된 연체 자료가 존재 합니다.\n먼저 삭제 해 주십시오.";
//    			mapRtn.put("rtnCode", "-1");
//    			mapRtn.put("rtnMsg", strMsg);
//
//    			return mapRtn;
//    		}
//    	}

		List list = gamNticRequestMngtDAO.selectLevReqestArrrgAmt(vo);
		String dlySerNo = (String)vo.get("dlySerNo");
		Map map;

		for(int i=0; i<list.size(); i++) {
			map = (Map)list.get(i);

			if("Y".equals(map.get("dlyBillPrtYn"))) {	// 고지 출력이 되어 있으면 고지 출력을 취소 한다.
				map.put("dlyBillPrtYn", "N");
				gamNticRequestMngtDAO.updateUnpaidPrintYn(map);
			}
			if(dlySerNo.equals(map.get("dlySerNo"))) {
				break;	// 여기까지 연체를 취소 한다.
			}
		}

		gamNticRequestMngtDAO.updateLevReqestUnarrrgAmt(vo);	// 연체를 취소한다.
		gamNticRequestMngtDAO.deleteNticRequestRevCollFC1(vo);	// 고지자료를 모두 삭제 한다.
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#cancelUnpaidRequestPk(java.util.Map)
	 */
	@Override
	public void cancelUnpaidRequestPk(Map<String, Object> vo) throws Exception {
		String maxSerNo = null;
		List list = gamNticRequestMngtDAO.selectLevReqestArrrgAmt(vo);
		Map map = null;

		String dlySerNo = (String)vo.get("dlySerNo");

		if(list==null || list.size()==0) {
			throw processException("fail.nticArrg.cancel");
		}

		map = (Map) list.get(0);

		// 연체 금액을 감산한다.
		BigDecimal bdArrrgAmt = (BigDecimal)map.get("arrrgAmt");
		BigDecimal bdNticAmt = (BigDecimal)map.get("nticAmt");
		BigDecimal bdFee = (BigDecimal)map.get("fee");
		bdArrrgAmt=BigDecimal.ZERO;

		int i;
		map = new HashMap(vo);

		for(i=0; i<list.size(); i++) {
			map.putAll((Map)list.get(i));

			if("Y".equals(map.get("dlyBillPrtYn"))) {	// 고지 출력이 되어 있으면 고지 출력을 취소 한다.
				map.put("dlyBillPrtYn", "N");
				gamNticRequestMngtDAO.updateUnpaidPrintYn(map);
				//egiroPrintCancel(map);
			}

			gamNticRequestMngtDAO.deleteUnpaidByPk(map);
			if(dlySerNo.equals(map.get("dlySerNo"))) {
				break;	// 여기까지 연체를 취소 한다.
			}
		}

		if(list.size()==1) {
			// 최초 연체를 취소 하므로 연체가 없어짐
			logger.info("first delay bill cancel");
			bdNticAmt=bdFee;
			vo.put("rcivSe", "0");	// 수납 상태를 미수납("0") 으로 세팅
			vo.put("dlyBillAmt", bdArrrgAmt);
			vo.put("dlySerNo", null);
			vo.put("arrrgTariff", null);
			vo.put("dlyBillDt", null);
			vo.put("prvBillDt", map.get("frstBillDt"));
			vo.put("billDt", map.get("prvBillDt"));
			vo.put("dlyDueDt", map.get("prvDueDt"));
			gamNticRequestMngtDAO.updateLevReqestUnarrrgAmt(vo);	// 연체를 취소한다.
		}
		else {
			Map arrrg = null;
			logger.info("last delay bill cancel");
			arrrg = (Map)list.get(i);
			vo.put("dlyBillAmnt", arrrg.get("arrrgAmt"));
			vo.put("dlySerNo", arrrg.get("dlySerNo"));
			vo.put("arrrgTariff", arrrg.get("arrrgTariff"));
			vo.put("dlyBillDt", arrrg.get("prvBillDt"));
			vo.put("dlyDueDt", arrrg.get("prvDueDt"));
			gamNticRequestMngtDAO.updateLevReqestArrrgAmt(vo);
		}
	}

	
	public int updateRentFeePaySttusRefresh() throws Exception {
		int ret = gamNticRequestMngtDAO.updateAssetRentFeePayDtlsMngtList();
		return ret+gamNticRequestMngtDAO.updateAssetRentFeePayDtlsMngtArrrgList();
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#selectRentFeeCheckReportListHWPML(egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtVO)
	 */
	@Override
	public String selectRentFeeListHWPML(GamPrtFcltyRentFeeMngtVO approvalOpt) throws Exception {
		// TODO Auto-generated method stub
    	StringBuilder result =  new StringBuilder(); //HWPML 처리 문자열 버퍼
    	
    	//HWPML용 인스턴스 생성
    	MakeRentFeeCheckReportHWPML reportHWP = new MakeRentFeeCheckReportHWPML();
    	
		//Head Element 구성 처리
		result.append(reportHWP.getXmlRentFeeCheckReportHead());
		
		//Body Element 구성 처리
		
		result.append(reportHWP.getXmlRentFeeCheckReportBody(approvalOpt));
		
    	
		return result.toString();
	}
	
	/*******
	 * 여기서 부터는 HWPML의 엘리먼트를 구성하기 위한 INNER CLASS
	 */
	class MakeRentFeeCheckReportHWPML {
		protected int instanceId = 2038414160;
		protected int zOrder = 0;
	
		/** InstId속성와 ZOrder 쓰는 엘리먼트들의 값을 변경시킨다.*/
		protected int getInstanceId() {
			return instanceId++;
		}
		protected int getZOrder() {
			return zOrder++;
		}
		
		/**파일을  BASE64엔코딩 문자열로 변환시킨다 */
		protected String fileToBase64(String fileName) throws Exception {
			FileInputStream fis = new FileInputStream(fileName);
			long fileSize = fis.getChannel().size();
			byte[] fileData = new byte[(int) fileSize];
			fis.read(fileData);
			fis.close();
			return new String(Base64.encodeBase64(fileData));
		}
		
		
		/**HWPML 용 산정조서 HEAD 엘리먼트 구성을 문자열로 가져온다.*/
		public StringBuilder getXmlRentFeeCheckReportHead() {
			StringBuilder sb = new StringBuilder();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM일 dd일 HH시 mm분 ss초", Locale.KOREA);
			String today = formatter.format(new Date());
			
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
			sb.append("<HWPML Style=\"embed\" SubVersion=\"8.0.0.0\" Version=\"2.8\">\n");
			sb.append("<HEAD SecCnt=\"1\">\n");
			sb.append("<DOCSUMMARY><TITLE>항만시설 사용료 산출내역</TITLE><AUTHOR>항만시설팀</AUTHOR><DATE>"+ today +"</DATE></DOCSUMMARY>\n");
			sb.append("<DOCSETTING><BEGINNUMBER Endnote=\"1\" Equation=\"1\" Footnote=\"1\" Page=\"1\" Picture=\"1\" Table=\"1\"/><CARETPOS List=\"0\" Para=\"7\" Pos=\"4\"/></DOCSETTING>\n");
			sb.append("<MAPPINGTABLE>\n");
			sb.append("<FACENAMELIST>\n");
			sb.append("<FONTFACE Count=\"2\" Lang=\"Hangul\"><FONT Id=\"0\" Name=\"함초롬돋움\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"함초롬바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT></FONTFACE>\n");
			sb.append("<FONTFACE Count=\"2\" Lang=\"Latin\"><FONT Id=\"0\" Name=\"함초롬돋움\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"함초롬바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT></FONTFACE>\n");
			sb.append("<FONTFACE Count=\"2\" Lang=\"Hanja\"><FONT Id=\"0\" Name=\"함초롬돋움\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"함초롬바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT></FONTFACE>\n");
			sb.append("<FONTFACE Count=\"2\" Lang=\"Japanese\"><FONT Id=\"0\" Name=\"함초롬돋움\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"함초롬바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT></FONTFACE>\n");
			sb.append("<FONTFACE Count=\"2\" Lang=\"Other\"><FONT Id=\"0\" Name=\"함초롬돋움\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"함초롬바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT></FONTFACE>\n");
			sb.append("<FONTFACE Count=\"2\" Lang=\"Symbol\"><FONT Id=\"0\" Name=\"함초롬돋움\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"함초롬바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT></FONTFACE>\n");
			sb.append("<FONTFACE Count=\"2\" Lang=\"User\"><FONT Id=\"0\" Name=\"함초롬돋움\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"함초롬바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT></FONTFACE>\n");
			sb.append("</FACENAMELIST>\n");
			sb.append("<BORDERFILLLIST Count=\"2\">\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"1\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.1mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"2\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.1mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"4294967295\" HatchColor=\"4278190080\"/></FILLBRUSH></BORDERFILL>\n");
			sb.append("</BORDERFILLLIST>\n");
			sb.append("<CHARSHAPELIST Count=\"8\">\n");
			sb.append("<CHARSHAPE BorderFillId=\"2\" Height=\"1000\" Id=\"0\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"2\" Height=\"1000\" Id=\"1\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"2\" Height=\"900\" Id=\"2\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"2\" Height=\"900\" Id=\"3\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"2\" Height=\"900\" Id=\"4\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"2\" Height=\"2000\" Id=\"5\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/><UNDERLINE Color=\"0\" Shape=\"Solid\" Type=\"Bottom\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"2\" Height=\"1200\" Id=\"6\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"2\" Height=\"1200\" Id=\"7\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"2\" Height=\"1200\" Id=\"8\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"255\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("</CHARSHAPELIST>\n");
			sb.append("<TABDEFLIST Count=\"2\">\n");
			sb.append("<TABDEF AutoTabLeft=\"false\" AutoTabRight=\"false\" Id=\"0\"/>\n");
			sb.append("<TABDEF AutoTabLeft=\"true\" AutoTabRight=\"false\" Id=\"1\"/>\n");
			sb.append("</TABDEFLIST>\n");
			sb.append("<NUMBERINGLIST Count=\"1\">\n");
			sb.append("<NUMBERING Id=\"1\" Start=\"0\"><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"1\" NumFormat=\"Digit\" Start=\"1\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^1.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"2\" NumFormat=\"HangulSyllable\" Start=\"1\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^2.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"3\" NumFormat=\"Digit\" Start=\"1\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^3)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"4\" NumFormat=\"HangulSyllable\" Start=\"1\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^4)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"5\" NumFormat=\"Digit\" Start=\"1\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^5)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"6\" NumFormat=\"HangulSyllable\" Start=\"1\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^6)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"7\" NumFormat=\"CircledDigit\" Start=\"1\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^7</PARAHEAD></NUMBERING>\n");
			sb.append("</NUMBERINGLIST>\n");
			sb.append("<BULLETLIST Count=\"1\">\n");
			sb.append("<BULLET Char=\"◯\" Id=\"1\"><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"false\" WidthAdjust=\"0\"/></BULLET>\n");
			sb.append("</BULLETLIST>\n");
			sb.append("<PARASHAPELIST Count=\"13\">\n");
			sb.append("<PARASHAPE Align=\"Left\" AutoSpaceEAsianEng=\"false\" AutoSpaceEAsianNum=\"false\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"0\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" AutoSpaceEAsianEng=\"false\" AutoSpaceEAsianNum=\"false\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"1\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-2620\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" AutoSpaceEAsianEng=\"false\" AutoSpaceEAsianNum=\"false\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"2\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" AutoSpaceEAsianEng=\"false\" AutoSpaceEAsianNum=\"false\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"3\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" AutoSpaceEAsianEng=\"false\" AutoSpaceEAsianNum=\"false\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"4\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"6\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"14000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" AutoSpaceEAsianEng=\"false\" AutoSpaceEAsianNum=\"false\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"5\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"5\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"12000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" AutoSpaceEAsianEng=\"false\" AutoSpaceEAsianNum=\"false\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"6\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"4\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"10000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" AutoSpaceEAsianEng=\"false\" AutoSpaceEAsianNum=\"false\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"7\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"3\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"8000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" AutoSpaceEAsianEng=\"false\" AutoSpaceEAsianNum=\"false\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"8\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"2\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"6000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" AutoSpaceEAsianEng=\"false\" AutoSpaceEAsianNum=\"false\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"9\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"1\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"4000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" AutoSpaceEAsianEng=\"false\" AutoSpaceEAsianNum=\"false\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"10\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"3000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Center\" AutoSpaceEAsianEng=\"false\" AutoSpaceEAsianNum=\"false\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"11\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" AutoSpaceEAsianEng=\"false\" AutoSpaceEAsianNum=\"false\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" Heading=\"1\" HeadingType=\"Bullet\" Id=\"12\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"2000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("</PARASHAPELIST>\n");
			sb.append("<STYLELIST Count=\"14\">\n");
			sb.append("<STYLE CharShape=\"0\" EngName=\"Normal\" Id=\"0\" LangId=\"1042\" LockForm=\"0\" Name=\"바탕글\" NextStyle=\"0\" ParaShape=\"3\" Type=\"Para\"/>\n");
			sb.append("<STYLE CharShape=\"0\" EngName=\"Body\" Id=\"1\" LangId=\"1042\" LockForm=\"0\" Name=\"본문\" NextStyle=\"1\" ParaShape=\"10\" Type=\"Para\"/>\n");
			sb.append("<STYLE CharShape=\"0\" EngName=\"Outline 1\" Id=\"2\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 1\" NextStyle=\"2\" ParaShape=\"12\" Type=\"Para\"/>\n");
			sb.append("<STYLE CharShape=\"0\" EngName=\"Outline 2\" Id=\"3\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 2\" NextStyle=\"3\" ParaShape=\"9\" Type=\"Para\"/>\n");
			sb.append("<STYLE CharShape=\"0\" EngName=\"Outline 3\" Id=\"4\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 3\" NextStyle=\"4\" ParaShape=\"8\" Type=\"Para\"/>\n");
			sb.append("<STYLE CharShape=\"0\" EngName=\"Outline 4\" Id=\"5\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 4\" NextStyle=\"5\" ParaShape=\"7\" Type=\"Para\"/>\n");
			sb.append("<STYLE CharShape=\"0\" EngName=\"Outline 5\" Id=\"6\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 5\" NextStyle=\"6\" ParaShape=\"6\" Type=\"Para\"/>\n");
			sb.append("<STYLE CharShape=\"0\" EngName=\"Outline 6\" Id=\"7\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 6\" NextStyle=\"7\" ParaShape=\"5\" Type=\"Para\"/>\n");
			sb.append("<STYLE CharShape=\"0\" EngName=\"Outline 7\" Id=\"8\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 7\" NextStyle=\"8\" ParaShape=\"4\" Type=\"Para\"/>\n");
			sb.append("<STYLE CharShape=\"1\" EngName=\"Page Number\" Id=\"9\" LangId=\"1042\" LockForm=\"0\" Name=\"쪽 번호\" NextStyle=\"0\" Type=\"Char\"/>\n");
			sb.append("<STYLE CharShape=\"2\" EngName=\"Header\" Id=\"10\" LangId=\"1042\" LockForm=\"0\" Name=\"머리말\" NextStyle=\"10\" ParaShape=\"2\" Type=\"Para\"/>\n");
			sb.append("<STYLE CharShape=\"3\" EngName=\"Footnote\" Id=\"11\" LangId=\"1042\" LockForm=\"0\" Name=\"각주\" NextStyle=\"11\" ParaShape=\"1\" Type=\"Para\"/>\n");
			sb.append("<STYLE CharShape=\"3\" EngName=\"Endnote\" Id=\"12\" LangId=\"1042\" LockForm=\"0\" Name=\"미주\" NextStyle=\"12\" ParaShape=\"1\" Type=\"Para\"/>\n");
			sb.append("<STYLE CharShape=\"4\" EngName=\"Memo\" Id=\"13\" LangId=\"1042\" LockForm=\"0\" Name=\"메모\" NextStyle=\"13\" ParaShape=\"0\" Type=\"Para\"/>\n");
			sb.append("</STYLELIST>\n");
			sb.append("</MAPPINGTABLE>\n");
			sb.append("<COMPATIBLEDOCUMENT TargetProgram=\"None\">\n");
			sb.append("<LAYOUTCOMPATIBILITY AdjustBaselineInFixedLinespacing=\"false\" AdjustBaselineOfObjectToBottom=\"false\" AdjustLineheightToFont=\"false\" AdjustMarginFromAdjustLineheight=\"false\" AdjustParaBorderOffsetWithBorder=\"false\" AdjustParaBorderfillToSpacing=\"false\" AdjustVertPosOfLine=\"false\" ApplyAtLeastToPercent100Pct=\"false\" ApplyCharSpacingToCharGrid=\"false\" ApplyExtendHeaderFooterEachSection=\"false\" ApplyFontWeightToBold=\"false\" ApplyFontspaceToLatin=\"false\" ApplyMinColumnWidthTo1mm=\"false\" ApplyNextspacingOfLastPara=\"false\" ApplyParaBorderToOutside=\"false\" ApplyPrevspacingBeneathObject=\"false\" ApplyTabPosBasedOnSegment=\"false\" BaseCharUnitOfIndentOnFirstChar=\"false\" BaseCharUnitOnEAsian=\"false\" BaseLinespacingOnLinegrid=\"false\" BreakTabOverLine=\"false\" ConnectParaBorderfillOfEqualBorder=\"false\" DoNotAdjustEmptyAnchorLine=\"false\" DoNotAdjustWordInJustify=\"false\" DoNotAlignLastForbidden=\"false\" DoNotAlignLastPeriod=\"false\" DoNotAlignWhitespaceOnRight=\"false\" DoNotApplyAutoSpaceEAsianEng=\"false\" DoNotApplyAutoSpaceEAsianNum=\"false\" DoNotApplyColSeparatorAtNoGap=\"false\" DoNotApplyExtensionCharCompose=\"false\" DoNotApplyGridInHeaderFooter=\"false\" DoNotApplyHeaderFooterAtNoSpace=\"false\" DoNotApplyImageEffect=\"false\" DoNotApplyLinegridAtNoLinespacing=\"false\" DoNotApplyShapeComment=\"false\" DoNotApplyStrikeoutWithUnderline=\"false\" DoNotApplyVertOffsetOfForward=\"false\" DoNotApplyWhiteSpaceHeight=\"false\" DoNotFormattingAtBeneathAnchor=\"false\" DoNotHoldAnchorOfTable=\"false\" ExtendLineheightToOffset=\"false\" ExtendLineheightToParaBorderOffset=\"false\" ExtendVertLimitToPageMargins=\"false\" FixedUnderlineWidth=\"false\" OverlapBothAllowOverlap=\"false\" TreatQuotationAsLatin=\"false\" UseInnerUnderline=\"false\" UseLowercaseStrikeout=\"false\"/>\n");
			sb.append("</COMPATIBLEDOCUMENT>\n");
			sb.append("</HEAD>\n");
			
			return sb;
		}
		
		/**HWPML 용 산정조서 엘리먼트를 문자열로 가져온다.
		 * @throws Exception */
		public StringBuilder getXmlRentFeeCheckReportBody(GamPrtFcltyRentFeeMngtVO approvalOpt) throws Exception {
			StringBuilder sb = new StringBuilder();
			
			List printList = gamPrtFcltyRentFeeMngtDao.selectNpticPrintInfo(approvalOpt);  
			
			sb.append("<BODY>\n");
			sb.append("<SECTION Id=\"0\">\n");
			sb.append("<P ColumnBreak=\"false\" InstId=\"2147689424\" PageBreak=\"false\" ParaShape=\"11\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"5\">\n");
			sb.append("<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\">\n");
			sb.append("<STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/>\n");
			sb.append("<HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/>\n");
			sb.append("<PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\">\n");
			sb.append("<PAGEMARGIN Bottom=\"4252\" Footer=\"4252\" Gutter=\"0\" Header=\"4252\" Left=\"8504\" Right=\"8504\" Top=\"5668\"/>\n");
			sb.append("</PAGEDEF>\n");
			sb.append("<FOOTNOTESHAPE>\n");
			sb.append("<AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/>\n");
			sb.append("<NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/>\n");
			sb.append("<NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/>\n");
			sb.append("<NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/>\n");
			sb.append("<NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/>\n");
			sb.append("</FOOTNOTESHAPE>\n");
			sb.append("<ENDNOTESHAPE>\n");
			sb.append("<AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/>\n");
			sb.append("<NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/>\n");
			sb.append("<NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/>\n");
			sb.append("<NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/>\n");
			sb.append("<NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/>\n");
			sb.append("</ENDNOTESHAPE>\n");
			sb.append("<PAGEBORDERFILL BorferFill=\"1\" FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\">\n");
			sb.append("<PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/>\n");
			sb.append("</PAGEBORDERFILL>\n");
			sb.append("<PAGEBORDERFILL BorferFill=\"1\" FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\">\n");
			sb.append("<PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/>\n");
			sb.append("</PAGEBORDERFILL>\n");
			sb.append("<PAGEBORDERFILL BorferFill=\"1\" FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\">\n");
			sb.append("<PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/>\n");
			sb.append("</PAGEBORDERFILL>\n");
			sb.append("</SECDEF>\n");
			sb.append("<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/>\n");
			sb.append("<CHAR>항만시설 사용료 산출내역</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\"/>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\">\n");
			sb.append("<CHAR>□ {업체이름}</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\"/>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\">\n");
			sb.append("<CHAR>가. 근거</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P InstId=\"2147689445\" ParaShape=\"12\" Style=\"2\">\n");
			sb.append("<TEXT CharShape=\"6\">\n");
			sb.append("<CHAR>{근거1}</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"12\" Style=\"2\">\n");
			sb.append("<TEXT CharShape=\"6\">\n");
			sb.append("<CHAR>{근거2}</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"12\" Style=\"2\">\n");
			sb.append("<TEXT CharShape=\"6\">\n");
			sb.append("<CHAR>{근거3}</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\"/>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\">\n");
			sb.append("<CHAR>나. 사용 기간 : {사용기간} ({사용}개월)</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\"/>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\">\n");
			sb.append("<CHAR>다 사용지역 : {주소}</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\"/>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\">\n");
			sb.append("<CHAR>라. 사용면적 : {납부금액} ㎡</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\"/>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\">\n");
			sb.append("<CHAR>마. 사용료 산출(항만부지 사용료)</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			
			sb.append("<P ParaShape=\"12\" Style=\"2\">\n");
			sb.append("<TEXT CharShape=\"6\">\n");
			sb.append("<CHAR>산출기준 : 사용면적 × 개별공시지가 × 요율  × 사용기간</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			
			sb.append("<P ParaShape=\"12\" Style=\"2\">\n");
			sb.append("<TEXT CharShape=\"6\">\n");
			sb.append("<CHAR>사 용 료 : {사용면적} ㎡ × {개별공시지가} 원 × {요율} × {사용개월}/12개월 = {금액} 원</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"8\">\n");
			sb.append("<CHAR>  ※ 공시지가 확인은 </CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\"/>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\">\n");
			sb.append("<CHAR>바. 총 납부금액 : {총 납부금액} 원</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"12\" Style=\"2\">\n");
			sb.append("<TEXT CharShape=\"6\">\n");
			sb.append("<CHAR>사 용 료 : {사용료} 원</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"12\" Style=\"2\">\n");
			sb.append("<TEXT CharShape=\"6\">\n");
			sb.append("<CHAR>부 가 세 : {부가세} 원</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"3\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"6\"/>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"11\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"7\">\n");
			sb.append("<CHAR>위와 같이 산출함.</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"11\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"7\">\n");
			sb.append("<CHAR>{날짜}</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("<P ParaShape=\"11\" Style=\"0\">\n");
			sb.append("<TEXT CharShape=\"7\">\n");
			sb.append("<CHAR>위 산출자 항만운영팀 {이름}</CHAR>\n");
			sb.append("</TEXT>\n");
			sb.append("</P>\n");
			sb.append("</SECTION>\n");
			sb.append("</BODY>\n");
			sb.append("<TAIL>\n");
			sb.append("<SCRIPTCODE Type=\"JScrip\" Version=\"1.0\"><SCRIPTHEADER>var Documents = XHwpDocuments;\n");
			sb.append("var Document = Documents.Active_XHwpDocument;\n");
			sb.append("</SCRIPTHEADER><SCRIPTSOURCE>function OnDocument_New() { }\n");
			sb.append("</SCRIPTSOURCE></SCRIPTCODE>\n");
			sb.append("</TAIL>\n");
			sb.append("</HWPML>\n");

			return sb;
		}
	}

}
