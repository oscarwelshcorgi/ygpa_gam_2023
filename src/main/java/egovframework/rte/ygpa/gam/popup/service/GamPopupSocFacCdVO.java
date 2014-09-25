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

	/** 청코드 */
    private String prtAtCode;
    
    /** 시설코드 */
    private String facCode;
    
    /** 시설부코드 */
    private String facSubCode;

    /** 시설명 */
    private String facKorNm;

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
	 * @return the facCode
	 */
	public String getFacCode() {
		return facCode;
	}

	/**
	 * @param facCode the facCode to set
	 */
	public void setFacCode(String facCode) {
		this.facCode = facCode;
	}

	/**
	 * @return the facSubCode
	 */
	public String getFacSubCode() {
		return facSubCode;
	}

	/**
	 * @param facSubCode the facSubCode to set
	 */
	public void setFacSubCode(String facSubCode) {
		this.facSubCode = facSubCode;
	}

	/**
	 * @return the facKorNm
	 */
	public String getFacKorNm() {
		return facKorNm;
	}

	/**
	 * @param facKorNm the facKorNm to set
	 */
	public void setFacKorNm(String facKorNm) {
		this.facKorNm = facKorNm;
	}
}
