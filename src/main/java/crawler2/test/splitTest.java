package crawler2.test;

public class splitTest {
    public static void main(String[] args) {
        String text="你好 夏天   我是   秋天";
        String[] res=text.split("\\s+");
        for (String r:res) {
            System.out.println(r);
        }
        //System.out.println(res);
    }
}
