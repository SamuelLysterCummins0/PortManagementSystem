package Classes;

import java.util.Iterator;

public class LinkIterator<K> implements Iterator<K> {
    private LinkNode<K> pos;
    public LinkIterator(LinkNode<K> fnode) { pos=fnode; }

    public boolean hasNext() {
        return pos!=null;
    }

    public K next() {
        LinkNode<K> temp=pos;
        pos=pos.next;
        return temp.getContents();
    }
}
