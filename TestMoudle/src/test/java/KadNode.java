// package test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class KadNode {

	static Random rand = new Random(0);
	static ArrayList<KadNode> nodes = new ArrayList<KadNode>();//这里是创建了一个集合，集合里面存放这个类本身创建的对象，类似存了这个类本省的结构体的集合。

	public static void main(String[] args)  {
		// create nodes.
		for (int i = 0; i < 256; i++)
			nodes.add(new KadNode(i));//for循环开始新建kanode这个类似C的结构体的东西，做一个初始化的操作调用的是 #1

		// create neighbors
		for (int i = 0; i < 100000; i++) {
			//nodes.get(rand.nextInt(nodes.size())).ping(nodes.get(rand.nextInt(nodes.size())));   -- 这里是他的源码等效于下面这一段
			int i1 = rand.nextInt(nodes.size());//获取一个随机数，范围是0，到nodes这个集合的长度
			KadNode kadNode = nodes.get(i1);//获取这个nodes集合，第i1位置的元素
			kadNode.ping(nodes.get(rand.nextInt(nodes.size())));//这个ping()括号里面同理上面


		}

		// print nodes
		for (KadNode node : nodes)//for循环开始输出node的值本质就是调用toString()这个方法将这个方法中的sb输出
			System.out.println(node);

		// by-hand testing
		Scanner inp = new Scanner(System.in);//获取一个接受控制台输入的类。
		while (true) {
			System.out.println("Please Input source id and target id:\n");//打印
			String input = inp.next();//通过控制台接收一行输入。
			if (input.equals("exit"))//如果输入exit结束程序
				break;

			//下面两行是将刚刚的输入通过 , 分割，分成两个数字。 也就是你输入的格式得是 1,2 这种形式的俩数字用逗号分割
			int srcid = Integer.parseInt(input.split(",")[0]);
			int tarid = Integer.parseInt(input.split(",")[1]);
			KadNode src = nodes.get(srcid);//获取第srcid个KadNode对象（结构体）
			System.out.printf("Search %d on %d...\n", tarid, srcid);//格式化输出
			while (true) {//这里应该不难理解
				int nid = src.findNode(tarid);
				if (nid > -1)
					src = nodes.get(nid);
				System.out.println(" -> found " + nid);
				if (nid == -1 || nid - tarid == 0)
					break;
			}
		}
	}

	public int id = 0;

	public ArrayList<ArrayList<KadNode>> buckets = new ArrayList<ArrayList<KadNode>>();//创建了一个存放这个类本身的属性 nodes 的集合

	public static final int BitLength = 8;// 创建了一个常量，貌似没用到，可以忽略

	static int[] maxsizes = { 0, 1, 2, 4, 8, 8, 8, 8, 8, 8 };//整数类型定长数组


	public KadNode(int id) {// #1 类的初始化方法 在 nodes.add(new KadNode(i)); 被调用
		this.id = id;
		for (int i = 0; i < 9; i++)
			buckets.add(new ArrayList<KadNode>());//向buckets加一个空的装KadNode的集合
	}

	// Returns the bucket id of the specified node n.
	public int getBucketID(KadNode n) {
		return getBucketK(n.id, getDistance(n.id, id));
	}

	// Returns the bucket id of the specified node id1 with the id2.
	public int getBucketByID(int id1, int id2) {
		return getBucketK(id1, id1 ^ id2);
	}

	/**
	 * Return the number of k-th bucket, i.e the value of k.
	 * 
	 * @param id       The node id.
	 * @param distance The xor result of node id and target id.
	 * @return
	 */
	public static int getBucketK(int id, int distance) {
		int d = distance, v = 0b10000000, k = 8;
		do {
			if ((d & v) > 0)
				break;
			v = v >> 1;
			k--;
		} while (k > 0);
		return k;
	}

	public static int getDistance(int id1, int id2) {
		return id1 ^ id2;
	}


	public void ping(KadNode node) {
		if (node != null && node.id != this.id)
			node.accept(this);
	}

	@Override
	public String toString() {//这个是将属性定制输出用的，会把 id, 进行格式化，组成sb这个字符串。
		StringBuilder sb = new StringBuilder();
		sb.append("ID: " + id);
		for (int i = 0; i < buckets.size(); i++) {
			sb.append("  " + i + ": ");
			for (KadNode node : buckets.get(i))
				sb.append(node.id + " -> ");
			sb.append("END\n");
		}

		return sb.toString();
	}


	private void accept(KadNode node) {
		int bid = this.getBucketID(node);
		// out.println("bid = " + bid + ", buckets.size() = " + buckets.size());
		ArrayList<KadNode> bk = buckets.get(bid);

		if (bk.contains(node)) {
			bk.remove(node);
			bk.add(node);
		} else {
			if (bk.size() < maxsizes[bid])
				bk.add(node);
			else {
				// TODO: evict several least recently nodes that do not response
			}
		}

	}

	// find node by id
	public int findNode(int id) {
		int bid = getBucketByID(this.id, id);
		// 等价于 ArrayList<KadNode> kadNodes = buckets.get(bid);   就是获取这个集合数组
		//获取一个流，这一步不需要理解
		//根据KadNode的id大小进行排序,
		int i = buckets.get(bid).size() == 0 ? -1//
				: buckets.get(bid).stream().min(Comparator.comparingInt(n -> n.id ^ id)).get().id;//获取kadNodes这个数组总最小的一个kadNode的id




		return i;
	}
}

