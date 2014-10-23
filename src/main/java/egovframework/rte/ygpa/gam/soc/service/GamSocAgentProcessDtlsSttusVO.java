/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 15.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamSocAgentProcessDtlsSttusVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	/** 등록항구(조회조건) **/
	private String sPrtAtCode;
	
	/** 공사항구(조회조건) **/
	private String sAppPrtAtCode;

	/** 공사준공년도(조회조건) **/
	private String sCmplYr;

	/** 허가원부일련번호(조회조건) **/
	private String sConstNo;
	
	/** 신청업체(조회조건) **/
	private String sAppAgentCode;
	
	/** 요금종류(조회조건) **/
	private String sFeeTp;
	
	/** 고지일자 시작(조회조건) **/
	private String sBillDtFr;

	/** 고지일자 종료(조회조건) **/
	private String sBillDtTo;

	/** 보전신청 총액**/
	private String sumExmpAmnt;
	
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
	 * @return the sAppPrtAtCode
	 */
	public String getsAppPrtAtCode() {
		return sAppPrtAtCode;
	}

	/**
	 * @param sAppPrtAtCode the sAppPrtAtCode to set
	 */
	public void setsAppPrtAtCode(String sAppPrtAtCode) {
		this.sAppPrtAtCode = sAppPrtAtCode;
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
	 * @return the sConstNo
	 */
	public String getsConstNo() {
		return sConstNo;
	}

	/**
	 * @param sConstNo the sConstNo to set
	 */
	public void setsConstNo(String sConstNo) {
		this.sConstNo = sConstNo;
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
	 * @return the sBillDtFr
	 */
	public String getsBillDtFr() {
		return sBillDtFr;
	}

	/**
	 * @param sBillDtFr the sBillDtFr to set
	 */
	public void setsBillDtFr(String sBillDtFr) {
		this.sBillDtFr = sBillDtFr;
	}

	/**
	 * @return the sBillDtTo
	 */
	public String getsBillDtTo() {
		return sBillDtTo;
	}

	/**
	 * @param sBillDtTo the sBillDtTo to set
	 */
	public void setsBillDtTo(String sBillDtTo) {
		this.sBillDtTo = sBillDtTo;
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
