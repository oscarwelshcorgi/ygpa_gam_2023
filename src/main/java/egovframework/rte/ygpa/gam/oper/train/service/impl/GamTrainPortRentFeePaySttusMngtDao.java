package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentFeePaySttusMngtVO;

/**
 * @Class Name : GamTrainPortRentFeePaySttusMngtDao.java
 * @Description : 철송장납부현황관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTrainPortRentFeePaySttusMngtDao")
public class GamTrainPortRentFeePaySttusMngtDao extends YGPAAbstractDAO {

	/**
	 * 자산임대료납부관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료납부관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentFeePayDtlsList(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePayDtlsList_D", searchVO);
    }

    /**
	 * 자산임대료납부관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료납부관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentFeePayDtlsListTotCnt(GamTrainPortRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePayDtlsListTotCnt_S", searchVO);
    }

    public EgovMap selectTrainPortRentFeePayDtlsMngtDetailMstPk(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePayDtlsMngtDetailMaster_D", searchVO);
    }

    public List selectTrainPortRentFeePayDtlsMngtDetailList(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePayDtlsMngtDetailList_D", searchVO);
    }

    public EgovMap selectTrainPortRentFeePayDtlsMngtDetailSumPk(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePayDtlsMngtDetailSum_D", searchVO);
    }

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료납부관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentFeePaySttusMngtVO selectTrainPortRentFeePayDtlsSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamTrainPortRentFeePaySttusMngtVO) selectByPk("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePayDtlsSum_S", searchVO);
	}

    public EgovMap selectNticArrrgDetail(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamTrainPortRentFeePaySttusMngtDao.selectNticArrrgDetail_S", searchVO);
    }


	/**
	 * 고지금액합계, 수납금액합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
	public GamTrainPortRentFeePaySttusMngtVO selectTrainPortRentFeePayDtlsMngtSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamTrainPortRentFeePaySttusMngtVO) selectByPk("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePayDtlsMngtSum_S", searchVO);
	}

	public List<?> selectNticArrrgList(GamTrainPortRentArrrgMngtVO searchVO)
			throws Exception {
        return list("gamTrainPortRentFeePaySttusMngtDao.selectNticArrrgList_D", searchVO);
	}

	public int selectNticArrrgListTotCnt(GamTrainPortRentArrrgMngtVO searchVO)
			throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentFeePaySttusMngtDao.selectNticArrrgListTotCnt_S", searchVO);
	}

	public List mergeNticArrrgList(Map mergeMap) throws Exception {
		return this.merge(mergeMap, "gamTrainPortRentFeePaySttusMngtDao.insertAssetPhoto_S", "gamTrainPortRentFeePaySttusMngtDao.updateAssetPhoto_S", "gamTrainPortRentFeePaySttusMngtDao.deleteAssetPhoto_S");
	}

	public int updateTrainPortRentFeePayDtlsMngtList() throws Exception {
		return update("gamTrainPortRentFeePaySttusMngtDao.updateTrainPortRentFeePayDtlsMngtList", null);
	}

	/**
	 * 연체 등록
	 * @param map
	 * @throws Exception
	 */
	public void updateRevArrrgAnlrveBndeRcvdTp(Map map) throws Exception {
		this.update("gamTrainPortRentFeePaySttusMngtDao.updateRevArrrgAnlrveBndeRcvdTp", map);
	}
}
