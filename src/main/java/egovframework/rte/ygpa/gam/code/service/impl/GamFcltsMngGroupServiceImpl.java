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
import egovframework.rte.ygpa.gam.code.service.GamFcltsMngGroupService;
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

@Service("gamFcltsMngGroupService")
public class GamFcltsMngGroupServiceImpl extends AbstractServiceImpl implements GamFcltsMngGroupService {
	
	@Resource(name="gamFcltsMngGroupDao")
	private GamFcltsMngGroupDao gamFcltsMngGroupDao;
	
	
	/**
	 *  메인시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectMainFcltsMngGroupList() throws Exception {
		// TODO Auto-generated method stub
		return (List)gamFcltsMngGroupDao.selectMainFcltsMngGroupList();
	}
	

	/**
	 *  시설물 분류관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltsMngGroupList(GamFcltsMngGroupVO vo) throws Exception {
		// TODO Auto-generated method stub
		return (List)gamFcltsMngGroupDao.selectFcltsMngGroupList(vo);
	}

	/**
	 *  시설물 분류관리 목록 총수
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public int selectFcltsMngGroupListTotCnt(GamFcltsMngGroupVO vo) throws Exception {
		// TODO Auto-generated method stub
		return gamFcltsMngGroupDao.selectFcltsMngGroupListTotCnt(vo);
	}
	
	
	/**
	 *  시설물 분류관리 상세보기/수정
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public EgovMap selectFcltsMngGroupDetail (Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamFcltsMngGroupDao.selectFcltsMngGroupDetail(vo);
	}

	
	
	/**
	 *  시설물 분류관리 신규코드 생성
	 * @param vo
	 * @return String
	 * @throws Exception
	 */
	public String selectNewFcltsMngGroup (GamFcltsMngGroupVO vo) throws Exception {
		return gamFcltsMngGroupDao.selectNewFcltsMngGroup(vo);
	}
	
	
	/**
	 * 시설물그룹관리 코드입력
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	public void insertFcltsMngGroup (GamFcltsMngGroupVO vo) throws Exception{
		gamFcltsMngGroupDao.insertFcltsMngGroup(vo);
	}
	
	
	/**
	 * 시설물그룹관리 코드수정
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	public void updateFcltsMngGroup (GamFcltsMngGroupVO vo) throws Exception{
		gamFcltsMngGroupDao.updateFcltsMngGroup(vo);
	}
	
	
	/**
	 * 시설물그룹관리 코드삭제
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	public void deleteFcltsMngGroup (GamFcltsMngGroupVO vo) throws Exception{
		gamFcltsMngGroupDao.deleteFcltsMngGroup(vo);
	}
	
	
	/**
	 * 시설물그룹관리 상위코드 LEAF_YN값 수정
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	public void updateChnageParentLeafYn (GamFcltsMngGroupVO vo) throws Exception{
		gamFcltsMngGroupDao.updateChnageParentLeafYn(vo);
	}
	
	
	/**
	 *  시설물그룹관리 상위코드에 따르는 하위코드 총수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltsClParentCdListCnt(GamFcltsMngGroupVO vo) throws Exception {
		return gamFcltsMngGroupDao.selectFcltsClParentCdListCnt(vo);
	}

}
