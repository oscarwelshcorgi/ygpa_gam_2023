/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author LFIT
 * @since 2014. 12. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 9.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamMechFcltySpecInqireVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 항코드 (조회조건) **/
	private String sPrtAtCode;
	
	/** 시설물관리그룹 (조회조건) **/
	private String sFcltsMngGroupNo;

	/** 시설분류 (조회조건) **/
	private String sPrtFcltyCd;

	/** 시설명 (조회조건) **/
	private String sPrtFcltyNm;

	/** 소재지 (조회조건) **/
	private String sLoc;

	/** 시설물 관리 번호 (조회조건-첨부파일정보출력시 사용) **/
	private String fcltsMngNo;

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
	 * @return the fcltsMngNo
	 */
	public String getFcltsMngNo() {
		return fcltsMngNo;
	}

	/**
	 * @param fcltsMngNo the fcltsMngNo to set
	 */
	public void setFcltsMngNo(String fcltsMngNo) {
		this.fcltsMngNo = fcltsMngNo;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
