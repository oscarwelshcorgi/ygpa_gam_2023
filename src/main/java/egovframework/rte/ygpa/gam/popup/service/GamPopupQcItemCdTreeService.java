/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service;

import java.util.List;

/**
 * 
 * @author 김종민
 * @since 2014. 12. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 31.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupQcItemCdTreeService {
	
	/**
	 * 점검관리 결과항목 Tree구조용 리스트
	 * @param searchVO
	 * @return list
	 */	
	List<?> selectQcItemCdTreeList(GamPopupQcItemCdTreeVO searchVO) throws Exception;
}
