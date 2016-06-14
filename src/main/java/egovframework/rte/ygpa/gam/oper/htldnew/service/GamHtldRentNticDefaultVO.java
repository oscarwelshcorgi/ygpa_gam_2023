/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 7.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldRentNticDefaultVO extends GamHtldRentMngDefaultVO{
	private static final long serialVersionUID = 1L;
	
	private String	accnutYear; 		/** 회계년도 */
	private String	rntfeeNticNo;		/**	임대료 고지번호 */
	private String	nticSeq;			/** 임대료 고지순번 */
	private String 	nticNo;			/** BILL NO LEV_REQEST_F REV_COLL_F 용*/
	private String 	chrgeKndCd;		/** 요금종류코드 */
	/**
	 * @return the accnutYear
	 */
	public String getAccnutYear() {
		return accnutYear;
	}
	/**
	 * @param accnutYear the accnutYear to set
	 */
	public void setAccnutYear(String accnutYear) {
		this.accnutYear = accnutYear;
	}
	/**
	 * @return the rntfeeNticNo
	 */
	public String getRntfeeNticNo() {
		return rntfeeNticNo;
	}
	/**
	 * @param rntfeeNticNo the rntfeeNticNo to set
	 */
	public void setRntfeeNticNo(String rntfeeNticNo) {
		this.rntfeeNticNo = rntfeeNticNo;
	}
	/**
	 * @return the nticSeq
	 */
	public String getNticSeq() {
		return nticSeq;
	}
	/**
	 * @param nticSeq the nticSeq to set
	 */
	public void setNticSeq(String nticSeq) {
		this.nticSeq = nticSeq;
	}
	/**
	 * @return the nticNo
	 */
	public String getNticNo() {
		return nticNo;
	}
	/**
	 * @param nticNo the nticNo to set
	 */
	public void setNticNo(String nticNo) {
		this.nticNo = nticNo;
	}
	/**
	 * @return the chrgeKndCd
	 */
	public String getChrgeKndCd() {
		return chrgeKndCd;
	}
	/**
	 * @param chrgeKndCd the chrgeKndCd to set
	 */
	public void setChrgeKndCd(String chrgeKndCd) {
		this.chrgeKndCd = chrgeKndCd;
	}
}
