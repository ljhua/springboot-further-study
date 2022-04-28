package me.junhua.common.page;

public interface Page<T> {

	long getTotalPages();

	long getPageNo();

	long getPreviousPageNo();

	long getNextPageNo();

}
