package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetPhotoMngtService;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetPhotoVO;

@Service("gamGisAssetPhotoMngtService")
public class GamGisAssetPhotoMngtServiceImpl extends AbstractServiceImpl implements GamGisAssetPhotoMngtService {

    @Resource(name="gamGisAssetPhotoMngtDao")
    private GamGisAssetPhotoMngtDao gamGisAssetPhotoMngtDao;


	@Override
	public String insertAssetPhoto(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamGisAssetPhotoMngtDao.insertAssetPhoto(vo);
	}

	@Override
	public void updateAssetPhoto(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisAssetPhotoMngtDao.updateAssetPhoto(vo);
	}

	@Override
	public void deleteAssetPhoto(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisAssetPhotoMngtDao.deleteAssetPhoto(vo);
	}

	@Override
	public EgovMap selectAssetPhoto(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamGisAssetPhotoMngtDao.selectAssetPhoto(vo);
	}

	@Override
	public void updateAssetPhotoGis(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisAssetPhotoMngtDao.updateAssetPhoto(vo);
	}

	@Override
	public List selectAssetPhotoList(GamGisAssetPhotoVO searchOpt) {
		// TODO Auto-generated method stub
		return gamGisAssetPhotoMngtDao.selectGamAssetPhotoList(searchOpt);
	}

	@Override
	public int selectAssetPhotoListTotCnt(GamGisAssetPhotoVO searchOpt) {
		// TODO Auto-generated method stub
		return gamGisAssetPhotoMngtDao.selectGamAssetPhotoListTotCnt(searchOpt);
	}

}
