/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author 김종민
 * @since 2014. 12. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 15.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltyQcHistInqireService {
	/**
	 * 점검이력 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcHistDtlsList(GamFcltyQcHistInqireVO searchVO) throws Exception;
	
	/**
	 * 점검이력 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectQcHistDtlsListTotCnt(GamFcltyQcHistInqireVO searchVO) throws Exception;
	
	/**
	 * 점검이력 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */		
	EgovMap selectQcHistDtlsDetail(Map<?, ?> searchVO) throws Exception;
}
