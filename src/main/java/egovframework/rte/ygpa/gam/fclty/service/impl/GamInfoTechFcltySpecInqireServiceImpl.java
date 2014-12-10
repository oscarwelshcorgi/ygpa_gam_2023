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
import egovframework.rte.ygpa.gam.fclty.service.GamInfoTechFcltySpecInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamInfoTechFcltySpecInqireVO;

/**
 *
 * @author 정성현
 * @since 2014. 12. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 9. 정성현		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamInfoTechFcltySpecInqireService")
public class GamInfoTechFcltySpecInqireServiceImpl extends AbstractServiceImpl implements GamInfoTechFcltySpecInqireService{
	@Resource(name="gamInfoTechFcltySpecInqireDao")
	private GamInfoTechFcltySpecInqireDao gamInfoTechFcltySpecInqireDao;
	/**
	 * 정보통신시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectInfoTechFcltySpecInqireList(GamInfoTechFcltySpecInqireVO searchVO) throws Exception {
		return gamInfoTechFcltySpecInqireDao.selectInfoTechFcltySpecInqireList(searchVO);
	}

	/**
	 * 정보통신시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectInfoTechFcltySpecInqireListTotCnt(GamInfoTechFcltySpecInqireVO searchVO) throws Exception {
		return gamInfoTechFcltySpecInqireDao.selectInfoTechFcltySpecInqireListTotCnt(searchVO);
	}

	/**
	 * 정보통신시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */
	public EgovMap selectInfoTechFcltySpecInqireDetail(Map searchVO) throws Exception {
		return gamInfoTechFcltySpecInqireDao.selectInfoTechFcltySpecInqireDetail(searchVO);
	}

	/**
	 * 정보통신시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectInfoTechFcltySpecInqireFileList(GamInfoTechFcltySpecInqireVO searchVO) throws Exception {
		return gamInfoTechFcltySpecInqireDao.selectInfoTechFcltySpecInqireFileList(searchVO);
	}

	/**
	 * 정보통신시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public int selectInfoTechFcltySpecInqireFileListTotCnt(GamInfoTechFcltySpecInqireVO searchVO) throws Exception {
		return gamInfoTechFcltySpecInqireDao.selectInfoTechFcltySpecInqireFileListTotCnt(searchVO);
	}


}
