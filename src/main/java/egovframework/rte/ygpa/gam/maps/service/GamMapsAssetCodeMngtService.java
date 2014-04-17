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

public interface GamMapsAssetCodeMngtService {
	/**
	 * 자산 코드 정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List selectAssetCodeInfoList(Map vo) throws Exception;

	EgovMap selectMapsAssetsCodeInfo(Map vo) throws Exception;

	/**
	 * 자산 임대 정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List selectAssetRentInfoList(Map vo) throws Exception;
}
