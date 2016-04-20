/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author Jongmin
 * @since 2016. 2. 20.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   아래한글 출력을 위한 점검결과 정보 클래스
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 2. 20.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltyQcHwpMngResultInfo {
	private EgovMap qcDetailData = null;  //점검 데이터
	private List<EgovMap> qcResultItemList = null; //점검 항목 목록
	private EgovMap mngGroupInfo = null;  //시설물 그룹 관리 정보(토목일 때만 사용)
	private List<Map<String, Object>> chargerInfoList = null; //검사자 정보리스트
	
	public GamFcltyQcHwpMngResultInfo(EgovMap qcDetailData, List<EgovMap> qcResultItemList, EgovMap mngGroupInfo,  List<Map<String, Object>> chargerInfoList) {
		this.qcDetailData = qcDetailData;
		this.qcResultItemList = qcResultItemList;
		this.mngGroupInfo = mngGroupInfo;
		this.chargerInfoList = chargerInfoList;
	}

	/**
	 * @return the qcDetailData
	 */
	public EgovMap getQcDetailData() {
		return qcDetailData;
	}

	/**
	 * @param qcDetailData the qcDetailData to set
	 */
	public void setQcDetailData(EgovMap qcDetailData) {
		this.qcDetailData = qcDetailData;
	}

	/**
	 * @return the qcResultItemList
	 */
	public List<EgovMap> getQcResultItemList() {
		return qcResultItemList;
	}

	/**
	 * @param qcResultItemList the qcResultItemList to set
	 */
	public void setQcResultItemList(List<EgovMap> qcResultItemList) {
		this.qcResultItemList = qcResultItemList;
	}

	/**
	 * @return the mngGroupInfo
	 */
	public EgovMap getMngGroupInfo() {
		return mngGroupInfo;
	}

	/**
	 * @param mngGroupInfo the mngGroupInfo to set
	 */
	public void setMngGroupInfo(EgovMap mngGroupInfo) {
		this.mngGroupInfo = mngGroupInfo;
	}

	/**
	 * @return the chargerInfoList
	 */
	public List<Map<String, Object>> getChargerInfoList() {
		return chargerInfoList;
	}

	/**
	 * @param chargerInfoList the chargerInfoList to set
	 */
	public void setChargerInfoList(List<Map<String, Object>> chargerInfoList) {
		this.chargerInfoList = chargerInfoList;
	}
}
