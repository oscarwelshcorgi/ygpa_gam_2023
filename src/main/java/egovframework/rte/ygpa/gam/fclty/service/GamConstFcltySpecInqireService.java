/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author LFIT
 * @since 2014. 12. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 5.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamConstFcltySpecInqireService {

	/**
	 * 시설관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltySpecInqireList(GamConstFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * 시설관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltySpecInqireListTotCnt(GamConstFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * 시설관리 상세화면
	 * @param emplyrId
	 * @return 
	 * @throws Exception
	 */
	EgovMap selectFcltySpecInqireDetail(Map searchVO) throws Exception;

	/**
	 * 시설관리 파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectFcltySpecInqireFileList(GamConstFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * 시설관리 파일 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltySpecInqireFileListTotCnt(GamConstFcltySpecInqireVO searchVO) throws Exception;
	
}
