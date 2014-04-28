/**
 *
 */
package egovframework.rte.ygpa.gam.main.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.main.service.GamMainMenuLoadService;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 4. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 4. 25.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamMainMenuLoadService")
public class GamMainMenuLoadServiceImpl extends AbstractServiceImpl implements
		GamMainMenuLoadService {

	@Resource(name="gamMainMenuLoadDAO")
	private GamMainMenuLoadDAO gamMainMenuLoadDAO;
	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.main.service.GamMainMenuLoadService#selectMenuList(java.util.Map)
	 */
	@Override
	public List selectMenuList(String userId) throws Exception {
		// TODO Auto-generated method stub
		return gamMainMenuLoadDAO.selectMenuList(userId);
	}

}
