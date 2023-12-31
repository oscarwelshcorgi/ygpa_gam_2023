/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 30.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 30.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamPopupHtldAddNticVO extends GamHtldRentMngDefaultVO{
	private static final long serialVersionUID = 1L;
	
	private String rntfeeSeq;				/** 임대료순번 */
	private String rentDetailRegistSeq;	/** 등록순번 */
	private String rntfeeSe; 				/** 임대료구분 0 - 일반 1 -실적 2- 지적 3 - 추가*/
	private String rntfeeSeNm;			/** 임대료구분명(추가일때만 사용) */
	private String paySe;					/** 납부구분 */
	private String nticBeginDt; 			/** 고지대상시작일 */
	private String nticEndDt;				/** 고지대상종료일 */
	private String rntfee; 					/** 임대료 */
	private String rm;						/** 비고 */
	private String regUsr;					/** 등록자 */
	private String updUsr;					/** 수정자 */
	/**
	 * @return the rntfeeSeq
	 */
	public String getRntfeeSeq() {
		return rntfeeSeq;
	}
	/**
	 * @param rntfeeSeq the rntfeeSeq to set
	 */
	public void setRntfeeSeq(String rntfeeSeq) {
		this.rntfeeSeq = rntfeeSeq;
	}
	/**
	 * @return the rentDetailRegistSeq
	 */
	public String getRentDetailRegistSeq() {
		return rentDetailRegistSeq;
	}
	/**
	 * @param rentDetailRegistSeq the rentDetailRegistSeq to set
	 */
	public void setRentDetailRegistSeq(String rentDetailRegistSeq) {
		this.rentDetailRegistSeq = rentDetailRegistSeq;
	}
	/**
	 * @return the rntfeeSe
	 */
	public String getRntfeeSe() {
		return rntfeeSe;
	}
	/**
	 * @param rntfeeSe the rntfeeSe to set
	 */
	public void setRntfeeSe(String rntfeeSe) {
		this.rntfeeSe = rntfeeSe;
	}
	/**
	 * @return the rntfeeSeNm
	 */
	public String getRntfeeSeNm() {
		return rntfeeSeNm;
	}
	/**
	 * @param rntfeeSeNm the rntfeeSeNm to set
	 */
	public void setRntfeeSeNm(String rntfeeSeNm) {
		this.rntfeeSeNm = rntfeeSeNm;
	}
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
	 * @return the nticBeginDt
	 */
	public String getNticBeginDt() {
		return nticBeginDt;
	}
	/**
	 * @param nticBeginDt the nticBeginDt to set
	 */
	public void setNticBeginDt(String nticBeginDt) {
		this.nticBeginDt = nticBeginDt;
	}
	/**
	 * @return the nticEndDt
	 */
	public String getNticEndDt() {
		return nticEndDt;
	}
	/**
	 * @param nticEndDt the nticEndDt to set
	 */
	public void setNticEndDt(String nticEndDt) {
		this.nticEndDt = nticEndDt;
	}
	/**
	 * @return the rntfee
	 */
	public String getRntfee() {
		return rntfee;
	}
	/**
	 * @param rntfee the rntfee to set
	 */
	public void setRntfee(String rntfee) {
		this.rntfee = rntfee;
	}
	/**
	 * @return the rm
	 */
	public String getRm() {
		return rm;
	}
	/**
	 * @param rm the rm to set
	 */
	public void setRm(String rm) {
		this.rm = rm;
	}
	/**
	 * @return the regUsr
	 */
	public String getRegUsr() {
		return regUsr;
	}
	/**
	 * @param regUsr the regUsr to set
	 */
	public void setRegUsr(String regUsr) {
		this.regUsr = regUsr;
	}
	/**
	 * @return the updUsr
	 */
	public String getUpdUsr() {
		return updUsr;
	}
	/**
	 * @param updUsr the updUsr to set
	 */
	public void setUpdUsr(String updUsr) {
		this.updUsr = updUsr;
	}

}
