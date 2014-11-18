/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
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
@Repository("gamFcltsClCdDao")
public class GamFcltsClCdDao extends YGPAAbstractDAO {
	
	/**
	 *  메인시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectMainFcltsClCdList() throws Exception {

		return list("gamFcltsClCdDao.selectMainFcltsClCdList_D", null);
	}
	
	
	
	/**
	 *  시설물 분류관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltsClCdList(GamFcltsClCdVO vo) throws Exception {
		// TODO Auto-generated method stub
		return list("gamFcltsClCdDao.selectFcltsClCdList_D", vo);
	}

	/**
	 *  시설물 분류관리 목록 총수
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public int selectFcltsClCdListTotCnt(GamFcltsClCdVO vo) throws Exception {
		// TODO Auto-generated method stub
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltsClCdDao.selectFcltsClCdListTotCnt_S", vo);
	}
	
	
	/**
	 *  시설물 분류관리 상세보기/수정
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public EgovMap selectFcltsClCdDetail (Map vo) throws Exception {
		// TODO Auto-generated method stub
		return (EgovMap) selectByPk("gamFcltsClCdDao.selectFcltsClCdDetail_S", vo);
	}
	
	
	/**
	 *  시설물분류 상위코드 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltsClUpperCdList (Map vo) throws Exception {
		return list("gamFcltsClCdDao.selectFcltsClUpperCdList_D", vo);
	}
	
	/**
	 *  시설물 분류관리 신규코드 생성
	 * @param vo
	 * @return String
	 * @throws Exception
	 */
	public String selectNewFcltsClCd (GamFcltsClCdVO vo) throws Exception {
		return (String) selectByPk("gamFcltsClCdDao.selectNewFcltsClCd_S", vo);
	}
	
	
	/**
	 * 시설물분류 코드입력
	 * @param GamFcltsClCdVO
	 * @return map
	 * @throws Exception
	 */
	public void insertFcltsClCd (GamFcltsClCdVO vo) throws Exception{
		insert("gamFcltsClCdDao.insertFcltsClCd", vo);
	}
	
	
	/**
	 * 시설물분류 코드수정
	 * @param GamFcltsClCdVO
	 * @return map
	 * @throws Exception
	 */
	public void updateFcltsClCd (GamFcltsClCdVO vo) throws Exception{
		update("gamFcltsClCdDao.updateFcltsClCd", vo);
	}
	
	
	/**
	 * 시설물분류 코드삭제
	 * @param GamFcltsClCdVO
	 * @return map
	 * @throws Exception
	 */
	public void deleteFcltsClCd (GamFcltsClCdVO vo) throws Exception{
		delete("gamFcltsClCdDao.deleteFcltsClCd", vo);
	}

}
