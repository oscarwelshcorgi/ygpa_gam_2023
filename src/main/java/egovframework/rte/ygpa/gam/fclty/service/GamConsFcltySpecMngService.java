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
 * @since 2014. 11. 4.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 4.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamConsFcltySpecMngService {


	/**
	 * 시설관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectFcltySpecMngList(GamConsFcltySpecMngVO vo) throws Exception;


	/**
	 * 시설관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltySpecMngListTotCnt(GamConsFcltySpecMngVO vo) throws Exception;


	/**
	 * 시설관리 파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectFcltySpecMngFileList(GamConsFcltySpecMngVO vo) throws Exception;


	/**
	 * 시설관리 파일 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltySpecMngFileListTotCnt(GamConsFcltySpecMngVO vo) throws Exception;


	/**
	 * 시설관리 상세화면
	 * @param emplyrId
	 * @return GamConsFcltySpecMngVO
	 * @throws Exception
	 */
	public EgovMap fcltyMngSelectView(Map<?,?> vo) throws Exception;
	
	/**
	 * 시설관리 상세화면(제원)
	 * @param emplyrId
	 * @return GamConsFcltySpecMngVO
	 * @throws Exception
	 */
	public EgovMap fcltySpecMngSelectView(Map<?,?> vo) throws Exception;
	

	/**
	 * 시설재원관리 입력
	 * @param Map, List
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	void insertFcltySpec(Map fcltyManageVO, List insertFileList) throws Exception;

	
	/**
	 * 시설재원관리 수정
	 * @param Map, Map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	void updateFcltySpec(Map fcltyManageVO, Map mergeMap) throws Exception;

	
	/**
	 * 시설재원관리 삭제
	 * @param Map
	 * @return 
	 * @throws Exception
	 */
	void deleteFcltySpec(Map<?,?> vo) throws Exception;
	

	
}