/**
 *
 */
package egovframework.rte.ygpa.gam.asset.sts.service;

import java.util.List;
import java.util.Map;


/**
 * GIS자산통계 조회 서비스
 * @author EUNSUNGJ
 * @since 2014. 11. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 24.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamGisAssetSttusInqireService {
    /**
	 * GIS자산통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectGisAssetSttusList(Map searchVO) throws Exception;

    /**
     * GIS자산통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetSttusListTotCnt(Map searchVO) throws Exception;

    /**
     * GIS자산통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetSttusListTotSum(Map searchVO) throws Exception;

    /**
     * 자산 코드에 대한 감가상각 정보 리스트를 조회 한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectAssetSttusInfoByCode(Map searchVO) throws Exception;

    /**
     * 자산 코드에 대한 감가상각 정보 리스트를 조회 한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    List selectAssetSttusDeprctnListByCode(Map searchVO) throws Exception;

    /**
	 * GIS자산 사용현황 통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectGisAssetRentSttusList(Map searchVO) throws Exception;

    /**
     * GIS자산 사용현황 통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetRentSttusListTotCnt(Map searchVO) throws Exception;

    /**
     * GIS자산 사용현황 통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetRentSttusListTotSum(Map searchVO) throws Exception;

    /**
     * 자산코드에 대한 임대현황 정보를 로딩한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectAssetRentSttusInfoByCode(Map searchVO) throws Exception;

    /**
     * 자산코드에 대한 업체별 임대현황 목록을 로딩한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    List selectAssetRentSttusListByCode(Map searchVO) throws Exception;

    /**
     * 시설에 대한 임대 정보를 조회 한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    List selectGisAssetRentSttusByFcltyList(Map searchVO) throws Exception;

    /**
	 * GIS자산 사용료현황 통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectGisAssetRentFeeSttusList(Map searchVO) throws Exception;

    /**
     * GIS자산 사용료현황 통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetRentFeeSttusListTotCnt(Map searchVO) throws Exception;

    /**
     * GIS자산 사용료현황 통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetRentFeeSttusListTotSum(Map searchVO) throws Exception;

    /**
     * 자산코드에 대한 임대료현황 정보를 로딩한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectAssetRentFeeSttusInfoByCode(Map searchVO) throws Exception;

    /**
     * 자산코드에 대한 업체별 임대료현황 목록을 로딩한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    List selectAssetRentFeeSttusListByCode(Map searchVO) throws Exception;


    /**
	 * GIS자산 유지보수현황 통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectGisAssetMntnRprSttusAssetList(Map searchVO) throws Exception;

    /**
     * GIS자산 유지보수현황 통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetMntnRprSttusAssetListTotCnt(Map searchVO) throws Exception;

    /**
     * GIS자산 유지보수현황 통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetMntnRprSttusAssetListTotSum(Map searchVO) throws Exception;

    /**
	 * GIS자산 하자보수현황 통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectGisAssetFlawRprSttusAssetList(Map searchVO) throws Exception;

    /**
     * GIS자산 하자보수현황 통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetFlawRprSttusAssetListTotCnt(Map searchVO) throws Exception;

    /**
     * GIS자산 하자보수현황 통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetFlawRprSttusAssetListTotSum(Map searchVO) throws Exception;

}
