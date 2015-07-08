package mining;

import java.util.*;

public class Miner
{
	SPTree spt;
	double delta;
	
	public Miner(SPTree sptree, double d)
	{
		spt=sptree;
		delta=d;
	}
	public void FreMine()
	{
		TreeNode curnode=spt.root;
//		System.out.println(1);
		GetFreofPattern(curnode, "");
		
	}
	void GetFreofPattern(TreeNode cur, String p)
	{
		int i, j, k;
		if(cur!=spt.root && cur.patterns.size()!=0)
		{
			for(i=0; i<cur.patterns.size(); i++)
			{
				patternNode pn=cur.patterns.elementAt(i);
				int num=0;
				HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
				for(j=0; j<pn.showtimes.size(); j++)
				{
					int t=pn.showtimes.elementAt(j).times;
					if(map.containsKey(t)==true)
					{
						int x=map.get(t);
						map.put(t, x+1);
					}
					else
					{
						map.put(t, 1);
					}
					num+=t;
				}
				if(num>=delta)
				{
					System.out.print(p+cur.value);
//					System.out.print(pn.count.size());
					System.out.print("  every bp shows: ");
					for(k=0; k<pn.count.size(); k++)
					{
						System.out.print(pn.count.elementAt(k));
						System.out.print(' ');
					}
					System.out.print("fre: ");
					System.out.println(num);
					
//					System.out.println(num);
					Iterator iterator = map.keySet().iterator(); 
					while (iterator.hasNext()) 
					{ 
						int va=(Integer)(iterator.next());
						System.out.println(p+cur.value+"连  续出现 "+va+" fre:"+map.get(va));
					}

				}
			}
		}
//		System.out.println("ok");
		TreeNode[] tn=cur.children.toArray(new TreeNode[0]);
		for(i=0; i<cur.children.size(); i++)
		{
			GetFreofPattern(tn[i], p+cur.value);
		}
	}
}