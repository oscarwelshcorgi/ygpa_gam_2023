/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 1. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 1. 15.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltySpecAtchFileVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String atchFileFcltsMngNo;		// 시설물 관리 번호
	private	String atchFileSeq;				// 첨부 파일 순번
	private	String atchFileSj;				// 첨부 파일 제목
	private	String atchFileNmLogic;			// 첨부 파일 명 논리
	private	String atchFileNmPhysicl;		// 첨부 파일 명 물리
	private	String regUsr;					// 등록자
	private	String registDt;				// 등록일시
	private	String updUsr;					// 수정자
	private	String updtDt;					// 수정일시
	private	String atchFileSe;				// 첨부 파일 구분
	private	String atchFileWritngDt;		// 첨부 파일 작성 일시
	private	String atchFileSeNm;			// 첨부 파일 구분 명

	/**
	 * @return the atchFileFcltsMngNo
	 */
	public String getAtchFileFcltsMngNo() {
		return atchFileFcltsMngNo;
	}
	/**
	 * @param atchFileFcltsMngNo the atchFileFcltsMngNo to set
	 */
	public void setAtchFileFcltsMngNo(String atchFileFcltsMngNo) {
		this.atchFileFcltsMngNo = atchFileFcltsMngNo;
	}
	/**
	 * @return the atchFileSeq
	 */
	public String getAtchFileSeq() {
		return atchFileSeq;
	}
	/**
	 * @param atchFileSeq the atchFileSeq to set
	 */
	public void setAtchFileSeq(String atchFileSeq) {
		this.atchFileSeq = atchFileSeq;
	}
	/**
	 * @return the atchFileSj
	 */
	public String getAtchFileSj() {
		return atchFileSj;
	}
	/**
	 * @param atchFileSj the atchFileSj to set
	 */
	public void setAtchFileSj(String atchFileSj) {
		this.atchFileSj = atchFileSj;
	}
	/**
	 * @return the atchFileNmLogic
	 */
	public String getAtchFileNmLogic() {
		return atchFileNmLogic;
	}
	/**
	 * @param atchFileNmLogic the atchFileNmLogic to set
	 */
	public void setAtchFileNmLogic(String atchFileNmLogic) {
		this.atchFileNmLogic = atchFileNmLogic;
	}
	/**
	 * @return the atchFileNmPhysicl
	 */
	public String getAtchFileNmPhysicl() {
		return atchFileNmPhysicl;
	}
	/**
	 * @param atchFileNmPhysicl the atchFileNmPhysicl to set
	 */
	public void setAtchFileNmPhysicl(String atchFileNmPhysicl) {
		this.atchFileNmPhysicl = atchFileNmPhysicl;
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
	 * @return the atchFileSe
	 */
	public String getAtchFileSe() {
		return atchFileSe;
	}
	/**
	 * @param atchFileSe the atchFileSe to set
	 */
	public void setAtchFileSe(String atchFileSe) {
		this.atchFileSe = atchFileSe;
	}
	/**
	 * @return the atchFileWritngDt
	 */
	public String getAtchFileWritngDt() {
		return atchFileWritngDt;
	}
	/**
	 * @param atchFileWritngDt the atchFileWritngDt to set
	 */
	public void setAtchFileWritngDt(String atchFileWritngDt) {
		this.atchFileWritngDt = atchFileWritngDt;
	}
	/**
	 * @return the atchFileSeNm
	 */
	public String getAtchFileSeNm() {
		return atchFileSeNm;
	}
	/**
	 * @param atchFileSeNm the atchFileSeNm to set
	 */
	public void setAtchFileSeNm(String atchFileSeNm) {
		this.atchFileSeNm = atchFileSeNm;
	}

}
