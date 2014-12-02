/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 01.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 01.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltyRepairMngService {
	
	
	/**
	 * 하자보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltyRepairMngList(GamFcltyRepairMngVO vo) throws Exception;
	
	
	/**
	 * 하자보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyRepairMngListTotCnt(GamFcltyRepairMngVO vo) throws Exception;
	
	
	/**
	 * 하자보수 검사자 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFlawExamUsrFList(GamFcltyRepairMngVO vo) throws Exception;
	
	
	/**
	 * 하자보수 검사자 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFlawExamUsrFListTotCnt(GamFcltyRepairMngVO vo) throws Exception;
	
	
	/**
	 * 하자보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltyRepairFileList(GamFcltyRepairMngVO vo) throws Exception;
	
	
	/**
	 * 하자보수 첨부파일 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyRepairFileListTotCnt(GamFcltyRepairMngVO vo) throws Exception;
	
	
	/**
	 * 하자보수 순번
	 * @param map
	 * @return String
	 * @throws Exception
	 */
	int selectNextMntnRprSeq(Map<String, Object> vo) throws Exception;
	
	
	/**
	 * 하자보수내역 입력
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	void insertFcltyRepairMng(Map<String, Object> vo) throws Exception;
	
	
	/**
	 * 하자보수내역 수정
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	void updateFcltyRepairMng(Map<String, Object> vo) throws Exception;
	
	
	/**
	 * 하자보수내역 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	void deleteFcltyRepairMng(Map<String, Object> vo) throws Exception;
	
	
	/**
	 * 하자보수내역 하위 검사자 전체 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	void deleteFlawExamUsrF(Map<String, Object> vo) throws Exception;
	
	
	/**
	 * 하자보수내역 하위 첨부파일 전체 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	void deleteFcltyRepairFile(Map<String, Object> vo) throws Exception;
	
	
	
	/**
	 * 하자보수 검사자 데이타 적용
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	List mergeFlawExamUsrF(Map<String, Object> mergeList) throws Exception;
	
	
	
	/**
	 * 하자보수 첨부파일 적용
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	List mergeFcltyRepairFile(Map<String, Object> mergeList) throws Exception;

}
