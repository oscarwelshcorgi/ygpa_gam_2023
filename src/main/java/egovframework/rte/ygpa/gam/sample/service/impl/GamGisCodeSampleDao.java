package egovframework.rte.ygpa.gam.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("gamGisCodeDao")
public class GamGisCodeSampleDao extends EgovAbstractDAO {
	
    public String insertGisCode(Map vo) throws Exception {
        return (String)insert("gamGisCodeDAO.insertGisCode_S", vo);
    }

    public void updateGisCode(Map vo) throws Exception {
        update("gamGisCodeDAO.updateGisCode_S", vo);
    }

    public void deleteGisCode(Map vo) throws Exception {
        delete("gamGisCodeDAO.deleteGisCode_S", vo);
    }

    public EgovMap selectGisCode(Map vo) throws Exception {
        return (EgovMap) selectByPk("gamGisCodeDAO.selectGisCode_S", vo);
    }

	public List selectGisCodeList(Map searchOpt) {
		// TODO Auto-generated method stub
		 return list("gamGisCodeDao.selectGisCodeList_D", searchOpt);
	}
	
	public int selectGisCodeListTotCnt(Map searchOpt) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamAssetDao.selectGisCodeListTotCnt_S", searchOpt);
	}

}
