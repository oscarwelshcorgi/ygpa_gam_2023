package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocAgentFInfoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocAgentFInfoVO;


/**
 * @Class Name : GamPopupScoAgentFInfoServiceImpl.java
 * @Description : 허가원부정보 Implement class
 * @Modification Information
 * 
 * @author HNJ
 * @since 2014. 9. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 25.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamPopupSocAgentFInfoService")
public class GamPopupSocAgentFInfoServiceImpl extends AbstractServiceImpl implements GamPopupSocAgentFInfoService {

	@Resource(name="gamPopupSocAgentFInfoDao")
    private GamPopupSocAgentFInfoDao gamPopupSocAgentFInfoDao;
	
	@Override
	public List selectSocAgentFInfoList(GamPopupSocAgentFInfoVO searchVO)
			throws Exception {
		return gamPopupSocAgentFInfoDao.selectSocAgentFInfoList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.popup.service.GamPopupSocAgentFInfoService#selectSocAgentFInfoListTotCnt(egovframework.rte.ygpa.gam.popup.service.GamPopupSocAgentFInfoVO)
	 */
	@Override
	public int selectSocAgentFInfoListTotCnt(GamPopupSocAgentFInfoVO searchVO)
			throws Exception {
		return gamPopupSocAgentFInfoDao.selectSocAgentFInfoListTotCnt(searchVO);
	}



}
