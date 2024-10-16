// Generated by view binder compiler. Do not edit!
package com.example.parameters_activities_01.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.parameters_activities_01.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class Fragment02Binding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final Button btnReturn;

  @NonNull
  public final EditText editTextNumber;

  @NonNull
  public final EditText editTextText;

  @NonNull
  public final TextView labelTitle;

  private Fragment02Binding(@NonNull NestedScrollView rootView, @NonNull Button btnReturn,
      @NonNull EditText editTextNumber, @NonNull EditText editTextText,
      @NonNull TextView labelTitle) {
    this.rootView = rootView;
    this.btnReturn = btnReturn;
    this.editTextNumber = editTextNumber;
    this.editTextText = editTextText;
    this.labelTitle = labelTitle;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static Fragment02Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static Fragment02Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_02, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static Fragment02Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_return;
      Button btnReturn = ViewBindings.findChildViewById(rootView, id);
      if (btnReturn == null) {
        break missingId;
      }

      id = R.id.editTextNumber;
      EditText editTextNumber = ViewBindings.findChildViewById(rootView, id);
      if (editTextNumber == null) {
        break missingId;
      }

      id = R.id.editTextText;
      EditText editTextText = ViewBindings.findChildViewById(rootView, id);
      if (editTextText == null) {
        break missingId;
      }

      id = R.id.labelTitle;
      TextView labelTitle = ViewBindings.findChildViewById(rootView, id);
      if (labelTitle == null) {
        break missingId;
      }

      return new Fragment02Binding((NestedScrollView) rootView, btnReturn, editTextNumber,
          editTextText, labelTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}