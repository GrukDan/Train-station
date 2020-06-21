package bsuir.model.pageModel;

public class Page {
    private int page;
    private int size;
    private long totalElements;
    private boolean direction;
    private String parameter;

    public Page(int page, int size, long totalElements, boolean direction, String parameter) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.direction = direction;
        this.parameter = parameter;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
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

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", size=" + size +
                ", totalElements=" + totalElements +
                ", direction=" + direction +
                ", parameter='" + parameter + '\'' +
                '}';
    }
}
