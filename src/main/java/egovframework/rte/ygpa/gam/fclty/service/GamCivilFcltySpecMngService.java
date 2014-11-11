/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 10.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamCivilFcltySpecMngService {
	
	/**
	 * 토목시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	List selectCivilFcltySpecMngList(GamCivilFcltySpecMngVO searchVO) throws Exception;
	
	/**
	 * 토목시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectCivilFcltySpecMngListTotCnt(GamCivilFcltySpecMngVO searchVO) throws Exception;

	/**
	 * 토목시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	EgovMap selectCivilFcltySpecMngDetail(Map searchVO) throws Exception;
	
	/**
	 * 토목시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void insertCivilFcltySpecMngDetail(Map<String, Object> vo) throws Exception;
	
	/**
	 * 토목시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void updateCivilFcltySpecMngDetail(Map<String, Object> vo) throws Exception;
	
	/**
	 * 토목시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void deleteCivilFcltySpecMngDetail(Map<String, Object> vo) throws Exception;
	
}
