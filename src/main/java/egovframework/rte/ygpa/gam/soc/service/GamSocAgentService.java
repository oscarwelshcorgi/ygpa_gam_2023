/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import java.util.List;
import java.util.Map;


/**
 * 
 * @author HNJ
 * @since 2014. 9. 23.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 23.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamSocAgentService {
	
	/**
	 * 항만공사허가업체정보를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만공사허가업체정보
	 * @exception Exception
	 */
	GamSocAgentVO selectSocAgentInfo(GamSocAgentVO searchVO) throws Exception;
	
	
	/**
	 * 항만공사허가원부 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만공사허가원부 목록
	 * @exception Exception
	 */
    List selectSocAgentList(GamSocAgentVO searchVO) throws Exception;
    
    
    
    /**
	 * 항만공사허가원부 총갯수 및 금액합계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만공사허가원부 총갯수 및 금액합계
	 * @exception
	 */
    GamSocAgentVO selectSocAgentInfoSum(GamSocAgentVO searchVO) throws Exception;
    
    
    /**
	 * 항만공사허가원부 저장
	 * @param Map
	 * @exception
	 */
    public void insertSocAgentData(Map form) throws Exception;
    
    
    /**
	 * 항만공사허가원부정보 수정
	 * @param Map
	 * @exception
	 */
    public void updateSocAgentData(Map form) throws Exception;
    
    
    /**
	 * 항만공사허가원부정보 삭제
	 * @param Map
	 * @exception
	 */
    public void deleteSocAgentData(Map search) throws Exception;
    
    
    /**
	 * 항만공사허가원부하위정보 수정
	 * @param Map
	 * @exception
	 */
    public void insertSocAgentList(Map form) throws Exception;
    
    
    /**
	 * 항만공사허가원부 삭제
	 * @param Map
	 * @exception
	 */
    public void deleteSocAgentList(Map form) throws Exception;
    
}
