package egovframework.rte.ygpa.gam.fclty.service;

import egovframework.com.cmm.ComDefaultVO;

public class GamFcltyDrwInfoFVO extends ComDefaultVO{
	private static final long serialVersionUID = 1L;
    /** 도면 목록 등록 년도 */
    private String drwLstRegistYear;

    /** 도면 목록 순번 */
    private String drwLstSeq;

    /** 도면 목록 명 */
    private String drwLstNm;

    /** 도면 목록 관리 부서 코드 */
    private String drwLstMngDeptCd;

    /** 등록자 */
    private String regUsr;

    /** 등록일시 */
    private String registDt;

    /** 도면 목록 구분 코드 */
    private String drwLstSeCd;

    /** 도면 목록 GIS 코드 */
    private String drwLstGisCd;

    /** 공사명 */
    private String authnm;

    /** 제출자 */
    private String sbmNm;
    
    /** 검토자 */
    private String exmNm;
    
    /** 시공자 */
    private String cnstrtr;

    /**  */
    private String scl;

    
    /**
	 * @return the authnm
	 */
	public String getAuthnm() {
		return authnm;
	}

	/**
	 * @param authnm the authnm to set
	 */
	public void setAuthnm(String authnm) {
		this.authnm = authnm;
	}

	/**
	 * @return the sbmNm
	 */
	public String getSbmNm() {
		return sbmNm;
	}

	/**
	 * @param sbmNm the sbmNm to set
	 */
	public void setSbmNm(String sbmNm) {
		this.sbmNm = sbmNm;
	}

	/**
	 * @return the exmNm
	 */
	public String getExmNm() {
		return exmNm;
	}

	/**
	 * @param exmNm the exmNm to set
	 */
	public void setExmNm(String exmNm) {
		this.exmNm = exmNm;
	}

	/**
	 * @return the cnstrtr
	 */
	public String getCnstrtr() {
		return cnstrtr;
	}

	/**
	 * @param cnstrtr the cnstrtr to set
	 */
	public void setCnstrtr(String cnstrtr) {
		this.cnstrtr = cnstrtr;
	}

	/**
	 * @return the scl
	 */
	public String getScl() {
		return scl;
	}

	/**
	 * @param scl the scl to set
	 */
	public void setScl(String scl) {
		this.scl = scl;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public String getDrwLstRegistYear() {
        return drwLstRegistYear;
    }

    public void setDrwLstRegistYear(String drwLstRegistYear) {
        this.drwLstRegistYear = drwLstRegistYear;
    }

    public String getDrwLstSeq() {
        return drwLstSeq;
    }

    public void setDrwLstSeq(String drwLstSeq) {
        this.drwLstSeq = drwLstSeq;
    }

    public String getDrwLstNm() {
        return drwLstNm;
    }

    public void setDrwLstNm(String drwLstNm) {
        this.drwLstNm = drwLstNm;
    }

    public String getDrwLstMngDeptCd() {
        return drwLstMngDeptCd;
    }

    public void setDrwLstMngDeptCd(String drwLstMngDeptCd) {
        this.drwLstMngDeptCd = drwLstMngDeptCd;
    }

    public String getRegUsr() {
        return regUsr;
    }

    public void setRegUsr(String regUsr) {
        this.regUsr = regUsr;
    }

    public String getRegistDt() {
        return registDt;
    }

    public void setRegistDt(String registDt) {
        this.registDt = registDt;
    }

    public String getDrwLstSeCd() {
        return drwLstSeCd;
    }

    public void setDrwLstSeCd(String drwLstSeCd) {
        this.drwLstSeCd = drwLstSeCd;
    }

    public String getDrwLstGisCd() {
        return drwLstGisCd;
    }

    public void setDrwLstGisCd(String drwLstGisCd) {
        this.drwLstGisCd = drwLstGisCd;
    }
}