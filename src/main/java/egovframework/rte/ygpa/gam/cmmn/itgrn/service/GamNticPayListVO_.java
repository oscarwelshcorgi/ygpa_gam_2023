package egovframework.rte.ygpa.gam.cmmn.itgrn.service;

import egovframework.com.cmm.ComDefaultVO;

public class GamNticPayListVO_ extends ComDefaultVO{
	
	private static final long serialVersionUID = 1L;
	
    /** 회계 년도 */
    private String accnutYear;

    /** 관리 횟수 */
    private String mngCnt;

    /** 관리 번호 */
    private String mngNo;

    /** 관리 년도 */
    private String mngYear;

    /** 고지번호 */
    private String nticno;

    /** 고지 횟수 */
    private String nticCnt;

    /** 항코드 */
    private String prtAtCode;

    /** 요금 종류 */
    private String chrgeKnd;

    /** 회계 구분 코드 */
    private String accnutSeCd;

    /** 업체코드 */
    private String entrpscd;

    /** 고지 금액 */
    private Long nticAmt;

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
    private Long overrpayAmt;

    /** 임시 발행 번호 */
    private String tmprIsuNo;

    /** 할인 금액 */
    private Long dscntAmt;

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
    private Long vat;

    /** 부가세 여부 */
    private String vatYn;

    /** 징수관 구분 */
    private String prcepturSe;

    /** 지로 수납처 */
    private String giroRcivPlace;

    /** 지로 수납 구분 */
    private String giroRcivSe;

    /** 수수료 */
    private Long cmsn;

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
    
    /** 요금 종류 display*/
    private String chrgeKndNm;

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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAccnutYear() {
        return accnutYear;
    }

    public void setAccnutYear(String accnutYear) {
        this.accnutYear = accnutYear;
    }

    public String getMngCnt() {
        return mngCnt;
    }

    public void setMngCnt(String mngCnt) {
        this.mngCnt = mngCnt;
    }

    public String getMngNo() {
        return mngNo;
    }

    public void setMngNo(String mngNo) {
        this.mngNo = mngNo;
    }

    public String getMngYear() {
        return mngYear;
    }

    public void setMngYear(String mngYear) {
        this.mngYear = mngYear;
    }

    public String getNticno() {
        return nticno;
    }

    public void setNticno(String nticno) {
        this.nticno = nticno;
    }

    public String getNticCnt() {
        return nticCnt;
    }

    public void setNticCnt(String nticCnt) {
        this.nticCnt = nticCnt;
    }

    public String getPrtAtCode() {
        return prtAtCode;
    }

    public void setPrtAtCode(String prtAtCode) {
        this.prtAtCode = prtAtCode;
    }

    public String getChrgeKnd() {
        return chrgeKnd;
    }

    public void setChrgeKnd(String chrgeKnd) {
        this.chrgeKnd = chrgeKnd;
    }

    public String getAccnutSeCd() {
        return accnutSeCd;
    }

    public void setAccnutSeCd(String accnutSeCd) {
        this.accnutSeCd = accnutSeCd;
    }

    public String getEntrpscd() {
        return entrpscd;
    }

    public void setEntrpscd(String entrpscd) {
        this.entrpscd = entrpscd;
    }

    public Long getNticAmt() {
        return nticAmt;
    }

    public void setNticAmt(Long nticAmt) {
        this.nticAmt = nticAmt;
    }

    public String getNticDt() {
        return nticDt;
    }

    public void setNticDt(String nticDt) {
        this.nticDt = nticDt;
    }

    public String getNhtIsueYn() {
        return nhtIsueYn;
    }

    public void setNhtIsueYn(String nhtIsueYn) {
        this.nhtIsueYn = nhtIsueYn;
    }

    public String getComputDtls() {
        return computDtls;
    }

    public void setComputDtls(String computDtls) {
        this.computDtls = computDtls;
    }

    public String getPayTmlmt() {
        return payTmlmt;
    }

    public void setPayTmlmt(String payTmlmt) {
        this.payTmlmt = payTmlmt;
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

    public String getRcivDt() {
        return rcivDt;
    }

    public void setRcivDt(String rcivDt) {
        this.rcivDt = rcivDt;
    }

    public String getRcivSe() {
        return rcivSe;
    }

    public void setRcivSe(String rcivSe) {
        this.rcivSe = rcivSe;
    }

    public String getIncpctyCd() {
        return incpctyCd;
    }

    public void setIncpctyCd(String incpctyCd) {
        this.incpctyCd = incpctyCd;
    }

    public Long getOverrpayAmt() {
        return overrpayAmt;
    }

    public void setOverrpayAmt(Long overrpayAmt) {
        this.overrpayAmt = overrpayAmt;
    }

    public String getTmprIsuNo() {
        return tmprIsuNo;
    }

    public void setTmprIsuNo(String tmprIsuNo) {
        this.tmprIsuNo = tmprIsuNo;
    }

    public Long getDscntAmt() {
        return dscntAmt;
    }

    public void setDscntAmt(Long dscntAmt) {
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

    public Long getVat() {
        return vat;
    }

    public void setVat(Long vat) {
        this.vat = vat;
    }

    public String getVatYn() {
        return vatYn;
    }

    public void setVatYn(String vatYn) {
        this.vatYn = vatYn;
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

    public Long getCmsn() {
        return cmsn;
    }

    public void setCmsn(Long cmsn) {
        this.cmsn = cmsn;
    }

    public String getClosYn() {
        return closYn;
    }

    public void setClosYn(String closYn) {
        this.closYn = closYn;
    }

    public String getDeptcd() {
        return deptcd;
    }

    public void setDeptcd(String deptcd) {
        this.deptcd = deptcd;
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
}