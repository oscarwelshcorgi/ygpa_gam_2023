/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFuelCodeService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFuelCodeVO;

/**
 *
 * @author ACEWOLF
 * @since 2014. 11. 28.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 28.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamPopupFuelCodeService")
public class GamPopupFuelCodeServiceImpl extends AbstractServiceImpl implements GamPopupFuelCodeService {

	@Resource(name="gamPopupFuelCodeDao")
	private GamPopupFuelCodeDao gamPopupFuelCodeDao;

	/**
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
	public List selectFuelCodeList(GamPopupFuelCodeVO searchVO) throws Exception {
		return gamPopupFuelCodeDao.selectFuelCodeList(searchVO);
	}

    /**
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	public int selectFuelCodeListTotCnt(GamPopupFuelCodeVO searchVO) throws Exception {
		return gamPopupFuelCodeDao.selectFuelCodeListTotCnt(searchVO);
	}

}
