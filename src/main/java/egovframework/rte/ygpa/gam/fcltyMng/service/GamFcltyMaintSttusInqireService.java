/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import java.util.List;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 8.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 8.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltyMaintSttusInqireService {
	
	
	/**
	 * 유지보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectFcltyMaintSttusInqireList(GamFcltyMaintSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 유지보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyMaintSttusInqireListTotCnt(GamFcltyMaintSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 유지보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	EgovMap selectFcltyMaintSttusInqireDetail(GamFcltyMaintSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 유지보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectMntnSttusRprObjFcltsFList(GamFcltyMaintSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 유지보수 대상시설물 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectMntnSttusRprObjFcltsFListTotCnt(GamFcltyMaintSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 유지보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectFcltyMaintSttusFileList(GamFcltyMaintSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 유지보수 첨부파일 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyMaintSttusFileListTotCnt(GamFcltyMaintSttusInqireVO vo) throws Exception;
	

}
