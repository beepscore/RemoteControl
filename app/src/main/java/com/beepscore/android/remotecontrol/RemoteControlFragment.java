package com.beepscore.android.remotecontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
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

        configureButtonsTextAndListener(view);

        return view;
    }

    private void configureButtonsTextAndListener(View view) {

        View.OnClickListener numberButtonListener = new View.OnClickListener() {
            public void onClick(View view) {
                TextView textView = (TextView)view;
                String working = mWorkingTextView.getText().toString();
                String text = textView.getText().toString();
                if (working.equals(R.string.zero)) {
                    mWorkingTextView.setText(text);
                } else {
                    mWorkingTextView.setText(working + text);
                }
            }
        };

        // configure buttons in code rather than xml
        TableLayout tableLayout = (TableLayout)view.findViewById(R.id.fragment_remote_control_tableLayout);
        int number = 1;

        // configure number rows except bottom row
        // skip first two TextViews
        final int NUMBER_OF_VIEWS_TO_SKIP = 2;
        for (int i = NUMBER_OF_VIEWS_TO_SKIP; i < tableLayout.getChildCount() - 1; i++) {
            TableRow tableRow = (TableRow)tableLayout.getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); j++) {
                Button button = (Button)tableRow.getChildAt(j);
                button.setText("" + number);
                button.setOnClickListener(numberButtonListener);
                number++;
            }
        }

        // configure bottom row
        TableRow bottomRow = (TableRow)tableLayout.getChildAt(tableLayout.getChildCount() - 1);

        Button deleteButton = (Button)bottomRow.getChildAt(0);
        configureAsDeleteButton(deleteButton);

        Button zeroButton = (Button)bottomRow.getChildAt(1);
        configureAsZeroButton(numberButtonListener, zeroButton);

        Button enterButton = (Button)bottomRow.getChildAt(2);
        configureAsEnterButton(enterButton);
    }

    private void configureAsDeleteButton(Button deleteButton) {
        deleteButton.setTextAppearance(getActivity(), R.style.RemoteButton_bold);
        deleteButton.setText(R.string.delete);

        View.OnClickListener deleteButtonListener = new View.OnClickListener() {
            public void onClick(View view) {
                mWorkingTextView.setText(R.string.zero);
            }
        };
        deleteButton.setOnClickListener(deleteButtonListener);
    }

    private void configureAsZeroButton(View.OnClickListener numberButtonListener, Button zeroButton) {
        zeroButton.setText(R.string.zero);
        zeroButton.setOnClickListener(numberButtonListener);
    }

    private void configureAsEnterButton(Button enterButton) {
        enterButton.setTextAppearance(getActivity(), R.style.RemoteButton_bold);
        enterButton.setText(R.string.enter);

        View.OnClickListener enterButtonListener = new View.OnClickListener() {
            public void onClick(View view) {
                CharSequence working = mWorkingTextView.getText();
                if (working.length() > 0) {
                    mSelectedTextView.setText(working);
                }
                mWorkingTextView.setText(R.string.zero);
            }
        };
        enterButton.setOnClickListener(enterButtonListener);
    }

}
