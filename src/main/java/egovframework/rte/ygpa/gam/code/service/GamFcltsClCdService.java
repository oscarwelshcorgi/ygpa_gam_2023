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

public interface GamFcltsClCdService {
	
	/**
	 *  메인시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectMainFcltsClCdList () throws Exception;
	
	
	/**
	 *  시설물 분류관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltsClCdList (GamFcltsClCdVO vo) throws Exception;
	
	
	/**
	 *  시설물 분류관리 목록 총수
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	int selectFcltsClCdListTotCnt (GamFcltsClCdVO vo) throws Exception;
	
	
	/**
	 *  시설물 분류관리 상세보기/수정
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	EgovMap selectFcltsClCdDetail (Map vo) throws Exception;
	
	
	/**
	 *  시설물분류 상위코드 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltsClUpperCdList (Map vo) throws Exception;
	
	
	/**
	 *  시설물 분류관리 신규코드 생성
	 * @param vo
	 * @return String
	 * @throws Exception
	 */
	String selectNewFcltsClCd (GamFcltsClCdVO vo) throws Exception;
	
	
	/**
	 * 시설물분류 코드입력
	 * @param GamFcltsClCdVO
	 * @return map
	 * @throws Exception
	 */
	void insertFcltsClCd (GamFcltsClCdVO vo) throws Exception;
	
	
	/**
	 * 시설물분류 코드수정
	 * @param GamFcltsClCdVO
	 * @return map
	 * @throws Exception
	 */
	void updateFcltsClCd (GamFcltsClCdVO vo) throws Exception;
	
	
	/**
	 * 시설물분류 코드삭제
	 * @param GamFcltsClCdVO
	 * @return map
	 * @throws Exception
	 */
	void deleteFcltsClCd (GamFcltsClCdVO vo) throws Exception;

}
