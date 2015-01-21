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
	 * 점검관리목록 인쇄 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcMngDtlsReportI(GamFcltyQcwWrtMngVO searchVO) throws Exception;

	/**
	 * 점검구분 이름 조회(인쇄화면에 사용)
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	String selectQcSeNm(GamFcltyQcwWrtMngVO searchVO) throws Exception;
	
	/**
	 * 점검관리내역 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */		
	EgovMap selectQcMngDtlsDetail(Map<?, ?> searchVO) throws Exception;
	
	/**
	 * 점검관리내역 데이터 삽입
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void insertQcMngDtls(Map<String, Object> vo) throws Exception;
	
	/**
	 * 점검관리내역 데이터 수정
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void updateQcMngDtls(Map<?, ?> vo) throws Exception;
	
	/**
	 * 점검관리내역 데이터 삭제
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	void deleteQcMngDtls(Map<?, ?> vo) throws Exception;
		
	/**
	 * 점검관리대상시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcMngObjFcltsList(GamFcltyQcwWrtMngVO searchVO) throws Exception;
	
	/**
	 * 점검관리대상시설물 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectQcMngObjFcltsListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception;
	
	/**
	 * 점검관리대상시설물 데이터 병합 저장
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	void mergeQcMngObjFclts(Map<String, Object> mergeMap) throws Exception;
	
	/**
	 * 점검관리첨부파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcMngAtchFileList(GamFcltyQcwWrtMngVO searchVO) throws Exception;
	
	/**
	 * 점검관리첨부파일 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectQcMngAtchFileListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception;
		
	/**
	 * 점검관리첨부파일 데이터 병합 저장
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	void mergeQcMngAtchFile(Map<String, Object> mergeMap) throws Exception;
	
	/**
	 * 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception;
	
	/**
	 * 점검관리결과항목 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectQcMngResultItemListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception;
		
	/**
	 * 점검관리결과항목 데이터 병합 저장
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	void mergeQcMngResultItem(Map<String, Object> mergeMap) throws Exception;
	
}
