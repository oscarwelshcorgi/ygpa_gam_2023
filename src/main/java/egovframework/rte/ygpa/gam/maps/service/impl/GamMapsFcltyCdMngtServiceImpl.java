/**
 *
 */
package egovframework.rte.ygpa.gam.maps.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.maps.service.GamMapsAssetCodeMngtService;
import egovframework.rte.ygpa.gam.maps.service.GamMapsFcltyCdMngtService;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 31.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamMapsFcltyCdMngtService")
public class GamMapsFcltyCdMngtServiceImpl extends AbstractServiceImpl
		implements GamMapsFcltyCdMngtService {

	@Resource(name="gamMapsFcltyCdMngtDAO")
	  private GamMapsFcltyCdMngtDAO gamMapsFcltyCdMngtDAO;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.maps.service.GamMapsFcltyCdMngtService#selectFcltyCdInfo(java.util.Map)
	 */
	@Override
	public EgovMap selectArchFcltyInfo(Map vo) throws Exception {
		return gamMapsFcltyCdMngtDAO.selectMapsArchFcltyCdInfo(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.maps.service.GamMapsFcltyCdMngtService#selectMapsArchFcltyFileList(java.util.Map)
	 */
	@Override
	public List selectMapsArchFcltyFileList(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamMapsFcltyCdMngtDAO.selectMapsArchFcltyFileList(vo);
	}

}
