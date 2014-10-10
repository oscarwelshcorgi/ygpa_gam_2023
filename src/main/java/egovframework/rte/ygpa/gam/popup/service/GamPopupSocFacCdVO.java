/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 25.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamPopupSocFacCdVO extends ComDefaultVO  {
	private static final long serialVersionUID = 1L;

	/** 청코드 (조회조건)*/
    private String sPrtAtCode;
    
    /** 시설코드 (조회조건)*/
    private String sFacCode;
    
    /** 시설부코드 (조회조건)*/
    private String sFacSubCode;

    /** 시설명 (조회조건)*/
    private String sFacKorNm;

	private String prtAtCode;
    
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
	 * @return the sFacCode
	 */
	public String getsFacCode() {
		return sFacCode;
	}

	/**
	 * @param sFacCode the sFacCode to set
	 */
	public void setsFacCode(String sFacCode) {
		this.sFacCode = sFacCode;
	}

	/**
	 * @return the sFacSubCode
	 */
	public String getsFacSubCode() {
		return sFacSubCode;
	}

	/**
	 * @param sFacSubCode the sFacSubCode to set
	 */
	public void setsFacSubCode(String sFacSubCode) {
		this.sFacSubCode = sFacSubCode;
	}

	/**
	 * @return the sFacKorNm
	 */
	public String getsFacKorNm() {
		return sFacKorNm;
	}

	/**
	 * @param sFacKorNm the sFacKorNm to set
	 */
	public void setsFacKorNm(String sFacKorNm) {
		this.sFacKorNm = sFacKorNm;
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
}
