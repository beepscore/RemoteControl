package com.beepscore.android.remotecontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by stevebaker on 11/26/14.
 */
public class RemoteControlFragment extends Fragment {
    private TextView mSelectedTextView;
    private TextView mWorkingTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remote_control, parent, false);
        mSelectedTextView = (TextView)view.findViewById(R.id.fragment_remote_control_selectedTextView);
        mWorkingTextView = (TextView)view.findViewById(R.id.fragment_remote_control_workingTextView);

        View.OnClickListener numberButtonListener = new View.OnClickListener() {
            public void onClick(View view) {
                TextView textView = (TextView)view;
                String working = mWorkingTextView.getText().toString();
                String text = textView.getText().toString();
                if (working.equals("0")) {
                    mWorkingTextView.setText(text);
                } else {
                    mWorkingTextView.setText(working + text);
                }
            }
        };

        View.OnClickListener enterButtonListener = new View.OnClickListener() {
            public void onClick(View view) {
                CharSequence working = mWorkingTextView.getText();
                if (working.length() > 0) {
                    mSelectedTextView.setText(working);
                }
                mWorkingTextView.setText("0");
            }
        };

        Button zeroButton = (Button)view.findViewById(R.id.fragment_remote_control_zeroButton);
        zeroButton.setOnClickListener(numberButtonListener);

        Button oneButton = (Button)view.findViewById(R.id.fragment_remote_control_oneButton);
        oneButton.setOnClickListener(numberButtonListener);

        Button enterButton = (Button)view.findViewById(R.id.fragment_remote_control_enterButton);
        enterButton.setOnClickListener(enterButtonListener);

        return view;
    }
}
