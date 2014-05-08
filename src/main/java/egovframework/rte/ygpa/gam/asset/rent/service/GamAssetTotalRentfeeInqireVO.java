package egovframework.rte.ygpa.gam.asset.rent.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamAssetTotalRentfeeInqireVO.java
 * @Description : 자산별사용료현황조회 VO class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamAssetTotalRentfeeInqireVO extends ComDefaultVO {
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
    
    private String usagePrposCdNm;

    private String reqstEntrpsCd;
    
    private String reqstEntrpsCdNm;

    private String usageCnd;
    
    private String usageCndNm;

    private String exemptSe;
    
    private String exemptSeNm;

    private String fee;
    
    private String rdcxptFee;
    
    private String prtAtCodeNm;
    
    private String rentMngNo;
    
    private String usagePdFrom;
    
    private String usagePdTo;
    
    private String gisAssetsCd;
    
    private String gisAssetsSubCd;

    private String rentGisAssetsCd;
    
    private String gisAssetsNm;

    private String quayCd;
    
    private String quayCdNm;

    /** 검색 항코드 */
    private String sPrtAtCode;
    
    /** 검색 업체명 */
    private String sEntrpscd;
    
    /** 검색 사용기간 시작 */
    private String sGrUsagePdFrom;
    
    /** 검색 사용기간 종료 */
    private String sGrUsagePdTo;
    
    private String sUsageCnd;
    
    private String sQuayCd;
    
    private String sGisAssetsCd;
    
    private String sGisAssetsSubCd;
    
    
    /** 사용료 합계 */
    private String sumFee;
    
    /** 디스플레이 자료수 */
    private String dpTotCnt;
    
    /** 감면사용료 합계 */
    private String sumRdcxptFee;
    
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
	 * @return the usagePrposCdNm
	 */
	public String getUsagePrposCdNm() {
		return usagePrposCdNm;
	}

	/**
	 * @param usagePrposCdNm the usagePrposCdNm to set
	 */
	public void setUsagePrposCdNm(String usagePrposCdNm) {
		this.usagePrposCdNm = usagePrposCdNm;
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
	 * @return the reqstEntrpsCdNm
	 */
	public String getReqstEntrpsCdNm() {
		return reqstEntrpsCdNm;
	}

	/**
	 * @param reqstEntrpsCdNm the reqstEntrpsCdNm to set
	 */
	public void setReqstEntrpsCdNm(String reqstEntrpsCdNm) {
		this.reqstEntrpsCdNm = reqstEntrpsCdNm;
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
	 * @return the usageCndNm
	 */
	public String getUsageCndNm() {
		return usageCndNm;
	}

	/**
	 * @param usageCndNm the usageCndNm to set
	 */
	public void setUsageCndNm(String usageCndNm) {
		this.usageCndNm = usageCndNm;
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
	 * @return the exemptSeNm
	 */
	public String getExemptSeNm() {
		return exemptSeNm;
	}

	/**
	 * @param exemptSeNm the exemptSeNm to set
	 */
	public void setExemptSeNm(String exemptSeNm) {
		this.exemptSeNm = exemptSeNm;
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
	 * @return the prtAtCodeNm
	 */
	public String getPrtAtCodeNm() {
		return prtAtCodeNm;
	}

	/**
	 * @param prtAtCodeNm the prtAtCodeNm to set
	 */
	public void setPrtAtCodeNm(String prtAtCodeNm) {
		this.prtAtCodeNm = prtAtCodeNm;
	}

	/**
	 * @return the rentMngNo
	 */
	public String getRentMngNo() {
		return rentMngNo;
	}

	/**
	 * @param rentMngNo the rentMngNo to set
	 */
	public void setRentMngNo(String rentMngNo) {
		this.rentMngNo = rentMngNo;
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
	 * @return the rentGisAssetsCd
	 */
	public String getRentGisAssetsCd() {
		return rentGisAssetsCd;
	}

	/**
	 * @param rentGisAssetsCd the rentGisAssetsCd to set
	 */
	public void setRentGisAssetsCd(String rentGisAssetsCd) {
		this.rentGisAssetsCd = rentGisAssetsCd;
	}

	/**
	 * @return the gisAssetsNm
	 */
	public String getGisAssetsNm() {
		return gisAssetsNm;
	}

	/**
	 * @param gisAssetsNm the gisAssetsNm to set
	 */
	public void setGisAssetsNm(String gisAssetsNm) {
		this.gisAssetsNm = gisAssetsNm;
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
	 * @return the quayCdNm
	 */
	public String getQuayCdNm() {
		return quayCdNm;
	}

	/**
	 * @param quayCdNm the quayCdNm to set
	 */
	public void setQuayCdNm(String quayCdNm) {
		this.quayCdNm = quayCdNm;
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
	 * @return the sUsageCnd
	 */
	public String getsUsageCnd() {
		return sUsageCnd;
	}

	/**
	 * @param sUsageCnd the sUsageCnd to set
	 */
	public void setsUsageCnd(String sUsageCnd) {
		this.sUsageCnd = sUsageCnd;
	}

	/**
	 * @return the sQuayCd
	 */
	public String getsQuayCd() {
		return sQuayCd;
	}

	/**
	 * @param sQuayCd the sQuayCd to set
	 */
	public void setsQuayCd(String sQuayCd) {
		this.sQuayCd = sQuayCd;
	}

	/**
	 * @return the sGisAssetsCd
	 */
	public String getsGisAssetsCd() {
		return sGisAssetsCd;
	}

	/**
	 * @param sGisAssetsCd the sGisAssetsCd to set
	 */
	public void setsGisAssetsCd(String sGisAssetsCd) {
		this.sGisAssetsCd = sGisAssetsCd;
	}

	/**
	 * @return the sGisAssetsSubCd
	 */
	public String getsGisAssetsSubCd() {
		return sGisAssetsSubCd;
	}

	/**
	 * @param sGisAssetsSubCd the sGisAssetsSubCd to set
	 */
	public void setsGisAssetsSubCd(String sGisAssetsSubCd) {
		this.sGisAssetsSubCd = sGisAssetsSubCd;
	}

	/**
	 * @return the sumFee
	 */
	public String getSumFee() {
		return sumFee;
	}

	/**
	 * @param sumFee the sumFee to set
	 */
	public void setSumFee(String sumFee) {
		this.sumFee = sumFee;
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
	 * @return the sumRdcxptFee
	 */
	public String getSumRdcxptFee() {
		return sumRdcxptFee;
	}

	/**
	 * @param sumRdcxptFee the sumRdcxptFee to set
	 */
	public void setSumRdcxptFee(String sumRdcxptFee) {
		this.sumRdcxptFee = sumRdcxptFee;
	}

}
