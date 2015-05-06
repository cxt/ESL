package com.cxt.esl.util.enumType;

public enum WorkStatus {
	/*，-1 未知异常，0 初始，1 正常，2 生成图片，3 下发图片没feedback，4 下发图片有feedback，5 esl 不在线'*/
	// 利用构造函数传参
    EXCEPTION(-1), INITIAL(0), NORMAL (1),GENERRATE_PIC(2),NO_FEEDBACK(3),FEEDBACK(4),NO_ONLINE(5);

    // 定义私有变量
    private int nCode ;

    // 构造函数，枚举类型只能为私有
    private WorkStatus( int _nCode) {
        this . nCode = _nCode;
    }

    @Override
    public String toString() {
        return String.valueOf ( this . nCode );
    }
}

