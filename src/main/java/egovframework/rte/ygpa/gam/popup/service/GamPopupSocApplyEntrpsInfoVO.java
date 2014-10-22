/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamPopupSocApplyEntrpsInfoVO.java
 * @Description : 투자비보전신청업체정보 VO class
 * @Modification Information
 * 
 * @author HNJ
 * @since 2014. 10. 21.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 21.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamPopupSocApplyEntrpsInfoVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;
	
	
	/** 등록항구(면제요청청코드) */
    private String appPrtAtCode;
    
    /** 구분 */
    private String gubun;
    
    /** 사용여부 */
    private String useYn;
    
    /** 면제요청업체코드 */
    private String appAgentCode;
    
    /** 면제요청업체명 */
    private String firmKorNm;
    
    

	/**
	 * @return the appPrtAtCode
	 */
	public String getAppPrtAtCode() {
		return appPrtAtCode;
	}

	/**
	 * @param appPrtAtCode the appPrtAtCode to set
	 */
	public void setAppPrtAtCode(String appPrtAtCode) {
		this.appPrtAtCode = appPrtAtCode;
	}

	/**
	 * @return the gubun
	 */
	public String getGubun() {
		return gubun;
	}

	/**
	 * @param gubun the gubun to set
	 */
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}

	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	/**
	 * @return the appAgentCode
	 */
	public String getAppAgentCode() {
		return appAgentCode;
	}

	/**
	 * @param appAgentCode the appAgentCode to set
	 */
	public void setAppAgentCode(String appAgentCode) {
		this.appAgentCode = appAgentCode;
	}

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

	
    
	

}
