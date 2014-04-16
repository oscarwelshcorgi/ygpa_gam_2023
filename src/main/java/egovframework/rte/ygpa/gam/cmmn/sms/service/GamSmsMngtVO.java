/**
 * 
 */
package egovframework.rte.ygpa.gam.cmmn.sms.service;

import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;

/**
 * @Class Name : GamSendMesgListMngtVO.java
 * @Description : SMS 목록 관리  VO Class
 * @Modification Information
 *
 * @author 김종민
 * @since 2014-04-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */


public class GamSmsMngtVO extends ErpCmmnCdDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** SMS순번 */
    private String smsSeq;
    
    /** 재전송 SMS순번*/
    private String newSmsSeq;

    /** 수신번호 */
    private String recptnNo;
    
    /** 회신번호 */
    private String replyNo;
    
    /** 내용 */
    private String cn;
    
    /** 전송일자 */
    private String transmisDT;
    
    /** 전송상태 */
    private String transmisSttus;
    
    /** 등록자 */
    private String regUsr;
    
    /** 등록일시 */
    private String registDT;
    
    /** 항코드 */
    private String prtAtCode;
    
    /** 관리년도 */
    private String mngYear;
    
    /** 관리번호 */
    private String mngNo;
    
    /** 관리횟수 */
    private String mngCnt;

    /** 조회 from 날짜 */
    private String searchDTFrom;
    
    /** 조회 to 날짜 */
    private String searchDTTo;
    
	/**
	 * @return the smsSeq
	 */
	public String getSmsSeq() {
		return smsSeq;
	}

	/**
	 * @param smsSeq the smsSeq to set
	 */
	public void setSmsSeq(String smsSeq) {
		this.smsSeq = smsSeq;
	}

	/**
	 * @return the newSmsSeq
	 */
	public String getNewSmsSeq() {
		return newSmsSeq;
	}

	/**
	 * @param newSmsSeq the newSmsSeq to set
	 */
	public void setNewSmsSeq(String newSmsSeq) {
		this.newSmsSeq = newSmsSeq;
	}

	/**
	 * @return the recptnNo
	 */
	public String getRecptnNo() {
		return recptnNo;
	}

	/**
	 * @param recptnNo the recptnNo to set
	 */
	public void setRecptnNo(String recptnNo) {
		this.recptnNo = recptnNo;
	}

	/**
	 * @return the replyNo
	 */
	public String getReplyNo() {
		return replyNo;
	}

	/**
	 * @param replyNo the replyNo to set
	 */
	public void setReplyNo(String replyNo) {
		this.replyNo = replyNo;
	}

	/**
	 * @return the cn
	 */
	public String getCn() {
		return cn;
	}

	/**
	 * @param cn the cn to set
	 */
	public void setCn(String cn) {
		this.cn = cn;
	}

	/**
	 * @return the transmisDT
	 */
	public String getTransmisDT() {
		return transmisDT;
	}

	/**
	 * @param transmisDT the transmisDT to set
	 */
	public void setTransmisDT(String transmisDT) {
		this.transmisDT = transmisDT;
	}

	/**
	 * @return the transmisSttus
	 */
	public String getTransmisSttus() {
		return transmisSttus;
	}

	/**
	 * @param transmisSttus the transmisSttus to set
	 */
	public void setTransmisSttus(String transmisSttus) {
		this.transmisSttus = transmisSttus;
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

	/**
	 * @return the registDT
	 */
	public String getRegistDT() {
		return registDT;
	}

	/**
	 * @param registDT the registDT to set
	 */
	public void setRegistDT(String registDT) {
		this.registDT = registDT;
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
	 * @return the mngYear
	 */
	public String getMngYear() {
		return mngYear;
	}

	/**
	 * @param mngYear the mngYear to set
	 */
	public void setMngYear(String mngYear) {
		this.mngYear = mngYear;
	}

	/**
	 * @return the mngNo
	 */
	public String getMngNo() {
		return mngNo;
	}

	/**
	 * @param mngNo the mngNo to set
	 */
	public void setMngNo(String mngNo) {
		this.mngNo = mngNo;
	}

	/**
	 * @return the mngCnt
	 */
	public String getMngCnt() {
		return mngCnt;
	}

	/**
	 * @param mngCnt the mngCnt to set
	 */
	public void setMngCnt(String mngCnt) {
		this.mngCnt = mngCnt;
	}

	/**
	 * @return the searchDTFrom
	 */
	public String getSearchDTFrom() {
		return searchDTFrom;
	}

	/**
	 * @param searchDTFrom the searchDTFrom to set
	 */
	public void setSearchDTFrom(String searchDTFrom) {
		this.searchDTFrom = searchDTFrom;
	}

	/**
	 * @return the searchDTTo
	 */
	public String getSearchDTTo() {
		return searchDTTo;
	}

	/**
	 * @param searchDTTo the searchDTTo to set
	 */
	public void setSearchDTTo(String searchDTTo) {
		this.searchDTTo = searchDTTo;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
