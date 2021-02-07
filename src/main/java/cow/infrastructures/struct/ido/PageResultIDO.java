package cow.infrastructures.struct.ido;



import java.util.List;


public class PageResultIDO<T> {

    private List<T> list;

    private int count;

    public PageResultIDO() {
    }

    public PageResultIDO(List<T> list, int count) {
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
