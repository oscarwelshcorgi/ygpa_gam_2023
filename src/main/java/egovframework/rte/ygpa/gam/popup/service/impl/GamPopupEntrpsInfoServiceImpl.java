package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupEntrpsInfoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupEntrpsInfoVO;

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
@Service("gamPopupEntrpsInfoService")

public class GamPopupEntrpsInfoServiceImpl extends AbstractServiceImpl implements GamPopupEntrpsInfoService {

	@Resource(name="gamPopupEntrpsInfoDao")
    private GamPopupEntrpsInfoDao gamPopupEntrpsInfoDao;

	/**
	 * 업체정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectEntrpsInfoList(GamPopupEntrpsInfoVO searchVO) throws Exception {
        return gamPopupEntrpsInfoDao.selectEntrpsInfoList(searchVO);
    }

    /**
	 * 업체정보 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectEntrpsInfoListTotCnt(GamPopupEntrpsInfoVO searchVO) throws Exception {
		return gamPopupEntrpsInfoDao.selectEntrpsInfoListTotCnt(searchVO);
	}

}
