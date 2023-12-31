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
public class GamRoadMaintenanceVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 검색 조건 */
	private String sYear;
	private String sSn;
	private String sFcltsGbn;
	private String sFcltsMngGroupNo;
	private String sFcltsMngGroupNm;
	private String sCntrwkBegin;
	private String sCntrwkEnd;

	/** 항구분 */
	private String prtAtCodeNm ;
	/** 종별 */
	private String fcltsGbnNm;
	/** 관리 그룹 명 */
	private String fcltsMngGroupNm ;
	/** 위치 */
	private String loc;


	/** 연도(PK) */
	private String year;
	/** 순번(PK) */
	private String sn;
	/** 시설물 관리 그룹 번호(PK)(FK) */
	private String fcltsMngGroupNo;
	/** 시행주체 */
	private String opertnMby;
	/** 공사명칭 */
	private String cntrwkNm;
	/** 공사시작 */
	private String cntrwkBegin;
	/** 공사종료 */
	private String cntrwkEnd;
	/** 시공자 */
	private String cnstrtr;
	/** 사업책임자 */
	private String bsnsRspnber;
	/** 공사비 */
	private String cntrwkCt;
	/** 사진 */
	private String photo;
	/** 준공검사조서 */
	private String competInspctWtnnc;
	/** 등록자 */
	private String register;
	/** 등록일 */
	private String rgsde;
	/** 수정자 */
	private String updusr;
	/** 수정일 */
	private String updde;
	/** 하자기간 */
	private String flawEndTerm;
	/**하자만료일자*/
	private String flawEndDt;
	/** 준공일자 */
	private String bldDt;
	/** 보수위치 */
	private String repairLoc;
	/** 보수내용 */
	private String repairCn;
	/** 보수상태 */
	private String repairSttus;
	/**공사내용*/
	private String cntrwkCn;

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
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}
	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
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
	 * @return the opertnMby
	 */
	public String getOpertnMby() {
		return opertnMby;
	}
	/**
	 * @param opertnMby the opertnMby to set
	 */
	public void setOpertnMby(String opertnMby) {
		this.opertnMby = opertnMby;
	}
	/**
	 * @return the cntrwkNm
	 */
	public String getCntrwkNm() {
		return cntrwkNm;
	}
	/**
	 * @param cntrwkNm the cntrwkNm to set
	 */
	public void setCntrwkNm(String cntrwkNm) {
		this.cntrwkNm = cntrwkNm;
	}
	/**
	 * @return the cntrwkBegin
	 */
	public String getCntrwkBegin() {
		return cntrwkBegin;
	}
	/**
	 * @param cntrwkBegin the cntrwkBegin to set
	 */
	public void setCntrwkBegin(String cntrwkBegin) {
		this.cntrwkBegin = cntrwkBegin;
	}
	/**
	 * @return the cntrwkEnd
	 */
	public String getCntrwkEnd() {
		return cntrwkEnd;
	}
	/**
	 * @param cntrwkEnd the cntrwkEnd to set
	 */
	public void setCntrwkEnd(String cntrwkEnd) {
		this.cntrwkEnd = cntrwkEnd;
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
	 * @return the bsnsRspnber
	 */
	public String getBsnsRspnber() {
		return bsnsRspnber;
	}
	/**
	 * @param bsnsRspnber the bsnsRspnber to set
	 */
	public void setBsnsRspnber(String bsnsRspnber) {
		this.bsnsRspnber = bsnsRspnber;
	}
	/**
	 * @return the cntrwkCt
	 */
	public String getCntrwkCt() {
		return cntrwkCt;
	}
	/**
	 * @param cntrwkCt the cntrwkCt to set
	 */
	public void setCntrwkCt(String cntrwkCt) {
		this.cntrwkCt = cntrwkCt;
	}
	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * @return the competInspctWtnnc
	 */
	public String getCompetInspctWtnnc() {
		return competInspctWtnnc;
	}
	/**
	 * @param competInspctWtnnc the competInspctWtnnc to set
	 */
	public void setCompetInspctWtnnc(String competInspctWtnnc) {
		this.competInspctWtnnc = competInspctWtnnc;
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
	 * @return the sYear
	 */
	public String getsYear() {
		return sYear;
	}
	/**
	 * @param sYear the sYear to set
	 */
	public void setsYear(String sYear) {
		this.sYear = sYear;
	}
	/**
	 * @return the sSn
	 */
	public String getsSn() {
		return sSn;
	}
	/**
	 * @param sSn the sSn to set
	 */
	public void setsSn(String sSn) {
		this.sSn = sSn;
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
	 * @return the sCntrwkBegin
	 */
	public String getsCntrwkBegin() {
		return sCntrwkBegin;
	}
	/**
	 * @param sCntrwkBegin the sCntrwkBegin to set
	 */
	public void setsCntrwkBegin(String sCntrwkBegin) {
		this.sCntrwkBegin = sCntrwkBegin;
	}
	/**
	 * @return the sCntrwkEnd
	 */
	public String getsCntrwkEnd() {
		return sCntrwkEnd;
	}
	/**
	 * @param sCntrwkEnd the sCntrwkEnd to set
	 */
	public void setsCntrwkEnd(String sCntrwkEnd) {
		this.sCntrwkEnd = sCntrwkEnd;
	}
	/**
	 * @return the flawEndTerm
	 */
	public String getFlawEndTerm() {
		return flawEndTerm;
	}
	/**
	 * @param flawEndTerm the flawEndTerm to set
	 */
	public void setFlawEndTerm(String flawEndTerm) {
		this.flawEndTerm = flawEndTerm;
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
	 * @return the repairLoc
	 */
	public String getRepairLoc() {
		return repairLoc;
	}
	/**
	 * @param repairLoc the repairLoc to set
	 */
	public void setRepairLoc(String repairLoc) {
		this.repairLoc = repairLoc;
	}
	/**
	 * @return the repairCn
	 */
	public String getRepairCn() {
		return repairCn;
	}
	/**
	 * @param repairCn the repairCn to set
	 */
	public void setRepairCn(String repairCn) {
		this.repairCn = repairCn;
	}
	/**
	 * @return the repairSttus
	 */
	public String getRepairSttus() {
		return repairSttus;
	}
	/**
	 * @param repairSttus the repairSttus to set
	 */
	public void setRepairSttus(String repairSttus) {
		this.repairSttus = repairSttus;
	}
	/**
	 * @return the cntrwkCn
	 */
	public String getCntrwkCn() {
		return cntrwkCn;
	}
	/**
	 * @param cntrwkCn the cntrwkCn to set
	 */
	public void setCntrwkCn(String cntrwkCn) {
		this.cntrwkCn = cntrwkCn;
	}
}
