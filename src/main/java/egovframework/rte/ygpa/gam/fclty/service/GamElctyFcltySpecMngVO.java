/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 19.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 19.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamElctyFcltySpecMngVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	
	/** 항만시설 구분 */
    private String prtFcltySe;

	/** 항구분 */
    private String sPrtAtCode;
    
    /** 시설물관리그룹넘버 */
    private String sFcltsMngGroupNo;
    
    /** 시설분류 */
    private String sPrtFcltyCd;
    
    /** 시설명 */
    private String sPrtFcltyNm;
    
    /**소재지*/
    private String sLoc;
    
    private String sFcltsMngNo;

	/**
	 * @return the prtFcltySe
	 */
	public String getPrtFcltySe() {
		return prtFcltySe;
	}

	/**
	 * @param prtFcltySe the prtFcltySe to set
	 */
	public void setPrtFcltySe(String prtFcltySe) {
		this.prtFcltySe = prtFcltySe;
	}

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
	 * @return the sFcltsMngGroupNo
	 */
	public String getsFcltsMngGroupNo() {
		return sFcltsMngGroupNo;
	}

	/**
	 * @param sFcltsMngGroupNo the sFcltsMngGroupNo to set
	 */
	public void setsFcltsMngGroupNo(String sFcltsMngGroupNo) {
		this.sFcltsMngGroupNo = sFcltsMngGroupNo;
	}

	/**
	 * @return the sPrtFcltyCd
	 */
	public String getsPrtFcltyCd() {
		return sPrtFcltyCd;
	}

	/**
	 * @param sPrtFcltyCd the sPrtFcltyCd to set
	 */
	public void setsPrtFcltyCd(String sPrtFcltyCd) {
		this.sPrtFcltyCd = sPrtFcltyCd;
	}

	/**
	 * @return the sPrtFcltyNm
	 */
	public String getsPrtFcltyNm() {
		return sPrtFcltyNm;
	}

	/**
	 * @param sPrtFcltyNm the sPrtFcltyNm to set
	 */
	public void setsPrtFcltyNm(String sPrtFcltyNm) {
		this.sPrtFcltyNm = sPrtFcltyNm;
	}

	/**
	 * @return the sLoc
	 */
	public String getsLoc() {
		return sLoc;
	}

	/**
	 * @param sLoc the sLoc to set
	 */
	public void setsLoc(String sLoc) {
		this.sLoc = sLoc;
	}

	/**
	 * @return the sFcltsMngNo
	 */
	public String getsFcltsMngNo() {
		return sFcltsMngNo;
	}

	/**
	 * @param sFcltsMngNo the sFcltsMngNo to set
	 */
	public void setsFcltsMngNo(String sFcltsMngNo) {
		this.sFcltsMngNo = sFcltsMngNo;
	}

	
	
}
