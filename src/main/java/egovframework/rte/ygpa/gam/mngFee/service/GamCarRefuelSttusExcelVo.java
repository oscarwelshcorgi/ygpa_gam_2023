/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import egovframework.com.cmm.ComDefaultVO;
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

public class GamCarRefuelSttusExcelVo extends GamDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String	carRegistNo;	//차량 등록 번호
	private	String	refuelMt;		//주유월
	private	String	refuelQy;		//주유량
	private	String	regUsr;			//등록자

	/**
	 * @return the carRegistNo
	 */
	public String getCarRegistNo() {
		return carRegistNo;
	}
	/**
	 * @param carRegistNo the carRegistNo to set
	 */
	public void setCarRegistNo(String carRegistNo) {
		this.carRegistNo = carRegistNo;
	}
	/**
	 * @return the refuelMt
	 */
	public String getRefuelMt() {
		return refuelMt;
	}
	/**
	 * @param refuelMt the refuelMt to set
	 */
	public void setRefuelMt(String refuelMt) {
		this.refuelMt = refuelMt;
	}
	/**
	 * @return the refuelQy
	 */
	public String getRefuelQy() {
		return refuelQy;
	}
	/**
	 * @param refuelQy the refuelQy to set
	 */
	public void setRefuelQy(String refuelQy) {
		this.refuelQy = refuelQy;
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
