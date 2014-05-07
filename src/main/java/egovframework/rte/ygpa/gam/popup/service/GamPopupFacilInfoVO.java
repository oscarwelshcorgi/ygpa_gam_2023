package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamPopupFacilInfoVO.java
 * @Description : 선식정보 VO class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-05-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamPopupFacilInfoVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    private String sPrtAtCode;
    
    private String sFacCode;
    
    private String sFacSubCode;
    
    private String sFacKorNm;

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

}
