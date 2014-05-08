package egovframework.rte.ygpa.eap.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.eap.service.EapGwCallInterfaceService;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.impl.GamEApprovalRequestDAO;

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

    @Resource(name="gamEApprovalRequestDAO")
    private GamEApprovalRequestDAO gamEApprovalRequestDAO;

    @Resource(name="eapGwCallInterfaceDAO")
    private EapGwCallInterfaceDAO eapGwCallInterfaceDAO;

    /** ID Generation */
    //@Resource(name="{egovEapGwCallInterfaceIdGnrService}")
    //private EgovIdGnrService egovIdGnrService;

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

    public void updateEapGwCallInterface(Map<String, Object> vo) throws Exception {
        eapGwCallInterfaceDAO.updateGwCallInterface(vo);
    }

    public void deleteEapGwCallInterface(Map<String, Object> vo) throws Exception {
        eapGwCallInterfaceDAO.deleteGwCallInterface(vo);
    }

    public EgovMap selectEapGwCallInterface(Map<String, Object> vo) throws Exception {
    	EgovMap resultVO = eapGwCallInterfaceDAO.selectGwCallInterface(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    public List selectEapGwCallInterfaceList(Map<String, Object> searchVO) throws Exception {
        return eapGwCallInterfaceDAO.selectGwCallInterfaceList(searchVO);
    }

    public int selectEapGwCallInterfaceListTotCnt(Map<String, Object> searchVO) {
		return eapGwCallInterfaceDAO.selectGwCallInterfaceListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.eap.service.EapGwCallInterfaceService#eApprovalTest(java.util.Map)
	 */
	@Override
	public void eApprovalTest(Map<String, Object> vo) throws Exception {
		String miskey = (String)vo.get("miskey");
		Map<String, String> map = new HashMap();
		map.put("sanctnSttus", (String)vo.get("testEa"));
		String mode = miskey.substring(5, 8);
		String prtAtCode = miskey.substring(8, 11);
		String mngYear = miskey.substring(11, 15);
		String mngNo = miskey.substring(15, 18);
		String mngCnt = miskey.substring(18);
		if("CNF".equals(mode)) {	// 사용 승낙
			map.put("prtAtCode", prtAtCode);
			map.put("mngYear", mngYear);
			map.put("mngNo", mngNo);
			map.put("mngCnt", mngCnt);
			gamEApprovalRequestDAO.updateAssetRentSanctn(map);	// 결재 코드 삽입
		}
		else if("NTC".equals(mode)) {	// 사용료 고지 (징수결의)
			map.put("prtAtCode", prtAtCode);
			map.put("mngYear", mngYear);
			map.put("mngNo", mngNo);
			String nticCnt = mngCnt.substring(2, 4);
			mngCnt=mngCnt.substring(0, 2);
			map.put("mngCnt", mngCnt);
			map.put("nticCnt", nticCnt);
			gamEApprovalRequestDAO.updateNticIssueSanctn(map);	// 결재 코드 삽입
		}
		eapGwCallInterfaceDAO.updateGwCallInterface(vo);	// 결재 코드 갱신
	}

}
