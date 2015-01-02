/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;


/**
*
* @author 정성현
* @since 2014. 12. 11.
* @version 1.0
* @see
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일 		 수정자		 수정내용
*  -------		--------	---------------------------
*  2014. 12. 11.	정성현		최초 생성
*
* Copyright (C) 2013 by LFIT  All right reserved.
* </pre>
*/


public interface GamFcltyUseUnuseSttusInqireService {


	/**
	 * 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List selectFcltyUseUnuseSttusInqireList(GamFcltyUseUnuseSttusInqireVO searchVO) throws Exception;



	GamFcltyUseUnuseSttusInqireVO selectFcltyUseUnuseSttusInqireListTotCnt(GamFcltyUseUnuseSttusInqireVO searchVO) throws Exception;



	List selectFcltyUseUnuseSttusInqireDetail(GamFcltyUseUnuseSttusInqireVO searchVO) throws Exception;



	/**
	 * @param searchVO 
	 * @return
	 */
	




	



}
