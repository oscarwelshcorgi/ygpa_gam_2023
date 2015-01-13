/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author HNJ
 * @since 2014. 10. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 31.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltyCtrtSttusInqireVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String rnum;				// 순번
	private	String entrpsNm;			// 업체 명
	private	String rm;					// 비고
	private	String dealRelate;			// 거래 관계
	private	String induty;				// 업종
	private	String rprsntv;				// 대표자
	private	String tlphonNo;			// 전화 번호
	private	String faxNo;				// FAX 번호
	private	String postNo;				// 우편 번호
	private	String roadnmAdres;			// 도로명 주소
	private	String lnmAdres;			// 지번 주소
	private	String stplPrdlst;			// 주요 품목
	private	String bsnmNo;				// 사업자 번호
	private	String charger;				// 담당자
	private	String chargerOfcPos;		// 담당자 직위
	private	String chargerMoblphonNo;	// 담당자 휴대폰 번호
	private	String chargerEmail;		// 담당자 E-MAIL
	private	String prevCtrtAmt;			// 이전 계약 금액
	private	String currCtrtAmt;			// 현재 계약 금액
	private	String sCtrtYr;				// 검색 계약 년도
	private	String sPrevCtrtYr;			// 검색 이전 계약 년도
	private	String sCtrtSe;				// 검색 계약 구분
	private	String sCtrtNm;				// 검색 계약 명
	private	String sRegistEntrpsCd;		// 검색 등록 업체 코드
	private	String sRegistEntrpsNm;		// 검색 등록 업체 명
	private	String sStartCtrtDt;		// 검색 계약 일자 FROM
	private	String sEndCtrtDt;			// 검색 계약 일자 END
	private	String sStartCtrtAmt;		// 검색 계약 금액 FROM
	private	String sEndCtrtAmt;			// 검색 계약 금액 TO
	private	String totalCount;			// 조회 자료 수
	private	String sumPrevCtrtAmt;		// 이전 계약 금액 합계
	private	String sumCurrCtrtAmt;		// 현재 계약 금액 합계
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
	 * @return the dealRelate
	 */
	public String getDealRelate() {
		return dealRelate;
	}
	/**
	 * @param dealRelate the dealRelate to set
	 */
	public void setDealRelate(String dealRelate) {
		this.dealRelate = dealRelate;
	}
	/**
	 * @return the induty
	 */
	public String getInduty() {
		return induty;
	}
	/**
	 * @param induty the induty to set
	 */
	public void setInduty(String induty) {
		this.induty = induty;
	}
	/**
	 * @return the rprsntv
	 */
	public String getRprsntv() {
		return rprsntv;
	}
	/**
	 * @param rprsntv the rprsntv to set
	 */
	public void setRprsntv(String rprsntv) {
		this.rprsntv = rprsntv;
	}
	/**
	 * @return the tlphonNo
	 */
	public String getTlphonNo() {
		return tlphonNo;
	}
	/**
	 * @param tlphonNo the tlphonNo to set
	 */
	public void setTlphonNo(String tlphonNo) {
		this.tlphonNo = tlphonNo;
	}
	/**
	 * @return the faxNo
	 */
	public String getFaxNo() {
		return faxNo;
	}
	/**
	 * @param faxNo the faxNo to set
	 */
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	/**
	 * @return the postNo
	 */
	public String getPostNo() {
		return postNo;
	}
	/**
	 * @param postNo the postNo to set
	 */
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	/**
	 * @return the roadnmAdres
	 */
	public String getRoadnmAdres() {
		return roadnmAdres;
	}
	/**
	 * @param roadnmAdres the roadnmAdres to set
	 */
	public void setRoadnmAdres(String roadnmAdres) {
		this.roadnmAdres = roadnmAdres;
	}
	/**
	 * @return the lnmAdres
	 */
	public String getLnmAdres() {
		return lnmAdres;
	}
	/**
	 * @param lnmAdres the lnmAdres to set
	 */
	public void setLnmAdres(String lnmAdres) {
		this.lnmAdres = lnmAdres;
	}
	/**
	 * @return the stplPrdlst
	 */
	public String getStplPrdlst() {
		return stplPrdlst;
	}
	/**
	 * @param stplPrdlst the stplPrdlst to set
	 */
	public void setStplPrdlst(String stplPrdlst) {
		this.stplPrdlst = stplPrdlst;
	}
	/**
	 * @return the bsnmNo
	 */
	public String getBsnmNo() {
		return bsnmNo;
	}
	/**
	 * @param bsnmNo the bsnmNo to set
	 */
	public void setBsnmNo(String bsnmNo) {
		this.bsnmNo = bsnmNo;
	}
	/**
	 * @return the charger
	 */
	public String getCharger() {
		return charger;
	}
	/**
	 * @param charger the charger to set
	 */
	public void setCharger(String charger) {
		this.charger = charger;
	}
	/**
	 * @return the chargerOfcPos
	 */
	public String getChargerOfcPos() {
		return chargerOfcPos;
	}
	/**
	 * @param chargerOfcPos the chargerOfcPos to set
	 */
	public void setChargerOfcPos(String chargerOfcPos) {
		this.chargerOfcPos = chargerOfcPos;
	}
	/**
	 * @return the chargerMoblphonNo
	 */
	public String getChargerMoblphonNo() {
		return chargerMoblphonNo;
	}
	/**
	 * @param chargerMoblphonNo the chargerMoblphonNo to set
	 */
	public void setChargerMoblphonNo(String chargerMoblphonNo) {
		this.chargerMoblphonNo = chargerMoblphonNo;
	}
	/**
	 * @return the chargerEmail
	 */
	public String getChargerEmail() {
		return chargerEmail;
	}
	/**
	 * @param chargerEmail the chargerEmail to set
	 */
	public void setChargerEmail(String chargerEmail) {
		this.chargerEmail = chargerEmail;
	}
	/**
	 * @return the prevCtrtAmt
	 */
	public String getPrevCtrtAmt() {
		return prevCtrtAmt;
	}
	/**
	 * @param prevCtrtAmt the prevCtrtAmt to set
	 */
	public void setPrevCtrtAmt(String prevCtrtAmt) {
		this.prevCtrtAmt = prevCtrtAmt;
	}
	/**
	 * @return the currCtrtAmt
	 */
	public String getCurrCtrtAmt() {
		return currCtrtAmt;
	}
	/**
	 * @param currCtrtAmt the currCtrtAmt to set
	 */
	public void setCurrCtrtAmt(String currCtrtAmt) {
		this.currCtrtAmt = currCtrtAmt;
	}
	/**
	 * @return the sCtrtYr
	 */
	public String getsCtrtYr() {
		return sCtrtYr;
	}
	/**
	 * @param sCtrtYr the sCtrtYr to set
	 */
	public void setsCtrtYr(String sCtrtYr) {
		this.sCtrtYr = sCtrtYr;
	}
	/**
	 * @return the sPrevCtrtYr
	 */
	public String getsPrevCtrtYr() {
		return sPrevCtrtYr;
	}
	/**
	 * @param sPrevCtrtYr the sPrevCtrtYr to set
	 */
	public void setsPrevCtrtYr(String sPrevCtrtYr) {
		this.sPrevCtrtYr = sPrevCtrtYr;
	}
	/**
	 * @return the sCtrtSe
	 */
	public String getsCtrtSe() {
		return sCtrtSe;
	}
	/**
	 * @param sCtrtSe the sCtrtSe to set
	 */
	public void setsCtrtSe(String sCtrtSe) {
		this.sCtrtSe = sCtrtSe;
	}
	/**
	 * @return the sCtrtNm
	 */
	public String getsCtrtNm() {
		return sCtrtNm;
	}
	/**
	 * @param sCtrtNm the sCtrtNm to set
	 */
	public void setsCtrtNm(String sCtrtNm) {
		this.sCtrtNm = sCtrtNm;
	}
	/**
	 * @return the sRegistEntrpsCd
	 */
	public String getsRegistEntrpsCd() {
		return sRegistEntrpsCd;
	}
	/**
	 * @param sRegistEntrpsCd the sRegistEntrpsCd to set
	 */
	public void setsRegistEntrpsCd(String sRegistEntrpsCd) {
		this.sRegistEntrpsCd = sRegistEntrpsCd;
	}
	/**
	 * @return the sRegistEntrpsNm
	 */
	public String getsRegistEntrpsNm() {
		return sRegistEntrpsNm;
	}
	/**
	 * @param sRegistEntrpsNm the sRegistEntrpsNm to set
	 */
	public void setsRegistEntrpsNm(String sRegistEntrpsNm) {
		this.sRegistEntrpsNm = sRegistEntrpsNm;
	}
	/**
	 * @return the sStartCtrtDt
	 */
	public String getsStartCtrtDt() {
		return sStartCtrtDt;
	}
	/**
	 * @param sStartCtrtDt the sStartCtrtDt to set
	 */
	public void setsStartCtrtDt(String sStartCtrtDt) {
		this.sStartCtrtDt = sStartCtrtDt;
	}
	/**
	 * @return the sEndCtrtDt
	 */
	public String getsEndCtrtDt() {
		return sEndCtrtDt;
	}
	/**
	 * @param sEndCtrtDt the sEndCtrtDt to set
	 */
	public void setsEndCtrtDt(String sEndCtrtDt) {
		this.sEndCtrtDt = sEndCtrtDt;
	}
	/**
	 * @return the sStartCtrtAmt
	 */
	public String getsStartCtrtAmt() {
		return sStartCtrtAmt;
	}
	/**
	 * @param sStartCtrtAmt the sStartCtrtAmt to set
	 */
	public void setsStartCtrtAmt(String sStartCtrtAmt) {
		this.sStartCtrtAmt = sStartCtrtAmt;
	}
	/**
	 * @return the sEndCtrtAmt
	 */
	public String getsEndCtrtAmt() {
		return sEndCtrtAmt;
	}
	/**
	 * @param sEndCtrtAmt the sEndCtrtAmt to set
	 */
	public void setsEndCtrtAmt(String sEndCtrtAmt) {
		this.sEndCtrtAmt = sEndCtrtAmt;
	}
	/**
	 * @return the totalCount
	 */
	public String getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the sumPrevCtrtAmt
	 */
	public String getSumPrevCtrtAmt() {
		return sumPrevCtrtAmt;
	}
	/**
	 * @param sumPrevCtrtAmt the sumPrevCtrtAmt to set
	 */
	public void setSumPrevCtrtAmt(String sumPrevCtrtAmt) {
		this.sumPrevCtrtAmt = sumPrevCtrtAmt;
	}
	/**
	 * @return the sumCurrCtrtAmt
	 */
	public String getSumCurrCtrtAmt() {
		return sumCurrCtrtAmt;
	}
	/**
	 * @param sumCurrCtrtAmt the sumCurrCtrtAmt to set
	 */
	public void setSumCurrCtrtAmt(String sumCurrCtrtAmt) {
		this.sumCurrCtrtAmt = sumCurrCtrtAmt;
	}

}
