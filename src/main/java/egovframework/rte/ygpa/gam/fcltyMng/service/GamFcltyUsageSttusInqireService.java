/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeGubunMngVo;
/**
 *
 * @author jckim
 * @since 2014. 12. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 9.		jckim		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltyUsageSttusInqireService {
	/**
	 * 시설물 사용현황 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltyUsageSttusInqireList(GamFcltyUsageSttusInqireVO vo) throws Exception;

	/**
	 * 카운트
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyUsageSttusInqireListTotCnt(GamFcltyUsageSttusInqireVO vo) throws Exception;

	/**
	 * 카운트
	 * @param vo
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyGisAssetsCdFList(GamFcltyUsageSttusInqireVO vo) throws Exception;

	/**
	 * GIS 자산
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyGisAssetsCdFListTotCnt(GamFcltyUsageSttusInqireVO vo) throws Exception;




}
