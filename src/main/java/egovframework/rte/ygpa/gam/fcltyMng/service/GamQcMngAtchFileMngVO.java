/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 5. 6.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 5. 6.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamQcMngAtchFileMngVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String sFcltsMngGroupNo;		// 검색 시설물 관리 그룹 번호
	private String sQcMngSeq;				// 검색 점검 관리 순번
	private	String sFcltsJobSe;				// 검색 시설물 업무 구분

	private	String fcltsMngGroupNo;			// 시설물 관리 그룹 번호
	private String qcMngSeq;				// 점검 관리 순번
	private	String fcltsJobSe;				// 시설물 업무 구분
	private	String atchFileSeq;				// 첨부 파일 순번
	private	String atchFileSe;				// 첨부 파일 구분
	private	String atchFileSj;				// 첨부 파일 설명
	private	String atchFileNmLogic;			// 첨부 파일 명 논리
	private	String atchFileNmPhysicl;		// 첨부 파일 명 물리
	private String atchFileWritngDt;		// 첨부 파일 작성 일자
	private	String atchFileRm;				// 첨부 파일 비고
	private	String regUsr;					// 등록자
	private	String registDt;				// 등록일시
	private	String updUsr;					// 수정자
	private	String updtDt;					// 수정일시
	private	String atchFileSeNm;			// 첨부 파일 구분 명
	private	String atchFileSelChk;			// 첨부 파일 선택 검사

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
	 * @return the sQcMngSeq
	 */
	public String getsQcMngSeq() {
		return sQcMngSeq;
	}
	/**
	 * @param sQcMngSeq the sQcMngSeq to set
	 */
	public void setsQcMngSeq(String sQcMngSeq) {
		this.sQcMngSeq = sQcMngSeq;
	}
	/**
	 * @return the sFcltsJobSe
	 */
	public String getsFcltsJobSe() {
		return sFcltsJobSe;
	}
	/**
	 * @param sFcltsJobSe the sFcltsJobSe to set
	 */
	public void setsFcltsJobSe(String sFcltsJobSe) {
		this.sFcltsJobSe = sFcltsJobSe;
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
	 * @return the qcMngSeq
	 */
	public String getQcMngSeq() {
		return qcMngSeq;
	}
	/**
	 * @param qcMngSeq the qcMngSeq to set
	 */
	public void setQcMngSeq(String qcMngSeq) {
		this.qcMngSeq = qcMngSeq;
	}
	/**
	 * @return the fcltsJobSe
	 */
	public String getFcltsJobSe() {
		return fcltsJobSe;
	}
	/**
	 * @param fcltsJobSe the fcltsJobSe to set
	 */
	public void setFcltsJobSe(String fcltsJobSe) {
		this.fcltsJobSe = fcltsJobSe;
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
	 * @return the atchFileRm
	 */
	public String getAtchFileRm() {
		return atchFileRm;
	}
	/**
	 * @param atchFileRm the atchFileRm to set
	 */
	public void setAtchFileRm(String atchFileRm) {
		this.atchFileRm = atchFileRm;
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
	/**
	 * @return the atchFileSelChk
	 */
	public String getAtchFileSelChk() {
		return atchFileSelChk;
	}
	/**
	 * @param atchFileSelChk the atchFileSelChk to set
	 */
	public void setAtchFileSelChk(String atchFileSelChk) {
		this.atchFileSelChk = atchFileSelChk;
	}

}
