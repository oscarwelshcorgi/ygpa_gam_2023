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
	List<?> selectQcSttusDtlsList(GamFcltyQcSttusInqireVO searchVO) throws Exception;
	
	/**
	 * 점검관리내역 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectQcSttusDtlsListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception;
	
	/**
	 * 점검관리내역 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */		
	EgovMap selectQcSttusDtlsDetail(Map<?, ?> searchVO) throws Exception;
	
	/**
	 * 점검관리대상시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcSttusObjFcltsList(GamFcltyQcSttusInqireVO searchVO) throws Exception;
	
	/**
	 * 점검관리대상시설물 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectQcSttusObjFcltsListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception;
		
	/**
	 * 점검관리첨부파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcSttusAtchFileList(GamFcltyQcSttusInqireVO searchVO) throws Exception;
	
	/**
	 * 점검관리첨부파일 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectQcSttusAtchFileListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception;
		
	/**
	 * 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	List<?> selectQcSttusResultItemList(GamFcltyQcSttusInqireVO searchVO) throws Exception;
	
	/**
	 * 점검관리결과항목 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	int selectQcSttusResultItemListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception;
		
}
