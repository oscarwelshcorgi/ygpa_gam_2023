/**
 *
 */
package egovframework.rte.ygpa.gam.code.service;

import egovframework.rte.ygpa.gam.cmmn.service.GamDefaultVO;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 4. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 4. 10.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamBupjungdongOlnlpVO extends GamDefaultVO {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 공시지가 적용년도
	 */
	private String olnlpApplcYear;

	/**
	 * 법정동 코드
	 */
	private String bupjungdongCd;

	/**
	 * 산 여부 (Y or empty)
	 */
	private String mt;

	/**
	 * 지번
	 */
	private String lnm;

	/**
	 * 적용시작일자
	 */
	private String beginDt;

	/**
	 * 적용 종료일자
	 */
	private String endDt;

	/**
	 * 공시지가
	 */
	private Integer olnlp;

	/**
	 * 등록자
	 */
	private String regUsr;

	/**
	 * 등록일자
	 */
	private String registDt;

	public String getOlnlpApplcYear() {
		return olnlpApplcYear;
	}

	public void setOlnlpApplcYear(String olnlpApplcYear) {
		this.olnlpApplcYear = olnlpApplcYear;
	}

	public String getBupjungdongCd() {
		return bupjungdongCd;
	}

	public void setBupjungdongCd(String bupjungdongCd) {
		this.bupjungdongCd = bupjungdongCd;
	}

	public String getBeginDt() {
		return beginDt;
	}

	public void setBeginDt(String beginDt) {
		this.beginDt = beginDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public Integer getOlnlp() {
		return olnlp;
	}

	public void setOlnlp(Integer olnlp) {
		this.olnlp = olnlp;
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

	public String getLnm() {
		return lnm;
	}

	public void setLnm(String lotcode) {
		this.lnm = lotcode;
	}

	public String getMt() {
		return mt;
	}

	public void setMt(String mt) {
		this.mt = mt;
	}

}
