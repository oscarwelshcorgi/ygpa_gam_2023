/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService;
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
		gamNticRequestMngtDAO.deleteNticRequestRevCollF(vo);	// 고지정보를 삭제한다.
		vo.put("accnutYear", "");
		vo.put("nticno", "");
		vo.put("nhtIsueYn", "N");
		gamNticRequestMngtDAO.updateLevReqestIssueYn(vo);	// 고지를 취소한다.
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
	public void updateNticPrintYn(Map<String, Object> vo) throws Exception {
		gamNticRequestMngtDAO.updateLevReqestNhtPrintYn(vo);
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
