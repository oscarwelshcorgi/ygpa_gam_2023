/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 10.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldRentMngtMainVO extends ComDefaultVO{
	private static final long serialVersionUID = 1L;
	
	private String sNticDt;			/** 고지(예정)날짜 */
	private String sPaySe; 			/** 납부구분 */
	private String sTermnYn; 		/** 계약해지유무 */
	/**
	 * @return the sNticDt
	 */
	public String getsNticDt() {
		return sNticDt;
	}
	/**
	 * @param sNticDt the sNticDt to set
	 */
	public void setsNticDt(String sNticDt) {
		this.sNticDt = sNticDt;
	}
	/**
	 * @return the sPaySe
	 */
	public String getsPaySe() {
		return sPaySe;
	}
	/**
	 * @param sPaySe the sPaySe to set
	 */
	public void setsPaySe(String sPaySe) {
		this.sPaySe = sPaySe;
	}
	/**
	 * @return the sTermnYn
	 */
	public String getsTermnYn() {
		return sTermnYn;
	}
	/**
	 * @param sTermnYn the sTermnYn to set
	 */
	public void setsTermnYn(String sTermnYn) {
		this.sTermnYn = sTermnYn;
	}

}
