/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service;

import java.util.Date;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author Administrator
 * @since 2014. 3. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 5.		Administrator		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamEntrpsChargerFVO extends ComDefaultVO{

	private static final long serialVersionUID = 1L;
	
	 /** 담당자 번호 */
    private Integer chargerNo;

    /** 업체코드 */
    private String entrpscd;

    /** 담당자 명 */
    private String chargerNm;

    /** 담당자 직위 */
    private String chargerOfcPos;

    /** 담당 업무 */
    private String chrgJob;

    /** 담당자 휴대폰 번호 */
    private String chargerMoblphonNo;

    /** 담당자 전화번호 */
    private String chargerTlphonNo;

    /** 담당자 팩스 */
    private String chargerFax;

    /** 담당자 이메일 */
    private String chargerEmail;

    /** 담당자 부서 */
    private String chargerDept;

    /** 관리 부서 코드 */
    private String mngDeptCd;

    /** 등록자 */
    private String regUsr;

    /** 등록일시 */
    private Date registDt;

    /** 수정자 */
    private String updUsr;

    /** 수정일시 */
    private Date updtDt;

    public Integer getChargerNo() {
        return chargerNo;
    }

    public void setChargerNo(Integer chargerNo) {
        this.chargerNo = chargerNo;
    }

    public String getEntrpscd() {
        return entrpscd;
    }

    public void setEntrpscd(String entrpscd) {
        this.entrpscd = entrpscd;
    }

    public String getChargerNm() {
        return chargerNm;
    }

    public void setChargerNm(String chargerNm) {
        this.chargerNm = chargerNm;
    }

    public String getChargerOfcPos() {
        return chargerOfcPos;
    }

    public void setChargerOfcPos(String chargerOfcPos) {
        this.chargerOfcPos = chargerOfcPos;
    }

    public String getChrgJob() {
        return chrgJob;
    }

    public void setChrgJob(String chrgJob) {
        this.chrgJob = chrgJob;
    }

    public String getChargerMoblphonNo() {
        return chargerMoblphonNo;
    }

    public void setChargerMoblphonNo(String chargerMoblphonNo) {
        this.chargerMoblphonNo = chargerMoblphonNo;
    }

    public String getChargerTlphonNo() {
        return chargerTlphonNo;
    }

    public void setChargerTlphonNo(String chargerTlphonNo) {
        this.chargerTlphonNo = chargerTlphonNo;
    }

    public String getChargerFax() {
        return chargerFax;
    }

    public void setChargerFax(String chargerFax) {
        this.chargerFax = chargerFax;
    }

    public String getChargerEmail() {
        return chargerEmail;
    }

    public void setChargerEmail(String chargerEmail) {
        this.chargerEmail = chargerEmail;
    }

    public String getChargerDept() {
        return chargerDept;
    }

    public void setChargerDept(String chargerDept) {
        this.chargerDept = chargerDept;
    }

    public String getMngDeptCd() {
        return mngDeptCd;
    }

    public void setMngDeptCd(String mngDeptCd) {
        this.mngDeptCd = mngDeptCd;
    }

    public String getRegUsr() {
        return regUsr;
    }

    public void setRegUsr(String regUsr) {
        this.regUsr = regUsr;
    }

    public Date getRegistDt() {
        return registDt;
    }

    public void setRegistDt(Date registDt) {
        this.registDt = registDt;
    }

    public String getUpdUsr() {
        return updUsr;
    }

    public void setUpdUsr(String updUsr) {
        this.updUsr = updUsr;
    }

    public Date getUpdtDt() {
        return updtDt;
    }

    public void setUpdtDt(Date updtDt) {
        this.updtDt = updtDt;
    }
}