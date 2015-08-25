package egovframework.rte.ygpa.gam.asset.rent.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamAssetPopupInqireVO.java
 * @Description : 자산정보현황알림 VO class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public class GamAssetPopupInqireVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;

    /** 자산임대(허가여부 'N' 건수) */
    private String prmisnYnCnt;

    /** 징수의뢰(오늘날짜 기준에 해당하는  고지기간) */
    private String nticPdCnt;

    /** 세입징수(수납구분 'N' 건수) */
    private String rcivSeCnt;

    /**
     * 연체 고지 건수
     */
    private String overNticCnt;

    /** 세입징수(수납구분 'N' 건수) */
    private String nhtIsueCnt;

    /** 부서코드 */
    private String sDeptcd;


	/**
	 * @return the prmisnYnCnt
	 */
	public String getPrmisnYnCnt() {
		return prmisnYnCnt;
	}

	/**
	 * @param prmisnYnCnt the prmisnYnCnt to set
	 */
	public void setPrmisnYnCnt(String prmisnYnCnt) {
		this.prmisnYnCnt = prmisnYnCnt;
	}

	/**
	 * @return the nticPdCnt
	 */
	public String getNticPdCnt() {
		return nticPdCnt;
	}

	/**
	 * @param nticPdCnt the nticPdCnt to set
	 */
	public void setNticPdCnt(String nticPdCnt) {
		this.nticPdCnt = nticPdCnt;
	}

	/**
	 * @return the rcivSeCnt
	 */
	public String getRcivSeCnt() {
		return rcivSeCnt;
	}

	/**
	 * @param rcivSeCnt the rcivSeCnt to set
	 */
	public void setRcivSeCnt(String rcivSeCnt) {
		this.rcivSeCnt = rcivSeCnt;
	}

	/**
	 * @return the nhtIsueCnt
	 */
	public String getNhtIsueCnt() {
		return nhtIsueCnt;
	}

	/**
	 * @param nhtIsueCnt the nhtIsueCnt to set
	 */
	public void setNhtIsueCnt(String nhtIsueCnt) {
		this.nhtIsueCnt = nhtIsueCnt;
	}

	/**
	 * @return the sDeptcd
	 */
	public String getsDeptcd() {
		return sDeptcd;
	}

	/**
	 * @param sDeptcd the sDeptcd to set
	 */
	public void setsDeptcd(String sDeptcd) {
		this.sDeptcd = sDeptcd;
	}

	/**
	 * @return the overNticCnt
	 */
	public String getOverNticCnt() {
		return overNticCnt;
	}

	/**
	 * @param overNticCnt the overNticCnt to set
	 */
	public void setOverNticCnt(String overNticCnt) {
		this.overNticCnt = overNticCnt;
	}

}
