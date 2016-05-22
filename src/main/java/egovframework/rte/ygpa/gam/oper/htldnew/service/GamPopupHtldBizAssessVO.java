/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 15.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamPopupHtldBizAssessVO extends ComDefaultVO{
	private static final long serialVersionUID = 1L;

	private String mngYear;				/** 관리년도 */
	private String mngNo; 				/** 관리번호 */
	private String mngSeq;				/** 관리순번 */
	private String registSeq;				/** 등록순번 */
	private String aseRntfee;				/** 실적평가금액 */
	private String aseApplcBegin; 		/** 실적평가적용시작일 */
	private String aseApplcEnd; 			/** 실적평가적용시작일 */
	private String applicRsn; 				/** 실적평가내용(적용사유) */
	private String updUsr;					/** 수정자 */
	private String updtDt;					/** 수정일시 */
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
	 * @return the registSeq
	 */
	public String getRegistSeq() {
		return registSeq;
	}
	/**
	 * @param registSeq the registSeq to set
	 */
	public void setRegistSeq(String registSeq) {
		this.registSeq = registSeq;
	}
	/**
	 * @return the aseRntfee
	 */
	public String getAseRntfee() {
		return aseRntfee;
	}
	/**
	 * @param aseRntfee the aseRntfee to set
	 */
	public void setAseRntfee(String aseRntfee) {
		this.aseRntfee = aseRntfee;
	}
	/**
	 * @return the aseApplcBegin
	 */
	public String getAseApplcBegin() {
		return aseApplcBegin;
	}
	/**
	 * @param aseApplcBegin the aseApplcBegin to set
	 */
	public void setAseApplcBegin(String aseApplcBegin) {
		this.aseApplcBegin = aseApplcBegin;
	}
	/**
	 * @return the aseApplcEnd
	 */
	public String getAseApplcEnd() {
		return aseApplcEnd;
	}
	/**
	 * @param aseApplcEnd the aseApplcEnd to set
	 */
	public void setAseApplcEnd(String aseApplcEnd) {
		this.aseApplcEnd = aseApplcEnd;
	}
	/**
	 * @return the applicRsn
	 */
	public String getApplicRsn() {
		return applicRsn;
	}
	/**
	 * @param applicRsn the applicRsn to set
	 */
	public void setApplicRsn(String applicRsn) {
		this.applicRsn = applicRsn;
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
}
