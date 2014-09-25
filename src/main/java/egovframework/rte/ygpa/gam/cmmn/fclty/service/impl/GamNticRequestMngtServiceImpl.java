/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService;

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
		gamNticRequestMngtDAO.updateLevReqestIssueYn(vo);
		gamNticRequestMngtDAO.insertNticRequestRevCollF(vo);
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
    	BigDecimal bdBillSumAmnt = new BigDecimal(map.get("billSumAmnt").toString());
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

    	mapResult.put("prtAtCode", strPrtAtCode );
    	mapResult.put("feeTp", strFeeTp );
    	mapResult.put("fiscalYr", strFiscalYr );
    	mapResult.put("billNo", strBillNo );
    	mapResult.put("dlySerNo", strDlySerNo );

    	// 고지발행건인지 아닌지 확인
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
//    	BigDecimal bdBillSumAmnt = new BigDecimal(revCollF.get("billSumAmnt").toString());
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
    	Map egiroMap = gamNticRequestMngtDAO.getWorkDtSysdateInfo(mapResult);
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
        		mapResult.put("amount", bdAmount );
        		mapResult.put("amouuntAf", bdAmount );
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
		// TODO Auto-generated method stub
//		vo.put("feeTp", "C1");
//		try {
//			vo.put("arrrgNo", Integer.parseInt((String)vo.get("arrrgNo"))+1);
//		}
//		catch(Exception e) {
//			vo.put("arrrgNo", 1);
//			// 연체 금액 계산
//			// 연체 납부기한
//			vo.put("dlyPayTmlmt", getDueDate(this.TERM_FOR_PAYMENT15));
//            Map dlyAmntMap = calculateDlyBillAmnt(vo);
//
//			Integer d = Integer.parseInt((String)vo.get("nticAmt"));
//			vo.put("arrrgAmt", +1);
//		}
		gamNticRequestMngtDAO.updateLevReqestArrrgAmt(vo);
		gamNticRequestMngtDAO.removeNticRequestRevCollFC1(vo);
		gamNticRequestMngtDAO.insertNticRequestRevCollFC1(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#updateNticPrintYn(java.util.Map)
	 */
	@Override
	public void updateNticPrintState(Map<String, Object> vo) throws Exception {
		gamNticRequestMngtDAO.updateLevReqestNhtPrintYn(vo);
		gamNticRequestMngtDAO.updateRevCollFBillPrintYn(vo);
		// 2014-08-13 전자고지 출력
		if("Y".equals(vo.get("nhtPrintYn"))) {
	    	egiroPrint(vo);
		}
		else {
			egiroPrintCancel(vo);
		}
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

        termMonth = (BigDecimal) dlyTermMap.get("diff_month");
        termDay   = (BigDecimal) dlyTermMap.get("diff_day");

        //if ( billDt.compareTo(p2FrgCmmService.getCalendarDate(p2CmmIPAService.REV_STD_DATE_20090801)) >= 0 ) {  //2009.08.01 이후
            //연체월, 일 수 재계산 ; 원고지납부기한과 연체고지일자 사이
            searchMap.put("dt_fr", map.get("payTmlmt"));
            searchMap.put("dt_to", map.get("dly_bill_dt"));
            //dlyTermMap = p2FrgCmmService.getDiffDates(searchMap);
            dlyTermMap = getDiffDates(searchMap);

            termMonth = (BigDecimal) dlyTermMap.get("diff_month");
            termDay   = (BigDecimal) dlyTermMap.get("diff_day");

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
		// TODO Auto-generated method stub

	}


}
