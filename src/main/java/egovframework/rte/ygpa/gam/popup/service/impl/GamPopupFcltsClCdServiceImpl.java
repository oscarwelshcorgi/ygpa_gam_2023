/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFcltsClCdService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFcltsClCdVO;

/**
 *
 * @author 김종민
 * @since 2014. 11. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 5.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamPopupFcltsClCdService")
public class GamPopupFcltsClCdServiceImpl extends AbstractServiceImpl implements GamPopupFcltsClCdService {

	@Resource(name="gamPopupFcltsClCdDao")
	private GamPopupFcltsClCdDao gamPopupFcltsClCdDao;

	/**
	 * 시설물 분류 목록을 조회한다.
	 * @name selectFcltsClCdList
	 * @param searchVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectFcltsClCdList(GamPopupFcltsClCdVO searchVO) throws Exception {
		return gamPopupFcltsClCdDao.selectFcltsClCdList(searchVO);
	}

	/**
	 * 시설물 분류 목록 총 갯수를 조회한다.
	 * @name selectFcltsClCdListTotCnt
	 * @param searchVO
	 * @return totalCount
	 * @exception
	 */
	public int selectFcltsClCdListTotCnt(GamPopupFcltsClCdVO searchVO) throws Exception {
		return gamPopupFcltsClCdDao.selectFcltsClCdListTotCnt(searchVO);
	}

}
