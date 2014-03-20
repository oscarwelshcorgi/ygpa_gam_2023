/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;

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
	 * 전체 사용료를 등록한다.
	 */
    public String insertBillCreateOnce(Map<String, Object> vo) throws Exception {
        return (String)insert("GamAssetsUsePermMngtDAO.insertBillCreateOnce", vo);
    }

    /**
	 * 월 사용료를 등록한다.
	 */
    public String insertBillCreatePreMonth(Map<String, Object> vo) throws Exception {
		vo.put("blTpNum", "1");
        return (String)insert("GamAssetsUsePermMngtDAO.insertBillCreateMore", vo);
    }

    /**
	 * 3분납 사용료를 등록한다.
	 */
    public String insertBillCreatePreThird(Map<String, Object> vo) throws Exception {
		vo.put("blTpNum", "4");
        return (String)insert("GamAssetsUsePermMngtDAO.insertBillCreateMore", vo);
    }

    /**
	 * 분기별 사용료를 등록한다.
	 */
    public String insertBillCreatePreQuater(Map<String, Object> vo) throws Exception {
		vo.put("blTpNum", "3");
        return (String)insert("GamAssetsUsePermMngtDAO.insertBillCreateMore", vo);
    }

    /**
	 * 반기별 사용료를 등록한다.
	 */
    public String insertBillCreatePreHalf(Map<String, Object> vo) throws Exception {
		vo.put("blTpNum", "6");
        return (String)insert("GamAssetsUsePermMngtDAO.insertBillCreateMore", vo);
    }

    /**
	 * 연별 사용료를 등록한다.
	 */
    public String insertBillCreatePreYear(Map<String, Object> vo) throws Exception {
		vo.put("blTpNum", "12");
        return (String)insert("GamAssetsUsePermMngtDAO.insertBillCreateMore", vo);
    }
}
