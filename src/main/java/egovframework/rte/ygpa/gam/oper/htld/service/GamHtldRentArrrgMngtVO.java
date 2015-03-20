/**
 *
 */
package egovframework.rte.ygpa.gam.oper.htld.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 5. 11.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 5. 11.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamHtldRentArrrgMngtVO extends ComDefaultVO {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 청코드
	 */
	private String prtAtCode;

	/**
	 * 관리년도
	 */
	private String mngYear;

	/**
	 * 관려번호
	 */
	private String mngNo;

	/**
	 * 관리횟수
	 */
	private String mngCnt;

	/**
	 * 고지 횟수
	 */
	private String nticCnt;

	/**
	 * 신규 납부기한
	 */
	private String newPayTmlmt;

	/**
	 * 납부기한 시작
	 */
	private String payTmlmtFrom;

	/**
	 * 납부기한 종료
	 */
	private String payTmlmtTo;

	/**
	 * 수납구분 코드
	 */
	private String rcivSe;

	/**
	 * 업체코드
	 */
	private String entrpscd;

	/**
	 * 요금종류코드
	 */
	private String chrgeKnd;

	/**
	 * 연체요율
	 */
	private String arrrgTariff;

	/**
	 * 연체료 합계
	 */
	private String arrrgAmt;

	private String regUsr;

	private String updUsr;

	private String userName;

	private String deptCd;

	public String getPrtAtCode() {
		return prtAtCode;
	}
	public void setPrtAtCode(String prtAtCode) {
		this.prtAtCode = prtAtCode;
	}
	public String getPayTmlmtFrom() {
		return payTmlmtFrom;
	}
	public void setPayTmlmtFrom(String payTmlmtFrom) {
		this.payTmlmtFrom = payTmlmtFrom;
	}
	public String getPayTmlmtTo() {
		return payTmlmtTo;
	}
	public void setPayTmlmtTo(String payTmlmtTo) {
		this.payTmlmtTo = payTmlmtTo;
	}
	public String getRcivSe() {
		return rcivSe;
	}
	public void setRcivSe(String rcivSe) {
		this.rcivSe = rcivSe;
	}
	public String getEntrpscd() {
		return entrpscd;
	}
	public void setEntrpscd(String entrpscd) {
		this.entrpscd = entrpscd;
	}
	public String getChrgeKnd() {
		return chrgeKnd;
	}
	public void setChrgeKnd(String chrgeKnd) {
		this.chrgeKnd = chrgeKnd;
	}
	public String getArrrgTariff() {
		return arrrgTariff;
	}
	public void setArrrgTariff(String arrrgRate) {
		this.arrrgTariff = arrrgRate;
	}
	public String getUpdUsr() {
		return updUsr;
	}
	public void setUpdUsr(String updUsr) {
		this.updUsr = updUsr;
	}
	public String getMngYear() {
		return mngYear;
	}
	public void setMngYear(String mngYear) {
		this.mngYear = mngYear;
	}
	public String getMngNo() {
		return mngNo;
	}
	public void setMngNo(String mngNo) {
		this.mngNo = mngNo;
	}
	public String getMngCnt() {
		return mngCnt;
	}
	public void setMngCnt(String mngCnt) {
		this.mngCnt = mngCnt;
	}
	public String getNticCnt() {
		return nticCnt;
	}
	public void setNticCnt(String nticCnt) {
		this.nticCnt = nticCnt;
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
	 * @return the newPayTmlmt
	 */
	public String getNewPayTmlmt() {
		return newPayTmlmt;
	}
	/**
	 * @param newPayTmlmt the newPayTmlmt to set
	 */
	public void setNewPayTmlmt(String newPayTmlmt) {
		this.newPayTmlmt = newPayTmlmt;
	}
	/**
	 * @return the arrrgAmt
	 */
	public String getArrrgAmt() {
		return arrrgAmt;
	}
	/**
	 * @param arrrgAmt the arrrgAmt to set
	 */
	public void setArrrgAmt(String arrrgAmt) {
		this.arrrgAmt = arrrgAmt;
	}
	/**
	 * @return the deptCd
	 */
	public String getDeptCd() {
		return deptCd;
	}
	/**
	 * @param deptCd the deptCd to set
	 */
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


}
