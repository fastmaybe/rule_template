package mytest;


/**
 * Description RuanZhuCode
 *
 * @author suntao(01408885)
 * @since 2021.06.18
 */
public class SoftWorkCode {
    public static void main(String[] args) {
        String popHome = "D:\\E-pan\\workspace\\ibu-saas-iss-bps-service\\saas-iss-bps-core";
        String docPath = "D:\\E-pan\\工作留存\\saas-bps软著\\code.txt";
        FileUtil.traverseFolder3(popHome, docPath);
    }
}
