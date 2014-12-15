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
 * @since 2014. 11. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 17.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamMechFcltySpecMngService {
	
	/**
	 * 기계시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	List<?> selectMechFcltySpecMngList(GamMechFcltySpecMngVO searchVO) throws Exception;
	
	/**
	 * 기계시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectMechFcltySpecMngListTotCnt(GamMechFcltySpecMngVO searchVO) throws Exception;

	/**
	 * 기계시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	EgovMap selectMechFcltySpecMngDetail(Map<?, ?> searchVO) throws Exception;
	
	/**
	 * 기계시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void insertMechFcltySpecMngDetail(Map<?, ?> vo) throws Exception;
	
	/**
	 * 기계시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void updateMechFcltySpecMngDetail(Map<?, ?> vo) throws Exception;
	
	/**
	 * 기계시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void deleteMechFcltySpecMngDetail(Map<?, ?> vo) throws Exception;
	
	/**
	 * 기계시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	List<?> selectMechFcltySpecFileList(GamMechFcltySpecMngVO searchVO) throws Exception;
	
	/**
	 * 기계시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	int selectMechFcltySpecFileListTotCnt(GamMechFcltySpecMngVO searchVO) throws Exception;	
	
	/**
	 * 기계시설재원관리 첨부파일 목록을 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	void deleteMechFcltySpecFileList(Map<?, ?> vo) throws Exception;
	
	/**
	 * 기계시설재원관리 첨부파일을 병합저장한다..
	 * @param map
	 * @return list
	 * @throws Exception
	 */			
	public void mergeFcltyFileMngt(Map<String, Object> mergeList) throws Exception;
	
}
