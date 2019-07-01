package egovframework.rte.ygpa.gam.code.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.code.service.GamBuldMktcStdAmManageService;
import egovframework.rte.ygpa.gam.code.service.GamBuldMktcStdAmVO;

/**
 * 
 * 건축물시가표준액에 대한 서비스 구현클래스를 정의한다.
 * 
 */


@Service("gamBuldMktcStdAmManageService")
public class GamBuldMktcStdAmManageServiceImpl extends AbstractServiceImpl implements GamBuldMktcStdAmManageService{
	
	@Resource(name="gamBuldMktcStdAmManageDAO")
	private GamBuldMktcStdAmManageDAO gamBuldMktcStdAmManageDAO;

	/** 
	 * 건축물시가표준액 목록조회한다.
	 */	
	public List selectBuldMktcStdAmList(GamBuldMktcStdAmVO searchVO) throws Exception {			
		return gamBuldMktcStdAmManageDAO.selectBuldMktcStdAmList(searchVO);
	}
	
	/** 
	 * 건축물시가표준액 총 갯수를 조회한다.
	 */	
	public int selectBuldMktcStdAmListTotCnt(GamBuldMktcStdAmVO searchVO) throws Exception {
		return gamBuldMktcStdAmManageDAO.selectBuldMktcStdAmListTotCnt(searchVO);
	}

	/** 
	 * 건축물시가표준액을 등록한다.
	 */
	public void insertBuldMktcStdAmList(GamBuldMktcStdAmVO inputVO) throws Exception {
		gamBuldMktcStdAmManageDAO.insertBuldMktcStdAmList(inputVO);
		
	}
	/** 
	 * 건축물시가표준액을 수정한다.
	 */
	public void updateBuldMktcStdAmList(GamBuldMktcStdAmVO inputVO) throws Exception {
		gamBuldMktcStdAmManageDAO.updateBuldMktcStdAmList(inputVO);		
	}

	/** 
	 * 건축물시가표준액을 삭제한다.
	 */	
	@Override
	public void deleteBuldMktcStdAm(GamBuldMktcStdAmVO deleteVO) throws Exception {
		gamBuldMktcStdAmManageDAO.deleteBuldMktcStdAm(deleteVO);		
	}



}
