/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.ocr.service;

import java.util.List;

/**
 * 
 * @author Jongmin
 * @since 2017. 8. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2017. 8. 25.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamOcrRecvInquireService {
	
	List<?> selectOcrRecvInquireList(GamOcrRecvInquireVO searchVO) throws Exception;
	
	int selectOcrRecvInquireListCnt(GamOcrRecvInquireVO searchVO) throws Exception;

}
