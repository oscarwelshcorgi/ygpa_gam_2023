package egovframework.rte.ygpa.gam.code.service;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @Class Name : ErpAssetCdDefaultVO.java
 * @Description : ErpAssetCd Default VO class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
/**
 *
 * @author EUNSUNGJ
 * @since 2014. 2. 6.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 6.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
/**
 *
 * @author EUNSUNGJ
 * @since 2014. 2. 6.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 6.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
public class GamGisAssetCodeDefaultVO extends GamGisAssetCodeVO {

	/** 검색조건 */
    private String searchCondition = "";
    /** 검색조건 자산,품목구분 */
    private String searchGisAssetsSeCd;
    private String searchPrdlstSe;


	/** 검색Keyword */
    private String searchKeyword = "";

    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

    private String searchGisAssetsPrtAtCode = "";

    private String searchGisErpAssetCls = "";

    private String searchGisErpAssetNo = "";

    private String searchGisErpAssetNoSeq = "";

    private String searchGisAssetsCd = "";

    private String searchGisAssetsLocCd = "";

    private String searchGisAssetsQuayCd = "";

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
    /**
	 * @return the searchGisAssetsSeCd
	 */
	public String getSearchGisAssetsSeCd() {
		return searchGisAssetsSeCd;
	}

	/**
	 * @param searchGisAssetsSeCd the searchGisAssetsSeCd to set
	 */
	public void setSearchGisAssetsSeCd(String searchGisAssetsSeCd) {
		this.searchGisAssetsSeCd = searchGisAssetsSeCd;
	}

	/**
	 * @return the searchPrdlstSe
	 */
	public String getSearchPrdlstSe() {
		return searchPrdlstSe;
	}

	/**
	 * @param searchPrdlstSe the searchPrdlstSe to set
	 */
	public void setSearchPrdlstSe(String searchPrdlstSe) {
		this.searchPrdlstSe = searchPrdlstSe;
	}
}
