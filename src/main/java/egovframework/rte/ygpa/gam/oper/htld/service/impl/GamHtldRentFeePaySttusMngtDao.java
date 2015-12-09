package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtVO;
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
	 * 고지금액합계, 수납금액합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
	public GamHtldRentFeePaySttusMngtVO selectHtldRentFeePaySttusMngtSum(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamHtldRentFeePaySttusMngtVO) selectByPk("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtSum_S", searchVO);
	}


    /**
     * 지로 수납된 자료인지 체크 한다.
     * @param searchVO
     * @return 납부유무자료
   	 * @exception Exception
     */
	public Map selectHtldCheckOcrResult(GamPrtFcltyRentFeePaySttusMngtVO searchVO) {
		return (EgovMap) selectByPk("gamHtldRentFeePaySttusMngtDao.selectHtldCheckOcrResult_S", searchVO);
	}

    /**
     * 수납처리를 하기 위한 LEV_REQUEST_F 수정
     * @param searchVO
     * @return 
   	 * @exception Exception
     */
	public void htldUpdateLevReqestRcivSe(GamPrtFcltyRentFeePaySttusMngtVO vo) {
		this.update("gamHtldRentFeePaySttusMngtDao.htldUpdateLevReqestRcivSe", vo);
	}
	
    /**
     * 수납처리를 하기 위한 REV_COLL_F 수정
     * @param map
     * @return 
   	 * @exception Exception
     */
	public void htldUpdateRevCollRcvdTp(Map<String, Object> map) {
		this.update("gamHtldRentFeePaySttusMngtDao.htldUpdateRevCollRcvdTp", map);
	}

    /**
     * 배후단지임대료납부상세 원고지 정보
     * @param searchVO
     * @return 원고지 정보
   	 * @exception Exception
     */
	public EgovMap selectHtldRentFeePaySttusMngtDetailMstPk(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDetailMaster_S", searchVO);
    }

    /**
     * 배후단지임대료납부상세 전체사용료 목록
     * @param searchVO
     * @return 전체사용료 목록
   	 * @exception Exception
     */
    public List selectHtldRentFeePaySttusMngtDetailList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDetailList_D", searchVO);
    }

    /**
     * 배후단지임대료납부상세 총고지금액, 총납부금액, 관리비, 연체료, 과태료
     * @param searchVO
     * @return 총고지금액, 총납부금액, 관리비, 연체료, 과태료 정보
   	 * @exception Exception
     */
    public EgovMap selectHtldRentFeePaySttusMngtDetailSumPk(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDetailSum_D", searchVO);
    }

    
    /**
     * 배후단지임대료납부상세 연체정보
     * @param searchVO
     * @return 해당고지의 연체정보
   	 * @exception Exception
     */
    public EgovMap selectNticArrrgDetail(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamHtldRentFeePaySttusMngtDao.selectNticArrrgDetail_S", searchVO);
    }

	/**
	 * 연체정보 등록 전 연체내역조회
	 * @param gamHtldRentFeeMngtVO
	 * @return
	 */
	public Map selectLevReqestUnpaidF(
			GamHtldRentArrrgMngtVO gamHtldRentFeeMngtVO) {
		return (EgovMap) selectByPk("gamHtldRentFeePaySttusMngtDao.selectLevReqestUnpaidF_S", gamHtldRentFeeMngtVO);
	}

	/**
	 * 연체고지 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String insertNticRequestUnpaidF(Map<String, Object> vo) throws Exception {
        return (String)insert("gamHtldRentFeePaySttusMngtDao.insertNticRequestUnpaidF_S", vo);
    }

	/**
	 * 연체고지 등록 후 원고지 정보 업데이트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String updateLevRequestUnpaidF(Map<String, Object> vo) throws Exception {
        return (String)insert("gamHtldRentFeePaySttusMngtDao.updateLevReqestArrrgAmt_S", vo);
    }
    
    /**
     * 배후단지임대료연체현황관리 연체목록
     * @param searchVO
     * @return 연체현황 목록
   	 * @exception Exception
     */
	public List selectHtldRentFeePaySttusMngtDlyList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDlyList_D", searchVO);
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

	/**
	 * @param gamHtldRentFeeMngtVO
	 * @return
	 */
	public Map selectArrrglevReqestPk(
			GamHtldRentFeePaySttusMngtVO gamHtldRentFeeMngtVO) {
		return (EgovMap) selectByPk("gamHtldRentFeePaySttusMngtDao.selectArrrglevReqestPk_S", gamHtldRentFeeMngtVO);
	}
	
	/**
	 * 연체고지 수정
	 * @param map
	 */
	public void updateNticRequestUnpaidF(Map map) {
        update("gamHtldRentFeePaySttusMngtDao.updateNticRequestUnpaidF_S", map);
	}


	/**
	 * @param searchVO
	 * @return
	 */
	public EgovMap selectHtldShowFeePayPopup(GamPrtFcltyRentFeePaySttusMngtVO searchVO) {
		return (EgovMap) selectByPk("gamHtldRentFeePaySttusMngtDao.selectHtldShowFeePayPopup_S", searchVO);
	}

}
