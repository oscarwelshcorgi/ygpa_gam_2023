/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
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
	public List selectCivilFcltySpecMngList(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return list("gamCivilFcltySpecMngDao.selectCivilFcltySpecMngList", searchVO);
	}
	
	/**
	 * 토목시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectCivilFcltySpecMngListTotCnt(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamCivilFcltySpecMngDao.selectCivilFcltySpecMngListTotCnt", searchVO);
	}

	
	/**
	 * 토목시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	public EgovMap selectCivilFcltySpecMngDetail(Map searchVO) throws Exception {
		return (EgovMap)selectByPk("gamCivilFcltySpecMngDao.selectCivilFcltySpecMngDetail", searchVO);
	}
	
	/**
	 * 토목시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertCivilFcltySpecMngDetail(Map<String, Object> vo) throws Exception {
		insert("gamCivilFcltySpecMngDao.insertCivilFcltySpecMngDetail", vo);
	}
	
	/**
	 * 토목시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateCivilFcltySpecMngDetail(Map<String, Object> vo) throws Exception {
		update("gamCivilFcltySpecMngDao.updateCivilFcltySpecMngDetail", vo);
	}
	
	/**
	 * 토목시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteCivilFcltySpecMngDetail(Map<String, Object> vo) throws Exception {
		delete("gamCivilFcltySpecMngDao.deleteCivilFcltySpecMngDetail", vo);
	}	
}
