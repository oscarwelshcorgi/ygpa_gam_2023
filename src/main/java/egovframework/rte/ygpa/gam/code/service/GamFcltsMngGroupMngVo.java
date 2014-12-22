/**
 *
 */
package egovframework.rte.ygpa.gam.code.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author 김종민
 * @since 2014. 12. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 10.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltsMngGroupMngVo  extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 자료컬럼 **/
	private String fcltsMngGroupNo;			//시설물 관리 그룹 번호
	private String fcltsMngGroupNm;			//시설물 관리 그룹 명
	private String loc;						//위치
	private String owner;					//소유자
	private String bldDt;					//준공 일자
	private String cnstBeginDt;				//공사 시작 일자
	private String cnstEndDt;				//공사 종료 일자
	private String cnstrtr;					//시공자
	private String cnstrctAmt;				//시공 금액
	private String fcltsCn;					//시설물 내용
	private String fcltsStrct;				//시설물 구조
	private String archFcltsCnt;			//건축 시설물 갯수
	private String cvlEngFcltsCnt;			//토목 시설물 갯수
	private String mechFcltsCnt;			//기계 시설물 갯수
	private String elctyFcltsCnt;			//전기 시설물 갯수
	private String infoCommFcltsCnt;		//정보 통신 시설물 갯수
	private String etcFcltsCnt;				//기타 시설물 갯수
	private String rm;						//비고
	private	String regUsr;					//등록자
	private	String registDt;				//등록일시
	private	String updUsr;					//수정자
	private	String updtDt;					//수정일시
	/** 조회조건 **/
	private String sFcltsMngGroupNo;		//시설물 관리 그룹 번호
	private String sFcltsMngGroupNm;		//시설물 관리 그룹 명
	/** 합계컬럼 **/
	private String	dataCount;				//자료수
	private	String	sumArchFcltsCnt;		//총 건축 시설물 갯수
	private	String	sumCvlEngFcltsCnt;		//총 토목 시설물 갯수
	private	String	sumMechFcltsCnt;		//총 기계 시설물 갯수
	private	String	sumElctyFcltsCnt;		//총 전기 시설물 갯수
	private	String	sumInfoCommFcltsCnt;	//총 정보 통신 시설물 갯수
	private	String	sumEtcFcltsCnt;			//총 기타 시설물 갯수

	/**
	 * @return the fcltsMngGroupNo
	 */
	public String getFcltsMngGroupNo() {
		return fcltsMngGroupNo;
	}
	/**
	 * @param fcltsMngGroupNo the fcltsMngGroupNo to set
	 */
	public void setFcltsMngGroupNo(String fcltsMngGroupNo) {
		this.fcltsMngGroupNo = fcltsMngGroupNo;
	}
	/**
	 * @return the fcltsMngGroupNm
	 */
	public String getFcltsMngGroupNm() {
		return fcltsMngGroupNm;
	}
	/**
	 * @param fcltsMngGroupNm the fcltsMngGroupNm to set
	 */
	public void setFcltsMngGroupNm(String fcltsMngGroupNm) {
		this.fcltsMngGroupNm = fcltsMngGroupNm;
	}
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
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * @return the bldDt
	 */
	public String getBldDt() {
		return bldDt;
	}
	/**
	 * @param bldDt the bldDt to set
	 */
	public void setBldDt(String bldDt) {
		this.bldDt = bldDt;
	}
	/**
	 * @return the cnstBeginDt
	 */
	public String getCnstBeginDt() {
		return cnstBeginDt;
	}
	/**
	 * @param cnstBeginDt the cnstBeginDt to set
	 */
	public void setCnstBeginDt(String cnstBeginDt) {
		this.cnstBeginDt = cnstBeginDt;
	}
	/**
	 * @return the cnstEndDt
	 */
	public String getCnstEndDt() {
		return cnstEndDt;
	}
	/**
	 * @param cnstEndDt the cnstEndDt to set
	 */
	public void setCnstEndDt(String cnstEndDt) {
		this.cnstEndDt = cnstEndDt;
	}
	/**
	 * @return the cnstrtr
	 */
	public String getCnstrtr() {
		return cnstrtr;
	}
	/**
	 * @param cnstrtr the cnstrtr to set
	 */
	public void setCnstrtr(String cnstrtr) {
		this.cnstrtr = cnstrtr;
	}
	/**
	 * @return the cnstrctAmt
	 */
	public String getCnstrctAmt() {
		return cnstrctAmt;
	}
	/**
	 * @param cnstrctAmt the cnstrctAmt to set
	 */
	public void setCnstrctAmt(String cnstrctAmt) {
		this.cnstrctAmt = cnstrctAmt;
	}
	/**
	 * @return the fcltsCn
	 */
	public String getFcltsCn() {
		return fcltsCn;
	}
	/**
	 * @param fcltsCn the fcltsCn to set
	 */
	public void setFcltsCn(String fcltsCn) {
		this.fcltsCn = fcltsCn;
	}
	/**
	 * @return the fcltsStrct
	 */
	public String getFcltsStrct() {
		return fcltsStrct;
	}
	/**
	 * @param fcltsStrct the fcltsStrct to set
	 */
	public void setFcltsStrct(String fcltsStrct) {
		this.fcltsStrct = fcltsStrct;
	}
	/**
	 * @return the archFcltsCnt
	 */
	public String getArchFcltsCnt() {
		return archFcltsCnt;
	}
	/**
	 * @param archFcltsCnt the archFcltsCnt to set
	 */
	public void setArchFcltsCnt(String archFcltsCnt) {
		this.archFcltsCnt = archFcltsCnt;
	}
	/**
	 * @return the cvlEngFcltsCnt
	 */
	public String getCvlEngFcltsCnt() {
		return cvlEngFcltsCnt;
	}
	/**
	 * @param cvlEngFcltsCnt the cvlEngFcltsCnt to set
	 */
	public void setCvlEngFcltsCnt(String cvlEngFcltsCnt) {
		this.cvlEngFcltsCnt = cvlEngFcltsCnt;
	}
	/**
	 * @return the mechFcltsCnt
	 */
	public String getMechFcltsCnt() {
		return mechFcltsCnt;
	}
	/**
	 * @param mechFcltsCnt the mechFcltsCnt to set
	 */
	public void setMechFcltsCnt(String mechFcltsCnt) {
		this.mechFcltsCnt = mechFcltsCnt;
	}
	/**
	 * @return the elctyFcltsCnt
	 */
	public String getElctyFcltsCnt() {
		return elctyFcltsCnt;
	}
	/**
	 * @param elctyFcltsCnt the elctyFcltsCnt to set
	 */
	public void setElctyFcltsCnt(String elctyFcltsCnt) {
		this.elctyFcltsCnt = elctyFcltsCnt;
	}
	/**
	 * @return the infoCommFcltsCnt
	 */
	public String getInfoCommFcltsCnt() {
		return infoCommFcltsCnt;
	}
	/**
	 * @param infoCommFcltsCnt the infoCommFcltsCnt to set
	 */
	public void setInfoCommFcltsCnt(String infoCommFcltsCnt) {
		this.infoCommFcltsCnt = infoCommFcltsCnt;
	}
	/**
	 * @return the etcFcltsCnt
	 */
	public String getEtcFcltsCnt() {
		return etcFcltsCnt;
	}
	/**
	 * @param etcFcltsCnt the etcFcltsCnt to set
	 */
	public void setEtcFcltsCnt(String etcFcltsCnt) {
		this.etcFcltsCnt = etcFcltsCnt;
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
	 * @return the registDt
	 */
	public String getRegistDt() {
		return registDt;
	}
	/**
	 * @param registDt the registDt to set
	 */
	public void setRegistDt(String registDt) {
		this.registDt = registDt;
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
	/**
	 * @return the updtDt
	 */
	public String getUpdtDt() {
		return updtDt;
	}
	/**
	 * @param updtDt the updtDt to set
	 */
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}
	/**
	 * @return the sFcltsMngGroupNo
	 */
	public String getsFcltsMngGroupNo() {
		return sFcltsMngGroupNo;
	}
	/**
	 * @param sFcltsMngGroupNo the sFcltsMngGroupNo to set
	 */
	public void setsFcltsMngGroupNo(String sFcltsMngGroupNo) {
		this.sFcltsMngGroupNo = sFcltsMngGroupNo;
	}
	/**
	 * @return the sFcltsMngGroupNm
	 */
	public String getsFcltsMngGroupNm() {
		return sFcltsMngGroupNm;
	}
	/**
	 * @param sFcltsMngGroupNm the sFcltsMngGroupNm to set
	 */
	public void setsFcltsMngGroupNm(String sFcltsMngGroupNm) {
		this.sFcltsMngGroupNm = sFcltsMngGroupNm;
	}
	/**
	 * @return the dataCount
	 */
	public String getDataCount() {
		return dataCount;
	}
	/**
	 * @param dataCount the dataCount to set
	 */
	public void setDataCount(String dataCount) {
		this.dataCount = dataCount;
	}
	/**
	 * @return the sumArchFcltsCnt
	 */
	public String getSumArchFcltsCnt() {
		return sumArchFcltsCnt;
	}
	/**
	 * @param sumArchFcltsCnt the sumArchFcltsCnt to set
	 */
	public void setSumArchFcltsCnt(String sumArchFcltsCnt) {
		this.sumArchFcltsCnt = sumArchFcltsCnt;
	}
	/**
	 * @return the sumCvlEngFcltsCnt
	 */
	public String getSumCvlEngFcltsCnt() {
		return sumCvlEngFcltsCnt;
	}
	/**
	 * @param sumCvlEngFcltsCnt the sumCvlEngFcltsCnt to set
	 */
	public void setSumCvlEngFcltsCnt(String sumCvlEngFcltsCnt) {
		this.sumCvlEngFcltsCnt = sumCvlEngFcltsCnt;
	}
	/**
	 * @return the sumMechFcltsCnt
	 */
	public String getSumMechFcltsCnt() {
		return sumMechFcltsCnt;
	}
	/**
	 * @param sumMechFcltsCnt the sumMechFcltsCnt to set
	 */
	public void setSumMechFcltsCnt(String sumMechFcltsCnt) {
		this.sumMechFcltsCnt = sumMechFcltsCnt;
	}
	/**
	 * @return the sumElctyFcltsCnt
	 */
	public String getSumElctyFcltsCnt() {
		return sumElctyFcltsCnt;
	}
	/**
	 * @param sumElctyFcltsCnt the sumElctyFcltsCnt to set
	 */
	public void setSumElctyFcltsCnt(String sumElctyFcltsCnt) {
		this.sumElctyFcltsCnt = sumElctyFcltsCnt;
	}
	/**
	 * @return the sumInfoCommFcltsCnt
	 */
	public String getSumInfoCommFcltsCnt() {
		return sumInfoCommFcltsCnt;
	}
	/**
	 * @param sumInfoCommFcltsCnt the sumInfoCommFcltsCnt to set
	 */
	public void setSumInfoCommFcltsCnt(String sumInfoCommFcltsCnt) {
		this.sumInfoCommFcltsCnt = sumInfoCommFcltsCnt;
	}
	/**
	 * @return the sumEtcFcltsCnt
	 */
	public String getSumEtcFcltsCnt() {
		return sumEtcFcltsCnt;
	}
	/**
	 * @param sumEtcFcltsCnt the sumEtcFcltsCnt to set
	 */
	public void setSumEtcFcltsCnt(String sumEtcFcltsCnt) {
		this.sumEtcFcltsCnt = sumEtcFcltsCnt;
	}

}