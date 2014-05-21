package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;

@Repository("gamGisAssetCodeMngtDao")
public class GamGisAssetCodeMngtDao extends YGPAAbstractDAO {

    public String insertAssetCode(Map vo) throws Exception {
        return (String)insert("gamGisAssetCodeDao.insertAssetCode_S", vo);
    }

    public void updateAssetCode(Map vo) throws Exception {
        update("gamGisAssetCodeDao.updateAssetCode_S", vo);
    }


    public String selectAssetCodeGetSubCode(Map vo) throws Exception {
        return (String)selectByPk("gamGisAssetCodeDao.selectAssetCodeGetSubCode_S", vo);
    }

    public void changeAssetCodePkGisAssetsPhoto(Map vo) throws Exception {
        update("gamGisAssetCodeDao.changeAssetCodePkGisAssetsPhoto", vo);
    }

    public void changeAssetCodePkPrtFcltyPhoto(Map vo) throws Exception {
        update("gamGisAssetCodeDao.changeAssetCodePkPrtFcltyPhoto", vo);
    }

    public void changeAssetCodePkPrtFcltyCd(Map vo) throws Exception {
        update("gamGisAssetCodeDao.changeAssetCodePkPrtFcltyCd", vo);
    }

    public void changeAssetCodePkLevReqestPlotByFee(Map vo) throws Exception {
        update("gamGisAssetCodeDao.changeAssetCodePkLevReqestPlotByFee", vo);
    }

    public void changeAssetCodePkAssetsRentDetail(Map vo) throws Exception {
        update("gamGisAssetCodeDao.changeAssetCodePkAssetsRentDetail", vo);
    }

    public void changeAssetCodePk(Map vo) throws Exception {
        update("gamGisAssetCodeDao.changeAssetCodePk", vo);
    }

    public void deleteAssetCode(Map vo) throws Exception {
        delete("gamGisAssetCodeDao.deleteAssetCode_S", vo);
    }

    public EgovMap selectAssetCode(Map vo) throws Exception {
        return (EgovMap) selectByPk("gamGisAssetCodeDao.selectAssetCode_S", vo);
    }

    public EgovMap selectGetNewAssetCode(Map vo) throws Exception {
    	return (EgovMap) selectByPk("gamGisAssetCodeDao.selectGetNewAssetCode_S", vo);
    }

	public List selectGamAssetCodeList(GamGisAssetCodeVO searchOpt) {
		// TODO Auto-generated method stub
		 return list("gamGisAssetCodeDao.selectAssetCodeList_D", searchOpt);
	}

	public int selectGamAssetCodeListTotCnt(GamGisAssetCodeVO searchOpt) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamGisAssetCodeDao.selectAssetCodeListTotCnt_S", searchOpt);
	}

    public EgovMap selectAssetCodeOlnlp(Map vo) throws Exception {
    	return (EgovMap) selectByPk("gamGisAssetCodeDao.selectAssetCodeOlnlp_S", vo);
    }
}
