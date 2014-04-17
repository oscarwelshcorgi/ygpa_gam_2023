package egovframework.rte.ygpa.gam.cmmn.itgrn.service;

import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;

public class GamNticPayListVO extends ErpCmmnCdDefaultVO{
	private static final long serialVersionUID = 1L;

	/** 항코드 */
	private String prtAtCode;
	
	/** 고지 검색 시작일 */
	private String sGrUsagePdFrom;
	
	/** 고지 검색 종료일*/
	private String sGrUsagePdTo;
	
	/** 요금 종류 코드 */
	private String chrgeKndCd;
	
	/** 요금 종류 이름 */
	private String chrgeKndNm;
	
	/** 수납 구분 */
	private String rcivSe;
	
	/** 회계 년도 */
	private String fiscalYr;
	
	/** 고지 번호 */
	private String nticno;

	/** 레코드 번호 */
	private String intSeq;
	
	/**
	 * @return the prtAtCode
	 */
	public String getPrtAtCode() {
		return prtAtCode;
	}

	/**
	 * @param prtAtCode the prtAtCode to set
	 */
	public void setPrtAtCode(String prtAtCode) {
		this.prtAtCode = prtAtCode;
	}

	/**
	 * @return the sGrUsagePdFrom
	 */
	public String getsGrUsagePdFrom() {
		return sGrUsagePdFrom;
	}

	/**
	 * @param sGrUsagePdFrom the sGrUsagePdFrom to set
	 */
	public void setsGrUsagePdFrom(String sGrUsagePdFrom) {
		this.sGrUsagePdFrom = sGrUsagePdFrom;
	}

	/**
	 * @return the sGrUsagePdTo
	 */
	public String getsGrUsagePdTo() {
		return sGrUsagePdTo;
	}

	/**
	 * @param sGrUsagePdTo the sGrUsagePdTo to set
	 */
	public void setsGrUsagePdTo(String sGrUsagePdTo) {
		this.sGrUsagePdTo = sGrUsagePdTo;
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
	 * @return the chrgeKndNm
	 */
	public String getChrgeKndNm() {
		return chrgeKndNm;
	}

	/**
	 * @param chrgeKndNm the chrgeKndNm to set
	 */
	public void setChrgeKndNm(String chrgeKndNm) {
		this.chrgeKndNm = chrgeKndNm;
	}

	/**
	 * @return the rcivSe
	 */
	public String getRcivSe() {
		return rcivSe;
	}

	/**
	 * @param rcivSe the rcivSe to set
	 */
	public void setRcivSe(String rcivSe) {
		this.rcivSe = rcivSe;
	}

	/**
	 * @return the fiscalYr
	 */
	public String getFiscalYr() {
		return fiscalYr;
	}

	/**
	 * @param fiscalYr the fiscalYr to set
	 */
	public void setFiscalYr(String fiscalYr) {
		this.fiscalYr = fiscalYr;
	}

	/**
	 * @return the nticno
	 */
	public String getNticno() {
		return nticno;
	}

	/**
	 * @param nticno the nticno to set
	 */
	public void setNticno(String nticno) {
		this.nticno = nticno;
	}

	/**
	 * @return the intSeq
	 */
	public String getIntSeq() {
		return intSeq;
	}

	/**
	 * @param intSeq the intSeq to set
	 */
	public void setIntSeq(String intSeq) {
		this.intSeq = intSeq;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}