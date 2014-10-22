/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 15.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamSocApplyLgerVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	/** 등록항구(조회조건) **/
	private String sPrtAtCode;
			
	/** 면제요청업체코드(조회조건) **/
	private String sAppAgentCode;

	/** 요금종류(조회조건) **/
	private String sFeeTp;

	/** 사용유무(조회조건) **/
	private String sUseYn;

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
	 * @return the sFeeTp
	 */
	public String getsFeeTp() {
		return sFeeTp;
	}

	/**
	 * @param sFeeTp the sFeeTp to set
	 */
	public void setsFeeTp(String sFeeTp) {
		this.sFeeTp = sFeeTp;
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
}
