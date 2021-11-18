package com.algorithm.treeForeach;


public class BinaryTreeDemo {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "武松");
        HeroNode node6 = new HeroNode(6, "关胜");
        HeroNode node7 = new HeroNode(7, "秦明");
        HeroNode node8 = new HeroNode(8, "呼延灼");
        HeroNode node9 = new HeroNode(9, "花荣");
        HeroNode node10 = new HeroNode(10, "柴进");

        binaryTree.setRoot(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        node7.setRight(node8);
        node8.setRight(node9);
        node4.setLeft(node10);

        //前序遍历
        System.out.println("=============前序遍历===============");
        binaryTree.preOrder(); //1,2,4,5,3
        System.out.println("=============前序遍历===============");

        //中序遍历
        System.out.println("=============中序遍历===============");
        binaryTree.infixOrder();
        System.out.println("=============中序遍历===============");

        //后序遍历
        System.out.println("=============后序遍历===============");
        binaryTree.postOrder();
        System.out.println("=============后序遍历===============");
    }
}

//定义二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
}

//定义 node 节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;


    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);

        //递归左子树
        if (this.left != null) {
            this.left.preOrder();
        }

        //递归右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归左子树
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        //递归右子树
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        //递归左子树
        if (this.left != null) {
            this.left.postOrder();
        }

        //递归右子树
        if (this.right != null) {
            this.right.postOrder();
        }

        System.out.println(this);
    }
}
