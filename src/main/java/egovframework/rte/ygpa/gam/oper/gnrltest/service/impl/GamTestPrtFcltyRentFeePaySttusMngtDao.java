package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestFcltyRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeePaySttusMngtVO;

/**
 * @Class Name : GamTestPrtFcltyRentFeePaySttusMngtDao.java
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
@Repository("gamTestPrtFcltyRentFeePaySttusMngtDao")
public class GamTestPrtFcltyRentFeePaySttusMngtDao extends YGPAAbstractDAO {

	/**
	 * 항만시설납부현황관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentFeePaySttusMngtList(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtList_D", searchVO);
    }

    /**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentFeePaySttusMngtListTotCnt(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyRentFeePaySttusMngtVO selectPrtFcltyRentFeePaySttusSum(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamTestPrtFcltyRentFeePaySttusMngtVO) selectByPk("gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusSum_S", searchVO);
	}






	public EgovMap selectPrtFcltyRentFeePaySttusMngtDetailMstPk(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDetailMaster_D", searchVO);
    }

    public List selectPrtFcltyRentFeePaySttusMngtDetailList(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDetailList_D", searchVO);
    }

    public EgovMap selectPrtFcltyRentFeePaySttusMngtDetailSumPk(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDetailSum_D", searchVO);
    }



    public EgovMap selectNticArrrgDetail(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamTestPrtFcltyRentFeePaySttusMngtDao.selectNticArrrgDetail_S", searchVO);
    }


	/**
	 * 고지금액합계, 수납금액합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
	public GamTestPrtFcltyRentFeePaySttusMngtVO selectPrtFcltyRentFeePaySttusMngtSum(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamTestPrtFcltyRentFeePaySttusMngtVO) selectByPk("gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtSum_S", searchVO);
	}

	public List<?> selectNticArrrgList(GamTestFcltyRentArrrgMngtVO searchVO)
			throws Exception {
        return list("gamTestPrtFcltyRentFeePaySttusMngtDao.selectNticArrrgList_D", searchVO);
	}

	public int selectNticArrrgListTotCnt(GamTestFcltyRentArrrgMngtVO searchVO)
			throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyRentFeePaySttusMngtDao.selectNticArrrgListTotCnt_S", searchVO);
	}

	public List mergeNticArrrgList(Map mergeMap) throws Exception {
		return this.merge(mergeMap, "gamTestPrtFcltyRentFeePaySttusMngtDao.insertAssetPhoto_S", "gamTestPrtFcltyRentFeePaySttusMngtDao.updateAssetPhoto_S", "gamTestPrtFcltyRentFeePaySttusMngtDao.deleteAssetPhoto_S");
	}

	/**
	 * 연체 등록
	 * @param map
	 * @throws Exception
	 */
	public void updateRevArrrgAnlrveBndeRcvdTp(Map map) throws Exception {
		this.update("gamTestPrtFcltyRentFeePaySttusMngtDao.updateRevArrrgAnlrveBndeRcvdTp", map);
	}




	/** change** */
	public List selectPrtFcltyRentFeePaySttusMngtDlyList(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyList_D", searchVO);
    }


	/** change**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentFeePaySttusMngtDlyListTotCnt(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyListTotCnt_S", searchVO);
    }

    /** change** */
    public EgovMap selectPrtFcltyRentFeePaySttusMngtDlyListSum(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyListSum_D", searchVO);
    }

    public EgovMap selectPrtFcltyRentFeePaySttusMngtDlyInfo(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyInfo_S", searchVO);
    }

	/**
	 * 연체금만 있는 고지서를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List selectArrrgNpticPrintInfo(Map searchVO) throws Exception {
        return list("gamTestPrtFcltyRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo_S", searchVO);
        //return (EgovMap) selectByPk("gamTestPrtFcltyRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo_S", searchVO);
	}

	/**
	 * 연체금만 있는 고지서를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List selectArrrgNpticPrintInfo2(Map searchVO) throws Exception {
        return list("gamTestPrtFcltyRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo2_D", searchVO);
	}

	public EgovMap selectFeePayPopup(GamTestPrtFcltyRentFeePaySttusMngtVO vo) throws Exception {
        return (EgovMap) selectByPk("gamTestPrtFcltyRentFeePaySttusMngtDao.selectFeePayPopup_S", vo);
	}

	public void updateLevReqestRcivSe(GamTestPrtFcltyRentFeePaySttusMngtVO vo) throws Exception {
		this.update("gamTestPrtFcltyRentFeePaySttusMngtDao.updateLevReqestRcivSe_S", vo);
	}

	public void updateRevCollRcvdTp(Map vo) throws Exception {
		this.update("gamTestPrtFcltyRentFeePaySttusMngtDao.updateRevCollRcvdTp_S", vo);
	}

	public EgovMap selectCheckOcrResult(GamTestPrtFcltyRentFeePaySttusMngtVO vo) throws Exception {
        return (EgovMap) selectByPk("gamTestPrtFcltyRentFeePaySttusMngtDao.selectCheckOcrResult_S", vo);
	}

}
