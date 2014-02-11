package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamPopupEntrpsInfoVO.java
 * @Description : 업체정보 VO class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamPopupEntrpsInfoVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** 업체코드 */
    private String entrpscd;	

    /** 업체 명 */
    private String entrpsNm;	
    
    /** 업체 유형 */
    private String entrpsTy;	
    
    /** 사업자 구분 */
    private String bsnmSe;	    
    
    /** 대표자 명 */
    private String rprsntvNm;	
    
    /** 사업자등록번호 */
    private String bizrno;	    
    
    /** 법인등록번호 */
    private String cprregistno; 
    
    /** 업종 */
    private String induty;	    
    
    /** 업태 */
    private String bizcnd;	    
    
    /** 전화번호 */
    private String tlphonNo;	
    
    /** 우편번호 */
    private String zip;	        
    
    /** 주소 */
    private String adres;	    
    
    /** 등록자 */
    private String regUsr;	    
    
    /** 등록일시 */
    private String registDt;	
    
    /** 수정자 */
    private String updUsr;	    
    
    /** 수정일시 */
    private String updtDt;	    
    
    /** 팩스 */
    private String fax;

	/**
	 * @return the entrpscd
	 */
	public String getEntrpscd() {
		return entrpscd;
	}

	/**
	 * @param entrpscd the entrpscd to set
	 */
	public void setEntrpscd(String entrpscd) {
		this.entrpscd = entrpscd;
	}

	/**
	 * @return the entrpsNm
	 */
	public String getEntrpsNm() {
		return entrpsNm;
	}

	/**
	 * @param entrpsNm the entrpsNm to set
	 */
	public void setEntrpsNm(String entrpsNm) {
		this.entrpsNm = entrpsNm;
	}

	/**
	 * @return the entrpsTy
	 */
	public String getEntrpsTy() {
		return entrpsTy;
	}

	/**
	 * @param entrpsTy the entrpsTy to set
	 */
	public void setEntrpsTy(String entrpsTy) {
		this.entrpsTy = entrpsTy;
	}

	/**
	 * @return the bsnmSe
	 */
	public String getBsnmSe() {
		return bsnmSe;
	}

	/**
	 * @param bsnmSe the bsnmSe to set
	 */
	public void setBsnmSe(String bsnmSe) {
		this.bsnmSe = bsnmSe;
	}

	/**
	 * @return the rprsntvNm
	 */
	public String getRprsntvNm() {
		return rprsntvNm;
	}

	/**
	 * @param rprsntvNm the rprsntvNm to set
	 */
	public void setRprsntvNm(String rprsntvNm) {
		this.rprsntvNm = rprsntvNm;
	}

	/**
	 * @return the bizrno
	 */
	public String getBizrno() {
		return bizrno;
	}

	/**
	 * @param bizrno the bizrno to set
	 */
	public void setBizrno(String bizrno) {
		this.bizrno = bizrno;
	}

	/**
	 * @return the cprregistno
	 */
	public String getCprregistno() {
		return cprregistno;
	}

	/**
	 * @param cprregistno the cprregistno to set
	 */
	public void setCprregistno(String cprregistno) {
		this.cprregistno = cprregistno;
	}

	/**
	 * @return the induty
	 */
	public String getInduty() {
		return induty;
	}

	/**
	 * @param induty the induty to set
	 */
	public void setInduty(String induty) {
		this.induty = induty;
	}

	/**
	 * @return the bizcnd
	 */
	public String getBizcnd() {
		return bizcnd;
	}

	/**
	 * @param bizcnd the bizcnd to set
	 */
	public void setBizcnd(String bizcnd) {
		this.bizcnd = bizcnd;
	}

	/**
	 * @return the tlphonNo
	 */
	public String getTlphonNo() {
		return tlphonNo;
	}

	/**
	 * @param tlphonNo the tlphonNo to set
	 */
	public void setTlphonNo(String tlphonNo) {
		this.tlphonNo = tlphonNo;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the adres
	 */
	public String getAdres() {
		return adres;
	}

	/**
	 * @param adres the adres to set
	 */
	public void setAdres(String adres) {
		this.adres = adres;
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
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
    
	
}
