/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcHistInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcHistInqireVO;

/**
 * 
 * @author 김종민
 * @since 2014. 12. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 15.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamFcltyQcHistInqireService")
public class GamFcltyQcHistInqireServiceImpl extends AbstractServiceImpl implements GamFcltyQcHistInqireService{
	@Resource(name="gamFcltyQcHistInqireDao")
	GamFcltyQcHistInqireDao gamFcltyQcHistInqireDao;
	
	/**
	 * 점검이력 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcHistDtlsList(GamFcltyQcHistInqireVO searchVO) throws Exception {
		return gamFcltyQcHistInqireDao.selectQcHistDtlsList(searchVO);
	}
	
	/**
	 * 점검이력 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcHistDtlsListTotCnt(GamFcltyQcHistInqireVO searchVO) throws Exception {
		return gamFcltyQcHistInqireDao.selectQcHistDtlsListTotCnt(searchVO);
	}
	
	/**
	 * 점검이력 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */		
	public EgovMap selectQcHistDtlsDetail(Map<?, ?> searchVO) throws Exception {
		return gamFcltyQcHistInqireDao.selectQcHistDtlsDetail(searchVO);
	}
}
