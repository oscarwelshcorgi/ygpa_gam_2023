/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import java.util.List;

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
	int selectEnergyUsageMngListYearCnt(GamEnergyUsageMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectEnergyUsageMngList(GamEnergyUsageMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectEnergyUsageMngChartList(GamEnergyUsageMngVo searchVO) throws Exception;

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
