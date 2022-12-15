/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sajid
 * @since Oct 31, 2018
 */
@ManagedBean
public class PaginationBean {

	private Logger logger = LoggerFactory.getLogger(PaginationBean.class);

	// Entity Handlers
	private Integer pageSize;
	private Integer totalPages;

	private Integer paginationStart;
	private Integer paginationEnd;

	private Integer currentPage;

	@PostConstruct
	private void init() {
		System.out.println("PaginationBean - Injection works!"); // It works.
		setDefaultValues();
	}

	private void setDefaultValues() {

		logger.debug("Default value for pagination bean is set.");

		pageSize = 10;
		totalPages = 0;

		paginationStart = 0;
		paginationEnd = 1;

		currentPage = 1;
	}

	public void calculatePagination(Long count) {
		
		// If first FETCH is an index out of count range, reset page index
		logger.debug("Calculating value for pagination bean.");
		
		int firstFetchOnPage = currentPage * pageSize;
		if (firstFetchOnPage > count) {
			totalPages = 0;

			paginationStart = 0;
			paginationEnd = 1;

			// when there is no data
			currentPage = 1;

		} else {
			if (count < pageSize) {
				totalPages = 1;
				// when there is data for only one page
				currentPage = 1;
				paginationStart = 1;
				paginationEnd = 2;
			} else {
				totalPages = (int) (count / pageSize);
				paginationStart = currentPage;
				paginationEnd = currentPage + pageSize;

				/*
				 * If page size is 10 current page is 4 then the position is
				 * between 31-40
				 */
				// currentPosition = ((currentPage-1)*pageSize);
				// currentPage = (int) Math.ceil(currentPosition/pageSize);

			}

		}

		System.out.println("Total Pages : " + totalPages);

		System.out.println("Page Size : " + pageSize);

		System.out.println("Pagination Start : " + paginationStart);
		System.out.println("Pagination End : " + paginationEnd);

		System.out.println("Current page is : " + currentPage);
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the totalPages
	 */
	public Integer getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages
	 *            the totalPages to set
	 */
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the paginationStart
	 */
	public Integer getPaginationStart() {
		return paginationStart;
	}

	/**
	 * @param paginationStart
	 *            the paginationStart to set
	 */
	public void setPaginationStart(Integer paginationStart) {
		this.paginationStart = paginationStart;
	}

	/**
	 * @return the paginationEnd
	 */
	public Integer getPaginationEnd() {
		return paginationEnd;
	}

	/**
	 * @param paginationEnd
	 *            the paginationEnd to set
	 */
	public void setPaginationEnd(Integer paginationEnd) {
		this.paginationEnd = paginationEnd;
	}

	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

}
