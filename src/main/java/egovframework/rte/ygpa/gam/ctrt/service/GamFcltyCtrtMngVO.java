/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltyCtrtMngVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String ctrtNo;				// 계약 번호
	private	String ctrtSe;				// 계약 구분
	private	String bidPblancNo;			// 입찰 공고 번호
	private	String bidPblancDt;			// 입찰 공고 일자
	private	String bidDt;				// 입찰 일자
	private	String registEntrpsCd;		// 등록 업체 코드
	private	String registEntrpsNm;		// 등록 업체 명
	private	String siteDesc;			// 현장 설명
	private	String ctrtNm;				// 계약 명
	private	String planAmt;				// 설계 금액
	private	String prmtAmt;				// 예정 금액
	private	String scsbider;			// 낙찰자
	private	String scsbidAmt;			// 낙찰 금액
	private	String scsbidRate;			// 낙찰 율
	private	String baseAmt;				// 기초 금액
	private	String bidMth;				// 입찰 방법
	private	String ctrtMth;				// 계약 방법
	private	String ctrtDt;				// 계약 일자
	private	String ctrtAmt;				// 계약 금액
	private	String ctrtDtFrom;			// 계약 기간 FROM
	private	String ctrtDtTo;			// 계약 기간 TO
	private	String ctrtGrntyAmt;		// 계약 보증 금액
	private	String prcuPblancNo;		// 조달 공고 번호
	private	String ctrtGrntyMth;		// 계약 보증 방법
	private	String ctrtExamDt;			// 계약 검사 일자
	private	String intendant1;			// 감독자 1
	private	String intendant2;			// 감독자 2
	private	String intendant3;			// 감독자 3
	private	String caryFwdBdgtAmt;		// 이월 예산 금액
	private	String regUsr;				// 등록자
	private	String registDt;			// 등록일시
	private	String updUsr;				// 수정자
	private	String updtDt;				// 수정일시
	private	String orderMthd;			// 발주 방식
	private	String sldrtGrnty;			// 연대 보증
	private	String jobChrgDeptCd;		// 업무 담당 부서 코드
	private	String flawDtFrom;			// 하자 기간 FROM
	private	String flawDtTo;			// 하자 기간 TO
	private	String causeAct;			// 원인 행위
	private	String ctrtSeNm;			// 계약 구분 명
	private	String ctrtGrntyMthNm;		// 계약 보증 방법 명
	private	String jobChrgDeptNm;		// 업무 담당 부서 명
	private	String sCtrtNo;				// 검색 계약 번호
	private	String sCtrtSe;				// 검색 계약 구분
	private	String sCtrtNm;				// 검색 계약 명
	private	String sRegistEntrpsCd;		// 검색 등록 업체 코드
	private	String sRegistEntrpsNm;		// 검색 등록 업체 명
	private	String sStartCtrtDt;		// 검색 계약 일자 FROM
	private	String sEndCtrtDt;			// 검색 계약 일자 END
	private	String sStartCtrtAmt;		// 검색 계약 금액 FROM
	private	String sEndCtrtAmt;			// 검색 계약 금액 TO
	private	String totalCount;			// 조회 자료 수
	private	String sumPlanAmt;			// 설계 금액 합계
	private	String sumPrmtAmt;			// 예정 금액 합계
	private	String sumScsbidAmt;		// 낙찰 금액 합계
	private	String sumBaseAmt;			// 기초 금액 합계
	private	String sumCtrtAmt;			// 계약 금액 합계

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
	 * @return the registEntrpsNm
	 */
	public String getRegistEntrpsNm() {
		return registEntrpsNm;
	}
	/**
	 * @param registEntrpsNm the registEntrpsNm to set
	 */
	public void setRegistEntrpsNm(String registEntrpsNm) {
		this.registEntrpsNm = registEntrpsNm;
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
	 * @return the scsbider
	 */
	public String getScsbider() {
		return scsbider;
	}
	/**
	 * @param scsbider the scsbider to set
	 */
	public void setScsbider(String scsbider) {
		this.scsbider = scsbider;
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
	 * @return the ctrtDtFrom
	 */
	public String getCtrtDtFrom() {
		return ctrtDtFrom;
	}
	/**
	 * @param ctrtDtFrom the ctrtDtFrom to set
	 */
	public void setCtrtDtFrom(String ctrtDtFrom) {
		this.ctrtDtFrom = ctrtDtFrom;
	}
	/**
	 * @return the ctrtDtTo
	 */
	public String getCtrtDtTo() {
		return ctrtDtTo;
	}
	/**
	 * @param ctrtDtTo the ctrtDtTo to set
	 */
	public void setCtrtDtTo(String ctrtDtTo) {
		this.ctrtDtTo = ctrtDtTo;
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
	 * @return the caryFwdBdgtAmt
	 */
	public String getCaryFwdBdgtAmt() {
		return caryFwdBdgtAmt;
	}
	/**
	 * @param caryFwdBdgtAmt the caryFwdBdgtAmt to set
	 */
	public void setCaryFwdBdgtAmt(String caryFwdBdgtAmt) {
		this.caryFwdBdgtAmt = caryFwdBdgtAmt;
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
	 * @return the flawDtFrom
	 */
	public String getFlawDtFrom() {
		return flawDtFrom;
	}
	/**
	 * @param flawDtFrom the flawDtFrom to set
	 */
	public void setFlawDtFrom(String flawDtFrom) {
		this.flawDtFrom = flawDtFrom;
	}
	/**
	 * @return the flawDtTo
	 */
	public String getFlawDtTo() {
		return flawDtTo;
	}
	/**
	 * @param flawDtTo the flawDtTo to set
	 */
	public void setFlawDtTo(String flawDtTo) {
		this.flawDtTo = flawDtTo;
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
	/**
	 * @return the ctrtSeNm
	 */
	public String getCtrtSeNm() {
		return ctrtSeNm;
	}
	/**
	 * @param ctrtSeNm the ctrtSeNm to set
	 */
	public void setCtrtSeNm(String ctrtSeNm) {
		this.ctrtSeNm = ctrtSeNm;
	}
	/**
	 * @return the ctrtGrntyMthNm
	 */
	public String getCtrtGrntyMthNm() {
		return ctrtGrntyMthNm;
	}
	/**
	 * @param ctrtGrntyMthNm the ctrtGrntyMthNm to set
	 */
	public void setCtrtGrntyMthNm(String ctrtGrntyMthNm) {
		this.ctrtGrntyMthNm = ctrtGrntyMthNm;
	}
	/**
	 * @return the jobChrgDeptNm
	 */
	public String getJobChrgDeptNm() {
		return jobChrgDeptNm;
	}
	/**
	 * @param jobChrgDeptNm the jobChrgDeptNm to set
	 */
	public void setJobChrgDeptNm(String jobChrgDeptNm) {
		this.jobChrgDeptNm = jobChrgDeptNm;
	}
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
	 * @return the sRegistEntrpsNm
	 */
	public String getsRegistEntrpsNm() {
		return sRegistEntrpsNm;
	}
	/**
	 * @param sRegistEntrpsNm the sRegistEntrpsNm to set
	 */
	public void setsRegistEntrpsNm(String sRegistEntrpsNm) {
		this.sRegistEntrpsNm = sRegistEntrpsNm;
	}
	/**
	 * @return the sStartCtrtDt
	 */
	public String getsStartCtrtDt() {
		return sStartCtrtDt;
	}
	/**
	 * @param sStartCtrtDt the sStartCtrtDt to set
	 */
	public void setsStartCtrtDt(String sStartCtrtDt) {
		this.sStartCtrtDt = sStartCtrtDt;
	}
	/**
	 * @return the sEndCtrtDt
	 */
	public String getsEndCtrtDt() {
		return sEndCtrtDt;
	}
	/**
	 * @param sEndCtrtDt the sEndCtrtDt to set
	 */
	public void setsEndCtrtDt(String sEndCtrtDt) {
		this.sEndCtrtDt = sEndCtrtDt;
	}
	/**
	 * @return the sStartCtrtAmt
	 */
	public String getsStartCtrtAmt() {
		return sStartCtrtAmt;
	}
	/**
	 * @param sStartCtrtAmt the sStartCtrtAmt to set
	 */
	public void setsStartCtrtAmt(String sStartCtrtAmt) {
		this.sStartCtrtAmt = sStartCtrtAmt;
	}
	/**
	 * @return the sEndCtrtAmt
	 */
	public String getsEndCtrtAmt() {
		return sEndCtrtAmt;
	}
	/**
	 * @param sEndCtrtAmt the sEndCtrtAmt to set
	 */
	public void setsEndCtrtAmt(String sEndCtrtAmt) {
		this.sEndCtrtAmt = sEndCtrtAmt;
	}
	/**
	 * @return the totalCount
	 */
	public String getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the sumPlanAmt
	 */
	public String getSumPlanAmt() {
		return sumPlanAmt;
	}
	/**
	 * @param sumPlanAmt the sumPlanAmt to set
	 */
	public void setSumPlanAmt(String sumPlanAmt) {
		this.sumPlanAmt = sumPlanAmt;
	}
	/**
	 * @return the sumPrmtAmt
	 */
	public String getSumPrmtAmt() {
		return sumPrmtAmt;
	}
	/**
	 * @param sumPrmtAmt the sumPrmtAmt to set
	 */
	public void setSumPrmtAmt(String sumPrmtAmt) {
		this.sumPrmtAmt = sumPrmtAmt;
	}
	/**
	 * @return the sumScsbidAmt
	 */
	public String getSumScsbidAmt() {
		return sumScsbidAmt;
	}
	/**
	 * @param sumScsbidAmt the sumScsbidAmt to set
	 */
	public void setSumScsbidAmt(String sumScsbidAmt) {
		this.sumScsbidAmt = sumScsbidAmt;
	}
	/**
	 * @return the sumBaseAmt
	 */
	public String getSumBaseAmt() {
		return sumBaseAmt;
	}
	/**
	 * @param sumBaseAmt the sumBaseAmt to set
	 */
	public void setSumBaseAmt(String sumBaseAmt) {
		this.sumBaseAmt = sumBaseAmt;
	}
	/**
	 * @return the sumCtrtAmt
	 */
	public String getSumCtrtAmt() {
		return sumCtrtAmt;
	}
	/**
	 * @param sumCtrtAmt the sumCtrtAmt to set
	 */
	public void setSumCtrtAmt(String sumCtrtAmt) {
		this.sumCtrtAmt = sumCtrtAmt;
	}

}
