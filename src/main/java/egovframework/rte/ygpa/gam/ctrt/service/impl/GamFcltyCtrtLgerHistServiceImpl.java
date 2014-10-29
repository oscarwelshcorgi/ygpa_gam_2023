/**
 * 
 */
package egovframework.rte.ygpa.gam.ctrt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtLgerHistService;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtLgerHistVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltyCtrtLgerHistService")
public class GamFcltyCtrtLgerHistServiceImpl  extends AbstractServiceImpl implements GamFcltyCtrtLgerHistService {

	@Resource(name="gamFcltyCtrtLgerHistDao")
    private GamFcltyCtrtLgerHistDao gamFcltyCtrtLgerHistDao;

	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 계약대장목록 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
	public List selectFcltyCtrtLgerHistList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistList(searchVO);
	}


	/**
	 * 계약대장목록,  합계금액 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대장목록
	 * @exception Exception
	 */
	public GamFcltyCtrtLgerHistVO selectFcltyCtrtLgerHistInfoSum(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistInfoSum(searchVO);
	}

}
