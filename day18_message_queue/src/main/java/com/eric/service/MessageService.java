package com.eric.service;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-31 14:56
 */
public interface MessageService {

    /**
     * send message
     *
     * @param id message id
     */
    void sendMessage(String id);

    /**
     * get message from queue, then send massage
     *
     * @return message
     */
    String doSendMessage();

}
