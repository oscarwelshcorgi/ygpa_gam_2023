/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author kok
 * @since 2014. 3. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 7.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamOlnlpFVO extends ComDefaultVO{

	private static final long serialVersionUID = 1L;
	
	/** GIS 자산 코드 */
    private String gisAssetsCd;

    /** GIS 자산 항코드 */
    private String gisAssetsPrtAtCode;

    /** GIS 자산 SUB 코드 */
    private String gisAssetsSubCd;

    /** 공시지가 순번 */
    private String olnlpSeq;

    /** 공시지가 */
    private Long olnlp;

    /** 등록자 */
    private String regUsr;

    /** 등록일시 */
    private String registDt;

    /** 수정자 */
    private String updUsr;

    /** 수정일시 */
    private String updtDt;

    /** 시작 일자 */
    private String beginDt;

    /** 종료 일자 */
    private String endDt;

    public String getGisAssetsCd() {
        return gisAssetsCd;
    }

    public void setGisAssetsCd(String gisAssetsCd) {
        this.gisAssetsCd = gisAssetsCd;
    }

    public String getGisAssetsPrtAtCode() {
        return gisAssetsPrtAtCode;
    }

    public void setGisAssetsPrtAtCode(String gisAssetsPrtAtCode) {
        this.gisAssetsPrtAtCode = gisAssetsPrtAtCode;
    }

    public String getGisAssetsSubCd() {
        return gisAssetsSubCd;
    }

    public void setGisAssetsSubCd(String gisAssetsSubCd) {
        this.gisAssetsSubCd = gisAssetsSubCd;
    }

    public String getOlnlpSeq() {
        return olnlpSeq;
    }

    public void setOlnlpSeq(String olnlpSeq) {
        this.olnlpSeq = olnlpSeq;
    }

    public Long getOlnlp() {
        return olnlp;
    }

    public void setOlnlp(Long olnlp) {
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

    public String getUpdUsr() {
        return updUsr;
    }

    public void setUpdUsr(String updUsr) {
        this.updUsr = updUsr;
    }

    public String getUpdtDt() {
        return updtDt;
    }

    public void setUpdtDt(String updtDt) {
        this.updtDt = updtDt;
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
}