import java.util.ArrayList;
import java.util.Iterator;

public class TestingTree {

	public static void main(String[] args) {
//		Treenode<Integer> tree = new Treenode<Integer>();
//		System.out.println(tree.getData());
//		tree.setData(1);
//		System.out.println(tree.getData());
//		
//		tree.getChildren().add(new Treenode<Integer>());
//		tree.getChildren().add(new Treenode<Integer>());
//		tree.getChildren().add(new Treenode<Integer>());
//		tree.getChildren().get(2).setData(2);
//		
//		ArrayList<Integer> a = new ArrayList<Integer>();
//		a.add(1);a.add(1);a.add(1);
//		System.out.println(a);
//		
//		
//		System.out.print("Tree is null: "); System.out.println(tree == null);
//		
//		System.out.print("Find largest int: ");

		
		BST<Integer> t = new BST<Integer>();
		BSTnode<Integer> n = new BSTnode<Integer>(10, null, null);
		System.out.println(n.getKey());
		
		System.out.println();
		
		
	}

}
