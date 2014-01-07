package egovframework.rte.ygpa.erp.cmm.service;

/**
 * @Class Name : ErpCmmnCdClVO.java
 * @Description : ErpCmmnCdCl VO class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class ErpCmmnCdClVO extends ErpCmmnCdClDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** BIG_CLS_CD */
    private java.lang.String bigClsCd;
    
    /** CD_CMMNT */
    private java.lang.String cdCmmnt;
    
    /** SYSTEM_NAME */
    private java.lang.String systemName;
    
    /** INPUT_EMP_NO */
    private java.lang.String inputEmpNo;
    
    /** INPUT_DATE */
    private java.sql.Date inputDate;
    
    /** UPDATE_EMP_NO */
    private java.lang.String updateEmpNo;
    
    /** UPDATE_DATE */
    private java.sql.Date updateDate;
    
    public java.lang.String getBigClsCd() {
        return this.bigClsCd;
    }
    
    public void setBigClsCd(java.lang.String bigClsCd) {
        this.bigClsCd = bigClsCd;
    }
    
    public java.lang.String getCdCmmnt() {
        return this.cdCmmnt;
    }
    
    public void setCdCmmnt(java.lang.String cdCmmnt) {
        this.cdCmmnt = cdCmmnt;
    }
    
    public java.lang.String getSystemName() {
        return this.systemName;
    }
    
    public void setSystemName(java.lang.String systemName) {
        this.systemName = systemName;
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
    
}
