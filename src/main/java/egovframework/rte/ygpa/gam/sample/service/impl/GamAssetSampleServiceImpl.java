package egovframework.rte.ygpa.gam.sample.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.asset.service.GisAssetsCdDefaultVO;
import egovframework.rte.ygpa.gam.sample.service.GamAssetSampleService;

@Service("gamAssetSampleService")
public class GamAssetSampleServiceImpl implements GamAssetSampleService {

    @Resource(name="gamAssetSampleDao")
    private GamAssetSampleDao gamAssetDao;

	@Override
	public List selectGamAssetUseList(GisAssetsCdDefaultVO searchOpt) {
		// TODO Auto-generated method stub
		return gamAssetDao.selectGamAssetUseList(searchOpt);
	}

	@Override
	public int selectGamAssetUseListTotCnt(GisAssetsCdDefaultVO searchOpt) {
		// TODO Auto-generated method stub
		return gamAssetDao.selectGamAssetUseListTotCnt(searchOpt);
	}

}
