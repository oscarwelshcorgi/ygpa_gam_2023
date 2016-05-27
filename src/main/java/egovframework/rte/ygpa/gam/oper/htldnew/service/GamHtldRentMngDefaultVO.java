/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 27.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 27.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldRentMngDefaultVO extends ComDefaultVO{
	private static final long serialVersionUID = 1L;
	
	private String mngYear;				/** 관리년도 */
	private String mngNo; 				/** 관리번호 */
	private String mngSeq;				/** 관리순번 */
	private String registSeq; 				/** 등록순번 */
	private String histSeq;					/** 이력순번 */
	/**
	 * @return the mngYear
	 */
	public String getMngYear() {
		return mngYear;
	}
	/**
	 * @param mngYear the mngYear to set
	 */
	public void setMngYear(String mngYear) {
		this.mngYear = mngYear;
	}
	/**
	 * @return the mngNo
	 */
	public String getMngNo() {
		return mngNo;
	}
	/**
	 * @param mngNo the mngNo to set
	 */
	public void setMngNo(String mngNo) {
		this.mngNo = mngNo;
	}
	/**
	 * @return the mngSeq
	 */
	public String getMngSeq() {
		return mngSeq;
	}
	/**
	 * @param mngSeq the mngSeq to set
	 */
	public void setMngSeq(String mngSeq) {
		this.mngSeq = mngSeq;
	}
	/**
	 * @return the registSeq
	 */
	public String getRegistSeq() {
		return registSeq;
	}
	/**
	 * @param registSeq the registSeq to set
	 */
	public void setRegistSeq(String registSeq) {
		this.registSeq = registSeq;
	}
	/**
	 * @return the histSeq
	 */
	public String getHistSeq() {
		return histSeq;
	}
	/**
	 * @param histSeq the histSeq to set
	 */
	public void setHistSeq(String histSeq) {
		this.histSeq = histSeq;
	}
}
