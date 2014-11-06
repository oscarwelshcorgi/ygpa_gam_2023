package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamFcltyRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtVO;

/**
 * @Class Name : GamPrtFcltyRentFeePaySttusMngtDao.java
 * @Description : 항만시설납부현황관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPrtFcltyRentFeePaySttusMngtDao")
public class GamPrtFcltyRentFeePaySttusMngtDao extends YGPAAbstractDAO {

	/**
	 * 항만시설납부현황관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentFeePaySttusMngtList(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtList_D", searchVO);
    }

    /**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentFeePaySttusMngtListTotCnt(GamPrtFcltyRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyRentFeePaySttusMngtVO selectPrtFcltyRentFeePaySttusSum(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentFeePaySttusMngtVO) selectByPk("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusSum_S", searchVO);
	}






	public EgovMap selectPrtFcltyRentFeePaySttusMngtDetailMstPk(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDetailMaster_D", searchVO);
    }

    public List selectPrtFcltyRentFeePaySttusMngtDetailList(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDetailList_D", searchVO);
    }

    public EgovMap selectPrtFcltyRentFeePaySttusMngtDetailSumPk(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDetailSum_D", searchVO);
    }



    public EgovMap selectNticArrrgDetail(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamPrtFcltyRentFeePaySttusMngtDao.selectNticArrrgDetail_S", searchVO);
    }


	/**
	 * 고지금액합계, 수납금액합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
	public GamPrtFcltyRentFeePaySttusMngtVO selectPrtFcltyRentFeePaySttusMngtSum(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentFeePaySttusMngtVO) selectByPk("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtSum_S", searchVO);
	}

	public List<?> selectNticArrrgList(GamFcltyRentArrrgMngtVO searchVO)
			throws Exception {
        return list("gamPrtFcltyRentFeePaySttusMngtDao.selectNticArrrgList_D", searchVO);
	}

	public int selectNticArrrgListTotCnt(GamFcltyRentArrrgMngtVO searchVO)
			throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentFeePaySttusMngtDao.selectNticArrrgListTotCnt_S", searchVO);
	}

	public List mergeNticArrrgList(Map mergeMap) throws Exception {
		return this.merge(mergeMap, "gamPrtFcltyRentFeePaySttusMngtDao.insertAssetPhoto_S", "gamPrtFcltyRentFeePaySttusMngtDao.updateAssetPhoto_S", "gamPrtFcltyRentFeePaySttusMngtDao.deleteAssetPhoto_S");
	}

	public int updatePrtFcltyRentFeePaySttusMngtList() throws Exception {
		return update("gamPrtFcltyRentFeePaySttusMngtDao.updatePrtFcltyRentFeePaySttusMngtList", null);
	}

	/**
	 * 연체 등록
	 * @param map
	 * @throws Exception
	 */
	public void updateRevArrrgAnlrveBndeRcvdTp(Map map) throws Exception {
		this.update("gamPrtFcltyRentFeePaySttusMngtDao.updateRevArrrgAnlrveBndeRcvdTp", map);
	}




	/** change** */
	public List selectPrtFcltyRentFeePaySttusMngtDlyList(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyList_D", searchVO);
    }


	/** change**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentFeePaySttusMngtDlyListTotCnt(GamPrtFcltyRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyListTotCnt_S", searchVO);
    }

    /** change** */
    public EgovMap selectPrtFcltyRentFeePaySttusMngtDlyListSum(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyListSum_D", searchVO);
    }

    public EgovMap selectPrtFcltyRentFeePaySttusMngtDlyInfo(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyInfo_S", searchVO);
    }


}
