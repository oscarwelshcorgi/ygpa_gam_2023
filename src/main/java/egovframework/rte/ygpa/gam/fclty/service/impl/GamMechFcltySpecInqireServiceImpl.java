/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamMechFcltySpecInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamMechFcltySpecInqireVO;

/**
 * 
 * @author LFIT
 * @since 2014. 12. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 9.		김영길		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamMechFcltySpecInqireService")
public class GamMechFcltySpecInqireServiceImpl extends AbstractServiceImpl implements GamMechFcltySpecInqireService {

	@Resource(name="gamMechFcltySpecInqireDao")
	private GamMechFcltySpecInqireDao gamMechFcltySpecInqireDao;
	
	/**
	 * 기계시설제원관리 목록 조회
	 * @param searchVO
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List selectMechFcltySpecInqireList(GamMechFcltySpecInqireVO searchVO) throws Exception {
		return gamMechFcltySpecInqireDao.selectMechFcltySpecInqireList(searchVO);
	}

	/**
	 * 기계시설제원관리 목록 총수 조회
	 * @param searchVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int selectMechFcltySpecInqireListTotCnt(GamMechFcltySpecInqireVO searchVO) throws Exception {
		return gamMechFcltySpecInqireDao.selectMechFcltySpecInqireListTotCnt(searchVO);
	}

	/**
	 * 기계시설제원관리 데이터 조회
	 * @param searchVO
	 * @return egovMap
	 * @throws Exception
	 */
	@Override
	public EgovMap selectMechFcltySpecInqireDetail(Map searchVO) throws Exception {
		return gamMechFcltySpecInqireDao.selectMechFcltySpecInqireDetail(searchVO);
	}

	/**
	 * 기계시설제원관리 첨부파일 목록을 가져온다.
	 * @param searchVO
	 * @return list 
	 * @throws Exception
	 */
	@Override
	public List selectMechFcltySpecInqireFileList(GamMechFcltySpecInqireVO searchVO) throws Exception {
		return gamMechFcltySpecInqireDao.selectMechFcltySpecInqireFileList(searchVO);
	}

	/**
	 * 기계시설제원관리 첨부파일 목록 총수를 가져온다.
	 * @param searchVO
	 * @return list 
	 * @throws Exception
	 */
	@Override
	public int selectMechFcltySpecInqireFileListTotCnt(GamMechFcltySpecInqireVO searchVO) throws Exception {
		return gamMechFcltySpecInqireDao.selectMechFcltySpecInqireFileListTotCnt(searchVO);
	}

}
