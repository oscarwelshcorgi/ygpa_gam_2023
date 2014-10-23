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
public class GamSocAgentProcessRealloadNewVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;

	/** 청코드(조회조건) **/
	private String sAppPrtAtCode;

	/** 요금종류코드(조회조건) **/
	private String sFeeTp;
	
	/** 공사준공년도(조회조건) **/
	private String sCmplYr;
	
	/** 공사일련번호(조회조건) **/
	private String sConstNo;
	
	/** 고지구분(조회조건) **/
	private String sBillGubun;
	
	/** 업체코드(조회조건) **/
	private String sAgentCode;

	/** 업체구분(조회조건) **/
	private String sAgentGubun;
	
	/** 조회시작일(조회조건) **/
	private String sSearchDtFr;

	/** 조회종료일(조회조건) **/
	private String sSearchDtTo;
	
	/** 총자료수 (resultMap) **/
	private int totalCnt;
	
	/** 총고지금액 (resultMap) **/
	private String sumExmpAmnt;

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
	 * @return the sBillGubun
	 */
	public String getsBillGubun() {
		return sBillGubun;
	}

	/**
	 * @param sBillGubun the sBillGubun to set
	 */
	public void setsBillGubun(String sBillGubun) {
		this.sBillGubun = sBillGubun;
	}

	/**
	 * @return the sAgentCode
	 */
	public String getsAgentCode() {
		return sAgentCode;
	}

	/**
	 * @param sAgentCode the sAgentCode to set
	 */
	public void setsAgentCode(String sAgentCode) {
		this.sAgentCode = sAgentCode;
	}

	/**
	 * @return the sAgentGubun
	 */
	public String getsAgentGubun() {
		return sAgentGubun;
	}

	/**
	 * @param sAgentGubun the sAgentGubun to set
	 */
	public void setsAgentGubun(String sAgentGubun) {
		this.sAgentGubun = sAgentGubun;
	}

	/**
	 * @return the sSearchDtFr
	 */
	public String getsSearchDtFr() {
		return sSearchDtFr;
	}

	/**
	 * @param sSearchDtFr the sSearchDtFr to set
	 */
	public void setsSearchDtFr(String sSearchDtFr) {
		this.sSearchDtFr = sSearchDtFr;
	}

	/**
	 * @return the sSearchDtTo
	 */
	public String getsSearchDtTo() {
		return sSearchDtTo;
	}

	/**
	 * @param sSearchDtTo the sSearchDtTo to set
	 */
	public void setsSearchDtTo(String sSearchDtTo) {
		this.sSearchDtTo = sSearchDtTo;
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
	public String getSumExmpAmnt() {
		return sumExmpAmnt;
	}

	/**
	 * @param sumExmpAmnt the sumExmpAmnt to set
	 */
	public void setSumExmpAmnt(String sumExmpAmnt) {
		this.sumExmpAmnt = sumExmpAmnt;
	}	
}
