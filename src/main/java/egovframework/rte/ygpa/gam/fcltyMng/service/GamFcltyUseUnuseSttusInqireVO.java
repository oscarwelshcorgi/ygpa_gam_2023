/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author 정성현
 * @since 2014. 12. 11.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 11.	정성현		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltyUseUnuseSttusInqireVO extends ComDefaultVO  {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** 항코드 */
    private String searchPrtAtCode;

    /** 시설명 */
    private String searchFcltyNm;

    /** 소재지*/
    private String searchLoc;

    /** 조회시작일(조회조건) **/
	private String searchDtFr;

	/** 조회종료일(조회조건) **/
	private String searchDtTo;

	/**
	 * @return the searchPrtAtCode
	 */
	public String getSearchPrtAtCode() {
		return searchPrtAtCode;
	}

	/**
	 * @param searchPrtAtCode the searchPrtAtCode to set
	 */
	public void setSearchPrtAtCode(String searchPrtAtCode) {
		this.searchPrtAtCode = searchPrtAtCode;
	}

	/**
	 * @return the searchFcltyNm
	 */
	public String getSearchFcltyNm() {
		return searchFcltyNm;
	}

	/**
	 * @param searchFcltyNm the searchFcltyNm to set
	 */
	public void setSearchFcltyNm(String searchFcltyNm) {
		this.searchFcltyNm = searchFcltyNm;
	}

	/**
	 * @return the searchDtFr
	 */
	public String getSearchDtFr() {
		return searchDtFr;
	}

	/**
	 * @param searchDtFr the searchDtFr to set
	 */
	public void setSearchDtFr(String searchDtFr) {
		this.searchDtFr = searchDtFr;
	}

	/**
	 * @return the searchDtTo
	 */
	public String getSearchDtTo() {
		return searchDtTo;
	}

	/**
	 * @param searchDtTo the searchDtTo to set
	 */
	public void setSearchDtTo(String searchDtTo) {
		this.searchDtTo = searchDtTo;
	}

	/**
	 * @return the searchLoc
	 */
	public String getSearchLoc() {
		return searchLoc;
	}

	/**
	 * @param searchLoc the searchLoc to set
	 */
	public void setSearchLoc(String searchLoc) {
		this.searchLoc = searchLoc;
	}
}


