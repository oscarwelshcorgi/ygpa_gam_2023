/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocAgentFInfoVO;

/**
 * @Class Name : GamPopupSocAgentFInfoDao.java
 * @Description : 허가원부정보 DAO Class
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

@Repository("gamPopupSocAgentFInfoDao")
public class GamPopupSocAgentFInfoDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectSocAgentFInfoList(GamPopupSocAgentFInfoVO searchVO) throws Exception {
		return list("gamPopupSocAgentFInfoDao.selectSocAgentFInfoList_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public int selectSocAgentFInfoListTotCnt(GamPopupSocAgentFInfoVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamPopupSocAgentFInfoDao.selectSocAgentFInfoListTotCnt_S", searchVO);
	}

}
