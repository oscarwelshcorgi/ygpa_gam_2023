package egovframework.rte.ygpa.gam.asset.rent.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamCmpyRecvStsInqireVO.java
 * @Description : 업체별수입현황조회 VO class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamCmpyRecvStsInqireVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** 항코드 */
    private String sPrtAtCode;
	
    /** 고지업체 */
    private String sEntrpscd;
    
    /** 요금종류 */
    private String sChrgeKnd;
    
    /** 고지기간 시작일 */
    private String sGrUsagePdFrom;
    
    /** 고지기간 종료일 */
    private String sGrUsagePdTo;
    
    /** 디스플레이 자료수 */
    private String dpTotCnt;
    
    /** 고지금액합계 */
    private String sumNticAmtSum;
    
    /** 수납금액합계 */
    private String sumRcvdAmtSum;
    
    /** 미수납금액합계 */
    private String sumNotRcvdAmtSum;

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
	 * @return the dpTotCnt
	 */
	public String getDpTotCnt() {
		return dpTotCnt;
	}

	/**
	 * @param dpTotCnt the dpTotCnt to set
	 */
	public void setDpTotCnt(String dpTotCnt) {
		this.dpTotCnt = dpTotCnt;
	}

	/**
	 * @return the sumNticAmtSum
	 */
	public String getSumNticAmtSum() {
		return sumNticAmtSum;
	}

	/**
	 * @param sumNticAmtSum the sumNticAmtSum to set
	 */
	public void setSumNticAmtSum(String sumNticAmtSum) {
		this.sumNticAmtSum = sumNticAmtSum;
	}

	/**
	 * @return the sumRcvdAmtSum
	 */
	public String getSumRcvdAmtSum() {
		return sumRcvdAmtSum;
	}

	/**
	 * @param sumRcvdAmtSum the sumRcvdAmtSum to set
	 */
	public void setSumRcvdAmtSum(String sumRcvdAmtSum) {
		this.sumRcvdAmtSum = sumRcvdAmtSum;
	}

	/**
	 * @return the sumNotRcvdAmtSum
	 */
	public String getSumNotRcvdAmtSum() {
		return sumNotRcvdAmtSum;
	}

	/**
	 * @param sumNotRcvdAmtSum the sumNotRcvdAmtSum to set
	 */
	public void setSumNotRcvdAmtSum(String sumNotRcvdAmtSum) {
		this.sumNotRcvdAmtSum = sumNotRcvdAmtSum;
	}
    
	
    
}
