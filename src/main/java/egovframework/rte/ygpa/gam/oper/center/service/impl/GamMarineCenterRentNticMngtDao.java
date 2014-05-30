package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentNticMngtVO;

/**
 * @Class Name : GamMarineCenterRentNticMngtDao.java
 * @Description : 마린센터임대료납부관리 DAO Class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-02-05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamMarineCenterRentNticMngtDao")
public class GamMarineCenterRentNticMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 자산임대료납부관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료납부관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentFeePayDtlsList(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
        return list("gamMarineCenterRentNticMngtDao.selectMarineCenterRentFeePayDtlsList_D", searchVO);
    }

    /**
	 * 자산임대료납부관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료납부관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentFeePayDtlsListTotCnt(GamMarineCenterRentNticMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentNticMngtDao.selectMarineCenterRentFeePayDtlsListTotCnt_S", searchVO);
    }

    public EgovMap selectMarineCenterRentFeePayDtlsMngtDetailMstPk(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamMarineCenterRentNticMngtDao.selectMarineCenterRentFeePayDtlsMngtDetailMaster_D", searchVO);
    }

    public List selectMarineCenterRentFeePayDtlsMngtDetailList(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
        return list("gamMarineCenterRentNticMngtDao.selectMarineCenterRentFeePayDtlsMngtDetailList_D", searchVO);
    }

    public EgovMap selectMarineCenterRentFeePayDtlsMngtDetailSumPk(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamMarineCenterRentNticMngtDao.selectMarineCenterRentFeePayDtlsMngtDetailSum_D", searchVO);
    }

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료납부관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentNticMngtVO selectMarineCenterRentFeePayDtlsSum(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentNticMngtVO) selectByPk("gamMarineCenterRentNticMngtDao.selectMarineCenterRentFeePayDtlsSum_S", searchVO);
	}

    public EgovMap selectNticArrrgDetail(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamMarineCenterRentNticMngtDao.selectNticArrrgDetail_S", searchVO);
    }


	/**
	 * 고지금액합계, 수납금액합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
	public GamMarineCenterRentNticMngtVO selectMarineCenterRentFeePayDtlsMngtSum(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentNticMngtVO) selectByPk("gamMarineCenterRentNticMngtDao.selectMarineCenterRentFeePayDtlsMngtSum_S", searchVO);
	}

	public List<?> selectNticArrrgList(GamMarineCenterRentArrrgMngtVO searchVO)
			throws Exception {
        return list("gamMarineCenterRentNticMngtDao.selectNticArrrgList_D", searchVO);
	}

	public int selectNticArrrgListTotCnt(GamMarineCenterRentArrrgMngtVO searchVO)
			throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentNticMngtDao.selectNticArrrgListTotCnt_S", searchVO);
	}

	public List mergeNticArrrgList(Map mergeMap) throws Exception {
		return this.merge(mergeMap, "gamMarineCenterRentNticMngtDao.insertAssetPhoto_S", "gamMarineCenterRentNticMngtDao.updateAssetPhoto_S", "gamMarineCenterRentNticMngtDao.deleteAssetPhoto_S");
	}

	public int updateMarineCenterRentFeePayDtlsMngtList() throws Exception {
		return update("gamMarineCenterRentNticMngtDao.updateMarineCenterRentFeePayDtlsMngtList", null);
	}

	/**
	 * 연체 등록
	 * @param map
	 * @throws Exception
	 */
	public void updateRevArrrgAnlrveBndeRcvdTp(Map map) throws Exception {
		this.update("gamMarineCenterRentNticMngtDao.updateRevArrrgAnlrveBndeRcvdTp", map);
	}


	/** change** */
	public List selectMarineCenterRentNticMngtDlyList(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
        return list("gamMarineCenterRentNticMngtDao.selectMarineCenterRentNticMngtDlyList_D", searchVO);
    }
	
	
	/** change**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentNticMngtDlyListTotCnt(GamMarineCenterRentNticMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentNticMngtDao.selectMarineCenterRentNticMngtDlyListTotCnt_S", searchVO);
    }
    
    /** change** */
    public EgovMap selectMarineCenterRentNticMngtDlyListSum(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamMarineCenterRentNticMngtDao.selectMarineCenterRentNticMngtDlyListSum_D", searchVO);
    }

}
