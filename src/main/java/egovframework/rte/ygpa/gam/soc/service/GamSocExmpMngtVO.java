/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 23.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 23.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamSocExmpMngtVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;

	/** 청코드(조회조건)  **/
	private String sAppPrtAtCode;

	/** 요금종류코드(조회조건)  **/
	private String sFeeTp;
	
	/** 회계년도(조회조건)  **/
	private String sFiscalYr;
	
	/** 관리번호(조회조건)  **/
	private String sSocNo;

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
	 * @return the sFiscalYr
	 */
	public String getsFiscalYr() {
		return sFiscalYr;
	}

	/**
	 * @param sFiscalYr the sFiscalYr to set
	 */
	public void setsFiscalYr(String sFiscalYr) {
		this.sFiscalYr = sFiscalYr;
	}

	/**
	 * @return the sSocNo
	 */
	public String getsSocNo() {
		return sSocNo;
	}

	/**
	 * @param sSocNo the sSocNo to set
	 */
	public void setsSocNo(String sSocNo) {
		this.sSocNo = sSocNo;
	}

}
