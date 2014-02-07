package egovframework.rte.ygpa.gam.asset.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamCmpyRecvStsInqireVO.java
 * @Description : 업체별수입현황조회 VO class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamCmpyRecvStsInqireVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** 수납일from */
    private String sRcivDtFrom;
	
    /** 수납일 to */
    private String sRcivDtTo;

	/**
	 * @return the sRcivDtFrom
	 */
	public String getsRcivDtFrom() {
		return sRcivDtFrom;
	}

	/**
	 * @param sRcivDtFrom the sRcivDtFrom to set
	 */
	public void setsRcivDtFrom(String sRcivDtFrom) {
		this.sRcivDtFrom = sRcivDtFrom;
	}

	/**
	 * @return the sRcivDtTo
	 */
	public String getsRcivDtTo() {
		return sRcivDtTo;
	}

	/**
	 * @param sRcivDtTo the sRcivDtTo to set
	 */
	public void setsRcivDtTo(String sRcivDtTo) {
		this.sRcivDtTo = sRcivDtTo;
	}
    
}
