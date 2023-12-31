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
	 * GIS 항만 시설
	 * @param vo
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyGisPrtFcltyCdList(GamFcltyUsageSttusInqireVO vo) throws Exception;

	/**
	 * GIS 자산
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyGisPrtFcltyCdListTotCnt(GamFcltyUsageSttusInqireVO vo) throws Exception;

	/**
	 * 시설물 사용현황 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltyAssetsRentList(GamFcltyUsageSttusInqireVO vo) throws Exception;

	/**
	 * 카운트
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyAssetsRentListTotCnt(GamFcltyUsageSttusInqireVO vo) throws Exception;

	/**
	 * 점검 관리 내역
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectQcMngList(GamFcltyUsageSttusInqireVO vo) throws Exception;
	
	/**
	 * 하자 보수 내역
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFlawList(GamFcltyUsageSttusInqireVO vo) throws Exception;


	/**
	 * 유지 보수 내역
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectMntnRprDtlsList(GamFcltyUsageSttusInqireVO vo) throws Exception;


	/**
	 * 시설물관리그룹명조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	
	EgovMap selectFcltsMngGroupNm(Map searchVO) throws Exception;
	}
