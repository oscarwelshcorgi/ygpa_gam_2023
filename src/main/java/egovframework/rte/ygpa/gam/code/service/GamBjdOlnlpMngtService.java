/**
 *
 */
package egovframework.rte.ygpa.gam.code.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 12.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamBjdOlnlpMngtService {
	/**
	 * 공시지가목록 조회
	 * @param vo
	 * @return lis t
	 * @throws Exception
	 */
	List selectBjdOlnlpList(GamBjdOlnlpMngtVO vo) throws Exception;

	/**
	 * 공시지가목록 갯수 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int selectBjdOlnlpListTotCnt(GamBjdOlnlpMngtVO vo) throws Exception;

	/**
	 * 공시지가 저장
	 * @param mergeMap
	 * @return
	 * @throws Exception
	 */
	List mergeOlnlpMngt(Map mergeMap) throws Exception;


	/**
	 * 기존 자산별 공시지가 데이터를 업데이트 한다.
	 * @throws Exception
	 */
	void applyOlnlpData() throws Exception;

	/**
	 * 법정동코드 조회
	 * throws Exception
	 */
	List selectBupjungdongCd() throws Exception;

	/**
	 * @param bjdOlnlpLinkVO
	 */
	void insertBupjungdongOlnlpF(Map bjdOlnlpLinkVO) throws Exception;
}
