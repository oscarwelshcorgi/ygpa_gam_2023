/**
 * 
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.service;

import java.util.Map;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 7.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamGisPrtFcltyCdMngtService {
	
	/**
	 * GIS 항만시설코드 정보를 삽입한다.
	 * @param vo - 삽입할 정보가 담긴 VO
	 * @return
	 * @exception Exception
	 */	
	public void insertGisPrtFclty(Map<String, Object> vo) throws Exception;
	
	/**
	 * GIS 항만시설코드 정보를 수정한다.
	 * @param vo - 수정할 정보가 담긴 VO
	 * @return
	 * @exception Exception
	 */	
	public void updateGisPrtFclty(Map<String, Object> vo) throws Exception;
	
	/**
	 * GIS 항만시설코드 정보를 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 VO
	 * @return
	 * @exception Exception
	 */	
	public void deleteGisPrtFclty(Map<String, Object> vo) throws Exception;	
	
	/**
	 * GIS 항만시설순번 다음순번을 가져온다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return
	 * @exception Exception
	 */	
	public String selectNextFcltySeq(Map<String, Object> vo) throws Exception;
	
}
