package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetPopupInqireVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyPopupInqireService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyPopupInqireVO;

/**
 * @Class Name : GamTestPrtFcltyPopupInqireServiceImpl.java
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
@Service("gamTestPrtFcltyPopupInqireService")
public class GamTestPrtFcltyPopupInqireServiceImpl  extends AbstractServiceImpl implements GamTestPrtFcltyPopupInqireService {

	@Resource(name="gamTestPrtFcltyPopupInqireDao")
    private GamTestPrtFcltyPopupInqireDao gamTestPrtFcltyPopupInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyPopupInqireService#selectPrtFcltyPopupInqire(egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyPopupInqireVO)
	 */
	@Override
	public GamTestPrtFcltyPopupInqireVO selectPrtFcltyPopupInqire(
			GamTestPrtFcltyPopupInqireVO searchVO) throws Exception {
		return gamTestPrtFcltyPopupInqireDao.selectPrtFcltyPopupInqire(searchVO);
	}

}
