/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author 김종민
 * @since 2014. 12. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 10.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltsMngGroupService {
	/**
	 * 시설물관리그룹 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	List selectFcltsMngGroupList(GamFcltsMngGroupVO searchVO) throws Exception;
	
	/**
	 * 시설물관리그룹 목록 총수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectFcltsMngGroupListTotCnt(GamFcltsMngGroupVO searchVO) throws Exception;
	
	/**
	 * 시설물 관리그룹 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	EgovMap selectFcltsMngGroupDetail(Map searchVO) throws Exception;
	
	/**
	 * 시설물관리그룹 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void insertFcltsMngGroupDetail(Map<String, Object> vo) throws Exception;
	
	/**
	 * 시설물관리그룹 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void updateFcltsMngGroupDetail(Map<String, Object> vo) throws Exception;
	
	/**
	 * 시설물관리그룹 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void deleteFcltsMngGroupDetail(Map<String, Object> vo) throws Exception;
}
