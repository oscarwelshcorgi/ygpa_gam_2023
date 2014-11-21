/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamFcltsClCdService;
import egovframework.rte.ygpa.gam.code.service.GamFcltsClCdVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 17.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltsClCdService")
public class GamFcltsClCdServiceImpl extends AbstractServiceImpl implements GamFcltsClCdService {
	
	@Resource(name="gamFcltsClCdDao")
	private GamFcltsClCdDao gamFcltsClCdDao;
	
	
	/**
	 *  메인시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectMainFcltsClCdList() throws Exception {
		// TODO Auto-generated method stub
		return (List)gamFcltsClCdDao.selectMainFcltsClCdList();
	}
	

	/**
	 *  시설물 분류관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltsClCdList(GamFcltsClCdVO vo) throws Exception {
		// TODO Auto-generated method stub
		return (List)gamFcltsClCdDao.selectFcltsClCdList(vo);
	}

	/**
	 *  시설물 분류관리 목록 총수
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public int selectFcltsClCdListTotCnt(GamFcltsClCdVO vo) throws Exception {
		// TODO Auto-generated method stub
		return gamFcltsClCdDao.selectFcltsClCdListTotCnt(vo);
	}
	
	
	/**
	 *  시설물 분류관리 상세보기/수정
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public EgovMap selectFcltsClCdDetail (Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamFcltsClCdDao.selectFcltsClCdDetail(vo);
	}
	
	
	/**
	 *  시설물분류 상위코드 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltsClUpperCdList (Map vo) throws Exception {
		return (List)gamFcltsClCdDao.selectFcltsClUpperCdList(vo);
	}
	
	
	/**
	 *  시설물 분류관리 신규코드 생성
	 * @param vo
	 * @return String
	 * @throws Exception
	 */
	public String selectNewFcltsClCd (GamFcltsClCdVO vo) throws Exception {
		return gamFcltsClCdDao.selectNewFcltsClCd(vo);
	}
	
	
	/**
	 * 시설물분류 코드입력
	 * @param GamFcltsClCdVO
	 * @return map
	 * @throws Exception
	 */
	public void insertFcltsClCd (GamFcltsClCdVO vo) throws Exception{
		gamFcltsClCdDao.insertFcltsClCd(vo);
	}
	
	
	/**
	 * 시설물분류 코드수정
	 * @param GamFcltsClCdVO
	 * @return map
	 * @throws Exception
	 */
	public void updateFcltsClCd (GamFcltsClCdVO vo) throws Exception{
		gamFcltsClCdDao.updateFcltsClCd(vo);
	}
	
	
	/**
	 * 시설물분류 코드삭제
	 * @param GamFcltsClCdVO
	 * @return map
	 * @throws Exception
	 */
	public void deleteFcltsClCd (GamFcltsClCdVO vo) throws Exception{
		gamFcltsClCdDao.deleteFcltsClCd(vo);
	}
	
	
	/**
	 * 시설물분류 상위코드 LEAF_YN값 수정
	 * @param GamFcltsClCdVO
	 * @return map
	 * @throws Exception
	 */
	public void updateChnageParentLeafYn (GamFcltsClCdVO vo) throws Exception{
		gamFcltsClCdDao.updateChnageParentLeafYn(vo);
	}
	
	
	/**
	 *  시설물 분류관리 상위코드에 따르는 하위코드 총수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltsClParentCdListCnt(GamFcltsClCdVO vo) throws Exception {
		return gamFcltsClCdDao.selectFcltsClParentCdListCnt(vo);
	}

}
