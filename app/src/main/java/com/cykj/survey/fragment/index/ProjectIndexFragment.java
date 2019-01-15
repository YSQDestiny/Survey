package com.cykj.survey.fragment.index;

import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.activity.MapProjectActivity;
import com.cykj.survey.activity.project.ProjectAccidentActivity;
import com.cykj.survey.activity.project.ProjectGeologyActivity;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.project.ProjectScaleFragment;
import com.cykj.survey.model.ProjectAccident;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProjectIndexFragment extends BaseFragment {

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.layout_fragment_company_index, null);
        ButterKnife.bind(this, root);
        initTopbar();
        initView();
        return root;
    }

    private void initTopbar(){
        topbar.setTitle("步骤选择");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
    }

    private void initView(){
        showEditTextDialog();
        QMUICommonListItemView item1 = groupListView.createItemView("基础信息");
        item1.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item2 = groupListView.createItemView("建设规模");
        item1.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item3 = groupListView.createItemView("地质信息");
        item2.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item4 = groupListView.createItemView("路径添加");
        item3.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView item5 = groupListView.createItemView("现场风险");
        item4.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView){
                    CharSequence text = ((QMUICommonListItemView) v).getText();
                    switch (text.toString()){
                        case "基础信息":
                            BaseFragment baseFragment = new ProjectFragment();
                            startFragment(baseFragment);
                            break;
                        case "建设规模":
                            BaseFragment baseFragment1 = new ProjectScaleFragment();
                            startFragment(baseFragment1);
                            break;
                        case "地质信息":
                            Intent intent = new Intent(getActivity(),ProjectGeologyActivity.class);
                            getActivity().startActivity(intent);
                            break;
                        case "路径添加":
                            Intent intent1 = new Intent(getActivity(),MapProjectActivity.class);
                            getActivity().startActivity(intent1);
                            break;
                        case "现场风险":
                            Intent intent2 = new Intent(getActivity(),ProjectAccidentActivity.class);
                            getActivity().startActivity(intent2);
                            break;
                        default:

                            break;
                    }
                }
            }
        };

        QMUIGroupListView.newSection(getContext())
                .addItemView(item1,onClickListener)
                .addItemView(item2,onClickListener)
                .addItemView(item3,onClickListener)
                .addItemView(item4,onClickListener)
                .addTo(groupListView);
    }

    private void showEditTextDialog() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getActivity());
        builder.setTitle("项目名称")
                .setPlaceholder("在此输入项目名称")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getActivity(), "请填入项目名称", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void postInfo(){

        String url = Constants.TEST_SERVICE +"";
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("json", "")
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showToastShort("网络连接失败，请稍候重试！");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
