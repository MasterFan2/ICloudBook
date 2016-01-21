package com.masterfan.cloudbook.fragment.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.personal.ActionTabBookFriendFragmentActivity;
import com.masterfan.cloudbook.activity.personal.CommentActivity;
import com.masterfan.cloudbook.activity.personal.NoteBookActivity;
import com.masterfan.cloudbook.activity.personal.PersonalDataActivity;
import com.masterfan.library.ui.MTFBaseFragment;
import com.masterfan.library.ui.annotation.MTFFragmentFeature;

import org.xutils.view.annotation.ViewInject;

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
    LinearLayout noteBookLayout;//我的评论

    @Override
    public void initialize() {

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
        Intent intent = new Intent(getActivity(),ActionTabBookFriendFragmentActivity.class);
        if(intent != null){
            animStart(intent);
        }else{
            Log.i("AAAA","ActionTabBookFriendFragmentActivity为空");
        }
    }

    @OnClick(R.id.personal_comment_layout)
    public void onclickComment(View view){
        Intent intent = new Intent(getActivity(),CommentActivity.class);
        if(intent != null){
            animStart(intent);
        }else{
            Log.i("AAAA","CommentActivity为空");
        }
    }

    @OnClick(R.id.personal_notebook_layout)
    public void onclickNoteBook(View view){
        Intent intent = new Intent(getActivity(),NoteBookActivity.class);
        if(intent != null){
            animStart(intent);
        }else{
            Log.i("AAAA","CommentActivity为空");
        }
    }
}
