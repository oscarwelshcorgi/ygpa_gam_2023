/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 12. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 15.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltyQcHistInqireVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;

	/**점검진단기관명(조회조건)*/
	private String sQcInspInsttNm;
	
	/**시설물관리번호(조회조건)*/
	private String sFcltsMngNo;
	
	/**점검진단 시작일(조회조건)*/
	private String sQcInspDtFr;
	
	/**점검진단 종료일(조회조건)*/
	private String sQcInspDtTo;

	/**점검진단 업무구분(조회조건)*/
	private String sFcltsJobSe;
	
	/**
	 * @return the sQcInspInsttNm
	 */
	public String getsQcInspInsttNm() {
		return sQcInspInsttNm;
	}

	/**
	 * @param sQcInspInsttNm the sQcInspInsttNm to set
	 */
	public void setsQcInspInsttNm(String sQcInspInsttNm) {
		this.sQcInspInsttNm = sQcInspInsttNm;
	}

	/**
	 * @return the sFcltsMngNo
	 */
	public String getsFcltsMngNo() {
		return sFcltsMngNo;
	}

	/**
	 * @param sFcltsMngNo the sFcltsMngNo to set
	 */
	public void setsFcltsMngNo(String sFcltsMngNo) {
		this.sFcltsMngNo = sFcltsMngNo;
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
}
