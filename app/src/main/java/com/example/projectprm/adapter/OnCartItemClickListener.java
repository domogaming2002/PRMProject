package com.example.projectprm.adapter;

public interface OnCartItemClickListener {
    void onMinusBtnClick(int position);

    void onPlusBtnClick(int position);

    void onCheckToBuyChange(int position, boolean isCheck);
}
