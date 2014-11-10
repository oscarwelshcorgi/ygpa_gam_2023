/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;


/**
 *
 * @author HNJ
 * @since 2014. 11. 4.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 4.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamConsFcltySpecMngService {

	/**
	 * 메뉴 정보를 등록
	 * @param vo GamConsFcltySpecMngVO
	 * @exception Exception
	 */
//	void insertFcltyManage(Map fcltyMngtList) throws Exception;


	/**
	 * 시설관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltySpecMngList(GamConsFcltySpecMngVO vo) throws Exception;


	/**
	 * 시설관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltySpecMngListTotCnt(GamConsFcltySpecMngVO vo) throws Exception;


	/**
	 * 시설관리 파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltySpecMngPhotoList(GamConsFcltySpecMngVO vo) throws Exception;


	/**
	 * 시설관리 파일 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltySpecMngPhotoListTotCnt(GamConsFcltySpecMngVO vo) throws Exception;


	/**
	 * 시설관리 시퀀스 가져오기
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	String insertFcltyGetSeq() throws Exception;


	/**
	 * 시설관리 상세화면
	 * @param emplyrId
	 * @return GamConsFcltySpecMngVO
	 * @throws Exception
	 */
	public EgovMap fcltyMngSelectView(Map vo) throws Exception;
	
	/**
	 * 시설관리 상세화면(제원)
	 * @param emplyrId
	 * @return GamConsFcltySpecMngVO
	 * @throws Exception
	 */
	public EgovMap fcltySpecMngSelectView(Map vo) throws Exception;
	

	// 시설관리 저장
	public void insertFcltySpec(Map form) throws Exception;

	// 시설관리 수정
	public void updateFcltySpec(Map form) throws Exception;

	// 시설 정보 삭제
	public void deleteFcltySpec(Map vo) throws Exception;

	// 시설 파일 저장
	public List mergeFcltyPhotoMngt(Map mergeList, String prtFcltySe) throws Exception;
	
	
	/**
	 * 제원관리 층수 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltyinfo9List(GamConsFcltySpecMngVO vo) throws Exception;


	/**
	 * 제원관리 층수 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyinfo9ListTotCnt(GamConsFcltySpecMngVO vo) throws Exception;
}