public class Exceptions {


    // Unchecked Exceptions
    public static void errorArithmetic() {
        double result = 10 / 0;
        System.out.println(result);
    }

    public static void exampleException() {
        try {
            checkedTwoExceptions(true);
        }catch (RuntimeException e){
            System.out.println("RuntimeException");
        }
        catch (Exception e){
            System.out.println("Exception");
        }
    }

    private static void checkedTwoExceptions(boolean flag) throws Exception{
        if (flag){
            throw new Exception();
        }else {
            throw new RuntimeException();
        }
    }

    // Checked Exceptions
    public static void checkedException() throws Exception {
        try {
            throw new Exception();
        } catch (Exception e) {
            throw new Exception();
        } finally {
            System.out.println("hey Exception");
        }


    }
}
