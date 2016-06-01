/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class GamHtldRentMngtMainVO extends GamHtldRentMngDefaultVO{
	private static final long serialVersionUID = 1L;
	
	private String paySe; 			/** 납부구분 */
	private String termnYn; 		/** 계약해지유무 */
	/**
	 * @return the paySe
	 */
	public String getPaySe() {
		return paySe;
	}
	/**
	 * @param paySe the paySe to set
	 */
	public void setPaySe(String paySe) {
		this.paySe = paySe;
	}
	/**
	 * @return the termnYn
	 */
	public String getTermnYn() {
		return termnYn;
	}
	/**
	 * @param termnYn the termnYn to set
	 */
	public void setTermnYn(String termnYn) {
		this.termnYn = termnYn;
	}
}
