package egovframework.rte.ygpa.gam.oper.cntnr.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamCntnrQuayMtRentFeeSttusInqireVO.java
 * @Description : 컨테이너부두임대월별사용료현황조회 VO class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamCntnrQuayMtRentFeeSttusInqireVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** 사용 년도 */
    private String usageYear;
    
    /** 사용 분기 */
    private String usageQu;
    
    /** 사용 월 */
    private String usageMt;
    
    private String assetsUsageSeq;
    
    private String mngYear;
    
    private String mngNo;
    
    private String mngCnt;
    
    private String prtAtCode;
    
    private String usagePrposCd;
    
    private String reqstEntrpsCd;
    
    private String usageCnd;
    
    private String exemptSe;
    
    private String fee;
    
    private String rdcxptFee;
    
    private String updUsr;
    
    private String updtDt;
    
    private String usagePdFrom;
    
    private String usagePdTo;
    
    private String gisAssetsCd;
    
    private String gisAssetsSubCd;
    
    private String quayCd;
    
    /** 검색 항코드 */
    private String sPrtAtCode;
    
    /** 검색 업체명 */
    private String sEntrpscd;

	/** 검색 자산 코드 */
    private String sAssetsCd;
    
    private String sAssetsSubCd;
    
    /** 검색 년 월*/
    private String sStartYr;
    
    private String sStartMn;
    
    private String sEndYr;
    
    private String sEndMn;
    
    /** 자료수, 합계 */
    private String totSumCnt;
    
    private String totSumFee;

    /** 검색 사용기간 시작 */
    private String sGrUsagePdFrom;
    
    /** 검색 사용기간 종료 */
    private String sGrUsagePdTo;

	/**
	 * @return the usageYear
	 */
	public String getUsageYear() {
		return usageYear;
	}

	/**
	 * @param usageYear the usageYear to set
	 */
	public void setUsageYear(String usageYear) {
		this.usageYear = usageYear;
	}

	/**
	 * @return the usageQu
	 */
	public String getUsageQu() {
		return usageQu;
	}

	/**
	 * @param usageQu the usageQu to set
	 */
	public void setUsageQu(String usageQu) {
		this.usageQu = usageQu;
	}

	/**
	 * @return the usageMt
	 */
	public String getUsageMt() {
		return usageMt;
	}

	/**
	 * @param usageMt the usageMt to set
	 */
	public void setUsageMt(String usageMt) {
		this.usageMt = usageMt;
	}

	/**
	 * @return the assetsUsageSeq
	 */
	public String getAssetsUsageSeq() {
		return assetsUsageSeq;
	}

	/**
	 * @param assetsUsageSeq the assetsUsageSeq to set
	 */
	public void setAssetsUsageSeq(String assetsUsageSeq) {
		this.assetsUsageSeq = assetsUsageSeq;
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
	 * @return the usagePrposCd
	 */
	public String getUsagePrposCd() {
		return usagePrposCd;
	}

	/**
	 * @param usagePrposCd the usagePrposCd to set
	 */
	public void setUsagePrposCd(String usagePrposCd) {
		this.usagePrposCd = usagePrposCd;
	}

	/**
	 * @return the reqstEntrpsCd
	 */
	public String getReqstEntrpsCd() {
		return reqstEntrpsCd;
	}

	/**
	 * @param reqstEntrpsCd the reqstEntrpsCd to set
	 */
	public void setReqstEntrpsCd(String reqstEntrpsCd) {
		this.reqstEntrpsCd = reqstEntrpsCd;
	}

	/**
	 * @return the usageCnd
	 */
	public String getUsageCnd() {
		return usageCnd;
	}

	/**
	 * @param usageCnd the usageCnd to set
	 */
	public void setUsageCnd(String usageCnd) {
		this.usageCnd = usageCnd;
	}

	/**
	 * @return the exemptSe
	 */
	public String getExemptSe() {
		return exemptSe;
	}

	/**
	 * @param exemptSe the exemptSe to set
	 */
	public void setExemptSe(String exemptSe) {
		this.exemptSe = exemptSe;
	}

	/**
	 * @return the fee
	 */
	public String getFee() {
		return fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(String fee) {
		this.fee = fee;
	}

	/**
	 * @return the rdcxptFee
	 */
	public String getRdcxptFee() {
		return rdcxptFee;
	}

	/**
	 * @param rdcxptFee the rdcxptFee to set
	 */
	public void setRdcxptFee(String rdcxptFee) {
		this.rdcxptFee = rdcxptFee;
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
	 * @return the usagePdFrom
	 */
	public String getUsagePdFrom() {
		return usagePdFrom;
	}

	/**
	 * @param usagePdFrom the usagePdFrom to set
	 */
	public void setUsagePdFrom(String usagePdFrom) {
		this.usagePdFrom = usagePdFrom;
	}

	/**
	 * @return the usagePdTo
	 */
	public String getUsagePdTo() {
		return usagePdTo;
	}

	/**
	 * @param usagePdTo the usagePdTo to set
	 */
	public void setUsagePdTo(String usagePdTo) {
		this.usagePdTo = usagePdTo;
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
	 * @return the quayCd
	 */
	public String getQuayCd() {
		return quayCd;
	}

	/**
	 * @param quayCd the quayCd to set
	 */
	public void setQuayCd(String quayCd) {
		this.quayCd = quayCd;
	}

	/**
	 * @return the sPrtAtCode
	 */
	public String getsPrtAtCode() {
		return sPrtAtCode;
	}

	/**
	 * @param sPrtAtCode the sPrtAtCode to set
	 */
	public void setsPrtAtCode(String sPrtAtCode) {
		this.sPrtAtCode = sPrtAtCode;
	}

	/**
	 * @return the sEntrpscd
	 */
	public String getsEntrpscd() {
		return sEntrpscd;
	}

	/**
	 * @param sEntrpscd the sEntrpscd to set
	 */
	public void setsEntrpscd(String sEntrpscd) {
		this.sEntrpscd = sEntrpscd;
	}

	/**
	 * @return the sGrUsagePdFrom
	 */
	public String getsGrUsagePdFrom() {
		return sGrUsagePdFrom;
	}

	/**
	 * @param sGrUsagePdFrom the sGrUsagePdFrom to set
	 */
	public void setsGrUsagePdFrom(String sGrUsagePdFrom) {
		this.sGrUsagePdFrom = sGrUsagePdFrom;
	}

	/**
	 * @return the sGrUsagePdTo
	 */
	public String getsGrUsagePdTo() {
		return sGrUsagePdTo;
	}

	/**
	 * @param sGrUsagePdTo the sGrUsagePdTo to set
	 */
	public void setsGrUsagePdTo(String sGrUsagePdTo) {
		this.sGrUsagePdTo = sGrUsagePdTo;
	}

	/**
	 * @return the sAssetsCd
	 */
	public String getsAssetsCd() {
		return sAssetsCd;
	}

	/**
	 * @param sAssetsCd the sAssetsCd to set
	 */
	public void setsAssetsCd(String sAssetsCd) {
		this.sAssetsCd = sAssetsCd;
	}

	/**
	 * @return the sAssetsSubCd
	 */
	public String getsAssetsSubCd() {
		return sAssetsSubCd;
	}

	/**
	 * @param sAssetsSubCd the sAssetsSubCd to set
	 */
	public void setsAssetsSubCd(String sAssetsSubCd) {
		this.sAssetsSubCd = sAssetsSubCd;
	}

	/**
	 * @return the sStartYr
	 */
	public String getsStartYr() {
		return sStartYr;
	}

	/**
	 * @param sStartYr the sStartYr to set
	 */
	public void setsStartYr(String sStartYr) {
		this.sStartYr = sStartYr;
	}

	/**
	 * @return the sStartMn
	 */
	public String getsStartMn() {
		return sStartMn;
	}

	/**
	 * @param sStartMn the sStartMn to set
	 */
	public void setsStartMn(String sStartMn) {
		this.sStartMn = sStartMn;
	}

	/**
	 * @return the sEndYr
	 */
	public String getsEndYr() {
		return sEndYr;
	}

	/**
	 * @param sEndYr the sEndYr to set
	 */
	public void setsEndYr(String sEndYr) {
		this.sEndYr = sEndYr;
	}

	/**
	 * @return the sEndMn
	 */
	public String getsEndMn() {
		return sEndMn;
	}

	/**
	 * @param sEndMn the sEndMn to set
	 */
	public void setsEndMn(String sEndMn) {
		this.sEndMn = sEndMn;
	}

	/**
	 * @return the totSumCnt
	 */
	public String getTotSumCnt() {
		return totSumCnt;
	}

	/**
	 * @param totSumCnt the totSumCnt to set
	 */
	public void setTotSumCnt(String totSumCnt) {
		this.totSumCnt = totSumCnt;
	}

	/**
	 * @return the totSumFee
	 */
	public String getTotSumFee() {
		return totSumFee;
	}

	/**
	 * @param totSumFee the totSumFee to set
	 */
	public void setTotSumFee(String totSumFee) {
		this.totSumFee = totSumFee;
	}
    
}
