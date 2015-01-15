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
	List<?> selectCivilFcltySpecMngList(GamCivilFcltySpecMngVO searchVO) throws Exception;
	
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
	EgovMap selectCivilFcltySpecMngDetail(Map<?, ?> searchVO) throws Exception;
	
	/**
	 * 토목시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void insertCivilFcltySpecMngDetail(Map<?, ?> vo) throws Exception;
	
	/**
	 * 토목시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void updateCivilFcltySpecMngDetail(Map<?, ?> vo) throws Exception;
	
	/**
	 * 토목시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void deleteCivilFcltySpecMngDetail(Map<?, ?> vo) throws Exception;
	
	/**
	 * 토목시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	List<?> selectCivilFcltySpecFileList(GamCivilFcltySpecMngVO searchVO) throws Exception;
	
	/**
	 * 토목시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	int selectCivilFcltySpecFileListTotCnt(GamCivilFcltySpecMngVO searchVO) throws Exception;	
	
	
	/**
	 * 토목시설재원관리 첨부파일을 병합저장한다..
	 * @param map
	 * @return list
	 * @throws Exception
	 */			
	public void mergeFcltyFileMngt(Map<String, Object> mergeList) throws Exception;
	
}
