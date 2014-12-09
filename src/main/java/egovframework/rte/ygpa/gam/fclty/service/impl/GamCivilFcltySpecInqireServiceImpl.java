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
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltySpecInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltySpecInqireVO;


/**
 *
 * @author 김종민
 * @since 2014. 11. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 10.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
*/

@Service("gamCivilFcltySpecInqireService")
public class GamCivilFcltySpecInqireServiceImpl extends AbstractServiceImpl implements GamCivilFcltySpecInqireService{
	@Resource(name="gamCivilFcltySpecInqireDao")
	private GamCivilFcltySpecInqireDao gamCivilFcltySpecInqireDao;
	/**
	 * 토목시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectCivilFcltySpecInqireList(GamCivilFcltySpecInqireVO searchVO) throws Exception {
		return gamCivilFcltySpecInqireDao.selectCivilFcltySpecInqireList(searchVO);
	}


	public int selectCivilFcltySpecInqireListTotCnt(GamCivilFcltySpecInqireVO searchVO) throws Exception {
		return gamCivilFcltySpecInqireDao.selectCivilFcltySpecInqireListTotCnt(searchVO);
	}

	/**
	 * 토목시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */
	public EgovMap selectCivilFcltySpecInqireDetail(Map searchVO) throws Exception {
		return gamCivilFcltySpecInqireDao.selectCivilFcltySpecInqireDetail(searchVO);
	}



	public List selectCivilFcltySpecInqireFileList(GamCivilFcltySpecInqireVO searchVO) throws Exception {
		return gamCivilFcltySpecInqireDao.selectCivilFcltySpecInqireFileList(searchVO);
	}




	public int selectCivilFcltySpecInqireFileListTotCnt(GamCivilFcltySpecInqireVO searchVO) throws Exception {
		return gamCivilFcltySpecInqireDao.selectCivilFcltySpecInqireFileListTotCnt(searchVO);
	}


}
