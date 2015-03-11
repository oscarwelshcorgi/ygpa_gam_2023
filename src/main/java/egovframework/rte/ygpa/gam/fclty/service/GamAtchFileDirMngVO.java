/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 14.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 14.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamAtchFileDirMngVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String dirNo;				// 디렉토리 번호
	private	String dirNm;				// 디렉토리 명
	private	String dirPath;				// 디렉토리 PATH
	private	String dirUpperNo;			// 디렉토리 상위 번호
	private	String depthSort;			// 단계
	private	String leafYn;				// LEAF 여부
	private	String dirFcltsJobSe;		// 시설물 업무 구분
	private	String regUsr;				// 등록자
	private	String registDt;			// 등록일시
	private	String updUsr;				// 수정자
	private	String updtDt;				// 수정일시
	private	String dirQueryOption;		// 디렉토리 조회 OPTION

	/**
	 * @return the dirNo
	 */
	public String getDirNo() {
		return dirNo;
	}
	/**
	 * @param dirNo the dirNo to set
	 */
	public void setDirNo(String dirNo) {
		this.dirNo = dirNo;
	}
	/**
	 * @return the dirNm
	 */
	public String getDirNm() {
		return dirNm;
	}
	/**
	 * @param dirNm the dirNm to set
	 */
	public void setDirNm(String dirNm) {
		this.dirNm = dirNm;
	}
	/**
	 * @return the dirPath
	 */
	public String getDirPath() {
		return dirPath;
	}
	/**
	 * @param dirPath the dirPath to set
	 */
	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}
	/**
	 * @return the dirUpperNo
	 */
	public String getDirUpperNo() {
		return dirUpperNo;
	}
	/**
	 * @param dirUpperNo the dirUpperNo to set
	 */
	public void setDirUpperNo(String dirUpperNo) {
		this.dirUpperNo = dirUpperNo;
	}
	/**
	 * @return the depthSort
	 */
	public String getDepthSort() {
		return depthSort;
	}
	/**
	 * @param depthSort the depthSort to set
	 */
	public void setDepthSort(String depthSort) {
		this.depthSort = depthSort;
	}
	/**
	 * @return the leafYn
	 */
	public String getLeafYn() {
		return leafYn;
	}
	/**
	 * @param leafYn the leafYn to set
	 */
	public void setLeafYn(String leafYn) {
		this.leafYn = leafYn;
	}
	/**
	 * @return the dirFcltsJobSe
	 */
	public String getDirFcltsJobSe() {
		return dirFcltsJobSe;
	}
	/**
	 * @param dirFcltsJobSe the dirFcltsJobSe to set
	 */
	public void setDirFcltsJobSe(String dirFcltsJobSe) {
		this.dirFcltsJobSe = dirFcltsJobSe;
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
	 * @return the dirQueryOption
	 */
	public String getDirQueryOption() {
		return dirQueryOption;
	}
	/**
	 * @param dirQueryOption the dirQueryOption to set
	 */
	public void setDirQueryOption(String dirQueryOption) {
		this.dirQueryOption = dirQueryOption;
	}

}
