package egovframework.rte.ygpa.erp.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ygpa.erp.code.service.ErpAssetDeprctnService;
import egovframework.rte.ygpa.erp.code.service.ErpAssetDeprctnDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetDeprctnVO;
import egovframework.rte.ygpa.erp.code.service.impl.ErpAssetDeprctnDAO;

/**
 * @Class Name : ErpAssetDeprctnServiceImpl.java
 * @Description : ErpAssetDeprctn Business Implement class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("erpAssetDeprctnService")
public class ErpAssetDeprctnServiceImpl extends AbstractServiceImpl implements
        ErpAssetDeprctnService {

    @Resource(name="erpAssetDeprctnDAO")
    private ErpAssetDeprctnDAO erpAssetDeprctnDAO;
    
    /** ID Generation */
    //@Resource(name="{egovErpAssetDeprctnIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * ERP_ASSET_DEPRCTN을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpAssetDeprctnVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertErpAssetDeprctn(ErpAssetDeprctnVO vo) throws Exception {
    	log.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	log.debug(vo.toString());
    	
    	erpAssetDeprctnDAO.insertErpAssetDeprctn(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * ERP_ASSET_DEPRCTN을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpAssetDeprctnVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateErpAssetDeprctn(ErpAssetDeprctnVO vo) throws Exception {
        erpAssetDeprctnDAO.updateErpAssetDeprctn(vo);
    }

    /**
	 * ERP_ASSET_DEPRCTN을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpAssetDeprctnVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteErpAssetDeprctn(ErpAssetDeprctnVO vo) throws Exception {
        erpAssetDeprctnDAO.deleteErpAssetDeprctn(vo);
    }

    /**
	 * ERP_ASSET_DEPRCTN을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpAssetDeprctnVO
	 * @return 조회한 ERP_ASSET_DEPRCTN
	 * @exception Exception
	 */
    public ErpAssetDeprctnVO selectErpAssetDeprctn(ErpAssetDeprctnVO vo) throws Exception {
        ErpAssetDeprctnVO resultVO = erpAssetDeprctnDAO.selectErpAssetDeprctn(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * ERP_ASSET_DEPRCTN 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_DEPRCTN 목록
	 * @exception Exception
	 */
    public List selectErpAssetDeprctnList(ErpAssetDeprctnDefaultVO searchVO) throws Exception {
        return erpAssetDeprctnDAO.selectErpAssetDeprctnList(searchVO);
    }

    /**
	 * ERP_ASSET_DEPRCTN 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_DEPRCTN 총 갯수
	 * @exception
	 */
    public int selectErpAssetDeprctnListTotCnt(ErpAssetDeprctnDefaultVO searchVO) {
		return erpAssetDeprctnDAO.selectErpAssetDeprctnListTotCnt(searchVO);
	}
    
}
