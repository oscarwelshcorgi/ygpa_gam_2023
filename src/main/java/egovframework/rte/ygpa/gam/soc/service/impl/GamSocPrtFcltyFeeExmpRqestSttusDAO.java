/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.soc.service.GamSocPrtFcltyFeeExmpRqestSttusVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 16.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 16.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamSocPrtFcltyFeeExmpRqestSttusDao")
public class GamSocPrtFcltyFeeExmpRqestSttusDAO extends YGPAAbstractDAO {
	/**
	 * 투자비보전신청대장 리스트를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 면제요청내역 리스트
	 * @exception
	 */
	List selectSocPrtFcltyFeeExmpRqestSttusList(GamSocPrtFcltyFeeExmpRqestSttusVO searchVO) {
		return list("gamSocPrtFcltyFeeExmpRqestSttusDAO.selectSocPrtFcltyFeeExmpRqestSttusList_S", searchVO);
	}
	
	/**
	 * 투자비보전신청대장 리스트의 총 개수를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	int selectSocPrtFcltyFeeExmpRqestSttusListTotCnt(GamSocPrtFcltyFeeExmpRqestSttusVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamSocPrtFcltyFeeExmpRqestSttusDao.selectSocPrtFcltyFeeExmpRqestSttusListTotCnt_S", searchVO);
	}
}
