/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentService;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentVO;

/**
 * 
 * @author HNJ
 * @since 2014. 9. 23.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 23.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamSocAgentService")
public class GamSocAgentServiceImpl extends AbstractServiceImpl implements GamSocAgentService {
	
	@Resource(name="gamSocAgentDao")
    private GamSocAgentDao gamSocAgentDao;
	
	/**
	 * 항만공사허가업체정보를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return Map
	 * @exception Exception
	 */
    public GamSocAgentVO selectSocAgentInfo(GamSocAgentVO searchVO) throws Exception {
        return gamSocAgentDao.selectSocAgentInfo(searchVO);
    }
	
	
	/**
	 * 항만공사허가원부 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectSocAgentList(GamSocAgentVO searchVO) throws Exception {
        return gamSocAgentDao.selectSocAgentList(searchVO);
    }

   
    
    /**
	 * 항만공사허가원부 총갯수 및 금액합계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public GamSocAgentVO selectSocAgentInfoSum(GamSocAgentVO searchVO) throws Exception {
		return gamSocAgentDao.selectSocAgentInfoSum(searchVO);
	}

}
