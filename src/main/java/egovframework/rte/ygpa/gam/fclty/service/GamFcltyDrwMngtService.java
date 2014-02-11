/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;


/**
 * 
 * @author kok
 * @since 2014. 2. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 7.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltyDrwMngtService {

	/**
	 * 도면목록관리 등록
	 * @param vo GamFcltyDrwDtaFVO
	 * @exception Exception
	 */
	void insertFcltyDrwListMng(GamFcltyDrwDtaFVO vo) throws Exception;

	
	/**
	 * 도면목록관리 저장
	 * @param vo GamFcltyDrwInfoFVO
	 * @exception Exception
	 */
	void insertFcltyDrwInfoListMng(GamFcltyDrwInfoFVO vo) throws Exception;

	
	/**
	 * 도면목록관리 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<ComDefaultVO> selectFcltyDrwMngtList(ComDefaultVO vo) throws Exception;
	
	
	/**
	 * 도면목록관리 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyDrwMngtListTotCnt(ComDefaultVO vo) throws Exception;
	
	
	/**
	 * 도면목록관리 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<ComDefaultVO> selectFcltyDrwMngtInfoList(ComDefaultVO vo) throws Exception;
	
	
	/**
	 * 도면목록관리 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyDrwMngtInfoListTotCnt(ComDefaultVO vo) throws Exception;
	
	
	/**
	 * 시설관리 시퀀스 가져오기
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	String insertFcltyInfoGetSeq() throws Exception;

	
	/**
	 * 시설관리 시퀀스 가져오기
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	String insertFcltyGetSeq() throws Exception;
	
	
	/** 
	 * 도면목록관리 상세화면
	 * @param fcltyManageVO
	 * @return GamFcltyDrwDtaFVO
	 * @throws Exception
	 */
	public GamFcltyDrwDtaFVO fcltyDrwListMngSelectView(GamFcltyDrwDtaFVO fcltyManageVO) throws Exception;

	
	/** 
	 * 도면목록관리 상세화면
	 * @param fcltyManageVO
	 * @return GamFcltyDrwInfoFVO
	 * @throws Exception
	 */
	public GamFcltyDrwInfoFVO fcltyDrwInfoListMngSelectView(GamFcltyDrwInfoFVO fcltyManageVO) throws Exception;
	
	
	/**
	 * 도면목록관리 수정화면
	 * @param FcltyManageVO
	 * @throws Exception
	 */
	public void updateFcltyDrwListMng(GamFcltyDrwDtaFVO fcltyManageVO) throws Exception;
	
	
	/**
	 * 도면목록관리 삭제
	 * @param fcltyManageVO
	 * @throws Exception
	 */
	public void deleteFcltyDrwListMng(GamFcltyDrwDtaFVO fcltyManageVO) throws Exception;
}