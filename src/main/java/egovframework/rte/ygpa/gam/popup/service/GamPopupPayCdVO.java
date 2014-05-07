/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 5. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 5. 7.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamPopupPayCdVO extends ComDefaultVO  {
	
	/** 항코드 */
    private String prtAtCode;
    
    /** 요금코드 */
    private String feeTp;
    
    /** 요금명 */
    private String feeTpKorNm;

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
	 * @return the feeTpKorNm
	 */
	public String getFeeTpKorNm() {
		return feeTpKorNm;
	}

	/**
	 * @param feeTpKorNm the feeTpKorNm to set
	 */
	public void setFeeTpKorNm(String feeTpKorNm) {
		this.feeTpKorNm = feeTpKorNm;
	}

}
