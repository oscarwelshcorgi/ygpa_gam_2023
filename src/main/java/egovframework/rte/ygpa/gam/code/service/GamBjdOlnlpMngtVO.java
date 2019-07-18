/**
 *
 */
package egovframework.rte.ygpa.gam.code.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author EUNSUNGJ
 * @since 2015. 4. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 4. 15.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamBjdOlnlpMngtVO {
	/**
	 * 검색 조건
	 */
	private String searchLoc;
	private String searchLnm;
	private String searchLnmSub;
	
	/**
	 * @return the searchLoc
	 */
	public String getSearchLoc() {
		return searchLoc;
	}
	/**
	 * @param searchLoc the searchLoc to set
	 */
	public void setSearchLoc(String searchLoc) {
		this.searchLoc = searchLoc;
	}
	/**
	 * @return the searchLnm
	 */
	public String getSearchLnm() {
		return searchLnm;
	}
	/**
	 * @param searchLnm the searchLnm to set
	 */
	public void setSearchLnm(String searchLnm) {
		this.searchLnm = searchLnm;
	}
	/**
	 * @return the searchLnmSub
	 */
	public String getSearchLnmSub() {
		return searchLnmSub;
	}
	/**
	 * @param searchLnmSub the searchLnmSub to set
	 */
	public void setSearchLnmSub(String searchLnmSub) {
		this.searchLnmSub = searchLnmSub;
	}
	/**
	 * 기준년도
	 */
	private String olnlpApplcYear;
	/**
	 * 소재지
	 */
	private String loc;
	/**
	 * 산
	 */
	private String mt;
	/**
	 * 지번
	 */
	private String lnm;
	/**
	 * 부지번
	 */
	private String lnmSub;
	/**
	 * 공시지가
	 */
	private String olnlp;
	/**
	 * @return the loc
	 */
	public String getLoc() {
		return loc;
	}
	/**
	 * @param loc the loc to set
	 */
	public void setLoc(String loc) {
		this.loc = loc;
	}
	/**
	 * @return the lnm
	 */
	public String getLnm() {
		return lnm;
	}
	/**
	 * @param lnm the lnm to set
	 */
	public void setLnm(String lnm) {
		this.lnm = lnm;
	}
	/**
	 * @return the lnmSub
	 */
	public String getLnmSub() {
		return lnmSub;
	}
	/**
	 * @param lnmSub the lnmSub to set
	 */
	public void setLnmSub(String lnmSub) {
		this.lnmSub = lnmSub;
	}
	/**
	 * @return the olnlp
	 */
	public String getOlnlp() {
		return olnlp;
	}
	/**
	 * @param olnlp the olnlp to set
	 */
	public void setOlnlp(String olnlp) {
		this.olnlp = olnlp;
	}
	/**
	 * @return the olnlpApplcYear
	 */
	public String getOlnlpApplcYear() {
		return olnlpApplcYear;
	}
	/**
	 * @param olnlpApplcYear the olnlpApplcYear to set
	 */
	public void setOlnlpApplcYear(String olnlpApplcYear) {
		this.olnlpApplcYear = olnlpApplcYear;
	}
	/**
	 * @return the mt
	 */
	public String getMt() {
		return mt;
	}
	/**
	 * @param mt the mt to set
	 */
	public void setMt(String mt) {
		this.mt = mt;
	}

}
