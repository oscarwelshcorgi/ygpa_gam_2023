/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 15.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamSocShipProcessRealloadVO extends ComDefaultVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** 검색항코드 */
    private String sPrtAtCode;
    
    /** 검색항이름 */
    private String sPrtKorNm;
    
    /** 고지기간검색시작일 */
    private String sFrDt;
    
    /** 고지기간검색종료일 */
    private String sToDt;
    
    /** 업체코드 */
    private String sExmpAgentCode;
    
    /** 업체코드 */
    private String sExmpAgentName;
    
    /** 호출부호 */
    private String sVsslKey;
    
    /** 호출부호 */
    private String sVsslNm;
    
    /** 요금종류 */
    private String feeTp;
    
    
    /** 총갯수 */
    private int totalCnt;
    
    /** 총면제금액 */
    private long sumExmpAmnt;
    
    
    

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
	 * @return the sPrtKorNm
	 */
	public String getsPrtKorNm() {
		return sPrtKorNm;
	}

	/**
	 * @param sPrtKorNm the sPrtKorNm to set
	 */
	public void setsPrtKorNm(String sPrtKorNm) {
		this.sPrtKorNm = sPrtKorNm;
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
	 * @return the sVsslKey
	 */
	public String getsVsslKey() {
		return sVsslKey;
	}

	/**
	 * @param sVsslKey the sVsslKey to set
	 */
	public void setsVsslKey(String sVsslKey) {
		this.sVsslKey = sVsslKey;
	}

	/**
	 * @return the sVsslNm
	 */
	public String getsVsslNm() {
		return sVsslNm;
	}

	/**
	 * @param sVsslNm the sVsslNm to set
	 */
	public void setsVsslNm(String sVsslNm) {
		this.sVsslNm = sVsslNm;
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
	 * @return the sumExmpAmnt
	 */
	public long getSumExmpAmnt() {
		return sumExmpAmnt;
	}

	/**
	 * @param sumExmpAmnt the sumExmpAmnt to set
	 */
	public void setSumExmpAmnt(long sumExmpAmnt) {
		this.sumExmpAmnt = sumExmpAmnt;
	}

	/**
	 * @return the feeTp
	 */
	public String getFeeTp() {
		return feeTp;
	}

	/**
	 * @param feeTp the feeTp to set
	 */
	public void setFeeTp(String feeTp) {
		this.feeTp = feeTp;
	}

	/**
	 * @return the sExmpAgentName
	 */
	public String getsExmpAgentName() {
		return sExmpAgentName;
	}

	/**
	 * @param sExmpAgentName the sExmpAgentName to set
	 */
	public void setsExmpAgentName(String sExmpAgentName) {
		this.sExmpAgentName = sExmpAgentName;
	}


    
    
    

}
