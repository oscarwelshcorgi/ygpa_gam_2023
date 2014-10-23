/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 13.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 13.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamSocTotalBsnsSetoffDtlsVO extends ComDefaultVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** 검색항코드 */
    private String sPrtAtCode;
    
    /** 검색요금종류코드 */
    private String sFeeTp;
    
    /** 검색보전업체코드 */
    private String sExmpAgentCode;
    
    /** 검색보전횟수 */
    private String sUseNo;
    
    /** 검색처리기간시작일 */
    private String sFrDt;
    
    /** 검색처리기간종료일 */
    private String sToDt;
    
    /** 검색구분 */
    private String sType;
    
   
    
    /** 항코드 */
    private String prtAtCode;
    
    /** 허가원부공사준공년도 */
    private String cmplYr;
    
    /** 허가원부일련번호 */
    private String constNo;
    
    
    
    /** 총갯수 */
    private int totalCnt;
    
    /** 총상계금액(청) */
    private long sumPrtTotalAmnt;
    
    /** 총상계금액(공사) */
    private long sumAppTotalAmnt;


    
    

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
	 * @return the sFeeTp
	 */
	public String getsFeeTp() {
		return sFeeTp;
	}

	/**
	 * @param sFeeTp the sFeeTp to set
	 */
	public void setsFeeTp(String sFeeTp) {
		this.sFeeTp = sFeeTp;
	}

	/**
	 * @return the sExmpAgentCode
	 */
	public String getsExmpAgentCode() {
		return sExmpAgentCode;
	}

	/**
	 * @param sExmpAgentCode the sExmpAgentCode to set
	 */
	public void setsExmpAgentCode(String sExmpAgentCode) {
		this.sExmpAgentCode = sExmpAgentCode;
	}

	/**
	 * @return the sUseNo
	 */
	public String getsUseNo() {
		return sUseNo;
	}

	/**
	 * @param sUseNo the sUseNo to set
	 */
	public void setsUseNo(String sUseNo) {
		this.sUseNo = sUseNo;
	}

	/**
	 * @return the sFrDt
	 */
	public String getsFrDt() {
		return sFrDt;
	}

	/**
	 * @param sFrDt the sFrDt to set
	 */
	public void setsFrDt(String sFrDt) {
		this.sFrDt = sFrDt;
	}

	/**
	 * @return the sToDt
	 */
	public String getsToDt() {
		return sToDt;
	}

	/**
	 * @param sToDt the sToDt to set
	 */
	public void setsToDt(String sToDt) {
		this.sToDt = sToDt;
	}

	/**
	 * @return the sType
	 */
	public String getsType() {
		return sType;
	}

	/**
	 * @param sType the sType to set
	 */
	public void setsType(String sType) {
		this.sType = sType;
	}

	/**
	 * @return the prtAtCode
	 */
	public String getPrtAtCode() {
		return prtAtCode;
	}

	/**
	 * @param prtAtCode the prtAtCode to set
	 */
	public void setPrtAtCode(String prtAtCode) {
		this.prtAtCode = prtAtCode;
	}

	/**
	 * @return the cmplYr
	 */
	public String getCmplYr() {
		return cmplYr;
	}

	/**
	 * @param cmplYr the cmplYr to set
	 */
	public void setCmplYr(String cmplYr) {
		this.cmplYr = cmplYr;
	}

	/**
	 * @return the constNo
	 */
	public String getConstNo() {
		return constNo;
	}

	/**
	 * @param constNo the constNo to set
	 */
	public void setConstNo(String constNo) {
		this.constNo = constNo;
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

	/**
	 * @return the sumPrtTotalAmnt
	 */
	public long getSumPrtTotalAmnt() {
		return sumPrtTotalAmnt;
	}

	/**
	 * @param sumPrtTotalAmnt the sumPrtTotalAmnt to set
	 */
	public void setSumPrtTotalAmnt(long sumPrtTotalAmnt) {
		this.sumPrtTotalAmnt = sumPrtTotalAmnt;
	}

	/**
	 * @return the sumAppTotalAmnt
	 */
	public long getSumAppTotalAmnt() {
		return sumAppTotalAmnt;
	}

	/**
	 * @param sumAppTotalAmnt the sumAppTotalAmnt to set
	 */
	public void setSumAppTotalAmnt(long sumAppTotalAmnt) {
		this.sumAppTotalAmnt = sumAppTotalAmnt;
	}

	

}
