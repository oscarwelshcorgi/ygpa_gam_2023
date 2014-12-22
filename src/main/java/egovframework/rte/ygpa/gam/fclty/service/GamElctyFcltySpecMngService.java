/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 19.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 19.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamElctyFcltySpecMngService {
	
	/**
	 * 전기시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	List<?> selectElctyFcltySpecMngList(GamElctyFcltySpecMngVO searchVO) throws Exception;
	
	/**
	 * 전기시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectElctyFcltySpecMngListTotCnt(GamElctyFcltySpecMngVO searchVO) throws Exception;

	/**
	 * 전기시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	EgovMap selectElctyFcltySpecMngDetail(Map<?,?> searchVO) throws Exception;
	
	/**
	 * 전기시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void insertElctyFcltySpecMngDetail(Map<?, ?> vo) throws Exception;
	
	/**
	 * 전기시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void updateElctyFcltySpecMngDetail(Map<?, ?> vo) throws Exception;
	
	/**
	 * 전기시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void deleteElctyFcltySpecMngDetail(Map<?,?> vo) throws Exception;
	
	/**
	 * 전기시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	List<?> selectElctyFcltySpecFileList(GamElctyFcltySpecMngVO searchVO) throws Exception;
	
	/**
	 * 전기시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	int selectElctyFcltySpecFileListTotCnt(GamElctyFcltySpecMngVO searchVO) throws Exception;	
	
	/**
	 * 전기시설재원관리 첨부파일 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void insertElctyFcltySpecFileDetail(Map<?,?> vo) throws Exception;
	
	/**
	 * 전기시설재원관리 첨부파일 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void updateElctyFcltySpecFileDetail(Map<?,?> vo) throws Exception;
	
	/**
	 * 전기시설재원관리 첨부파일 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	void deleteElctyFcltySpecFileDetail(Map<?,?> vo) throws Exception;
	
	/**
	 * 전기시설재원관리 첨부파일 목록을 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	void deleteElctyFcltySpecFileList(Map<?,?> vo) throws Exception;
	
	/**
	 * 전기시설재원관리 첨부파일을 병합저장한다..
	 * @param map
	 * @return list
	 * @throws Exception
	 */			
	public List<?> mergeFcltyFileMngt(Map<String,Object> mergeList) throws Exception;
	
}
