class BinaryNode<V extends Comparable<V>>{
    V value;
    Comparable key;     // данные в узле
    BinaryNode left;    // левый
    BinaryNode right;   // правый

    BinaryNode (Comparable k){
        key = k;
        left = right = null;
    }
}

public class SplayTree<T extends Comparable<T>> {
    private BinaryNode root;


    private SplayTree(){
        root = null;
    }


    public void insert (Comparable<T> key){
        BinaryNode n;
        int c;
        if (root == null) {
            root = new BinaryNode(key);
            return;
        }
        splay(key);
        if ((c = key.compareTo((T) root.key)) == 0) {

            return;
        }
        n = new BinaryNode(key);
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


    void remove(Comparable<T> key){
        BinaryNode n;
        splay(key);
        if (key.compareTo((T) root.key)!= 0){
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

    public Comparable find(Comparable<T> key) {
        if (isEmpty()) return null;
        splay(key);
        if(root.key.compareTo(key) != 0) return null;
        return root.key;
    }

    public Comparable findMin() {
        BinaryNode r = root;
        if (isEmpty()) return null;
        while (r.left != null)
            r = r.left;
        splay(r.key);
        return r.key;
    }
    public Comparable findMax() {
        BinaryNode r = root;
        if (isEmpty()) return null;
        while (r.right != null)
            r = r.right;
        splay(r.key);
        return r.key;
    }


    public boolean contains (Comparable<T> key){
        if (isEmpty()) return false;
        splay(key);
        return key.compareTo((T) root.key) == 0;
    }

    private boolean isEmpty(){
        return root == null;
    }


    private static BinaryNode head = new BinaryNode(null);

    private void  splay(Comparable<T> key){
        BinaryNode t, r, l, s;
        l = r = head;
        t = root; 
        head.left = head.right = null;
        for (;;){
            if (key.compareTo((T) t.key) < 0){
                if (t.left == null) break;
                if(key.compareTo((T) t.left.key) < 0){
                    s = t.left;                              // перемещение вправо
                    t.left = s.right;
                    s.right = t;
                    t = s;
                    if (t.left == null) break;
                }
                r.left = t;                                  // ссылка на правую
                r = t;
                t = t.left;
            }else if (key.compareTo((T) t.key) > 0){
                if (t.right == null) break;
                if (key.compareTo((T) t.right.key) > 0){
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

    public static void main(String[] args) {
        SplayTree t = new SplayTree();
        t.root= new  BinaryNode(8);
        t.root.left = new BinaryNode(3);
        t.root.left.right =new BinaryNode(4);
        t.root.left.right.right = new BinaryNode(6);
        t.root.left.right.right.left = new BinaryNode(5);
        t.root.left.right.right.right = new BinaryNode(7);
        t.root.right= new BinaryNode(10);
        t.root.right.right = new BinaryNode(11);
        t.root.right.right.right = new BinaryNode(12);
        t.root.right.right.right.right = new BinaryNode(15);
        t.root.right.right.right.right.right = new BinaryNode(17);
        t.root.right.right.right.right.left = new BinaryNode(13);
        t.root.right.right.right.right.left.right = new BinaryNode(14);
        t.splay(14);
        System.out.println();
    }

}
