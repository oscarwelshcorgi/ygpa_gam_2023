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

}
