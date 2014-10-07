/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import java.util.List;

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
	GamSocApplyVO selectSocApplyDetailInquire(GamSocApplyVO searchVO);
	
	/**
	 * 면제요청내역관리 데이터의 총 개수를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	int selectSocApplyDetailTotCnt(GamSocApplyVO searchVO);
	
	/**
	 * 면제요청내역 리스트를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 면제요청내역 리스트
	 * @exception
	 */
	List selectSocApplyList(GamSocApplyVO searchVO);
	
	/**
	 * 면제요청내역 리스트의 총 개수를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	int selectSocApplyListTotCnt(GamSocApplyVO searchVO);
	
	/**
	 * 면제요청내역관리 데이터를 삽입한다. 
	 * @param insertVO - 삽입할 정보가 담긴 VO
	 * @return 
	 * @exception
	 */
	void insertSocApplyDetail(GamSocApplyVO insertVO);
	
	/**
	 * 면제요청내역관리 데이터를 수정한다. 
	 * @param updateVO - 수정할 정보가 담긴 VO
	 * @return 
	 * @exception
	 */	
	void updateSocApplyDetail(GamSocApplyVO updateVO);
	
	/**
	 * 면제요청내역관리 데이터를 삭제한다. 
	 * @param deleteVO - 삭제할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	void deleteSocApplyDetail(GamSocApplyVO deleteVO);
}
