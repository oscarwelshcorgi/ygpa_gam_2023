/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 14.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 14.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamSocApplyDtlsVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	/** 등록항구(조회조건) **/
	private String sPrtAtCode;
	
	/** 공사항구(조회조건) **/
	private String sAppPrtAtCode;
		
	/** 면제요청업체코드(조회조건) **/
	private String sAppAgentCode;

	/** 요금종류(조회조건) **/
	private String sFeeTp;

	/** 보전신청 총액**/
	private String sumExmpAmnt;
	
	/** 보전누계 총액**/
	private String sumExmpAcc;
	
	/** 남아있는 잔고총액**/
	private String sumExmpRemain;
	
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
	 * @return the sumExmpRemain
	 */
	public String getSumExmpRemain() {
		return sumExmpRemain;
	}

	/**
	 * @param sumExmpRemain the sumExmpRemain to set
	 */
	public void setSumExmpRemain(String sumExmpRemain) {
		this.sumExmpRemain = sumExmpRemain;
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
