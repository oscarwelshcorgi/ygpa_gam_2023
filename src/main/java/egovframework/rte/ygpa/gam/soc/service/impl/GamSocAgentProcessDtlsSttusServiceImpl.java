/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentProcessDtlsSttusService;
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

@Service("gamSocAgentProcessDtlsSttusService")
public class GamSocAgentProcessDtlsSttusServiceImpl extends AbstractServiceImpl implements GamSocAgentProcessDtlsSttusService {
	@Resource(name="gamSocAgentProcessDtlsSttusDao")
	private GamSocAgentProcessDtlsSttusDAO gamSocAgentProcessDtlsSttusDao;
	
	/**
	 * 투자비보전처리현황 리스트를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전처리현황 리스트
	 * @exception
	 */	
	public List selectSocAgentProcessDtlsSttusList(GamSocAgentProcessDtlsSttusVO searchVO) {
		return gamSocAgentProcessDtlsSttusDao.selectSocAgentProcessDtlsSttusList(searchVO);
	}

	/**
	 * 투자비보전처리현황 리스트의 총계 데이터를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총계데이터VO
	 * @exception
	 */	
	public GamSocAgentProcessDtlsSttusVO selectSocAgentProcessDtlsSttusListTotSum(GamSocAgentProcessDtlsSttusVO searchVO) {
		return gamSocAgentProcessDtlsSttusDao.selectSocAgentProcessDtlsSttusListTotSum(searchVO);
	}
}

