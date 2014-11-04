/**
 * 
 */
package egovframework.rte.ygpa.gam.ctrt.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 31.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltyCtrtSttusInqireVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;
	
	
	/** 계약구분 */
    private String sCtrtSe;
    
    /** 계약명 */
    private String sCtrtNm;
    
    /** 계약연도 */
    private String sCtrtYr;
    
    /** 이전계약연도 */
    private String sPrevCtrtYr;
    
    
    
    /** 등록업체코드 */
    private String sRegistEntrpsCd;
    
        
    /** 자료 총갯수 */
    private int totalCnt;
    
    /** 이전연도거래금액 총액 */
    private long sumPrevCtrtAmt;
    
    /** 검색연도거래금액 총액 */
    private long sumCurrCtrtAmt;

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
	 * @return the sCtrtYr
	 */
	public String getsCtrtYr() {
		return sCtrtYr;
	}

	/**
	 * @param sCtrtYr the sCtrtYr to set
	 */
	public void setsCtrtYr(String sCtrtYr) {
		this.sCtrtYr = sCtrtYr;
	}

	/**
	 * @return the sPrevCtrtYr
	 */
	public String getsPrevCtrtYr() {
		return sPrevCtrtYr;
	}

	/**
	 * @param sPrevCtrtYr the sPrevCtrtYr to set
	 */
	public void setsPrevCtrtYr(String sPrevCtrtYr) {
		this.sPrevCtrtYr = sPrevCtrtYr;
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
	 * @return the sumPrevCtrtAmt
	 */
	public long getSumPrevCtrtAmt() {
		return sumPrevCtrtAmt;
	}

	/**
	 * @param sumPrevCtrtAmt the sumPrevCtrtAmt to set
	 */
	public void setSumPrevCtrtAmt(long sumPrevCtrtAmt) {
		this.sumPrevCtrtAmt = sumPrevCtrtAmt;
	}

	/**
	 * @return the sumCurrCtrtAmt
	 */
	public long getSumCurrCtrtAmt() {
		return sumCurrCtrtAmt;
	}

	/**
	 * @param sumCurrCtrtAmt the sumCurrCtrtAmt to set
	 */
	public void setSumCurrCtrtAmt(long sumCurrCtrtAmt) {
		this.sumCurrCtrtAmt = sumCurrCtrtAmt;
	}

    
    



	

}
