package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadIncidentMngVO;

/**
 * @Class Name : GamPopupEntrpsInfoDao.java
 * @Description : 업체정보 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPopupRoadMngGroupDao")
public class GamPopupRoadMngGroupDao extends YGPAAbstractDAO {

	/**
	 * @return
	 */
	public List selectRoadMngGroupList() {
		// TODO Auto-generated method stub
		return list("gamPopupRoadMngGroupDao.selectRoadMngGroupList", "");
	}

	/**
	 * @return
	 */
	public List selectRoadNoList(GamRoadIncidentMngVO gamRoadIncidentMngVO) {
		// TODO Auto-generated method stub
		return list("gamPopupRoadMngGroupDao.selectRoadNoList", gamRoadIncidentMngVO);
	}
	

    
}
