# 图的实现
## 邻接矩阵

```java
import java.util.ArrayList;
import java.util.List;

class GraphAdjMat {
    List<Integer> vertices;//顶点列表，元素代表顶点值，索引是顶点索引
    List<List<Integer>> adjMat; //邻接矩阵，行列索引对应顶点索引

    public GraphAdjMat(int[] vertices, int[][] edges) {
        this.vertices = new ArrayList<>();
        this.adjMat = new ArrayList<>();
        //添加顶点
        for(int val : vertices)
        {
            addVertex(val);
        }
        /*
                添加边
                注意
                edges这个二维数组里面表示的是索引对应的顶点直接有边
                如int[][] edges = {{0, 1}, {0, 2}, {1, 3}};
                第一个元素{0,1}表示索引0，1对应的顶点之间有边
         */
        for(int[] e : edges)
        {
            addEdge(e[0],e[1]);
        }
    }
    public int size(){
        return vertices.size();
    }
    public void addVertex(int val){
        int n = size();
        vertices.add(val);
        List<Integer> newRow = new ArrayList<>();
        for(int j = 0;j < n ;j++)
        {
            newRow.add(0);
        }
        adjMat.add(newRow);
        for(List<Integer> row : adjMat){
            row.add(0);
        }
    }
    public removeVertex(int index){
        if (index >= size())
        {
            throw new IndexOutOfBoundsException();
        }
        vertices.remove((index));
        adjMat.remove(index);
        for (List<Integer> row : adjMat)
        {
            row.remove(index);
        }
    }
    
    public void addEdge(int i , int j)
    {
        if(i <0 || j< 0 || i>= size() || j>= size() || i==j)
        {
            throw new IndexOutOfBoundsException();
        }
        adjMat.get(i).set(j,1);
        adjMat.get(j).set(i,1);
    }
    public void removeEdge(int i ,int j){
        if(i<0||j<0||i>=size()||j>=size()){
            throw new IndexOutOfBoundsException();
        }
        adjMat.get(i).set(j,0);
        adjMat.get(j).set(i,0);
    }
    public void print(){
        System.out.print("顶点列表 = ");
        System.out.println(vertices);
        System.out.println("邻接矩阵 = ");

        for (List<Integer> row : adjMat) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println(); 
        }

    }
}
```
## 邻接表
实际上我们使用Vertex类表示顶点，这样删除时只需要删除一个不需要删除其他（借助哈希表）

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GraphAdjList {
    Map<Vertex, List<Vertex>> adjList;

    public GraphAdjList(Vertex[][] edges) {
        this.adjList = new HashMap<>();
        for (Vertex[] edge : edges) {
            addVertex(edge[0]);
            addVertex(edge[1]);
            addEdge(edge[0], edge[1]);
        }
    }

    public int size() {
        return adjList.size();
    }

    public void addEdge(Vertex vet1, Vertex vet2) {
        if (!adjList.containsKey(vet1) || !adjList.containsKey(vet2)) {
            throw new IllegalArgumentException();
        }
        adjList.get(vet1).add(vet2);
        adjList.get(vet2).add(vet1);
    }

    public void removeEdge(Vertex vet1, Vertex vet2) {
        if (!adjList.containsKey(vet1) || !adjList.containsKey(vet2) || vet1 == vet2) {
            throw new IllegalArgumentException();
        }
        adjList.get(vet1).remove(vet2);
        adjList.get(vet2).remove(vet1);
    }

    public void addVertex(Vertex vet) {
        if (adjList.containsKey(vet)) {
            return;
        }
        adjList.put(vet, new ArrayList<>());
    }

    public void removeVertex(Vertex vet) {
        if (!adjList.contains(vet)) {
            throw new IllegalArgumentException();
        }
        adjList.remove(vet);
        for (List<Vertex> list : adjList.values()) {
            list.remove((vet));
        }
    }

    public void print() {
        System.out.println("邻接表 = ");
        for (Map.Entry<Vertex,List<Vertex>> pair : adjList.entrySet())
        {
            List<Integer> temp = new ArrayList<>();
            for (Vertex vertex : pair.getValue())
            {
                temp.add(vertex.val);
            }
            System.out.println(pair.getKey().val + ": " + temp + ",");
        }
    }
}
```
| |邻接矩阵|邻接表（链表）|邻接表（哈希表）|
|:---|:---:|:---:|:---:|
|**判断是否邻接**|O(1)|O(n)|O(1)|
|**添加边**|O(1)|O(1)|O(1)|
|**删除边**|O(1)|O(n)|O(1)|
|**添加顶点**|O(n)|O(1)|O(1)|
|**删除顶点**|O(n*n)|O(n+m)|O(n+m)|
|**内存空间**|O(n*n)|O(n+m)|O(n+m)|