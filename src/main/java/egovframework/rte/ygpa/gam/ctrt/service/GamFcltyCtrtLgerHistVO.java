/**
 * 
 */
package egovframework.rte.ygpa.gam.ctrt.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltyCtrtLgerHistVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;
	
	
	/** 계약구분 */
    private String sCtrtSe;
    
    /** 계약명 */
    private String sCtrtNm;
    
    /** 계약일 검색조건 시작일 */
    private String sCtrtFrDt;
    
    /** 계약일 검색조건 종료일 */
    private String sCtrtToDt;

    /** 등록업체코드 */
    private String sRegistEntrpsCd;
    
    /** 계약금액 검색조건 시작 */
    private String sCtrtAmtFr;
    
    /** 계약금액 검색조건 종료 */
    private String sCtrtAmtTo;
    
    /** 자료 총갯수 */
    private int totalCnt;
    
    /** 설계금액 총액 */
    private long sumPlanAmt;
    
    /** 예정금액 총액 */
    private long sumPrmtAmt;
    
    /** 낙찰금액 총액 */
    private long sumScsbidAmt;
    
    /** 기초금액 총액 */
    private long sumBaseAmt;
    

	
	/** 변경계약금액 총액 */
	private long sumChangeCtrtAmt;
	
	/** 최종계약금액 총액 */
	private long sumLastCtrtAmt;
	
	/** 금회기성금액 총액 */
	private long sumThisTimeEstbAmt;
	
	/** 선금정산금액 총액 */
	private long sumDepositExcclcAmt;
	
	/** 지급금액 총액 */
	private long sumPymntAmt;
	
	/** 지급누계금액 총액 */
	private long sumPymntAggrAmt;
	
	/** 이행금액 총액 */
	private long sumFulfillAmt;
	
	/** 이월금액 총액 */
	private long sumCaryFwdAmt;
	
	private String ctrtNo;
	
	private String seq;



	/**
	 * @return the sCtrtSe
	 */
	public String getsCtrtSe() {
		return sCtrtSe;
	}

	/**
	 * @param sCtrtSe the sCtrtSe to set
	 */
	public void setsCtrtSe(String sCtrtSe) {
		this.sCtrtSe = sCtrtSe;
	}

	/**
	 * @return the sCtrtNm
	 */
	public String getsCtrtNm() {
		return sCtrtNm;
	}

	/**
	 * @param sCtrtNm the sCtrtNm to set
	 */
	public void setsCtrtNm(String sCtrtNm) {
		this.sCtrtNm = sCtrtNm;
	}

	/**
	 * @return the sCtrtFrDt
	 */
	public String getsCtrtFrDt() {
		return sCtrtFrDt;
	}

	/**
	 * @param sCtrtFrDt the sCtrtFrDt to set
	 */
	public void setsCtrtFrDt(String sCtrtFrDt) {
		this.sCtrtFrDt = sCtrtFrDt;
	}

	/**
	 * @return the sCtrtToDt
	 */
	public String getsCtrtToDt() {
		return sCtrtToDt;
	}

	/**
	 * @param sCtrtToDt the sCtrtToDt to set
	 */
	public void setsCtrtToDt(String sCtrtToDt) {
		this.sCtrtToDt = sCtrtToDt;
	}

	/**
	 * @return the sRegistEntrpsCd
	 */
	public String getsRegistEntrpsCd() {
		return sRegistEntrpsCd;
	}

	/**
	 * @param sRegistEntrpsCd the sRegistEntrpsCd to set
	 */
	public void setsRegistEntrpsCd(String sRegistEntrpsCd) {
		this.sRegistEntrpsCd = sRegistEntrpsCd;
	}



	/**
	 * @return the sumPlanAmt
	 */
	public long getSumPlanAmt() {
		return sumPlanAmt;
	}

	/**
	 * @param sumPlanAmt the sumPlanAmt to set
	 */
	public void setSumPlanAmt(long sumPlanAmt) {
		this.sumPlanAmt = sumPlanAmt;
	}

	/**
	 * @return the sumPrmtAmt
	 */
	public long getSumPrmtAmt() {
		return sumPrmtAmt;
	}

	/**
	 * @param sumPrmtAmt the sumPrmtAmt to set
	 */
	public void setSumPrmtAmt(long sumPrmtAmt) {
		this.sumPrmtAmt = sumPrmtAmt;
	}

	/**
	 * @return the sumScsbidAmt
	 */
	public long getSumScsbidAmt() {
		return sumScsbidAmt;
	}

	/**
	 * @param sumScsbidAmt the sumScsbidAmt to set
	 */
	public void setSumScsbidAmt(long sumScsbidAmt) {
		this.sumScsbidAmt = sumScsbidAmt;
	}

	/**
	 * @return the sumBaseAmt
	 */
	public long getSumBaseAmt() {
		return sumBaseAmt;
	}

	/**
	 * @param sumBaseAmt the sumBaseAmt to set
	 */
	public void setSumBaseAmt(long sumBaseAmt) {
		this.sumBaseAmt = sumBaseAmt;
	}

	/**
	 * @return the totalCnt
	 */
	public int getTotalCnt() {
		return totalCnt;
	}

	/**
	 * @param totalCnt the totalCnt to set
	 */
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}


	/**
	 * @return the sumThisTimeEstbAmt
	 */
	public long getSumThisTimeEstbAmt() {
		return sumThisTimeEstbAmt;
	}

	/**
	 * @param sumThisTimeEstbAmt the sumThisTimeEstbAmt to set
	 */
	public void setSumThisTimeEstbAmt(long sumThisTimeEstbAmt) {
		this.sumThisTimeEstbAmt = sumThisTimeEstbAmt;
	}

	/**
	 * @return the sumDepositExcclcAmt
	 */
	public long getSumDepositExcclcAmt() {
		return sumDepositExcclcAmt;
	}

	/**
	 * @param sumDepositExcclcAmt the sumDepositExcclcAmt to set
	 */
	public void setSumDepositExcclcAmt(long sumDepositExcclcAmt) {
		this.sumDepositExcclcAmt = sumDepositExcclcAmt;
	}

	/**
	 * @return the sumPymntAmt
	 */
	public long getSumPymntAmt() {
		return sumPymntAmt;
	}

	/**
	 * @param sumPymntAmt the sumPymntAmt to set
	 */
	public void setSumPymntAmt(long sumPymntAmt) {
		this.sumPymntAmt = sumPymntAmt;
	}

	/**
	 * @return the sumPymntAggrAmt
	 */
	public long getSumPymntAggrAmt() {
		return sumPymntAggrAmt;
	}

	/**
	 * @param sumPymntAggrAmt the sumPymntAggrAmt to set
	 */
	public void setSumPymntAggrAmt(long sumPymntAggrAmt) {
		this.sumPymntAggrAmt = sumPymntAggrAmt;
	}

	/**
	 * @param sumChangeCtrtAmt the sumChangeCtrtAmt to set
	 */
	public void setSumChangeCtrtAmt(long sumChangeCtrtAmt) {
		this.sumChangeCtrtAmt = sumChangeCtrtAmt;
	}

	/**
	 * @param sumLastCtrtAmt the sumLastCtrtAmt to set
	 */
	public void setSumLastCtrtAmt(long sumLastCtrtAmt) {
		this.sumLastCtrtAmt = sumLastCtrtAmt;
	}

	/**
	 * @return the sumChangeCtrtAmt
	 */
	public long getSumChangeCtrtAmt() {
		return sumChangeCtrtAmt;
	}

	/**
	 * @return the sumLastCtrtAmt
	 */
	public long getSumLastCtrtAmt() {
		return sumLastCtrtAmt;
	}

	/**
	 * @return the sumFulfillAmt
	 */
	public long getSumFulfillAmt() {
		return sumFulfillAmt;
	}

	/**
	 * @param sumFulfillAmt the sumFulfillAmt to set
	 */
	public void setSumFulfillAmt(long sumFulfillAmt) {
		this.sumFulfillAmt = sumFulfillAmt;
	}

	/**
	 * @return the sumCaryFwdAmt
	 */
	public long getSumCaryFwdAmt() {
		return sumCaryFwdAmt;
	}

	/**
	 * @param sumCaryFwdAmt the sumCaryFwdAmt to set
	 */
	public void setSumCaryFwdAmt(long sumCaryFwdAmt) {
		this.sumCaryFwdAmt = sumCaryFwdAmt;
	}

	

	/**
	 * @return the sCtrtAmtFr
	 */
	public String getsCtrtAmtFr() {
		return sCtrtAmtFr;
	}

	/**
	 * @param sCtrtAmtFr the sCtrtAmtFr to set
	 */
	public void setsCtrtAmtFr(String sCtrtAmtFr) {
		this.sCtrtAmtFr = sCtrtAmtFr;
	}

	/**
	 * @return the sCtrtAmtTo
	 */
	public String getsCtrtAmtTo() {
		return sCtrtAmtTo;
	}

	/**
	 * @param sCtrtAmtTo the sCtrtAmtTo to set
	 */
	public void setsCtrtAmtTo(String sCtrtAmtTo) {
		this.sCtrtAmtTo = sCtrtAmtTo;
	}

	/**
	 * @return the ctrtNo
	 */
	public String getCtrtNo() {
		return ctrtNo;
	}

	/**
	 * @param ctrtNo the ctrtNo to set
	 */
	public void setCtrtNo(String ctrtNo) {
		this.ctrtNo = ctrtNo;
	}

	/**
	 * @return the seq
	 */
	public String getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}

	

}
