package egovframework.rte.ygpa.gam.oper.htld.service;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;

/**
 * @Class Name : GamHtldAssessVO.java
 * @Description : 배후단지 실적평가 객체
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-02-12
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldAssessVO implements Serializable {

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
     * 평가구분
     */
    private String assessSe;

    /**
     * 평가결과
     */
    private String assessResult;

    /**
     * 적용 단가
     */
    private String applcPrice;

    /**
     * 평가기간From
     */
    private String dtFrom;

    /**
     * 평가기간To
     */
    private String dtTo;

    /**
     * 평가일자
     */
    private String assessDt;

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
	 * @return the assessResult
	 */
	public String getAssessResult() {
		return assessResult;
	}

	/**
	 * @param assessResult the assessResult to set
	 */
	public void setAssessResult(String assessResult) {
		this.assessResult = assessResult;
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
	 * @return the assessDt
	 */
	public String getAssessDt() {
		return assessDt;
	}

	/**
	 * @param assessDt the assessDt to set
	 */
	public void setAssessDt(String assessDt) {
		this.assessDt = assessDt;
	}

}
