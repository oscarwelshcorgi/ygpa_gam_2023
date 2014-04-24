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
    
    /** 수납일from */
    private String sRcivDtFrom;
	
    /** 수납일 to */
    private String sRcivDtTo;
    
    /** 업체코드 */
    private String sEntrpscd;

    private String totSumCnt;
    
    private String totSumNickAmt;
    
    private String totSumDscntAmt;
    
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
	 * @return the totSumNickAmt
	 */
	public String getTotSumNickAmt() {
		return totSumNickAmt;
	}

	/**
	 * @param totSumNickAmt the totSumNickAmt to set
	 */
	public void setTotSumNickAmt(String totSumNickAmt) {
		this.totSumNickAmt = totSumNickAmt;
	}

	/**
	 * @return the totSumDscntAmt
	 */
	public String getTotSumDscntAmt() {
		return totSumDscntAmt;
	}

	/**
	 * @param totSumDscntAmt the totSumDscntAmt to set
	 */
	public void setTotSumDscntAmt(String totSumDscntAmt) {
		this.totSumDscntAmt = totSumDscntAmt;
	}

	/**
	 * @return the sRcivDtFrom
	 */
	public String getsRcivDtFrom() {
		return sRcivDtFrom;
	}

	/**
	 * @param sRcivDtFrom the sRcivDtFrom to set
	 */
	public void setsRcivDtFrom(String sRcivDtFrom) {
		this.sRcivDtFrom = sRcivDtFrom;
	}

	/**
	 * @return the sRcivDtTo
	 */
	public String getsRcivDtTo() {
		return sRcivDtTo;
	}

	/**
	 * @param sRcivDtTo the sRcivDtTo to set
	 */
	public void setsRcivDtTo(String sRcivDtTo) {
		this.sRcivDtTo = sRcivDtTo;
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

}
