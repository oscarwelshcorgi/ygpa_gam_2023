/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageHistInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageHistInqireVO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUseUnuseSttusInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUseUnuseSttusInqireVO;

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
@Service("gamFcltyUseUnuseSttusInqireService")
public class GamFcltyUseUnuseSttusInqireServiceImpl implements GamFcltyUseUnuseSttusInqireService {

	@Resource(name="gamFcltyUseUnuseSttusInqireDao")
	private GamFcltyUseUnuseSttusInqireDao gamFcltyUseUnuseSttusInqireDao;

	@Override
	public List selectFcltyUseUnuseSttusInqireList(GamFcltyUseUnuseSttusInqireVO searchVO) throws Exception{
		return (List)gamFcltyUseUnuseSttusInqireDao.selectFcltyUseUnuseSttusInqireList(searchVO);
	}

//	public int selectFcltyUseUnuseSttusInqireListTotCnt(GamFcltyUseUnuseSttusInqireVO searchVO) throws Exception{
//		return gamFcltyUseUnuseSttusInqireDao.selectFcltyUseUnuseSttusInqireListTotCnt(searchVO);
//	}

}
