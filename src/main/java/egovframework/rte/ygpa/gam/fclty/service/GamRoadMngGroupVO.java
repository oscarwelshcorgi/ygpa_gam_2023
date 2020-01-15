/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author HNJ
 * @since 2014. 12. 8.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 8.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamRoadMngGroupVO extends ComDefaultVO {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/* 검색 시설물 종류*/
	private String sFcltsGbn;

	/* 검색 시설물　관리　그룹*/
	private String sFcltsMngGroupNo;

	/* 검색 시설물　관리　그룹　명*/
	private String sFcltsMngGroupNm;

	/** 자료컬럼 **/
	private String fcltsMngGroupNo;			//시설물 관리 그룹 번호
	private String fcltsMngGroupNm;			//시설물 관리 그룹 명
	private String prtAtCode;				//항구분
	private String prtAtCodeNm;				//항구분명
	private String loc;						//위치
	private String owner;					//소유자
	private String bldDt;					//준공 일자
	private String cnstBeginDt;				//공사 시작 일자
	private String cnstEndDt;				//공사 종료 일자
	private String cnstrtr;					//시공자
	private String cnstrctAmt;				//시공 금액
	private String fcltsGbn;				//시설물 종별
	private String fcltsGbnNm;				//시설물 종별 명
	private String fcltsSe;					//시설물 구분
	private String fcltsSeNm;				//시설물 구분 명
	private String fcltsKnd;				//시설물 종류
	private String fcltsKndNm;				//시설물 종류 명
	private String archFcltyUseYn;			//건축 시설 사용 유무
	private String cvlEngFcltyUseYn;		//토목 시설 사용 유무
	private String mechFcltyUseYn;			//기계 시설 사용 유무
	private String elctyFcltyUseYn;			//전기 시설 사용 유무
	private String infoCommFcltyUseYn;		//정보 통신 사용 유무
	private	String regUsr;					//등록자
	private	String registDt;				//등록일시
	private	String updUsr;					//수정자
	private	String updtDt;					//수정일시

	private	String cnstDt;					//공사일자

	private	String stndrd;					//규격
	private	String qy;						//수량
	private	String etcStndrd;				//기타 규격
	private	String etcQy;					//기타 수량

	private String flawEndDt; //하자만료일
	private String fcltsStateCls; //상태등급
	private String fcltsSummary; //시설개요
	private String fcltsScale; //시설규모
	/**
	 * @return the sFcltsGbn
	 */
	public String getsFcltsGbn() {
		return sFcltsGbn;
	}
	/**
	 * @param sFcltsGbn the sFcltsGbn to set
	 */
	public void setsFcltsGbn(String sFcltsGbn) {
		this.sFcltsGbn = sFcltsGbn;
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
	 * @return the fcltsGbn
	 */
	public String getFcltsGbn() {
		return fcltsGbn;
	}
	/**
	 * @param fcltsGbn the fcltsGbn to set
	 */
	public void setFcltsGbn(String fcltsGbn) {
		this.fcltsGbn = fcltsGbn;
	}
	/**
	 * @return the fcltsGbnNm
	 */
	public String getFcltsGbnNm() {
		return fcltsGbnNm;
	}
	/**
	 * @param fcltsGbnNm the fcltsGbnNm to set
	 */
	public void setFcltsGbnNm(String fcltsGbnNm) {
		this.fcltsGbnNm = fcltsGbnNm;
	}
	/**
	 * @return the fcltsSe
	 */
	public String getFcltsSe() {
		return fcltsSe;
	}
	/**
	 * @param fcltsSe the fcltsSe to set
	 */
	public void setFcltsSe(String fcltsSe) {
		this.fcltsSe = fcltsSe;
	}
	/**
	 * @return the fcltsSeNm
	 */
	public String getFcltsSeNm() {
		return fcltsSeNm;
	}
	/**
	 * @param fcltsSeNm the fcltsSeNm to set
	 */
	public void setFcltsSeNm(String fcltsSeNm) {
		this.fcltsSeNm = fcltsSeNm;
	}
	/**
	 * @return the fcltsKnd
	 */
	public String getFcltsKnd() {
		return fcltsKnd;
	}
	/**
	 * @param fcltsKnd the fcltsKnd to set
	 */
	public void setFcltsKnd(String fcltsKnd) {
		this.fcltsKnd = fcltsKnd;
	}
	/**
	 * @return the fcltsKndNm
	 */
	public String getFcltsKndNm() {
		return fcltsKndNm;
	}
	/**
	 * @param fcltsKndNm the fcltsKndNm to set
	 */
	public void setFcltsKndNm(String fcltsKndNm) {
		this.fcltsKndNm = fcltsKndNm;
	}
	/**
	 * @return the archFcltyUseYn
	 */
	public String getArchFcltyUseYn() {
		return archFcltyUseYn;
	}
	/**
	 * @param archFcltyUseYn the archFcltyUseYn to set
	 */
	public void setArchFcltyUseYn(String archFcltyUseYn) {
		this.archFcltyUseYn = archFcltyUseYn;
	}
	/**
	 * @return the cvlEngFcltyUseYn
	 */
	public String getCvlEngFcltyUseYn() {
		return cvlEngFcltyUseYn;
	}
	/**
	 * @param cvlEngFcltyUseYn the cvlEngFcltyUseYn to set
	 */
	public void setCvlEngFcltyUseYn(String cvlEngFcltyUseYn) {
		this.cvlEngFcltyUseYn = cvlEngFcltyUseYn;
	}
	/**
	 * @return the mechFcltyUseYn
	 */
	public String getMechFcltyUseYn() {
		return mechFcltyUseYn;
	}
	/**
	 * @param mechFcltyUseYn the mechFcltyUseYn to set
	 */
	public void setMechFcltyUseYn(String mechFcltyUseYn) {
		this.mechFcltyUseYn = mechFcltyUseYn;
	}
	/**
	 * @return the elctyFcltyUseYn
	 */
	public String getElctyFcltyUseYn() {
		return elctyFcltyUseYn;
	}
	/**
	 * @param elctyFcltyUseYn the elctyFcltyUseYn to set
	 */
	public void setElctyFcltyUseYn(String elctyFcltyUseYn) {
		this.elctyFcltyUseYn = elctyFcltyUseYn;
	}
	/**
	 * @return the infoCommFcltyUseYn
	 */
	public String getInfoCommFcltyUseYn() {
		return infoCommFcltyUseYn;
	}
	/**
	 * @param infoCommFcltyUseYn the infoCommFcltyUseYn to set
	 */
	public void setInfoCommFcltyUseYn(String infoCommFcltyUseYn) {
		this.infoCommFcltyUseYn = infoCommFcltyUseYn;
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
	 * @return the flawEndDt
	 */
	public String getFlawEndDt() {
		return flawEndDt;
	}
	/**
	 * @param flawEndDt the flawEndDt to set
	 */
	public void setFlawEndDt(String flawEndDt) {
		this.flawEndDt = flawEndDt;
	}
	/**
	 * @return the fcltsStateCls
	 */
	public String getFcltsStateCls() {
		return fcltsStateCls;
	}
	/**
	 * @param fcltsStateCls the fcltsStateCls to set
	 */
	public void setFcltsStateCls(String fcltsStateCls) {
		this.fcltsStateCls = fcltsStateCls;
	}
	/**
	 * @return the fcltsSummary
	 */
	public String getFcltsSummary() {
		return fcltsSummary;
	}
	/**
	 * @param fcltsSummary the fcltsSummary to set
	 */
	public void setFcltsSummary(String fcltsSummary) {
		this.fcltsSummary = fcltsSummary;
	}
	/**
	 * @return the fcltsScale
	 */
	public String getFcltsScale() {
		return fcltsScale;
	}
	/**
	 * @param fcltsScale the fcltsScale to set
	 */
	public void setFcltsScale(String fcltsScale) {
		this.fcltsScale = fcltsScale;
	}
	/**
	 * @return the cnstDt
	 */
	public String getCnstDt() {
		return cnstDt;
	}
	/**
	 * @param cnstDt the cnstDt to set
	 */
	public void setCnstDt(String cnstDt) {
		this.cnstDt = cnstDt;
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
	 * @return the stndrd
	 */
	public String getStndrd() {
		return stndrd;
	}
	/**
	 * @param stndrd the stndrd to set
	 */
	public void setStndrd(String stndrd) {
		this.stndrd = stndrd;
	}
	/**
	 * @return the qy
	 */
	public String getQy() {
		return qy;
	}
	/**
	 * @param qy the qy to set
	 */
	public void setQy(String qy) {
		this.qy = qy;
	}
	/**
	 * @return the etcStndrd
	 */
	public String getEtcStndrd() {
		return etcStndrd;
	}
	/**
	 * @param etcStndrd the etcStndrd to set
	 */
	public void setEtcStndrd(String etcStndrd) {
		this.etcStndrd = etcStndrd;
	}
	/**
	 * @return the etcQy
	 */
	public String getEtcQy() {
		return etcQy;
	}
	/**
	 * @param etcQy the etcQy to set
	 */
	public void setEtcQy(String etcQy) {
		this.etcQy = etcQy;
	}



}
