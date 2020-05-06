package com.cjf.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cjf.ui.dialog.LoadingDialog;
import com.cjf.ui.dialog.adapter.DefaultSingleCheckBoxAdapter;
import com.cjf.ui.dialog.adapter.DefaultSingleCheckBoxSubtitleAdapter;
import com.cjf.ui.dialog.builder.ListDialogBuilder;
import com.cjf.ui.dialog.builder.LoadingDialogBuilder;
import com.cjf.ui.dialog.builder.MessageDialogBuilder;
import com.cjf.ui.dialog.builder.ViewDialogBuilder;
import com.cjf.ui.entities.DefaultCompoundItemBean;
import com.cjf.ui.entities.DefaultCompoundSubtitleItemBean;
import com.cjf.ui.text.ClearTextInputEditText;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onChooseDialog(View view) {
//        new AlertDialogFragment.Builder()
//                .setItems(new String[]{"是", "否"}, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .create().show(this);
//        new AlertDialogFragment.Builder()
//                .setSingleChoiceItems(new String[]{"是", "否"},0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .create().show(this);
//        new AlertDialogFragment.Builder()
//                .setMultiChoiceItems(new String[]{"是", "否"}, new boolean[]{true, false}, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//
//                    }
//                })
//                .create()
//                .setCancelButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .setOkButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }).show(this);

        List<DefaultCompoundItemBean> stringItemList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringItemList.add(new DefaultCompoundItemBean("position=" + i));
        }
        DefaultSingleCheckBoxAdapter adapter = new DefaultSingleCheckBoxAdapter(this, stringItemList);
        adapter.setOnCheckedChangeListener((compoundItem, checkItem, buttonView, isChecked) -> {
            Toast.makeText(getApplication(), checkItem.getLeft() + String.valueOf(isChecked), Toast.LENGTH_LONG).show();
        });
        new ListDialogBuilder()
                .setTitle("温馨提示")
                .fromBottom()
                .fullScreen()
                .setAdapter(adapter)
                .setCancelButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setOkButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show(this);
    }

    public void onMessageDialog(View view) {
        new MessageDialogBuilder().setTitle("温馨提示")
                .create()
                .setOkButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show(this);
    }

    public void onLoadingDialog(View view) {
        mLoadingDialog = new LoadingDialogBuilder()
                .setMessage("加载中...")
                .show(this);
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mLoadingDialog.dismiss();
            }
        }).start();
    }

    public void onChooseDescDialog(View view) {
        List<DefaultCompoundSubtitleItemBean> stringItemList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringItemList.add(new DefaultCompoundSubtitleItemBean("position=" + i, "desc" + i));
        }
        DefaultSingleCheckBoxSubtitleAdapter adapter = new DefaultSingleCheckBoxSubtitleAdapter(this, stringItemList);
        adapter.setOnCheckedChangeListener((compoundItem, checkItem, buttonView, isChecked) -> {
            Toast.makeText(getApplication(), checkItem.getLeft() + String.valueOf(isChecked), Toast.LENGTH_LONG).show();
        });
        new ListDialogBuilder()
                .setTitle("温馨提示")
                .fromBottom()
                .fullScreen()
                .setAdapter(adapter)
                .setCancelButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setOkButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show(this);
    }

    public void onViewDialog(View view) {
        new ViewDialogBuilder()
                .setTitle("温馨提示")
                .setCancelButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setOkButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create(new ClearTextInputEditText(this))
                .show(this);
    }

    public void startSwipeRecyclerViewActivity(View view) {
        startActivity(new Intent(this, SwipeRecyclerViewActivity.class));
    }
}
