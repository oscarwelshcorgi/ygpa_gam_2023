/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairSttusInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairSttusInqireVO;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 01.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 01.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltyRepairSttusInqireService")
public class GamFcltyRepairSttusInqireServiceImpl extends AbstractServiceImpl implements GamFcltyRepairSttusInqireService {
	
	@Resource(name="gamFcltyRepairSttusInqireDao")
	private GamFcltyRepairSttusInqireDao gamFcltyRepairSttusInqireDao;

	/**
	 * 하자보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyRepairSttusInqireList(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return (List)gamFcltyRepairSttusInqireDao.selectFcltyRepairSttusInqireList(vo);
	}

	
	/**
	 * 하자보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyRepairSttusInqireListTotCnt(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return gamFcltyRepairSttusInqireDao.selectFcltyRepairSttusInqireListTotCnt(vo);
	}
	
	
	/**
	 * 하자보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFlawRprSttusObjFcltsFList(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return (List)gamFcltyRepairSttusInqireDao.selectFlawRprSttusObjFcltsFList(vo);
	}
	
	
	/**
	 * 하자보수 대상시설물 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFlawRprSttusObjFcltsFListTotCnt(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return gamFcltyRepairSttusInqireDao.selectFlawRprSttusObjFcltsFListTotCnt(vo);
	}
	
	
	
	/**
	 * 하자보수 검사자 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFlawExamUsrSttusFList(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return (List)gamFcltyRepairSttusInqireDao.selectFlawExamUsrSttusFList(vo);
	}
	
	
	/**
	 * 하자보수 검사자 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFlawExamUsrSttusFListTotCnt(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return gamFcltyRepairSttusInqireDao.selectFlawExamUsrSttusFListTotCnt(vo);
	}
	
	
	/**
	 * 하자보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyRepairSttusFileList(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return (List)gamFcltyRepairSttusInqireDao.selectFcltyRepairSttusFileList(vo);
	}
	
	
	/**
	 * 하자보수 첨부파일 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyRepairSttusFileListTotCnt(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return gamFcltyRepairSttusInqireDao.selectFcltyRepairSttusFileListTotCnt(vo);
	}
	

}
