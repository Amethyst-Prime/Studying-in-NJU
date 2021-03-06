package Addition.Addition;
import java.util.Scanner;
/**
 * 链表类
 * @author szh
 *
 */
public class MyLink {

    /**
     * 链表节点类
     * @author szh
     *
     */
    class Node{
        //多项式的系数
        private float coef;
        //多项式的指数
        private int expn;
        //指向下级节点
        public Node next = null;
        public Node(float coef, int expn){
            this.coef = coef;
            this.expn = expn;
        }
    }
    /**
     * 创建链表类
     * 从从控制台不断读入数据，以(0 0)结束
     * @return  创建好链表的第一个节点
     */
    public Node createLink(){
        //存取从控制台读到的系数和指数
        float coef = 0.0f;
        int expn = 0;
        //头尾节点(尾节点方便插入)
        Node head, tail;
        head= new Node(-1, -1);
        head.next = null;
        tail = head;
        Scanner scanner = new Scanner(System.in);
        while(true){
            String res = scanner.nextLine();
            //以空格分割一行中的字符串
            String[] resArray = res.split("\\s+");
            coef = Float.parseFloat(resArray[0]);
            expn = Integer.parseInt(resArray[1]);
            if(coef == 0 && expn == 0.0f){
                break;
            }
            Node node = new Node(coef, expn);
            node.next = null;
            tail.next = node;
            tail = tail.next;
        }
        return head;
    }
    /**
     * 打印链表
     * @param head 链表首节点
     */
    public void printLink(Node head){
        while(head != null){
            System.out.format("%.2f*X^%d  ", head.coef, head.expn);
            head = head.next;
        }
        System.out.println();
    }

    /**
     * 计算两个链表的和
     * @param nodeA
     * @param nodeB
     * @return  最终和的链表
     */
    public Node addLink(Node nodeA, Node nodeB){
        Node nodeC = new Node(-1, -1);
        nodeC.next = null;
        //始终指向链表的当前需要处理的节点(刚开始要除去开头的(-1,-1)节点)
        Node pA = nodeA.next, pB = nodeB.next, pC = nodeC;
        //当前指向的两个链表的指数
        int valueAExpn = 0, valueBExpn = 0;
        while(pA != null && pB != null){
            valueAExpn = pA.expn;
            valueBExpn = pB.expn;
            if(valueAExpn < valueBExpn){
                //将A中的该节点加入C中, 同时移动A和C的指针指向下个元素
                pC.next = pA;
                pC = pC.next;
                pA = pA.next;
            }else if(valueAExpn > valueBExpn){
                //将B中的该节点加入C中,同时移动B和C的指针指向下个元素
                pC.next = pB;
                pC = pC.next;
                pB = pB.next;
            }else{
                //两节点指数相同
                //现将系数相加放到A节点的系数中,然后将A节点加入C中
                float sum = pA.coef + pB.coef;
                if(sum != 0.0f){
                    //系数和不为0,将A节点的系数改变为sum,然后将A节点加入C中
                    pA.coef = sum;
                    pC.next = pA;
                    pC = pC.next;
                }else{
                    //系数和为0,必须将A链表中的该节点从链表中删除掉,如果只移动指针,会在输出时也输出该项
                    //在整个A链表中依次查找,必须找到要删除节点的前驱节点才能将其删除
                    Node s = nodeA;
                    while(s != pA){
                        s = s.next;
                    }
                    //删除该节点
                    s.next = pA.next;
                }
                //对于系数相同的情况,A和B节点指针都往后移动
                pA = pA.next;
                pB = pB.next;
            }
        }
        if(pA != null){
            pC.next = pA;
        }
        if(pB != null){
            pC.next = pB;
        }
        return nodeC;
    }
}
