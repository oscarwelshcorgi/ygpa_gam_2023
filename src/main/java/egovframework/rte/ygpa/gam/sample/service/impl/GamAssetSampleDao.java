package egovframework.rte.ygpa.gam.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.gam.asset.service.GisAssetsCdDefaultVO;

@Repository("gamAssetSampleDao")
public class GamAssetSampleDao extends EgovAbstractDAO {

	public List selectGamAssetUseList(GisAssetsCdDefaultVO searchOpt) {
		// TODO Auto-generated method stub
		 return list("gamAssetDao.selectGamAssetUseList_D", searchOpt);
	}

	public int selectGamAssetUseListTotCnt(GisAssetsCdDefaultVO searchOpt) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamAssetDao.selectGamAssetUseListTotCnt_S", searchOpt);
	}

}
