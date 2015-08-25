package egovframework.rte.ygpa.gam.oper.gnrl.service;

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
public class GamPrtFcltyPopupInqireVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;

    /** 자산사용 현황 건수 */
    private String currentUseCnt;

    /**
     * 고지 도래 건수
     */
    private String nticPdCnt;

    /**
     * 미납 자료 건수
     */
    private String overDueCnt;


    /**
     * 연체 고지 자료 건수
     */
    private String overNticCnt;

    /**
     * 계약 만료 자료 건수
     */
    private String closeEndContract;


    /** 부서코드 */
    private String sDeptcd;


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


	/**
	 * @return the currentUseCnt
	 */
	public String getCurrentUseCnt() {
		return currentUseCnt;
	}

	/**
	 * @param currentUseCnt the currentUseCnt to set
	 */
	public void setCurrentUseCnt(String currentUseCnt) {
		this.currentUseCnt = currentUseCnt;
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
	 * @return the overDueCnt
	 */
	public String getOverDueCnt() {
		return overDueCnt;
	}

	/**
	 * @param overDueCnt the overDueCnt to set
	 */
	public void setOverDueCnt(String overDueCnt) {
		this.overDueCnt = overDueCnt;
	}

	/**
	 * @return the closeEndContract
	 */
	public String getCloseEndContract() {
		return closeEndContract;
	}

	/**
	 * @param closeEndContract the closeEndContract to set
	 */
	public void setCloseEndContract(String closeEndContract) {
		this.closeEndContract = closeEndContract;
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

}
