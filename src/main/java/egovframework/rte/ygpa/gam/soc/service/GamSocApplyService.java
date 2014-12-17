/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 7.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamSocApplyService {
	/**
	 * 면제요청내역관리 데이터를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 면제요청내역관리 VO
	 * @exception
	 */
	EgovMap selectSocApplyDetailInquire(GamSocApplyVO searchVO);
		
	/**
	 * 면제요청내역 리스트를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 면제요청내역 리스트
	 * @exception
	 */
	List<?> selectSocApplyList(GamSocApplyVO searchVO);
	
	/**
	 * 면제요청내역 리스트의 총 개수를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	int selectSocApplyListTotCnt(GamSocApplyVO searchVO);
	
	/**
	 * 면제요청내역관리 데이터를 삽입한다. 
	 * @param insertMap - 삽입할 정보가 담긴 Map
	 * @return 
	 * @exception
	 */
	void insertSocApplyDetail(Map<?, ?> insertMap);
	
	/**
	 * 면제요청내역관리 데이터를 수정한다. 
	 * @param updateVO - 수정할 정보가 담긴 VO
	 * @return 
	 * @exception
	 */	
	void updateSocApplyDetail(Map<?, ?> updateMap);
	
	/**
	 * 면제요청내역관리 데이터를 삭제한다. 
	 * @param deleteVO - 삭제할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	void deleteSocApplyDetail(Map<?, ?> deleteMap);
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물리스트를  조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	List<?> selectSocApplyFacilList(GamSocApplyVO searchVO);

	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물리스트 총갯슈를  조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	int selectSocApplyFacilListTotCnt(GamSocApplyVO searchVO);
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물데이터 유무를  조회한다. 
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return
	 * @exception
	 */	
	int selectSocApplyFacilInfoCnt(Map<?, ?> searchMap);
	
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물데이터를 삽입한다. 
	 * @param insertMap - 삽입할 정보가 담긴 Map
	 * @return
	 * @exception
	 */	
	void insertSocApplyFacilInfo(Map<?, ?> insertMap);
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물데이터전체를 삭제한다. 
	 * @param deleteVO - 삭제할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	void deleteSocApplyFacilList(Map<?, ?> deleteMap);

	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류리스트를  조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	List<?> selectSocApplyFeeList(GamSocApplyVO searchVO);

	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류리스트 총갯슈를  조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	int selectSocApplyFeeListTotCnt(GamSocApplyVO searchVO);
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류데이터 유무를  조회한다. 
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return
	 * @exception
	 */	
	int selectSocApplyFeeInfoCnt(Map<?, ?> searchMap);;
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류데이터를 삽입한다. 
	 * @param insertMap - 삽입할 정보가 담긴 Map
	 * @return
	 * @exception
	 */	
	void insertSocApplyFeeInfo(Map<?, ?> insertMap);
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류데이터전체를 삭제한다. 
	 * @param deleteVO - 삭제할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	void deleteSocApplyFeeList(Map<?, ?> deleteMap);
}
