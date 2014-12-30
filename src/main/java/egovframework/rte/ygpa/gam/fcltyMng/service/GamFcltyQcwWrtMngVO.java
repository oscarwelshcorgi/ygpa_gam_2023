/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 24.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltyQcwWrtMngVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;

	/**시설물업무구분(조회조건)*/
	private String sFcltsJobSe;
	
	/**점검관리명(조회조건)*/
	private String sQcMngNm;
	
	/**점검진단구분(조회조건)*/
	private String sQcInspSe;

	/**시설물관리그룹번호(조회조건)*/
	private String sFcltsMngGroupNo;
	
	/**점검관리순번(조회조건)*/
	private String sQcMngSeq;
	
	/**시행시작일(조회조건)*/
	private String sQcInspDtFr;
	
	/**시행종료일(조회조건)*/
	private String sQcInspDtTo;

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
	 * @return the sQcMngNm
	 */
	public String getsQcMngNm() {
		return sQcMngNm;
	}

	/**
	 * @param sQcMngNm the sQcMngNm to set
	 */
	public void setsQcMngNm(String sQcMngNm) {
		this.sQcMngNm = sQcMngNm;
	}

	/**
	 * @return the sQcInspSe
	 */
	public String getsQcInspSe() {
		return sQcInspSe;
	}

	/**
	 * @param sQcInspSe the sQcInspSe to set
	 */
	public void setsQcInspSe(String sQcInspSe) {
		this.sQcInspSe = sQcInspSe;
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
	 * @return the sQcInspDtFr
	 */
	public String getsQcInspDtFr() {
		return sQcInspDtFr;
	}

	/**
	 * @param sQcInspDtFr the sQcInspDtFr to set
	 */
	public void setsQcInspDtFr(String sQcInspDtFr) {
		this.sQcInspDtFr = sQcInspDtFr;
	}

	/**
	 * @return the sQcInspDtTo
	 */
	public String getsQcInspDtTo() {
		return sQcInspDtTo;
	}

	/**
	 * @param sQcInspDtTo the sQcInspDtTo to set
	 */
	public void setsQcInspDtTo(String sQcInspDtTo) {
		this.sQcInspDtTo = sQcInspDtTo;
	}

}
