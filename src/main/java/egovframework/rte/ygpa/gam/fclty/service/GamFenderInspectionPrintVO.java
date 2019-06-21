/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 17.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFenderInspectionPrintVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;


	
	/** 연도(PK) */ 
	private String year;
	/** 시설물 관리 그룹 번호(PK)(FK) */ 
	private String fcltsMngGroupNo;
	/** 시설물 종별 */ 
	private String fcltsGbn;
	/** 공사 일자 */ 
	private String cnstDt;
	/** 소유자 */ 
	private String owner;
	/** 시공자 */ 
	private String cnstrtr;
	/** 항만시설 규격 */ 
	private String prtFcltyStndrd;
	/** 시설 수량 */ 
	
	private String fcltsMngGroupNm;
	private String FcltsGbnNm;
	private String fcltsSeNm;
	private String fcltsKndNm;
	private String bldDt;
	private String loc;
	
	
	private String prtPrtFcltyCnt;
	/** 점검일자1 */ 
	private String chckDeOne;
	/** 점검자1 */ 
	private String insctrOne;
	/** 점검내용1 */ 
	private String chckCnOne;
	/** 상태평가1 */ 
	private String sttusEvlOne;
	/** 사진1 */ 
	private String photoOne;
	/** 점검표1 */ 
	private String chckTableOne;
	/** 점검일자2 */ 
	private String chckDeTwo;
	/** 점검자2 */ 
	private String insctrTwo;
	/** 점검내용2 */ 
	private String chckCnTwo;
	/** 상태평가2 */ 
	private String sttusEvlTwo;
	/** 사진2 */ 
	private String photoTwo;
	/** 점검표2 */ 
	private String chckTableTwo;
	/** 점검일자3 */ 
	private String chckDeThree;
	/** 점검자3 */ 
	private String insctrThree;
	/** 점검내용3 */ 
	private String chckCnThree;
	/** 상태평가3 */ 
	private String sttusEvlThree;
	/** 사진3 */ 
	private String photoThree;
	/** 점검표3 */ 
	private String chckTableThree;
	/** 등록자 */ 
	private String register;
	/** 등록일 */ 
	private String rgsde;
	/** 수정자 */ 
	private String updusr;
	/** 수정일 */ 
	private String updde;

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
	 * @return the prtFcltyStndrd
	 */
	public String getPrtFcltyStndrd() {
		return prtFcltyStndrd;
	}
	/**
	 * @param prtFcltyStndrd the prtFcltyStndrd to set
	 */
	public void setPrtFcltyStndrd(String prtFcltyStndrd) {
		this.prtFcltyStndrd = prtFcltyStndrd;
	}
	/**
	 * @return the prtPrtFcltyCnt
	 */
	public String getPrtPrtFcltyCnt() {
		return prtPrtFcltyCnt;
	}
	/**
	 * @param prtPrtFcltyCnt the prtPrtFcltyCnt to set
	 */
	public void setPrtPrtFcltyCnt(String prtPrtFcltyCnt) {
		this.prtPrtFcltyCnt = prtPrtFcltyCnt;
	}
	/**
	 * @return the chckDeOne
	 */
	public String getChckDeOne() {
		return chckDeOne;
	}
	/**
	 * @param chckDeOne the chckDeOne to set
	 */
	public void setChckDeOne(String chckDeOne) {
		this.chckDeOne = chckDeOne;
	}
	/**
	 * @return the insctrOne
	 */
	public String getInsctrOne() {
		return insctrOne;
	}
	/**
	 * @param insctrOne the insctrOne to set
	 */
	public void setInsctrOne(String insctrOne) {
		this.insctrOne = insctrOne;
	}
	/**
	 * @return the chckCnOne
	 */
	public String getChckCnOne() {
		return chckCnOne;
	}
	/**
	 * @param chckCnOne the chckCnOne to set
	 */
	public void setChckCnOne(String chckCnOne) {
		this.chckCnOne = chckCnOne;
	}
	/**
	 * @return the sttusEvlOne
	 */
	public String getSttusEvlOne() {
		return sttusEvlOne;
	}
	/**
	 * @param sttusEvlOne the sttusEvlOne to set
	 */
	public void setSttusEvlOne(String sttusEvlOne) {
		this.sttusEvlOne = sttusEvlOne;
	}
	/**
	 * @return the photoOne
	 */
	public String getPhotoOne() {
		return photoOne;
	}
	/**
	 * @param photoOne the photoOne to set
	 */
	public void setPhotoOne(String photoOne) {
		this.photoOne = photoOne;
	}
	/**
	 * @return the chckTableOne
	 */
	public String getChckTableOne() {
		return chckTableOne;
	}
	/**
	 * @param chckTableOne the chckTableOne to set
	 */
	public void setChckTableOne(String chckTableOne) {
		this.chckTableOne = chckTableOne;
	}
	/**
	 * @return the chckDeTwo
	 */
	public String getChckDeTwo() {
		return chckDeTwo;
	}
	/**
	 * @param chckDeTwo the chckDeTwo to set
	 */
	public void setChckDeTwo(String chckDeTwo) {
		this.chckDeTwo = chckDeTwo;
	}
	/**
	 * @return the insctrTwo
	 */
	public String getInsctrTwo() {
		return insctrTwo;
	}
	/**
	 * @param insctrTwo the insctrTwo to set
	 */
	public void setInsctrTwo(String insctrTwo) {
		this.insctrTwo = insctrTwo;
	}
	/**
	 * @return the chckCnTwo
	 */
	public String getChckCnTwo() {
		return chckCnTwo;
	}
	/**
	 * @param chckCnTwo the chckCnTwo to set
	 */
	public void setChckCnTwo(String chckCnTwo) {
		this.chckCnTwo = chckCnTwo;
	}
	/**
	 * @return the sttusEvlTwo
	 */
	public String getSttusEvlTwo() {
		return sttusEvlTwo;
	}
	/**
	 * @param sttusEvlTwo the sttusEvlTwo to set
	 */
	public void setSttusEvlTwo(String sttusEvlTwo) {
		this.sttusEvlTwo = sttusEvlTwo;
	}
	/**
	 * @return the photoTwo
	 */
	public String getPhotoTwo() {
		return photoTwo;
	}
	/**
	 * @param photoTwo the photoTwo to set
	 */
	public void setPhotoTwo(String photoTwo) {
		this.photoTwo = photoTwo;
	}
	/**
	 * @return the chckTableTwo
	 */
	public String getChckTableTwo() {
		return chckTableTwo;
	}
	/**
	 * @param chckTableTwo the chckTableTwo to set
	 */
	public void setChckTableTwo(String chckTableTwo) {
		this.chckTableTwo = chckTableTwo;
	}
	/**
	 * @return the chckDeThree
	 */
	public String getChckDeThree() {
		return chckDeThree;
	}
	/**
	 * @param chckDeThree the chckDeThree to set
	 */
	public void setChckDeThree(String chckDeThree) {
		this.chckDeThree = chckDeThree;
	}
	/**
	 * @return the insctrThree
	 */
	public String getInsctrThree() {
		return insctrThree;
	}
	/**
	 * @param insctrThree the insctrThree to set
	 */
	public void setInsctrThree(String insctrThree) {
		this.insctrThree = insctrThree;
	}
	/**
	 * @return the chckCnThree
	 */
	public String getChckCnThree() {
		return chckCnThree;
	}
	/**
	 * @param chckCnThree the chckCnThree to set
	 */
	public void setChckCnThree(String chckCnThree) {
		this.chckCnThree = chckCnThree;
	}
	/**
	 * @return the sttusEvlThree
	 */
	public String getSttusEvlThree() {
		return sttusEvlThree;
	}
	/**
	 * @param sttusEvlThree the sttusEvlThree to set
	 */
	public void setSttusEvlThree(String sttusEvlThree) {
		this.sttusEvlThree = sttusEvlThree;
	}
	/**
	 * @return the photoThree
	 */
	public String getPhotoThree() {
		return photoThree;
	}
	/**
	 * @param photoThree the photoThree to set
	 */
	public void setPhotoThree(String photoThree) {
		this.photoThree = photoThree;
	}
	/**
	 * @return the chckTableThree
	 */
	public String getChckTableThree() {
		return chckTableThree;
	}
	/**
	 * @param chckTableThree the chckTableThree to set
	 */
	public void setChckTableThree(String chckTableThree) {
		this.chckTableThree = chckTableThree;
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
	 * @return the fcltsGbnNm
	 */
	public String getFcltsGbnNm() {
		return FcltsGbnNm;
	}
	/**
	 * @param fcltsGbnNm the fcltsGbnNm to set
	 */
	public void setFcltsGbnNm(String fcltsGbnNm) {
		FcltsGbnNm = fcltsGbnNm;
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

	
}
