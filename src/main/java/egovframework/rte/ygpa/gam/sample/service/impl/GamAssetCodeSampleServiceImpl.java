package egovframework.rte.ygpa.gam.sample.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.sample.service.GamAssetCodeSampleService;
import egovframework.rte.ygpa.gam.sample.service.GamAssetSampleService;

@Service("gamAssetCodeSampleService")
public class GamAssetCodeSampleServiceImpl extends AbstractServiceImpl implements GamAssetCodeSampleService {

    @Resource(name="gamAssetCodeSampleDao")
    private GamAssetCodeSampleDao gamAssetCodeDao;


	@Override
	public String insertAssetCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamAssetCodeDao.insertAssetCode(vo);
	}

	@Override
	public void updateAssetCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamAssetCodeDao.updateAssetCode(vo);
	}

	@Override
	public void deleteAssetCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamAssetCodeDao.deleteAssetCode(vo);
	}

	@Override
	public EgovMap selectAssetCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamAssetCodeDao.selectAssetCode(vo);
	}

	@Override
	public void updateAssetCodeGis(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamAssetCodeDao.updateAssetCode(vo);
	}

	@Override
	public List selectAssetCodeList(Map searchOpt) {
		// TODO Auto-generated method stub
		return gamAssetCodeDao.selectGamAssetCodeList(searchOpt);
	}

	@Override
	public int selectAssetCodeListTotCnt(Map searchOpt) {
		// TODO Auto-generated method stub
		return gamAssetCodeDao.selectGamAssetCodeListTotCnt(searchOpt);
	}

}
