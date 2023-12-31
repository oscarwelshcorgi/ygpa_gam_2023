/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author Lee
 * @since 2014. 10. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 24.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamEnergyUsageMngService {

	/**
	 * @param searchVO
	 * @return
	 */
	int selectEnergyUsageMngListTotCnt(GamEnergyUsageMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectEnergyUsageMngList(GamEnergyUsageMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	EgovMap selectEnergyUsageMngPk(GamEnergyUsageMngVo searchVO) throws Exception;

	/**
	 * @param gamEnergyUsageMngVo
	 * @return
	 */
	List selectEnergyUsageMngChartList(GamEnergyUsageMngVo gamEnergyUsageMngVo) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectEnergyUsageMngYearCntList(GamEnergyUsageMngVo searchVO) throws Exception;

	/**
	 * @param gamEnergyUsageMngVo
	 */
	void insertEnergyUsageMng(GamEnergyUsageMngVo gamEnergyUsageMngVo) throws Exception;

	/**
	 * @param gamEnergyUsageMngVo
	 */
	void updateEnergyUsageMng(GamEnergyUsageMngVo gamEnergyUsageMngVo) throws Exception;

	/**
	 * @param gamEnergyUsageMngVo
	 */
	void deleteEnergyUsageMng(GamEnergyUsageMngVo gamEnergyUsageMngVo) throws Exception;

	/**
	 * @param gamEnergyUsageMngVo
	 */
	void copyEnergyUsageMng(GamEnergyUsageMngVo gamEnergyUsageMngVo) throws Exception;

}
