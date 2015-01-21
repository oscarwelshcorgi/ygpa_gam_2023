package egovframework.rte.ygpa.gam.asset.sts.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.service.GamAssetDisUseMngtService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetDisUseMngtVO;
import egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetUseSttusInqireService;
import egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;

/**
 * @Class Name : GamAssetDisUseMngtServiceImpl.java
 * @Description : GIS자산통계 정보 서비스 구현
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-11-24
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamGisAssetUseSttusInqireService")
public class GamGisAssetUseSttusInqireServiceImpl  extends AbstractServiceImpl implements GamGisAssetUseSttusInqireService {

	@Resource(name="gamGisAssetUseSttusInqireDao")
    private GamGisAssetUseSttusInqireDao gamGisAssetUseSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetUseSttusInqireService#selectGisAssetUseSttusList(java.util.Map)
	 */
	@Override
	public List selectGisAssetUseSttusList(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamGisAssetUseSttusInqireDao.selectGisAssetUseSttusList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetUseSttusInqireService#selectGisAssetUseSttusListTotCnt(java.util.Map)
	 */
	@Override
	public int selectGisAssetUseSttusListTotCnt(Map searchVO) throws Exception {
		return gamGisAssetUseSttusInqireDao.selectGisAssetUseSttusListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetUseSttusInqireService#selectGisAssetUseSttusListTotSum(java.util.Map)
	 */
	@Override
	public Map selectGisAssetUseSttusListTotSum(Map searchVO) throws Exception {
		return gamGisAssetUseSttusInqireDao.selectGisAssetUseSttusListTotSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetUseSttusInqireService#selectAssetUseSttusInfoByCode(java.util.Map)
	 */
	@Override
	public Map selectAssetUseSttusInfoByCode(Map searchVO) throws Exception {
		return gamGisAssetUseSttusInqireDao.selectAssetUseSttusInfoByCode(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetUseSttusInqireService#selectAssetUseSttusInfoListByCode(java.util.Map)
	 */
	@Override
	public List selectAssetUseSttusInfoListByCode(Map searchVO)
			throws Exception {
		return gamGisAssetUseSttusInqireDao.selectAssetUseSttusListByCode(searchVO);
	}

}
