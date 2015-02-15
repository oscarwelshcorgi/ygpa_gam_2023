/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 15.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltsAtchFileMngVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String atchFileNo;				// 첨부 파일 번호
	private	String atchFileNmLogic;			// 첨부 파일 명 논리
	private	String atchFileNmPhysicl;		// 첨부 파일 명 물리
	private	String atchFileSe;				// 첨부 파일 구분
	private	String atchFileDirNo;			// 디렉토리 번호
	private	String atchFileFcltsDataSe;		// 시설물 자료 구분
	private	String atchFileFcltsMngNo;		// 시설물 관리 번호
	private	String atchFileFcltsJobSe;		// 시설물 업무 구분
	private	String atchFileFcltsMngSeq;		// 시설물 관리 순번
	private	String regUsr;					// 등록자
	private	String registDt;				// 등록일시
	private	String updUsr;					// 수정자
	private	String updtDt;					// 수정일시

	/**
	 * @return the atchFileNo
	 */
	public String getAtchFileNo() {
		return atchFileNo;
	}
	/**
	 * @param atchFileNo the atchFileNo to set
	 */
	public void setAtchFileNo(String atchFileNo) {
		this.atchFileNo = atchFileNo;
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
	 * @return the atchFileDirNo
	 */
	public String getAtchFileDirNo() {
		return atchFileDirNo;
	}
	/**
	 * @param atchFileDirNo the atchFileDirNo to set
	 */
	public void setAtchFileDirNo(String atchFileDirNo) {
		this.atchFileDirNo = atchFileDirNo;
	}
	/**
	 * @return the atchFileFcltsDataSe
	 */
	public String getAtchFileFcltsDataSe() {
		return atchFileFcltsDataSe;
	}
	/**
	 * @param atchFileFcltsDataSe the atchFileFcltsDataSe to set
	 */
	public void setAtchFileFcltsDataSe(String atchFileFcltsDataSe) {
		this.atchFileFcltsDataSe = atchFileFcltsDataSe;
	}
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
	 * @return the atchFileFcltsJobSe
	 */
	public String getAtchFileFcltsJobSe() {
		return atchFileFcltsJobSe;
	}
	/**
	 * @param atchFileFcltsJobSe the atchFileFcltsJobSe to set
	 */
	public void setAtchFileFcltsJobSe(String atchFileFcltsJobSe) {
		this.atchFileFcltsJobSe = atchFileFcltsJobSe;
	}
	/**
	 * @return the atchFileFcltsMngSeq
	 */
	public String getAtchFileFcltsMngSeq() {
		return atchFileFcltsMngSeq;
	}
	/**
	 * @param atchFileFcltsMngSeq the atchFileFcltsMngSeq to set
	 */
	public void setAtchFileFcltsMngSeq(String atchFileFcltsMngSeq) {
		this.atchFileFcltsMngSeq = atchFileFcltsMngSeq;
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

}
