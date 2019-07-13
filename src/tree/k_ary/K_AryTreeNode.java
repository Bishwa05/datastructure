package tree.k_ary;

public class K_AryTreeNode {
    private int data;
    private K_AryTreeNode[] child;

    public K_AryTreeNode(int k){
        child = new K_AryTreeNode[k];
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public K_AryTreeNode getChild(int i) {
        return child[i];
    }

    public void setChild(int i, K_AryTreeNode childNode) {
        this.child[i] = childNode;
    }
}
