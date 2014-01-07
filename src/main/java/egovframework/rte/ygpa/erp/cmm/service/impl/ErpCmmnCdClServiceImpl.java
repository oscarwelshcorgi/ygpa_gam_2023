package egovframework.rte.ygpa.erp.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdClService;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdClDefaultVO;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdClVO;
import egovframework.rte.ygpa.erp.cmm.service.impl.ErpCmmnCdClDAO;

/**
 * @Class Name : ErpCmmnCdClServiceImpl.java
 * @Description : ErpCmmnCdCl Business Implement class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("erpCmmnCdClService")
public class ErpCmmnCdClServiceImpl extends AbstractServiceImpl implements
        ErpCmmnCdClService {

    @Resource(name="erpCmmnCdClDAO")
    private ErpCmmnCdClDAO erpCmmnCdClDAO;
    
    /** ID Generation */
    //@Resource(name="{egovErpCmmnCdClIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * ERP_CMMN_CD_CL을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpCmmnCdClVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertErpCmmnCdCl(ErpCmmnCdClVO vo) throws Exception {
    	log.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	log.debug(vo.toString());
    	
    	erpCmmnCdClDAO.insertErpCmmnCdCl(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * ERP_CMMN_CD_CL을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpCmmnCdClVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateErpCmmnCdCl(ErpCmmnCdClVO vo) throws Exception {
        erpCmmnCdClDAO.updateErpCmmnCdCl(vo);
    }

    /**
	 * ERP_CMMN_CD_CL을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpCmmnCdClVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteErpCmmnCdCl(ErpCmmnCdClVO vo) throws Exception {
        erpCmmnCdClDAO.deleteErpCmmnCdCl(vo);
    }

    /**
	 * ERP_CMMN_CD_CL을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpCmmnCdClVO
	 * @return 조회한 ERP_CMMN_CD_CL
	 * @exception Exception
	 */
    public ErpCmmnCdClVO selectErpCmmnCdCl(ErpCmmnCdClVO vo) throws Exception {
        ErpCmmnCdClVO resultVO = erpCmmnCdClDAO.selectErpCmmnCdCl(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * ERP_CMMN_CD_CL 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_CMMN_CD_CL 목록
	 * @exception Exception
	 */
    public List selectErpCmmnCdClList(ErpCmmnCdClDefaultVO searchVO) throws Exception {
        return erpCmmnCdClDAO.selectErpCmmnCdClList(searchVO);
    }

    /**
	 * ERP_CMMN_CD_CL 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_CMMN_CD_CL 총 갯수
	 * @exception
	 */
    public int selectErpCmmnCdClListTotCnt(ErpCmmnCdClDefaultVO searchVO) {
		return erpCmmnCdClDAO.selectErpCmmnCdClListTotCnt(searchVO);
	}
    
}
