package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetPopupInqireVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyPopupInqireService;

/**
 * @Class Name : GamPrtFcltyPopupInqireServiceImpl.java
 * @Description : 항만시설정보현황알림 Business Implement class
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
@Service("gamPrtFcltyPopupInqireService")
public class GamPrtFcltyPopupInqireServiceImpl  extends AbstractServiceImpl implements GamPrtFcltyPopupInqireService {

	@Resource(name="gamPrtFcltyPopupInqireDao")
    private GamPrtFcltyPopupInqireDao gamPrtFcltyPopupInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설정보현황알림 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetPopupInqireVO selectPrtFcltyPopupInqire(GamAssetPopupInqireVO searchVO) throws Exception {
        return gamPrtFcltyPopupInqireDao.selectPrtFcltyPopupInqire(searchVO);
    }

}
