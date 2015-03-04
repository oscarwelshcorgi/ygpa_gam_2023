/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateDefaultVO;
import egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateMngtService;
import egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateVO;
import egovframework.rte.ygpa.gam.code.service.GamFcltsClCdMngService;
import egovframework.rte.ygpa.gam.code.service.GamFcltsClCdMngVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtVO;

/**
 *
 * @author eunsungj
 * @since 2015. 3. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 3.		eunsungj 최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamCofixIntrrateMngtService")
public class GamCofixIntrrateMngtServiceImpl extends AbstractServiceImpl implements GamCofixIntrrateMngtService {

	@Resource(name="gamCofixIntrrateMngtDao")
	private GamCofixIntrrateMngtDao gamCofixIntrrateMngtDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateMngtService#selectCofixIntrrateList(egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateVO)
	 */
	@Override
	public List selectCofixIntrrateList(GamCofixIntrrateDefaultVO vo) throws Exception {
		return gamCofixIntrrateMngtDao.selectCofixIntrrateList(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateMngtService#mergeCofixIntrrate(java.util.List, java.util.List)
	 */
	@Override
	public void mergeCofixIntrrate(List<GamCofixIntrrateVO> cList, List<GamCofixIntrrateVO> uList, List<GamCofixIntrrateVO> dList) throws Exception {
		GamCofixIntrrateVO vo=null;
		Map map;
		for(int i=0; i<dList.size(); i++) {
			vo = dList.get(i);
			gamCofixIntrrateMngtDao.deleteCofixIntrrate(vo);
		}
		for(int i=0; i<uList.size(); i++) {
			vo = uList.get(i);
			gamCofixIntrrateMngtDao.updateCofixIntrrate(vo);
		}
		for(int i=0; i<cList.size(); i++) {
			vo = cList.get(i);
			gamCofixIntrrateMngtDao.insertCofixIntrrate(vo);
		}
	}

}
