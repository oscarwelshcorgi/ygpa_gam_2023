/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import java.util.List;
import java.util.Map;

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
	List selectFcltyMaintSttusInqireList(GamFcltyMaintSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 유지보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyMaintSttusInqireListTotCnt(GamFcltyMaintSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 유지보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectMntnRprObjFcltsFList(GamFcltyMaintSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 유지보수 대상시설물 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectMntnRprObjFcltsFListTotCnt(GamFcltyMaintSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 유지보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltyMaintFileList(GamFcltyMaintSttusInqireVO vo) throws Exception;
	
	
	/**
	 * 유지보수 첨부파일 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectFcltyMaintFileListTotCnt(GamFcltyMaintSttusInqireVO vo) throws Exception;
	

}
