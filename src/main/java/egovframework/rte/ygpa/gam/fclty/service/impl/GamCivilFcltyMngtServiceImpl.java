/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.mngt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fclty.mngt.service.GamCivilFcltyManageVO;
import egovframework.rte.ygpa.gam.fclty.mngt.service.GamCivilFcltyMngtService;

/**
 * 
 * @author Administrator
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		Administrator		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("civilFcltyMngtService")
public class GamCivilFcltyMngtServiceImpl extends AbstractServiceImpl implements GamCivilFcltyMngtService{

	protected Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="civilFcltyMngtDao")
    private GamCivilFcltyMngtDao civilFcltyMngtDao;
	
	/**
	 * 메뉴 정보를 등록
	 * @param vo GamCivilFcltyManageVO
	 * @exception Exception
	 */
	public void insertCivilFcltyManage(GamCivilFcltyManageVO vo) throws Exception {
		civilFcltyMngtDao.insertCivilFcltyManage(vo);
	}
	
	public List selectCivilFcltyMngtList(ComDefaultVO vo) throws Exception {
   		return civilFcltyMngtDao.selectCivilFcltyMngtList(vo);
	}
}