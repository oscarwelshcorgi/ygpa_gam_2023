/**
 *
 */
package egovframework.rte.ygpa.gam.maps.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.maps.service.GamMapsAssetCodeMngtService;
import egovframework.rte.ygpa.gam.maps.service.GamMapsAssetRentMngtService;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 31.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamMapsAssetRentMngtServiceImpl")
public class GamMapsAssetRentMngtServiceImpl extends AbstractServiceImpl
		implements GamMapsAssetRentMngtService {

	@Resource(name="gamMapsAssetRentMngtDAO")
	  private GamMapsAssetRentMngtDAO gamMapsAssetRentMngtDAO;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.maps.service.GamMapsAssetRentMngtService#selectAssetRentInfo(java.util.Map)
	 */
	@Override
	public EgovMap selectMapsAssetsRentInfo(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamMapsAssetRentMngtDAO.selectMapsAssetsRentInfo(vo);
	}

}
