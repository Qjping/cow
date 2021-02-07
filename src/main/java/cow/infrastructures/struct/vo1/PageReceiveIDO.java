package cow.infrastructures.struct.vo1;


public class PageReceiveIDO {
    private Integer page = 1;

    private Integer limit = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PageReceiveIDO{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
