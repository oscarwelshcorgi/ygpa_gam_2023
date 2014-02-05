/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltyManageVO;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltyMngtService;

/**
 * 
 * @author kok
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamCivilFcltyMngtService")
public class GamCivilFcltyMngtServiceImpl extends AbstractServiceImpl implements GamCivilFcltyMngtService{

	@Resource(name="gamCivilFcltyMngtDao")
    private GamCivilFcltyMngtDao gamCivilFcltyMngtDao;
	
	/**
	 * 시설관리 저장
	 * @param vo GamCivilFcltyManageVO
	 * @exception Exception
	 */
	public void insertCivilFcltyManage(GamCivilFcltyManageVO vo) throws Exception {
		gamCivilFcltyMngtDao.insertCivilFcltyManage(vo);
	}
	
	
	/**
	 * 시설관리 목록
	 */
	public List<ComDefaultVO> selectCivilFcltyMngtList(ComDefaultVO vo) throws Exception {
   		return (List<ComDefaultVO>)gamCivilFcltyMngtDao.selectCivilFcltyMngtList(vo);
	}
	
	
	/**
	 * 시설관리 카운트
	 */
	public int selectCivilFcltyMngtListTotCnt(ComDefaultVO vo) throws Exception {
		return gamCivilFcltyMngtDao.selectCivilFcltyMngtListTotCnt(vo);
    }

	
	/**
	 * 시설관리 목록
	 */
	public List<ComDefaultVO> selectSearchGisCdPopupList(ComDefaultVO vo) throws Exception {
   		return (List<ComDefaultVO>)gamCivilFcltyMngtDao.selectSearchGisCdPopupList(vo);
	}
	
	
	/**
	 * 시설관리 카운트
	 */
	public int selectSearchGisCdPopupListTotCnt(ComDefaultVO vo) throws Exception {
		return gamCivilFcltyMngtDao.selectSearchGisCdPopupListTotCnt(vo);
    }
	
	
	/**
	 * 시설관리 상세화면
	 * @param vo
	 * @return GamCivilFcltyManageVO
	 */
	public GamCivilFcltyManageVO civilFcltyMngSelectView(GamCivilFcltyManageVO vo) {
		GamCivilFcltyManageVO civilFcltyManageVO = gamCivilFcltyMngtDao.civilFcltyMngSelectView(vo);		
		return civilFcltyManageVO;
	}
	
	
	/**
	 * 시설관리 수정화면
	 */
	public void updateCivilFclty(GamCivilFcltyManageVO vo) throws Exception {
		gamCivilFcltyMngtDao.updateCivilFclty(vo);
	}
	

	/**
	 * 시설관리 삭제
	 */
	public void deleteCivilFclty(GamCivilFcltyManageVO vo) {
		gamCivilFcltyMngtDao.deleteCivilFclty(vo);
	}
}