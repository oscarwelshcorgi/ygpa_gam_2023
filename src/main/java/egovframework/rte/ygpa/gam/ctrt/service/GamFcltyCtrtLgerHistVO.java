/**
 * 
 */
package egovframework.rte.ygpa.gam.ctrt.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltyCtrtLgerHistVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;
	
	
	/** 계약구분 */
    private String sCtrtSe;
    
    /** 계약명 */
    private String sCtrtNm;
    
    /** 계약일 검색조건 시작일 */
    private String sCtrtFrDt;
    
    /** 계약일 검색조건 종료일 */
    private String sCtrtToDt;

    /** 등록업체코드 */
    private String sRegistEntrpsCd;
    
    /** 입찰일 검색조건 시작일 */
    private String sBidFrDt;
    
    /** 입찰일 검색조건 종료일 */
    private String sBidToDt;
    
    /** 자료 총갯수 */
    private int totalCnt;
    
    /** 설계금액 총액 */
    private long sumPlanAmt;
    
    /** 예정금액 총액 */
    private long sumPrmtAmt;
    
    /** 낙찰금액 총액 */
    private long sumScsbidAmt;
    
    /** 기초금액 총액 */
    private long sumBaseAmt;
    
    
    
    

	/**
	 * @return the sCtrtSe
	 */
	public String getsCtrtSe() {
		return sCtrtSe;
	}

	/**
	 * @param sCtrtSe the sCtrtSe to set
	 */
	public void setsCtrtSe(String sCtrtSe) {
		this.sCtrtSe = sCtrtSe;
	}

	/**
	 * @return the sCtrtNm
	 */
	public String getsCtrtNm() {
		return sCtrtNm;
	}

	/**
	 * @param sCtrtNm the sCtrtNm to set
	 */
	public void setsCtrtNm(String sCtrtNm) {
		this.sCtrtNm = sCtrtNm;
	}

	/**
	 * @return the sCtrtFrDt
	 */
	public String getsCtrtFrDt() {
		return sCtrtFrDt;
	}

	/**
	 * @param sCtrtFrDt the sCtrtFrDt to set
	 */
	public void setsCtrtFrDt(String sCtrtFrDt) {
		this.sCtrtFrDt = sCtrtFrDt;
	}

	/**
	 * @return the sCtrtToDt
	 */
	public String getsCtrtToDt() {
		return sCtrtToDt;
	}

	/**
	 * @param sCtrtToDt the sCtrtToDt to set
	 */
	public void setsCtrtToDt(String sCtrtToDt) {
		this.sCtrtToDt = sCtrtToDt;
	}

	/**
	 * @return the sRegistEntrpsCd
	 */
	public String getsRegistEntrpsCd() {
		return sRegistEntrpsCd;
	}

	/**
	 * @param sRegistEntrpsCd the sRegistEntrpsCd to set
	 */
	public void setsRegistEntrpsCd(String sRegistEntrpsCd) {
		this.sRegistEntrpsCd = sRegistEntrpsCd;
	}

	/**
	 * @return the sBidfrDt
	 */
	public String getsBidFrDt() {
		return sBidFrDt;
	}

	/**
	 * @param sBidfrDt the sBidfrDt to set
	 */
	public void setsBidFrDt(String sBidFrDt) {
		this.sBidFrDt = sBidFrDt;
	}

	/**
	 * @return the sBidtoDt
	 */
	public String getsBidToDt() {
		return sBidToDt;
	}

	/**
	 * @param sBidtoDt the sBidtoDt to set
	 */
	public void setsBidToDt(String sBidToDt) {
		this.sBidToDt = sBidToDt;
	}



	/**
	 * @return the sumPlanAmt
	 */
	public long getSumPlanAmt() {
		return sumPlanAmt;
	}

	/**
	 * @param sumPlanAmt the sumPlanAmt to set
	 */
	public void setSumPlanAmt(long sumPlanAmt) {
		this.sumPlanAmt = sumPlanAmt;
	}

	/**
	 * @return the sumPrmtAmt
	 */
	public long getSumPrmtAmt() {
		return sumPrmtAmt;
	}

	/**
	 * @param sumPrmtAmt the sumPrmtAmt to set
	 */
	public void setSumPrmtAmt(long sumPrmtAmt) {
		this.sumPrmtAmt = sumPrmtAmt;
	}

	/**
	 * @return the sumScsbidAmt
	 */
	public long getSumScsbidAmt() {
		return sumScsbidAmt;
	}

	/**
	 * @param sumScsbidAmt the sumScsbidAmt to set
	 */
	public void setSumScsbidAmt(long sumScsbidAmt) {
		this.sumScsbidAmt = sumScsbidAmt;
	}

	/**
	 * @return the sumBaseAmt
	 */
	public long getSumBaseAmt() {
		return sumBaseAmt;
	}

	/**
	 * @param sumBaseAmt the sumBaseAmt to set
	 */
	public void setSumBaseAmt(long sumBaseAmt) {
		this.sumBaseAmt = sumBaseAmt;
	}

	/**
	 * @return the totalCnt
	 */
	public int getTotalCnt() {
		return totalCnt;
	}

	/**
	 * @param totalCnt the totalCnt to set
	 */
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

}
