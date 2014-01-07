package egovframework.rte.ygpa.erp.code.service;

/**
 * @Class Name : ErpAssetCateVO.java
 * @Description : ErpAssetCate VO class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class ErpAssetCateVO extends ErpAssetCateDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** ASSET_CATE_CLS */
    private java.lang.String assetCateCls;
    
    /** ASSET_CATE_CD */
    private java.lang.String assetCateCd;
    
    /** NAME */
    private java.lang.String name;
    
    /** INPUT_EMP_NO */
    private java.lang.String inputEmpNo;
    
    /** INPUT_DATE */
    private java.sql.Date inputDate;
    
    /** UPDATE_EMP_NO */
    private java.lang.String updateEmpNo;
    
    /** UPDATE_DATE */
    private java.sql.Date updateDate;
    
    public java.lang.String getAssetCateCls() {
        return this.assetCateCls;
    }
    
    public void setAssetCateCls(java.lang.String assetCateCls) {
        this.assetCateCls = assetCateCls;
    }
    
    public java.lang.String getAssetCateCd() {
        return this.assetCateCd;
    }
    
    public void setAssetCateCd(java.lang.String assetCateCd) {
        this.assetCateCd = assetCateCd;
    }
    
    public java.lang.String getName() {
        return this.name;
    }
    
    public void setName(java.lang.String name) {
        this.name = name;
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
