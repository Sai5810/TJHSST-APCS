// Name:
// Date:
import java.util.*;
/*******************
 This driver provides an ArrayList of input strings. One by one, it adds
 the letters to the tree.   Display it as a sideways
 tree (take the code from TreeLab).  Prompt the user for a target and
 search the BST for it.  Display the tree's minimum and maximum values.
 Print the letters in order from smallest to largest.
 **********************/
public class BST_Driver {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		ArrayList < String > list = new ArrayList<>();
		list.add("M A E N I R A C");
		list.add("A M E R I C A N");
		list.add("A A C E I M N R");
		list.add("A");
		list.add("6 8 2 9 3 0 1");
		list.add("Florida Oklahoma Colorado Massachusetts Arizona Iowa New_Hampshire Washington West_Virginia Kazakhstan Arkansas");

		for (String string: list) {
			BST bst = new BST(); //we want to start anew
			String[] str = string.split(" ");
			for (String item: str)
				bst.add(item);

			System.out.println(bst.display());
			System.out.println("Size = " + bst.size());
			System.out.println("Min = " + bst.min());
			System.out.println("Max = " + bst.max());
			System.out.print("Input target: ");
			String target = keyboard.next();
			boolean itemFound = bst.contains(target);
			if (itemFound)
				System.out.println("found: " + target);
			else
				System.out.println(target + " not found.");
			System.out.println("in-order traversal: " + bst.toString());
			System.out.println("--------------------------");
		}
	}
}
