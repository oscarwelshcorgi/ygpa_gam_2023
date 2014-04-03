package egovframework.rte.ygpa.erp.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdService;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;
import egovframework.rte.ygpa.erp.code.service.impl.ErpAssetCdDAO;

/**
 * @Class Name : ErpAssetCdServiceImpl.java
 * @Description : ErpAssetCd Business Implement class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Service("erpAssetCdService")
public class ErpAssetCdServiceImpl extends AbstractServiceImpl implements
        ErpAssetCdService {

    @Resource(name="erpAssetCdDAO")
    private ErpAssetCdDAO erpAssetCdDAO;

    /** ID Generation */
    //@Resource(name="{egovErpAssetCdIdGnrService}")
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * ERP_ASSET_CD을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpAssetCdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertErpAssetCd(ErpAssetCdVO vo) throws Exception {
    	log.debug(vo.toString());

    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	log.debug(vo.toString());

    	erpAssetCdDAO.insertErpAssetCd(vo);
    	//TODO 해당 테이블 정보에 맞게 수정
        return null;
    }

    /**
	 * ERP_ASSET_CD을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpAssetCdVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateErpAssetCd(ErpAssetCdVO vo) throws Exception {
        erpAssetCdDAO.updateErpAssetCd(vo);
    }

    /**
	 * ERP_ASSET_CD을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpAssetCdVO
	 * @return void형
	 * @exception Exception
	 */
    public void deleteErpAssetCd(ErpAssetCdVO vo) throws Exception {
        erpAssetCdDAO.deleteErpAssetCd(vo);
    }

    /**
	 * ERP_ASSET_CD을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpAssetCdVO
	 * @return 조회한 ERP_ASSET_CD
	 * @exception Exception
	 */
    public ErpAssetCdVO selectErpAssetCd(ErpAssetCdVO vo) throws Exception {
        ErpAssetCdVO resultVO = erpAssetCdDAO.selectErpAssetCd(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * ERP_ASSET_CD 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_CD 목록
	 * @exception Exception
	 */
    public List selectErpAssetCdList(ErpAssetCdDefaultVO searchVO) throws Exception {
        return erpAssetCdDAO.selectErpAssetCdList(searchVO);
    }

    /**
	 * ERP_ASSET_CD 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_CD 총 갯수
	 * @exception
	 */
    public int selectErpAssetCdListTotCnt(ErpAssetCdDefaultVO searchVO) {
		return erpAssetCdDAO.selectErpAssetCdListTotCnt(searchVO);
	}

    public List selectErpAssetCdPopupList(ErpAssetCdDefaultVO searchVO) throws Exception {
        return erpAssetCdDAO.selectErpAssetCdPopupList(searchVO);
    }

    public int selectErpAssetCdPopupListTotCnt(ErpAssetCdDefaultVO searchVO) {
		return erpAssetCdDAO.selectErpAssetCdPopupListTotCnt(searchVO);
	}

}
