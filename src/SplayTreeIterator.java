import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SplayTreeIterator <V extends Comparable<V>> implements Iterator<V> {
    private  SplayTree<V> tree;
    private List<BinaryNode <V>> elements;
    private BinaryNode<V> currentNode;


    public SplayTreeIterator (SplayTree<V> tree, BinaryNode<V> root){
        this.tree = tree;
        elements = new ArrayList<>();
        addToList(root);
        if (!elements.isEmpty()) currentNode = elements.get(0);
    }

    private void addToList(BinaryNode<V> node){
        if (node != null){
            addToList(node.left);
            elements.add(node);
            addToList(node.right);
        }
    }
    @Override
    public boolean hasNext() {
        return currentNode!=null;
    }

    @Override
    public V next() {
        if (currentNode == null) return null;
        V result = currentNode.key;
        int index = elements.indexOf(currentNode) + 1;
        currentNode = index == elements.size() ? null : elements.get(index);
        return result;
    }


    @Override
    public void remove() {
        int index = currentNode == null ? elements.size() : elements.indexOf(currentNode);
        if (index < 1 )return;
        tree.remove(elements.get(index- 1).key);
    }
}

