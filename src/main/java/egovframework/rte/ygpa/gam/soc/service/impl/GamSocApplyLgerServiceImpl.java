/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.soc.service.GamSocApplyLgerService;
import egovframework.rte.ygpa.gam.soc.service.GamSocApplyLgerVO;

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

@Service("gamSocApplyLgerService")
public class GamSocApplyLgerServiceImpl extends AbstractServiceImpl implements GamSocApplyLgerService {
	@Resource(name="gamSocApplyLgerDao")
	private GamSocApplyLgerDAO gamSocApplyLgerDao;
	
	/**
	 * 투자비보전신청대장 리스트를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전신청대장 리스트
	 * @exception
	 */	
	public List<?> selectSocApplyLgerList(GamSocApplyLgerVO searchVO) {
		return gamSocApplyLgerDao.selectSocApplyLgerList(searchVO);
	}

	/**
	 * 투자비보전신청대장 리스트의 총 개수를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */	
	public int selectSocApplyLgerListTotCnt(GamSocApplyLgerVO searchVO) {
		return gamSocApplyLgerDao.selectSocApplyLgerListTotCnt(searchVO);
	}
}
