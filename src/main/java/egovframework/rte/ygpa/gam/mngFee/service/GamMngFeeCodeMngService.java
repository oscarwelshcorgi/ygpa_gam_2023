/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import java.util.List;
import java.util.Map;

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

public interface GamMngFeeCodeMngService {

	/**
	 * @param searchVO
	 * @return
	 */
	int selectMngFeeCodeMngListTotCnt(GamMngFeeCodeMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectMngFeeCodeMngList(GamMngFeeCodeMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	EgovMap selectMngFeeCodeMngPk(GamMngFeeCodeMngVo searchVO) throws Exception;

	/**
	 * @return
	 */
	List selectMngFeeFcltySeMngList(Map searchVO) throws Exception;

	/**
	 * @param gamMngFeeCodeMngVo
	 * @return
	 */
	String selectMngFeeCodeMngMaxFcltyCd(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) throws Exception;

	/**
	 * @param gamMngFeeCodeMngVo
	 */
	void insertMngFeeCodeMng(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) throws Exception;

	/**
	 * @param gamMngFeeCodeMngVo
	 */
	void updateMngFeeCodeMng(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) throws Exception;

	/**
	 * @param gamMngFeeCodeMngVo
	 */
	void deleteMngFeeCodeMng(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) throws Exception;

}
