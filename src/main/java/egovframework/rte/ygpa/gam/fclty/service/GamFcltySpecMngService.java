/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
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

public interface GamFcltySpecMngService {

	/**
	 * 메뉴 정보를 등록
	 * @param vo GamFcltyManageVO
	 * @exception Exception
	 */
//	void insertFcltyManage(Map fcltyMngtList) throws Exception;


	/**
	 * 시설관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltySpecMngList(GamFcltyManageVO vo) throws Exception;


	/**
	 * 시설관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltySpecMngListTotCnt(GamFcltyManageVO vo) throws Exception;


	/**
	 * 시설관리 파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltySpecMngPhotoList(GamFcltyManageVO vo) throws Exception;


	/**
	 * 시설관리 파일 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltySpecMngPhotoListTotCnt(GamFcltyManageVO vo) throws Exception;


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
	 * @return GamFcltyManageVO
	 * @throws Exception
	 */
	public EgovMap fcltyMngSelectView(Map fcltyManageVO) throws Exception;

	// 시설관리 저장
	public String insertFclty(Map form) throws Exception;

	// 시설관리 수정
	public void updateFclty(Map form) throws Exception;

	// 시설 정보 삭제
	public void deleteFclty(Map vo) throws Exception;

	// 시설 파일 저장
	public List mergeFcltyPhotoMngt(Map mergeList, String prtFcltySe) throws Exception;
}