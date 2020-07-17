public class FloatingOperandSpecialCase{
  public static void main(
                        String args[]){
    new Worker().doMod();
  }//end main()
}//end class definition

class Worker{
  public void doMod(){
    double myVar01 = 15.5;
    double myVar02 = 1.55;
    System.out.println(
                      myVar01%myVar02);
  }//end doMod()
}//end class definition


//Unfortunately, due to floating arithmetic inaccuracy, the modulus operation in this program produces an entirely incorrect result.
//The result should be 0.0, and that is the result produced by my hand calculator

//result is 1.5499999999999996