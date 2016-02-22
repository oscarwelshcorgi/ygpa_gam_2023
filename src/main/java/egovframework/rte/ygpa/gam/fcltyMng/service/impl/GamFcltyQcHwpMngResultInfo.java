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
	private Map<String, Object> chargerInfo1 = null; //검사자 정보1
	private Map<String, Object> chargerInfo2 = null; //검사자 정보2
	
	public GamFcltyQcHwpMngResultInfo(EgovMap qcDetailData, List<EgovMap> qcResultItemList, EgovMap mngGroupInfo, Map<String, Object> chargerInfo1, Map<String, Object> chargerInfo2) {
		this.qcDetailData = qcDetailData;
		this.qcResultItemList = qcResultItemList;
		this.mngGroupInfo = mngGroupInfo;
		this.chargerInfo1 = chargerInfo1;
		this.chargerInfo2 = chargerInfo2;
	}
	
	public void setQcDetailData(EgovMap value) { this.qcDetailData = value; } 
	public EgovMap getQcDetailData() { return this.qcDetailData; }
	public void setQcResultItemList(List<EgovMap> value) { this.qcResultItemList = value; }
	public List<EgovMap> getQcResultItemList() { return this.qcResultItemList; }
	public void setMngGroupInfo(EgovMap value) { this.mngGroupInfo = value; } 
	public EgovMap getMngGroupInfo() { return this.mngGroupInfo; }
	public void setChargerInfo1(Map<String, Object> value) { this.chargerInfo1 = value; }
	public Map<String, Object> getChargerInfo1() { return this.chargerInfo1; }
	public void setChargerInfo2(Map<String, Object> value) { this.chargerInfo2 = value; }
	public Map<String, Object> getChargerInfo2() { return this.chargerInfo2; }
}
