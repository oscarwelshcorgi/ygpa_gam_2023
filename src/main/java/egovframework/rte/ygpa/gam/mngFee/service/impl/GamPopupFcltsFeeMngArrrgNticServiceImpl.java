/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamPopupFcltsFeeMngArrrgNticService;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 25.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamPopupFcltsFeeMngArrrgNticService")
public class GamPopupFcltsFeeMngArrrgNticServiceImpl extends AbstractServiceImpl implements GamPopupFcltsFeeMngArrrgNticService {

	@Resource(name="gamPopupFcltsFeeMngArrrgNticDao")
	private GamPopupFcltsFeeMngArrrgNticDao gamPopupFcltsFeeMngArrrgNticDao;

	@Override
	public EgovMap calcDlyBillAmnt(Map<String, Object> vo) throws Exception {
		return gamPopupFcltsFeeMngArrrgNticDao.calcDlyBillAmnt(vo);
	}

}
