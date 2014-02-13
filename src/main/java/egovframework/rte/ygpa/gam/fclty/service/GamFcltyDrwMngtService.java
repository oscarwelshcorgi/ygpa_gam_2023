/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;

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
	 * 도면 정보 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<GamFcltyDrwInfoFVO> selectFcltyDrwMngtInfoList(GamFcltyDrwInfoFVO vo) throws Exception;
	
	/**
	 * 도면 정보 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyDrwMngtInfoListTotCnt(GamFcltyDrwInfoFVO vo) throws Exception;
	
	/**
	 * 도면 자료 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<GamFcltyDrwDtaFVO> selectFcltyDrwMngtList(GamFcltyDrwDtaFVO vo) throws Exception;
	
	/**
	 * 도면 자료 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyDrwMngtListTotCnt(GamFcltyDrwDtaFVO vo) throws Exception;
	
	/**
	 * 도면 정보 시퀀스
	 * @return String
	 * @throws Exception
	 */
	String insertFcltyInfoGetSeq() throws Exception;
	
	/**
	 * 도면 자료 저장
	 * @param vo
	 * @throws Exception
	 */
	void insertFcltyDrwListMng(GamFcltyDrwDtaFVO vo) throws Exception;
	
	/**
	 * 도면 정보 수정
	 * @param vo
	 * @throws Exception
	 */
	void updateFcltyDrwInfoListMng(GamFcltyDrwInfoFVO vo) throws Exception;

	/**
	 * 도면 정보 상세화면
	 * @param fcltyManageVO
	 * @return GamFcltyDrwInfoFVO
	 * @throws Exception
	 */
	public GamFcltyDrwInfoFVO fcltyDrwInfoListMngSelectView(GamFcltyDrwInfoFVO fcltyManageVO) throws Exception;
	
	/**
	 * 도면 정보 저장
	 * @param vo
	 * @throws Exception
	 */
	void insertFcltyDrwInfoListMng(GamFcltyDrwInfoFVO vo) throws Exception;

	/**
	 * 도면 자료 상세화면
	 * @param fcltyManageVO
	 * @return GamFcltyDrwDtaFVO
	 * @throws Exception
	 */
	public GamFcltyDrwDtaFVO fcltyDrwListMngSelectView(GamFcltyDrwDtaFVO fcltyManageVO) throws Exception;
	
	/**
	 * 도면 자료 수정화면
	 * @param fcltyManageVO
	 * @throws Exception
	 */
	public void updateFcltyDrwListMng(GamFcltyDrwDtaFVO fcltyManageVO) throws Exception;
	
	/**
	 * 도면 정보 삭제
	 * @param fcltyManageVO
	 * @throws Exception
	 */
	public void deleteFcltyDrwInfoListMng(GamFcltyDrwDtaFVO fcltyManageVO) throws Exception;
	
	/**
	 * 도면 자료 삭제
	 * @param fcltyManageVO
	 * @throws Exception
	 */
	public void deleteFcltyDrwListMng(GamFcltyDrwDtaFVO fcltyManageVO) throws Exception;
}