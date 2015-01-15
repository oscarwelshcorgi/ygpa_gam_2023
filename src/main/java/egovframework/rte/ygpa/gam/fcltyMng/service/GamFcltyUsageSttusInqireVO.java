/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author jckim
 * @since 2014. 12. 8.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 8.		jckim		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GamFcltyUsageSttusInqireVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	/** 항코드 (조회조건) **/
	private String sPrtAtCode;

	/** 시설구분(조회조건) **/
	private String sFcltsJobSe;

	/** 시설명(조회조건) **/
	private String sPrtFcltyNm;

	/** 시설물 관리번호(조회조건) **/
	private String sFcltsMngNo;

	/** 사용기간 (조회조건) **/
	private String sUsagePdFrom;
	private String sUsagePdTo;

// ==== 자산 임대 ==== //
	/** GIS 자산 **/
	private String gisAssets;
	/** GIS 자산 항코드 **/
	private String gisAssetsPrtAtCode;
	/** GIS 자산 항코드 명 **/
	private String gisAssetsPrtAtNm;
	/** GIS 자산 코드**/
	private String gisAssetsCd;
	/** GIS 자산 서브 코드**/
	private String gisAssetsSubCd;
	/** 항만시설 구분 **/
	private String prtFcltySe;
	/** 항만시설 명 **/
	private String prtFcltyNm;
	/** 항만시설 규격 **/
	private String prtFcltyDtndrd;
	/** 항만시설 단위 **/
	private String prtFcltyUnit;
	/** 항만시설 설치 일자 **/
	private String prtFcltyInstlDt;
	/** 항만시설 변경일자 **/
	private String prtFcltyChangeDt;
	/** 항만시설 관리 업체 **/
	private String prtFcltyMngEntrps;
	/** 시설 만료일자 **/
	private String prtFcltyExprDt;
	/** 시설 수량 **/
	private String prtPrtFcltyCnt;
	/** 시설 담당 **/
	private String prtPrtFcltyMnger;
	/** 시설물 관리 번호 **/
	private String fcltsMngNo;
	/** 시설물 관리 그룹 **/
	private String fcltsMngGroupNo;

// ==== 자산 임대 ==== //
	/** 항코드 **/
	private String prtAtCode;
	/** 관리번호 **/
	private String mngYearNo;
	/** GIS 코드 **/
//	private String gisAssets;
	/** 문서번호 **/
	private String docNo;
	/** 고지방법 **/
	private String nticMth;
	/** 사용목적 **/
	private String usagePurps;
	/** 사용내역 **/
	private String usageDtls;
	/** 면적 **/
	private int usageAr;
	/** 금액 **/
	private int fee;
	/** 허가일자 **/
	private String prmisnDt;

	// ==== 점검 관리 내역 ==== //
	/** 시설물 업무 구분 **/
	private String fcltsJobSe;
	/** 시설물 업무 구분 명**/
	private String fcltsJobNm;
	/** 시설물 관리 순번 **/
	private int qcMngSeq;
	/** 시행 년도 **/
	private String enforceYear;
	/** 점검 관리 명 **/
	private String qcMngNm;
	/** 점검 진단 일자 **/
	private String qcInspDt;
	/** 점검 진단 구분 **/
	private String qcInspSe;
	/** 점검 진단 기관 명 **/
	private String qcInspInsttNm;
	/** 책임 기술자 명 **/
	private String responEngineerNm;
	/** 점검 시작 일자 **/
	private String qcBeginDt;
	/** 점검 종료 일자 **/
	private String qcEndDt;
	/** 점검 진단 예산 **/
	private int qcInspBdgt;
	/** 점검 진단 금액 **/
	private int qcInspAmt;
	/** 상태 평가 등급**/
	private String sttusEvlLvl;
	/** 점검 진단 결과 **/
	private String qcInspResult;
	/** 조치 내용 **/
	private String actionCn;
	/** 조치 구분 **/
	private String actionSe;
	/** 비고 **/
	private String rm;

	// ==== 점검 관리 대상 시설물 ==== //
	/** 시설물 관리번호(자산인대 사용) **/
	/** 시설물 업무 구분 **/
//	private String objFcltsJobSe;
	/** 점검 진단 구분 **/
//	private String objQcInspSe;
	/** 점검 진단 일자 **/
//	private String objQcInspDt;
	/** 감리자 **/
	private String inspector;
	/** 점검 진단 결과 **/
//	private String objQcInspResult;
	/** 비고 **/
//	private String objRm;

	// ==== 점검 관리 결과 항목 ==== //
	/** 점검 항목 코드 **/
	private String qcItemCd;
	/** 순번 **/
	private String seq;
	/** 점검 결과 구분 **/
	private String inspResultChk;
	/** 점검 결과 내용 **/
	private String inspResultCn;

	// ==== 하자 보수 내역 ==== //
	/** 하자 보수 시작 이랒 **/
	private String flawRprStartDt;
	/** 하자 보수 종료 일자 **/
	private String flawRprEndDt;
	/** 하자 보수 금액 **/
	private String flawRprAmt;
	/** 하자 보수 내용 **/
	private String flawRprContents;
	/** 하자 보수 업체 명 **/
	private String flawRprEntrpsNm;
	/** 하자 보수 유형 **/
	private String flawRprTy;
	/** 하자 발생 일자 **/
	private String flawOccrrncDt;
	/** 하자보수 완료 여부 **/
	private String flawRprComptYn;
	/** 하자 검사 구분 **/
	private String flawExamSe;
	/** 하자 검사 구분 **/
	private String flawExamNm;
	/** 시행년도 **/
//	private String enforceYear;
	/** 하자 보수 명 **/
	private String flawRprNm;
	/** 하자 검사 일자 **/
	private String flawExamDt;
	/** 하자 유무 **/
	private String flawEnnc;
	/** 하자 검사 결과 **/
	private String flawExamResult;
	/** 비고 **/
//	private String rm;
	/** 계약번호 **/
	private String ctrtNo;

	// ==== 하자 보수 대상 시설물 ==== //
	/** 시설물 관리 그룹 번호 **/
//	private String fcltsMngGroupNo;
	/** 시설물 업무 구분 **/
//	private String fcltsJobSe;
	/** 하자 보수 순번 **/
	private String flawRprSeq;
	/** 시설물 관리 번호 **/
//	private String fcltsMngNo;
	/** 하자 검사 일자 **/
//	private String flawExamDt;
	/** 하자 유무 **/
//	private String flawEnnc;
	/** 하자 검사 결과 **/
//	private String flawExamResult;
	/** 비고 **/
//	private String rm;

	// ==== 하자 검사자 ==== //
	/** 순번 **/
//	private String seq;
	/** 하자 검사자 **/
	private String flawExamUsr;
	/** 하자 검사 일자 **/
//	private String flawExamDt;
	/** 하자 검사 완료 여부 **/
	private String flawExamComptYn;

	// ==== 유지 보수 내역 ==== //
//	private String fcltsJobSe;
//	private String enforceYear;
	/** 유지 보수 구분 **/
	private String mntnRprSeq;
	/** 유지 보수 구분 **/
	private String mntnRprSe;
	/** 유지 보수 부위 **/
	private String mntnRprPart;
	/** 유지 보수 내용 **/
	private String mntnRprCn;
	/** 유지 보수 예산 **/
	private String mntnRprBdgt;
	/** 유지 보수 공사명 **/
	private String mntnRprCnstNm;
	/** 공사 시작 일 **/
	private String mntnRprCnstStartDt;
	/** 공사 종료 일 **/
	private String mntnRprCnstEndDt;
	/** 공사 금액 **/
	private String mntnRprCnstAmt;
	/** 설계자 **/
	private String plannerNm;
	/** 시공자 **/
	private String cnstrtr;
	/** 책임 시공자 **/
	private String responEngineer;
	/** 공사 감독자 **/
	private String cnstChargNm;
//	private String rm;

	// ==== 유지 보수 내역 ==== //
	private String mntnRprCnstMth;
	private String unit;
	private String qy;
	private String price;
//	private String mntnRprCnstAmt;

	/**
	 * @return the sPrtAtCode
	 */
	public String getsPrtAtCode() {
		return sPrtAtCode;
	}
	/**
	 * @return the mntnRprSeq
	 */
	public String getMntnRprSeq() {
		return mntnRprSeq;
	}
	/**
	 * @param mntnRprSeq the mntnRprSeq to set
	 */
	public void setMntnRprSeq(String mntnRprSeq) {
		this.mntnRprSeq = mntnRprSeq;
	}
	/**
	 * @param sPrtAtCode the sPrtAtCode to set
	 */
	public void setsPrtAtCode(String sPrtAtCode) {
		this.sPrtAtCode = sPrtAtCode;
	}
	/**
	 * @return the sFcltsJobSe
	 */
	public String getsFcltsJobSe() {
		return sFcltsJobSe;
	}
	/**
	 * @param sFcltsJobSe the sFcltsJobSe to set
	 */
	public void setsFcltsJobSe(String sFcltsJobSe) {
		this.sFcltsJobSe = sFcltsJobSe;
	}
	/**
	 * @return the sPrtFcltyNm
	 */
	public String getsPrtFcltyNm() {
		return sPrtFcltyNm;
	}
	/**
	 * @param sPrtFcltyNm the sPrtFcltyNm to set
	 */
	public void setsPrtFcltyNm(String sPrtFcltyNm) {
		this.sPrtFcltyNm = sPrtFcltyNm;
	}
	/**
	 * @return the sFcltsMngNo
	 */
	public String getsFcltsMngNo() {
		return sFcltsMngNo;
	}
	/**
	 * @param sFcltsMngNo the sFcltsMngNo to set
	 */
	public void setsFcltsMngNo(String sFcltsMngNo) {
		this.sFcltsMngNo = sFcltsMngNo;
	}
	/**
	 * @return the sUsagePdFrom
	 */
	public String getsUsagePdFrom() {
		return sUsagePdFrom;
	}
	/**
	 * @param sUsagePdFrom the sUsagePdFrom to set
	 */
	public void setsUsagePdFrom(String sUsagePdFrom) {
		this.sUsagePdFrom = sUsagePdFrom;
	}
	/**
	 * @return the sUsagePdTo
	 */
	public String getsUsagePdTo() {
		return sUsagePdTo;
	}
	/**
	 * @param sUsagePdTo the sUsagePdTo to set
	 */
	public void setsUsagePdTo(String sUsagePdTo) {
		this.sUsagePdTo = sUsagePdTo;
	}
	/**
	 * @return the gisAssets
	 */
	public String getGisAssets() {
		return gisAssets;
	}
	/**
	 * @param gisAssets the gisAssets to set
	 */
	public void setGisAssets(String gisAssets) {
		this.gisAssets = gisAssets;
	}
	/**
	 * @return the gisAssetsPrtAtCode
	 */
	public String getGisAssetsPrtAtCode() {
		return gisAssetsPrtAtCode;
	}
	/**
	 * @param gisAssetsPrtAtCode the gisAssetsPrtAtCode to set
	 */
	public void setGisAssetsPrtAtCode(String gisAssetsPrtAtCode) {
		this.gisAssetsPrtAtCode = gisAssetsPrtAtCode;
	}
	/**
	 * @return the gisAssetsPrtAtNm
	 */
	public String getGisAssetsPrtAtNm() {
		return gisAssetsPrtAtNm;
	}
	/**
	 * @param gisAssetsPrtAtNm the gisAssetsPrtAtNm to set
	 */
	public void setGisAssetsPrtAtNm(String gisAssetsPrtAtNm) {
		this.gisAssetsPrtAtNm = gisAssetsPrtAtNm;
	}
	/**
	 * @return the gisAssetsCd
	 */
	public String getGisAssetsCd() {
		return gisAssetsCd;
	}
	/**
	 * @param gisAssetsCd the gisAssetsCd to set
	 */
	public void setGisAssetsCd(String gisAssetsCd) {
		this.gisAssetsCd = gisAssetsCd;
	}
	/**
	 * @return the gisAssetsSubCd
	 */
	public String getGisAssetsSubCd() {
		return gisAssetsSubCd;
	}
	/**
	 * @param gisAssetsSubCd the gisAssetsSubCd to set
	 */
	public void setGisAssetsSubCd(String gisAssetsSubCd) {
		this.gisAssetsSubCd = gisAssetsSubCd;
	}
	/**
	 * @return the prtFcltySe
	 */
	public String getPrtFcltySe() {
		return prtFcltySe;
	}
	/**
	 * @param prtFcltySe the prtFcltySe to set
	 */
	public void setPrtFcltySe(String prtFcltySe) {
		this.prtFcltySe = prtFcltySe;
	}
	/**
	 * @return the prtFcltyNm
	 */
	public String getPrtFcltyNm() {
		return prtFcltyNm;
	}
	/**
	 * @param prtFcltyNm the prtFcltyNm to set
	 */
	public void setPrtFcltyNm(String prtFcltyNm) {
		this.prtFcltyNm = prtFcltyNm;
	}
	/**
	 * @return the prtFcltyDtndrd
	 */
	public String getPrtFcltyDtndrd() {
		return prtFcltyDtndrd;
	}
	/**
	 * @param prtFcltyDtndrd the prtFcltyDtndrd to set
	 */
	public void setPrtFcltyDtndrd(String prtFcltyDtndrd) {
		this.prtFcltyDtndrd = prtFcltyDtndrd;
	}
	/**
	 * @return the prtFcltyUnit
	 */
	public String getPrtFcltyUnit() {
		return prtFcltyUnit;
	}
	/**
	 * @param prtFcltyUnit the prtFcltyUnit to set
	 */
	public void setPrtFcltyUnit(String prtFcltyUnit) {
		this.prtFcltyUnit = prtFcltyUnit;
	}
	/**
	 * @return the prtFcltyInstlDt
	 */
	public String getPrtFcltyInstlDt() {
		return prtFcltyInstlDt;
	}
	/**
	 * @param prtFcltyInstlDt the prtFcltyInstlDt to set
	 */
	public void setPrtFcltyInstlDt(String prtFcltyInstlDt) {
		this.prtFcltyInstlDt = prtFcltyInstlDt;
	}
	/**
	 * @return the prtFcltyChangeDt
	 */
	public String getPrtFcltyChangeDt() {
		return prtFcltyChangeDt;
	}
	/**
	 * @param prtFcltyChangeDt the prtFcltyChangeDt to set
	 */
	public void setPrtFcltyChangeDt(String prtFcltyChangeDt) {
		this.prtFcltyChangeDt = prtFcltyChangeDt;
	}
	/**
	 * @return the prtFcltyMngEntrps
	 */
	public String getPrtFcltyMngEntrps() {
		return prtFcltyMngEntrps;
	}
	/**
	 * @param prtFcltyMngEntrps the prtFcltyMngEntrps to set
	 */
	public void setPrtFcltyMngEntrps(String prtFcltyMngEntrps) {
		this.prtFcltyMngEntrps = prtFcltyMngEntrps;
	}
	/**
	 * @return the prtFcltyExprDt
	 */
	public String getPrtFcltyExprDt() {
		return prtFcltyExprDt;
	}
	/**
	 * @param prtFcltyExprDt the prtFcltyExprDt to set
	 */
	public void setPrtFcltyExprDt(String prtFcltyExprDt) {
		this.prtFcltyExprDt = prtFcltyExprDt;
	}
	/**
	 * @return the prtPrtFcltyCnt
	 */
	public String getPrtPrtFcltyCnt() {
		return prtPrtFcltyCnt;
	}
	/**
	 * @param prtPrtFcltyCnt the prtPrtFcltyCnt to set
	 */
	public void setPrtPrtFcltyCnt(String prtPrtFcltyCnt) {
		this.prtPrtFcltyCnt = prtPrtFcltyCnt;
	}
	/**
	 * @return the prtPrtFcltyMnger
	 */
	public String getPrtPrtFcltyMnger() {
		return prtPrtFcltyMnger;
	}
	/**
	 * @param prtPrtFcltyMnger the prtPrtFcltyMnger to set
	 */
	public void setPrtPrtFcltyMnger(String prtPrtFcltyMnger) {
		this.prtPrtFcltyMnger = prtPrtFcltyMnger;
	}
	/**
	 * @return the fcltsMngNo
	 */
	public String getFcltsMngNo() {
		return fcltsMngNo;
	}
	/**
	 * @param fcltsMngNo the fcltsMngNo to set
	 */
	public void setFcltsMngNo(String fcltsMngNo) {
		this.fcltsMngNo = fcltsMngNo;
	}
	/**
	 * @return the fcltsMngGroupNo
	 */
	public String getFcltsMngGroupNo() {
		return fcltsMngGroupNo;
	}
	/**
	 * @param fcltsMngGroupNo the fcltsMngGroupNo to set
	 */
	public void setFcltsMngGroupNo(String fcltsMngGroupNo) {
		this.fcltsMngGroupNo = fcltsMngGroupNo;
	}
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
	 * @return the mngYearNo
	 */
	public String getMngYearNo() {
		return mngYearNo;
	}
	/**
	 * @param mngYearNo the mngYearNo to set
	 */
	public void setMngYearNo(String mngYearNo) {
		this.mngYearNo = mngYearNo;
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
	 * @return the usagePurps
	 */
	public String getUsagePurps() {
		return usagePurps;
	}
	/**
	 * @param usagePurps the usagePurps to set
	 */
	public void setUsagePurps(String usagePurps) {
		this.usagePurps = usagePurps;
	}
	/**
	 * @return the usageDtls
	 */
	public String getUsageDtls() {
		return usageDtls;
	}
	/**
	 * @param usageDtls the usageDtls to set
	 */
	public void setUsageDtls(String usageDtls) {
		this.usageDtls = usageDtls;
	}
	/**
	 * @return the usageAr
	 */
	public int getUsageAr() {
		return usageAr;
	}
	/**
	 * @param usageAr the usageAr to set
	 */
	public void setUsageAr(int usageAr) {
		this.usageAr = usageAr;
	}
	/**
	 * @return the fee
	 */
	public int getFee() {
		return fee;
	}
	/**
	 * @param fee the fee to set
	 */
	public void setFee(int fee) {
		this.fee = fee;
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
	 * @return the fcltsJobSe
	 */
	public String getFcltsJobSe() {
		return fcltsJobSe;
	}
	/**
	 * @param fcltsJobSe the fcltsJobSe to set
	 */
	public void setFcltsJobSe(String fcltsJobSe) {
		this.fcltsJobSe = fcltsJobSe;
	}
	/**
	 * @return the fcltsJobNm
	 */
	public String getFcltsJobNm() {
		return fcltsJobNm;
	}
	/**
	 * @param fcltsJobNm the fcltsJobNm to set
	 */
	public void setFcltsJobNm(String fcltsJobNm) {
		this.fcltsJobNm = fcltsJobNm;
	}
	/**
	 * @return the qcMngSeq
	 */
	public int getQcMngSeq() {
		return qcMngSeq;
	}
	/**
	 * @param qcMngSeq the qcMngSeq to set
	 */
	public void setQcMngSeq(int qcMngSeq) {
		this.qcMngSeq = qcMngSeq;
	}
	/**
	 * @return the enforceYear
	 */
	public String getEnforceYear() {
		return enforceYear;
	}
	/**
	 * @param enforceYear the enforceYear to set
	 */
	public void setEnforceYear(String enforceYear) {
		this.enforceYear = enforceYear;
	}
	/**
	 * @return the qcMngNm
	 */
	public String getQcMngNm() {
		return qcMngNm;
	}
	/**
	 * @param qcMngNm the qcMngNm to set
	 */
	public void setQcMngNm(String qcMngNm) {
		this.qcMngNm = qcMngNm;
	}
	/**
	 * @return the qcInspDt
	 */
	public String getQcInspDt() {
		return qcInspDt;
	}
	/**
	 * @param qcInspDt the qcInspDt to set
	 */
	public void setQcInspDt(String qcInspDt) {
		this.qcInspDt = qcInspDt;
	}
	/**
	 * @return the qcInspSe
	 */
	public String getQcInspSe() {
		return qcInspSe;
	}
	/**
	 * @param qcInspSe the qcInspSe to set
	 */
	public void setQcInspSe(String qcInspSe) {
		this.qcInspSe = qcInspSe;
	}
	/**
	 * @return the qcInspInsttNm
	 */
	public String getQcInspInsttNm() {
		return qcInspInsttNm;
	}
	/**
	 * @param qcInspInsttNm the qcInspInsttNm to set
	 */
	public void setQcInspInsttNm(String qcInspInsttNm) {
		this.qcInspInsttNm = qcInspInsttNm;
	}
	/**
	 * @return the responEngineerNm
	 */
	public String getResponEngineerNm() {
		return responEngineerNm;
	}
	/**
	 * @param responEngineerNm the responEngineerNm to set
	 */
	public void setResponEngineerNm(String responEngineerNm) {
		this.responEngineerNm = responEngineerNm;
	}
	/**
	 * @return the qcBeginDt
	 */
	public String getQcBeginDt() {
		return qcBeginDt;
	}
	/**
	 * @param qcBeginDt the qcBeginDt to set
	 */
	public void setQcBeginDt(String qcBeginDt) {
		this.qcBeginDt = qcBeginDt;
	}
	/**
	 * @return the qcEndDt
	 */
	public String getQcEndDt() {
		return qcEndDt;
	}
	/**
	 * @param qcEndDt the qcEndDt to set
	 */
	public void setQcEndDt(String qcEndDt) {
		this.qcEndDt = qcEndDt;
	}
	/**
	 * @return the qcInspBdgt
	 */
	public int getQcInspBdgt() {
		return qcInspBdgt;
	}
	/**
	 * @param qcInspBdgt the qcInspBdgt to set
	 */
	public void setQcInspBdgt(int qcInspBdgt) {
		this.qcInspBdgt = qcInspBdgt;
	}
	/**
	 * @return the qcInspAmt
	 */
	public int getQcInspAmt() {
		return qcInspAmt;
	}
	/**
	 * @param qcInspAmt the qcInspAmt to set
	 */
	public void setQcInspAmt(int qcInspAmt) {
		this.qcInspAmt = qcInspAmt;
	}
	/**
	 * @return the sttusEvlLvl
	 */
	public String getSttusEvlLvl() {
		return sttusEvlLvl;
	}
	/**
	 * @param sttusEvlLvl the sttusEvlLvl to set
	 */
	public void setSttusEvlLvl(String sttusEvlLvl) {
		this.sttusEvlLvl = sttusEvlLvl;
	}
	/**
	 * @return the qcInspResult
	 */
	public String getQcInspResult() {
		return qcInspResult;
	}
	/**
	 * @param qcInspResult the qcInspResult to set
	 */
	public void setQcInspResult(String qcInspResult) {
		this.qcInspResult = qcInspResult;
	}
	/**
	 * @return the actionCn
	 */
	public String getActionCn() {
		return actionCn;
	}
	/**
	 * @param actionCn the actionCn to set
	 */
	public void setActionCn(String actionCn) {
		this.actionCn = actionCn;
	}
	/**
	 * @return the actionSe
	 */
	public String getActionSe() {
		return actionSe;
	}
	/**
	 * @param actionSe the actionSe to set
	 */
	public void setActionSe(String actionSe) {
		this.actionSe = actionSe;
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
	 * @return the inspector
	 */
	public String getInspector() {
		return inspector;
	}
	/**
	 * @param inspector the inspector to set
	 */
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	/**
	 * @return the qcItemCd
	 */
	public String getQcItemCd() {
		return qcItemCd;
	}
	/**
	 * @param qcItemCd the qcItemCd to set
	 */
	public void setQcItemCd(String qcItemCd) {
		this.qcItemCd = qcItemCd;
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
	/**
	 * @return the inspResultChk
	 */
	public String getInspResultChk() {
		return inspResultChk;
	}
	/**
	 * @param inspResultChk the inspResultChk to set
	 */
	public void setInspResultChk(String inspResultChk) {
		this.inspResultChk = inspResultChk;
	}
	/**
	 * @return the inspResultCn
	 */
	public String getInspResultCn() {
		return inspResultCn;
	}
	/**
	 * @param inspResultCn the inspResultCn to set
	 */
	public void setInspResultCn(String inspResultCn) {
		this.inspResultCn = inspResultCn;
	}
	/**
	 * @return the flawRprStartDt
	 */
	public String getFlawRprStartDt() {
		return flawRprStartDt;
	}
	/**
	 * @param flawRprStartDt the flawRprStartDt to set
	 */
	public void setFlawRprStartDt(String flawRprStartDt) {
		this.flawRprStartDt = flawRprStartDt;
	}
	/**
	 * @return the flawRprEndDt
	 */
	public String getFlawRprEndDt() {
		return flawRprEndDt;
	}
	/**
	 * @param flawRprEndDt the flawRprEndDt to set
	 */
	public void setFlawRprEndDt(String flawRprEndDt) {
		this.flawRprEndDt = flawRprEndDt;
	}
	/**
	 * @return the flawRprAmt
	 */
	public String getFlawRprAmt() {
		return flawRprAmt;
	}
	/**
	 * @param flawRprAmt the flawRprAmt to set
	 */
	public void setFlawRprAmt(String flawRprAmt) {
		this.flawRprAmt = flawRprAmt;
	}
	/**
	 * @return the flawRprContents
	 */
	public String getFlawRprContents() {
		return flawRprContents;
	}
	/**
	 * @param flawRprContents the flawRprContents to set
	 */
	public void setFlawRprContents(String flawRprContents) {
		this.flawRprContents = flawRprContents;
	}
	/**
	 * @return the flawRprEntrpsNm
	 */
	public String getFlawRprEntrpsNm() {
		return flawRprEntrpsNm;
	}
	/**
	 * @param flawRprEntrpsNm the flawRprEntrpsNm to set
	 */
	public void setFlawRprEntrpsNm(String flawRprEntrpsNm) {
		this.flawRprEntrpsNm = flawRprEntrpsNm;
	}
	/**
	 * @return the flawRprTy
	 */
	public String getFlawRprTy() {
		return flawRprTy;
	}
	/**
	 * @param flawRprTy the flawRprTy to set
	 */
	public void setFlawRprTy(String flawRprTy) {
		this.flawRprTy = flawRprTy;
	}
	/**
	 * @return the flawOccrrncDt
	 */
	public String getFlawOccrrncDt() {
		return flawOccrrncDt;
	}
	/**
	 * @param flawOccrrncDt the flawOccrrncDt to set
	 */
	public void setFlawOccrrncDt(String flawOccrrncDt) {
		this.flawOccrrncDt = flawOccrrncDt;
	}
	/**
	 * @return the flawRprComptYn
	 */
	public String getFlawRprComptYn() {
		return flawRprComptYn;
	}
	/**
	 * @param flawRprComptYn the flawRprComptYn to set
	 */
	public void setFlawRprComptYn(String flawRprComptYn) {
		this.flawRprComptYn = flawRprComptYn;
	}
	/**
	 * @return the flawExamSe
	 */
	public String getFlawExamSe() {
		return flawExamSe;
	}
	/**
	 * @param flawExamSe the flawExamSe to set
	 */
	public void setFlawExamSe(String flawExamSe) {
		this.flawExamSe = flawExamSe;
	}
	/**
	 * @return the flawExamNm
	 */
	public String getFlawExamNm() {
		return flawExamNm;
	}
	/**
	 * @param flawExamNm the flawExamNm to set
	 */
	public void setFlawExamNm(String flawExamNm) {
		this.flawExamNm = flawExamNm;
	}
	/**
	 * @return the flawRprNm
	 */
	public String getFlawRprNm() {
		return flawRprNm;
	}
	/**
	 * @param flawRprNm the flawRprNm to set
	 */
	public void setFlawRprNm(String flawRprNm) {
		this.flawRprNm = flawRprNm;
	}
	/**
	 * @return the flawExamDt
	 */
	public String getFlawExamDt() {
		return flawExamDt;
	}
	/**
	 * @param flawExamDt the flawExamDt to set
	 */
	public void setFlawExamDt(String flawExamDt) {
		this.flawExamDt = flawExamDt;
	}
	/**
	 * @return the flawEnnc
	 */
	public String getFlawEnnc() {
		return flawEnnc;
	}
	/**
	 * @param flawEnnc the flawEnnc to set
	 */
	public void setFlawEnnc(String flawEnnc) {
		this.flawEnnc = flawEnnc;
	}
	/**
	 * @return the flawExamResult
	 */
	public String getFlawExamResult() {
		return flawExamResult;
	}
	/**
	 * @param flawExamResult the flawExamResult to set
	 */
	public void setFlawExamResult(String flawExamResult) {
		this.flawExamResult = flawExamResult;
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
	 * @return the flawRprSeq
	 */
	public String getFlawRprSeq() {
		return flawRprSeq;
	}
	/**
	 * @param flawRprSeq the flawRprSeq to set
	 */
	public void setFlawRprSeq(String flawRprSeq) {
		this.flawRprSeq = flawRprSeq;
	}
	/**
	 * @return the flawExamUsr
	 */
	public String getFlawExamUsr() {
		return flawExamUsr;
	}
	/**
	 * @param flawExamUsr the flawExamUsr to set
	 */
	public void setFlawExamUsr(String flawExamUsr) {
		this.flawExamUsr = flawExamUsr;
	}
	/**
	 * @return the flawExamComptYn
	 */
	public String getFlawExamComptYn() {
		return flawExamComptYn;
	}
	/**
	 * @param flawExamComptYn the flawExamComptYn to set
	 */
	public void setFlawExamComptYn(String flawExamComptYn) {
		this.flawExamComptYn = flawExamComptYn;
	}
	/**
	 * @return the mntnRprSe
	 */
	public String getMntnRprSe() {
		return mntnRprSe;
	}
	/**
	 * @param mntnRprSe the mntnRprSe to set
	 */
	public void setMntnRprSe(String mntnRprSe) {
		this.mntnRprSe = mntnRprSe;
	}
	/**
	 * @return the mntnRprPart
	 */
	public String getMntnRprPart() {
		return mntnRprPart;
	}
	/**
	 * @param mntnRprPart the mntnRprPart to set
	 */
	public void setMntnRprPart(String mntnRprPart) {
		this.mntnRprPart = mntnRprPart;
	}
	/**
	 * @return the mntnRprCn
	 */
	public String getMntnRprCn() {
		return mntnRprCn;
	}
	/**
	 * @param mntnRprCn the mntnRprCn to set
	 */
	public void setMntnRprCn(String mntnRprCn) {
		this.mntnRprCn = mntnRprCn;
	}
	/**
	 * @return the mntnRprBdgt
	 */
	public String getMntnRprBdgt() {
		return mntnRprBdgt;
	}
	/**
	 * @param mntnRprBdgt the mntnRprBdgt to set
	 */
	public void setMntnRprBdgt(String mntnRprBdgt) {
		this.mntnRprBdgt = mntnRprBdgt;
	}
	/**
	 * @return the mntnRprCnstNm
	 */
	public String getMntnRprCnstNm() {
		return mntnRprCnstNm;
	}
	/**
	 * @param mntnRprCnstNm the mntnRprCnstNm to set
	 */
	public void setMntnRprCnstNm(String mntnRprCnstNm) {
		this.mntnRprCnstNm = mntnRprCnstNm;
	}
	/**
	 * @return the mntnRprCnstStartDt
	 */
	public String getMntnRprCnstStartDt() {
		return mntnRprCnstStartDt;
	}
	/**
	 * @param mntnRprCnstStartDt the mntnRprCnstStartDt to set
	 */
	public void setMntnRprCnstStartDt(String mntnRprCnstStartDt) {
		this.mntnRprCnstStartDt = mntnRprCnstStartDt;
	}
	/**
	 * @return the mntnRprCnstEndDt
	 */
	public String getMntnRprCnstEndDt() {
		return mntnRprCnstEndDt;
	}
	/**
	 * @param mntnRprCnstEndDt the mntnRprCnstEndDt to set
	 */
	public void setMntnRprCnstEndDt(String mntnRprCnstEndDt) {
		this.mntnRprCnstEndDt = mntnRprCnstEndDt;
	}
	/**
	 * @return the mntnRprCnstAmt
	 */
	public String getMntnRprCnstAmt() {
		return mntnRprCnstAmt;
	}
	/**
	 * @param mntnRprCnstAmt the mntnRprCnstAmt to set
	 */
	public void setMntnRprCnstAmt(String mntnRprCnstAmt) {
		this.mntnRprCnstAmt = mntnRprCnstAmt;
	}
	/**
	 * @return the plannerNm
	 */
	public String getPlannerNm() {
		return plannerNm;
	}
	/**
	 * @param plannerNm the plannerNm to set
	 */
	public void setPlannerNm(String plannerNm) {
		this.plannerNm = plannerNm;
	}
	/**
	 * @return the cnstrtr
	 */
	public String getCnstrtr() {
		return cnstrtr;
	}
	/**
	 * @param cnstrtr the cnstrtr to set
	 */
	public void setCnstrtr(String cnstrtr) {
		this.cnstrtr = cnstrtr;
	}
	/**
	 * @return the responEngineer
	 */
	public String getResponEngineer() {
		return responEngineer;
	}
	/**
	 * @param responEngineer the responEngineer to set
	 */
	public void setResponEngineer(String responEngineer) {
		this.responEngineer = responEngineer;
	}
	/**
	 * @return the cnstChargNm
	 */
	public String getCnstChargNm() {
		return cnstChargNm;
	}
	/**
	 * @param cnstChargNm the cnstChargNm to set
	 */
	public void setCnstChargNm(String cnstChargNm) {
		this.cnstChargNm = cnstChargNm;
	}
	/**
	 * @return the mntnRprCnstMth
	 */
	public String getMntnRprCnstMth() {
		return mntnRprCnstMth;
	}
	/**
	 * @param mntnRprCnstMth the mntnRprCnstMth to set
	 */
	public void setMntnRprCnstMth(String mntnRprCnstMth) {
		this.mntnRprCnstMth = mntnRprCnstMth;
	}
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @return the qy
	 */
	public String getQy() {
		return qy;
	}
	/**
	 * @param qy the qy to set
	 */
	public void setQy(String qy) {
		this.qy = qy;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
