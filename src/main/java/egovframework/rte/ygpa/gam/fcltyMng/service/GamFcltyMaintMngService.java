/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 25.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltyMaintMngService {
	
	
	/**
	 * 유지보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectFcltyMaintMngList(GamFcltyMaintMngVO vo) throws Exception;
	
	
	/**
	 * 유지보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyMaintMngListTotCnt(GamFcltyMaintMngVO vo) throws Exception;
	
	
	/**
	 * 유지보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	EgovMap selectFcltyMaintMngDetail(GamFcltyMaintMngVO vo) throws Exception;
	
	
	/**
	 * 유지보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectMntnRprObjFcltsFList(GamFcltyMaintMngVO vo) throws Exception;
	
	
	/**
	 * 유지보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectFcltyMaintFileList(GamFcltyMaintMngVO vo) throws Exception;
	
	
	/**
	 * 유지보수내역 입력
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	void insertFcltyMaintMng(Map insertMntnData, List insertObjList, List insertFileList) throws Exception;

	
	
	/**
	 * 유지보수내역 수정
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	void updateFcltyMaintMng(Map updateMntnData, List insertObjList, List insertFileList) throws Exception;
	
	
	/**
	 * 유지보수내역 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	void deleteFcltyMaintMng(Map<?,?> vo) throws Exception;
	
	
	

}
