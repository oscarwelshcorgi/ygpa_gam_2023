package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetPopupInqireService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetPopupInqireVO;

/**
 * @Class Name : GamAssetPopupInqireServiceImpl.java
 * @Description : 자산정보현황알림 Business Implement class
 * @Modification Information
 *
 * @author 정윤후
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetPopupInqireService")

public class GamAssetPopupInqireServiceImpl  extends AbstractServiceImpl implements GamAssetPopupInqireService {

	@Resource(name="gamAssetPopupInqireDao")
    private GamAssetPopupInqireDao gamAssetPopupInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산정보현황 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetPopupInqireVO selectAssetPopupInqire(GamAssetPopupInqireVO searchVO) throws Exception {
        return gamAssetPopupInqireDao.selectAssetPopupInqire(searchVO);
    }

}
