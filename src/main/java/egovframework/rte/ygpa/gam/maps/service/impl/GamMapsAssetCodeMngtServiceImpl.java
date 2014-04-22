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

@Service("gamMapsAssetCodeMngtService")
public class GamMapsAssetCodeMngtServiceImpl extends AbstractServiceImpl
		implements GamMapsAssetCodeMngtService {

	@Resource(name="gamMapsAssetCodeMngtDAO")
	  private GamMapsAssetCodeMngtDAO gamMapsAssetCodeMngtDAO;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.maps.service.impl.GamMapsAssetCodeMngtService#selectAssetCodeInfoList(java.util.Map)
	 */
	@Override
	public List selectAssetCodeInfoList(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamMapsAssetCodeMngtDAO.selectAssetCodeInfoList(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.maps.service.GamMapsAssetCodeMngtService#selectAssetRentInfoList(java.util.Map)
	 */
	@Override
	public List selectAssetRentInfoList(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamMapsAssetCodeMngtDAO.selectAssetRentInfoList(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.maps.service.GamMapsAssetCodeMngtService#selectMapsAssetsCodeInfo(java.util.Map)
	 */
	@Override
	public EgovMap selectMapsAssetsCodeInfo(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamMapsAssetCodeMngtDAO.selectMapsAssetsCodeInfo(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.maps.service.GamMapsAssetCodeMngtService#selectMapsAssetsCodeUseInfo(java.util.Map)
	 */
	@Override
	public List selectMapsAssetsCodeUseInfo(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamMapsAssetCodeMngtDAO.selectMapsAssetsCodeUseInfo(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.maps.service.GamMapsAssetCodeMngtService#selectMapsAssetsCodeUseSummary(java.util.Map)
	 */
	@Override
	public EgovMap selectMapsAssetsCodeUseSummary(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamMapsAssetCodeMngtDAO.selectMapsAssetsCodeUseSummary(vo);
	}

}
