/**
 * 
 */
package egovframework.rte.ygpa.gam.ctrt.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtLgerHistVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamFcltyCtrtLgerHistDao")
public class GamFcltyCtrtLgerHistDao extends YGPAAbstractDAO {
	
	/**
	 * 계약대장목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List<?> selectFcltyCtrtLgerHistList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
        return list("gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistList_D", searchVO);
    }


    /**
	 * 계약대장목록,  합계금액 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대장목록
	 * @exception Exception
	 */
    public GamFcltyCtrtLgerHistVO selectFcltyCtrtLgerHistInfoSum(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
    	return (GamFcltyCtrtLgerHistVO) selectByPk("gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistInfoSum_S", searchVO);
    }
    
    
    /**
	 * 계약대장 상세내역 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대장 상세내역
	 * @exception Exception
	 */
    public GamFcltyCtrtLgerHistVO selectFcltyCtrtLgerHistDetail(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
    	return (GamFcltyCtrtLgerHistVO) selectByPk("gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistDetail_S", searchVO);
    }
    
    
    /**
	 * 계약공동도급목록 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급목록
	 * @exception Exception
	 */
	public List<?> selectFcltyCtrtJoinContrFList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return list("gamFcltyCtrtLgerHistDao.selectFcltyCtrtJoinContrFList_D", searchVO);
	}
	
	
	/**
	 * 계약공동도급목록 총갯수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급목록 총갯수
	 * @exception Exception
	 */
	public int selectFcltyCtrtJoinContrFTotalCnt(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamCustTpSalesSttutsCreatDao.selectFcltyCtrtJoinContrFTotalCnt_S", searchVO);
	}
	
	
	/**
	 * 계약공동도급 상세내역 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급 상세내역
	 * @exception Exception
	 */
	public GamFcltyCtrtLgerHistVO selectFcltyCtrtJoinContrFDetail(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return (GamFcltyCtrtLgerHistVO) selectByPk("gamFcltyCtrtLgerHistDao.selectFcltyCtrtJoinContrFDetail_S", searchVO);
	}
	
	
	/**
	 * 계약변경목록 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대장목록
	 * @exception Exception
	 */
	public List<?> selectFcltyCtrtChangeFList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return list("gamFcltyCtrtLgerHistDao.selectFcltyCtrtChangeFList_D", searchVO);
	}
	
	
	/**
	 * 계약변경목록 총갯수 및 금액합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약변경목록 총갯수 및 금액합계
	 * @exception Exception
	 */
	public GamFcltyCtrtLgerHistVO selectFcltyCtrtChangeFListSum(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return (GamFcltyCtrtLgerHistVO) selectByPk("gamFcltyCtrtLgerHistDao.selectFcltyCtrtChangeFListSum_S", searchVO);
	}
	
	
	/**
	 * 계약대금지급목록 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급목록
	 * @exception Exception
	 */
	public List<?> selectFcltyCtrtMoneyPymntFList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return list("gamFcltyCtrtLgerHistDao.selectFcltyCtrtMoneyPymntFList_D", searchVO);
	}
	
	
	/**
	 * 계약대금지급목록 총갯수 및 금액합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급목록 총갯수 및 금액합계
	 * @exception Exception
	 */
	public GamFcltyCtrtLgerHistVO selectFcltyCtrtMoneyPymntFListSum(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return (GamFcltyCtrtLgerHistVO) selectByPk("gamFcltyCtrtLgerHistDao.selectFcltyCtrtMoneyPymntFListSum_S", searchVO);
	}
	
	
	/**
	 * 계약이행이월목록 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월목록
	 * @exception Exception
	 */
	public List<?> selectFcltyCtrtFulfillCaryFwdFList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return list("gamFcltyCtrtLgerHistDao.selectFcltyCtrtFulfillCaryFwdFList_D", searchVO);
	}
	
	
	/**
	 * 계약이행이월목록 총갯수 및 금액합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월목록 총갯수 및 금액합계
	 * @exception Exception
	 */
	public GamFcltyCtrtLgerHistVO selectFcltyCtrtFulfillCaryFwdFListSum(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return (GamFcltyCtrtLgerHistVO) selectByPk("gamFcltyCtrtLgerHistDao.selectFcltyCtrtFulfillCaryFwdFListSum_S", searchVO);
	}
	
	
	/**
	 * 계약낙찰정보 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약낙찰정보 목록
	 * @exception Exception
	 */
	public List<?> selectFcltyCtrtScsbidInfoHistList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return list("gamFcltyCtrtLgerHistDao.selectFcltyCtrtScsbidInfoHistList_D", searchVO);
	}
	
	/**
	 * 계약낙찰정보 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약낙찰정보 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtScsbidInfoHistListTotCnt(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtLgerHistDao.selectFcltyCtrtScsbidInfoHistListTotCnt_S", searchVO);
	}
	
	

}
