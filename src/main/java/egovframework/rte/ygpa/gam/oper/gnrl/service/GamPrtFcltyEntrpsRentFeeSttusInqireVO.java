package egovframework.rte.ygpa.gam.oper.gnrl.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamPrtFcltyEntrpsRentFeeSttusInqireVO.java
 * @Description : 항만시설업체별사용료현황조회 VO class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamPrtFcltyEntrpsRentFeeSttusInqireVO extends ComDefaultVO {
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
    
    /** 디스플레이 자료수 */
    private String dpTotCnt;
    
    /** 전체 사용료합계 */
    private String sumTotalFeeSum;

    
    /** 검색시작년 */
    private String serchStartYr;
    
    /** 검색시작월 */
    private String serchStartMn;
    
    /** 검색종료년 */
    private String serchEndYr;
    
    /** 검색종료월 */
    private String serchEndMn;

    /** 자산코드 검색 */
    private String searchAssetsCd;
    
    /** 자산부코드 검색 */
    private String searchAssetsSubCd;
    
    

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
	 * @return the dpTotCnt
	 */
	public String getDpTotCnt() {
		return dpTotCnt;
	}

	/**
	 * @param dpTotCnt the dpTotCnt to set
	 */
	public void setDpTotCnt(String dpTotCnt) {
		this.dpTotCnt = dpTotCnt;
	}

	/**
	 * @return the sumTotalFeeSum
	 */
	public String getSumTotalFeeSum() {
		return sumTotalFeeSum;
	}

	/**
	 * @param sumTotalFeeSum the sumTotalFeeSum to set
	 */
	public void setSumTotalFeeSum(String sumTotalFeeSum) {
		this.sumTotalFeeSum = sumTotalFeeSum;
	}

	/**
	 * @return the serchStartYr
	 */
	public String getSerchStartYr() {
		return serchStartYr;
	}

	/**
	 * @param serchStartYr the serchStartYr to set
	 */
	public void setSerchStartYr(String serchStartYr) {
		this.serchStartYr = serchStartYr;
	}

	/**
	 * @return the serchStartMn
	 */
	public String getSerchStartMn() {
		return serchStartMn;
	}

	/**
	 * @param serchStartMn the serchStartMn to set
	 */
	public void setSerchStartMn(String serchStartMn) {
		if(Integer.parseInt(serchStartMn) < 10){
			serchStartMn = "0" + serchStartMn;
		}
		this.serchStartMn = serchStartMn;
	}

	/**
	 * @return the serchEndYr
	 */
	public String getSerchEndYr() {
		return serchEndYr;
	}

	/**
	 * @param serchEndYr the serchEndYr to set
	 */
	public void setSerchEndYr(String serchEndYr) {
		this.serchEndYr = serchEndYr;
	}

	/**
	 * @return the serchEndMn
	 */
	public String getSerchEndMn() {
		return serchEndMn;
	}

	/**
	 * @param serchEndMn the serchEndMn to set
	 */
	public void setSerchEndMn(String serchEndMn) {
		if(Integer.parseInt(serchEndMn) < 10){
			serchEndMn = "0" + serchEndMn;
		}
		this.serchEndMn = serchEndMn;
	}

	/**
	 * @return the searchAssetsCd
	 */
	public String getSearchAssetsCd() {
		return searchAssetsCd;
	}

	/**
	 * @param searchAssetsCd the searchAssetsCd to set
	 */
	public void setSearchAssetsCd(String searchAssetsCd) {
		this.searchAssetsCd = searchAssetsCd;
	}

	/**
	 * @return the searchAssetsSubCd
	 */
	public String getSearchAssetsSubCd() {
		return searchAssetsSubCd;
	}

	/**
	 * @param searchAssetsSubCd the searchAssetsSubCd to set
	 */
	public void setSearchAssetsSubCd(String searchAssetsSubCd) {
		this.searchAssetsSubCd = searchAssetsSubCd;
	}

	
    
}
