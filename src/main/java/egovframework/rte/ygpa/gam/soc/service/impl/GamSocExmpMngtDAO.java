/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.soc.service.GamSocExmpMngtVO;

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

@Repository("gamSocExmpMngtDAO")
public class GamSocExmpMngtDAO extends YGPAAbstractDAO {
	
	/**
	 * 투자비보전내역관리 데이터를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	GamSocExmpMngtVO selectSocExmpMngtDetailInquire(GamSocExmpMngtVO searchVO) {
		return (GamSocExmpMngtVO) selectByPk("gamSocExmpMngtDAO.selectSocExmpMngtDetail_D", searchVO);
	}

	/**
	 * 투자비보전내역관리 데이터를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	int selectSocExmpMngtDetailTotCnt(GamSocExmpMngtVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamSocExmpMngtDAO.selectSocExmpMngtDetailTotCnt_S", searchVO);
	}

	/**
	 * 투자비보전내역관리 데이터에 새로운 soc 일련번호를 가져온다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 새로운 soc 일련번호
	 * @exception
	 */
	String selectSocExmpMngtGetNextSocNo(GamSocExmpMngtVO searchVO) {
		String result = (String)getSqlMapClientTemplate().queryForObject("gamSocExmpMngtDAO.selectSocExmpMngtGetNextSocNo", searchVO);
		if(result == null) {
			result = "1";
		}
		if(result.length() < 6) {
			for(int i=0; i < 6-result.length(); i++) {
				result = "0" + result;
			}
		}
		return result;
	}
	
	/**
	 * 투자비보전내역관리 데이터를 삽입한다. 
	 * @param insertVO - 삽입할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	public void insertSocExmpMngtDetail(GamSocExmpMngtVO insertVO) {
		insert("gamSocExmpMngtDAO.insertSocExmpMngtDetail", insertVO);
	}
	
	/**
	 * 투자비보전내역관리 데이터를 수정한다. 
	 * @param searchVO - 수정할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	public void updateSocExmpMngtDetail(GamSocExmpMngtVO updateVO) {
		update("gamSocExmpMngtDAO.updateSocExmpMngtDetail", updateVO);
	}

	/**
	 * 투자비보전내역관리 데이터를 삭제한다. 
	 * @param searchVO - 삭제할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	public void deleteSocExmpMngtDetail(GamSocExmpMngtVO deleteVO) {
		delete("gamSocExmpMngtDAO.deleteSocExmpMngtDetail", deleteVO);	
	}
}
