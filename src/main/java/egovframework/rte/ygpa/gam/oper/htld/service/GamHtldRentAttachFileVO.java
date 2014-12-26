package egovframework.rte.ygpa.gam.oper.htld.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamHtldRentAttachFileVO
 * @Description : 배후단지사용 첨부파일 VO
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-12-22
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldRentAttachFileVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;

	private String quayGroupCd;

    /** 항코드 */
    private String prtAtCode;

    /**  관리 년도 */
    private String mngYear;

    /**  관리 번호 */
    private String mngNo;

    /**  관리 횟수  */
    private String mngCnt;

    /** 부두코드 */
    private String quayCd;

    /** 사진 순번 */
    private String photoSeq;

    /** 사진 제목 */
    private String photoSj;

    /** 파일명 물리 */
    private String filenmPhysicl;

    /** 파일명 논리 */
    private String filenmLogic;

    /** 촬영 일시 */
    private String shotDt;

    /** 사진 설명 */
    private String photoDesc;

    /** 등록자 */
    private String regUsr;

    /** 등록일시 */
    private String registDt;

    /** 수정자 */
    private String updUsr;

    /** 수정일시 */
    private String updtDt;

	public String getPrtAtCode() {
		return prtAtCode;
	}

	public void setPrtAtCode(String prtAtCode) {
		this.prtAtCode = prtAtCode;
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

	public String getQuayCd() {
		return quayCd;
	}

	public void setQuayCd(String quayCd) {
		this.quayCd = quayCd;
	}

	public String getPhotoSeq() {
		return photoSeq;
	}

	public void setPhotoSeq(String photoSeq) {
		this.photoSeq = photoSeq;
	}

	public String getPhotoSj() {
		return photoSj;
	}

	public void setPhotoSj(String photoSj) {
		this.photoSj = photoSj;
	}

	public String getFilenmPhysicl() {
		return filenmPhysicl;
	}

	public void setFilenmPhysicl(String filenmPhysicl) {
		this.filenmPhysicl = filenmPhysicl;
	}

	public String getFilenmLogic() {
		return filenmLogic;
	}

	public void setFilenmLogic(String filenmLogic) {
		this.filenmLogic = filenmLogic;
	}

	public String getShotDt() {
		return shotDt;
	}

	public void setShotDt(String shotDt) {
		this.shotDt = shotDt;
	}

	public String getPhotoDesc() {
		return photoDesc;
	}

	public void setPhotoDesc(String photoDesc) {
		this.photoDesc = photoDesc;
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

	public String getQuayGroupCd() {
		return quayGroupCd;
	}

	public void setQuayGroupCd(String quayGroupCd) {
		this.quayGroupCd = quayGroupCd;
	}



}
