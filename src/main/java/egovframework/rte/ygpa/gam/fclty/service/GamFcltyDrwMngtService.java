/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

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
	 * 도면목록관리 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<GamFcltyDrwInfoFVO> selectDrwListMngtList(GamFcltyDrwInfoFVO vo) throws Exception;

	/**
	 * 도면목록관리 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectDrwListMngtListTotCnt(GamFcltyDrwInfoFVO vo) throws Exception;

	/**
	 * 도면목록관리 파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<ComDefaultVO> selectDrwListPhotoList(GamFcltyDrwDtaFVO vo) throws Exception;

	/**
	 * 도면목록관리 파일 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectDrwListPhotoListTotCnt(GamFcltyDrwDtaFVO vo) throws Exception;

	/**
	 * 도면목록관리 저장
	 * @param drwListMngtList
	 * @throws Exception
	 */
	void insertDrwListMng(Map<String, Object> drwListMngtList) throws Exception;

	/**
	 * 도면목록관리 수정
	 * @param drwListMngtList
	 * @throws Exception
	 */
	void updateDrwListMng(Map<String, Object> drwListMngtList) throws Exception;

	/**
	 * 도면목록관리 삭제
	 * @param vo
	 * @throws Exception
	 */
	public void deleteDrwListMng(Map vo) throws Exception;

	/**
	 *  도면 목록 관리 상세 마스터
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map selectDrwListDetailMaster(Map vo) throws Exception;

	/**
	 *  도면 목록 관리 상세 파일 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectDrwListDetailFileList(Map vo) throws Exception;


	/**
	 * 도면 목록관리 저장
	 * @param map
	 * @throws Exception
	 */

	public List mergeDrwInfoMngt(Map mergeList) throws Exception;

}