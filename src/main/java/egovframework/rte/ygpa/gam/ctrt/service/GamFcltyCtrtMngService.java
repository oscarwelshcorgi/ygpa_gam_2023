/**
 * 
 */
package egovframework.rte.ygpa.gam.ctrt.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltyCtrtMngService {
	
	/**
	 * 계약정보목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약정보목록
	 * @exception Exception
	 */
	List<?> selectFcltyCtrtMngList(GamFcltyCtrtMngVO searchVO) throws Exception;
	
	/**
	 * 계약정보목록 통계정보를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약정보목록 통계정보 VO
	 * @exception Exception
	 */
	GamFcltyCtrtMngVO selectFcltyCtrtMngTotSum(GamFcltyCtrtMngVO searchVO) throws Exception;
	
	/**
	 * 계약정보를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약정보 VO
	 * @exception Exception
	 */
	GamFcltyCtrtMngVO selectFcltyCtrtInfoDetail(GamFcltyCtrtMngVO searchVO) throws Exception;
	
	/**
	 * 계약정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	void insertFcltyCtrtInfoDetail(Map<?, ?> insertMap) throws Exception;
	
	/**
	 * 계약정보를 수정한다.
	 * @param updateMap - 수정할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	void updateFcltyCtrtInfoDetail(Map<?, ?> updateMap) throws Exception;

	/**
	 * 계약정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	void deleteFcltyCtrtInfoDetail(Map<?, ?> deleteMap) throws Exception;
	
	
	/**
	 * 계약공동도급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급 목록
	 * @exception Exception
	 */
	List<?> selectFcltyCtrtJoinContrList(GamFcltyCtrtMngVO searchVO) throws Exception;
	
	/**
	 * 계약공동도급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급 목록 총개수
	 * @exception Exception
	 */
	int selectFcltyCtrtJoinContrListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception;
	
		
	/**
	 * 계약공동도급정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	void mergeFcltyCtrtJoinContrDetail(Map<String, Object> map) throws Exception;
	
	/**
	 * 계약번호에 해당하는 계약공동도급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	void deleteFcltyCtrtJoinContrList(Map<?, ?> deleteMap) throws Exception;


	/**
	 * 계약하도급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약하도급 목록
	 * @exception Exception
	 */
	List<?> selectFcltyCtrtSubCtrtList(GamFcltyCtrtMngVO searchVO) throws Exception;
	
	/**
	 * 계약하도급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약하도급 목록 총개수
	 * @exception Exception
	 */
	int selectFcltyCtrtSubCtrtListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception;
	
	
	/**
	 * 계약하도급정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	void mergeFcltyCtrtSubCtrtDetail(Map<String, Object> map) throws Exception;
	
	/**
	 * 계약번호에 해당하는 계약하도급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	void deleteFcltyCtrtSubCtrtList(Map<?, ?> deleteMap) throws Exception;

	/**
	 * 계약변경 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약변경 목록
	 * @exception Exception
	 */
	List<?> selectFcltyCtrtChangeList(GamFcltyCtrtMngVO searchVO) throws Exception;
	
	/**
	 * 계약변경 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약변경 목록 총개수
	 * @exception Exception
	 */
	int selectFcltyCtrtChangeListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception;
	
	
	/**
	 * 계약변경정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	void mergeFcltyCtrtChangeDetail(Map<String, Object> map) throws Exception;
	
	/**
	 * 계약번호에 해당하는 계약변경정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	void deleteFcltyCtrtChangeList(Map<?, ?> deleteMap) throws Exception;
	

	/**
	 * 계약대금지급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급 목록
	 * @exception Exception
	 */
	List<?> selectFcltyCtrtMoneyPymntList(GamFcltyCtrtMngVO searchVO) throws Exception;
	
	/**
	 * 계약대금지급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급 목록 총개수
	 * @exception Exception
	 */
	int selectFcltyCtrtMoneyPymntListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception;
	
	
	/**
	 * 계약대금지급정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	void mergeFcltyCtrtMoneyPymntDetail(Map<String, Object> map) throws Exception;
	
	/**
	 * 계약번호에 해당하는 계약대금지급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	void deleteFcltyCtrtMoneyPymntList(Map<?, ?> deleteMap) throws Exception;


	/**
	 * 계약이행이월 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월 목록
	 * @exception Exception
	 */
	List<?> selectFcltyCtrtFulFillCaryFwdList(GamFcltyCtrtMngVO searchVO) throws Exception;
	
	/**
	 * 계약이행이월 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월 목록 총개수
	 * @exception Exception
	 */
	int selectFcltyCtrtFulFillCaryFwdListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception;
	
	
	/**
	 * 계약이행이월정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	void mergeFcltyCtrtFulFillCaryFwdDetail(Map<String, Object> map) throws Exception;
	
	/**
	 * 계약번호에 해당하는 계약이행이월정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	void deleteFcltyCtrtFulFillCaryFwdList(Map<?, ?> deleteMap) throws Exception;	
}
