/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.umt.service.UserManageVO;


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
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamCivilFcltyMngtService {

	/**
	 * 메뉴 정보를 등록
	 * @param vo GamCivilFcltyManageVO
	 * @exception Exception
	 */
	void insertCivilFcltyManage(GamCivilFcltyManageVO vo) throws Exception;

	
	/**
	 * 시설관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<ComDefaultVO> selectCivilFcltyMngtList(ComDefaultVO vo) throws Exception;
	
	
	/**
	 * 시설관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectCivilFcltyMngtListTotCnt(ComDefaultVO vo) throws Exception;

	
	/**
	 * 자산코드팝업 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<ComDefaultVO> selectSearchGisCdPopupList(ComDefaultVO vo) throws Exception;
	
	
	/**
	 * 자산코드팝업 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectSearchGisCdPopupListTotCnt(ComDefaultVO vo) throws Exception;
	
	
	/** 
	 * 시설관리 상세화면
	 * @param emplyrId
	 * @return GamCivilFcltyManageVO
	 * @throws Exception
	 */
	public GamCivilFcltyManageVO civilFcltyMngSelectView(GamCivilFcltyManageVO civilFcltyManageVO) throws Exception;
	
	
	
	/**
	 * 시설관리 수정화면
	 * @param civilFcltyManageVO
	 * @throws Exception
	 */
	public void updateCivilFclty(GamCivilFcltyManageVO civilFcltyManageVO) throws Exception;
	
	
	/**
	 * 시설관리 삭제
	 * @param civilFcltyManageVO
	 * @throws Exception
	 */
	public void deleteCivilFclty(GamCivilFcltyManageVO civilFcltyManageVO) throws Exception;
}