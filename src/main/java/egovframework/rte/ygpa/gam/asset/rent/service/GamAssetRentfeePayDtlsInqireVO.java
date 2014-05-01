package egovframework.rte.ygpa.gam.asset.rent.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamAssetRentfeePayDtlsInqireVO.java
 * @Description : 사용료납부내역조회 VO class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-01-23
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamAssetRentfeePayDtlsInqireVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** 검색조건 */
    private String sPrtAtCode;
    
    /** 검색조건 */
    private String sMngYear;
    
    /** 검색조건 */
    private String sMngNo;
    
    /** 검색조건 */
    private String sMngCnt;
        
    /** 검색조건 */
    private String sEntrpscd;
    
    /** 검색조건 */
    private String sGrUsagePdFrom;
    
    /** 검색조건 */
    private String sGrUsagePdTo;
    
    /** 검색조건 */
    private String sChrgeKnd;
 
    /** 자료수, 고지금액, 수납금액 합계*/
    private String totSumCnt;
    
    private String totSumNticAmt;
    
    private String totSumRcvdAmt;

	/**
	 * @return the sPrtAtCode
	 */
	public String getsPrtAtCode() {
		return sPrtAtCode;
	}

	/**
	 * @param sPrtAtCode the sPrtAtCode to set
	 */
	public void setsPrtAtCode(String sPrtAtCode) {
		this.sPrtAtCode = sPrtAtCode;
	}

	/**
	 * @return the sMngYear
	 */
	public String getsMngYear() {
		return sMngYear;
	}

	/**
	 * @param sMngYear the sMngYear to set
	 */
	public void setsMngYear(String sMngYear) {
		this.sMngYear = sMngYear;
	}

	/**
	 * @return the sMngNo
	 */
	public String getsMngNo() {
		return sMngNo;
	}

	/**
	 * @param sMngNo the sMngNo to set
	 */
	public void setsMngNo(String sMngNo) {
		this.sMngNo = sMngNo;
	}

	/**
	 * @return the sMngCnt
	 */
	public String getsMngCnt() {
		return sMngCnt;
	}

	/**
	 * @param sMngCnt the sMngCnt to set
	 */
	public void setsMngCnt(String sMngCnt) {
		this.sMngCnt = sMngCnt;
	}

	/**
	 * @return the sEntrpscd
	 */
	public String getsEntrpscd() {
		return sEntrpscd;
	}

	/**
	 * @param sEntrpscd the sEntrpscd to set
	 */
	public void setsEntrpscd(String sEntrpscd) {
		this.sEntrpscd = sEntrpscd;
	}

	/**
	 * @return the sGrUsagePdFrom
	 */
	public String getsGrUsagePdFrom() {
		return sGrUsagePdFrom;
	}

	/**
	 * @param sGrUsagePdFrom the sGrUsagePdFrom to set
	 */
	public void setsGrUsagePdFrom(String sGrUsagePdFrom) {
		this.sGrUsagePdFrom = sGrUsagePdFrom;
	}

	/**
	 * @return the sGrUsagePdTo
	 */
	public String getsGrUsagePdTo() {
		return sGrUsagePdTo;
	}

	/**
	 * @param sGrUsagePdTo the sGrUsagePdTo to set
	 */
	public void setsGrUsagePdTo(String sGrUsagePdTo) {
		this.sGrUsagePdTo = sGrUsagePdTo;
	}

	/**
	 * @return the sChrgeKnd
	 */
	public String getsChrgeKnd() {
		return sChrgeKnd;
	}

	/**
	 * @param sChrgeKnd the sChrgeKnd to set
	 */
	public void setsChrgeKnd(String sChrgeKnd) {
		this.sChrgeKnd = sChrgeKnd;
	}

	/**
	 * @return the totSumCnt
	 */
	public String getTotSumCnt() {
		return totSumCnt;
	}

	/**
	 * @param totSumCnt the totSumCnt to set
	 */
	public void setTotSumCnt(String totSumCnt) {
		this.totSumCnt = totSumCnt;
	}

	/**
	 * @return the totSumNticAmt
	 */
	public String getTotSumNticAmt() {
		return totSumNticAmt;
	}

	/**
	 * @param totSumNticAmt the totSumNticAmt to set
	 */
	public void setTotSumNticAmt(String totSumNticAmt) {
		this.totSumNticAmt = totSumNticAmt;
	}

	/**
	 * @return the totSumRcvdAmt
	 */
	public String getTotSumRcvdAmt() {
		return totSumRcvdAmt;
	}

	/**
	 * @param totSumRcvdAmt the totSumRcvdAmt to set
	 */
	public void setTotSumRcvdAmt(String totSumRcvdAmt) {
		this.totSumRcvdAmt = totSumRcvdAmt;
	}
}
