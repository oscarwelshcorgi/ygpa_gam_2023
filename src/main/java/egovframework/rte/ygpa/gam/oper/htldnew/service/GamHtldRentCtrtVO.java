/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 2.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 2.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldRentCtrtVO extends GamHtldRentMngDefaultVO{
	private static final long serialVersionUID = 1L;

	private String entrpsCd;		/** 업체코드 */
	private String boundCd;		/** 구역코드 */
	private String chrgeKndCd; 	/** 요금종류코드 */
	private String grRentAr;		/** 총임대면적 */
	private String paySe;			/** 납부구분 */
	private String ctrtBeginDt;		/** 계약기간 시작일 */
	private String ctrtEndDt; 		/** 계약기간 종료일 */
	private String ctrtDt;			/** 계약일 */
	private String termnDt; 		/** 계약해지일 */
	private String termnRsn; 		/** 계약해지사유 */
	private String regUsr; 			/** 등록자 */
	private String registDt; 		/** 등록일시 */
	private String updUsr;			/** 수정자 */
	private String updtDt;			/** 수정일시 */
	/**
	 * @return the entrpsCd
	 */
	public String getEntrpsCd() {
		return entrpsCd;
	}
	/**
	 * @param entrpsCd the entrpsCd to set
	 */
	public void setEntrpsCd(String entrpsCd) {
		this.entrpsCd = entrpsCd;
	}
	/**
	 * @return the boundCd
	 */
	public String getBoundCd() {
		return boundCd;
	}
	/**
	 * @param boundCd the boundCd to set
	 */
	public void setBoundCd(String boundCd) {
		this.boundCd = boundCd;
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
	/**
	 * @return the grRentAr
	 */
	public String getGrRentAr() {
		return grRentAr;
	}
	/**
	 * @param grRentAr the grRentAr to set
	 */
	public void setGrRentAr(String grRentAr) {
		this.grRentAr = grRentAr;
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
	 * @return the ctrtBeginDt
	 */
	public String getCtrtBeginDt() {
		return ctrtBeginDt;
	}
	/**
	 * @param ctrtBeginDt the ctrtBeginDt to set
	 */
	public void setCtrtBeginDt(String ctrtBeginDt) {
		this.ctrtBeginDt = ctrtBeginDt;
	}
	/**
	 * @return the ctrtEndDt
	 */
	public String getCtrtEndDt() {
		return ctrtEndDt;
	}
	/**
	 * @param ctrtEndDt the ctrtEndDt to set
	 */
	public void setCtrtEndDt(String ctrtEndDt) {
		this.ctrtEndDt = ctrtEndDt;
	}
	/**
	 * @return the ctrtDt
	 */
	public String getCtrtDt() {
		return ctrtDt;
	}
	/**
	 * @param ctrtDt the ctrtDt to set
	 */
	public void setCtrtDt(String ctrtDt) {
		this.ctrtDt = ctrtDt;
	}
	/**
	 * @return the termnDt
	 */
	public String getTermnDt() {
		return termnDt;
	}
	/**
	 * @param termnDt the termnDt to set
	 */
	public void setTermnDt(String termnDt) {
		this.termnDt = termnDt;
	}
	/**
	 * @return the termnRsn
	 */
	public String getTermnRsn() {
		return termnRsn;
	}
	/**
	 * @param termnRsn the termnRsn to set
	 */
	public void setTermnRsn(String termnRsn) {
		this.termnRsn = termnRsn;
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
	 * @return the registDt
	 */
	public String getRegistDt() {
		return registDt;
	}
	/**
	 * @param registDt the registDt to set
	 */
	public void setRegistDt(String registDt) {
		this.registDt = registDt;
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
	/**
	 * @return the updtDt
	 */
	public String getUpdtDt() {
		return updtDt;
	}
	/**
	 * @param updtDt the updtDt to set
	 */
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}
}
