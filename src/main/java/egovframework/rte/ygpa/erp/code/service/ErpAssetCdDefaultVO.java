package egovframework.rte.ygpa.erp.code.service;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErpAssetCdDefaultVO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** 검색조건 */
    private String searchCondition = "";

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

    private String searchAssetCls;

    private String searchAssetNo;

    private String searchAssetNoSeq;

    private String searchItemName;

    private String searchModelName;

    private String searchAssetSize;

    private String searchStartDt;

    private String searchEndDt;

    private String searchDeptCd;

    private String sGisAssetsSeCd;

    private String sPrdlstSe;


    public String getSearchAssetNo() {
		return searchAssetNo;
	}

	public void setSearchAssetNo(String searchAssetNo) {
		this.searchAssetNo = searchAssetNo;
	}

	public String getSearchAssetNoSeq() {
		return searchAssetNoSeq;
	}

	public void setSearchAssetNoSeq(String searchAssetNoSeq) {
		this.searchAssetNoSeq = searchAssetNoSeq;
	}

	public String getSearchStartDt() {
		return searchStartDt;
	}

	public void setSearchStartDt(String searchStartDt) {
		this.searchStartDt = searchStartDt;
	}

	public String getSearchEndDt() {
		return searchEndDt;
	}

	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}

	public String getSearchDeptCd() {
		return searchDeptCd;
	}

	public void setSearchDeptCd(String searchDeptCd) {
		this.searchDeptCd = searchDeptCd;
	}

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

	public String getSearchAssetCls() {
		return searchAssetCls;
	}

	public void setSearchAssetCls(String searchAssetCls) {
		this.searchAssetCls = searchAssetCls;
	}

	public String getSearchItemName() {
		return searchItemName;
	}

	public void setSearchItemName(String searchItemName) {
		this.searchItemName = searchItemName;
	}

	public String getSearchAssetSize() {
		return searchAssetSize;
	}

	public void setSearchAssetSize(String searchAssetSize) {
		this.searchAssetSize = searchAssetSize;
	}

	public String getSearchModelName() {
		return searchModelName;
	}

	public void setSearchModelName(String searchModelName) {
		this.searchModelName = searchModelName;
	}

	/**
	 * @return the sGisAssetsSeCd
	 */
	public String getsGisAssetsSeCd() {
		return sGisAssetsSeCd;
	}

	/**
	 * @param sGisAssetsSeCd the sGisAssetsSeCd to set
	 */
	public void setsGisAssetsSeCd(String sGisAssetsSeCd) {
		this.sGisAssetsSeCd = sGisAssetsSeCd;
	}

	/**
	 * @return the sPrdlstSe
	 */
	public String getsPrdlstSe() {
		return sPrdlstSe;
	}

	/**
	 * @param sPrdlstSe the sPrdlstSe to set
	 */
	public void setsPrdlstSe(String sPrdlstSe) {
		this.sPrdlstSe = sPrdlstSe;
	}

}
