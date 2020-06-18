package bsuir.model.pageModel;

public class Page {
    private int page;
    private int size;
    private long totalPages;
    private boolean direction;
    private String parameter;

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", size=" + size +
                ", totalPages=" + totalPages +
                ", direction=" + direction +
                ", parameter='" + parameter + '\'' +
                '}';
    }


    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public Page(int page, int size, long totalPages, boolean direction, String parameter) {
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.direction = direction;
        this.parameter = parameter;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Page() {
    }

}
