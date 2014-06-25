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
 *  2014. 6. 25.		sj		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamCivilFcltyMngtService {

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
	List selectCivilFcltyMngtList(GamFcltyManageVO vo) throws Exception;


	/**
	 * 시설관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectCivilFcltyMngtListTotCnt(GamFcltyManageVO vo) throws Exception;


	/**
	 * 시설관리 파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectCivilFcltyMngtPhotoList(GamFcltyManageVO vo) throws Exception;


	/**
	 * 시설관리 파일 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectCivilFcltyMngtPhotoListTotCnt(GamFcltyManageVO vo) throws Exception;


	/**
	 * 시설관리 시퀀스 가져오기
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	String insertCivilFcltyGetSeq() throws Exception;


	/**
	 * 시설관리 상세화면
	 * @param emplyrId
	 * @return GamFcltyManageVO
	 * @throws Exception
	 */
	public EgovMap CivilfcltyMngSelectView(Map fcltyManageVO) throws Exception;

	// 시설관리 저장
	public String insertCivilFclty(Map form) throws Exception;

	// 시설관리 수정
	public void updateCivilFclty(Map form) throws Exception;

	// 시설 정보 삭제
	public void deleteCivilFclty(Map vo) throws Exception;

	// 시설 파일 저장
	public List mergeCivilFcltyPhotoMngt(Map mergeList, String prtFcltySe) throws Exception;
}