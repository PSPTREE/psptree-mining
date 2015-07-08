package mining;

import java.util.*;

public class TreeBuilder
{
	public SPTree sptree;
	public TreeNode curnode;
	public patternNode curpa;
	public String laststr;
	public TreeBuilder()
	{
		sptree=new SPTree();
		curnode=sptree.getRoot();
		curpa=null;
		laststr="";
	}
	public void AddNodesToTree(String ps_name){
		int i, j, k, l;
		//comment
		System.out.print(ps_name);
		if(!laststr.equals("")){
			if(laststr.charAt(laststr.length()-1)>ps_name.charAt(0))
			{
				ps_name=laststr+'.'+ps_name;
			}
			else
			{
				ps_name=laststr+ps_name;
			}
		}
		String[] pns=ps_name.split("\\.");
		laststr=pns[pns.length-1];
		if(pns.length==1)
			return;
		l=0;
		for(l=0; l<pns.length-1; l++)
		{
			String ps=pns[l];
			PriorityQueue<TreeNode> children;
			Vector<Integer> vec=new Vector<Integer>();
			int tempcount=1;
			curnode=sptree.root;
			for(i=0; i<ps.length(); i++)
			{
				if(ps.charAt(i)=='w')
					continue;
				if(i!=ps.length()-1)
				{
					if(ps.charAt(i) == ps.charAt(i+1))
					{
						tempcount++;
						continue;
					}
				}
				vec.add(tempcount);
				tempcount=1;
				children=curnode.getChildren();
				if(children.size()==0)
				{
					TreeNode temp=new TreeNode(curnode, ps.charAt(i));
					curnode.addChild(temp);
					curnode=temp;
//					System.out.println("create node "+temp.value);
				}
				else
				{
					for(j=0; j<children.size(); j++)
					{
						TreeNode[] child=children.toArray(new TreeNode[0]);
						if(child[j].getValue()==ps.charAt(i))
						{
							curnode=child[j];
							break;
						}
					}
					if(j==children.size())
					{
						TreeNode temp=new TreeNode(curnode, ps.charAt(i));
						curnode.addChild(temp);
						curnode=temp;
//						System.out.println("create node "+temp.value);
					}
				}
			}
//			System.out.println(ps.substring(0, i));
			patternNode pn=new patternNode(vec);
			pn.showtimes.add(new Stamp(1));
			if(curnode.patterns.size()==0)
			{
				(curnode.patterns).add(pn);
//				System.out.println("create patternnode ");
			}
			else
			{
				for(k=0; k<curnode.patterns.size();k++)
				{
					patternNode pnk=curnode.patterns.elementAt(k);
					if(pnk.count.equals(vec))
					{
						if(curpa!=null &&curpa.count.equals(vec))
						{
							pnk.showtimes.set(pnk.showtimes.size()-1, new Stamp(++(pnk.showtimes.lastElement().times)));
//							System.out.println("patter lianxu ");
						}
						else
						{
							pnk.showtimes.add(new Stamp(1));
//							System.out.println("patter bulianxu ");
						}
						break;
					}
				}
				if(k==curnode.patterns.size())
				{
					curnode.patterns.add(pn);
//					System.out.println("create patternnode, not found ");
				}
			}
			curpa=pn;
		}
	}
}