package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadIncidentMngVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupRoadMngGroupService;

/**
 * @Class Name : GamPopupEntrpsInfoServiceImpl.java
 * @Description : 업체정보 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamPopupRoadMngGroupService")

public class GamPopupRoadMngGroupServiceImpl extends AbstractServiceImpl implements GamPopupRoadMngGroupService {

	@Resource(name="gamPopupRoadMngGroupDao")
    private GamPopupRoadMngGroupDao gamPopupRoadMngGroupDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.popup.service.GamPopupRoadMngGroupService#selectRoadMngGroupList()
	 */
	@Override
	public List selectRoadMngGroupList() throws Exception {
		// TODO Auto-generated method stub
		return gamPopupRoadMngGroupDao.selectRoadMngGroupList();
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.popup.service.GamPopupRoadMngGroupService#selectRoadNoList()
	 */
	@Override
	public List selectRoadNoList(GamRoadIncidentMngVO gamRoadIncidentMngVO) throws Exception {
		// TODO Auto-generated method stub
		return gamPopupRoadMngGroupDao.selectRoadNoList(gamRoadIncidentMngVO);
	}

	

}
