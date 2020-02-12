class BinaryNode<T extends  Comparable<T>>{
    T key;     // данные в узле
    BinaryNode<T> left;    // левый
    BinaryNode<T> right;   // правый

    BinaryNode (T k){
        key = k;
        left = right = null;
    }
}

public class SplayTree<T extends Comparable<T>> {
    private BinaryNode<T> root;


    public SplayTree(){
        root = null;
    }


    public void insert (T key){
        BinaryNode n;
        int c;
        if (root == null) {
            root = new BinaryNode<>(key);
            return;
        }
        splay(key);
        if ((c = key.compareTo(root.key)) == 0) {
            return;
        }
        n = new BinaryNode<>(key);
        if (c < 0) {
            n.left = root.left;
            n.right = root;
            root.left = null;
        } else {
            n.right = root.right;
            n.left = root;
            root.right = null;
        }
        root = n;
    }


    void remove(T key){
        BinaryNode n;
        splay(key);
        if (key.compareTo(root.key)!= 0){
            return;                             // Не найдено
        }
        if (root.left == null){
            root= root.right;
        } else {                                // Найдем в левом поддереве максимум
            n = root.right;                     // расширим до корня и присвоим правому ребенку
            root = root.left;
            splay(key);
            root.right = n;
        }
    }

    public Comparable find(T key) {
        if (isEmpty()) return null;
        splay(key);
        if(root.key.compareTo(key) != 0) return null;
        return root.key;
    }

    public T findMin() {
        BinaryNode<T> r = root;
        if (isEmpty()) return null;
        while (r.left != null)
            r = r.left;
        return r.key;
    }
    public T findMax() {
        BinaryNode<T> r = root;
        if (isEmpty()) return null;
        while (r.right != null)
            r = r.right;
        return r.key;
    }


    public boolean  contains (T key){
        if (isEmpty()) return false;
        splay(key);
        return key.compareTo(root.key) == 0;
    }

    public boolean isEmpty(){
        return root == null;
    }


    private  BinaryNode<T> head = new BinaryNode<>(null);

    public void  splay(T key){
        BinaryNode<T> t, r, l, s;
        l = r = head;
        t = root; 
        head.left = head.right = null;
        for (;;){
            if (key.compareTo(t.key) < 0){
                if (t.left == null) break;
                if(key.compareTo(t.left.key) < 0){
                    s = t.left;                              // перемещение вправо
                    t.left = s.right;
                    s.right = t;
                    t = s;
                    if (t.left == null) break;
                }
                r.left = t;                                  // ссылка на правую
                r = t;
                t = t.left;
            }else if (key.compareTo( t.key) > 0){
                if (t.right == null) break;
                if (key.compareTo(t.right.key) > 0){
                    s = t.right;                              // перемещение влево
                    t.right = s.left;
                    s.left = t;
                    t = s;
                    if (t.right == null) break;
                }
                l.right =t;                                     // ссылка на левую
                l = t;
                t = t.right;
            }else {
                break;
            }
        }
        l.right = t.right;
        r.left = t.right;
        t.left = head.right;
        t.right = head.left;
        root = t;
    }




}
