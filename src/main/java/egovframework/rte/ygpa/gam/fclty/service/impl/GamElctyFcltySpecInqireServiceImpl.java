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
import egovframework.rte.ygpa.gam.fclty.service.GamElctyFcltySpecInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamElctyFcltySpecInqireVO;

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
 *  -------		   --------	---------------------------
 *  2014. 12. 9.	정성현		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */


@Service("gamElctyFcltySpecInqireService")
public class GamElctyFcltySpecInqireServiceImpl extends AbstractServiceImpl implements GamElctyFcltySpecInqireService {
	@Resource(name = "gamElctyFcltySpecInqireDao")
	private GamElctyFcltySpecInqireDao gamElctyFcltySpecInqireDao;

	/**
	 * 전기시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectElctyFcltySpecInqireList(GamElctyFcltySpecInqireVO searchVO) throws Exception {
		return gamElctyFcltySpecInqireDao.selectElctyFcltySpecInqireList(searchVO);
	}

	/**
	 * 전기시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectElctyFcltySpecInqireListTotCnt(GamElctyFcltySpecInqireVO searchVO) throws Exception {
		return gamElctyFcltySpecInqireDao.selectElctyFcltySpecInqireListTotCnt(searchVO);
	}

	/**
	 * 전기시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */
	public EgovMap selectElctyFcltySpecInqireDetail(Map searchVO) throws Exception {
		return gamElctyFcltySpecInqireDao.selectElctyFcltySpecInqireDetail(searchVO);
	}

	/**
	 * 전기시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectElctyFcltySpecInqireFileList(GamElctyFcltySpecInqireVO searchVO) throws Exception {
		return gamElctyFcltySpecInqireDao.selectElctyFcltySpecInqireFileList(searchVO);
	}

	/**
	 * 전기시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public int selectElctyFcltySpecInqireFileListTotCnt(GamElctyFcltySpecInqireVO searchVO) throws Exception {
		return gamElctyFcltySpecInqireDao.selectElctyFcltySpecInqireFileListTotCnt(searchVO);
	}

}
