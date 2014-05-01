/**
 *
 */
package egovframework.rte.ygpa.gam.code.service;

import java.util.List;
import java.util.Map;
/**
 *
 * @author kok
 * @since 2014. 3. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 7.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamOlnlpMngtService {


	/**
	 * 공시지가 등록 현황 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<GisAssetsCodeVO> selectOlnlpInsertList(GisAssetsCodeVO vo) throws Exception;


	/**
	 * 공시지가 등록 현황 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectOlnlpInsertListTotCnt(GisAssetsCodeVO vo) throws Exception;


	/**
	 * 공시지가 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<GamOlnlpFVO> selectOlnlpMngtList(GamOlnlpFVO vo) throws Exception;


	/**
	 * 공시지가 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectOlnlpMngtListTotCnt(GamOlnlpFVO vo) throws Exception;


	/**
	 * 공시지가 관리 등록화면
	 * @param vo
	 * @throws Exception
	 */
	void insertOlnlpMngt(GamOlnlpFVO vo) throws Exception;

	/**
	 * 공시지가 관리 수정화면
	 * @param vo
	 * @throws Exception
	 */
	void updateOlnlpMngt(GamOlnlpFVO vo) throws Exception;


	/**
	 * 공시지가 관리 삭제
	 * @param vo
	 * @throws Exception
	 */
	void deleteOlnlpMngt(GamOlnlpFVO vo) throws Exception;

	/**
	 * 공시지가 저장
	 * @param mergeMap
	 * @return
	 * @throws Exception
	 */
	List mergeOlnlpMngt(Map mergeMap) throws Exception;

	/**
	 * 법정동 자료에서 공시지가 자료를 생성한다.
	 * @throws Exception
	 */
	void createOlnlpFromBJD() throws Exception;

	/**
	 * 법정동 자료를 삭제 한다.
	 * @throws Exception
	 */
	void deleteOlnlpBJD();

}