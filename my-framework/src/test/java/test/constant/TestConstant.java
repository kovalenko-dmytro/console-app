package test.constant;

public enum TestConstant {

    COMMON_PACKAGE_FOR_TEST("test.common");

    private String value;

    TestConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
