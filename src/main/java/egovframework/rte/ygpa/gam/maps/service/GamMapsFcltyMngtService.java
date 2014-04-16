/**
 *
 */
package egovframework.rte.ygpa.gam.maps.service;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;


/**
 *
 * @author kok
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamMapsFcltyMngtService {

	/**
	 * 시설 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectMapsFcltyMngtList(Map vo) throws Exception;

	/**
	 * 시설 정보 상세 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	EgovMap selectMapsFcltyMngtInfo(Map vo) throws Exception;
}