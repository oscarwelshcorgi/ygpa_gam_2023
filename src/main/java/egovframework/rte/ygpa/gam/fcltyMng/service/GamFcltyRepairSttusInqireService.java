/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;


/**
 * 
 * @author HNJ
 * @since 2014. 12. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 9.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltyRepairSttusInqireService {
	
	
	/**
	 * 하자보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltyRepairSttusInqireList(GamFcltyRepairSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 하자보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyRepairSttusInqireListTotCnt(GamFcltyRepairSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 하자보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	EgovMap selectFcltyRepairSttusInqireDetail(GamFcltyRepairSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 하자보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFlawRprSttusObjFcltsFList(GamFcltyRepairSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 하자보수 대상시설물 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFlawRprSttusObjFcltsFListTotCnt(GamFcltyRepairSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 하자보수 검사자 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFlawExamUsrSttusFList(GamFcltyRepairSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 하자보수 검사자 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFlawExamUsrSttusFListTotCnt(GamFcltyRepairSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 하자보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltyRepairSttusFileList(GamFcltyRepairSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 하자보수 첨부파일 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyRepairSttusFileListTotCnt(GamFcltyRepairSttusInqireVO vo) throws Exception;
	
	

}
