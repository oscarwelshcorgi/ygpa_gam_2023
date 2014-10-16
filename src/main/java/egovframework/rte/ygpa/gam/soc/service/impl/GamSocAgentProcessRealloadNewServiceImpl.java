/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentProcessRealloadNewService;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentProcessRealloadNewVO;

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

@Service("gamSocAgentProcessRealloadNewService")
public class GamSocAgentProcessRealloadNewServiceImpl extends AbstractServiceImpl implements GamSocAgentProcessRealloadNewService {
	@Resource(name="gamSocAgentProcessRealloadNewDao")
	GamSocAgentProcessRealloadNewDAO gamSocAgentProcessRealloadNewDao;
	/**
	 * 업체별투자비보전상계내역 리스트를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별투자비보전상계내역 리스트
	 * @exception
	 */
	public List selectSocAgentProcessRealloadNewList(GamSocAgentProcessRealloadNewVO searchVO) {
		return gamSocAgentProcessRealloadNewDao.selectSocAgentProcessRealloadNewList(searchVO);
	}

	/**
	 * 업체별투자비보전상계내역 리스트의 총 개수와 합계금액을 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return VO - 조회한 정보가 담긴 VO
	 * @exception
	 */	
	public GamSocAgentProcessRealloadNewVO selectSocAgentProcessRealloadNewListTotCnt(GamSocAgentProcessRealloadNewVO searchVO) {
		return gamSocAgentProcessRealloadNewDao.selectSocAgentProcessRealloadNewListTotCnt(searchVO);
	}
}
