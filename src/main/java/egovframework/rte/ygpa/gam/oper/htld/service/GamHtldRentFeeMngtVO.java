package egovframework.rte.ygpa.gam.oper.htld.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamHtldRentFeeMngtVO.java
 * @Description : 배후단지임대료관리  VO class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldRentFeeMngtVO extends ComDefaultVO {
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

    /**
     * 총 사용 시작
     */
    private String grUsagePdFrom;

    /**
     * 총 사용 종료
     */
    private String grUsagePdTo;

    /** 고지 기간 FROM */
    private String nticPdFrom;

    /** 고지 기간 TO */
    private String nticPdTo;

    /** 고지 일자 시작(조회) */
    private String nticDtFrom;

    /** 고지 일자 끝(조회) */
    private String nticDtTo;

    /** 회계 년도 */
    private String accnutYear;

    /** 고지번호 */
    private String nticno;

    /** 고지 일자 */
    private String nticDt;

    /** 납부 기한 */
    private String payTmlmt;

    /** 분납 이자 */
    private String intrAmnt;
    
    /** 실적평가금 **/
    private String bizAssessAmnt;
    
    /** 지적측정금 **/
    private String areaAssessAmnt;
    
    /** 분납 이자 */
    private String intrChrgeKnd;

    /** 분납 이자 */
    private String intrFeeNticno;

    /** 분납 이자 */
    private String cofixObjYrmt;

    /** 분납 이자 */
    private String intrRate;

    /**
     * 결재 상태
     */
    private String sanctnSttus;

    /**
     * 결재자 사원번호
     */
    private String sanctnerEmplNo;

	/**
     * 결재 일시
     */
    private String sanctnDt;

	/**
     * 변상금 2014-10-13 추가 (eunsungj)
     */
    private String reimFee;

    /**
     * 변상금 고지번호 2014-10-13 추가 (eunsungj)
     */
    private String reimFeeNticno;

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

    /** 관리 번호(조합) */
    private String rentMngNo;

    /** 자료수 */
    private String sumCnt;

    /******* 세입징수 ********/

    /** 회계 구분 코드 */
    private String accnutSeCd;

    /** 산출 내역 */
    private String computDtls;

    /** 최초 고지 일자 */
    private String frstNticDt;

    /** 수납 이체 상태 코드 */
    private String rcivTransfrSttusCd;

    /** 불능 코드 */
    private String incpctyCd;

    /** 과오납 금액 */
    private String overrpayAmt;

    /** 임시 발행 번호 */
    private String tmprIsuNo;

    /** 할인 금액 */
    private String dscntAmt;

    /** 할인 사유 */
    private String dscntRsn;

    /** 할인 코드 */
    private String dscntCd;

    /** 수납 구분명 */
    private String rcivSeNm;

    /** 금융 기관 수납 일자 */
    private String fnncInsttRcivDt;

    /** 우편 고지 유무 */
    private String postNticEnnc;

    /** 불납 사유 코드 */
    private String npymnRsnCd;

    /** 전자 고지 결과 */
    private String elctrnNticResult;

    /** 전자 고지 정보 조회 일자 */
    private String elctrnNticInfoInqireDt;

    /** 정산 여부 */
    private String excclcYn;

    /** 징수관 구분 */
    private String prcepturSe;

    /** 지로 수납처 */
    private String giroRcivPlace;

    /** 지로 수납 구분 */
    private String giroRcivSe;

    /** 수수료 */
    private String cmsn;

    /** 마감 여부 */
    private String closYn;

    /** 담당자 */
    private String charger;

    /** 작업 구분 */
    private String opertSe;

    /** 원고지 요금 종류 */
    private String orginlNticChrgeKnd;

    /** 원고지 회계 년도 */
    private String orginlNticAccnutYear;

    /** 원고지 번호 */
    private String orginlNticNo;

    /** 전자 세금 계산서 발행 여부 */
    private String elctrnTaxbilIsuYn;

    /** 시작일 */
    private String beginDt;

    /** 종료일 */
    private String endDt;

    /** 고지 발행 여부 */
    private String sNhtIsueYn;

    /**  */
    private String payinstIntrrate;

	public String getNticCnt() {
		return nticCnt;
	}

	public void setNticCnt(String nticCnt) {
		this.nticCnt = nticCnt;
	}

	public String getFcltySe() {
		return fcltySe;
	}

	public void setFcltySe(String fcltySe) {
		this.fcltySe = fcltySe;
	}

	public String getChrgeKnd() {
		return chrgeKnd;
	}

	public void setChrgeKnd(String chrgeKnd) {
		this.chrgeKnd = chrgeKnd;
	}

	public String getEntrpscd() {
		return entrpscd;
	}

	public void setEntrpscd(String entrpscd) {
		this.entrpscd = entrpscd;
	}

	public String getEntrpsNm() {
		return entrpsNm;
	}

	public void setEntrpsNm(String entrpsNm) {
		this.entrpsNm = entrpsNm;
	}

	public String getNticPdFrom() {
		return nticPdFrom;
	}

	public void setNticPdFrom(String nticPdFrom) {
		this.nticPdFrom = nticPdFrom;
	}

	public String getNticPdTo() {
		return nticPdTo;
	}

	public void setNticPdTo(String nticPdTo) {
		this.nticPdTo = nticPdTo;
	}

	public String getNticDtFrom() {
		return nticDtFrom;
	}

	public void setNticDtFrom(String nticDtFrom) {
		this.nticDtFrom = nticDtFrom;
	}

	public String getNticDtTo() {
		return nticDtTo;
	}

	public void setNticDtTo(String nticDtTo) {
		this.nticDtTo = nticDtTo;
	}

	public String getAccnutYear() {
		return accnutYear;
	}

	public void setAccnutYear(String accnutYear) {
		this.accnutYear = accnutYear;
	}

	public String getNticno() {
		return nticno;
	}

	public void setNticno(String nticno) {
		this.nticno = nticno;
	}

	public String getNticDt() {
		return nticDt;
	}

	public void setNticDt(String nticDt) {
		this.nticDt = nticDt;
	}

	public String getPayTmlmt() {
		return payTmlmt;
	}

	public void setPayTmlmt(String payTmlmt) {
		this.payTmlmt = payTmlmt;
	}

	public String getIntrAmnt() {
		return intrAmnt;
	}

	public void setIntrAmnt(String intrAmnt) {
		this.intrAmnt = intrAmnt;
	}

	public String getIntrChrgeKnd() {
		return intrChrgeKnd;
	}

	public void setIntrChrgeKnd(String intrChrgeKnd) {
		this.intrChrgeKnd = intrChrgeKnd;
	}

	public String getIntrFeeNticno() {
		return intrFeeNticno;
	}

	public void setIntrFeeNticno(String intrFeeNticno) {
		this.intrFeeNticno = intrFeeNticno;
	}

	public String getCofixObjYrmt() {
		return cofixObjYrmt;
	}

	public void setCofixObjYrmt(String cofixObjYrmt) {
		this.cofixObjYrmt = cofixObjYrmt;
	}

	public String getIntrRate() {
		return intrRate;
	}

	public void setIntrRate(String intrRate) {
		this.intrRate = intrRate;
	}

	public String getSanctnSttus() {
		return sanctnSttus;
	}

	public void setSanctnSttus(String sanctnSttus) {
		this.sanctnSttus = sanctnSttus;
	}

	public String getSanctnerEmplNo() {
		return sanctnerEmplNo;
	}

	public void setSanctnerEmplNo(String sanctnerEmplNo) {
		this.sanctnerEmplNo = sanctnerEmplNo;
	}

	public String getSanctnDt() {
		return sanctnDt;
	}

	public void setSanctnDt(String sanctnDt) {
		this.sanctnDt = sanctnDt;
	}

	public String getReimFee() {
		return reimFee;
	}

	public void setReimFee(String reimFee) {
		this.reimFee = reimFee;
	}

	public String getReimFeeNticno() {
		return reimFeeNticno;
	}

	public void setReimFeeNticno(String reimFeeNticno) {
		this.reimFeeNticno = reimFeeNticno;
	}

	public String getOlnlp() {
		return olnlp;
	}

	public void setOlnlp(String olnlp) {
		this.olnlp = olnlp;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getVatYn() {
		return vatYn;
	}

	public void setVatYn(String vatYn) {
		this.vatYn = vatYn;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getNticAmt() {
		return nticAmt;
	}

	public void setNticAmt(String nticAmt) {
		this.nticAmt = nticAmt;
	}

	public String getRm() {
		return rm;
	}

	public void setRm(String rm) {
		this.rm = rm;
	}

	public String getRcivSe() {
		return rcivSe;
	}

	public void setRcivSe(String rcivSe) {
		this.rcivSe = rcivSe;
	}

	public String getRcivDt() {
		return rcivDt;
	}

	public void setRcivDt(String rcivDt) {
		this.rcivDt = rcivDt;
	}

	public String getNhtIsueYn() {
		return nhtIsueYn;
	}

	public void setNhtIsueYn(String nhtIsueYn) {
		this.nhtIsueYn = nhtIsueYn;
	}

	public String getArrrgNo() {
		return arrrgNo;
	}

	public void setArrrgNo(String arrrgNo) {
		this.arrrgNo = arrrgNo;
	}

	public String getArrrgAmt() {
		return arrrgAmt;
	}

	public void setArrrgAmt(String arrrgAmt) {
		this.arrrgAmt = arrrgAmt;
	}

	public String getReqestSeq() {
		return reqestSeq;
	}

	public void setReqestSeq(String reqestSeq) {
		this.reqestSeq = reqestSeq;
	}

	public String getDeptcd() {
		return deptcd;
	}

	public void setDeptcd(String deptcd) {
		this.deptcd = deptcd;
	}

	public String getNticMth() {
		return nticMth;
	}

	public void setNticMth(String nticMth) {
		this.nticMth = nticMth;
	}

	public String getRegUsr() {
		return regUsr;
	}

	public void setRegUsr(String regUsr) {
		this.regUsr = regUsr;
	}

	public String getRegistDt() {
		return registDt;
	}

	public void setRegistDt(String registDt) {
		this.registDt = registDt;
	}

	public String getUpdUsr() {
		return updUsr;
	}

	public void setUpdUsr(String updUsr) {
		this.updUsr = updUsr;
	}

	public String getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}

	public String getPrtAtCode() {
		return prtAtCode;
	}

	public void setPrtAtCode(String prtAtCode) {
		this.prtAtCode = prtAtCode;
	}

	public String getMngYear() {
		return mngYear;
	}

	public void setMngYear(String mngYear) {
		this.mngYear = mngYear;
	}

	public String getMngNo() {
		return mngNo;
	}

	public void setMngNo(String mngNo) {
		this.mngNo = mngNo;
	}

	public String getMngCnt() {
		return mngCnt;
	}

	public void setMngCnt(String mngCnt) {
		this.mngCnt = mngCnt;
	}

	public String getRentMngNo() {
		return rentMngNo;
	}

	public void setRentMngNo(String rentMngNo) {
		this.rentMngNo = rentMngNo;
	}

	public String getSumCnt() {
		return sumCnt;
	}

	public void setSumCnt(String sumCnt) {
		this.sumCnt = sumCnt;
	}

	public String getAccnutSeCd() {
		return accnutSeCd;
	}

	public void setAccnutSeCd(String accnutSeCd) {
		this.accnutSeCd = accnutSeCd;
	}

	public String getComputDtls() {
		return computDtls;
	}

	public void setComputDtls(String computDtls) {
		this.computDtls = computDtls;
	}

	public String getFrstNticDt() {
		return frstNticDt;
	}

	public void setFrstNticDt(String frstNticDt) {
		this.frstNticDt = frstNticDt;
	}

	public String getRcivTransfrSttusCd() {
		return rcivTransfrSttusCd;
	}

	public void setRcivTransfrSttusCd(String rcivTransfrSttusCd) {
		this.rcivTransfrSttusCd = rcivTransfrSttusCd;
	}

	public String getIncpctyCd() {
		return incpctyCd;
	}

	public void setIncpctyCd(String incpctyCd) {
		this.incpctyCd = incpctyCd;
	}

	public String getOverrpayAmt() {
		return overrpayAmt;
	}

	public void setOverrpayAmt(String overrpayAmt) {
		this.overrpayAmt = overrpayAmt;
	}

	public String getTmprIsuNo() {
		return tmprIsuNo;
	}

	public void setTmprIsuNo(String tmprIsuNo) {
		this.tmprIsuNo = tmprIsuNo;
	}

	public String getDscntAmt() {
		return dscntAmt;
	}

	public void setDscntAmt(String dscntAmt) {
		this.dscntAmt = dscntAmt;
	}

	public String getDscntRsn() {
		return dscntRsn;
	}

	public void setDscntRsn(String dscntRsn) {
		this.dscntRsn = dscntRsn;
	}

	public String getDscntCd() {
		return dscntCd;
	}

	public void setDscntCd(String dscntCd) {
		this.dscntCd = dscntCd;
	}

	public String getRcivSeNm() {
		return rcivSeNm;
	}

	public void setRcivSeNm(String rcivSeNm) {
		this.rcivSeNm = rcivSeNm;
	}

	public String getFnncInsttRcivDt() {
		return fnncInsttRcivDt;
	}

	public void setFnncInsttRcivDt(String fnncInsttRcivDt) {
		this.fnncInsttRcivDt = fnncInsttRcivDt;
	}

	public String getPostNticEnnc() {
		return postNticEnnc;
	}

	public void setPostNticEnnc(String postNticEnnc) {
		this.postNticEnnc = postNticEnnc;
	}

	public String getNpymnRsnCd() {
		return npymnRsnCd;
	}

	public void setNpymnRsnCd(String npymnRsnCd) {
		this.npymnRsnCd = npymnRsnCd;
	}

	public String getElctrnNticResult() {
		return elctrnNticResult;
	}

	public void setElctrnNticResult(String elctrnNticResult) {
		this.elctrnNticResult = elctrnNticResult;
	}

	public String getElctrnNticInfoInqireDt() {
		return elctrnNticInfoInqireDt;
	}

	public void setElctrnNticInfoInqireDt(String elctrnNticInfoInqireDt) {
		this.elctrnNticInfoInqireDt = elctrnNticInfoInqireDt;
	}

	public String getExcclcYn() {
		return excclcYn;
	}

	public void setExcclcYn(String excclcYn) {
		this.excclcYn = excclcYn;
	}

	public String getPrcepturSe() {
		return prcepturSe;
	}

	public void setPrcepturSe(String prcepturSe) {
		this.prcepturSe = prcepturSe;
	}

	public String getGiroRcivPlace() {
		return giroRcivPlace;
	}

	public void setGiroRcivPlace(String giroRcivPlace) {
		this.giroRcivPlace = giroRcivPlace;
	}

	public String getGiroRcivSe() {
		return giroRcivSe;
	}

	public void setGiroRcivSe(String giroRcivSe) {
		this.giroRcivSe = giroRcivSe;
	}

	public String getCmsn() {
		return cmsn;
	}

	public void setCmsn(String cmsn) {
		this.cmsn = cmsn;
	}

	public String getClosYn() {
		return closYn;
	}

	public void setClosYn(String closYn) {
		this.closYn = closYn;
	}

	public String getCharger() {
		return charger;
	}

	public void setCharger(String charger) {
		this.charger = charger;
	}

	public String getOpertSe() {
		return opertSe;
	}

	public void setOpertSe(String opertSe) {
		this.opertSe = opertSe;
	}

	public String getOrginlNticChrgeKnd() {
		return orginlNticChrgeKnd;
	}

	public void setOrginlNticChrgeKnd(String orginlNticChrgeKnd) {
		this.orginlNticChrgeKnd = orginlNticChrgeKnd;
	}

	public String getOrginlNticAccnutYear() {
		return orginlNticAccnutYear;
	}

	public void setOrginlNticAccnutYear(String orginlNticAccnutYear) {
		this.orginlNticAccnutYear = orginlNticAccnutYear;
	}

	public String getOrginlNticNo() {
		return orginlNticNo;
	}

	public void setOrginlNticNo(String orginlNticNo) {
		this.orginlNticNo = orginlNticNo;
	}

	public String getElctrnTaxbilIsuYn() {
		return elctrnTaxbilIsuYn;
	}

	public void setElctrnTaxbilIsuYn(String elctrnTaxbilIsuYn) {
		this.elctrnTaxbilIsuYn = elctrnTaxbilIsuYn;
	}

	public String getBeginDt() {
		return beginDt;
	}

	public void setBeginDt(String beginDt) {
		this.beginDt = beginDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getsNhtIsueYn() {
		return sNhtIsueYn;
	}

	public void setsNhtIsueYn(String sNhtIsueYn) {
		this.sNhtIsueYn = sNhtIsueYn;
	}

	public String getPayinstIntrrate() {
		return payinstIntrrate;
	}

	public void setPayinstIntrrate(String payinstIntrrate) {
		this.payinstIntrrate = payinstIntrrate;
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
	 * @return the bizAssessAmnt
	 */
	public String getBizAssessAmnt() {
		return bizAssessAmnt;
	}

	/**
	 * @param bizAssessAmnt the bizAssessAmnt to set
	 */
	public void setBizAssessAmnt(String bizAssessAmnt) {
		this.bizAssessAmnt = bizAssessAmnt;
	}

	/**
	 * @return the areaAssessAmnt
	 */
	public String getAreaAssessAmnt() {
		return areaAssessAmnt;
	}

	/**
	 * @param areaAssessAmnt the areaAssessAmnt to set
	 */
	public void setAreaAssessAmnt(String areaAssessAmnt) {
		this.areaAssessAmnt = areaAssessAmnt;
	}

}
