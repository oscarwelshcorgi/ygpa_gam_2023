package egovframework.rte.ygpa.gam.ctrt.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author HNJ
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltyCtrtSttusInqireService {

	/**
	 * @name selectFcltyCtrtSttusInqireList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtSttusInqireList(GamFcltyCtrtSttusInqireVO searchVO) throws Exception;

	/**
	 * @name selectFcltyCtrtSttusInqireListSum
	 * @param searchVO
	 * @return GamFcltyCtrtSttusInqireVO
	 * @throws Exception
	 */
	GamFcltyCtrtSttusInqireVO selectFcltyCtrtSttusInqireListSum(GamFcltyCtrtSttusInqireVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	EgovMap selectEntrpsInfo(Map searchVO) throws Exception;

}
