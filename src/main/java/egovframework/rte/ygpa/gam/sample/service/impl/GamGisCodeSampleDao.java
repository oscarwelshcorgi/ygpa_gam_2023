package egovframework.rte.ygpa.gam.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.asset.service.GisAssetsCdDefaultVO;
import egovframework.rte.ygpa.gam.asset.service.GisAssetsCdVO;

@Repository("gamGisCodeSampleDao")
public class GamGisCodeSampleDao extends EgovAbstractDAO {

    public String insertGisCode(GisAssetsCdVO vo) throws Exception {
        return (String)insert("gamGisCodeDAO.insertGisCode_S", vo);
    }

    public void updateGisCode(GisAssetsCdVO vo) throws Exception {
        update("gamGisCodeDAO.updateGisCode_S", vo);
    }

    public void deleteGisCode(GisAssetsCdVO vo) throws Exception {
        delete("gamGisCodeDAO.deleteGisCode_S", vo);
    }

    public EgovMap selectGisCode(GisAssetsCdVO vo) throws Exception {
        return (EgovMap) selectByPk("gamGisCodeDAO.selectGisCode_S", vo);
    }

	public List selectGisCodeList(GisAssetsCdDefaultVO searchOpt) {
		// TODO Auto-generated method stub
		 return list("gamGisCodeDao.selectGisCodeList_D", searchOpt);
	}

	public int selectGisCodeListTotCnt(GisAssetsCdDefaultVO searchOpt) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamAssetDao.selectGisCodeListTotCnt_S", searchOpt);
	}

}
