/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 17.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamSocStatsVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	
	/** 항코드(조회조건) **/
	private String sPrtAtCode;

	/** 조사대상(조회조건) **/
	private String sSearchTarget;
	
	/** 기준(조회조건) **/
	private String sBasis;
	
	/** 업체코드(조회조건) **/
	private String sExmpAgentCode;
	
	/** 조회시작월(조회조건) **/
	private String sSearchFr;
	
	/** 조회종료월(조회조건) **/
	private String sSearchTo;
	
	/** 조회수 (resultMap) **/
	private int totCnt;
	
	/** 청 상계금액합계 (resultMap) **/
	private String totExmpAmntSum;

	/** 공사 상계금액합계 (resultMap) **/
	private String totExmpAmntPaSum;

	/** 청 공사 상계금액합계 (resultMap) **/
	private String totExmpAmntTotSum;

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
	 * @return the sSearchTarget
	 */
	public String getsSearchTarget() {
		return sSearchTarget;
	}

	/**
	 * @param sSearchTarget the sSearchTarget to set
	 */
	public void setsSearchTarget(String sSearchTarget) {
		this.sSearchTarget = sSearchTarget;
	}

	/**
	 * @return the sBasis
	 */
	public String getsBasis() {
		return sBasis;
	}

	/**
	 * @param sBasis the sBasis to set
	 */
	public void setsBasis(String sBasis) {
		this.sBasis = sBasis;
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
	 * @return the sSearchFr
	 */
	public String getsSearchFr() {
		return sSearchFr;
	}

	/**
	 * @param sSearchFr the sSearchFr to set
	 */
	public void setsSearchFr(String sSearchFr) {
		this.sSearchFr = sSearchFr;
	}

	/**
	 * @return the sSearchTo
	 */
	public String getsSearchTo() {
		return sSearchTo;
	}

	/**
	 * @param sSearchTo the sSearchTo to set
	 */
	public void setsSearchTo(String sSearchTo) {
		this.sSearchTo = sSearchTo;
	}

	/**
	 * @return the totCnt
	 */
	public int getTotCnt() {
		return totCnt;
	}

	/**
	 * @param totCnt the totCnt to set
	 */
	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}

	/**
	 * @return the totExmpAmntSum
	 */
	public String getTotExmpAmntSum() {
		return totExmpAmntSum;
	}

	/**
	 * @param totExmpAmntSum the totExmpAmntSum to set
	 */
	public void setTotExmpAmntSum(String totExmpAmntSum) {
		this.totExmpAmntSum = totExmpAmntSum;
	}

	/**
	 * @return the totExmpAmntPaSum
	 */
	public String getTotExmpAmntPaSum() {
		return totExmpAmntPaSum;
	}

	/**
	 * @param totExmpAmntPaSum the totExmpAmntPaSum to set
	 */
	public void setTotExmpAmntPaSum(String totExmpAmntPaSum) {
		this.totExmpAmntPaSum = totExmpAmntPaSum;
	}

	/**
	 * @return the totExmpAmntTotSum
	 */
	public String getTotExmpAmntTotSum() {
		return totExmpAmntTotSum;
	}

	/**
	 * @param totExmpAmntTotSum the totExmpAmntTotSum to set
	 */
	public void setTotExmpAmntTotSum(String totExmpAmntTotSum) {
		this.totExmpAmntTotSum = totExmpAmntTotSum;
	}
}
