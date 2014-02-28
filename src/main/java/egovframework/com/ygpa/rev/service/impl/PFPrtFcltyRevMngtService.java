/**
 *
 */
package egovframework.com.ygpa.rev.service.impl;

import java.util.List;
import java.util.Map;

/**
 * 징수의뢰 관련 고지를 처리하기위한 서비스
 * @author EUNSUNGJ
 * @since 2014. 2. 26.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 26.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface PFPrtFcltyRevMngtService {

	/**
	 * 징수의뢰 자료에대해 고지를 수행 한다.
	 * @param map - prtAtCode, mngYear, mngNo, mngCnt, nticCnt
	 * @throws Exception
	 */
	public void insertRevColl(Map<String, Object> map) throws Exception;

	/**
	 * 징수의뢰 자료에 대해 고지를 취소 한다.
	 * @param map
	 * @throws Exception
	 */
	public void cancelRevColl(Map<String, Object> map) throws Exception;

	/**
	 * 징수의뢰 자료 목록에 대해 일괄 고지를 수행 한다.
	 * @param map
	 * @throws Exception
	 */
	public void insertRevCollList(List<Map<String, Object>> map) throws Exception;

	/**
	 * 징수의뢰 자료 목록에 대해 일괄 고지를 취소 한다.
	 * @param map
	 * @throws Exception
	 */
	public void cancelRevCollList(List<Map<String, Object>> map) throws Exception;

	/**
	 * 연체 세입 등록을 한다.
	 * @param map - 연체세입 등록 처리 대상 prtAtCode, mngYear, mngNo, mngCnt, feeTp, accnutYear, billNo, updUsr
	 * @throws Exception
	 */
	public void insertC1RevColl(Map<String, Object> map) throws Exception;

	/**
	 * 연체 세입 취소처리를 한다.
	 * @param map - 처리 대상 prtAtCode, mngYear, mngNo, mngCnt, feeTp, accnutYear, billNo, updUsr
	 * @throws Exception
	 */
	public void cancelC1RevColl(Map<String, Object> map) throws Exception;


}
