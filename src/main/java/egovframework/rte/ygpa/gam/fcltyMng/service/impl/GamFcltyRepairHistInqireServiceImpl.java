/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairHistInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairHistInqireVO;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 01.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 01.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltyRepairHistInqireService")
public class GamFcltyRepairHistInqireServiceImpl extends AbstractServiceImpl implements GamFcltyRepairHistInqireService {
	
	@Resource(name="gamFcltyRepairHistInqireDao")
	private GamFcltyRepairHistInqireDao gamFcltyRepairHistInqireDao;

	/**
	 * 하자보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFcltyRepairHistInqireList(GamFcltyRepairHistInqireVO vo) throws Exception {
		return (List<?>)gamFcltyRepairHistInqireDao.selectFcltyRepairHistInqireList(vo);
	}

	
	/**
	 * 하자보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyRepairHistInqireListTotCnt(GamFcltyRepairHistInqireVO vo) throws Exception {
		return gamFcltyRepairHistInqireDao.selectFcltyRepairHistInqireListTotCnt(vo);
	}
	
	
	
	/**
	 * 하자보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	public EgovMap selectFcltyRepairHistInqireDetail(GamFcltyRepairHistInqireVO vo) throws Exception{
		return gamFcltyRepairHistInqireDao.selectFcltyRepairHistInqireDetail(vo);
	}
	
	

}
