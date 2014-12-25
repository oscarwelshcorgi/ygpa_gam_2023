/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamConstFcltySpecInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamConstFcltySpecInqireVO;

/**
 * 
 * @author LFIT
 * @since 2014. 12. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 5.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("GamConstFcltySpecInqireService")
public class GamConstFcltySpecInqireServiceImpl extends AbstractServiceImpl implements GamConstFcltySpecInqireService {

	
	@Resource(name="GamConstFcltySpecInqireDao")
    private GamConstFcltySpecInqireDao GamConstFcltySpecInqireDao;
	
	/**
	 * 시설관리 목록
	 */
	@Override
	public List selectFcltySpecInqireList(GamConstFcltySpecInqireVO searchVO)	throws Exception {
		return (List)GamConstFcltySpecInqireDao.selectFcltySpecInqireList(searchVO);
	}

	/**
	 * 시설관리 총 수
	 */
	@Override
	public int selectFcltySpecInqireListTotCnt(GamConstFcltySpecInqireVO searchVO) throws Exception {
		return GamConstFcltySpecInqireDao.selectFcltySpecInqireListTotCnt(searchVO);
	}

	/**
	 * 시설관리 상세화면
	 */
	@Override
	public EgovMap selectFcltySpecInqireDetail(Map searchVO)  throws Exception {
		return GamConstFcltySpecInqireDao.selectFcltySpecInqireDetail(searchVO);
	}

	/**
	 * 시설관리 파일 목록
	 */
	@Override
	public List<ComDefaultVO> selectFcltySpecInqireFileList(GamConstFcltySpecInqireVO searchVO) throws Exception {
		return (List<ComDefaultVO>)GamConstFcltySpecInqireDao.selectFcltySpecInqireFileList(searchVO);
	}

	/**
	 * 시설관리 파일 총 수
	 */
	@Override
	public int selectFcltySpecInqireFileListTotCnt(GamConstFcltySpecInqireVO searchVO) throws Exception {
		return GamConstFcltySpecInqireDao.selectFcltySpecInqireFileListTotCnt(searchVO);
	}

}
