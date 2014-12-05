/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 26.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 26.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamPopupSocVsslCdVO extends ComDefaultVO  {
	private static final long serialVersionUID = 1L;

    /** 호출부호 */
    private String vsslKey;
    
    /** 선박명 */
    private String vsslKorNm;

	/**
	 * @return the vsslKey
	 */
	public String getVsslKey() {
		return vsslKey;
	}

	/**
	 * @param vsslKey the vsslKey to set
	 */
	public void setVsslKey(String vsslKey) {
		this.vsslKey = vsslKey;
	}

	/**
	 * @return the vsslKorNm
	 */
	public String getVsslKorNm() {
		return vsslKorNm;
	}

	/**
	 * @param vsslKorNm the vsslKorNm to set
	 */
	public void setVsslKorNm(String vsslKorNm) {
		this.vsslKorNm = vsslKorNm;
	}
}
