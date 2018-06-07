package com.cykj.survey.fragment.index;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.activity.PhotoUploadActivity;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.lib.Group;
import com.cykj.survey.lib.annotation.Widget;
import com.cykj.survey.model.Company;
import com.cykj.survey.model.Industry;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.DateUtil;
import com.cykj.survey.util.DeviceUtils;
import com.cykj.survey.util.JsonUtil;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.io.IOException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BusinessFragment extends BaseFragment {

    private Handler handler;

    @BindView(R.id.topbar)
    QMUITopBar mTopbar;

    @BindView(R.id.business_edit_name)
    EditText businessEditName;

    @BindView(R.id.business_edit_addr)
    EditText businessEditAddr;

    @BindView(R.id.business_edit_contacts)
    EditText businessEditContacts;

    @BindView(R.id.business_edit_manager)
    EditText businessEditManager;

    @BindView(R.id.business_edit_deputy_manager)
    EditText businessEditDeputyManager;

    @BindView(R.id.business_edit_safe)
    EditText businessEditSafe;

    @BindView(R.id.business_edit_worker)
    EditText businessEditWorker;

    @BindView(R.id.business_edit_special_worker)
    EditText businessEditSpecialWorker;

    @BindView(R.id.business_edit_assets)
    EditText businessEditAssets;

    @BindView(R.id.business_edit_amount)
    EditText businessEditAmount;

    @BindView(R.id.groupListView)
    QMUIGroupListView mGroupListView;

    @BindView(R.id.business_edit_coverage)
    EditText businessEditCoverage;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_business, null);
        ButterKnife.bind(this, root);
        initTopbar();
        initGroupListView();
        handler = new Handler();
        return root;
    }

    /**
     * 初始化标题栏
     */
    private void initTopbar() {
        mTopbar.setTitle("企业信息");

        mTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mTopbar.addRightTextButton("下一页", R.id.topbar_right_text_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        QMUIFragment fragment = new LicenseUploadFragment();
//                        startFragment(fragment);
                        try {
                            postJson();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

        QMUIStatusBarHelper.translucent(getActivity());

    }

    private void  postJson() throws IOException{
        final QMUITipDialog tipDialog = new QMUITipDialog.Builder(getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("提交中，请稍等")
                .create();
        tipDialog.show();
        String url = "http://2f6bbg.natappfree.cc/company/post";

        Date date = new Date();
        Company company = new Company();
        company.setName(businessEditName.getText().toString());
        company.setAddr(businessEditAddr.getText().toString());
        company.setLinkman(businessEditContacts.getText().toString());
        company.setManager(businessEditManager.getText().toString());
        company.setViceManager(businessEditDeputyManager.getText().toString());
        company.setSafe(businessEditSafe.getText().toString());
        company.setWokerNormal(Integer.parseInt(businessEditWorker.getText().toString()));
        company.setWokerSpecial(Integer.parseInt(businessEditSpecialWorker.getText().toString()));
        company.setMakeTime(DateUtil.parseToSQLDate(date,DateUtil.yyyyMMddHHmmss));
        company.setUniqueId(DeviceUtils.getUniqueId(getActivity()));
        String json = JSONObject.toJSONString(company);

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("json",json)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                tipDialog.dismiss();
                String resultStr = response.body().string();
                ResultModel result = JSONObject.parseObject(resultStr,ResultModel.class);
                if (result.getCode() == 0){
                    handler.post(seccessRun);
                    Constants constants = new Constants();
                    constants.setReportId(Long.parseLong(result.getData()));
                    QMUIFragment fragment = new LicenseUploadFragment();
                    startFragment(fragment);
                }else {
                    handler.post(failRun);
                    return;
                }
            }
        });

    }

    Runnable seccessRun = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(getContext(),"保存成功",Toast.LENGTH_SHORT).show();
        }
    };

    Runnable failRun = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(getContext(),"保存失败",Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 初始化GroupList
     */
    private void initGroupListView() {
        QMUIGroupListView.Section section = new QMUIGroupListView.Section(getActivity());

        QMUICommonListItemView itemView = mGroupListView.createItemView("投保险种");
        itemView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_NONE);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMultiChoiceDialog(v);
            }
        };

        section.addItemView(itemView, onClickListener)
                .addTo(mGroupListView);
    }

    /**
     * 弹出多选框
     * @param v
     */
    private void showMultiChoiceDialog(final View v) {
        final String[] items = new String[]{"综合险", "一切险", "基本险"};
        final int[] selected = new int[items.length];
        if (v instanceof QMUICommonListItemView) {
            String str = ((QMUICommonListItemView) v).getDetailText().toString();
            if (!str.equals("")) {
                String[] str1 = str.split(";");
                int a = 0;
                for (int i = 0; i < str1.length; i++) {
                    for (int j = 0; j < items.length; j++) {
                        if (str1[i].equals(items[j])) {
                            selected[a] = j;
                            a++;
                        }
                    }
                }
            }
        }
        final QMUIDialog.MultiCheckableDialogBuilder builder = new QMUIDialog.MultiCheckableDialogBuilder(getActivity())
                .setCheckedItems(selected)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.addAction("取消", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
            }
        });
        builder.addAction("提交", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                String result = "";
                for (int i = 0; i < builder.getCheckedItemIndexes().length; i++) {
                    result += "" + items[builder.getCheckedItemIndexes()[i]] + ";";
                }
                if (v instanceof QMUICommonListItemView) {
                    ((QMUICommonListItemView) v).setDetailText(result);
                }
                dialog.dismiss();
            }
        });
        builder.create(mCurrentDialogStyle).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
