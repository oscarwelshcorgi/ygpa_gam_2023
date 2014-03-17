/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.code.service.GamEntrpsChargerFVO;
import egovframework.rte.ygpa.gam.code.service.GamEntrpsInfoFVO;

/**
 * 
 * @author kok
 * @since 2014. 3. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 5.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamCmpyInfoMngtDao")
public class GamCmpyInfoMngtDao extends YGPAAbstractDAO{

	/**
	 * 업체정보관리 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<GamEntrpsInfoFVO> selectCmpyInfoMngtList(GamEntrpsInfoFVO vo) throws Exception{
		return list("gamCmpyInfoMngtDao.selectCmpyInfoMngtList", vo);
	}

	
	/**
	 * 업체정보관리 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectCmpyInfoMngtListTotCnt(GamEntrpsInfoFVO vo) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("gamCmpyInfoMngtDao.selectCmpyInfoMngtListTotCnt", vo);
	}
	
	
	/**
	 * 업체관리 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<GamEntrpsChargerFVO> selectCmpyMngtList(GamEntrpsChargerFVO vo) throws Exception{
		return list("gamCmpyInfoMngtDao.selectCmpyMngtList", vo);
	}
	
	
	/**
	 * 업체관리 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectCmpyMngtListTotCnt(GamEntrpsChargerFVO vo) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("gamCmpyInfoMngtDao.selectCmpyMngtListTotCnt", vo);
	}

	
	/**
	 * 업체정보관리 상세화면
	 * @param vo
	 * @return vo
	 * @throws Exception
	 */
	public GamEntrpsInfoFVO selectCmpyInfoMngtDetail(GamEntrpsInfoFVO vo) throws Exception{
		return (GamEntrpsInfoFVO) selectByPk("gamCmpyInfoMngtDao.selectCmpyInfoMngtDetail", vo);
	}
	
	
	/**
	 * 업체담당자 정보 상세화면
	 * @param vo
	 * @return vo
	 * @throws Exception
	 */
	public GamEntrpsChargerFVO selectCmpyMngtDetail(GamEntrpsChargerFVO vo) throws Exception{
		return (GamEntrpsChargerFVO) selectByPk("gamCmpyInfoMngtDao.selectCmpyMngtDetail", vo);
	}
	
	
	/**
	 * 업체정보 관리 저장
	 * @param vo
	 */
	public void insertCmpyInfo(HashMap<String,String> form){
		insert("gamCmpyInfoMngtDao.insertCmpyInfo", form);
	}

	
	/**
	 * 업체 담당자 저장
	 * @param vo
	 */
	public void insertCmpyCharger(Map<String,String> insertList){
		insert("gamCmpyInfoMngtDao.insertCmpyCharger", insertList);
	}
	
	
	/**
	 * 업체정보 관리 수정
	 * @param vo
	 */
	public void updateCmpyInfo(HashMap<String,String> form){
		update("gamCmpyInfoMngtDao.updateCmpyInfo", form);
	}
	
	
	/**
	 * 업체 담당자 수정
	 * @param vo
	 */
	public void updateCmpyCharger(Map<String,String> updateList){
		update("gamCmpyInfoMngtDao.updateCmpyCharger", updateList);
	}
	
	
	/**
	 * 업체정보 관리 삭제
	 * @param vo
	 */
	public void deleteCmpyInfo(String entrpscd){
		delete("gamCmpyInfoMngtDao.deleteCmpyInfo", entrpscd);
	}
	
	
	/**
	 * 업체 담당자 삭제
	 * @param vo
	 */
	public void deleteCmpyCharger(Map<String,String> deleteList){
		delete("gamCmpyInfoMngtDao.deleteCmpyCharger", deleteList);
	}

	
	/**
	 * 업체코드 체크
	 * @param vo
	 */
	public int checkEntrpscd(String entrpscd){
		return (Integer)getSqlMapClientTemplate().queryForObject("gamCmpyInfoMngtDao.checkEntrpscd", entrpscd);
	}
}