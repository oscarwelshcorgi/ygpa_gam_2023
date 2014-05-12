package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentFeePaySttusMngtVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentArrrgMngtVO;

/**
 * @Class Name : GamCmmnCntrRentFeePaySttusMngtDao.java
 * @Description : 공컨장치장납부현황관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmmnCntrRentFeePaySttusMngtDao")
public class GamCmmnCntrRentFeePaySttusMngtDao extends YGPAAbstractDAO {

	/**
	 * 자산임대료납부관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료납부관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrRentFeePayDtlsList(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePayDtlsList_D", searchVO);
    }

    /**
	 * 자산임대료납부관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료납부관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrRentFeePayDtlsListTotCnt(GamCmmnCntrRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePayDtlsListTotCnt_S", searchVO);
    }

    public EgovMap selectCmmnCntrRentFeePayDtlsMngtDetailMstPk(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePayDtlsMngtDetailMaster_D", searchVO);
    }

    public List selectCmmnCntrRentFeePayDtlsMngtDetailList(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePayDtlsMngtDetailList_D", searchVO);
    }

    public EgovMap selectCmmnCntrRentFeePayDtlsMngtDetailSumPk(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePayDtlsMngtDetailSum_D", searchVO);
    }

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료납부관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrRentFeePaySttusMngtVO selectCmmnCntrRentFeePayDtlsSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamCmmnCntrRentFeePaySttusMngtVO) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePayDtlsSum_S", searchVO);
	}

    public EgovMap selectNticArrrgDetail(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectNticArrrgDetail_S", searchVO);
    }


	/**
	 * 고지금액합계, 수납금액합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
	public GamCmmnCntrRentFeePaySttusMngtVO selectCmmnCntrRentFeePayDtlsMngtSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamCmmnCntrRentFeePaySttusMngtVO) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePayDtlsMngtSum_S", searchVO);
	}

	public List<?> selectNticArrrgList(GamCmmnCntrRentArrrgMngtVO searchVO)
			throws Exception {
        return list("gamCmmnCntrRentFeePaySttusMngtDao.selectNticArrrgList_D", searchVO);
	}

	public int selectNticArrrgListTotCnt(GamCmmnCntrRentArrrgMngtVO searchVO)
			throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentFeePaySttusMngtDao.selectNticArrrgListTotCnt_S", searchVO);
	}

	public List mergeNticArrrgList(Map mergeMap) throws Exception {
		return this.merge(mergeMap, "gamCmmnCntrRentFeePaySttusMngtDao.insertAssetPhoto_S", "gamCmmnCntrRentFeePaySttusMngtDao.updateAssetPhoto_S", "gamCmmnCntrRentFeePaySttusMngtDao.deleteAssetPhoto_S");
	}

	public int updateCmmnCntrRentFeePayDtlsMngtList() throws Exception {
		return update("gamCmmnCntrRentFeePaySttusMngtDao.updateCmmnCntrRentFeePayDtlsMngtList", null);
	}

	/**
	 * 연체 등록
	 * @param map
	 * @throws Exception
	 */
	public void updateRevArrrgAnlrveBndeRcvdTp(Map map) throws Exception {
		this.update("gamCmmnCntrRentFeePaySttusMngtDao.updateRevArrrgAnlrveBndeRcvdTp", map);
	}
}
