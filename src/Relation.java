
/**
 *
 * @author SanCin
 */


public class Relation {

    private int size;

    //default constructor
    public Relation
        () {
        size = 0;
    }

    //normal constructor
    public Relation(int size) {
        this.size = size;
    }

    //Getter
    public int getSize() {
        return size;
    }
    
    //Setter
    public void setSize(int size) {
        this.size = size;
    }

    //processor to determine reflexive and irreflexive relation
    //see the diagonal matric is equal to 1
    public String detReflexive(int[][] arr) {

        String reflexive;
        int result = 0, i = 0, j = 0;
        do {
            result = result + arr[i][j];

            i = i + 1;
            j = j + 1;
        } while (i != getSize());
        
        if(result == getSize()) 
        reflexive = "Yes";
        else 
        reflexive ="No";

        return reflexive;
    }

    //processor to determine reflexive and irreflexive relation
    //see the diagonal matric is equal to 0
    public String detIrreflexive(int[][] arr) {

        String irreflexive;
        int result = 0, i = 0, j = 0;
        do {
            result = result + arr[i][j];

            i = i + 1;
            j = j + 1;
        } while (i != getSize());
        
        if(result == 0) 
        irreflexive = "Yes";
        else 
        irreflexive ="No";
        
        return irreflexive;
    }


    //processor to determine symmetric and antisymmetric relation
    //determine Symmetric closures
    /*this process is created by comparing original elements in an array with 
        closure element*/
    //it use Symmetric closure processor and compare array processor
    public String detSymmetric(int[][] arr) {
        
        String sym;
        int[][] arrComp = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            arrComp[i] = arr[i].clone();
        }

        detSymClosure(arrComp);

        if (equals(arr, arrComp))
        sym = "Yes";
        else
        sym = "No";

        return sym;
    }

    //determine anti symmetric
    //determine Symmetric closures
    /*this process is created by comparing all element if i != j and ij = 1 and ji ==1 that mean 
        it is anti symmetric */
    public String detAntisymmetric(int[][] arr) {
        
        String sym = "";
        
        a:
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                if(i != j) {
                    if (arr[i][j] == 1 && arr[j][i] == 1) {
                        sym = "No";
                        break a;
                    }    
                }
                else sym = "Yes";
            } 
        }
        return sym;
    }

    //determine transitive relation
    /*this process is created by comparing original elements in an array with 
        closure element*/
    //it use transitive closure processor and compare array processor
    public String detTransitif(int arr[][]) {
        
        String s;
        int[][] arrComp = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            arrComp[i] = arr[i].clone();
        }
        for (int i = 0; i < arrComp.length; i++) {
            for (int j = 0; j < arrComp[i].length; j++) {
                if(arr[i][j] == 1)
                procTrans(i, j, arrComp);
            }
        }
        if (equals(arr, arrComp))
        s = "Yes";
        else
        s = "No";

        return s;
    }

    //determine Symmetric closures
    /*this processor check and change all upper diagonal and lower diagonal 
        element to become value*/
    public void detSymClosure(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] == 1)
                arr[j][i] = 1;
            }  
        }
    }

    //determine Reflaxive closures
    //this processor change the diagonal element in matric to 1
    public void detRefClosure(int[][] arr) {

        int i = 0, j = 0;
        do {
            if(arr[i][j] == 0) {
                arr[i][j] = 1;
            }
            i = i + 1;
            j = j + 1;
        } while (i != size && j != getSize());
    }
    
    //determine transitive closure
    /*this process is designed by checking the value of 'ab' and 'bc' 
        converted the value to 'ac' then convert 
            the ac element whose value is 0 to 1*/
    public void detTransClosure(int arr[][]) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] == 1)
                procTrans(i, j, arr);
            }
        }
    }

    //processor booleon to compare array
    static boolean equals(int[][] arr, int[][] arrComp) {

        if(arr.length != arrComp.length)
        return false;
 
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] != arrComp[i][j])
                return false;
            }
        }
        return true;
    }

    //processor to cal data for transitive closure
    static void procTrans(int u, int v, int arr[][]) {
          
        for (int i = 0; i < arr.length; i ++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[u][v] == 1 && arr[v][j] == 1 && arr[u][j] == 0) {
                    arr[u][j] = 1;
                }
            }
        }
    }
}