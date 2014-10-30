/**
 * 
 */
package egovframework.rte.ygpa.gam.ctrt.service;

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
    
    /** 입찰일 검색조건 시작일 */
    private String sBidFrDt;
    
    /** 입찰일 검색조건 종료일 */
    private String sBidToDt;
    
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
    
    
    private String ctrtNo;
	private String ctrtSe;
	private String bidPblancNo;
	private String bidPblancDt;
	private String bidDt;
	private String registEntrpsCd;
	private String siteDesc;
	private String ctrtNm;
	private String planAmt;
	private String prmtAmt;
	private String scsbidAmt;
	private String scsbidRate;
	private String baseAmt;
	private String bidMth;
	private String ctrtMth;
	private String ctrtDt;
	private String ctrtAmt;
	private String ctrtPdFrom;
	private String ctrtPdTo;
	private String ctrtGrntyAmt;
	private String prcuPblancNo;
	private String ctrtGrntyMth;
	private String ctrtExamDt;
	private String intendant1;
	private String intendant2;
	private String intendant3;
	private String caryFwd_bdgtAmt;
	private String elctrnSanctnTrnsmisSe;
	private String elctrnSanctnProgrsCd;
	private String elctrnSanctnTrnsmisDt;
	private String elctrnSanctnInterlockInfo;
	private String elctrnSanctnDocId;
	private String confmDt;
	private String confmerCd;
	private String regUsr;
	private String registDt;
	private String updUsr;
	private String updtDt;
	private String orderMthd;
	private String sldrtGrnty;
	private String jobChrgDeptCd;
	private String flawPdFrom;
	private String flawPdTo;
	private String causeAct;
    
    
    
    

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
	 * @return the sBidfrDt
	 */
	public String getsBidFrDt() {
		return sBidFrDt;
	}

	/**
	 * @param sBidfrDt the sBidfrDt to set
	 */
	public void setsBidFrDt(String sBidFrDt) {
		this.sBidFrDt = sBidFrDt;
	}

	/**
	 * @return the sBidtoDt
	 */
	public String getsBidToDt() {
		return sBidToDt;
	}

	/**
	 * @param sBidtoDt the sBidtoDt to set
	 */
	public void setsBidToDt(String sBidToDt) {
		this.sBidToDt = sBidToDt;
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
	 * @return the ctrtSe
	 */
	public String getCtrtSe() {
		return ctrtSe;
	}

	/**
	 * @param ctrtSe the ctrtSe to set
	 */
	public void setCtrtSe(String ctrtSe) {
		this.ctrtSe = ctrtSe;
	}

	/**
	 * @return the bidPblancNo
	 */
	public String getBidPblancNo() {
		return bidPblancNo;
	}

	/**
	 * @param bidPblancNo the bidPblancNo to set
	 */
	public void setBidPblancNo(String bidPblancNo) {
		this.bidPblancNo = bidPblancNo;
	}

	/**
	 * @return the bidPblancDt
	 */
	public String getBidPblancDt() {
		return bidPblancDt;
	}

	/**
	 * @param bidPblancDt the bidPblancDt to set
	 */
	public void setBidPblancDt(String bidPblancDt) {
		this.bidPblancDt = bidPblancDt;
	}

	/**
	 * @return the bidDt
	 */
	public String getBidDt() {
		return bidDt;
	}

	/**
	 * @param bidDt the bidDt to set
	 */
	public void setBidDt(String bidDt) {
		this.bidDt = bidDt;
	}

	/**
	 * @return the registEntrpsCd
	 */
	public String getRegistEntrpsCd() {
		return registEntrpsCd;
	}

	/**
	 * @param registEntrpsCd the registEntrpsCd to set
	 */
	public void setRegistEntrpsCd(String registEntrpsCd) {
		this.registEntrpsCd = registEntrpsCd;
	}

	/**
	 * @return the siteDesc
	 */
	public String getSiteDesc() {
		return siteDesc;
	}

	/**
	 * @param siteDesc the siteDesc to set
	 */
	public void setSiteDesc(String siteDesc) {
		this.siteDesc = siteDesc;
	}

	/**
	 * @return the ctrtNm
	 */
	public String getCtrtNm() {
		return ctrtNm;
	}

	/**
	 * @param ctrtNm the ctrtNm to set
	 */
	public void setCtrtNm(String ctrtNm) {
		this.ctrtNm = ctrtNm;
	}

	/**
	 * @return the planAmt
	 */
	public String getPlanAmt() {
		return planAmt;
	}

	/**
	 * @param planAmt the planAmt to set
	 */
	public void setPlanAmt(String planAmt) {
		this.planAmt = planAmt;
	}

	/**
	 * @return the prmtAmt
	 */
	public String getPrmtAmt() {
		return prmtAmt;
	}

	/**
	 * @param prmtAmt the prmtAmt to set
	 */
	public void setPrmtAmt(String prmtAmt) {
		this.prmtAmt = prmtAmt;
	}

	/**
	 * @return the scsbidAmt
	 */
	public String getScsbidAmt() {
		return scsbidAmt;
	}

	/**
	 * @param scsbidAmt the scsbidAmt to set
	 */
	public void setScsbidAmt(String scsbidAmt) {
		this.scsbidAmt = scsbidAmt;
	}

	/**
	 * @return the scsbidRate
	 */
	public String getScsbidRate() {
		return scsbidRate;
	}

	/**
	 * @param scsbidRate the scsbidRate to set
	 */
	public void setScsbidRate(String scsbidRate) {
		this.scsbidRate = scsbidRate;
	}

	/**
	 * @return the baseAmt
	 */
	public String getBaseAmt() {
		return baseAmt;
	}

	/**
	 * @param baseAmt the baseAmt to set
	 */
	public void setBaseAmt(String baseAmt) {
		this.baseAmt = baseAmt;
	}

	/**
	 * @return the bidMth
	 */
	public String getBidMth() {
		return bidMth;
	}

	/**
	 * @param bidMth the bidMth to set
	 */
	public void setBidMth(String bidMth) {
		this.bidMth = bidMth;
	}

	/**
	 * @return the ctrtMth
	 */
	public String getCtrtMth() {
		return ctrtMth;
	}

	/**
	 * @param ctrtMth the ctrtMth to set
	 */
	public void setCtrtMth(String ctrtMth) {
		this.ctrtMth = ctrtMth;
	}

	/**
	 * @return the ctrtDt
	 */
	public String getCtrtDt() {
		return ctrtDt;
	}

	/**
	 * @param ctrtDt the ctrtDt to set
	 */
	public void setCtrtDt(String ctrtDt) {
		this.ctrtDt = ctrtDt;
	}

	/**
	 * @return the ctrtAmt
	 */
	public String getCtrtAmt() {
		return ctrtAmt;
	}

	/**
	 * @param ctrtAmt the ctrtAmt to set
	 */
	public void setCtrtAmt(String ctrtAmt) {
		this.ctrtAmt = ctrtAmt;
	}

	/**
	 * @return the ctrtPdFrom
	 */
	public String getCtrtPdFrom() {
		return ctrtPdFrom;
	}

	/**
	 * @param ctrtPdFrom the ctrtPdFrom to set
	 */
	public void setCtrtPdFrom(String ctrtPdFrom) {
		this.ctrtPdFrom = ctrtPdFrom;
	}

	/**
	 * @return the ctrtPdTo
	 */
	public String getCtrtPdTo() {
		return ctrtPdTo;
	}

	/**
	 * @param ctrtPdTo the ctrtPdTo to set
	 */
	public void setCtrtPdTo(String ctrtPdTo) {
		this.ctrtPdTo = ctrtPdTo;
	}

	/**
	 * @return the ctrtGrntyAmt
	 */
	public String getCtrtGrntyAmt() {
		return ctrtGrntyAmt;
	}

	/**
	 * @param ctrtGrntyAmt the ctrtGrntyAmt to set
	 */
	public void setCtrtGrntyAmt(String ctrtGrntyAmt) {
		this.ctrtGrntyAmt = ctrtGrntyAmt;
	}

	/**
	 * @return the prcuPblancNo
	 */
	public String getPrcuPblancNo() {
		return prcuPblancNo;
	}

	/**
	 * @param prcuPblancNo the prcuPblancNo to set
	 */
	public void setPrcuPblancNo(String prcuPblancNo) {
		this.prcuPblancNo = prcuPblancNo;
	}

	/**
	 * @return the ctrtGrntyMth
	 */
	public String getCtrtGrntyMth() {
		return ctrtGrntyMth;
	}

	/**
	 * @param ctrtGrntyMth the ctrtGrntyMth to set
	 */
	public void setCtrtGrntyMth(String ctrtGrntyMth) {
		this.ctrtGrntyMth = ctrtGrntyMth;
	}

	/**
	 * @return the ctrtExamDt
	 */
	public String getCtrtExamDt() {
		return ctrtExamDt;
	}

	/**
	 * @param ctrtExamDt the ctrtExamDt to set
	 */
	public void setCtrtExamDt(String ctrtExamDt) {
		this.ctrtExamDt = ctrtExamDt;
	}

	/**
	 * @return the intendant1
	 */
	public String getIntendant1() {
		return intendant1;
	}

	/**
	 * @param intendant1 the intendant1 to set
	 */
	public void setIntendant1(String intendant1) {
		this.intendant1 = intendant1;
	}

	/**
	 * @return the intendant2
	 */
	public String getIntendant2() {
		return intendant2;
	}

	/**
	 * @param intendant2 the intendant2 to set
	 */
	public void setIntendant2(String intendant2) {
		this.intendant2 = intendant2;
	}

	/**
	 * @return the intendant3
	 */
	public String getIntendant3() {
		return intendant3;
	}

	/**
	 * @param intendant3 the intendant3 to set
	 */
	public void setIntendant3(String intendant3) {
		this.intendant3 = intendant3;
	}

	/**
	 * @return the caryFwd_bdgtAmt
	 */
	public String getCaryFwd_bdgtAmt() {
		return caryFwd_bdgtAmt;
	}

	/**
	 * @param caryFwd_bdgtAmt the caryFwd_bdgtAmt to set
	 */
	public void setCaryFwd_bdgtAmt(String caryFwd_bdgtAmt) {
		this.caryFwd_bdgtAmt = caryFwd_bdgtAmt;
	}

	/**
	 * @return the elctrnSanctnTrnsmisSe
	 */
	public String getElctrnSanctnTrnsmisSe() {
		return elctrnSanctnTrnsmisSe;
	}

	/**
	 * @param elctrnSanctnTrnsmisSe the elctrnSanctnTrnsmisSe to set
	 */
	public void setElctrnSanctnTrnsmisSe(String elctrnSanctnTrnsmisSe) {
		this.elctrnSanctnTrnsmisSe = elctrnSanctnTrnsmisSe;
	}

	/**
	 * @return the elctrnSanctnProgrsCd
	 */
	public String getElctrnSanctnProgrsCd() {
		return elctrnSanctnProgrsCd;
	}

	/**
	 * @param elctrnSanctnProgrsCd the elctrnSanctnProgrsCd to set
	 */
	public void setElctrnSanctnProgrsCd(String elctrnSanctnProgrsCd) {
		this.elctrnSanctnProgrsCd = elctrnSanctnProgrsCd;
	}

	/**
	 * @return the elctrnSanctnTrnsmisDt
	 */
	public String getElctrnSanctnTrnsmisDt() {
		return elctrnSanctnTrnsmisDt;
	}

	/**
	 * @param elctrnSanctnTrnsmisDt the elctrnSanctnTrnsmisDt to set
	 */
	public void setElctrnSanctnTrnsmisDt(String elctrnSanctnTrnsmisDt) {
		this.elctrnSanctnTrnsmisDt = elctrnSanctnTrnsmisDt;
	}

	/**
	 * @return the elctrnSanctnInterlockInfo
	 */
	public String getElctrnSanctnInterlockInfo() {
		return elctrnSanctnInterlockInfo;
	}

	/**
	 * @param elctrnSanctnInterlockInfo the elctrnSanctnInterlockInfo to set
	 */
	public void setElctrnSanctnInterlockInfo(String elctrnSanctnInterlockInfo) {
		this.elctrnSanctnInterlockInfo = elctrnSanctnInterlockInfo;
	}

	/**
	 * @return the elctrnSanctnDocId
	 */
	public String getElctrnSanctnDocId() {
		return elctrnSanctnDocId;
	}

	/**
	 * @param elctrnSanctnDocId the elctrnSanctnDocId to set
	 */
	public void setElctrnSanctnDocId(String elctrnSanctnDocId) {
		this.elctrnSanctnDocId = elctrnSanctnDocId;
	}

	/**
	 * @return the confmDt
	 */
	public String getConfmDt() {
		return confmDt;
	}

	/**
	 * @param confmDt the confmDt to set
	 */
	public void setConfmDt(String confmDt) {
		this.confmDt = confmDt;
	}

	/**
	 * @return the confmerCd
	 */
	public String getConfmerCd() {
		return confmerCd;
	}

	/**
	 * @param confmerCd the confmerCd to set
	 */
	public void setConfmerCd(String confmerCd) {
		this.confmerCd = confmerCd;
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
	 * @return the orderMthd
	 */
	public String getOrderMthd() {
		return orderMthd;
	}

	/**
	 * @param orderMthd the orderMthd to set
	 */
	public void setOrderMthd(String orderMthd) {
		this.orderMthd = orderMthd;
	}

	/**
	 * @return the sldrtGrnty
	 */
	public String getSldrtGrnty() {
		return sldrtGrnty;
	}

	/**
	 * @param sldrtGrnty the sldrtGrnty to set
	 */
	public void setSldrtGrnty(String sldrtGrnty) {
		this.sldrtGrnty = sldrtGrnty;
	}

	/**
	 * @return the jobChrgDeptCd
	 */
	public String getJobChrgDeptCd() {
		return jobChrgDeptCd;
	}

	/**
	 * @param jobChrgDeptCd the jobChrgDeptCd to set
	 */
	public void setJobChrgDeptCd(String jobChrgDeptCd) {
		this.jobChrgDeptCd = jobChrgDeptCd;
	}

	/**
	 * @return the flawPdFrom
	 */
	public String getFlawPdFrom() {
		return flawPdFrom;
	}

	/**
	 * @param flawPdFrom the flawPdFrom to set
	 */
	public void setFlawPdFrom(String flawPdFrom) {
		this.flawPdFrom = flawPdFrom;
	}

	/**
	 * @return the flawPdTo
	 */
	public String getFlawPdTo() {
		return flawPdTo;
	}

	/**
	 * @param flawPdTo the flawPdTo to set
	 */
	public void setFlawPdTo(String flawPdTo) {
		this.flawPdTo = flawPdTo;
	}

	/**
	 * @return the causeAct
	 */
	public String getCauseAct() {
		return causeAct;
	}

	/**
	 * @param causeAct the causeAct to set
	 */
	public void setCauseAct(String causeAct) {
		this.causeAct = causeAct;
	}

}