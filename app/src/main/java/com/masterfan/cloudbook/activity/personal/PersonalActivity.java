package com.masterfan.cloudbook.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 个人
 * 现在是没有用的！！！！！
 * Created by Administrator on 2016/1/19 0019.
 */
@MTFActivityFeature(layout = R.layout.activity_personal,toolbar = R.id.toolbar, status_bar_color = R.color.colorPrimary)
public class PersonalActivity  extends MTFBaseActivity {

    @Bind(R.id.personal_my_book_friend_layout)
    public LinearLayout bookFriendLayout;//我的书友

    @Bind(R.id.personal_comment_layout)
    public LinearLayout commentLayout;//我的评论

    @Override
    public void initialize(Bundle savedInstanceState) {
        toolbar.setTitle("个人");
    }

    @OnClick(R.id.personal_my_book_friend_layout)
    public void onclickBookFriend(View view){
        Intent intent = new Intent(this,ActionTabBookFriendFragmentActivity.class);
        animStart(intent);
    }

    @OnClick(R.id.personal_comment_layout)
    public void onclickComment(View view){
        Intent intent = new Intent(this,CommentActivity.class);
        animStart(intent);
    }

}
