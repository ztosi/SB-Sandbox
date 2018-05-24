import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class TestMain {


    public static void main(String[] args) {


        System.out.println("Size:" + args[0]);
        int n = 0;
        try{
          n =  Integer.parseUnsignedInt(args[0]);
        } catch(NumberFormatException nfe) {
            System.err.println("Size argument was not a parsable unsigned integer, exiting...");
            System.exit(0);
        }
        System.out.println("Creating random " + n + "x" + n + " Matrix using...");
        INDArray mat = null;
        if(args[1].equalsIgnoreCase("c") || args[1].equalsIgnoreCase("row")) {
            mat = Nd4j.rand('c', new int[]{n,n});
            System.out.println("C aka row-major ordering...");
        } else if (args[1].equalsIgnoreCase(("f")) || args[1].equalsIgnoreCase("col")) {
            mat = Nd4j.rand('f', new int[]{n,n});
            System.out.println("Fortran aka column-major ordering...");
        } else {
            System.err.println("Matrix layout not specified in 2nd argument...");
            System.exit(0);
        }

        int iterations = 0;
        try{
            iterations =  Integer.parseUnsignedInt(args[2]);
        } catch(NumberFormatException nfe) {
            System.err.println("Number of iterations is nonsensical...");
            System.exit(0);
        }

        long st = System.nanoTime();
        for(int ii=0; ii<iterations; ii++) {
            mat = mat.muli(mat);
            if ((ii + 1) % (int) (iterations * 0.01) == 0) {
                System.out.print((ii + 1) / (int) (iterations * 0.01) + "%  ");
            }
        }
        long ed = System.nanoTime();
        System.out.println();

        System.out.println(iterations + " in place for a " + n + "x" + n + " random double matrix completed in: " +
                        (double)(ed-st)/1E9 + " seconds.");


        if (n <= 50) {
            System.out.println(mat);
        }


    }

}
