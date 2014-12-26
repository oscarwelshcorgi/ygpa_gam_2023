/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 2.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 2.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltsFeeMngNticDetailVo extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String	mngMt;				//관리 월
	private	String	mngFeeJobSe;		//관리비 업무 구분
	private	String	mngSeq;				//관리 순번
	private	String	reqestSeq;			//의뢰 순번
	private	String	entrpscd;			//업체코드
	private	String	entrpsNm;			//업체명
	private	String	bizrno;				//사업자번호
	private	String	mngFeeJobSeNm;		//관리비 업무 구분 명
	private String	prtAtCode;			//항 코드
	private String	chrgeKnd;			//요금 종류
	private String	chrgeKndNm;			//요금 종류 명
	private String	accnutYear;			//회계 년도
	private String	nticNo;				//고지 번호
	private String	firstNticDt;		//최초 고지 일자
	private String	firstPayTmlmt;		//최초 납부 기한
	private String	nticDt;				//고지 일자
	private String	payTmlmt;			//납부 기한
	private String	fee;				//사용료
	private String	vatYn;				//부가세 구분
	private String	vatYnNm;			//부가세 구분 명
	private String	vat;				//부가세
	private String	nticAmt;			//고지 금액
	private String	totNticAmt;			//총 고지 금액
	private String	rcivSe;				//수납 구분
	private String	rcivSeNm;			//수납 구분 명
	private String	rcivDt;				//수납 일자
	private String	nhtIsueYn;			//고지 여부
	private String	nhtPrintYn;			//출력 여부
	private String	aditNticYn;			//추가 고지 여부
	private String	nticMth;			//고지 방법
	private String	arrrgNo;			//연체 번호
	private String	arrrgAmt;			//연체 금액
	private String	arrrgTariff;		//연체 요율
	private String	arrrgPayDates;		//연체 일수
	private String	rm;					//비고
	private	String	deptCd;				//부서코드
	private	String	regUsr;				//등록자
	private	String	registDt;			//등록일시
	private	String	updUsr;				//수정자
	private	String	updtDt;				//수정일시
	private String	arrrgSttus;			//연체 상태

	/**
	 * @return the mngMt
	 */
	public String getMngMt() {
		return mngMt;
	}
	/**
	 * @param mngMt the mngMt to set
	 */
	public void setMngMt(String mngMt) {
		this.mngMt = mngMt;
	}
	/**
	 * @return the mngFeeJobSe
	 */
	public String getMngFeeJobSe() {
		return mngFeeJobSe;
	}
	/**
	 * @param mngFeeJobSe the mngFeeJobSe to set
	 */
	public void setMngFeeJobSe(String mngFeeJobSe) {
		this.mngFeeJobSe = mngFeeJobSe;
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
	 * @return the reqestSeq
	 */
	public String getReqestSeq() {
		return reqestSeq;
	}
	/**
	 * @param reqestSeq the reqestSeq to set
	 */
	public void setReqestSeq(String reqestSeq) {
		this.reqestSeq = reqestSeq;
	}
	/**
	 * @return the entrpscd
	 */
	public String getEntrpscd() {
		return entrpscd;
	}
	/**
	 * @param entrpscd the entrpscd to set
	 */
	public void setEntrpscd(String entrpscd) {
		this.entrpscd = entrpscd;
	}
	/**
	 * @return the entrpsNm
	 */
	public String getEntrpsNm() {
		return entrpsNm;
	}
	/**
	 * @param entrpsNm the entrpsNm to set
	 */
	public void setEntrpsNm(String entrpsNm) {
		this.entrpsNm = entrpsNm;
	}
	/**
	 * @return the bizrno
	 */
	public String getBizrno() {
		return bizrno;
	}
	/**
	 * @param bizrno the bizrno to set
	 */
	public void setBizrno(String bizrno) {
		this.bizrno = bizrno;
	}
	/**
	 * @return the mngFeeJobSeNm
	 */
	public String getMngFeeJobSeNm() {
		return mngFeeJobSeNm;
	}
	/**
	 * @param mngFeeJobSeNm the mngFeeJobSeNm to set
	 */
	public void setMngFeeJobSeNm(String mngFeeJobSeNm) {
		this.mngFeeJobSeNm = mngFeeJobSeNm;
	}
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
	 * @return the chrgeKnd
	 */
	public String getChrgeKnd() {
		return chrgeKnd;
	}
	/**
	 * @param chrgeKnd the chrgeKnd to set
	 */
	public void setChrgeKnd(String chrgeKnd) {
		this.chrgeKnd = chrgeKnd;
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
	 * @return the firstNticDt
	 */
	public String getFirstNticDt() {
		return firstNticDt;
	}
	/**
	 * @param firstNticDt the firstNticDt to set
	 */
	public void setFirstNticDt(String firstNticDt) {
		this.firstNticDt = firstNticDt;
	}
	/**
	 * @return the firstPayTmlmt
	 */
	public String getFirstPayTmlmt() {
		return firstPayTmlmt;
	}
	/**
	 * @param firstPayTmlmt the firstPayTmlmt to set
	 */
	public void setFirstPayTmlmt(String firstPayTmlmt) {
		this.firstPayTmlmt = firstPayTmlmt;
	}
	/**
	 * @return the nticDt
	 */
	public String getNticDt() {
		return nticDt;
	}
	/**
	 * @param nticDt the nticDt to set
	 */
	public void setNticDt(String nticDt) {
		this.nticDt = nticDt;
	}
	/**
	 * @return the payTmlmt
	 */
	public String getPayTmlmt() {
		return payTmlmt;
	}
	/**
	 * @param payTmlmt the payTmlmt to set
	 */
	public void setPayTmlmt(String payTmlmt) {
		this.payTmlmt = payTmlmt;
	}
	/**
	 * @return the fee
	 */
	public String getFee() {
		return fee;
	}
	/**
	 * @param fee the fee to set
	 */
	public void setFee(String fee) {
		this.fee = fee;
	}
	/**
	 * @return the vatYn
	 */
	public String getVatYn() {
		return vatYn;
	}
	/**
	 * @param vatYn the vatYn to set
	 */
	public void setVatYn(String vatYn) {
		this.vatYn = vatYn;
	}
	/**
	 * @return the vatYnNm
	 */
	public String getVatYnNm() {
		return vatYnNm;
	}
	/**
	 * @param vatYnNm the vatYnNm to set
	 */
	public void setVatYnNm(String vatYnNm) {
		this.vatYnNm = vatYnNm;
	}
	/**
	 * @return the vat
	 */
	public String getVat() {
		return vat;
	}
	/**
	 * @param vat the vat to set
	 */
	public void setVat(String vat) {
		this.vat = vat;
	}
	/**
	 * @return the nticAmt
	 */
	public String getNticAmt() {
		return nticAmt;
	}
	/**
	 * @param nticAmt the nticAmt to set
	 */
	public void setNticAmt(String nticAmt) {
		this.nticAmt = nticAmt;
	}
	/**
	 * @return the totNticAmt
	 */
	public String getTotNticAmt() {
		return totNticAmt;
	}
	/**
	 * @param totNticAmt the totNticAmt to set
	 */
	public void setTotNticAmt(String totNticAmt) {
		this.totNticAmt = totNticAmt;
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
	 * @return the rcivSeNm
	 */
	public String getRcivSeNm() {
		return rcivSeNm;
	}
	/**
	 * @param rcivSeNm the rcivSeNm to set
	 */
	public void setRcivSeNm(String rcivSeNm) {
		this.rcivSeNm = rcivSeNm;
	}
	/**
	 * @return the rcivDt
	 */
	public String getRcivDt() {
		return rcivDt;
	}
	/**
	 * @param rcivDt the rcivDt to set
	 */
	public void setRcivDt(String rcivDt) {
		this.rcivDt = rcivDt;
	}
	/**
	 * @return the nhtIsueYn
	 */
	public String getNhtIsueYn() {
		return nhtIsueYn;
	}
	/**
	 * @param nhtIsueYn the nhtIsueYn to set
	 */
	public void setNhtIsueYn(String nhtIsueYn) {
		this.nhtIsueYn = nhtIsueYn;
	}
	/**
	 * @return the nhtPrintYn
	 */
	public String getNhtPrintYn() {
		return nhtPrintYn;
	}
	/**
	 * @param nhtPrintYn the nhtPrintYn to set
	 */
	public void setNhtPrintYn(String nhtPrintYn) {
		this.nhtPrintYn = nhtPrintYn;
	}
	/**
	 * @return the aditNticYn
	 */
	public String getAditNticYn() {
		return aditNticYn;
	}
	/**
	 * @param aditNticYn the aditNticYn to set
	 */
	public void setAditNticYn(String aditNticYn) {
		this.aditNticYn = aditNticYn;
	}
	/**
	 * @return the nticMth
	 */
	public String getNticMth() {
		return nticMth;
	}
	/**
	 * @param nticMth the nticMth to set
	 */
	public void NticMth(String nticMth) {
		this.nticMth = nticMth;
	}
	/**
	 * @return the arrrgNo
	 */
	public String getArrrgNo() {
		return arrrgNo;
	}
	/**
	 * @param arrrgNo the arrrgNo to set
	 */
	public void setArrrgNo(String arrrgNo) {
		this.arrrgNo = arrrgNo;
	}
	/**
	 * @return the arrrgAmt
	 */
	public String getArrrgAmt() {
		return arrrgAmt;
	}
	/**
	 * @param arrrgAmt the arrrgAmt to set
	 */
	public void setArrrgAmt(String arrrgAmt) {
		this.arrrgAmt = arrrgAmt;
	}
	/**
	 * @return the arrrgTariff
	 */
	public String getArrrgTariff() {
		return arrrgTariff;
	}
	/**
	 * @param arrrgTariff the arrrgTariff to set
	 */
	public void setArrrgTariff(String arrrgTariff) {
		this.arrrgTariff = arrrgTariff;
	}
	/**
	 * @return the arrrgPayDates
	 */
	public String getArrrgPayDates() {
		return arrrgPayDates;
	}
	/**
	 * @param arrrgPayDates the arrrgPayDates to set
	 */
	public void setArrrgPayDates(String arrrgPayDates) {
		this.arrrgPayDates = arrrgPayDates;
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
	 * @return the deptCd
	 */
	public String getDeptCd() {
		return deptCd;
	}
	/**
	 * @param deptCd the deptCd to set
	 */
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
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
	/**
	 * @return the arrrgSttus
	 */
	public String getArrrgSttus() {
		return arrrgSttus;
	}
	/**
	 * @param arrrgSttus the arrrgSttus to set
	 */
	public void setArrrgSttus(String arrrgSttus) {
		this.arrrgSttus = arrrgSttus;
	}

}
