/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldQuGtqyVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngtMainVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentRntfeeVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 10.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamHtldRentMngtMainDao")
public class GamHtldRentMngtMainDao extends YGPAAbstractDAO {
	
	/**
	 * 배후단지 임대 상세목록 조회
	 * @param 
	 * @return List 임대상세목록
	 * @exception Exception
	 */
	public List<?> selectHtldRentDetailList(GamHtldRentMngtMainVO vo) throws Exception {
		return (List<?>) list("gamHtldRentMngtMainDao.selectHtldRentDetailList_D", vo);
	}
	
	/**
	 *  Cofix 이자율 조회
	 * @param 
	 * @return String 이자율
	 * @exception Exception
	 */
	public String selectCofixIntrrate(GamHtldRentMngtMainVO vo) throws Exception {
		return (String)selectByPk("gamHtldRentMngtMainDao.selectCofixIntrrate_S", vo);
	}
		
	/**
	 * 임대료 등록
	 * @param GamHtldRntfeeVO
	 * @return 
	 * @exception Exception
	 */
	public void insertHtldRntfee(GamHtldRentRntfeeVO vo) throws Exception {
		insert("gamHtldRentMngtMainDao.insertHtldRntfee_S", vo);
	}

	/**
	 * 임대료 수정
	 * @param GamHtldRntfeeVO
	 * @return 
	 * @exception Exception
	 */
	public void updateHtldRntfee(GamHtldRentRntfeeVO vo) throws Exception {
		insert("gamHtldRentMngtMainDao.updateHtldRntfee_S", vo);
	}

	/**
	 * 임대료 순번 생성
	 * @param GamHtldRntfeeVO
	 * @return 임대료 순번
	 * @throws Exception
	 */
	public String selectNextRntfeeSeq(GamHtldRentRntfeeVO searchVO) throws Exception {
		return (String) getSqlMapClientTemplate().queryForObject("gamHtldRentMngtMainDao.selectNextRntfeeSeq_S", searchVO);
	}


	/**
	 * @param nowYear
	 */
	public void deleteHtldRentData(Map deleteRentData) throws Exception{
		// TODO Auto-generated method stub
		delete("gamHtldRentMngtMainDao.deleteHtldRentData", deleteRentData);
	}

	/**
	 * @param nowYear
	 */
	public void deleteHtldRentDetailData(Map deleteRentData) throws Exception{
		// TODO Auto-generated method stub
		delete("gamHtldRentMngtMainDao.deleteHtldRentDetailData", deleteRentData);
	}
	
	/**
	 * @param nowYear
	 */
	public String inserHtldRentData(Map param) throws Exception{
		// TODO Auto-generated method stub
		return (String)insert("gamHtldRentMngtMainDao.inserHtldRentData", param); 
		
	}

	/**
	 * @param nowYear
	 */
	public void inserHtldRentDetailData(Map param) throws Exception{
		// TODO Auto-generated method stub
		insert("gamHtldRentMngtMainDao.inserHtldRentDetailData", param);
		
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectHtldQuGtqyList(GamHtldQuGtqyVO searchVO) throws Exception{
		// TODO Auto-generated method stub
		return list("gamHtldRentMngtMainDao.selectHtldQuGtqyList", searchVO);
	}

	/**
	 * @param vo
	 */
	public void deleteHtldQuGtqyList(GamHtldQuGtqyVO vo) throws Exception{
		// TODO Auto-generated method stub
		delete("gamHtldRentMngtMainDao.deleteHtldQuGtqyList", vo);
	}

	/**
	 * @param vo
	 */
	public void updateHtldQuGtqyList(GamHtldQuGtqyVO vo) throws Exception{
		// TODO Auto-generated method stub
		update("gamHtldRentMngtMainDao.updateHtldQuGtqyList", vo);
	}

	/**
	 * @param vo
	 */
	public void insertHtldQuGtqyList(GamHtldQuGtqyVO vo) throws Exception{
		// TODO Auto-generated method stub
		insert("gamHtldRentMngtMainDao.insertHtldQuGtqyList", vo);
	}

	/**
	 * @return
	 */
	public List deleteHtldRentList() throws Exception{
		// TODO Auto-generated method stub
		return  (List<?>) list("gamHtldRentMngtMainDao.deleteHtldRentList", null);
	}




}
