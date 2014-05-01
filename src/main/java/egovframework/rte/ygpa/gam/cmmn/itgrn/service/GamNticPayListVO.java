package egovframework.rte.ygpa.gam.cmmn.itgrn.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;

@JsonIgnoreProperties(ignoreUnknown = true)
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
	
	/** 디스플레이 자료갯수 */
	private String dpTotCnt;

	/** 고지금액 */
	private String sumBillAmnt;
	
	/** 수납금액 */
	private String sumRcvdAmnt;

	/** 수납금액 */
	private String sumNotRcvdAmnt;
	
	/** 연체고지금액 */
	private String sumDlyBillAmnt;
	
	private String feeTp;
	
	private String billNo;
	
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

	/**
	 * @return the dpTotCnt
	 */
	public String getDpTotCnt() {
		return dpTotCnt;
	}

	/**
	 * @param dpTotCnt the dpTotCnt to set
	 */
	public void setDpTotCnt(String dpTotCnt) {
		this.dpTotCnt = dpTotCnt;
	}

	/**
	 * @return the sumBillAmnt
	 */
	public String getSumBillAmnt() {
		return sumBillAmnt;
	}

	/**
	 * @param sumBillAmnt the sumBillAmnt to set
	 */
	public void setSumBillAmnt(String sumBillAmnt) {
		this.sumBillAmnt = sumBillAmnt;
	}

	/**
	 * @return the sumRcvdAmnt
	 */
	public String getSumRcvdAmnt() {
		return sumRcvdAmnt;
	}

	/**
	 * @param sumRcvdAmnt the sumRcvdAmnt to set
	 */
	public void setSumRcvdAmnt(String sumRcvdAmnt) {
		this.sumRcvdAmnt = sumRcvdAmnt;
	}

	/**
	 * @return the sumNotRcvdAmnt
	 */
	public String getSumNotRcvdAmnt() {
		return sumNotRcvdAmnt;
	}

	/**
	 * @param sumRcvdAmnt the sumNotRcvdAmnt to set
	 */
	public void setSumNotRcvdAmnt(String sumNotRcvdAmnt) {
		this.sumNotRcvdAmnt = sumNotRcvdAmnt;
	}
	
	/**
	 * @return the sumDlyBillAmnt
	 */
	public String getSumDlyBillAmnt() {
		return sumDlyBillAmnt;
	}

	/**
	 * @param sumDlyBillAmnt the sumDlyBillAmnt to set
	 */
	public void setSumDlyBillAmnt(String sumDlyBillAmnt) {
		this.sumDlyBillAmnt = sumDlyBillAmnt;
	}

	/**
	 * @return the feeTp
	 */
	public String getFeeTp() {
		return feeTp;
	}

	/**
	 * @param feeTp the feeTp to set
	 */
	public void setFeeTp(String feeTp) {
		this.feeTp = feeTp;
	}

	/**
	 * @return the billNo
	 */
	public String getBillNo() {
		return billNo;
	}

	/**
	 * @param billNo the billNo to set
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
}