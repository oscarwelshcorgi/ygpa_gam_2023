package egovframework.rte.ygpa.erp.code.service;

/**
 * @Class Name : ErpAssetDeprctnVO.java
 * @Description : ErpAssetDeprctn VO class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class ErpAssetDeprctnVO extends ErpAssetDeprctnDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** REVAL_YEAR */
    private java.lang.String revalYear;
    
    /** THIS_END_BASE_VALUE_EVAL_AMT */
    private java.math.BigDecimal thisEndBaseValueEvalAmt;
    
    /** THIS_TERM_INCRE_YEAR_MON */
    private java.lang.String thisTermIncreYearMon;
    
    /** THIS_TERM_INCRE_AMT */
    private java.math.BigDecimal thisTermIncreAmt;
    
    /** BS_THIS_CUR_AMT */
    private java.math.BigDecimal bsThisCurAmt;
    
    /** BS_PRE_DEPRCTN_SUM */
    private java.math.BigDecimal bsPreDeprctnSum;
    
    /** BS_NO_DEPRCTN_BAL */
    private java.math.BigDecimal bsNoDeprctnBal;
    
    /** PRE_END_ASSET_BUY_SUM */
    private java.math.BigDecimal preEndAssetBuySum;
    
    /** ASSET_BUY_AMT */
    private java.math.BigDecimal assetBuyAmt;
    
    /** THIS_END_ASSET_BUY_SUM */
    private java.math.BigDecimal thisEndAssetBuySum;
    
    /** GNRL_DEPRCTN_RATE */
    private java.math.BigDecimal gnrlDeprctnRate;
    
    /** THIS_TERM_DEPRCTN_AMT */
    private java.math.BigDecimal thisTermDeprctnAmt;
    
    /** PRE_END_CUR_BUY_AMT */
    private java.math.BigDecimal preEndCurBuyAmt;
    
    /** CUR_AMT */
    private java.math.BigDecimal curAmt;
    
    /** INPUT_EMP_NO */
    private java.lang.String inputEmpNo;
    
    /** INPUT_DATE */
    private java.sql.Date inputDate;
    
    /** UPDATE_EMP_NO */
    private java.lang.String updateEmpNo;
    
    /** UPDATE_DATE */
    private java.sql.Date updateDate;
    
    /** DEPRCTN_YEAR */
    private java.lang.String deprctnYear;
    
    /** ASSET_CLS */
    private java.lang.String assetCls;
    
    /** ASSET_NO */
    private java.math.BigDecimal assetNo;
    
    /** ASSET_NO_SEQ */
    private java.math.BigDecimal assetNoSeq;
    
    public java.lang.String getRevalYear() {
        return this.revalYear;
    }
    
    public void setRevalYear(java.lang.String revalYear) {
        this.revalYear = revalYear;
    }
    
    public java.math.BigDecimal getThisEndBaseValueEvalAmt() {
        return this.thisEndBaseValueEvalAmt;
    }
    
    public void setThisEndBaseValueEvalAmt(java.math.BigDecimal thisEndBaseValueEvalAmt) {
        this.thisEndBaseValueEvalAmt = thisEndBaseValueEvalAmt;
    }
    
    public java.lang.String getThisTermIncreYearMon() {
        return this.thisTermIncreYearMon;
    }
    
    public void setThisTermIncreYearMon(java.lang.String thisTermIncreYearMon) {
        this.thisTermIncreYearMon = thisTermIncreYearMon;
    }
    
    public java.math.BigDecimal getThisTermIncreAmt() {
        return this.thisTermIncreAmt;
    }
    
    public void setThisTermIncreAmt(java.math.BigDecimal thisTermIncreAmt) {
        this.thisTermIncreAmt = thisTermIncreAmt;
    }
    
    public java.math.BigDecimal getBsThisCurAmt() {
        return this.bsThisCurAmt;
    }
    
    public void setBsThisCurAmt(java.math.BigDecimal bsThisCurAmt) {
        this.bsThisCurAmt = bsThisCurAmt;
    }
    
    public java.math.BigDecimal getBsPreDeprctnSum() {
        return this.bsPreDeprctnSum;
    }
    
    public void setBsPreDeprctnSum(java.math.BigDecimal bsPreDeprctnSum) {
        this.bsPreDeprctnSum = bsPreDeprctnSum;
    }
    
    public java.math.BigDecimal getBsNoDeprctnBal() {
        return this.bsNoDeprctnBal;
    }
    
    public void setBsNoDeprctnBal(java.math.BigDecimal bsNoDeprctnBal) {
        this.bsNoDeprctnBal = bsNoDeprctnBal;
    }
    
    public java.math.BigDecimal getPreEndAssetBuySum() {
        return this.preEndAssetBuySum;
    }
    
    public void setPreEndAssetBuySum(java.math.BigDecimal preEndAssetBuySum) {
        this.preEndAssetBuySum = preEndAssetBuySum;
    }
    
    public java.math.BigDecimal getAssetBuyAmt() {
        return this.assetBuyAmt;
    }
    
    public void setAssetBuyAmt(java.math.BigDecimal assetBuyAmt) {
        this.assetBuyAmt = assetBuyAmt;
    }
    
    public java.math.BigDecimal getThisEndAssetBuySum() {
        return this.thisEndAssetBuySum;
    }
    
    public void setThisEndAssetBuySum(java.math.BigDecimal thisEndAssetBuySum) {
        this.thisEndAssetBuySum = thisEndAssetBuySum;
    }
    
    public java.math.BigDecimal getGnrlDeprctnRate() {
        return this.gnrlDeprctnRate;
    }
    
    public void setGnrlDeprctnRate(java.math.BigDecimal gnrlDeprctnRate) {
        this.gnrlDeprctnRate = gnrlDeprctnRate;
    }
    
    public java.math.BigDecimal getThisTermDeprctnAmt() {
        return this.thisTermDeprctnAmt;
    }
    
    public void setThisTermDeprctnAmt(java.math.BigDecimal thisTermDeprctnAmt) {
        this.thisTermDeprctnAmt = thisTermDeprctnAmt;
    }
    
    public java.math.BigDecimal getPreEndCurBuyAmt() {
        return this.preEndCurBuyAmt;
    }
    
    public void setPreEndCurBuyAmt(java.math.BigDecimal preEndCurBuyAmt) {
        this.preEndCurBuyAmt = preEndCurBuyAmt;
    }
    
    public java.math.BigDecimal getCurAmt() {
        return this.curAmt;
    }
    
    public void setCurAmt(java.math.BigDecimal curAmt) {
        this.curAmt = curAmt;
    }
    
    public java.lang.String getInputEmpNo() {
        return this.inputEmpNo;
    }
    
    public void setInputEmpNo(java.lang.String inputEmpNo) {
        this.inputEmpNo = inputEmpNo;
    }
    
    public java.sql.Date getInputDate() {
        return this.inputDate;
    }
    
    public void setInputDate(java.sql.Date inputDate) {
        this.inputDate = inputDate;
    }
    
    public java.lang.String getUpdateEmpNo() {
        return this.updateEmpNo;
    }
    
    public void setUpdateEmpNo(java.lang.String updateEmpNo) {
        this.updateEmpNo = updateEmpNo;
    }
    
    public java.sql.Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(java.sql.Date updateDate) {
        this.updateDate = updateDate;
    }
    
    public java.lang.String getDeprctnYear() {
        return this.deprctnYear;
    }
    
    public void setDeprctnYear(java.lang.String deprctnYear) {
        this.deprctnYear = deprctnYear;
    }
    
    public java.lang.String getAssetCls() {
        return this.assetCls;
    }
    
    public void setAssetCls(java.lang.String assetCls) {
        this.assetCls = assetCls;
    }
    
    public java.math.BigDecimal getAssetNo() {
        return this.assetNo;
    }
    
    public void setAssetNo(java.math.BigDecimal assetNo) {
        this.assetNo = assetNo;
    }
    
    public java.math.BigDecimal getAssetNoSeq() {
        return this.assetNoSeq;
    }
    
    public void setAssetNoSeq(java.math.BigDecimal assetNoSeq) {
        this.assetNoSeq = assetNoSeq;
    }
    
}
