/**
 *
 */
package egovframework.rte.ygpa.gam.code.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author 김종민
 * @since 2014. 12. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 10.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltsMngGroupMngService {

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFcltsMngGroupMngList(GamFcltsMngGroupMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	GamFcltsMngGroupMngVo selectFcltsMngGroupMngListTotCnt(GamFcltsMngGroupMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	EgovMap selectFcltsMngGroupMngPk(GamFcltsMngGroupMngVo searchVO) throws Exception;

	/**
	 * @param gamFcltsMngGroupMngVo
	 * @return
	 */
	String selectFcltsMngGroupMngMaxGroupNo(GamFcltsMngGroupMngVo gamFcltsMngGroupMngVo) throws Exception;

	/**
	 * @param gamFcltsMngGroupMngVo
	 */
	void insertFcltsMngGroupMng(GamFcltsMngGroupMngVo gamFcltsMngGroupMngVo) throws Exception;

	/**
	 * @param gamFcltsMngGroupMngVo
	 */
	void updateFcltsMngGroupMng(GamFcltsMngGroupMngVo gamFcltsMngGroupMngVo) throws Exception;

	/**
	 * @param gamFcltsMngGroupMngVo
	 */
	void deleteFcltsMngGroupMng(GamFcltsMngGroupMngVo gamFcltsMngGroupMngVo) throws Exception;

}
