package egovframework.rte.ygpa.gam.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("gamAssetCodeSampleDao")
public class GamAssetCodeSampleDao extends EgovAbstractDAO {

    public String insertAssetCode(Map vo) throws Exception {
        return (String)insert("gamGisAssetPhotoDao.insertAssetPhoto_S", vo);
    }

    public void updateAssetCode(Map vo) throws Exception {
        update("gamGisAssetPhotoDao.updateAssetPhoto_S", vo);
    }

    public void deleteAssetCode(Map vo) throws Exception {
        delete("gamGisAssetPhotoDao.deleteAssetPhoto_S", vo);
    }

    public EgovMap selectAssetCode(Map vo) throws Exception {
        return (EgovMap) selectByPk("gamGisAssetPhotoDao.selectAssetPhoto_S", vo);
    }

	public List selectGamAssetCodeList(Map searchOpt) {
		// TODO Auto-generated method stub
		 return list("gamGisAssetPhotoDao.selectAssetPhotoList_D", searchOpt);
	}

	public int selectGamAssetCodeListTotCnt(Map searchOpt) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamGisAssetPhotoDao.selectAssetPhotoListTotCnt_S", searchOpt);
	}

	public List selectGamAssetCodePhotoList(Map searchOpt) {
		// TODO Auto-generated method stub
		 return list("gamGisAssetPhotoDao.selectAssetPhotoList_D", searchOpt);
	}

	public int selectGamAssetCodePhotoListTotCnt(Map searchOpt) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamGisAssetPhotoDao.selectAssetPhotoListTotCnt_S", searchOpt);
	}

}
