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

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentArrrgNticInfoVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentArrrgNticIssueService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticDefaultVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 10.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamHtldRentArrrgNticIssueService")
public class GamHtldRentArrrgNticIssueServiceImpl extends AbstractServiceImpl implements GamHtldRentArrrgNticIssueService {
	@Resource(name="gamHtldRentArrrgNticIssueDao")
	private GamHtldRentArrrgNticIssueDao gamHtldRentArrrgNticIssueDao;
	
	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 고지 정보 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectNticIssueMasterl(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return gamHtldRentArrrgNticIssueDao.selectNticIssueMaster(searchVO);
	}
	
	/**
	 * 고지 상세 목록 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectNticIssueDetailListl(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return gamHtldRentArrrgNticIssueDao.selectNticIssueDetailList(searchVO);
	}

	/**
	 * 연체고지 정보 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectNticArrrgDetaill(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return gamHtldRentArrrgNticIssueDao.selectNticArrrgDetail(searchVO);
	}
	
	/**
	 * 연체고지 실행
	 * @param GamHtldRentArrrgNticInfoVO
	 * @return
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public void execArrrgNticIssue(GamHtldRentArrrgNticInfoVO arrrgVO) throws Exception {
		Map<String, Object> map = (Map<String, Object>) gamHtldRentArrrgNticIssueDao.selectArrrgInfo(arrrgVO);
		gamHtldRentArrrgNticIssueDao.insertArrrgInfo(map);
		gamHtldRentArrrgNticIssueDao.updateLevReqestNticInfo(map);
	}
}
