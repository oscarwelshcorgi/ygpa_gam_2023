/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import egovframework.rte.ygpa.gam.cmmn.service.GamDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 17.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamElctyUsageSttusExcelVo extends GamDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String	usageMt;			//사용 년월
	private	String	mngFeeFcltyCd;		//관리비 시설 코드
	private	String	prevMtUsageQy;		//전월 사용 량
	private	String	saidMtUsageQy;		//당월 사용 량
	private	String	applcCoef;			//적용 계수
	private	String	regUsr;				//등록자

	/**
	 * @return the usageMt
	 */
	public String getUsageMt() {
		return usageMt;
	}
	/**
	 * @param usageMt the usageMt to set
	 */
	public void setUsageMt(String usageMt) {
		this.usageMt = usageMt;
	}
	/**
	 * @return the mngFeeFcltyCd
	 */
	public String getMngFeeFcltyCd() {
		return mngFeeFcltyCd;
	}
	/**
	 * @param mngFeeFcltyCd the mngFeeFcltyCd to set
	 */
	public void setMngFeeFcltyCd(String mngFeeFcltyCd) {
		this.mngFeeFcltyCd = mngFeeFcltyCd;
	}
	/**
	 * @return the prevMtUsageQy
	 */
	public String getPrevMtUsageQy() {
		return prevMtUsageQy;
	}
	/**
	 * @param prevMtUsageQy the prevMtUsageQy to set
	 */
	public void setPrevMtUsageQy(String prevMtUsageQy) {
		this.prevMtUsageQy = prevMtUsageQy;
	}
	/**
	 * @return the saidMtUsageQy
	 */
	public String getSaidMtUsageQy() {
		return saidMtUsageQy;
	}
	/**
	 * @param saidMtUsageQy the saidMtUsageQy to set
	 */
	public void setSaidMtUsageQy(String saidMtUsageQy) {
		this.saidMtUsageQy = saidMtUsageQy;
	}
	/**
	 * @return the applcCoef
	 */
	public String getApplcCoef() {
		return applcCoef;
	}
	/**
	 * @param applcCoef the applcCoef to set
	 */
	public void setApplcCoef(String applcCoef) {
		this.applcCoef = applcCoef;
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

}
