public enum DemoEnum {

    APPLE(1,"苹果"),

    BANANA(2,"香蕉"),

    ORANGE(3,"橘子"),

    WATERMELON(4,"西瓜"),;

    private Integer code;
    private String message;

    DemoEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static DemoEnum getByCode(Integer code){
        if(code == null){
            return null;
        }

        for (DemoEnum anEnum : values()) {
            if(anEnum.getCode().equals(code))
                return anEnum;
        }

        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
