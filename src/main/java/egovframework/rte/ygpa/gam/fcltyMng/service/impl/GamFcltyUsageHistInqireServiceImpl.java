/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageHistInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageHistInqireVO;

/**
 * 
 * @author LFIT
 * @since 2014. 12. 11.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 11.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamFcltyUsageHistInqireService")
public class GamFcltyUsageHistInqireServiceImpl implements GamFcltyUsageHistInqireService {

	@Resource(name="gamFcltyUsageHistInqireDao")
	private GamFcltyUsageHistInqireDao gamFcltyUsageHistInqireDao;
	
	@Override
	public List selectFcltyUsageHistInqireList(GamFcltyUsageHistInqireVO searchVO) throws Exception{
		return (List)gamFcltyUsageHistInqireDao.selectFcltyUsageHistInqireList(searchVO);
	}

	@Override
	public GamFcltyUsageHistInqireVO selectFcltyUsageHistInqireListTotCnt(GamFcltyUsageHistInqireVO searchVO) throws Exception{
		return gamFcltyUsageHistInqireDao.selectFcltyUsageHistInqireListTotCnt(searchVO);
	}

}
