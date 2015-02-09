/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

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
	List<?> selectFcltyRepairMngList(GamFcltyRepairMngVO vo) throws Exception;
	
	
	/**
	 * 하자보수 내역 총갯수, 금액합계
	 * @param vo
	 * @return GamFcltyRepairMngVO
	 * @throws Exception
	 */
	GamFcltyRepairMngVO selectFcltyRepairMngListTotCnt(GamFcltyRepairMngVO vo) throws Exception;
	
	
	/**
	 * 하자보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	EgovMap selectFcltyRepairMngDetail(GamFcltyRepairMngVO vo) throws Exception;
	
	
	/**
	 * 하자보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectFlawRprObjFcltsFList(GamFcltyRepairMngVO vo) throws Exception;
	
	
	/**
	 * 하자보수 대상시설물 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFlawRprObjFcltsFListTotCnt(GamFcltyRepairMngVO vo) throws Exception;
	
	
	/**
	 * 하자보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectFcltyRepairFileList(GamFcltyRepairMngVO vo) throws Exception;
	
	
	/**
	 * 하자보수내역 입력
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	void insertFcltyRepairMng(Map insertRprData, List insertObjList, List insertFileList) throws Exception;
	
	
	/**
	 * 하자보수내역 수정
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	void updateFcltyRepairMng(Map updateRprData, Map updateObj, List updateFileList) throws Exception;
	
	
	/**
	 * 하자보수내역 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	void deleteFcltyRepairMng(Map<?,?> vo) throws Exception;

	
	
	/**
	 * 하자검사조서인쇄
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	EgovMap selectFcltyRepairCheckReport(GamFcltyRepairMngVO vo) throws Exception;
	
	
	/**
	 * 하자검사관리대장인쇄
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	EgovMap selectFcltyRepairCheckMng(GamFcltyRepairMngVO vo) throws Exception;
	
	
	/**
	 *  계약당 계약당 하자보증 내역 인쇄
	 * @param  String
	 * @return list
	 * @throws Exception
	 */
	List<?> selectFcltyRepairMngListPerCtrt(String vo) throws Exception;
	
	
	/**
	 * 계약당 계약당 하자보증 내역 총갯수
	 * @param String
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyRepairMngListPerCtrtTotalCnt(String vo) throws Exception;

}
