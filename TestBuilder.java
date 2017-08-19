package effective_java;
/**
 * Created with IntelliJ IDEA.
 * User: wenchao
 * Date: 2017/8/18 15:05
 * from: Effective java读书笔记
 * desc: 构造函数有多个参数时考虑使用构造器 这里用一个人的基本信息做例子
 */
public class TestBuilder {
    // required 必填项
    private String userName;
    private final String certificateCode;

    // optional 选填项
    private String otherInfo;
    private String telephoneNum;
    private String sex;
    private Double weight;

    /**
     * 传统构造方法
     * @param userName
     * @param certificateCode
     * @param telephoneNum
     * @param sex
     * @param otherInfo
     * @param weight
     */
    public TestBuilder(String userName, String certificateCode, String telephoneNum, String sex, String otherInfo, Double weight) {
        this.userName = userName;
        this.certificateCode = certificateCode;
        this.telephoneNum = telephoneNum;
        this.sex = sex;
        this.otherInfo = otherInfo;
        this.weight = weight;
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    /**
     * 用构造器的构造方法 好处
     * 1. 不像传统构造器一样掺入多个参数，传入多个参数必须要求使用者知道每个参数的次序
     * 2.
     * @param builder
     */
    public TestBuilder(Builder builder) {
        this.userName = builder.userName;
        this.certificateCode = builder.certificateCode;
        this.telephoneNum = builder.telephoneNum;
        this.sex = builder.sex;

        this.weight = builder.weight;
        this.otherInfo = builder.otherInfo;
    }

    /**
     * 构造类
     */
    private static class Builder {
        // required 必填项
        private String userName;
        private String certificateCode;
        private String telephoneNum;
        private String sex;

        // optional 选填项
        private String otherInfo = "默认值";
        private Double weight = 50.0;

        public Builder(String userName, String certificateCode) {
            this.certificateCode = certificateCode;
            this.userName = userName;
        }

        public Builder telephoneNum(String telephoneNum) {
            this.telephoneNum = telephoneNum;
            return this;
        }

        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }

        private Builder weight(Double weight) {
            this.weight = weight;
            return this;
        }
        public Builder otherInfo(String otherInfo) {
            this.otherInfo = otherInfo;
            return this;
        }

        public TestBuilder build() {
            return new TestBuilder(this);
        }
    }

    /**
     * 测试方法
     * @param args
     */
    public static void main(String[] args) {
        TestBuilder testBuilder = new TestBuilder.Builder("wenchao", "4128xxxxxxxxx").sex("男").telephoneNum("156xxxxxxxx").build();

        System.out.println(testBuilder.getCertificateCode());
    }

/*
问题如下：
1. 用setter方式也可以给一个空的实例对象set初始值, 这样也可以饱合一个实例对象，那么这样使用构造器的优势何在？
2. 如果定义每一个私有作用域为final，那么用setter的方式会编译错误，而用构造器不用报错误，这样可以在运行期动态为每一个私用变量赋值，而以后不会再被改变。
这样情况会在哪种场景使用到，以后工作学习中寻找答案？
3. 构造类Builder只用一个构造器，把少数的必选参数当作构造器的参数，这样如果想要实例化Builder必选填必填项，所以如果想要达到这种效果一定不能重载构造器否则就达不到
设计要求了， 也就是说构造器这种方法只是更适用于多参，少必填的时候, “如果重载了空的构造器，就可以绕过必填了”
*/
}
