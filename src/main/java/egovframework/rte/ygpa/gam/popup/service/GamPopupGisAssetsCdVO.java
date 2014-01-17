package egovframework.rte.ygpa.gam.popup.service;

/**
 * @Class Name : GisAssetsCdFVO.java
 * @Description : GisAssetsCdF VO class
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-01-15
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public class GamPopupGisAssetsCdVO extends GamPopupGisAssetsCdDefaultVO{
    private static final long serialVersionUID = 1L;

    /** GIS_ASSETS_PRT_AT_CODE */
    private String gisAssetsPrtAtCode;

    /** 시설코드 : 시설코드-시설SUB코드 */
    private String gisAssetsCode;

    private String gisAssetsCd;

    private String gisAssetsSubCd;

    /** GIS_ASSETS_NM */
    private String gisAssetsNm;

    /** GIS_ASSETS_MNG_DEPT_CD */
    private String gisAssetsMngDeptCd;

    /** GIS_ASSETS_OPER_DEPT_CD */
    private String gisAssetsOperDeptCd;

    /** GIS_ASSETS_LOCPLC */
    private String gisAssetsLocplc;

    /** GIS_ASSETS_LNM */
    private String gisAssetsLnm;

    /** GIS_ASSETS_LNM_SUB */
    private String gisAssetsLnmSub;

    /** GIS_ASSETS_AR */
    private java.math.BigDecimal gisAssetsAr;

    /** GIS_ASSETS_USAGE_YN */
    private String gisAssetsUsageYn;
    public String getGisAssetsNm() {
        return this.gisAssetsNm;
    }


    public String getGisAssetsCd() {
		return gisAssetsCd;
	}


	public void setGisAssetsCd(String gisAssetsCd) {
		this.gisAssetsCd = gisAssetsCd;
	}


	public String getGisAssetsSubCd() {
		return gisAssetsSubCd;
	}


	public void setGisAssetsSubCd(String gisAssetsSubCd) {
		this.gisAssetsSubCd = gisAssetsSubCd;
	}


	public void setGisAssetsNm(String gisAssetsNm) {
        this.gisAssetsNm = gisAssetsNm;
    }

    public String getGisAssetsMngDeptCd() {
        return this.gisAssetsMngDeptCd;
    }

    public void setGisAssetsMngDeptCd(String gisAssetsMngDeptCd) {
        this.gisAssetsMngDeptCd = gisAssetsMngDeptCd;
    }

    public String getGisAssetsOperDeptCd() {
        return this.gisAssetsOperDeptCd;
    }

    public void setGisAssetsOperDeptCd(String gisAssetsOperDeptCd) {
        this.gisAssetsOperDeptCd = gisAssetsOperDeptCd;
    }

    public String getGisAssetsLocplc() {
        return this.gisAssetsLocplc;
    }

    public void setGisAssetsLocplc(String gisAssetsLocplc) {
        this.gisAssetsLocplc = gisAssetsLocplc;
    }

    public String getGisAssetsLnm() {
        return this.gisAssetsLnm;
    }

    public void setGisAssetsLnm(String gisAssetsLnm) {
        this.gisAssetsLnm = gisAssetsLnm;
    }

    public String getGisAssetsLnmSub() {
        return this.gisAssetsLnmSub;
    }

    public void setGisAssetsLnmSub(String gisAssetsLnmSub) {
        this.gisAssetsLnmSub = gisAssetsLnmSub;
    }

    public String getGisAssetsPrtAtCode() {
        return this.gisAssetsPrtAtCode;
    }

    public void setGisAssetsPrtAtCode(String gisAssetsPrtAtCode) {
        this.gisAssetsPrtAtCode = gisAssetsPrtAtCode;
    }

    public java.math.BigDecimal getGisAssetsAr() {
        return this.gisAssetsAr;
    }

    public void setGisAssetsAr(java.math.BigDecimal gisAssetsAr) {
        this.gisAssetsAr = gisAssetsAr;
    }

    public String getGisAssetsUsageYn() {
        return this.gisAssetsUsageYn;
    }

    public void setGisAssetsUsageYn(String gisAssetsUsageYn) {
        this.gisAssetsUsageYn = gisAssetsUsageYn;
    }

	public String getGisAssetsCode() {
		return gisAssetsCode;
	}

	public void setGisAssetsCode(String gisAssetsCode) {
		this.gisAssetsCode = gisAssetsCode;
	}

}
