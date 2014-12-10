/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author 정성현
 * @since 2014. 12. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 9.	정성현		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamInfoTechFcltySpecInqireService {

	/**
	 * 정보통신시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectInfoTechFcltySpecInqireList(GamInfoTechFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * 정보통신시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectInfoTechFcltySpecInqireListTotCnt(GamInfoTechFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * 정보통신시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */
	EgovMap selectInfoTechFcltySpecInqireDetail(Map searchVO) throws Exception;


	/**
	 * 정보통신시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectInfoTechFcltySpecInqireFileList(GamInfoTechFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * 정보통신시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	int selectInfoTechFcltySpecInqireFileListTotCnt(GamInfoTechFcltySpecInqireVO searchVO) throws Exception;



}
