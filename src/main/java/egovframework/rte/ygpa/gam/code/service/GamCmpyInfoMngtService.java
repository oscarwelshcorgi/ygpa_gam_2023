/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Administrator
 * @since 2014. 3. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 5.		Administrator		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamCmpyInfoMngtService {

	/**
	 * 업체정보관리 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<GamEntrpsInfoFVO> selectCmpyInfoMngtList(GamEntrpsInfoFVO vo) throws Exception;
	
	
	/**
	 * 업체정보관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectCmpyInfoMngtListTotCnt(GamEntrpsInfoFVO vo) throws Exception;
	
	
	/**
	 * 업체관리 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<GamEntrpsChargerFVO> selectCmpyMngtList(GamEntrpsChargerFVO vo) throws Exception;
	
	
	/**
	 * 업체관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectCmpyMngtListTotCnt(GamEntrpsChargerFVO vo) throws Exception;

	
	/**
	 * 업체정보관리 상세
	 * @param vo
	 * @return vo
	 * @throws Exception
	 */
	GamEntrpsInfoFVO selectCmpyInfoMngtDetail(GamEntrpsInfoFVO vo) throws Exception;
	
	
	/**
	 * 업체담당자 정보 상세
	 * @param vo
	 * @return vo
	 * @throws Exception
	 */
	GamEntrpsChargerFVO selectCmpyMngtDetail(GamEntrpsChargerFVO vo) throws Exception;

	
	/**
	 * 업체코드 체크
	 * @param entrpscd
	 * @return int
	 * @throws Exception
	 */
	int checkEntrpscd(String entrpscd) throws Exception;
	
	
	/**
	 * 업체정보 관리 저장
	 * @param vo
	 * @throws Exception
	 */
	void insertCmpyInfoMngt(Map<String, Object> cmpyMngtList) throws Exception;
	
	
	/**
	 * 업체정보 관리 수정
	 * @param vo
	 * @throws Exception
	 */
	void updateCmpyInfoMngt(Map<String, Object> cmpyMngtList) throws Exception;
	
	
	/**
	 * 업체정보 관리 삭제
	 * @param vo
	 * @throws Exception
	 */
	void deleteCmpyInfoMngt(String entrpscd) throws Exception;
}