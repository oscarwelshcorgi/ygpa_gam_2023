/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 24.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamSocExmpMngtService {
	/**
	 * 투자비보전내역관리 데이터를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	GamSocExmpMngtVO selectSocExmpMngtDetailInquire(GamSocExmpMngtVO searchVO);

	/**
	 * 투자비보전내역관리 데이터의 총 개수를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	int selectSocExmpMngtDetailTotCnt(GamSocExmpMngtVO searchVO);
	
	/**
	 * 투자비보전내역관리 데이터에 새로운 soc 일련번호를 가져온다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 새로운 soc 일련번호
	 * @exception
	 */
	String selectSocExmpMngtGetNextSocNo(GamSocExmpMngtVO searchVO);
	
	/**
	 * 투자비보전내역관리 데이터를 삽입한다. 
	 * @param searchVO - 삽입할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	void insertSocExmpMngtDetail(GamSocExmpMngtVO insertVO);
	
	/**
	 * 투자비보전내역관리 데이터를 수정한다. 
	 * @param searchVO - 수정할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	void updateSocExmpMngtDetail(GamSocExmpMngtVO updateVO);
	
	/**
	 * 투자비보전내역관리 데이터를 삭제한다. 
	 * @param searchVO - 삭제할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	void deleteSocExmpMngtDetail(GamSocExmpMngtVO deleteVO);
}
