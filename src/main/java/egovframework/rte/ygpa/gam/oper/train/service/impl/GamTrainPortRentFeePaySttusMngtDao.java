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
	 * 철송장임대납부현황관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대납부현황관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentFeePaySttusMngtList(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtList_D", searchVO);
    }

    /**
	 * 철송장임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentFeePaySttusMngtListTotCnt(GamTrainPortRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대납부현황관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentFeePaySttusMngtVO selectTrainPortRentFeePaySttusSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamTrainPortRentFeePaySttusMngtVO) selectByPk("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusSum_S", searchVO);
	}






	public EgovMap selectTrainPortRentFeePaySttusMngtDetailMstPk(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtDetailMaster_D", searchVO);
    }

    public List selectTrainPortRentFeePaySttusMngtDetailList(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtDetailList_D", searchVO);
    }

    public EgovMap selectTrainPortRentFeePaySttusMngtDetailSumPk(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtDetailSum_D", searchVO);
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
	public GamTrainPortRentFeePaySttusMngtVO selectTrainPortRentFeePaySttusMngtSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamTrainPortRentFeePaySttusMngtVO) selectByPk("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtSum_S", searchVO);
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

	/**
	 * 연체 등록
	 * @param map
	 * @throws Exception
	 */
	public void updateRevArrrgAnlrveBndeRcvdTp(Map map) throws Exception {
		this.update("gamTrainPortRentFeePaySttusMngtDao.updateRevArrrgAnlrveBndeRcvdTp", map);
	}




	/** change** */
	public List selectTrainPortRentFeePaySttusMngtDlyList(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtDlyList_D", searchVO);
    }


	/** change**
	 * 철송장임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentFeePaySttusMngtDlyListTotCnt(GamTrainPortRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtDlyListTotCnt_S", searchVO);
    }

    /** change** */
    public EgovMap selectTrainPortRentFeePaySttusMngtDlyListSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtDlyListSum_D", searchVO);
    }

    public EgovMap selectTrainPortRentFeePaySttusMngtDlyInfo(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtDlyInfo_S", searchVO);
    }

	/**
	 * 연체금만 있는 고지서를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public EgovMap selectArrrgNpticPrintInfo(Map searchVO) throws Exception {
        return (EgovMap) selectByPk("gamTrainPortRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo_S", searchVO);
	}

	/**
	 * 연체금만 있는 고지서를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List selectArrrgNpticPrintInfo2(Map searchVO) throws Exception {
        return list("gamTrainPortRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo2_D", searchVO);
	}
	public EgovMap selectFeePayPopup(GamTrainPortRentFeePaySttusMngtVO vo) throws Exception {
        return (EgovMap) selectByPk("gamTrainPortRentFeePaySttusMngtDao.selectFeePayPopup_S", vo);
	}

	public void updateLevReqestRcivSe(GamTrainPortRentFeePaySttusMngtVO vo) throws Exception {
		this.update("gamTrainPortRentFeePaySttusMngtDao.updateLevReqestRcivSe_S", vo);
	}

	public void updateRevCollRcvdTp(Map vo) throws Exception {
		this.update("gamTrainPortRentFeePaySttusMngtDao.updateRevCollRcvdTp_S", vo);
	}

	public EgovMap selectCheckOcrResult(GamTrainPortRentFeePaySttusMngtVO vo) throws Exception {
        return (EgovMap) selectByPk("gamTrainPortRentFeePaySttusMngtDao.selectCheckOcrResult_S", vo);
	}
}
