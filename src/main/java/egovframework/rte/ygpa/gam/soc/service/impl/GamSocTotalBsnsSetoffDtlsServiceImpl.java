/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.soc.service.GamSocTotalBsnsSetoffDtlsService;
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

@Service("gamSocTotalBsnsSetoffDtlsService")
public class GamSocTotalBsnsSetoffDtlsServiceImpl extends AbstractServiceImpl implements GamSocTotalBsnsSetoffDtlsService {
	
	
	@Resource(name="gamSocTotalBsnsSetoffDtlsDAO")
    private GamSocTotalBsnsSetoffDtlsDAO gamSocTotalBsnsSetoffDtlsDAO;
	

	/**
	 * 총사업비상계처리내역 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectSocTotalBsnsSetoffDtlsList(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception {
        return gamSocTotalBsnsSetoffDtlsDAO.selectSocTotalBsnsSetoffDtlsList(searchVO);
    }
    
    
    
    /**
	 * 총사업비상계처리내역 목록 총갯수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총사업비상계처리내역 목록 총갯수
	 * @exception Exception
	 */
    public int selectSocTotalBsnsSetoffDtlsListTotCnt(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception{
    	return gamSocTotalBsnsSetoffDtlsDAO.selectSocTotalBsnsSetoffDtlsListTotCnt(searchVO);
    }
    
    
    
    /**
	 * 총사업비상계처리상세내역 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총사업비상계처리상세내역 목록
	 * @exception Exception
	 */
    public List selectSocTotalBsnsSetoffDtlsDetail(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception{
    	return gamSocTotalBsnsSetoffDtlsDAO.selectSocTotalBsnsSetoffDtlsDetail(searchVO);
    }
    
    
    
    /**
	 * 총사업비상계처리상세내역 총갯수 및 금액합계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총사업비상계처리상세내역 총갯수 및 금액합계
	 * @exception Exception
	 */
    public GamSocTotalBsnsSetoffDtlsVO selectSocTotalBsnsSetoffDtlsDetailSum(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception{
    	return gamSocTotalBsnsSetoffDtlsDAO.selectSocTotalBsnsSetoffDtlsDetailSum(searchVO);
    }
    
    
    /**
	 * 총사업비상계처리내역 프린트목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectSocTotalBsnsSetoffDtlsListPrint(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception {
        return gamSocTotalBsnsSetoffDtlsDAO.selectSocTotalBsnsSetoffDtlsListPrint(searchVO);
    }
    
    
    
    /**
	 * 총사업비상계처리내역 프린트목록 총갯수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총사업비상계처리내역 프린트목록 총갯수
	 * @exception Exception
	 */
    public int selectSocTotalBsnsSetoffDtlsListPrintTotCnt(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception{
    	return gamSocTotalBsnsSetoffDtlsDAO.selectSocTotalBsnsSetoffDtlsListPrintTotCnt(searchVO);
    }

}
