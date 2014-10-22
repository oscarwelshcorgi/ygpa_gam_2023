/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocApplyEntrpsInfoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocApplyEntrpsInfoVO;

/**
 * @Class Name : GamPopupScoEntrpsInfoServiceImpl.java
 * @Description : 투자비보전신청업체정보 Business Implement class
 * @Modification Information
 * 
 * @author HNJ
 * @since 2014. 10. 21.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 21.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamPopupSocApplyEntrpsInfoService")

public class GamPopupSocApplyEntrpsInfoServiceImpl extends AbstractServiceImpl implements GamPopupSocApplyEntrpsInfoService {
	
	@Resource(name="gamPopupSocApplyEntrpsInfoDao")
    private GamPopupSocApplyEntrpsInfoDao gamPopupSocApplyEntrpsInfoDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.popup.service.GamPopupSocApplyEntrpsInfoService#selectSocApplyEntrpsInfoList(egovframework.rte.ygpa.gam.popup.service.GamPopupSocApplyEntrpsInfoVO)
	 */
	@Override
	public List selectSocApplyEntrpsInfoList(GamPopupSocApplyEntrpsInfoVO searchVO) throws Exception {
		return gamPopupSocApplyEntrpsInfoDao.selectSocApplyEntrpsInfoList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.popup.service.GamPopupSocApplyEntrpsInfoService#selectSocApplyEntrpsInfoListTotCnt(egovframework.rte.ygpa.gam.popup.service.GamPopupSocApplyEntrpsInfoVO)
	 */
	@Override
	public int selectSocApplyEntrpsInfoListTotCnt(GamPopupSocApplyEntrpsInfoVO searchVO) throws Exception {
		return gamPopupSocApplyEntrpsInfoDao.selectSocApplyEntrpsInfoListTotCnt(searchVO);
	}

}
