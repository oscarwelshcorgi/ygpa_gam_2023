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
 * @since 2014. 12. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 9.		김영길		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamMechFcltySpecInqireService {

	/**
	 * 기계시설제원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectMechFcltySpecInqireList(GamMechFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * 기계시설제원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	GamMechFcltySpecInqireVO selectMechFcltySpecInqireListTotCnt(GamMechFcltySpecInqireVO searchVO)throws Exception;

	/**
	 * 기계시설제원관리 데이터 조회
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectMechFcltySpecInqireDetail(Map searchVO) throws Exception;

	/**
	 * 기계시설제원관리 첨부파일 목록
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List selectMechFcltySpecInqireFileList(GamMechFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * 기계시설제원관리 첨부파일 목록 총수
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	int selectMechFcltySpecInqireFileListTotCnt(GamMechFcltySpecInqireVO searchVO) throws Exception;

	
	
}
