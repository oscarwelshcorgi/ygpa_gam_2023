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
	 * 공컨장치장임대납부현황관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대납부현황관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrRentFeePaySttusMngtList(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtList_D", searchVO);
    }

    /**
	 * 공컨장치장임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrRentFeePaySttusMngtListTotCnt(GamCmmnCntrRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대납부현황관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrRentFeePaySttusMngtVO selectCmmnCntrRentFeePaySttusSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamCmmnCntrRentFeePaySttusMngtVO) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusSum_S", searchVO);
	}






	public EgovMap selectCmmnCntrRentFeePaySttusMngtDetailMstPk(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtDetailMaster_D", searchVO);
    }

    public List selectCmmnCntrRentFeePaySttusMngtDetailList(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtDetailList_D", searchVO);
    }

    public EgovMap selectCmmnCntrRentFeePaySttusMngtDetailSumPk(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtDetailSum_D", searchVO);
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
	public GamCmmnCntrRentFeePaySttusMngtVO selectCmmnCntrRentFeePaySttusMngtSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamCmmnCntrRentFeePaySttusMngtVO) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtSum_S", searchVO);
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

	/**
	 * 연체 등록
	 * @param map
	 * @throws Exception
	 */
	public void updateRevArrrgAnlrveBndeRcvdTp(Map map) throws Exception {
		this.update("gamCmmnCntrRentFeePaySttusMngtDao.updateRevArrrgAnlrveBndeRcvdTp", map);
	}




	/** change** */
	public List selectCmmnCntrRentFeePaySttusMngtDlyList(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtDlyList_D", searchVO);
    }


	/** change**
	 * 공컨장치장임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrRentFeePaySttusMngtDlyListTotCnt(GamCmmnCntrRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtDlyListTotCnt_S", searchVO);
    }

    /** change** */
    public EgovMap selectCmmnCntrRentFeePaySttusMngtDlyListSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtDlyListSum_D", searchVO);
    }

    public EgovMap selectCmmnCntrRentFeePaySttusMngtDlyInfo(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtDlyInfo_S", searchVO);
    }

	/**
	 * 연체금만 있는 고지서를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public EgovMap selectArrrgNpticPrintInfo(Map searchVO) throws Exception {
        return (EgovMap) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo_S", searchVO);
	}

	/**
	 * 연체금만 있는 고지서를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List selectArrrgNpticPrintInfo2(Map searchVO) throws Exception {
        return list("gamCmmnCntrRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo2_D", searchVO);
	}
	
	public EgovMap selectFeePayPopup(GamCmmnCntrRentFeePaySttusMngtVO vo) throws Exception {
        return (EgovMap) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectFeePayPopup_S", vo);
	}

	public void updateLevReqestRcivSe(GamCmmnCntrRentFeePaySttusMngtVO vo) throws Exception {
		this.update("gamCmmnCntrRentFeePaySttusMngtDao.updateLevReqestRcivSe_S", vo);
	}

	public void updateRevCollRcvdTp(Map vo) throws Exception {
		this.update("gamCmmnCntrRentFeePaySttusMngtDao.updateRevCollRcvdTp_S", vo);
	}

	public EgovMap selectCheckOcrResult(GamCmmnCntrRentFeePaySttusMngtVO vo) throws Exception {
        return (EgovMap) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectCheckOcrResult_S", vo);
	}
}
