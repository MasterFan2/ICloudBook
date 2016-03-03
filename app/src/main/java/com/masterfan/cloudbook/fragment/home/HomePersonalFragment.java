package com.masterfan.cloudbook.fragment.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.home.ui.AboutWeActivity;
import com.masterfan.cloudbook.activity.home.ui.CopyrightNoticeActivity;
import com.masterfan.cloudbook.activity.home.ui.ProblemfeedbackActivity;
import com.masterfan.cloudbook.activity.home.ui.VersionUpdateActivity;
import com.masterfan.cloudbook.activity.login.LoginActivity;
import com.masterfan.cloudbook.activity.personal.ui.BookFriendFragmentActivity;
import com.masterfan.cloudbook.activity.personal.ui.CommentListActivity;
import com.masterfan.cloudbook.activity.personal.ui.MessageListActivity;
import com.masterfan.cloudbook.activity.personal.ui.MyUploadListActivity;
import com.masterfan.cloudbook.activity.personal.ui.NoteBookListActivity;
import com.masterfan.cloudbook.activity.personal.ui.PersonalDataActivity;
import com.masterfan.cloudbook.activity.personal.ui.WorksmanagementFragmentActivity;
import com.masterfan.library.ui.MTFBaseFragment;
import com.masterfan.library.ui.annotation.MTFFragmentFeature;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by 13510 on 2016/1/19.
 */
@MTFFragmentFeature(layout = R.layout.activity_personal)
public class HomePersonalFragment extends MTFBaseFragment {

    @Bind(R.id.personal_head_img)
    ImageView headImageView;

    @Bind(R.id.personal_my_book_friend_layout)
    LinearLayout bookFriendLayout;//我的书友

    @Bind(R.id.personal_comment_layout)
    LinearLayout commentLayout;//我的评论

    @Bind(R.id.personal_notebook_layout)
    LinearLayout noteBookLayout;//我的笔记

    @Bind(R.id.personal_upload_book_layout)
    LinearLayout uoloadBookLayout;//我的上传

    @Bind(R.id.personal_works_management_layout)
    LinearLayout workManagementLayout;//图书管理

    @Bind(R.id.personal_message_center_layout)
    LinearLayout messageLayout;//消息中心


    @Bind(R.id.personal_problem_feedback_layout)
    LinearLayout problemFeedbadkLayout;//问题反馈

    @Bind(R.id.personal_version_update_layout)
    LinearLayout versionUpdateLayout;//版本更新

    @Bind(R.id.personal_copyright_notice_layout)
    LinearLayout copyrightNoticeLayout;//版权声明

    @Bind(R.id.personal_about_we_layout)
    LinearLayout aboutWeLayout;//关于我们

    @Bind(R.id.personal_switching_account_layout)
    LinearLayout switchingAccountLayout;//切换账号

    @Bind(R.id.personal_exit_login_layout)
    LinearLayout exitLoginLayout;//退出登录

    @Override
    public void initialize() {

    }

    @OnClick(R.id.personal_problem_feedback_layout)
    public void onclickProblemFeedback(){
        Intent intent = new Intent(HomePersonalFragment.this.getContext(),ProblemfeedbackActivity.class);
        if(intent != null){
            animStart(intent);
        }else{
            Log.i("AAAA","ProblemfeedbackActivity==null");
        }
    }

    @OnClick(R.id.personal_version_update_layout)
    public void onclickVersionUpdata(){
        Intent intent = new Intent(HomePersonalFragment.this.getContext(),VersionUpdateActivity.class);
        if(intent != null){
            animStart(intent);
        }else{
            Log.i("AAAA","VersionUpdateActivity==null");
        }
    }

    @OnClick(R.id.personal_copyright_notice_layout)
    public void onclickCopyrightNotice(){
        Intent intent = new Intent(HomePersonalFragment.this.getContext(),CopyrightNoticeActivity.class);
        if(intent != null){
            animStart(intent);
        }else{
            Log.i("AAAA","CopyrightNoticeActivity==null");
        }
    }

    @OnClick(R.id.personal_about_we_layout)
    public void onclickAboutWe(){
        Intent intent = new Intent(HomePersonalFragment.this.getContext(),AboutWeActivity.class);
        if(intent != null){
            animStart(intent);
        }else{
            Log.i("AAAA","AboutWeActivity==null");
        }
    }
    @OnClick(R.id.personal_switching_account_layout)
    public void onclickSwitchingAccount(){
        Intent intent = new Intent(HomePersonalFragment.this.getContext(),LoginActivity.class);
        if(intent != null){
            animStart(intent);
            animFinish();
        }else{
            Log.i("AAAA","AboutWeActivity==null");
        }
    }

    @OnClick(R.id.personal_exit_login_layout)
    public void onclickExitLogin(){
            animFinish();
    }
    @OnClick(R.id.personal_head_img)
    public void onclickHeadImage(){
        Intent intent = new Intent(HomePersonalFragment.this.getContext(),PersonalDataActivity.class);
        if(intent != null){
            animStart(intent);
        }else{
            Log.i("AAAA","PersonalDataActivity为空");
        }
    }

    @OnClick(R.id.personal_my_book_friend_layout)
    public void onclickBookFriend(View view){
        Intent intent = new Intent(getActivity(),BookFriendFragmentActivity.class);
        if(intent != null){
            animStart(intent);
        }else{
            Log.i("AAAA","ActionTabBookFriendFragmentActivity为空");
        }
    }

    @OnClick(R.id.personal_comment_layout)
    public void onclickComment(View view){
        Intent intent = new Intent(getActivity(),CommentListActivity.class);
        if(intent != null){
            animStart(intent);
        }else{
            Log.i("AAAA","CommentActivity为空");
        }
    }

    @OnClick(R.id.personal_notebook_layout)
    public void onclickNoteBook(View view){
        Intent intent = new Intent(getActivity(),NoteBookListActivity.class);
        if(intent != null){
            animStart(intent);
        }else{
            Log.i("AAAA","CommentActivity为空");
        }
    }

    @OnClick(R.id.personal_upload_book_layout)
    public void onclickUploadBook(View view){
        Intent intent = new Intent(getActivity(),MyUploadListActivity.class);
        if(intent != null){
            animStart(intent);
        }else{
            Log.i("AAAA","CommentActivity为空");
        }
    }

    @OnClick(R.id.personal_works_management_layout)
    public void setOnclickWorksManagement(View view){
        Intent intent = new Intent(getActivity(),WorksmanagementFragmentActivity.class);
        if(intent != null){
            startActivityForResult(intent, 0);
        }else{
            Log.i("AAAA","intent==null");
        }
    }

    @OnClick(R.id.personal_message_center_layout)
    public void setOnclickMessageCenter(View view){
        Intent intent = new Intent(getActivity(),MessageListActivity.class);
        if(intent != null){
            startActivityForResult(intent, 0);
        }else{
            Log.i("AAAA","intent==null");
        }
    }
}
