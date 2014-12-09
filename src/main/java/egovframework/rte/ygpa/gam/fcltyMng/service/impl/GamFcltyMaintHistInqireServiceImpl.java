/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintHistInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintHistInqireVO;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 9.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltyMaintHistInqireService")
public class GamFcltyMaintHistInqireServiceImpl extends AbstractServiceImpl implements GamFcltyMaintHistInqireService {
	
	@Resource(name="gamFcltyMaintHistInqireDao")
	private GamFcltyMaintHistInqireDao gamFcltyMaintHistInqireDao;

	/**
	 * 유지보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyMaintHistInqireList(GamFcltyMaintHistInqireVO vo) throws Exception {
		return (List)gamFcltyMaintHistInqireDao.selectFcltyMaintHistInqireList(vo);
	}

	
	/**
	 * 유지보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyMaintHistInqireListTotCnt(GamFcltyMaintHistInqireVO vo) throws Exception {
		return gamFcltyMaintHistInqireDao.selectFcltyMaintHistInqireListTotCnt(vo);
	}
	
}
