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

    /** 시설구분 */
    private String searchFcltyCdSub;

	/** 조회시작일(조회조건) **/
	private String sSearchDtFr;

	/** 조회종료일(조회조건) **/
	private String sSearchDtTo;




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
	 * @return the searchFcltyCdSub
	 */
	public String getSearchFcltyCdSub() {
		return searchFcltyCdSub;
	}

	/**
	 * @param searchFcltyCdSub the searchFcltyCdSub to set
	 */
	public void setSearchFcltyCdSub(String searchFcltyCdSub) {
		this.searchFcltyCdSub = searchFcltyCdSub;
	}

	/**
	 * @return the sSearchDtFr
	 */
	public String getsSearchDtFr() {
		return sSearchDtFr;
	}

	/**
	 * @param sSearchDtFr the sSearchDtFr to set
	 */
	public void setsSearchDtFr(String sSearchDtFr) {
		this.sSearchDtFr = sSearchDtFr;
	}

	/**
	 * @return the sSearchDtTo
	 */
	public String getsSearchDtTo() {
		return sSearchDtTo;
	}

	/**
	 * @param sSearchDtTo the sSearchDtTo to set
	 */
	public void setsSearchDtTo(String sSearchDtTo) {
		this.sSearchDtTo = sSearchDtTo;
	}
}
