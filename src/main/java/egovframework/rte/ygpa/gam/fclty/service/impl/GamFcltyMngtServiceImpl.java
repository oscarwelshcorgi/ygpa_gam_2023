/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyManageVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyMngtService;

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

@Service("gamFcltyMngtService")
public class GamFcltyMngtServiceImpl extends AbstractServiceImpl implements GamFcltyMngtService{

	@Resource(name="gamFcltyMngtDao")
    private GamFcltyMngtDao gamFcltyMngtDao;
	
	/**
	 * 시설관리 저장
	 * @param vo GamFcltyManageVO
	 * @exception Exception
	 */
	public void insertFcltyManage(GamFcltyManageVO vo) throws Exception {
		gamFcltyMngtDao.insertFcltyManage(vo);
	}
	
	
	/**
	 * 시설관리 목록
	 */
	public List<ComDefaultVO> selectFcltyMngtList(ComDefaultVO vo) throws Exception {
   		return (List<ComDefaultVO>)gamFcltyMngtDao.selectFcltyMngtList(vo);
	}
	
	
	/**
	 * 시설관리 카운트
	 */
	public int selectFcltyMngtListTotCnt(ComDefaultVO vo) throws Exception {
		return gamFcltyMngtDao.selectFcltyMngtListTotCnt(vo);
    }

	
	/**
	 * 시설관리 시퀀스
	 */
	public String insertFcltyGetSeq() throws Exception {
		return gamFcltyMngtDao.insertFcltyGetSeq();
	}
	
	
	/**
	 * 시설관리 상세화면
	 * @param vo
	 * @return GamFcltyManageVO
	 */
	public GamFcltyManageVO fcltyMngSelectView(GamFcltyManageVO vo) {
		GamFcltyManageVO fcltyManageVO = gamFcltyMngtDao.fcltyMngSelectView(vo);		
		return fcltyManageVO;
	}
	
	
	/**
	 * 시설관리 수정화면
	 */
	public void updateFclty(GamFcltyManageVO vo) throws Exception {
		gamFcltyMngtDao.updateFclty(vo);
	}
	

	/**
	 * 시설관리 삭제
	 */
	public void deleteFclty(GamFcltyManageVO vo) {
		gamFcltyMngtDao.deleteFclty(vo);
	}
}