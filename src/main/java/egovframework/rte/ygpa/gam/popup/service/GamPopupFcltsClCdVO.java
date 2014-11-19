/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 5.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamPopupFcltsClCdVO extends ComDefaultVO  {
	private static final long serialVersionUID = 1L;
	
	/** 시설물 분류코드 첫문자 (조회조건) **/
	private String sFcltsClCdChar;
	
	/** 시설물 분류코드 (조회조건) **/
	private String sFcltsClCd;
	
	/** 시설물 분류명 (조회조건) **/
	private String sFcltsClCdNm;

	/**
	 * @return the sFcltsClCdChar
	 */
	public String getsFcltsClCdChar() {
		return sFcltsClCdChar;
	}

	/**
	 * @param sFcltsClCdChar the sFcltsClCdChar to set
	 */
	public void setsFcltsClCdChar(String sFcltsClCdChar) {
		this.sFcltsClCdChar = sFcltsClCdChar;
	}

	/**
	 * @return the sFcltsClCd
	 */
	public String getsFcltsClCd() {
		return sFcltsClCd;
	}

	/**
	 * @param sFcltsClCd the sFcltsClCd to set
	 */
	public void setsFcltsClCd(String sFcltsClCd) {
		this.sFcltsClCd = sFcltsClCd;
	}

	/**
	 * @return the sFcltsClCdNm
	 */
	public String getsFcltsClCdNm() {
		return sFcltsClCdNm;
	}

	/**
	 * @param sFcltsClCdNm the sFcltsClCdNm to set
	 */
	public void setsFcltsClCdNm(String sFcltsClCdNm) {
		this.sFcltsClCdNm = sFcltsClCdNm;
	}

}
