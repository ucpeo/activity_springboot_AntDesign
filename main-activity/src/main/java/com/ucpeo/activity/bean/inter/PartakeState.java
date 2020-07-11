package com.ucpeo.activity.bean.inter;

public interface PartakeState {
    /*
     * 参与表 状态:注册
     */
    int PART_STATE_SIGN = 0;
    /*
     * 参与表 状态:等待
     */

    int PART_STATE_WAIT = 1;
    /*
     * 参与表 状态:任务中
     */
    int PART_STATE_ING = 2;
    /*
     * 参与表 状态:提交
     */
    int PART_STATE_SUBMIT = 3;
    /*
     * 参与表 状态:审核
     */
    int PART_STATE_CHECK = 4;
    /*
     * 参与表 状态:申诉
     */
    int PART_STATE_APPEAL = 5;
    /*
     * 参与表 状态:撤销
     */
    int PART_STATE_BREAK = 6;
    /*
     * 参与表 状态:完成
     */
    int PART_STATE_SUCCESS = 7;

}

