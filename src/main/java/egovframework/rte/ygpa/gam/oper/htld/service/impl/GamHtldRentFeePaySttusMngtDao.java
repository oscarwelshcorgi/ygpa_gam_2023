package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeePaySttusMngtVO;

/**
 * @Class Name : GamHtldRentFeePaySttusMngtDao.java
 * @Description : 배후단지납부현황관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldRentFeePaySttusMngtDao")
public class GamHtldRentFeePaySttusMngtDao extends YGPAAbstractDAO {

	/**
	 * 배후단지임대납부현황관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대납부현황관리 목록
	 * @exception Exception
	 */
    public List selectHtldRentFeePaySttusMngtList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtList_D", searchVO);
    }

    /**
	 * 배후단지임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentFeePaySttusMngtListTotCnt(GamHtldRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대납부현황관리 목록
	 * @exception Exception
	 */
	public GamHtldRentFeePaySttusMngtVO selectHtldRentFeePaySttusSum(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamHtldRentFeePaySttusMngtVO) selectByPk("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusSum_S", searchVO);
	}





	public EgovMap selectHtldRentFeePaySttusMngtDetailMstPk(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDetailMaster_D", searchVO);
    }

    public List selectHtldRentFeePaySttusMngtDetailList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDetailList_D", searchVO);
    }

    public EgovMap selectHtldRentFeePaySttusMngtDetailSumPk(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDetailSum_D", searchVO);
    }



    public EgovMap selectNticArrrgDetail(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamHtldRentFeePaySttusMngtDao.selectNticArrrgDetail_S", searchVO);
    }


	/**
	 * 고지금액합계, 수납금액합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
	public GamHtldRentFeePaySttusMngtVO selectHtldRentFeePaySttusMngtSum(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamHtldRentFeePaySttusMngtVO) selectByPk("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtSum_S", searchVO);
	}

	public List<?> selectNticArrrgList(GamHtldRentArrrgMngtVO searchVO)
			throws Exception {
        return list("gamHtldRentFeePaySttusMngtDao.selectNticArrrgList_D", searchVO);
	}

	public int selectNticArrrgListTotCnt(GamHtldRentArrrgMngtVO searchVO)
			throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentFeePaySttusMngtDao.selectNticArrrgListTotCnt_S", searchVO);
	}

	public List mergeNticArrrgList(Map mergeMap) throws Exception {
		return this.merge(mergeMap, "gamHtldRentFeePaySttusMngtDao.insertAssetPhoto_S", "gamHtldRentFeePaySttusMngtDao.updateAssetPhoto_S", "gamHtldRentFeePaySttusMngtDao.deleteAssetPhoto_S");
	}

	/**
	 * 연체 등록
	 * @param map
	 * @throws Exception
	 */
	public void updateRevArrrgAnlrveBndeRcvdTp(Map map) throws Exception {
		this.update("gamHtldRentFeePaySttusMngtDao.updateRevArrrgAnlrveBndeRcvdTp", map);
	}




	/** change** */
	public List selectHtldRentFeePaySttusMngtDlyList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDlyList_D", searchVO);
    }


	/** change**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentFeePaySttusMngtDlyListTotCnt(GamHtldRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDlyListTotCnt_S", searchVO);
    }

    /** change** */
    public EgovMap selectHtldRentFeePaySttusMngtDlyListSum(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDlyListSum_D", searchVO);
    }

    public EgovMap selectPrtFcltyRentFeePaySttusMngtDlyInfo(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyInfo_S", searchVO);
    }

}
