package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("gamAssetCodeDao")
public class GamAssetCodeDao extends EgovAbstractDAO {
	
    public String insertAssetCode(Map vo) throws Exception {
        return (String)insert("gamAssetCodeDAO.insertAssetCode_S", vo);
    }

    public void updateAssetCode(Map vo) throws Exception {
        update("gamAssetCodeDAO.updateAssetCode_S", vo);
    }

    public void deleteAssetCode(Map vo) throws Exception {
        delete("AssetCodeDAO.deleteAssetCode_S", vo);
    }

    public EgovMap selectAssetCode(Map vo) throws Exception {
        return (EgovMap) selectByPk("AssetCodeDAO.selectAssetCode_S", vo);
    }

	public List selectGamAssetCodeList(Map searchOpt) {
		// TODO Auto-generated method stub
		 return list("gamAssetCodeDao.selectAssetCodeList_D", searchOpt);
	}
	
	public int selectGamAssetCodeListTotCnt(Map searchOpt) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamAssetCodeDao.selectAssetCodeListTotCnt_S", searchOpt);
	}

}
