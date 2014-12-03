/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 18.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 18.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamPopupConsFcltyInfoVO extends ComDefaultVO  {
	private static final long serialVersionUID = 1L;
	
	/** 항코드 (조회조건) **/
	private String sPrtAtCode;
	
	/** 건축시설명(조회조건) **/
	private String sPrtFcltyNm;
	
	/** 건축시설분류코드(조회조건) **/
	private String sPrtFcltyCd;
	
	private String searchFcltyCd;

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
	 * @return the searchFcltyCd
	 */
	public String getSearchFcltyCd() {
		return searchFcltyCd;
	}

	/**
	 * @param searchFcltyCd the searchFcltyCd to set
	 */
	public void setSearchFcltyCd(String searchFcltyCd) {
		this.searchFcltyCd = searchFcltyCd;
	}
	
	
}
