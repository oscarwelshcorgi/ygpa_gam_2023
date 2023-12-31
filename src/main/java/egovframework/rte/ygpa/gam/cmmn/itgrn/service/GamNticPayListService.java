/**
 * 
 */
package egovframework.rte.ygpa.gam.cmmn.itgrn.service;

import java.util.List;



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
	 * 세입목록조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectNticPayList(GamNticPayListVO vo) throws Exception;
	
	
	/**
	 * 세입목록조회 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectNticPayListTotCnt(GamNticPayListVO vo) throws Exception;
	
	
	/**
	 * 자료수와 고지금액합계,수납금액(수납구분이 2나 3)합계,미수납금액(수납구분이 2나 3이 아닌것)합계
	 * @param vo
	 * @return vo
	 * @throws Exception
	 */
	GamNticPayListVO selectNticPayListSum(GamNticPayListVO vo) throws Exception;
	
	
	/**
	 * 연체세입목록조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectDelayNticPayList(GamNticPayListVO vo) throws Exception;
	
	
	/**
	 * 연체세입목록조회 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectDelayNticPayListTotCnt(GamNticPayListVO vo) throws Exception;
	
	
	/**
	 * 자료수와 미납고지금액합계
	 * @param vo
	 * @return vo
	 * @throws Exception
	 */
	GamNticPayListVO selectDelayNticPayListSum(GamNticPayListVO vo) throws Exception;
}