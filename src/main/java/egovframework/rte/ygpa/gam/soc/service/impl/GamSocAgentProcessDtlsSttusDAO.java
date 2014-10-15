/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentProcessDtlsSttusVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 15.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamSocAgentProcessDtlsSttusDao")
public class GamSocAgentProcessDtlsSttusDAO extends YGPAAbstractDAO {
	/**
	 * 투자비보전처리현황 리스트를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전처리현황 리스트
	 * @exception
	 */
	List selectSocAgentProcessDtlsSttusList(GamSocAgentProcessDtlsSttusVO searchVO) {
		return list("gamSocAgentProcessDtlsSttusDAO.selectSocAgentProcessDtlsSttusList_S", searchVO);
	}
	
	/**
	 * 투자비보전처리현황 리스트의 총 개수를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	int selectSocAgentProcessDtlsSttusListTotCnt(GamSocAgentProcessDtlsSttusVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamSocAgentProcessDtlsSttusDao.selectSocAgentProcessDtlsSttusListTotCnt_S", searchVO);
	}
}
