package algo.string;

public class ComplexMultiplication {
    public String complexNumberMultiply(String a, String b) {
        int realA, compA;
        int readB, compB;
        realA = Integer.parseInt(a.substring(0, a.indexOf("+")));
        readB = Integer.parseInt(b.substring(0, b.indexOf("+")));
        compA = Integer.parseInt(a.substring(a.indexOf("+")+1, a.length()-1));
        compB = Integer.parseInt(b.substring(b.indexOf("+")+1, b.length()-1));

        int real = (realA*readB - (compA*compB));
        int comp = realA*compB+readB*compA;
        return String.format("%d+%di", real, comp);
    }
    public static void main(String[] args){
        System.out.println(new ComplexMultiplication().complexNumberMultiply(
                "1+1i", "1+-1i"
        ));
    }
}
