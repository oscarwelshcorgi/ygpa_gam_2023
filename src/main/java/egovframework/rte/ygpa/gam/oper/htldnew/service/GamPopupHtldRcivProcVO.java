/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 15.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamPopupHtldRcivProcVO extends GamHtldRentNticDefaultVO{
	private static final long serialVersionUID = 1L;
	
	private String rcivSe;				/** 수납구분 */
	private String rcivDt;				/** 수납일자 */
	private String rcvdTp;				/** REV_COLL_F 용 수납구분 */
	private String rcvdDt;				/** REV_COLL_F 용 수납일자 */
	private String rm;					/** 비고 */
	private String updUsr;			 	/** 수정자 */
	/**
	 * @return the rcivSe
	 */
	public String getRcivSe() {
		return rcivSe;
	}
	/**
	 * @param rcivSe the rcivSe to set
	 */
	public void setRcivSe(String rcivSe) {
		this.rcivSe = rcivSe;
	}
	/**
	 * @return the rcivDt
	 */
	public String getRcivDt() {
		return rcivDt;
	}
	/**
	 * @param rcivDt the rcivDt to set
	 */
	public void setRcivDt(String rcivDt) {
		this.rcivDt = rcivDt;
	}
	/**
	 * @return the rcvdTp
	 */
	public String getRcvdTp() {
		return rcvdTp;
	}
	/**
	 * @param rcvdTp the rcvdTp to set
	 */
	public void setRcvdTp(String rcvdTp) {
		this.rcvdTp = rcvdTp;
	}
	/**
	 * @return the rcvdDt
	 */
	public String getRcvdDt() {
		return rcvdDt;
	}
	/**
	 * @param rcvdDt the rcvdDt to set
	 */
	public void setRcvdDt(String rcvdDt) {
		this.rcvdDt = rcvdDt;
	}
	/**
	 * @return the rm
	 */
	public String getRm() {
		return rm;
	}
	/**
	 * @param rm the rm to set
	 */
	public void setRm(String rm) {
		this.rm = rm;
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
}
