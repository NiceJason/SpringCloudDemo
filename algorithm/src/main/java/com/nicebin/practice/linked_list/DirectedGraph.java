package com.nicebin.practice.linked_list;

import lombok.Data;
import org.junit.Test;

import java.util.*;

/**
 * @auther: NiceBin
 * @description: 有向图求闭环
 *               例如微服务的调用依赖看有没有闭环
 * @date: 2021/3/16 21:00
 */
public class DirectedGraph {

    /**
     * 微服务依赖查询，看有没闭环，有的话路径怎样
     * 如 A->B，B->C，C->D，C->A
     * 则有闭环 A->B->C->A
     *
     * 思路：
     * 链表求环的思路，非递归算法
     * 链表求环有3种方法
     *          1.遍历两次，第一次读取到元素后就停下开，开始第二次遍历，在经历小于第一次遍历步长之前
     *            如果发现有相同的元素，则说明有环，复杂度为n2
     *          2.利用外部HashSet去储存已遍历的元素，以此替代第二次遍历，复杂度为n，不要要空间n
     *          3.利用快慢指针
     *          https://blog.csdn.net/weixin_31785421/article/details/114863628
     *
     * 利用方法1，再结合bfs那种广度搜索的思想
     *
     * 创建一个Node类，用于记录节点信息，包含名字，所指向的下一个节点列表
     * 创建一个地图，用于存放所有的Node，以此来遍历
     */
    @Test
    public void dependencySearch(){
        String dependency= "[Q-W],[W-Q],[A-B],[B-C],[B-D],[C-D],[D-E],[E-G],[G-A],[G-C],[Y-Z]";
//        String dependency= "[A-B],[B-C],[C-D],[D-E],[E-G],[G-A]";
        HashMap<Character,Node> graph = mappingToGraph(dependency);
        List<String> allPath = dependencySearchAlgorithm(graph);
        for (String path :
                allPath) {
            System.out.println(path);
        }

    }

    /**
     * 将点映射成二维数组
     */
    private HashMap<Character,Node> mappingToGraph(String dependency){

        HashMap<Character,Node> graph = new HashMap<>();
        String[] dependencies = dependency.split(",");
        for(int i =0 ;i<dependencies.length;i++){
            char start  = dependencies[i].charAt(1);
            char end = dependencies[i].charAt(3);

            Node startNode;
            Node endNode;
            //第一次添加
            if(!graph.containsKey(start)){
                startNode = new Node(start);
                graph.put(start,startNode);
            }else{
                startNode = graph.get(start);
            }

            //第一次添加
            if(!graph.containsKey(end)){
                endNode = new Node(end);
                graph.put(end,endNode);
            }

            List<Character> startList = startNode.getList();
            startList.add(end);
        }
      return graph;
    }

    //节点类，这里的list不能保存Node
    //因为一旦形成回环，则list瞬间爆栈，如Node-A的list有Node-B 如果AB回环了，则他们list互相指对方，无穷无尽
    @Data
     class Node{
        char name;
        List<Character> list = new ArrayList<>();

        public Node(char name){
            this.name = name;
        }
    }

    private List<String> dependencySearchAlgorithm(HashMap<Character,Node> graph){
        //用于记录每个Node里面的List所存储的值
        Queue<Node> record = new LinkedList<>();
        //用于记录本个root遍历过的节点
        HashSet<Character> sign = new HashSet<>();

        //记录回环集合
        List<String> allPath = new ArrayList<>();

        Set<Character> keys = graph.keySet();

        //外层循环每个节点都来一次
        for (Character key :
                keys) {
            //记录的数据全部重置
            sign.clear();
            record.clear();
            //记录路径
            String path ="";

            Node root = graph.get(key);
            record.add(root);

            //内层循环某个节点，看成单向列表闭环的问题
            while (!record.isEmpty()){
                Node node = record.poll();
                Character name = node.getName();

                //如果标记中已经存在，那么现在是第二次访问该节点
                if(sign.contains(name)){
                    //如果该节点切好是root节点，那么表示回环了
                    if(root == node){
                        //记录最后再次访问自己
                        path = path +" "+name;
                        allPath.add(path);
                        break;
                    }
                }else{
                    //打上标记
                    sign.add(name);
                    //记录本个root所走的路径（只记一次）
                    path = path +" "+name;
                }

                List<Character> list = node.getList();
                for (Character nodeName :
                        list) {
                    //从地图中获得节点信息
                    Node nextNode = graph.get(nodeName);
                    //记录下去，为下一次循环做准备
                    record.add(nextNode);
                }
            }
        }
        return allPath;
    }

}

