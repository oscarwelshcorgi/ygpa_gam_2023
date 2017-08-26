/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.ocr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.ocr.service.GamOcrRecvInquireService;
import egovframework.rte.ygpa.gam.oper.ocr.service.GamOcrRecvInquireVO;

/**
 * 
 * @author Jongmin
 * @since 2017. 8. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2017. 8. 25.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamOcrRecvInquireService")
public class GamOcrRecvInquireServiceImpl extends AbstractServiceImpl implements GamOcrRecvInquireService {
	@Resource(name="gamOcrRecvInquireDao")
	GamOcrRecvInquireDao gamOcrRecvInquireDao;
	
	public List<?> selectOcrRecvInquireList(GamOcrRecvInquireVO searchVO) throws Exception {
		return gamOcrRecvInquireDao.selectOcrRecvInquireList(searchVO);
	}

	public int selectOcrRecvInquireListCnt(GamOcrRecvInquireVO searchVO) throws Exception {
		return gamOcrRecvInquireDao.selectOcrRecvInquireListCnt(searchVO);
	}

}
