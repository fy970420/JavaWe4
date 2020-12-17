package model.vo;

import java.util.HashMap;

public class Page {
    int pageSize;
    int pageNumber;
    String sort;
    String sortOrder;

    public static Page getPageParams(HashMap<String, Object> mapPage) {
        Page page = new Page();
        page.setPageSize((int)Math.floor((double)mapPage.get("pageSize")));
        page.setPageNumber((int)Math.floor((double)mapPage.get("pageNumber")));
        page.setSort((String) mapPage.get("sort"));
        page.setSortOrder((String) mapPage.get("sortOrder"));
        return page;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", sort='" + sort + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                '}';
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Page() {
    }

    public Page(int pageSize, int pageNumber, String sort, String sortOrder) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sort = sort;
        this.sortOrder = sortOrder;
    }
}
