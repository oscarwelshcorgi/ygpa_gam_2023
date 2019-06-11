/**
 *
 */
package egovframework.com.ygpa.uat.uia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.ygpa.uat.uia.service.GamUserCfgService;
import egovframework.com.ygpa.uat.uia.service.GamUserCfgVo;

/**
 *
 * @author verstar
 * @since 2019. 6. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2019. 6. 10.		verstar		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamUserCfgService")
public class GamUserCfgServiceImpl implements GamUserCfgService {

	@Autowired
	private GamUserCfgDao gamUserCfgDao;

	/* (non-Javadoc)
	 * @see egovframework.com.ygpa.uat.uia.service.GamUserCfgService#selectUserCfgPk(egovframework.com.ygpa.uat.uia.service.GamUserCfgVo)
	 */
	@Override
	public GamUserCfgVo selectUserCfgPk(GamUserCfgVo vo) {
		return gamUserCfgDao.selectUserCfgPk(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.com.ygpa.uat.uia.service.GamUserCfgService#insertUserCfgPk(egovframework.com.ygpa.uat.uia.service.GamUserCfgVo)
	 */
	@Override
	public void insertUserCfgPk(GamUserCfgVo vo) {
		gamUserCfgDao.insertUserCfgPk(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.com.ygpa.uat.uia.service.GamUserCfgService#updateUserCfgPk(egovframework.com.ygpa.uat.uia.service.GamUserCfgVo)
	 */
	@Override
	public void updateUserCfgPk(GamUserCfgVo vo) {
		gamUserCfgDao.updateUserCfgPk(vo);
	}

}
