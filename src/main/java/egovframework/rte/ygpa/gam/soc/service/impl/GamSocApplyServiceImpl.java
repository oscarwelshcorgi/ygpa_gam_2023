/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.soc.service.GamSocApplyService;
import egovframework.rte.ygpa.gam.soc.service.GamSocApplyVO;
/**
 * 
 * @author 김종민
 * @since 2014. 10. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 7.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamSocApplyService")
public class GamSocApplyServiceImpl implements GamSocApplyService {
	@Resource(name="gamSocApplyDao")
	private GamSocApplyDAO gamSocApplyDao;
	
	/**
	 * 면제요청내역관리 데이터를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 면제요청내역관리 VO
	 * @exception
	 */
	public GamSocApplyVO selectSocApplyDetailInquire(GamSocApplyVO searchVO) {
		return gamSocApplyDao.selectSocApplyDetailInquire(searchVO);
	}

	/**
	 * 면제요청내역 리스트를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 면제요청내역 리스트
	 * @exception
	 */
	public List<?> selectSocApplyList(GamSocApplyVO searchVO) {
		return gamSocApplyDao.selectSocApplyList(searchVO);
	}

	/**
	 * 면제요청내역 리스트의 총 개수를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	public int selectSocApplyListTotCnt(GamSocApplyVO searchVO) {
		return gamSocApplyDao.selectSocApplyListTotCnt(searchVO);
	}

	/**
	 * 면제요청내역관리 데이터를 삽입한다. 
	 * @param insertVO - 삽입할 정보가 담긴 VO
	 * @return 
	 * @exception
	 */
	public void insertSocApplyDetail(Map<?, ?> insertMap) {
		gamSocApplyDao.insertSocApplyDetail(insertMap);
	}

	/**
	 * 면제요청내역관리 데이터를 수정한다. 
	 * @param updateVO - 수정할 정보가 담긴 VO
	 * @return 
	 * @exception
	 */	
	public void updateSocApplyDetail(Map<?, ?> updateMap) {
		gamSocApplyDao.updateSocApplyDetail(updateMap);
	}

	/**
	 * 면제요청내역관리 데이터를 삭제한다. 
	 * @param deleteVO - 삭제할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	public void deleteSocApplyDetail(Map<?, ?> deleteMap) {
		gamSocApplyDao.deleteSocApplyDetail(deleteMap);
	}
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물리스트를  조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	public List<?> selectSocApplyFacilList(GamSocApplyVO searchVO) {
		return gamSocApplyDao.selectSocApplyFacilList(searchVO);
	}

	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물리스트 총갯슈를  조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	public int selectSocApplyFacilListTotCnt(GamSocApplyVO searchVO) {
		return gamSocApplyDao.selectSocApplyFacilListTotCnt(searchVO);
	}
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물데이터 유무를  조회한다. 
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return
	 * @exception
	 */	
	public int selectSocApplyFacilInfoCnt(Map<?, ?> searchMap) {
		return gamSocApplyDao.selectSocApplyFacilInfoCnt(searchMap);
	}
	
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물데이터를 삽입한다. 
	 * @param insertMap - 삽입할 정보가 담긴 Map
	 * @return
	 * @exception
	 */	
	public void insertSocApplyFacilInfo(Map<?, ?> insertMap) {
		gamSocApplyDao.insertSocApplyFacilInfo(insertMap);
	}
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물데이터전체를 삭제한다. 
	 * @param deleteVO - 삭제할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	public void deleteSocApplyFacilList(Map<?, ?> deleteMap) {
		gamSocApplyDao.deleteSocApplyFacilList(deleteMap);
	}

	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류리스트를  조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	public List<?> selectSocApplyFeeList(GamSocApplyVO searchVO) {
		return gamSocApplyDao.selectSocApplyFeeList(searchVO);
	}

	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류리스트 총갯슈를  조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	public int selectSocApplyFeeListTotCnt(GamSocApplyVO searchVO) {
		return gamSocApplyDao.selectSocApplyFacilListTotCnt(searchVO);
	}
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류데이터 유무를  조회한다. 
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return
	 * @exception
	 */	
	public int selectSocApplyFeeInfoCnt(Map<?, ?> searchMap) {
		return gamSocApplyDao.selectSocApplyFeeInfoCnt(searchMap);
	}
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류데이터를 삽입한다. 
	 * @param insertMap - 삽입할 정보가 담긴 Map
	 * @return
	 * @exception
	 */	
	public void insertSocApplyFeeInfo(Map<?, ?> insertMap) {
		gamSocApplyDao.insertSocApplyFeeInfo(insertMap);
	}
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류데이터전체를 삭제한다. 
	 * @param deleteVO - 삭제할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	public void deleteSocApplyFeeList(Map<?, ?> deleteMap) {
		gamSocApplyDao.deleteSocApplyFeeList(deleteMap);
	}

}
