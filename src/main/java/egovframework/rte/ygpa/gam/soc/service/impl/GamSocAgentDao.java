/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentVO;


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

@Repository("gamSocAgentDao")
public class GamSocAgentDao extends YGPAAbstractDAO {
	
	/**
	 * 항만공사허가원부 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만공사허가원부 목록
	 * @exception Exception
	 */
    public GamSocAgentVO selectSocAgentInfo(GamSocAgentVO searchVO) throws Exception {
        return (GamSocAgentVO) selectByPk("gamSocAgentDao.selectSocAgentInfo_D", searchVO);
    }
	

	/**
	 * 항만공사허가원부 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만공사허가원부 목록
	 * @exception Exception
	 */
    public List<?> selectSocAgentList(GamSocAgentVO searchVO) throws Exception {
        return list("gamSocAgentDao.selectSocAgentList_D", searchVO);
    }
    
    
    /**
	 * 항만공사허가원부 목록 총갯수 및 금액합계를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만공사허가원부 총갯수 및 금액합계
	 * @exception
	 */
    public GamSocAgentVO selectSocAgentInfoSum(GamSocAgentVO searchVO) {
        return (GamSocAgentVO)selectByPk("gamSocAgentDao.selectSocAgentInfoSum_S", searchVO);
    }
    
    
    /**
	 * 항만공사허가원부 저장
	 * @param Map
	 * @exception
	 */
	public void insertSocAgentData(Map<?, ?> form){
		insert("gamSocAgentDao.insertSocAgentData", form);
	}
	
	
	/**
	 * 항만공사허가원부 수정
	 * @param Map
	 * @exception
	 */
    public void updateSocAgentData(Map<?, ?> form) {
    	update("gamSocAgentDao.updateSocAgentData",form);
    }
    
    
    /**
	 * 항만공사허가원부정보 삭제
	 * @param Map
	 * @exception
	 */
    public void deleteSocAgentData(Map<?, ?> search) {
    	update("gamSocAgentDao.deleteSocAgentData",search);
    }
    
	
    /**
	 * 항만공사허가원부 하위정보 수정
	 * @param Map
	 * @exception
	 */
    public void insertSocAgentList(Map<?, ?> form) {
    	insert("gamSocAgentDao.insertNewSocAgentList", form);
    }
    
    
    /**
	 * 항만공사허가원부 하위정보 삭제
	 * @param Map
	 * @exception
	 */
    public void deleteSocAgentList(Map<?, ?> form) throws Exception{
    	delete("gamSocAgentDao.deleteOldSocAgentList", form);
    }

}
