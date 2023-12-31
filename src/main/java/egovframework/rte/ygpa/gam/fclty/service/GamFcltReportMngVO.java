/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2015. 2. 6.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 6.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltReportMngVO extends ComDefaultVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** 시설물번호 */
    private String fcltsNo;
    
    
    /** 시설물관리그룹번호 */
    private String fcltsMngGroupNo;
    
    
    /** 계획이력구분 */
    private String planHistSe;
    
    
    /** 시설물업무구분 */
    private String fcltsJobSe;
    
    
    
    

	/**
	 * @return the fcltsNo
	 */
	public String getFcltsNo() {
		return fcltsNo;
	}

	/**
	 * @param fcltsNo the fcltsNo to set
	 */
	public void setFcltsNo(String fcltsNo) {
		this.fcltsNo = fcltsNo;
	}

	/**
	 * @return the fcltsMngGroupNo
	 */
	public String getFcltsMngGroupNo() {
		return fcltsMngGroupNo;
	}

	/**
	 * @param fcltsMngGroupNo the fcltsMngGroupNo to set
	 */
	public void setFcltsMngGroupNo(String fcltsMngGroupNo) {
		this.fcltsMngGroupNo = fcltsMngGroupNo;
	}

	/**
	 * @return the planHistSe
	 */
	public String getPlanHistSe() {
		return planHistSe;
	}

	/**
	 * @param planHistSe the planHistSe to set
	 */
	public void setPlanHistSe(String planHistSe) {
		this.planHistSe = planHistSe;
	}

	/**
	 * @return the fcltsJobSe
	 */
	public String getFcltsJobSe() {
		return fcltsJobSe;
	}

	/**
	 * @param fcltsJobSe the fcltsJobSe to set
	 */
	public void setFcltsJobSe(String fcltsJobSe) {
		this.fcltsJobSe = fcltsJobSe;
	}

}
