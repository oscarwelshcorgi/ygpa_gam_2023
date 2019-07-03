/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author 源�?ъ쿋
 * @since 2019. 7. 2.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2019. 7. 2.		源�?ъ쿋		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldQuGtqyVO {
	/** 검색 연도 */ 
	private String sYear;
	
	/** 연도 */ 
	private String year;
	/** 1분기 일반 */ 
	private String oneQuGnrl;
	/** 1분기 컨테이너 */ 
	private String oneQuCntanr;
	/** 2분기 일반 */ 
	private String twoQuGnrl;
	/** 2분기 컨테이너 */ 
	private String twoQuCntanr;
	/** 3분기 일반 */ 
	private String threeQuGnrl;
	/** 3분기 컨테이너 */ 
	private String threeQuCntanr;
	/** 4분기 일반 */ 
	private String fourGnrl;
	/** 4분기 컨테이너 */ 
	private String fourQuCntanr;
	/** 등록자 */ 
	private String register;
	/** 등록일 */ 
	private String rgsde;
	/** 수정자 */ 
	private String updusr;
	/** 수정일 */ 
	private String updde;
	/**
	 * @return the sYear
	 */
	public String getsYear() {
		return sYear;
	}
	/**
	 * @param sYear the sYear to set
	 */
	public void setsYear(String sYear) {
		this.sYear = sYear;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the oneQuGnrl
	 */
	public String getOneQuGnrl() {
		return oneQuGnrl;
	}
	/**
	 * @param oneQuGnrl the oneQuGnrl to set
	 */
	public void setOneQuGnrl(String oneQuGnrl) {
		this.oneQuGnrl = oneQuGnrl;
	}
	/**
	 * @return the oneQuCntanr
	 */
	public String getOneQuCntanr() {
		return oneQuCntanr;
	}
	/**
	 * @param oneQuCntanr the oneQuCntanr to set
	 */
	public void setOneQuCntanr(String oneQuCntanr) {
		this.oneQuCntanr = oneQuCntanr;
	}
	/**
	 * @return the twoQuGnrl
	 */
	public String getTwoQuGnrl() {
		return twoQuGnrl;
	}
	/**
	 * @param twoQuGnrl the twoQuGnrl to set
	 */
	public void setTwoQuGnrl(String twoQuGnrl) {
		this.twoQuGnrl = twoQuGnrl;
	}
	/**
	 * @return the twoQuCntanr
	 */
	public String getTwoQuCntanr() {
		return twoQuCntanr;
	}
	/**
	 * @param twoQuCntanr the twoQuCntanr to set
	 */
	public void setTwoQuCntanr(String twoQuCntanr) {
		this.twoQuCntanr = twoQuCntanr;
	}
	/**
	 * @return the threeQuGnrl
	 */
	public String getThreeQuGnrl() {
		return threeQuGnrl;
	}
	/**
	 * @param threeQuGnrl the threeQuGnrl to set
	 */
	public void setThreeQuGnrl(String threeQuGnrl) {
		this.threeQuGnrl = threeQuGnrl;
	}
	/**
	 * @return the threeQuCntanr
	 */
	public String getThreeQuCntanr() {
		return threeQuCntanr;
	}
	/**
	 * @param threeQuCntanr the threeQuCntanr to set
	 */
	public void setThreeQuCntanr(String threeQuCntanr) {
		this.threeQuCntanr = threeQuCntanr;
	}
	/**
	 * @return the fourGnrl
	 */
	public String getFourGnrl() {
		return fourGnrl;
	}
	/**
	 * @param fourGnrl the fourGnrl to set
	 */
	public void setFourGnrl(String fourGnrl) {
		this.fourGnrl = fourGnrl;
	}
	/**
	 * @return the fourQuCntanr
	 */
	public String getFourQuCntanr() {
		return fourQuCntanr;
	}
	/**
	 * @param fourQuCntanr the fourQuCntanr to set
	 */
	public void setFourQuCntanr(String fourQuCntanr) {
		this.fourQuCntanr = fourQuCntanr;
	}
	/**
	 * @return the register
	 */
	public String getRegister() {
		return register;
	}
	/**
	 * @param register the register to set
	 */
	public void setRegister(String register) {
		this.register = register;
	}
	/**
	 * @return the rgsde
	 */
	public String getRgsde() {
		return rgsde;
	}
	/**
	 * @param rgsde the rgsde to set
	 */
	public void setRgsde(String rgsde) {
		this.rgsde = rgsde;
	}
	/**
	 * @return the updusr
	 */
	public String getUpdusr() {
		return updusr;
	}
	/**
	 * @param updusr the updusr to set
	 */
	public void setUpdusr(String updusr) {
		this.updusr = updusr;
	}
	/**
	 * @return the updde
	 */
	public String getUpdde() {
		return updde;
	}
	/**
	 * @param updde the updde to set
	 */
	public void setUpdde(String updde) {
		this.updde = updde;
	}

	
	
}
