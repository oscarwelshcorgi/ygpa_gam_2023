/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintSttusInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintSttusInqireVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 25.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltyMaintSttusInqireService")
public class GamFcltyMaintSttusInqireServiceImpl extends AbstractServiceImpl implements GamFcltyMaintSttusInqireService {
	
	@Resource(name="gamFcltyMaintSttusInqireDao")
	private GamFcltyMaintSttusInqireDao gamFcltyMaintSttusInqireDao;

	/**
	 * 유지보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyMaintSttusInqireList(GamFcltyMaintSttusInqireVO vo) throws Exception {
		return (List)gamFcltyMaintSttusInqireDao.selectFcltyMaintSttusInqireList(vo);
	}

	
	/**
	 * 유지보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyMaintSttusInqireListTotCnt(GamFcltyMaintSttusInqireVO vo) throws Exception {
		return gamFcltyMaintSttusInqireDao.selectFcltyMaintSttusInqireListTotCnt(vo);
	}
	
	
	/**
	 * 유지보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	public EgovMap selectFcltyMaintSttusInqireDetail(GamFcltyMaintSttusInqireVO vo) throws Exception{
		return gamFcltyMaintSttusInqireDao.selectFcltyMaintSttusInqireDetail(vo);
	}
	
	
	/**
	 * 유지보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectMntnSttusRprObjFcltsFList(GamFcltyMaintSttusInqireVO vo) throws Exception {
		return (List)gamFcltyMaintSttusInqireDao.selectMntnSttusRprObjFcltsFList(vo);
	}
	
	
	/**
	 * 유지보수 대상시설물 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectMntnSttusRprObjFcltsFListTotCnt(GamFcltyMaintSttusInqireVO vo) throws Exception {
		return gamFcltyMaintSttusInqireDao.selectMntnSttusRprObjFcltsFListTotCnt(vo);
	}
	
	
	/**
	 * 유지보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyMaintSttusFileList(GamFcltyMaintSttusInqireVO vo) throws Exception {
		return (List)gamFcltyMaintSttusInqireDao.selectFcltyMaintSttusFileList(vo);
	}
	
	
	/**
	 * 유지보수 첨부파일 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyMaintSttusFileListTotCnt(GamFcltyMaintSttusInqireVO vo) throws Exception {
		return gamFcltyMaintSttusInqireDao.selectFcltyMaintSttusFileListTotCnt(vo);
	}
	

}
