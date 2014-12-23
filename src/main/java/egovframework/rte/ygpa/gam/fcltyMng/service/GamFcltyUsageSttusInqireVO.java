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

	/** 사용기간 (조회조건) **/
	private String sUsagePdFrom;
	private String sUsagePdTo;

// ==== 자산 임대 ==== //
	private String GisAssets;
	private String PrtFcltySe;
	private String PrtFcltyNm;
	private String PrtFcltyDtndrd;
	private String PrtFcltyUnit;
	private String PrtFcltyInstlDt;
	private String PrtFcltyChangeDt;
	private String PrtFcltyMngEntrps;
	private String PrtFcltyExprDt;
	private String PrtPrtFcltyCnt;
	private String PrtPrtFcltyMnger;
	private String FcltsMngGroupNo;

// ==== 자산 임대 ==== //
	/** 항코드 **/
	private String prtAtCode;

	/** 관리번호 **/
	private String mngYearNo;

	/** GIS 코드 **/
	private String gisAssets;

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

	/**
	 * @return the sPrtAtCode
	 */
	public String getsPrtAtCode() {
		return sPrtAtCode;
	}

	/**
	 * @return the prtFcltySe
	 */
	public String getPrtFcltySe() {
		return PrtFcltySe;
	}

	/**
	 * @param prtFcltySe the prtFcltySe to set
	 */
	public void setPrtFcltySe(String prtFcltySe) {
		PrtFcltySe = prtFcltySe;
	}

	/**
	 * @return the prtFcltyNm
	 */
	public String getPrtFcltyNm() {
		return PrtFcltyNm;
	}

	/**
	 * @param prtFcltyNm the prtFcltyNm to set
	 */
	public void setPrtFcltyNm(String prtFcltyNm) {
		PrtFcltyNm = prtFcltyNm;
	}

	/**
	 * @return the prtFcltyDtndrd
	 */
	public String getPrtFcltyDtndrd() {
		return PrtFcltyDtndrd;
	}

	/**
	 * @param prtFcltyDtndrd the prtFcltyDtndrd to set
	 */
	public void setPrtFcltyDtndrd(String prtFcltyDtndrd) {
		PrtFcltyDtndrd = prtFcltyDtndrd;
	}

	/**
	 * @return the prtFcltyUnit
	 */
	public String getPrtFcltyUnit() {
		return PrtFcltyUnit;
	}

	/**
	 * @param prtFcltyUnit the prtFcltyUnit to set
	 */
	public void setPrtFcltyUnit(String prtFcltyUnit) {
		PrtFcltyUnit = prtFcltyUnit;
	}

	/**
	 * @return the prtFcltyInstlDt
	 */
	public String getPrtFcltyInstlDt() {
		return PrtFcltyInstlDt;
	}

	/**
	 * @param prtFcltyInstlDt the prtFcltyInstlDt to set
	 */
	public void setPrtFcltyInstlDt(String prtFcltyInstlDt) {
		PrtFcltyInstlDt = prtFcltyInstlDt;
	}

	/**
	 * @return the prtFcltyChangeDt
	 */
	public String getPrtFcltyChangeDt() {
		return PrtFcltyChangeDt;
	}

	/**
	 * @param prtFcltyChangeDt the prtFcltyChangeDt to set
	 */
	public void setPrtFcltyChangeDt(String prtFcltyChangeDt) {
		PrtFcltyChangeDt = prtFcltyChangeDt;
	}

	/**
	 * @return the prtFcltyMngEntrps
	 */
	public String getPrtFcltyMngEntrps() {
		return PrtFcltyMngEntrps;
	}

	/**
	 * @param prtFcltyMngEntrps the prtFcltyMngEntrps to set
	 */
	public void setPrtFcltyMngEntrps(String prtFcltyMngEntrps) {
		PrtFcltyMngEntrps = prtFcltyMngEntrps;
	}

	/**
	 * @return the prtFcltyExprDt
	 */
	public String getPrtFcltyExprDt() {
		return PrtFcltyExprDt;
	}

	/**
	 * @param prtFcltyExprDt the prtFcltyExprDt to set
	 */
	public void setPrtFcltyExprDt(String prtFcltyExprDt) {
		PrtFcltyExprDt = prtFcltyExprDt;
	}

	/**
	 * @return the prtPrtFcltyCnt
	 */
	public String getPrtPrtFcltyCnt() {
		return PrtPrtFcltyCnt;
	}

	/**
	 * @param prtPrtFcltyCnt the prtPrtFcltyCnt to set
	 */
	public void setPrtPrtFcltyCnt(String prtPrtFcltyCnt) {
		PrtPrtFcltyCnt = prtPrtFcltyCnt;
	}

	/**
	 * @return the prtPrtFcltyMnger
	 */
	public String getPrtPrtFcltyMnger() {
		return PrtPrtFcltyMnger;
	}

	/**
	 * @param prtPrtFcltyMnger the prtPrtFcltyMnger to set
	 */
	public void setPrtPrtFcltyMnger(String prtPrtFcltyMnger) {
		PrtPrtFcltyMnger = prtPrtFcltyMnger;
	}

	/**
	 * @return the fcltsMngGroupNo
	 */
	public String getFcltsMngGroupNo() {
		return FcltsMngGroupNo;
	}

	/**
	 * @param fcltsMngGroupNo the fcltsMngGroupNo to set
	 */
	public void setFcltsMngGroupNo(String fcltsMngGroupNo) {
		FcltsMngGroupNo = fcltsMngGroupNo;
	}

	/**
	 * @param sPrtAtCode the sPrtAtCode to set
	 */
	public void setsPrtAtCode(String sPrtAtCode) {
		this.sPrtAtCode = sPrtAtCode;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
