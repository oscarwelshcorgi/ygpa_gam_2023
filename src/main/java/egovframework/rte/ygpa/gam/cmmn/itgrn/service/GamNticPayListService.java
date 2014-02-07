/**
 * 
 */
package egovframework.rte.ygpa.gam.cmmn.itgrn.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;


/**
 * 
 * @author kok
 * @since 2014. 2. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 7.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamNticPayListService {

	/**
	 * 납부현황목록조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<ComDefaultVO> selectNticPayList(ComDefaultVO vo) throws Exception;
	
	
	/**
	 * 납부현황목록조회 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectNticPayListTotCnt(ComDefaultVO vo) throws Exception;
	
	
	/** 
	 * 시납부현황목록조회 상세화면
	 * @param emplyrId
	 * @return GamNticPayListVO
	 * @throws Exception
	 */
	public GamNticPayListVO gamNticPayListSelectView(GamNticPayListVO nticPayListVO) throws Exception;
}