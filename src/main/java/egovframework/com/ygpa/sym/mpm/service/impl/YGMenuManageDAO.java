package egovframework.com.ygpa.sym.mpm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.mnu.mcm.service.MenuCreatVO;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;


/**
 * 메뉴 처리 DAO
 * @author EUNSUNGJ
 * @since 2013. 8. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2013. 8. 22.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("ygMenuManageDAO")
public class YGMenuManageDAO extends EgovComAbstractDAO{
    /*### 메뉴관련 프로세스 ###*/
    /**
     * 상단 메뉴 목록 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectMainMenuHead(MenuManageVO vo) throws Exception{
        vo.setTempValue("false");
        List list = (List)list("ygMenuManageDAO.selectMainMenuRoot", vo);
        Map map = new HashMap();
        map.put("collection", list);
        map.put("MenuManageVO", vo);
        
        return list("ygMenuManageDAO.selectMainMenuHead", map);
    }

    /**
     * 상단 메뉴 목록 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectSystemMainMenuHead(MenuManageVO vo) throws Exception{
        vo.setTempValue("true");
        List list = (List)list("ygMenuManageDAO.selectMainMenuRoot", vo);
        Map map = new HashMap();
        map.put("collection", list);
        map.put("MenuManageVO", vo);
        
        return list("ygMenuManageDAO.selectMainMenuHead", map);
        
    }    
    
    
    /**
     * 메뉴목록을 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectMenuManageList(ComDefaultVO vo) throws Exception{
        return list("ygMenuManageDAO.selectMenuManageList_D", vo);
    }

    /**
     * 메뉴 전체목록을 조회
     * @return
     * @throws Exception
     */
    public List selectMenuList() throws Exception{
        ComDefaultVO vo  = new ComDefaultVO();
        return list("ygMenuManageDAO.selectMenuListT_D", vo);
    }

    /**
     * 메뉴생성관리 내역을 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectMenuCreatManagList(ComDefaultVO vo) throws Exception{
        return list("ygMenuManageDAO.selectMenuCreatManageList_D", vo);
    }    

    /**
     * 메뉴생성 내역을 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectMenuCreatList(MenuCreatVO vo) throws Exception{
        return list("ygMenuManageDAO.selectMenuCreatList_D", vo);
    }    
}