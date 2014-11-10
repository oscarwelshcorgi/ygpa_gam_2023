/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
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
@Repository("gamCivilFcltySpecMngDao")
public class GamCivilFcltySpecMngDao extends YGPAAbstractDAO {
	/**
	 * 토목시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	public List selectCivilFcltySpecMngList(GamCivilFcltySpecMngVO searchVO) {
		return list("gamCivilFcltySpecMngDao.selectCivilFcltySpecMngList", searchVO);
	}
	
	/**
	 * 토목시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectCivilFcltySpecMngListTotCnt(GamCivilFcltySpecMngVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamCivilFcltySpecMngDao.selectCivilFcltySpecMngListTotCnt", searchVO);
	}
}
