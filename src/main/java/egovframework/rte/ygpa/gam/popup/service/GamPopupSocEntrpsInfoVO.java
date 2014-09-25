/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamPopupSocEntrpsInfoVO.java
 * @Description : 비관리청 업체정보 VO class
 * @Modification Information
 * 
 * @author HNJ
 * @since 2014. 9. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 24.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamPopupSocEntrpsInfoVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;
	
	
	/** 업체 명 */
    private String firmKorNm;
    
    /** 업체 코드 */
    private String agentCode;
    
    /** 사업자등록번호 */
    private String bzRgstId;

	/**
	 * @return the firmKorNm
	 */
	public String getFirmKorNm() {
		return firmKorNm;
	}

	/**
	 * @param firmKorNm the firmKorNm to set
	 */
	public void setFirmKorNm(String firmKorNm) {
		this.firmKorNm = firmKorNm;
	}

	/**
	 * @return the agentCode
	 */
	public String getAgentCode() {
		return agentCode;
	}

	/**
	 * @param agentCode the agentCode to set
	 */
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	/**
	 * @return the bzRgstId
	 */
	public String getBzRgstId() {
		return bzRgstId;
	}

	/**
	 * @param bzRgstId the bzRgstId to set
	 */
	public void setBzRgstId(String bzRgstId) {
		this.bzRgstId = bzRgstId;
	}
	

}
