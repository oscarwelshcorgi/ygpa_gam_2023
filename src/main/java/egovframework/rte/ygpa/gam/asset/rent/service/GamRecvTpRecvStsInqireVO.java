package egovframework.rte.ygpa.gam.asset.rent.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamRecvTpRecvStsInqireVO.java
 * @Description : 수입종류별수입현황조회 VO class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamRecvTpRecvStsInqireVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;

	/** 조회 조건 (항코드) */
    private String sPrtAtCode;
    
    /** 조회 조건 (업체코드) */
    private String sEntrpscd;

    /** 조회 조건 (요금종류) */
    private String sChrgeKnd;
    
    /** 조회 조건 (고지기간 시작일) */
    private String sGrUsagePdFrom;
    
    /** 조회 조건 (고지기간 끝일) */
    private String sGrUsagePdTo;
    
    /** 자료수, 고지금액총합, 수납금액총합, 미수납금액총합 */
    private String totSumCnt;
    
    private String totSumNticAmt;
    
    private String totSumRcvdAmt;
    
    private String totSumNotRcvdAmt;

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

	/**
	 * @return the totSumNotRcvdAmt
	 */
	public String getTotSumNotRcvdAmt() {
		return totSumNotRcvdAmt;
	}

	/**
	 * @param totSumNotRcvdAmt the totSumNotRcvdAmt to set
	 */
	public void setTotSumNotRcvdAmt(String totSumNotRcvdAmt) {
		this.totSumNotRcvdAmt = totSumNotRcvdAmt;
	}
}
