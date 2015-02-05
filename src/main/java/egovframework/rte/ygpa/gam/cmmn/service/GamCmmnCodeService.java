package egovframework.rte.ygpa.gam.cmmn.service;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;

/**
 * 공통 코드 서비스
 * @author EUNSUNGJ
 * @since 2015. 2. 4.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 4.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
public interface GamCmmnCodeService {

    /**
     * 항코드에 대한 위치 코드를 조회한다.
     *
     * @param vo
     * @return List(코드)
     * @throws Exception
     */
    public List selectLocCodeList(Map vo) throws Exception;

    /**
     * 위치 코드에 대한 부두코드를 조회한다.
     *
     * @param vo
     * @return List(코드)
     * @throws Exception
     */
    public List selectQuayCodeList(Map vo) throws Exception;

    /**
     * 공통코드에서 데이터를 필터링 하여 조회 한다.
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectCmmnCdCodeList(Map vo) throws Exception;

}
