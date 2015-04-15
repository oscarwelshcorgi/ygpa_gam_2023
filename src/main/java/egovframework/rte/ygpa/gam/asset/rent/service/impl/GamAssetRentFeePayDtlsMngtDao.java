package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO;


/**
 * @Class Name : GamAssetRentFeePayDtlsMngtDao.java
 * @Description : 자산임대료납부관리 DAO Class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetRentFeePayDtlsMngtDao")
public class GamAssetRentFeePayDtlsMngtDao extends YGPAAbstractDAO {

	/**
	 * 자산임대납부현황관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대납부현황관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentFeePayDtlsMngtList(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
        return list("gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtList_D", searchVO);
    }

    /**
	 * 자산임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentFeePayDtlsMngtListTotCnt(GamAssetRentFeePayDtlsMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대납부현황관리 목록
	 * @exception Exception
	 */
	public GamAssetRentFeePayDtlsMngtVO selectAssetRentFeePayDtlsSum(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
		return (GamAssetRentFeePayDtlsMngtVO) selectByPk("gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsSum_S", searchVO);
	}






	public EgovMap selectAssetRentFeePayDtlsMngtDetailMstPk(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtDetailMaster_D", searchVO);
    }

    public List selectAssetRentFeePayDtlsMngtDetailList(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
        return list("gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtDetailList_D", searchVO);
    }

    public EgovMap selectAssetRentFeePayDtlsMngtDetailSumPk(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtDetailSum_D", searchVO);
    }



    public EgovMap selectNticArrrgDetail(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamAssetRentFeePayDtlsMngtDao.selectNticArrrgDetail_S", searchVO);
    }


	/**
	 * 고지금액합계, 수납금액합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
	public GamAssetRentFeePayDtlsMngtVO selectAssetRentFeePayDtlsMngtSum(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
		return (GamAssetRentFeePayDtlsMngtVO) selectByPk("gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtSum_S", searchVO);
	}

	public List<?> selectNticArrrgList(GamAssetRentArrrgMngtVO searchVO)
			throws Exception {
        return list("gamAssetRentFeePayDtlsMngtDao.selectNticArrrgList_D", searchVO);
	}

	public int selectNticArrrgListTotCnt(GamAssetRentArrrgMngtVO searchVO)
			throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentFeePayDtlsMngtDao.selectNticArrrgListTotCnt_S", searchVO);
	}

	public List mergeNticArrrgList(Map mergeMap) throws Exception {
		return this.merge(mergeMap, "gamAssetRentFeePayDtlsMngtDao.insertAssetPhoto_S", "gamAssetRentFeePayDtlsMngtDao.updateAssetPhoto_S", "gamAssetRentFeePayDtlsMngtDao.deleteAssetPhoto_S");
	}

	/**
	 * 연체 등록
	 * @param map
	 * @throws Exception
	 */
	public void updateRevArrrgAnlrveBndeRcvdTp(Map map) throws Exception {
		this.update("gamAssetRentFeePayDtlsMngtDao.updateRevArrrgAnlrveBndeRcvdTp", map);
	}




	/** change** */
	public List selectAssetRentFeePayDtlsMngtDlyList(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
        return list("gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtDlyList_D", searchVO);
    }


	/** change**
	 * 자산임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentFeePayDtlsMngtDlyListTotCnt(GamAssetRentFeePayDtlsMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtDlyListTotCnt_S", searchVO);
    }

    /** change** */
    public EgovMap selectAssetRentFeePayDtlsMngtDlyListSum(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtDlyListSum_D", searchVO);
    }

    public EgovMap selectAssetRentFeePayDtlsMngtDlyInfo(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtDlyInfo_S", searchVO);
    }

	/**
	 * 연체금만 있는 고지서를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public EgovMap selectArrrgNpticPrintInfo(Map searchVO) throws Exception {
        return (EgovMap) selectByPk("gamAssetRentFeePayDtlsMngtDao.selectArrrgNpticPrintInfo_S", searchVO);
	}

	/**
	 * 연체금만 있는 고지서를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List selectArrrgNpticPrintInfo2(Map searchVO) throws Exception {
        return list("gamAssetRentFeePayDtlsMngtDao.selectArrrgNpticPrintInfo2_D", searchVO);
	}

	public EgovMap selectFeePayPopup(GamAssetRentFeePayDtlsMngtVO vo) throws Exception {
        return (EgovMap) selectByPk("gamAssetRentFeePayDtlsMngtDao.selectFeePayPopup_S", vo);
	}

	public void updateLevReqestRcivSe(GamAssetRentFeePayDtlsMngtVO vo) throws Exception {
		this.update("gamAssetRentFeePayDtlsMngtDao.updateLevReqestRcivSe_S", vo);
	}

	public void updateRevCollRcvdTp(Map vo) throws Exception {
		this.update("gamAssetRentFeePayDtlsMngtDao.updateRevCollRcvdTp_S", vo);
	}

	public EgovMap selectCheckOcrResult(GamAssetRentFeePayDtlsMngtVO vo) throws Exception {
        return (EgovMap) selectByPk("gamAssetRentFeePayDtlsMngtDao.selectCheckOcrResult_S", vo);
	}

}
