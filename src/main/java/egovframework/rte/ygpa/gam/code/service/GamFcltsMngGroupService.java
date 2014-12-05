/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 17.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltsMngGroupService {
	
	/**
	 *  메인시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectMainFcltsMngGroupList () throws Exception;
	
	
	/**
	 *  시설물그룹관리관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltsMngGroupList (GamFcltsMngGroupVO vo) throws Exception;
	
	
	/**
	 *  시설물그룹관리관리 목록 총수
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	int selectFcltsMngGroupListTotCnt (GamFcltsMngGroupVO vo) throws Exception;
	
	
	/**
	 *  시설물그룹관리관리 상세보기/수정
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	EgovMap selectFcltsMngGroupDetail (Map vo) throws Exception;

	
	
	/**
	 *  시설물그룹관리관리 신규코드 생성
	 * @param vo
	 * @return String
	 * @throws Exception
	 */
	String selectNewFcltsMngGroup (GamFcltsMngGroupVO vo) throws Exception;
	
	
	/**
	 * 시설물그룹관리 코드입력
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	void insertFcltsMngGroup (GamFcltsMngGroupVO vo) throws Exception;
	
	
	/**
	 * 시설물그룹관리 코드수정
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	void updateFcltsMngGroup (GamFcltsMngGroupVO vo) throws Exception;
	
	
	/**
	 * 시설물그룹관리 코드삭제
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	void deleteFcltsMngGroup (GamFcltsMngGroupVO vo) throws Exception;
	
	
	/**
	 * 시설물그룹관리 상위코드 LEAF_YN값 수정
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	void updateChnageParentLeafYn (GamFcltsMngGroupVO vo) throws Exception;
	
	
	/**
	 *  시설물그룹관리 상위코드에 따르는 하위코드 총수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltsClParentCdListCnt(GamFcltsMngGroupVO vo) throws Exception;

}
