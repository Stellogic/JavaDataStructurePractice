## 二叉树的遍历
### 层序遍历（广度优先，bfs）
广度优先，和队列的“先进先出”类似。因此考虑使用队列

```java
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

List<Integer> levelOrder(TreeNode root) {
    //初始化一个队列,作为遍历过程中的临时储存。
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    //初始化一个列表储存结果
    List<Integer> result = new ArrayList<>();
    while (!queue.isEmpty())
    {
        TreeNode node = queue.poll();//出队
        result.add(node.val);
        if(node.left != null){
            queue.offer(node.left);
        }
        if(node.right != null)
        {
            queue.offer(node.right);
        }
    }
    return result;
}
```
时间复杂度：O(N).要访问所有节点<br>
空间复杂度：O(N).队列和result占用空间
### 深度优先（前序，中序，后序）
前序：当前节点->左子树->右子树<br>
中序：左子树->当前节点->右子树<br>
后序：左子树->右子树->当前节点

```java
import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.List;

List<Integer> list = new LinkedList<>();

void preOrder(TreeNode cur) {
    if (cur == null) {
        return;
    }
    list.add(cur.val);
    preOrder(cur.left);
    preOrder(cur.right);

}

void inOrder(TreeNode cur){
    if(cur == null)
    {
        return;
    }
    inOrder(cur.left);
    list.add(cur.val);
    inOrder(cur.right);
}

void postOrder(TreeNode cur){
    if(cur == null)
    {
        return;
    }
    postOrder(cur.left);
    postOrder(cur.right);
    list.add(cur.val);
}
```
时间复杂度：都是O(N)，因为要遍历所有节点
空间复杂度: O(N)。list储存结果是O（N）；栈帧应该是O(logN),因为每次要按深度递到叶节点后再归，理想状态平衡的话应当是O（logN）