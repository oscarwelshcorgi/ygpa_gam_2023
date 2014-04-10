package egovframework.rte.ygpa.gam.port_mis.service;

import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;

/**
 * @Class Name : GamCustTpSalesSttutsCreatVO.java
 * @Description : 고객군들통계(포트미스정보) DAO Class
 * @Modification Information
 *
 * @author 김종민
 * @since 2014-04-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamCustTpSalesSttutsCreatVO extends ErpCmmnCdDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** 항코드  */
    private String prtAtCode;
    
    /** 업체코드 */
    private String entrpsCd;
    
    /** 생성년월 */
    private String yrMt;
    
    /** 요금종류코드 */
    private String chrgeKndCd;
    
    /** 매출액 */
    private String costVal;
    
    /** 수정자 */
    private String updUsr;
    
    /** 수정일시 */
    private String updtDt;
    
    /** 통계 생성 리턴 값 */
    private String result;

    /** 조회 시작년월 */
    private String startYrMt;
    
    /** 조회 끝년월 */
    private String endYrMt;
    
	/**
	 * @return the prtAtCode
	 */
	public String getPrtAtCode() {
		return prtAtCode;
	}

	/**
	 * @param prtAtCode the prtAtCode to set
	 */
	public void setPrtAtCode(String prtAtCode) {
		this.prtAtCode = prtAtCode;
	}

	/**
	 * @return the entrpsCd
	 */
	public String getEntrpsCd() {
		return entrpsCd;
	}

	/**
	 * @param entRpsCd the entrpsCd to set
	 */
	public void setEntrpsCd(String entrpsCd) {
		this.entrpsCd = entrpsCd;
	}

	/**
	 * @return the yrMt
	 */
	public String getYrMt() {
		return yrMt;
	}

	/**
	 * @param yrMt the yrMt to set
	 */
	public void setYrMt(String yrMt) {
		this.yrMt = yrMt;
	}

	/**
	 * @return the chrgeKndCd
	 */
	public String getChrgeKndCd() {
		return chrgeKndCd;
	}

	/**
	 * @param chrgeKndCo the chrgeKndCo to set
	 */
	public void setChrgeKndCd(String chrgeKndCd) {
		this.chrgeKndCd = chrgeKndCd;
	}

	/**
	 * @return the costVal
	 */
	public String getCostVal() {
		return costVal;
	}

	/**
	 * @param costVal the costVal to set
	 */
	public void setCostVal(String costVal) {
		this.costVal = costVal;
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
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the startYrMt
	 */
	public String getStartYrMt() {
		return startYrMt;
	}

	/**
	 * @param startYrMt the startYrMt to set
	 */
	public void setStartYrMt(String startYrMt) {
		this.startYrMt = startYrMt;
	}

	/**
	 * @return the endYrMt
	 */
	public String getEndYrMt() {
		return endYrMt;
	}

	/**
	 * @param endYrMt the endYrMt to set
	 */
	public void setEndYrMt(String endYrMt) {
		this.endYrMt = endYrMt;
	}
}
