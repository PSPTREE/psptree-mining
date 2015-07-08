package mining;

import java.util.*;
import java.util.Comparator;

public class SPTree
{
	TreeNode root;
	SPTree()
	{
		root=new TreeNode(null, '\0');
	}
	public TreeNode getRoot () {
		return root;
	}
}



class MyCompare implements Comparator<Object>{
	
public int compare(Object o0, Object o1) {
	TreeNode tn0 = (TreeNode) o0;
	TreeNode tn1 = (TreeNode) o1;
		if (tn0.value > tn1.value) {
			return 1; // 第一个大于第二个
		} else if (tn0.value < tn1.value) {
			return -1;// 第一个小于第二个
		} else {
			return 0; // 等于
		}
	}
}

class TreeNode {
	public PriorityQueue<TreeNode> children;
	public TreeNode parent;
	public char value;
	public Vector<patternNode> patterns;

	TreeNode (TreeNode pa, char ch) {
		Comparator<Object> comparator = new MyCompare();
		children = new PriorityQueue<TreeNode>(1, comparator);
		patterns = new Vector<patternNode>();
		value = ch;
		parent=pa;
	}

	public char getValue()
	{
		return value;
	}
	
	public void addChild (TreeNode n) {
		children.add(n);
	}

	public void setParent (TreeNode n) {
		parent = n;
	}

	public TreeNode getParent () {
		return parent;
	}

	public PriorityQueue<TreeNode> getChildren () {
		return children;
	}
}

class patternNode
{
	public Vector<Integer> count;
	public Vector<Stamp> showtimes;
	patternNode(Vector<Integer> vec)
	{
		count=vec;
		showtimes=new Vector<Stamp>();
	}
}

class Stamp
{
	public int times;
//	public Date firstShow, lastShow;
	Stamp(int i)
	{
		times=i;
	}
}