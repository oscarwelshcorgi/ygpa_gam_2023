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

@Repository("gamNticRequestMngtDAO")
public class GamNticRequestMngtDAO extends YGPAAbstractDAO {

	/**
	 * 고지 정보를 한건 불러온다.
	 */
	public EgovMap selectLevRequestByPk(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamNticRequestMngtDAO.selectLevRequestByPk_S", vo);
    }

	/**
	 * 고지정보를 불러온다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectLevRequestList(Map<String, Object> vo) throws Exception {
        return (List) list("gamNticRequestMngtDAO.selectLevRequestList_D", vo);
    }

	/**
	 * 고지 정보를 전송한다.
	 */
	public String insertNticRequestRevCollF(Map<String, Object> vo) throws Exception {
        return (String)insert("gamNticRequestMngtDAO.insertNticRequestRevCollF", vo);
    }

	/**
	 * 고지 취소 정보를 전송한다.
	 */
	public String insertCancelNticRequestRevCollF(Map<String, Object> vo) throws Exception {
        return (String)insert("gamNticRequestMngtDAO.insertCancelNticRequestRevCollF", vo);
    }

	/**
	 * 고지서 발부여부를 저장한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updateLevReqestIssueYn(Map<String, Object> vo) throws Exception {
        update("gamNticRequestMngtDAO.updateLevReqestIssueYn", vo);
	}

	/**
	 * 고지서 출력여부를 저장한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updateLevReqestNhtPrintYn(Map<String, Object> vo) throws Exception {
        update("gamNticRequestMngtDAO.updateLevReqestNhtPrintYn", vo);
	}

	/**
	 * 징수의뢰 기간별 통계정보를 저장한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String insertLevReqestPdByStats(Map<String, Object> vo) throws Exception {
        return (String)insert("gamNticRequestMngtDAO.insertLevReqestPdByStats", vo);
    }

	/**
	 * 징수의뢰 기간별 통계정보를 삭제한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteLevReqestPdByStats(Map<String, Object> vo) throws Exception {
        return delete("gamNticRequestMngtDAO.deleteLevReqestPdByStats", vo);
    }

}