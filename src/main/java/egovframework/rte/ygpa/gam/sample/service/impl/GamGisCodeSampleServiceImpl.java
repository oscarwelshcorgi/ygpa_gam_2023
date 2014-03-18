package egovframework.rte.ygpa.gam.sample.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.asset.service.GisAssetsCdDefaultVO;
import egovframework.rte.ygpa.gam.asset.service.GisAssetsCdVO;
import egovframework.rte.ygpa.gam.sample.service.GamGisCodeSampleService;

@Service("gamGisCodeSampleService")
public class GamGisCodeSampleServiceImpl implements GamGisCodeSampleService {

    @Resource(name="gamGisCodeSampleDao")
    private GamGisCodeSampleDao gamGisCodeDao;


	@Override
	public String insertGisCode(GisAssetsCdVO vo) throws Exception {
		// TODO Auto-generated method stub
		return gamGisCodeDao.insertGisCode(vo);
	}

	@Override
	public void updateGisCode(GisAssetsCdVO vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisCodeDao.updateGisCode(vo);
	}

	@Override
	public void deleteGisCode(GisAssetsCdVO vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisCodeDao.deleteGisCode(vo);
	}

	@Override
	public EgovMap selectGisCode(GisAssetsCdVO vo) throws Exception {
		// TODO Auto-generated method stub
		return gamGisCodeDao.selectGisCode(vo);
	}

	@Override
	public List selectGisCodeList(GisAssetsCdDefaultVO searchOpt) {
		// TODO Auto-generated method stub
		return gamGisCodeDao.selectGisCodeList(searchOpt);
	}

	@Override
	public int selectGisCodeListTotCnt(GisAssetsCdDefaultVO searchOpt) {
		// TODO Auto-generated method stub
		return gamGisCodeDao.selectGisCodeListTotCnt(searchOpt);
	}

}
