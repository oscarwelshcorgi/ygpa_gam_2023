package egovframework.rte.ygpa.gam.oper.gnrl.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.rte.ygpa.gam.cmmn.service.GamDefaultVO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamPrtFcltyUsageConfmInqireVO extends GamDefaultVO {
    private static final long serialVersionUID = 1L;

    private String rnum;
    private String prtAtCode;
    private String prtAtCodeNm;
    private String entrpsNm;
    private String bizrno;
    private String rprsntvNm;
    private String gisAssetsLocplc;
    private String gisAssetsPrprtySeCdNm;
    private String reqstSeCdNm;
    private String grAr;
    private String grFee;
    private String pass4;
    private String pass3;
    private String pass2;
    private String pass1;
    private String currYearFee;
    private String frstUsagePdFrom;
    private String grUsagePdFrom;
    private String grUsagePdTo;
    private String usageDays;
    private String usagePurps;
    private String prmisnUsrNm;
    private String referYear;


	/**
	 * @return the usagePurps
	 */
	public String getUsagePurps() {
		return usagePurps;
	}
	/**
	 * @param usagePurps the usagePurps to set
	 */
	public void setUsagePurps(String usagePurps) {
		this.usagePurps = usagePurps;
	}
	/**
	 * @return the prmisnUsrNm
	 */
	public String getPrmisnUsrNm() {
		return prmisnUsrNm;
	}
	/**
	 * @param prmisnUsrNm the prmisnUsrNm to set
	 */
	public void setPrmisnUsrNm(String prmisnUsrNm) {
		this.prmisnUsrNm = prmisnUsrNm;
	}
	/**
	 * @return the rnum
	 */
	public String getRnum() {
		return rnum;
	}
	/**
	 * @param rnum the rnum to set
	 */
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	/**
	 * @return the prtAtCode
	 */
	public String getPrtAtCode() {
		return prtAtCode;
	}
	/**
	 * @param prtAtCode the prtAtCode to set
	 */
	public void setPrtAtCode(String prtAtCode) {
		this.prtAtCode = prtAtCode;
	}
	/**
	 * @return the prtAtCodeNm
	 */
	public String getPrtAtCodeNm() {
		return prtAtCodeNm;
	}
	/**
	 * @param prtAtCodeNm the prtAtCodeNm to set
	 */
	public void setPrtAtCodeNm(String prtAtCodeNm) {
		this.prtAtCodeNm = prtAtCodeNm;
	}
	/**
	 * @return the entrpsNm
	 */
	public String getEntrpsNm() {
		return entrpsNm;
	}
	/**
	 * @param entrpsNm the entrpsNm to set
	 */
	public void setEntrpsNm(String entrpsNm) {
		this.entrpsNm = entrpsNm;
	}
	/**
	 * @return the bizrno
	 */
	public String getBizrno() {
		return bizrno;
	}
	/**
	 * @param bizrno the bizrno to set
	 */
	public void setBizrno(String bizrno) {
		this.bizrno = bizrno;
	}
	/**
	 * @return the rprsntvNm
	 */
	public String getRprsntvNm() {
		return rprsntvNm;
	}
	/**
	 * @param rprsntvNm the rprsntvNm to set
	 */
	public void setRprsntvNm(String rprsntvNm) {
		this.rprsntvNm = rprsntvNm;
	}
	/**
	 * @return the gisAssetsLocplc
	 */
	public String getGisAssetsLocplc() {
		return gisAssetsLocplc;
	}
	/**
	 * @param gisAssetsLocplc the gisAssetsLocplc to set
	 */
	public void setGisAssetsLocplc(String gisAssetsLocplc) {
		this.gisAssetsLocplc = gisAssetsLocplc;
	}
	/**
	 * @return the gisAssetsPrprtySeCdNm
	 */
	public String getGisAssetsPrprtySeCdNm() {
		return gisAssetsPrprtySeCdNm;
	}
	/**
	 * @param gisAssetsPrprtySeCdNm the gisAssetsPrprtySeCdNm to set
	 */
	public void setGisAssetsPrprtySeCdNm(String gisAssetsPrprtySeCdNm) {
		this.gisAssetsPrprtySeCdNm = gisAssetsPrprtySeCdNm;
	}
	/**
	 * @return the reqstSeCdNm
	 */
	public String getReqstSeCdNm() {
		return reqstSeCdNm;
	}
	/**
	 * @param reqstSeCdNm the reqstSeCdNm to set
	 */
	public void setReqstSeCdNm(String reqstSeCdNm) {
		this.reqstSeCdNm = reqstSeCdNm;
	}
	/**
	 * @return the grAr
	 */
	public String getGrAr() {
		return grAr;
	}
	/**
	 * @param grAr the grAr to set
	 */
	public void setGrAr(String grAr) {
		this.grAr = grAr;
	}
	/**
	 * @return the grFee
	 */
	public String getGrFee() {
		return grFee;
	}
	/**
	 * @param grFee the grFee to set
	 */
	public void setGrFee(String grFee) {
		this.grFee = grFee;
	}
	/**
	 * @return the pass4
	 */
	public String getPass4() {
		return pass4;
	}
	/**
	 * @param pass4 the pass4 to set
	 */
	public void setPass4(String pass4) {
		this.pass4 = pass4;
	}
	/**
	 * @return the pass3
	 */
	public String getPass3() {
		return pass3;
	}
	/**
	 * @param pass3 the pass3 to set
	 */
	public void setPass3(String pass3) {
		this.pass3 = pass3;
	}
	/**
	 * @return the pass2
	 */
	public String getPass2() {
		return pass2;
	}
	/**
	 * @param pass2 the pass2 to set
	 */
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
	/**
	 * @return the pass1
	 */
	public String getPass1() {
		return pass1;
	}
	/**
	 * @param pass1 the pass1 to set
	 */
	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}
	/**
	 * @return the currYear
	 */
	public String getCurrYearFee() {
		return currYearFee;
	}
	/**
	 * @param currYear the currYear to set
	 */
	public void setCurrYearFee(String currYearFee) {
		this.currYearFee = currYearFee;
	}
	/**
	 * @return the frstUsagePdFrom
	 */
	public String getFrstUsagePdFrom() {
		return frstUsagePdFrom;
	}
	/**
	 * @param frstUsagePdFrom the frstUsagePdFrom to set
	 */
	public void setFrstUsagePdFrom(String frstUsagePdFrom) {
		this.frstUsagePdFrom = frstUsagePdFrom;
	}
	/**
	 * @return the grUsagePdFrom
	 */
	public String getGrUsagePdFrom() {
		return grUsagePdFrom;
	}
	/**
	 * @param grUsagePdFrom the grUsagePdFrom to set
	 */
	public void setGrUsagePdFrom(String grUsagePdFrom) {
		this.grUsagePdFrom = grUsagePdFrom;
	}
	/**
	 * @return the grUsagePdTo
	 */
	public String getGrUsagePdTo() {
		return grUsagePdTo;
	}
	/**
	 * @param grUsagePdTo the grUsagePdTo to set
	 */
	public void setGrUsagePdTo(String grUsagePdTo) {
		this.grUsagePdTo = grUsagePdTo;
	}
	/**
	 * @return the usageDays
	 */
	public String getUsageDays() {
		return usageDays;
	}
	/**
	 * @param usageDays the usageDays to set
	 */
	public void setUsageDays(String usageDays) {
		this.usageDays = usageDays;
	}
	/**
	 * @return the referYear
	 */
	public String getReferYear() {
		return referYear;
	}
	/**
	 * @param referYear the referYear to set
	 */
	public void setReferYear(String referYear) {
		this.referYear = referYear;
	}



}
