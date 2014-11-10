/**
 * 
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 7.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamGisPrtFcltyCdMngtDao")
public class GamGisPrtFcltyCdMngtDao extends YGPAAbstractDAO {
	
	public void insertGisPrtFclty(Map<String, Object> vo) throws Exception {
		insert("gamGisPrtFcltyCdMngtDao.insertGisPrtFclty", vo);
	}

	public void updateGisPrtFclty(Map<String, Object> vo) throws Exception {
		update("gamGisPrtFcltyCdMngtDao.updateGisPrtFclty", vo);
	}
	
	public void deleteGisPrtFclty(Map<String, Object> vo) throws Exception {
		delete("gamGisPrtFcltyCdMngtDao.deleteGisPrtFclty", vo);
	}
	
	public String selectNextFcltySeq(Map<String, Object> vo) throws Exception {
		return (String) getSqlMapClient().queryForObject("gamGisPrtFcltyCdMngtDao.selectNextFcltySeq", vo); 
	}
}
