package mobile;



import java.util.*;
  
class Get_Node
{
    HashMap<Character,Get_Node> child;
  
    boolean isLast;
 
    public Get_Node()
    {
        child = new HashMap<Character,Get_Node>();
  
        for (char i = 'a'; i <= 'z'; i++)
             child.put(i,null);
  
        isLast = false;
    }
}
  
class Test
{
    Get_Node root;
   public void insertIntoTest(String contacts[])
    {
        root = new Get_Node();
        int n = contacts.length;
        for (int i = 0; i < n; i++)
        {
            insert(contacts[i]);
        }
    }
  
    public void insert(String s)
    {
        int len = s.length();
  
        Get_Node itr = root;
        for (int i = 0; i < len; i++)
        {
            Get_Node nextNode = itr.child.get(s.charAt(i));
            if (nextNode == null)
            {
                nextNode = new Get_Node();
  
                 itr.child.put(s.charAt(i),nextNode);
            }
  
            itr = nextNode;
  
            if (i == len - 1)
                itr.isLast = true;
        }
    }
  
    public void displayContacts(Get_Node curNode,
                                   String prefix)
    {
  
        if (curNode.isLast)
            System.out.println(prefix);
  
        for (char i = 'a'; i <= 'z'; i++)
        {
            Get_Node nextNode = curNode.child.get(i);
            if (nextNode != null)
            {
                displayContacts(nextNode, prefix + i);
            }
        }
    }
  
    void displayContacts(String str)
    {
        Get_Node prevNode = root;
  
       
        String prefix = "";
        int len = str.length();
  
        int i;
        for (i = 0; i < len; i++)
        {
            prefix += str.charAt(i);
  
            char lastChar = prefix.charAt(i);
  
            Get_Node curNode = prevNode.child.get(lastChar);
  
            if (curNode == null)
            {
                System.out.println("nNo Results Found for "+ prefix );
                i++;
                break;
            }
  
            System.out.println("Suggestions based on "+ prefix +" "+ "are");
            displayContacts(curNode, prefix);
  
            prevNode = curNode;
        }
  
        for ( ; i < len; i++)
        {
            prefix += str.charAt(i);
            System.out.println("No Results Found for " + prefix );
        }
    }
}
  
// Driver code
class Mobile
{
    public static void main(String args[])
    {
        Test test = new Test();
        Scanner input=new Scanner(System.in);        

        String contacts [] = {"Ali", "Ahmed","Khan","Mohammad"};
  
        test.insertIntoTest(contacts);
  
       System.out.println("Enter any character for search contacts: ");
        
        String query =input.next();
  
        
        test.displayContacts(query);
        
    }
}
