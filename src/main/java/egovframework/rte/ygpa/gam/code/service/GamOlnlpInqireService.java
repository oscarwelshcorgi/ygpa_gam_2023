/**
 *
 */
package egovframework.rte.ygpa.gam.code.service;

import java.util.List;
import java.util.Map;

/**
 * 공시지가 조회 서비스
 * @author EUNSUNGJ
 * @since 2014. 5. 1.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 5. 1.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
public interface GamOlnlpInqireService {


	/**
	 * 공시지가 등록 현황 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectOlnlpInsertList(GisAssetsCodeVO vo) throws Exception;


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
	List selectOlnlpInqireList(GamOlnlpFVO vo) throws Exception;


	/**
	 * 공시지가 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectOlnlpInqireListTotCnt(GamOlnlpFVO vo) throws Exception;

}