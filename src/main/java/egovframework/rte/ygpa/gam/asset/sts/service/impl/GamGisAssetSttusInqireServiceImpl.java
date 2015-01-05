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
@Service("gamGisAssetSttusInqireService")
public class GamGisAssetSttusInqireServiceImpl  extends AbstractServiceImpl implements GamGisAssetSttusInqireService {

	@Resource(name="gamGisAssetSttusInqireDao")
    private GamGisAssetSttusInqireDao gamGisAssetSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.impl.GamGisAssetSttusInqireService#selectGisAssetSttusAssetList(java.util.Map)
	 */
	@Override
	public List selectGisAssetSttusAssetList(Map searchVO) throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetSttusAssetList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.impl.GamGisAssetSttusInqireService#selectGisAssetSttusAssetListTotCnt(java.util.Map)
	 */
	@Override
	public int selectGisAssetSttusAssetListTotCnt(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetSttusAssetListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.impl.GamGisAssetSttusInqireService#selectGisAssetSttusAssetListTotSum(java.util.Map)
	 */
	@Override
	public Map selectGisAssetSttusAssetListTotSum(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetSttusAssetListTotSum(searchVO);
	}

}
