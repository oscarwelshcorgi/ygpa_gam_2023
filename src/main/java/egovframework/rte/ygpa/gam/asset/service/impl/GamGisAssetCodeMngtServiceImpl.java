package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.asset.service.GamGisAssetCodeMngtService;
import egovframework.rte.ygpa.gam.asset.service.GamGisAssetCodeVO;

@Service("gamGisAssetCodeMngtService")
public class GamGisAssetCodeMngtServiceImpl extends AbstractServiceImpl implements GamGisAssetCodeMngtService {

    @Resource(name="gamGisAssetCodeMngtDao")
    private GamGisAssetCodeMngtDao gamGisAssetCodeMngtDao;


	@Override
	public String insertAssetCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamGisAssetCodeMngtDao.insertAssetCode(vo);
	}

	@Override
	public void updateAssetCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisAssetCodeMngtDao.updateAssetCode(vo);
	}

	@Override
	public void deleteAssetCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisAssetCodeMngtDao.deleteAssetCode(vo);
	}

	@Override
	public EgovMap selectAssetCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamGisAssetCodeMngtDao.selectAssetCode(vo);
	}

	@Override
	public void updateAssetCodeGis(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisAssetCodeMngtDao.updateAssetCode(vo);
	}

	@Override
	public List selectAssetCodeList(GamGisAssetCodeVO searchOpt) {
		// TODO Auto-generated method stub
		return gamGisAssetCodeMngtDao.selectGamAssetCodeList(searchOpt);
	}

	@Override
	public int selectAssetCodeListTotCnt(GamGisAssetCodeVO searchOpt) {
		// TODO Auto-generated method stub
		return gamGisAssetCodeMngtDao.selectGamAssetCodeListTotCnt(searchOpt);
	}

}
