/**
 * 
 */
package egovframework.rte.ygpa.gam.ctrt.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltyCtrtMngVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;

	/** 계약번호 (조회조건) **/
	private String sCtrtNo;

	/** 계약번호 **/
	private String ctrtNo;
	
	/** 계약방법 **/
	private String ctrtSe;
	
	/** 발주방식 **/
	private String orderMthd;
	
	/** 입찰공고번호 **/
	private String bidPblancNo;
	
	/** 입찰공고일자 **/
	private String bidPblancDt;
	
	/** 입찰일자 **/
	private String bidDt;
	
	/** 등록업체코드 **/
	private String registEntprsCd;
	
	/** 현장설명 **/
	private String siteDesc;
	
	/** 계약명 **/
	private String ctrtNm;
	
	/** 설계금액 **/
	private String planAmt;
	
	/** 예정금액 **/
	private String prmtAmt;
	
	/** 낙찰금액 **/
	private String scsbidAmt;
	
	/** 낙찰율 **/
	private String scsbidRate;
	
	/** 기초금액 **/
	private String baseAmt;
	
	/** 입찰방법 **/
	private String bidMth;
	
	/** 계약방법 **/
	private String ctrtMth;
	
	/** 계약일자 **/
	private String ctrtDt;
	
	/** 계약금액 **/
	private String ctrtAmt;
	
	/** 계약기간 from **/
	private String ctrtPdFrom;
	
	/** 계약기간 To **/
	private String ctrtPdTo;
	
	/** 계약보증금액 **/
	private String ctrtGrntyAmt;
	
	/** 조달공고번호 **/
	private String prcuPblancNo;
	
	/** 계약보증방법 **/
	private String ctrtGrntyMth;
	
	/** 계약검사일자 **/
	private String ctrtExamDt;
	
	/** 감독자1 **/
	private String intendant1;
	
	/** 감독자2 **/
	private String intendant2;
	
	/** 감독자3 **/
	private String intendant3;
	
	/** 이월예산금액 **/
	private String crayFwdBdgtAmt;
	
	/** 전자결재전송구분 **/
	private String elctrnSanctnTrnsmisSe;
	
	/** 전자결재전송코드 **/
	private String elctrnSanctnProgrsCd;
	
	/** 전자결재전송일자 **/
	private String elctrnSanctnTransmisDt;
	
	/** 전자결재연동정보 **/
	private String elctrnSanctnInterlockInfo;
	
	/** 전자결재문서아이디 **/
	private String elctrnSanctnDocId;
	
	/** 승인일자 **/
	private String confmDt;
	
	/** 승인자코드 **/
	private String confmerCd;
	
	/** 등록자 **/
	private String regUsr;
	
	/** 등록일시 **/
	private String registDt;
	
	/** 수정자 **/
	private String updUsr;

	/** 수정일시 **/	
	private String updtDt;

	/**
	 * @return the sCtrtNo
	 */
	public String getsCtrtNo() {
		return sCtrtNo;
	}

	/**
	 * @param sCtrtNo the sCtrtNo to set
	 */
	public void setsCtrtNo(String sCtrtNo) {
		this.sCtrtNo = sCtrtNo;
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
	 * @return the registEntprsCd
	 */
	public String getRegistEntprsCd() {
		return registEntprsCd;
	}

	/**
	 * @param registEntprsCd the registEntprsCd to set
	 */
	public void setRegistEntprsCd(String registEntprsCd) {
		this.registEntprsCd = registEntprsCd;
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
	 * @return the crayFwdBdgtAmt
	 */
	public String getCrayFwdBdgtAmt() {
		return crayFwdBdgtAmt;
	}

	/**
	 * @param crayFwdBdgtAmt the crayFwdBdgtAmt to set
	 */
	public void setCrayFwdBdgtAmt(String crayFwdBdgtAmt) {
		this.crayFwdBdgtAmt = crayFwdBdgtAmt;
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
	 * @return the elctrnSanctnTransmisDt
	 */
	public String getElctrnSanctnTransmisDt() {
		return elctrnSanctnTransmisDt;
	}

	/**
	 * @param elctrnSanctnTransmisDt the elctrnSanctnTransmisDt to set
	 */
	public void setElctrnSanctnTransmisDt(String elctrnSanctnTransmisDt) {
		this.elctrnSanctnTransmisDt = elctrnSanctnTransmisDt;
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
}
