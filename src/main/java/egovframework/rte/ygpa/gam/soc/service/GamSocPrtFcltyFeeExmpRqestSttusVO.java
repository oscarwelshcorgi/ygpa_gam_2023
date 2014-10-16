/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 16.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 16.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamSocPrtFcltyFeeExmpRqestSttusVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	/** 청코드(조회조건) **/
	private String sPrtAtCode;
	
	/** 업체코드(조회조건) **/
	private String sAppAgentCode;

	/** 구분(조회조건) **/
	private String sUseYn;

	/** 공사준공년도(조회조건) **/
	private String sCmplYr;

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
	 * @return the sAppAgentCode
	 */
	public String getsAppAgentCode() {
		return sAppAgentCode;
	}

	/**
	 * @param sAppAgentCode the sAppAgentCode to set
	 */
	public void setsAppAgentCode(String sAppAgentCode) {
		this.sAppAgentCode = sAppAgentCode;
	}

	/**
	 * @return the sUseYn
	 */
	public String getsUseYn() {
		return sUseYn;
	}

	/**
	 * @param sUseYn the sUseYn to set
	 */
	public void setsUseYn(String sUseYn) {
		this.sUseYn = sUseYn;
	}

	/**
	 * @return the sCmplYr
	 */
	public String getsCmplYr() {
		return sCmplYr;
	}

	/**
	 * @param sCmplYr the sCmplYr to set
	 */
	public void setsCmplYr(String sCmplYr) {
		this.sCmplYr = sCmplYr;
	}
	
}
