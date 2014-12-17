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
import egovframework.rte.ygpa.gam.fclty.service.GamConsFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamConsFcltySpecMngService;

/**
 *
 * @author HNJ
 * @since 2014. 11. 4.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 4.	HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamConsFcltySpecMngService")
public class GamConsFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamConsFcltySpecMngService{

	@Resource(name="gamConsFcltySpecMngDao")
    private GamConsFcltySpecMngDao gamConsFcltySpecMngDao;

	/**
	 * 시설관리 목록
	 */
	public List selectFcltySpecMngList(GamConsFcltySpecMngVO vo) throws Exception {
   		return (List)gamConsFcltySpecMngDao.selectFcltySpecMngList(vo);
	}


	/**
	 * 시설관리 총 수
	 */
	public int selectFcltySpecMngListTotCnt(GamConsFcltySpecMngVO vo) throws Exception {
		return gamConsFcltySpecMngDao.selectFcltySpecMngListTotCnt(vo);
    }


	/**
	 * 시설관리 파일 목록
	 */
	public List<ComDefaultVO> selectFcltySpecMngFileList(GamConsFcltySpecMngVO vo) throws Exception {
		return (List<ComDefaultVO>)gamConsFcltySpecMngDao.selectFcltySpecMngFileList(vo);
	}


	/**
	 * 시설관리 파일 총 수
	 */
	public int selectFcltySpecMngFileListTotCnt(GamConsFcltySpecMngVO vo) throws Exception {
		return gamConsFcltySpecMngDao.selectFcltySpecMngFileListTotCnt(vo);
	}



	// 시설관리 저장
	public void insertFcltySpec(Map form) throws Exception{
		gamConsFcltySpecMngDao.insertFcltySpec(form);
	}

	// 시설관리 수정
	public void updateFcltySpec(Map form) throws Exception{
		gamConsFcltySpecMngDao.updateFcltySpec(form);
	}

	// 시설 정보 삭제
	public void deleteFcltySpec(Map vo) throws Exception{
		gamConsFcltySpecMngDao.deleteFcltySpec(vo);
	}

	// 시설 파일 입력/수정/삭제
	public List mergeFcltyFileMngt(Map mergeMap) throws Exception{
		return gamConsFcltySpecMngDao.mergeFcltyFile(mergeMap);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFcltySpecMngService#fcltyMngSelectView(java.util.Map)
	 */
	@Override
	public EgovMap fcltyMngSelectView(Map vo)
			throws Exception {
		return gamConsFcltySpecMngDao.fcltyMngSelectView(vo);
	}
	
	
	public EgovMap fcltySpecMngSelectView(Map vo)
			throws Exception {
		return gamConsFcltySpecMngDao.fcltySpecMngSelectView(vo);
	}

	
	
	/**
	 * 시설 첨부파일 삭제
	 * @param vo
	 * @return 
	 * @throws Exception
	 */
	public void deleteFcltyTotalFile(Map vo) throws Exception{
		gamConsFcltySpecMngDao.deleteFcltyTotalFile(vo);
	}

}