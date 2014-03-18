package egovframework.rte.ygpa.eap.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.eap.service.EapGwCallInterfaceService;

/**
 * @Class Name : EapGwCallInterfaceServiceImpl.java
 * @Description : EapGwCallInterface Business Implement class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Service("eapGwCallInterfaceService")
public class EapGwCallInterfaceServiceImpl extends AbstractServiceImpl implements
        EapGwCallInterfaceService {

    @Resource(name="eapGwCallInterfaceDAO")
    private EapGwCallInterfaceDAO eapGwCallInterfaceDAO;

    /** ID Generation */
    //@Resource(name="{egovEapGwCallInterfaceIdGnrService}")
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * ERP_ASSET_CATE을 등록한다.
	 * @param vo - 등록할 정보가 담긴 EapGwCallInterfaceVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertEapGwCallInterface(Map<String, Object> vo) throws Exception {
    	log.debug(vo.toString());

    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	log.debug(vo.toString());


    	//TODO 해당 테이블 정보에 맞게 수정
        return eapGwCallInterfaceDAO.insertGwCallInterface(vo);
    }

    /**
	 * ERP_ASSET_CATE을 수정한다.
	 * @param vo - 수정할 정보가 담긴 EapGwCallInterfaceVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateEapGwCallInterface(Map<String, Object> vo) throws Exception {
        eapGwCallInterfaceDAO.updateGwCallInterface(vo);
    }

    /**
	 * ERP_ASSET_CATE을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 EapGwCallInterfaceVO
	 * @return void형
	 * @exception Exception
	 */
    public void deleteEapGwCallInterface(Map<String, Object> vo) throws Exception {
        eapGwCallInterfaceDAO.deleteGwCallInterface(vo);
    }

    /**
	 * ERP_ASSET_CATE을 조회한다.
	 * @param vo - 조회할 정보가 담긴 EapGwCallInterfaceVO
	 * @return 조회한 ERP_ASSET_CATE
	 * @exception Exception
	 */
    public EgovMap selectEapGwCallInterface(Map<String, Object> vo) throws Exception {
    	EgovMap resultVO = eapGwCallInterfaceDAO.selectGwCallInterface(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * ERP_ASSET_CATE 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_CATE 목록
	 * @exception Exception
	 */
    public List selectEapGwCallInterfaceList(Map<String, Object> searchVO) throws Exception {
        return eapGwCallInterfaceDAO.selectGwCallInterfaceList(searchVO);
    }

    /**
	 * ERP_ASSET_CATE 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_CATE 총 갯수
	 * @exception
	 */
    public int selectEapGwCallInterfaceListTotCnt(Map<String, Object> searchVO) {
		return eapGwCallInterfaceDAO.selectGwCallInterfaceListTotCnt(searchVO);
	}

}
