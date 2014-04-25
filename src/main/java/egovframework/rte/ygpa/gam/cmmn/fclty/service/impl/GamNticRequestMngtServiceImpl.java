/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 4. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 4. 24.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamNticRequestMngtServiceImpl")
public class GamNticRequestMngtServiceImpl extends AbstractServiceImpl implements
		GamNticRequestMngtService {

    @Resource(name="gamNticRequestMngtDAO")
    private GamNticRequestMngtDAO gamNticRequestMngtDAO;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#sendNticRequest(java.util.Map)
	 */
	@Override
	public void sendNticRequest(Map<String, Object> vo) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#cancelNticRequest(java.util.Map)
	 */
	@Override
	public void cancelNticRequest(Map<String, Object> vo) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#sendUnpaidRequest(java.util.Map)
	 */
	@Override
	public void sendUnpaidRequest(Map<String, Object> vo) throws Exception {
		// TODO Auto-generated method stub

	}


}
