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
	 * 자산현황 통계 정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	EgovMap selectMapsSttusByCodeInfo(Map vo) throws Exception;

	/**
	 * 자산현황 변동 이력 정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List selectMapsHistSttusByCodeInfo(Map vo) throws Exception;

	/**
	 * 자산 임대 정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	Map selectMapsAssetsCodeUseInfo(Map vo) throws Exception;


	List selectAssetRentInfoList(Map vo) throws Exception;

	/**
	 * 자산 사용 현황 정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	EgovMap selectMapsAssetsCodeUseSummary(Map vo) throws Exception;

	/**
	 * 입력된 법정동 코드에 대한 주소를 로딩한다.
	 * @param bjdCode	법정동코드 (10자 이상)
	 * @return 주소
	 * @throws Exception
	 */
	String selectMapsBjdCodeInfo(String bjdCode) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	Object selectMapsAssetsRentInfo(Map searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	Map selectMapsHtldRentInfo(Map searchVO) throws Exception;

}
