package Dto;

import java.util.List;

public class ItemsDto {
    private String name;
    private int pageNo;
    // 页大小
    private int pageSize;
    // 总记录数
    private Long total;
    // 当前页的数据集合
    private List data;


    private Long totalPages;

    private int first;

    private long last;

    private int up;

    private long down;

    @Override
    public String toString() {
        return "ItemsDto{" +
                "name='" + name + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", data=" + data +
                ", totalPages=" + totalPages +
                ", first=" + first +
                ", last=" + last +
                ", up=" + up +
                ", down=" + down +
                '}';
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public void setLast(long last) {
        this.last = last;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public void setDown(long down) {
        this.down = down;
    }

    // 计算总页数的方法
    public long getTotalPages() {
        if(this.total % this.pageSize == 0)
            return this.total / this.pageSize;
        return this.total / this.pageSize + 1;
    }


    // 返回首页的方法
    public int getFirst() {
        return 1;
    }

    // 返回末页的方法
    public long getLast() {
        return getTotalPages();
    }

    // 返回上一页的方法
    public int getUp() {
        if(this.pageNo <= 1)
            return 1;
        return this.pageNo - 1;
    }

    // 返回下一页的方法
    public long getDown() {
        if(this.pageNo >= getLast())
            return getLast();
        return this.pageNo + 1;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
