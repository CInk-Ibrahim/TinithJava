package optBST;

public class OptBST {
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	OptBST X = new OptBST();
    }

    String K[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o" };
    double F[] = { .15, .025, .05, .025, .05, .125, .025, .075, .075, .05, .15, .075, .05, .025, .05 };
    // double F[] = { 0.15, 0.15, 0.35, 0.25, 0.10 };
    double C[][] = new double[F.length][F.length];
    int R[][] = new int[F.length][F.length];

    public OptBST() {
	OptimalBST(0, F.length - 1);
    }

    public OptBST(int i, int j) {
	OptimalBST(i, j);
    }

    public void OptimalBST(int i, int j) {
	System.out.println("i = " + i + "& j =" + j);
	for (int a = i; a <= j; a++) {
	    // Calculate Diagonal of Cost Table & Root Table
	    C[a][a] = F[a];
	    R[a][a] = a;
	}
	printCostTable();
	printRootTable();
	for (int a = 0; a < F.length; a++) {
	    for (int b = 0; a + b < F.length; b++) {
		System.out.println(b + "-" + (a + b));
		cost(b, a + b);
	    }
	}
	printCostTable();
	printRootTable();
	BTreePrinter.printNode(treePrinter(0, F.length - 1));
    }

    public Node<String> treePrinter(int i, int j) {
	if (i > j) {
	    return null;
	}
	int r = R[i][j];
	String f = K[r];
	Node<String> root = new Node<String>(f);
	if (i == r) {
	    root.right = treePrinter(r + 1, j);
	} else if (j == r) {
	    root.left = treePrinter(i, r - 1);
	} else {
	    root.left = treePrinter(i, r - 1);
	    root.right = treePrinter(r + 1, j);
	}
	return root;
    }

    public void cost(int i, int j) {
	double minC = 0;
	int minR = -1;
	if (i >= j) {
	    return;
	} else {
	    minC = Double.POSITIVE_INFINITY;
	}
	for (int r = i; r <= j; r++) {
	    double rC = 0;
	    if (i == r) {
		rC = C[r + 1][j] + sumFreq(i, j);
	    } else if (j == r) {
		rC = C[i][r - 1] + sumFreq(i, j);
	    } else {
		rC = C[i][r - 1] + C[r + 1][j] + sumFreq(i, j);
	    }
	    if (rC < minC) {
		minC = rC;
		minR = r;
	    }
	}
	C[i][j] = minC;
	R[i][j] = minR;
    }

    public double sumFreq(int i, int j) {
	// Returns sum of frequences from i to j
	double total = 0;
	if (i <= j) {
	    for (int a = i; a <= j; a++) {
		total = total + F[a];
	    }
	}
	return total;
    }

    public void printCostTable() {
	// System.out.print(C[3][4]);
	for (int i = 0; i < F.length; i++) {
	    for (int j = 0; j < F.length; j++) {
		System.out.printf("%2.3f", C[i][j]);
		System.out.printf(" ");
	    }
	    System.out.println();
	}
    }

    public void printRootTable() {
	// System.out.print(R[3][4]);
	for (int i = 0; i < F.length; i++) {
	    for (int j = 0; j < F.length; j++) {
		System.out.printf("%2d", R[i][j]);
		System.out.printf(" ");
	    }
	    System.out.println();
	}
    }
}
