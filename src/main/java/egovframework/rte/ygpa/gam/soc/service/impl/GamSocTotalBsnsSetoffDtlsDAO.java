/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.cmmn.itgrn.service.GamNticPayListVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocTotalBsnsSetoffDtlsVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 13.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 13.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamSocTotalBsnsSetoffDtlsDAO")
public class GamSocTotalBsnsSetoffDtlsDAO extends YGPAAbstractDAO {
	
	
	/**
	 * 총사업비상계처리내역 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총사업비상계처리내역 목록
	 * @exception Exception
	 */
    List selectSocTotalBsnsSetoffDtlsList(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception{
    	return list("gamSocTotalBsnsSetoffDtlsDAO.selectSocTotalBsnsSetoffDtlsList_D", searchVO);
    }
    
    
    /**
	 * 총사업비상계처리내역 목록 총갯수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총사업비상계처리내역 목록 총갯수
	 * @exception Exception
	 */
    int selectSocTotalBsnsSetoffDtlsListTotCnt(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception{
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamSocTotalBsnsSetoffDtlsDAO.selectSocTotalBsnsSetoffDtlsListTotCnt_S", searchVO);
    }
    
    
    
    /**
	 * 총사업비상계처리상세내역 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총사업비상계처리상세내역 목록
	 * @exception Exception
	 */
    List selectSocTotalBsnsSetoffDtlsDetail(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception{
    	return list("gamSocTotalBsnsSetoffDtlsDAO.selectSocTotalBsnsSetoffDtlsDetail_D", searchVO);
    }
    
    
    
    /**
	 * 총사업비상계처리상세내역 총갯수 및 금액합계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총사업비상계처리상세내역 총갯수 및 금액합계
	 * @exception Exception
	 */
    GamSocTotalBsnsSetoffDtlsVO selectSocTotalBsnsSetoffDtlsDetailSum(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception{
    	return (GamSocTotalBsnsSetoffDtlsVO) selectByPk("gamSocTotalBsnsSetoffDtlsDAO.selectSocTotalBsnsSetoffDtlsDetailSum_S", searchVO);
    }
    
    
    /**
	 * 총사업비상계처리내역 프린트목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총사업비상계처리내역 프린트목록
	 * @exception Exception
	 */
    List selectSocTotalBsnsSetoffDtlsListPrint(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception{
    	return list("gamSocTotalBsnsSetoffDtlsDAO.selectSocTotalBsnsSetoffDtlsListPrint_D", searchVO);
    }
    
    
    /**
	 * 총사업비상계처리내역 프린트목록 총갯수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총사업비상계처리내역 프린트목록 총갯수
	 * @exception Exception
	 */
    int selectSocTotalBsnsSetoffDtlsListPrintTotCnt(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception{
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamSocTotalBsnsSetoffDtlsDAO.selectSocTotalBsnsSetoffDtlsListPrintTotCnt_S", searchVO);
    }

}
