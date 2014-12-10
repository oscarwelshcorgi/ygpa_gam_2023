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
 * @author 김종민
 * @since 2014. 12. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 10.	김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltsMngGroupService")
public class GamFcltsMngGroupServiceImpl extends AbstractServiceImpl implements GamFcltsMngGroupService {
	@Resource(name="gamFcltsMngGroupDao")
	private GamFcltsMngGroupDao gamFcltsMngGroupDao;

	/**
	 * 시설물관리그룹 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	public List selectFcltsMngGroupList(GamFcltsMngGroupVO searchVO) throws Exception {
		return gamFcltsMngGroupDao.selectFcltsMngGroupList(searchVO);
	}
	
	/**
	 * 시설물관리그룹 목록 총수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectFcltsMngGroupListTotCnt(GamFcltsMngGroupVO searchVO) throws Exception {
		return gamFcltsMngGroupDao.selectFcltsMngGroupListTotCnt(searchVO);
	}
	
	/**
	 * 시설물 관리그룹 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	public EgovMap selectFcltsMngGroupDetail(Map searchVO) throws Exception {
		return gamFcltsMngGroupDao.selectFcltsMngGroupDetail(searchVO);
	}
	
	/**
	 * 시설물관리그룹 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertFcltsMngGroupDetail(Map<String, Object> vo) throws Exception {
		gamFcltsMngGroupDao.insertFcltsMngGroupDetail(vo);
	}
	
	/**
	 * 시설물관리그룹 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateFcltsMngGroupDetail(Map<String, Object> vo) throws Exception {
		gamFcltsMngGroupDao.updateFcltsMngGroupDetail(vo);
	}
	
	/**
	 * 시설물관리그룹 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteFcltsMngGroupDetail(Map<String, Object> vo) throws Exception {
		gamFcltsMngGroupDao.deleteFcltsMngGroupDetail(vo);
	}
}
