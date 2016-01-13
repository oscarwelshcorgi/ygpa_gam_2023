/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 26.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 26.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamPopupCtrtNoVO extends ComDefaultVO  {
	private static final long serialVersionUID = 1L;
	
	/** 계약번호 */
	private String sCtrtNo;
	
	/** 계약명 */
	private String sCtrtNm;
	
	/** 계약년도 */
	private String sCtrtYear;

	/**
	 * @return the sCtrtNo
	 */
	public String getsCtrtNo() {
		return sCtrtNo;
	}

	/**
	 * @param sCtrtNo the sCtrtNo to set
	 */
	public void setsCtrtNo(String sCtrtNo) {
		this.sCtrtNo = sCtrtNo;
	}

	/**
	 * @return the sCtrtNm
	 */
	public String getsCtrtNm() {
		return sCtrtNm;
	}

	/**
	 * @param sCtrtNm the sCtrtNm to set
	 */
	public void setsCtrtNm(String sCtrtNm) {
		this.sCtrtNm = sCtrtNm;
	}

	/**
	 * @return the sCtrtYear
	 */
	public String getsCtrtYear() {
		return sCtrtYear;
	}

	/**
	 * @param sCtrtYear the sCtrtYear to set
	 */
	public void setsCtrtYear(String sCtrtYear) {
		this.sCtrtYear = sCtrtYear;
	}
	
}
