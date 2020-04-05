package com.example.easyrequisition.util;

public interface ItemTouchHelperAdapter {
    void onItemDismiss(int position);

    boolean onItemMove(int fromPosition, int toPosition);
}
