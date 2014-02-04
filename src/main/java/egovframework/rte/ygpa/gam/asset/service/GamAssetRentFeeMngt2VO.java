package egovframework.rte.ygpa.gam.asset.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamAssetRentFeeMngt2VO.java
 * @Description : 세입징수 테이블 VO class
 * @Modification Information
 *
 * @author 정윤후
 * @since 2014-01-23
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamAssetRentFeeMngt2VO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** 요금 종류 */
    private String chrgeKnd;	               

    /** 회계 년도 */
    private String accnutYear;	               

    /** 고지번호 */
    private String nticno;	                   

    /** 회계 구분 코드 */
    private String accnutSeCd;	           

    /** 업체코드 */
    private String entrpscd;	               

    /** 고지 금액 */
    private String nticAmt;	               

    /** 고지 일자 */
    private String nticDt;	                   

    /** 고지서 발부 여부 */
    private String nhtIsueYn;	               

    /** 산출 내역 */
    private String computDtls;	               

    /** 납부 기한 */
    private String payTmlmt;	               

    /** 최초 고지 일자 */
    private String frstNticDt;	           

    /** 수납 이체 상태 코드 */
    private String rcivTransfrSttusCd;	   

    /** 수납 일자 */
    private String rcivDt;	                   

    /** 수납 구분 */
    private String rcivSe;	                   

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

    /** 부가세 */
    private String vat;	                       

    /** 부가세 여부 */
    private String vatYn;	                   

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

    /** 부서코드 */
    private String deptcd;	                   

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

    /** 고지 횟수 */
    private String nticCnt;	               

    /** 항코드 */
    private String prtAtCode;	               

    /** 관리 년도 */
    private String mngYear;	               

    /** 관리 번호 */
    private String mngNo;	                   

    /** 관리 횟수 */
    private String mngCnt;

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
	 * @return the accnutSeCd
	 */
	public String getAccnutSeCd() {
		return accnutSeCd;
	}

	/**
	 * @param accnutSeCd the accnutSeCd to set
	 */
	public void setAccnutSeCd(String accnutSeCd) {
		this.accnutSeCd = accnutSeCd;
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
	 * @return the computDtls
	 */
	public String getComputDtls() {
		return computDtls;
	}

	/**
	 * @param computDtls the computDtls to set
	 */
	public void setComputDtls(String computDtls) {
		this.computDtls = computDtls;
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
	 * @return the frstNticDt
	 */
	public String getFrstNticDt() {
		return frstNticDt;
	}

	/**
	 * @param frstNticDt the frstNticDt to set
	 */
	public void setFrstNticDt(String frstNticDt) {
		this.frstNticDt = frstNticDt;
	}

	/**
	 * @return the rcivTransfrSttusCd
	 */
	public String getRcivTransfrSttusCd() {
		return rcivTransfrSttusCd;
	}

	/**
	 * @param rcivTransfrSttusCd the rcivTransfrSttusCd to set
	 */
	public void setRcivTransfrSttusCd(String rcivTransfrSttusCd) {
		this.rcivTransfrSttusCd = rcivTransfrSttusCd;
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
	 * @return the incpctyCd
	 */
	public String getIncpctyCd() {
		return incpctyCd;
	}

	/**
	 * @param incpctyCd the incpctyCd to set
	 */
	public void setIncpctyCd(String incpctyCd) {
		this.incpctyCd = incpctyCd;
	}

	/**
	 * @return the overrpayAmt
	 */
	public String getOverrpayAmt() {
		return overrpayAmt;
	}

	/**
	 * @param overrpayAmt the overrpayAmt to set
	 */
	public void setOverrpayAmt(String overrpayAmt) {
		this.overrpayAmt = overrpayAmt;
	}

	/**
	 * @return the tmprIsuNo
	 */
	public String getTmprIsuNo() {
		return tmprIsuNo;
	}

	/**
	 * @param tmprIsuNo the tmprIsuNo to set
	 */
	public void setTmprIsuNo(String tmprIsuNo) {
		this.tmprIsuNo = tmprIsuNo;
	}

	/**
	 * @return the dscntAmt
	 */
	public String getDscntAmt() {
		return dscntAmt;
	}

	/**
	 * @param dscntAmt the dscntAmt to set
	 */
	public void setDscntAmt(String dscntAmt) {
		this.dscntAmt = dscntAmt;
	}

	/**
	 * @return the dscntRsn
	 */
	public String getDscntRsn() {
		return dscntRsn;
	}

	/**
	 * @param dscntRsn the dscntRsn to set
	 */
	public void setDscntRsn(String dscntRsn) {
		this.dscntRsn = dscntRsn;
	}

	/**
	 * @return the dscntCd
	 */
	public String getDscntCd() {
		return dscntCd;
	}

	/**
	 * @param dscntCd the dscntCd to set
	 */
	public void setDscntCd(String dscntCd) {
		this.dscntCd = dscntCd;
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
	 * @return the fnncInsttRcivDt
	 */
	public String getFnncInsttRcivDt() {
		return fnncInsttRcivDt;
	}

	/**
	 * @param fnncInsttRcivDt the fnncInsttRcivDt to set
	 */
	public void setFnncInsttRcivDt(String fnncInsttRcivDt) {
		this.fnncInsttRcivDt = fnncInsttRcivDt;
	}

	/**
	 * @return the postNticEnnc
	 */
	public String getPostNticEnnc() {
		return postNticEnnc;
	}

	/**
	 * @param postNticEnnc the postNticEnnc to set
	 */
	public void setPostNticEnnc(String postNticEnnc) {
		this.postNticEnnc = postNticEnnc;
	}

	/**
	 * @return the npymnRsnCd
	 */
	public String getNpymnRsnCd() {
		return npymnRsnCd;
	}

	/**
	 * @param npymnRsnCd the npymnRsnCd to set
	 */
	public void setNpymnRsnCd(String npymnRsnCd) {
		this.npymnRsnCd = npymnRsnCd;
	}

	/**
	 * @return the elctrnNticResult
	 */
	public String getElctrnNticResult() {
		return elctrnNticResult;
	}

	/**
	 * @param elctrnNticResult the elctrnNticResult to set
	 */
	public void setElctrnNticResult(String elctrnNticResult) {
		this.elctrnNticResult = elctrnNticResult;
	}

	/**
	 * @return the elctrnNticInfoInqireDt
	 */
	public String getElctrnNticInfoInqireDt() {
		return elctrnNticInfoInqireDt;
	}

	/**
	 * @param elctrnNticInfoInqireDt the elctrnNticInfoInqireDt to set
	 */
	public void setElctrnNticInfoInqireDt(String elctrnNticInfoInqireDt) {
		this.elctrnNticInfoInqireDt = elctrnNticInfoInqireDt;
	}

	/**
	 * @return the excclcYn
	 */
	public String getExcclcYn() {
		return excclcYn;
	}

	/**
	 * @param excclcYn the excclcYn to set
	 */
	public void setExcclcYn(String excclcYn) {
		this.excclcYn = excclcYn;
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
	 * @return the prcepturSe
	 */
	public String getPrcepturSe() {
		return prcepturSe;
	}

	/**
	 * @param prcepturSe the prcepturSe to set
	 */
	public void setPrcepturSe(String prcepturSe) {
		this.prcepturSe = prcepturSe;
	}

	/**
	 * @return the giroRcivPlace
	 */
	public String getGiroRcivPlace() {
		return giroRcivPlace;
	}

	/**
	 * @param giroRcivPlace the giroRcivPlace to set
	 */
	public void setGiroRcivPlace(String giroRcivPlace) {
		this.giroRcivPlace = giroRcivPlace;
	}

	/**
	 * @return the giroRcivSe
	 */
	public String getGiroRcivSe() {
		return giroRcivSe;
	}

	/**
	 * @param giroRcivSe the giroRcivSe to set
	 */
	public void setGiroRcivSe(String giroRcivSe) {
		this.giroRcivSe = giroRcivSe;
	}

	/**
	 * @return the cmsn
	 */
	public String getCmsn() {
		return cmsn;
	}

	/**
	 * @param cmsn the cmsn to set
	 */
	public void setCmsn(String cmsn) {
		this.cmsn = cmsn;
	}

	/**
	 * @return the closYn
	 */
	public String getClosYn() {
		return closYn;
	}

	/**
	 * @param closYn the closYn to set
	 */
	public void setClosYn(String closYn) {
		this.closYn = closYn;
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
	 * @return the charger
	 */
	public String getCharger() {
		return charger;
	}

	/**
	 * @param charger the charger to set
	 */
	public void setCharger(String charger) {
		this.charger = charger;
	}

	/**
	 * @return the opertSe
	 */
	public String getOpertSe() {
		return opertSe;
	}

	/**
	 * @param opertSe the opertSe to set
	 */
	public void setOpertSe(String opertSe) {
		this.opertSe = opertSe;
	}

	/**
	 * @return the orginlNticChrgeKnd
	 */
	public String getOrginlNticChrgeKnd() {
		return orginlNticChrgeKnd;
	}

	/**
	 * @param orginlNticChrgeKnd the orginlNticChrgeKnd to set
	 */
	public void setOrginlNticChrgeKnd(String orginlNticChrgeKnd) {
		this.orginlNticChrgeKnd = orginlNticChrgeKnd;
	}

	/**
	 * @return the orginlNticAccnutYear
	 */
	public String getOrginlNticAccnutYear() {
		return orginlNticAccnutYear;
	}

	/**
	 * @param orginlNticAccnutYear the orginlNticAccnutYear to set
	 */
	public void setOrginlNticAccnutYear(String orginlNticAccnutYear) {
		this.orginlNticAccnutYear = orginlNticAccnutYear;
	}

	/**
	 * @return the orginlNticNo
	 */
	public String getOrginlNticNo() {
		return orginlNticNo;
	}

	/**
	 * @param orginlNticNo the orginlNticNo to set
	 */
	public void setOrginlNticNo(String orginlNticNo) {
		this.orginlNticNo = orginlNticNo;
	}

	/**
	 * @return the elctrnTaxbilIsuYn
	 */
	public String getElctrnTaxbilIsuYn() {
		return elctrnTaxbilIsuYn;
	}

	/**
	 * @param elctrnTaxbilIsuYn the elctrnTaxbilIsuYn to set
	 */
	public void setElctrnTaxbilIsuYn(String elctrnTaxbilIsuYn) {
		this.elctrnTaxbilIsuYn = elctrnTaxbilIsuYn;
	}

	/**
	 * @return the beginDt
	 */
	public String getBeginDt() {
		return beginDt;
	}

	/**
	 * @param beginDt the beginDt to set
	 */
	public void setBeginDt(String beginDt) {
		this.beginDt = beginDt;
	}

	/**
	 * @return the endDt
	 */
	public String getEndDt() {
		return endDt;
	}

	/**
	 * @param endDt the endDt to set
	 */
	public void setEndDt(String endDt) {
		this.endDt = endDt;
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
    
    
}
