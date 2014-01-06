package egovframework.com.ygpa.sym.mpm.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.sym.mnu.mcm.service.MenuCreatVO;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.com.ygpa.sym.mpm.service.YGMenuManageService;


/**
 * 메뉴목록관리, 생성, 사이트맵을 처리하는 비즈니스 구현 클래스를 정의한다.
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
@Service("ygMenuManageService")
public class YGMenuManageServiceImpl extends AbstractServiceImpl implements YGMenuManageService {

	protected Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="ygMenuManageDAO")
    private YGMenuManageDAO ygMenuManageDAO;

    /*### 메뉴관련 프로세스 ###*/

    /* 상단 메뉴 목록 조회
     * (non-Javadoc)
     * @see egovframework.com.ipa.sym.mpm.service.ISMenuManageService#selectMainMenuHead(egovframework.com.sym.mpm.service.MenuManageVO)
     */
    public List selectMainMenuHead(MenuManageVO vo) throws Exception {
        return ygMenuManageDAO.selectMainMenuHead(vo);
    }   
    
    /* 상단 메뉴 목록 조회
     * (non-Javadoc)
     * @see egovframework.com.ipa.sym.mpm.service.ISMenuManageService#selectMainMenuHead(egovframework.com.sym.mpm.service.MenuManageVO)
     */
    public List selectSystemMainMenuHead(MenuManageVO vo) throws Exception {
        return ygMenuManageDAO.selectSystemMainMenuHead(vo);
    }   
    /* 메뉴 목록을 조회
     * (non-Javadoc)
     * @see egovframework.com.ipa.sym.mpm.service.ISMenuManageService#selectMenuManageList(egovframework.com.cmm.ComDefaultVO)
     */
    public List selectMenuManageList(ComDefaultVO vo) throws Exception {
        return ygMenuManageDAO.selectMenuManageList(vo);
    }
    

    /* 메뉴 전체목록을 조회
     * (non-Javadoc)
     * @see egovframework.com.ipa.sym.mpm.service.ISMenuManageService#selectMenuList()
     */
    public List selectMenuList() throws Exception {
        return ygMenuManageDAO.selectMenuList();
    }

    /* 메뉴생성관리 목록을 조회
     * (non-Javadoc)
     * @see egovframework.com.ipa.sym.mpm.service.ISMenuManageService#selectMenuCreatManagList(egovframework.com.cmm.ComDefaultVO)
     */
    public List selectMenuCreatManagList(ComDefaultVO vo) throws Exception {
        return ygMenuManageDAO.selectMenuCreatManagList(vo);
    }

    /* 메뉴생성 내역을 조회
     * (non-Javadoc)
     * @see egovframework.com.ipa.sym.mpm.service.ISMenuManageService#selectMenuCreatList(egovframework.com.sym.mpm.service.MenuCreatVO)
     */
    public List selectMenuCreatList(MenuCreatVO vo) throws Exception {
        return ygMenuManageDAO.selectMenuCreatList(vo);
    }    
}