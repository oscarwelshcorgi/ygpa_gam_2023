/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocEntrpsInfoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocEntrpsInfoVO;

/**
 * @Class Name : GamPopupScoEntrpsInfoServiceImpl.java
 * @Description : 업체정보 Business Implement class
 * @Modification Information
 * 
 * @author HNJ
 * @since 2014. 9. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 24.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamPopupSocEntrpsInfoService")

public class GamPopupSocEntrpsInfoServiceImpl extends AbstractServiceImpl implements GamPopupSocEntrpsInfoService {
	
	@Resource(name="gamPopupSocEntrpsInfoDao")
    private GamPopupSocEntrpsInfoDao gamPopupSocEntrpsInfoDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.popup.service.GamPopupSocEntrpsInfoService#selectSocEntrpsInfoList(egovframework.rte.ygpa.gam.popup.service.GamPopupSocEntrpsInfoVO)
	 */
	@Override
	public List selectSocEntrpsInfoList(GamPopupSocEntrpsInfoVO searchVO) throws Exception {
		return gamPopupSocEntrpsInfoDao.selectSocEntrpsInfoList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.popup.service.GamPopupSocEntrpsInfoService#selectSocEntrpsInfoListTotCnt(egovframework.rte.ygpa.gam.popup.service.GamPopupSocEntrpsInfoVO)
	 */
	@Override
	public int selectSocEntrpsInfoListTotCnt(GamPopupSocEntrpsInfoVO searchVO) throws Exception {
		return gamPopupSocEntrpsInfoDao.selectSocEntrpsInfoListTotCnt(searchVO);
	}

}
