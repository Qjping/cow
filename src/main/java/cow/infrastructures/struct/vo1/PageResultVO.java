package cow.infrastructures.struct.vo1;


import java.util.List;

public class PageResultVO<T> {
    private List<T> list;
    private int count;


    public PageResultVO(List<T> list, int count) {
        this.list = list;
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PageResultIDO{" +
                "list=" + list +
                ", count=" + count +
                '}';
    }
}
