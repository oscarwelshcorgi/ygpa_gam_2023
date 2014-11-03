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
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtSttusInqireService;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtSttusInqireVO;

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

@Service("gamFcltyCtrtSttusInqireService")
public class GamFcltyCtrtSttusInqireServiceImpl  extends AbstractServiceImpl implements GamFcltyCtrtSttusInqireService {

	@Resource(name="gamFcltyCtrtSttusInqireDao")
    private GamFcltyCtrtSttusInqireDao gamFcltyCtrtSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());
	
	
	/**
	 * 계약이력목록 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이력목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtSttusInqireList(GamFcltyCtrtSttusInqireVO searchVO) throws Exception {
		return gamFcltyCtrtSttusInqireDao.selectFcltyCtrtSttusInqireList(searchVO);
	}
	
	
	/**
	 * 계약이력목록 총갯수 및 금액합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이력목록 총갯수 및 금액합계
	 * @exception Exception
	 */
	public GamFcltyCtrtSttusInqireVO selectFcltyCtrtSttusInqireInfoSum(GamFcltyCtrtSttusInqireVO searchVO) throws Exception {
		return gamFcltyCtrtSttusInqireDao.selectFcltyCtrtSttusInqireInfoSum(searchVO);
	}
	

}
