package egovframework.rte.ygpa.gam.asset.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamAssetTypeValueStsVO.java
 * @Description : 자산종류별자산가치통계조회 VO class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamAssetTypeValueStsVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    private String sDeprctnYear;

	/**
	 * @return the sDeprctnYear
	 */
	public String getsDeprctnYear() {
		return sDeprctnYear;
	}

	/**
	 * @param sDeprctnYear the sDeprctnYear to set
	 */
	public void setsDeprctnYear(String sDeprctnYear) {
		this.sDeprctnYear = sDeprctnYear;
	}
    
}
