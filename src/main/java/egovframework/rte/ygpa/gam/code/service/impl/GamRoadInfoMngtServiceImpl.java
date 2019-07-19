/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.code.service.GamRoadInfoMngtService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupRoadInfoMngtVO;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 12.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamRoadInfoMngtService")
public class GamRoadInfoMngtServiceImpl extends AbstractServiceImpl implements GamRoadInfoMngtService{

	@Resource(name="gamRoadInfoMngtDao")
  private GamRoadInfoMngtDao gamRoadInfoMngtDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.code.service.GamRoadInfoMngtService#selectRoadInfoList(egovframework.rte.ygpa.gam.popup.service.GamRoadInfoMngtVO)
	 */
	@Override
	public List<?> selectRoadInfoList(GamPopupRoadInfoMngtVO searchVO) {
		// TODO Auto-generated method stub
		return gamRoadInfoMngtDao.selectRoadInfoList(searchVO);
	}




}