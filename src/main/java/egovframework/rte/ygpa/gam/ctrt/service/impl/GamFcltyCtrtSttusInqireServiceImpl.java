/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
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

	@Override
	public List selectFcltyCtrtSttusInqireList(GamFcltyCtrtSttusInqireVO searchVO) throws Exception {
		return gamFcltyCtrtSttusInqireDao.selectFcltyCtrtSttusInqireList(searchVO);
	}

	@Override
	public GamFcltyCtrtSttusInqireVO selectFcltyCtrtSttusInqireListSum(GamFcltyCtrtSttusInqireVO searchVO) throws Exception {
		return gamFcltyCtrtSttusInqireDao.selectFcltyCtrtSttusInqireListSum(searchVO);
	}

	@Override
	public EgovMap selectEntrpsInfo(Map searchVO) throws Exception {
		return gamFcltyCtrtSttusInqireDao.selectEntrpsInfo(searchVO);
	}

}
