/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamFcltsMngGroupVO;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 5.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamFcltsMngGroupDao")
public class GamFcltsMngGroupDao extends YGPAAbstractDAO {
	
	/**
	 *  메인시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectMainFcltsMngGroupList() throws Exception {

		return list("gamFcltsMngGroupDao.selectMainFcltsMngGroupList_D", null);
	}
	
	
	
	/**
	 *  시설물 분류관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltsMngGroupList(GamFcltsMngGroupVO vo) throws Exception {
		// TODO Auto-generated method stub
		return list("gamFcltsMngGroupDao.selectFcltsMngGroupList_D", vo);
	}

	/**
	 *  시설물 분류관리 목록 총수
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public int selectFcltsMngGroupListTotCnt(GamFcltsMngGroupVO vo) throws Exception {
		// TODO Auto-generated method stub
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltsMngGroupDao.selectFcltsMngGroupListTotCnt_S", vo);
	}
	
	
	/**
	 *  시설물 분류관리 상세보기/수정
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public EgovMap selectFcltsMngGroupDetail (Map vo) throws Exception {
		// TODO Auto-generated method stub
		return (EgovMap) selectByPk("gamFcltsMngGroupDao.selectFcltsMngGroupDetail_S", vo);
	}

	
	/**
	 *  시설물 분류관리 신규코드 생성
	 * @param vo
	 * @return String
	 * @throws Exception
	 */
	public String selectNewFcltsMngGroup (GamFcltsMngGroupVO vo) throws Exception {
		return (String) selectByPk("gamFcltsMngGroupDao.selectNewFcltsMngGroup_S", vo);
	}
	
	
	/**
	 * 시설물그룹관리 코드입력
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	public void insertFcltsMngGroup (GamFcltsMngGroupVO vo) throws Exception{
		insert("gamFcltsMngGroupDao.insertFcltsMngGroup", vo);
	}
	
	
	/**
	 * 시설물그룹관리 코드수정
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	public void updateFcltsMngGroup (GamFcltsMngGroupVO vo) throws Exception{
		update("gamFcltsMngGroupDao.updateFcltsMngGroup", vo);
	}
	
	
	/**
	 * 시설물그룹관리 코드삭제
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	public void deleteFcltsMngGroup (GamFcltsMngGroupVO vo) throws Exception{
		delete("gamFcltsMngGroupDao.deleteFcltsMngGroup", vo);
	}
	
	
	/**
	 * 시설물그룹관리 상위코드 LEAF_YN값 수정
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	public void updateChnageParentLeafYn (GamFcltsMngGroupVO vo) throws Exception{
		update("gamFcltsMngGroupDao.updateChnageParentLeafYn", vo);
	}
	
	
	/**
	 *  시설물그룹관리 상위코드에 따르는 하위코드 총수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltsClParentCdListCnt(GamFcltsMngGroupVO vo) throws Exception {
		// TODO Auto-generated method stub
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltsMngGroupDao.selectFcltsClParentCdListCnt_S", vo);
	}

}
