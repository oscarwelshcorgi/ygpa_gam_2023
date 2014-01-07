package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("gamAssetDao")
public class GamAssetDao extends EgovAbstractDAO {
	
	public List selectGamAssetUseList(Map searchOpt) {
		// TODO Auto-generated method stub
		 return list("gamAssetDao.selectGamAssetUseList_D", searchOpt);
	}
	
	public int selectGamAssetUseListTotCnt(Map searchOpt) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamAssetDao.selectGamAssetUseListTotCnt_S", searchOpt);
	}

}
