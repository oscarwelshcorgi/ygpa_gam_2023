/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 20.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 20.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamTestNticRequestMngtDAO")
public class GamTestNticRequestMngtDAO extends YGPAAbstractDAO {

	/**
	 * 고지 정보를 한건 불러온다.
	 */
	public EgovMap selectLevRequestByPk(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamTestNticRequestMngtDAO.selectLevRequestByPk_S", vo);
    }

	/**
	 * 회계년도와 고지번호를 조회한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public EgovMap selectNticNoAccnutYear(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamTestNticRequestMngtDAO.selectNticNoAccnutYear_S", vo);
    }


	public EgovMap selectReimNticNoAccnutYear(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamTestNticRequestMngtDAO.selectReimNticNoAccnutYear_S", vo);
    }
	/**
	 * 고지정보를 불러온다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectLevRequestList(Map<String, Object> vo) throws Exception {
        return (List) list("gamTestNticRequestMngtDAO.selectLevRequestList_D", vo);
    }

	/**
	 * 고지 정보를 전송한다.
	 */
	public String insertNticRequestRevCollF(Map<String, Object> vo) throws Exception {
        return (String)insert("gamTestNticRequestMngtDAO.insertNticRequestRevCollF", vo);
    }

	public String insertNticRequestInterestRevCollF(Map<String, Object> vo) throws Exception {
        return (String)insert("gamTestNticRequestMngtDAO.insertNticRequestInterestRevCollF", vo);
    }

	/**
	 * 연체 고지 정보를 전송한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String insertNticRequestRevCollFC1(Map<String, Object> vo) throws Exception {
        return (String)insert("gamTestNticRequestMngtDAO.insertNticRequestRevCollFC1", vo);
    }

	public void deleteNticRequestRevCollFC1(Map<String, Object> vo) throws Exception {
        delete("gamTestNticRequestMngtDAO.deleteNticRequestRevCollFC1", vo);
    }

	/**
	 * 통계 정보를 생성한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String insertLevReqestPlotByFee(Map<String, Object> vo) throws Exception {
        return (String)insert("gamTestNticRequestMngtDAO.insertLevReqestPlotByFee", vo);
    }

	/**
	 * 고지 취소 정보를 전송한다.
	 */
	public String insertCancelNticRequestRevCollF(Map<String, Object> vo) throws Exception {
        return (String)insert("gamTestNticRequestMngtDAO.insertCancelNticRequestRevCollF", vo);
    }

	/**
	 * 납부여부를 조회한다.
	 */
	public EgovMap selectNticRequestRcvdTp(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamTestNticRequestMngtDAO.selectNticRequestRcvdTp_S", vo);
	}

	/**
	 * 고지를 삭제 한다.
	 */
	public int deleteNticRequestRevCollF(Map<String, Object> vo) throws Exception {
        return delete("gamTestNticRequestMngtDAO.deleteNticRequestRevCollF", vo);
    }

	/**
	 * 연체금액을 조회 한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectLevReqestArrrgAmt(Map<String, Object> vo) throws Exception {
        return list("gamTestNticRequestMngtDAO.selectLevReqestArrrgAmt", vo);
	}

	public List selectUnpaidList(Map<String, Object> vo) throws Exception {
        return list("gamTestNticRequestMngtDAO.selectUnpaidList", vo);
	}

	public String selectUnpaidMaxNo(Map<String, Object> vo) throws Exception {
        return (String) selectByPk("gamTestNticRequestMngtDAO.selectUnpaidMaxNo", vo);
	}

	public String selectUnpaidMaxSerNo(Map<String, Object> vo) throws Exception {
        return (String) selectByPk("gamTestNticRequestMngtDAO.selectUnpaidMaxSerNo", vo);
	}

	/**
	 * 연체료를 등록한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updateLevReqestArrrgAmt(Map<String, Object> vo) throws Exception {
        update("gamTestNticRequestMngtDAO.updateLevReqestArrrgAmt", vo);
	}

	/**
	 * 연체내역을 삭제한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updateLevReqestUnarrrgAmt(Map<String, Object> vo) throws Exception {
        update("gamTestNticRequestMngtDAO.updateLevReqestUnarrrgAmt", vo);
	}

	/**
	 * 마감정보를 조회 한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public EgovMap selectPortmisMagamInfo(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamTestNticRequestMngtDAO.selectPortmisMagamInfo", vo);
	}


	public String selectLevReqestInterest(Map<String, Object> vo) throws Exception {
        return (String) selectByPk("gamTestNticRequestMngtDAO.selectLevReqestInterest", vo);
	}

	/**
	 * 고지서 발부여부를 저장한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updateLevReqestIssueYn(Map<String, Object> vo) throws Exception {
        update("gamTestNticRequestMngtDAO.updateLevReqestIssueYn", vo);
	}

	/**
	 * 고지서 출력여부를 저장한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updateLevReqestNhtPrintYn(Map<String, Object> vo) throws Exception {
        update("gamTestNticRequestMngtDAO.updateLevReqestNhtPrintYn", vo);
        // 고지를 한다.
	}

	public String insertEgiroPrint(Map<String, Object> vo) throws Exception {
        return (String)insert("gamTestNticRequestMngtDAO.insertEgiroPrint", vo);
	}

	public String insertEgiroPrint2(Map<String, Object> vo) throws Exception {
        return (String)insert("gamTestNticRequestMngtDAO.insertEgiroPrint2", vo);
	}

	public void updateEgiroPrintCancel(Map<String, Object> vo) throws Exception {
        update("gamTestNticRequestMngtDAO.updateEgiroPrintCancel", vo);
	}

	public EgovMap getWorkDtSysdateInfo(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamTestNticRequestMngtDAO.getWorkDtSysdateInfo", vo);
	}

	public EgovMap getWorkDtInfo(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamTestNticRequestMngtDAO.getWorkDtInfo", vo);
	}

	public EgovMap getEgiroAgentInfo(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamTestNticRequestMngtDAO.getEgiroAgentInfo", vo);
	}

	public EgovMap getEgiroFeeTpMap(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamTestNticRequestMngtDAO.getEgiroFeeTpMap", vo);
	}


	public EgovMap selectRevCollF(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamTestNticRequestMngtDAO.selectRevCollF", vo);
	}

	public EgovMap selectEgiroInfoByPk(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamTestNticRequestMngtDAO.selectEgiroInfoByPk", vo);
	}

	/**
	 * 포트미스에 고지서 출력 여부를 저장한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updateRevCollFBillPrintYn(Map<String, Object> vo) throws Exception {
        update("gamTestNticRequestMngtDAO.updateRevCollFBillPrintYn", vo);
	}

	/**
	 * 연체 고지서 출력 여부를 저장한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updateUnpaidFBillPrintYn(Map<String, Object> vo) throws Exception {
        update("gamTestNticRequestMngtDAO.updateUnpaidFBillPrintYn", vo);
	}
	/**
	 * 징수의뢰 기간별 통계정보를 저장한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String insertLevReqestPdByStats(Map<String, Object> vo) throws Exception {
        return (String)insert("gamTestNticRequestMngtDAO.insertLevReqestPdByStats", vo);
    }

	/**
	 * 징수의뢰 기간별 통계정보를 삭제한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteLevReqestPdByStats(Map<String, Object> vo) throws Exception {
        return delete("gamTestNticRequestMngtDAO.deleteLevReqestPdByStats", vo);
    }

	public EgovMap getSfMakeDigit(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamTestNticRequestMngtDAO.getSfMakeDigit", vo);
	}

	public EgovMap selectPortRevAnlrveLevMgtFeeIcheFCnt(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamTestNticRequestMngtDAO.selectPortRevAnlrveLevMgtFeeIcheFCnt", vo);
	}

	public void updateUnpaidPrintYn(Map<String, Object> vo) throws Exception {
        update("gamTestNticRequestMngtDAO.updateUnpaidPrintYn", vo);
	}

	public void deleteUnpaidByPk(Map<String, Object> vo) throws Exception {
		delete("gamTestNticRequestMngtDAO.deleteUnpaidByPk", vo);
	}

	public int updateAssetRentFeePayDtlsMngtList() throws Exception {
        return update("gamTestNticRequestMngtDAO.updateAssetRentFeePayDtlsMngtList", null);
	}

	public int updateAssetRentFeePayDtlsMngtArrrgList() throws Exception {
        return update("gamTestNticRequestMngtDAO.updateAssetRentFeePayDtlsMngtArrrgList", null);
	}

}
