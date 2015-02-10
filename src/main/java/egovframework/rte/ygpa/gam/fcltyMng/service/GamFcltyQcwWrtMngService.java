/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 24.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltyQcwWrtMngService {
	/**
	 * 점검관리내역 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcMngDtlsList(GamFcltyQcwWrtMngVO searchVO) throws Exception;
	
	/**
	 * 점검관리내역 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectQcMngDtlsListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception;

	/**
	 * 점검관리내역 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */		
	EgovMap selectQcMngDtlsDetail(GamFcltyQcwWrtMngVO searchVO) throws Exception;
	
	/**
	 * 점검관리내역 데이터 삽입
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void insertQcMngDtls(Map<String, String> detailForm, List<HashMap<String, String>> qcObjList,  List<HashMap<String, String>> qcResultList, List<HashMap<String, String>> atchFileList) throws Exception;
	
	/**
	 * 점검관리내역 데이터 수정
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void updateQcMngDtls(Map<String, String> detailForm, List<HashMap<String, String>> qcObjList,  List<HashMap<String, String>> qcResultList, List<HashMap<String, String>> atchFileList) throws Exception;
	
	/**
	 * 점검관리내역 데이터 삭제
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void deleteQcMngDtls(Map<?, ?> vo) throws Exception;
	
	/**
	 * 점검관리첨부파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcMngAtchFileList(GamFcltyQcwWrtMngVO searchVO) throws Exception;
	
	/**
	 * 점검관리대상시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcMngObjFcltsList(GamFcltyQcwWrtMngVO searchVO) throws Exception;
		
	/**
	 * 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception;

	/**
	 * 기계 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectMechQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception;
	
	/**
	 * 건축 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectArchQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception;

	/**
	 * 출력물용 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectPrintQcMngResultItemList(GamFcltyQcPrintVO searchVO) throws Exception;
}
