/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import java.util.List;
import java.util.Map;

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

public interface GamFcltsMngFeeMngService {

	/**
	 * @param searchVO
	 * @return
	 */
	int selectFcltsMngFeeMngListTotCnt(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFcltsMngFeeMngList(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception;

	/**
	 * @param gamFcltsMngFeeMngVo
	 */
	void insertFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception;

	/**
	 * @param gamFcltsMngFeeMngVo
	 */
	void deleteFcltsMngFeeMng(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception;

	List mergeSaveFcltsMngFeeMng(Map mergeList) throws Exception;

	void insertFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception;

	/**
	 * 관리비 관리 상세 테이블 조회
	 * @param gamFcltsMngFeeMngVo
	 */
	List selectFcltsMngFeeMngDetailList(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception;

	/**
	 * 관리비 관리 마스터 테이블 update
	 */
	void updateFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception;

	/**
	 * 관리비 관리 디테일 테이블 update
	 */
	void updateFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception;

	/**
	 * 관리비 관리 디테일 테이블 delete
	 */
	void deleteFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception;

	/**
	 * 관리비 관리 요금 코드 select
	 * @param GamFcltsMngFeeMngVo
	 */
	List selectChargeKndList() throws Exception;

	/**
	 * 관리비 관리 징수의뢰 고지의뢰 같이한다.
	 * @param GamFcltsMngFeeMngVo
	 */
	void confirmFcltsMngFeeMngPerm(Map<String, Object> vo) throws Exception;

}
