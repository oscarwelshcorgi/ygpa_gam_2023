/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 31.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamElctyEquipCapaMngService {

	/**
	 * @param searchVO
	 * @return
	 */
	GamElctyEquipCapaMngVo selectElctyEquipCapaMngListTotCnt(GamElctyEquipCapaMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectElctyEquipCapaMngList(GamElctyEquipCapaMngVo searchVO) throws Exception;

	/**
	 * @param gamElctyEquipCapaMngVo
	 * @return
	 */
	EgovMap selectElctyEquipCapaMngPk(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception;

	/**
	 * @param gamElctyEquipCapaMngVo
	 * @return
	 */
	List selectElctyEquipCapaMngChartList(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception;

	/**
	 * @param gamElctyEquipCapaMngVo
	 * @return
	 */
	EgovMap selectElctyEquipCapaMngPrevYearCapa(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception;

	/**
	 * @param gamElctyEquipCapaMngVo
	 * @return
	 */
	List selectElctyEquipCapaMngYearCntList(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception;

	/**
	 * @param gamElctyEquipCapaMngVo
	 * @return
	 */
	String selectElctyEquipCapaMngNewMngSeq(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception;

	/**
	 * @param gamElctyEquipCapaMngVo
	 */
	void insertElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception;

	/**
	 * @param gamElctyEquipCapaMngVo
	 */
	void updateElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception;

	/**
	 * @param gamElctyEquipCapaMngVo
	 */
	void deleteElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception;

	/**
	 * @param gamElctyEquipCapaMngVo
	 */
	void copyElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception;

}
