/**
 *
 */
package egovframework.rte.ygpa.gam.code.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 22.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamQcItemCdMngService {

	/**
	 * @param searchVO
	 * @return
	 */
	List selectQcItemCdMngList(GamQcItemCdMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	GamQcItemCdMngVo selectQcItemCdMngListTotCnt(GamQcItemCdMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	EgovMap selectQcItemCdMngPk(GamQcItemCdMngVo searchVO) throws Exception;

	/**
	 * @param gamQcItemCdMngVo
	 * @return
	 */
	List selectQcItemCdMngTreeList(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception;

	/**
	 * @param gamQcItemCdMngVo
	 * @return
	 */
	String selectQcItemCdMngNewQcItemCd(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception;

	/**
	 * @param gamQcItemCdMngVo
	 */
	void insertQcItemCdMng(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception;

	/**
	 * @param gamQcItemCdMngVo
	 */
	void updateQcItemCdMng(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception;

	/**
	 * @param gamQcItemCdMngVo
	 */
	void deleteQcItemCdMng(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception;

}
