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

public interface GamFcltyQcSttusInqireService {
	/**
	 * 점검관리내역 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcMngDtlsList(GamFcltyQcSttusInqireVO searchVO) throws Exception;
	
	/**
	 * 점검관리내역 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectQcMngDtlsListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception;

	/**
	 * 점검관리목록 인쇄 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcMngDtlsReportI(GamFcltyQcSttusInqireVO searchVO) throws Exception;

	/**
	 * 점검구분 이름 조회(인쇄화면에 사용)
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	String selectQcSeNm(GamFcltyQcSttusInqireVO searchVO) throws Exception;
	
	/**
	 * 점검관리내역 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */		
	EgovMap selectQcMngDtlsDetail(Map<?, ?> searchVO) throws Exception;
	
	/**
	 * 점검관리대상시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcMngObjFcltsList(GamFcltyQcSttusInqireVO searchVO) throws Exception;
	
	/**
	 * 점검관리대상시설물 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectQcMngObjFcltsListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception;
	
	/**
	 * 점검관리첨부파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcMngAtchFileList(GamFcltyQcSttusInqireVO searchVO) throws Exception;
	
	/**
	 * 점검관리첨부파일 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectQcMngAtchFileListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception;
		
	/**
	 * 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcMngResultItemList(GamFcltyQcSttusInqireVO searchVO) throws Exception;
	
	/**
	 * 점검관리결과항목 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectQcMngResultItemListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception;
		
}
