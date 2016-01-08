package egovframework.rte.ygpa.gam.oper.htld.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamHtldAssessVO.java
 * @Description : 배후단지 실적평가 객체
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-02-12
 * @version 1.0
 * @see
 * 2015-12-24 김종민 전면 수정
 *  Copyright (C)  All right reserved.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldAssessVO extends ComDefaultVO {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** 항코드 */
    private String prtAtCode;

    /** 관리년도 */
    private String mngYear;

    /** 관리번호 */
    private String mngNo;

    /** 관리횟수 */
    private String mngCnt;

    /**
     * 평가번호
     */
    private String assessNo;

    /**
     * 삭제를 위한 이전 평가 번호
     */
    private String oldAssessNo;

    /**
     * 평가구분 1:실적 2:면적
     */
    private String assessSe;

    /**
     * 요금종류코드
     */
    private String chrgeKnd;

    /**
     * 고지번호
     */
    private String nticCnt;

    /**
     * 평가기간시작일
     */
    private String dtFrom;
    
    /**
     * 평가기간종료일
     */
    private String dtTo;
    
    /**
     * 적용단가
     */
    private String usagePrice;
    /**
     * 변경단가
     */
    private String changePrice;
    /**
     * 인상단가
     */
    private String increasePrice;

    /**
     * 사용면적
     */
    private String usageAr;

    /**
     * 변경면적
     */
    private String changeAr;

    /**
     * 변동면적
     */
    private String increaseAr;
    
    /**
     * 평가금액
     */
    private String assessAmt;
    
    /**
     * 등록자
     */
    private String regUsr;
    
    /**
     * 등록일자
     */
    private String registDt;

    /**
     * 수정자
     */
    private String updUsr;

    /**
     * 수정일자
     */
    private String updtDtt;

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
	 * @return the assessNo
	 */
	public String getAssessNo() {
		return assessNo;
	}

	/**
	 * @param assessNo the assessNo to set
	 */
	public void setAssessNo(String assessNo) {
		this.assessNo = assessNo;
	}

	/**
	 * @return the oldAssessNo
	 */
	public String getOldAssessNo() {
		return oldAssessNo;
	}

	/**
	 * @param oldAssessNo the oldAssessNo to set
	 */
	public void setOldAssessNo(String oldAssessNo) {
		this.oldAssessNo = oldAssessNo;
	}

	/**
	 * @return the assessSe
	 */
	public String getAssessSe() {
		return assessSe;
	}

	/**
	 * @param assessSe the assessSe to set
	 */
	public void setAssessSe(String assessSe) {
		this.assessSe = assessSe;
	}

	/**
	 * @return the chrgeKnd
	 */
	public String getChrgeKnd() {
		return chrgeKnd;
	}

	/**
	 * @param chageKnd the chrgeKnd to set
	 */
	public void setChrgeKnd(String chrgeKnd) {
		this.chrgeKnd = chrgeKnd;
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
	 * @return the dtFrom
	 */
	public String getDtFrom() {
		return dtFrom;
	}

	/**
	 * @param dtFrom the dtFrom to set
	 */
	public void setDtFrom(String dtFrom) {
		this.dtFrom = dtFrom;
	}

	/**
	 * @return the dtTo
	 */
	public String getDtTo() {
		return dtTo;
	}

	/**
	 * @param dtTo the dtTo to set
	 */
	public void setDtTo(String dtTo) {
		this.dtTo = dtTo;
	}

	/**
	 * @return the usagePrice
	 */
	public String getUsagePrice() {
		return usagePrice;
	}

	/**
	 * @param usagePrice the usagePrice to set
	 */
	public void setUsagePrice(String usagePrice) {
		this.usagePrice = usagePrice;
	}

	/**
	 * @return the changePrice
	 */
	public String getChangePrice() {
		return changePrice;
	}

	/**
	 * @param changePrice the changePrice to set
	 */
	public void setChangePrice(String changePrice) {
		this.changePrice = changePrice;
	}

	/**
	 * @return the increasePrice
	 */
	public String getIncreasePrice() {
		return increasePrice;
	}

	/**
	 * @param increasePrice the increasePrice to set
	 */
	public void setIncreasePrice(String increasePrice) {
		this.increasePrice = increasePrice;
	}

	/**
	 * @return the usageAr
	 */
	public String getUsageAr() {
		return usageAr;
	}

	/**
	 * @param usageAr the usageAr to set
	 */
	public void setUsageAr(String usageAr) {
		this.usageAr = usageAr;
	}

	/**
	 * @return the changeAr
	 */
	public String getChangeAr() {
		return changeAr;
	}

	/**
	 * @param changeAr the changeAr to set
	 */
	public void setChangeAr(String changeAr) {
		this.changeAr = changeAr;
	}

	/**
	 * @return the increaseAr
	 */
	public String getIncreaseAr() {
		return increaseAr;
	}

	/**
	 * @param increaseAr the increaseAr to set
	 */
	public void setIncreaseAr(String increaseAr) {
		this.increaseAr = increaseAr;
	}

	/**
	 * @return the assessAmt
	 */
	public String getAssessAmt() {
		return assessAmt;
	}

	/**
	 * @param assessAmt the assessAmt to set
	 */
	public void setAssessAmt(String assessAmt) {
		this.assessAmt = assessAmt;
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
	 * @return the updtDtt
	 */
	public String getUpdtDtt() {
		return updtDtt;
	}

	/**
	 * @param updtDtt the updtDtt to set
	 */
	public void setUpdtDtt(String updtDtt) {
		this.updtDtt = updtDtt;
	}    
}
