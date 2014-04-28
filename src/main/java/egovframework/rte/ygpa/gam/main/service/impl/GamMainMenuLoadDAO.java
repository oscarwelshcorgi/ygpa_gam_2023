/**
 *
 */
package egovframework.rte.ygpa.gam.main.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 12.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamMainMenuLoadDAO")
public class GamMainMenuLoadDAO extends YGPAAbstractDAO {

	/**
	 * 자산코드 정보 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List selectMenuList(String userId) throws Exception{
		return list("gamMainMenuLoadDAO.selectMenuList_D", userId);
	}

}
