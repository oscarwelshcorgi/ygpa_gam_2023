/**
 *
 */
package egovframework.rte.ygpa.gam.maps.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 31.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamMapsCivilFcltyInqireService {
	/**
	 * 시설 코드 정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	EgovMap selectFcltySpecInfo(Map vo) throws Exception;

	/**
	 * 첨부파일목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectFcltyFileList(Map vo) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	EgovMap selectFcltyMaintInfo(Map searchVO) throws Exception;

}
