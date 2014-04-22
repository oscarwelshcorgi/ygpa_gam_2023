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
        return (String)insert("gamGisAssetPhotoDao.insertAssetPhoto_S", vo);
    }

    public void updateAssetPhoto(Map vo) throws Exception {
        update("gamGisAssetPhotoDao.updateAssetPhoto_S", vo);
    }

    public void deleteAssetPhoto(Map vo) throws Exception {
        delete("gamGisAssetPhotoDao.deleteAssetPhoto_S", vo);
    }

    public EgovMap selectAssetPhoto(Map vo) throws Exception {
        return (EgovMap) selectByPk("gamGisAssetPhotoDao.selectAssetPhoto_S", vo);
    }

    public Integer selectGamAssetPhotoMaxSeq(Map vo) throws Exception {
    	return (Integer) selectByPk("gamGisAssetPhotoDao.selectGetNewAssetPhotoSeq_S", vo);
    }

	public List selectGamAssetPhotoList(GamGisAssetPhotoVO searchOpt) {
		// TODO Auto-generated method stub
		 return list("gamGisAssetPhotoDao.selectAssetPhotoList_D", searchOpt);
	}

	public int selectGamAssetPhotoListTotCnt(GamGisAssetPhotoVO searchOpt) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamGisAssetPhotoDao.selectAssetPhotoListTotCnt_S", searchOpt);
	}

	public List mergeGisAssetPhoto(Map map) throws Exception {
		return this.merge(map, "gamGisAssetPhotoDao.insertAssetPhoto_S", "gamGisAssetPhotoDao.updateAssetPhoto_S", "gamGisAssetPhotoDao.deleteAssetPhoto_S");
	}

}
