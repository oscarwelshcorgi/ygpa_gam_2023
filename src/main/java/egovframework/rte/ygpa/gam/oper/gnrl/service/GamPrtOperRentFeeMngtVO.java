package egovframework.rte.ygpa.gam.oper.gnrl.service;

import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;

/**
 * @Class Name : GamPrtOperRentFeeMngtVO.java
 * @Description : 항만시설사용료관리 (항만시설/일반부두/항만시설사용료관리)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamPrtOperRentFeeMngtVO extends ErpCmmnCdDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /**  */
    private java.lang.String assetCls;
    
    /**  */
    private java.lang.String prprtyCd;
    
    /**  */
    private java.lang.String acqDateFrom;
    
    /**  */
    private java.lang.String itemName;

    /** 항코드 */
    private java.lang.String prtAtCode;
    
    /** 관리 년도 */
    private java.lang.String mngYear;
    
    /** 관리 번호 */
    private java.lang.String mngNo;
    
    /** 관리 횟수 */
    private java.lang.String mngCnt;
    
    /** 업체코드 */
    private java.lang.String entrpscd;
    
    /** 날짜 */
    private java.lang.String dt;
    
    /** 신청 구분 코드 */
    private java.lang.String reqstSeCd;
    
    /** 총 면적 */
    private java.lang.String grAr;
    
    /** 총 사용료 */
    private java.lang.String grFee;
    
    /** 고지 방법 */
    private java.lang.String nticMth;
    
    /** 최초 허가 일자 */
    private java.lang.String frstPrmisnDt;
    
    /** 허가 일자 */
    private java.lang.String prmisnDt;
    
    /** 허가 여부 */
    private java.lang.String prmisnYn;
    
    /** 총 사용 기간 FROM */
    private java.lang.String grUsagePdFrom;
    
    /** 총 사용 기간 TO */
    private java.lang.String grUsagePdTo;
    
    /** 문서 번호 */
    private java.lang.String docNo;
    
    /** 비고 */
    private java.lang.String rm;
    
    /** 코멘트 */
    private java.lang.String cmt;
    
    /** 기타 */
    private java.lang.String etc;
    
    /** 등록자 */
    private java.lang.String regUsr;
    
    /** 등록일시 */
    private java.lang.String registDt;
    
    /** 수정자 */
    private java.lang.String updUsr;
    
    /** 수정일시 */
    private java.lang.String updtDt;
    
    /** 총 감면 사용료 */
    private java.lang.String grRdcxptFee;
    
    /** GIS 코드 */
    private java.lang.String gisCd;
    
    /** 부서코드 */
    private java.lang.String deptcd;
    
    //----------------
    
    /** 자산 사용 순번 */
    private java.lang.String assetsUsageSeq;
    
    /** GIS 자산 코드 */
    private java.lang.String gisAssetsCd;
    
    /** GIS 자산 SUB 코드 */
    private java.lang.String gisAssetsSubCd;
    
    /** 사용 면적 */
    private java.lang.String usageAr;
    
    /** 사용 기간 FROM */
    private java.lang.String usagePdFrom;
    
    /** 사용 기간 TO */
    private java.lang.String usagePdTo;
    
    /** 사용 목적 */
    private java.lang.String usagePurps;
    
    /** 사용 내역 */
    private java.lang.String usageDtls;
    
    /** 사용 용도 코드 */
    private java.lang.String usagePrposCd;
    
    /** 면제 구분 */
    private java.lang.String exemptSe;
    
    /** 면제 사유 코드 */
    private java.lang.String exemptRsnCd;
    
    /** 면제 사유 */
    private java.lang.String exemptRsn;
    
    /** 면제 기간 FROM */
    private java.lang.String exemptPdFrom;
    
    /** 면제 기간 TO */
    private java.lang.String exemptPdTo;
    
    /** 산출 내역 */
    private java.lang.String computDtls;
    
    /** 공시지가 */
    private java.lang.String olnlp;
    
    /** 적용 요율 */
    private java.lang.String applcTariff;
    
    /** 적용 방법 */
    private java.lang.String applcMth;
    
    /** 포장 구분 */
    private java.lang.String packSe;
    
    /** 업체 구분 */
    private java.lang.String entrpsSe;
    
    /** 사용료 계산 구분 */
    private java.lang.String feeCalcSe;
    
    /** 감면 사용료 계산 구분 */
    private java.lang.String rdcxptFeeCalcSe;
    
    /** 감면 사용료 */
    private java.lang.String rdcxptFee;
    
    /** 사용료 */
    private java.lang.String fee;
    
    /** 해지 일자 */
    private java.lang.String trmnatDt;
    
    /** 해지 사유 */
    private java.lang.String trmnatRsn;
    
    /*
    private java.lang.String gisCd;
    private java.lang.String regUsr;
    private java.lang.String registDt;
    private java.lang.String updUsr;
    private java.lang.String updtDt;
    private java.lang.String prtAtCode;
    */
    
    /** GIS 자산 항코드 */
    private java.lang.String gisAssetsPrtAtCode;
    
    /*
    private java.lang.String mngYear;
    private java.lang.String mngNo;
    private java.lang.String mngCnt;
    */

	/**
	 * @return the assetCls
	 */
	public java.lang.String getAssetCls() {
		return assetCls;
	}

	/**
	 * @param assetCls the assetCls to set
	 */
	public void setAssetCls(java.lang.String assetCls) {
		this.assetCls = assetCls;
	}

	/**
	 * @return the prprtyCd
	 */
	public java.lang.String getPrprtyCd() {
		return prprtyCd;
	}

	/**
	 * @param prprtyCd the prprtyCd to set
	 */
	public void setPrprtyCd(java.lang.String prprtyCd) {
		this.prprtyCd = prprtyCd;
	}

	/**
	 * @return the acqDateFrom
	 */
	public java.lang.String getAcqDateFrom() {
		return acqDateFrom;
	}

	/**
	 * @param acqDateFrom the acqDateFrom to set
	 */
	public void setAcqDateFrom(java.lang.String acqDateFrom) {
		this.acqDateFrom = acqDateFrom;
	}

	/**
	 * @return the itemName
	 */
	public java.lang.String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(java.lang.String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the prtAtCode
	 */
	public java.lang.String getPrtAtCode() {
		return prtAtCode;
	}

	/**
	 * @param prtAtCode the prtAtCode to set
	 */
	public void setPrtAtCode(java.lang.String prtAtCode) {
		this.prtAtCode = prtAtCode;
	}

	/**
	 * @return the mngYear
	 */
	public java.lang.String getMngYear() {
		return mngYear;
	}

	/**
	 * @param mngYear the mngYear to set
	 */
	public void setMngYear(java.lang.String mngYear) {
		this.mngYear = mngYear;
	}

	/**
	 * @return the mngNo
	 */
	public java.lang.String getMngNo() {
		return mngNo;
	}

	/**
	 * @param mngNo the mngNo to set
	 */
	public void setMngNo(java.lang.String mngNo) {
		this.mngNo = mngNo;
	}

	/**
	 * @return the mngCnt
	 */
	public java.lang.String getMngCnt() {
		return mngCnt;
	}

	/**
	 * @param mngCnt the mngCnt to set
	 */
	public void setMngCnt(java.lang.String mngCnt) {
		this.mngCnt = mngCnt;
	}

	/**
	 * @return the entrpscd
	 */
	public java.lang.String getEntrpscd() {
		return entrpscd;
	}

	/**
	 * @param entrpscd the entrpscd to set
	 */
	public void setEntrpscd(java.lang.String entrpscd) {
		this.entrpscd = entrpscd;
	}

	/**
	 * @return the dt
	 */
	public java.lang.String getDt() {
		return dt;
	}

	/**
	 * @param dt the dt to set
	 */
	public void setDt(java.lang.String dt) {
		this.dt = dt;
	}

	/**
	 * @return the reqstSeCd
	 */
	public java.lang.String getReqstSeCd() {
		return reqstSeCd;
	}

	/**
	 * @param reqstSeCd the reqstSeCd to set
	 */
	public void setReqstSeCd(java.lang.String reqstSeCd) {
		this.reqstSeCd = reqstSeCd;
	}

	/**
	 * @return the grAr
	 */
	public java.lang.String getGrAr() {
		return grAr;
	}

	/**
	 * @param grAr the grAr to set
	 */
	public void setGrAr(java.lang.String grAr) {
		this.grAr = grAr;
	}

	/**
	 * @return the grFee
	 */
	public java.lang.String getGrFee() {
		return grFee;
	}

	/**
	 * @param grFee the grFee to set
	 */
	public void setGrFee(java.lang.String grFee) {
		this.grFee = grFee;
	}

	/**
	 * @return the nticMth
	 */
	public java.lang.String getNticMth() {
		return nticMth;
	}

	/**
	 * @param nticMth the nticMth to set
	 */
	public void setNticMth(java.lang.String nticMth) {
		this.nticMth = nticMth;
	}

	/**
	 * @return the frstPrmisnDt
	 */
	public java.lang.String getFrstPrmisnDt() {
		return frstPrmisnDt;
	}

	/**
	 * @param frstPrmisnDt the frstPrmisnDt to set
	 */
	public void setFrstPrmisnDt(java.lang.String frstPrmisnDt) {
		this.frstPrmisnDt = frstPrmisnDt;
	}

	/**
	 * @return the prmisnDt
	 */
	public java.lang.String getPrmisnDt() {
		return prmisnDt;
	}

	/**
	 * @param prmisnDt the prmisnDt to set
	 */
	public void setPrmisnDt(java.lang.String prmisnDt) {
		this.prmisnDt = prmisnDt;
	}

	/**
	 * @return the prmisnYn
	 */
	public java.lang.String getPrmisnYn() {
		return prmisnYn;
	}

	/**
	 * @param prmisnYn the prmisnYn to set
	 */
	public void setPrmisnYn(java.lang.String prmisnYn) {
		this.prmisnYn = prmisnYn;
	}

	/**
	 * @return the grUsagePdFrom
	 */
	public java.lang.String getGrUsagePdFrom() {
		return grUsagePdFrom;
	}

	/**
	 * @param grUsagePdFrom the grUsagePdFrom to set
	 */
	public void setGrUsagePdFrom(java.lang.String grUsagePdFrom) {
		this.grUsagePdFrom = grUsagePdFrom;
	}

	/**
	 * @return the grUsagePdTo
	 */
	public java.lang.String getGrUsagePdTo() {
		return grUsagePdTo;
	}

	/**
	 * @param grUsagePdTo the grUsagePdTo to set
	 */
	public void setGrUsagePdTo(java.lang.String grUsagePdTo) {
		this.grUsagePdTo = grUsagePdTo;
	}

	/**
	 * @return the docNo
	 */
	public java.lang.String getDocNo() {
		return docNo;
	}

	/**
	 * @param docNo the docNo to set
	 */
	public void setDocNo(java.lang.String docNo) {
		this.docNo = docNo;
	}

	/**
	 * @return the rm
	 */
	public java.lang.String getRm() {
		return rm;
	}

	/**
	 * @param rm the rm to set
	 */
	public void setRm(java.lang.String rm) {
		this.rm = rm;
	}

	/**
	 * @return the cmt
	 */
	public java.lang.String getCmt() {
		return cmt;
	}

	/**
	 * @param cmt the cmt to set
	 */
	public void setCmt(java.lang.String cmt) {
		this.cmt = cmt;
	}

	/**
	 * @return the etc
	 */
	public java.lang.String getEtc() {
		return etc;
	}

	/**
	 * @param etc the etc to set
	 */
	public void setEtc(java.lang.String etc) {
		this.etc = etc;
	}

	/**
	 * @return the regUsr
	 */
	public java.lang.String getRegUsr() {
		return regUsr;
	}

	/**
	 * @param regUsr the regUsr to set
	 */
	public void setRegUsr(java.lang.String regUsr) {
		this.regUsr = regUsr;
	}

	/**
	 * @return the registDt
	 */
	public java.lang.String getRegistDt() {
		return registDt;
	}

	/**
	 * @param registDt the registDt to set
	 */
	public void setRegistDt(java.lang.String registDt) {
		this.registDt = registDt;
	}

	/**
	 * @return the updUsr
	 */
	public java.lang.String getUpdUsr() {
		return updUsr;
	}

	/**
	 * @param updUsr the updUsr to set
	 */
	public void setUpdUsr(java.lang.String updUsr) {
		this.updUsr = updUsr;
	}

	/**
	 * @return the updtDt
	 */
	public java.lang.String getUpdtDt() {
		return updtDt;
	}

	/**
	 * @param updtDt the updtDt to set
	 */
	public void setUpdtDt(java.lang.String updtDt) {
		this.updtDt = updtDt;
	}

	/**
	 * @return the grRdcxptFee
	 */
	public java.lang.String getGrRdcxptFee() {
		return grRdcxptFee;
	}

	/**
	 * @param grRdcxptFee the grRdcxptFee to set
	 */
	public void setGrRdcxptFee(java.lang.String grRdcxptFee) {
		this.grRdcxptFee = grRdcxptFee;
	}

	/**
	 * @return the gisCd
	 */
	public java.lang.String getGisCd() {
		return gisCd;
	}

	/**
	 * @param gisCd the gisCd to set
	 */
	public void setGisCd(java.lang.String gisCd) {
		this.gisCd = gisCd;
	}

	/**
	 * @return the deptcd
	 */
	public java.lang.String getDeptcd() {
		return deptcd;
	}

	/**
	 * @param deptcd the deptcd to set
	 */
	public void setDeptcd(java.lang.String deptcd) {
		this.deptcd = deptcd;
	}

	/**
	 * @return the assetsUsageSeq
	 */
	public java.lang.String getAssetsUsageSeq() {
		return assetsUsageSeq;
	}

	/**
	 * @param assetsUsageSeq the assetsUsageSeq to set
	 */
	public void setAssetsUsageSeq(java.lang.String assetsUsageSeq) {
		this.assetsUsageSeq = assetsUsageSeq;
	}

	/**
	 * @return the gisAssetsCd
	 */
	public java.lang.String getGisAssetsCd() {
		return gisAssetsCd;
	}

	/**
	 * @param gisAssetsCd the gisAssetsCd to set
	 */
	public void setGisAssetsCd(java.lang.String gisAssetsCd) {
		this.gisAssetsCd = gisAssetsCd;
	}

	/**
	 * @return the gisAssetsSubCd
	 */
	public java.lang.String getGisAssetsSubCd() {
		return gisAssetsSubCd;
	}

	/**
	 * @param gisAssetsSubCd the gisAssetsSubCd to set
	 */
	public void setGisAssetsSubCd(java.lang.String gisAssetsSubCd) {
		this.gisAssetsSubCd = gisAssetsSubCd;
	}

	/**
	 * @return the usageAr
	 */
	public java.lang.String getUsageAr() {
		return usageAr;
	}

	/**
	 * @param usageAr the usageAr to set
	 */
	public void setUsageAr(java.lang.String usageAr) {
		this.usageAr = usageAr;
	}

	/**
	 * @return the usagePdFrom
	 */
	public java.lang.String getUsagePdFrom() {
		return usagePdFrom;
	}

	/**
	 * @param usagePdFrom the usagePdFrom to set
	 */
	public void setUsagePdFrom(java.lang.String usagePdFrom) {
		this.usagePdFrom = usagePdFrom;
	}

	/**
	 * @return the usagePdTo
	 */
	public java.lang.String getUsagePdTo() {
		return usagePdTo;
	}

	/**
	 * @param usagePdTo the usagePdTo to set
	 */
	public void setUsagePdTo(java.lang.String usagePdTo) {
		this.usagePdTo = usagePdTo;
	}

	/**
	 * @return the usagePurps
	 */
	public java.lang.String getUsagePurps() {
		return usagePurps;
	}

	/**
	 * @param usagePurps the usagePurps to set
	 */
	public void setUsagePurps(java.lang.String usagePurps) {
		this.usagePurps = usagePurps;
	}

	/**
	 * @return the usageDtls
	 */
	public java.lang.String getUsageDtls() {
		return usageDtls;
	}

	/**
	 * @param usageDtls the usageDtls to set
	 */
	public void setUsageDtls(java.lang.String usageDtls) {
		this.usageDtls = usageDtls;
	}

	/**
	 * @return the usagePrposCd
	 */
	public java.lang.String getUsagePrposCd() {
		return usagePrposCd;
	}

	/**
	 * @param usagePrposCd the usagePrposCd to set
	 */
	public void setUsagePrposCd(java.lang.String usagePrposCd) {
		this.usagePrposCd = usagePrposCd;
	}

	/**
	 * @return the exemptSe
	 */
	public java.lang.String getExemptSe() {
		return exemptSe;
	}

	/**
	 * @param exemptSe the exemptSe to set
	 */
	public void setExemptSe(java.lang.String exemptSe) {
		this.exemptSe = exemptSe;
	}

	/**
	 * @return the exemptRsnCd
	 */
	public java.lang.String getExemptRsnCd() {
		return exemptRsnCd;
	}

	/**
	 * @param exemptRsnCd the exemptRsnCd to set
	 */
	public void setExemptRsnCd(java.lang.String exemptRsnCd) {
		this.exemptRsnCd = exemptRsnCd;
	}

	/**
	 * @return the exemptRsn
	 */
	public java.lang.String getExemptRsn() {
		return exemptRsn;
	}

	/**
	 * @param exemptRsn the exemptRsn to set
	 */
	public void setExemptRsn(java.lang.String exemptRsn) {
		this.exemptRsn = exemptRsn;
	}

	/**
	 * @return the exemptPdFrom
	 */
	public java.lang.String getExemptPdFrom() {
		return exemptPdFrom;
	}

	/**
	 * @param exemptPdFrom the exemptPdFrom to set
	 */
	public void setExemptPdFrom(java.lang.String exemptPdFrom) {
		this.exemptPdFrom = exemptPdFrom;
	}

	/**
	 * @return the exemptPdTo
	 */
	public java.lang.String getExemptPdTo() {
		return exemptPdTo;
	}

	/**
	 * @param exemptPdTo the exemptPdTo to set
	 */
	public void setExemptPdTo(java.lang.String exemptPdTo) {
		this.exemptPdTo = exemptPdTo;
	}

	/**
	 * @return the computDtls
	 */
	public java.lang.String getComputDtls() {
		return computDtls;
	}

	/**
	 * @param computDtls the computDtls to set
	 */
	public void setComputDtls(java.lang.String computDtls) {
		this.computDtls = computDtls;
	}

	/**
	 * @return the olnlp
	 */
	public java.lang.String getOlnlp() {
		return olnlp;
	}

	/**
	 * @param olnlp the olnlp to set
	 */
	public void setOlnlp(java.lang.String olnlp) {
		this.olnlp = olnlp;
	}

	/**
	 * @return the applcTariff
	 */
	public java.lang.String getApplcTariff() {
		return applcTariff;
	}

	/**
	 * @param applcTariff the applcTariff to set
	 */
	public void setApplcTariff(java.lang.String applcTariff) {
		this.applcTariff = applcTariff;
	}

	/**
	 * @return the applcMth
	 */
	public java.lang.String getApplcMth() {
		return applcMth;
	}

	/**
	 * @param applcMth the applcMth to set
	 */
	public void setApplcMth(java.lang.String applcMth) {
		this.applcMth = applcMth;
	}

	/**
	 * @return the packSe
	 */
	public java.lang.String getPackSe() {
		return packSe;
	}

	/**
	 * @param packSe the packSe to set
	 */
	public void setPackSe(java.lang.String packSe) {
		this.packSe = packSe;
	}

	/**
	 * @return the entrpsSe
	 */
	public java.lang.String getEntrpsSe() {
		return entrpsSe;
	}

	/**
	 * @param entrpsSe the entrpsSe to set
	 */
	public void setEntrpsSe(java.lang.String entrpsSe) {
		this.entrpsSe = entrpsSe;
	}

	/**
	 * @return the feeCalcSe
	 */
	public java.lang.String getFeeCalcSe() {
		return feeCalcSe;
	}

	/**
	 * @param feeCalcSe the feeCalcSe to set
	 */
	public void setFeeCalcSe(java.lang.String feeCalcSe) {
		this.feeCalcSe = feeCalcSe;
	}

	/**
	 * @return the rdcxptFeeCalcSe
	 */
	public java.lang.String getRdcxptFeeCalcSe() {
		return rdcxptFeeCalcSe;
	}

	/**
	 * @param rdcxptFeeCalcSe the rdcxptFeeCalcSe to set
	 */
	public void setRdcxptFeeCalcSe(java.lang.String rdcxptFeeCalcSe) {
		this.rdcxptFeeCalcSe = rdcxptFeeCalcSe;
	}

	/**
	 * @return the rdcxptFee
	 */
	public java.lang.String getRdcxptFee() {
		return rdcxptFee;
	}

	/**
	 * @param rdcxptFee the rdcxptFee to set
	 */
	public void setRdcxptFee(java.lang.String rdcxptFee) {
		this.rdcxptFee = rdcxptFee;
	}

	/**
	 * @return the fee
	 */
	public java.lang.String getFee() {
		return fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(java.lang.String fee) {
		this.fee = fee;
	}

	/**
	 * @return the trmnatDt
	 */
	public java.lang.String getTrmnatDt() {
		return trmnatDt;
	}

	/**
	 * @param trmnatDt the trmnatDt to set
	 */
	public void setTrmnatDt(java.lang.String trmnatDt) {
		this.trmnatDt = trmnatDt;
	}

	/**
	 * @return the trmnatRsn
	 */
	public java.lang.String getTrmnatRsn() {
		return trmnatRsn;
	}

	/**
	 * @param trmnatRsn the trmnatRsn to set
	 */
	public void setTrmnatRsn(java.lang.String trmnatRsn) {
		this.trmnatRsn = trmnatRsn;
	}

	/**
	 * @return the gisAssetsPrtAtCode
	 */
	public java.lang.String getGisAssetsPrtAtCode() {
		return gisAssetsPrtAtCode;
	}

	/**
	 * @param gisAssetsPrtAtCode the gisAssetsPrtAtCode to set
	 */
	public void setGisAssetsPrtAtCode(java.lang.String gisAssetsPrtAtCode) {
		this.gisAssetsPrtAtCode = gisAssetsPrtAtCode;
	}
    
}
