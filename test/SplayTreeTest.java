import org.junit.Assert;
import org.junit.Test;

public class SplayTreeTest  {
    @Test
    public void isEmpty() {
        SplayTree<Integer> treeEmpty = new SplayTree<>();
        Assert.assertTrue(null, treeEmpty.isEmpty());
        treeEmpty.insert(12);
        Assert.assertFalse(treeEmpty.isEmpty());
    }

    @Test
    public void findMin(){
        SplayTree<Integer> minTree = new SplayTree<>();
        minTree.insert(50);
        minTree.insert(12);
        minTree.insert(23);
        Assert.assertEquals(12, minTree.findMin());
    }

    @Test
    public void findMax(){
        SplayTree<Integer> minTree = new SplayTree<>();
        minTree.insert(12);
        minTree.insert(143);
        minTree.insert(242);
        Assert.assertEquals(242, minTree.findMax());
    }

    @Test
    public void contains(){
        SplayTree<Integer> treeCont = new SplayTree<>();
        treeCont.insert(21);
        treeCont.insert(31);
        treeCont.insert(3);
        Assert.assertTrue(treeCont.contains(3));
        Assert.assertFalse(treeCont.contains(2));
    }
}