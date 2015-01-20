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
	public List selectGisAssetSttusList(Map searchVO) throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetSttusList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.impl.GamGisAssetSttusInqireService#selectGisAssetSttusAssetListTotCnt(java.util.Map)
	 */
	@Override
	public int selectGisAssetSttusListTotCnt(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetSttusListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.impl.GamGisAssetSttusInqireService#selectGisAssetSttusAssetListTotSum(java.util.Map)
	 */
	@Override
	public Map selectGisAssetSttusListTotSum(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetSttusListTotSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectGisAssetRentSttusAssetList(java.util.Map)
	 */
	@Override
	public List selectGisAssetRentSttusList(Map searchVO) throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetRentSttusList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectGisAssetRentSttusAssetListTotCnt(java.util.Map)
	 */
	@Override
	public int selectGisAssetRentSttusListTotCnt(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetRentSttusListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectGisAssetRentSttusAssetListTotSum(java.util.Map)
	 */
	@Override
	public Map selectGisAssetRentSttusListTotSum(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetRentSttusListTotSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectGisAssetRentFeeSttusAssetList(java.util.Map)
	 */
	@Override
	public List selectGisAssetRentFeeSttusList(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetRentFeeSttusList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectGisAssetRentFeeSttusAssetListTotCnt(java.util.Map)
	 */
	@Override
	public int selectGisAssetRentFeeSttusListTotCnt(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetRentFeeSttusListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectGisAssetRentFeeSttusAssetListTotSum(java.util.Map)
	 */
	@Override
	public Map selectGisAssetRentFeeSttusListTotSum(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetSttusListTotSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectGisAssetMntnRprSttusAssetList(java.util.Map)
	 */
	@Override
	public List selectGisAssetMntnRprSttusAssetList(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetSttusList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectGisAssetMntnRprSttusAssetListTotCnt(java.util.Map)
	 */
	@Override
	public int selectGisAssetMntnRprSttusAssetListTotCnt(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetSttusListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectGisAssetMntnRprSttusAssetListTotSum(java.util.Map)
	 */
	@Override
	public Map selectGisAssetMntnRprSttusAssetListTotSum(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetSttusListTotSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectGisAssetFlawRprSttusAssetList(java.util.Map)
	 */
	@Override
	public List selectGisAssetFlawRprSttusAssetList(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetSttusList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectGisAssetFlawRprSttusAssetListTotCnt(java.util.Map)
	 */
	@Override
	public int selectGisAssetFlawRprSttusAssetListTotCnt(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetSttusListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectGisAssetFlawRprSttusAssetListTotSum(java.util.Map)
	 */
	@Override
	public Map selectGisAssetFlawRprSttusAssetListTotSum(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetSttusListTotSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectGisAssetRentSttusByFcltyList(java.util.Map)
	 */
	@Override
	public List selectGisAssetRentSttusByFcltyList(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectGisAssetSttusByFcltyList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectAssetSttusInfoByCode(java.util.Map)
	 */
	@Override
	public Map selectAssetSttusInfoByCode(Map searchVO) throws Exception {
		return gamGisAssetSttusInqireDao.selectAssetSttusInfoByCode(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectAssetSttusDeprctnListByCode(java.util.Map)
	 */
	@Override
	public List selectAssetSttusDeprctnListByCode(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectAssetSttusDeprctnListByCode(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectAssetRentSttusInfoByCode(java.util.Map)
	 */
	@Override
	public Map selectAssetRentSttusInfoByCode(Map searchVO) throws Exception {
		return gamGisAssetSttusInqireDao.selectAssetRentSttusInfoByCode(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectAssetRentSttusListByCode(java.util.Map)
	 */
	@Override
	public List selectAssetRentSttusListByCode(Map searchVO) throws Exception {
		return gamGisAssetSttusInqireDao.selectAssetRentSttusListByCode(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectAssetRentFeeSttusInfoByCode(java.util.Map)
	 */
	@Override
	public Map selectAssetRentFeeSttusInfoByCode(Map searchVO) throws Exception {
		return gamGisAssetSttusInqireDao.selectAssetRentFeeSttusInfoByCode(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService#selectAssetRentFeeSttusListByCode(java.util.Map)
	 */
	@Override
	public List selectAssetRentFeeSttusListByCode(Map searchVO)
			throws Exception {
		return gamGisAssetSttusInqireDao.selectAssetRentFeeSttusListByCode(searchVO);
	}

}
