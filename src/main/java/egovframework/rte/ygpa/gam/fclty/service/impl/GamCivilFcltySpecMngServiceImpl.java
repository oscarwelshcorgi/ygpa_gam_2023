/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltySpecMngVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 10.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamCivilFcltySpecMngService")
public class GamCivilFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamCivilFcltySpecMngService{
	@Resource(name="gamCivilFcltySpecMngDao")
	private GamCivilFcltySpecMngDao gamCivilFcltySpecMngDao;
	/**
	 * 토목시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	public List selectCivilFcltySpecMngList(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return gamCivilFcltySpecMngDao.selectCivilFcltySpecMngList(searchVO);
	}
	
	/**
	 * 토목시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectCivilFcltySpecMngListTotCnt(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return gamCivilFcltySpecMngDao.selectCivilFcltySpecMngListTotCnt(searchVO);
	}
}
