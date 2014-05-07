package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFacilInfoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFacilInfoVO;

/**
 * @Class Name : GamPopupFacilInfoServiceImpl.java
 * @Description : 선석정보 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamPopupFacilInfoService")

public class GamPopupFacilInfoServiceImpl extends AbstractServiceImpl implements GamPopupFacilInfoService {

	@Resource(name="gamPopupFacilInfoDao")
    private GamPopupFacilInfoDao gamPopupFacilInfoDao;

	/**
	 * 선석정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectFacilInfoList(GamPopupFacilInfoVO searchVO) throws Exception {
        return gamPopupFacilInfoDao.selectFacilInfoList(searchVO);
    }

    /**
	 * 선석정보 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectFacilInfoListTotCnt(GamPopupFacilInfoVO searchVO) throws Exception {
		return gamPopupFacilInfoDao.selectFacilInfoListTotCnt(searchVO);
	}

}
