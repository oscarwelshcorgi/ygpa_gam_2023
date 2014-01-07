package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.asset.service.GamAssetService;

@Service("gamAssetService")
public class GamAssetServiceImpl implements GamAssetService {
	
    @Resource(name="gamAssetDao")
    private GamAssetDao gamAssetDao;

	@Override
	public List selectGamAssetUseList(Map searchOpt) {
		// TODO Auto-generated method stub
		return gamAssetDao.selectGamAssetUseList(searchOpt);
	}

	@Override
	public int selectGamAssetUseListTotCnt(Map searchOpt) {
		// TODO Auto-generated method stub
		return gamAssetDao.selectGamAssetUseListTotCnt(searchOpt);
	}

}
