/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.mngt.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;


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

public interface GamCivilFcltyMngtService {

	/**
	 * 메뉴 정보를 등록
	 * @param vo GamCivilFcltyManageVO
	 * @exception Exception
	 */
	void insertCivilFcltyManage(GamCivilFcltyManageVO vo) throws Exception;

	
	List selectCivilFcltyMngtList(ComDefaultVO vo) throws Exception;
}