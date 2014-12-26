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
import egovframework.rte.ygpa.gam.fclty.service.GamConsFcltySpecInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamConsFcltySpecInqireVO;

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

@Service("gamConsFcltySpecInqireService")
public class GamConsFcltySpecInqireServiceImpl extends AbstractServiceImpl implements GamConsFcltySpecInqireService {

	
	@Resource(name="gamConsFcltySpecInqireDao")
    private GamConsFcltySpecInqireDao gamConsFcltySpecInqireDao;
	
	/**
	 * 시설관리 목록
	 */
	@Override
	public List selectFcltySpecInqireList(GamConsFcltySpecInqireVO searchVO)	throws Exception {
		return (List)gamConsFcltySpecInqireDao.selectFcltySpecInqireList(searchVO);
	}

	/**
	 * 시설관리 총 수
	 */
	@Override
	public int selectFcltySpecInqireListTotCnt(GamConsFcltySpecInqireVO searchVO) throws Exception {
		return gamConsFcltySpecInqireDao.selectFcltySpecInqireListTotCnt(searchVO);
	}

	/**
	 * 시설관리 상세화면
	 */
	@Override
	public EgovMap fcltInqireSelectView(Map fcltyManageVO)  throws Exception {
		return gamConsFcltySpecInqireDao.fcltInqireSelectView(fcltyManageVO);
	}

	/**
	 * 시설관리 상세화면(제원)
	 */
	@Override
	public EgovMap fcltySpecInqireSelectView(Map fcltyManageVO) throws Exception {
		return gamConsFcltySpecInqireDao.fcltySpecInqireSelectView(fcltyManageVO);
	}

	/**
	 * 시설관리 파일 목록
	 */
	@Override
	public List<ComDefaultVO> selectFcltySpecInqireFileList(GamConsFcltySpecInqireVO searchVO) throws Exception {
		return (List<ComDefaultVO>)gamConsFcltySpecInqireDao.selectFcltySpecInqireFileList(searchVO);
	}

	/**
	 * 시설관리 파일 총 수
	 */
	@Override
	public int selectFcltySpecInqireFileListTotCnt(GamConsFcltySpecInqireVO searchVO) throws Exception {
		return gamConsFcltySpecInqireDao.selectFcltySpecInqireFileListTotCnt(searchVO);
	}

}
