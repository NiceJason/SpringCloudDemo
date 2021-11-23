package Interger_test;

import lombok.Getter;

/**
 * @Author DiaoJianBin
 * @Date 2021/11/19 15:27
 */
public enum TestEnum {
    SUCCESS(1000);
    @Getter
    int code;
    TestEnum(int code){
        this.code = code;
    }
}
