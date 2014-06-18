package egovframework.rte.ygpa.gam.oper.center.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetPopupInqireVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterPopupInqireService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayPopupInqireService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyPopupInqireService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldPopupInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrPopupInqireService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortPopupInqireService;

/**
 * @Class Name : GamMarineCenterPopupInqireServiceImpl.java
 * @Description : 마린센터 정보현황알림 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 * 수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.06.17  sj          최초 생성
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamMarineCenterPopupInqireService")
public class GamMarineCenterPopupInqireServiceImpl  extends AbstractServiceImpl implements GamMarineCenterPopupInqireService {

	@Resource(name="GamMarineCenterPopupInqireDao")
    private GamMarineCenterPopupInqireDao gamMarineCenterPopupInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 마린센터 정보현황알림 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetPopupInqireVO selectMarineCenterPopupInqire(GamAssetPopupInqireVO searchVO) throws Exception {
        return gamMarineCenterPopupInqireDao.selectMarineCenterPopupInqire(searchVO);
    }

}
