package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupMngCodeService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupMngCodeVO;

/**
 * @Class Name : GamPopupMngCodeServiceImpl.java
 * @Description : 업체정보 Business Implement class
 * @Modification Information
 *
 * @since 2014-10-30
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Service("gamPopupMngCodeService")
public class GamPopupMngCodeServiceImpl extends AbstractServiceImpl implements GamPopupMngCodeService {

	@Resource(name="gamPopupMngCodeDao")
    private GamPopupMngCodeDao gamPopupMngCodeDao;

	/**
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMngCodeList(GamPopupMngCodeVO searchVO) throws Exception {
        return gamPopupMngCodeDao.selectMngCodeList(searchVO);
    }

    /**
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMngCodeListTotCnt(GamPopupMngCodeVO searchVO) throws Exception {
		return gamPopupMngCodeDao.selectMngCodeListTotCnt(searchVO);
	}

}
