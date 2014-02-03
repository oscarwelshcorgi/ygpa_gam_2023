/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.mngt.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.rte.ygpa.gam.fclty.mngt.service.GamCivilFcltyManageVO;

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

@Repository("civilFcltyMngtDao")
public class GamCivilFcltyMngtDao extends EgovComAbstractDAO{

	/**
	 * 메뉴목록 기본정보를 등록
	 * @param vo MenuManageVO 
	 * @exception Exception
	 */
	public void insertCivilFcltyManage(GamCivilFcltyManageVO vo){
		insert("gamCivilFcltyMngtDao.insertCivilFcltyManage", vo);
	}
	
	
	/**
	 * 메뉴목록을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List selectCivilFcltyMngtList(ComDefaultVO vo) throws Exception{
		return list("gamCivilFcltyMngtDao.selectCivilFcltyMngtList", vo);
	}
}
