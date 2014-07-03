package egovframework.rte.ygpa.gam.oper.cntnr.service;

import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;

/**
 * @Class Name : GamCntnrQuayRentMngtDetailVO.java
 * @Description : 컨테이너부두사용목록관리 상세 VO class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamCntnrQuayRentMngtDetailVO extends ErpCmmnCdDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** 자산 사용 순번 */                                               
    private String assetsUsageSeq;                         
                                                       
    /** GIS 자산 코드 */                                                   
    private String gisAssetsCd;                           
                                                            
    /** GIS 자산 SUB 코드 */                                                   
    private String gisAssetsSubCd;    
    
    /** GIS 자산 명 */                                                   
    private String gisAssetsNm;

    /** 자산코드 */                                                   
    private String assetsCdStr;	    
    
    /**  사용 면적 */                                                    
    private String usageAr;                               
                                                              
    /**  사용 기간 FROM */                                              
    private String usagePdFrom;                             
                                                              
    /** 사용 기간 TO */                                                    
    private String usagePdTo;                                  
                                                              
    /**  사용 목적 */                                                   
    private String usagePurps;                                 
                                                       
    /**  사용 내역 */                                       
    private String usageDtls;                                
                                                                  
    /**  사용 용도 코드 */                                                   
    private String usagePrposCd;                              
                                                                
    /** 면제 구분 */                                                       
    private String exemptSe;         
    
    /** 면제 구분 명*/                                                       
    private String exemptSeNm;
                                                                  
    /**  면제 사유 코드 */                                                     
    private String exemptRsnCd;                                    
                                                         
    /**  면제 사유 */                                                   
    private String exemptRsn;                                  
                                                             
    /**  면제 기간 FROM */                                                             
    private String exemptPdFrom;   
            
    /**  면제 기간 TO */
    private String exemptPdTo;   

    /**  산출 내역 */
    private String computDtls;  
                 
    /**  공시지가 */
    private String olnlp;        
                
    /**  적용 요율 */
    private String applcTariff;

    /**
     * 적용단가
     */
    private String applcPrice;

    /**  적용 방법 */
    private String applcMth;     
                
    /**  포장 구분 */
    private String packSe; 
                      
    /**  업체 구분 */
    private String entrpsSe; 
                    
    /**  사용료 계산 구분 */
    private String feeCalcSe;  
                  
    /**  감면 사용료 계산 구분  */
    private String rdcxptFeeCalcSe;   
           
    /**  감면 사용료 */
    private String rdcxptFee;    
                
    /** 사용료 */
    private String fee;      
                    
    /**  해지 일자 */
    private String trmnatDt;  
                   
    /**  해지 사유 */
    private String trmnatRsn; 
                   
    /** GIS 코드 */
    private String gisCd;  
                      
    /** 등록자 */
    private String regUsr;    
                   
    /** 등록일시 */
    private String registDt;  
                   
    /** 수정자 */
    private String updUsr;  
                     
    /** 수정일시 */
    private String updtDt; 
                      
    /** 항코드 */
    private String prtAtCode;  
                  
    /** GIS 자산 항코드 */
    private String gisAssetsPrtAtCode;  
         
    /**  관리 년도 */
    private String mngYear;        
              
    /**  관리 번호 */
    private String mngNo;    
                    
    /**  관리 횟수  */
    private String mngCnt;
    
    /**  관리 년도 (상세)  */
    private String detailMngYear;
    
    /**  관리 번호 (상세)  */
    private String detailMngNo;
    
    /**  관리 횟수 (상세)  */
    private String detailMngCnt;
    
    /**  항코드 (상세)  */
    private String detailPrtAtCode; 
    
    /**  GIS코드 (상세)  */
    private String detailGisCd;
    
    /** 등록자 */
    private String detailRegUsr;    
                   
    /** 등록일시 */
    private String detailRegistDt;  
                   
    /** 수정자 */
    private String detailUpdUsr;  
                     
    /** 수정일시 */
    private String detailUpdtDt; 

    /** 허가 여부 */
    private String detailPrmisnYn;
    
    /** 부두코드 */
    private String quayCd;
    
    /** 자산순번MAX+1 */
    private int maxAssetsUsageSeq;

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
	 * @return the assetsCdStr
	 */
	public String getAssetsCdStr() {
		return assetsCdStr;
	}

	/**
	 * @param assetsCdStr the assetsCdStr to set
	 */
	public void setAssetsCdStr(String assetsCdStr) {
		this.assetsCdStr = assetsCdStr;
	}

	/**
	 * @return the usageAr
	 */
	public String getUsageAr() {
		return usageAr;
	}

	/**
	 * @param usageAr the usageAr to set
	 */
	public void setUsageAr(String usageAr) {
		this.usageAr = usageAr;
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
	 * @return the exemptRsnCd
	 */
	public String getExemptRsnCd() {
		return exemptRsnCd;
	}

	/**
	 * @param exemptRsnCd the exemptRsnCd to set
	 */
	public void setExemptRsnCd(String exemptRsnCd) {
		this.exemptRsnCd = exemptRsnCd;
	}

	/**
	 * @return the exemptRsn
	 */
	public String getExemptRsn() {
		return exemptRsn;
	}

	/**
	 * @param exemptRsn the exemptRsn to set
	 */
	public void setExemptRsn(String exemptRsn) {
		this.exemptRsn = exemptRsn;
	}

	/**
	 * @return the exemptPdFrom
	 */
	public String getExemptPdFrom() {
		return exemptPdFrom;
	}

	/**
	 * @param exemptPdFrom the exemptPdFrom to set
	 */
	public void setExemptPdFrom(String exemptPdFrom) {
		this.exemptPdFrom = exemptPdFrom;
	}

	/**
	 * @return the exemptPdTo
	 */
	public String getExemptPdTo() {
		return exemptPdTo;
	}

	/**
	 * @param exemptPdTo the exemptPdTo to set
	 */
	public void setExemptPdTo(String exemptPdTo) {
		this.exemptPdTo = exemptPdTo;
	}

	/**
	 * @return the computDtls
	 */
	public String getComputDtls() {
		return computDtls;
	}

	/**
	 * @param computDtls the computDtls to set
	 */
	public void setComputDtls(String computDtls) {
		this.computDtls = computDtls;
	}

	/**
	 * @return the olnlp
	 */
	public String getOlnlp() {
		return olnlp;
	}

	/**
	 * @param olnlp the olnlp to set
	 */
	public void setOlnlp(String olnlp) {
		this.olnlp = olnlp;
	}

	/**
	 * @return the applcTariff
	 */
	public String getApplcTariff() {
		return applcTariff;
	}

	/**
	 * @param applcTariff the applcTariff to set
	 */
	public void setApplcTariff(String applcTariff) {
		this.applcTariff = applcTariff;
	}

	/**
	 * @return the applcMth
	 */
	public String getApplcMth() {
		return applcMth;
	}

	/**
	 * @param applcMth the applcMth to set
	 */
	public void setApplcMth(String applcMth) {
		this.applcMth = applcMth;
	}

	/**
	 * @return the packSe
	 */
	public String getPackSe() {
		return packSe;
	}

	/**
	 * @param packSe the packSe to set
	 */
	public void setPackSe(String packSe) {
		this.packSe = packSe;
	}

	/**
	 * @return the entrpsSe
	 */
	public String getEntrpsSe() {
		return entrpsSe;
	}

	/**
	 * @param entrpsSe the entrpsSe to set
	 */
	public void setEntrpsSe(String entrpsSe) {
		this.entrpsSe = entrpsSe;
	}

	/**
	 * @return the feeCalcSe
	 */
	public String getFeeCalcSe() {
		return feeCalcSe;
	}

	/**
	 * @param feeCalcSe the feeCalcSe to set
	 */
	public void setFeeCalcSe(String feeCalcSe) {
		this.feeCalcSe = feeCalcSe;
	}

	/**
	 * @return the rdcxptFeeCalcSe
	 */
	public String getRdcxptFeeCalcSe() {
		return rdcxptFeeCalcSe;
	}

	/**
	 * @param rdcxptFeeCalcSe the rdcxptFeeCalcSe to set
	 */
	public void setRdcxptFeeCalcSe(String rdcxptFeeCalcSe) {
		this.rdcxptFeeCalcSe = rdcxptFeeCalcSe;
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
	 * @return the trmnatDt
	 */
	public String getTrmnatDt() {
		return trmnatDt;
	}

	/**
	 * @param trmnatDt the trmnatDt to set
	 */
	public void setTrmnatDt(String trmnatDt) {
		this.trmnatDt = trmnatDt;
	}

	/**
	 * @return the trmnatRsn
	 */
	public String getTrmnatRsn() {
		return trmnatRsn;
	}

	/**
	 * @param trmnatRsn the trmnatRsn to set
	 */
	public void setTrmnatRsn(String trmnatRsn) {
		this.trmnatRsn = trmnatRsn;
	}

	/**
	 * @return the gisCd
	 */
	public String getGisCd() {
		return gisCd;
	}

	/**
	 * @param gisCd the gisCd to set
	 */
	public void setGisCd(String gisCd) {
		this.gisCd = gisCd;
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
	 * @return the detailMngYear
	 */
	public String getDetailMngYear() {
		return detailMngYear;
	}

	/**
	 * @param detailMngYear the detailMngYear to set
	 */
	public void setDetailMngYear(String detailMngYear) {
		this.detailMngYear = detailMngYear;
	}

	/**
	 * @return the detailMngNo
	 */
	public String getDetailMngNo() {
		return detailMngNo;
	}

	/**
	 * @param detailMngNo the detailMngNo to set
	 */
	public void setDetailMngNo(String detailMngNo) {
		this.detailMngNo = detailMngNo;
	}

	/**
	 * @return the detailMngCnt
	 */
	public String getDetailMngCnt() {
		return detailMngCnt;
	}

	/**
	 * @param detailMngCnt the detailMngCnt to set
	 */
	public void setDetailMngCnt(String detailMngCnt) {
		this.detailMngCnt = detailMngCnt;
	}

	/**
	 * @return the detailPrtAtCode
	 */
	public String getDetailPrtAtCode() {
		return detailPrtAtCode;
	}

	/**
	 * @param detailPrtAtCode the detailPrtAtCode to set
	 */
	public void setDetailPrtAtCode(String detailPrtAtCode) {
		this.detailPrtAtCode = detailPrtAtCode;
	}

	/**
	 * @return the detailGisCd
	 */
	public String getDetailGisCd() {
		return detailGisCd;
	}

	/**
	 * @param detailGisCd the detailGisCd to set
	 */
	public void setDetailGisCd(String detailGisCd) {
		this.detailGisCd = detailGisCd;
	}

	/**
	 * @return the detailRegUsr
	 */
	public String getDetailRegUsr() {
		return detailRegUsr;
	}

	/**
	 * @param detailRegUsr the detailRegUsr to set
	 */
	public void setDetailRegUsr(String detailRegUsr) {
		this.detailRegUsr = detailRegUsr;
	}

	/**
	 * @return the detailRegistDt
	 */
	public String getDetailRegistDt() {
		return detailRegistDt;
	}

	/**
	 * @param detailRegistDt the detailRegistDt to set
	 */
	public void setDetailRegistDt(String detailRegistDt) {
		this.detailRegistDt = detailRegistDt;
	}

	/**
	 * @return the detailUpdUsr
	 */
	public String getDetailUpdUsr() {
		return detailUpdUsr;
	}

	/**
	 * @param detailUpdUsr the detailUpdUsr to set
	 */
	public void setDetailUpdUsr(String detailUpdUsr) {
		this.detailUpdUsr = detailUpdUsr;
	}

	/**
	 * @return the detailUpdtDt
	 */
	public String getDetailUpdtDt() {
		return detailUpdtDt;
	}

	/**
	 * @param detailUpdtDt the detailUpdtDt to set
	 */
	public void setDetailUpdtDt(String detailUpdtDt) {
		this.detailUpdtDt = detailUpdtDt;
	}

	/**
	 * @return the detailPrmisnYn
	 */
	public String getDetailPrmisnYn() {
		return detailPrmisnYn;
	}

	/**
	 * @param detailPrmisnYn the detailPrmisnYn to set
	 */
	public void setDetailPrmisnYn(String detailPrmisnYn) {
		this.detailPrmisnYn = detailPrmisnYn;
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
	 * @return the maxAssetsUsageSeq
	 */
	public int getMaxAssetsUsageSeq() {
		return maxAssetsUsageSeq;
	}

	/**
	 * @param maxAssetsUsageSeq the maxAssetsUsageSeq to set
	 */
	public void setMaxAssetsUsageSeq(int maxAssetsUsageSeq) {
		this.maxAssetsUsageSeq = maxAssetsUsageSeq;
	}
    

	public String getApplcPrice() {
		return applcPrice;
	}

	public void setApplcPrice(String applcPrice) {
		this.applcPrice = applcPrice;
	}
}
