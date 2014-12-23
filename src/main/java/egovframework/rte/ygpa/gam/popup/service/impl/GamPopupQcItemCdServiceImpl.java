/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupQcItemCdService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupQcItemCdVo;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 23.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 23.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamPopupQcItemCdService")
public class GamPopupQcItemCdServiceImpl extends AbstractServiceImpl implements GamPopupQcItemCdService {

	@Resource(name="gamPopupQcItemCdDao")
	private GamPopupQcItemCdDao gamPopupQcItemCdDao;

	/**
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
	public List selectQcItemCdList(GamPopupQcItemCdVo searchVO) throws Exception {
		return gamPopupQcItemCdDao.selectQcItemCdList(searchVO);
	}

	/**
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return Cnt
	 * @exception Exception
	 */
	public int selectQcItemCdListTotCnt(GamPopupQcItemCdVo searchVO) throws Exception {
		return gamPopupQcItemCdDao.selectQcItemCdListTotCnt(searchVO);
	}

}
