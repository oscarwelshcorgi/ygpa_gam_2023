package egovframework.rte.ygpa.gam.asset.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamAssetEvlDtlsInqireVO.java
 * @Description : 자산가치평가내역조회 VO class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamAssetEvlDtlsInqireVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** 항코드 */
    private String sPrtAtCode;

    /** GIS 자산 코드 */
    private String sGisAssetsCd;
	
    /** GIS 자산 명 */
    private String sGisAssetsNm;
    
    /** 사용기간 FORM */
    private String sUsagePdFrom;
    
    /** 사용기간 TO */
    private String sUsagePdTo;
    
    /**  GIS 자산 가치 조회 일자 FORM */
    private String sGisAssetsValInqireDtFrom;

    /**  GIS 자산 가치 조회 일자 TO */
    private String sGisAssetsValInqireDtTo;
    
    /** GIS 자산 준공년도(검색조건) */
    private String sGisAssetsBlddate;
    
    /** GIS 자산 위치 코드(검색조건) */
    private String sGisAssetsLocCd; 
    
    /** GIS 자산 부두 코드(검색조건) */
    private String sGisAssetsQuayCd;
    
    /** GIS 자산 구분 코드(검색조건) */
    private String sGisAssetsSeCd;	   
    
    /** GIS 자산 재산 구분 코드(검색조건) */
    private String sGisAssetsPrprtySeCd;
    
    /** GIS 자산 준공년도 */
    private String gisAssetsBlddate;
    
    /** GIS 자산 위치 코드 */
    private String gisAssetsLocCd; 
    
    /** GIS 자산 부두 코드 */
    private String gisAssetsQuayCd;
    
    /** GIS 자산 구분 코드 */
    private String gisAssetsSeCd;	   
    
    /** GIS 자산 재산 구분 코드 */
    private String gisAssetsPrprtySeCd;
    
    /** ERP 자산 구분 코드 */
    private String erpAssetsSeCd;
    
    /** ERP 자산 번호 */
    private String erpAssetsNo;
    
    /** ERP 자산 번호 순번 */
    private String erpAssetsNoSeq;
    
    /** [ERP] 상각연도 */
    private String deprctnYear;

    /** [ERP] 자산구분*/
    private String assetCls;

    /** [ERP] 자산번호*/
    private String assetNo;

    /** [ERP] 자산번호순번 */
    private String assetNoSeq;

    /** [ERP] 재평가금액 */
    private String revalAmt;

    /** [ERP] 당기자산증가금액 */
    private String thisTermIncreAmt;

    /** [ERP] 대차대조기말현재금액 */
    private String bsThisCurAmt;

    /** [ERP] 대차대조전기말상각누계금액 */
    private String bsPreDeprctnSum;

    /** [ERP] 대차대조미상각잔액 */
    private String bsNoDeprctnBal;

    /** [ERP] 전기말자본적지출금액누계 */
    private String preEndAssetBuySum;

    /** [ERP] 자본적지출금액 */
    private String assetBuyAmt;

    /** [ERP] 일반상각율 */
    private String gnrlDeprctnRate;

    /** [ERP] 당기상각금액 */
    private String thisTermDeprctnAmt;

    /** [ERP] 잔존금액 */
    private String curAmt;

    /** [ERP] 입력자코드 */
    private String inputEmpNo;

    /** [ERP] 입력일자 */
    private String inputDate;

    /** [ERP] 수정자코드 */
    private String updateEmpNo;

    /** [ERP] 수정일자 */
    private String updateDate;
    
    private String sumRevalAmt;       
    
    private String sumThisTermIncreAmt; 
    
    private String sumBsThisCurAmt;     
    
    private String sumBsPreDeprctnSum;   
    
    private String sumBsNoDeprctnBal;  
    
    private String sumPreEndAssetBuySum;  
    
    private String sumAssetBuyAmt;        
    
    private String sumGnrlDeprctnRate;    
    
    private String sumThisTermDeprctnAmt; 
    
    private String sumCurAmt;             
    
    private String sumCnt;             

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
	 * @return the sGisAssetsNm
	 */
	public String getsGisAssetsNm() {
		return sGisAssetsNm;
	}

	/**
	 * @param sGisAssetsNm the sGisAssetsNm to set
	 */
	public void setsGisAssetsNm(String sGisAssetsNm) {
		this.sGisAssetsNm = sGisAssetsNm;
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
	 * @return the sGisAssetsValInqireDtFrom
	 */
	public String getsGisAssetsValInqireDtFrom() {
		return sGisAssetsValInqireDtFrom;
	}

	/**
	 * @param sGisAssetsValInqireDtFrom the sGisAssetsValInqireDtFrom to set
	 */
	public void setsGisAssetsValInqireDtFrom(String sGisAssetsValInqireDtFrom) {
		this.sGisAssetsValInqireDtFrom = sGisAssetsValInqireDtFrom;
	}

	/**
	 * @return the sGisAssetsValInqireDtTo
	 */
	public String getsGisAssetsValInqireDtTo() {
		return sGisAssetsValInqireDtTo;
	}

	/**
	 * @param sGisAssetsValInqireDtTo the sGisAssetsValInqireDtTo to set
	 */
	public void setsGisAssetsValInqireDtTo(String sGisAssetsValInqireDtTo) {
		this.sGisAssetsValInqireDtTo = sGisAssetsValInqireDtTo;
	}

	/**
	 * @return the deprctnYear
	 */
	public String getDeprctnYear() {
		return deprctnYear;
	}

	/**
	 * @param deprctnYear the deprctnYear to set
	 */
	public void setDeprctnYear(String deprctnYear) {
		this.deprctnYear = deprctnYear;
	}

	/**
	 * @return the assetCls
	 */
	public String getAssetCls() {
		return assetCls;
	}

	/**
	 * @param assetCls the assetCls to set
	 */
	public void setAssetCls(String assetCls) {
		this.assetCls = assetCls;
	}

	/**
	 * @return the assetNo
	 */
	public String getAssetNo() {
		return assetNo;
	}

	/**
	 * @param assetNo the assetNo to set
	 */
	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	/**
	 * @return the assetNoSeq
	 */
	public String getAssetNoSeq() {
		return assetNoSeq;
	}

	/**
	 * @param assetNoSeq the assetNoSeq to set
	 */
	public void setAssetNoSeq(String assetNoSeq) {
		this.assetNoSeq = assetNoSeq;
	}

	/**
	 * @return the revalAmt
	 */
	public String getRevalAmt() {
		return revalAmt;
	}

	/**
	 * @param revalAmt the revalAmt to set
	 */
	public void setRevalAmt(String revalAmt) {
		this.revalAmt = revalAmt;
	}

	/**
	 * @return the thisTermIncreAmt
	 */
	public String getThisTermIncreAmt() {
		return thisTermIncreAmt;
	}

	/**
	 * @param thisTermIncreAmt the thisTermIncreAmt to set
	 */
	public void setThisTermIncreAmt(String thisTermIncreAmt) {
		this.thisTermIncreAmt = thisTermIncreAmt;
	}

	/**
	 * @return the bsThisCurAmt
	 */
	public String getBsThisCurAmt() {
		return bsThisCurAmt;
	}

	/**
	 * @param bsThisCurAmt the bsThisCurAmt to set
	 */
	public void setBsThisCurAmt(String bsThisCurAmt) {
		this.bsThisCurAmt = bsThisCurAmt;
	}

	/**
	 * @return the bsPreDeprctnSum
	 */
	public String getBsPreDeprctnSum() {
		return bsPreDeprctnSum;
	}

	/**
	 * @param bsPreDeprctnSum the bsPreDeprctnSum to set
	 */
	public void setBsPreDeprctnSum(String bsPreDeprctnSum) {
		this.bsPreDeprctnSum = bsPreDeprctnSum;
	}

	/**
	 * @return the bsNoDeprctnBal
	 */
	public String getBsNoDeprctnBal() {
		return bsNoDeprctnBal;
	}

	/**
	 * @param bsNoDeprctnBal the bsNoDeprctnBal to set
	 */
	public void setBsNoDeprctnBal(String bsNoDeprctnBal) {
		this.bsNoDeprctnBal = bsNoDeprctnBal;
	}

	/**
	 * @return the preEndAssetBuySum
	 */
	public String getPreEndAssetBuySum() {
		return preEndAssetBuySum;
	}

	/**
	 * @param preEndAssetBuySum the preEndAssetBuySum to set
	 */
	public void setPreEndAssetBuySum(String preEndAssetBuySum) {
		this.preEndAssetBuySum = preEndAssetBuySum;
	}

	/**
	 * @return the assetBuyAmt
	 */
	public String getAssetBuyAmt() {
		return assetBuyAmt;
	}

	/**
	 * @param assetBuyAmt the assetBuyAmt to set
	 */
	public void setAssetBuyAmt(String assetBuyAmt) {
		this.assetBuyAmt = assetBuyAmt;
	}

	/**
	 * @return the gnrlDeprctnRate
	 */
	public String getGnrlDeprctnRate() {
		return gnrlDeprctnRate;
	}

	/**
	 * @param gnrlDeprctnRate the gnrlDeprctnRate to set
	 */
	public void setGnrlDeprctnRate(String gnrlDeprctnRate) {
		this.gnrlDeprctnRate = gnrlDeprctnRate;
	}

	/**
	 * @return the thisTermDeprctnAmt
	 */
	public String getThisTermDeprctnAmt() {
		return thisTermDeprctnAmt;
	}

	/**
	 * @param thisTermDeprctnAmt the thisTermDeprctnAmt to set
	 */
	public void setThisTermDeprctnAmt(String thisTermDeprctnAmt) {
		this.thisTermDeprctnAmt = thisTermDeprctnAmt;
	}

	/**
	 * @return the curAmt
	 */
	public String getCurAmt() {
		return curAmt;
	}

	/**
	 * @param curAmt the curAmt to set
	 */
	public void setCurAmt(String curAmt) {
		this.curAmt = curAmt;
	}

	/**
	 * @return the inputEmpNo
	 */
	public String getInputEmpNo() {
		return inputEmpNo;
	}

	/**
	 * @param inputEmpNo the inputEmpNo to set
	 */
	public void setInputEmpNo(String inputEmpNo) {
		this.inputEmpNo = inputEmpNo;
	}

	/**
	 * @return the inputDate
	 */
	public String getInputDate() {
		return inputDate;
	}

	/**
	 * @param inputDate the inputDate to set
	 */
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * @return the updateEmpNo
	 */
	public String getUpdateEmpNo() {
		return updateEmpNo;
	}

	/**
	 * @param updateEmpNo the updateEmpNo to set
	 */
	public void setUpdateEmpNo(String updateEmpNo) {
		this.updateEmpNo = updateEmpNo;
	}

	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the erpAssetsSeCd
	 */
	public String getErpAssetsSeCd() {
		return erpAssetsSeCd;
	}

	/**
	 * @param erpAssetsSeCd the erpAssetsSeCd to set
	 */
	public void setErpAssetsSeCd(String erpAssetsSeCd) {
		this.erpAssetsSeCd = erpAssetsSeCd;
	}

	/**
	 * @return the erpAssetsNo
	 */
	public String getErpAssetsNo() {
		return erpAssetsNo;
	}

	/**
	 * @param erpAssetsNo the erpAssetsNo to set
	 */
	public void setErpAssetsNo(String erpAssetsNo) {
		this.erpAssetsNo = erpAssetsNo;
	}

	/**
	 * @return the erpAssetsNoSeq
	 */
	public String getErpAssetsNoSeq() {
		return erpAssetsNoSeq;
	}

	/**
	 * @param erpAssetsNoSeq the erpAssetsNoSeq to set
	 */
	public void setErpAssetsNoSeq(String erpAssetsNoSeq) {
		this.erpAssetsNoSeq = erpAssetsNoSeq;
	}

	/**
	 * @return the sGisAssetsBlddate
	 */
	public String getsGisAssetsBlddate() {
		return sGisAssetsBlddate;
	}

	/**
	 * @param sGisAssetsBlddate the sGisAssetsBlddate to set
	 */
	public void setsGisAssetsBlddate(String sGisAssetsBlddate) {
		this.sGisAssetsBlddate = sGisAssetsBlddate;
	}

	/**
	 * @return the sGisAssetsLocCd
	 */
	public String getsGisAssetsLocCd() {
		return sGisAssetsLocCd;
	}

	/**
	 * @param sGisAssetsLocCd the sGisAssetsLocCd to set
	 */
	public void setsGisAssetsLocCd(String sGisAssetsLocCd) {
		this.sGisAssetsLocCd = sGisAssetsLocCd;
	}

	/**
	 * @return the sGisAssetsSeCd
	 */
	public String getsGisAssetsSeCd() {
		return sGisAssetsSeCd;
	}

	/**
	 * @param sGisAssetsSeCd the sGisAssetsSeCd to set
	 */
	public void setsGisAssetsSeCd(String sGisAssetsSeCd) {
		this.sGisAssetsSeCd = sGisAssetsSeCd;
	}

	/**
	 * @return the sGisAssetsPrprtySeCd
	 */
	public String getsGisAssetsPrprtySeCd() {
		return sGisAssetsPrprtySeCd;
	}

	/**
	 * @param sGisAssetsPrprtySeCd the sGisAssetsPrprtySeCd to set
	 */
	public void setsGisAssetsPrprtySeCd(String sGisAssetsPrprtySeCd) {
		this.sGisAssetsPrprtySeCd = sGisAssetsPrprtySeCd;
	}

	/**
	 * @return the gisAssetsBlddate
	 */
	public String getGisAssetsBlddate() {
		return gisAssetsBlddate;
	}

	/**
	 * @param gisAssetsBlddate the gisAssetsBlddate to set
	 */
	public void setGisAssetsBlddate(String gisAssetsBlddate) {
		this.gisAssetsBlddate = gisAssetsBlddate;
	}

	/**
	 * @return the gisAssetsLocCd
	 */
	public String getGisAssetsLocCd() {
		return gisAssetsLocCd;
	}

	/**
	 * @param gisAssetsLocCd the gisAssetsLocCd to set
	 */
	public void setGisAssetsLocCd(String gisAssetsLocCd) {
		this.gisAssetsLocCd = gisAssetsLocCd;
	}

	/**
	 * @return the gisAssetsSeCd
	 */
	public String getGisAssetsSeCd() {
		return gisAssetsSeCd;
	}

	/**
	 * @param gisAssetsSeCd the gisAssetsSeCd to set
	 */
	public void setGisAssetsSeCd(String gisAssetsSeCd) {
		this.gisAssetsSeCd = gisAssetsSeCd;
	}

	/**
	 * @return the gisAssetsPrprtySeCd
	 */
	public String getGisAssetsPrprtySeCd() {
		return gisAssetsPrprtySeCd;
	}

	/**
	 * @param gisAssetsPrprtySeCd the gisAssetsPrprtySeCd to set
	 */
	public void setGisAssetsPrprtySeCd(String gisAssetsPrprtySeCd) {
		this.gisAssetsPrprtySeCd = gisAssetsPrprtySeCd;
	}

	/**
	 * @return the sGisAssetsQuayCd
	 */
	public String getsGisAssetsQuayCd() {
		return sGisAssetsQuayCd;
	}

	/**
	 * @param sGisAssetsQuayCd the sGisAssetsQuayCd to set
	 */
	public void setsGisAssetsQuayCd(String sGisAssetsQuayCd) {
		this.sGisAssetsQuayCd = sGisAssetsQuayCd;
	}

	/**
	 * @return the gisAssetsQuayCd
	 */
	public String getGisAssetsQuayCd() {
		return gisAssetsQuayCd;
	}

	/**
	 * @param gisAssetsQuayCd the gisAssetsQuayCd to set
	 */
	public void setGisAssetsQuayCd(String gisAssetsQuayCd) {
		this.gisAssetsQuayCd = gisAssetsQuayCd;
	}

	/**
	 * @return the sumRevalAmt
	 */
	public String getSumRevalAmt() {
		return sumRevalAmt;
	}

	/**
	 * @param sumRevalAmt the sumRevalAmt to set
	 */
	public void setSumRevalAmt(String sumRevalAmt) {
		this.sumRevalAmt = sumRevalAmt;
	}

	/**
	 * @return the sumThisTermIncreAmt
	 */
	public String getSumThisTermIncreAmt() {
		return sumThisTermIncreAmt;
	}

	/**
	 * @param sumThisTermIncreAmt the sumThisTermIncreAmt to set
	 */
	public void setSumThisTermIncreAmt(String sumThisTermIncreAmt) {
		this.sumThisTermIncreAmt = sumThisTermIncreAmt;
	}

	/**
	 * @return the sumBsThisCurAmt
	 */
	public String getSumBsThisCurAmt() {
		return sumBsThisCurAmt;
	}

	/**
	 * @param sumBsThisCurAmt the sumBsThisCurAmt to set
	 */
	public void setSumBsThisCurAmt(String sumBsThisCurAmt) {
		this.sumBsThisCurAmt = sumBsThisCurAmt;
	}

	/**
	 * @return the sumBsPreDeprctnSum
	 */
	public String getSumBsPreDeprctnSum() {
		return sumBsPreDeprctnSum;
	}

	/**
	 * @param sumBsPreDeprctnSum the sumBsPreDeprctnSum to set
	 */
	public void setSumBsPreDeprctnSum(String sumBsPreDeprctnSum) {
		this.sumBsPreDeprctnSum = sumBsPreDeprctnSum;
	}

	/**
	 * @return the sumBsNoDeprctnBal
	 */
	public String getSumBsNoDeprctnBal() {
		return sumBsNoDeprctnBal;
	}

	/**
	 * @param sumBsNoDeprctnBal the sumBsNoDeprctnBal to set
	 */
	public void setSumBsNoDeprctnBal(String sumBsNoDeprctnBal) {
		this.sumBsNoDeprctnBal = sumBsNoDeprctnBal;
	}

	/**
	 * @return the sumPreEndAssetBuySum
	 */
	public String getSumPreEndAssetBuySum() {
		return sumPreEndAssetBuySum;
	}

	/**
	 * @param sumPreEndAssetBuySum the sumPreEndAssetBuySum to set
	 */
	public void setSumPreEndAssetBuySum(String sumPreEndAssetBuySum) {
		this.sumPreEndAssetBuySum = sumPreEndAssetBuySum;
	}

	/**
	 * @return the sumAssetBuyAmt
	 */
	public String getSumAssetBuyAmt() {
		return sumAssetBuyAmt;
	}

	/**
	 * @param sumAssetBuyAmt the sumAssetBuyAmt to set
	 */
	public void setSumAssetBuyAmt(String sumAssetBuyAmt) {
		this.sumAssetBuyAmt = sumAssetBuyAmt;
	}

	/**
	 * @return the sumGnrlDeprctnRate
	 */
	public String getSumGnrlDeprctnRate() {
		return sumGnrlDeprctnRate;
	}

	/**
	 * @param sumGnrlDeprctnRate the sumGnrlDeprctnRate to set
	 */
	public void setSumGnrlDeprctnRate(String sumGnrlDeprctnRate) {
		this.sumGnrlDeprctnRate = sumGnrlDeprctnRate;
	}

	/**
	 * @return the sumThisTermDeprctnAmt
	 */
	public String getSumThisTermDeprctnAmt() {
		return sumThisTermDeprctnAmt;
	}

	/**
	 * @param sumThisTermDeprctnAmt the sumThisTermDeprctnAmt to set
	 */
	public void setSumThisTermDeprctnAmt(String sumThisTermDeprctnAmt) {
		this.sumThisTermDeprctnAmt = sumThisTermDeprctnAmt;
	}

	/**
	 * @return the sumCurAmt
	 */
	public String getSumCurAmt() {
		return sumCurAmt;
	}

	/**
	 * @param sumCurAmt the sumCurAmt to set
	 */
	public void setSumCurAmt(String sumCurAmt) {
		this.sumCurAmt = sumCurAmt;
	}
	
	/**
	 * @return the sumCnt
	 */
	public String getSumCnt() {
		return sumCnt;
	}

	/**
	 * @param sumCnt the sumCnt to set
	 */
	public void setSumCnt(String sumCnt) {
		this.sumCnt = sumCnt;
	}
	
}
