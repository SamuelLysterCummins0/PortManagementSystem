package Classes;

import Classes.LinkIterator;

import java.io.Serializable;
import java.util.Iterator;

public class LinkList<L> implements Iterable<L>, Serializable {
    public LinkNode<L> head=null;
    public void add(L e) {
        LinkNode<L> Ln=new LinkNode<>();
        Ln.setContents(e);
        Ln.next=head;
        head=Ln;
    }

    public void remove(L e){
        LinkNode<L> current = head;
        if(head == null){
            return;
}
if(head.getContents().equals(e)){
    head = head.next;
    return;
}

while(current.next !=null){
    if(current.next.getContents().equals(e)){
        current.next = current.next.next;
        return;
    }
    current = current.next;
}
}
    public boolean contains(L e) {
        LinkNode<L> current = head;
        while (current != null) {
            if (current.getContents().equals(e)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }


    @Override
    public Iterator<L> iterator() {
        return new LinkIterator<L>(head);
    }

}
