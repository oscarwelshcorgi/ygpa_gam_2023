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
 * @author kok
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		kok		최초 생성
 *  2016. 6. 16.		sj		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamElctyFcltyMngtService {

	/**
	 * 메뉴 정보를 등록
	 * @param vo GamFcltyManageVO
	 * @exception Exception
	 */
//	void insertFcltyManage(Map fcltyMngtList) throws Exception;


	/**
	 * 전기관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectElctyFcltyMngtList(GamFcltyManageVO vo) throws Exception;


	/**
	 * 전기관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectElctyFcltyMngtListTotCnt(GamFcltyManageVO vo) throws Exception;


	/**
	 * 전기관리 파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectElctyFcltyMngtPhotoList(GamFcltyManageVO vo) throws Exception;


	/**
	 * 전기관리 파일 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectElctyFcltyMngtPhotoListTotCnt(GamFcltyManageVO vo) throws Exception;


	/**
	 * 전기관리 시퀀스 가져오기
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	String insertElctyFcltyGetSeq() throws Exception;


	/**
	 * 전기관리 상세화면
	 * @param emplyrId
	 * @return GamFcltyManageVO
	 * @throws Exception
	 */
	public EgovMap ElctyfcltyMngSelectView(Map fcltyManageVO) throws Exception;

	// 전기관리 저장
	public String insertElctyFclty(Map form) throws Exception;

	// 전기관리 수정
	public void updateElctyFclty(Map form) throws Exception;

	// 전기 정보 삭제
	public void deleteElctyFclty(Map vo) throws Exception;

	// 전기 파일 저장
	public List mergeElctyFcltyPhotoMngt(Map mergeList, String prtFcltySe) throws Exception;
}