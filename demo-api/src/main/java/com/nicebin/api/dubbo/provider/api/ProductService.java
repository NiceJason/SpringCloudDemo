package com.nicebin.api.dubbo.provider.api;

/**
 * @Author DiaoJianBin
 * @Date 2022/3/31 11:03
 */
public interface ProductService {
    void sendTestMessage(String message);

    void sendTagMessage(String message);

    void sendOrderMessage(String message);
}
