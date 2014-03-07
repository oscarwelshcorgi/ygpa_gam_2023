package egovframework.rte.ygpa.gam.fclty.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * Class Name : GamFcltyManageVO.java
 * @author kok
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltyManageVO extends ComDefaultVO{

	private static final long serialVersionUID = 1L;

    /** GIS 항만 시설 코드 */
    private String gisPrtFcltyCd;

	/** GIS 항만 시설 순번 */
    private String gisPrtFcltySeq;
    
    /** 항만시설 명 */
    private String prtFcltyNm;
    
    /** 항만시설 규격 */
    private String prtFcltyStndrd;
    
    /** 항만시설 단위 */
    private String prtFcltyUnit;
    
    /** 항만시설 설치 일자 */
    private String prtFcltyInstlDt;
    
    /** 항만시설 변경 일자 */
    private String prtFcltyChangeDt;
    
    /** 등록자 */
    private String regUsr;
    
    /** 등록일시 */
    private String registDt;
    
    /** 수정자 */
    private String updUsr;
    
    /** 수정일시 */
    private String updtDt;
    
    /** 항만시설 구분 */
    private String prtFcltySe;
    
    /** 항만시설 관리 업체 코드 */
    private String prtFcltyMngEntrpsCd;
    
    /** 항만시설 GIS 코드 */
    private String prtFcltyGisCd;
    
    /** GIS 자산 항코드 */
    private String gisAssetsPrtAtCode;

    /** GIS 자산 코드 */
    private String gisAssetsCd;
    
    /** GIS 자산 SUB 코드 */
    private String gisAssetsSubCd;

    /** 조회 검색 조건 */
    private String searchPrtAtCode;
	private String searchAssetsCd;
    private String searchAssetsSubCd;
    private String searchFcltyCd;
    private String searchFcltySeq;

    // display
    private String gisAssetsDisplay;
    private String gisPrtFcltyDisplay;
    private String gisAssetsLocCd;
    private String gisAssetsLocplc;
	private String gisAssetsLnm;
    private String gisAssetsLnmSub;
    private String gisAssetsNm;
    private String gisAssetsPrtAtName;
    private String prtFcltyMngEntrpsNm;
    
    /**
     * @return the gisAssetsPrtAtName
     */
    public String getGisAssetsPrtAtName() {
    	return gisAssetsPrtAtName;
    }
    
    /**
     * @param gisAssetsPrtAtName the gisAssetsPrtAtName to set
     */
    public void setGisAssetsPrtAtName(String gisAssetsPrtAtName) {
    	this.gisAssetsPrtAtName = gisAssetsPrtAtName;
    }
    
    /**
     * @return the prtFcltyMngEntrpsNm
     */
    public String getPrtFcltyMngEntrpsNm() {
    	return prtFcltyMngEntrpsNm;
    }
    
    /**
     * @param prtFcltyMngEntrpsNm the prtFcltyMngEntrpsNm to set
     */
    public void setPrtFcltyMngEntrpsNm(String prtFcltyMngEntrpsNm) {
    	this.prtFcltyMngEntrpsNm = prtFcltyMngEntrpsNm;
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
	 * @return the gisAssetsLocplc
	 */
	public String getGisAssetsLocplc() {
		return gisAssetsLocplc;
	}
	
	/**
	 * @param gisAssetsLocplc the gisAssetsLocplc to set
	 */
	public void setGisAssetsLocplc(String gisAssetsLocplc) {
		this.gisAssetsLocplc = gisAssetsLocplc;
	}

	/**
	 * @return the gisAssetsLnm
	 */
	public String getGisAssetsLnm() {
		return gisAssetsLnm;
	}

	/**
	 * @param gisAssetsLnm the gisAssetsLnm to set
	 */
	public void setGisAssetsLnm(String gisAssetsLnm) {
		this.gisAssetsLnm = gisAssetsLnm;
	}

	/**
	 * @return the gisAssetsLnmSub
	 */
	public String getGisAssetsLnmSub() {
		return gisAssetsLnmSub;
	}

	/**
	 * @param gisAssetsLnmSub the gisAssetsLnmSub to set
	 */
	public void setGisAssetsLnmSub(String gisAssetsLnmSub) {
		this.gisAssetsLnmSub = gisAssetsLnmSub;
	}
	
    /**
	 * @return the searchPrtAtCode
	 */
	public String getSearchPrtAtCode() {
		return searchPrtAtCode;
	}

	/**
	 * @param searchPrtAtCode the searchPrtAtCode to set
	 */
	public void setSearchPrtAtCode(String searchPrtAtCode) {
		this.searchPrtAtCode = searchPrtAtCode;
	}

	/**
	 * @return the searchAssetsCd
	 */
	public String getSearchAssetsCd() {
		return searchAssetsCd;
	}

	/**
	 * @param searchAssetsCd the searchAssetsCd to set
	 */
	public void setSearchAssetsCd(String searchAssetsCd) {
		this.searchAssetsCd = searchAssetsCd;
	}

	/**
	 * @return the searchAssetsSubCd
	 */
	public String getSearchAssetsSubCd() {
		return searchAssetsSubCd;
	}

	/**
	 * @param searchAssetsSubCd the searchAssetsSubCd to set
	 */
	public void setSearchAssetsSubCd(String searchAssetsSubCd) {
		this.searchAssetsSubCd = searchAssetsSubCd;
	}

	/**
	 * @return the searchFcltyCd
	 */
	public String getSearchFcltyCd() {
		return searchFcltyCd;
	}

	/**
	 * @param searchFcltyCd the searchFcltyCd to set
	 */
	public void setSearchFcltyCd(String searchFcltyCd) {
		this.searchFcltyCd = searchFcltyCd;
	}

	/**
	 * @return the searchFcltySeq
	 */
	public String getSearchFcltySeq() {
		return searchFcltySeq;
	}

	/**
	 * @param searchFcltySeq the searchFcltySeq to set
	 */
	public void setSearchFcltySeq(String searchFcltySeq) {
		this.searchFcltySeq = searchFcltySeq;
	}

	
    /**
	 * @return the gisPrtFcltyDisplay
	 */
	public String getGisPrtFcltyDisplay() {
		return gisPrtFcltyDisplay;
	}

	/**
	 * @param gisPrtFcltyDisplay the gisPrtFcltyDisplay to set
	 */
	public void setGisPrtFcltyDisplay(String gisPrtFcltyDisplay) {
		this.gisPrtFcltyDisplay = gisPrtFcltyDisplay;
	}

	/**
	 * @return the gisAssetsDisplay
	 */
	public String getGisAssetsDisplay() {
		return gisAssetsDisplay;
	}

	/**
	 * @param gisAssetsDisplay the gisAssetsDisplay to set
	 */
	public void setGisAssetsDisplay(String gisAssetsDisplay) {
		this.gisAssetsDisplay = gisAssetsDisplay;
	}

	/**
	 * @return the gisPrtFcltyCd
	 */
	public String getGisPrtFcltyCd() {
		return gisPrtFcltyCd;
	}

	/**
	 * @param gisPrtFcltyCd the gisPrtFcltyCd to set
	 */
	public void setGisPrtFcltyCd(String gisPrtFcltyCd) {
		this.gisPrtFcltyCd = gisPrtFcltyCd;
	}

	/**
	 * @return the gisPrtFcltySeq
	 */
	public String getGisPrtFcltySeq() {
		return gisPrtFcltySeq;
	}

	/**
	 * @param gisPrtFcltySeq the gisPrtFcltySeq to set
	 */
	public void setGisPrtFcltySeq(String gisPrtFcltySeq) {
		this.gisPrtFcltySeq = gisPrtFcltySeq;
	}

	/**
	 * @return the prtFcltyNm
	 */
	public String getPrtFcltyNm() {
		return prtFcltyNm;
	}

	/**
	 * @param prtFcltyNm the prtFcltyNm to set
	 */
	public void setPrtFcltyNm(String prtFcltyNm) {
		this.prtFcltyNm = prtFcltyNm;
	}

	/**
	 * @return the prtFcltyStndrd
	 */
	public String getPrtFcltyStndrd() {
		return prtFcltyStndrd;
	}

	/**
	 * @param prtFcltyStndrd the prtFcltyStndrd to set
	 */
	public void setPrtFcltyStndrd(String prtFcltyStndrd) {
		this.prtFcltyStndrd = prtFcltyStndrd;
	}

	/**
	 * @return the prtFcltyUnit
	 */
	public String getPrtFcltyUnit() {
		return prtFcltyUnit;
	}

	/**
	 * @param prtFcltyUnit the prtFcltyUnit to set
	 */
	public void setPrtFcltyUnit(String prtFcltyUnit) {
		this.prtFcltyUnit = prtFcltyUnit;
	}

	/**
	 * @return the prtFcltyInstlDt
	 */
	public String getPrtFcltyInstlDt() {
		return prtFcltyInstlDt;
	}

	/**
	 * @param prtFcltyInstlDt the prtFcltyInstlDt to set
	 */
	public void setPrtFcltyInstlDt(String prtFcltyInstlDt) {
		this.prtFcltyInstlDt = prtFcltyInstlDt;
	}

	/**
	 * @return the prtFcltyChangeDt
	 */
	public String getPrtFcltyChangeDt() {
		return prtFcltyChangeDt;
	}

	/**
	 * @param prtFcltyChangeDt the prtFcltyChangeDt to set
	 */
	public void setPrtFcltyChangeDt(String prtFcltyChangeDt) {
		this.prtFcltyChangeDt = prtFcltyChangeDt;
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
	 * @return the prtFcltySe
	 */
	public String getPrtFcltySe() {
		return prtFcltySe;
	}

	/**
	 * @param prtFcltySe the prtFcltySe to set
	 */
	public void setPrtFcltySe(String prtFcltySe) {
		this.prtFcltySe = prtFcltySe;
	}

	/**
	 * @return the prtFcltyMngEntrpsCd
	 */
	public String getPrtFcltyMngEntrpsCd() {
		return prtFcltyMngEntrpsCd;
	}

	/**
	 * @param prtFcltyMngEntrpsCd the prtFcltyMngEntrpsCd to set
	 */
	public void setPrtFcltyMngEntrpsCd(String prtFcltyMngEntrpsCd) {
		this.prtFcltyMngEntrpsCd = prtFcltyMngEntrpsCd;
	}

	/**
	 * @return the prtFcltyGisCd
	 */
	public String getPrtFcltyGisCd() {
		return prtFcltyGisCd;
	}

	/**
	 * @param prtFcltyGisCd the prtFcltyGisCd to set
	 */
	public void setPrtFcltyGisCd(String prtFcltyGisCd) {
		this.prtFcltyGisCd = prtFcltyGisCd;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}