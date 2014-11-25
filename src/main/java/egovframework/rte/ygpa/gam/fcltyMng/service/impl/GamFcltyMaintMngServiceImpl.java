/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintMngService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintMngVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 25.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltyMaintMngService")
public class GamFcltyMaintMngServiceImpl extends AbstractServiceImpl implements GamFcltyMaintMngService {
	
	@Resource(name="gamFcltyMaintMngDao")
	private GamFcltyMaintMngDao gamFcltyMaintMngDao;

	/**
	 * 유지보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyMaintMngList(GamFcltyMaintMngVO vo) throws Exception {
		return (List)gamFcltyMaintMngDao.selectFcltyMaintMngList(vo);
	}

	
	/**
	 * 유지보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyMaintMngListTotCnt(GamFcltyMaintMngVO vo) throws Exception {
		return gamFcltyMaintMngDao.selectFcltyMaintMngListTotCnt(vo);
	}

}
