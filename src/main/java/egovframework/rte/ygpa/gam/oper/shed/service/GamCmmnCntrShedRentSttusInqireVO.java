package egovframework.rte.ygpa.gam.oper.shed.service;

import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;

/**
 * @Class Name : GamCmmnCntrShedRentSttusInqireVO.java
 * @Description : 공컨장치장임대현황조회 (항만시설/공컨장치장/공컨장치장임대현황조회)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamCmmnCntrShedRentSttusInqireVO extends ErpCmmnCdDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** 항코드 */
    private String prtAtCode;
    
    /** 관리 년도 */
    private String mngYear;
    
    /** 관리 번호 */
    private String mngNo;
    
    /** 관리 횟수 */
    private String mngCnt;
    
    /** 업체코드 */
    private String entrpscd;
    
    /** 업체 명 */
    private String entrpsNm;
    
    /** 날짜 */
    private String dt;
    
    /** 신청 구분 코드 */
    private String reqstSeCd;
    
    /** 총 면적 */
    private String grAr;
    
    /** 총 사용료 */
    private String grFee;
    
    /** 고지 방법 */
    private String nticMth;
    
    /** 최초 허가 일자 */
    private String frstPrmisnDt;
    
    /** 허가 일자 */
    private String prmisnDt;
    
    /** 허가 여부 */
    private String prmisnYn;
    
    /** 총 사용 기간 FROM */
    private String grUsagePdFrom;
    
    /** 총 사용 기간 TO */
    private String grUsagePdTo;
    
    /** 문서 번호 */
    private String docNo;
    
    /** 비고 */
    private String rm;
    
    /** 코멘트 */
    private String cmt;
    
    /** 기타 */
    private String etc;
    
    /** 등록자 */
    private String regUsr;
    
    /** 등록일시 */
    private String registDt;
    
    /** 수정자 */
    private String updUsr;
    
    /** 수정일시 */
    private String updtDt;
    
    /** 총 감면 사용료 */
    private String grRdcxptFee;
    
    /** GIS 코드 */
    private String gisCd;
    
    /** 부서코드 */
    private String deptcd;

    /** 검색조건 */
    private String sPrtAtCode;
    
    /** 검색조건 */
    private String sReqstSeCd;
    
    /** 검색조건 */
    private String sEntrpscd;
    
    /** 검색조건 */
    private String sMngYear;
    
    /** 검색조건 */
    private String sMngNo;
    
    /** 검색조건 */
    private String sMngCnt;
    
    /** 검색조건 */
    private String sPrmisnYn;
    
    /** 검색조건 */
    private String sGrUsagePdFrom;
    
    /** 검색조건 */
    private String sGrUsagePdTo;
    
    /** 검색조건 */
    private String sGrAr;
    
    /** 검색조건(사용 용도 코드) */                                                   
    private String sUsagePrposCd; 
    
    /** 자료수 */
    private String sumCnt;
    
    /** 총면적 */
    private String sumGrAr;
    
    /** 총사용료 */
    private String sumGrFee;
    
    /** 관리번호(MAX) */
    private String maxMngCnt; 
    
    /** 항코드(연장신청) */
    private String rPrtAtCode;
    
    /** 관리 년도(연장신청) */
    private String rMngYear;
    
    /** 관리 번호(연장신청) */
    private String rMngNo;
    
    /** 관리 횟수(연장신청) */
    private String rMngCnt;
    
    /** 관리 번호(조합) */
    private String rentMngNo;

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
	 * @return the dt
	 */
	public String getDt() {
		return dt;
	}

	/**
	 * @param dt the dt to set
	 */
	public void setDt(String dt) {
		this.dt = dt;
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
	 * @return the grAr
	 */
	public String getGrAr() {
		return grAr;
	}

	/**
	 * @param grAr the grAr to set
	 */
	public void setGrAr(String grAr) {
		this.grAr = grAr;
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
	 * @return the frstPrmisnDt
	 */
	public String getFrstPrmisnDt() {
		return frstPrmisnDt;
	}

	/**
	 * @param frstPrmisnDt the frstPrmisnDt to set
	 */
	public void setFrstPrmisnDt(String frstPrmisnDt) {
		this.frstPrmisnDt = frstPrmisnDt;
	}

	/**
	 * @return the prmisnDt
	 */
	public String getPrmisnDt() {
		return prmisnDt;
	}

	/**
	 * @param prmisnDt the prmisnDt to set
	 */
	public void setPrmisnDt(String prmisnDt) {
		this.prmisnDt = prmisnDt;
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
	 * @return the docNo
	 */
	public String getDocNo() {
		return docNo;
	}

	/**
	 * @param docNo the docNo to set
	 */
	public void setDocNo(String docNo) {
		this.docNo = docNo;
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
	 * @return the cmt
	 */
	public String getCmt() {
		return cmt;
	}

	/**
	 * @param cmt the cmt to set
	 */
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}

	/**
	 * @return the etc
	 */
	public String getEtc() {
		return etc;
	}

	/**
	 * @param etc the etc to set
	 */
	public void setEtc(String etc) {
		this.etc = etc;
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
	 * @return the grRdcxptFee
	 */
	public String getGrRdcxptFee() {
		return grRdcxptFee;
	}

	/**
	 * @param grRdcxptFee the grRdcxptFee to set
	 */
	public void setGrRdcxptFee(String grRdcxptFee) {
		this.grRdcxptFee = grRdcxptFee;
	}

	/**
	 * @return the gisCd
	 */
	public String getGisCd() {
		return gisCd;
	}

	/**
	 * @param gisCd the gisCd to set
	 */
	public void setGisCd(String gisCd) {
		this.gisCd = gisCd;
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
	 * @return the sPrtAtCode
	 */
	public String getsPrtAtCode() {
		return sPrtAtCode;
	}

	/**
	 * @param sPrtAtCode the sPrtAtCode to set
	 */
	public void setsPrtAtCode(String sPrtAtCode) {
		this.sPrtAtCode = sPrtAtCode;
	}

	/**
	 * @return the sReqstSeCd
	 */
	public String getsReqstSeCd() {
		return sReqstSeCd;
	}

	/**
	 * @param sReqstSeCd the sReqstSeCd to set
	 */
	public void setsReqstSeCd(String sReqstSeCd) {
		this.sReqstSeCd = sReqstSeCd;
	}

	/**
	 * @return the sEntrpscd
	 */
	public String getsEntrpscd() {
		return sEntrpscd;
	}

	/**
	 * @param sEntrpscd the sEntrpscd to set
	 */
	public void setsEntrpscd(String sEntrpscd) {
		this.sEntrpscd = sEntrpscd;
	}

	/**
	 * @return the sMngNo
	 */
	public String getsMngNo() {
		return sMngNo;
	}

	/**
	 * @param sMngNo the sMngNo to set
	 */
	public void setsMngNo(String sMngNo) {
		this.sMngNo = sMngNo;
	}

	/**
	 * @return the sPrmisnYn
	 */
	public String getsPrmisnYn() {
		return sPrmisnYn;
	}

	/**
	 * @param sPrmisnYn the sPrmisnYn to set
	 */
	public void setsPrmisnYn(String sPrmisnYn) {
		this.sPrmisnYn = sPrmisnYn;
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
	 * @return the sGrAr
	 */
	public String getsGrAr() {
		return sGrAr;
	}

	/**
	 * @param sGrAr the sGrAr to set
	 */
	public void setsGrAr(String sGrAr) {
		this.sGrAr = sGrAr;
	}

	/**
	 * @return the sumCnt
	 */
	public String getSumCnt() {
		return sumCnt;
	}

	/**
	 * @param sumCnt the sumCnt to set
	 */
	public void setSumCnt(String sumCnt) {
		this.sumCnt = sumCnt;
	}

	/**
	 * @return the sumGrAr
	 */
	public String getSumGrAr() {
		return sumGrAr;
	}

	/**
	 * @param sumGrAr the sumGrAr to set
	 */
	public void setSumGrAr(String sumGrAr) {
		this.sumGrAr = sumGrAr;
	}

	/**
	 * @return the sumGrFee
	 */
	public String getSumGrFee() {
		return sumGrFee;
	}

	/**
	 * @param sumGrFee the sumGrFee to set
	 */
	public void setSumGrFee(String sumGrFee) {
		this.sumGrFee = sumGrFee;
	}

	/**
	 * @return the maxMngCnt
	 */
	public String getMaxMngCnt() {
		return maxMngCnt;
	}

	/**
	 * @param maxMngCnt the maxMngCnt to set
	 */
	public void setMaxMngCnt(String maxMngCnt) {
		this.maxMngCnt = maxMngCnt;
	}

	/**
	 * @return the rPrtAtCode
	 */
	public String getrPrtAtCode() {
		return rPrtAtCode;
	}

	/**
	 * @param rPrtAtCode the rPrtAtCode to set
	 */
	public void setrPrtAtCode(String rPrtAtCode) {
		this.rPrtAtCode = rPrtAtCode;
	}

	/**
	 * @return the rMngYear
	 */
	public String getrMngYear() {
		return rMngYear;
	}

	/**
	 * @param rMngYear the rMngYear to set
	 */
	public void setrMngYear(String rMngYear) {
		this.rMngYear = rMngYear;
	}

	/**
	 * @return the rMngNo
	 */
	public String getrMngNo() {
		return rMngNo;
	}

	/**
	 * @param rMngNo the rMngNo to set
	 */
	public void setrMngNo(String rMngNo) {
		this.rMngNo = rMngNo;
	}

	/**
	 * @return the rMngCnt
	 */
	public String getrMngCnt() {
		return rMngCnt;
	}

	/**
	 * @param rMngCnt the rMngCnt to set
	 */
	public void setrMngCnt(String rMngCnt) {
		this.rMngCnt = rMngCnt;
	}

	/**
	 * @return the rentMngNo
	 */
	public String getRentMngNo() {
		return rentMngNo;
	}

	/**
	 * @param rentMngNo the rentMngNo to set
	 */
	public void setRentMngNo(String rentMngNo) {
		this.rentMngNo = rentMngNo;
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
	 * @return the sMngYear
	 */
	public String getsMngYear() {
		return sMngYear;
	}

	/**
	 * @param sMngYear the sMngYear to set
	 */
	public void setsMngYear(String sMngYear) {
		this.sMngYear = sMngYear;
	}

	/**
	 * @return the sMngCnt
	 */
	public String getsMngCnt() {
		return sMngCnt;
	}

	/**
	 * @param sMngCnt the sMngCnt to set
	 */
	public void setsMngCnt(String sMngCnt) {
		this.sMngCnt = sMngCnt;
	}

	/**
	 * @return the sUsagePrposCd
	 */
	public String getsUsagePrposCd() {
		return sUsagePrposCd;
	}

	/**
	 * @param sUsagePrposCd the sUsagePrposCd to set
	 */
	public void setsUsagePrposCd(String sUsagePrposCd) {
		this.sUsagePrposCd = sUsagePrposCd;
	}

}
