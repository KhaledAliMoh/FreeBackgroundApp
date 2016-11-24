package com.example.khaled.FreeBackgroundApp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Khaled on 11/23/2016.
 */
public class CommentFragment extends Fragment {
    final String Tag = "Comment Fragment";
    ListView listViewComments;
    Button buttonAddComment;
    EditText editTextNewCommentContent;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_comment, container, false);

        listViewComments = (ListView) rootView.findViewById(R.id.listView_comments);
        listViewComments.setAdapter(new CommentsAdapter(getActivity(), 4));

        editTextNewCommentContent = (EditText) rootView.findViewById(R.id.editText_new_comment);
        buttonAddComment = (Button) rootView.findViewById(R.id.button_add_comment);
        buttonAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextNewCommentContent.getText() != null){
                    // update the the ArrayList which come from server(add all information like id and profile image url) then call
                    //CommentsAdapter adapter = (CommentsAdapter) listViewComments.getAdapter();
                    //adapter.notifyDataSetChanged();
                }
            }
        });

        return rootView;
    }
}
