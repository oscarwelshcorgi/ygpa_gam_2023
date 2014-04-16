/**
 *
 */
package egovframework.rte.ygpa.gam.maps.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.maps.service.GamMapsFcltyMngtService;

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

@Service("gamMapsFcltyMngtService")
public class GamMapsFcltyMngtServiceImpl extends AbstractServiceImpl implements GamMapsFcltyMngtService{

	@Resource(name="gamMapsFcltyMngtDao")
    private GamMapsFcltyMngtDao gamMapsFcltyMngtDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.maps.service.GamMapsFcltyMngtService#selectMapsFcltyMngtList(egovframework.com.cmm.ComDefaultVO)
	 */
	@Override
	public List selectMapsFcltyMngtList(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamMapsFcltyMngtDao.selectMapsFcltyMngtList(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.maps.service.GamMapsFcltyMngtService#selectMapsFcltyMngtInfo(java.util.Map)
	 */
	@Override
	public EgovMap selectMapsFcltyMngtInfo(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamMapsFcltyMngtDao.selectMapsFcltyMngtInfo(vo);
	}
}