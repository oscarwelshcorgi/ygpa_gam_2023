package  egovframework.com.ygpa.sym.mpm.service;

import java.util.List;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.sym.mnu.mcm.service.MenuCreatVO;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;

/**
 *  메뉴관리에 관한 서비스 인터페이스
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
public interface YGMenuManageService {

    /*### 메뉴관련 프로세스 ###*/


    /**
     * 상단 메뉴 목록 조회
     * @param vo
     * @return
     * @throws Exception
     */
    List selectMainMenuHead(MenuManageVO vo) throws Exception;
    /**
     * 상단 메뉴 목록 조회
     * @param vo
     * @return
     * @throws Exception
     */
    List selectSystemMainMenuHead(MenuManageVO vo) throws Exception;    
    /**
     * 메뉴 목록을 조회
     * @param vo
     * @return
     * @throws Exception
     */
    List selectMenuManageList(ComDefaultVO vo) throws Exception;
    /**
     * 메뉴 전체목록을 조회
     * @return
     * @throws Exception
     */
    List selectMenuList() throws Exception;
    /**
     * 메뉴생성관리 목록을 조회
     * @param vo
     * @return
     * @throws Exception
     */
    List selectMenuCreatManagList(ComDefaultVO vo) throws Exception;
    /**
     * 메뉴생성 내역을 조회
     * @param vo
     * @return
     * @throws Exception
     */
    List selectMenuCreatList(MenuCreatVO vo) throws Exception;
        
}