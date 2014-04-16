/**
 *
 */
package egovframework.rte.ygpa.gam.maps.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyManageVO;

/**
 *
 * @author kok
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamMapsFcltyMngtDao")
public class GamMapsFcltyMngtDao extends YGPAAbstractDAO{

	/**
	 * 시설 목록 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List selectMapsFcltyMngtList(Map vo) throws Exception{
		return list("gamMapsFcltyMngtDao.selectMapsFcltyMngtList", vo);
	}

	/**
	 * 시설 정보 상세 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public EgovMap selectMapsFcltyMngtInfo(Map vo) throws Exception{
		return (EgovMap) selectByPk("gamMapsFcltyMngtDao.selectMapsFcltyMngtInfo", vo);
	}

}