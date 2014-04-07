package egovframework.rte.ygpa.gam.code.service;

/**
 * @Class Name : GamAssetDisUseMngtVO.java
 * @Description : 자산폐기등록 VO class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public class GamGisAssetPhotoVO extends GamGisAssetPhotoDefaultVO {
    private static final long serialVersionUID = 1L;

    /**
     * 항코드
     */
    private String gisAssetsPrtAtCode;

    /** GIS 자산 SUB 코드 */
    private String gisAssetsSubCd;

    /** GIS 자산 코드 */
    private String gisAssetsCd;

    /**
     * 자산 사진 순번
     */
    private String photoSeq;

    /** GIS 자산 사진 제목 */
    private String photoSj;

    /**
     * 물리 파일 명
     */
    private String filenmPhysicl;

    /**
     * 논리 파일 명
     */
    private String filenmLogic;

    /**
     * 촬영 일시
     */
    private String shotDt;

    /** 등록자 */
    private String regUsr;

    /** 등록일자 */
    private String registdt;

    /** 수정자 */
    private String updUsr;

    /** 수정일자 */
    private String updtdt;

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

	public String getPhotoSeq() {
		return photoSeq;
	}

	public void setPhotoSeq(String photoSeq) {
		this.photoSeq = photoSeq;
	}

	public String getPhotoSj() {
		return photoSj;
	}

	public void setPhotoSj(String photoSj) {
		this.photoSj = photoSj;
	}

	public String getFilenmPhysicl() {
		return filenmPhysicl;
	}

	public void setFilenmPhysicl(String filenmPhysicl) {
		this.filenmPhysicl = filenmPhysicl;
	}

	public String getFilenmLogic() {
		return filenmLogic;
	}

	public void setFilenmLogic(String filenmLogic) {
		this.filenmLogic = filenmLogic;
	}

	public String getShotDt() {
		return shotDt;
	}

	public void setShotDt(String shotDt) {
		this.shotDt = shotDt;
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
	 * @return the registdt
	 */
	public String getRegistdt() {
		return registdt;
	}

	/**
	 * @param registdt the registdt to set
	 */
	public void setRegistdt(String registdt) {
		this.registdt = registdt;
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
	 * @return the updtdt
	 */
	public String getUpdtdt() {
		return updtdt;
	}

	/**
	 * @param updtdt the updtdt to set
	 */
	public void setUpdtdt(String updtdt) {
		this.updtdt = updtdt;
	}

	public String getGisAssetsPrtAtCode() {
		return gisAssetsPrtAtCode;
	}

	public void setGisAssetsPrtAtCode(String gisAssetsPrtAtCode) {
		this.gisAssetsPrtAtCode = gisAssetsPrtAtCode;
	}

}
