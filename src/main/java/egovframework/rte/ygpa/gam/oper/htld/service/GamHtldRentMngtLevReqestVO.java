package egovframework.rte.ygpa.gam.oper.htld.service;

import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;

/**
 * @Class Name : GamHtldRentMngtLevReqestVO.java
 * @Description : 배후단지사용목록관리 징수의뢰 VO class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-23
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public class GamHtldRentMngtLevReqestVO extends ErpCmmnCdDefaultVO {
    private static final long serialVersionUID = 1L;

    /** 고지 횟수 */
    private String nticCnt;

    /** 시설 구분 */
    private String fcltySe;

    /** 요금 종류 */
    private String chrgeKnd;

    /** 업체코드 */
    private String entrpscd;

    /** 업체 명 */
    private String entrpsNm;

    /** 고지 기간 FROM */
    private String nticPdFrom;

    /** 공사기간 TO */
    private String constPerTo;

    /** 회계 년도 */
    private String accnutYear;

    /** 고지번호 */
    private String nticno;

    /** 고지 일자 */
    private String nticDt;

    /** 납부 기한 */
    private String payTmlmt;

    /** 공시지가 */
    private String olnlp;

    /** 사용료 */
    private String fee;

    /** 부가세 여부 */
    private String vatYn;

    /** 부가세 */
    private String vat;

    /** 고지 금액 */
    private String nticAmt;

    /** 비고 */
    private String rm;

    /** 수납 구분 */
    private String rcivSe;

    /** 수납 일자 */
    private String rcivDt;

    /** 고지서 발부 여부 */
    private String nhtIsueYn;

    /** 연체 번호 */
    private String arrrgNo;

    /** 연체 금액 */
    private String arrrgAmt;

    /** 의뢰 순번 */
    private String reqestSeq;

    /** 부서코드 */
    private String deptcd;

    /** 고지 방법 */
    private String nticMth;

    /** 등록자 */
    private String regUsr;

    /** 등록일시 */
    private String registDt;

    /** 수정자 */
    private String updUsr;

    /** 수정일시 */
    private String updtDt;

    /** 항코드 */
    private String prtAtCode;

    /** 관리 년도 */
    private String mngYear;

    /** 관리 번호 */
    private String mngNo;

    /** 관리 횟수 */
    private String mngCnt;

    /** 허가 여부 */
    private String prmisnYn;

    /** 총사용기간 FROM */
    private String grUsagePdFrom;

    /** 총사용기간 TO */
    private String grUsagePdTo;

    /** 총 사용료 */
    private String grFee;

    /** 신청 구분 코드 */
    private String reqstSeCd;

    /** 납부방법 코드 */
    private String payMth;

    /** 부가세 코드 */
    private String taxtSe;

	/**
	 * @return the taxtSe
	 */
	public String getTaxtSe() {
		return taxtSe;
	}

	/**
	 * @param taxtSe the taxtSe to set
	 */
	public void setTaxtSe(String taxtSe) {
		this.taxtSe = taxtSe;
	}

	/**
	 * @return the nticCnt
	 */
	public String getNticCnt() {
		return nticCnt;
	}

	/**
	 * @param nticCnt the nticCnt to set
	 */
	public void setNticCnt(String nticCnt) {
		this.nticCnt = nticCnt;
	}

	/**
	 * @return the fcltySe
	 */
	public String getFcltySe() {
		return fcltySe;
	}

	/**
	 * @param fcltySe the fcltySe to set
	 */
	public void setFcltySe(String fcltySe) {
		this.fcltySe = fcltySe;
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
	 * @return the nticPdFrom
	 */
	public String getNticPdFrom() {
		return nticPdFrom;
	}

	/**
	 * @param nticPdFrom the nticPdFrom to set
	 */
	public void setNticPdFrom(String nticPdFrom) {
		this.nticPdFrom = nticPdFrom;
	}

	/**
	 * @return the constPerTo
	 */
	public String getConstPerTo() {
		return constPerTo;
	}

	/**
	 * @param constPerTo the constPerTo to set
	 */
	public void setConstPerTo(String constPerTo) {
		this.constPerTo = constPerTo;
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
	 * @return the olnlp
	 */
	public String getOlnlp() {
		return olnlp;
	}

	/**
	 * @param olnlp the olnlp to set
	 */
	public void setOlnlp(String olnlp) {
		this.olnlp = olnlp;
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
	 * @return the deptcd
	 */
	public String getDeptcd() {
		return deptcd;
	}

	/**
	 * @param deptcd the deptcd to set
	 */
	public void setDeptcd(String deptcd) {
		this.deptcd = deptcd;
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
	public void setNticMth(String nticMth) {
		this.nticMth = nticMth;
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
	 * @return the mngCnt
	 */
	public String getMngCnt() {
		return mngCnt;
	}

	/**
	 * @param mngCnt the mngCnt to set
	 */
	public void setMngCnt(String mngCnt) {
		this.mngCnt = mngCnt;
	}

	/**
	 * @return the prmisnYn
	 */
	public String getPrmisnYn() {
		return prmisnYn;
	}

	/**
	 * @param prmisnYn the prmisnYn to set
	 */
	public void setPrmisnYn(String prmisnYn) {
		this.prmisnYn = prmisnYn;
	}

	/**
	 * @return the grUsagePdFrom
	 */
	public String getGrUsagePdFrom() {
		return grUsagePdFrom;
	}

	/**
	 * @param grUsagePdFrom the grUsagePdFrom to set
	 */
	public void setGrUsagePdFrom(String grUsagePdFrom) {
		this.grUsagePdFrom = grUsagePdFrom;
	}

	/**
	 * @return the grUsagePdTo
	 */
	public String getGrUsagePdTo() {
		return grUsagePdTo;
	}

	/**
	 * @param grUsagePdTo the grUsagePdTo to set
	 */
	public void setGrUsagePdTo(String grUsagePdTo) {
		this.grUsagePdTo = grUsagePdTo;
	}

	/**
	 * @return the grFee
	 */
	public String getGrFee() {
		return grFee;
	}

	/**
	 * @param grFee the grFee to set
	 */
	public void setGrFee(String grFee) {
		this.grFee = grFee;
	}

	/**
	 * @return the reqstSeCd
	 */
	public String getReqstSeCd() {
		return reqstSeCd;
	}

	/**
	 * @param reqstSeCd the reqstSeCd to set
	 */
	public void setReqstSeCd(String reqstSeCd) {
		this.reqstSeCd = reqstSeCd;
	}

	/**
	 * @return the payMth
	 */
	public String getPayMth() {
		return payMth;
	}

	/**
	 * @param payMth the payMth to set
	 */
	public void setPayMth(String payMth) {
		this.payMth = payMth;
	}

}
