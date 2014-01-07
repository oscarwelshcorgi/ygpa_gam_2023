package egovframework.rte.ygpa.erp.cmm.service;

/**
 * @Class Name : ErpCmmnCdVO.java
 * @Description : ErpCmmnCd VO class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class ErpCmmnCdVO extends ErpCmmnCdDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** SM_CLS_NAME */
    private java.lang.String smClsName;
    
    /** REF_CD */
    private java.lang.String refCd;
    
    /** REMARK */
    private java.lang.String remark;
    
    /** OUTPUT_ORDER */
    private java.math.BigDecimal outputOrder;
    
    /** USE_CLS */
    private java.lang.String useCls;
    
    /** INPUT_EMP_NO */
    private java.lang.String inputEmpNo;
    
    /** INPUT_DATE */
    private java.sql.Date inputDate;
    
    /** UPDATE_EMP_NO */
    private java.lang.String updateEmpNo;
    
    /** UPDATE_DATE */
    private java.sql.Date updateDate;
    
    /** SM_CLS */
    private java.lang.String smCls;
    
    /** BIG_CLS_CD */
    private java.lang.String bigClsCd;
    
    public java.lang.String getSmClsName() {
        return this.smClsName;
    }
    
    public void setSmClsName(java.lang.String smClsName) {
        this.smClsName = smClsName;
    }
    
    public java.lang.String getRefCd() {
        return this.refCd;
    }
    
    public void setRefCd(java.lang.String refCd) {
        this.refCd = refCd;
    }
    
    public java.lang.String getRemark() {
        return this.remark;
    }
    
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }
    
    public java.math.BigDecimal getOutputOrder() {
        return this.outputOrder;
    }
    
    public void setOutputOrder(java.math.BigDecimal outputOrder) {
        this.outputOrder = outputOrder;
    }
    
    public java.lang.String getUseCls() {
        return this.useCls;
    }
    
    public void setUseCls(java.lang.String useCls) {
        this.useCls = useCls;
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
    
    public java.lang.String getSmCls() {
        return this.smCls;
    }
    
    public void setSmCls(java.lang.String smCls) {
        this.smCls = smCls;
    }
    
    public java.lang.String getBigClsCd() {
        return this.bigClsCd;
    }
    
    public void setBigClsCd(java.lang.String bigClsCd) {
        this.bigClsCd = bigClsCd;
    }
    
}
