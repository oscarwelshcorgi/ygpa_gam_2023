/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 16.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 16.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamSocPrtFcltyFeeExmpRqestSttusVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	/** 청코드(조회조건) **/
	private String sPrtAtCode;
	
	/** 업체코드(조회조건) **/
	private String sAppAgentCode;

	/** 구분(조회조건) **/
	private String sUseYn;

	/** 공사준공년도(조회조건) **/
	private String sCmplYr;

	/** 보전신청 총액**/
	private String sumExmpAmnt;
	
	/** 보전누계 총액**/
	private String sumExmpAcc;
	
	/** 조회검색수 **/
	private int totCnt;
	
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
	 * @return the sAppAgentCode
	 */
	public String getsAppAgentCode() {
		return sAppAgentCode;
	}

	/**
	 * @param sAppAgentCode the sAppAgentCode to set
	 */
	public void setsAppAgentCode(String sAppAgentCode) {
		this.sAppAgentCode = sAppAgentCode;
	}

	/**
	 * @return the sUseYn
	 */
	public String getsUseYn() {
		return sUseYn;
	}

	/**
	 * @param sUseYn the sUseYn to set
	 */
	public void setsUseYn(String sUseYn) {
		this.sUseYn = sUseYn;
	}

	/**
	 * @return the sCmplYr
	 */
	public String getsCmplYr() {
		return sCmplYr;
	}

	/**
	 * @param sCmplYr the sCmplYr to set
	 */
	public void setsCmplYr(String sCmplYr) {
		this.sCmplYr = sCmplYr;
	}

	/**
	 * @return the sumExmpAmnt
	 */
	public String getSumExmpAmnt() {
		return sumExmpAmnt;
	}

	/**
	 * @param sumExmpAmnt the sumExmpAmnt to set
	 */
	public void setSumExmpAmnt(String sumExmpAmnt) {
		this.sumExmpAmnt = sumExmpAmnt;
	}

	/**
	 * @return the sumExmpAcc
	 */
	public String getSumExmpAcc() {
		return sumExmpAcc;
	}

	/**
	 * @param sumExmpAcc the sumExmpAcc to set
	 */
	public void setSumExmpAcc(String sumExmpAcc) {
		this.sumExmpAcc = sumExmpAcc;
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
	
}
