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

}
