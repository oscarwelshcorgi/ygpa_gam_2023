/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupQcItemCdTreeService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupQcItemCdTreeVO;

/**
 * 
 * @author 김종민
 * @since 2014. 12. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 31.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamPopupQcItemCdTreeService")
public class GamPopupQcItemCdTreeServiceImpl extends AbstractServiceImpl implements GamPopupQcItemCdTreeService {

	@Resource(name="gamPopupQcItemCdTreeDao")
	private GamPopupQcItemCdTreeDao gamPopupQcItemCdTreeDao;

	/**
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
	public List<?> selectQcItemCdTreeList(GamPopupQcItemCdTreeVO searchVO) throws Exception {
		return gamPopupQcItemCdTreeDao.selectQcItemCdTreeList(searchVO);
	}

}