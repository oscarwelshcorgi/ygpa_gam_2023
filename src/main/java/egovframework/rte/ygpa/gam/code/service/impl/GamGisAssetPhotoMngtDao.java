package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetPhotoVO;

@Repository("gamGisAssetPhotoMngtDao")
public class GamGisAssetPhotoMngtDao extends YGPAAbstractDAO {

    public String insertAssetPhoto(Map vo) throws Exception {
        return (String)insert("gamAssetPhotoDao.insertAssetPhoto_S", vo);
    }

    public void updateAssetPhoto(Map vo) throws Exception {
        update("gamAssetPhotoDao.updateAssetPhoto_S", vo);
    }

    public void deleteAssetPhoto(Map vo) throws Exception {
        delete("gamAssetPhotoDao.deleteAssetPhoto_S", vo);
    }

    public EgovMap selectAssetPhoto(Map vo) throws Exception {
        return (EgovMap) selectByPk("gamAssetPhotoDao.selectAssetPhoto_S", vo);
    }

    public EgovMap selectGetNewAssetPhoto(Map vo) throws Exception {
    	return (EgovMap) selectByPk("gamAssetPhotoDao.selectGetNewAssetPhoto_S", vo);
    }

	public List selectGamAssetPhotoList(GamGisAssetPhotoVO searchOpt) {
		// TODO Auto-generated method stub
		 return list("gamGisAssetPhotoDao.selectAssetPhotoList_D", searchOpt);
	}

	public int selectGamAssetPhotoListTotCnt(GamGisAssetPhotoVO searchOpt) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamGisAssetPhotoDao.selectAssetPhotoListTotCnt_S", searchOpt);
	}

}
