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
 *  2014. 6. 17.		sj		추가 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamInfoTechFcltyMngtService {

	/**
	 * 정보통신 메뉴 정보를 등록
	 * @param vo GamFcltyManageVO
	 * @exception Exception
	 */
//	void insertFcltyManage(Map fcltyMngtList) throws Exception;


	/**
	 * 정보통신 시설관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectInfoTechFcltyMngtList(GamFcltyManageVO vo) throws Exception;


	/**
	 * 정보통신 시설관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectInfoTechFcltyMngtListTotCnt(GamFcltyManageVO vo) throws Exception;


	/**
	 * 정보통신 시설관리 파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectInfoTechFcltyMngtPhotoList(GamFcltyManageVO vo) throws Exception;


	/**
	 * 정보통신 시설관리 파일 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectInfoTechFcltyMngtPhotoListTotCnt(GamFcltyManageVO vo) throws Exception;


	/**
	 * 정보통신 시설관리 시퀀스 가져오기
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	String insertInfoTechFcltyGetSeq() throws Exception;


	/**
	 * 정보통신 시설관리 상세화면
	 * @param emplyrId
	 * @return GamFcltyManageVO
	 * @throws Exception
	 */
	public EgovMap InfoTechfcltyMngSelectView(Map fcltyManageVO) throws Exception;

	// 시설관리 저장
	public String insertInfoTechFclty(Map form) throws Exception;

	// 시설관리 수정
	public void updateInfoTechFclty(Map form) throws Exception;

	// 시설 정보 삭제
	public void deleteInfoTechFclty(Map vo) throws Exception;

	// 시설 파일 저장
	public List mergeInfoTechFcltyPhotoMngt(Map mergeList, String prtFcltySe) throws Exception;
}