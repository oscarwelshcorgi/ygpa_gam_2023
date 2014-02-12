/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwInfoFVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwMngtService;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwDtaFVO;

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

@Service("gamFcltyDrwMngtService")
public class GamFcltyDrwMngtServiceImpl extends AbstractServiceImpl implements GamFcltyDrwMngtService{

	@Resource(name="gamFcltyDrwMngtDao")
    private GamFcltyDrwMngtDao gamFcltyDrwMngtDao;
	
	/**
	 * 도면 정보 저장
	 * @param vo GamFcltyDrwInfoFVO
	 * @exception Exception
	 */
	public void insertFcltyDrwInfoListMng(GamFcltyDrwInfoFVO vo) throws Exception {
		gamFcltyDrwMngtDao.insertFcltyDrwInfoListMng(vo);
	}
	
	/**
	 * 도면 자료 저장
	 * @param vo GamFcltyDrwDtaFVO
	 * @exception Exception
	 */
	public void insertFcltyDrwListMng(GamFcltyDrwDtaFVO vo) throws Exception {
		gamFcltyDrwMngtDao.insertFcltyDrwListMng(vo);
	}
	
	/**
	 * 도면 자료 목록
	 */
	public List<GamFcltyDrwDtaFVO> selectFcltyDrwMngtList(GamFcltyDrwDtaFVO vo) throws Exception {
   		return (List<GamFcltyDrwDtaFVO>)gamFcltyDrwMngtDao.selectFcltyDrwMngtList(vo);
	}
	
	/**
	 * 도면 자료 목록 총 수
	 */
	public int selectFcltyDrwMngtListTotCnt(GamFcltyDrwDtaFVO vo) throws Exception {
		return gamFcltyDrwMngtDao.selectFcltyDrwMngtListTotCnt(vo);
    }
	
	/**
	 * 도면 정보 목록
	 */
	public List<GamFcltyDrwInfoFVO> selectFcltyDrwMngtInfoList(GamFcltyDrwInfoFVO vo) throws Exception {
		return (List<GamFcltyDrwInfoFVO>)gamFcltyDrwMngtDao.selectFcltyDrwMngtInfoList(vo);
	}
	
	/**
	 * 도면 정보 총 수
	 */
	public int selectFcltyDrwMngtInfoListTotCnt(GamFcltyDrwInfoFVO vo) throws Exception {
		return gamFcltyDrwMngtDao.selectFcltyDrwMngtInfoListTotCnt(vo);
	}
	
	/**
	 * 도면 정보 시퀀스
	 */
	public String insertFcltyInfoGetSeq() throws Exception {
		return gamFcltyDrwMngtDao.insertFcltyInfoGetSeq();
	}
	
	/**
	 * 도면 정보 상세화면
	 */
	public GamFcltyDrwInfoFVO fcltyDrwInfoListMngSelectView(GamFcltyDrwInfoFVO vo) {
		GamFcltyDrwInfoFVO fcltyManageVO = gamFcltyDrwMngtDao.fcltyDrwInfoListMngSelectView(vo);		
		return fcltyManageVO;
	}
	
	/**
	 * 도면 자료 상세화면
	 */
	public GamFcltyDrwDtaFVO fcltyDrwListMngSelectView(GamFcltyDrwDtaFVO vo) {
		GamFcltyDrwDtaFVO fcltyManageVO = gamFcltyDrwMngtDao.fcltyDrwListMngSelectView(vo);		
		return fcltyManageVO;
	}
	
	/**
	 * 도면목록관리 수정화면
	 */
	public void updateFcltyDrwListMng(GamFcltyDrwDtaFVO vo) throws Exception {
		gamFcltyDrwMngtDao.updateFcltyDrwListMng(vo);
	}

	/**
	 * 도면목록관리 삭제
	 */
	public void deleteFcltyDrwListMng(GamFcltyDrwDtaFVO vo) {
		gamFcltyDrwMngtDao.deleteFcltyDrwListMng(vo);
	}
}