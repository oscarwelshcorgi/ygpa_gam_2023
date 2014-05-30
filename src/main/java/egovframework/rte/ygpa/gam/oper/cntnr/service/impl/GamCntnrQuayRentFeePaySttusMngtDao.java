package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtVO;


/**
 * @Class Name : GamCntnrQuayRentFeePaySttusMngtDao.java
 * @Description : 컨테이너부두납부현황관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCntnrQuayRentFeePaySttusMngtDao")
public class GamCntnrQuayRentFeePaySttusMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 컨테이너부두임대납부현황관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대납부현황관리 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayRentFeePaySttusMngtList(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtList_D", searchVO);
    }

    /**
	 * 컨테이너부두임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayRentFeePaySttusMngtListTotCnt(GamCntnrQuayRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대납부현황관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayRentFeePaySttusMngtVO selectCntnrQuayRentFeePaySttusSum(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamCntnrQuayRentFeePaySttusMngtVO) selectByPk("gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusSum_S", searchVO);
	}
	
	
	
	
	
	
	
	public EgovMap selectCntnrQuayRentFeePaySttusMngtDetailMstPk(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtDetailMaster_D", searchVO);
    }

    public List selectCntnrQuayRentFeePaySttusMngtDetailList(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtDetailList_D", searchVO);
    }

    public EgovMap selectCntnrQuayRentFeePaySttusMngtDetailSumPk(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtDetailSum_D", searchVO);
    }

    

    public EgovMap selectNticArrrgDetail(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamCntnrQuayRentFeePaySttusMngtDao.selectNticArrrgDetail_S", searchVO);
    }


	/**
	 * 고지금액합계, 수납금액합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
	public GamCntnrQuayRentFeePaySttusMngtVO selectCntnrQuayRentFeePaySttusMngtSum(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamCntnrQuayRentFeePaySttusMngtVO) selectByPk("gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtSum_S", searchVO);
	}

	public List<?> selectNticArrrgList(GamCntnrQuayRentArrrgMngtVO searchVO)
			throws Exception {
        return list("gamCntnrQuayRentFeePaySttusMngtDao.selectNticArrrgList_D", searchVO);
	}

	public int selectNticArrrgListTotCnt(GamCntnrQuayRentArrrgMngtVO searchVO)
			throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentFeePaySttusMngtDao.selectNticArrrgListTotCnt_S", searchVO);
	}

	public List mergeNticArrrgList(Map mergeMap) throws Exception {
		return this.merge(mergeMap, "gamCntnrQuayRentFeePaySttusMngtDao.insertAssetPhoto_S", "gamCntnrQuayRentFeePaySttusMngtDao.updateAssetPhoto_S", "gamCntnrQuayRentFeePaySttusMngtDao.deleteAssetPhoto_S");
	}

	public int updateCntnrQuayRentFeePaySttusMngtList() throws Exception {
		return update("gamCntnrQuayRentFeePaySttusMngtDao.updateCntnrQuayRentFeePaySttusMngtList", null);
	}

	/**
	 * 연체 등록
	 * @param map
	 * @throws Exception
	 */
	public void updateRevArrrgAnlrveBndeRcvdTp(Map map) throws Exception {
		this.update("gamCntnrQuayRentFeePaySttusMngtDao.updateRevArrrgAnlrveBndeRcvdTp", map);
	}
	
	
	
	
	/** change** */
	public List selectCntnrQuayRentFeePaySttusMngtDlyList(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtDlyList_D", searchVO);
    }
	
	
	/** change**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayRentFeePaySttusMngtDlyListTotCnt(GamCntnrQuayRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtDlyListTotCnt_S", searchVO);
    }
    
    /** change** */
    public EgovMap selectCntnrQuayRentFeePaySttusMngtDlyListSum(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtDlyListSum_D", searchVO);
    }
	
}
