/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.ocr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
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
@Repository("gamOcrRecvInquireDao")
public class GamOcrRecvInquireDao extends YGPAAbstractDAO {
	
	public List<?> selectOcrRecvInquireList(GamOcrRecvInquireVO searchVO) throws Exception {
		return (List<?>) list("gamOcrRecvInquireDao.selectOcrRecvInquireList_D", searchVO);
	}
	
	public int selectOcrRecvInquireListCnt(GamOcrRecvInquireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamOcrRecvInquireDao.selectOcrRecvInquireListCnt_S", searchVO);
	}
	
}
