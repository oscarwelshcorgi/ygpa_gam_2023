/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;


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

public interface GamFcltyMngtService {

	/**
	 * 메뉴 정보를 등록
	 * @param vo GamFcltyManageVO
	 * @exception Exception
	 */
	void insertFcltyManage(GamFcltyManageVO vo) throws Exception;

	
	/**
	 * 시설관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<ComDefaultVO> selectFcltyMngtList(ComDefaultVO vo) throws Exception;
	
	
	/**
	 * 시설관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyMngtListTotCnt(ComDefaultVO vo) throws Exception;
	
	
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
	public GamFcltyManageVO fcltyMngSelectView(GamFcltyManageVO fcltyManageVO) throws Exception;
	
	
	
	/**
	 * 시설관리 수정화면
	 * @param FcltyManageVO
	 * @throws Exception
	 */
	public void updateFclty(GamFcltyManageVO fcltyManageVO) throws Exception;
	
	
	/**
	 * 시설관리 삭제
	 * @param fcltyManageVO
	 * @throws Exception
	 */
	public void deleteFclty(GamFcltyManageVO fcltyManageVO) throws Exception;
}