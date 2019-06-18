/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamPopupAgentFInfoVO.java
 * @Description : 허가원부정보 VO class
 * @Modification Information
 *
 * @author HNJ
 * @since 2014. 9. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 25.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamPopupSocAgentFInfoVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

    /** 구분 */
    private String sSe;

	/** 청코드 */
    private String sPrtAtCode;

    /** 업체코드 */
    private String sAgentCode;

    /** 업체명 */
    private String sAgentName;



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
	 * @return the sAgentName
	 */
	public String getsAgentName() {
		return sAgentName;
	}

	/**
	 * @param sAgentName the sAgentName to set
	 */
	public void setsAgentName(String sAgentName) {
		this.sAgentName = sAgentName;
	}

	/**
	 * @return the sSe
	 */
	public String getsSe() {
		return sSe;
	}

	/**
	 * @param sSe the sSe to set
	 */
	public void setsSe(String sSe) {
		this.sSe = sSe;
	}

}
