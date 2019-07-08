/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 20.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 20.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamAssetsUsePermMngtDAO")
public class GamAssetsUsePermMngtDAO extends YGPAAbstractDAO {
	/**
	 * 사용정보를 불러온다.
	 */
	public EgovMap selectAssetRentByPk(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamAssetsUsePermMngtDao.selectAssetRentByPk_S", vo);
    }

	/**
	 * 사용승낙을 처리한다.
	 */
	public void confirmAssetUsePerm(Map<String, Object> vo) throws Exception {
        update("gamAssetsUsePermMngtDao.confirmAssetsUsePerm", vo);
    }

	/**
	 * 사용승낙을 취소한다.
	 */
	public void cancelAssetUsePerm(Map<String, Object> vo) throws Exception {
        update("gamAssetsUsePermMngtDao.cancelAssetsUsePerm", vo);
    }

	/**
	 * 전체 사용료를 등록한다.
	 */
    public String insertBillCreateOnce(Map<String, Object> vo) throws Exception {
        return (String)insert("gamAssetsUsePermMngtDao.insertBillCreateOnce", vo);
    }

    /**
	 * 월 사용료를 등록한다.
	 */
    public String insertBillCreatePreMonth(Map<String, Object> vo) throws Exception {
		vo.put("blTpNum", "1");
        return (String)insert("gamAssetsUsePermMngtDao.insertBillCreateMore", vo);
    }

    /**
	 * 3분납 사용료를 등록한다.
	 */
    public String insertBillCreatePreThird(Map<String, Object> vo) throws Exception {
		vo.put("blTpNum", "4");
        return (String)insert("gamAssetsUsePermMngtDao.insertBillCreateMore", vo);
    }

    /**
	 * 분기별 사용료를 등록한다.
	 */
    public String insertBillCreatePreQuater(Map<String, Object> vo) throws Exception {
		vo.put("blTpNum", "3");
        return (String)insert("gamAssetsUsePermMngtDao.insertBillCreateMore", vo);
    }

    /**
	 * 반기별 사용료를 등록한다.
	 */
    public String insertBillCreatePreHalf(Map<String, Object> vo) throws Exception {
		vo.put("blTpNum", "6");
        return (String)insert("gamAssetsUsePermMngtDao.insertBillCreateMore", vo);
    }

    /**
	 * 연별 사용료를 등록한다.
	 */
    public String insertBillCreatePreYear(Map<String, Object> vo) throws Exception {
		vo.put("blTpNum", "12");
        return (String)insert("gamAssetsUsePermMngtDao.insertBillCreateMore", vo);
    }

    /**
     * 사용료 정보를 삭제한다.
     * @param vo
     * @throws Exception
     */
    public void deleteLevRequest(Map<String, Object> vo) throws Exception {
        delete("gamAssetsUsePermMngtDao.deleteLevRequest_D", vo);
    }

    /**
     * 사용료 정보를 한건 삭제한다.
     * @param vo
     * @throws Exception
     */
    public void deleteLevRequestSingle(Map<String, Object> vo) throws Exception {
        delete("gamAssetsUsePermMngtDao.deleteLevRequest_S", vo);
    }

    /**
     * 사용료 통계 정보를 삭제한다.
     * @param vo
     * @throws Exception
     */
    public void deleteAssetsUsagePdByStats(Map<String, Object> vo) throws Exception {
    	delete("gamAssetsUsePermMngtDao.deleteAssetsUsagePdByStats_D", vo);
    }

    /**
     * 사용료 통계 정보를 생성한다.
     * @param vo
     * @throws Exception
     */
    public String insertAssetsUsagePdByStats(Map<String, Object> vo) throws Exception {
        return (String)insert("gamAssetsUsePermMngtDao.insertAssetsUsagePdByStats_D", vo);
    }

    /**
     * 추가고지 정보를 생성한다.
     * @param vo
     * @return
     * @throws Exception
     */
    public String insertBillCreateAdit(Map<String, Object> vo) throws Exception {
        return (String)insert("gamAssetsUsePermMngtDao.insertBillCreateAdit", vo);
    }

	/**
	 * @return
	 */
	public Map selectElctrnSanctnPk() {
		// TODO Auto-generated method stub
		return (EgovMap) selectByPk("gamAssetsUsePermMngtDao.selectElctrnSanctnPk", null);
	}

	/**
	 * @param paramMap
	 * @return 
	 */
	public String insertElctrnSanctn(Map paramMap) {
		// TODO Auto-generated method stub
		return (String)insert("gamAssetsUsePermMngtDao.insertElctrnSanctn", paramMap);
	}

	/**
	 * @param paramMap
	 */
	public void insertGwcallFwdIf(Map paramMap) {
		// TODO Auto-generated method stub
		insert("gamAssetsUsePermMngtDao.insertGwcallFwdIf", paramMap);
	}

	/**
	 * @param paramMap
	 */
	public void updateAssetsRentF(Map paramMap) {
		// TODO Auto-generated method stub
		update("gamAssetsUsePermMngtDao.updateAssetsRentF", paramMap);
	}

}
