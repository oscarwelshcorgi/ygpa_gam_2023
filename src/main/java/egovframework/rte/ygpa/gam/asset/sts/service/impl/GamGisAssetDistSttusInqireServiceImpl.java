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
import egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetDistSttusInqireService;
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
@Service("gamGisAssetDistSttusInqireService")
public class GamGisAssetDistSttusInqireServiceImpl  extends AbstractServiceImpl implements GamGisAssetDistSttusInqireService {

	@Resource(name="gamGisAssetDistSttusInqireDao")
    private GamGisAssetDistSttusInqireDao gamGisAssetDistSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetDistSttusInqireService#selectGisAssetDistSttusList(java.util.Map)
	 */
	@Override
	public List selectGisAssetDistSttusList(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamGisAssetDistSttusInqireDao.selectGisAssetDistSttusList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetDistSttusInqireService#selectGisAssetDistSttusListTotCnt(java.util.Map)
	 */
	@Override
	public int selectGisAssetDistSttusListTotCnt(Map searchVO) throws Exception {
		return gamGisAssetDistSttusInqireDao.selectGisAssetDistSttusListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetDistSttusInqireService#selectGisAssetDistSttusListTotSum(java.util.Map)
	 */
	@Override
	public Map selectGisAssetDistSttusListTotSum(Map searchVO) throws Exception {
		return gamGisAssetDistSttusInqireDao.selectGisAssetDistSttusListTotSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetDistSttusInqireService#selectAssetDistSttusInfoByCode(java.util.Map)
	 */
	@Override
	public Map selectAssetDistSttusInfoByCode(Map searchVO) throws Exception {
		return gamGisAssetDistSttusInqireDao.selectAssetDistSttusInfoByCode(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetDistSttusInqireService#selectAssetDistSttusInfoListByCode(java.util.Map)
	 */
	@Override
	public List selectAssetDistSttusInfoListByCode(Map searchVO)
			throws Exception {
		return gamGisAssetDistSttusInqireDao.selectAssetDistSttusListByCode(searchVO);
	}

}
