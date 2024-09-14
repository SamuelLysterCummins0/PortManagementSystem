package Classes;


//
public class LinkNode<N>  {
    public LinkNode<N> next = null;
    private N contents;

    public N getContents() {
        return contents;
    }

    public void setContents(N c) {
        contents = c;
    }
}