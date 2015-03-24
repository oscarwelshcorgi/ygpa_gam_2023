/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 3. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 24.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamPopupAtchDirFileVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String listSe;				// LIST 구분
	private	String dirNo;				// 디렉토리 번호
	private	String dirNm;				// 디렉토리 명
	private	String dirPath;				// 디렉토리 PATH
	private	String fileNo;				// 파일 번호
	private	String fileNm;				// 파일 명
	private	String sSearchSe;			// 검색 구분
	private	String sFcltsJobSe;			// 검색 시설물 업무 구분
	private	String sDirFileNm;			// 검색 디렉토리/파일 명

	/**
	 * @return the listSe
	 */
	public String getListSe() {
		return listSe;
	}
	/**
	 * @param listSe the listSe to set
	 */
	public void setListSe(String listSe) {
		this.listSe = listSe;
	}
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
	 * @return the fileNo
	 */
	public String getFileNo() {
		return fileNo;
	}
	/**
	 * @param fileNo the fileNo to set
	 */
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	/**
	 * @return the fileNm
	 */
	public String getFileNm() {
		return fileNm;
	}
	/**
	 * @param fileNm the fileNm to set
	 */
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	/**
	 * @return the sSearchSe
	 */
	public String getsSearchSe() {
		return sSearchSe;
	}
	/**
	 * @param sSearchSe the sSearchSe to set
	 */
	public void setsSearchSe(String sSearchSe) {
		this.sSearchSe = sSearchSe;
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
	 * @return the sDirFileNm
	 */
	public String getsDirFileNm() {
		return sDirFileNm;
	}
	/**
	 * @param sDirFileNm the sDirFileNm to set
	 */
	public void setsDirFileNm(String sDirFileNm) {
		this.sDirFileNm = sDirFileNm;
	}

}
