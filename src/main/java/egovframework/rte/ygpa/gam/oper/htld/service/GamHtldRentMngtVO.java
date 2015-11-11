package egovframework.rte.ygpa.gam.oper.htld.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;

/**
 * @Class Name : GamHtldRentMngtVO.java
 * @Description : 배후단지임대목록관리
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
public class GamHtldRentMngtVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;

    /** 항코드 */
    private String prtAtCode;

    /** 부두그룹코드 */
    private String quayGroupCd;

    /** 관리년도 */
    private String mngYear;

    /** 관리번호 */
    private String mngNo;

    /** 관리횟수 */
    private String mngCnt;

    /**
     * 요금종류코드
     */
    private String chrgeKnd;

    /** 구역코드 */
    private String rentAreaCd;

    /** 구역코드명 */
    private String rentArea;

    /** 업체코드 */
    private String entrpscd;

    /** 업체코드 명 */
    private String entrpsNm;

    /** 신청구분코드 */
    private String reqstSeCd;

    /** 입주면적 */
    private String grAr;

    /** 총 사용료 */
    private String grFee;

    /** 총 사용 시작 */
    private String grUsagePdFrom;

    /** 총 사용 종료 */
    private String grUsagePdTo;

    /** 등록자 */
    private String regUsr;

    /** 등록일시 */
    private String registDt;

    /** 수정자 */
    private String updUsr;

    /** 수정일시 */
    private String updtDt;

    /** GIS 코드 */
    private String gisCd;

    /** 최초신청일자 */
    private String frstReqstDt;

    /** 결재자 */
    private String sanctnSttus;

    /** 결재자 명 */
    private String sanctnSttusNm;

    /** 사원번호 */
    private String sanctnerEmplNo;

    /** 결재일자 */
    private String sanctnDt;

    /** 신청일자 */
    private String reqstDt;

    /** 부서코드 */
    private String deptcd;

    /** 부서코드 명 */
    private String deptcdNm;

    /** 운영연월 */
    private String operYrMt;

    /** 업체종류 */
    private String compTp;

    /** 취급화종 */
    private String frghtTp;

    /** 과세구분 */
    private String taxtSe;

    /** 과세구분 명 */
    private String taxtSeNm;

    /** 납부방법 */
    private String nticMth;

    /** 납부방법 명 */
    private String nticMthNm;

    /** 최초승낙일자 */
    private String frstPrmisnDt;

    /** 승낙일자 */
    private String prmisnDt;

    /** 승낙여부 */
    private String prmisnYn;

    /** 결재자 명 */
    private String docNo;

    /** 결재자 명 */
    private String rm;

    /** 결재자 명 */
    private String cmt;

    /** 결재자 명 */
    private String etc;

    /** 결재자 명 */
    private String grRdcxptFee;

    /** 결재자 명 */
    private String payMth;

    /** 결재자 명 */
    private String payMthNm;

    /** 결재자 명 */
    private String payinstIntrrate;

    /** 결재자 명 */
    private String chargerNo;

    /**
     * 적용단가
     */
    private String applcPrice;
    
    /** 계약해지 및 변경유무*/
    private String termnYn;
    
    /** 계약해지 및 변경일자*/
    private String termnDt;
    
    /** 계약해지 및 변경사유*/
    private String termnKnd;
    
    /** 계약해지 및 변경 사용자*/
    private String termnUsr;
    
    
	public String getPrtAtCode() {
		return prtAtCode;
	}
	public void setPrtAtCode(String prtAtCode) {
		this.prtAtCode = prtAtCode;
	}
	public String getQuayGroupCd() {
		return quayGroupCd;
	}
	public void setQuayGroupCd(String quayGroupCd) {
		this.quayGroupCd = quayGroupCd;
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
	public String getRentAreaCd() {
		return rentAreaCd;
	}
	public void setRentAreaCd(String rentAreaCd) {
		this.rentAreaCd = rentAreaCd;
	}
	public String getRentArea() {
		return rentArea;
	}
	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
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
	public String getReqstSeCd() {
		return reqstSeCd;
	}
	public void setReqstSeCd(String reqstSeCd) {
		this.reqstSeCd = reqstSeCd;
	}
	public String getGrAr() {
		return grAr;
	}
	public void setGrAr(String grAr) {
		this.grAr = grAr;
	}
	public String getGrFee() {
		return grFee;
	}
	public void setGrFee(String grFee) {
		this.grFee = grFee;
	}
	public String getGrUsagePdFrom() {
		return grUsagePdFrom;
	}
	public void setGrUsagePdFrom(String grUsagePdFrom) {
		this.grUsagePdFrom = grUsagePdFrom;
	}
	public String getGrUsagePdTo() {
		return grUsagePdTo;
	}
	public void setGrUsagePdTo(String grUsagePdTo) {
		this.grUsagePdTo = grUsagePdTo;
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
	public String getGisCd() {
		return gisCd;
	}
	public void setGisCd(String gisCd) {
		this.gisCd = gisCd;
	}
	public String getFrstReqstDt() {
		return frstReqstDt;
	}
	public void setFrstReqstDt(String frstReqstDt) {
		this.frstReqstDt = frstReqstDt;
	}
	public String getSanctnSttus() {
		return sanctnSttus;
	}
	public void setSanctnSttus(String sanctnSttus) {
		this.sanctnSttus = sanctnSttus;
	}
	public String getSanctnSttusNm() {
		return sanctnSttusNm;
	}
	public void setSanctnSttusNm(String sanctnSttusNm) {
		this.sanctnSttusNm = sanctnSttusNm;
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
	public String getReqstDt() {
		return reqstDt;
	}
	public void setReqstDt(String reqstDt) {
		this.reqstDt = reqstDt;
	}
	public String getDeptcd() {
		return deptcd;
	}
	public void setDeptcd(String deptcd) {
		this.deptcd = deptcd;
	}
	public String getDeptcdNm() {
		return deptcdNm;
	}
	public void setDeptcdNm(String deptcdNm) {
		this.deptcdNm = deptcdNm;
	}
	public String getOperYrMt() {
		return operYrMt;
	}
	public void setOperYrMt(String operYrMt) {
		this.operYrMt = operYrMt;
	}
	public String getCompTp() {
		return compTp;
	}
	public void setCompTp(String compTp) {
		this.compTp = compTp;
	}
	public String getFrghtTp() {
		return frghtTp;
	}
	public void setFrghtTp(String frghtTp) {
		this.frghtTp = frghtTp;
	}
	public String getTaxtSe() {
		return taxtSe;
	}
	public void setTaxtSe(String taxtSe) {
		this.taxtSe = taxtSe;
	}
	public String getTaxtSeNm() {
		return taxtSeNm;
	}
	public void setTaxtSeNm(String taxtSeNm) {
		this.taxtSeNm = taxtSeNm;
	}
	public String getNticMth() {
		return nticMth;
	}
	public void setNticMth(String nticMth) {
		this.nticMth = nticMth;
	}
	public String getNticMthNm() {
		return nticMthNm;
	}
	public void setNticMthNm(String nticMthNm) {
		this.nticMthNm = nticMthNm;
	}
	public String getFrstPrmisnDt() {
		return frstPrmisnDt;
	}
	public void setFrstPrmisnDt(String frstPrmisnDt) {
		this.frstPrmisnDt = frstPrmisnDt;
	}
	public String getPrmisnDt() {
		return prmisnDt;
	}
	public void setPrmisnDt(String prmisnDt) {
		this.prmisnDt = prmisnDt;
	}
	public String getPrmisnYn() {
		return prmisnYn;
	}
	public void setPrmisnYn(String prmisnYn) {
		this.prmisnYn = prmisnYn;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getRm() {
		return rm;
	}
	public void setRm(String rm) {
		this.rm = rm;
	}
	public String getCmt() {
		return cmt;
	}
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getGrRdcxptFee() {
		return grRdcxptFee;
	}
	public void setGrRdcxptFee(String grRdcxptFee) {
		this.grRdcxptFee = grRdcxptFee;
	}
	public String getPayMth() {
		return payMth;
	}
	public void setPayMth(String payMth) {
		this.payMth = payMth;
	}
	public String getPayMthNm() {
		return payMthNm;
	}
	public void setPayMthNm(String payMthNm) {
		this.payMthNm = payMthNm;
	}
	public String getPayinstIntrrate() {
		return payinstIntrrate;
	}
	public void setPayinstIntrrate(String payinstIntrrate) {
		this.payinstIntrrate = payinstIntrrate;
	}
	public String getChargerNo() {
		return chargerNo;
	}
	public void setChargerNo(String chargerNo) {
		this.chargerNo = chargerNo;
	}
	/**
	 * @return the applcPrice
	 */
	public String getApplcPrice() {
		return applcPrice;
	}
	/**
	 * @param applcPrice the applcPrice to set
	 */
	public void setApplcPrice(String applcPrice) {
		this.applcPrice = applcPrice;
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
	 * @return the termnYN
	 */
	public String getTermnYn() {
		return termnYn;
	}
	/**
	 * @param termnYN the termnYN to set
	 */
	public void setTermnYn(String termnYn) {
		this.termnYn = termnYn;
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
	 * @return the termnKnd
	 */
	public String getTermnKnd() {
		return termnKnd;
	}
	/**
	 * @param termnKnd the termnKnd to set
	 */
	public void setTermnKnd(String termnKnd) {
		this.termnKnd = termnKnd;
	}
	/**
	 * @return the termnUsr
	 */
	public String getTermnUsr() {
		return termnUsr;
	}
	/**
	 * @param termnUsr the termnUsr to set
	 */
	public void setTermnUsr(String termnUsr) {
		this.termnUsr = termnUsr;
	}

}
