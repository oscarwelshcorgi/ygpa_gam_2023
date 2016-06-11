/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngDefaultVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticInfoVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticIssueService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentRntfeeVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 3.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamHtldRentNticIssueService")
public class GamHtldRentNticIssueServiceImpl extends AbstractServiceImpl implements GamHtldRentNticIssueService {
	@Resource(name="gamHtldRentNticIssueDao")
	private GamHtldRentNticIssueDao gamHtldRentNticIssueDao;
	
	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 고지 정보 조회
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectNticIssueMasterl(GamHtldRentMngDefaultVO searchVO) throws Exception {
		return gamHtldRentNticIssueDao.selectNticIssueMaster(searchVO);
	}
	
	/**
	 * 고지 상세 목록 조회
	 * @param GamHtldRentMngDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectNticIssueDetailListl(GamHtldRentMngDefaultVO searchVO) throws Exception {
		return gamHtldRentNticIssueDao.selectNticIssueDetailList(searchVO);
	}

	/** 고지 실행
	 * @param nticInfo
	 * @param rntfeeList
	 * @param loginVO
	 */
	@SuppressWarnings("unchecked")
	public void execNticIssue(GamHtldRentNticInfoVO nticInfo, List<GamHtldRentRntfeeVO> rntfeeList, LoginVO loginVO) throws Exception {
		nticInfo.setDeptCd(loginVO.getDeptCd());
		nticInfo.setUserName(loginVO.getName());
		nticInfo.setFcltySe("1");
		nticInfo.setRegUsr(loginVO.getId());
		nticInfo.setUpdUsr(loginVO.getId());
		nticInfo.setRcivSe("0"); //고지(미수납) 상태로 설정 
		
		Map<String, String> summKey = (Map<String, String>) gamHtldRentNticIssueDao.selectHtldNticSummPk(nticInfo);
		nticInfo.setRntfeeNticNo(summKey.get("rntfeeNticNo"));
		nticInfo.setAccnutYear(summKey.get("accnutYear"));
		if("I".equals(summKey.get("summUpdtId"))) {
			gamHtldRentNticIssueDao.insertHtldNticSumm(nticInfo);
		} else {
			gamHtldRentNticIssueDao.updateHtldNticSumm(nticInfo);
		}
		nticInfo.setNticSeq(gamHtldRentNticIssueDao.selectHtldNticDtlsNextNticSeq(nticInfo));
		nticInfo.setNticCnt(gamHtldRentNticIssueDao.selectLevReqestNextNticCnt(nticInfo));
		nticInfo.setNticNo(gamHtldRentNticIssueDao.selectRevCollNextNticNo(nticInfo));
		gamHtldRentNticIssueDao.insertHtldNticDtls(nticInfo);
		for(GamHtldRentRntfeeVO item : rntfeeList) {
			item.setUpdUsr(nticInfo.getUpdUsr());
			item.setAccnutYear(nticInfo.getAccnutYear());
			item.setRntfeeNticNo(nticInfo.getRntfeeNticNo());
			item.setNticSeq(nticInfo.getNticSeq());
			item.setRcivSe(nticInfo.getRcivSe());
			item.setPayTmlmt(nticInfo.getPayTmlmt());
			gamHtldRentNticIssueDao.updateHtldRntfee(item);
		}
		gamHtldRentNticIssueDao.insertLevReqest(nticInfo);
		gamHtldRentNticIssueDao.insertRevColl(nticInfo);
	}
}
