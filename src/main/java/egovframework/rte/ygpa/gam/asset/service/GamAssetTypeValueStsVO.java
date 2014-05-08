package egovframework.rte.ygpa.gam.asset.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamAssetTypeValueStsVO.java
 * @Description : 자산종류별자산가치통계조회 VO class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamAssetTypeValueStsVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    private String sDeprctnYear;
    
    /**
	 * 재평가금액
	 */
    private String sumRevalAmt;
    
    /**
	 * 당기자산증가금액
	 */
    private String sumThisTermIncreAmt;
    
    /**
	 * 대차대조기말현재금액
	 */
    private String sumBsThisCurAmt;
    
    /**
	 * 대차대조전기말상각누계금액
	 */
    private String sumBsPreDeprctnSum;
    
    /**
	 * 대차대조미상각잔액
	 */
    private String sumBsNoDeprctnBal;
    
    /**
	 * 전기말자본적지출금액 누계
	 */
    private String sumPreEndAssetBuySum;
    
    /**
	 * 자본적지출금액
	 */
    private String sumAssetBuyAmt;
    
    /**
	 * 당기상각금액
	 */
    private String sumThisTermDeprctnAmt;
    
    /**
	 * 잔존금액
	 */
    private String sumCurAmt;
    

	/**
	 * @return the sDeprctnYear
	 */
	public String getsDeprctnYear() {
		return sDeprctnYear;
	}

	/**
	 * @param sDeprctnYear the sDeprctnYear to set
	 */
	public void setsDeprctnYear(String sDeprctnYear) {
		this.sDeprctnYear = sDeprctnYear;
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
    
}
